module Eclipse
	module Script
		class Parser < Sass::Script::Parser
			def lexer_class
				Lexer
			end
			
			def line
				{
					:line => @lexer.line,
					:tokens => @lexer.tokens
				}
			end
		end
	end
end