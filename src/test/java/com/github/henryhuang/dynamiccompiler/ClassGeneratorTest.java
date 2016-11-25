package com.github.henryhuang.dynamiccompiler;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.henryhuang.dynamiccompiler.ClassGenerator;

/**
 * 
 * @author Henry Huang <a href="mailto:h1886@outlook.com">h1886@outlook.com</a>
 * @version 9:09:19 PM Nov 25, 2016
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
