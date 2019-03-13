package com.hs.blog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * TODO: 日期工具类
 *
 * @author 83998
 * @date 2019/3/12 15:17
 */

public class DateUtils {
    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public final static String[] week = { "", "周日", "周一", "周二", "周三", "周四", "周五", "周六" };

    public final static String[] week2 = { "", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

    public static java.text.SimpleDateFormat SDF_YYMMDD = new java.text.SimpleDateFormat("yyyy-MM-dd");

    public static java.text.SimpleDateFormat SDF_YYYY_MM_DD_HH_MM_SS = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date StringToDate(String dateString, String format) {
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(dateString);
        } catch (Exception e) {
            date = null;
            logger.error(e.getMessage());
        }

        return date;
    }

    public static String StringToDate_YYYY_MM_DD(Date date) {
        String dates = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dates = sdf.format(date);
        } catch (Exception e) {
            date = null;
            logger.error(e.getMessage());
        }

        return dates;

    }

    public static String StringToDate_MM_DD(Date date) {
        String dates = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
            dates = sdf.format(date);
        } catch (Exception e) {
            date = null;
            logger.error(e.getMessage());
        }

        return dates;

    }

    public static Date StringToDate(String dateString) {
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdf.parse(dateString);
        } catch (Exception e) {
            date = null;
            logger.error(e.getMessage());
        }

        return date;
    }

    public static Date StringtoDateHHMM(String dateString){
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            date = sdf.parse(dateString);
        } catch (Exception e) {
            date = null;
            logger.error(e.getMessage());
        }

        return date;
    }

    public static Date StringToDatetpy(String dateString) {
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
            date = sdf.parse(dateString);
            System.out.println(date);
        } catch (Exception e) {
            date = null;
            logger.error(e.getMessage());
        }

        return date;
    }
    public static Date StringToDate_YYYY_MM_DD_HH_MM(String dateString) {
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            date = sdf.parse(dateString);
        } catch (Exception e) {
            date = null;
            logger.error(e.getMessage());
        }

        return date;
    }

    public static Date StringToDate_YYYYMMDDHHMM(String dateString) {
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            date = sdf.parse(dateString);
        } catch (Exception e) {
            date = null;
            logger.error(e.getMessage());
        }

        return date;
    }

    public static Date StringToDate_YY_MM_DD_HH_MM(String dateString) {
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm");
            date = sdf.parse(dateString);
        } catch (Exception e) {
            date = null;
            logger.error(e.getMessage());
        }

        return date;
    }

    public static Date StringToDate_YYYY_MM_DD_HH_MM_SS(String dateString) {
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdf.parse(dateString);
        } catch (Exception e) {
            date = null;
            logger.error(e.getMessage());
        }

        return date;
    }

    public static Date stringToDate(String dateString, String format) {
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(dateString);
        } catch (Exception e) {
            date = null;
            logger.error(e.getMessage());
        }
        return date;
    }

    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date StringToDateShort(String dateString) {
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            date = sdf.parse(dateString);
        } catch (Exception e) {
            date = null;
            logger.error(e.getMessage());
        }

        return date;
    }

    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String getByTimeMillis(long timeMillis) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timeMillis);
        return dateToString(c.getTime());

    }

    public static long getTimeMillis(String dateTime) {
        if (dateTime == null || dateTime.equals("")) {
            return 0l;
        }
        Date date = DateUtils.StringToDate(dateTime);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    public static String getNowDateYYYYMMDD() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    public static String getNowDate(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String getNowDateYYMMDD() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        return sdf.format(date);
    }

    public static String getNowDateYYYYMM(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return sdf.format(date);
    }

    public static String getNowDateYYYY_MM_DD() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String getNowDateYYYY_MM_DD_HH_MM() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(date);
    }

    public static String getNowDateYYYY_MM_DD_HH_MM_SS() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String getNowDateYYYYMMDDHHMMSS() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    public static String getNowDateYYYYMMDDHHMMSSSS() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        return sdf.format(date);
    }

    public static Integer handleIntTime(Date date, String weekDayName) {
        for (int i = 0; i <= 3; i++) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, -i);
            int day = cal.get(Calendar.DAY_OF_WEEK);
            String tempWeek = week[day];
            if (tempWeek.equals(weekDayName))
                return Integer.valueOf(dateToString(cal.getTime(), "yyyyMMdd"));
        }
        return null;
    }

    public static String getWeekStr2(Date date,String[] week) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return week[cal.get(Calendar.DAY_OF_WEEK)];
    }

    public static String getWeekStr(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return week[cal.get(Calendar.DAY_OF_WEEK)];
    }

    /**
     * 补充年份
     * 排除1月份获取同年12月份时间&12月份获取同年1月份时间
     * @param dateStr
     * @param date 参考时间
     * @return
     */
    public static Date strToDateDafueYear(String dateStr,Date date) {
        Date nowDate=new Date();
        String nowYear=DateUtils.dateToString(nowDate,"yyyy");
        Date matchDate = DateUtils.StringToDate(nowYear+"-"+dateStr, "yyyy-MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(matchDate);
        Calendar now = Calendar.getInstance();
        //有参考值使用参考值的年份
        if(date!=null){
            now.setTime(date);
            calendar.set(Calendar.YEAR, now.get(Calendar.YEAR));
        }else{
            //排除1月份获取同年12月份时间&12月份获取同年1月份时间
            if(calendar.getTimeInMillis()<now.getTimeInMillis()&&calendar.get(Calendar.MONTH)==0&&now.get(Calendar.MONTH)==11){
                calendar.set(Calendar.YEAR, now.get(Calendar.YEAR)+1);
            }else if(calendar.getTimeInMillis()>now.getTimeInMillis()&&calendar.get(Calendar.MONTH)==11&&now.get(Calendar.MONTH)==0){
                calendar.set(Calendar.YEAR, now.get(Calendar.YEAR)-1);
            }
        }
        return calendar.getTime();
    }
    public static Date strToDateDafueYear(String dateStr) {
        return strToDateDafueYear(dateStr,null);
    }
    public static Date getDateByOffsetDay(Date date, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, offset);
        return cal.getTime();
    }

    public static boolean isDateBefore(String date2, Date date1) {
        try {
            DateFormat df = DateFormat.getDateTimeInstance();
            return date1.before(df.parse(date2));
        } catch (ParseException e) {
            System.out.print(e.getMessage());
            return false;
        }
    }

    public static boolean isIndexDCDateBefore(Date date){
        try {
            Calendar c = Calendar.getInstance();
            GregorianCalendar ca = new GregorianCalendar();

            if(ca.get(GregorianCalendar.AM_PM) == 1)//判断上下午时间
                c.set(Calendar.HOUR, -2);
            else
                c.set(Calendar.HOUR, +10);

            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MINUTE, 0);

            Date afterTime=c.getTime();//当天10时后
            return afterTime.before(date);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return false;
        }
    }

    public static boolean isIndexDCDateAfter(Date date){
        try {
            Calendar c = Calendar.getInstance();
            GregorianCalendar ca = new GregorianCalendar();

            if(ca.get(GregorianCalendar.AM_PM) == 1)//判断上下午时间
                c.set(Calendar.HOUR, +22);
            else
                c.set(Calendar.HOUR, +34);

            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MINUTE, 0);

            Date beforeTime=c.getTime();//明天10时前
            return beforeTime.after(date);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return false;
        }

    }

    public static Date GetDCMatchEndTime(Date matchTime, int aheadMilli) {
        if(matchTime == null) return null;
        Calendar matchCal = Calendar.getInstance();
        matchCal.setTime(matchTime);

        Calendar stopPlayTicketCal = (Calendar) matchCal.clone();
        stopPlayTicketCal.set(Calendar.HOUR_OF_DAY, 4);
        stopPlayTicketCal.set(Calendar.MINUTE, 50);
        stopPlayTicketCal.set(Calendar.SECOND, 0);

        //欧洲杯延长销售时间 9点开始至次日凌晨6点
        Date euroCupStart = DateUtils.StringToDate("2012-06-08 9:00:00") ;
        Date euroCupEnd = DateUtils.StringToDate("2012-07-03 06:00:00");
        if(matchTime.getTime()>euroCupStart.getTime()&&matchTime.getTime()<euroCupEnd.getTime()){
            stopPlayTicketCal.set(Calendar.HOUR_OF_DAY, 5);
        }
        stopPlayTicketCal.add(Calendar.MILLISECOND, -aheadMilli);

        Calendar startPlayTicketCal = (Calendar) matchCal.clone();
        startPlayTicketCal.set(Calendar.HOUR_OF_DAY, 9);
        startPlayTicketCal.set(Calendar.MINUTE, 0);
        startPlayTicketCal.set(Calendar.SECOND, 0);

        Calendar weStartPlayTicketCal = (Calendar) startPlayTicketCal.clone();
        weStartPlayTicketCal.add(Calendar.MILLISECOND, +aheadMilli);
        // if(matchCal.after(stopPlayTicketCal)&&(matchCal.before(startPlayTicketCal)||matchCal.equals(startPlayTicketCal))){
        //
        // //(4:50-aheadMilli)——(9:00) 都设置成为(4:50-aheadMilli)
        // return stopPlayTicketCal.getTime();
        // }else
        // if(matchCal.after(startPlayTicketCal)&&matchCal.before(weStartPlayTicketCal)){
        // //(9:00)——(9:00+aheadMilli) 都设置 (4:50-aheadMilli)+offset
        // int offset=(int)
        // (weStartPlayTicketCal.getTimeInMillis()-matchCal.getTimeInMillis());
        // stopPlayTicketCal.add(Calendar.MILLISECOND, +offset);
        // return stopPlayTicketCal.getTime();
        if (matchCal.after(stopPlayTicketCal) && matchCal.before(weStartPlayTicketCal)) {
            // (4:50-aheadMilli)——(9:00+aheadMilli) 都设置 (4:50-aheadMilli)
            return stopPlayTicketCal.getTime();
        } else {
            matchCal.add(Calendar.MILLISECOND, -aheadMilli);
            return matchCal.getTime();
        }
    }


    /**
     * 获取距离现在的时间
     */
    public static String getMinutes (Date times){
        long time = new Date().getTime() - times.getTime();//time 单位是 毫秒
        String res = null;
        //转化成天数
        //先判断是不是小于 60 * 60 * 1000  也就是 小于1小时，那么显示 ： **分钟前
        if(time < 60 * 60 * 1000){
            res =  (time / 1000 / 60 ) + "分钟前";
        }
        //如果大于等于1小时 小于等于一天，那么显示 ： **小时前
        else if(time >= 60 * 60 * 1000 && time < 24 * 60 * 60 * 1000){
            res =  (time / 1000 / 60 / 60 ) + "小时前";
        }
        //如果大于等于1小时 小于等于一天，那么显示 ： **小时前
        else if(time >= 24 * 60 * 60 * 1000){
            res =  (time / 1000 / 60 / 60 /24 ) + "天前";
        }
        //如果时间不明确或者发帖不足一分钟 ，则不显示
        else{
            res = "";
        }

        return res;
    }

    /**
     * 取得系统当前时间前n个月的相对应的一天

     * @param n int
     * @return String yyyy-mm-dd
     */
    public static String getNMonthBeforeCurrentDay(int n) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -n);
        return "" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE);

    }

    public static List<String> getAllBeforeDateToAfterDateSpace(String string, String string2) {
        List<String> date = null;
        try {
            date = new ArrayList<String>();
            Date dateTemp = new SimpleDateFormat("yyyy-MM-dd").parse(string);
            Date dateTemp2 = new SimpleDateFormat("yyyy-MM-dd").parse(string2);
            Calendar calendarTemp = Calendar.getInstance();
            calendarTemp.setTime(dateTemp);
            while (calendarTemp.getTime().getTime()!= dateTemp2.getTime()) {
                date.add(new SimpleDateFormat("yyyy-MM-dd").format(calendarTemp.getTime()));
                calendarTemp.add(Calendar.DAY_OF_YEAR, 1);
            }
            date.add(string2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /*******得到昨天日期*******/
    public static String getYesterDay(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
    }

    /**
     * 获取两个日期之间间隔天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getTwoDay(Date startDate, Date endDate) {
        long day = 0;
        try {
            day = (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day+"";
    }

    public static void main(String args[]) {
        String dateStr1 = "2011-07-03 4:50:00";
        String dateStr2 = "2012-07-03 5:15:00";
        String dateStr3 = "2012-07-03 6:15:00";
        String dateStr4 = "2012-07-03 7:15:00";
        String dateStr5 = "2012-07-03 8:15:00";
        //String dateStr6= "2012-06-09 9:00:00";
        String dateStr6 = "2012-07-03 9:16:00";
        String dateStr7 = "2012-07-03 10:15:00";
        String format = "yyyy-MM-dd HH:mm:ss";
        Date date = stringToDate(dateStr1, format);
        Date handleDate = GetDCMatchEndTime(date, 1200000);
        System.out.println(dateToString(handleDate));

        date = stringToDate(dateStr2, format);
        handleDate = GetDCMatchEndTime(date, 600000);
        System.out.println(dateToString(handleDate));

        date = stringToDate(dateStr3, format);
        handleDate = GetDCMatchEndTime(date, 600000);
        System.out.println(dateToString(handleDate));

        date = stringToDate(dateStr4, format);
        handleDate = GetDCMatchEndTime(date, 600000);
        System.out.println(dateToString(handleDate));

        date = stringToDate(dateStr5, format);
        handleDate = GetDCMatchEndTime(date, 600000);
        System.out.println(dateToString(handleDate));

        date = stringToDate(dateStr6, format);
        handleDate = GetDCMatchEndTime(date, 600000);
        System.out.println(dateToString(handleDate));

        date = stringToDate(dateStr7, format);
        handleDate = GetDCMatchEndTime(date, 600000);
        System.out.println(dateToString(handleDate));
//		String dateText="12-30 02:30";
//		System.out.println(DateTools.dateToString(strToDateDafueYear(dateText,new Date())));
    }

    /**
     * 检查给定时间是否在指定时间区间
     * @param startTime
     * @param endTime
     * @param checkTime
     * @return
     */
    public static boolean isBetween(Date startTime,Date endTime,Date checkTime){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar cal3 = Calendar.getInstance();
        cal1.setTime(checkTime);
        cal2.setTime(startTime);
        cal3.setTime(endTime);
        if(cal2.before(cal1)&& cal3.after(cal1)){
            return true;
        }
        return false;
    }

    //根据日期取得星期几
    public static String getWeek(Date date){
        String[] weeks = {"周日","周一","周二","周三","周四","周五","周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index<0){
            week_index = 0;
        }
        return weeks[week_index];
    }

    /**
     * 日期加减操作
     * @param date
     * @return
     */
    public static Date jiaOrJian(Date date,int num){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,num);
        return cal.getTime();
    }

    public static Date toDate(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        try {
            date=sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}