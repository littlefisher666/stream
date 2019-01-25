package com.hsjry.java8.stream.base.intermediate.distinct;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * 去重，要求集合中的对象有实现equals方法，是通过该方法判断对象是否相同的
 *
 * @author jinyn22648
 * @version $$Id: Distinct.java, v 0.1 2019/1/23 11:30 AM jinyn22648 Exp $$
 */
@Slf4j
public class Distinct {

    @Test
    public void distinct() {

        List<String> list = Lists.newArrayList("a", "b", "c", "d", "a", "e", "f", "c");
        log.info("before: {}", list);
        // 去重后的集合
        List<String> distinctList = list.stream().distinct()
            // 该操作符为终止操作符，其他包内会介绍，该操作符用于生成一个集合
            .collect(Collectors.toList());
        log.info("after: {}", distinctList);
        // 集合中一共有2个重复
        Assert.assertEquals(list.size() - 2, distinctList.size());
    }
}
