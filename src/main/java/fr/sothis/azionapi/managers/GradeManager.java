package fr.sothis.azionapi.managers;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;

import org.bson.Document;

import fr.sothis.azionapi.database.DatabaseManager;
import fr.sothis.azionapi.pojo.Grade;

import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.eq;

public class GradeManager {

    private MongoCollection<Grade> grades;

    public GradeManager(DatabaseManager databaseManager) {
        this.grades = databaseManager.getGrades();
    }

    public Grade getGrade(String name) {
        return grades.find(eq("name", name)).first();
    }

    public boolean isRegistered(String name) {
        if (getGrade(name) != null) {
            return true;
        }
        return false;
    }

    public void updateGrade(Grade grade) {
        Document filterById = new Document("name", grade.getName());
        FindOneAndReplaceOptions returnDocAfterReplace = new FindOneAndReplaceOptions()
                .returnDocument(ReturnDocument.AFTER);
        Grade updatedGuildData = grades.findOneAndReplace(filterById, grade, returnDocAfterReplace);
    }

    public void updateGrade(String name, Consumer<Grade> consumer) {
        Grade grade = getGrade(name);
        consumer.accept(grade);
        updateGrade(grade);
    }
}
