package com.hsjry.java8.stream.base.intermediate.filter;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * 过滤操作符，过滤后只包含满足断言{@link Predicate}的数据
 *
 * @author jinyn22648
 * @version $$Id: Filter.java, v 0.1 2019/1/23 11:38 AM jinyn22648 Exp $$
 */
@Slf4j
public class Filter {

    @Test
    public void filter() {
        List<String> list = Lists.newArrayList("a", "b", null, "c", "d", "a", "e", "f", "c", null);
        log.info("before: {}", list);

        List<String> filteredList = list.stream().filter(Objects::nonNull).collect(Collectors.toList());
        log.info("after: {}", filteredList);

        Assert.assertEquals(list.size() - 2, filteredList.size());
    }

}
