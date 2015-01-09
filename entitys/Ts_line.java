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
public class Ts_line {

    @Id
    private String TS_LineId;
  
    @Basic
    private Integer TS_LineNum;
  
    @Basic
    private String TS_LineName;
  
    @Basic
    private Timestamp TS_LineInsertionTimestamp;
  
    @Basic
    private Integer State_LineUpState;
  
    @Basic
    private Timestamp State_upDateTime;
  
	public String getTS_LineId() {
		return TS_LineId;
	}
	public void setTS_LineId(String TS_LineId) {
		this.TS_LineId = TS_LineId;
	}
  
	public Integer getTS_LineNum() {
		return TS_LineNum;
	}
	public void setTS_LineNum(Integer TS_LineNum) {
		this.TS_LineNum = TS_LineNum;
	}
  
	public String getTS_LineName() {
		return TS_LineName;
	}
	public void setTS_LineName(String TS_LineName) {
		this.TS_LineName = TS_LineName;
	}
  
	public Timestamp getTS_LineInsertionTimestamp() {
		return TS_LineInsertionTimestamp;
	}
	public void setTS_LineInsertionTimestamp(Timestamp TS_LineInsertionTimestamp) {
		this.TS_LineInsertionTimestamp = TS_LineInsertionTimestamp;
	}
  
	public Integer getState_LineUpState() {
		return State_LineUpState;
	}
	public void setState_LineUpState(Integer State_LineUpState) {
		this.State_LineUpState = State_LineUpState;
	}
  
	public Timestamp getState_upDateTime() {
		return State_upDateTime;
	}
	public void setState_upDateTime(Timestamp State_upDateTime) {
		this.State_upDateTime = State_upDateTime;
	}
  

}