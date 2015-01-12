package odata.subway.tokyo.realtime;

import java.sql.Timestamp;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

public class CMAttr {
//	public double State_HingeFriction;            
//	public double State_Temperature;               
//	public int State_TicketErrors;                 
//	public int State_TicketsAccepted;             
//	public int State_TicketsRefused;              
//	public int State_DeviceUpState;
//	
//	public CMAttr() {}
//	public CMAttr(String s) {
//		String[] attr = s.split("+");
//		this.State_HingeFriction = Double.parseDouble(attr[0]);
//		this.State_Temperature = Double.parseDouble(attr[1]);
//		this.State_TicketErrors = Integer.parseInt(attr[2]);
//		this.State_TicketsAccepted = Integer.parseInt(attr[3]);
//		this.State_TicketsRefused = Integer.parseInt(attr[4]);
//		this.State_DeviceUpState = Integer.parseInt(attr[5]);
//	}
//	public void set(double hf, double t, int te, int ta, int tr, int d) {
//		State_HingeFriction = hf;
//		State_Temperature = t;
//		State_TicketErrors = te;
//		State_TicketsAccepted = ta;
//		State_TicketsRefused = tr;
//		State_DeviceUpState = d;
//	}
//	
//	public String toString() {
//		return Double.toString(State_HingeFriction) +"+"+ Double.toString(State_Temperature)
//				+"+"+ Integer.toString(State_TicketErrors) +"+"+ Integer.toString(State_TicketsAccepted)
//				+"+"+ Integer.toString(State_TicketsRefused) +"+"+ Integer.toString(State_DeviceUpState);
//	}
	 //Id
	    private String TS_GateId;
	  
	    //Basic
	    private String TS_GateDeviceName;
	  
	    //Basic
	    private Timestamp TS_GateInsertionTimestamp;
	  
	    //Basic
	    private String TS_GateDeviceFunction;
	  
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
	    private double State_MotorEngagedTime;
	  
	    //Basic
	    private String State_GateName;
	  
	    //Basic
	    private double State_MotorEngagedTimeExceptUpTime;
	  
	    //Basic
	    private double State_HingeFriction;
	  
	    //Basic
	    private double State_Temperature;
	  
	    //Basic
	    private Integer State_TicketErrors;
	  
	    //Basic
	    private Integer State_TicketsAccepted;
	  
	    //Basic
	    private Integer State_TicketsRefused;
	  
	    //Basic
	    private double State_UpTime;
	  
	    //Basic
	    private Integer State_DeviceUpState;
	  
	    //Basic
	    private String State_DeviceUpStateDescription;
	  
	    //Basic
	    private Timestamp State_upDateTime;
	  
		public String getTS_GateId() {
			return TS_GateId;
		}
		public void setTS_GateId(String TS_GateId) {
			this.TS_GateId = TS_GateId;
		}
	  
		public String getTS_GateDeviceName() {
			return TS_GateDeviceName;
		}
		public void setTS_GateDeviceName(String TS_GateDeviceName) {
			this.TS_GateDeviceName = TS_GateDeviceName;
		}
	  
		public Timestamp getTS_GateInsertionTimestamp() {
			return TS_GateInsertionTimestamp;
		}
		public void setTS_GateInsertionTimestamp(Timestamp TS_GateInsertionTimestamp) {
			this.TS_GateInsertionTimestamp = TS_GateInsertionTimestamp;
		}
	  
