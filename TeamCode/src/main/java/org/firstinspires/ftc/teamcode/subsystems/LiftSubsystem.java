package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.controller.wpilibcontroller.ElevatorFeedforward;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ProfiledPIDController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.trajectory.TrapezoidProfile;

public class LiftSubsystem extends ProfiledPIDSubsystem {
    private Motor liftMotor;
    private static double kS = 0, kG = 0, kV = 0, kA = 0;
    private final ElevatorFeedforward elevatorFeedForward = new ElevatorFeedforward(kS, kG, kV, kA);
    private static double kP = 1.0, kI = 0, kD = 0;
    private final double distancePerPulse = 2;

    public LiftSubsystem(Motor liftMotor) {
        super(new ProfiledPIDController(kP, kI, kD, new TrapezoidProfile.Constraints(3.0, 3.0)), 0.0);
        this.liftMotor = liftMotor;
        liftMotor.encoder.setDistancePerPulse(distancePerPulse);
        setGoal(0.0);
    }

    @Override
    public void useOutput(double output, TrapezoidProfile.State setPoint) {
        double feedForward = elevatorFeedForward.calculate(setPoint.velocity);
        liftMotor.set(((output + feedForward) / distancePerPulse) / liftMotor.ACHIEVABLE_MAX_TICKS_PER_SECOND);
    }

    @Override
    protected double getMeasurement() {
        return liftMotor.encoder.getDistance();
    }
}