package cn.edu.sdut.softlab.Java8.Stream;

import java.util.stream.Stream;

public class ForEachDemo {
	public static void main() {
		Stream<String> stream = Stream.of("I", "love", "you", "too");
		stream.forEach(str -> System.out.println(str));
	}
}
