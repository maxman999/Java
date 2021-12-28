package chapter2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {
        List<Integer> numbers1 = Arrays.asList(1,2,3);
        List<Integer> numbers2 = Arrays.asList(3,4);

        // Q1. 두 개의 숫자 리스트가 있을 때 모든 숫자 쌍의 리스트를 반환하시오.
        List<int[]> pairs =  numbers1.stream()
                                     .flatMap( i -> numbers2.stream()
                                                            .map(j -> new int[]{i,j}))
                                     .collect(Collectors.toList());

        List cPairs = pairs.stream()
                         .map(i -> Arrays.toString(i))
                         .collect(Collectors.toList());
        System.out.println(cPairs);

        // Q2.이전 예제에서 합이 3으로 나누어 떨어지는 쌍만 반환하시오.
        List<int[]> pairs2 =  numbers1.stream()
                                        .flatMap( i ->
                                                numbers2.stream()
                                                        .filter( j -> (i + j) % 3 == 0 )
                                                        .map(j -> new int[]{i,j})
                                        )
                                        .collect(Collectors.toList());

        List cPairs2 = pairs2.stream()
                .map(i -> Arrays.toString(i))
                .collect(Collectors.toList());
        System.out.println(cPairs2 + "\n");


        // 실전 연습
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // Q1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오.
        List<Transaction> q1 = transactions.stream()
                                            .filter(tcl -> tcl.getYear() == 2011)
                                            .sorted(Comparator.comparing(Transaction::getValue))
                                            .collect(Collectors.toList());

        System.out.print(" q1 : ");
        q1.forEach(vo -> System.out.print(vo.getValue() + ","));

        // Q2. 거래자가 근무하는 모든 도시를 중복 없이 나열하시오.
        List<String> q2 = transactions.stream()
                                        .map(Transaction::getTrader)
                                        .map(Trader::getCity)
                                        .distinct()
                                        .collect(Collectors.toList());
        System.out.println("\n q2 : " + q2);

        // Q3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.
        List<String> q3 = transactions.stream()
                                        .map(Transaction::getTrader)
                                        .filter( i -> i.getCity().equals("Cambridge"))
                                        .sorted(Comparator.comparing(Trader::getName))
                                        .map(Trader::getName)
                                        .collect(Collectors.toList());
        System.out.println(" q3 : " + q3);

        // Q4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오.
        List<String> q4 = transactions.stream()
                                        .map(Transaction::getTrader)
                                        .sorted(Comparator.comparing(Trader::getName))
                                        .map(Trader::getName)
                                        .collect(Collectors.toList());
        System.out.println(" q4 : " + q4);

        // Q5. 밀라노에 거래자가 있는가?
        Boolean q5 = transactions.stream()
                                    .map(Transaction::getTrader)
                                    .map(Trader::getCity)
                                    .anyMatch(i -> i.equals("Milano"));
        System.out.println(" q5 : " + q5);

        // Q6. 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오.
        List<Integer> q6 = transactions.stream()
                                        .filter(i -> i.getTrader().getCity().equals("Cambridge"))
                                        .map(Transaction::getValue)
                                        .collect(Collectors.toList());
        System.out.println(" q6 : " + q6);

        // Q7. 전체 트랜잭션 중 최대값은 얼마인가?
        int q7 = transactions.stream()
                                .map(Transaction::getValue)
                                .reduce(Integer::max)
                                .get();
        System.out.println(" q7 : " + q7);
        // Q8. 전체 트랜잭션 중 최소값은 얼마인가?
        int q8 = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min)
                .get();
        System.out.println(" q8 : " + q8);
    }
}
