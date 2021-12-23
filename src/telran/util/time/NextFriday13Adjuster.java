package telran.util.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;

public class NextFriday13Adjuster implements TemporalAdjuster {

	private static final DayOfWeek FRIDAY = DayOfWeek.FRIDAY;
	private static final int DAY = 13;

	@Override
	public Temporal adjustInto(Temporal temporal) {
		// TODO done	
		LocalDate currentDay = (LocalDate) temporal;
		currentDay = setCurrenDate(currentDay);
		while (currentDay.getDayOfWeek() != FRIDAY) {
			currentDay = setCurrenDate(currentDay);
		}  
		return currentDay;
	}
	
	private LocalDate setCurrenDate(LocalDate currentDay) {
		if(currentDay.getDayOfMonth()>=13) {
			currentDay = currentDay.plusMonths(1);
		}
		return  LocalDate.of(currentDay.getYear(), currentDay.getMonth(), DAY);

	}

}
