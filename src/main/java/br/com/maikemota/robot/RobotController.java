package br.com.maikemota.robot;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Rectangle;

import br.com.maikemota.robot.keyboard.SpecialKeys;
import br.com.maikemota.robot.keyboard.KeyboardController;

public class RobotController {

    private static Robot context;

    private RobotController() throws AWTException {
    }

    public static RobotController initialize() throws AWTException {
        RobotController controller = new RobotController();
        RobotController.context = new Robot();
        KeyboardController.setContext(RobotController.context);
        // MouseUtil.setContext(RobotController.context);
        return controller;
    }

    public static RobotController initialize(final int startUpDelay) throws AWTException {
        RobotController controller = RobotController.initialize();
        controller.waitFor(startUpDelay);
        return controller;
    }

    public static RobotController initialize(final int delayBetweenClicks, final int delayBetweenKeys)
            throws AWTException {
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
            RobotController.context.mouseMove((int) currentX, (int) currentY);
            this.waitFor(1);
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
        this.waitFor(50);
        this.releaseLeftMouseButton();
        return this;
    }

    public RobotController doubleClickLeftMouseButton() {
        this.clickLeftMouseButton();
        this.waitFor(50);
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
        this.waitFor(50);
        this.releaseRightMouseButton();
        return this;
    }

    public RobotController doubleClickRightMouseButton() {
        this.clickRightMouseButton();
        this.waitFor(50);
        this.clickRightMouseButton();
        return this;
    }

    public RobotController type(final String text) {
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char character = text.charAt(i);
            KeyboardController.type(character);
            this.waitFor(20);
        }
        return this;
    }

    public RobotController hitEnter() {
        this.holdEnter();
        this.releaseEnter();
        return this;
    }

    public RobotController holdKey(SpecialKeys key) {
        KeyboardController.hold(key);
        return this;
    }

    public RobotController releaseKey(SpecialKeys key) {
        KeyboardController.release(key);
        return this;
    }

    public RobotController holdEnter() {
        this.holdKey(SpecialKeys.ENTER);
        return this;
    }

    public RobotController releaseEnter() {
        this.releaseKey(SpecialKeys.ENTER);
        return this;
    }

    public RobotController holdControlKey() {
        this.holdKey(SpecialKeys.CONTROL);
        return this;
    }

    public RobotController releaseControlKey() {
        this.releaseKey(SpecialKeys.CONTROL);
        return this;
    }

    public RobotController holdWindowsKey() {
        this.holdKey(SpecialKeys.WINDOWS);
        return this;
    }

    public RobotController releaseWindowsKey() {
        this.releaseKey(SpecialKeys.WINDOWS);
        return this;
    }

    public RobotController holdArrowUpKey(){
        this.holdKey(SpecialKeys.ARROW_UP);
        return this;
    }

    public RobotController releaseArrowUpKey(){
        this.releaseKey(SpecialKeys.ARROW_UP);
        return this;
    }

    public RobotController hitArrowUpKey() {
        this.holdArrowUpKey();
        this.releaseArrowUpKey();
        return this;
    }

    /**
     * Wait for determined amount of time
     * @param delay time to wait in ms
     */
    public RobotController waitFor(final long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Wait for determined amount of time
     * @param delay time to wait
     * @param unit Unit of time for wait
     */
    public RobotController waitFor(final long delay, final ChronoUnit unit){
        final long unitDelay = unit.getDuration().toMillis();
        return waitFor(delay * unitDelay);
    }

    public RobotController takeScreenShot() throws IOException {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        RobotController.context.createScreenCapture(screenRect);
        BufferedImage screenFullImage = RobotController.context.createScreenCapture(screenRect);
        ImageIO.write(screenFullImage, "jpg", new File(String.valueOf(System.currentTimeMillis()) + ".jpg"));
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