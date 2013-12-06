require 'eclipse'

$data = File.read('src/test/resources/test.scss')

class Eclipse::Token
	def to_s
		"[#{type}] #{pos}: #{$data[pos .. pos + length - 1]}"
	end
end

parse($data).each do |x|
	puts x
end
