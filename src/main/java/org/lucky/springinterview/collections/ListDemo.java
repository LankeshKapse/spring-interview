package org.lucky.springinterview.collections;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ListDemo {

    public static void main(String[] args) {

        // convert iterator to list
        List<String> list1 = Arrays.asList("one", "two", "three", "four");
        Iterator<String> itr = list1.iterator();
        // using while
        ArrayList<String> strings = new ArrayList<>();
        while (itr.hasNext())
            strings.add(itr.next());
        System.out.println(strings);

        // using stream
        Iterable<String> iterable = list1::iterator;

        List<String> list = StreamSupport.stream(iterable.spliterator(), false)
                .toList();
        System.out.println(list);

        // remove duplicates
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(1);

        // using hashset
        HashSet<Integer> hashSet = new HashSet<>(list2);
        System.out.println(hashSet);
        //Using stream api
        List<Integer> list3 = list2.stream()
                .distinct()
                .toList();
        System.out.println(list3);


        // find duplicates from list
        List<Integer> finalMap = list2.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toMap(Function.identity(), _ -> 1, (v1, _) -> v1 + 1), m -> m.entrySet().stream().filter(e -> e.getValue() > 1).map(Map.Entry::getKey).toList()
                        )
                );
        System.out.println(String.format("duplicate elements in map %s input list %s",finalMap,list2));

        System.out.println(list2.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));

        List<Integer> list4 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list5 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list6 = Arrays.asList(5, 2, 3, 4, 1);

        System.out.println("list are equal %s input list %s second %s" .formatted(list4.equals(list5), list4,list5));
        System.out.println("list are equal %s input list %s second %s" .formatted(list4.equals(list6), list4,list6));
        System.out.println(list4.equals(list6));

        long count = IntStream.range(0, list4.size())
                .filter(i -> list4.get(i) != list6.get(i))
                .count();
        System.out.println("list are equal %s input list %s second %s" .formatted(count <= 0, list4,list6));


        list5.stream().filter(i -> i == 5)
                .findFirst()
                .ifPresent(System.out::println);

        ArrayList<Integer> list7 = new ArrayList<>(list4);
        System.out.println(list7.removeIf(i -> i == 5)+" > "+list7);

        System.out.println(list7.stream()
                .filter(i -> i != 4)
                .toList());

        ArrayList<Integer> list8 = new ArrayList<>(list4);
        list8.addLast(1);
        System.out.println("input list %s".formatted(list8));


        LinkedList<Integer> list9 = new LinkedList<>(list8);
        list9.removeFirstOccurrence(1);
        System.out.println("input list %s vs output %s ".formatted(list8,list9));

        System.out.println(list9.stream().skip(1).toList());

        List<String> listFirst = Arrays.asList("red", "blue", "blue", "green", "red");
        List<String> otherList = Arrays.asList("red", "green", "green", "yellow");

        Set<String> result = listFirst.stream()
                .distinct()
                .filter(otherList::contains)
                .collect(Collectors.toSet());
        System.out.println(result);
        ArrayList<String> list10 = new ArrayList<>(listFirst);
        list10.removeAll(otherList);
        System.out.println(list10);

        HashMap<String,List<Integer>> map = new HashMap<>();
        map.put("abc", new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
        map.put("xyz", new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
        map.put("pqr", new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)));

        System.out.println(map);

        List<Integer> abc = map.merge("abc", new ArrayList<>(Arrays.asList(7)), (v1, v2) -> {
            v1.addAll(v2);
            return v1;
        });
        System.out.println(map);

        List<Integer> abc1 = map.compute("abc", (key, val) -> val);


        LinkedHashMap<Integer, String> lmap = new MyLinkedHashMap<>(16,.75f, true);
        lmap.put(1, "one");
        lmap.put(2, "two");
        lmap.put(3, "three");
        lmap.put(4, "four");
        lmap.put(5, "five");
        System.out.println("key set >> "+lmap.keySet());
        lmap.get(1);
        System.out.println("key set >> "+lmap.keySet());
        System.out.println(lmap.firstEntry());
        lmap.get(2);
        System.out.println(lmap.firstEntry());
        System.out.println("key set >> "+lmap.keySet());
        lmap.pollLastEntry();
        System.out.println("key set >> "+lmap.keySet());

        HashMap<Integer, List<Integer>> smap = new HashMap<>();
        smap.put(1, new ArrayList<>(Arrays.asList(1, 2, 4)));
        smap.put(5, new ArrayList<>(Arrays.asList(4, 1, 5)));
        smap.put(4, new ArrayList<>(Arrays.asList(1, 9, 4)));

        System.out.println(smap);
        Collections.sort(new ArrayList<Integer>(smap.keySet()));
        System.out.println(smap);
        System.out.println(smap.values()
                .stream()
                .flatMap(Collection::stream)
                .sorted(Comparator.reverseOrder())
                .distinct()
                .flatMap(i -> Stream.concat(Stream.of(i),Stream.of(i)))
                .distinct()
                .toList());

        HashMap<Integer, Integer> imap = new HashMap<>();
        imap.put(1, 5);
        imap.put(2, 4);
        imap.put(3, 3);
        imap.put(4, 2);
        imap.put(5, 1);
        System.out.println(imap);

        TreeMap<Integer, Integer> tmap = new TreeMap<>(imap);
        System.out.println(tmap);
        Map<Integer, Integer> collect = imap.entrySet()
                .stream()
                .sorted((o1, o2) -> o1.getValue().compareTo(o2.getValue()))
                .toList()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println(collect);
    }
}

class MyLinkedHashMap<K, V> extends LinkedHashMap<K, V> {

    private static final int MAX_ENTRIES = 3;

    public MyLinkedHashMap(
            int initialCapacity, float loadFactor, boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_ENTRIES;
    }

}
