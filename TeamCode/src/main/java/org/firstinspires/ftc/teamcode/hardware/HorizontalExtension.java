package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.Servo;

public class HorizontalExtension {
    public Servo horLeft;
    public Servo horRight;

    enum HorizontalPosition {
        READY(0, 0),
        DROP_OFF(1, 1);

        public final double leftPos;
        public final double rightPos;

        HorizontalPosition(double leftPos, double rightPos){
            this.leftPos = leftPos;
            this.rightPos = rightPos;
        }
    }

    public HorizontalExtension(DeviceManager deviceManager){
        horLeft = deviceManager.horLeft;
        horRight = deviceManager.horRight;

        horLeft.setDirection(Servo.Direction.REVERSE);
        //horLeft.setPosition(0);
        //horRight.setPosition(0);
    }
}
