package com.mygdx.deleter.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.mygdx.deleter.screens.MainScreen;
import com.mygdx.deleter.screens.TableContainer;

public class DeleteFilesClass {
	private static List<String> deleteList; 
	private static int amountOfDelete=0; //counting how many files you want to delete
	private static int amountOfSurvive=0;
	
	public static void deleteFiles() {
		String photoShort; 
		boolean delete;
		deleteList = new ArrayList<String>();
		TableContainer.addMessageBottomPannel("DELETE FILES PROCES");
		/*
		 * Iterate threw all files and check if this file is on list,
		 * if it is on a list or its TXT file, its skipping
		 * if its not on a list adding to list for delete.
		 */
		for(String photo : MainScreen.inputFilesList){
			//Making shorter name to compare with list (1342, 2122 instead full path
			photoShort = photo.substring(photo.lastIndexOf("\\")+1,photo.lastIndexOf("."));
			photoShort = photoShort.substring(photoShort.length()-4);
			delete = true;
			
			for(String text : MainScreen.fotoListFromTXT){
				if((photo.contains(".txt"))||(photoShort.equals(text)) ){
					System.out.println(photoShort+ " zostawiam");
					delete = false;
					amountOfSurvive++;
				}					
			}
			if(delete){
				deleteList.add(photo);	
				amountOfDelete++;
			}
		}
		
		//Popup Dialog if you are sure to delete files
		Dialog dialog = new Dialog("Warning", MainScreen.skin, "dialog") {
		    public void result(Object obj) {
		        System.out.println("result "+obj);
		       // doYouWantToDelete(deleteList);
		    }
		};
		dialog.text("Are you sure you want to delete " + amountOfDelete + " photos?");
		dialog.text(amountOfSurvive + " files will left");
		dialog.button("Yes", true); //sends "true" as the result
		dialog.button("No", false);  //sends "false" as the result
		dialog.key(Keys.ENTER, true); //sends "true" when the ENTER key is pressed
		dialog.show(MainScreen.stage);
	}
	
	private static void doYouWantToDelete(List<String> deleteList) {
// If u want to delete files it going threw all deleteList and deleting it permanently
		for(String deletePhoto : deleteList){
			System.out.println("Usuwam: " + deletePhoto);
			MainScreen.inputFilesList.remove(deletePhoto);
			 new File(deletePhoto).delete();
		}
		
	}
}
