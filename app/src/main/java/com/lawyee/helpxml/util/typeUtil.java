package com.lawyee.helpxml.util;

import android.util.Log;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.ContentValues.TAG;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: typeUtil
 * @Package com.laywee.lawyeehelper2.util
 * @Description: 类型转换工具
 * @author: YFL
 * @date: 2017/3/24 11:18
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017/3/24 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */


public class typeUtil {

    public static String getStringType(int number) {
        return String.valueOf(number);
    }

    public static String getStringType(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(date);
    }

    public static int getIntegerType(String number) {
        double d = Double.parseDouble(number);
        return (int )d;
    }


    public static int getIntegerType(float number) {
        return (int) number;
    }

    /**
     * 四舍五入
     *
     * @param date
     * @return
     */
    public static String getStringType(double date) {
        Log.d(TAG, "四舍五入之前 " + date);
        BigDecimal b = new BigDecimal(date);
        BigDecimal bd = b.setScale(2, BigDecimal.ROUND_HALF_UP);
        Log.d(TAG, "四舍五入之后 " + String.valueOf(bd));
        return String.valueOf(bd);
    }

}
