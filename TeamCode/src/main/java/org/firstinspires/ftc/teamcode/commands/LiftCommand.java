package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class LiftCommand extends CommandBase {
    private LiftSubsystem subsystem;
    private int level;
    public LiftCommand(LiftSubsystem subsystem){
        this.subsystem = subsystem;
        level = 0;
        addRequirements(subsystem);
    }
    @Override
    public void initialize(){
        if(level == 0){
            subsystem.setGoal(0.5);
            level++;
        }else if(level == 1){
            subsystem.setGoal(1);
            level++;
        }else if(level == 2){
            subsystem.setGoal(0);
            level = 0;
        }
    }
}