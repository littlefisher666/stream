package com.hsjry.java8.stream.base.terminal.reduce;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * 对流中的元素进行叠加计算
 * {@link Stream#reduce(Object, BinaryOperator)}
 * {@link Stream#reduce(BinaryOperator)}
 * {@link Stream#reduce(Object, BiFunction, BinaryOperator)}
 *
 * @author jinyn22648
 * @version $$Id: Reduce.java, v 0.1 2019/1/23 3:13 PM jinyn22648 Exp $$
 */
@Slf4j
public class Reduce {

    @Test
    public void reduceWithOneParameter() {
        List<Integer> numbers = Lists.newArrayList(1, 2, 3, 4, 5);
        log.info("before: {}", numbers);
        // 累加
        Integer sum = numbers.stream().reduce(
            // 该lambda表达式有两个入参，第一个入参是上次操作的结果，第二个参数是流中当前对象数据
            Integer::sum).orElse(null);
        log.info("sum: {}", sum);
        Assert.assertTrue(sum != null && sum == 15);
    }

    @Test
    public void reduceWithTwoParameters() {
        List<Integer> numbers = Lists.newArrayList(1, 2, 3, 4, 5);
        log.info("before: {}", numbers);
        // 累加
        Integer sum = numbers.stream().reduce(
            // 该入参定义了流中初始化值为10，而如果未定义该参数，则默认使用第一个参数当初始化值
            10,
            // 该lambda表达式有两个入参，第一个入参是上次操作的结果，第二个参数是流中当前对象数据
            Integer::sum);
        log.info("sum: {}", sum);
        Assert.assertTrue(sum != null && sum == 25);
    }

    @Test
    public void reduceWithThreeParameters() {
        // 不建议使用，该方法仅针对于并行流会有作用
    }
}
