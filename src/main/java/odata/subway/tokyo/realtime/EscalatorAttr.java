package odata.subway.tokyo.realtime;

public class EscalatorAttr {
	double State_RollerFriction;     
	double State_Temperature;               
	int State_DeviceUpState;
	
	public void set(double r, double t, int d) {
		State_RollerFriction = r;
		State_Temperature = t;
		State_DeviceUpState = d;
	}
	public String toString() {
		return Double.toString(State_RollerFriction) +"+"+ Double.toString(State_Temperature) +"+"+ Integer.toString(State_DeviceUpState);
	}

}
