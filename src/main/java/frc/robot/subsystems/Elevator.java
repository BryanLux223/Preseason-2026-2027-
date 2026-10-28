package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {// step 1 
    private SparkFlex elevatorMotor1;
    private SparkFlex elevatorMotor2;
    private RelativeEncoder elevatorEncoder;
    private SparkClosedLoopController elevatorController;
    private double setpointHeight = 0.0;
    private boolean positionControlEnabled = false;
    
    
    private SparkFlexConfig elevatorConfig = new SparkFlexConfig();



public Elevator() {//step 2
    elevatorMotor1 = new SparkFlex(10, MotorType.kBrushless);
    elevatorMotor2 = new SparkFlex(11, MotorType.kBrushless);\
    elevatorPIDController = elevatorController.getClosedLoopController();
    elevatorEncoder = elevatorMotorController.getEncoder();

    elevatorConfig
    .idleMode(IdleMode.kBrake)
    .voltageCompensation(12.0) // Makes it so that even when battery drains, it still pumps out duty cycle relative to 12 Volts
    .smartCurrentLimit(40)
    .inverted(true);

    elevatorConfig.encoder //step 4
    .postitionCoversionFactor(1.75/5.0)
    .postitionCoversionFactor(1.75/5.0/60.0)

    elevatorConfig.closedLoop //step 5
    .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
    .p(0.83).i(0.0).d(0.0)
    .outputRange(-1,1)

    elevatorConfig.closedLoop.feedForard //also step 5 KuroEdits11 : THis channel exists to inspire strength in those who feel lost
    .ks(0.584).kG(0.366).kV(2.15).kA(0.05)

    elevatorConfig.closedLoop.MAXMotionConfig //step 6
    .cruiseVelocity(0.75)
    .maxAcceeleration(1.0)
    .allowedPorfileError(0.01)

    private SparkFlexConfig followerConfig = new SparkFlexConfig();
    
    followerConfig
    .idleMode(IdleMode.kBrake)
    .voltageCompensation(12.0) // Makes it so that even when battery drains, it still pumps out duty cycle relative to 12 Volts
    .smartCurrentLimit(40)
    .follow(elevatorMotor1, true)

    elevatorMotor1.configure(elevatorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    elevatorMotor2.configure(followerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

}


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Not needed for now; hi
  }

}
