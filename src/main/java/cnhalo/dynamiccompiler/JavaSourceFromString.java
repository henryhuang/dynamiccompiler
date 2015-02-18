package cnhalo.dynamiccompiler;

import java.net.URI;

import javax.tools.SimpleJavaFileObject;

/**
 *
 *
 * @author	HuangYijie
 * @date Feb 18, 2016 2:36:54 PM 
 * 
 */
public class JavaSourceFromString extends SimpleJavaFileObject {
	final String code;

	JavaSourceFromString(String name, String code) {
		super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
		this.code = code;
	}

	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		return code;
	}
	
}
