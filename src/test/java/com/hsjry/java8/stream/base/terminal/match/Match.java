package com.hsjry.java8.stream.base.terminal.match;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * {@link Stream#allMatch(Predicate)}只有在所有的元素都满足断言时才返回{@link Boolean#TRUE}，否则{@link Boolean#FALSE}，流为空时总是返回{@link Boolean#TRUE}
 * {@link Stream#anyMatch(Predicate)}只有在任意一个元素满足断言时就返回{@link Boolean#TRUE}，否则{@link Boolean#FALSE}
 * {@link Stream#noneMatch(Predicate)}只有在所有的元素都不满足断言时才返回{@link Boolean#TRUE}，否则{@link Boolean#FALSE}
 *
 * @author jinyn22648
 * @version $$Id: Match.java, v 0.1 2019/1/23 2:57 PM jinyn22648 Exp $$
 */
@Slf4j
public class Match {

    @Test
    public void allMatchTrue() {
        List<String> list = Lists.newArrayList("a", "b", "c", "d", "a", "e", "f", "c");
        log.info("before: {}", list);
        boolean allMatch = list.stream().allMatch(Objects::nonNull);
        log.info("allMatch: {}", allMatch);
        Assert.assertTrue(allMatch);

    }

    @Test
    public void allMatchFalse() {
        List<String> list = Lists.newArrayList("a", "b", "c", "d", "a", "e", "f", null);
        log.info("before: {}", list);
        boolean allMatch = list.stream().allMatch(Objects::nonNull);
        log.info("allMatch: {}", allMatch);
        Assert.assertFalse(allMatch);
    }

    @Test
    public void anyMatchTrue() {
        List<String> list = Lists.newArrayList("a", "b", "c", "d", "a", "e", "f", null);
        log.info("before: {}", list);
        boolean anyMatch = list.stream().anyMatch(Objects::nonNull);
        log.info("anyMatch: {}", anyMatch);
        Assert.assertTrue(anyMatch);
    }

    @Test
    public void anyMatchFalse() {
        List<String> list = Lists.newArrayList("a", "b", "c", "d", "a", "e", "f", "c");
        log.info("before: {}", list);
        boolean anyMatch = list.stream().anyMatch(Objects::isNull);
        log.info("anyMatch: {}", anyMatch);
        Assert.assertFalse(anyMatch);
    }

    @Test
    public void noneMatchTrue() {
        List<String> list = Lists.newArrayList(null, null, null, null, null, null, null, null);
        log.info("before: {}", list);
        boolean noneMatch = list.stream().noneMatch(Objects::nonNull);
        log.info("noneMatch: {}", noneMatch);
        Assert.assertTrue(noneMatch);
    }

    @Test
    public void noneMatchFalse() {
        List<String> list = Lists.newArrayList("a", null, null, null, null, null, null, null);
        log.info("before: {}", list);
        boolean noneMatch = list.stream().noneMatch(Objects::nonNull);
        log.info("noneMatch: {}", noneMatch);
        Assert.assertFalse(noneMatch);
    }
}
