package linear.object;

import java.util.ArrayList;

/**
 * Sample comparing the use of CS132ArrayList which uses the Object type to the
 * Java ArrayList which is generic and uses a type parameter.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Feb 19, 2016
 */
public class CS132VsJavaAPI {

	public static void main(String[] args) {

		/*
		 * Using CS132ArrayList: The 132 ArrayList stored its elements as type
		 * Object, which allowed the list to store any type of object. While
		 * this is desirable, it means that lots of casting is required and that
		 * the compiler cannot catch many common type errors. Because the
		 * compiler cannot catch the errors they become runtime errors, which
		 * are often more difficult to diagnose and fix.
		 */
		CS132ArrayList l1 = new CS132ArrayList();
		l1.add("a String");
		l1.add(new Integer(3));
		l1.add(new Double(2.17));

		String s1 = (String) l1.get(0); 		// cast is required.
		Double d1 = (Double) l1.get(1); 		// uh oh!

		/*
		 * Using Java's ArrayList: The Java ArrayList is generic. The type of
		 * objects that can be stored in the list is specified by a type
		 * parameter when the list is created. This allows the compiler to
		 * detect many common type errors. It also means that casts are not
		 * required when retrieving items from the list.
		 */
		ArrayList<String> l2 = new ArrayList<String>();
		l2.add("a string");
		l2.add("another string");
		l2.add("only strings!");
		l2.add(new Integer(4)); 				// no good!

		String s2 = l2.get(1); 					// no need for a cast!
		Double d2 = (Double) l2.get(2); 		// no good!
	}
}
