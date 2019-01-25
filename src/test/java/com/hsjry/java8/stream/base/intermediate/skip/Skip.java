package com.hsjry.java8.stream.base.intermediate.skip;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * 返回丢弃了前n个元素的流，如果流中的元素小于或者等于n，则返回空的流。
 *
 * @author jinyn22648
 * @version $$Id: Skip.java, v 0.1 2019/1/23 2:08 PM jinyn22648 Exp $$
 */
@Slf4j
public class Skip {

    @Test
    public void skip() {
        List<String> list = Lists.newArrayList("a", "b", "c", "d", "a", "e", "f", "c");
        log.info("before: {}", list);

        // 过滤掉前面3个元素
        List<String> skippedList = list.stream().skip(3).collect(Collectors.toList());
        log.info("after: {}", skippedList);
        Assert.assertEquals(list.size() - 3, skippedList.size());
    }
}
