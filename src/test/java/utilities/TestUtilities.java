package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtilities {

	public static String getDateAndTime()
	{
		Date dt = new Date();
		System.out.println(dt);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY_MMM_dd_HH_mm_ss_SSS");
		String s = sdf.format(dt);
		System.out.println(s);
		return s;
	}
}
