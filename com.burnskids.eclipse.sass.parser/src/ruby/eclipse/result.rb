module Eclipse
	class Result
		attr_reader :tree
		attr_reader :tokens
		
		if RUBY_PLATFORM == "java"
			include Java::com.burnskids.eclipse.sass.parser.ISourceTokenParseResult
			
			# Java getter aliases
			alias :getTree :tree
			alias :getTokens :tokens
		end
		
		def initialize(size, tree, tokens)
			@tree = Java::com.burnskids.eclipse.sass.parser.ast.SassModuleDeclaration.new(size)
			@tokens = tokens
			
			@tokens.sort!
		end
	end
end