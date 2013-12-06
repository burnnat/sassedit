module Eclipse
	module Script
		class Lexer < Sass::Script::Lexer
			TYPES = {
				:lparen => :structure,
				:rparen => :structure,
				:comma => :structure,
				:colon => :structure,
				:splat => :structure,
				
				:plus => :operator,
				:minus => :operator,
				:times => :operator,
				:div => :operator,
				:mod => :operator,
				:single_eq => :operator,
				
				:and => :operator,
				:or => :operator,
				:not => :operator,
				:eq => :operator,
				:neq => :operator,
				:gte => :operator,
				:lte => :operator,
				:gt => :operator,
				:lt => :operator,
				
				:begin_interpolation => :interpolation,
				:end_interpolation => :interpolation,
				
				:bool => :boolean,
				:const => :variable,
				:ident => :string,
				:raw => :keyword
			}
			
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
				
				@tokens << Token.new(pos, size, TYPES[type] || type)
				
				ret
			end
			
			def tokens
				@tokens
			end
		end
	end
end