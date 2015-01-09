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
public class Ts_station_line {

    @Id
    private String TS_StationLineId;
  
    @Basic
    private String TS_StationId;
  
    @Basic
    private String TS_LineId;
  
	public String getTS_StationLineId() {
		return TS_StationLineId;
	}
	public void setTS_StationLineId(String TS_StationLineId) {
		this.TS_StationLineId = TS_StationLineId;
	}
  
	public String getTS_StationId() {
		return TS_StationId;
	}
	public void setTS_StationId(String TS_StationId) {
		this.TS_StationId = TS_StationId;
	}
  
	public String getTS_LineId() {
		return TS_LineId;
	}
	public void setTS_LineId(String TS_LineId) {
		this.TS_LineId = TS_LineId;
	}
  

}