package odata.subway.tokyo.realtime;

public class CMAttr {
	double State_HingeFriction;            
	double State_Temperature;               
	int State_TicketErrors;                 
	int State_TicketsAccepted;             
	int State_TicketsRefused;              
	int State_DeviceUpState;
	
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
