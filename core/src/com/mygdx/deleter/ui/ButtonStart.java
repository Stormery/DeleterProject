package com.mygdx.deleter.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ButtonStart extends Button{

	private static TextureAtlas startButtonAtlas;
	public ButtonStart(final IClickCallback callback){
		super(prepareStartButtonStyle());
		init(callback);
	}
	
private void init(final IClickCallback callback) {
	this.addListener(new ClickListener(){
		@Override
		public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			callback.onClick();
			return super.touchDown(event, x, y, pointer, button);
		}
	});
	
	}
	//	public ButtonHandler(String imageName, final IClickCallback callback) {
//		super(new Texture (imageName));
//		this.addListener(new ClickListener(){
//			@Override
//			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//				callback.onClick();
//				return super.touchDown(event, x, y, pointer, button);
//			}
//		});
//	}
	private static ButtonStyle prepareStartButtonStyle(){
		startButtonAtlas = new TextureAtlas("buttons/allButtons.pack");
		Skin skin  = new Skin(startButtonAtlas);
		ButtonStyle buttonStyle = new ButtonStyle();
		buttonStyle.up = skin.getDrawable("smallButtonStartUp");
		buttonStyle.down = skin.getDrawable("smallButtonStartDown");
		return buttonStyle;
	}
}
