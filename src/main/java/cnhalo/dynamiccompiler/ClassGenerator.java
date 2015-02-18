package cnhalo.dynamiccompiler;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

/**
 * build a class from code
 *
 * @author HuangYijie
 * @date Feb 18, 2016 2:36:35 PM
 * 
 */
public class ClassGenerator {

	private String classRootDir;

	public ClassGenerator() {
		this(".");
	}
	
	/**
	 * class root dir
	 *
	 * @param classRootDir
	 */
	public ClassGenerator(String classRootDir) {
		this.classRootDir = classRootDir;
	}

	/**
	 * 
	 *
	 * @param classFullName
	 * @param code
	 * @return
	 * @throws MalformedURLException
	 * @throws ClassNotFoundException
	 */
	public Class<?> generate(String classFullName, String code)
			throws MalformedURLException, ClassNotFoundException {

		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		JavaFileObject fileObject = new JavaSourceFromString(classFullName, code);

		if (!new File(classRootDir).exists()) {
			new File(classRootDir).mkdirs();
		}

		Iterable<String> options = Arrays.asList("-d", classRootDir);
		Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(fileObject);
		CompilationTask task = compiler.getTask(null, null, null, options, null, compilationUnits);

		// 产生class
		boolean success = task.call();
		if (success) {
			File root = new File(classRootDir);
			URL[] urls;
			urls = new URL[] { root.toURI().toURL() };
			URLClassLoader classLoader = URLClassLoader.newInstance(urls);
			Class<?> clazz = Class.forName(classFullName, true, classLoader);
			return clazz;
		}

		return null;
	}

}
