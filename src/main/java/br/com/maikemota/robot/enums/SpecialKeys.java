package br.com.maikemota.robot.enums;

import static java.awt.event.KeyEvent.*;

public enum SpecialKeys {
    ENTER(VK_ENTER),
    CONTROL(VK_CONTROL);
    
    private final int keyCode;
     
    private SpecialKeys(int keyCode) {
        this.keyCode = keyCode;
    }

    public int geKeyCode() {
        return this.keyCode;
    }
}