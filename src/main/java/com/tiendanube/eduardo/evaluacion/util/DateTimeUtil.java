package com.tiendanube.eduardo.evaluacion.util;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    public static String getCurrentDateTimeFormatted() {
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.of("-03:00"))
                .truncatedTo(ChronoUnit.MILLIS);
        return now.format(FORMATTER);
    }

}

