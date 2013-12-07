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
				
				size = @scanner.matched_size
				final = @scanner.pos
				pos = final - size
				type = ret.type
				
				Eclipse.log("    (#{pos} :: #{type}) #{@scanner.string[pos..final-1]}")
				
				if type == :ident and COLORS.include?(ret.value)
					type = :color
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