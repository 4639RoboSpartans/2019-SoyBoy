/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.kauailabs.navx.frc.AHRS;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DriveTrainSys extends InjectedSubsystem {
	private static final int frontLeftPort = 0;
	private static final int frontRightPort = 1;

	private final WPI_TalonSRX frontLeft;
	private final WPI_TalonSRX frontRight;
	private final DifferentialDrive drive;
	private final AHRS navx;

	public DriveTrainSys() {
		frontLeft = new WPI_TalonSRX(frontLeftPort);
		frontRight = new WPI_TalonSRX(frontRightPort);
		navx = new AHRS(Port.kMXP);

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

	public void stop() {
		drive.stopMotor();
	}

}
