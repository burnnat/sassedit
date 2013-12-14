module Sass
end

require 'sass/util'
require 'sass/tree/node'
require 'sass/tree/root_node'
require 'sass/tree/rule_node'
require 'sass/tree/comment_node'
require 'sass/tree/prop_node'
require 'sass/tree/directive_node'
require 'sass/tree/media_node'
require 'sass/tree/supports_node'
require 'sass/tree/css_import_node'
require 'sass/tree/variable_node'
require 'sass/tree/mixin_def_node'
require 'sass/tree/mixin_node'
require 'sass/tree/trace_node'
require 'sass/tree/content_node'
require 'sass/tree/function_node'
require 'sass/tree/return_node'
require 'sass/tree/extend_node'
require 'sass/tree/if_node'
require 'sass/tree/while_node'
require 'sass/tree/for_node'
require 'sass/tree/each_node'
require 'sass/tree/debug_node'
require 'sass/tree/warn_node'
require 'sass/tree/import_node'
require 'sass/tree/charset_node'
require 'sass/selector'
require 'sass/script'
require 'sass/scss'
require 'sass/media'

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