package odata.subway.tokyo.realtime;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class PeopleHistory {
	 //Id
	    private String TS_HTId;
	  
	    //Basic
	    private String TS_HTFrame;
	  
	    //Basic
	    private Timestamp TS_HTupDateTime;
	  
	    //Basic
	    private Integer TS_HTTotalPassenger;
	  
	    //Basic
	    private double TS_HTTotalPassengerForTenThousand;
	  
	    //Basic
	    private Integer TS_MaxTotalPassenger;
	  
	    //Basic
	    private Integer TS_HTMaxTotalPassenger;
	  
	    //Basic
	    private Integer TS_HTLineNum;
	  
	    //Basic
	    private Integer TS_HTLineOfStationNum;
	  
	    //Basic
	    private String TS_HTStationName;
	  
	    //Basic
	    private String TS_HTAllStation;
	  
		public String getTS_HTId() {
			return TS_HTId;
		}
		public void setTS_HTId(String TS_HTId) {
			this.TS_HTId = TS_HTId;
		}
	  
		public String getTS_HTFrame() {
			return TS_HTFrame;
		}
		public void setTS_HTFrame(String TS_HTFrame) {
			this.TS_HTFrame = TS_HTFrame;
		}
	  
		public Timestamp getTS_HTupDateTime() {
			return TS_HTupDateTime;
		}
		public void setTS_HTupDateTime(Timestamp TS_HTupDateTime) {
			this.TS_HTupDateTime = TS_HTupDateTime;
		}
	  
		public Integer getTS_HTTotalPassenger() {
			return TS_HTTotalPassenger;
		}
		public void setTS_HTTotalPassenger(Integer TS_HTTotalPassenger) {
			this.TS_HTTotalPassenger = TS_HTTotalPassenger;
		}
	  
		public double getTS_HTTotalPassengerForTenThousand() {
			return TS_HTTotalPassengerForTenThousand;
		}
		public void setTS_HTTotalPassengerForTenThousand(double TS_HTTotalPassengerForTenThousand) {
			this.TS_HTTotalPassengerForTenThousand = TS_HTTotalPassengerForTenThousand;
		}
	  
		public Integer getTS_MaxTotalPassenger() {
			return TS_MaxTotalPassenger;
		}
		public void setTS_MaxTotalPassenger(Integer TS_MaxTotalPassenger) {
			this.TS_MaxTotalPassenger = TS_MaxTotalPassenger;
		}
	  
		public Integer getTS_HTMaxTotalPassenger() {
			return TS_HTMaxTotalPassenger;
		}
		public void setTS_HTMaxTotalPassenger(Integer TS_HTMaxTotalPassenger) {
			this.TS_HTMaxTotalPassenger = TS_HTMaxTotalPassenger;
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
		public String toString() {
			StringBuilder sb = new StringBuilder();
			String spliter = "PEO";
			sb.append(this.getTS_HTId()+spliter);
			sb.append(this.getTS_HTFrame()+spliter);
			sb.append(this.getTS_HTupDateTime()+spliter);
			sb.append(this.getTS_HTTotalPassenger()+spliter);
			sb.append(this.getTS_HTTotalPassengerForTenThousand()+spliter);
			sb.append(this.getTS_MaxTotalPassenger()+spliter);
			sb.append(this.getTS_HTMaxTotalPassenger()+spliter);
			sb.append(this.getTS_HTLineNum()+spliter);
			sb.append(this.getTS_HTLineOfStationNum()+spliter);
			sb.append(this.getTS_HTStationName()+spliter);
			sb.append(this.getTS_HTAllStation());
			return sb.toString();
		}
		public void fromString(String s) {
			String attr[] = s.split("PEO");
			this.setTS_HTId(attr[0]);
			this.setTS_HTFrame(attr[1]);
			this.setTS_HTupDateTime(Timestamp.valueOf(attr[2]));
			this.setTS_HTTotalPassenger(Integer.valueOf(attr[3]));
			this.setTS_HTTotalPassengerForTenThousand(Double.valueOf(attr[4]));
			this.setTS_MaxTotalPassenger(Integer.valueOf(attr[5]));
			this.setTS_HTMaxTotalPassenger(Integer.valueOf(attr[6]));
			this.setTS_HTLineNum(Integer.valueOf(attr[7]));
			this.setTS_HTLineOfStationNum(Integer.valueOf(attr[8]));
			this.setTS_HTStationName(attr[9]);
			this.setTS_HTAllStation(attr[9]);
		}
		public void fromJSON(JSONObject j) throws JSONException {
			this.setTS_HTAllStation(j.getString("TS_HTAllStation"));
			this.setTS_HTFrame(j.getString("TS_HTFrame"));
			this.setTS_HTId(j.getString("TS_HTId"));
			this.setTS_HTLineNum(Integer.valueOf(j.getString("TS_HTLineNum")));
			this.setTS_HTLineOfStationNum(Integer.valueOf(j.getString("TS_HTLineOfStationNum")));
			this.setTS_HTMaxTotalPassenger(Integer.valueOf(j.getString("TS_HTMaxTotalPassenger")));
			this.setTS_HTStationName(j.getString("TS_HTStationName"));
			this.setTS_HTTotalPassenger(Integer.valueOf(j.getString("TS_HTTotalPassenger")));
			this.setTS_HTTotalPassengerForTenThousand(Double.valueOf(j.getString("TS_HTTotalPassengerForTenThousand")));
			//this.setTS_HTupDateTime(Timestamp.valueOf(j.getString("TS_HTupDateTime")));
			this.setTS_MaxTotalPassenger(Integer.valueOf(j.getString("TS_MaxTotalPassenger")));
		}
		public Map toMap() {
			Map data = new HashMap();
			 data.put("TS_HTTotalPassenger", this.TS_HTTotalPassenger);
			 data.put("TS_HTTotalPassengerForTenThousand", this.TS_HTTotalPassengerForTenThousand);
			 data.put("TS_HTId", this.TS_HTId);//uuid要自己生成
			 data.put("TS_HTFrame", this.TS_HTFrame);//TS_HTFrame转换成String类型
			 data.put("TS_HTLineNum", this.TS_HTLineNum);
			 data.put("LineOfStationNum", this.TS_HTLineOfStationNum);
			 data.put("TS_HTMaxTotalPassenger", this.TS_HTMaxTotalPassenger);
			 data.put("TS_MaxTotalPassenger", this.TS_MaxTotalPassenger);
			 return data;
		}
}
