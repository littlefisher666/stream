package com.hsjry.java8.stream.base.terminal.min;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * {@link Stream#min(Comparator)}返回流中的最小值，不限于数值类型，也可以是其他类型，只要提供{@link Comparator}比较器
 *
 * @author jinyn22648
 * @version $$Id: Min.java, v 0.1 2019/1/23 3:12 PM jinyn22648 Exp $$
 */
@Slf4j
public class Min {

    @Test
    public void min() {
        List<String> list = Lists.newArrayList("a", "b", "c", "d", "a", "e", "f", "c");
        log.info("before: {}", list);
        String min = list.stream().min(String::compareTo).orElse(null);
        log.info("min: {}", min);
        Assert.assertEquals("a", min);
    }
}
