// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  //Chassis//
public WPI_TalonSRX mi1 = new WPI_TalonSRX(4);
public WPI_TalonSRX mi2 = new WPI_TalonSRX(7);
public WPI_TalonSRX mi3 = new WPI_TalonSRX(3);
  SpeedControllerGroup mI= new SpeedControllerGroup(mi1, mi2,mi3);
public WPI_TalonSRX md1 = new WPI_TalonSRX(5);
public WPI_TalonSRX md2 = new WPI_TalonSRX(6);
public WPI_TalonSRX md3 = new WPI_TalonSRX(7);
  SpeedControllerGroup mD = new SpeedControllerGroup(md1,md2,md3);

//Compressor//
public Compressor c = new Compressor();

//Solenoides//
public DoubleSolenoid A = new DoubleSolenoid(0, 1);
public DoubleSolenoid B = new DoubleSolenoid(0, 2);
public DoubleSolenoid C = new DoubleSolenoid(0, 3);

//JoyStick//
public XboxController cm1 = new XboxController(0);
public XboxController cm2 = new XboxController(1);

DifferentialDrive L1 = new DifferentialDrive(mI, mD);
boolean HatchState = false;
double Dir = 0;



  @Override
  public void robotInit() {
    c.setClosedLoopControl(true);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    
    Dir=(cm1.getRawAxis(3)-cm1.getRawAxis(2));
    L1.arcadeDrive(Dir,cm1.getRawAxis(1));
    
    if(cm2.getRawButton(1)){
     if(HatchState = true) { //mecanismo para meter y sacar el primer piston 
      A.set(DoubleSolenoid.Value.kForward);
      }
      else{
      A.set(DoubleSolenoid.Value.kOff);
    }
    HatchState = !HatchState; 
    }
    
       
    if(cm2.getRawButton(3)){ //meter piston//
          A.set(DoubleSolenoid.Value.kReverse);
        }else{
         A.set(DoubleSolenoid.Value.kOff); 
        }
         
    if(cm2.getRawButton(2)){ //sacar piston// 
      B.set(DoubleSolenoid.Value.kForward);
    }
    else{
      B.set(DoubleSolenoid.Value.kOff);
    } 
    if (cm1.getRawButton(1)){ //mecanismo velocidad + //
      C.set(DoubleSolenoid.Value.kForward);
    }
    else{
      C.set(DoubleSolenoid.Value.kOff);

    }
    if(cm1.getRawButton(4)){ //mecanismo velocidad - // 
      C.set(DoubleSolenoid.Value.kForward);
    }
    else{
      C.set(DoubleSolenoid.Value.kOff);
    }
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
