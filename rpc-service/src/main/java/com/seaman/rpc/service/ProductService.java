package com.seaman.rpc.service;

import com.seaman.rpc.api.IProductService;
import com.seaman.rpc.api.bean.Product;

/**
 * 版权：    上海云砺信息科技有限公司
 * 创建者:   wangqiuhua
 * 创建时间:  2019-01-22 14:26
 * 功能描述:
 * 修改历史:
 */
public class ProductService implements IProductService {

    @Override
    public Product getById(Long id) {

        Product product = new Product();
        product.setId(id);
        product.setName("产品名称");
        product.setPrice(10F);

        return product;
    }
}
