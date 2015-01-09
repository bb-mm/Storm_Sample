package odata.subway.tokyo.realtime;

public class ElevatorAttr {
	public double  State_MotorVibration;
	public double  State_Temperature;
	public int State_DeviceUpState; //0 for ok, 1 for error
	
	public ElevatorAttr(){}
	public ElevatorAttr(String s) {
		String[] attr = s.split("+");
		this.State_MotorVibration = Double.parseDouble(attr[0]);
		this.State_Temperature = Double.parseDouble(attr[1]);
		this.State_DeviceUpState = Integer.parseInt(attr[2]);
	}
	public void set(double m, double t, int d) {
		State_MotorVibration = m;
		State_Temperature = t;
		State_DeviceUpState = d;
	}
	public String toString() {
		return Double.toString(State_MotorVibration) +"+"+ Double.toString(State_Temperature) +"+"+ Integer.toString(State_DeviceUpState);
	}
}
