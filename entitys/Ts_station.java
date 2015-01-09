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
public class Ts_station {

    @Id
    private String TS_StationId;
  
    @Basic
    private Integer TS_LineNum;
  
    @Basic
    private Integer TS_LineOfStationNum;
  
    @Basic
    private String TS_StationName;
  
    @Basic
    private Timestamp TS_StationInsertionTimestamp;
  
    @Basic
    private String State_type;
  
    @Basic
    private String State_message;
  
    @Basic
    private Integer State_totalPassengers;
  
    @Basic
    private Timestamp State_upDateTime;
  
	public String getTS_StationId() {
		return TS_StationId;
	}
	public void setTS_StationId(String TS_StationId) {
		this.TS_StationId = TS_StationId;
	}
  
	public Integer getTS_LineNum() {
		return TS_LineNum;
	}
	public void setTS_LineNum(Integer TS_LineNum) {
		this.TS_LineNum = TS_LineNum;
	}
  
	public Integer getTS_LineOfStationNum() {
		return TS_LineOfStationNum;
	}
	public void setTS_LineOfStationNum(Integer TS_LineOfStationNum) {
		this.TS_LineOfStationNum = TS_LineOfStationNum;
	}
  
	public String getTS_StationName() {
		return TS_StationName;
	}
	public void setTS_StationName(String TS_StationName) {
		this.TS_StationName = TS_StationName;
	}
  
	public Timestamp getTS_StationInsertionTimestamp() {
		return TS_StationInsertionTimestamp;
	}
	public void setTS_StationInsertionTimestamp(Timestamp TS_StationInsertionTimestamp) {
		this.TS_StationInsertionTimestamp = TS_StationInsertionTimestamp;
	}
  
	public String getState_type() {
		return State_type;
	}
	public void setState_type(String State_type) {
		this.State_type = State_type;
	}
  
	public String getState_message() {
		return State_message;
	}
	public void setState_message(String State_message) {
		this.State_message = State_message;
	}
  
	public Integer getState_totalPassengers() {
		return State_totalPassengers;
	}
	public void setState_totalPassengers(Integer State_totalPassengers) {
		this.State_totalPassengers = State_totalPassengers;
	}
  
	public Timestamp getState_upDateTime() {
		return State_upDateTime;
	}
	public void setState_upDateTime(Timestamp State_upDateTime) {
		this.State_upDateTime = State_upDateTime;
	}
  

}