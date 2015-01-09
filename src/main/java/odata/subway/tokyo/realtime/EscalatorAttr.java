package odata.subway.tokyo.realtime;

public class EscalatorAttr {
	public double State_RollerFriction;     
	public double State_Temperature;               
	public int State_DeviceUpState;
	//other attrs...fuck...
	public String TS_EscalatorInsertionTimestamp;
	public String TS_EscalatorId;
	public String TS_EscalatorDeviceName;
	public String State_UpTime;
	public String TS_HTFrame;
	public String TS_HTFrameTime;
	public String State_MotorEngagedTime;
	public String TS_HTStationName;
	public String TS_HTLineNum;
	public String TS_HTLineOfStationNum;
	
	public EscalatorAttr(){}
	public EscalatorAttr(String s) {
		String[] attr = s.split("+");
		this.State_RollerFriction = Double.parseDouble(attr[0]);
		this.State_Temperature = Double.parseDouble(attr[1]);
		this.State_DeviceUpState = Integer.parseInt(attr[2]);
	}
	public void set(double r, double t, int d) {
		State_RollerFriction = r;
		State_Temperature = t;
		State_DeviceUpState = d;
	}
	public void setTS_EscalatorInsertionTimestamp(String s) {
		TS_EscalatorInsertionTimestamp = s;
	}
	public void setTS_EscalatorId(String s) {
		TS_EscalatorId = s;
	}
	public void setTS_EscalatorDeviceName(String s) {
		TS_EscalatorDeviceName = s;
	}
	public void setState_UpTime(String s) {
		State_UpTime = s;
	}
	public void setTS_HTFrame(String s) {
		TS_HTFrame = s;
	}
	public void setTS_HTFrameTime(String s) {
		TS_HTFrameTime = s;
	}
	public void setState_MotorEngagedTime(String s) {
		State_MotorEngagedTime = s;
	}
	public void setTS_HTStationName(String s) {
		TS_HTStationName = s;
	}
	public void setTS_HTLineNum(String s) {
		TS_HTLineNum = s;
	}
	public String toString() {
		return Double.toString(State_RollerFriction) +"+"+ Double.toString(State_Temperature) +"+"+ Integer.toString(State_DeviceUpState);
	}

}
