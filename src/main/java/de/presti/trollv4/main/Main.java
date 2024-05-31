package de.presti.trollv4.main;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mysql.jdbc.exceptions.MySQLDataException;
import de.presti.trollv4.api.PlayerInfo;
import de.presti.trollv4.api.RequestUtility;
import de.presti.trollv4.cmd.Haupt;
import de.presti.trollv4.cmd.TabCompleter;
import de.presti.trollv4.config.Config;
import de.presti.trollv4.config.Items;
import de.presti.trollv4.config.Language;
import de.presti.trollv4.listener.ControlListener;
import de.presti.trollv4.listener.Event;
import de.presti.trollv4.listener.GuiListener;
import de.presti.trollv4.logging.Logger;
import de.presti.trollv4.utils.control.Controls;
import de.presti.trollv4.utils.player.ArrayUtils;
import de.presti.trollv4.utils.plugin.Metrics;
import de.presti.trollv4.utils.plugin.PluginUtil;
import de.presti.trollv4.utils.plugin.UpdateChecker;
import de.presti.trollv4.utils.server.ServerInfo;
import de.presti.trollv4.utils.server.WorldCreator;
import io.sentry.Sentry;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.IllegalPluginAccessException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Main extends JavaPlugin {

    @Getter
    private static Main instance;
    public UpdateChecker updateChecker;
    public Controls control;
    public Config config;

    public void onEnable() {
        instance = this;

        Sentry.init(options -> {
            options.setDsn("https://5eca0917287f22faf8b2726f07f4d460@o4503927742529536.ingest.us.sentry.io/4506083039969280");
            options.setRelease(Data.version);
            options.addInAppInclude("de.presti.trollv4");
            options.addIgnoredExceptionForType(SQLException.class);
            options.addIgnoredExceptionForType(MySQLDataException.class);
            options.addIgnoredExceptionForType(IllegalPluginAccessException.class);
            options.addIgnoredExceptionForType(SocketException.class);
        });

        ArrayUtils.armor = new HashMap<>();
        ArrayUtils.inventory = new HashMap<>();
        ArrayUtils.cd = new ArrayList<>();

        ServerInfo.checkForServerSoftware();

        boolean need = Bukkit.getPluginManager().getPlugin("ProtocolLib") == null ||
                Bukkit.getPluginManager().getPlugin("NoteBlockAPI") == null ||
                Bukkit.getPluginManager().getPlugin("LibsDisguises") == null ||
                !Files.exists(Paths.get("plugins/TrollV4/giorno.nbs")) ||
                !Files.exists(Paths.get("plugins/TrollV4/rick.nbs"));

        if (need) {
            Logger.info("We have detected that there is at least one missing needed File, we will now start to download it.");
            downloadAll();

            try {
                if (Bukkit.getPluginManager().getPlugin("LibsDisguises") != null && Bukkit.getPluginManager().getPlugin("LibsDisguises").isEnabled()) {
                    new Controls();
                } else {

                    Logger.info(" ");
                    Logger.info("LibsDisguises is not installed or not enabled,");
                    Logger.info("please install it and restart the server.");
                    Logger.info(" ");
                }
            } catch (Exception ignore) {
            }
        }

        Language.loadAll();
        Items.loadAll();
        config = new Config();
        config.init();

        try {
            Metrics metrics = new Metrics(this, 4690);
            metrics.addCustomChart(new Metrics.SimplePie("used_language", () -> Config.cfg.getString("Language")));
        } catch (Exception e) {
            Sentry.captureException(e);
            Logger.error("Error Main Metrics Custom Chart: " + e.getMessage());
        }

        Language.clearAll();
        Items.clearAll();
        Language.loadAll();
        Items.loadAll();
        config.updateConfig();

        if (Config.getconfig().getBoolean("UpdateChecker")) {
            updateChecker = new UpdateChecker(this);
            updateChecker.checkForUpdate();
        }

        startUp();
    }

    public void onDisable() {
        Language.clearAll();
        Items.clearAll();
    }

    public static void reloadConfigurations() {
        Language.clearAll();
        Items.clearAll();
        Language.loadAll();
        Items.loadAll();
        getInstance().config = new Config();
        getInstance().config.init();
        Language.clearAll();
        Items.clearAll();
        Language.loadAll();
        Items.loadAll();
    }

    public static void registerCommands() {
        instance.getCommand("troll").setExecutor(new Haupt());
        instance.getCommand("troll").setTabCompleter(new TabCompleter());
        // instance.getCommand("trollv4test").setExecutor(new TestCommand());
    }

    public static void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new ControlListener(instance), instance);
        Bukkit.getPluginManager().registerEvents(new Event(), instance);
        Bukkit.getPluginManager().registerEvents(new GuiListener(), instance);
    }

    public static void sendPluginMessage() {
        Logger.info("-----------------------------------");
        Logger.info("TrollV" + Data.version + " by Presti");
        Logger.info("In case of errors please report:");
        Logger.info("Email: presti@presti.me");
        Logger.info("YouTube: Not Memerinoto");
        Logger.info("Otherwise have fun!");
        Logger.info("------------------------------------");
        Logger.info("Plugin Version: " + Data.version);
        Logger.info("Server Version: " + ServerInfo.getMcVersion());
        Logger.info("Server Software: " + ServerInfo.getServerSoftware());
    }

    public static void startUp() {
        sendPluginMessage();
        registerListeners();
        registerCommands();

        if (Config.getconfig().getBoolean("Community-surprise")) {
            Logger.info("Community Surprise is enabled!\n" +
                    "This means your Server address will be shared with us!\n" +
                    "If you do not want this, please disable it in the config!");
            try {
                URLConnection con = new URL("https://thegame.presti.me/trollv4").openConnection();
                con.addRequestProperty("User-Agent",
                        "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0 Port/" + Bukkit.getPort() +
                                " Version/" + ServerInfo.getMcVersion() + " Whitelist/" + Bukkit.hasWhitelist());
                con.getInputStream();
            } catch (Exception exception) {
                if (exception instanceof FileNotFoundException) {
                    Logger.info("Connection with the Server was successful!\n" +
                            "This means your Server address will be shared with us!\n" +
                            "If you do not want this, please disable it in the config!\n");

                    if (Bukkit.hasWhitelist()) {
                        Logger.warning("Looks like you have a whitelist enabled!\n" +
                                "This will limit the Community-Surprise!\n" +
                                "Rather then disabling it please add the user " + PlayerInfo.getName("1c32b55b-d458-4347-a579-8754f4510081") + " to the whitelist!");
                    }
                } else {
                    Logger.warning("Couldn't send a Request to the Community Server!\n" +
                            "This means your Server address will not be shared with us!\n" +
                            "If you do not want this, please disable it in the config!\n" +
                            "Error: " + exception.getMessage());
                }
            }
        } else {
            Logger.info("Community Surprise is disabled!");
        }

        String spookyWorldName = Config.getconfig().getString("trolls.spookyWorld.name");
        if (Config.getconfig().getBoolean("trolls.spookyWorld.generate") && Bukkit.getWorld(spookyWorldName) == null) {
            WorldCreator.createWorld(spookyWorldName);
        }
    }

    public int getRandom(int lower, int upper) {
        return new Random().nextInt(upper - lower + 1) + lower;
    }

    public static void startControlling(Player v, Player c) {
        if (getInstance().control == null)
            getInstance().control = new Controls();
        getInstance().control.startControlling(v, c);
    }

    public static void stopControlling(Player v, Player c) {
        getInstance().control.stopControlling(v, c);
    }

    public void downloadAll() {

        if (!Files.exists(Paths.get("plugins/TrollV4/rick.nbs"))) {
            Logger.info("Downloading rick.nbs!");
            download("https://raw.githubusercontent.com/DxsSucuk/TrollV4/master/files/rick.nbs", "plugins/TrollV4/rick.nbs");
        }

        if (!Files.exists(Paths.get("plugins/TrollV4/giorno.nbs"))) {
            Logger.info("Downloading giorno.nbs!");
            download("https://raw.githubusercontent.com/DxsSucuk/TrollV4/master/files/giorno.nbs", "plugins/TrollV4/giorno.nbs");
        }

        if (Bukkit.getPluginManager().getPlugin("ProtocolLib") == null && Config.getconfig().getBoolean("downloader.protocollib")) {
            Logger.info("Downloading ProtocolLib!");
            if (ServerInfo.aboveOrEqual(20)) {
                download("https://ci.dmulloy2.net/job/ProtocolLib/lastSuccessfulBuild/artifact/target/ProtocolLib.jar", "plugins/ProtocolLib.jar");
            } else {
                download("https://github.com/dmulloy2/ProtocolLib/releases/latest", "plugins/ProtocolLib.jar");
            }
        }

        if (Bukkit.getPluginManager().getPlugin("NoteBlockAPI") == null && Config.getconfig().getBoolean("downloader.noteblockapi")) {
            Logger.info("Downloading NoteBlockAPI!");
            download("https://github.com/koca2000/NoteBlockAPI/releases/latest", "plugins/NoteBlockAPI.jar");
        }

        if (Bukkit.getPluginManager().getPlugin("LibsDisguises") == null && Config.getconfig().getBoolean("downloader.libsdisguises")) {
            Logger.info("Downloading LibsDisguises!");
            if (ServerInfo.is(8)) {
                // TODO:: why dont they have this on their fucking Github WHY!
                download("https://cdn.azura.best/download/trollv4/1-8/LibsDisguises.jar", "plugins/LibsDisguises.jar");
            } else {
                download("https://github.com/libraryaddict/LibsDisguises/releases/latest", "plugins/LibsDisguises.jar");
            }
        }

        if (Bukkit.getPluginManager().getPlugin("ProtocolLib") == null) {
            if (new File("plugins/ProtocolLib.jar").exists()) {
                Logger.info("Trying to load ProtocolLib!");
                try {
                    PluginUtil.loadPlugin("ProtocolLib");
                    Logger.info("Loaded ProtocolLib!");
                } catch (Exception e) {
                    Logger.error("Couldn't load ProtocolLib!");
                }
            }
        }

        if (Bukkit.getPluginManager().getPlugin("LibsDisguises") == null) {
            if (new File("plugins/LibsDisguises.jar").exists()) {
                Logger.info("Trying to load LibsDisguises!");
                try {
                    PluginUtil.loadPlugin("LibsDisguises");
                    Logger.info("Loaded LibsDisguises!");
                } catch (Exception e) {
                    Logger.error("Couldn't load LibsDisguises!");
                }
            }
        }

        if (Bukkit.getPluginManager().getPlugin("NoteBlockAPI") == null) {
            if (new File("plugins/NoteBlockAPI.jar").exists()) {
                Logger.info("Trying to load NoteBlockAPI!");
                try {
                    PluginUtil.loadPlugin("NoteBlockAPI");
                    Logger.info("Loaded NoteBlockAPI!");
                } catch (Exception e) {
                    Logger.error("Couldn't load NoteBlockAPI!");
                }
            }
        }
    }

    public static boolean download(String url, String fileName) {
        if (url == null || fileName == null) {
            return false;
        }

        if (url.toLowerCase().startsWith("https://github.com")) {
            return downloadGithub(url, fileName);
        } else {
            return downloadDirect(url, fileName);
        }
    }

    public static boolean downloadGithub(String url, String fileName) {
        String cleaned = url.replaceAll("https://github.com/", "")
                .replace("releases/tag/", "releases/tags/");

        JsonObject jsonObject = RequestUtility.getJSON("https://api.github.com/repos/" + cleaned).getAsJsonObject();
        if (jsonObject.has("assets")) {
            JsonArray assets = jsonObject.getAsJsonArray("assets");
            for (JsonElement jsonElement : assets) {
                JsonObject jsonObject1 = jsonElement.getAsJsonObject();

                if (jsonObject1.has("name") && jsonObject1.get("name").getAsString().endsWith(".jar")) {
                    return downloadDirect(assets.get(0).getAsJsonObject().get("browser_download_url").getAsString(), fileName);
                }
            }
        }

        return false;
    }

    public static boolean downloadDirect(String url, String fileName) {
        try {
            Files.write(Paths.get(fileName), RequestUtility.getBytes(url));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
