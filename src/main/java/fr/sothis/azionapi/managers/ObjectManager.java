package fr.sothis.azionapi.managers;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;

import org.bson.Document;
import org.bukkit.Bukkit;

import fr.sothis.azionapi.AzionAPI;

import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.eq;

public class ObjectManager<T> {

    private T T;
    private MongoCollection<T> collection;
    private String name;

    public ObjectManager(String name) {
        if (AzionAPI.getInstance().getDatabaseManager().getCollections().containsKey(name)) {
            collection = (MongoCollection<T>) AzionAPI.getInstance().getDatabaseManager().getCollections().get(name);
            this.name = name;
            this.T = T;
        }
        Bukkit.getLogger().severe("Error with ObjectManager");
    }

    public T getObject(String field) {
        return collection.find(eq(field, name)).first();
    }

    public boolean isRegistered(String name) {
        if (getObject(name) != null) {
            return true;
        }
        return false;
    }

    public void updateObject(String key, String field) {
        Document filterById = new Document(field, key);
        FindOneAndReplaceOptions returnDocAfterReplace = new FindOneAndReplaceOptions()
                .returnDocument(ReturnDocument.AFTER);
        T updatedGuildData = collection.findOneAndReplace(filterById, T, returnDocAfterReplace);
    }

    public void updateObject(String key, String field, Consumer<T> consumer) {
        T grade = getObject(field);
        consumer.accept(T);
        updateObject(key, field);
    }

}
