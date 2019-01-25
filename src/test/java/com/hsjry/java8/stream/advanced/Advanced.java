package com.hsjry.java8.stream.advanced;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.hsjry.java8.stream.model.RepayPlan;
import com.hsjry.java8.stream.model.User;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jinyn22648
 * @version $$Id: Advanced.java, v 0.1 2019/1/25 4:03 PM jinyn22648 Exp $$
 */
@Slf4j
public class Advanced {

    /**
     * 经常有需求，入参是一个List<对象>，要求转出一个list，其中存的是这个对象的主键，并且做去重去空操作
     */
    @Test
    public void getId() {
        List<User> userList = Lists.newArrayList(User.builder().id("1").name("name1").build(), User.builder().id("2")
            .name("name2").build(), User.builder().id("3").name("name3").build(), User.builder().id("4").name("name4")
            .build(), User.builder().id("5").name("name5").build(), User.builder().id("1").name("name6").build());
        log.info("before: {}", userList);

        List<String> idList = userList.stream().map(User::getId).filter(StringUtils::isNotBlank).distinct().collect(
            Collectors.toList());
        log.info("after: {}", idList);
    }

    /**
     * 计算还款计划中已还款本金
     */
    @Test
    public void sum() {
        List<RepayPlan> repayPlanList = Lists.newArrayList(RepayPlan.builder().leftRepayPrincipal(BigDecimal.TEN)
                .repayPlanStatus(RepayPlan.EnumRepayPlanStatus.REPAY).build(), RepayPlan.builder().leftRepayPrincipal(
            BigDecimal.TEN).repayPlanStatus(RepayPlan.EnumRepayPlanStatus.UNREPAY).build(),
            RepayPlan.builder().leftRepayPrincipal(BigDecimal.TEN)
                .repayPlanStatus(RepayPlan.EnumRepayPlanStatus.UNREPAY).build(), RepayPlan.builder().leftRepayPrincipal(
                BigDecimal.TEN).repayPlanStatus(RepayPlan.EnumRepayPlanStatus.UNREPAY).build(),
            RepayPlan.builder().leftRepayPrincipal(BigDecimal.TEN)
                .repayPlanStatus(RepayPlan.EnumRepayPlanStatus.UNREPAY).build(), RepayPlan.builder().leftRepayPrincipal(
                BigDecimal.TEN).repayPlanStatus(RepayPlan.EnumRepayPlanStatus.UNREPAY).build());

        log.info("before: {}", repayPlanList);

        BigDecimal allLeftRepayPrincipal = repayPlanList.stream().filter(
            input -> RepayPlan.EnumRepayPlanStatus.UNREPAY.equals(input.getRepayPlanStatus())).map(
            RepayPlan::getLeftRepayPrincipal).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        log.info("after: {}", allLeftRepayPrincipal);
    }

    /**
     * 经常会遇到把一个对象，按照主键作为key，对象为value，转出一个map，用于后面使用map.get方法来获取参数
     */
    @Test
    public void toMap() {

        List<User> userList = Lists.newArrayList(User.builder().id("1").name("name1").build(), User.builder().id("2")
            .name("name2").build(), User.builder().id("3").name("name3").build(), User.builder().id("4").name("name4")
            .build(), User.builder().id("5").name("name5").build(), User.builder().id("1").name("name6").build());
        log.info("before: {}", userList);

        Map<String, User> userMap = userList.stream().collect(
            Collectors.toMap(User::getId, input -> input, (o1, o2) -> o1));
        log.info("after: {}", userMap);

    }
}
