package telran.application.dates;

import java.time.*;
import java.time.format.TextStyle;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Locale;

public class PrintCalendar {
	private static final TextStyle WEEK_DAY_LENTH = TextStyle.SHORT_STANDALONE;
	static DayOfWeek[] daysOfWeek = DayOfWeek.values();
	private static Locale locale = Locale.forLanguageTag("");

	public static void main(String[] args) {
		try {
			// TODO Part for arguments processing done
			// java - jar <jar file name> <month number> <year><full name of week day
			// (SUNDAY upper case)>
			// no arguments current month, current year, MONDAY+
			// no year, no week day - current year, MONDAY+
			// no week day - MONDAY+
			setDaysOfWeek(args);
			if(args.length == 0) {
			printCalendar(LocalDate.now().getMonthValue(), LocalDate.now().getYear());
			}
			if(args.length == 1) {
				printCalendar(Integer.parseInt(args[0]), LocalDate.now().getYear());
			}
			if(args.length > 1) 
			{printCalendar(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


	}

	private static void setDaysOfWeek(String[] args) throws IllegalArgumentException {
		if (args.length > 2) {
			// TODO reordering of static daysOfWeek Done
			// in case of wrong week day exceptin should be thrown
			int dayOfWeekToStart = DayOfWeek.valueOf(args[2].toUpperCase()).getValue()-1;
			LinkedList<DayOfWeek> newOrderForDaysWeek = new LinkedList<>();
			for( int i = 0; i < daysOfWeek.length; i++) {
				if (dayOfWeekToStart == daysOfWeek.length) {
					dayOfWeekToStart = 0;
				}
				newOrderForDaysWeek.add(daysOfWeek[dayOfWeekToStart++]);
			}
			newOrderForDaysWeek.toArray(daysOfWeek);
		} 
		else {
			daysOfWeek = DayOfWeek.values();
		}
	}

	private static void printCalendar(int month, int year) {
		printTitle(month, year);
		printWeekDays();
		printDates(month, year);
	}

	private static void printDates(int month, int year) {
		int firstColumn = getFirstColumn(month, year);
		printOffSet(firstColumn);
		int days = getDaysNumbers(month, year);
		int columnWidth = getColumnWidth();
		int line = 1;
		for (int i = 1; i <= days; i++) {
			System.out.printf("%" + columnWidth + "d", i);
			if ((line + firstColumn) % daysOfWeek.length == 0) {
				System.out.println();
				firstColumn = 0;
			} else {
				firstColumn++;
			}
		}
	}

	private static int getColumnWidth() {
		return (daysOfWeek[0].getDisplayName(WEEK_DAY_LENTH, locale) + " ").length();
	}

	private static int getDaysNumbers(int month, int year) {
		
		return YearMonth.of(year, month).lengthOfMonth();
	}

	private static void printOffSet(int firstColumn) {
System.out.print(" ".repeat(firstColumn*getColumnWidth()));		
	}

	private static int getFirstColumn(int month, int year) {
		LocalDate firstDateMonth = LocalDate.of(year, month, 1);
		int firstWeekDay = firstDateMonth.getDayOfWeek().getValue();
		int firstValue = daysOfWeek[0].getValue();
		int delta = firstWeekDay - firstValue ;
		return  delta >= 0? delta : delta+ daysOfWeek.length ;
	}

	private static void printWeekDays() {
		String res = " ";
		for (int i = 0; i < daysOfWeek.length; i++) {
			res += daysOfWeek[i].getDisplayName(TextStyle.SHORT, locale) + " ";
		}
		System.out.println(res);
	}

	private static void printTitle(int month, int year) {
		Month monthObj = Month.of(month);
		System.out.printf("%s, %d\n", monthObj.getDisplayName(TextStyle.FULL_STANDALONE, locale), year);
	}

}
