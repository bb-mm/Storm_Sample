package odata.subway.tokyo.realtime;

public class ElevatorAttr {
	double  State_MotorVibration;
	double  State_Temperature;
	int State_DeviceUpState; //0 for ok, 1 for error
	
	public void set(double m, double t, int d) {
		State_MotorVibration = m;
		State_Temperature = t;
		State_DeviceUpState = d;
	}
	public String toString() {
		return Double.toString(State_MotorVibration) + Double.toString(State_Temperature) + Integer.toString(State_DeviceUpState);
	}
}
