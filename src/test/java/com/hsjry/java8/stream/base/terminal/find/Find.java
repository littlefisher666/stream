package com.hsjry.java8.stream.base.terminal.find;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * {@link Stream#findAny()}返回任意一个元素，如果流为空，返回空的{@link Optional}，对于并行流来说，它只需要返回任意一个元素即可，所以性能可能要好于{@link Stream#findFirst()}，但是有可能多次执行的时候返回的结果不一样。
 * {@link Stream#findFirst()}返回第一个元素，如果流为空，返回空的{@link Optional}。
 * 除非数据量特别大， 否则不建议使用并行流
 *
 * @author jinyn22648
 * @version $$Id: Find.java, v 0.1 2019/1/23 2:39 PM jinyn22648 Exp $$
 */
@Slf4j
public class Find {

    @Test
    public void findAny() {
        List<String> list = Lists.newArrayList("a", "b", "c", "d", "a", "e", "f", "c");
        log.info("before: {}", list);

        // 并行流，每次执行的结果可能不同
        String parallelStreamValue = list.parallelStream().findAny().orElse(null);
        log.info("parallelStream after: {}", parallelStreamValue);
        Assert.assertTrue(list.contains(parallelStreamValue));
        // 串行流，每次执行结果都是第一个
        String streamValue = list.stream().findAny().orElse(null);
        log.info("stream after: {}", streamValue);
        Assert.assertEquals(list.get(0), streamValue);
    }

    @Test
    public void findFirst() {
        List<String> list = Lists.newArrayList("a", "b", "c", "d", "a", "e", "f", "c");
        log.info("before: {}", list);
        // 并行流
        String parallelStreamValue = list.parallelStream().findFirst().orElse(null);
        log.info("parallelStream after: {}", parallelStreamValue);
        Assert.assertTrue(list.contains(parallelStreamValue));
        // 串行流
        String streamValue = list.stream().findFirst().orElse(null);
        log.info("stream after: {}", streamValue);
        Assert.assertEquals(list.get(0), streamValue);
    }
}
