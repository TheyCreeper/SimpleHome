package dev.theycreeper.simplehome.saving;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.theycreeper.simplehome.SimpleHome;
import dev.theycreeper.simplehome.models.Home;
import org.bukkit.Location;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static aQute.bnd.annotation.headers.Category.json;

public class JsonHelper {
    private Connection connection;
    Logger logger = SimpleHome.getInstance().getLogger();

    public JsonHelper() {

    }

    private static JsonHelper instance;

    public static JsonHelper getInstance() {
        if (instance == null) instance = new JsonHelper();
        return instance;
    }

    public static void HomeWrite(UUID playerId, List<Home> homes) {
        try (FileWriter writer = new FileWriter(getDataFolder() + "/" + playerId + ".json")) {
            Gson gson = createGson();
            gson.toJson(homes, writer);
            System.out.println("Successfully saved to user's json save");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Home> GetPlayerHomes(UUID playerId) {
        Path filePath = Path.of(getDataFolder() + "/" + playerId + ".json");
        FindOrCreate(filePath);
        try {
            String homesRaw = Files.readString(filePath);
            Type listType = new TypeToken<List<Home>>(){}.getType();
            var list = createGson().fromJson(homesRaw, listType);
            if (list == null) {
                return new ArrayList<Home>();
            }
            return createGson().fromJson(homesRaw, listType);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Home>();
    }

    private static Gson createGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Location.class, new LocationTypeAdapter())
                .create();
    }

    private static String getDataFolder() {
        return SimpleHome.getInstance().getDataFolder().toString();
    }

    private static void FindOrCreate(Path path) {
        if(!Files.exists(path)) {
            try {
                Files.createFile(path);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
