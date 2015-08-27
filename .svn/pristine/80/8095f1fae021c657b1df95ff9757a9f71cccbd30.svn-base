package ca.uwaterloo.lab4_202_08;

//singleton class 
public abstract class StepCountingAlgorithm {
	public static int currentState = 0;
	private static int counter = 0; 
	public static float orientation = 0; 

	public static boolean isStepTaken(float acc_x, float acc_y, float acc_z, float currentOrientation) {
		counter ++;
		
		// Implementation of finite state machine using switch-case
		switch(currentState) {
			case 0: 
				if(acc_y < -0.4 && acc_z > 1.2 && (acc_z - acc_y) > 2.2 && (Math.abs(acc_x) < 0.4)) currentState = 1; 
				break;
			
			case 1: 
				if(acc_y > 0 && acc_z < -0.7 && (Math.abs(acc_x) < 0.4)) {
					currentState = 0; 
					if(counter > 35) { // Ensures step is not taken when devices is shaken
						counter = 0; 
						orientation = currentOrientation;
						return true; // step is taken
					}
					counter = 0;
				} 
				break;
				
			default: 
				break;
		}
		
		return false; // no step is taken 
	}
}