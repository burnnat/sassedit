module Eclipse
	module SCSS
		class Parser < Sass::SCSS::Parser
			def self.capture(name, &block)
				define_method(name) do |*args|
					ret = nil
					
					begin
						Eclipse.log("+[#{@captures.size}] Begin capture: #{name}")
						
						tokens = start_capture
						ret = self.class.superclass.instance_method(name).bind(self).call(*args)
						
						self.instance_exec(tokens, &block) unless ret.nil?
					ensure
						Eclipse.log("  --> REJECT") if ret.nil?
						stop_capture
						Eclipse.log("-[#{@captures.size}] End capture: #{name}")
					end
					
					ret
				end
			end
			
			def parse
				@captures = []
				@tokens = []
				
				super
				
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
					Eclipse.log("    (#{pos}) #{tok}")
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
				Eclipse.log("+[x] Begin capture: sass_script")
				
				ret = super
				output = @line
				
				@tokens.push(*output[:tokens])
				@line = output[:line]

				Eclipse.log("-[x] End capture: sass_script")
				ret
			end
			
			capture(:directive) do |tokens|
				record(:directive, tokens[0..1])
			end
			
			capture(:mixin_directive) do |tokens|
				record(:mixin, tokens[0])
			end
			
			capture(:function_directive) do |tokens|
				record(:function, tokens[0])
			end
			
			capture(:block) do |tokens|
				record(:structure, tokens[0])
				record(:structure, tokens[-1])
			end
			
			capture(:block_contents) do |tokens|
				tokens.each { |x| record(:structure, x) }
			end
			
			capture(:block_child) do |tokens|
				# Nothing here, to swallow children from being registered
				# as "structure" tokens in the block contents. This can be
				# removed once we have support for ruleset selectors.
			end
			
			capture(:declaration) do |tokens|
				record(:property, tokens[0])
				record(:structure, tokens[1])
			end
			
			capture(:variable) do |tokens|
				record(:variable, tokens[0..1])
				record(:structure, tokens[2])
				record(:keyword, tokens[3])
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