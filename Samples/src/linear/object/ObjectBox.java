package linear.object;

/**
 * Container that uses the type Object to allow it to store objects of any type.
 * Compare this class to the GenericBox class in the linear.generic package to
 * see how the same functionality is accomplished using Java Generics.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Feb 19, 2016
 */
public class ObjectBox {
	// Use Object so value can refer to any type of object!
	private Object value;

	public ObjectBox(Object initValue) {
		value = initValue;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object newValue) {
		value = newValue;
	}

	public static void main(String[] args) {
		ObjectBox ob = new ObjectBox("Test String");

		/*
		 * Because the compiler does not know what type is stored in the object
		 * it is necessary to type cast the values when they are retrieved.
		 */
		String s = (String) ob.getValue(); // cast is required.

		// We can put any type we like into the object
		ob.setValue(new Double(3.14));
		Double d = (Double) ob.getValue();

		// But that can lead to runtime errors that the compiler cannot detect.
		String s2 = (String) ob.getValue();
	}
}
