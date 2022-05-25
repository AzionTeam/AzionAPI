package fr.sothis.azionapi.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;

import fr.sothis.azionapi.pojo.Grade;
import fr.sothis.azionapi.pojo.Report;
import fr.sothis.azionapi.pojo.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Logger;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DatabaseManager {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<User> users;
    private MongoCollection<Grade> grades;
    private MongoCollection<Report> reports;

    private HashMap<String, MongoCollection<?>> collections;

    public void init(Logger logger, String name) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true)
                .conventions(Arrays.asList(Conventions.ANNOTATION_CONVENTION, Conventions.USE_GETTERS_FOR_SETTERS))
                .build();
        CodecRegistry codecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        mongoClient = MongoClients.create("mongodb://Admin:wH7S%26Cofose6TP@192.168.11.201:27017/?zlibCompressionLevel=-1&maxPoolSize=1000&appName=default&w=majority");
        mongoDatabase = mongoClient.getDatabase("Azion").withCodecRegistry(codecRegistry);
        users = mongoDatabase.getCollection("users", User.class);
        grades = mongoDatabase.getCollection("grades", Grade.class);
        reports = mongoDatabase.getCollection("reports", Report.class);

        logger.info("Plugin '" + name + "' est connecté à la base de données");
    }

    public void close() {
        mongoClient.close();
    }

    public MongoCollection<User> getUsers() {
        return users;
    }

    public MongoCollection<Grade> getGrades() {
        return grades;
    }

    public MongoCollection<Report> getReports() {
        return reports;
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    public <T> HashMap<String, MongoCollection<?>> getCollections() {
        return collections;
    }
}
