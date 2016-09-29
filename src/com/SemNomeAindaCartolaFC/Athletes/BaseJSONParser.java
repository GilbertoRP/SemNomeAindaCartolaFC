package com.SemNomeAindaCartolaFC.Athletes;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;
import org.json.JSONObject;

public class BaseJSONParser {
	
	public static JSONObject getJSONObjectFromFile(String stringFilePath) throws IOException {

		Path filePath = Paths.get(stringFilePath);
		try(Stream<String> lines = Files.lines(filePath)){
			String jsonString = lines.reduce((line, lastLine) -> line + lastLine).get();
			return new JSONObject(jsonString);
		}
		finally {
			
		}
	}

}
