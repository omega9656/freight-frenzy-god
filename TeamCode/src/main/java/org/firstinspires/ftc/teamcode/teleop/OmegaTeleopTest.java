package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.hardware.Robot;

@TeleOp(name = "Testing")
public class OmegaTeleopTest extends OpMode {
    Robot robot;

    @Override
    public void init() {
        robot = new Robot(hardwareMap);
        robot.init(false);
    }

    @Override
    public void loop() {
        //slides();
        slidesSetPosition();
        updateTel();
        //drive(2, OmegaTeleop.DriveMode.CUBED);
    }

    public void drive(double strafe, OmegaTeleop.DriveMode driveMode) {
        // https://gm0.copperforge.cc/en/stable/docs/software/mecanum-drive.html
        // https://www.chiefdelphi.com/t/paper-mecanum-and-omni-kinematic-and-force-analysis/106153/5 (3rd paper)

        // moving left joystick up means robot moves forward
        double vertical = -gamepad1.left_stick_y;  // flip sign because y axis is reversed on joystick

        // moving left joystick to the right means robot moves right
        double horizontal = gamepad1.left_stick_x * strafe;  // counteract imperfect strafing by multiplying by constant

        // moving right joystick to the right means clockwise rotation of robot
        double rotate = gamepad1.right_stick_x;

        // calculate initial power from gamepad inputs
        // to understand this, draw force vector diagrams (break into components)
        // and observe the goBILDA diagram on the GM0 page (linked above)
        double frontLeftPower = vertical + horizontal + rotate;
        double backLeftPower = vertical - horizontal + rotate;
        double frontRightPower = vertical - horizontal - rotate;
        double backRightPower = vertical + horizontal - rotate;

        // if there is a power level that is out of range
        if (
                Math.abs(frontLeftPower) > 1 ||
                        Math.abs(backLeftPower) > 1 ||
                        Math.abs(frontRightPower) > 1 ||
                        Math.abs(backRightPower) > 1
        ) {
            // scale the power within [-1, 1] to keep the power levels proportional
            // (if the power is over 1 the FTC SDK will just make it 1)

            // find the largest power
            double max = Math.max(Math.abs(frontLeftPower), Math.abs(backLeftPower));
            max = Math.max(Math.abs(frontRightPower), max);
            max = Math.max(Math.abs(backRightPower), max);

            // scale everything with the ratio max:1
            // don't need to worry about signs because max is positive
            frontLeftPower /= max;
            backLeftPower /= max;
            frontRightPower /= max;
            backRightPower /= max;
        }

        // square or cube gamepad inputs
        if (driveMode == OmegaTeleop.DriveMode.SQUARED) {
            // need to keep the sign, so multiply by absolute value of itself
            frontLeftPower *= Math.abs(frontLeftPower);
            backLeftPower *= Math.abs(backLeftPower);
            frontRightPower *= Math.abs(frontRightPower);
            backRightPower *= Math.abs(backRightPower);
        } else if (driveMode == OmegaTeleop.DriveMode.CUBED) {
            frontLeftPower = Math.pow(frontLeftPower, 3);
            backLeftPower = Math.pow(backLeftPower, 3);
            frontRightPower = Math.pow(frontRightPower, 3);
            backRightPower = Math.pow(backRightPower, 3);
        } // if drive mode is normal, don't do anything

        // set final power values to motors
        robot.drivetrain.frontLeft.setPower(frontLeftPower);
        robot.drivetrain.backLeft.setPower(backLeftPower);
        robot.drivetrain.frontRight.setPower(frontRightPower);
        robot.drivetrain.backRight.setPower(backRightPower);
    }


    public void slides(){
        if(gamepad1.left_bumper){
            robot.slides.slidesLeft.setVelocity(-360, AngleUnit.DEGREES);
            robot.slides.slidesRight.setVelocity(360, AngleUnit.DEGREES);
        }
        else {
            robot.slides.slidesLeft.setVelocity(0, AngleUnit.DEGREES);
            robot.slides.slidesRight.setVelocity(0, AngleUnit.DEGREES);
        }

        if(gamepad1.left_trigger > 0.25){
            robot.slides.slidesLeft.setVelocity(360, AngleUnit.DEGREES);
            robot.slides.slidesRight.setVelocity(-360, AngleUnit.DEGREES);
        }
        else {
            robot.slides.slidesLeft.setVelocity(0, AngleUnit.DEGREES);
            robot.slides.slidesRight.setVelocity(0, AngleUnit.DEGREES);
        }

    }

    public void slidesSetPosition(){
        robot.slides.slidesLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.slides.slidesRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        if(gamepad2.right_bumper){
            robot.slides.slidesRight.setPower(0.6);
            robot.slides.slidesLeft.setPower(0.6);

            robot.slides.slidesRight.setTargetPosition(1200);

            robot.slides.slidesLeft.setTargetPosition(-1200);

        }
        else if(gamepad2.left_bumper){
            robot.slides.slidesRight.setPower(0.6);
            robot.slides.slidesLeft.setPower(0.6);

            robot.slides.slidesRight.setTargetPosition(30);

            robot.slides.slidesLeft.setTargetPosition(-30);
        }
        /*else {
            robot.slides.slidesRight.setPower(0.2);
            robot.slides.slidesLeft.setPower(0.2);

            robot.slides.slidesRight.setTargetPosition(robot.slides.slidesRight.getCurrentPosition());
            robot.slides.slidesLeft.setTargetPosition(robot.slides.slidesLeft.getCurrentPosition());
        }*/
    }

    public void updateTel(){
        telemetry.addData("Left Sides Pos: ", robot.slides.slidesLeft.getCurrentPosition());
        telemetry.addData("Left Sides Power: ", robot.slides.slidesLeft.getPower());
        telemetry.addData("Right Sides Pos: ", robot.slides.slidesRight.getCurrentPosition());
        telemetry.addData("Right Sides Power: ", robot.slides.slidesRight.getPower());
        telemetry.addData("mode: ", robot.slides.slidesRight.getMode());
        telemetry.update();
    }
}
