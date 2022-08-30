package org.firstinspires.ftc.teamcode.teleop.Modular;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleop.Modular.OmegaTeleopModular;

@TeleOp(name = "Squared Modular")
public class SquaredModular extends OmegaTeleopModular {
    @Override
    public DriveMode getCurrentMode() {
        return DriveMode.SQUARED;
    }
}
