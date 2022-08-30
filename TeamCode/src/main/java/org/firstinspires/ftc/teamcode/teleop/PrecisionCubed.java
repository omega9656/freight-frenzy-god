package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Precision Cubed")
public class PrecisionCubed extends OmegaTeleop{
    @Override
    public DriveMode getCurrentMode() {
        return DriveMode.NORMAL;
    }

    @Override
    public boolean isSlow() { return true; }
}
