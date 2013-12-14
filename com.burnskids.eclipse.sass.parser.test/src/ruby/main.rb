require 'eclipse'

$data = File.read(ARGV[0])

class Eclipse::Token
	def to_s
		"[#{type}] #{pos}: #{$data[pos .. pos + length - 1]}"
	end
end

Eclipse.set_debug(true)

parse($data).each do |x|
	puts x
end
