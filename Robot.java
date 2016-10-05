
package org.usfirst.frc.team5295.robot;

import java.util.ArrayList;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
    private Cam camCenter = new Cam("camCenter", NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController));
	private Cam camRight = new Cam("camRight", NIVision.IMAQdxOpenCamera("cam1", NIVision.IMAQdxCameraControlMode.CameraControlModeController));
	ArrayList<Cam> camList = new ArrayList<Cam>();
    final String defaultAuto = "Default";
    final String rightAuto = "Straight Then Right";
    String autoSelected;
    SendableChooser chooser;
    CameraServer server = CameraServer.getInstance();
    

    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Straight then left", defaultAuto);
        chooser.addObject("Straight then right", rightAuto);
        SmartDashboard.putData("Auto choices", chooser);
        
    }
    

    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
		System.out.println("Auto selected: " + autoSelected);
		changeCam(camCenter);
    }


    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case rightAuto:
    		
    		
    		
            break;
    	case defaultAuto:
    		
    		
    	default:
    	
            break;
    	}
    }


    public void teleopPeriodic() {
        
    }
    

    public void testPeriodic() {
    
    }
    
	private int curCam;
	private Image frame;
	
	public void CameraFeeds()
	{
        curCam = camCenter.getId();
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        server = CameraServer.getInstance();
        server.setQuality(50);
        
}
	
	public void changeCam(Cam nc)
    {
		int newId = nc.getId();
		NIVision.IMAQdxStopAcquisition(curCam);
    	NIVision.IMAQdxConfigureGrab(newId);
    	NIVision.IMAQdxStartAcquisition(newId);
    	curCam = newId;
    }
    
    public void updateCam()
    {
    	NIVision.IMAQdxGrab(curCam, frame, 1);
        server.setImage(frame);
}
    public void end()
	{
		NIVision.IMAQdxStopAcquisition(curCam);
}
    public void addCam(String camName, String logicalName) {
    	
    	int cm = NIVision.IMAQdxOpenCamera(logicalName, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
    	camList.add(new Cam(camName, cm));
    }

}
