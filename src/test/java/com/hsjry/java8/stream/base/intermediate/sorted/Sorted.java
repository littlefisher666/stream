package com.hsjry.java8.stream.base.intermediate.sorted;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.hsjry.java8.stream.model.User;
import com.hsjry.java8.stream.model.UserWithComparator;

import lombok.extern.slf4j.Slf4j;

/**
 * {@link Stream#sorted()}将流中的元素按照自然排序方式进行排序，如果元素没实现{@link Comparable}接口，则会抛{@link ClassCastException}异常
 * {@link Stream#sorted(Comparator)}将流原的元素按照给的比较器进行排序
 *
 * @author jinyn22648
 * @version $$Id: Sorted.java, v 0.1 2019/1/23 2:11 PM jinyn22648 Exp $$
 */
@Slf4j
public class Sorted {

    @Test(expected = ClassCastException.class)
    public void sortedThrowException() {
        List<User> userList = Lists.newArrayList(User.builder().id("1").build(), User.builder().id("2").build(),
            User.builder().id("3").build(), User.builder().id("4").build(), User.builder().id("5").build());
        log.info("before: {}", userList);

        List<User> userListThrowException = userList.stream().sorted().collect(Collectors.toList());
        log.info("after: {}", userListThrowException);
    }

    @Test
    public void sorted() {
        List<String> list = Lists.newArrayList("a", "b", "c", "d", "a", "e", "f", "c");
        log.info("before: {}", list);
        List<String> afterList = list.stream().sorted().collect(Collectors.toList());
        log.info("after: {}", afterList);
        Assert.assertEquals(afterList, Lists.newArrayList("a", "a", "b", "c", "c", "d", "e", "f"));
    }

    @Test
    public void soredWithComparator() {
        List<UserWithComparator> userList = Lists.newArrayList(UserWithComparator.builder().id("1").build(),
            UserWithComparator.builder().build(), UserWithComparator.builder().id("4").build(),
            UserWithComparator.builder().id("3").build(), UserWithComparator.builder().id("2").build());
        log.info("before: {}", userList);
        List<UserWithComparator> afterUserList = userList.stream().sorted().collect(Collectors.toList());
        log.info("after: {}", afterUserList);
        Assert.assertEquals(userList.size(), afterUserList.size());

    }

    @Test
    public void sortedByComparator() {
        List<User> userList = Lists.newArrayList(User.builder().id("1").build(), User.builder().id("5").build(),
            User.builder().id("4").build(), User.builder().id("3").build(), User.builder().id("2").build());
        log.info("before: {}", userList);

        List<User> afterUserList = userList.stream().sorted(Comparator.comparing(User::getId)).collect(
            Collectors.toList());
        log.info("after: {}", afterUserList);
        Assert.assertEquals(userList.size(), afterUserList.size());
    }
}
