module Sass
end

require 'sass/util'
require 'sass/engine'

require 'eclipse/capture'
require 'eclipse/token'
require 'eclipse/script/const'
require 'eclipse/script/lexer'
require 'eclipse/script/parser'
require 'eclipse/scss/parser'

module Eclipse
	@@debug = false
	
	def self.set_debug(debug)
		@@debug = debug
	end
	
	def self.log(message)
		puts message if @@debug
	end
end

def set_debug(debug)
	Eclipse.set_debug(debug)
end

def parse(data)
	# Supply a StringScanner instead of a raw string here,
	# as Sass automatically strips carriage returns from
	# raw strings which throws off the scanner offsets.
	Eclipse::SCSS::Parser.new(StringScanner.new(data), nil).parse
end