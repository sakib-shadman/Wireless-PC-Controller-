package pcremote;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class KeyboardManager {

	
	
		private Robot robot;
		
		
		
		public KeyboardManager() throws AWTException {
			this.robot = new Robot();
		}
		
		
		public void pressKey(String key) {
			
			 if(key.equalsIgnoreCase("save"))
			{
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_S);
				
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_S);
				
			}
			
			else if(key.equalsIgnoreCase("clear"))
			{
				
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
			
				
			}
			else if(key.equals("close"))
			{
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_F4);
				
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyRelease(KeyEvent.VK_F4);
				
			}
			else
			{
				int code = getKeyCode(key);
				
				//System.out.println("SERVER Key '" + code + "'");
				if(code!=-1)
				{
					robot.keyPress(code);
				    robot.keyRelease(code);
				
				}
				
			}
			
		}
		
		private int getKeyCode(String key) {
			if (key.equals("ENTER")) {
				return KeyEvent.VK_ENTER;
			} else if (key.equals("BACK_SPACE")) {
				return KeyEvent.VK_BACK_SPACE;
			} else if (key.equals("TAB")) {
				return KeyEvent.VK_TAB;
			} else if (key.equals("CAPS_LOCK")) {
				return KeyEvent.VK_CAPS_LOCK;
			} else if (key.equals("LEFT")) {
				return KeyEvent.VK_LEFT;
			} else if (key.equals("UP")) {
				return KeyEvent.VK_UP;
			} else if (key.equals("RIGHT")) {
				return KeyEvent.VK_RIGHT;
			} else if (key.equals("DOWN")) {
				return KeyEvent.VK_DOWN;
			} else if (key.equals("DELETE")) {
				return KeyEvent.VK_DELETE;
			} else if (key.equals("EURO_SIGN¬")) {
				return KeyEvent.VK_EURO_SIGN;
			} else if (key.equals("SPACE")) {
				return KeyEvent.VK_SPACE;
			} else if (key.equals("@")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_2);
	            robot.keyRelease(KeyEvent.VK_SHIFT);
	            robot.keyRelease(KeyEvent.VK_2);
				return -1;
			} else if (key.equals(",")) {
				return KeyEvent.VK_COMMA;
			} else if (key.equals("-")) {
				return KeyEvent.VK_MINUS;
			} else if (key.equals(".")) {
				return KeyEvent.VK_PERIOD;
			} else if (key.equals(":")) {
				
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_SEMICOLON);
	            robot.keyRelease(KeyEvent.VK_SHIFT);
	            robot.keyRelease(KeyEvent.VK_SEMICOLON);
				return -1;
				
			} else if (key.equals(";")) {
				return KeyEvent.VK_SEMICOLON;
			} else if (key.equals("/")) {
				return KeyEvent.VK_SLASH;
			} else if (key.equals("=")) {
				return KeyEvent.VK_EQUALS;
			} else if (key.equals("[")) {
				return KeyEvent.VK_OPEN_BRACKET;
			} else if (key.equals("]")) {
				return KeyEvent.VK_CLOSE_BRACKET;
			} else if (key.equals("\\")) {
				return KeyEvent.VK_BACK_SLASH;
			} else if (key.equals("*")) {
				
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_8);
	            robot.keyRelease(KeyEvent.VK_SHIFT);
	            robot.keyRelease(KeyEvent.VK_8);
				return -1;
				
			} else if (key.equals("+")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_EQUALS);
	            robot.keyRelease(KeyEvent.VK_SHIFT);
	            robot.keyRelease(KeyEvent.VK_EQUALS);
				return -1;
			} else if (key.equals("\"")) {
				return KeyEvent.VK_QUOTE;
			} else if (key.equals("&")) {
				return KeyEvent.VK_AMPERSAND;
			} else if (key.equals("<")) {
				return KeyEvent.VK_LESS;
			} else if (key.equals(">")) {
				return KeyEvent.VK_GREATER;
			} else if (key.equals("%")) {
				
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_5);
	            robot.keyRelease(KeyEvent.VK_SHIFT);
	            robot.keyRelease(KeyEvent.VK_5);
				return -1;
				
			} else if (key.equals("$")) {
				
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_4);
	            robot.keyRelease(KeyEvent.VK_SHIFT);
	            robot.keyRelease(KeyEvent.VK_4);
				return -1;
				
			} else if (key.equals("!")) {
				
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_1);
	            robot.keyRelease(KeyEvent.VK_SHIFT);
	            robot.keyRelease(KeyEvent.VK_1);
				return -1;
			} else if (key.equals("?")) {
				
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_SLASH);
	            robot.keyRelease(KeyEvent.VK_SHIFT);
	            robot.keyRelease(KeyEvent.VK_SLASH);
				return -1;
				
			} else if (key.equals("(")) {
				
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_9);
	            robot.keyRelease(KeyEvent.VK_SHIFT);
	            robot.keyRelease(KeyEvent.VK_9);
				return -1;
				//return KeyEvent.VK_LEFT_PARENTHESIS;
			} else if (key.equals(")")) {
				
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_0);
	            robot.keyRelease(KeyEvent.VK_SHIFT);
	            robot.keyRelease(KeyEvent.VK_0);
				return -1;
				
			} else if (key.equals("#")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_3);
	            robot.keyRelease(KeyEvent.VK_SHIFT);
	            robot.keyRelease(KeyEvent.VK_3); 
				return -1;
				
			} else if (key.equals("_")) {
				
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_MINUS);
	            robot.keyRelease(KeyEvent.VK_SHIFT);
	            robot.keyRelease(KeyEvent.VK_MINUS);
				return -1;
				
			} else if (key.equals("0")) {
				return KeyEvent.VK_0;
			} else if (key.equals("1")) {
				return KeyEvent.VK_1;
			} else if (key.equals("2")) {
				return KeyEvent.VK_2;
			} else if (key.equals("3")) {
				return KeyEvent.VK_3;
			} else if (key.equals("4")) {
				return KeyEvent.VK_4;
			} else if (key.equals("5")) {
				return KeyEvent.VK_5;
			} else if (key.equals("6")) {
				return KeyEvent.VK_6;
			} else if (key.equals("7")) {
				return KeyEvent.VK_7;
			} else if (key.equals("8")) {
				return KeyEvent.VK_8;
			} else if (key.equals("9")) {
				return KeyEvent.VK_9;
			} else if (key.equalsIgnoreCase("A")) {
				return KeyEvent.VK_A;
			} else if (key.equalsIgnoreCase("B")) {
				return KeyEvent.VK_B;
			} else if (key.equalsIgnoreCase("C")) {
				return KeyEvent.VK_C;
			} else if (key.equalsIgnoreCase("D")) {
				return KeyEvent.VK_D;
			} else if (key.equalsIgnoreCase("E")) {
				return KeyEvent.VK_E;
			} else if (key.equalsIgnoreCase("F")) {
				return KeyEvent.VK_F;
			} else if (key.equalsIgnoreCase("G")) {
				return KeyEvent.VK_G;
			} else if (key.equalsIgnoreCase("H")) {
				return KeyEvent.VK_H;
			} else if (key.equalsIgnoreCase("I")) {
				return KeyEvent.VK_I;
			} else if (key.equalsIgnoreCase("J")) {
				return KeyEvent.VK_J;
			} else if (key.equalsIgnoreCase("K")) {
				return KeyEvent.VK_K;
			} else if (key.equalsIgnoreCase("L")) {
				return KeyEvent.VK_L;
			} else if (key.equalsIgnoreCase("M")) {
				return KeyEvent.VK_M;
			} else if (key.equalsIgnoreCase("N")) {
				return KeyEvent.VK_N;
			} else if (key.equalsIgnoreCase("O")) {
				return KeyEvent.VK_O;
			} else if (key.equalsIgnoreCase("P")) {
				return KeyEvent.VK_P;
			} else if (key.equalsIgnoreCase("Q")) {
				return KeyEvent.VK_Q;
			} else if (key.equalsIgnoreCase("R")) {
				return KeyEvent.VK_R;
			} else if (key.equalsIgnoreCase("S")) {
				return KeyEvent.VK_S;
			} else if (key.equalsIgnoreCase("T")) {
				return KeyEvent.VK_T;
			} else if (key.equalsIgnoreCase("U")) {
				return KeyEvent.VK_U;
			} else if (key.equalsIgnoreCase("V")) {
				return KeyEvent.VK_V;
			} else if (key.equalsIgnoreCase("W")) {
				return KeyEvent.VK_W;
			} else if (key.equalsIgnoreCase("X")) {
				return KeyEvent.VK_X;
			} else if (key.equalsIgnoreCase("Y")) {
				return KeyEvent.VK_Y;
			} else if (key.equalsIgnoreCase("Z")) {
				return KeyEvent.VK_Z;
			}
			
			return -1;
		}
		
}
