/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FRC Team 4639. All Rights Reserved.                     */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.kauailabs.navx.frc.AHRS;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DriveTrainSys extends InjectedSubsystem {
	private static final int frontLeftPort = 0;
	private static final int frontRightPort = 1;

	private final DifferentialDrive drive;

	private final Solenoid front, rear;
	private final AHRS navx;

	public DriveTrainSys() {
		WPI_TalonSRX frontLeft = new WPI_TalonSRX(frontLeftPort);
		WPI_TalonSRX frontRight = new WPI_TalonSRX(frontRightPort);
		navx = new AHRS(Port.kMXP);

		front = new Solenoid(0, 0);
		rear = new Solenoid(0, 1);

		frontLeft.configFactoryDefault();
		frontRight.configFactoryDefault();

		drive = new DifferentialDrive(frontLeft, frontRight);
	}

	public AHRS getAhrs() {
		return navx;
	}

	public void drive(double speed, double rotation) {
		drive.arcadeDrive(speed, rotation);
	}

	public void setFrontSolenoid(boolean on) {
		front.set(on);
	}

	public void setRearSolenoid(boolean on) {
		rear.set(on);
	}
}
