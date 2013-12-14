module Eclipse
	class Capture
		attr_reader :input
		attr_reader :output
		
		def initialize
			@input = []
			@output = []
		end
		
		def <<(token)
			if token.type.nil?
				@input << token
			else
				@output << token
			end
		end
		
		def merge(other)
			@output.push(*other.output)
		end
	end
end