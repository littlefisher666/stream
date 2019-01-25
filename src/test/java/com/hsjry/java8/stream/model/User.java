package com.hsjry.java8.stream.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author jinyn22648
 * @version $$Id: User.java, v 0.1 2019/1/23 2:03 PM jinyn22648 Exp $$
 */
@Data
@Builder
public class User {

    private String id;

    private String name;
}
