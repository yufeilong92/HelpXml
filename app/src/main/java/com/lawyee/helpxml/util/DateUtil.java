package com.lawyee.helpxml.util;

import android.content.Context;

import com.lawyee.helpxml.bean.Holidays;
import com.lawyee.helpxml.model.HoliDaySingleton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Title: DateUtil
 * @Package com.laywee.lawyeehelper2.util
 * @Description: 日期计算工具类
 * @date: 2017/3/17 10:40
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: ${year} www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

public class DateUtil {

    /**
     * @Description: (获取两段时间的间隔天数)
     * @param:  startDate （开始日期）
     * @param:  endDate （结束日期）
     * @return: 间隔天数
     * @throws
     */
    public static int getDateDiff(Date startDate,Date endDate){
        int days = (int) ((endDate.getTime() - startDate.getTime()) / (1000*3600*24));
        return days;
    }
    /**

     * 计算两个日期之间的工作日数
     * @param {} d1
     * @param {} d2
     * @return {} days
     */
    public static int intervalBetweenDateWorkDays(Date startDate,Date endDate,Context context){
        if(startDate.getTime()>endDate.getTime()){
            return 0-intervalBetweenDateWorkDays  (endDate,startDate,context);
        }
        int start=dateToCalendar(startDate).get(Calendar.YEAR);
        int end=dateToCalendar(endDate).get(Calendar.YEAR);
        Map<String ,String> map=getHolidayMap(context,start,end);
        int workdays=0;//工作天数
        int  totalDays = (int) ((endDate.getTime()-startDate.getTime())/(1000*60*60*24));
        Date temDate =  endDate;
        for(int i=1;i<=totalDays;i++){
            temDate = addDate(4,i,startDate);
                int  week =dateToCalendar(temDate).get(Calendar.DAY_OF_WEEK)-1;//星期
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
                String str=sdf.format(temDate);
                String  obj = map.get(str);
                //1=休息日；0=工作日
                if(obj!=null)
                {
                    if(obj.equals("0"))
                        workdays +=1;
                    else
                    continue;
            }
            if(week<6 && week>0)//周一至周五
                workdays +=1;
        }
        return workdays;
    }
    /**
     * @Description: 计算间隔工作日
     * @param: date （起始日期）
     *  @param: days（间隔天数）
     * @return: 计算完成的date
     * @throws
     */
    public static Date intervalDaysWorkDay(Date date, int days, Context context){
        Date startDate=new Date(date.getTime());
        Date endDate=new Date(date.getTime());
        Date tempDate=new Date(date.getTime());
        tempDate.setTime(startDate.getTime()+days*(1000*60*60*24));
        int sum=0;
        Map<String ,String> map=getHolidayMap(context,dateToCalendar(startDate).get(Calendar.YEAR),dateToCalendar(tempDate).get(Calendar.YEAR));
        while (sum!=days){
            endDate.setTime(endDate.getTime()+1000*60*60*24);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
            String str=sdf.format(endDate);
            String obj=map.get(str);
            if(obj!=null){
                if(obj.equals("0")) {
                    sum+=1;
                }else{
                    continue;
                }
            }
            int week=dateToCalendar(endDate).get(Calendar.DAY_OF_WEEK)-1;
            if(week<6&&week>0){
                sum+=1;
            }
        }
        return endDate;
    }
    /**
     * @Description: 计算日期
     * @param: et_mouth（月数Texiview）
     * @param: et_day（日数Texiview）
     * @param: position （0：正常日计算 1：工作日计算）
     * @param: date （起始日期）
     * @return: 计算完成的String
     * @throws
     */
    public  static String getEndDate(Context context,String et_mouth, String et_day, int position,Date date){
        int month_num=0;
        int day_num=0;
        if(et_mouth!=null&&!et_mouth.equals("")&&!et_mouth.equals("-")){
            month_num=Integer.parseInt(et_mouth);
        }
        if(et_day!=null&&!et_day.equals("")&&!et_day.equals("-")){
            day_num=Integer.parseInt(et_day);
        }
        Date nowtime = new Date(date.getTime());
        if(position==1){
            if(day_num>=0){
                nowtime=intervalDaysWorkDay(date,day_num,context);
            }
        }else{
            nowtime=addDate(5,month_num,date);
            nowtime=addDate(4,day_num,nowtime);

        }
        DateFormat format=new SimpleDateFormat("yyyy/MM/dd");
        String time=format.format(nowtime);
        return time;

    }
    /**
     * @Description: 看这一天是不是节假日,选出之后的第一个工作日
     * @param: date （所要判断的日期）
     * @return: 获得的第一个工作日
     * @throws
     */
    public static Date judgeHoliday(Date date,Context context){
        boolean isNoHliday=true;
        Date temDate = null;
        int x=0;
        Map<String ,String> map=getHolidayMap(context,dateToCalendar(date).get(Calendar.YEAR),dateToCalendar(date).get(Calendar.YEAR));
        while(isNoHliday){
            temDate = addDate(4,x,date);
            int  week =dateToCalendar(temDate).get(Calendar.DAY_OF_WEEK)-1;//星期
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
            String str=sdf.format(temDate);
            String  obj = map.get(str);
            //1=休息日；0=工作日
            if(week<6 && week>0)//周一至周五
            {
                if(obj==null)
                {
                    isNoHliday=false;
                }
            }
            x++;
        }
        return temDate;


    }

