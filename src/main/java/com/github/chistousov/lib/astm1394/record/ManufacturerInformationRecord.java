package com.github.chistousov.lib.astm1394.record;

/**
 * <p>
 * This recording is provided solely for the individual use of the instrument or computer system manufacturer. 
 * It has no internal hierarchical level and can be inserted at any point except immediately after the entry of 
 * the end-of-message flag. It is recommended that you do not implement this entry type unless all other possibilities 
 * have been exhausted.
 * (Эта запись предоставляется исключительно для индивидуального использования производителем прибора или компьютерной системы. 
 * Он не имеет внутреннего иерархического уровня и может быть вставлен в любой точке, кроме как сразу после записи признака конца 
 * сообщения. Рекомендуется не реализовывать этот тип записи, если не исчерпаны все другие возможности.)
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public class ManufacturerInformationRecord extends Record {

    public ManufacturerInformationRecord(String fieldDelimiter, String repeatDelimiter, String componentDelimiter, String escapeDelimiter) {
        super("", 3, RecordType.M, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
    }

	public ManufacturerInformationRecord(String record, String fieldDelimiter, String repeatDelimiter, String componentDelimiter, String escapeDelimiter) throws IllegalArgumentException {
        super(record, 3, RecordType.M, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);

        if(record.charAt(0) != 'M'){
            throw new IllegalArgumentException("The message does not start with M, so it is not an information entry from the instrument");
        }
        setSequenceNumber(getField(1));
	}
}
