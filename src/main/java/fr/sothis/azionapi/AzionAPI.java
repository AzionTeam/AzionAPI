package fr.sothis.azionapi;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;

import fr.sothis.azionapi.listeners.AzionListener;
import fr.sothis.azionapi.database.DatabaseManager;
import fr.sothis.azionapi.event.Join;
import fr.sothis.azionapi.listeners.ListenerManager;
import fr.sothis.azionapi.managers.GradeManager;
import fr.sothis.azionapi.managers.ObjectManager;
import fr.sothis.azionapi.managers.ReportManager;
import fr.sothis.azionapi.managers.UserManager;
import fr.sothis.azionapi.socket.Sockets;
import fr.sothis.azionapi.tools.title.TitleBuilder;
import fr.sothis.azionapi.tools.title.TitleOptions;
import fr.sothis.azionapi.utils.Communication;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

public class AzionAPI {

    private static Plugin plugin;
    private static Logger logger;
    private static boolean vaultState;

    private static AzionAPI instance;
    private static Sockets sockets;
    private static Communication communication;

    private static DatabaseManager databaseManager;
    private static ListenerManager listenerManager;
    private static UserManager userManager;
    private static GradeManager gradeManager;
    private static ReportManager reportManager;
    private static ObjectManager objectManager;

    private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;

    /**
     * Start the API (and the database connection)
     * Collect informations of plugin and the name of plugin
     * The class plugin help to register the events of the API
     *
     * @param plug
     * @param name
     */
    public static void start(Plugin plug, String name) {
        plugin = plug;
        logger = plug.getLogger();

        disableLog("org.mongodb");
        disableLog("org.bson");

        if(plug.isEnabled()) {
            databaseManager = new DatabaseManager();
            databaseManager.init(logger, name);

            listenerManager = new ListenerManager();

            userManager = new UserManager(databaseManager, econ);
            gradeManager = new GradeManager(databaseManager);
            sockets = new Sockets(listenerManager);
            communication = new Communication(sockets, plug);
            communication.start(name);
            reportManager = new ReportManager(databaseManager, listenerManager, sockets);

            vaultState = startVault();
            registerEvent();
        }
    }

    private static boolean startVault() {
        if (!setupEconomy()) {
            logger.severe("Attention Vault n'est pas dans la liste des plugins !");
            return false;
        }
        setupPermissions();
        setupChat();
        return true;
    }

    private static void disableLog(String name) {
        ch.qos.logback.classic.Logger rootLogger2 = (ch.qos.logback.classic.Logger) LoggerFactory.getILoggerFactory().getLogger(name);
        rootLogger2.setLevel(Level.OFF);
    }

    /**
     * Stop the API
     * Just close the database
     */
    public static void stop() {
        if(databaseManager != null) {
            databaseManager.close();
        }
    }

    /**
     * Just a function to stack the class ;)
     */
    private static void registerEvent() {
        PluginManager pm = plugin.getServer().getPluginManager();

        if(vaultState == true) {
            pm.registerEvents(new Join(databaseManager, userManager), plugin);
        } else {
            logger.severe("Attention un event de l'api n'a pas pu etre enregistrer car vault est manquant");
            logger.severe("Merci de bien vouloir rajouter le plugin afin d'eviter tout problème !");
        }
    }

    public void registerEvent(AzionListener clazz) {
        listenerManager.addListener(clazz);
    }

    public <T> MongoCollection<T> createCollection(String name, Class T) {
        MongoIterable<String> listCol = databaseManager.getMongoDatabase().listCollectionNames();
        int nmb = 0;
        while (listCol.iterator().hasNext()) {
            if (listCol.iterator().next().equals("name")) {
                nmb++;
            }
        }
        if (nmb == 0) {
            databaseManager.getMongoDatabase().createCollection(name);
        }
        try {
            MongoCollection<T> collection = databaseManager.getMongoDatabase().getCollection(name, T);
            databaseManager.getCollections().put(name, collection);
            return collection;
        } catch (Exception e) {
            logger.severe("The object are not valid or the collection doesn't exist");
            e.printStackTrace();
            plugin.getPluginLoader().disablePlugin(plugin);
        }
        return null;
    }

    /**
     * Return the instance of the class to get access to other getters
     *
     * @return
     */
    public static AzionAPI getInstance() {
        return instance;
    }

    /**
     * Get the manager to interact with users in database
     *
     * @return
     */
    public UserManager getUserManager() {
        return userManager;
    }

    /**
     * Get the manager to interact with grades in database
     *
     * @return
     */
    public GradeManager getGradeManager() {
        return gradeManager;
    }

    public ReportManager getReportManager() {
        return reportManager;
    }

    public void sendTitle(Player player, String title, ChatColor chatColorTitle, String subtitle,
            ChatColor chatColorSubTitle, TitleOptions options) {
        TitleBuilder.sendTitle(player, title, chatColorTitle, subtitle, chatColorSubTitle, options);
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    private static boolean setupEconomy() {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private static boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = plugin.getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private static boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = plugin.getServer().getServicesManager()
                .getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public ObjectManager getObjectManager() {
        return objectManager;
    }
}
