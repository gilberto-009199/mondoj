package org.voyager.desktop;


import static java.lang.System.out;

import javax.swing.JOptionPane;
import javax.swing.plaf.OptionPaneUI;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.math.Vector3;

import org.voyager.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		final Main game = new Main();
		
		game.setInput(new InputProcessor() {
			
			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				
				return false;
			}
			
			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				
				return false;
			}
			
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			
				return false;
			}
			
			@Override
			public boolean scrolled(int amount) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean keyUp(int keycode) {
				out.println(keycode);
				if(keycode == Input.Keys.DOWN) {
					game.cam.position.y--;
					game.cam.update();
				}else if(keycode == Input.Keys.UP) {
					game.cam.position.y++;
					game.cam.update();
				}else if(keycode == Input.Keys.LEFT) {
					game.cam.position.x--;
					game.cam.update();
				}else if(keycode == Input.Keys.RIGHT) {
					game.cam.position.x++;
					game.cam.update();
				}
				return true;
			}
			int angulo = 8;
			int anguloX = 0;
			int anguloY = 0;
			int anguloZ = 0;
			@Override
			public boolean keyTyped(char character) {
				
				out.println(character);
				if("e".equals(character+"")) {
					//String res = new JOptionPane().showInputDialog(null, "", "", JOptionPane.QUESTION_MESSAGE);
					out.println(" X: "+game.cam.position.x);
					out.println(" Y: "+game.cam.position.y);
					out.println(" Z: "+game.cam.position.z);
				}
				// Input.Keys.LEFT
				if("q".equals(character+"")) {
					anguloY++;
					
				// Input.Keys.RIGHT
				}else if("e".equals(character+"")) {
					anguloY--;
					
				}
				out.println(" A X: "+anguloX);
				out.println(" A X: "+game.cam.direction.x);
				out.println(" A Y: "+anguloY);
				out.println(" A Y: "+game.cam.direction.y);
				out.println(" A Z: "+anguloZ);
				out.println(" A Z: "+game.cam.direction.z);
				
				
				game.cam.rotate(angulo,anguloX,anguloY,anguloZ);
				game.cam.update();
				angulo = 8;
				anguloX = 0;
				anguloY = 0;
				anguloZ = 0;
				
				return true;
			}
			
			@Override
			public boolean keyDown(int keycode) {
					
				
				return true;
			}
		});
		
		new LwjglApplication(game, config);
	}

}
