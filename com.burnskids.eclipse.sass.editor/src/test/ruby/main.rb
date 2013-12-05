require 'eclipse'

filename = 'test.scss'
$data = File.read('src/test/resources/test.scss')

tokens = Eclipse::SCSS::Parser.new($data, filename).parse_tokens

tokens.each do |x|
	puts x
end
