package cnhalo.dynamiccompiler;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 *
 * @author HuangYijie
 * @date Feb 16, 2016 10:33:44 AM
 * 
 */
public class ClassGeneratorTest {

	private static OutputStream out;
	private static PrintStream printStream;

	@BeforeClass
	public static void init() {
		out = new ByteArrayOutputStream();
		printStream = new PrintStream(out);
		System.setOut(printStream);
	}

	@AfterClass
	public static void afterClass() {
		printStream.close();
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
		ClassGenerator builder = new ClassGenerator(".");
		try {
			Class<?> testClass = builder.generate("Test",
					"public class Test{public static void main(String[] args){System.out.println(\"Test!\");}}");
			String[] params = null;
			testClass.getMethod("main", String[].class).invoke(null, (Object) params);
			String ret = out.toString().trim();
			assertEquals("Test!", ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
