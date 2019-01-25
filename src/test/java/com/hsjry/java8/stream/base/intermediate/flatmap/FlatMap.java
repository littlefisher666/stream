package com.hsjry.java8.stream.base.intermediate.flatmap;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

/**
 * 混合了map + flatten的功能，它将映射后的流的元素全部放入到一个新的流中
 *
 * @author jinyn22648
 * @version $$Id: FlatMap.java, v 0.1 2019/1/23 11:42 AM jinyn22648 Exp $$
 */
@Slf4j
public class FlatMap {

    @Test
    public void flatMap() {
        Map<String, List<String>> map = Maps.newHashMap();
        map.put("key1", Lists.newArrayList("a", "b", "c", "d"));
        map.put("key2", Lists.newArrayList("d", "e", "f", "g"));
        log.info("before: {}", map);

        // 把map中value的2个List集合进行合并组成新的流
        List<String> flatMappedList = map.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        log.info("after: {}", flatMappedList);
        Assert.assertEquals(map.get("key1").size() + map.get("key2").size(), flatMappedList.size());
    }
}
