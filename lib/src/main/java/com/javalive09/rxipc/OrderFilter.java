package com.javalive09.rxipc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by peter on 2018/12/17
 */
public class OrderFilter {

    private List<String> orders = new ArrayList<>();

    public OrderFilter(String... orders) {
        add(orders);
    }

    public void add(String... orders) {
        this.orders.addAll(Arrays.asList(orders));
    }

    public boolean contain(String order) {
        return orders.contains(order);
    }

    public void checkOrder(Collection<OrderFilter> orderFilterCollection) throws Exception {
        for (OrderFilter filter : orderFilterCollection) {
            for (String order : orders) {
                if (filter.contain(order)) {
                    throw new Exception("you already have order :" + order + " , keep order atomic!");
                }
            }
        }
    }

    @Override
    public String toString() {
        return "OrderFilter{" +
                "orders=" + orders +
                '}';
    }

}
