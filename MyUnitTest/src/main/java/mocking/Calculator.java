package mocking;

public class Calculator {
	private MathService mathService;
	
	public Calculator(MathService mathService) {
		this.mathService=mathService;
	}
	
	public int addNumbers(int a, int b) {
		return mathService.add(a, b);
	}

}
