package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Precision Normal")
public class PrecisionNormal extends OmegaTeleop{
    @Override
    public DriveMode getCurrentMode() { return DriveMode.NORMAL; }

    @Override
    public boolean isSlow() { return true; }
}
