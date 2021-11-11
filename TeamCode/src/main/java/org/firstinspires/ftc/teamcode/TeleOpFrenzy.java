package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommand;
import org.firstinspires.ftc.teamcode.commands.SpinnerCommand;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SpinnerSubsystem;

@TeleOp(name = "MainTeleop")
public class TeleOpFrenzy extends CommandOpMode {

    private Motor frontLeft, backLeft, frontRight, backRight;
    private MotorGroupTemp leftDrive, rightDrive;
    private Motor duckSpinner;
    private Motor liftMotor;

    private GamepadEx driver;
    private RevIMU imu;

    private DriveSubsystem driveSubsystem;
    private DriveCommand driveCommand;

    private SpinnerSubsystem spinnerSubsystem;
    private SpinnerCommand spinnerCommand, spinnerCommandTwo;

    private LiftSubsystem liftSubsystem;
    private LiftCommand liftCommand;

    @Override
    public void initialize() {
        frontLeft = new Motor(hardwareMap, "fL");
        backLeft = new Motor(hardwareMap, "bL");
        frontRight = new Motor(hardwareMap, "fR");
        backRight = new Motor(hardwareMap, "bR");

        leftDrive = new MotorGroupTemp(frontLeft, backLeft);
        rightDrive = new MotorGroupTemp(frontRight, backRight);

        duckSpinner = new Motor(hardwareMap, "dS");

        driver = new GamepadEx(gamepad1);
        imu = new RevIMU(hardwareMap);
        imu.init();

        driveSubsystem = new DriveSubsystem(leftDrive, rightDrive, imu, telemetry);
        driveCommand = new DriveCommand(driveSubsystem, driver::getLeftY, driver::getRightX);

        spinnerSubsystem = new SpinnerSubsystem(duckSpinner);
        spinnerCommand = new SpinnerCommand(spinnerSubsystem, true);
        spinnerCommandTwo = new SpinnerCommand(spinnerSubsystem, false);

        liftSubsystem = new LiftSubsystem(liftMotor);
        liftCommand = new LiftCommand(liftSubsystem);

        // Shreya had never listened to Drake until like a year after she move to Toronto
        driver.getGamepadButton(GamepadKeys.Button.A).whenHeld(spinnerCommand);
        driver.getGamepadButton(GamepadKeys.Button.B).whenHeld(spinnerCommandTwo);
        driver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(liftCommand);

        register(driveSubsystem);
        driveSubsystem.setDefaultCommand(driveCommand);
    }
}
