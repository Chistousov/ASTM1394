package com.github.chistousov.lib.astm1394.record.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.github.chistousov.lib.astm1394.record.Component;
import com.github.chistousov.lib.astm1394.record.GeneralConsiderations;
import com.github.chistousov.lib.astm1394.record.IWithComments;
import com.github.chistousov.lib.astm1394.record.Record;
import com.github.chistousov.lib.astm1394.record.RecordType;
import com.github.chistousov.lib.astm1394.record.UniversalTestIDField;
import com.github.chistousov.lib.astm1394.record.comment.CommentRecord;


/**
 * <p>
 * The test order record defines the attributes of a specific clinical device service 
 * request and contains all sample information. The information system will generate 
 * an order record to request a given test, battery, or test set. The information in 
 * an order record usually refers to a single sample. However, there is not always 
 * an unambiguous relationship between the sample and the ordered tests. Different test 
 * batteries are usually ordered in a different order, even though they may be done on 
 * the same sample. In this case, the pattern information is duplicated in each order record that uses that pattern.
 * (Запись заказа на тестирование определяет атрибуты конкретного запроса на услуги клинического прибора и 
 * содержит всю информацию о образцах. Информационная система сгенерирует запись заказа, чтобы запросить данный тест, 
 * батарею или набор тестов. Информация в записи заказа обычно относится к одному образцу. Однако не всегда существует 
 * однозначная связь между образцом и заказанными испытаниями. Разные батареи для испытаний обычно заказываются в разном
 *  порядке, даже если они могут быть выполнены на одном образце. В этом случае информация об образце дублируется в каждой 
 * записи заказа, в которой используется этот образец.)
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public class TestOrderRecord<T extends Record, T1 extends CommentRecord> extends Record implements IWithComments<T1> {
    


	/*
	 * Это текстовое поле должно представлять собой уникальный идентификатор образца, присвоенный информационной системой и возвращенный прибором. Если в образце имеется несколько компонентов, дополнительно идентифицирующих полученные из него культуры, эти идентификаторы компонентов будут следовать за идентификатором образца и будут разделены разделителями компонентов. Например, идентификатор образца может содержать номер образца, за которым следует номер изолята, номер лунки или чашки (например, 10435A ˆ 01 ˆ 64).
	 */
	private Component<String> specimenID;

	/*
	 * Это текстовое поле должно представлять уникальный идентификатор, присвоенный прибором, если он отличается от идентификатора информационной системы, и возвращаемый с результатами для использования при ссылке на любые результаты.
	 */
	private Component<String> instrumentSpecimenID;

	/*
	 * В этом поле должен использоваться универсальный идентификатор теста, как описано в разделе 5.6.1.
	 */
	private UniversalTestIDField[] universalTestID;

	/*
	 * Коды приоритета тестирования
	 */
	private List<Component<Priority>> priorities; 

	/*
	 * Содержимое этого поля должно быть представлено, как указано в разделе 5.6.2, и будет обозначать дату и время, когда заказ на тестирование следует считать заказанным. Обычно это дата и время регистрации заказа. Это дата и время, относительно которых следует рассматривать приоритеты. Если служба заказа желает, чтобы тест проводился в определенное время в будущем, например, тест должен быть проведен через два дня в 20:00, здесь должны быть указаны будущая дата и время. Обратите внимание, что здесь должны быть записаны данные заголовка сообщения, а также будущая дата и время. Кроме того, обратите внимание, что дата и время записи заголовка сообщения (см. Раздел 6.14) указывают время, когда приказ был передан на инструмент или от него.
	 */
	private Component<LocalDateTime> requestedOrOrderedDateAndTime;

	/*
	 * Это поле должно отображать фактическое время сбора или получения образца.
	 */
	private Component<LocalDateTime> specimenCollectionDateAndTime;

	/*
	 * Это поле должно содержать дату и время окончания сбора пробы по времени, например, 24-часового сбора мочи. Значение указывается в соответствии с разделом 5.6.2.
	 */
	private Component<LocalDateTime> collectionEndTime; 

	/*
	 * Это значение должно представлять общий объем образцов, таких как моча или другие объемные образцы, когда в прибор отправляется только аликвота. Единица измерения по умолчанию - миллилитры. Если единицы представлены явно, они должны быть отделены от числового значения разделителем компонентов, например, 300 g. Единицы должны соответствовать соглашениям, приведенным в разделе 5.6.4.
	 */
	private Component<String> collectionVolume;

	/*
	 * В этом поле должны быть указаны лицо и объект, в котором был взят образец. Если возникнут вопросы, касающиеся обстоятельств сбора образцов, с этим лицом свяжутся.
	 */ 
	private Component<String> collectorId;


	/*
	 * В этом поле должны быть указаны действия, которые необходимо предпринять в отношении образцов, которые сопровождают этот запрос или предшествуют ему.
	 */
	private Component<ActionCode> actionCode;

	/*
	 * Это поле, представляющее тест или код, должно указывать на любую особую опасность, связанную с образцом, например, пациент с гепатитом, подозрение на сибирскую язву.
	 */
	private Component<String> dangerCode;

	/*
	 * Здесь будет представлена дополнительная информация об образце, которая будет использоваться для сообщения такой информации, как количество вдыхаемого O2 для газов крови, точка менструального цикла для цервикального мазка Папаниколау или другие условия, которые влияют на интерпретацию теста.
	 */
	private Component<String> relevantClinicalInformation;

	/*
	 * Это необязательное поле должно содержать фактическое время входа в систему, зарегистрированное в лаборатории. Должно использоваться соглашение, указанное в разделе 5.6.2.
	 */
	private Component<LocalDateTime> dateTimeSpecimenReceived;

	/*
	 * Это поле может содержать два отдельных элемента - тип образца и источник образца - как определено в разделах 8.4.16.1 и 8.4.16.2. Компоненты должны быть разделены разделителями компонентов. 8.4.16.1 Тип образца Образцы образцов культур типа или источников: кровь, моча, сыворотка, волосы, рана, биопсия, мокрота и т. Д. 8.4.16.2 Источник образца Это всегда второй компонент поля дескриптора образца и используется специально. для определения участка тела источника образца (например, левая рука, левая рука, правое легкое).
	 */
	private Component<String> specimenDescriptor;
	
	/*
	 * Это поле должно содержать имя лечащего врача в формате, указанном в разделе 5.6.6.
	 */
	private Component<String> orderingPhysician;
	
	/*
	 * Это поле должно содержать номер телефона запрашивающего врача и будет использоваться для ответа на приказы обратного вызова и для критически ненормальных результатов. Используйте формат, указанный в Разделе 5.6.3.
	 */
	private Component<String> physiciansTelephoneNumber;

	/*
	 * Текст, отправленный запрашивающей стороной, должен быть возвращен отправителем вместе с ответом.
	 */
	private Component<String> userFieldNumber1;

	/*
	 * Это поле аналогично тому, что описано в Разделе 8.4.19.
	 */
	private Component<String> userFieldNumber2;

	/*
	 * Это необязательное поле, определяемое для любого использования лабораторией. 
	 */
	private Component<String> laboratoryFieldNumber1;

	/*
	 * Это поле аналогично описанному в Разделе 8.4.21.
	 */
	private Component<String> laboratoryFieldNumber2;

	/*
	 * Это поле используется для указания даты и времени, когда результаты для заказа составляются в отчете или в этом сообщении, или когда статус, как определено в разделах 8.4.26 или 9.9, введен или изменен. Когда информационная система запрашивает у прибора непереданные результаты, информация в этом поле может использоваться для управления обработкой в канале связи. Обычно служба заказа запрашивает только те результаты, для которых дата и время отчета больше, чем дата и время последнего получения результатов запрашивающей системой. Дату и время следует записывать, как указано в разделе 5.6.2.
	 */
	private Component<LocalDateTime> dateTimeResultsReportedOrLastModified;

	/*
	 * В этом поле содержится плата за выставление счетов или справочная информация по этому прибору для выполненных тестов.
	 */
	private Component<String> instrumentChargeToInformationSystem;

	/*
	 * Этот идентификатор может обозначать секцию прибора, где проводился тест. В случае, если несколько инструментов находятся на одной строке или тест был перемещен с одного инструмента на другой, в этом поле будет показано, какой инструмент или часть инструмента выполнили тест.
	 */
	private Component<String> instrumentSectionId;

	private Component<ReportType> reportType;
	
	/*
	 * Это поле не используется, но зарезервировано для будущего расширения.
	 */
	private Component<String> reservedField;


	/*
	 * Это поле определяет место сбора образцов, если оно отличается от местоположения пациента.
	 */
	private Component<String> locationOfSpecimenCollection;

	/*
	 * Это поле используется для целей эпидемиологической отчетности и показывает, является ли идентифицированный организм результатом внутрибольничной (внутрибольничной) инфекции.
	 */ 
	private Component<String> nosocomialInfectionFlag;

	/*
	 * В случаях, когда к отобранному образцу может применяться отдельная услуга, а услуга отличается от услуги записи пациентов, это поле может использоваться для определения конкретной услуги, ответственной за такой сбор.
	 */
	private Component<String> specimenService;

	/*
	 * В тех случаях, когда образец мог быть собран в учреждении, а учреждение отличается от учреждения, занимающегося историей болезни, это поле может использоваться для записи учреждения, занимающегося сбором образцов.
	 */
	private Component<String> specimenInstitution;

	//зависимый результаты
	private List<T> resultRecords;

	/**
	 * <p>
	 * Запись заказа на тестирование определяет атрибуты конкретного запроса на услуги клинического прибора и содержит всю информацию о образцах. Информационная система сгенерирует запись заказа, чтобы запросить данный тест, батарею или набор тестов. Информация в записи заказа обычно относится к одному образцу. Однако не всегда существует однозначная связь между образцом и заказанными испытаниями. Разные батареи для испытаний обычно заказываются в разном порядке, даже если они могут быть выполнены на одном образце. В этом случае информация об образце дублируется в каждой записи заказа, в которой используется этот образец.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     * @param fieldDelimiter Один допустимый символ, как определено в разделе 5.1, за исключением Latin-1 (13) (возврат каретки), должен разделять соседние поля. Разделитель полей является переменным и определяется в заголовке сообщения. Один и тот же разделитель должен использоваться во всех записях, следующих за заголовком и предшествующих записи терминатора сообщения.
	 * @param repeatDelimiter Повторяющийся разделитель - это один допустимый символ, как определено в Разделе 5.1, за исключением Latin-1 (13) и значения для разделителя полей, определенного в Разделе 5.4.2. Разделитель повтора должен быть определен в заголовке сообщения и используется для разделения переменных номеров дескрипторов для полей, содержащих части равных элементов одного и того же набора.
	 * @param componentDelimiter Разделитель компонентов - это один допустимый символ, как определено в Разделе 5.1, за исключением Latin-1 (13) и значений разделителя поля и повтора. Разделитель компонентов используется для разделения элементов данных полей иерархического или квалификационного характера. Например, улица, город, штат, почтовый индекс и т. Д. В адресном поле будут разделены разделителями компонентов.
	 * @param escapeDelimiter Разделитель escape - это один допустимый символ, как определено в Разделе 5.1, за исключением Latin-1 (13) и значений разделителей field, repeat и компонентов. Разделитель escape используется в текстовых полях для обозначения особых операций. Применение ограничителя перехода не является обязательным и может использоваться или игнорироваться по усмотрению передатчика или приемника. Однако все приложения должны принимать escape-разделитель и использовать его для правильного анализа полей в записи.
     */
	public TestOrderRecord(
						String fieldDelimiter, 
						String repeatDelimiter, 
						String componentDelimiter, 
						String escapeDelimiter){
		super("", 31, RecordType.O, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
	}

	/**
	 * <p>
	 * Запись заказа на тестирование определяет атрибуты конкретного запроса на услуги клинического прибора и содержит всю информацию о образцах. Информационная система сгенерирует запись заказа, чтобы запросить данный тест, батарею или набор тестов. Информация в записи заказа обычно относится к одному образцу. Однако не всегда существует однозначная связь между образцом и заказанными испытаниями. Разные батареи для испытаний обычно заказываются в разном порядке, даже если они могут быть выполнены на одном образце. В этом случае информация об образце дублируется в каждой записи заказа, в которой используется этот образец.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     * @param record строка записи
     */
	public TestOrderRecord(String recordString, 
							String fieldDelimiter, 
							String repeatDelimiter, 
							String componentDelimiter, 
							String escapeDelimiter) throws IllegalArgumentException {
        super(recordString, 31, RecordType.O, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);

        if(recordString.charAt(0) != 'O'){
            throw new IllegalArgumentException("Сообщение не начинается с O, поэтому не является записью задания на тест");
        }
        setSequenceNumber(getField(1));

		this.specimenID = new Component<>(String.class, getField(2));
		this.instrumentSpecimenID = new Component<>(String.class, getField(3));

		//парсинг повторяющегося поля с несколькими компонентами
		String field4 = getField(4);
		if(!field4.equals("")){
			String[] repeatFieldsUniversalTestID = field4.split("\\" + repeatDelimiter);
			universalTestID  = new UniversalTestIDField[repeatFieldsUniversalTestID.length];
	
			for(int i =0; i < repeatFieldsUniversalTestID.length; i++){
				String[] components = repeatFieldsUniversalTestID[i].split("\\" + componentDelimiter);
				universalTestID[i] = new UniversalTestIDField(components[0], components[1], components[2], components[3]);
			}
		}
		


		
		String[] prioritiesArrayStr = getField(5).split("\\" + repeatDelimiter);
		priorities = new ArrayList<>();

		for(int i = 0; i < prioritiesArrayStr.length; i++) {
			final int ii = i;
			priorities.add(new Component<>(() -> Priority.getBy(prioritiesArrayStr[ii]), Priority::getIdForComponent));
		}

		this.requestedOrOrderedDateAndTime = new Component<>(LocalDateTime.class, getField(6));
		this.specimenCollectionDateAndTime = new Component<>(LocalDateTime.class, getField(7));
		this.collectionEndTime = new Component<>(LocalDateTime.class, getField(8));
		this.collectionVolume = new Component<>(String.class, getField(9));
		this.collectorId = new Component<>(String.class, getField(10));
		this.actionCode = new Component<>(() -> ActionCode.getBy(getField(11)), ActionCode::getIdForComponent);
		this.dangerCode = new Component<>(String.class, getField(12));
		this.relevantClinicalInformation = new Component<>(String.class, getField(13));
		this.dateTimeSpecimenReceived = new Component<>(LocalDateTime.class, getField(14));
		this.specimenDescriptor = new Component<>(String.class, getField(15));
		this.orderingPhysician = new Component<>(String.class, getField(16));
		this.physiciansTelephoneNumber = new Component<>(String.class, getField(17));
		this.userFieldNumber1 = new Component<>(String.class, getField(18));
		this.userFieldNumber2 = new Component<>(String.class, getField(19));
		this.laboratoryFieldNumber1 = new Component<>(String.class, getField(20));
		this.laboratoryFieldNumber2 = new Component<>(String.class, getField(21));
		this.dateTimeResultsReportedOrLastModified = new Component<>(LocalDateTime.class, getField(22));
		this.instrumentChargeToInformationSystem = new Component<>(String.class, getField(23));
		this.instrumentSectionId = new Component<>(String.class, getField(24));
		this.reportType = new Component<>(() -> ReportType.getBy(getField(25)),  ReportType::getIdForComponent);
		this.reservedField = new Component<>(String.class, getField(26));
		this.locationOfSpecimenCollection = new Component<>(String.class, getField(27));
		this.nosocomialInfectionFlag = new Component<>(String.class, getField(28));
		this.specimenService = new Component<>(String.class, getField(29));
		this.specimenInstitution = new Component<>(String.class, getField(30));
    }

	/**
	 * <p>
	 * Добавляет результат к данному результату
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void addResult(T resultRecord){
		if(this.resultRecords == null){
			this.resultRecords = new ArrayList<>();
		}
		resultRecord.setSequenceNumber(String.valueOf(this.resultRecords.size() + 1));
		this.resultRecords.add(resultRecord);
	}

	/**
	 * <p>
	 * Возвращает результаты данного заказа
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public List<T> getResultRecords() {
		return resultRecords;
	}

	@Override
	public String toString() {
		StringBuilder returnStr = new StringBuilder();
		
		returnStr.append(this.getRecord());

		if(this.commentRecords != null){
			this.commentRecords.forEach(commentRecord -> returnStr.append(commentRecord.toString()));
		}

		if(this.resultRecords != null){
			this.resultRecords.forEach(resultRecord -> returnStr.append(resultRecord.toString()));
		}

		return returnStr.toString();
	}

	/**
	 * <p>
	 * Это текстовое поле должно представлять собой уникальный идентификатор образца, присвоенный информационной системой и возвращенный прибором. Если в образце имеется несколько компонентов, дополнительно идентифицирующих полученные из него культуры, эти идентификаторы компонентов будут следовать за идентификатором образца и будут разделены разделителями компонентов. Например, идентификатор образца может содержать номер образца, за которым следует номер изолята, номер лунки или чашки (например, 10435A ˆ 01 ˆ 64).
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getSpecimenID() {
		return specimenID.getValue();
	}

	/**
	 * <p>
	 * Это текстовое поле должно представлять собой уникальный идентификатор образца, присвоенный информационной системой и возвращенный прибором. Если в образце имеется несколько компонентов, дополнительно идентифицирующих полученные из него культуры, эти идентификаторы компонентов будут следовать за идентификатором образца и будут разделены разделителями компонентов. Например, идентификатор образца может содержать номер образца, за которым следует номер изолята, номер лунки или чашки (например, 10435A ˆ 01 ˆ 64).
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setSpecimenID(String specimenID) {
		Component<String> specimenIDComponent = new Component<>(String.class, specimenID);
		setField(specimenIDComponent.toString(), 2);
		this.specimenID = specimenIDComponent;
	}

	/**
	 * <p>
	 * Это текстовое поле должно представлять уникальный идентификатор, присвоенный прибором, если он отличается от идентификатора информационной системы, и возвращаемый с результатами для использования при ссылке на любые результаты.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getInstrumentSpecimenID() {
		return instrumentSpecimenID.getValue();
	}

	/**
	 * <p>
	 * Это текстовое поле должно представлять уникальный идентификатор, присвоенный прибором, если он отличается от идентификатора информационной системы, и возвращаемый с результатами для использования при ссылке на любые результаты.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setInstrumentSpecimenID(String instrumentSpecimenID) {
		Component<String> instrumentSpecimenIDComponent = new Component<>(String.class, instrumentSpecimenID);
		setField(instrumentSpecimenIDComponent.toString(), 3);
		this.instrumentSpecimenID = instrumentSpecimenIDComponent;
	}

	/**
	 * <p>
	 * В этом поле должен использоваться универсальный идентификатор теста, как описано в разделе 5.6.1.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public UniversalTestIDField[] getUniversalTestID() {
		return universalTestID;
	}

	/**
	 * <p>
	 * В этом поле должен использоваться универсальный идентификатор теста, как описано в разделе 5.6.1.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setUniversalTestID(UniversalTestIDField[] universalTestID) {
		this.universalTestID = universalTestID;
		
		String[] fieldStr = new String[this.universalTestID.length];
		for(int i = 0; i < this.universalTestID.length; i++) {
			fieldStr[i] = universalTestID[i].toString(getComponentDelimiter().getValue());
		}
		setField(String.join(this.getRepeatDelimiter().toString(), Arrays.asList(fieldStr)), 4);

	}

	/**
	 * <p>
	 * Коды приоритета тестирования
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public List<Priority> getPriorities() {
		return priorities.stream().map(Component::getValue).collect(Collectors.toList());
	}

	/**
	 * <p>
	 * Коды приоритета тестирования
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setPriorities(List<Priority> priorities) {
		
		this.priorities = priorities.stream().map(pr -> new Component<>(() -> pr, Priority::getIdForComponent)).collect(Collectors.toList());

		List<String> prioritiesStrList = this.priorities.stream().map(Component::toString).collect(Collectors.toList());

		setField(String.join("\\" + this.getRepeatDelimiter().toString(), prioritiesStrList), 5);
	}

	/**
	 * <p>
	 * Содержимое этого поля должно быть представлено, как указано в разделе 5.6.2, и будет обозначать дату и время, когда заказ на тестирование следует считать заказанным. Обычно это дата и время регистрации заказа. Это дата и время, относительно которых следует рассматривать приоритеты. Если служба заказа желает, чтобы тест проводился в определенное время в будущем, например, тест должен быть проведен через два дня в 20:00, здесь должны быть указаны будущая дата и время. Обратите внимание, что здесь должны быть записаны данные заголовка сообщения, а также будущая дата и время. Кроме того, обратите внимание, что дата и время записи заголовка сообщения (см. Раздел 6.14) указывают время, когда приказ был передан на инструмент или от него.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public LocalDateTime getRequestedOrOrderedDateAndTime() {
		return requestedOrOrderedDateAndTime.getValue();
	}

	/**
	 * <p>
	 * Содержимое этого поля должно быть представлено, как указано в разделе 5.6.2, и будет обозначать дату и время, когда заказ на тестирование следует считать заказанным. Обычно это дата и время регистрации заказа. Это дата и время, относительно которых следует рассматривать приоритеты. Если служба заказа желает, чтобы тест проводился в определенное время в будущем, например, тест должен быть проведен через два дня в 20:00, здесь должны быть указаны будущая дата и время. Обратите внимание, что здесь должны быть записаны данные заголовка сообщения, а также будущая дата и время. Кроме того, обратите внимание, что дата и время записи заголовка сообщения (см. Раздел 6.14) указывают время, когда приказ был передан на инструмент или от него.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setRequestedOrOrderedDateAndTime(LocalDateTime requestedOrOrderedDateAndTime) {
		Component<LocalDateTime> requestedOrOrderedDateAndTimeComponent = new Component<>(LocalDateTime.class, requestedOrOrderedDateAndTime.format(GeneralConsiderations.DATE_TIME_FORMATTER));
		setField(requestedOrOrderedDateAndTimeComponent.toString(), 6);
		this.requestedOrOrderedDateAndTime = requestedOrOrderedDateAndTimeComponent;
	}

	/**
	 * <p>
	 * Это поле должно отображать фактическое время сбора или получения образца.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public LocalDateTime getSpecimenCollectionDateAndTime() {
		return specimenCollectionDateAndTime.getValue();
	}

	/**
	 * <p>
	 * Это поле должно отображать фактическое время сбора или получения образца.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setSpecimenCollectionDateAndTime(LocalDateTime specimenCollectionDateAndTime) {
		Component<LocalDateTime> specimenCollectionDateAndTimeComponent = new Component<>(LocalDateTime.class, specimenCollectionDateAndTime.format(GeneralConsiderations.DATE_TIME_FORMATTER));
		setField(specimenCollectionDateAndTimeComponent.toString(), 7);
		this.specimenCollectionDateAndTime = specimenCollectionDateAndTimeComponent;
	}

	/**
	 * <p>
	 *  Это поле должно содержать дату и время окончания сбора пробы по времени, например, 24-часового сбора мочи. Значение указывается в соответствии с разделом 5.6.2.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public LocalDateTime getCollectionEndTime() {
		return collectionEndTime.getValue();
	}

	/**
	 * <p>
	 *  Это поле должно содержать дату и время окончания сбора пробы по времени, например, 24-часового сбора мочи. Значение указывается в соответствии с разделом 5.6.2.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setCollectionEndTime(LocalDateTime collectionEndTime) {
		Component<LocalDateTime> collectionEndTimeComponent = new Component<>(LocalDateTime.class, collectionEndTime.format(GeneralConsiderations.DATE_TIME_FORMATTER));
		setField(collectionEndTimeComponent.toString(), 8);
		this.collectionEndTime = collectionEndTimeComponent;
	}

	/**
	 * <p>
	 * Это значение должно представлять общий объем образцов, таких как моча или другие объемные образцы, когда в прибор отправляется только аликвота. Единица измерения по умолчанию - миллилитры. Если единицы представлены явно, они должны быть отделены от числового значения разделителем компонентов, например, 300 g. Единицы должны соответствовать соглашениям, приведенным в разделе 5.6.4.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getCollectionVolume() {
		return collectionVolume.getValue();
	}

	/**
	 * <p>
	 * Это значение должно представлять общий объем образцов, таких как моча или другие объемные образцы, когда в прибор отправляется только аликвота. Единица измерения по умолчанию - миллилитры. Если единицы представлены явно, они должны быть отделены от числового значения разделителем компонентов, например, 300 g. Единицы должны соответствовать соглашениям, приведенным в разделе 5.6.4.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setCollectionVolume(String collectionVolume) {
		Component<String> collectionVolumeComponent = new Component<>(String.class, collectionVolume);
		setField(collectionVolumeComponent.toString(), 9);
		this.collectionVolume = collectionVolumeComponent;
	}

	/**
	 * <p>
	 * В этом поле должны быть указаны лицо и объект, в котором был взят образец. Если возникнут вопросы, касающиеся обстоятельств сбора образцов, с этим лицом свяжутся.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getCollectorId() {
		return collectorId.getValue();
	}

	/**
	 * <p>
	 * В этом поле должны быть указаны лицо и объект, в котором был взят образец. Если возникнут вопросы, касающиеся обстоятельств сбора образцов, с этим лицом свяжутся.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setCollectorId(String collectorId) {
		Component<String> collectorIdComponent = new Component<>(String.class, collectorId);
		setField(collectorIdComponent.toString(), 10);
		this.collectorId = collectorIdComponent;
	}

	/**
	 * <p>
	 * В этом поле должны быть указаны действия, которые необходимо предпринять в отношении образцов, которые сопровождают этот запрос или предшествуют ему.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public ActionCode getActionCode() {
		return actionCode.getValue();
	}

	/**
	 * <p>
	 * В этом поле должны быть указаны действия, которые необходимо предпринять в отношении образцов, которые сопровождают этот запрос или предшествуют ему.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setActionCode(ActionCode actionCode) {
		Component<ActionCode> actionCodeComponent = new Component<>(
			() -> actionCode, ActionCode::getIdForComponent
		);
		setField(actionCodeComponent.toString(), 11);
		this.actionCode = actionCodeComponent;
	}

	/**
	 * <p>
	 * Это поле, представляющее тест или код, должно указывать на любую особую опасность, связанную с образцом, например, пациент с гепатитом, подозрение на сибирскую язву.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getDangerCode() {
		return dangerCode.getValue();
	}

	/**
	 * <p>
	 * Это поле, представляющее тест или код, должно указывать на любую особую опасность, связанную с образцом, например, пациент с гепатитом, подозрение на сибирскую язву.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setDangerCode(String dangerCode) {
		Component<String> dangerCodeComponent = new Component<>(String.class, dangerCode);
		setField(dangerCodeComponent.toString(), 12);
		this.dangerCode = dangerCodeComponent;
	}

	/**
	 * <p>
	 * Здесь будет представлена дополнительная информация об образце, которая будет использоваться для сообщения такой информации, как количество вдыхаемого O2 для газов крови, точка менструального цикла для цервикального мазка Папаниколау или другие условия, которые влияют на интерпретацию теста.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getRelevantClinicalInformation() {
		return relevantClinicalInformation.getValue();
	}

	/**
	 * <p>
	 * Здесь будет представлена дополнительная информация об образце, которая будет использоваться для сообщения такой информации, как количество вдыхаемого O2 для газов крови, точка менструального цикла для цервикального мазка Папаниколау или другие условия, которые влияют на интерпретацию теста.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setRelevantClinicalInformation(String relevantClinicalInformation) {
		Component<String> relevantClinicalInformationComponent = new Component<>(String.class, relevantClinicalInformation);
		setField(relevantClinicalInformationComponent.toString(), 13);
		this.relevantClinicalInformation = relevantClinicalInformationComponent;
	}

	/**
	 * <p>
	 * Это необязательное поле должно содержать фактическое время входа в систему, зарегистрированное в лаборатории. Должно использоваться соглашение, указанное в разделе 5.6.2.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public LocalDateTime getDateTimeSpecimenReceived() {
		return dateTimeSpecimenReceived.getValue();
	}

	/**
	 * <p>
	 * Это необязательное поле должно содержать фактическое время входа в систему, зарегистрированное в лаборатории. Должно использоваться соглашение, указанное в разделе 5.6.2.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setDateTimeSpecimenReceived(LocalDateTime dateTimeSpecimenReceived) {
		Component<LocalDateTime> dateTimeSpecimenReceivedComponent = new Component<>(LocalDateTime.class, dateTimeSpecimenReceived.format(GeneralConsiderations.DATE_TIME_FORMATTER));
		setField(dateTimeSpecimenReceivedComponent.toString(), 14);
		this.collectionEndTime = dateTimeSpecimenReceivedComponent;
	}

	/**
	 * <p>
	 * Это поле может содержать два отдельных элемента - тип образца и источник образца - как определено в разделах 8.4.16.1 и 8.4.16.2. Компоненты должны быть разделены разделителями компонентов. 8.4.16.1 Тип образца Образцы образцов культур типа или источников: кровь, моча, сыворотка, волосы, рана, биопсия, мокрота и т. Д. 8.4.16.2 Источник образца Это всегда второй компонент поля дескриптора образца и используется специально. для определения участка тела источника образца (например, левая рука, левая рука, правое легкое).
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getSpecimenDescriptor() {
		return specimenDescriptor.getValue();
	}

	/**
	 * <p>
	 * Это поле может содержать два отдельных элемента - тип образца и источник образца - как определено в разделах 8.4.16.1 и 8.4.16.2. Компоненты должны быть разделены разделителями компонентов. 8.4.16.1 Тип образца Образцы образцов культур типа или источников: кровь, моча, сыворотка, волосы, рана, биопсия, мокрота и т. Д. 8.4.16.2 Источник образца Это всегда второй компонент поля дескриптора образца и используется специально. для определения участка тела источника образца (например, левая рука, левая рука, правое легкое).
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setSpecimenDescriptor(String specimenDescriptor) {
		Component<String> specimenDescriptorComponent = new Component<>(String.class, specimenDescriptor);
		setField(specimenDescriptorComponent.toString(), 15);
		this.specimenDescriptor = specimenDescriptorComponent;
	}

	/**
	 * <p>
	 * Это поле должно содержать имя лечащего врача в формате, указанном в разделе 5.6.6.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getOrderingPhysician() {
		return orderingPhysician.getValue();
	}

	/**
	 * <p>
	 * Это поле должно содержать имя лечащего врача в формате, указанном в разделе 5.6.6.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setOrderingPhysician(String orderingPhysician) {
		Component<String> orderingPhysicianComponent = new Component<>(String.class, orderingPhysician);
		setField(orderingPhysicianComponent.toString(), 16);
		this.orderingPhysician = orderingPhysicianComponent;
	}

	/**
	 * <p>
	 * Это поле должно содержать номер телефона запрашивающего врача и будет использоваться для ответа на приказы обратного вызова и для критически ненормальных результатов. Используйте формат, указанный в Разделе 5.6.3.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getPhysiciansTelephoneNumber() {
		return physiciansTelephoneNumber.getValue();
	}

	/**
	 * <p>
	 * Это поле должно содержать номер телефона запрашивающего врача и будет использоваться для ответа на приказы обратного вызова и для критически ненормальных результатов. Используйте формат, указанный в Разделе 5.6.3.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setPhysiciansTelephoneNumber(String physiciansTelephoneNumber) {
		Component<String> physiciansTelephoneNumberComponent = new Component<>(String.class, physiciansTelephoneNumber);
		setField(physiciansTelephoneNumberComponent.toString(), 17);
		this.physiciansTelephoneNumber = physiciansTelephoneNumberComponent;
	}

	/**
	 * <p>
	 * Текст, отправленный запрашивающей стороной, должен быть возвращен отправителем вместе с ответом.
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
	 * Текст, отправленный запрашивающей стороной, должен быть возвращен отправителем вместе с ответом.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setUserFieldNumber1(String userFieldNumber1) {
		Component<String> userFieldNumber1Component = new Component<>(String.class, userFieldNumber1);
		setField(userFieldNumber1Component.toString(), 18);
		this.userFieldNumber1 = userFieldNumber1Component;
	}

	/**
	 * <p>
	 * Это поле аналогично тому, что описано в Разделе 8.4.19.
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
	 * Это поле аналогично тому, что описано в Разделе 8.4.19.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setUserFieldNumber2(String userFieldNumber2) {
		Component<String> userFieldNumber2Component = new Component<>(String.class, userFieldNumber2);
		setField(userFieldNumber2Component.toString(), 19);
		this.userFieldNumber2 = userFieldNumber2Component;
	}

	/**
	 * <p>
	 * Это необязательное поле, определяемое для любого использования лабораторией. 
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getLaboratoryFieldNumber1() {
		return laboratoryFieldNumber1.getValue();
	}

	/**
	 * <p>
	 * Это необязательное поле, определяемое для любого использования лабораторией. 
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setLaboratoryFieldNumber1(String laboratoryFieldNumber1) {
		Component<String> laboratoryFieldNumber1Component = new Component<>(String.class, laboratoryFieldNumber1);
		setField(laboratoryFieldNumber1Component.toString(), 20);
		this.laboratoryFieldNumber1 = laboratoryFieldNumber1Component;
	}

	/**
	 * <p>
	 * Это поле аналогично описанному в Разделе 8.4.21.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getLaboratoryFieldNumber2() {
		return laboratoryFieldNumber2.getValue();
	}
    
	/**
	 * <p>
	 * Это поле аналогично описанному в Разделе 8.4.21.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setLaboratoryFieldNumber2(String laboratoryFieldNumber2) {
		Component<String> laboratoryFieldNumber2Component = new Component<>(String.class, laboratoryFieldNumber2);
		setField(laboratoryFieldNumber2Component.toString(), 21);
		this.laboratoryFieldNumber2 = laboratoryFieldNumber2Component;
	}

	/**
	 * <p>
	 * Это поле используется для указания даты и времени, когда результаты для заказа составляются в отчете или в этом сообщении, или когда статус, как определено в разделах 8.4.26 или 9.9, введен или изменен. Когда информационная система запрашивает у прибора непереданные результаты, информация в этом поле может использоваться для управления обработкой в канале связи. Обычно служба заказа запрашивает только те результаты, для которых дата и время отчета больше, чем дата и время последнего получения результатов запрашивающей системой. Дату и время следует записывать, как указано в разделе 5.6.2.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public LocalDateTime getDateTimeResultsReportedOrLastModified() {
		return dateTimeResultsReportedOrLastModified.getValue();
	}

	/**
	 * <p>
	 * Это поле используется для указания даты и времени, когда результаты для заказа составляются в отчете или в этом сообщении, или когда статус, как определено в разделах 8.4.26 или 9.9, введен или изменен. Когда информационная система запрашивает у прибора непереданные результаты, информация в этом поле может использоваться для управления обработкой в канале связи. Обычно служба заказа запрашивает только те результаты, для которых дата и время отчета больше, чем дата и время последнего получения результатов запрашивающей системой. Дату и время следует записывать, как указано в разделе 5.6.2.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setDateTimeResultsReportedOrLastModified(LocalDateTime dateTimeResultsReportedOrLastModified) {
		Component<LocalDateTime> dateTimeResultsReportedOrLastModifiedComponent = new Component<>(LocalDateTime.class, dateTimeResultsReportedOrLastModified.format(GeneralConsiderations.DATE_TIME_FORMATTER));
		setField(dateTimeResultsReportedOrLastModifiedComponent.toString(), 22);
		this.dateTimeResultsReportedOrLastModified = dateTimeResultsReportedOrLastModifiedComponent;
	}

	/**
	 * <p>
	 * В этом поле содержится плата за выставление счетов или справочная информация по этому прибору для выполненных тестов.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getInstrumentChargeToInformationSystem() {
		return instrumentChargeToInformationSystem.getValue();
	}

	/**
	 * <p>
	 * В этом поле содержится плата за выставление счетов или справочная информация по этому прибору для выполненных тестов.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setInstrumentChargeToInformationSystem(String instrumentChargeToInformationSystem) {
		Component<String> instrumentChargeToInformationSystemComponent = new Component<>(String.class, instrumentChargeToInformationSystem);
		setField(instrumentChargeToInformationSystemComponent.toString(), 23);
		this.instrumentChargeToInformationSystem = instrumentChargeToInformationSystemComponent;
	}


	/**
	 * <p>
	 * Этот идентификатор может обозначать секцию прибора, где проводился тест. В случае, если несколько инструментов находятся на одной строке или тест был перемещен с одного инструмента на другой, в этом поле будет показано, какой инструмент или часть инструмента выполнили тест.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getInstrumentSectionId() {
		return instrumentSectionId.getValue();
	}
   
	/**
	 * <p>
	 * Этот идентификатор может обозначать секцию прибора, где проводился тест. В случае, если несколько инструментов находятся на одной строке или тест был перемещен с одного инструмента на другой, в этом поле будет показано, какой инструмент или часть инструмента выполнили тест.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setInstrumentSectionId(String instrumentSectionId) {
		Component<String> instrumentSectionIdComponent = new Component<>(String.class, instrumentSectionId);
		setField(instrumentSectionIdComponent.toString(), 24);
		this.instrumentSectionId = instrumentSectionIdComponent;
	}

	public ReportType getReportType() {
		return reportType.getValue();
	}

	public void setReportType(ReportType reportType) {
		Component<ReportType> reportTypeComponent = new Component<>(
			() -> reportType, ReportType::getIdForComponent
		);
		setField(reportTypeComponent.toString(), 25);
		this.reportType = reportTypeComponent;
	}

	/**
	 * <p>
	 * Это поле не используется, но зарезервировано для будущего расширения.
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
	 * Это поле не используется, но зарезервировано для будущего расширения.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setReservedField(String reservedField) {
		Component<String> reservedFieldComponent = new Component<>(String.class, reservedField);
		setField(reservedFieldComponent.toString(), 26);
		this.reservedField = reservedFieldComponent;
	}

 	/**
	 * <p>
	 * Это поле определяет место сбора образцов, если оно отличается от местоположения пациента.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getLocationOfSpecimenCollection() {
		return locationOfSpecimenCollection.getValue();
	}

	/**
	 * <p>
	 * Это поле определяет место сбора образцов, если оно отличается от местоположения пациента.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setLocationOfSpecimenCollection(String locationOfSpecimenCollection) {
		Component<String> locationOfSpecimenCollectionComponent = new Component<>(String.class, locationOfSpecimenCollection);
		setField(locationOfSpecimenCollectionComponent.toString(), 27);
		this.locationOfSpecimenCollection = locationOfSpecimenCollectionComponent;
	}

	/**
	 * <p>
	 * Это поле используется для целей эпидемиологической отчетности и показывает, является ли идентифицированный организм результатом внутрибольничной (внутрибольничной) инфекции.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getNosocomialInfectionFlag() {
		return nosocomialInfectionFlag.getValue();
	}
   
	/**
	 * <p>
	 * Это поле используется для целей эпидемиологической отчетности и показывает, является ли идентифицированный организм результатом внутрибольничной (внутрибольничной) инфекции.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setNosocomialInfectionFlag(String nosocomialInfectionFlag) {
		Component<String> nosocomialInfectionFlagComponent = new Component<>(String.class, nosocomialInfectionFlag);
		setField(nosocomialInfectionFlagComponent.toString(), 28);
		this.nosocomialInfectionFlag = nosocomialInfectionFlagComponent;
	}

	/**
	 * <p>
	 * В случаях, когда к отобранному образцу может применяться отдельная услуга, а услуга отличается от услуги записи пациентов, это поле может использоваться для определения конкретной услуги, ответственной за такой сбор.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getSpecimenService() {
		return specimenService.getValue();
	}

	/**
	 * <p>
	 * В случаях, когда к отобранному образцу может применяться отдельная услуга, а услуга отличается от услуги записи пациентов, это поле может использоваться для определения конкретной услуги, ответственной за такой сбор.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */	
	public void setSpecimenService(String specimenService) {
		Component<String> specimenServiceComponent = new Component<>(String.class, specimenService);
		setField(specimenServiceComponent.toString(), 29);
		this.specimenService = specimenServiceComponent;
	}

	/**
	 * <p>
	 *  В тех случаях, когда образец мог быть собран в учреждении, а учреждение отличается от учреждения, занимающегося историей болезни, это поле может использоваться для записи учреждения, занимающегося сбором образцов.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */	
	public String getSpecimenInstitution() {
		return specimenInstitution.getValue();
	}
    
	/**
	 * <p>
	 *  В тех случаях, когда образец мог быть собран в учреждении, а учреждение отличается от учреждения, занимающегося историей болезни, это поле может использоваться для записи учреждения, занимающегося сбором образцов.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */	
	public void setSpecimenInstitution(String specimenInstitution) {
		Component<String> specimenInstitutionComponent = new Component<>(String.class, specimenInstitution);
		setField(specimenInstitutionComponent.toString(), 30);
		this.specimenInstitution = specimenInstitutionComponent;
	}

	private List<T1> commentRecords;
	
	@Override
	public void addCommentRecord(T1 commentRecord){
		if(this.commentRecords == null) {
			this.commentRecords = new ArrayList<>();
		}
		commentRecord.setSequenceNumber(String.valueOf(this.commentRecords.size() + 1));
		this.commentRecords.add(commentRecord);
	}
	
	@Override
	public List<T1> getCommentRecords() {
		return commentRecords;
	}
}