package com.hsjry.java8.stream.base.intermediate.peek;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.hsjry.java8.stream.model.User;

import lombok.extern.slf4j.Slf4j;

/**
 * 使用一个Consumer消费流中的元素
 * 使用该操作符不会对原有的流有任何的影响，仅是相当于for循环的时候做一下跟当前循环无关的事情，例如日志打印
 *
 * @author jinyn22648
 * @version $$Id: Peek.java, v 0.1 2019/1/23 1:55 PM jinyn22648 Exp $$
 */
@Slf4j
public class Peek {

    @Test
    public void peek() {

        List<User> userList = Lists.newArrayList(User.builder().id("1").build(), User.builder().id("2").build(),
            User.builder().id("3").build(), User.builder().id("4").build(), User.builder().id("5").build());
        log.info("before: {}", userList);
        List<String> idList = Lists.newArrayList();
        // 循环时，把id塞到一个新的集合，但是流没有任何影响
        List<User> afterList = userList.stream().peek(input -> idList.add(input.getId())).collect(Collectors.toList());

        log.info("idList: {}", idList);
        log.info("after: {}", afterList);
        Assert.assertEquals(userList.size(), idList.size());
        Assert.assertEquals(userList.size(), afterList.size());
        Assert.assertEquals(userList, afterList);
    }
}
