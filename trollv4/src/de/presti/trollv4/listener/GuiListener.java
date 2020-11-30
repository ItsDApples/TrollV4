package de.presti.trollv4.listener;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XPotion;
import com.cryptomorin.xseries.XSound;

import de.presti.trollv4.api.PlayMusic;
import de.presti.trollv4.api.TrollV4API;
import de.presti.trollv4.cmd.Haupt;
import de.presti.trollv4.config.Config;
import de.presti.trollv4.config.Items;
import de.presti.trollv4.config.Language;
import de.presti.trollv4.invs.*;
import de.presti.trollv4.main.*;
import de.presti.trollv4.utils.*;
import de.presti.trollv4.utils.crossversion.DemoScreen;
import de.presti.trollv4.utils.crossversion.HS;
import de.presti.trollv4.utils.crossversion.Titles;
import de.presti.trollv4.utils.player.ArrayUtils;
import de.presti.trollv4.utils.player.LocationUtil;
import net.jitse.npclib.NPCLib;
import net.jitse.npclib.api.NPC;
import net.jitse.npclib.api.skin.MineSkinFetcher;
import net.jitse.npclib.api.skin.Skin;
import net.jitse.npclib.api.state.NPCAnimation;
import net.jitse.npclib.api.state.NPCSlot;
import net.jitse.npclib.api.state.NPCState;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright § Baris Arslan 2018											    *
*	Erstellt: 11.05.2019 / 23:33:04												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich gesch§tzt.			*
*	Das Urheberrecht liegt, soweit nicht ausdr§cklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielf§ltigung, Verbreitung, Vermietung, Verleihung,			*
*	§ffentlichen Zug§nglichmachung oder anderer Nutzung							*
*	bedarf der ausdr§cklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class GuiListener implements Listener {
	public int taskID;
	public int taskID2;

	// Troll Item Gui

	@EventHandler
	public void onInteract(InventoryClickEvent e) {
		try {
			Player p = (Player) e.getWhoClicked();

			ItemStack item = new ItemStack(XMaterial.DIAMOND_SWORD.parseMaterial());
			ItemMeta imeta = item.getItemMeta();
			imeta.setDisplayName(Items.getItem("gui.items.diamondsword"));
			imeta.addEnchant(XEnchantment.DAMAGE_ALL.parseEnchantment(), 1000, true);
			imeta.addEnchant(XEnchantment.FIRE_ASPECT.parseEnchantment(), 1000, true);
			item.setItemMeta(imeta);
			item.setAmount(1);

			ItemStack item2 = new ItemStack(XMaterial.DIAMOND_CHESTPLATE.parseMaterial());
			ItemMeta imeta2 = item2.getItemMeta();
			imeta2.setDisplayName(Items.getItem("gui.items.diamondprotectionchest"));
			imeta2.addEnchant(XEnchantment.PROTECTION_ENVIRONMENTAL.parseEnchantment(), 1000, true);
			item2.setItemMeta(imeta2);
			item2.setAmount(1);

			ItemStack item3 = new ItemStack(XMaterial.DIAMOND_CHESTPLATE.parseMaterial());
			ItemMeta imeta3 = item3.getItemMeta();
			imeta3.setDisplayName(Items.getItem("gui.items.diamondthornschest"));
			imeta3.addEnchant(XEnchantment.THORNS.parseEnchantment(), 1000, true);
			item3.setItemMeta(imeta3);
			item3.setAmount(1);

			ItemStack item4 = new ItemStack(XMaterial.DIAMOND_PICKAXE.parseMaterial());
			ItemMeta imeta4 = item4.getItemMeta();
			imeta4.setDisplayName(Items.getItem("gui.items.diamondpickaxe"));
			imeta4.addEnchant(XEnchantment.DIG_SPEED.parseEnchantment(), 1000, true);
			item4.setItemMeta(imeta4);
			item4.setAmount(1);

			ItemStack item5 = new ItemStack(XMaterial.BOW.parseMaterial());
			ItemMeta imeta5 = item5.getItemMeta();
			imeta5.setDisplayName(Items.getItem("gui.items.onehitbow"));
			imeta5.addEnchant(XEnchantment.ARROW_INFINITE.parseEnchantment(), 1000, true);
			imeta5.addEnchant(XEnchantment.ARROW_KNOCKBACK.parseEnchantment(), 1000, true);
			imeta5.addEnchant(XEnchantment.ARROW_DAMAGE.parseEnchantment(), 1000, true);
			item5.setItemMeta(imeta5);
			item5.setAmount(1);

			ItemStack item6 = new ItemStack(XMaterial.WOODEN_HOE.parseMaterial());
			ItemMeta imeta6 = item6.getItemMeta();
			imeta6.setDisplayName(Items.getItem("gui.items.woodenhoe"));
			imeta6.addEnchant(XEnchantment.DAMAGE_ALL.parseEnchantment(), 1000, true);
			imeta6.addEnchant(XEnchantment.FIRE_ASPECT.parseEnchantment(), 1000, true);
			item6.setItemMeta(imeta6);
			item6.setAmount(1);

			ItemStack item7 = new ItemStack(XMaterial.WOODEN_SWORD.parseMaterial());
			ItemMeta imeta7 = item7.getItemMeta();
			imeta7.setDisplayName(Items.getItem("gui.items.woodensword"));
			imeta7.addEnchant(XEnchantment.DAMAGE_ALL.parseEnchantment(), 1000, true);
			imeta7.addEnchant(XEnchantment.FIRE_ASPECT.parseEnchantment(), 1000, true);
			item7.setItemMeta(imeta7);
			item7.setAmount(1);

			ItemStack tntbow = InvManager.createItem(XMaterial.BOW.parseMaterial(), 1,
					Items.getItem("gui.items.bow.tnt"), "§eTNT-Arrows");
			ItemStack lavabow = InvManager.createItem(XMaterial.BOW.parseMaterial(), 1,
					Items.getItem("gui.items.bow.lava"), "§eLava-Arrows");
			ItemStack strikebow = InvManager.createItem(XMaterial.BOW.parseMaterial(), 1,
					Items.getItem("gui.items.bow.lightning"), "§eLightning-Arrows");
			ItemStack creeperbow = InvManager.createItem(XMaterial.BOW.parseMaterial(), 1,
					Items.getItem("gui.items.bow.creeper"), "§eCreeper-Arrows");
			ItemStack bedrockbow = InvManager.createItem(XMaterial.BOW.parseMaterial(), 1,
					Items.getItem("gui.items.bow.bedrock"), "§eBedrock-Arrows");

			ItemStack minigun = new ItemStack(XMaterial.IRON_AXE.parseMaterial());
			ItemMeta meta = minigun.getItemMeta();
			meta.setDisplayName(Items.getItem("gui.items.minigun"));
			minigun.setItemMeta(meta);

			ItemStack fireball = new ItemStack(XMaterial.STICK.parseMaterial());
			ItemMeta fmeta = fireball.getItemMeta();
			fmeta.setDisplayName(Items.getItem("gui.items.fireball"));
			fireball.setItemMeta(fmeta);

			if (e.getView().getTitle().equalsIgnoreCase("§2Item Troll Menu")) {
				e.setCancelled(true);
				if (p.hasPermission("troll.items")) {

					if (e.getCurrentItem().getItemMeta() == null) {
						return;
					}

					if (e.getCurrentItem().getType() == XMaterial.AIR.parseMaterial()) {
						return;
					}

					if (e.getCurrentItem().getItemMeta().equals(item.getItemMeta())) {
						p.getInventory().addItem(item);
					} else if (e.getCurrentItem().getItemMeta().equals(item2.getItemMeta())) {
						p.getInventory().addItem(item2);
					} else if (e.getCurrentItem().getItemMeta().equals(item3.getItemMeta())) {
						p.getInventory().addItem(item3);
					} else if (e.getCurrentItem().getItemMeta().equals(item4.getItemMeta())) {
						p.getInventory().addItem(item4);
					} else if (e.getCurrentItem().getItemMeta().equals(item5.getItemMeta())) {
						p.getInventory().addItem(item5);
					} else if (e.getCurrentItem().getItemMeta().equals(item6.getItemMeta())) {
						p.getInventory().addItem(item6);
					} else if (e.getCurrentItem().getItemMeta().equals(item7.getItemMeta())) {
						p.getInventory().addItem(item7);
					} else if (e.getCurrentItem().getItemMeta().equals(tntbow.getItemMeta())) {
						p.getInventory().addItem(tntbow);
					} else if (e.getCurrentItem().getItemMeta().equals(lavabow.getItemMeta())) {
						p.getInventory().addItem(lavabow);
					} else if (e.getCurrentItem().getItemMeta().equals(strikebow.getItemMeta())) {
						p.getInventory().addItem(strikebow);
					} else if (e.getCurrentItem().getItemMeta().equals(creeperbow.getItemMeta())) {
						p.getInventory().addItem(creeperbow);
					} else if (e.getCurrentItem().getItemMeta().equals(bedrockbow.getItemMeta())) {
						p.getInventory().addItem(bedrockbow);
					} else if (e.getCurrentItem().getItemMeta().equals(fireball.getItemMeta())) {
						p.getInventory().addItem(fireball);
					} else if (e.getCurrentItem().getItemMeta().equals(minigun.getItemMeta())) {
						p.getInventory().addItem(minigun);
					}

					p.playSound(p.getLocation(), XSound.ENTITY_PLAYER_LEVELUP.parseSound(), 3.0F, 2.0F);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	// Troll Rest Guis

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onTrollGuiClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		try {
			if (e.getView().getTitle().equalsIgnoreCase("§2Player Choice Menu")) {
				e.getResult();
				e.setResult(Result.DENY);
				if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null) {
					if (e.getCurrentItem().getType() == XMaterial.PLAYER_HEAD.parseItem().getType()) {
						for (Player all : Bukkit.getOnlinePlayers()) {
							if (e.getCurrentItem().getItemMeta().getDisplayName().replace("§2", "")
									.equalsIgnoreCase(all.getName())) {
								if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
									ArrayUtils.trolling.put(p.getName(), all.getName());
									e.getView().close();
									new InvManager().openPlayerInv(p);
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
									e.getView().close();
								}
							}
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bNext Page")) {
						int page = Integer.parseInt(
								e.getInventory().getItem(45).getItemMeta().getDisplayName().replaceAll("§cPage: ", ""));

						ItemStack pagei = XMaterial.RED_STAINED_GLASS_PANE.parseItem();
						ItemMeta pagem = pagei.getItemMeta();
						pagem.setDisplayName("§cPage: " + page + 1);
						pagei.setItemMeta(pagem);

						ItemStack pagen = XMaterial.BLUE_STAINED_GLASS_PANE.parseItem();
						ItemMeta pagenm = pagen.getItemMeta();
						pagenm.setDisplayName("§bNext Page");

						pagen.setItemMeta(pagenm);

						e.getInventory().clear();

						ItemStack gl = XMaterial.BLACK_STAINED_GLASS_PANE.parseItem();
						ItemMeta glm = gl.getItemMeta();
						glm.setDisplayName("§7");
						gl.setItemMeta(glm);

						e.getInventory().setItem(46, gl);
						e.getInventory().setItem(47, gl);
						e.getInventory().setItem(48, gl);
						e.getInventory().setItem(49, gl);
						e.getInventory().setItem(51, gl);
						e.getInventory().setItem(52, gl);
						e.getInventory().setItem(53, gl);

						if (Bukkit.getOnlinePlayers().size() > (45 * (page))) {

							ArrayList<ItemStack> items = new ArrayList<ItemStack>();

							e.getInventory().setItem(45, pagei);

							if (Data.async) {
								new BukkitRunnable() {

									@Override
									public void run() {
										int i = 0;
										for (Player all : Bukkit.getOnlinePlayers()) {
											if (i > (45 * page)) {
												items.add(SetItems.buildSkull(all.getName(), "§2" + all.getName()));
											}
											i++;
										}

										new BukkitRunnable() {

											@Override
											public void run() {

												for (ItemStack is : items) {
													e.getInventory().addItem(is);
												}

											}
										}.runTask(Main.instance);
									}
								}.runTaskAsynchronously(Main.instance);
							} else {
								int i = 0;
								for (Player all : Bukkit.getOnlinePlayers()) {
									if (i > (45 * page)) {
										e.getInventory()
												.addItem(SetItems.buildSkull(all.getName(), "§2" + all.getName()));
									}
									i++;
								}
							}
						}

						if (Bukkit.getOnlinePlayers().size() > (45 * (page + 1))) {
							e.getInventory().setItem(50, pagen);
						} else {
							pagenm.setDisplayName("§cNo Next Page");
							pagen.setItemMeta(pagenm);
							e.getInventory().setItem(50, pagen);
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cConfiguration")) {
						if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
							e.getView().close();
							InvManager.openConfigInv(p);
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					}
				}
			} else if (e.getView().getTitle().equalsIgnoreCase("§cTroll Config Menu")) {
				e.getResult();
				e.setResult(Result.DENY);

				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cBack")) {
					if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
						e.getView().close();
						new InvManager().choicePlayer(p);
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cReload Config")) {
					if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
						e.getView().close();
						Main.reloadConfigurations();
						p.sendMessage(Data.prefix + "§cReloaded!");
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase("§cCustom§7-§2Item§7-§2Name")) {
					if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
						Config.cfg.set("Custom-Item-Name", !Config.cfg.getBoolean("Custom-Item-Name"));
						Config.cfg.save(Config.getFile());

						e.getInventory().setItem(0,
								SetItems.buildItem("§cCustom§7-§2Item§7-§2Name", XMaterial.PAPER,
										new String[] { "§cCurrent Value:",
												(Config.cfg.getBoolean("Custom-Item-Name") ? "§ayes" : "§cno") }));

					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bUpdateChecker")) {
					if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
						Config.cfg.set("UpdateChecker", !Config.cfg.getBoolean("UpdateChecker"));
						Config.cfg.save(Config.getFile());

						e.getInventory().setItem(1,
								SetItems.buildItem("§bUpdateChecker", XMaterial.CLOCK,
										new String[] { "§cCurrent Value:",
												(Config.cfg.getBoolean("UpdateChecker") ? "§ayes" : "§cno") }));

					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cAuto§bUpdate")) {
					if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
						Config.cfg.set("AutoUpdate", !Config.cfg.getBoolean("AutoUpdate"));
						Config.cfg.save(Config.getFile());

						e.getInventory().setItem(2,
								SetItems.buildItem("§cAuto§bUpdate", XMaterial.CAULDRON,
										new String[] { "§cCurrent Value:",
												(Config.cfg.getBoolean("AutoUpdate") ? "§ayes" : "§cno") }));

					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2Animations")) {
					if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
						Config.cfg.set("Animations", !Config.cfg.getBoolean("Animations"));
						Config.cfg.save(Config.getFile());

						e.getInventory().setItem(3,
								SetItems.buildItem("§2Animations", XMaterial.GLASS_PANE,
										new String[] { "§cCurrent Value:",
												(Config.cfg.getBoolean("Animations") ? "§ayes" : "§cno") }));

					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aASync")) {
					if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
						Config.cfg.set("ASync", !Config.cfg.getBoolean("ASync"));
						Config.cfg.save(Config.getFile());

						e.getInventory().setItem(4, SetItems.buildItem("§aASync", XMaterial.PLAYER_HEAD, new String[] {
								"§cCurrent Value:", (Config.cfg.getBoolean("ASync") ? "§ayes" : "§cno") }));

					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase("§2Community§7-§csurprise")) {
					if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
						Config.cfg.set("Community-surprise", !Config.cfg.getBoolean("Community-surprise"));
						Config.cfg.save(Config.getFile());

						e.getInventory().setItem(5,
								SetItems.buildItem("§2Community§7-§csurprise", XMaterial.CAKE,
										new String[] { "§cCurrent Value:",
												(Config.cfg.getBoolean("Community-surprise") ? "§ayes" : "§cno") }));

					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				}

			} else if (e.getView().getTitle().equalsIgnoreCase("§2Which MLG?")) {
				e.getResult();
				e.setResult(Result.DENY);
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cBack")) {
					if (p.hasPermission("troll.mlg") || p.hasPermission("troll.*")) {
						e.getView().close();
						new InvManager().openPlayerInv(p);
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(Items.getItem("gui.mlgs.water"))) {
					Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
					if (p.hasPermission("troll.mlg") || p.hasPermission("troll*")) {
						e.getView().close();
						if (t != null) {
							p.sendMessage(Data.prefix + Language.getMessage("gui.mlg", t));
							e.getView().close();
							t.setAllowFlight(true);
							t.setVelocity(t.getLocation().getDirection().multiply(0.5D).setY(3.8D));
							t.playSound(t.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
							t.playSound(t.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F, 25.0F);
							ItemStack bucket = new ItemStack(XMaterial.WATER_BUCKET.parseItem());
							ItemMeta meta = bucket.getItemMeta();
							meta.setDisplayName("§cMLG§8-§bBucket");
							bucket.setItemMeta(meta);
							t.getInventory().addItem(new ItemStack[] { bucket });
							Titles.send(t, 1, 10, 1, "§2MAKE A §cMLG", "");
							t.setAllowFlight(false);
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("noonline"));
							e.getView().close();
						}
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(Items.getItem("gui.mlgs.lava"))) {
					Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
					if (p.hasPermission("troll.mlg") || p.hasPermission("troll.*")) {
						e.getView().close();
						if (t != null) {
							p.sendMessage(Data.prefix + Language.getMessage("gui.mlg", t));
							t.setAllowFlight(true);
							t.setVelocity(t.getLocation().getDirection().multiply(0.5D).setY(3.8D));
							t.playSound(t.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
							t.playSound(t.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F, 25.0F);
							ItemStack bucket = new ItemStack(XMaterial.LAVA_BUCKET.parseItem());
							ItemMeta meta = bucket.getItemMeta();
							meta.setDisplayName("§cMLG§8-§bBucket");
							bucket.setItemMeta(meta);
							t.getInventory().addItem(new ItemStack[] { bucket });
							Titles.send(t, 1, 10, 1, "§2MAKE A §cMLG", "");
							t.setAllowFlight(false);
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("noonline"));
							e.getView().close();
						}
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(Items.getItem("gui.mlgs.cobweb"))) {
					Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
					if (p.hasPermission("troll.mlg") || p.hasPermission("troll.*")) {
						e.getView().close();
						if (t != null) {
							p.sendMessage(Data.prefix + Language.getMessage("gui.mlg", t));
							t.setAllowFlight(true);
							t.setVelocity(t.getLocation().getDirection().multiply(0.5D).setY(3.8D));
							t.playSound(t.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
							t.playSound(t.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F, 25.0F);
							ItemStack web = new ItemStack(XMaterial.COBWEB.parseItem());
							ItemMeta webm = web.getItemMeta();
							webm.setDisplayName("§cMLG§8-§bWeb");
							web.setItemMeta(webm);
							t.getInventory().addItem(new ItemStack[] { web });
							Titles.send(t, 1, 10, 1, "§2MAKE A §cMLG", "");
							t.setAllowFlight(false);
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(Items.getItem("gui.mlgs.slime"))) {
					Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
					if (p.hasPermission("troll.mlg") || p.hasPermission("troll.*")) {
						e.getView().close();
						if (t != null) {
							p.sendMessage(Data.prefix + Language.getMessage("gui.mlg", t));
							t.setAllowFlight(true);
							t.setVelocity(t.getLocation().getDirection().multiply(0.5D).setY(3.8D));
							t.playSound(t.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
							t.playSound(t.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F, 25.0F);
							ItemStack slime = new ItemStack(XMaterial.SLIME_BLOCK.parseItem());
							ItemMeta slimem = slime.getItemMeta();
							slimem.setDisplayName("§cMLG§8-§aSlime");
							slime.setItemMeta(slimem);
							t.getInventory().addItem(new ItemStack[] { slime });
							Titles.send(t, 1, 10, 1, "§2MAKE A §cMLG", "");
							t.setAllowFlight(false);
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("noonline"));
							e.getView().close();
						}
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				}
			}
			if (e.getView().getTitle().equalsIgnoreCase("§2Player Troll Menu")) {
				e.getResult();
				e.setResult(Result.DENY);
				if (e.getCurrentItem() == null && e.getCurrentItem().getItemMeta() == null) {
				} else {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cRechoice")) {
						if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
							ArrayUtils.trolling.remove(p.getName());
							e.getView().close();
							new InvManager().choicePlayer(p);
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cNext Page")) {
						if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
							InvManager.setPageTwoTrolls(e.getClickedInventory(), p);
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.explode"))) {
						Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
						if (p.hasPermission("troll.explode") || p.hasPermission("troll.*")) {
							if (t != null) {
								t.playSound(t.getLocation(), XSound.ENTITY_FIREWORK_ROCKET_LAUNCH.parseSound(), 100.0F,
										200.0F);
								t.getWorld().createExplosion(t.getLocation(), 3.0F);
								t.setHealth(0.0D);
								p.sendMessage(Data.prefix + Language.getMessage("gui.explode", t));
								e.getView().close();
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.fakehack"))) {
						Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
						if (p.hasPermission("troll.fakehack") || p.hasPermission("troll.*")) {
							if (t != null) {
								if (ArrayUtils.fc.contains(t)) {
									p.sendMessage(Data.prefix + Language.getMessage("gui.fakehack.off", t));
									e.getView().close();
									t.setWalkSpeed(0.2F);
									t.setAllowFlight(false);
									ArrayUtils.fc.remove(t);
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("gui.fakehack.on", t));
									e.getView().close();
									ArrayUtils.fc.add(t);

								}
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.demo"))) {
						Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
						if (p.hasPermission("troll.demo") || p.hasPermission("troll.*")) {
							if (t != null) {
								p.sendMessage(Data.prefix + Language.getMessage("gui.demo", t));
								e.getView().close();
								DemoScreen.showDemoScreen(t);
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.strike"))) {
						if (p.hasPermission("troll.strike") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								p.sendMessage(Data.prefix + Language.getMessage("gui.strike", t));
								e.getView().close();
								t.getLocation().getWorld().strikeLightning(t.getLocation());
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.hackuser"))) {
						Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
						if (p.hasPermission("troll.hackuser") || p.hasPermission("troll.*")) {
							if (t != null) {
								p.sendMessage(Data.prefix + Language.getMessage("gui.hackuser", t));
								e.getView().close();
								if (ArrayUtils.hackuser.containsKey(t)) {
									ArrayUtils.hackuser.get(t).cancel();
									ArrayUtils.hackuser.remove(t);
									ArrayUtils.hackuser.put(t, new BukkitRunnable() {
										int countdown = Config.cfg.getInt("trolls.hack.time");

										@Override
										public void run() {
											if (countdown <= 0) {
												HS.Hack(t);
												Titles.send(t, 1, 20, 1, "§cHACKED", "§4" + Main.getRandomID());
												ArrayUtils.hackuser.get(t).cancel();

												return;
											}
											HS.Hack2(t);
											Titles.send(t, 1, 20, 1, "§cHacking in " + countdown,
													"§4" + Main.getRandomID());
											t.damage(0.1D);
											countdown--;
										}

									});
									ArrayUtils.hackuser.get(t).runTaskTimer(Main.getPlugin(), 0, 20);

								} else {
									ArrayUtils.hackuser.put(t, new BukkitRunnable() {
										int countdown = Config.cfg.getInt("trolls.hack.time");

										@Override
										public void run() {
											if (countdown <= 0) {
												HS.Hack(t);
												Titles.send(t, 1, 20, 1, "§cHACKED", "§4" + Main.getRandomID());
												ArrayUtils.hackuser.get(t).cancel();

												return;
											}
											HS.Hack2(t);
											Titles.send(t, 1, 20, 1, "§cHacking in " + countdown,
													"§4" + Main.getRandomID());
											t.damage(0.1D);
											countdown--;
										}

									});
									ArrayUtils.hackuser.get(t).runTaskTimer(Main.getPlugin(), 0, 20);
								}
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.rocket"))) {
						Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
						if (p.hasPermission("troll.rocket") || p.hasPermission("troll.*")) {
							if (t != null) {
								p.sendMessage(Data.prefix + Language.getMessage("gui.rocket", t));
								e.getView().close();
								t.setAllowFlight(true);
								t.setVelocity(t.getLocation().getDirection().multiply(0.5D).setY(3.8D));
								t.playSound(t.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
								t.playSound(t.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F,
										25.0F);
								t.setAllowFlight(false);
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}

					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.mlg"))) {
						if (p.hasPermission("troll.mlg") || p.hasPermission("troll.*")) {
							new InvManager().openMLGchoiceInv(p);
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.spam"))) {
						Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
						if (p.hasPermission("troll.spam") || p.hasPermission("troll.*")) {
							if (t != null) {
								p.sendMessage(Data.prefix + Language.getMessage("gui.spam", t));
								e.getView().close();
								new BukkitRunnable() {

									@Override
									public void run() {
										for (int i = 0; i < 1000; i++) {
											t.sendMessage("§cREEEEEEEEEEEEEEEEEEEEEEE!");
										}
									}
								}.runTaskAsynchronously(Main.instance);
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.startcontrol"))) {
						Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
						if (p.hasPermission("troll.control") || p.hasPermission("troll.*")) {
							if (!p.hasMetadata("C_H")) {
								if (t != null) {
									if (!t.hasMetadata("C_P")) {
										if (t != p) {
											if (!(t.hasPermission("troll.control"))) {
												Main.startControlling(t, p);
											} else {
												p.sendMessage(Data.prefix
														+ Language.getMessage("gui.startcontrol.cantcontrol", t));
												e.getView().close();
											}
										} else {
											p.sendMessage(
													Data.prefix + Language.getMessage("gui.startcontrol.yourself", t));
											e.getView().close();
										}
									} else {
										p.sendMessage(
												Data.prefix + Language.getMessage("gui.startcontrol.iscontroled", t));
										e.getView().close();
									}
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("noonline"));
									e.getView().close();
								}
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("gui.startcontrol.alreadyc", t));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.fakeop"))) {
						if (p.hasPermission("troll.fakeop") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								p.sendMessage(Data.prefix + Language.getMessage("gui.fakeop.default", t));
								e.getView().close();
								t.sendMessage(Language.getMessage("gui.fakeop.opm", t));
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.crash"))) {
						if (p.hasPermission("troll.crash") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								p.sendMessage(Data.prefix + Language.getMessage("gui.crash.default", t));
								e.getView().close();
								t.kickPlayer(Language.getMessage("gui.crash.message"));
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.freeze"))) {
						if (p.hasPermission("troll.freeze") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (ArrayUtils.freeze.contains(t)) {
									p.sendMessage(Data.prefix + Language.getMessage("gui.freeze.off", t));
									e.getView().close();
									t.setWalkSpeed(0.2F);
									t.setFlySpeed(0.1F);
									ArrayUtils.freeze.remove(t);
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("gui.freeze.on", t));
									e.getView().close();
									t.setWalkSpeed(0F);
									t.setFlySpeed(0F);
									ArrayUtils.freeze.add(t);
								}
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.anticheat"))) {
						if (p.hasPermission("troll.ac") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								p.sendMessage(Data.prefix + Language.getMessage("gui.anticheat.default", t));
								t.sendMessage(Language.getMessage("gui.anticheat.detected"));
								t.teleport(new Location(t.getWorld(), t.getLocation().getX(),
										t.getLocation().getY() + 2, t.getLocation().getZ()));
								e.getView().close();
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.lagging"))
							|| e.getCurrentItem().getType() == XMaterial.GRASS.parseMaterial()) {
						if (p.hasPermission("troll.lag") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (!ArrayUtils.lagging.contains(t)) {
									p.sendMessage(Data.prefix + Language.getMessage("gui.lag.on", t));
									ArrayUtils.lagging.add(t);
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("gui.lag.off", t));
									ArrayUtils.lagging.remove(t);
								}
								e.getView().close();
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.arrest"))) {
						if (p.hasPermission("troll.arrest") || p.hasPermission("troll.*")) {

							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								ArrayUtils.arrest.put(p, t.getLocation());
								t.teleport(ArrayUtils.arrest.get(p));
								// Y
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY() - 1, t.getLocation().getZ()))
										.setType(XMaterial.BEDROCK.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY() + 2, t.getLocation().getZ()))
										.setType(XMaterial.BEDROCK.parseMaterial());
								// X
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
												t.getLocation().getY(), t.getLocation().getZ()))
										.setType(XMaterial.BEDROCK.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
												t.getLocation().getY(), t.getLocation().getZ()))
										.setType(XMaterial.BEDROCK.parseMaterial());
								// Z
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY(), t.getLocation().getZ() + 1))
										.setType(XMaterial.BEDROCK.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY(), t.getLocation().getZ() - 1))
										.setType(XMaterial.BEDROCK.parseMaterial());

								p.sendMessage(Data.prefix + Language.getMessage("gui.arrest", t));
								e.getView().close();
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.rotateplayer"))) {
						if (p.hasPermission("troll.rotate") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (ArrayUtils.rotateplayer.contains(t)) {
									p.sendMessage(Data.prefix + Language.getMessage("gui.rotate.off", t));
									ArrayUtils.rotateplayer.remove(t);
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("gui.rotate.on", t));
									new Haupt().rop(t);
									ArrayUtils.rotateplayer.add(t);
								}
								e.getView().close();
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.randomteleport"))) {
						if (p.hasPermission("troll.randomtp") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (ArrayUtils.randomtp.contains(t)) {
									p.sendMessage(Data.prefix + Language.getMessage("gui.rndmtp.off", t));
									ArrayUtils.randomtp.remove(t);
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("gui.rndmtp.on", t));
									new Haupt().rtp(t);
									ArrayUtils.randomtp.add(t);
								}
								e.getView().close();
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.tnttrace"))) {
						if (p.hasPermission("troll.tnttrain") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (ArrayUtils.tntp.contains(t)) {
									p.sendMessage(Data.prefix + Language.getMessage("gui.tnttrace.off", t));
									ArrayUtils.tntp.remove(t);
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("gui.tnttrace.on", t));
									new Haupt().spawntntatplayer(t);
									ArrayUtils.tntp.add(t);
								}
								e.getView().close();
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.wtf"))) {
						if (p.hasPermission("troll.wtf") || p.hasPermission("troll.*")) {

							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {

								t.addPotionEffect(
										new PotionEffect(XPotion.BLINDNESS.parsePotionEffectType(), 200, 5, true));
								t.addPotionEffect(new PotionEffect(XPotion.CONFUSION.parsePotionEffectType(), 200, 2));
								t.addPotionEffect(
										new PotionEffect(XPotion.SLOW.parsePotionEffectType(), 200, 10, true));

								ArrayUtils.wtf.put(p, new BukkitRunnable() {
									int countdown = 4;

									@Override
									public void run() {

										if (countdown == 0) {
											t.teleport(t.getLocation());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
															t.getLocation().getY() + 1, t.getLocation().getZ() + 1))
													.setType(XMaterial.GLASS.parseMaterial());
											t.chat("Help me Pls im stucked ;-; I dont know where im pls help!!!");
											Bukkit.getScheduler().cancelTask(ArrayUtils.wtf.get(p).getTaskId());
											return;
										}

										if (countdown == 4) {
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
															t.getLocation().getY() - 1, t.getLocation().getZ()))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY() - 1, t.getLocation().getZ()))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY() - 1, t.getLocation().getZ()))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
															t.getLocation().getY() - 1, t.getLocation().getZ() + 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY() - 1, t.getLocation().getZ() + 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY() - 1, t.getLocation().getZ() + 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
															t.getLocation().getY() - 1, t.getLocation().getZ() - 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY() - 1, t.getLocation().getZ() - 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY() - 1, t.getLocation().getZ() - 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.teleport(t.getLocation());
										}

										if (countdown == 3) {
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY(), t.getLocation().getZ()))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY(), t.getLocation().getZ()))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
															t.getLocation().getY(), t.getLocation().getZ() + 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY(), t.getLocation().getZ() + 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY(), t.getLocation().getZ() + 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
															t.getLocation().getY(), t.getLocation().getZ() - 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY(), t.getLocation().getZ() - 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY(), t.getLocation().getZ() - 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY() + 1, t.getLocation().getZ()))
													.setType(XMaterial.GLASS.parseMaterial());
											t.teleport(t.getLocation());
										}

										if (countdown == 2) {
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY() + 1, t.getLocation().getZ() + 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY() + 1, t.getLocation().getZ() + 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY() + 1, t.getLocation().getZ() - 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY() + 1, t.getLocation().getZ() - 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
															t.getLocation().getY() + 1, t.getLocation().getZ() - 1))
													.setType(XMaterial.GLASS.parseMaterial());
											t.teleport(t.getLocation());
										}

										if (countdown == 1) {
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
															t.getLocation().getY() + 2, t.getLocation().getZ()))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY() + 2, t.getLocation().getZ()))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY() + 2, t.getLocation().getZ()))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
															t.getLocation().getY() + 2, t.getLocation().getZ() + 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY() + 2, t.getLocation().getZ() + 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY() + 2, t.getLocation().getZ() + 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
															t.getLocation().getY() + 2, t.getLocation().getZ() - 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY() + 2, t.getLocation().getZ() - 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY() + 2, t.getLocation().getZ() - 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY() + 1, t.getLocation().getZ()))
													.setType(XMaterial.GLASS.parseMaterial());
											t.teleport(t.getLocation());
										}

										if (countdown <= 0) {
											Bukkit.getScheduler().cancelTask(ArrayUtils.wtf.get(p).getTaskId());
										}
										countdown--;
									}
								});
								ArrayUtils.wtf.get(p).runTaskTimer(Main.instance, 0L, 20L);

								p.sendMessage(Data.prefix + Language.getMessage("gui.wtf", t));
								e.getView().close();
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.webtrap"))) {
						if (p.hasPermission("troll.webtrap") || p.hasPermission("troll.*")) {

							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								p.sendMessage(Data.prefix + Language.getMessage("gui.webtrap", t));
								e.getView().close();

								// Oben
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY() + 2, t.getLocation().getZ()))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
												t.getLocation().getY() + 2, t.getLocation().getZ()))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
												t.getLocation().getY() + 2, t.getLocation().getZ()))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY() + 2, t.getLocation().getZ() + 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY() + 2, t.getLocation().getZ() - 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
												t.getLocation().getY() + 2, t.getLocation().getZ() + 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
												t.getLocation().getY() + 2, t.getLocation().getZ() + 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY() + 2, t.getLocation().getZ() - 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
												t.getLocation().getY() + 2, t.getLocation().getZ() - 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
												t.getLocation().getY() + 2, t.getLocation().getZ() - 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								// Mitte
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY() + 1, t.getLocation().getZ()))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
												t.getLocation().getY() + 1, t.getLocation().getZ()))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
												t.getLocation().getY() + 1, t.getLocation().getZ()))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY() + 1, t.getLocation().getZ() + 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY() + 1, t.getLocation().getZ() - 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
												t.getLocation().getY() + 1, t.getLocation().getZ() + 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
												t.getLocation().getY() + 1, t.getLocation().getZ() + 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY() + 1, t.getLocation().getZ() - 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
												t.getLocation().getY() + 1, t.getLocation().getZ() - 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
												t.getLocation().getY() + 1, t.getLocation().getZ() - 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								// Unten
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY(), t.getLocation().getZ()))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
												t.getLocation().getY(), t.getLocation().getZ()))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
												t.getLocation().getY(), t.getLocation().getZ()))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY(), t.getLocation().getZ() + 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
												t.getLocation().getY(), t.getLocation().getZ() + 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
												t.getLocation().getY(), t.getLocation().getZ() + 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY(), t.getLocation().getZ() - 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
												t.getLocation().getY(), t.getLocation().getZ() - 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
												t.getLocation().getY(), t.getLocation().getZ() - 1))
										.setType(XMaterial.COBWEB.parseMaterial());

							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.lsd"))) {
						if (p.hasPermission("troll.lsd") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								TrollV4API.LSD(t);
								p.sendMessage(Data.prefix + Language.getMessage("gui.lsd", t));
								e.getView().close();
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.guardian"))) {
						if (p.hasPermission("troll.lsd") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								TrollV4API.GuardinShow(t, false);
								p.sendMessage(Data.prefix + Language.getMessage("gui.guardian", t));
								e.getView().close();
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
						/*
						 * } else if
						 * (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(
						 * "§7EndScreen")) { if (p.hasPermission("troll.lsd") ||
						 * p.hasPermission("troll.*")) { Player t =
						 * Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName())); if (t != null) {
						 * TrollV4API.EndGame(t); if
						 * (Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						 * p.sendMessage(Data.prefix + "Der Spieler §c" + t.getName() +
						 * " §2sieht nun den EndScreen!"); } else if
						 * (Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						 * p.sendMessage(Data.prefix + "The User §c" + t.getName() +
						 * " §2now sees the EndScreen!"); } else { p.sendMessage(Data.prefix +
						 * "The User §c" + t.getName() + " §2now sees the EndScreen!"); }
						 * e.getView().close(); } else { if
						 * (Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						 * p.sendMessage(Data.noton); } else if
						 * (Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						 * p.sendMessage(Data.notonus); } else { p.sendMessage(Data.notonus); }
						 * e.getView().close(); } } else { if
						 * (Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						 * p.sendMessage(Data.noperm); } else if
						 * (Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						 * p.sendMessage(Data.nopermus); } else { p.sendMessage(Data.nopermus); }
						 * 
						 * }
						 */
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.herobrine"))) {
						if (p.hasPermission("troll.herobrine") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (ArrayUtils.herobrine.contains(t)) {
									p.sendMessage(Data.prefix + Language.getMessage("gui.herobrine.off", t));
									e.getView().close();
									ArrayUtils.herobrine.remove(t);
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("gui.herobrine.on", t));
									e.getView().close();
									ArrayUtils.herobrine.add(t);
								}
								e.getView().close();
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.arrowspam"))) {
						if (p.hasPermission("troll.arrowspam") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (ArrayUtils.userbowspam.contains(t)) {
									p.sendMessage(Data.prefix + Language.getMessage("gui.arrowspam.off", t));
									e.getView().close();
									ArrayUtils.arrowspam.get(t).cancel();
									ArrayUtils.userbowspam.remove(t);
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("gui.arrowspam.on", t));
									e.getView().close();
									ArrayUtils.userbowspam.add(t);
									ArrayUtils.arrowspam.put(t, new BukkitRunnable() {

										@Override
										public void run() {

											Location loc = t.getLocation().clone();

											Arrow arrow = (Arrow) t.getWorld()
													.spawn(loc.clone().add(Main.getPlugin().getRandom(-10, 10),
															Main.getPlugin().getRandom(5, 10),
															Main.getPlugin().getRandom(-10, 10)), Arrow.class);
											Location aloc = arrow.getLocation();
											Vector angle = new Vector(loc.getX() - aloc.getX(),
													loc.getY() - aloc.getBlockY(), loc.getZ() - aloc.getBlockZ());
											arrow.setVelocity(angle.normalize().multiply(2.0D));

											Arrow arrow2 = (Arrow) t.getWorld()
													.spawn(loc.clone().add(Main.getPlugin().getRandom(-10, 10),
															Main.getPlugin().getRandom(5, 10),
															Main.getPlugin().getRandom(-10, 10)), Arrow.class);
											Location aloc2 = arrow2.getLocation();
											Vector angle2 = new Vector(loc.getX() - aloc2.getX(),
													loc.getY() - aloc2.getBlockY(), loc.getZ() - aloc2.getBlockZ());
											arrow2.setVelocity(angle2.normalize().multiply(2.0D));

											Arrow arrow3 = (Arrow) t.getWorld()
													.spawn(loc.clone().add(Main.getPlugin().getRandom(-10, 10),
															Main.getPlugin().getRandom(5, 10),
															Main.getPlugin().getRandom(-10, 10)), Arrow.class);
											Location aloc3 = arrow3.getLocation();
											Vector angle3 = new Vector(loc.getX() - aloc3.getX(),
													loc.getY() - aloc3.getBlockY(), loc.getZ() - aloc3.getBlockZ());
											arrow3.setVelocity(angle3.normalize().multiply(2.0D));

											Arrow arrow4 = (Arrow) t.getWorld()
													.spawn(loc.clone().add(Main.getPlugin().getRandom(-10, 10),
															Main.getPlugin().getRandom(5, 10),
															Main.getPlugin().getRandom(-10, 10)), Arrow.class);
											Location aloc4 = arrow4.getLocation();
											Vector angle4 = new Vector(loc.getX() - aloc4.getX(),
													loc.getY() - aloc4.getBlockY(), loc.getZ() - aloc4.getBlockZ());
											arrow4.setVelocity(angle4.normalize().multiply(2.0D));

											Arrow arrow5 = (Arrow) t.getWorld()
													.spawn(loc.clone().add(Main.getPlugin().getRandom(-10, 10),
															Main.getPlugin().getRandom(5, 10),
															Main.getPlugin().getRandom(-10, 10)), Arrow.class);
											Location aloc5 = arrow5.getLocation();
											Vector angle5 = new Vector(loc.getX() - aloc5.getX(),
													loc.getY() - aloc5.getBlockY(), loc.getZ() - aloc5.getBlockZ());
											arrow5.setVelocity(angle5.normalize().multiply(2.0D));

										}
									});
									ArrayUtils.arrowspam.get(t).runTaskTimer(Main.getPlugin(), 0L, 10L);
								}
								e.getView().close();
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.tornado"))) {
						if (p.hasPermission("troll.tornado") || p.hasPermission("troll.*")) {

							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (ArrayUtils.tornado.contains(t)) {
									p.sendMessage(Data.prefix + Language.getMessage("gui.tornado.off", t));
									e.getView().close();
									ArrayUtils.tornador.get(t).cancel();
									ArrayUtils.tornado.remove(t);
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("gui.tornado.on", t));
									e.getView().close();
									ArrayUtils.tornado.add(t);
									ArrayUtils.tornador.put(t, new BukkitRunnable() {

										Random r = new Random();

										int rix = r.nextBoolean() ? -1 : 1;
										int riz = r.nextBoolean() ? -1 : 1;

										@Override
										public void run() {
											Location location1 = t.getLocation();
											FallingBlock o;
											Location location3 = new Location(location1.getWorld(), location1.getX(),
													location1.getY() - 1, location1.getZ());

											if ((location3 != null) && location3.getBlock() != null && location3
													.getBlock().getType() != XMaterial.AIR.parseMaterial()) {
												o = location3.getWorld().spawnFallingBlock(
														location3.getBlock().getLocation(),
														location3.getBlock().getType(), (byte) 0);
												location3.getBlock().setType(XMaterial.AIR.parseMaterial());
												o.setFallDistance(0.0F);
												o.setVelocity(new Vector(
														r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
														0.6D + r.nextInt(2) / 4.5D,
														r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											}

											location3 = new Location(location1.getWorld(), location1.getX(),
													location1.getY() - 1, location1.getZ() - 1);

											if ((location3 != null) && location3.getBlock() != null && location3
													.getBlock().getType() != XMaterial.AIR.parseMaterial()) {
												o = location3.getWorld().spawnFallingBlock(
														location3.getBlock().getLocation(),
														location3.getBlock().getType(), (byte) 0);
												location3.getBlock().setType(XMaterial.AIR.parseMaterial());
												o.setFallDistance(0.0F);
												o.setVelocity(new Vector(
														r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
														0.6D + r.nextInt(2) / 4.5D,
														r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											}

											location3 = new Location(location1.getWorld(), location1.getX() - 1,
													location1.getY() - 1, location1.getZ() - 1);

											if ((location3 != null) && location3.getBlock() != null && location3
													.getBlock().getType() != XMaterial.AIR.parseMaterial()) {
												o = location3.getWorld().spawnFallingBlock(
														location3.getBlock().getLocation(),
														location3.getBlock().getType(), (byte) 0);
												location3.getBlock().setType(XMaterial.AIR.parseMaterial());
												o.setFallDistance(0.0F);
												o.setVelocity(new Vector(
														r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
														0.6D + r.nextInt(2) / 4.5D,
														r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											}

											location3 = new Location(location1.getWorld(), location1.getX() + 1,
													location1.getY() - 1, location1.getZ() + 1);

											if ((location3 != null) && location3.getBlock() != null && location3
													.getBlock().getType() != XMaterial.AIR.parseMaterial()) {
												o = location3.getWorld().spawnFallingBlock(
														location3.getBlock().getLocation(),
														location3.getBlock().getType(), (byte) 0);
												location3.getBlock().setType(XMaterial.AIR.parseMaterial());
												o.setFallDistance(0.0F);
												o.setVelocity(new Vector(
														r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
														0.6D + r.nextInt(2) / 4.5D,
														r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											}

											location3 = new Location(location1.getWorld(), location1.getX() - 1,
													location1.getY() - 1, location1.getZ() + 1);

											if ((location3 != null) && location3.getBlock() != null && location3
													.getBlock().getType() != XMaterial.AIR.parseMaterial()) {
												o = location3.getWorld().spawnFallingBlock(
														location3.getBlock().getLocation(),
														location3.getBlock().getType(), (byte) 0);
												location3.getBlock().setType(XMaterial.AIR.parseMaterial());
												o.setFallDistance(0.0F);
												o.setVelocity(new Vector(
														r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
														0.6D + r.nextInt(2) / 4.5D,
														r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											}

											location3 = new Location(location1.getWorld(), location1.getX() + 1,
													location1.getY() - 1, location1.getZ() - 1);

											if ((location3 != null) && location3.getBlock() != null && location3
													.getBlock().getType() != XMaterial.AIR.parseMaterial()) {
												o = location3.getWorld().spawnFallingBlock(
														location3.getBlock().getLocation(),
														location3.getBlock().getType(), (byte) 0);
												location3.getBlock().setType(XMaterial.AIR.parseMaterial());
												o.setFallDistance(0.0F);
												o.setVelocity(new Vector(
														r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
														0.6D + r.nextInt(2) / 4.5D,
														r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											}

											location3 = new Location(location1.getWorld(), location1.getX() - 1,
													location1.getY() - 1, location1.getZ());

											if ((location3 != null) && location3.getBlock() != null && location3
													.getBlock().getType() != XMaterial.AIR.parseMaterial()) {
												o = location3.getWorld().spawnFallingBlock(
														location3.getBlock().getLocation(),
														location3.getBlock().getType(), (byte) 0);
												location3.getBlock().setType(XMaterial.AIR.parseMaterial());
												o.setFallDistance(0.0F);
												o.setVelocity(new Vector(
														r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
														0.6D + r.nextInt(2) / 4.5D,
														r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											}

											location3 = new Location(location1.getWorld(), location1.getX() + 1,
													location1.getY() - 1, location1.getZ());

											if ((location3 != null) && location3.getBlock() != null && location3
													.getBlock().getType() != XMaterial.AIR.parseMaterial()) {
												o = location3.getWorld().spawnFallingBlock(
														location3.getBlock().getLocation(),
														location3.getBlock().getType(), (byte) 0);
												location3.getBlock().setType(XMaterial.AIR.parseMaterial());
												o.setFallDistance(0.0F);
												o.setVelocity(new Vector(
														r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
														0.6D + r.nextInt(2) / 4.5D,
														r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											}

											location3 = new Location(location1.getWorld(), location1.getX(),
													location1.getY() - 1, location1.getZ() + 1);

											if ((location3 != null) && location3.getBlock() != null && location3
													.getBlock().getType() != XMaterial.AIR.parseMaterial()) {
												o = location3.getWorld().spawnFallingBlock(
														location3.getBlock().getLocation(),
														location3.getBlock().getType(), (byte) 0);
												location3.getBlock().setType(XMaterial.AIR.parseMaterial());
												o.setFallDistance(0.0F);
												o.setVelocity(new Vector(
														r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
														0.6D + r.nextInt(2) * 2.5D,
														r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											}

											location3 = new Location(location1.getWorld(), location1.getX(),
													location1.getY() - 1, location1.getZ());

											location3.getBlock().setType(XMaterial.STONE.parseMaterial());
											o = location1.getWorld().spawnFallingBlock(
													location3.getBlock().getLocation(), location3.getBlock().getType(),
													(byte) 0);
											o.setFallDistance(0.0F);
											o.setVelocity(new Vector(
													r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
													0.6D + r.nextInt(2) * 2.5D,
													r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											location3.getBlock().setType(XMaterial.AIR.parseMaterial());

											location3 = new Location(location1.getWorld(), location1.getX(),
													location1.getY() - 1, location1.getZ() + 1);

											location3.getBlock().setType(XMaterial.STONE.parseMaterial());
											o = location1.getWorld().spawnFallingBlock(
													location3.getBlock().getLocation(), location3.getBlock().getType(),
													(byte) 0);
											o.setFallDistance(0.0F);
											o.setVelocity(new Vector(
													r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
													0.6D + r.nextInt(2) * 2.5D,
													r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											location3.getBlock().setType(XMaterial.AIR.parseMaterial());

											location3 = new Location(location1.getWorld(), location1.getX(),
													location1.getY() - 1, location1.getZ() - 1);

											location3.getBlock().setType(XMaterial.STONE.parseMaterial());
											o = location1.getWorld().spawnFallingBlock(
													location3.getBlock().getLocation(), location3.getBlock().getType(),
													(byte) 0);
											o.setFallDistance(0.0F);
											o.setVelocity(new Vector(
													r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
													0.6D + r.nextInt(2) * 2.5D,
													r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											location3.getBlock().setType(XMaterial.AIR.parseMaterial());

											location3 = new Location(location1.getWorld(), location1.getX() + 1,
													location1.getY() - 1, location1.getZ());

											location3.getBlock().setType(XMaterial.STONE.parseMaterial());
											o = location1.getWorld().spawnFallingBlock(
													location3.getBlock().getLocation(), location3.getBlock().getType(),
													(byte) 0);
											o.setFallDistance(0.0F);
											o.setVelocity(new Vector(
													r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
													0.6D + r.nextInt(2) * 2.5D,
													r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											location3.getBlock().setType(XMaterial.AIR.parseMaterial());

											location3 = new Location(location1.getWorld(), location1.getX() - 1,
													location1.getY() - 1, location1.getZ());

											location3.getBlock().setType(XMaterial.STONE.parseMaterial());
											o = location1.getWorld().spawnFallingBlock(
													location3.getBlock().getLocation(), location3.getBlock().getType(),
													(byte) 0);
											o.setFallDistance(0.0F);
											o.setVelocity(new Vector(
													r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
													0.6D + r.nextInt(2) * 2.5D,
													r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											location3.getBlock().setType(XMaterial.AIR.parseMaterial());

											Location location2 = new Location(location1.getWorld(), location1.getX(),
													location1.getY() + 3.0D, location1.getZ(),
													location1.getYaw() + 15.0F, location1.getPitch());
											t.teleport(location2);

										}
									});
									ArrayUtils.tornador.get(t).runTaskTimer(Main.getPlugin(), 0, 8);
								}
								e.getView().close();
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.fakeinv"))) {
						if (p.hasPermission("troll.fakeinv") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (ArrayUtils.fakeinv.containsKey(t)) {
									InvSaver is = ArrayUtils.fakeinv.get(t);

									t.getInventory().setArmorContents(is.getArmor());
									t.getInventory().setExtraContents(is.getExtracont());
									t.getInventory().setContents(is.getContent());

									p.sendMessage(Data.prefix + Language.getMessage("gui.fakeinv.cancel", t));
									e.getView().close();

									ArrayUtils.fakeinv.remove(t);
								} else {
									InvSaver is;

									if (Main.version.startsWith("v1_8")) {
										is = new InvSaver(t.getName(), t.getInventory().getContents(),
												t.getInventory().getArmorContents());
									} else {
										is = new InvSaver(t.getName(), t.getInventory().getContents(),
												t.getInventory().getExtraContents(),
												t.getInventory().getArmorContents());
									}

									t.getInventory().clear();

									ArrayUtils.fakeinv.put(t, is);

									p.sendMessage(Data.prefix + Language.getMessage("gui.fakeinv.default", t));
									e.getView().close();

									new BukkitRunnable() {

										@Override
										public void run() {
											if (ArrayUtils.fakeinv.containsKey(t)) {
												InvSaver is = ArrayUtils.fakeinv.get(t);
												t.getInventory().setArmorContents(is.getArmor());
												if (!Main.version.startsWith("v1_8")) {
													t.getInventory().setExtraContents(is.getExtracont());
												}
												t.getInventory().setContents(is.getContent());
												ArrayUtils.fakeinv.remove(t);
											}
											cancel();
										}
									}.runTaskLater(Main.instance, (Config.cfg.getInt("trolls.fakeinv.time") * 20L));

								}
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.noinvforyou"))) {
						if (p.hasPermission("troll.fakeinv") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {

								if (ArrayUtils.noinv.contains(t)) {

									p.sendMessage(Data.prefix + Language.getMessage("gui.noinv.off", t));
									e.getView().close();

									ArrayUtils.noinv.remove(t);
								} else {

									p.sendMessage(Data.prefix + Language.getMessage("gui.noinv.on", t));
									e.getView().close();

									if (t.getOpenInventory() != null) {
										t.getOpenInventory().close();
									}

									ArrayUtils.noinv.add(t);
								}

							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.slipperyhands"))) {
						if (p.hasPermission("troll.slipperyhands") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {

								if (ArrayUtils.noitem.contains(t)) {

									p.sendMessage(Data.prefix + Language.getMessage("gui.slipperyhands.off", t));
									e.getView().close();

									ArrayUtils.noitem.remove(t);
								} else {

									p.sendMessage(Data.prefix + Language.getMessage("gui.slipperyhands.on", t));
									e.getView().close();

									if (t.getItemInHand() != null
											&& t.getItemInHand().getType() != XMaterial.AIR.parseMaterial()) {
										t.getWorld().dropItemNaturally(t.getLocation(), t.getItemInHand())
												.setPickupDelay(20);
									}
									t.getInventory().setItem(t.getInventory().getHeldItemSlot(), null);

									new BukkitRunnable() {

										@Override
										public void run() {
											Player sheesh = t;
											if (ArrayUtils.noitem.contains(sheesh)) {
												if (sheesh.getItemInHand() != null && sheesh.getItemInHand()
														.getType() != XMaterial.AIR.parseMaterial()) {
													sheesh.getWorld().dropItemNaturally(sheesh.getLocation(),
															sheesh.getItemInHand()).setPickupDelay(20);
													sheesh.getInventory()
															.setItem(sheesh.getInventory().getHeldItemSlot(), null);
												}
											} else {
												cancel();
											}
										}
									}.runTaskTimer(Main.instance, 0L,
											(Config.cfg.getInt("trolls.slipperyhands.time") * 20L));

									ArrayUtils.noitem.add(t);
								}

							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.tntworld"))) {
						if (p.hasPermission("troll.tntworld") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								new BukkitRunnable() {

									@Override
									public void run() {
										Location oldl = t.getLocation();
										for (int x = 0; x < 200; x++) {
											for (int y = 0; y < 30; y++) {
												for (int z = 0; z < 200; z++) {
													if (new Location(oldl.getWorld(), oldl.getBlockX() - 100 + x,
															oldl.getBlockY() - 7 + y, oldl.getBlockZ() - 100 + z)
																	.getBlock()
																	.getType() != XMaterial.AIR.parseMaterial()) {
														Location l = new Location(oldl.getWorld(),
																oldl.getBlockX() - 100 + x, oldl.getBlockY() - 7 + y,
																oldl.getBlockZ() - 100 + z);
														t.sendBlockChange(l, XMaterial.TNT.parseMaterial(), (byte) 0);
													}
												}
											}
										}
									}
								}.runTaskAsynchronously(Main.instance);
								p.sendMessage(Data.prefix + Language.getMessage("gui.tntworld.default", t));
								e.getView().close();
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.rickroll"))) {
						if (p.hasPermission("troll.rickroll") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (!PlayMusic.isPlaying(t)) {
									PlayMusic.play(t, "plugins/TrollV4/rick.nbs");
									p.sendMessage(Data.prefix + Language.getMessage("gui.rickroll.default", t));
									e.getView().close();
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("gui.rickroll.ishearing", t));
									e.getView().close();
								}
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.dontstopjumping"))) {
						if (p.hasPermission("troll.dontstopjumping") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (ArrayUtils.jumping.containsKey(t)) {

									p.sendMessage(Data.prefix + Language.getMessage("gui.dontstopjumping.off", t));

									ArrayUtils.jumping.get(t).cancel();
									ArrayUtils.jumping.remove(t);
									e.getView().close();
								} else {

									p.sendMessage(Data.prefix + Language.getMessage("gui.dontstopjumping.on", t));

									ArrayUtils.jumping.put(t, new BukkitRunnable() {

										@Override
										public void run() {

											Player jumper = t;

											if (jumper.isOnGround()) {
												jumper.setVelocity(new Vector(0.0, 0.45, 0.0));
											}

										}
									});

									ArrayUtils.jumping.get(t).runTaskTimer(Main.instance, 0L, 12L);
									e.getView().close();

								}
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.deaf"))) {
						if (p.hasPermission("troll.deaf") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (ArrayUtils.deaf.contains(t)) {

									p.sendMessage(Data.prefix + Language.getMessage("gui.deaf.off", t));

									ArrayUtils.deaf.remove(t);
									e.getView().close();
								} else {

									p.sendMessage(Data.prefix + Language.getMessage("gui.deaf.on", t));

									ArrayUtils.deaf.add(t);
									e.getView().close();
								}
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.confused"))) {
						if (p.hasPermission("troll.confused") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (ArrayUtils.confus.contains(t)) {

									p.sendMessage(Data.prefix + Language.getMessage("gui.confused.off", t));

									ArrayUtils.confus.remove(t);
									e.getView().close();
								} else {

									p.sendMessage(Data.prefix + Language.getMessage("gui.confused.on", t));

									ArrayUtils.confus.add(t);
									e.getView().close();
								}
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.anvils"))) {
						if (p.hasPermission("troll.anvils") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (ArrayUtils.anvils.containsKey(t)) {

									p.sendMessage(Data.prefix + Language.getMessage("gui.anvils.off", t));

									ArrayUtils.anvils.get(t).cancel();
									ArrayUtils.anvils.remove(t);
									e.getView().close();
								} else {

									p.sendMessage(Data.prefix + Language.getMessage("gui.anvils.on", t));

									ArrayUtils.anvils.put(t, new BukkitRunnable() {

										@Override
										public void run() {
											Location oldl = t.getLocation().add(0, 5, 0);
											Location randoml = LocationUtil.getLocFromRad(oldl, 5, 0, 5);

											randoml.getWorld().spawnFallingBlock(randoml,
													XMaterial.DAMAGED_ANVIL.parseMaterial(), (byte) 0);

										}
									});

									ArrayUtils.anvils.get(t).runTaskTimer(Main.instance, 20L,
											(Config.cfg.getInt("trolls.anvils.time") * 20L));

									e.getView().close();
								}
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.cows"))) {
						if (p.hasPermission("troll.cows") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {

								p.sendMessage(Data.prefix + Language.getMessage("gui.cows", t));

								for (int x = -1; x < 1; x++) {
									for (int z = -1; z < 1; z++) {
										if (x != 0 && z != 0) {
											t.getWorld().spawnEntity(t.getLocation().add(x, z, 0), EntityType.COW);
										}
									}
								}

								e.getView().close();
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Items.getItem("gui.trolls.giorno"))) {
						if (p.hasPermission("troll.giorno") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (!ArrayUtils.isJojo) {
									PlayMusic.play(t, "plugins/TrollV4/giorno.nbs");
									p.sendMessage(Data.prefix + Language.getMessage("gui.giorno.on", t));

									final Skin[] giorno = new Skin[1];
									final Skin[] goldenwind = new Skin[1];

									MineSkinFetcher.fetchSkinFromIdAsync(2073768622, new MineSkinFetcher.Callback() {

										@Override
										public void call(Skin skinData) {

											if (skinData != null) {
												giorno[0] = skinData;
											}

										}
									});

									MineSkinFetcher.fetchSkinFromIdAsync(1831521135, new MineSkinFetcher.Callback() {

										@Override
										public void call(Skin skinData) {

											if (skinData != null) {
												goldenwind[0] = skinData;
											}

										}
									});

									ArrayUtils.isJojo = true;
									
									new BukkitRunnable() {

										@Override
										public void run() {
											for (Player all : Bukkit.getOnlinePlayers()) {
												NPC npc = Main.npcLib.createNPC();
												npc.setSkin(giorno[0]);
												npc.create();
												npc.setLocation(all.getLocation());
												npc.create();
												npc.lookAt(all.getLocation().add(0, 1, 0));
												npc.setItem(NPCSlot.MAINHAND,
														new ItemStack(XMaterial.ARROW.parseMaterial()));
												all.sendMessage("Giorno Giovanna have a Dream!");

												new BukkitRunnable() {

													@Override
													public void run() {
														npc.toggleState(NPCState.CROUCHED);

														NPC npc2 = Main.npcLib.createNPC();
														npc2.setSkin(goldenwind[0]);
														npc2.create();
														npc2.setLocation(all.getLocation().add(0, 2, 0));

														new BukkitRunnable() {

															@Override
															public void run() {
																if (ArrayUtils.isJojo) {
																	npc2.playAnimation(NPCAnimation.SWING_MAINHAND);
																	npc2.playAnimation(NPCAnimation.SWING_OFFHAND);
																} else {
																	cancel();
																}
															}
														}.runTaskTimer(Main.instance, 20L, 20L);

														npc2.create();
														npc2.lookAt(all.getLocation().add(0, 1, 0));

														npc.toggleState(NPCState.CROUCHED);
														
														ArrayUtils.npcs.add(npc2);

													}
												}.runTaskLater(Main.instance, 60L);
												
												ArrayUtils.npcs.add(npc);
												
											}
										}
									}.runTaskLater(Main.instance, 20L);
									e.getView().close();
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("gui.giorno.off", t));

									ArrayUtils.isJojo = false;
									
									for (NPC npcs : ArrayUtils.npcs) {
										npcs.destroy();
										ArrayUtils.npcs.remove(npcs);
									}
									
									for (Player all : Bukkit.getOnlinePlayers()) {
										PlayMusic.stop(all);
									}
									
									e.getView().close();
								}
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					}
				}
			} else if (e.getView().getTitle().equalsIgnoreCase("§2Server Troll Menu")) {
				e.getResult();
				e.setResult(Result.DENY);
				if (e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(Items.getItem("gui.servertrolls.tpall"))) {
					if (p.hasPermission("troll.tpall") || p.hasPermission("troll.*")) {
						e.getView().close();
						for (Player all : Bukkit.getOnlinePlayers()) {
							all.teleport(p.getLocation());
						}
						p.sendMessage(Data.prefix + Language.getMessage("gui.tpall"));
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(Items.getItem("gui.servertrolls.fakeleave"))) {
					if (p.hasPermission("troll.fakeleave") || p.hasPermission("troll.*")) {
						e.getView().close();
						Bukkit.broadcastMessage(Language.getMessage("gui.fakeleave.message", p));
						p.sendMessage(Data.prefix + Language.getMessage("gui.fakeleave.default"));
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(Items.getItem("gui.servertrolls.hackmessage"))) {
					if (p.hasPermission("troll.hackmessage") || p.hasPermission("troll.*")) {
						p.sendMessage(Data.prefix + Language.getMessage("gui.hackserver"));

						e.getView().close();

						taskID2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
							int countdown = Config.cfg.getInt("trolls.hack.time");

							@Override
							public void run() {
								if (countdown <= 0) {
									for (Player all : Bukkit.getOnlinePlayers()) {
										HS.Hack(all);
										Titles.send(all, 1, 20, 1, "§cHACKED", "§4" + Main.getRandomID());
										Bukkit.getScheduler().cancelTask(taskID2);
									}
									return;
								}
								for (Player all : Bukkit.getOnlinePlayers()) {
									HS.Hack2(all);
									Titles.send(all, 1, 20, 1, "§cHacking in " + countdown, "§4" + Main.getRandomID());
									all.damage(0.1D);
									countdown--;
								}
							}
						}, 0, 20);
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				}
			}
		} catch (Exception e2) {

		}
	}

}
