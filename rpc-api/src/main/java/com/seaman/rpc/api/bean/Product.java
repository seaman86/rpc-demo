package com.seaman.rpc.api.bean;

import java.io.Serializable;

/**
 * 版权：    上海云砺信息科技有限公司
 * 创建者:   wangqiuhua
 * 创建时间:  2019-01-22 13:41
 * 功能描述:
 * 修改历史:
 */

public class Product implements Serializable {
    private Long id;
    private String Name;
    private float price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", price=" + price +
                '}';
    }
}
