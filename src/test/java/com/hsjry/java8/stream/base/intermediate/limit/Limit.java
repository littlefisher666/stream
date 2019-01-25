package com.hsjry.java8.stream.base.intermediate.limit;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * 保留流中的前n个数据
 *
 * @author jinyn22648
 * @version $$Id: Limit.java, v 0.1 2019/1/23 1:28 PM jinyn22648 Exp $$
 */
@Slf4j
public class Limit {

    @Test
    public void limit() {
        List<String> list = Lists.newArrayList("a", "b", "c", "d", "a", "e", "f", "c");
        log.info("before: {}", list);
        List<String> limitedList = list.stream().limit(3).collect(Collectors.toList());
        log.info("after: {}", limitedList);
        Assert.assertEquals(3, limitedList.size());
    }
}
