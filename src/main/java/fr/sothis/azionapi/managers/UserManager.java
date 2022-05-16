package fr.sothis.azionapi.managers;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import fr.sothis.azionapi.database.DatabaseManager;
import fr.sothis.azionapi.pojo.User;
import net.milkbowl.vault.economy.Economy;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.eq;

public class UserManager {

    private MongoCollection<User> users;
    private Economy economy;

    public UserManager(DatabaseManager databaseManager, Economy economy) {
        this.users = databaseManager.getUsers();
        this.economy = economy;
    }

    public User getUser(String uuid) {
        return users.find(eq("uuid", uuid)).first();
    }

    public User getUser(UUID uuid) {
        return getUser(uuid.toString());
    }

    public User getUser(Player player) {
        return getUser(player.getUniqueId().toString());
    }

    public boolean isRegistered(String uuid) {
        if(getUser(uuid) != null) {
            return true;
        }
        return false;
    }

    public boolean isRegistered(UUID uuid) {
        return isRegistered(uuid.toString());
    }

    public boolean isRegistered(Player player) {
        return isRegistered(player.getUniqueId().toString());
    }

    public void updateUser(User user) {
        Document filterById = new Document("uuid", user.getUuid());
        FindOneAndReplaceOptions returnDocAfterReplace = new FindOneAndReplaceOptions().returnDocument(ReturnDocument.AFTER);
        User updatedUser = users.findOneAndReplace(filterById, user , returnDocAfterReplace);
        economy.deleteBank(user.getName());
        economy.createBank(user.getName(), Bukkit.getOfflinePlayer(UUID.fromString(user.getUuid())));
        assert updatedUser != null;
        economy.bankDeposit(user.getName(), updatedUser.getMoney());
    }

    public void updateUser(String uuid, Consumer<User> consumer) {
        User user = getUser(uuid);
        consumer.accept(user);
        updateUser(user);
    }

    public void updateUser(UUID uuid, Consumer<User> consumer) {
        User user = getUser(uuid);
        consumer.accept(user);
        updateUser(user);
    }

    public void updateUser(Player uuid, Consumer<User> consumer) {
        User user = getUser(uuid);
        consumer.accept(user);
        updateUser(user);
    }

}
