package com.mygdx.deleter.ui;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.mygdx.deleter.screens.MainScreen;

public class TxtFileHandler {
	private static List<String> fotoListFromTXT;
	
	public static void makeTXTFile(List<String> name) {
		FileWriter writer = null;
		try {
			writer = new FileWriter("PhotoList.txt");
			for (String str : name) {
				writer.write(str);
				writer.write("\r\n");
			}
			MainScreen.addMessageBottomPannel("PhotoList.txt file made");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void readTxtFile(String yourFile) {
		fotoListFromTXT = new ArrayList<String>();
		try {
			FileInputStream fstream_school = new FileInputStream(yourFile);
			DataInputStream data_input = new DataInputStream(fstream_school);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(data_input));
			String str_line;
			while ((str_line = buffer.readLine()) != null) {
				str_line = str_line.trim();
				if ((str_line.length() != 0)) {
					
					MainScreen.addMessageLeftPannel(str_line);
					fotoListFromTXT.add(str_line);
				}
			}
			for (String listaZdjec : fotoListFromTXT) {
				Gdx.app.log("from TXT: ", listaZdjec);
			}
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
