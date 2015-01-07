package odata.subway.tokyo.realtime;

public class TMAttr {
	double State_RemainingInk;             
	int State_RemainingTickets;            
	double State_Temperature;              
	int State_DeviceUpState;
	
	public void set(double ri, int rt, double t, int d) {
		State_RemainingInk = ri;
		State_RemainingTickets = rt;
		State_Temperature = t;
		State_DeviceUpState = d;
	}
	
	public String toString() {
		return Double.toString(State_RemainingInk) +"+"+ Integer.toString(State_RemainingTickets) 
				+"+"+ Double.toString(State_Temperature) +"+"+ Integer.toString(State_DeviceUpState);
	}

}
