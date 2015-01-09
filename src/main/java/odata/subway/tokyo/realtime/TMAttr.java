package odata.subway.tokyo.realtime;

public class TMAttr {
	public double State_RemainingInk;             
	public int State_RemainingTickets;            
	public double State_Temperature;              
	public int State_DeviceUpState;
	
	public TMAttr(){}
	public TMAttr(String s) {
		String[] attr = s.split("+");
		this.State_RemainingInk = Double.parseDouble(attr[0]);
		this.State_RemainingTickets = Integer.parseInt(attr[1]);
		this.State_Temperature = Double.parseDouble(attr[2]);
		this.State_DeviceUpState = Integer.parseInt(attr[3]);
	}
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
