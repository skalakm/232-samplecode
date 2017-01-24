package linear.generic;

import linear.object.ObjectBox;

/**
 * A generic container that can store an object of any type. Compare this class
 * to the ObjectBox class in the linear.object package to see how the same
 * functionality is accomplished using Object references, and the issues that
 * can result.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Feb 19, 2016
 */

/*
 * The <T> is a "Type Parameter". It will take on a specified type when an
 * object is created. For example, the line:
 * 
 * GenericBox<String> gp = new GenericBox<String>();
 * 
 * results in T taking on the type String.
 */
public class GenericBox<T> {

	// Use the type parameter T anywhere the type is needed.
	private T value;
	
	public GenericBox(T initValue) {
		value = initValue;
	}
	
	public T getValue() {
		return value;
	}
	
	public void setValue(T newValue) {
		value = newValue;
	}

	public static void main(String[] args) {
		// Create a GenericBox that can hold a String object.
		GenericBox<String> sb = new GenericBox<String>("Test String");
		
		// Create a GenericBox that can hold a Double object.
		GenericBox<Double> db = new GenericBox<Double>(new Double(3.14));
		
		/*
		 * Because the compiler knows what type the generic object operates on
		 * type casts are required when retrieving values.
		 */
		String s = sb.getValue();
		Double d = db.getValue();
		
		/*
		 * Similarly, the compiler is able to detect and flag many type errors 
		 * making them easier to diagnose and fix.
		 */
		sb.setValue(new Double("2.17"));		
		db.setValue("999.99");
		Double d2 = (Double) sb.getValue();
		String s2 = (String) db.getValue();
	}
}
