package com.SemNomeAindaCartolaFC.Athletes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;
import org.json.JSONObject;

public class BaseJSONParser {
	
	public static JSONObject getJSONObjectFromFile(String jsonFilePath) throws IOException {

		String jsonString = BaseJSONParser.readAllLinesAsTextFromFile(jsonFilePath);
		return new JSONObject(jsonString);
	}

	private static String readAllLinesAsTextFromFile(String filePath) {
		StringBuilder builder = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			for(String line; (line = br.readLine()) != null; ) {
				builder.append(line);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return builder.toString();
	}

}
