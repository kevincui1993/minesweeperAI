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

/**
 *
 * @author Johnn
 */
public class MinesweeperAI {

    /**
     * @param args the command line arguments
     */
    
    static String PROGRAM_NAME = "MineSweeper";
    static double EASY_BUTTON_OFFSET = 170;
    
    public static void click(int x, int y) throws AWTException{
        Robot bot = new Robot();
        bot.mouseMove(x, y);    
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    
    public static void selectLevel(int windowX, int width, int windowY) throws AWTException {
        int easyX = windowX + width / 2;
        int easyY = (int) (windowY + EASY_BUTTON_OFFSET);
        System.out.println(easyX);
        System.out.println(easyY);
        click(easyX, easyY);
    }
    
    public static void main(String[] args) throws AWTException {
        final Rectangle programWindow = new Rectangle(0, 0, 0, 0); //needs to be final or effectively final for lambda
        WindowUtils.getAllWindows(true).forEach(desktopWindow -> {
            if (desktopWindow.getTitle().contains(PROGRAM_NAME)) {
                programWindow.setRect(desktopWindow.getLocAndSize());
            }
        });
        
        selectLevel(programWindow.x, programWindow.width, programWindow.y);
    }
    
}
