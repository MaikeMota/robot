package br.com.maikemota.robot;

import java.awt.AWTException;
import java.awt.Robot;

public class RobotController { 

    private final Robot robotInstance;

    private int delayBetweenClicks = 300;
    private int delayBetweenMouseMoviments = 20;
    private int delayBetweenKeys = 20;

    private int currentMouseX = 0;
    private int currentMouseY = 0;

    private int targetMouseX = 0;
    private int targetMouseY = 0;

    private boolean running = false;

    private Thread thread;

    private RobotController() throws AWTException {
        this.robotInstance = new Robot();

        this.thread = new Thread() {     
            @Override
            public void run() {

                while(running) {
                    if(currentMouseX != targetMouseX){
                        currentMouseX++;
                    }
                    if(currentMouseY != targetMouseY){
                        currentMouseY++;
                    }
                    robotInstance.mouseMove(currentMouseX, currentMouseY);
                }

            }
          };
    }

    public static RobotController initialize() throws AWTException {
        RobotController controller = new RobotController();
        controller.thread.start();
        controller.running = true;
        return controller;
    }

    public static RobotController initialize(final int delayBetweenClicks, final int delayBetweenMouseMoviments, final int delayBetweenKeys) throws AWTException {
        RobotController controller = RobotController.initialize();
        controller.delayBetweenClicks = delayBetweenClicks;
        controller.delayBetweenMouseMoviments = delayBetweenMouseMoviments;
        controller.delayBetweenKeys = delayBetweenKeys;
        return controller;        
    }

    public RobotController setDelayBetweenClicks(final int delay){ 
        this.delayBetweenClicks = delay;
        return this;
    }

    public RobotController setDelayBetweenMouseMoviments(final int delay){ 
        this.delayBetweenMouseMoviments= delay;
        return this;
    }

    public RobotController setDelayBetweenKeys(final int delay){ 
        this.delayBetweenKeys = delay;
        return this;
    }

    public RobotController moveMouse(final int x, final int y) {
        this.targetMouseX = x;
        this.targetMouseY = y;
        return this;
    }

    public void stop() {
        //this.running = false;
        //this.thread.interrupt();
    }



}