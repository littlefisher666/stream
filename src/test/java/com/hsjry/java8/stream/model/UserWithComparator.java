package com.hsjry.java8.stream.model;

import javax.annotation.Nonnull;

import lombok.Builder;
import lombok.Data;

/**
 * @author jinyn22648
 * @version $$Id: UserWithComparator.java, v 0.1 2019/1/23 2:31 PM jinyn22648 Exp $$
 */
@Data
@Builder
public class UserWithComparator implements Comparable<UserWithComparator> {

    private String id;

    @Override
    public int compareTo(@Nonnull UserWithComparator o) {
        if (this.id == null && o.id == null) {
            return 0;
        } else if (this.id == null) {
            return 1;
        } else if (o.id == null) {
            return -1;
        } else {
            return this.id.compareTo(o.id);
        }
    }
}
