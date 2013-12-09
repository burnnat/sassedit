package com.burnskids.eclipse.sass.parser;

public interface ISourceToken {

	public static final String DEFAULT = "default";
	public static final String BLOCK_COMMENT = "comment_block";
	public static final String SINGLE_COMMENT = "comment_line";
	public static final String STRUCTURE = "structure";
	public static final String OPERATOR = "operator";
	public static final String STRING = "string";
	public static final String NUMBER = "number";
	public static final String COLOR = "color";
	public static final String BOOLEAN = "boolean";
	public static final String DIRECTIVE = "directive";
	public static final String VARIABLE = "variable";
	public static final String FUNCTION = "function";
	public static final String MIXIN = "mixin";
	public static final String NULL = "null";
	public static final String KEYWORD = "keyword";
	public static final String SELECTOR = "selector";
	public static final String PROPERTY = "property";
	public static final String INTERPOLATION = "interpolation";

	public String getType();
	public Integer getOffset();
	public Integer getLength();

}
