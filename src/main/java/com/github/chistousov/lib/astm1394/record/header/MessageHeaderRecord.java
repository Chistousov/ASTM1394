package com.github.chistousov.lib.astm1394.record.header;

import java.time.LocalDateTime;

import com.github.chistousov.lib.astm1394.record.Component;
import com.github.chistousov.lib.astm1394.record.GeneralConsiderations;
import com.github.chistousov.lib.astm1394.record.Record;
import com.github.chistousov.lib.astm1394.record.RecordType;

/**
 * <p>
 * The header must contain identifiers for both the sender and the recipient. 
 * A message header is a zero-level record that must be followed by a message delimiter record 
 * at some point before the session is terminated or another header record is sent. 
 * This entry type must always be the first entry in a transfer.
 * (Заголовок должен содержать идентификаторы как отправителя, так и получателя. 
 * Заголовок сообщения - это запись нулевого уровня, за которой в какой-то момент должна 
 * следовать запись-ограничитель сообщения перед завершением сеанса или передачей другой записи заголовка. 
 * Этот тип записи всегда должен быть первой записью в передаче.)
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public class MessageHeaderRecord extends Record {

    /*
     * Это уникальный номер или другой идентификатор, который однозначно идентифицирует передачу для использования в сетевых системах, которые определили протоколы подтверждения, выходящие за рамки этого документа. Обратите внимание, что это третье поле.
     */
    private Component<String> messageControlId;
    

    /*
     * Это пароль уровня безопасности / доступа, согласованный отправителем и получателем. Если эта проверка безопасности не удалась, передача будет прервана, а отправитель будет уведомлен о нарушении доступа.
     */
    private Component<String> accessPassword;

    /*
     * Это поле предназначено для определения производителя / инструмента (ов), относящегося к данной строке. Используя повторение и / или разделители компонентов, это поле может отражать версии программного обеспечения или прошивки, несколько инструментов, доступных на линии, и т. Д.
     */
    private Component<String> senderNameOrId;

    /*
     * Это текстовое значение должно содержать почтовый адрес отправителя.
     */
    private Component<String> senderStreetAddress;

    /*
     * Это поле в настоящее время не используется, но зарезервировано для использования в будущем.
     */
    private Component<String> reservedField;

    /*
     * В этом поле указывается номер телефона для голосовой связи с отправителем.
     */
    private Component<String> senderTelephoneNumber;

    /*
     * Это поле содержит любые характеристики отправителя, такие как четность, контрольные суммы, дополнительные протоколы и т. Д., Необходимые для установления канала связи с отправителем.
     */
    private Component<String> characteristicsOfSender;

    /*
     * Это текстовое значение включает имя или другой идентификатор получателя. Его цель - убедиться, что передача действительно предназначена для получателя.
     */
    private Component<String> receiverId;
     
    /*
     * Это текстовое поле должно содержать любые комментарии или специальные инструкции, относящиеся к последующим передаваемым записям.
     */
    private Component<String> commentOrSpecialInstructions;

    /*
     * Идентификатор обработки указывает, как это сообщение
     */
    private Component<ProcessingId> processingId; 

    /*
     * Это значение определяет уровень версии спецификации.
     */
    private Component<String> versionNumber;

    /*
     * Это поле содержит дату и время, когда сообщение было создано с использованием формата, указанного в Разделе 5.6.2.
     */
    private Component<LocalDateTime> dateAndTimeOfMessage; 


	/**
	 * <p>
	 * The header must contain identifiers for both the sender and the recipient. 
	 * A message header is a zero-level record that must be followed by a message 
	 * delimiter record at some point before the session is terminated or another 
	 * header record is sent. This entry type must always be the first entry in a transfer.
	 * (Заголовок должен содержать идентификаторы как отправителя, так и получателя. 
	 * Заголовок сообщения - это запись нулевого уровня, за которой в какой-то момент 
	 * должна следовать запись-ограничитель сообщения перед завершением сеанса или 
	 * передачей другой записи заголовка. Этот тип записи всегда должен быть первой записью в передаче.)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     * @param fieldDelimiter One valid character, as defined in section 5.1, with the exception of Latin-1(13) (carriage return), must separate adjacent fields. The field separator is variable and is defined in the message header. The same delimiter must be used in all entries following the header and preceding the message terminator entry. (Один допустимый символ, как определено в разделе 5.1, за исключением Latin-1 (13) (возврат каретки), должен разделять соседние поля. Разделитель полей является переменным и определяется в заголовке сообщения. Один и тот же разделитель должен использоваться во всех записях, следующих за заголовком и предшествующих записи терминатора сообщения.)
	 * @param repeatDelimiter A repeating separator is a single valid character as defined in Section 5.1, except for Latin-1 (13) and the value for the field separator defined in Section 5.4.2. The repeat separator must be defined in the message header and is used to separate variable descriptor numbers for fields containing parts of equal elements of the same set. (Повторяющийся разделитель - это один допустимый символ, как определено в Разделе 5.1, за исключением Latin-1 (13) и значения для разделителя полей, определенного в Разделе 5.4.2. Разделитель повтора должен быть определен в заголовке сообщения и используется для разделения переменных номеров дескрипторов для полей, содержащих части равных элементов одного и того же набора.)
	 * @param componentDelimiter The component separator is a single valid character as defined in Section 5.1, except for Latin-1 (13) and field separator and repeat values. The component separator is used to separate the data elements of fields of a hierarchical or qualifying nature. For example, street, city, state, zip code, etc. in the address field will be separated by component delimiters. (Разделитель компонентов - это один допустимый символ, как определено в Разделе 5.1, за исключением Latin-1 (13) и значений разделителя поля и повтора. Разделитель компонентов используется для разделения элементов данных полей иерархического или квалификационного характера. Например, улица, город, штат, почтовый индекс и т. Д. В адресном поле будут разделены разделителями компонентов.)
	 * @param escapeDelimiter The escape delimiter is a single valid character as defined in Section 5.1, except for Latin-1 (13) and field, repeat, and component delimiter values. The escape delimiter is used in text fields to indicate special operations. The use of a branch terminator is optional and may be used or ignored at the discretion of the transmitter or receiver. However, all applications must accept the escape delimiter and use it to correctly parse fields in a record. (Разделитель escape - это один допустимый символ, как определено в Разделе 5.1, за исключением Latin-1 (13) и значений разделителей field, repeat и компонентов. Разделитель escape используется в текстовых полях для обозначения особых операций. Применение ограничителя перехода не является обязательным и может использоваться или игнорироваться по усмотрению передатчика или приемника. Однако все приложения должны принимать escape-разделитель и использовать его для правильного анализа полей в записи.)
     */
	public MessageHeaderRecord(String fieldDelimiter, String repeatDelimiter, String componentDelimiter, String escapeDelimiter){
		super(14, RecordType.H, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);

		setField(getRepeatDelimiter().getValue() + getComponentDelimiter().getValue() + getEscapeDelimiter().getValue(), 1);

	}

	/**
	 * <p>
	 * The header must contain identifiers for both the sender and the recipient. A message header is a zero-level 
	 * record that must be followed by a message delimiter record at some point before the session is terminated 
	 * or another header record is sent. This entry type must always be the first entry in a transfer.
	 * (Заголовок должен содержать идентификаторы как отправителя, так и получателя. Заголовок сообщения - это запись 
	 * нулевого уровня, за которой в какой-то момент должна следовать запись-ограничитель сообщения перед завершением 
	 * сеанса или передачей другой записи заголовка. Этот тип записи всегда должен быть первой записью в передаче.)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     * @param record record string
     */
	public MessageHeaderRecord(String recordString) throws IllegalArgumentException {
        super(recordString, 14, RecordType.H, String.valueOf(recordString.charAt(1)), String.valueOf(recordString.charAt(2)), String.valueOf(recordString.charAt(3)), String.valueOf(recordString.charAt(4)));
        
        if(recordString.charAt(0) != 'H'){
            throw new IllegalArgumentException("The message does not start with H, so it is not a message header");
        }

		setField(getRepeatDelimiter().getValue() + getComponentDelimiter().getValue() + getEscapeDelimiter().getValue(), 1);


        this.messageControlId = new Component<>(String.class, getField(2));
        this.accessPassword = new Component<>(String.class, getField(3));
        this.senderNameOrId = new Component<>(String.class, getField(4));
        this.senderStreetAddress = new Component<>(String.class, getField(5));
        this.reservedField = new Component<>(String.class, getField(6));
        this.senderTelephoneNumber =  new Component<>(String.class, getField(7));
        this.characteristicsOfSender =  new Component<>(String.class, getField(8));
        this.receiverId = new Component<>(String.class, getField(9));
        this.commentOrSpecialInstructions = new Component<>(String.class, getField(10));

        this.processingId = new Component<>(() -> ProcessingId.getBy(getField(11)), ProcessingId::getIdForComponent);

        this.versionNumber = new Component<>(String.class, getField(12));
        this.dateAndTimeOfMessage = new Component<>(LocalDateTime.class, getField(13));

    }


	/**
	 * <p>
	 * This is a unique number or other identifier that uniquely identifies a transmission for 
	 * use in network systems that have defined acknowledgment protocols that are outside the scope 
	 * of this document. Note that this is the third field.
	 * (Это уникальный номер или другой идентификатор, который однозначно идентифицирует передачу для 
	 * использования в сетевых системах, которые определили протоколы подтверждения, выходящие за рамки 
	 * этого документа. Обратите внимание, что это третье поле.)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getMessageControlId() {
		return messageControlId.getValue();
	}

	/**
	 * <p>
	 * This is a unique number or other identifier that uniquely identifies a transmission for 
	 * use in network systems that have defined acknowledgment protocols that are outside the scope 
	 * of this document. Note that this is the third field.
	 * (Это уникальный номер или другой идентификатор, который однозначно идентифицирует передачу для 
	 * использования в сетевых системах, которые определили протоколы подтверждения, выходящие за рамки 
	 * этого документа. Обратите внимание, что это третье поле.)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setMessageControlId(String messageControlId) {
		Component<String> messageControlIdComponent = new Component<>(String.class, messageControlId);
		setField(messageControlIdComponent.toString(), 2);
		this.messageControlId = messageControlIdComponent;
	}

	/**
	 * <p>
	 * This is the security/access level password agreed between sender and receiver. 
	 * If this security check fails, the transmission will be aborted and the sender 
	 * will be notified of the access violation.
	 * (Это пароль уровня безопасности / доступа, согласованный отправителем и получателем. 
	 * Если эта проверка безопасности не удалась, передача будет прервана, а отправитель будет 
	 * уведомлен о нарушении доступа.)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getAccessPassword() {
		return accessPassword.getValue();
	}

	/**
	 * <p>
	 * This is the security/access level password agreed between sender and receiver. 
	 * If this security check fails, the transmission will be aborted and the sender 
	 * will be notified of the access violation.
	 * (Это пароль уровня безопасности / доступа, согласованный отправителем и получателем. 
	 * Если эта проверка безопасности не удалась, передача будет прервана, а отправитель будет 
	 * уведомлен о нарушении доступа.)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setAccessPassword(String accessPassword) {
		Component<String> accessPasswordComponent = new Component<>(String.class, accessPassword);
		setField(accessPasswordComponent.toString(), 3);
		this.accessPassword = accessPasswordComponent;
	}


	/**
	 * <p>
	 * This field is for identifying the manufacturer/tool(s) related to a given line. Using repetition and/or component 
	 * separators, this field can reflect software or firmware versions, multiple tools available on line, etc.
	 * (Это поле предназначено для определения производителя / инструмента (ов), относящегося к данной строке. 
	 * Используя повторение и / или разделители компонентов, это поле может отражать версии программного обеспечения 
	 * или прошивки, несколько инструментов, доступных на линии, и т. Д.)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getSenderNameOrId() {
		return senderNameOrId.getValue();
	}

	/**
	 * <p>
	 * This field is for identifying the manufacturer/tool(s) related to a given line. Using repetition and/or component 
	 * separators, this field can reflect software or firmware versions, multiple tools available on line, etc.
	 * (Это поле предназначено для определения производителя / инструмента (ов), относящегося к данной строке. 
	 * Используя повторение и / или разделители компонентов, это поле может отражать версии программного обеспечения 
	 * или прошивки, несколько инструментов, доступных на линии, и т. Д.)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setSenderNameOrId(String senderNameOrId) {
		Component<String> senderNameOrIdComponent = new Component<>(String.class, senderNameOrId);
		setField(senderNameOrIdComponent.toString(), 4);
		this.senderNameOrId = senderNameOrIdComponent;
	}

	/**
	 * <p>
	 * This text value must contain the sender's email address.
	 * (Это текстовое значение должно содержать почтовый адрес отправителя.)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getSenderStreetAddress() {
		return senderStreetAddress.getValue();
	}

	/**
	 * <p>
	 * This text value must contain the sender's email address.
	 * (Это текстовое значение должно содержать почтовый адрес отправителя.)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setSenderStreetAddress(String senderStreetAddress) {
		Component<String> senderStreetAddressComponent = new Component<>(String.class, senderStreetAddress);
		setField(senderStreetAddressComponent.toString(), 5);
		this.senderStreetAddress = senderStreetAddressComponent;
	}

	/**
	 * <p>
	 * This field is not currently used but is reserved for future use.
	 * Это поле в настоящее время не используется, но зарезервировано для использования в будущем.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getReservedField() {
		return reservedField.getValue();
	}

	/**
	 * <p>
	 * This field is not currently used but is reserved for future use.
	 * Это поле в настоящее время не используется, но зарезервировано для использования в будущем.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setReservedField(String reservedField) {
		Component<String> reservedFieldComponent = new Component<>(String.class, reservedField);
		setField(reservedFieldComponent.toString(), 6);
		this.reservedField = reservedFieldComponent;
	}

	/**
	 * <p>
	 * This field specifies the phone number for voice communication with the sender.
	 * (В этом поле указывается номер телефона для голосовой связи с отправителем.)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getSenderTelephoneNumber() {
		return senderTelephoneNumber.getValue();
	}

	/**
	 * <p>
	 * This field specifies the phone number for voice communication with the sender.
	 * (В этом поле указывается номер телефона для голосовой связи с отправителем.)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setSenderTelephoneNumber(String senderTelephoneNumber) {
		Component<String> senderTelephoneNumberComponent = new Component<>(String.class, senderTelephoneNumber);
		setField(senderTelephoneNumberComponent.toString(), 7);
		this.senderTelephoneNumber = senderTelephoneNumberComponent;
	}

	/**
	 * <p>
	 * This field contains any characteristics of the sender such as parity, checksums, additional protocols, etc. required to establish a communication channel with the sender.
	 * (Это поле содержит любые характеристики отправителя, такие как четность, контрольные суммы, дополнительные протоколы и т. Д., Необходимые для установления канала связи с отправителем.)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getCharacteristicsOfSender() {
		return characteristicsOfSender.getValue();
	}

	/**
	 * <p>
	 * This field contains any characteristics of the sender such as parity, checksums, additional protocols, etc. required to establish a communication channel with the sender.
	 * (Это поле содержит любые характеристики отправителя, такие как четность, контрольные суммы, дополнительные протоколы и т. Д., Необходимые для установления канала связи с отправителем.)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setCharacteristicsOfSender(String characteristicsOfSender) {
		Component<String> characteristicsOfSenderComponent = new Component<>(String.class, characteristicsOfSender);
		setField(characteristicsOfSenderComponent.toString(), 8);
		this.characteristicsOfSender = characteristicsOfSenderComponent;
	}

	/**
	 * <p>
	 * This text value includes the name or other identifier of the recipient. Its purpose is to make sure that the transmission is really intended for the recipient.
	 * (Это текстовое значение включает имя или другой идентификатор получателя. Его цель - убедиться, что передача действительно предназначена для получателя.)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getReceiverId() {
		return receiverId.getValue();
	}

	/**
	 * <p>
	 * This text value includes the name or other identifier of the recipient. Its purpose is to make sure that the transmission is really intended for the recipient.
	 * (Это текстовое значение включает имя или другой идентификатор получателя. Его цель - убедиться, что передача действительно предназначена для получателя.)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setReceiverId(String receiverId) {
		Component<String> receiverIdComponent = new Component<>(String.class, receiverId);
		setField(receiverIdComponent.toString(), 9);
		this.receiverId = receiverIdComponent;
	}

    /**
	 * <p>
	 * This text field should contain any comments or special instructions related to subsequent transmitted records.
	 * (Это текстовое поле должно содержать любые комментарии или специальные инструкции, относящиеся к последующим передаваемым записям.)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getCommentOrSpecialInstructions() {
		return commentOrSpecialInstructions.getValue();
	}

	/**
	 * <p>
	 * This text field should contain any comments or special instructions related to subsequent transmitted records.
	 * (Это текстовое поле должно содержать любые комментарии или специальные инструкции, относящиеся к последующим передаваемым записям.)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setCommentOrSpecialInstructions(String commentOrSpecialInstructions) {
		Component<String> commentOrSpecialInstructionsComponent = new Component<>(String.class, commentOrSpecialInstructions);
		setField(commentOrSpecialInstructionsComponent.toString(), 10);
		this.commentOrSpecialInstructions = commentOrSpecialInstructionsComponent;
	}

	/**
	 * <p>
	 * The processing ID indicates how this message
	 * (Идентификатор обработки указывает, как это сообщение)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public ProcessingId getProcessingId() {
		return processingId.getValue();
	}

	/**
	 * <p>
	 * The processing ID indicates how this message
	 * (Идентификатор обработки указывает, как это сообщение)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setProcessingId(ProcessingId processingId) {
		Component<ProcessingId> processingIdComponent = new Component<>(
			() -> processingId, ProcessingId::getIdForComponent
		);
		setField(processingIdComponent.toString(), 11);
		this.processingId = processingIdComponent;
	}

	/**
	 * <p>
	 * This value specifies the revision level of the specification.
	 * (Это значение определяет уровень версии спецификации.)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getVersionNumber() {
		return versionNumber.getValue();
	}

	/**
	 * <p>
	 * This value specifies the revision level of the specification.
	 * (Это значение определяет уровень версии спецификации.)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setVersionNumber(String versionNumber) {
		Component<String> versionNumberComponent = new Component<>(String.class, versionNumber);
		setField(versionNumberComponent.toString(), 12);
		this.versionNumber = versionNumberComponent;
	}


	/**
	 * <p>
	 * This field contains the date and time the message was created using the format specified in Section 5.6.2.
	 * (Это поле содержит дату и время, когда сообщение было создано с использованием формата, указанного в Разделе 5.6.2.)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public LocalDateTime getDateAndTimeOfMessage() {
		return dateAndTimeOfMessage.getValue();
	}

	/**
	 * <p>
	 * This field contains the date and time the message was created using the format specified in Section 5.6.2.
	 * (Это поле содержит дату и время, когда сообщение было создано с использованием формата, указанного в Разделе 5.6.2.)
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setDateAndTimeOfMessage(LocalDateTime dateAndTimeOfMessage) {
		Component<LocalDateTime> dateAndTimeOfMessageComponent = new Component<>(LocalDateTime.class, dateAndTimeOfMessage.format(GeneralConsiderations.DATE_TIME_FORMATTER));
		setField(dateAndTimeOfMessageComponent.toString(), 13);
		this.dateAndTimeOfMessage = dateAndTimeOfMessageComponent;
	}

	

}
