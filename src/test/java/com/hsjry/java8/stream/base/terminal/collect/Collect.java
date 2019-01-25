package com.hsjry.java8.stream.base.terminal.collect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.hsjry.java8.stream.model.User;

import lombok.extern.slf4j.Slf4j;

/**
 * 该操作符内，当使用{@link java.util.stream.Collectors}中的方法时，会变形出很多的使用方式
 *
 * @author jinyn22648
 * @version $$Id: Collect.java, v 0.1 2019/1/25 2:59 PM jinyn22648 Exp $$
 */
@Slf4j
public class Collect {

    /**
     * 使用{@link Collectors#toList()}把流转为List
     */
    @Test
    public void toList() {
        List<User> userList = Lists.newArrayList(User.builder().id("1").build(), User.builder().id("2").build(),
            User.builder().id("3").build(), User.builder().id("4").build(), User.builder().id("5").build());
        log.info("before: {}", userList);

        List<String> idList = userList.stream().map(User::getId).collect(Collectors.toList());
        log.info("after: {}", idList);
    }

    /**
     * 使用{@link Collectors#toSet()}把流转为Set，且会进行去重
     */
    @Test
    public void toSet() {
        List<User> userList = Lists.newArrayList(User.builder().id("1").build(), User.builder().id("2").build(),
            User.builder().id("3").build(), User.builder().id("4").build(), User.builder().id("5").build(),
            User.builder().id("1").build());
        log.info("before: {}", userList);

        Set<String> idSet = userList.stream().map(User::getId).collect(Collectors.toSet());
        log.info("after: {}", idSet);
    }

    /**
     * 使用toMap转为Map结构
     * {@link Collectors#toMap(Function, Function)} 第一个入参为map的key如何生成，第二个参数为map的value如何生成
     */
    @Test
    public void toMapWithTwoParameters() {
        List<User> userList = Lists.newArrayList(User.builder().id("1").build(), User.builder().id("2").build(),
            User.builder().id("3").build(), User.builder().id("4").build(), User.builder().id("5").build());
        log.info("before: {}", userList);

        Map<String, User> map = userList.stream().collect(Collectors.toMap(User::getId, input -> input));
        log.info("after: {}", map);
    }

    /**
     * 使用toMap转为Map结构
     *
     * {@link Collectors#toMap(Function, Function, BinaryOperator)} 前两个参数跟上面一个，第三个参数是当key相同时，如何处理
     */
    @Test
    public void toMapWithThreeParameters() {
        List<User> userList = Lists.newArrayList(User.builder().id("1").build(), User.builder().id("2").build(),
            User.builder().id("3").build(), User.builder().id("4").build(), User.builder().id("5").build(),
            User.builder().id("1").build());
        log.info("before: {}", userList);
        Map<String, User> map = userList.stream().collect(Collectors.toMap(User::getId, input -> input,
            // 如果key相同，则选取第一个对象最为map的value
            (o1, o2) -> o1));
        log.info("after: {}", map);

    }

    /**
     * 使用toMap转为Map结构
     *
     * {@link Collectors#toMap(Function, Function, BinaryOperator, Supplier)} 前三个参数跟上面一个，第四个参数是指定Map的实现
     */
    @Test
    public void toMapWithFourParameters() {
        List<User> userList = Lists.newArrayList(User.builder().id("1").build(), User.builder().id("2").build(),
            User.builder().id("3").build(), User.builder().id("4").build(), User.builder().id("5").build(),
            User.builder().id("1").build());
        log.info("before: {}", userList);

        Map<String, User> map = userList.stream().collect(Collectors.toMap(User::getId, input -> input,
            // 如果key相同，则选取第一个对象最为map的value
            (o1, o2) -> o1,
            // 实现为HashMap，只要是Map的子类，都可以进行指定
            HashMap::new));
        log.info("after: {}", map);
    }

    /**
     * 对集合中的数据进行分组，最终结果是一个Map结构，key是分组参数，value是一个集合
     *
     * {@link Collectors#groupingBy(Function)} 入参为分组的参数
     */
    @Test
    public void groupingByWithOneParameter() {
        List<User> userList = Lists.newArrayList(User.builder().id("1").build(), User.builder().id("2").build(),
            User.builder().id("3").build(), User.builder().id("4").build(), User.builder().id("5").build(),
            User.builder().id("1").build());
        log.info("before: {}", userList);

        // 按照id进行分组
        Map<String, List<User>> userMap = userList.stream().collect(Collectors.groupingBy(User::getId));
        log.info("after: {}", userMap);

    }

    /**
     * 对集合中的数据进行分组，最终结果是一个Map结构，key是分组参数，value是一个集合
     *
     * {@link Collectors#groupingBy(Function, Collector)} 第一个参数跟上面一样
     * 第二个参数可以传入{@link Collectors#counting()}用以统计每个分组下的数量
     */
    @Test
    public void groupingByCounting() {
        List<User> userList = Lists.newArrayList(User.builder().id("1").build(), User.builder().id("2").build(),
            User.builder().id("3").build(), User.builder().id("4").build(), User.builder().id("5").build(),
            User.builder().id("1").build());
        log.info("before: {}", userList);

        // 按照id进行分组
        Map<String, Long> userMap = userList.stream().collect(
            Collectors.groupingBy(User::getId, Collectors.counting()));
        log.info("after: {}", userMap);
    }

    /**
     * 对集合中的数据进行分组，最终结果是一个Map结构，key是分组参数，value是一个集合
     *
     * {@link Collectors#groupingBy(Function, Collector)} 第一个参数跟上面一样
     * 第一个参数也可以传入{@link Collectors#mapping(Function, Collector)} 把map中的value进行改造
     */
    @Test
    public void groupingByMapping() {
        List<User> userList = Lists.newArrayList(User.builder().id("1").name("name1").build(), User.builder().id("2")
            .name("name2").build(), User.builder().id("3").name("name3").build(), User.builder().id("4").name("name4")
            .build(), User.builder().id("5").name("name5").build(), User.builder().id("1").name("name6").build());
        log.info("before: {}", userList);
        // 按照id进行分组
        Map<String, Set<String>> userMap = userList.stream().collect(
            Collectors.groupingBy(User::getId, Collectors.mapping(
                // map下的value是一个集合，该集合要放置的参数
                User::getName,
                // map下的value使用哪种集合，这里转换为Set
                Collectors.toSet())));
        log.info("after: {}", userMap);
    }

}
