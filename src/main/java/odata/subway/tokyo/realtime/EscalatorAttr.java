package odata.subway.tokyo.realtime;

import java.sql.Timestamp;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

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
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String spliter = "ESC";
		sb.append(this.getTS_EscalatorDeviceName()+spliter);
		sb.append(this.getTS_EscalatorId()+spliter);
		sb.append(this.getTS_HTAllStation()+spliter);
		sb.append(this.getTS_HTFrame()+spliter);
		sb.append(this.getTS_HTFrameTime()+spliter);
		sb.append(this.getTS_HTIsSingleDevice()+spliter);
		sb.append(this.getTS_HTStationName()+spliter);
		sb.append(this.getTS_EscalatorInsertionTimestamp()+spliter);
		sb.append(this.getTS_HTLineNum()+spliter);
		sb.append(this.getTS_HTLineOfStationNum()+spliter);
		sb.append(this.getState_DeviceUpStateDescription()+spliter);
		sb.append(this.getState_MotorEngagedTime()+spliter);
		sb.append(this.getState_MotorEngagedTimeExceptUpTime()+spliter);
		sb.append(this.getState_RollerFriction()+spliter);
		sb.append(this.getState_Temperature()+spliter);
		sb.append(this.getState_UpTime()+spliter);
		sb.append(this.getState_DeviceUpState()+spliter);
		sb.append(this.getState_upDateTime());
		return sb.toString();
	}
	public void fromString(String s) {
		//System.out.println(s);
		String attr[] = s.split("ESC");
		this.setTS_EscalatorDeviceName(attr[0]);
		this.setTS_EscalatorId(attr[1]);
		this.setTS_HTAllStation(attr[2]);
		this.setTS_HTFrame(attr[3]);
		this.setTS_HTFrameTime(attr[4]);
		this.setTS_HTIsSingleDevice(attr[5]);
		this.setTS_HTStationName(attr[6]);
		this.setTS_EscalatorInsertionTimestamp(Timestamp.valueOf(attr[7]));
		this.setTS_HTLineNum(Integer.valueOf(attr[8]));
		this.setTS_HTLineOfStationNum(Integer.valueOf(attr[9]));
		this.setState_DeviceUpStateDescription(attr[10]);
		this.setState_MotorEngagedTime(Double.valueOf(attr[11]));
		this.setState_MotorEngagedTimeExceptUpTime(Double.valueOf(attr[12]));
		this.setState_RollerFriction(Double.valueOf(attr[13]));
		this.setState_Temperature(Double.valueOf(attr[14]));
		this.setState_UpTime(Double.valueOf(attr[15]));
		this.setState_DeviceUpState(Integer.valueOf(attr[16]));
		//System.out.println(attr[17]);
		this.setState_upDateTime(Timestamp.valueOf(attr[17]));
		
	}
	public void fromJSON(JSONObject s) throws JSONException {
		this.setTS_EscalatorDeviceName(s.getString("TS_EscalatorDeviceName"));
		this.setTS_EscalatorId(s.getString("TS_EscalatorId"));
		this.setTS_HTAllStation(s.getString("TS_HTAllStation"));
		this.setTS_HTFrame(s.getString("TS_HTFrame"));
		this.setTS_HTFrameTime(s.getString("TS_HTFrameTime"));
		this.setTS_HTIsSingleDevice(s.getString("TS_HTIsSingleDevice"));
		this.setTS_HTStationName(s.getString("TS_HTStationName"));
		//this.setTS_EscalatorInsertionTimestamp(Timestamp.valueOf(s.getString("TS_EscalatorInsertionTimestamp")));
		this.setTS_HTLineNum(Integer.valueOf(s.getString("TS_HTLineNum")));
		this.setTS_HTLineOfStationNum(Integer.valueOf(s.getString("TS_HTLineOfStationNum")));
		this.setState_DeviceUpStateDescription(s.getString("State_DeviceUpStateDescription"));
		this.setState_MotorEngagedTime(Double.valueOf(s.getString("State_MotorEngagedTime")));
		this.setState_MotorEngagedTimeExceptUpTime(Double.valueOf(s.getString("State_MotorEngagedTimeExceptUpTime")));
		this.setState_RollerFriction(Double.valueOf(s.getString("State_RollerFriction")));
		this.setState_Temperature(Double.valueOf(s.getString("State_Temperature")));
		this.setState_UpTime(Double.valueOf(s.getString("State_UpTime")));
		this.setState_DeviceUpState(Integer.valueOf(s.getString("State_DeviceUpState")));
		//this.setState_upDateTime(Timestamp.valueOf(s.getString("State_upDateTime")));
	}
	public HashMap toMap() {
		HashMap res = new HashMap();
		res.put("TS_EscalatorDeviceName", this.TS_EscalatorDeviceName);
		res.put("TS_EscalatorId", this.TS_EscalatorId);
		res.put("TS_HTAllStation", this.TS_HTAllStation);
		res.put("TS_HTFrame", this.TS_HTFrame);
		res.put("TS_HTFrameTime", this.TS_HTFrameTime);
		res.put("TS_HTIsSingleDevice", this.TS_HTIsSingleDevice);
		res.put("TS_EscalatorInsertionTimestamp", this.TS_EscalatorInsertionTimestamp);
		res.put("TS_HTLineNum", this.TS_HTLineNum);
		res.put("TS_HTLineOfStationNum", this.TS_HTLineOfStationNum);
		res.put("State_DeviceUpStateDescription", this.State_DeviceUpStateDescription);
		res.put("State_MotorEngagedTime", this.State_MotorEngagedTime);
		res.put("State_MotorEngagedTimeExceptUpTime", this.State_MotorEngagedTimeExceptUpTime);
		res.put("State_RollerFriction", this.State_RollerFriction);
		res.put("State_Temperature", this.State_Temperature);
		res.put("State_UpTime", this.State_UpTime);
		res.put("State_DeviceUpState", this.State_DeviceUpState);
		res.put("State_upDateTime", this.State_upDateTime);
		res.put("TS_HTStationName", this.TS_HTStationName);
		return res;
	}
}
