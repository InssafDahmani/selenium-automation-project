package com.automation.US01.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MarkdownReporter {
    private static File reportFile;
    private static FileWriter writer;

    public static void initReport() {
        String dateName = new SimpleDateFormat("yyyy-MM-dd_HH-mm").format(new Date());
        String dirPath = System.getProperty("user.dir") + "/Reports";
        new File(dirPath).mkdirs(); // Créer le dossier s'il n'existe pas
        
        reportFile = new File(dirPath + "/TestReport_" + dateName + ".md");
        try {
            writer = new FileWriter(reportFile, true);
            writer.write("#  Rapport d'Exécution des Tests\n\n");
            writer.write("**Date d'exécution :** " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + "\n");
            writer.write("**Environnement :** QA | **Navigateur :** " + ConfigReader.get("browser") + "\n\n");
            writer.write("##  Résultats\n\n");
            writer.write("| Nom du Test | Statut | Message / Erreur | Capture d'écran |\n");
            writer.write("|-------------|--------|------------------|-----------------|\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addTestResult(String testName, String status, String message, String... screenshotPaths) {
        if (writer != null) {
            try {
                // Formater les liens des images pour Markdown
                String ssLink = "N/A";
                if (screenshotPaths != null && screenshotPaths.length > 0 && screenshotPaths[0] != null && !screenshotPaths[0].isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    for (String path : screenshotPaths) {
                        if (path != null && !path.isEmpty()) {
                            sb.append("<img src=\"").append(path).append("\" width=\"300\"/> ");
                        }
                    }
                    ssLink = sb.toString();
                }
                    
                // Nettoyer le message pour ne pas casser le tableau Markdown
                String safeMessage = message != null ? message.replace("|", " ").replace("\n", "<br>") : "";
                
                // Si c'est un PASS, on met un emoji vert, sinon rouge
                String statusIcon = status.equalsIgnoreCase("PASS") ? "✅ PASS" 
                                  : status.equalsIgnoreCase("FAIL") ? "❌ FAIL" 
                                  : "⚠️ SKIP";

                writer.write(String.format("| %s | %s | %s | %s |\n", testName, statusIcon, safeMessage, ssLink));
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeReport() {
        if (writer != null) {
            try {
                writer.write("\n---\n*Rapport généré automatiquement par le framework Selenium.*\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
