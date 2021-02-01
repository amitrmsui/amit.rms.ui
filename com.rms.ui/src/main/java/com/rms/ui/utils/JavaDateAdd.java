package com.rms.ui.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class JavaDateAdd
{
	
	public String getTomorrow() {
		
	    return formatDate(1);		
	}
	
	public String getNextWeek() {
		
	    return formatDate(8);		
	}
	
	public String formatDate(int i) {
		
	    String pattern = "dd MM YYYY";
	    Calendar calendar = Calendar.getInstance();
	    DateFormat df = new SimpleDateFormat(pattern);
	    
	    calendar.add(Calendar.DAY_OF_YEAR, i);
	    Date nextWeek1 = calendar.getTime();
	    String nextWeekAsString = df.format(nextWeek1);

	    String dateMonth[] = nextWeekAsString.split(" ");
	    if(Integer.parseInt(dateMonth[0])<10)
	    	dateMonth[0]=dateMonth[0].substring(1, 2);
	    String date = dateMonth[0];
	    String month = getMonth(dateMonth[1]);
		System.out.println(month+" "+date+", "+dateMonth[2]+".");
	    return month+" "+date+", "+dateMonth[2]+".";
		
	}
	
	public String getMonth(String s) {
		Map<String, String> hm = new HashMap<>();
		hm.put("01", "Jan");
		hm.put("02", "Feb");
		hm.put("03", "Mar");
		hm.put("04", "Apr");
		hm.put("05", "May");
		hm.put("06", "Jun");
		hm.put("07", "Jul");
		hm.put("08", "Aug");
		hm.put("09", "Sep");
		hm.put("10", "Oct");
		hm.put("11", "Nov");
		hm.put("12", "Dec");
		
		return hm.get(s);
	}

}