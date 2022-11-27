package io.github.superpro148.configlib148;

import com.google.gson.*;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class ConfigList {
    private ArrayList<ConfigOption> configOptions = new ArrayList<>();

    private File configFile;

    public ConfigList(String fileName) {
        this.configFile = new File(FabricLoader.getInstance().getConfigDir().toString() + "/" + fileName + ".json");
    }

    public ConfigOption register(ConfigOption option) {
        this.configOptions.add(option);
        return option;
    }

    public void saveConfig() {
        try {
            FileWriter configWriter = new FileWriter(this.configFile);
            JsonObject configJson = new JsonObject();
            for (int i = 0; i < this.configOptions.size(); i++) {
                ConfigOption option = this.configOptions.get(i);
                if (option.getClassType() == Boolean.class) {
                    configJson.addProperty(option.getKey(), (boolean) option.getValue());
                }
                if (option.getClassType() == Integer.class) {
                    configJson.addProperty(option.getKey(), (int) option.getValue());
                }
                if (option.getClassType() == Long.class) {
                    configJson.addProperty(option.getKey(), (long) option.getValue());
                }
                if (option.getClassType() == Float.class) {
                    configJson.addProperty(option.getKey(), (float) option.getValue());
                }
                if (option.getClassType() == Double.class) {
                    configJson.addProperty(option.getKey(), (double) option.getValue());
                }
                if (option.getClassType() == String.class) {
                    configJson.addProperty(option.getKey(), option.getValue().toString());
                }
                if (option.getClassType().isEnum()) {
                    configJson.addProperty(option.getKey(), option.getValue().toString());
                }
            }
            configWriter.write(configJson.toString());
            configWriter.close();
        } catch (IOException ignored) {}
    }

    public void readConfig() {
        try {
            String configText = Files.readString(this.configFile.toPath());
            try {
                JsonObject configJson = !JsonParser.parseString(configText).isJsonNull() ? (JsonObject) JsonParser.parseString(configText) : new JsonObject();
                for (int i = 0; i < this.configOptions.size(); i++) {
                    ConfigOption option = this.configOptions.get(i);
                    String key = option.getKey();
                    if (configJson.get(key) != null) {
                        if (option.getClassType() == Boolean.class) {
                            option.setValue(configJson.get(key).getAsBoolean());
                        }
                        if (option.getClassType() == Integer.class) {
                            option.setValue(configJson.get(key).getAsInt());
                        }
                        if (option.getClassType() == Long.class) {
                            option.setValue(configJson.get(key).getAsLong());
                        }
                        if (option.getClassType() == Float.class) {
                            option.setValue(configJson.get(key).getAsFloat());
                        }
                        if (option.getClassType() == Double.class) {
                            option.setValue(configJson.get(key).getAsDouble());
                        }
                        if (option.getClassType() == String.class) {
                            option.setValue(configJson.get(key).getAsString());
                        }
                        if (option.getClassType().isEnum()) {
                            option.setValue(Enum.valueOf(option.getClassType(), configJson.get(key).getAsString()));
                        }
                    }
                }
            } catch (JsonParseException ignored) {}
        } catch (IOException ignored) {}
    }
}
