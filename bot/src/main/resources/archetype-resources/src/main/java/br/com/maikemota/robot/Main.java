package br.com.maikemota.robot;

import java.awt.AWTException;

public class Main {

    public static void main(String[] args) throws AWTException, InterruptedException {
        RobotController
            .initialize()
            .moveMouse(500, 500)
            .moveMouse(600, 600)
            .stop();


            Thread.sleep(5000000);
    }
 }