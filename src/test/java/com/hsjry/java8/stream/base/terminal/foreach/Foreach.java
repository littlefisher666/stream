package com.hsjry.java8.stream.base.terminal.foreach;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.hsjry.java8.stream.model.User;

import lombok.extern.slf4j.Slf4j;

/**
 * {@link Stream#forEach(Consumer)}接收一个 Lambda 表达式，遍历流的每一个元素，如果使用并行流，则无法保证执行顺序
 * {@link Stream#forEachOrdered(Consumer)}接收一个 Lambda 表达式，遍历流的每一个元素，由于{@link Stream#forEach(Consumer)}在并行流时无法保证按照流的顺序执行，所以出现该操作符，按照顺序执行
 *
 * PS: 不能修改自己包含的本地变量值，也不能用 break/return 之类的关键字提前结束循环
 *
 * @author jinyn22648
 * @version $$Id: Foreach.java, v 0.1 2019/1/23 2:47 PM jinyn22648 Exp $$
 */
@Slf4j
public class Foreach {

    @Test
    public void foreach() {
        List<User> userList = Lists.newArrayList(User.builder().id("1").build(), User.builder().id("2").build(),
            User.builder().id("3").build(), User.builder().id("4").build(), User.builder().id("5").build());
        log.info("串行流执行");
        userList.stream().map(User::getId).forEach(input -> log.info("{}", input));
        log.info("并行流执行");
        userList.parallelStream().map(User::getId).forEach(input -> log.info("{}", input));
    }

    @Test
    public void forEachOrdered() {
        List<User> userList = Lists.newArrayList(User.builder().id("1").build(), User.builder().id("2").build(),
            User.builder().id("3").build(), User.builder().id("4").build(), User.builder().id("5").build());
        log.info("串行流执行");
        userList.stream().map(User::getId).forEachOrdered(input -> log.info("{}", input));
        log.info("并行流执行，按照原集合中的顺序进行遍历");
        userList.parallelStream().map(User::getId).forEachOrdered(input -> log.info("{}", input));
    }

}
