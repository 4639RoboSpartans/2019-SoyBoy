/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FRC Team 4639. All Rights Reserved.                     */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class InjectedSubsystem extends Subsystem {
	protected InjectedSubsystem() {
		super();
	}

	protected InjectedSubsystem(String name) {
		super(name);
	}

	@Override
	public final void setDefaultCommand(Command command) {
		super.setDefaultCommand(command);
	}

	@Override
	protected final void initDefaultCommand() {
	}
}
