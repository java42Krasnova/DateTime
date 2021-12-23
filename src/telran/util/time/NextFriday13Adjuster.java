package telran.util.time;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class NextFriday13Adjuster implements TemporalAdjuster {

	private static final DayOfWeek FRIDAY = DayOfWeek.FRIDAY;
	private static final int DAY = 13;

	@Override
	public Temporal adjustInto(Temporal temporal) {
		// TODO done
		temporal = setTemporal(temporal);
		while (temporal.get(ChronoField.DAY_OF_MONTH) != DAY) {
			temporal = temporal.plus(1, ChronoUnit.MONTHS).
					with(TemporalAdjusters.dayOfWeekInMonth(2, FRIDAY));
		}
		return temporal;
	}

	private Temporal setTemporal(Temporal temporal) {
		if (temporal.get(ChronoField.DAY_OF_MONTH) >= DAY) {
			temporal = temporal.plus(1, ChronoUnit.MONTHS);
		}
		return temporal.with(TemporalAdjusters.dayOfWeekInMonth(2, FRIDAY));
	}

}
