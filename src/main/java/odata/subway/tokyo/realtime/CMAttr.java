package odata.subway.tokyo.realtime;

public class CMAttr {
	public double State_HingeFriction;            
	public double State_Temperature;               
	public int State_TicketErrors;                 
	public int State_TicketsAccepted;             
	public int State_TicketsRefused;              
	public int State_DeviceUpState;
	
	public CMAttr() {}
	public CMAttr(String s) {
		String[] attr = s.split("+");
		this.State_HingeFriction = Double.parseDouble(attr[0]);
		this.State_Temperature = Double.parseDouble(attr[1]);
		this.State_TicketErrors = Integer.parseInt(attr[2]);
		this.State_TicketsAccepted = Integer.parseInt(attr[3]);
		this.State_TicketsRefused = Integer.parseInt(attr[4]);
		this.State_DeviceUpState = Integer.parseInt(attr[5]);
	}
	public void set(double hf, double t, int te, int ta, int tr, int d) {
		State_HingeFriction = hf;
		State_Temperature = t;
		State_TicketErrors = te;
		State_TicketsAccepted = ta;
		State_TicketsRefused = tr;
		State_DeviceUpState = d;
	}
	
	public String toString() {
		return Double.toString(State_HingeFriction) +"+"+ Double.toString(State_Temperature)
				+"+"+ Integer.toString(State_TicketErrors) +"+"+ Integer.toString(State_TicketsAccepted)
				+"+"+ Integer.toString(State_TicketsRefused) +"+"+ Integer.toString(State_DeviceUpState);
	}

}
