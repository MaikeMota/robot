package br.com.maikemota.robot.keyboard;

import java.awt.event.KeyEvent;

public enum SpecialKeys {
    ENTER(KeyEvent.VK_ENTER),
    CONTROL(KeyEvent.VK_CONTROL),
    WINDOWS(KeyEvent.VK_WINDOWS),
    ARROW_UP(KeyEvent.VK_UP),
    ARROW_RIGHT(KeyEvent.VK_RIGHT),
    ARROW_DOWN(KeyEvent.VK_DOWN),
    ARROW_LEFT(KeyEvent.VK_LEFT)
    ;
    
    private final int keyCode;
     
    private SpecialKeys(int keyCode) {
        this.keyCode = keyCode;
    }

    public int geKeyCode() {
        return this.keyCode;
    }
}