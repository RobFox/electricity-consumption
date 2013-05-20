package ee.serge.test;


import ee.serge.MonthlyConsumption;
import ee.serge.generator.MonthGenerator;
import org.junit.Test;

import ee.serge.ConsumptionCalculator;
import static org.junit.Assert.assertEquals;

public class CalendarTest {

	public ConsumptionCalculator calculator = new ConsumptionCalculator();

	@Test
	public void weekDayTest() {
		assertEquals(1, calculator.getWeekDay(1900, 1, 1));
		assertEquals(7, calculator.getWeekDay(2012, 1, 1));
		assertEquals(4, calculator.getWeekDay(2012, 6, 14));
	}

	@Test
	public void summerTimeStartTest() {
		assertEquals(28, calculator.getSummerTimeStartDate(2010));
		assertEquals(27, calculator.getSummerTimeStartDate(2011));
		assertEquals(25, calculator.getSummerTimeStartDate(2012));
		assertEquals(31, calculator.getSummerTimeStartDate(2013));
		assertEquals(30, calculator.getSummerTimeStartDate(2014));
		assertEquals(29, calculator.getSummerTimeStartDate(2015));
		assertEquals(27, calculator.getSummerTimeStartDate(2016));
		assertEquals(26, calculator.getSummerTimeStartDate(2017));
		assertEquals(25, calculator.getSummerTimeStartDate(2018));
		assertEquals(31, calculator.getSummerTimeStartDate(2019));
	}

	@Test
	public void summerTimeEndTest() {
		assertEquals(31, calculator.getSummerTimeEndDate(2010));
		assertEquals(30, calculator.getSummerTimeEndDate(2011));
		assertEquals(28, calculator.getSummerTimeEndDate(2012));
		assertEquals(27, calculator.getSummerTimeEndDate(2013));
		assertEquals(26, calculator.getSummerTimeEndDate(2014));
		assertEquals(25, calculator.getSummerTimeEndDate(2015));
		assertEquals(30, calculator.getSummerTimeEndDate(2016));
		assertEquals(29, calculator.getSummerTimeEndDate(2017));
		assertEquals(28, calculator.getSummerTimeEndDate(2018));
		assertEquals(27, calculator.getSummerTimeEndDate(2019));
	}

	@Test
	public void dayTimeTest() {
		assertEquals(false, calculator.isDayTime(2012, 3, 28, 7));
		assertEquals(true, calculator.isDayTime(2012, 3, 28, 8));
		assertEquals(true, calculator.isDayTime(2012, 3, 28, 23));
        assertEquals(false, calculator.isDayTime(2012, 3, 29, 0));

		assertEquals(false, calculator.isDayTime(2012, 3, 21, 6));
		assertEquals(true, calculator.isDayTime(2012, 3, 21, 7));
        assertEquals(true, calculator.isDayTime(2012, 3, 21, 8));
		assertEquals(true, calculator.isDayTime(2012, 3, 21, 22));
		assertEquals(false, calculator.isDayTime(2012, 3, 21, 23));
	}

	@Test
	public void realTest() {
        assertEquals(new MonthlyConsumption(6, 8),
                calculator.calculateMonthlyConsumption(MonthGenerator.getCodeBorneTestMonth()));
        assertEquals(new MonthlyConsumption(145, 105),
                calculator.calculateMonthlyConsumption(MonthGenerator.getOctober2014()));

	}
	
}

