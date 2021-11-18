package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.CommandBase;

public class DropOffCommand extends CommandBase {
    public DropOffSubsystem subsystem;

    public DropOffCommand(DropOffSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
        subsystem.drop();
    }
    public void end(boolean interrupted){
        subsystem.returnHome();
    }
}