package ee.serge;

import ee.serge.Date.Month;

/**
 * ConsumptionCalculator calculates clients monthly day- and night- consumption.
 *
 * All indexes (days, months) are counted starting from 1 (monday = 1, january = 1)
 * with the only exception being 24 hours a day that starts with 0.
 */
public class ConsumptionCalculator {

    private static ConsumptionCalculator singleton;
    /**
     * Day hours end at 23 when it's not summer time
     */
    private static final int NON_SUMMER_TIME_DAY_HOUR_END_HOUR = 22;
    /**
     * Day hours start at 7 when it's not summer time
     */
    private static final int NON_SUMMER_TIME_DAY_HOUR_START_HOUR = 7;
    /**
     * Day hours end at 24 when it's summer time
     */
    private static final int SUMMER_TIME_DAY_HOUR_END_HOUR = 23;
    /**
     * Day hours start at 8 when it's summer time
     */
    private static final int SUMMER_TIME_DAY_HOUR_START_HOUR = 8;
    private static final int SATURDAY = 6;
    private static final int SUNDAY = 7;

    private static final int DAYS_IN_WEEK = 7;
    private static final int HOURS_IN_DAY = 24;
    private static final int MONTHS_IN_YEAR = 12;

    public static void main(String[] args) {
        System.out.println(ConsumptionCalculator
                .calculateMonthlyConsumption(ee.serge.generator.MonthGenerator.getOctober2014()));
        System.out.println(ConsumptionCalculator
                .calculateMonthlyConsumption(ee.serge.generator.MonthGenerator.getMarch2012()));
    }

    public ConsumptionCalculator() {};

    /**
     * Prints the daily and nightly electricity consumption of the given month
     */
    public static MonthlyConsumption calculateMonthlyConsumption(Date date) {
        if (singleton == null) singleton = new ConsumptionCalculator();
        int dayConsumption = 0;
        int nightConsumption = 0;

        for (int hour = 0; hour < date.getHours().length; hour++) {
            int hourConsumption = date.getHours()[hour];
            int monthDay = hour / HOURS_IN_DAY + 1;
            int dayHour = hour % HOURS_IN_DAY;
            if (singleton.isDayTime(date.getYear(), date.getMonthIndex(), monthDay, dayHour)) {
                dayConsumption += hourConsumption;
            } else {
                nightConsumption += hourConsumption;
            }
        }
        return new MonthlyConsumption(dayConsumption, nightConsumption);
    }

    /**
     * Is it daytime, taking summer time into account
     */
    public boolean isDayTime(int year, int monthIndex, int monthDay, int hour) {
        int weekDay = getWeekDay(year, monthIndex, monthDay);
        if (isWorkDay(weekDay)) {
            if (isSummerTime(year, monthIndex, monthDay)) {
                return hour >= SUMMER_TIME_DAY_HOUR_START_HOUR && hour <= SUMMER_TIME_DAY_HOUR_END_HOUR;
            } else {
                return hour >= NON_SUMMER_TIME_DAY_HOUR_START_HOUR && hour <= NON_SUMMER_TIME_DAY_HOUR_END_HOUR;
            }
        }
        return false;
    }

    /**
     * Is it summer time (last Sunday of March to last Sunday of October)
     */
    private boolean isSummerTime(int year, int monthIndex, int monthDay) {
        if (monthIndex > Month.MARCH.getIndex() && monthIndex < Month.OCTOBER.getIndex()) {
            return true;
        } else if (monthIndex == Month.MARCH.getIndex() && monthDay >= getSummerTimeStartDate(year)) {
            return true;
        } else if (monthIndex == Month.OCTOBER.getIndex() && monthDay <= getSummerTimeEndDate(year)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the day in March on which summer time starts
     */
    public int getSummerTimeStartDate(int year) {
        return getLastSundayOfMonth(year, Month.MARCH);
    }

    /**
     * Returns the day in October on which summer time ends
     */
    public int getSummerTimeEndDate(int year) {
        return getLastSundayOfMonth(year, Month.OCTOBER);
    }

    /**
     * Returns the index of the last Sunday of a month starting of 1
     */
    private int getLastSundayOfMonth(int year, Month month) {
        for (int i = month.getLengthInDays(); i > 0; i--) {
            int weekDay = getWeekDay(year, month.getIndex(), i);
            if (weekDay == SUNDAY) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Is it a work day (Monday - Friday)
     * @param weekDay - index of the day (Monday being 1)
     */
    private boolean isWorkDay(int weekDay) {
        return weekDay < SATURDAY;
    }

    /**
     * Returns the index of the day of the week according to the date
     * @return 1 - Monday<br>2 - Tuesday<br>3 - Wednesday<br>
     * 4 - Thursday<br>5 - Friday<br>6 - Saturday<br>7 - Sunday<br>
     * http://en.wikipedia.org/wiki/Determination_of_the_day_of_the_week#
     * Implementation-dependent_methods_of_Sakamoto.2C_Lachman.2C_Keith_and_Craver
     */
    public int getWeekDay(int year, int monthIndex, int day) {
        if (monthIndex == Month.JANUARY.getIndex() || monthIndex == Month.FEBRUARY.getIndex()) {
            monthIndex += MONTHS_IN_YEAR;
            year -= 1;
        }
        return (day + 2 * monthIndex + (3 * (monthIndex + 1) / 5) +
                year + (year / 4) - (year / 100) + (year / 400)) % DAYS_IN_WEEK + 1;
    }

}
