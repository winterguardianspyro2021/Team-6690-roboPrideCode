// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
//import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import frc.lib.util.SwerveModuleConstants;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // public static final double ROBOT_MASS = (148 - 20.3) * 0.453592; // 32lbs * kg per pound
    // public static final double MANIPULATOR_MASS = 10 * 0.453592; // 10lbs * kg per pound
    // public static final double CHASSIS_MASS = ROBOT_MASS - MANIPULATOR_MASS;
    // public static final Translation3d CHASSIS_CG = new Translation3d(0,0,Units.inchesToMeters(8));
    public static final double GRAVITY = 9.81; // m/s/s
    public static final double LOOP_TIME = 0.13; //s, 20ms + 110ms sprk max velocity lag

    public static final class Drivebase {
        // Hold time on motor brakes when disabled
        public static final double WHEEL_LOCK_TIME = 10; // seconds

        // Robot heading control gains
        public static final double HEADING_KP = 0.4;
        public static final double HEADING_KI = 0;
        public static final double HEADING_KD = 0.01;

        // Motor and encoder inversions
        public static final boolean CANCODER_INVERT = false;
        public static final boolean DRIVE_MOTOR_INVERT = true;
        public static final boolean ANGLE_MOTOR_INVERT = false;
        public static final boolean INVERT_GYRO = false;

        // Module locations, in meters, as distances to the center of the robot.
        // Positive x is torwards the robot front, and +y is torwards robot left.
        public static final double FRONT_LEFT_X = Units.inchesToMeters(15.5);
        public static final double FRONT_LEFT_Y = Units.inchesToMeters(15.5);
        public static final double FRONT_RIGHT_X = Units.inchesToMeters(15.5);
        public static final double FRONT_RIGHT_Y = Units.inchesToMeters(-15.5);
        public static final double BACK_LEFT_X = Units.inchesToMeters(-15.5);
        public static final double BACK_LEFT_Y = Units.inchesToMeters(15.5);
        public static final double BACK_RIGHT_X = Units.inchesToMeters(-15.5);
        public static final double BACK_RIGHT_Y = Units.inchesToMeters(-15.5);

        public static final Translation2d[] MODULE_LOCATIONS = {
            new Translation2d(Drivebase.FRONT_RIGHT_X, Drivebase.FRONT_RIGHT_Y),
            new Translation2d(Drivebase.FRONT_LEFT_X, Drivebase.FRONT_LEFT_Y),
            new Translation2d(Drivebase.BACK_RIGHT_X, Drivebase.BACK_RIGHT_Y),
            new Translation2d(Drivebase.BACK_LEFT_X, Drivebase.BACK_LEFT_Y)
        };

        // Drivetrain limitations
        public static final double MAX_SPEED = Units.feetToMeters(8.0); // meters per second
        public static final double MAX_ANGULAR_VELOCITY = -MAX_SPEED / Math.hypot(FRONT_LEFT_X, FRONT_LEFT_Y); // rad/s
        public static final double TELEOP_MAX_SPEED = Units.feetToMeters(8.5); // meters per second

        // Theoretical max acceleration should be as follows:
        // (NEO stall torque * module gearing * number of modules) / (wheel radius * robot mass) = m/s/s
        // (2.6 * 6.75 * 4) / (Units.inchesToMeters(2) * ROBOT_MASS)
        // However, the drive is traction-limited, so the max accelration is actually (wheel coefficient of friction * gravity)
        // public static final double MAX_ACCELERATION = 1.19 * 9.81; // COF (blue nitrile on carpet) as reported by Studica

        // Swerve base kinematics object
        public static final SwerveDriveKinematics KINEMATICS = new SwerveDriveKinematics(MODULE_LOCATIONS);

        // Module PIDF gains
        public static final double MODULE_KP = 0.01;
        public static final double MODULE_KI = 0;
        public static final double MODULE_KD = 0;
        public static final double MODULE_IZ = 0;
        public static final double MODULE_KF = 0;

        public static final double VELOCITY_KP = 0.0020645; // kp from SysId, eventually
        public static final double VELOCITY_KI = 0; // Leave all of these zero to disable them
        public static final double VELOCITY_KD = 0;
        public static final double VELOCITY_IZ = 0;
        public static final double VELOCITY_KF = 0; 

        //Smart Current Limit For Motor Controllers
        public static final int Drive_Motor_Limit = 60;
        public static final int Angle_Motor_Limit = 20; 

        // Drive feedforward gains
        public static final double KS = 0;
        // public static final double KV = 12 / MAX_SPEED; // Volt-seconds per meter (max voltage divided by max speed)
        // public static final double KA = 12 / MAX_ACCELERATION; // Volt-seconds^2 per meter (max voltage divided by max accel)
        public static final double KV = 12; // SHOULD BE LIMITED
        public static final double KA = 12; // SHOULD BE LIMITED

        // Encoder conversion values.  Drive converts motor rotations to linear wheel distance
        // and steering converts motor rotations to module azimuth
        public static final double METERS_PER_MOTOR_ROTATION = (Math.PI * Units.inchesToMeters(4)) / 6.75; // TODO: Find wheel diameter, gear ratio
            // Calculation: 4in diameter wheels * pi [circumfrence] / gear ratio
        public static final double DEGREES_PER_STEERING_ROTATION = 360 / 21.4; // TODO: Find gear ratio
            // degrees per rotation / gear ratio between module and motor
        
        public static final int NUM_MODULES = 4;
        
        // Module specific constants
        public static final class Mod0 { // Front left
            public static final int DRIVE_MOTOR_ID = 8;
            public static final int ANGLE_MOTOR_ID = 7;
            public static final int CANCODER_ID = 12;
            public static final double ANGLE_OFFSET = 223.59;
            public static final SwerveModuleConstants CONSTANTS =
                new SwerveModuleConstants(DRIVE_MOTOR_ID, ANGLE_MOTOR_ID, CANCODER_ID, ANGLE_OFFSET);
        }
        public static final class Mod1 { // Front right
            public static final int DRIVE_MOTOR_ID = 2;
            public static final int ANGLE_MOTOR_ID = 1;
            public static final int CANCODER_ID = 9;
            public static final double ANGLE_OFFSET = 183.86;
            public static final SwerveModuleConstants CONSTANTS =
                new SwerveModuleConstants(DRIVE_MOTOR_ID, ANGLE_MOTOR_ID, CANCODER_ID, ANGLE_OFFSET);
        }
        public static final class Mod2 { // Back left
            public static final int DRIVE_MOTOR_ID = 6;
            public static final int ANGLE_MOTOR_ID = 5;
            public static final int CANCODER_ID = 11;
            public static final double ANGLE_OFFSET = 40.69;
            public static final SwerveModuleConstants CONSTANTS =
                new SwerveModuleConstants(DRIVE_MOTOR_ID, ANGLE_MOTOR_ID, CANCODER_ID, ANGLE_OFFSET);
        }
        public static final class Mod3 { // Back right
            public static final int DRIVE_MOTOR_ID = 4;
            public static final int ANGLE_MOTOR_ID = 3;
            public static final int CANCODER_ID = 10;
            public static final double ANGLE_OFFSET = 207.24;
            public static final SwerveModuleConstants CONSTANTS =
                new SwerveModuleConstants(DRIVE_MOTOR_ID, ANGLE_MOTOR_ID, CANCODER_ID, ANGLE_OFFSET);
        } 
        public static final class AutoConstants{
            public static final double kMaxSpeedMetersPerSecond = 0.65; 
            public static final double kMaxAccelerationMetersPerSecondSquared = 0.65;   

            public static final double autoBalanceSpeed = 0.9; 
            public static final double autoBalanceAcceleration = 0.9; 

            //To Lock Wheels 
            public static final double lockSpeed = 0.1;

            public static final double kPXController = 1; 
            public static final double kPYController = 1;
            public static final double kPThetaController = 0.725;
            public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
            public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;
            public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
            new TrapezoidProfile.Constraints(
                kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
            
        }
    } 

    public static final class Arm{ 
        //public static final int shoulderID = 10; 
        //public static final int wristID = 11;  
        public static final int Shoulder_Motor_Limit = 40;  
        public static final int Wrist_Motor_Limit = 20;
        public static final double shoulderSpeed = 4.0; 
        public static final double wristSpeed = .9;    
        public static final double auto_wristSpeed = .5; 
        public static final double raiseKP = 0.01; 
        public static final double raiseKI = 0;  
        public static final double raiseKD = 0; 
        public static final double pGain = 1.0;
        public static final double sVolts = 1.0;
        public static final double gVolts = 1.0; 
        public static final double voltageComp = 4.0;
        public static final double vVoltSecondPerRad = 0.5;
        public static final double aVoltSecondSquaredPerRad = 0.1;
    
        public static final double maxVelocityRadPerSecond = 0.3;
        public static final double maxAccelerationRadPerSecSquared = 0.5; 

        public static final double clawSpeed = 0.5; 

        public static final double armGearRatio = (30.0 / 1.0); // 30:1 
        public static final double angleConversionFactor = 360.0 / armGearRatio;

        public static final boolean tuningMode = true;

        //Claw
        public static final int COMPRESSOR = 1;
        public static final int SENSOR_AIR_PRESSURE = 3; 
        public static final double CLAW_RUN = 0.5; //collector percent 
        public static final double CLAW_REVERSE = -0.5; 
        public static final double CLAW_OFF = 0.0; 
        public static final int forwardChannel = 1; 
        public static final int reverseChannel = 2;  
        public static final int clawMotorID = 16;
    } 

    //Constants For Joystick
    public static final class Joystick{ 
        public static final int JOYSTICK_LEFT_Y_AXIS = 1; 
        public static final int BUTTON_Number = 1; 
    }
}
