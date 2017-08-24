package com.lawyee.helpxml.util;

import android.text.TextUtils;
import android.util.Log;

import static android.content.ContentValues.TAG;
import static com.lawyee.helpxml.util.typeUtil.getStringType;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: CaseUtil
 * @Package com.laywee.lawyeehelper2.util
 * @Description: 案件类型计算公式
 * @author: YFL
 * @date: 2017/3/24 11:18
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017/3/24 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */


public class CaseUtil {
    /**
     * @param str 财产案件
     * @return 计算金额
     */
    public String property_calculation(String str) {
//         判断逻辑 是否为空 否 判断金额
        String result = "";
        if (!TextUtils.isEmpty(str)) {
            int number = typeUtil.getIntegerType(str);
            if (0 < number && number <= 10000) {
                result = getStringType(50);
            } else if (10000 < number && number <= 100000) {
                result = getStringType((number - 10000) * 0.025 + 50);
            } else if (100000 < number && number <= 200000) {
                result = getStringType((number - 100000) * 0.02 + 2300);
            } else if (200000 < number && number <= 500000) {
                result = getStringType((number - 200000) * 0.015 + 4300);
            } else if (500000 < number && number <= 1000000) {
                result = getStringType((number - 500000) * 0.01 + 8800);
            } else if (1000000 < number && number <= 2000000) {
                result = getStringType((number - 1000000) * 0.009 + 13800);
            } else if (2000000 < number && number <= 5000000) {
                result = getStringType((number - 2000000) * 0.008 + 22800);
            } else if (5000000 < number && number <= 10000000) {
                result = getStringType((number - 5000000) * 0.007 + 46800);
            } else if (10000000 < number && number <= 20000000) {
                result = getStringType((number - 10000000) * 0.006 + 81800);
            } else if (20000000 < number) {
                result = getStringType((number - 20000000) * 0.005 + 141800);
            }
        }
        return result;
    }

    /**
     * 获取dobule 类型
     *
     * @param str
     * @return
     */
    public double propertys_calculation(String str) {
//         判断逻辑 是否为空 否 判断金额
        double result = 0;
        if (!TextUtils.isEmpty(str)) {
            int number = typeUtil.getIntegerType(str);
            if (0 < number && number <= 10000) {
                result = 50.00;
            } else if (10000 < number && number <= 100000) {
                result = ((number - 10000) * 0.025 + 50);
            } else if (100000 < number && number <= 200000) {
                result = ((number - 100000) * 0.02 + 2300);
            } else if (200000 < number && number <= 500000) {
                result = ((number - 200000) * 0.015 + 4300);
            } else if (500000 < number && number <= 1000000) {
                result = ((number - 500000) * 0.01 + 8800);
            } else if (1000000 < number && number <= 2000000) {
                result = ((number - 1000000) * 0.009 + 13800);
            } else if (2000000 < number && number <= 5000000) {
                result = ((number - 2000000) * 0.008 + 22800);
            } else if (5000000 < number && number <= 10000000) {
                result = ((number - 5000000) * 0.007 + 46800);
            } else if (10000000 < number && number <= 20000000) {
                result = ((number - 10000000) * 0.006 + 81800);
            } else if (20000000 < number) {
                result = ((number - 20000000) * 0.005 + 141800);
            }
        }
        return result;
    }

    /**
     * 离婚案件（涉及财产分割）
     *
     * @param data 计算金额
     * @return 折算后金额
     */
    public double divorce_property(String data) {
        double result = 0;
        if (!TextUtils.isEmpty(data)) {
            int number = typeUtil.getIntegerType(data);
            if (0 < number && number <= 200000) {
                result = (200.00);
            } else if (number > 200000) {
                result = ((number - 200000) * 0.005 + 200);
            }
        }
        return result;
    }

    /**
     * 侵害人格权（侵害姓名权，设计损害赔偿）
     *
     * @param data 原金额
     * @return 折算后金额
     */
    public double name_damage_compensate(String data) {
        double result = 0;
        if (!TextUtils.isEmpty(data)) {
            int number = typeUtil.getIntegerType(data);
            if (0 < number && number < 50000) {
                result = (300.00);
            } else if (50000 < number && number <= 100000) {
                result = ((number - 50000) * 0.01 + 300);
            } else if (100000 < number) {
                result = ((number - 100000) * 0.005 + 800);
            }
        }
        return result;
    }

