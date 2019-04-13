/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.kauailabs.navx.frc.AHRS;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class DriveTrainSys extends Subsystem {
	private static final int frontLeftPort = 2;
	private static final int frontRightPort = 14;
	private static final int rearLeftPort = 0;
	private static final int rearRightPort = 9;

	private final WPI_TalonSRX frontLeft;
	private final WPI_VictorSPX frontRight;
	private final WPI_TalonSRX rearLeft;
	private final WPI_TalonSRX rearRight;
	private final DifferentialDrive drive;
	private final AHRS navx;

	public DriveTrainSys() {
		frontLeft = new WPI_TalonSRX(frontLeftPort);
		frontRight = new WPI_VictorSPX(frontRightPort);
		rearLeft = new WPI_TalonSRX(rearLeftPort);
		rearRight = new WPI_TalonSRX(rearRightPort);
		navx = new AHRS(Port.kMXP);

		frontLeft.configFactoryDefault();
		frontRight.configFactoryDefault();
		rearLeft.configFactoryDefault();
		rearRight.configFactoryDefault();

        rearRight.follow(frontRight);
        rearLeft.follow(rearRight);

        drive = new DifferentialDrive(frontLeft, rearLeft);
	}

    @Override
    protected void initDefaultCommand() {

    }

    public AHRS getAhrs() {
		return navx;
	}

	public void drive(double xSpeed, double ySpeed) {
		drive.arcadeDrive(xSpeed, ySpeed);
	}

	public void stop() {
		drive.stopMotor();
	}

}
