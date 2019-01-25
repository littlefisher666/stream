package com.hsjry.java8.stream.base.create;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * 流的初始化方式，最常用的还是集合.stream()
 *
 * @author jinyn22648
 * @version $$Id: Create.java, v 0.1 2019/1/23 11:19 AM jinyn22648 Exp $$
 */
public class Create {

    @Test
    public void of() throws IOException {

        // 多个单独对象进行合并为一个流
        Stream<String> stream1 = Stream.of("a", "b", "c");
        // 多个集合合并为一个流
        Stream<String> stream2 = Stream.of(Lists.newArrayList("a", "b", "c"), Lists.newArrayList("d", "e", "f"))
            // 把集合的流转为对象的流
            .flatMap(Collection::stream);
        // 随机数的流
        Stream<Double> stream3 = Stream.generate(Math::random);
        // 从数组中创建流
        Stream<String> stream4 = Arrays.stream(new String[] {"a", "b", "c"});
        // 使用jdk8提供的Files.list方法获取流，同样的还有Files.find和Files.walk
        Stream<Path> stream5 = Files.list(new File("/").toPath());
        // 使用集合.stream()创建流，该种方法是最为常见创建流的方式
        Stream<String> stream6 = Lists.newArrayList("a", "b", "c").stream();
    }
}
