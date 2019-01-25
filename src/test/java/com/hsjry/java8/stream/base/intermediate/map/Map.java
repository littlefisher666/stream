package com.hsjry.java8.stream.base.intermediate.map;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.hsjry.java8.stream.model.User;

import lombok.extern.slf4j.Slf4j;

/**
 * 方法将流中的元素映射成另外的值，新的值类型可以和原来的元素的类型不同
 * 该操作符会遍历流中所有元素，且经过该操作符操作后的的流与操作前的流是一一对应的
 * 坚决禁止使用该操作符作为foreach来使用，如果不需要拿到流的结果，请直接使用{@link java.util.stream.Stream#forEach(Consumer)}终止操作符
 *
 * @author jinyn22648
 * @version $$Id: Map.java, v 0.1 2019/1/23 1:49 PM jinyn22648 Exp $$
 */
@Slf4j
public class Map {

    @Test
    public void map() {
        List<User> userList = Lists.newArrayList(User.builder().id("1").build(), User.builder().id("2").build(),
            User.builder().id("3").build(), User.builder().id("4").build(), User.builder().id("5").build());
        log.info("before: {}", userList);

        // 提取User对象中的id值作为一个新集合
        List<String> idList = userList.stream().map(User::getId).collect(Collectors.toList());
        log.info("after: {}", idList);
        Assert.assertEquals(userList.size(), idList.size());
    }
}

