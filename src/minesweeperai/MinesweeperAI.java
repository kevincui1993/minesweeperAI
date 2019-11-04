/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeperai;

import java.util.Arrays;
import com.sun.jna.*;
import com.sun.jna.platform.WindowUtils;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.*;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Johnn
 */
public class MinesweeperAI implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param args the command line arguments
     */
    
    enum GAME_LEVEL {
        GAME_LEVEL_EASY,
        GAME_LEVEL_MEDIUM,
        GAME_LEVEL_HARD
    }
    
    static String PROGRAM_NAME = "MineSweeper";
    static double EASY_BUTTON_OFFSET = 170;
    static int TILE_LENGTH = 30;
    static int window_start_x = 0;
    static int window_start_y = 0;
    static int window_width   = 0;
    static int window_height  = 0;
    static GAME_LEVEL game_level = GAME_LEVEL.GAME_LEVEL_EASY;
    static int is_running = 100;
    
    public static void click(int x, int y) throws AWTException{
        Robot bot = new Robot();
        bot.mouseMove(x, y);    
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    
    public static void selectLevel() throws AWTException {
        int easyX = window_start_x + window_width / 2;
        int easyY = (int) (window_start_y + EASY_BUTTON_OFFSET);
        System.out.println(easyX);
        System.out.println(easyY);
        click(easyX, easyY);
    }
    
    public static void gamePlay() throws AWTException
    {
        if (game_level == GAME_LEVEL.GAME_LEVEL_EASY) {
            int gridX = window_start_x + window_width / 2 - TILE_LENGTH * 5;
            int gridY = window_start_y + 100;
            int size = TILE_LENGTH * 10;
            
            int randomX = (int) ((Math.random() * (size + 1)) + gridX);
            int randomY = (int) ((Math.random() * (size + 1)) + gridY);
            
            click(randomX, randomY);
        }
    }
    
    public static boolean isRunning() {
        return --is_running != 0;
        
    }
  
    public static void main(String[] args) throws AWTException, InterruptedException {
        final Rectangle programWindow = new Rectangle(0, 0, 0, 0); //needs to be final or effectively final for lambda
        WindowUtils.getAllWindows(true).forEach(desktopWindow -> {
            if (desktopWindow.getTitle().contains(PROGRAM_NAME)) {
                programWindow.setRect(desktopWindow.getLocAndSize());
            }
        });
        window_start_x = programWindow.x;
        window_start_y = programWindow.y;
        window_width = programWindow.width;
        window_height = programWindow.height;
        
        is_running = 5;
        
        System.out.println(programWindow);
        selectLevel();
        
        while (isRunning()){
            Thread.sleep(200);
            gamePlay();
        }
        
        System.out.println("Finished");
    }  
}
