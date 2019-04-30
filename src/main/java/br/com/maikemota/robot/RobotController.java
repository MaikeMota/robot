package br.com.maikemota.robot;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.AWTException;
import java.awt.MouseInfo;

import br.com.maikemota.robot.enums.SpecialKeys;
import br.com.maikemota.robot.utils.KeyboardUtil;



public class RobotController {

    private static Robot context;

    private RobotController() throws AWTException {}

    public static RobotController initialize() throws AWTException {
        RobotController controller = new RobotController();
        RobotController.context = new Robot();
        KeyboardUtil.setContext(RobotController.context);
        // MouseUtil.setContext(RobotController.context);
        return controller;
    }

    public static RobotController initialize(final int startUpDelay) throws AWTException {
        RobotController controller = RobotController.initialize();
        RobotController.context.delay(startUpDelay);
        return controller;
    }

    public static RobotController initialize(final int delayBetweenClicks, final int delayBetweenKeys) throws AWTException {
        RobotController controller = RobotController.initialize();
        return controller;
    }

    public RobotController moveMouse(final double x, final double y) {
        double currentX = this.getCurrentMouseX();
        double currentY = this.getCurrentMouseY();
        while (currentX != x || currentY != y) {
            if (currentX != x) {
                currentX = this.applyStep(currentX, x);
            }
            if (currentY != y) {
                currentY = this.applyStep(currentY, y);
            }
            RobotController.context.mouseMove((int)currentX, (int)currentY);
            RobotController.context.delay(1);
        }
        return this;
    }

    public RobotController pressLeftMouseButton() {
        RobotController.context.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        return this;
    }

    public RobotController releaseLeftMouseButton() {
        RobotController.context.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        return this;
    }

    public RobotController clickLeftMouseButton() {
        this.pressLeftMouseButton();
        RobotController.context.delay(50);
        this.releaseLeftMouseButton();
        return this;
    }

    public RobotController doubleClickLeftMouseButton() {
        this.clickLeftMouseButton();
        RobotController.context.delay(50);
        this.clickLeftMouseButton();
        return this;
    }

    public RobotController pressRightMouseButton() {
        RobotController.context.mousePress(InputEvent.BUTTON2_DOWN_MASK);
        return this;
    }

    public RobotController releaseRightMouseButton() {
        RobotController.context.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
        return this;
    }

    public RobotController clickRightMouseButton() {
        this.pressRightMouseButton();
        RobotController.context.delay(50);
        this.releaseRightMouseButton();
        return this;
    }

    public RobotController doubleClickRightMouseButton() {
        this.clickRightMouseButton();
        RobotController.context.delay(50);
        this.clickRightMouseButton();
        return this;
    }

    public RobotController type(final String text) {
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char character = text.charAt(i);
            KeyboardUtil.type(character);
            this.wait(20);
        }
        return this;
    }    

    public RobotController hitEnter() {
        this.holdEnter();
        this.releaseEnter();
        return this;
    }

    public RobotController holdEnter() {
        KeyboardUtil.hold(SpecialKeys.ENTER);
        return this;
    }

    public RobotController releaseEnter() {
        KeyboardUtil.release(SpecialKeys.ENTER);
        return this;
    }

    public RobotController holdControlKey() {
        KeyboardUtil.hold(SpecialKeys.CONTROL);
        return this;
    }

    public RobotController releaseControlKey() {
        KeyboardUtil.release(SpecialKeys.CONTROL);
        return this;
    }

    public RobotController wait(final int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    private double applyStep(final double reference, final double target) {
        final double newPosition = reference + this.calculateStep(reference, target);
        return newPosition;
    }

    private double calculateStep(final double reference, final double target) { 
        double step = target > reference ? 1 : -1;
        return step;
    }

    private double getCurrentMouseX() {
        return MouseInfo.getPointerInfo().getLocation().getX();
    }

    private double getCurrentMouseY() {
        return MouseInfo.getPointerInfo().getLocation().getY();
    }

}