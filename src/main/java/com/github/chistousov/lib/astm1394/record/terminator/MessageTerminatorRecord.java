package com.github.chistousov.lib.astm1394.record.terminator;

import com.github.chistousov.lib.astm1394.record.Component;
import com.github.chistousov.lib.astm1394.record.Record;
import com.github.chistousov.lib.astm1394.record.RecordType;

/**
 * <p>
 * This is the last entry in the post. A header entry may be sent after this entry, marking the beginning of the second message.
 * (Это последняя запись в сообщении. Запись заголовка может быть передана после этой записи, означающей начало второго сообщения.)
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public class MessageTerminatorRecord extends Record {
	
	/*
	 * В этом поле приводится объяснение окончания сеанса.
	 */
	private Component<TerminationCode> terminationCode;
	 
	public MessageTerminatorRecord(String fieldDelimiter, String repeatDelimiter, String componentDelimiter, String escapeDelimiter) {
        super("", 3, RecordType.L, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
	}

	public MessageTerminatorRecord(String recordString, String fieldDelimiter, String repeatDelimiter, String componentDelimiter, String escapeDelimiter) throws IllegalArgumentException {
        super(recordString, 3, RecordType.L, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);

        if(recordString.charAt(0) != 'L'){
            throw new IllegalArgumentException("Сообщение не начинается с L, поэтому не является записью конца сообщения");
        }
        setSequenceNumber(getField(1));

		this.terminationCode = new Component<>(() -> TerminationCode.getBy(getField(2)), TerminationCode::getIdForComponent);
	}

	/**
	 * <p>
	 * В этом поле приводится объяснение окончания сеанса.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public TerminationCode getTerminationCode() {
		return terminationCode.getValue();
	}

		/**
	 * <p>
	 * В этом поле приводится объяснение окончания сеанса.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setTerminationCode(TerminationCode terminationCode) {
		Component<TerminationCode> terminationCodeComponent = new Component<>(
			() -> terminationCode, TerminationCode::getIdForComponent
		);
		setField(terminationCodeComponent.toString(), 2);
		this.terminationCode = terminationCodeComponent;
	}

	@Override
	public String toString() {
		return this.getRecord();
	}
}
