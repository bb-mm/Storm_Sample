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
public class Ts_thirtydaytotalpassengerhistory {

    @Id
    private String TS_HTId;
  
    @Basic
    private String TS_HTFrame;
  
    @Basic
    private Timestamp TS_HTupDateTime;
  
    @Basic
    private Integer TS_HTTotalPassenger;
  
    @Basic
    private double TS_HTTotalPassengerForTenThousand;
  
    @Basic
    private Integer TS_MaxTotalPassenger;
  
    @Basic
    private Integer TS_HTMaxTotalPassenger;
  
    @Basic
    private Integer TS_HTLineNum;
  
    @Basic
    private Integer TS_HTLineOfStationNum;
  
    @Basic
    private String TS_HTStationName;
  
    @Basic
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
  

}