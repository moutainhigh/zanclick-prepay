package com.zanclick.prepay.authorize;

import com.zanclick.prepay.authorize.entity.AuthorizeConfiguration;
import com.zanclick.prepay.authorize.entity.AuthorizeRefundOrder;
import com.zanclick.prepay.authorize.entity.SupplyChainTrade;
import com.zanclick.prepay.common.generator.CodeGenerator;
import com.zanclick.prepay.authorize.entity.SupplyChainBill;

/**
 * @author lvlu
 * @date 2019-08-29 15:13
 **/
public class Main {
    public static void main(String[] args) {
        String basePack = Main.class.getPackage().getName();
        CodeGenerator generator = new CodeGenerator();
        generator.generateMybatisXml(basePack, AuthorizeRefundOrder.class);
//        generator.generateDao(basePack,SupplyChainTrade.class);
//        generator.generateService(basePack,SupplyChainTrade.class);
    }
}
