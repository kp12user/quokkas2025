// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.util.function.FloatSupplier;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.io.PrintWriter;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class Climber extends SubsystemBase {
  TalonFX climbMotor;


  /** Creates a new DriveTrain. */
  public Climber() {
    climbMotor = new TalonFX(6);
    climbMotor.setNeutralMode(NeutralModeValue.Brake);
}


 
  public Command climbForward(DoubleSupplier climbPower) {
    // Inline construction of command goes here.
    return run(
        () -> {
          
          climbMotor.set(climbPower.getAsDouble());
        });
  }


  public Command climbBackward(DoubleSupplier climbPower) {
    // Inline construction of command goes here.
    return run(
        () -> {
          
          climbMotor.set(-climbPower.getAsDouble());
        });
  }
  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
