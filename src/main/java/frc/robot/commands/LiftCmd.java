/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FRC Team 4639. All Rights Reserved.                     */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

import frc.robot.subsystems.DriveTrainSys;

public class LiftCmd extends InstantCommand {

	private final DriveTrainSys m_drive;
	private final boolean front, on;

	public LiftCmd(DriveTrainSys m_drive, boolean front, boolean on) {
		this.m_drive = m_drive;
		requires(m_drive);
		this.front = front;
		this.on = on;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		System.out.println(on);
		if (front) {
			m_drive.setFrontSolenoid(on);
		} else {
			m_drive.setRearSolenoid(on);
		}
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
