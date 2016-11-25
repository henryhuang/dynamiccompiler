package com.github.henryhuang.dynamiccompiler;

import java.net.URI;

import javax.tools.SimpleJavaFileObject;

/**
 * 
 * @author Henry Huang <a href="mailto:h1886@outlook.com">h1886@outlook.com</a>
 * @version 9:06:27 PM Nov 25, 2016
 *
 */
public class JavaSourceFromString extends SimpleJavaFileObject {
	final String code;
	
	/**
	 * @param name the class's full name
	 * @param code
	 */
	JavaSourceFromString(String name, String code) {
		super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
		this.code = code;
	}

	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		return code;
	}
	
}
