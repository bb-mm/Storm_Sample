package odata.subway.tokyo.realtime;

import java.sql.Timestamp;

public class ElevatorAttr {
	
	private String TS_ElevatorId;
	  
    //Basic
    private String TS_ElevatorDeviceName;
  
    //Basic
    private Timestamp TS_ElevatorInsertionTimestamp;
  
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
    private double State_MotorVibration;
  
    //Basic
    private double State_Temperature;
  
    //Basic
    private double State_UpTime;
  
    //Basic
    private Integer State_DeviceUpState;
  
    //Basic
    private String State_DeviceUpStateDescription;
  
    //Basic
    private Timestamp State_upDateTime;
  
	public String getTS_ElevatorId() {
		return TS_ElevatorId;
	}
	public void setTS_ElevatorId(String TS_ElevatorId) {
		this.TS_ElevatorId = TS_ElevatorId;
	}
  
	public String getTS_ElevatorDeviceName() {
		return TS_ElevatorDeviceName;
	}
	public void setTS_ElevatorDeviceName(String TS_ElevatorDeviceName) {
		this.TS_ElevatorDeviceName = TS_ElevatorDeviceName;
	}
  
	public Timestamp getTS_ElevatorInsertionTimestamp() {
		return TS_ElevatorInsertionTimestamp;
	}
	public void setTS_ElevatorInsertionTimestamp(Timestamp TS_ElevatorInsertionTimestamp) {
		this.TS_ElevatorInsertionTimestamp = TS_ElevatorInsertionTimestamp;
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
  
	public double getState_MotorVibration() {
		return State_MotorVibration;
	}
	public void setState_MotorVibration(double State_MotorVibration) {
		this.State_MotorVibration = State_MotorVibration;
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
		public String toString() {
			StringBuilder sb = new StringBuilder();
			String spliter = "ELE";
			sb.append(this.getTS_ElevatorDeviceName()+spliter);
			sb.append(this.getTS_ElevatorId()+spliter);
			sb.append(this.getTS_HTAllStation()+spliter);
			sb.append(this.getTS_HTFrame()+spliter);
			sb.append(this.getTS_HTFrameTime()+spliter);
			sb.append(this.getTS_HTIsSingleDevice()+spliter);
			sb.append(this.getTS_HTStationName()+spliter);
			sb.append(this.getTS_ElevatorInsertionTimestamp()+spliter);
			sb.append(this.getTS_HTLineNum()+spliter);
			sb.append(this.getTS_HTLineOfStationNum()+spliter);
			sb.append(this.getState_DeviceUpStateDescription()+spliter);
			sb.append(this.getState_MotorEngagedTime()+spliter);
			sb.append(this.getState_MotorEngagedTimeExceptUpTime()+spliter);
			sb.append(this.getState_MotorVibration()+spliter);
			sb.append(this.getState_Temperature()+spliter);
			sb.append(this.getState_UpTime()+spliter);
			sb.append(this.getState_DeviceUpState()+spliter);
			sb.append(this.getState_upDateTime());
			return sb.toString();
		}
		public void fromString(String s) {
			String attr[] = s.split("ELE");
			this.setTS_ElevatorDeviceName(attr[0]);
			this.setTS_ElevatorId(attr[1]);
			this.setTS_HTAllStation(attr[2]);
			this.setTS_HTFrame(attr[3]);
			this.setTS_HTFrameTime(attr[4]);
			this.setTS_HTIsSingleDevice(attr[5]);
			this.setTS_HTStationName(attr[6]);
			this.setTS_ElevatorInsertionTimestamp(Timestamp.valueOf(attr[7]));
			this.setTS_HTLineNum(Integer.valueOf(attr[8]));
			this.setTS_HTLineOfStationNum(Integer.valueOf(attr[9]));
			this.setState_DeviceUpStateDescription(attr[10]);
			this.setState_MotorEngagedTime(Double.valueOf(attr[11]));
			this.setState_MotorEngagedTimeExceptUpTime(Double.valueOf(attr[12]));
			this.setState_MotorVibration(Double.valueOf(attr[13]));
			this.setState_Temperature(Double.valueOf(attr[14]));
			this.setState_UpTime(Double.valueOf(attr[15]));
			this.setState_DeviceUpState(Integer.valueOf(attr[16]));
			this.setState_upDateTime(Timestamp.valueOf(attr[17]));
			
		}
		public static void main(String[] args) {
			ElevatorAttr test = new ElevatorAttr();
		}
}
