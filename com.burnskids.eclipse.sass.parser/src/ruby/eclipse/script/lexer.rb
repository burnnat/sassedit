module Eclipse
	module Script
		class Lexer < Sass::Script::Lexer
			include Eclipse::Script::Const
			
			def initialize(str, line, offset, options)
				super
				@tokens = []
			end
			
			def next
				ret = super
				
				type = ret.type
				size = @scanner.matched_size
				final = @scanner.pos
				
				if type == :string && @scanner[2] == '#{'
					# The SassScript lexer automatically adjusts position back
					# to avoid consuming interpolation start tokens, so we just
					# need to adjust the size here to match.
					size -= 2
				end
				
				pos = final - size
				
				Eclipse.log("    (#{pos} :: #{type}) #{@scanner.string[pos..final-1]}")
				
				if type == :ident and COLORS.include?(ret.value)
					type = :color
				elsif type == :funcall
					# Function name
					@tokens << Token.new(pos, size - 1, :function)
					
					# Opening parenthesis
					pos = pos + size - 1
					size = 1
					type = :structure
				else
					type = TYPES[type] || type
				end
				
				@tokens << Token.new(pos, size, type)
				
				ret
			end
			
			def tokens
				@tokens
			end
		end
	end
end