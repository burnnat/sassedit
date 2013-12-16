package com.burnskids.eclipse.sass.parser.ast;

import org.eclipse.dltk.ast.declarations.Declaration;

public class MixinDeclaration extends Declaration {
	
	public MixinDeclaration(String name, int nameStart, int nameEnd, int start, int end) {
		super(start, end);
		
		setModifier(AccPublic);
		
		setName(name);
		setNameStart(nameStart);
		setNameEnd(nameEnd);
	}

}
