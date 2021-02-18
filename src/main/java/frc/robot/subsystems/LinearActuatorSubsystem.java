// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenixphoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.com.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class LinearActuatorSubsystem extends SubsystemBase {
  /** Creates a new LinearActuatorSubsystem. */
  private static final int TIMEOUT_MS = 10;
  private static final int ENC_COUNTS_PER_ROTATION = 4096;

  public static WPI_TalonSRX boomMotor = RobotMap.boomLinearActuatorMotor;
  public static WPI_TalonSRX vertMotor = RobotMap.vertLinearActuatorMotor;

  public LinearActuatorSubsystem() {
    vertMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, TIMEOUT_MS);
    boomMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, TIMEOUT_MS);

    vertMotor.set(ControlMode,PercentOutput, 0);
    boomMotor.set(ControlMode,PercentOutput, 0);

    vertMotor.setSensorPhase(true);
    boomMotor.setSensorPhase(false);
  }

  public static double getBoomEncoder() {
    return(boomMotor.getSensorCollection().getQuadraturePosition());
  }

  public static double getVertEncoder() {
    return(vertMotor.getSensorCollection().getQuadraturePosition());
  }

  public void setVertPower(double power) {
    vertMotor.set(power);
  }

  public void setBoomPower(double power) {
    boomMotor.set(power);
  }

  public static double getBoomEncoderRotations() {
    double rotations = getBoomEncoder()/ENC_COUNTS_PER_ROTATION;
  }

  public static double getVertEncoderRotations() {
    double rotations = getVertEncoder()/ENC_COUNTS_PER_ROTATION;
  }

  public void stop() {
    vertMotor.set(0,0);
    boomMotor.set(0,0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
