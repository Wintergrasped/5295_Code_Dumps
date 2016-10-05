package org.usfirst.frc.team5295.robot;

public class Cam {
	
	public String nme;
	public int ids;
	
	public Cam(String name, int id) {
		
		nme = name;
		ids= id;
		
	}
	
	public int getId() {
		return ids;
	}
	
	public String getName(){
		return nme;
	}
	public boolean setName(String nnmm){
		nme = nnmm;
		return true;
	}
	public boolean setId(int nid){
		ids = nid;
		return true;
	}

}
