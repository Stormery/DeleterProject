package com.mygdx.deleter.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class TableContainer {
	
	private static Table tableMain;
	private static Table tableInner;
	private static Table tableMid;
	private static Table tableRight;
	private static Table tableLeftScrollable;
	private static Table tableBottomScrollable;
	
	
	private static ScrollPane scrollPaneLeft;
	private static ScrollPane scrollPaneBottom;
	
	private static boolean tableDebug = false;
	
	

	public static void addMessageLeftPannel(String message) {
		tableLeftScrollable.add(new Label(message, MainScreen.skin)).left();
		tableLeftScrollable.row();
		scrollPaneLeft.layout();
		scrollPaneLeft.setScrollPercentY(100);
		scrollPaneLeft.setScrollPercentX(0);
		scrollPaneLeft.updateVisualScroll(); 
	}
	public static void addMessageBottomPannel(String message) {	
		tableBottomScrollable.add(new Label(message, MainScreen.skin)).left();
		tableBottomScrollable.row();
		scrollPaneBottom.layout();
		scrollPaneBottom.setScrollPercentY(100);
		scrollPaneBottom.setScrollPercentX(0);
		scrollPaneBottom.updateVisualScroll(); 
	}


	public static void initTables() {
		tableMain = new Table();
		tableMain.setFillParent(true);
		tableMain.setDebug(tableDebug);
		tableMain.top().left();

		tableInner = new Table(MainScreen.skin);
		tableInner.setDebug(tableDebug);
		tableInner.top();
		
		tableMid = new Table(MainScreen.skin);
		tableMid.setDebug(tableDebug);
		tableMid.top().left();
		
		tableRight = new Table(MainScreen.skin);
		tableRight.setDebug(tableDebug);
		tableRight.top().left();
		// ScrollingTable
				tableLeftScrollable = new Table();
				tableLeftScrollable.setDebug(false);
				tableLeftScrollable.top().left();
				
				tableBottomScrollable = new Table();
				tableBottomScrollable.setDebug(false);
				tableBottomScrollable.top().left();

				scrollPaneLeft = new ScrollPane(tableLeftScrollable);
				scrollPaneLeft.setOverscroll(false, false);
				
				scrollPaneBottom = new ScrollPane(tableBottomScrollable);
				scrollPaneBottom.setOverscroll(false, false);
				/////////////
				
		//Left Collumn
		tableInner.add(scrollPaneLeft).width(153f).height(440f).padLeft(6f).padTop(25f);
		//Mid Collumn
		tableInner.add(tableMid).width(315f).padLeft(10f).left().top();
		tableMid.add().height(170f).expandX().padBottom(10f);
		tableMid.row();
		tableMid.add().height(145f).expandX().padTop(10f);
		tableMid.row();
		tableMid.add(scrollPaneBottom).height(135f).width(315f).padBottom(5f).padTop(25f);
		
		//Right Collumn
		tableInner.add(tableRight).width(200f);
		tableRight.add().height(170f).expandX().padBottom(10f);
		tableRight.row();
		tableRight.add().height(145f).expandX().padTop(10f);
		tableRight.row();
		tableRight.add(MainScreen.buttonTxtStart).height(56f).expandX().padTop(25f);
		tableRight.row();
		tableRight.add(MainScreen.startButton).height(56f).width(96f).padBottom(20f).padTop(20f);
		
		


		tableMain.add(tableInner);
		MainScreen.stage.addActor(tableMain);
	}

}
