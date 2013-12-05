require 'sass'

require_relative 'script/lexer'
require_relative 'script/parser'
require_relative 'scss/parser'

module Eclipse
	Token = Struct.new(:pos, :length, :type) do
		def to_s
			"[#{type}] #{pos}: #{$data[pos .. pos + length - 1]}"
		end
		
		def <=>(other)
			pos <=> other.pos
		end
	end
end