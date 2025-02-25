// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.reduxrobotics.sensors.canandmag.Canandmag;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  // Left
  private final WPI_TalonSRX m_left1 = new WPI_TalonSRX(DriveConstants.kLeftMotor1Port);
  private final WPI_TalonSRX m_left2 = new WPI_TalonSRX(DriveConstants.kLeftMotor2Port);

  // Right
  private final WPI_TalonSRX m_right1 = new WPI_TalonSRX(DriveConstants.kRightMotor1Port);
  private final WPI_TalonSRX m_right2 = new WPI_TalonSRX(DriveConstants.kRightMotor2Port);

  // Define drive by leaders of motor groups
  private final DifferentialDrive m_drive =
      new DifferentialDrive(m_left1, m_right1);

  // private final Canandmag m_leftEncoder = new Canandmag(DriveConstants.kLeftEncoderCanId);
  // private final Canandmag m_rightEncoder = new Canandmag(DriveConstants.kRightEncoderCanId);

  private boolean forwardPhase = true;

  public DriveSubsystem() {
    SendableRegistry.addChild(m_drive, m_left1);
    SendableRegistry.addChild(m_drive, m_right1);

    m_left2.follow(m_left1);
    m_right2.follow(m_right1);
    // m_left1.addFollower(m_left2);
    // m_right1.addFollower(m_right2);

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_right1.setInverted(false);

    // Sets the distance per pulse for the encoders
    // m_leftEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
    // m_rightEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(double fwd, double rot) {
    fwd = (this.forwardPhase ? fwd : -fwd);  // Reverse front direction
    m_drive.arcadeDrive(fwd, rot);
  }

  /** Resets the drive encoders to currently read a position of 0. */
  public void resetEncoders() {
    // m_leftEncoder.zeroAll();
    // m_rightEncoder.zeroAll();
  }

  /**
   * Gets the average distance of the TWO encoders.
   *
   * @return the average of the TWO encoder readings
   */
  public double getAverageEncoderDistance() {
    // return (m_leftEncoder.getPosition() + m_rightEncoder.getPosition()) / 2.0;
    return 0;
  }

  /**
   * Sets the max output of the drive. Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

  public Command toggleForwardDirection() {
    return runOnce(
      () -> {
        forwardPhase = !forwardPhase;
      }
    );    
  }

  /**
   * 
   * @return true if going forward, false if reversed
   */
  public boolean getForwardDirection() {
    return forwardPhase;
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    super.initSendable(builder);
    // Publish encoder distances to telemetry.
    // builder.addDoubleProperty("leftDistance", m_leftEncoder::getPosition, null);
    // builder.addDoubleProperty("rightDistance", m_rightEncoder::getPosition, null);
  }
}