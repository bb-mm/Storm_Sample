/**
 * Entity Default Model
 */
package olingo.odata.jpa.entitys;
import java.sql.Timestamp;
import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**
 * @author Bruce Li
 */
@Entity
public class Ts_escalator {

    @Id
    private String TS_EscalatorId;
  
    @Basic
    private String TS_EscalatorDeviceName;
  
    @Basic
    private Timestamp TS_EscalatorInsertionTimestamp;
  
    @Basic
    private double State_MotorEngagedTime;
  
    @Basic
    private double State_RollerFriction;
  
    @Basic
    private Integer State_LineNum;
  
    @Basic
    private Integer State_LineOfStationNum;
  
    @Basic
    private String State_StationName;
  
    @Basic
    private double State_Temperature;
  
    @Basic
    private double State_UpTime;
  
    @Basic
    private Integer State_DeviceUpState;
  
    @Basic
    private String State_DeviceUpStateDescription;
  
    @Basic
    private Timestamp State_upDateTime;
  
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
  
	public Integer getState_LineNum() {
		return State_LineNum;
	}
	public void setState_LineNum(Integer State_LineNum) {
		this.State_LineNum = State_LineNum;
	}
  
	public Integer getState_LineOfStationNum() {
		return State_LineOfStationNum;
	}
	public void setState_LineOfStationNum(Integer State_LineOfStationNum) {
		this.State_LineOfStationNum = State_LineOfStationNum;
	}
  
	public String getState_StationName() {
		return State_StationName;
	}
	public void setState_StationName(String State_StationName) {
		this.State_StationName = State_StationName;
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