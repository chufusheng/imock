package com.tester.jvm.mock.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: hubinbin
 * @Date: 2020/1/13 下午5:10
 * @Description:
 */
public class DateUtil {

    public static final String DATA_FORMANT  = "yyyy-MM-dd HH:mm:ss";
    public static final String DATA_FORMANT2 = "yyyyMMdd";
    public static final String DATA_FORMANT3 = "yyyy-MM-dd";
    public static final String DATA_FORMANT4 = "yyyy-MM-dd HH:mm";
    public static final String DATA_FORMANT5 = "yyyy-MM";
    public static final String DATA_FORMANT6 = "yyyyMMddHHmmss";
    public static final String DATA_FORMANT7 = "yyyy年MM月dd日";
    public static final String DATA_FORMANT8 = "MM-dd";
    public static final String DATA_FORMANT9 = "yyyy.MM.dd";
    public static final String DATA_FORMANT10 = "yyyy.MM.dd HH:mm";
    public static final String DATA_FORMANT11 = "yyyy年MM月";
    public static final String DATA_FORMANT12 = "yyyyMMddHHmm";
    public static final String DATA_FORMANT13 = "yyyy.MM.dd HH:mm:ss";
    public static final String DATA_FORMANT14 = "yyyy/MM/dd";

    public static String format(String date) {
        SimpleDateFormat format = new SimpleDateFormat(DATA_FORMANT);
        return format.format(date);
    }


    /**
     * 对一个日期加减天数
     *
     * @param baseDate
     * @param days 正加 负减
     * @return
     */
    public static Date addDays(Date baseDate, int days) {
        Calendar c = Calendar.getInstance();
        if (baseDate != null) {
            c.setTime(baseDate);
        }
        c.add(Calendar.DAY_OF_YEAR, days);

        return c.getTime();
    }

    public static Date getDateStart(Date baseDate) {
        Calendar c = Calendar.getInstance();
        if (baseDate != null) {
            c.setTime(baseDate);
        }
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0); // 把当前时间分钟变成０
        c.set(Calendar.SECOND, 0); // 把当前时间秒数变成０
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    /**
     * 将日期设置成23时59分59秒
     *
     * @param baseDate
     * @return
     */
    public static Date getDateEnd(Date baseDate) {
        Calendar c = Calendar.getInstance();
        if (baseDate != null) {
            c.setTime(baseDate);
        }

        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59); // 把当前时间分钟变成０
        c.set(Calendar.SECOND, 59); // 把当前时间秒数变成０
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static String getFormantDate(Date date, String formant) {
        if (StringUtils.isBlank(formant)) {
            return null;
        }
        SimpleDateFormat sFormat = new SimpleDateFormat(formant);

        return sFormat.format(date);
    }

    /**
     * 获取当前时间的一个月之前的时间
     */
    public static Date getMonthAgo() {
        Date now = new Date();
        String monthFirst = getFormantDate(getMonthFirstDay(),DATA_FORMANT3);
        String monthLast = getFormantDate(getMonthLastDay(),DATA_FORMANT3);
        int days = Days.daysBetween(new LocalDate(monthFirst) , new LocalDate(monthLast)).getDays();
        return addDays(now , -days);
    }

    public static Date getMonthFirstDay() {
        Calendar ca = Calendar.getInstance();
//        ca.add(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MILLISECOND, ca.getActualMinimum(Calendar.MILLISECOND));
        ca.set(Calendar.SECOND, ca.getActualMinimum(Calendar.SECOND));
        ca.set(Calendar.MINUTE, ca.getActualMinimum(Calendar.MINUTE));
        ca.set(Calendar.HOUR_OF_DAY, ca.getActualMinimum(Calendar.HOUR_OF_DAY));
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMinimum(Calendar.DAY_OF_MONTH));
        return ca.getTime();
    }

    public static Date getMonthFirstDay(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
//        ca.add(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MILLISECOND, ca.getActualMinimum(Calendar.MILLISECOND));
        ca.set(Calendar.SECOND, ca.getActualMinimum(Calendar.SECOND));
        ca.set(Calendar.MINUTE, ca.getActualMinimum(Calendar.MINUTE));
        ca.set(Calendar.HOUR_OF_DAY, ca.getActualMinimum(Calendar.HOUR_OF_DAY));
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMinimum(Calendar.DAY_OF_MONTH));
        return ca.getTime();
    }

    public static Date getMonthLastDay() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MILLISECOND, ca.getActualMaximum(Calendar.MILLISECOND));
        ca.set(Calendar.SECOND, ca.getActualMaximum(Calendar.SECOND));
        ca.set(Calendar.MINUTE, ca.getActualMaximum(Calendar.MINUTE));
        ca.set(Calendar.HOUR_OF_DAY, ca.getActualMaximum(Calendar.HOUR_OF_DAY));
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return ca.getTime();
    }

    public static String formatDate(Long datelong) {
        Date date = new Date(datelong);
        return format(date.toString());
    }

    public static void main(String[] args) {
        System.out.println(formatDate(1717084800000L));
    }
}
