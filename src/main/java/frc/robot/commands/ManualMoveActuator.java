// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.LinearActuatorSubsystem;

public class ManualMoveActuator extends CommandBase {
  /** Creates a new ManualMoveActuator. */
  private LinearActuatorSubsystem actuator;
  private static XboxController controller = RobotContainer.controller;
  double rightStickYValue;
  double leftStickYValue;
  double vertPower;
  double boomPower;

  public ManualMoveActuator(LinearActuatorSubsystem LinearActuator) {
    actuator = linearActuator;
    addRequirements(actuator);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    rightStickYValue = controller.getY(Hand.kRight);
    leftStickYValue = controller.getY(Hand.kLeft);

    vertPower = rightStickYValue;
    boomPower = leftStickYValue;

    if (Math.abs(vertPower) < .2) {
      vertPower = 0;
    } if (Math.abs(boomPower) < .2) {
      boomPower = 0;
    }

    actuator.setVertPower(vertPower);
    actuator.setBoomPower(boomPower);

    if (MAX_ENC_VERT <= actuator.setVertEncoder()) {
      LinearActuatorSubsystem.setvertPower(0,0);
    } else {
      actuator.setVertPower(vertPower);
    }

    if (MAX_ENC_VERT <= actuator.setBoomEncoder()) {
      LinearActuatorSubsystem.setboomPower(0,0);
    } else {
      actuator.setBoomPower(boomPower);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
