/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CameraSubsystem extends SubsystemBase {
  /**
   * Creates a new CameraSubsystem.
   */
  public CameraSubsystem() {
    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture("driverCam", "/dev/video0");
    camera.setResolution(480, 360);
    camera.setFPS(8);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}