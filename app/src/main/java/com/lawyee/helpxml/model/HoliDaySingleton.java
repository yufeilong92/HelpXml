package com.lawyee.helpxml.model;


import com.lawyee.helpxml.bean.Holidays;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Package com.laywee.lawyeehelper2.model
 * @Description: $
 * @author: Uustrong
 * @date: 2017/3/21 17:51
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: ${year} www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

    //懒汉式单例类.在第一次调用的时候实例化自己
    public class HoliDaySingleton {
    public List<Holidays> getList() {
        return list;
    }

    private List<Holidays> list  =new ArrayList<>();
        private HoliDaySingleton(List<Holidays> list) {
            this.list=list;
        }
        private static HoliDaySingleton single=null;
        //静态工厂方法
        public static synchronized HoliDaySingleton getInstance(List<Holidays> list) {
            if (single == null) {
                single = new HoliDaySingleton(list);
            }
            return single;
        }
}
