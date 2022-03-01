package com.github.chistousov.lib.astm1394.record.request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.github.chistousov.lib.astm1394.record.Component;
import com.github.chistousov.lib.astm1394.record.GeneralConsiderations;
import com.github.chistousov.lib.astm1394.record.IWithComments;
import com.github.chistousov.lib.astm1394.record.Record;
import com.github.chistousov.lib.astm1394.record.RecordType;
import com.github.chistousov.lib.astm1394.record.comment.CommentRecord;

/**
 * <p>
 * The request information record is used by either the clinical device or the information system to remotely request information from the reciprocating system.
NOTE. Only one request record can remain at a time; the recipient of the request record must terminate the request when it is completed with a message terminator record, or the sender must cancel the request before sending the second logical request.
 * (Информационная запись запроса используется либо клиническим прибором, либо информационной системой для удаленного запроса информации из реципрокной системы.
ПРИМЕЧАНИЕ. Одновременно может оставаться только одна запись запроса; получатель записи запроса должен завершить запрос, когда он завершен, с помощью записи терминатора сообщения, или отправитель должен отменить запрос перед отправкой второго логического запроса.)
</p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public class RequestInformationRecord<T extends CommentRecord> extends Record implements IWithComments<T> {
	

	/*
	 * Это поле может содержать три или более компонентов для определения диапазона критериев выбора пациентов / образцов / производителей. Первый компонент - это идентификационный номер пациента информационной системы. Второй компонент - это идентификационный номер образца информационной системы. Любые дополнительные компоненты определяются производителем и используются в запросе информации о подрезультатах (то есть, отдельный изолятор / батарея для номера образца). Эти компоненты зависят от позиции. Список идентификаторов образцов может быть запрошен с помощью разделителя повторов для разделения идентификаторов.
	 * Когда вводится ВСЕ и информационная система отправляет запись запроса, под этим понимаются все результаты образцов, упорядоченные запрашивающей системой. Если прибор создает запись запроса, это означает, что все заказанные демографические данные и тесты должны быть отправлены на прибор в это время. Затем запрос интерпретируется для указанного подмножества образцов с дальнейшими изменениями в соответствии со спецификациями испытаний и диапазонами дат, как описано ниже.
	 * Эта спецификация не определяет, как долго данные должны храниться в приборе, и не требует, чтобы прибор предоставлял услуги поиска, подразумеваемые некоторым содержанием полей. Соответствующий ответ на запрос результатов - это просто возврат подмножества результатов, которые в настоящее время находятся в хранилище и могут быть практически получены.
	 */
	private Component<String> startingRangeIdNumber;

	/*
	 * Это поле похоже на поле, описанное в Разделе 11.3. Если запрашивается единичный результат или образец демографических данных или порядок испытаний, то это поле можно оставить пустым.
	 */
	private Component<String> endingRangeIDNumber;

	/*
	 * Это поле описано в разделе 5.6.1. В качестве альтернативы это поле может содержать несколько кодов, разделенных повторяющимися разделителями, или поле может содержать текст ВСЕ, который означает запрос всех результатов по всем тестам или батареям для пациентов / образцов / тестов, определенных в разделах 11.3 и 11.4, и в пределах дат. описано в разделах 11.6 и 11.7.
	 */
	private Component<String> universalTestId;

	/*
	 * Укажите, относятся ли дата и временные рамки, указанные в разделах 11.7 и 11.8, к дате сбора или заказа образца (см. Раздел 8.4.8) или дате испытания (см. Раздел 8.4.23): S указывает дату сбора образца R указывает на результат теста Дата. Если ничего не введено, критерием даты считается дата проверки результата.
	 */
	private Component<String> natureOfRequestTimeLimits;

	/*
	 * Это поле должно представлять либо начальную (самую раннюю) дату и время, для которых запрашиваются результаты, либо отдельные дату и время. Поле может содержать одну дату и время или несколько дат и времени, разделенных повторяющимися разделителями. Каждая дата и время должны быть представлены, как указано в разделе 5.6.2. Если дата и время не указаны, прибор должен предполагать, что информационная система хочет, чтобы результаты уходили в прошлое, насколько это возможно, и согласовывались с критериями, указанными в других полях.
	 */
	private Component<LocalDateTime> beginningRequestResultsDateAndTime;

	/*
	 * Это поле, если оно не пустое, указывает конечную или самую последнюю (или самую последнюю) дату и время, для которых запрашиваются результаты. Дата и время должны быть представлены в соответствии с Разделом 5.6.2.
	 */
	private Component<LocalDateTime> endingRequestResultsDateAndTime; 

	/*
	 * В этом поле указывается конкретный врач, запрашивающий результаты. Личность запрашивающего врача фиксируется, как указано в разделе 5.6.6.
	 */
	private Component<String> requestingPhysicianName;

	/*
	 * Это поле указано в разделе 5.6.3.
	 */
	private Component<String> requestingPhysicianTelephoneNumber;

	/*
	 * Это определяемое пользователем поле.
	 */
	private Component<String> userFieldNumber1;

	/*
	 * Это определяемое пользователем поле.
	 */
	private Component<String> userFieldNumber2;

	/*
	 * коды статусов
	 */
	private Component<RequestInformationStatusCodes> requestInformationStatusCode;

	public RequestInformationRecord(String fieldDelimiter, String repeatDelimiter, String componentDelimiter, String escapeDelimiter){
        super("", 13, RecordType.Q, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
	}

	public RequestInformationRecord(String recordString, String fieldDelimiter, String repeatDelimiter, String componentDelimiter, String escapeDelimiter) throws IllegalArgumentException {
        super(recordString, 13, RecordType.Q, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);

        if(recordString.charAt(0) != 'Q'){
            throw new IllegalArgumentException("Сообщение не начинается с Q, поэтому не является записью с запрашиваемой информацией");
        }
        setSequenceNumber(getField(1));

		this.startingRangeIdNumber = new Component<>(String.class, getField(2)); 
		this.endingRangeIDNumber = new Component<>(String.class, getField(3)); 
		this.universalTestId = new Component<>(String.class, getField(4));
		this.natureOfRequestTimeLimits = new Component<>(String.class, getField(5));
		this.beginningRequestResultsDateAndTime = new Component<>(LocalDateTime.class, getField(6));
		this.endingRequestResultsDateAndTime = new Component<>(LocalDateTime.class, getField(7));
		this.requestingPhysicianName = new Component<>(String.class, getField(8));
		this.requestingPhysicianTelephoneNumber = new Component<>(String.class, getField(9));
		this.userFieldNumber1 = new Component<>(String.class, getField(10));
		this.userFieldNumber2 = new Component<>(String.class, getField(11));
		this.requestInformationStatusCode = new Component<>(() -> RequestInformationStatusCodes.getBy(getField(12)), RequestInformationStatusCodes::getIdForComponent);
	}

	@Override
	public String toString() {
		StringBuilder returnStr = new StringBuilder();
		
		returnStr.append(this.getRecord());

		if(this.commentRecords != null){
			this.commentRecords.forEach(commentRecord -> returnStr.append(commentRecord.toString()));
		}

		return returnStr.toString();
	}

	/**
	 * <p>
	 * Это поле может содержать три или более компонентов для определения диапазона критериев выбора пациентов / образцов / производителей. Первый компонент - это идентификационный номер пациента информационной системы. Второй компонент - это идентификационный номер образца информационной системы. Любые дополнительные компоненты определяются производителем и используются в запросе информации о подрезультатах (то есть, отдельный изолятор / батарея для номера образца). Эти компоненты зависят от позиции. Список идентификаторов образцов может быть запрошен с помощью разделителя повторов для разделения идентификаторов.
	 * Когда вводится ВСЕ и информационная система отправляет запись запроса, под этим понимаются все результаты образцов, упорядоченные запрашивающей системой. Если прибор создает запись запроса, это означает, что все заказанные демографические данные и тесты должны быть отправлены на прибор в это время. Затем запрос интерпретируется для указанного подмножества образцов с дальнейшими изменениями в соответствии со спецификациями испытаний и диапазонами дат, как описано ниже.
	 * Эта спецификация не определяет, как долго данные должны храниться в приборе, и не требует, чтобы прибор предоставлял услуги поиска, подразумеваемые некоторым содержанием полей. Соответствующий ответ на запрос результатов - это просто возврат подмножества результатов, которые в настоящее время находятся в хранилище и могут быть практически получены.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getStartingRangeIdNumber() {
		return startingRangeIdNumber.getValue();
	}

	/**
	 * <p>
	 * Это поле может содержать три или более компонентов для определения диапазона критериев выбора пациентов / образцов / производителей. Первый компонент - это идентификационный номер пациента информационной системы. Второй компонент - это идентификационный номер образца информационной системы. Любые дополнительные компоненты определяются производителем и используются в запросе информации о подрезультатах (то есть, отдельный изолятор / батарея для номера образца). Эти компоненты зависят от позиции. Список идентификаторов образцов может быть запрошен с помощью разделителя повторов для разделения идентификаторов.
	 * Когда вводится ВСЕ и информационная система отправляет запись запроса, под этим понимаются все результаты образцов, упорядоченные запрашивающей системой. Если прибор создает запись запроса, это означает, что все заказанные демографические данные и тесты должны быть отправлены на прибор в это время. Затем запрос интерпретируется для указанного подмножества образцов с дальнейшими изменениями в соответствии со спецификациями испытаний и диапазонами дат, как описано ниже.
	 * Эта спецификация не определяет, как долго данные должны храниться в приборе, и не требует, чтобы прибор предоставлял услуги поиска, подразумеваемые некоторым содержанием полей. Соответствующий ответ на запрос результатов - это просто возврат подмножества результатов, которые в настоящее время находятся в хранилище и могут быть практически получены.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setStartingRangeIdNumber(String startingRangeIdNumber) {
		Component<String> startingRangeIdNumberComponent = new Component<>(String.class, startingRangeIdNumber);
		setField(startingRangeIdNumberComponent.toString(), 2);
		this.startingRangeIdNumber = startingRangeIdNumberComponent;
	}

	/**
	 * <p>
	 * Это поле похоже на поле, описанное в Разделе 11.3. Если запрашивается единичный результат или образец демографических данных или порядок испытаний, то это поле можно оставить пустым.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getEndingRangeIDNumber() {
		return endingRangeIDNumber.getValue();
	}

	/**
	 * <p>
	 * Это поле похоже на поле, описанное в Разделе 11.3. Если запрашивается единичный результат или образец демографических данных или порядок испытаний, то это поле можно оставить пустым.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setEndingRangeIDNumber(String endingRangeIDNumber) {
		Component<String> endingRangeIDNumberComponent = new Component<>(String.class, endingRangeIDNumber);
		setField(endingRangeIDNumberComponent.toString(), 3);
		this.endingRangeIDNumber = endingRangeIDNumberComponent;
	}

	/**
	 * <p>
	 * Это поле описано в разделе 5.6.1. В качестве альтернативы это поле может содержать несколько кодов, разделенных повторяющимися разделителями, или поле может содержать текст ВСЕ, который означает запрос всех результатов по всем тестам или батареям для пациентов / образцов / тестов, определенных в разделах 11.3 и 11.4, и в пределах дат. описано в разделах 11.6 и 11.7.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getUniversalTestId() {
		return universalTestId.getValue();
	}

	/**
	 * <p>
	 * Это поле описано в разделе 5.6.1. В качестве альтернативы это поле может содержать несколько кодов, разделенных повторяющимися разделителями, или поле может содержать текст ВСЕ, который означает запрос всех результатов по всем тестам или батареям для пациентов / образцов / тестов, определенных в разделах 11.3 и 11.4, и в пределах дат. описано в разделах 11.6 и 11.7.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setUniversalTestId(String universalTestId) {
		Component<String> universalTestIdComponent = new Component<>(String.class, universalTestId);
		setField(universalTestIdComponent.toString(), 4);
		this.universalTestId = universalTestIdComponent;
	}

	/**
	 * <p>
	 * Укажите, относятся ли дата и временные рамки, указанные в разделах 11.7 и 11.8, к дате сбора или заказа образца (см. Раздел 8.4.8) или дате испытания (см. Раздел 8.4.23): S указывает дату сбора образца R указывает на результат теста Дата. Если ничего не введено, критерием даты считается дата проверки результата.
	 *  </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getNatureOfRequestTimeLimits() {
		return natureOfRequestTimeLimits.getValue();
	}

	/**
	 * <p>
	 * Укажите, относятся ли дата и временные рамки, указанные в разделах 11.7 и 11.8, к дате сбора или заказа образца (см. Раздел 8.4.8) или дате испытания (см. Раздел 8.4.23): S указывает дату сбора образца R указывает на результат теста Дата. Если ничего не введено, критерием даты считается дата проверки результата.
	 *  </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setNatureOfRequestTimeLimits(String natureOfRequestTimeLimits) {
		Component<String> natureOfRequestTimeLimitsComponent = new Component<>(String.class, natureOfRequestTimeLimits);
		setField(natureOfRequestTimeLimitsComponent.toString(), 5);
		this.natureOfRequestTimeLimits = natureOfRequestTimeLimitsComponent;
	}

	/**
	 * <p>
	 * Это поле должно представлять либо начальную (самую раннюю) дату и время, для которых запрашиваются результаты, либо отдельные дату и время. Поле может содержать одну дату и время или несколько дат и времени, разделенных повторяющимися разделителями. Каждая дата и время должны быть представлены, как указано в разделе 5.6.2. Если дата и время не указаны, прибор должен предполагать, что информационная система хочет, чтобы результаты уходили в прошлое, насколько это возможно, и согласовывались с критериями, указанными в других полях.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public LocalDateTime getBeginningRequestResultsDateAndTime() {
		return beginningRequestResultsDateAndTime.getValue();
	}

	/**
	 * <p>
	 * Это поле должно представлять либо начальную (самую раннюю) дату и время, для которых запрашиваются результаты, либо отдельные дату и время. Поле может содержать одну дату и время или несколько дат и времени, разделенных повторяющимися разделителями. Каждая дата и время должны быть представлены, как указано в разделе 5.6.2. Если дата и время не указаны, прибор должен предполагать, что информационная система хочет, чтобы результаты уходили в прошлое, насколько это возможно, и согласовывались с критериями, указанными в других полях.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setBeginningRequestResultsDateAndTime(LocalDateTime beginningRequestResultsDateAndTime) {
		Component<LocalDateTime> beginningRequestResultsDateAndTimeComponent = new Component<>(LocalDateTime.class, beginningRequestResultsDateAndTime.format(GeneralConsiderations.DATE_TIME_FORMATTER));
		setField(beginningRequestResultsDateAndTimeComponent.toString(), 6);
		this.beginningRequestResultsDateAndTime = beginningRequestResultsDateAndTimeComponent;
	}

 	/**
	 * <p>
	 * Это поле, если оно не пустое, указывает конечную или самую последнюю (или самую последнюю) дату и время, для которых запрашиваются результаты. Дата и время должны быть представлены в соответствии с Разделом 5.6.2.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public LocalDateTime getEndingRequestResultsDateAndTime() {
		return endingRequestResultsDateAndTime.getValue();
	}

	/**
	 * <p>
	 * Это поле, если оно не пустое, указывает конечную или самую последнюю (или самую последнюю) дату и время, для которых запрашиваются результаты. Дата и время должны быть представлены в соответствии с Разделом 5.6.2.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setEndingRequestResultsDateAndTime(LocalDateTime endingRequestResultsDateAndTime) {
		Component<LocalDateTime> endingRequestResultsDateAndTimeComponent = new Component<>(LocalDateTime.class, endingRequestResultsDateAndTime.format(GeneralConsiderations.DATE_TIME_FORMATTER));
		setField(endingRequestResultsDateAndTimeComponent.toString(), 7);
		this.endingRequestResultsDateAndTime = endingRequestResultsDateAndTimeComponent;
	}

	/**
	 * <p>
	 * В этом поле указывается конкретный врач, запрашивающий результаты. Личность запрашивающего врача фиксируется, как указано в разделе 5.6.6.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getRequestingPhysicianName() {
		return requestingPhysicianName.getValue();
	}

	/**
	 * <p>
	 * В этом поле указывается конкретный врач, запрашивающий результаты. Личность запрашивающего врача фиксируется, как указано в разделе 5.6.6.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setRequestingPhysicianName(String requestingPhysicianName) {
		Component<String> requestingPhysicianNameComponent = new Component<>(String.class, requestingPhysicianName);
		setField(requestingPhysicianNameComponent.toString(), 8);
		this.requestingPhysicianName = requestingPhysicianNameComponent;
	}

	/**
	 * <p>
	 * Это поле указано в разделе 5.6.3.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getRequestingPhysicianTelephoneNumber() {
		return requestingPhysicianTelephoneNumber.getValue();
	}

	/**
	 * <p>
	 * Это поле указано в разделе 5.6.3.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setRequestingPhysicianTelephoneNumber(String requestingPhysicianTelephoneNumber) {
		Component<String> requestingPhysicianTelephoneNumberComponent = new Component<>(String.class, requestingPhysicianTelephoneNumber);
		setField(requestingPhysicianTelephoneNumberComponent.toString(), 9);
		this.requestingPhysicianTelephoneNumber = requestingPhysicianTelephoneNumberComponent;
	}

	/**
	 * <p>
	 * Это определяемое пользователем поле.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getUserFieldNumber1() {
		return userFieldNumber1.getValue();
	}

	/**
	 * <p>
	 * Это определяемое пользователем поле.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setUserFieldNumber1(String userFieldNumber1) {
		Component<String> userFieldNumber1Component = new Component<>(String.class, userFieldNumber1);
		setField(userFieldNumber1Component.toString(), 10);
		this.userFieldNumber1 = userFieldNumber1Component;
	}

	/**
	 * <p>
	 * Это определяемое пользователем поле.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getUserFieldNumber2() {
		return userFieldNumber2.getValue();
	}

	/**
	 * <p>
	 * Это определяемое пользователем поле.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setUserFieldNumber2(String userFieldNumber2) {
		Component<String> userFieldNumber2Component = new Component<>(String.class, userFieldNumber2);
		setField(userFieldNumber2Component.toString(), 11);
		this.userFieldNumber2 = userFieldNumber2Component;
	}

	/**
	 * <p>
	 * коды статусов
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public RequestInformationStatusCodes getRequestInformationStatusCode() {
		return requestInformationStatusCode.getValue();
	}

	/**
	 * <p>
	 * коды статусов
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setRequestInformationStatusCode(RequestInformationStatusCodes requestInformationStatusCode) {
		Component<RequestInformationStatusCodes> requestInformationStatusCodeComponent = new Component<>(
			() -> requestInformationStatusCode, RequestInformationStatusCodes::getIdForComponent
		);
		setField(requestInformationStatusCodeComponent.toString(), 12);
		this.requestInformationStatusCode = requestInformationStatusCodeComponent;
	}

	private List<T> commentRecords;
	
	@Override
	public void addCommentRecord(T commentRecord){
		if(this.commentRecords == null) {
			this.commentRecords = new ArrayList<>();
		}
		commentRecord.setSequenceNumber(String.valueOf(this.commentRecords.size() + 1));
		this.commentRecords.add(commentRecord);
	}
	
	@Override
	public List<T> getCommentRecords() {
		return commentRecords;
	}
}
