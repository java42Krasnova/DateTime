package telran.application.dates;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DateTimeWithZone {
	private static ZonedDateTime timeZone = ZonedDateTime.now();
	private static ArrayList<ZonedDateTime> listOftimeZones = new ArrayList<>();
	private static DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss VV");

	public static void main(String[] args) {
		// args[0] - optional substring of time Zone (default local time zone)
		// display out the time in the appropriate ZoneId
		try {
			if (args.length > 0 && args[0].contains("-help")) {
				System.out.printf("List of Zones: %s\n ", args[0]);
				printHelp();
				return;
			}
			if (args.length > 0) {
				System.out.printf("Selected zone is :%s\n ", args[0]);
			}
			getZonedDateTime(args);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			printHelp();
			return;
		}
		printTimeZone(listOftimeZones);
	}

	private static void printHelp() {
		System.out.println("Select one of following Zones. Default Zone is Local Zone");
		for (String zone : ZoneId.getAvailableZoneIds()) {
			System.out.println(timeZone.withZoneSameInstant(ZoneId.of(zone)).format(format));
		}
	}

	private static void printTimeZone(ArrayList<ZonedDateTime> listTimeZome) {
		for (ZonedDateTime zdt : listTimeZome) {
			System.out.printf("The time for selected Zone is: %s  \n", zdt.format(format));
		}
	}

	private static void getZonedDateTime(String[] args) throws Exception {
		if (args.length == 0) {
			listOftimeZones.add(timeZone);
		} else {
			for (String zone : ZoneId.getAvailableZoneIds()) {
				if (zone.toLowerCase().contains(args[0].toLowerCase())) {
					listOftimeZones.add(timeZone.withZoneSameInstant(ZoneId.of(zone)));
				}
			}
			if (listOftimeZones.isEmpty()) {
				throw new Exception("Wrong  Zone name " + args[0]);
			}
		}
	}
}
