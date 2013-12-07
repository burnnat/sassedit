module Eclipse
	class Token
		attr_reader :type
		attr_reader :pos
		attr_reader :length
		
		if RUBY_PLATFORM == "java"
			include Java::com.burnskids.eclipse.sass.editor.parser.ISourceToken
			
			# Java getter aliases
			alias :getType :type
			alias :getOffset :pos
			alias :getLength :length
		end
		
		def initialize(pos, length, type)
			@pos = pos
			@length = length
			@type = type
		end
		
		def to_s
			"[#{type}] (#{pos}, #{length})"
		end
		
		def <=>(other)
			pos <=> other.pos
		end
	end
end