    //日期添加运算
    public static Date addDate(int type, int numDay, Date dtDate)
    {
        Calendar c=Calendar.getInstance();
        c.clear();
        c.setTime(dtDate);
        int year =c.get(Calendar.YEAR);
        int month =c.get(Calendar.MONTH);
        int day =c.get(Calendar.DAY_OF_MONTH);
        int hours =c.get(Calendar.HOUR_OF_DAY);
        int  minutes=c.get(Calendar.MINUTE);
        int seconds =c.get(Calendar.SECOND);

        switch (type)
        {
            case 6 :// 年
               c.set(Calendar.YEAR,year+numDay);
                break;
            case 7 :// 季度
                c.set(Calendar.MONTH,month+3*numDay);
                break;
            case 5 :// 月
                c.set(Calendar.MONTH,month+numDay);
                break;
            case 4 :// 天
                c.set(Calendar.DAY_OF_MONTH,day+numDay);
                break;
            case 3 :// 时
                c.set(Calendar.HOUR_OF_DAY,hours+numDay);
                break;
            case 2 :// 分
                c.set(Calendar.MINUTE,minutes+numDay);
                break;
            case 1 :// 秒
                c.set(Calendar.SECOND,seconds+numDay);
                break;
            default :
                break;
        }
        return c.getTime();
    }
    //获取一个以日期为key的节假日的map
    public static Map<String ,String> getHolidayMap(Context context,int startYear,int endYear){
        List<Holidays> holidayses= HoliDaySingleton.getInstance(XmlUtils.getData(context)).getList();
        Map<String,String> holidayMap=new HashMap<String, String>() ;
        for(int i = 0; i <holidayses.size() ; i++) {
            if(holidayses.get(i).getYear()>=startYear&&holidayses.get(i).getYear()<=endYear) {
                holidayMap.put(holidayses.get(i).getDate(), holidayses.get(i).getAttribute());
            }
        }
        return holidayMap;
    }
    /**
     * @Description: String 转Date
     * @return:
     * @throws
     */
    public static Date calculateDate(String str) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");//小写的mm表示的是分钟
        Date date =new Date();
        try {
            date=sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    //日期转日历类
    public static Calendar dateToCalendar(Date date){
        Calendar c=Calendar.getInstance();
        c.clear();
        c.setTime(date);
        return c;

    }
}