		public String getTS_GateDeviceFunction() {
			return TS_GateDeviceFunction;
		}
		public void setTS_GateDeviceFunction(String TS_GateDeviceFunction) {
			this.TS_GateDeviceFunction = TS_GateDeviceFunction;
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
	  
		public double getState_MotorEngagedTime() {
			return State_MotorEngagedTime;
		}
		public void setState_MotorEngagedTime(double State_MotorEngagedTime) {
			this.State_MotorEngagedTime = State_MotorEngagedTime;
		}
	  
		public String getState_GateName() {
			return State_GateName;
		}
		public void setState_GateName(String State_GateName) {
			this.State_GateName = State_GateName;
		}
	  
		public double getState_MotorEngagedTimeExceptUpTime() {
			return State_MotorEngagedTimeExceptUpTime;
		}
		public void setState_MotorEngagedTimeExceptUpTime(double State_MotorEngagedTimeExceptUpTime) {
			this.State_MotorEngagedTimeExceptUpTime = State_MotorEngagedTimeExceptUpTime;
		}
	  
		public double getState_HingeFriction() {
			return State_HingeFriction;
		}
		public void setState_HingeFriction(double State_HingeFriction) {
			this.State_HingeFriction = State_HingeFriction;
		}
	  
		public double getState_Temperature() {
			return State_Temperature;
		}
		public void setState_Temperature(double State_Temperature) {
			this.State_Temperature = State_Temperature;
		}
	  
		public Integer getState_TicketErrors() {
			return State_TicketErrors;
		}
		public void setState_TicketErrors(Integer State_TicketErrors) {
			this.State_TicketErrors = State_TicketErrors;
		}
	  
		public Integer getState_TicketsAccepted() {
			return State_TicketsAccepted;
		}
		public void setState_TicketsAccepted(Integer State_TicketsAccepted) {
			this.State_TicketsAccepted = State_TicketsAccepted;
		}
	  
		public Integer getState_TicketsRefused() {
			return State_TicketsRefused;
		}
		public void setState_TicketsRefused(Integer State_TicketsRefused) {
			this.State_TicketsRefused = State_TicketsRefused;
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
			String spliter = "GATE";
			sb.append(this.getTS_GateDeviceName()+spliter);
			sb.append(this.getTS_GateId()+spliter);
			sb.append(this.getTS_HTAllStation()+spliter);
			sb.append(this.getTS_HTFrame()+spliter);
			sb.append(this.getTS_HTFrameTime()+spliter);
			sb.append(this.getTS_HTIsSingleDevice()+spliter);
			sb.append(this.getTS_HTStationName()+spliter);
			sb.append(this.getTS_GateInsertionTimestamp()+spliter);
			sb.append(this.getTS_HTLineNum()+spliter);
			sb.append(this.getTS_HTLineOfStationNum()+spliter);
			sb.append(this.getState_DeviceUpStateDescription()+spliter);
			sb.append(this.getState_MotorEngagedTime()+spliter);
			sb.append(this.getState_MotorEngagedTimeExceptUpTime()+spliter);
			sb.append(this.getState_Temperature()+spliter);
			sb.append(this.getState_UpTime()+spliter);
			sb.append(this.getState_DeviceUpState()+spliter);
			sb.append(this.getState_upDateTime());
			sb.append(this.getState_HingeFriction()+spliter);
			sb.append(this.getState_TicketErrors()+spliter);
			sb.append(this.getState_TicketsAccepted()+spliter);
			sb.append(this.getState_TicketsRefused()+spliter);
			sb.append(this.getState_GateName()+spliter);
			sb.append(this.getTS_GateDeviceFunction());
			return sb.toString();
		}
		public void fromString(String s) {
			String attr[] = s.split("GATE");
			this.setTS_GateDeviceName(attr[0]);
			this.setTS_GateId(attr[1]);
			this.setTS_HTAllStation(attr[2]);
			this.setTS_HTFrame(attr[3]);
			this.setTS_HTFrameTime(attr[4]);
			this.setTS_HTIsSingleDevice(attr[5]);
			this.setTS_HTStationName(attr[6]);
			this.setTS_GateInsertionTimestamp(Timestamp.valueOf(attr[7]));
			this.setTS_HTLineNum(Integer.valueOf(attr[8]));
			this.setTS_HTLineOfStationNum(Integer.valueOf(attr[9]));
			this.setState_DeviceUpStateDescription(attr[10]);
			this.setState_MotorEngagedTime(Double.valueOf(attr[11]));
			this.setState_MotorEngagedTimeExceptUpTime(Double.valueOf(attr[12]));
			this.setState_Temperature(Double.valueOf(attr[13]));
			this.setState_UpTime(Double.valueOf(attr[14]));
			this.setState_DeviceUpState(Integer.valueOf(attr[15]));
			this.setState_upDateTime(Timestamp.valueOf(attr[16]));
			this.setState_HingeFriction(Double.valueOf(attr[17]));
			this.setState_TicketErrors(Integer.valueOf(attr[18]));
			this.setState_TicketsAccepted(Integer.valueOf(attr[19]));
			this.setState_TicketsRefused(Integer.valueOf(attr[20]));
			this.setState_GateName(attr[21]);
			this.setTS_GateDeviceFunction(attr[22]);
		}
		public void fromJSON(JSONObject s) throws JSONException {
			this.setTS_GateDeviceName(s.getString("TS_GateDeviceName"));
			this.setTS_GateId(s.getString("TS_GateId"));
			this.setTS_HTAllStation(s.getString("TS_HTAllStation"));
			this.setTS_HTFrame(s.getString("TS_HTFrame"));
			this.setTS_HTFrameTime(s.getString("TS_HTFrameTime"));
			this.setTS_HTIsSingleDevice(s.getString("TS_HTIsSingleDevice"));
			this.setTS_HTStationName(s.getString("TS_HTStationName"));
			//this.setTS_GateInsertionTimestamp(Timestamp.valueOf(s.getString("TS_GateInsertionTimestamp")));
			this.setTS_HTLineNum(Integer.valueOf(s.getString("TS_HTLineNum")));
			this.setTS_HTLineOfStationNum(Integer.valueOf(s.getString("TS_HTLineOfStationNum")));
			this.setState_DeviceUpStateDescription(s.getString("State_DeviceUpStateDescription"));
			this.setState_MotorEngagedTime(Double.valueOf(s.getString("State_MotorEngagedTime")));
			this.setState_MotorEngagedTimeExceptUpTime(Double.valueOf(s.getString("State_MotorEngagedTimeExceptUpTime")));
			this.setState_Temperature(Double.valueOf(s.getString("State_Temperature")));
			this.setState_UpTime(Double.valueOf(s.getString("State_UpTime")));
			this.setState_DeviceUpState(Integer.valueOf(s.getString("State_DeviceUpState")));
			//this.setState_upDateTime(Timestamp.valueOf(s.getString("State_upDateTime")));
			this.setState_HingeFriction(Double.valueOf(s.getString("State_HingeFriction")));
			this.setState_TicketErrors(Integer.valueOf(s.getString("State_TicketErrors")));
			this.setState_TicketsAccepted(Integer.valueOf(s.getString("State_TicketsAccepted")));
			this.setState_TicketsRefused(Integer.valueOf(s.getString("State_TicketsRefused")));
			this.setState_GateName(s.getString("State_GateName"));
			this.setTS_GateDeviceFunction(s.getString("TS_GateDeviceFunction"));
		}
		public HashMap toMap() {
			HashMap res = new HashMap();
			res.put("TS_GateDeviceName", this.TS_GateDeviceName);
			res.put("TS_GateId", this.TS_GateId);
			res.put("TS_HTAllStation", this.TS_HTAllStation);
			res.put("TS_HTFrame", this.TS_HTFrame);
			res.put("TS_HTFrameTime", this.TS_HTFrameTime);
			res.put("TS_HTIsSingleDevice", this.TS_HTIsSingleDevice);
			res.put("TS_GateInsertionTimestamp", this.TS_GateInsertionTimestamp);
			res.put("TS_HTLineNum", this.TS_HTLineNum);
			res.put("TS_HTLineOfStationNum", this.TS_HTLineOfStationNum);
			res.put("State_DeviceUpStateDescription", this.State_DeviceUpStateDescription);
			res.put("State_MotorEngagedTime", this.State_MotorEngagedTime);
			res.put("State_MotorEngagedTimeExceptUpTime", this.State_MotorEngagedTimeExceptUpTime);
			res.put("TS_GateDeviceFunction", this.TS_GateDeviceFunction);
			res.put("State_Temperature", this.State_Temperature);
			res.put("State_UpTime", this.State_UpTime);
			res.put("State_DeviceUpState", this.State_DeviceUpState);
			res.put("State_upDateTime", this.State_upDateTime);
			res.put("TS_HTStationName", this.TS_HTStationName);
			res.put("State_HingeFriction", this.State_HingeFriction);
			res.put("State_TicketErrors", this.State_TicketErrors);
			res.put("State_TicketsAccepted", this.State_TicketsAccepted);
			res.put("State_TicketsRefused", this.State_TicketsRefused);
			return res;
		}
}
