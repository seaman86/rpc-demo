package com.seaman.rpc.api;

import com.seaman.rpc.api.bean.Product;

/**
 * 版权：    上海云砺信息科技有限公司
 * 创建者:   wangqiuhua
 * 创建时间:  2019-01-22 13:42
 * 功能描述:
 * 修改历史:
 */

public interface IProductService {
    Product getById(Long id);
}
