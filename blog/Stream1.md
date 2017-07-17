# **Java8系列--Java Stream入门篇（什么是Stream）**
## **1 知识框架**
  　　先上一张图来说明一下Java8中"流"的引进、意义和优势。
  ![stream1](http://img.blog.csdn.net/20170716204058364?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzM0Mjk5Njg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
## **2 传统Java集合的不足**
### **2.1 传统Java集合**
  　　集合是Java中用途十分广泛的一个集合，正是因为它对于数据的处理的优势，几乎任何一个Java应用程序都会设计对Java集合的制造和处理。

  　　然而，一个传统的Java集合仅仅是对Java集合内部的数据进行简单的添加、删除等操作，而且处理的数据类型有限。
 　　 
      当涉及到复杂的业务逻辑处理时，需要借助大量的**迭代器**才能完成需求，操作繁琐，工作量大。
### **2.2 声明式操作**
 　　我们熟悉的数据库SQL操作。例如：

```
  SELECT name FROM users WHERE age < 30
```
　　上面的SQL查询语句可以选出年龄小于30岁的用户的用户名。
在这里，我们只需要显式地声明我们的需求"选出年龄小于30岁的用户的用户名"就可以了，而不需要考虑如何实现。
    
      那么，Java集合如何实现这种显式的声明操作呢？
## **3 流是什么**
### **3.1 实例讲解**
　　下面以一个实例展开讲解，来更好地认识什么是"流"。
　　
    现在这里有若干**鹅卵石**，颜色、质量不等。
![鹅卵石](http://img.blog.csdn.net/20170716210426812?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzM0Mjk5Njg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
#### **3.1.1 需求**
  1.挑选出质量小于500g的鹅卵石，并2.按照质量从大到小的顺序将它们排成一条线。
#### **3.1.2 Java7(集合)实现**
**1.使用迭代器筛选元素**

```
List<Stone> stoneList = new ArrayList<Stone>();
		for (Stone s : stoneLine) {
			if (s.getWeight() < 500) {
				stoneList.add(s);
			}
		}
```

**2.使用匿名类对鹅卵石进行质量排序（从大到小）**

```
Collections.sort(stoneList, new Comparator<Stone>() {
			public int compare(Stone s1, Stone s2) {
				return Integer.compare(s1.getWeight(), s2.getWeight());
			}
		});
```

**3.将排好序的鹅卵石排成一条线（放到一起）**

```
List<String> lastStoneList = new ArrayList<Stone>();
		for (Stone s : stoneList) {
			lastStoneList.add(s.getName());
		}
	}
```

#### **3.1.3 Java8(Stream)实现**

```
List<String> lastStoneList = 
				stoneLine.stream()
				.filter(s -> s.getWeight() < 500)//挑选出质量小于500g的鹅卵石
				.sorted(comparing(Stone::getWeight))//按照质量进行排序
				.map(Stone::getName)//提取满足要求的鹅卵石的名字
				.collect(toList());//将名字保存到List中
```
### **3.2 什么是流**
  　　**流，是一系列数据项，它不是一种数据结构。**

  　　流可以进行相关的算法和计算，只是它并没有显式地表现，而是在内部进行隐式地操作。
## **4 流的优势**
　　流，解决了两个问题，1.直接实现你"想要什么"，2.可以进行并行处理。

　　流，的本质，集合Lambda表达式，对传统Java集合的功能强化。
　　Java 8 中的 Stream 是对集合（Collection）对象功能的增强，它专注于对集合对象进行各种非常便利、高效的聚合操作（aggregate operation），或者大批量数据操作 (bulk data operation)。Stream API 借助于同样新出现的 Lambda 表达式，极大的提高编程效率和程序可读性。同时它提供串行和并行两种模式进行汇聚操作，并发模式能够充分利用多核处理器的优势，使用 fork/join 并行方式来拆分任务和加速处理过程。通常编写并行代码很难而且容易出错, 但使用 Stream API 无需编写一行多线程的代码，就可以很方便地写出高性能的并发程序。所以说，Java 8 中首次出现的 java.util.stream 是一个函数式语言+多核时代综合影响的产物。

