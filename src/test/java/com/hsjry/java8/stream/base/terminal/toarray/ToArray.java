package com.hsjry.java8.stream.base.terminal.toarray;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Stream;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.hsjry.java8.stream.model.User;

import lombok.extern.slf4j.Slf4j;

/**
 * 将一个流转换成数组
 * {@link Stream#toArray()} 转成Object[]数组
 * {@link Stream#toArray(IntFunction)} 转成T[]数据，有泛型概念
 *
 * @author jinyn22648
 * @version $$Id: ToArray.java, v 0.1 2019/1/25 2:43 PM jinyn22648 Exp $$
 */
@Slf4j
public class ToArray {

    @Test
    public void toArray() {
        List<User> userList = Lists.newArrayList(User.builder().id("1").build(), User.builder().id("2").build(),
            User.builder().id("3").build(), User.builder().id("4").build(), User.builder().id("5").build());
        log.info("before: {}", userList);
        // toArray不带参数时，无法使用具体类型，返回仅能是Object
        Object[] arr = userList.stream().map(User::getId).toArray();
        Arrays.stream(arr).forEach(input -> log.info("after: {}", input));

        log.info("对数组打印日志，不允许直接把数组当参数来打印，否则只会打印数组中的第一个: {}", arr);
    }

    @Test
    public void toArrayWithOneParameter() {
        List<User> userList = Lists.newArrayList(User.builder().id("1").build(), User.builder().id("2").build(),
            User.builder().id("3").build(), User.builder().id("4").build(), User.builder().id("5").build());
        log.info("before: {}", userList);

        // toArray方法的参数，是泛型定义，无需定义数组的数量
        String[] arr = userList.stream().map(User::getId).toArray(String[]::new);
        Arrays.stream(arr).forEach(input -> log.info("after: {}", input));

        log.info("对数组打印日志，不允许直接把数组当参数来打印，否则只会打印数组中的第一个: {}", arr);

    }

}
