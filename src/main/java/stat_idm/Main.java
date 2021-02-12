package stat_idm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Création des statistiques
 * @author Maud Garçon & Emmanuel Chauvel
 *
 */

public class Main {

	private static final String HEADER = "Fichier,Egalite,Similitude";
	private static final String DELIMITER = ",";
	private static final String SEPARATOR = "\n";

	static FileOutputStream file = null;

	static FileWriter fw;

	public static void stat(String jsonway1, String jsonway2, String csvway) {

		ObjectMapper mapper = new ObjectMapper();

		JsonNode json1 = null;
		JsonNode json2 = null;

		try {
			json1 = mapper.readTree(new File("./"+jsonway1));
			json2 = mapper.readTree(new File("./"+jsonway2));

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		boolean egalite = json1.equals(json2);
		System.out.println(egalite);

		float similitude = (new Comparator()).compare(json1, json2);
		System.out.println(similitude);

		try {
			fw.write(csvway);
			fw.write(DELIMITER);
			fw.write(Boolean.toString(egalite));
			fw.write(DELIMITER);
			fw.write(Float.toString(similitude));
			fw.write(SEPARATOR);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {

		// Récupération des statistiques de comparaison des fichiers java et python

		try {
			
			String csvway = "./json_csv/comparateur_java_python.csv";

			fw = new FileWriter(csvway, true);

			fw.write(HEADER);
			fw.write(SEPARATOR);

			for (int i = 1; i <= 2; i++) {
				stat("json_java/json_" + i + ".json", "json_python/json_" + i + ".json", csvway);
			}

			fw.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Récupération des statistiques de comparaison des fichiers java et original

		try {
			
			String csvway = "./json_csv/comparateur_java_original.csv";

			fw = new FileWriter(csvway, true);

			fw.write(HEADER);
			fw.write(SEPARATOR);

			for (int i = 1; i <= 2; i++) {
				stat("json_java/json_" + i + ".json", "json_original/json_" + i + ".json", csvway);
			}

			fw.close();

		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		//Récupération des statistiques de comparaison des fichiers original et python

		try {
			
			String csvway = "./json_csv/comparateur_python_original.csv";

			fw = new FileWriter(csvway, true);

			fw.write(HEADER);
			fw.write(SEPARATOR);

			for (int i = 1; i <= 2; i++) {
				stat("json_python/json_" + i + ".json", "json_original/json_" + i + ".json", csvway);
			}

			fw.close();

		} catch (IOException e3) {
			e3.printStackTrace();
		}
	}

}
