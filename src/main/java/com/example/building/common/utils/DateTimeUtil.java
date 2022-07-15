package com.example.building.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 * DateTimeUtil
 */
public class DateTimeUtil {

    public static final LocalDateTime toLocalDateTime(final String year, final String month, final String day) {
        if (year == null || month == null || day == null) {
            return null;
        }
        try {
            LocalDate d = LocalDate.of(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
            return LocalDateTime.of(d, LocalTime.MIN);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * toLocalDateTime String→LocalDateTime
     *
     * @param date
     * @param format
     * @return
     */
    public static final LocalDateTime toLocalDateTime(final String date, DateTimeFormat format) {
        if (date == null || format == null) {
            return null;
        }
        DateTimeFormatter f = DateTimeFormatter.ofPattern(format.getValue());
        try {
            LocalDateTime dt = LocalDateTime.parse(date, f);
            return dt;
        } catch (Exception e) {
            try {
                LocalDate d = LocalDate.parse(date, f);
                return LocalDateTime.of(d, LocalTime.MIN);
            } catch (Exception ex) {
            }
            return null;
        }
    }

    /**
     * toLocalDate String→LocalDate
     *
     * @param date
     * @param format
     * @return
     */
    public static final LocalDate toLocalDate(final String date, DateTimeFormat format) {
        if (date == null || format == null) {
            return null;
        }
        DateTimeFormatter f = DateTimeFormatter.ofPattern(format.getValue());
        try {
            return LocalDate.parse(date, f);
        } catch (Exception e) {
            try {
                LocalDateTime dt = LocalDateTime.parse(date, f);
                return dt.toLocalDate();
            } catch (Exception ex) {
            }
            return null;
        }
    }

    /**
     * Convert timestamp String→LocalTime
     *
     * @param time
     * @param format
     * @return
     */
    public static final LocalTime toLocalTime(final String time, TimeFormat format) {
        if (time == null || format == null) {
            return null;
        }
        DateTimeFormatter f = DateTimeFormatter.ofPattern(format.getValue());
        try {
            return LocalTime.parse(time, f);
        } catch (Exception e) {
            try {
                LocalDateTime dt = LocalDateTime.parse(time, f);
                return dt.toLocalTime();
            } catch (Exception ex) {
            }
            return null;
        }
    }

    public static final boolean isXMonthsPasted(LocalDateTime time, long monthsToSubtract) {
        if (time == null) {
            return false;
        }
        return isXMonthsPasted(LocalDate.now(), LocalDate.of(time.getYear(), time.getMonth(), time.getDayOfMonth()), monthsToSubtract);
    }

    /**
     * isXMonthsPasted<br>
     *
     * @param past
     * @param monthsToSubtract
     * @return
     */
    public static final boolean isXMonthsPasted(LocalDate past, long monthsToSubtract) {
        return isXMonthsPasted(LocalDate.now(), past, monthsToSubtract);
    }

    /**
     * isXMonthsPasted<br>
     * today:2019/10/08 , past:2019/07/07,3：true<br>
     * today:2019/10/08 , past:2019/07/08,3：false<br>
     * today:2019/10/08 , past:2019/07/09,3：false<br>
     *
     * @param today
     * @param past
     * @param monthsToSubtract
     * @return
     */
    public static final boolean isXMonthsPasted(LocalDate today, LocalDate past, long monthsToSubtract) {
        if (today.minusMonths(monthsToSubtract).isAfter(past)) {
            return true;
        }
        return false;
    }

    /**
     * localDateToString LocalDate→String
     *
     * @param inDate
     * @param format
     * @return
     */
    public static final String localDateToString(LocalDate inDate, final DateTimeFormat format) {
        if (inDate == null || format == null) {
            return null;
        }
        try {
            return inDate.format(DateTimeFormatter.ofPattern(format.getValue()));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * localDateTimeToString LocalDateTime→String
     *
     * @param inDateTime
     * @param format
     * @return
     */
    public static final String localDateTimeToString(LocalDateTime inDateTime, final DateTimeFormat format) {
        if (inDateTime == null || format == null) {
            return null;
        }
        try {
            return inDateTime.format(DateTimeFormatter.ofPattern(format.getValue()));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * localTimeToString LocalTime→String
     *
     * @param inTime
     * @param format
     * @return
     */
    public static final String localTimeToString(LocalTime inTime, final TimeFormat format) {
        if (inTime == null || format == null) {
            return null;
        }
        try {
            return inTime.format(DateTimeFormatter.ofPattern(format.getValue()));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * toLocalFirstDate
     * ex: 2019/08/07 00:00:00 ⇒ 2019/08/01 00:00:00 2019/09/07 00:00:00 ⇒ 2019/09/01 00:00:00
     *
     * @param localDate
     * @return the first day of the month
     */
    public static final LocalDateTime toLocalFirstDate(LocalDateTime localDate) {
        LocalDate firstDayOfMonth = LocalDate.of(localDate.getYear(), localDate.getMonth(), 1);
        return LocalDateTime.of(firstDayOfMonth, LocalTime.MIN);
    }

    /**
     * toLocalLastDate
     * ex: 2019/08/07 00:00:00 ⇒ 2019/08/31 00:00:00 2019/09/07 00:00:00 ⇒ 2019/09/30 00:00:00
     *
     * @param localDate
     * @return the last day of the month
     */
    public static final LocalDateTime toLocalLastDate(LocalDateTime localDate) {
        LocalDate lastDayOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth()).toLocalDate();
        return LocalDateTime.of(lastDayOfMonth, LocalTime.MIN);
    }

    /**
     * toPreviousMonthLastDate
     * ex: 2019/10/07 00:00:00 ⇒ 2019/09/30 00:00:00
     *
     * @param localDate
     * @return the last day of the month
     */
    public static final LocalDateTime toPreviousMonthLastDate(LocalDateTime localDate) {
        LocalDateTime ldt = LocalDateTime.parse(localDate.toString());
        LocalDateTime previousMonth = ldt.minusMonths(1L);
        return toLocalLastDate(previousMonth);
    }

    /**
     * toBeginDayOfWeek
     *
     * @param localDate
     * @return the last day of the week
     */
    public static final LocalDateTime toBeginDayOfWeek(LocalDateTime localDate) {
        LocalDate baseDate = localDate.toLocalDate();
        LocalDate firstDayForWeek = baseDate.with(DayOfWeek.MONDAY);
        return LocalDateTime.of(firstDayForWeek, LocalTime.MIN);
    }

    /**
     * toEndDayOfWeek
     *
     * @param localDate
     * @return the last day of the week
     */
    public static final LocalDateTime toEndDayOfWeek(LocalDateTime localDate) {
        LocalDate baseDate = localDate.toLocalDate();
        LocalDate endDayForWeek = baseDate.with(DayOfWeek.SUNDAY);
        return LocalDateTime.of(endDayForWeek, LocalTime.MIN);
    }

    /**
     * isValidDate
     *
     * @param date
     * @param format
     * @return boolean [true|false]
     */
    public static final boolean isValidDate(String date, DateTimeFormat format) {
        if (date == null || format == null) {
            return false;
        }
        try {
            DateFormat sdf = new SimpleDateFormat(format.getValue());
            sdf.setLenient(false);
            return sdf.format(sdf.parse(date)).equals(date);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * convertGMTToLocalDateTime
     *
     * @param gmtDateTime
     * @return
     */
    public static LocalDateTime convertGMTToLocalDateTime(LocalDateTime gmtDateTime) {
        if (gmtDateTime == null) {
            return null;
        }
        try {
            return gmtDateTime.atZone(ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * getDateFromDateTime
     *
     * @param dateTime
     * @return
     */
    public static LocalDateTime getDateFromDateTime(LocalDate dateTime, int... dataTime) {
        if (dataTime == null || dataTime.length < 4) {
            dataTime = new int[] {0, 0, 0, 0};
        }
        LocalDateTime localDateTime = LocalDateTime.of(dateTime.getYear(),
                dateTime.getMonthValue(), dateTime.getDayOfMonth(), dataTime[0], dataTime[1],
                dataTime[2], dataTime[3]);
        return localDateTime;
    }

    /**
     * checkDate
     *
     * @param strDate
     * @return
     */
    public static boolean checkDate(String strDate) {
        if (strDate == null) {
            return false;
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.BASIC_ISO_DATE;
        try {
            LocalDate.parse(strDate, dateFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Compare 2 date
     *
     * @param dateFrom
     * @param dateTo
     * @param format
     * @return dateFrom = dateTo: 0
     *         dateFrom > dateTo: > 0
     *         dateFrom < dateTo: < 0
     */
    public static int compareDate(String dateFrom, String dateTo, DateTimeFormat format) {
        LocalDate localDateFrom = toLocalDate(dateFrom, format);
        LocalDate localDateTo = toLocalDate(dateTo, format);
        int result = localDateFrom.compareTo(localDateTo);
        return result;
    }

    /**
     * isExistDateInList
     *
     * @param dateCheck
     * @param lstLocalDate
     * @return isExist
     */
    public static boolean isExistDateInList(LocalDate dateCheck, List<LocalDate> lstLocalDate) {
        if (dateCheck == null || CollectionUtils.isEmpty(lstLocalDate)) {
            return false;
        }
        for (LocalDate localDate : lstLocalDate) {
            if (dateCheck.isEqual(localDate)) {
                return true;
            }
        }

        return false;
    }

    /**
     * toLocalDateTimeStr
     *
     * @param localDateTime
     * @param format
     * @return string LocalDateTime
     */
    public static String toLocalDateTimeStr(LocalDateTime localDateTime, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * getLocalDateFromFormatYmd
     *
     * @param date YYYYMMDD
     * @param format
     * @return
     */
    public static LocalDate getLocalDateFromFormatYmd(String date, String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDate localDate = LocalDate.from(dtf.parse(date));
        return localDate;
    }

    /**
     * checkDateYmd
     *
     * @param dateTime
     * @return boolean [true|false]
     */
    public static boolean checkDateYmd(String dateTime, DateTimeFormat format) {
        try {
            new SimpleDateFormat(format.getValue()).parse(dateTime);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
