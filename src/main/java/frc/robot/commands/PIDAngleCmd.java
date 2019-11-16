/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FRC Team 4639. All Rights Reserved.                     */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

import frc.robot.subsystems.DriveTrainSys;

public class PIDAngleCmd extends Command {
	private static final double P = 1.0 / 100.0, I = 0, D = 0;

	private final DriveTrainSys m_drive;
	private final PIDController pid;

	public PIDAngleCmd(DriveTrainSys m_drive, double angle) {
		this.m_drive = m_drive;
		requires(m_drive);
		this.pid = new PIDController(P, I, D, m_drive.getAhrs(),
				output -> m_drive.drive(0, output + 0.3 * Math.signum(output)));
		this.pid.setInputRange(-180, 180);
		this.pid.setAbsoluteTolerance(2);
		this.pid.setContinuous(true);
		this.pid.setSetpoint(angle);
	}

	@Override
	protected void initialize() {
		this.pid.enable();
	}

	@Override
	protected void end() {
		this.pid.disable();
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return this.pid.onTarget() && Math.abs(this.m_drive.getAhrs().getRate()) < 0.5;
	}
}
