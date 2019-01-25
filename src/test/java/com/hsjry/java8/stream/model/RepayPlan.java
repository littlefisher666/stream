package com.hsjry.java8.stream.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

/**
 * @author jinyn22648
 * @version $$Id: RepayPlan.java, v 0.1 2019/1/25 3:53 PM jinyn22648 Exp $$
 */
@Data
@Builder
public class RepayPlan {

    /**
     * 剩余应还本金
     */
    private BigDecimal leftRepayPrincipal;

    /**
     * 还款计划状态，1:待还款
     */
    private EnumRepayPlanStatus repayPlanStatus;

    @Getter
    @AllArgsConstructor
    public enum EnumRepayPlanStatus {
        UNREPAY("1", "待还款"),
        REPAY("3", "已还款"),
        ;

        private String code;

        private String description;
    }
}

