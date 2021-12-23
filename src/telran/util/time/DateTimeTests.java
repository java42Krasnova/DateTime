package telran.util.time;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
		assertEquals(("1812-06-06"), barMizvaAS.toString());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy");
		assertEquals("6/6/1812", barMizvaAS.format(dtf));
		System.out.println(barMizvaAS.format(DateTimeFormatter.ofPattern("d e ")));
		// assertEquals("6 Sut", barMizvaAS.format(DateTimeFormatter.ofPattern("d e
		// ")));
		assertEquals(LocalDate.parse("1812-06-06"), barMizvaAS);
		assertEquals(13 * 12, ChronoUnit.MONTHS.between(birthAS, barMizvaAS));
	}

	@Test
	void testBarMizvaAdjuster() {
		assertEquals(barMizvaAS, birthAS.with(new BarMizvaAdjuster()));
	}

	@Test
	void nextFriday13Adjuster() {
		assertEquals(LocalDate.parse("2022-05-13"), LocalDate.parse("2021-12-22").with(new NextFriday13Adjuster()));
		assertEquals(LocalDate.parse("2023-01-13"), LocalDate.parse("2022-05-14").with(new NextFriday13Adjuster()));
		assertEquals(LocalDate.parse("2021-08-13"), LocalDate.parse("2021-01-14").with(new NextFriday13Adjuster()));
		assertNotEquals(LocalDate.parse("2021-08-12"), LocalDate.parse("2021-01-14").with(new NextFriday13Adjuster()));
		assertEquals(LocalDate.parse("2021-08-13"), LocalDate.parse("2021-08-07").with(new NextFriday13Adjuster()));
		assertEquals(LocalDate.parse("2021-08-13"), LocalDate.parse("2021-07-13").with(new NextFriday13Adjuster()));
		assertEquals(LocalDate.parse("2021-08-13"), LocalDate.parse("2021-07-16").with(new NextFriday13Adjuster()));

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
		//TODO done!

		return (int) ChronoUnit.YEARS.between(birthDate, atDate);
	}

}
