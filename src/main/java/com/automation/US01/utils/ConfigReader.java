package com.automation.US01.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    // ─── Chargement automatique au démarrage ───
    static {
        try {
            java.io.InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
            if (is == null) {
                // Fallback to local file path if not found in classpath
                is = new FileInputStream("src/test/resources/config.properties");
            }
            properties.load(is);
            is.close();

        } catch (IOException e) {
            throw new RuntimeException(
                "config.properties introuvable : " + e.getMessage()
            );
        }
    }

    // ─── Retourne la valeur d'une clé ──────────
    public static String get(String key) {
        String value = properties.getProperty(key);

        if (value == null) {
            throw new RuntimeException(
                "Clé introuvable dans config.properties : " + key
            );
        }

        return value.trim();
    }
}