package odata.subway.tokyo.realtime;

import java.sql.Timestamp;

public class EscalatorAttr {
	public double State_RollerFriction;     
	public double State_Temperature;               
	public int State_DeviceUpState;
	//other attrs...fuck...
    //Id
    private String TS_EscalatorId;
  
    //Basic
    private String TS_EscalatorDeviceName;
  
    //Basic
    private Timestamp TS_EscalatorInsertionTimestamp;
  
    //Basic
    private String TS_HTFrame;
  
    //Basic
    private String TS_HTFrameTime;
  
    //Basic
    private String TS_HTIsSingleDevice;
  
    //Basic
    private Integer TS_HTLineNum;
  
    //Basic
    private Integer TS_HTLineOfStationNum;
  
    //Basic
    private String TS_HTStationName;
  
    //Basic
    private String TS_HTAllStation;
  
    //Basic
    private double State_MotorEngagedTimeExceptUpTime;
  
    //Basic
    private double State_MotorEngagedTime;
  
    //Basic
    private double State_UpTime;
    
    //Basic
    private String State_DeviceUpStateDescription;
  
    //Basic
    private Timestamp State_upDateTime;
	
//	public EscalatorAttr(){}
//	public EscalatorAttr(String s) {
//		String[] attr = s.split("+");
//		this.State_RollerFriction = Double.parseDouble(attr[0]);
//		this.State_Temperature = Double.parseDouble(attr[1]);
//		this.State_DeviceUpState = Integer.parseInt(attr[2]);
//	}
//	public void set(double r, double t, int d) {
//		this.State_RollerFriction = r;
//		this.State_Temperature = t;
//		this.State_DeviceUpState = d;
//	}
	public String getTS_EscalatorId() {
		return TS_EscalatorId;
	}
	public void setTS_EscalatorId(String TS_EscalatorId) {
		this.TS_EscalatorId = TS_EscalatorId;
	}
  
	public String getTS_EscalatorDeviceName() {
		return TS_EscalatorDeviceName;
	}
	public void setTS_EscalatorDeviceName(String TS_EscalatorDeviceName) {
		this.TS_EscalatorDeviceName = TS_EscalatorDeviceName;
	}
  
	public Timestamp getTS_EscalatorInsertionTimestamp() {
		return TS_EscalatorInsertionTimestamp;
	}
	public void setTS_EscalatorInsertionTimestamp(Timestamp TS_EscalatorInsertionTimestamp) {
		this.TS_EscalatorInsertionTimestamp = TS_EscalatorInsertionTimestamp;
	}
  
	public String getTS_HTFrame() {
		return TS_HTFrame;
	}
	public void setTS_HTFrame(String TS_HTFrame) {
		this.TS_HTFrame = TS_HTFrame;
	}
  
	public String getTS_HTFrameTime() {
		return TS_HTFrameTime;
	}
	public void setTS_HTFrameTime(String TS_HTFrameTime) {
		this.TS_HTFrameTime = TS_HTFrameTime;
	}
  
	public String getTS_HTIsSingleDevice() {
		return TS_HTIsSingleDevice;
	}
	public void setTS_HTIsSingleDevice(String TS_HTIsSingleDevice) {
		this.TS_HTIsSingleDevice = TS_HTIsSingleDevice;
	}
  
	public Integer getTS_HTLineNum() {
		return TS_HTLineNum;
	}
	public void setTS_HTLineNum(Integer TS_HTLineNum) {
		this.TS_HTLineNum = TS_HTLineNum;
	}
  
	public Integer getTS_HTLineOfStationNum() {
		return TS_HTLineOfStationNum;
	}
	public void setTS_HTLineOfStationNum(Integer TS_HTLineOfStationNum) {
		this.TS_HTLineOfStationNum = TS_HTLineOfStationNum;
	}
  
	public String getTS_HTStationName() {
		return TS_HTStationName;
	}
	public void setTS_HTStationName(String TS_HTStationName) {
		this.TS_HTStationName = TS_HTStationName;
	}
  
	public String getTS_HTAllStation() {
		return TS_HTAllStation;
	}
	public void setTS_HTAllStation(String TS_HTAllStation) {
		this.TS_HTAllStation = TS_HTAllStation;
	}
  
	public double getState_MotorEngagedTimeExceptUpTime() {
		return State_MotorEngagedTimeExceptUpTime;
	}
	public void setState_MotorEngagedTimeExceptUpTime(double State_MotorEngagedTimeExceptUpTime) {
		this.State_MotorEngagedTimeExceptUpTime = State_MotorEngagedTimeExceptUpTime;
	}
  
	public double getState_MotorEngagedTime() {
		return State_MotorEngagedTime;
	}
	public void setState_MotorEngagedTime(double State_MotorEngagedTime) {
		this.State_MotorEngagedTime = State_MotorEngagedTime;
	}
  
	public double getState_RollerFriction() {
		return State_RollerFriction;
	}
	public void setState_RollerFriction(double State_RollerFriction) {
		this.State_RollerFriction = State_RollerFriction;
	}
  
	public double getState_Temperature() {
		return State_Temperature;
	}
	public void setState_Temperature(double State_Temperature) {
		this.State_Temperature = State_Temperature;
	}
  
	public double getState_UpTime() {
		return State_UpTime;
	}
	public void setState_UpTime(double State_UpTime) {
		this.State_UpTime = State_UpTime;
	}
  
	public Integer getState_DeviceUpState() {
		return State_DeviceUpState;
	}
	public void setState_DeviceUpState(Integer State_DeviceUpState) {
		this.State_DeviceUpState = State_DeviceUpState;
	}
  
	public String getState_DeviceUpStateDescription() {
		return State_DeviceUpStateDescription;
	}
	public void setState_DeviceUpStateDescription(String State_DeviceUpStateDescription) {
		this.State_DeviceUpStateDescription = State_DeviceUpStateDescription;
	}
  
	public Timestamp getState_upDateTime() {
		return State_upDateTime;
	}
	public void setState_upDateTime(Timestamp State_upDateTime) {
		this.State_upDateTime = State_upDateTime;
	}
}
