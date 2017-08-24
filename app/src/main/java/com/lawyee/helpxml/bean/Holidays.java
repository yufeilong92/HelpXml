package com.lawyee.helpxml.bean;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Title: Holidays
 * @Package com.laywee.lawyeehelper2.bean
 * @Description: 节假日的bean
 * @author: czq
 * @date: 2017/3/17 14:51
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

public class Holidays {
    private String festival;
    private int year;
    private String  date;
    private String attribute;

    public Holidays() {
    }

    public Holidays(String festival, int year, String date, String attribute) {
        this.festival = festival;
        this.year = year;
        this.date = date;
        this.attribute = attribute;
    }

    public String getFestival() {
        return festival;
    }

    public void setFestival(String festival) {
        this.festival = festival;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
