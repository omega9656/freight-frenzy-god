package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Slides {
    public DcMotorEx slidesRight;
    public DcMotorEx slidesLeft;

    Position currentPos;

    public static final double maxPower = 0.6;

    // 806 1st
    public enum Position {
        DROP_OFF_HIGH(1600, -1600),
        DROP_OFF_MIDDLE(800, -800),
        DROP_OFF_BOTTOM(400, -400),
        PICKUP(0, 0); //picking up game elements

        public int leftArmPosition;
        public int rightArmPosition;

        Position(int leftArmPosition, int rightArmPosition){
            this.leftArmPosition = leftArmPosition;
            this.rightArmPosition = rightArmPosition;
        }
    }

    public Slides(DeviceManager deviceManager){
        slidesRight = deviceManager.slidesRight;
        slidesLeft = deviceManager.slidesLeft;

        slidesRight.setPower(maxPower);
        slidesLeft.setPower(maxPower);

        slidesRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slidesRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slidesRight.setDirection(DcMotorSimple.Direction.REVERSE);

        slidesLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slidesLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slidesLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        // sets default position
        slidesRight.setTargetPosition(0);
        slidesLeft.setTargetPosition(0);
    }


    public void run(Position position){
        slidesRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slidesRight.setTargetPosition(position.rightArmPosition);

        slidesLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slidesLeft.setTargetPosition(position.leftArmPosition);

        // updates the arm position to where it is currently
        currentPos = position;
    }

    //sets arm to 'drop off high' position
    public void dropOffHigh(){
        run(Position.DROP_OFF_HIGH);
    }
    //sets arm to 'drop off middle' position
    public void dropOffMiddle(){
        run(Position.DROP_OFF_MIDDLE);
    }

    // sets arm to level 1 position
    public void dropOffLow() {
        run(Position.DROP_OFF_BOTTOM);
    }
    //sets arm to 'pick up' position
    public void pickUp(){
        run(Position.PICKUP);
    }

}