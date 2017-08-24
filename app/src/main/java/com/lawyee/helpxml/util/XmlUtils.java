package com.lawyee.helpxml.util;

import android.content.Context;
import android.content.res.XmlResourceParser;

import com.lawyee.helpxml.R;
import com.lawyee.helpxml.bean.Holidays;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Title: XmlUtils
 * @Package com.laywee.lawyeehelper2.util
 * @Description: 本地XML解析
 * @author: czq
 * @date: 2017/3/17 15:19
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: ${year} www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

public class XmlUtils {
    /**
     * @Description: "获取Xml的中的数据"
     * @param: context
     * @return: ArrayList<Holidays>
     * @throws
     */
    public static  ArrayList<Holidays> getData(Context context) {
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat( "yyyy/MM/dd ");
        ArrayList<Holidays> list = new ArrayList<Holidays>();
        XmlResourceParser xrp = context.getResources().getXml(R.xml.festival_for_tools);

        try {
            // 直到文档的结尾处
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                // 如果遇到了开始标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    String tagName = xrp.getName();// 获取标签的名字
                    if (tagName.equals("item")) {
                        Holidays  course= new Holidays();
                        course.setYear( Integer.parseInt(xrp.getAttributeValue(null, "year")) );// 通过属性名来获取属性值

                        String str=xrp.getAttributeValue(null, "date");// 通过属性名来获取属性值
                            course.setDate(str);
                         course.setAttribute( xrp.getAttributeValue(null, "Attribute"));// 通过属性名来获取属性值
                        //再处理course的子节点
                        list.add(course);
                    }
                }
                xrp.next();// 获取解析下一个事件
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
