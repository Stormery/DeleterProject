package com.mygdx.deleter.buttons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.deleter.ui.IClickCallback;

public class ButtonSwitch extends Button {
	private static TextureAtlas switchButtonAtlas;
	
	public ButtonSwitch(final IClickCallback callback){
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
	private static ButtonStyle prepareStartButtonStyle(){
		switchButtonAtlas = new TextureAtlas("buttons/switchButton.pack");
		Skin skin  = new Skin(switchButtonAtlas);
		ButtonStyle buttonStyle = new ButtonStyle();
		buttonStyle.up = skin.getDrawable("01Off");
		buttonStyle.checked = skin.getDrawable("01On");
		
		
		return buttonStyle;
	}
}

