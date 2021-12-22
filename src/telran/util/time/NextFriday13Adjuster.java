package telran.util.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextFriday13Adjuster implements TemporalAdjuster {

	private static final DayOfWeek FRIDAY = DayOfWeek.FRIDAY;
	private static final int DAY = 13;

	@Override
	public Temporal adjustInto(Temporal temporal) {
		// TODO done
		LocalDate currentDay = (LocalDate) temporal;
		if (currentDay.getDayOfMonth() < DAY) {
			currentDay = LocalDate.of(currentDay.getYear(), currentDay.getMonth(), DAY);
		}
		if (currentDay.getDayOfMonth() == DAY && currentDay.getDayOfWeek() == FRIDAY) {
			return currentDay;
		}
		do {
			currentDay = getNextMonth(currentDay);
		} while (currentDay.getDayOfWeek() != FRIDAY);
		return currentDay;
	}

	private LocalDate getNextMonth(LocalDate currentDay) {
		int currentMonth = currentDay.getMonthValue();
		int currentYear = currentDay.getYear();
		if (currentMonth == 12) {
			return LocalDate.of(++currentYear, 1, DAY);
		}
		return LocalDate.of(currentYear, ++currentMonth, DAY);
	}

}
