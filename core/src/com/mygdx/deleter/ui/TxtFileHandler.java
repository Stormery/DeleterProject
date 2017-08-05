package com.mygdx.deleter.ui;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.mygdx.deleter.screens.MainScreen;

public class TxtFileHandler {
	
	private static String photoOnListName;
	public static void makeTXTFile(List<String> name) {
		FileWriter writer = null;
		try {
			writer = new FileWriter("PhotoList.txt");
			for (String str : name) {
				photoOnListName = str.substring(str.lastIndexOf("\\")+1,str.lastIndexOf("."));
				photoOnListName = photoOnListName.substring(photoOnListName.length()-4);
				writer.write(photoOnListName);
				writer.write("\r\n");
			}
			MainScreen.addMessageBottomPannel("PhotoList.txt file made");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void readTxtFile(String yourFile) {
		
		try {
			FileInputStream fstream_school = new FileInputStream(yourFile);
			DataInputStream data_input = new DataInputStream(fstream_school);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(data_input));
			String str_line;
			while ((str_line = buffer.readLine()) != null) {
				str_line = str_line.trim();
				if ((str_line.length() != 0)) {
					
					MainScreen.addMessageBottomPannel(str_line);
					MainScreen.	fotoListFromTXT.add(str_line);
				}
			}
			for (String listaZdjec : MainScreen.fotoListFromTXT) {
				Gdx.app.log("from TXT: ", listaZdjec);
			}
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
