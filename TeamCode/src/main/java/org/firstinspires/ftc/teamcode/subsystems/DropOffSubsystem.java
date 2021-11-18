package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;

public class DropOffSubsystem extends SubsystemBase {
    private SimpleServo dropOffRight;
    private SimpleServo dropOffLeft;

    public DropOffSubsystem(SimpleServo dropOffLeft, SimpleServo dropOffRight){
        this.dropOffLeft = dropOffLeft;
        this.dropOffRight = dropOffRight;
    }

    public void drop(){
        dropOffLeft.setPosition(90);
        dropOffRight.setPosition(-90);
    }

    public void returnHome(){
        dropOffRight.setPosition(0);
        dropOffLeft.setPosition(0);
    }
}
