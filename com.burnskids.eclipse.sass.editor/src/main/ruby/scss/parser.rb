module Eclipse
	module SCSS
		class Parser < Sass::SCSS::Parser
			def self.capture(name, &block)
				define_method(name) do |*args|
					tokens = start_capture
					ret = self.class.superclass.instance_method(name).bind(self).call(*args)
					
					self.instance_exec(tokens, &block) unless ret.nil?
					
					stop_capture
					ret
				end
			end
			
			def parse_tokens
				@captures = []
				@tokens = []
				
				parse
				
				@tokens.sort!
			end
			
			def record(type, pieces)
				return if pieces.nil?
				
				# coerce to an array
				pieces = [pieces].flatten
				
				front = pieces.map { |x| x.pos }.min
				back = pieces.map { |x| x.pos + x.length }.max
				
				@tokens << Token.new(front, back - front, type)
			end
			
			def start_capture
				capture = []
				@captures << capture
				capture
			end
			
			def stop_capture
				@captures.pop
			end
			
			def tok(rx, last_group_lookahead = false)
				pos = @scanner.pos
				tok = super
				
				unless tok.nil? or rx == S
					@captures.last << Token.new(pos, tok.size, nil)
				end
				
				tok
			end
			
			@sass_script_parser = Class.new(Eclipse::Script::Parser)
			@sass_script_parser.send(:include, Sass::SCSS::ScriptParser)
			
			def self.sass_script_parser
				@sass_script_parser
			end
			
			def sass_script(*args)
				ret = super
				output = @line
				
				@tokens.push(*output[:tokens])
				@line = output[:line]
				
				ret
			end
			
			capture(:directive) do |tokens|
				record(:directive, tokens[0..1])
			end
			
			capture(:block) do |tokens|
				record(:structure, tokens[0])
				record(:structure, tokens[-1])
			end
			
			capture(:block_contents) do |tokens|
				tokens.each { |x| record(:structure, x) }
			end
			
			capture(:declaration) do |tokens|
				record(:property, tokens[0])
				record(:structure, tokens[1])
			end
			
			capture(:variable) do |tokens|
				record(:variable, tokens[0..1])
				record(:structure, tokens[2])
				record(:guard, tokens[3])
			end
			
			def value!
				space = !str {ss}.empty?
				@use_property_exception ||= space || !tok?(IDENT)
				
				return true, Sass::Script::String.new("") if tok?(/\{/)
				
				return space, sass_script(:parse)
			end
		end
	end
end