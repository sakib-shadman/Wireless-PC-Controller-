package pcremote;


import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.File;

import javax.swing.*;

public class server {

	/* Object declaration */
	
	private static ServerSocket serverSocket;
	private static Socket clientSocket;
	private static InputStreamReader inputStreamReader;
	private static BufferedReader bufferedReader;
	private static String message,db;
	private static Robot robot;
	private static KeyboardManager keyboardmanager;
	static int key=1;
	static String a="rundll32.exe PowrProf.dll,SetSuspendState";
	static String b= "rundll32.exe powrprof.dll,SetSuspendState 0,1,0";
	static String c="Rundll32.exe User32.dll,LockWorkStation";
	
   
	public static void main(String[] args)  {
		
		try {         
			
			
		       // String[] command = {"cmd.exe", "/C", "Start", "G:\\Users\\myshortcut.lnk"};
			Process p1 =  Runtime.getRuntime().exec("cmd /c start \"\" \"G:\\Users\\myshortcutss.lnk\"");
		        Process p =  Runtime.getRuntime().exec("cmd /c start \"\" \"G:\\Users\\myshortcut.lnk\"");   
		       
		    } catch (IOException ex) {
		    } 
        
		try {
			robot = new Robot();
			keyboardmanager = new KeyboardManager();
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			
			serverSocket = new ServerSocket(4444); // Server socket
 
		} catch (IOException e) {
		System.out.println("Could not listen on port: 4444");
		}
 
	  System.out.println("Server started. Listening to the port 4444");
		clientSocket = new Socket();
 
		while (true) {
			try {
                int flag=0;
				clientSocket = serverSocket.accept(); 
				System.out.println("Connection acepted");// accept the client connection
				inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
				bufferedReader = new BufferedReader(inputStreamReader); // get the client message
				message = bufferedReader.readLine();
				
				PrintWriter writer = new PrintWriter("test.bat", "UTF-8");
		
				
			    System.out.println(message);
			    if(message==null)
					message="cls";
			    
					else if(message.substring(0, 1).equals("?"))
					{
					db =message.substring(1);
					message = "cls";
					}
			    
			    /*Volume Up and Down */
			     
					else if(message.equals("up"))
					{
						flag=1;
					writer.println("/*");
					writer.println("cscript /e:jscript \"%~f0\" */");
					message = "var shl = new ActiveXObject(\"WScript.Shell\");for (var i=0; i<2; i++) {shl.SendKeys(String.fromCharCode(0xAF));}";
					writer.println(message);
					}
			    
					else if(message.equals("down"))
					{
						flag=1;
					writer.println("/*");
					writer.println("cscript /e:jscript \"%~f0\" */");
					message = "var shl = new ActiveXObject(\"WScript.Shell\");for (var i=0; i<2; i++) {shl.SendKeys(String.fromCharCode(0xAE));}";
					writer.println(message);
					}
			    
			    /* Volume up and Down */
			    
			    /* Turn off Wifi Hotspot */ 
					else if(message.equals("disconnect"))
					{
						flag=1;
						try {         
							
							
						       // String[] command = {"cmd.exe", "/C", "Start", "G:\\Users\\myshortcut.lnk"};
						        Process p =  Runtime.getRuntime().exec("cmd /c start \"\" \"G:\\Users\\myshortcuts.lnk\"");   
						       
						    } catch (IOException ex) {
						    } 
				        
					}
			    
			    /* Turn off Wifi Hotspot */ 
			    
				/*	else if(message.startsWith("<"))
						{
						flag=1;
						String[] values = message.split(" ");
						message=values[1];
						//Runtime.getRuntime().exec("cmd "+message);
						// System.out.println(message);
					writer.println(message);
						}  */
			    
			    /* Power options control */
				else if(message.startsWith("y"))
		 		{
					message=message.replace("y","");
					System.out.println(message);
					flag=1;
					writer.println(message);
					//System.out.println("power ");
				}
			    
			    /* Power options control */
			    
			    /* Arrow Controls */
				else if(message.equalsIgnoreCase("UpArrow"))
				{
					robot.keyPress(KeyEvent.VK_UP);
					robot.keyRelease(KeyEvent.VK_UP);
					
				}
				
				else if(message.equalsIgnoreCase("DownArrow")){
					//Simulate press and release of key 'p'
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyRelease(KeyEvent.VK_DOWN);		
					
				}
				else if(message.equalsIgnoreCase("LeftArrow"))
				{
					robot.keyPress(KeyEvent.VK_LEFT);
					robot.keyRelease(KeyEvent.VK_LEFT);	
					
				}
				
				else if(message.equalsIgnoreCase("RightArrow"))
				{
					robot.keyPress(KeyEvent.VK_RIGHT);
					robot.keyRelease(KeyEvent.VK_RIGHT);	
					
				}
			    
			    
			    /* Arrow Controls */
			    
			    /* Keyboard */
				else if (message.startsWith("k")) {
					//System.out.println(message);
					String[] values = message.split(" ");
					if (values != null && values.length == 2) {
						keyboardmanager.pressKey(values[1]);
					}
										
				}
			    
			    /* Keyboard */
				
			    
			    /* Media Control */
				else if (message.startsWith("m")) {
					//System.out.println(message);
					String[] values = message.split(" ");
					
					if(values[1].equals("fullscreen"))
					{
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);	
					}
					
					
					else if(values[1].equals("minimize"))
					{
						robot.keyPress(KeyEvent.VK_ESCAPE);
						robot.keyRelease(KeyEvent.VK_ESCAPE);	
					}
					
					else if(values[1].equals("stop"))
					{
						robot.keyPress(KeyEvent.VK_ALT);
						robot.keyPress(KeyEvent.VK_F4);

						robot.keyRelease(KeyEvent.VK_ALT);
						robot.keyRelease(KeyEvent.VK_F4);	
					}
				 	
					
				}
			    
			    /* Media Control */
			    
			    
			    /* Mouse Pad */
				
				else if(message.contains(",") && !(message.equalsIgnoreCase(a)) && !(message.equalsIgnoreCase(b)) && !(message.equalsIgnoreCase(c)))
				{
					float movex=Float.parseFloat(message.split(",")[0]);
					float movey=Float.parseFloat(message.split(",")[1]);
	 			Point point = MouseInfo.getPointerInfo().getLocation(); 
					float nowx=point.x;
					float nowy=point.y;
					robot.mouseMove((int)(nowx+movex),(int)(nowy+movey));
					
				} 
			
				else if(message.contains("MOUSE_LEFT_CLICK")){
					
					robot.mousePress(InputEvent.BUTTON1_MASK);
					
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
					
				}
				else if(message.contains("MOUSE_RIGHT_CLICK"))
				{
					robot.mousePress(InputEvent.BUTTON3_MASK);
					robot.mouseRelease(InputEvent.BUTTON3_MASK);
					
				}
			    
			    /* Mouse Pad */
			    
			    
			
				else if(message.equalsIgnoreCase("space"))
				{
					robot.keyPress(KeyEvent.VK_SPACE);
					robot.keyRelease(KeyEvent.VK_SPACE);	
				}
			    
			    /* Slide */
				else if(message.equalsIgnoreCase("escape"))
				{
					robot.keyPress(KeyEvent.VK_ESCAPE);
					robot.keyRelease(KeyEvent.VK_ESCAPE);	
				}
				else if(message.equalsIgnoreCase("f5"))
				{
					robot.keyPress(KeyEvent.VK_F5);
					robot.keyRelease(KeyEvent.VK_F5);	
				}	
			    
				else if(message.equalsIgnoreCase("home"))
				{
					robot.keyPress(KeyEvent.VK_HOME);
					robot.keyRelease(KeyEvent.VK_HOME);	
				}	
			    
				else if(message.equalsIgnoreCase("end"))
				{
					robot.keyPress(KeyEvent.VK_END);
					robot.keyRelease(KeyEvent.VK_END);	
				}	
			    
			    /* Slide */
			    
				//writer.println(message);
				writer.close();
				try{
					Process p = Runtime.getRuntime()
						.exec("rundll32 url.dll,FileProtocolHandler test.bat");
					p.waitFor();
					} catch(Exception e){
			//		System.out.println("operation is failed.");
					}
		
				if (flag==1)
				{
		    	try {
				   Thread.sleep(1000);
		    		;
					} catch(InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
				}
				try{
					File file = new File("test.bat");
					if(file.delete()){
				
					}else{
					//		System.out.println("Delete operation is failed.");
						}
					}catch(Exception e){
							e.printStackTrace(); 
					}
				
				inputStreamReader.close();
				clientSocket.close();
				
			} catch (IOException ex) {
			//	System.out.println("Problem in message reading");
			}
		}    
		       
		   
	}
	
	
}
