package org.lucky.springinterview.controller;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SampleTest {

    @Test
    public void treeMapTest() throws Exception{
        TreeMap<String, Integer> map1 = new TreeMap<>(Comparator.reverseOrder());
        map1.put("c",3);
        map1.put("b",2);
        map1.put("a",1);
        System.out.println(map1);
        map1.reversed()
                .entrySet()
                .forEach(System.out::println);
        map1.tailMap("b").forEach((k,v) -> System.out.println(k+" -> "+v));
    }

    @Test
    public void sortingTest() throws Exception{
        Integer[] array = Arrays.asList(1,2,4,5).toArray(Integer[]::new);
        for (int i = 0; i < array.length; i++) {
            for (int j = i+1; j < array.length; j++) {
                if(array[j]<array[i]){
                    Integer tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
        int sum = Arrays.stream(array).mapToInt(Integer::intValue).sum();
        int totalSum = IntStream.rangeClosed(1, 5).sum();
        long count = Arrays.stream(array).map(i->i).peek(System.out::println).count();
//        System.out.println(count);
        System.out.println(sum);
        System.out.println(totalSum);
        System.out.println(totalSum-sum);

        List<Integer> list = new ArrayList<>(Arrays.stream(array).toList());

        list.iterator().forEachRemaining(i -> {
            System.out.println(i);
            list.add(1);
        });

        Vector<Integer> integers = new Vector<>(list);
        Enumeration<Integer> elements = integers.elements();
        while (elements.hasMoreElements()){
            System.out.println(elements.nextElement());
            integers.add(1);
        }
    }

    @Test
    public void codingTest() throws Exception{
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i< nums.length;i++){
            int compliment = target - nums[i];
            if (map.containsKey(compliment)) {
                System.out.println(nums[i]+" -> "+ nums[map.get(compliment)]);
            }
            map.put(nums[i], i);
        }
        System.out.println(Arrays.stream(twoSum(nums, target)).mapToObj(String::valueOf).collect(Collectors.joining(",")));
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    @Test
    public void staticTest() throws Exception{
        A a = null;
        a.method();

        int capacity=3;

        System.out.println(12345987654L  % 16);

    }


    @Test
    public void stringCountTest() throws Exception{
        String str = "aabbccddeeaa";
        ArrayDeque<String> chars = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            chars.add(""+c);
        }

        System.out.println(chars);
        for(int i=0; i<chars.size();i++){
            String peek = chars.peekFirst();
//            System.out.println("peek "+peek);
            int count =1;
            while(peek == chars.poll())
                count++;
//            System.out.println(peek+""+count);
        }

        System.out.println(Stream
                .iterate(str.length()-1, i -> i - 1)
                .limit(str.length())
                .map(i -> String.valueOf(str.charAt(i)))
                .collect(Collectors.joining("")));

        Arrays.stream(str.split(""))
                .reduce((f,s) -> s+f)
                .ifPresent(System.out::println);

        Stream.iterate(100, i -> i +1)
                .limit(100000)
                .parallel()
                .filter(i-> i % 11==0)
                .findAny().ifPresent(System.out::println);


        Stream.iterate(100, i -> i +1)
                .limit(100000)
                .parallel()
                .filter(i-> i %11==0)
                .findFirst().ifPresent(System.out::println);

        Map<String, Integer> wordCounts = new HashMap<>();
        wordCounts.put("apple", 5);
        wordCounts.put("banana", 7);
        wordCounts.put("cherry", 3);

        // Sort the map based on values in ascending order
        Map<String, Integer> sortedMapAsc = wordCounts.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        System.out.println("Sorted Map (Ascending Order): " + sortedMapAsc);

        String s = "Today is the happiest day of my life";
        String longest = Arrays.stream(s.split(" "))
                .max(Comparator.comparingInt(String::length))
                .orElse(null);
        System.out.println(longest);
    }


    @Test
    public void checkStaticMethod(){
        A a = new A();
        a =null;
        a.method();
        String str = "Hello how are you";

    }

}

class A {
    public static void method(){
        System.out.println("from static method A");
    }
}


