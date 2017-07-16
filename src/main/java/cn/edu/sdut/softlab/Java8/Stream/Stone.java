package cn.edu.sdut.softlab.Java8.Stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 实例讲解Stream
 */
public class Stone {
	public void stoneSort() {
		// 用迭代器筛选元素
		List<Stone> stoneList = new ArrayList<Stone>();
		for (Stone s : stoneLine) {
			if (s.getWeight() < 500) {
				stoneList.add(s);
			}
		}
		// 使用匿名类对石头进行排序
		Collections.sort(stoneList, new Comparator<Stone>() {
			public int compare(Stone s1, Stone s2) {
				return Integer.compare(s1.getWeight(), s2.getWeight());
			}
		});
		// 处理
		List<String> lastStoneList = new ArrayList<Stone>();
		for (Stone s : stoneList) {
			lastStoneList.add(s.getName());
		}
	}

	// Java8实现
	public void java8Stone() {
		List<String> lastStoneList = stoneLine.stream().filter(s -> s.getWeight() < 500)// 挑选出质量小于500g的鹅卵石
				.sorted(comparing(Stone::getWeight))// 按照质量进行排序
				.map(Stone::getName)// 提取满足要求的鹅卵石的名字
				.collect(toList());// 将名字保存到List中
	}
}
