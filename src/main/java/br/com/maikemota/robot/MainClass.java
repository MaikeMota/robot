package br.com.maikemota.robot;

import java.awt.AWTException;
import java.io.IOException;

public class MainClass {

    public static void main(String[] args) throws AWTException, IOException {

        RobotController
            .initialize(500)
            .moveMouse(160, 1015)
            .clickLeftMouseButton()
            .wait(5000)
            .holdWindowsKey()
            .hitArrowUpKey()
            .wait(100)
            .releaseWindowsKey()
            .holdControlKey()
            .type("t")
            .releaseControlKey()
            .wait(100)
            .moveMouse(900, 55)
            .doubleClickLeftMouseButton()
            .type("https://stackoverflow.com")
            .hitEnter()
            .wait(2500)
            .moveMouse(700, 130)
            .clickLeftMouseButton()
            .wait(500)
            .type("quick sort java")
            .hitEnter()
            .wait(2500)
            .takeScreenShot();
    }
}