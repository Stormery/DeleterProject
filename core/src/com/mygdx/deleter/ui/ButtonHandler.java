package com.mygdx.deleter.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ButtonHandler extends Image{

	public ButtonHandler(String imageName, final IClickCallback callback) {
		super(new Texture (imageName));
		this.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				callback.onClick();
				return super.touchDown(event, x, y, pointer, button);
			}
		});
	}
}
