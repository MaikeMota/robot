package br.com.maikemota.robot;

import java.awt.AWTException;

public class MainClass {

    public static void main(String[] args) throws AWTException {

        RobotController
            .initialize(500)
            .moveMouse(160, 1015)
            .clickLeftMouseButton()
            .wait(5000)
            .holdControlKey()
            .type("m")
            .wait(100)
            .type("t")
            .releaseControlKey()
            .wait(100)
            .moveMouse(900, 55)
            .wait(500)
            .doubleClickLeftMouseButton()
            .type("https://stackoverflow.com")
            .hitEnter();
    }
}