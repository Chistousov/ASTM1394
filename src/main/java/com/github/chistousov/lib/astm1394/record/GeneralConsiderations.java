package com.github.chistousov.lib.astm1394.record;

import java.time.format.DateTimeFormatter;

/**
 * <p>
 * Stores common constants
 * (Хранит общие константы)
 * </p>
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public class GeneralConsiderations {
    
    private GeneralConsiderations() {}

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("uuuuMMddHHmmss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.BASIC_ISO_DATE;

}