    /**
     * 知识产权民事案件（有争议全额或者价额）
     *
     * @param data
     * @return
     */
    public double controversial_knowledge_property(String data) {
        double result = 0;
        if (!TextUtils.isEmpty(data)) {
//            int number = typeUtil.getIntegerType(data);
//            if (0.0 < number && number <= 10000) {
//                result = typeUtil.getStringType(50.00);
//            } else if (10000 < number && number <= 100000) {
//                result = typeUtil.getStringType((number - 10000) * 0.025 + 50);
//            } else if (100000 < number && number <= 200000) {
//                result = typeUtil.getStringType((number - 100000) * 0.02 + 2300);
//            } else if (200000 < number && number <= 500000) {
//                result = typeUtil.getStringType((number - 200000) * 0.015 + 4300);
//            } else if (500000 < number && number <= 1000000) {
//                result = typeUtil.getStringType((number - 500000) * 0.01 + 8800);
//            } else if (1000000 < number && number < 2000000) {
//                result = typeUtil.getStringType((number - 1000000) * 0.009 + 13800);
//            } else if (2000000 < number && number < 5000000) {
//                result = typeUtil.getStringType((number - 2000000) * 0.008 + 22800);
//            } else if (5000000 < number && number <= 10000000) {
//                result = typeUtil.getStringType((number - 5000000) * 0.007 + 46800);
//            } else if (10000000 < number && number <= 20000000) {
//                result = typeUtil.getStringType((number - 10000000) * 0.006 + 81800);
//            } else if (20000000 < number) {
//                result = typeUtil.getStringType((number - 20000000) * 0.005 + 141800);
//            }
            result = propertys_calculation(data);

        }
        return result;
    }


    /**
     * 执行申请费用
     *
     * @param data 执行金额
     * @return 折算后的金额
     */
    public double implement_apply_money(String data) {
        double result = 0;
        if (!TextUtils.isEmpty(data)) {
            int number = typeUtil.getIntegerType(data);
            if (0 < number && number <= 10000) {
                result = (50.00);
            } else if (10000 < number && number <= 500000) {
                result = ((number - 10000) * 0.015 + 50);
            } else if (500000 < number && number <= 10000000) {
                result = ((number - 500000) * 0.01 + 7400);
            } else if (10000000 < number) {
                result = ((number - 10000000) * 0.001 + 77400);
            }
        }
        return result;
    }

    /**
     * 诉讼保全申请费
     *
     * @param data 诉讼保全额申请
     * @return 费用
     */
    public double patent_application_fee(String data) {
        double result = 0;
        if (!TextUtils.isEmpty(data)) {
            int number = typeUtil.getIntegerType(data);
            if (0 < number && number <= 1000) {
                result = (30.00);
            } else if (1000 < number && number <= 100000) {
                result = ((number - 1000) * 0.01 + 30);
            } else if (100000 < number) {
                result = ((number - 100000) * 0.005 + 1020);
            }

        }
        if (!(result == 0)) {
            if (result > 5000) {
                result = (5000.00);
            }
        }
        return result;
    }

    /**
     * 申请支付令
     *
     * @param data
     * @return
     */
    public double apply_money(String data) {
        double result = 0;
//        if (!TextUtils.isEmpty(data)) {
//            int number = typeUtil.getIntegerType(data);
//            if (0 < number && number <= 10000) {
//                result = typeUtil.getStringType(50 / 3);
//            } else if (10000 < number && number <= 100000) {
//                result = typeUtil.getStringType(((number - 10000) * 0.025 + 50) / 3);
//            } else if (100000 < number && number <= 200000) {
//                result = typeUtil.getStringType(((number - 100000) * 0.02 + 2300) / 3);
//            } else if (200000 < number && number <= 500000) {
//                result = typeUtil.getStringType(((number - 200000) * 0.015 + 4300) / 3);
//            } else if (500000 < number && number <= 1000000) {
//                result = typeUtil.getStringType(((number - 500000) * 0.01 + 8800) / 3);
//            } else if (1000000 < number && number <= 2000000) {
//                result = typeUtil.getStringType(((number - 1000000) * 0.009 + 13800) / 3);
//            } else if (2000000 < number && number <= 5000000) {
//                result = typeUtil.getStringType(((number - 2000000) * 0.008 + 22800) / 3);
//            } else if (5000000 < number && number <= 10000000) {
//                result = typeUtil.getStringType(((number - 5000000) * 0.007 + 46800) / 3);
//            } else if (10000000 < number && number <= 20000000) {
//                result = typeUtil.getStringType(((number - 10000000) * 0.006 + 81800) / 3);
//            } else if (20000000 < number) {
//                result = typeUtil.getStringType(((number - 20000000) * 0.005 + 141800) / 3);
//            }
//        }
        double v = propertys_calculation(data);
        result = (v / 3);
        return result;
    }

    /**
     * 破产案件
     *
     * @param data 财产
     * @return 计算后的财产
     */
    public double bankruptcy_case(String data) {
        double result = 0;
        if (!TextUtils.isEmpty(data)) {
            double v = propertys_calculation(data);
            result = (v / 2);
        }
        if (!(result==0)) {

            if (result > 300000) {
                result=300000.00;
            }
        }
        return result;
    }

    /**
     * 减半金额计算
     *
     * @param data 原金额
     * @return 减半金额
     */
    public String halve_money(double data) {
        String result = "";
        if (data != 0) {
            result = getStringType(data / 2);
            Log.d(TAG, "===: " + result);
        }
        return result;
    }


}
