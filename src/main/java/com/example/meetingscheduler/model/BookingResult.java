package com.example.meetingscheduler.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.meetingscheduler.utilities.TimeUtils.checkOverlap;
import static com.example.meetingscheduler.utilities.TimeUtils.isValid;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

public class BookingResult {

    private final WorkHours officeWorkHours;
    private final List<Booking> bookings;

    public BookingResult(WorkHours officeWorkHours, List<Booking> bookings) {
        this.officeWorkHours = officeWorkHours;
        this.bookings = bookings;
    }

    public String toJson() {
        final StringBuilder stringBuilder = new StringBuilder();
        final String separator = System.getProperty("line.separator");
        final List<Booking> uniqueBookings = findNotOverlappingBookings(bookings);
        final Map<LocalDate, List<Booking>> groupedByDate = groupByDate(uniqueBookings);

        groupedByDate.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(item -> {
                    LocalDate date = item.getKey();
                    stringBuilder.append(date).append(separator);

                    item.getValue().stream().forEach(b -> {
                        stringBuilder.append(b.getTimeFrom().toLocalTime().toString()).append(" ");
                        stringBuilder.append(b.getTimeTo().toLocalTime().toString()).append(" ");
                        stringBuilder.append(b.getEmployeeId());
                        stringBuilder.append(separator);
                    });
                });

        return stringBuilder.toString();
    }

    private List<Booking> findNotOverlappingBookings(List<Booking> bookings) {
        List<Booking> nonOverlapping = new ArrayList<>();
        bookings.sort(Comparator.comparing(Booking::getRequestDateTime));

        bookings.stream().forEach(booking -> {
            if (!isValid(booking, officeWorkHours)) {
                return;
            }

            if (!isOverlappingWith(booking, nonOverlapping)) {
                nonOverlapping.add(booking);
            }
        });

        return nonOverlapping;
    }

    private boolean isOverlappingWith(Booking booking, List<Booking> nonOverlapping) {
        return nonOverlapping.stream().filter(item -> checkOverlap(item.getTimeFrom().toLocalTime(), item.getTimeTo().toLocalTime(),
                booking.getTimeFrom().toLocalTime(), booking.getTimeTo().toLocalTime())).findAny().isPresent();
    }

    private Map<LocalDate, List<Booking>> groupByDate(List<Booking> bookings) {
        final Map<LocalDate, List<Booking>> groupedByDate;
        groupedByDate = bookings.stream().collect(Collectors.groupingBy(key -> key.getTimeFrom().toLocalDate(), mapping(Function.identity(), toList())));

        return groupedByDate;
    }
}
