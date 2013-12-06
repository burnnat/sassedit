module Eclipse
	module Script
		class Lexer < Sass::Script::Lexer
			def initialize(str, line, offset, options)
				super
				@tokens = []
			end
			
			def next
				ret = super
				
				size = @scanner.matched_size
				@tokens << Token.new(@scanner.pos - size, size, :script)
				
				ret
			end
			
			def tokens
				@tokens
			end
		end
	end
end