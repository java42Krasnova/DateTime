package telran.util.time;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DateTimeTests {
	LocalDate birthAS = LocalDate.of(1799, 6, 6);
	LocalDate barMizvaAS = birthAS.plusYears(13);
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testDateOperations() {
		
		assertEquals(LocalDate.of(1812, 6, 6), barMizvaAS);
		assertEquals("1812-06-06", barMizvaAS.toString());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy");
		assertEquals("6/6/1812", barMizvaAS.format(dtf));
		System.out.println(barMizvaAS.format(DateTimeFormatter.ofPattern("d MMM E yyyy ")));
		assertEquals(LocalDate.parse("1812-06-06"), barMizvaAS);
		assertEquals(13 * 12, ChronoUnit.MONTHS.between(birthAS, barMizvaAS));
	}
	@Test
	void testBarMizvaAdjuster() {
		assertEquals(barMizvaAS, birthAS.with(new BarMizvaAdjuster()));
	}
	@Test
	void testNextFriday13Adjuster() {
		assertEquals(LocalDate.parse("2022-05-13"), LocalDate.parse("2021-12-22").with(new NextFriday13Adjuster()));
		assertEquals(LocalDate.parse("2023-01-13"), LocalDate.parse("2022-05-13").with(new NextFriday13Adjuster()));
	}
	@Test
	void testGetAge() {
		LocalDate ld1 = LocalDate.parse("2021-12-22");
		LocalDate ld2 = LocalDate.parse("2021-01-31");
		LocalDate ld3 = LocalDate.parse("2021-06-05");
		assertEquals(2021 - 1799, getAgeAtDate(birthAS, ld1));
		assertEquals(2020 - 1799, getAgeAtDate(birthAS, ld2));
		assertEquals(2020 - 1799, getAgeAtDate(birthAS, ld3));
	}

	private int getAgeAtDate(LocalDate birthDate, LocalDate atDate) {
		
		
		return (int)ChronoUnit.YEARS.between(birthDate, atDate);
	}
	@Test
	void timingTest() {
		ChronoUnit chronoUnit = ChronoUnit.MONTHS;
		System.out.printf("time  between %s and %s is %d %s \n",
				birthAS, LocalDate.now(), chronoUnit.between(birthAS, LocalDate.now()), chronoUnit);
		Period period = Period.between(birthAS, LocalDate.now());
		System.out.printf("between %s and %s there are years %d, months %d, days %d\n",
				birthAS, LocalDate.now(), period.getYears(), period.getMonths(), period.getDays());
	}
	@Test
	void instantTest() {
		Instant instant = Instant.now(); 
		LocalDateTime ltd = LocalDateTime.ofInstant(instant,ZoneId.systemDefault());
		ZonedDateTime ztd = ZonedDateTime.ofInstant(instant,ZoneId.systemDefault());
		ztd = ztd.withZoneSameInstant(ZoneId.of("GMT+8"));
		System.out.printf("instant view %s, LocalDateTime %s, ZonedDayTime %s\n", instant, ltd, ztd);
	}
	@Test
	void zoneIDsTest() {
		ZonedDateTime ztd = ZonedDateTime.now();
		for(String zone: ZoneId.getAvailableZoneIds()) {
			
			if (zone.toLowerCase().contains("amer")) {
				System.out.println(ztd.withZoneSameInstant(ZoneId.of(zone)));
			}
		}
	}
	

}