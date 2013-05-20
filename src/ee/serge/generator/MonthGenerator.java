package ee.serge.generator;

import java.util.Random;

import ee.serge.Date;

public class MonthGenerator {

	private static Random rnd = new Random();
	
	public static Date getRandomMonth() {
		Date date = new Date(1, 2012);
		int hours[] = new int[31*24];
		for (int i = 0; i < hours.length; i++) {
			hours[i] = rnd.nextInt(5);
		}
		date.setHours(hours);
		return date;
	}
	
	public static Date getCodeBorneTestMonth() {
		Date date = new Date(1, 2012);
		int hours[] = new int[31*24];
		hours[0] = 2;
		hours[1] = 3;
		hours[25] = 3;
		hours[31] = 6;
		hours[743] = 0;
		date.setHours(hours);
		return date;
	}
	
	public static Date getMarch2012() {
		Date date = new Date(3, 2012);
		int hours[] = new int[31*24];
		for (int i = 0; i < hours.length; i++) {
			hours[i] = 1;
		}
		date.setHours(hours);
		return date;
	}

    public static Date getOctober2014() {
        Date date = new Date(10, 2014);
        int hours[] = new int[31*24];
        hours[0] = 2;
        hours[7] = 1;
        hours[8] = 6;

        hours[48] = 3;

        hours[96] = 4;
        hours[100] = 5;
        hours[119] = 6;

        hours[263] = 7;

        hours[464] = 7;

        hours[520] = 50;

        hours[560] = 8;
        hours[575] = 9;

        hours[576] = 10;
        hours[599] = 9;

        hours[630] = 13;
        hours[631] = 10;
        hours[647] = 14;

        hours[708] = 50;
        hours[723] = 11;
        hours[726] = 12;
        hours[742] = 5;
        hours[743] = 8;
        date.setHours(hours);
        return date;
    }
	
}
