/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FRC Team 4639. All Rights Reserved.                     */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

import frc.robot.commands.DriveCmd;
import frc.robot.commands.LiftCmd;
import frc.robot.commands.PIDAngleCmd;
import frc.robot.subsystems.DriveTrainSys;

public class Robot extends TimedRobot {
	private OI m_oi;
	private DriveTrainSys m_drive;

	private PIDAngleCmd curCmd;
	private Compressor compressor;

	@Override
	public void robotInit() {
		UsbCamera targetCam = new UsbCamera("camera", 0);
		targetCam.setBrightness(1);

		CameraServer.getInstance().startAutomaticCapture(targetCam);

		m_drive = new DriveTrainSys();
		m_oi = new OI();
		compressor = new Compressor(0);
		compressor.setClosedLoopControl(true);
		m_drive.setDefaultCommand(new DriveCmd(m_drive, m_oi));

		m_oi.getButton(0, 1).whenPressed(new LiftCmd(m_drive, false, true));
		m_oi.getButton(0, 2).whenPressed(new LiftCmd(m_drive, false, false));

		m_oi.getButton(0, 3).whenPressed(new LiftCmd(m_drive, true, true));
		m_oi.getButton(0, 4).whenPressed(new LiftCmd(m_drive, true, false));
	}

	@Override
	public void robotPeriodic() {
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		this.curCmd = new PIDAngleCmd(m_drive, 90);
		this.curCmd.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (this.curCmd != null) {
			this.curCmd.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
	}
}
