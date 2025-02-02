package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import javax.sql.StatementEvent;

public class DeviceManager {
    public DcMotorEx backRight;
    public DcMotorEx frontRight;
    public DcMotorEx backLeft;
    public DcMotorEx frontLeft;

    public DcMotorEx slidesLeft;
    public DcMotorEx slidesRight;

    public DcMotorEx intake;

    public DcMotorEx duckMech;

    public Servo horLeft;
    public Servo horRight;

    public Servo rightTrayTilt;
    public Servo leftTrayTilt;

    public HardwareMap hardwareMap;

    /***
     *
     * @param hardwareMap - readies hardwareMap so it can initialize devices in init()
     */
    public DeviceManager(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
    }

    /**
     * initializes the robot, get hardware from the expansion hub
     * @param autoRunning - indicates whether auto is running so we don't initialize our drive motors
     */
    void init(boolean autoRunning){
        if (!autoRunning) {
            backRight = hardwareMap.get(DcMotorEx.class, "back_right");
            frontRight = hardwareMap.get(DcMotorEx.class, "front_right");
            backLeft = hardwareMap.get(DcMotorEx.class, "back_left");
            frontLeft = hardwareMap.get(DcMotorEx.class, "front_left");
        }

        slidesLeft = hardwareMap.get(DcMotorEx.class, "left_slides");
        slidesRight = hardwareMap.get(DcMotorEx.class, "right_slides");

        horLeft = hardwareMap.get(Servo.class, "left_extension");
        horRight = hardwareMap.get(Servo.class, "right_extension");

        leftTrayTilt = hardwareMap.get(Servo.class, "left_tray");
        rightTrayTilt = hardwareMap.get(Servo.class, "right_tray");

        intake = hardwareMap.get(DcMotorEx.class, "intake");

    }

}
