package com.github.chistousov.lib.astm1394.record.patient;

import java.util.ArrayList;
import java.util.List;

import com.github.chistousov.lib.astm1394.record.Component;
import com.github.chistousov.lib.astm1394.record.IWithComments;
import com.github.chistousov.lib.astm1394.record.Record;
import com.github.chistousov.lib.astm1394.record.RecordType;
import com.github.chistousov.lib.astm1394.record.comment.CommentRecord;

/**
 * <p>
 * Each line of a patient record must begin with a record type and end with a carriage return.
 * (Каждая строка карты пациента должна начинаться с типа записи и заканчиваться символом возврата каретки.)
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public class PatientInformationRecord<T extends Record, T1 extends CommentRecord> extends Record implements IWithComments<T1> {
    
    /*
     * Этот идентификатор должен быть уникальным идентификатором, назначенным и используемым практикой для идентификации пациента и его / ее результатов после получения результатов тестирования.
     */
    private Component<Long> practiceAssignedPatientId;

    /*
     * Этот идентификатор должен быть уникальным номером обработки, присвоенным пациенту лабораторией.
     */
    private Component<Long> laboratoryAssignedPatientId;

    /*
     * Это поле необязательно должно использоваться для дополнительных, универсальных или определенных производителем идентификаторов (таких как номер учетной записи социального обеспечения), которые расположены между передатчиком и приемником. Обратите внимание, что физические лица не обязаны предоставлять номера социального страхования.
     */
    private Component<String> patientIdNumber3; 

    /*
     * Имя пациента должно быть представлено в следующем формате: фамилия, имя, отчество или инициалы, суффикс и титул, и каждый из этих компонентов должен быть разделен разделителем компонентов.
	 */
    private Component<String> patientName;

    /*
     * Необязательная девичья фамилия матери может потребоваться, чтобы различать пациентов с одинаковой датой рождения и фамилией, когда файлы реестра очень большие. Это имя должно быть представлено как девичья фамилия матери, например, Томпсон.
     */
    private Component<String> mothersMaidenName;


    /*
     * Дата рождения указывается в стандартном формате.
     */
    private Component<String> birthdate;

    /*
     * Это поле должно быть представлено буквами M, F или U.
     */
    private Component<PatientSex> patientSex;
    
    /*
     * Раса
     */
    private Component<PatientRaceEthnicOrigin> patientRaceEthnicOrigin;


    /*
     * Это текстовое значение должно содержать почтовый адрес почтового адреса пациента.
     */
    private Component<String> patientAddress;
    
    /*
     * Это поле зарезервировано для будущего расширения.
     */
    private Component<String> reservedField;

    /*
     * Телефон пациента
     */
    private Component<String> patientTelephoneNumber;

    /*
     * В этом поле указываются врачи, осуществляющие уход за пациентом, в виде имен или кодов по согласованию между отправителем и получателем. Идентификаторы или имена, или и то, и другое должны быть разделены разделителями компонентов, как указано в Разделе 5.6.6. Множественные имена врачей (например, лечащий врач, лечащий врач, лечащий врач) должны быть разделены повторяющимися разделителями. 
     */
    private Component<String> attendingPhysicianId;

    /*
     * Это необязательное текстовое поле для использования поставщиком (каждая лаборатория может использовать его по-своему).
     */
    private Component<String> specialField1;

    /*
     * Это необязательное текстовое поле для использования поставщиком
     */
    private Component<String> specialField2;

    /*
     * Это необязательное числовое поле, содержащее рост пациента. Единицы измерения по умолчанию - сантиметры. Если измеряется в единицах другой, единицы также должны быть переданы
     */
    private Component<String> patientHeight;

    /*
     * Это необязательное числовое поле, содержащее вес пациента. Единицы измерения по умолчанию - килограммы. При измерении в другой единице, например фунтах, название единицы также должно передаваться, как указано в Разделе 5.6.4. Информация о росте и весе в настоящее время требуется не всем лабораториям, но имеет значение при оценке нормативных значений, основанных на площади поверхности тела.
     */
    private Component<String> patientWeight;

    /*
     * Это значение следует вводить либо как код МКБ-9, либо как произвольный текст. Если зарегистрировано несколько диагнозов, они должны быть разделены повторяющимися разделителями.
     */
    private Component<String> patientsKnownOrSuspectedDiagnosis;

    /*
     * Это поле используется для пациентов, принимающих активные лекарства или подозреваемых в передозировке. Должно использоваться родовое имя. Это поле используется для интерпретации клинических результатов.
     */
    private Component<String> patientActiveMedications;

    /*
     * Это необязательное поле в произвольном тексте следует использовать для обозначения таких условий, которые влияют на результаты тестирования, например, 16-часовое голодание (для триглицеридов) и отсутствие красного мяса (для тестирования гемокульта).
     */
    private Component<String> patientsDiet;

    /*
     * Это текстовое поле для использования в практике; необязательный переданный текст будет возвращен с результатами.
     */ 
    private Component<String> practiceFieldNumber1; 

    /*
     * Это текстовое поле для использования в практике; необязательный переданный текст будет возвращен с результатами.
     */ 
    private Component<String> practiceFieldNumber2;

    /*
     * Эти значения должны быть представлены, как указано в разделе 5.1. Дата выписки, если она указана, следует за датой приема и отделяется от нее повторяющимся разделителем.
     */ 
    private Component<String> admissionAndDischargeDates;

    /*
     * Это значение должно быть представлено следующим минимальным списком или расширениями, согласованными между отправителем и получателем: OP (амбулаторный), PA (предварительный прием), IP (стационарный), ER (отделение неотложной помощи).
     */
    private Component<AdmissionStatus> admissionStatus;
     
    /*
     * Это текстовое значение должно отражать общее расположение клиники или сестринского отделения, палату или койку (или и то, и другое) пациента в сроки, согласованные отправителем и получателем.
     */
    private Component<String> location;

    /*
     * Это поле относится к разделу 7.28. Он идентифицирует класс кода или классификаторов, которые передаются (например, DRG или в будущем AVG [группы амбулаторного посещения]).
     */
    private Component<String> natureOfAlternativeDiagnosticCodeAndClassifiers;

    /*
     * В это поле могут быть включены альтернативные диагностические коды и классификации (например, коды DRG). Характер диагностического кода описан в разделе 7.27. Если включено несколько кодов, они должны быть разделены повторяющимися разделителями. За отдельными кодами могут следовать необязательные дескрипторы теста (если последние присутствуют), и они должны быть разделены разделителями компонентов.
     */
    private Component<String> alternativeDiagnosticCodeAndClassification;

    /*
     * При необходимости это значение должно включать религию пациента. Коды или имена могут быть отправлены по согласованию между отправителем и получателем. Полные названия религий также могут быть отправлены по запросу.
     */
    private Component<PatientReligion> patientReligion;

    /*
     * При необходимости в этом значении указывается семейное положение пациента.
     */
    private Component<MaritalStatus> maritalStatus;

    /*
     * Коды изоляции указывают на меры предосторожности, которые необходимо соблюдать для защиты пациента или персонала от инфекции. Ниже приведены рекомендуемые коды для общих мер предосторожности. Можно указать несколько мер предосторожности, разделенных повторяющимися разделителями. Также могут быть отправлены полные текстовые предупреждения.
     */
    private Component<IsolationStatus> isolationStatus;

    /*
     * Значение этого поля указывает на основной язык пациента. Это может потребоваться, если пациент плохо владеет местным языком.
     */
    private Component<String> language;

    /*
     * Это значение указывает на больничную услугу, назначенную пациенту в данный момент. И код, и текст могут быть отправлены, если они разделены разделителем компонентов.
     */
    private Component<String> hospitalService;
    
    /*
     * Это значение указывает на больничное учреждение, назначенное пациенту в данный момент. И код, и текст могут быть отправлены, если они разделены разделителем компонентов.
     */
    private Component<String> hospitalInstitution;

    /*
     * Это значение указывает группу дозировки пациента. Например, A – ВЗРОСЛЫЙ, P1 – ПЕДИАТРИЧЕСКИЙ (от одного до шести месяцев), P2 – ПЕДИАТРИЧЕСКИЙ (от шести месяцев до трех лет) и т. Д. Подкомпоненты этого поля могут использоваться для определения подгрупп доз.
     */
    private Component<String> dosageCategory;
    
	// для хранения зависимый заданий
	private List<T> testOrderRecords;


	/**
	 * <p>
	 * Каждая строка карты пациента должна начинаться с типа записи и заканчиваться символом возврата каретки.
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
	public PatientInformationRecord(String fieldDelimiter, String repeatDelimiter, String componentDelimiter, String escapeDelimiter) throws IllegalArgumentException {
        super("", 35, RecordType.P, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
	}

	/**
	 * <p>
	 * Каждая строка карты пациента должна начинаться с типа записи и заканчиваться символом возврата каретки.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     * @param record строка записи
     */
	public PatientInformationRecord(String recordString, String fieldDelimiter, String repeatDelimiter, String componentDelimiter, String escapeDelimiter) throws IllegalArgumentException {
        super(recordString, 35, RecordType.P, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);

        if(recordString.charAt(0) != 'P'){
            throw new IllegalArgumentException("Сообщение не начинается с P, поэтому не является записью информации о пациенте");
        }
        setSequenceNumber(getField(1));
		
		this.practiceAssignedPatientId = new Component<>(Long.class, getField(2));
        this.laboratoryAssignedPatientId = new Component<>(Long.class, getField(3));
        this.patientIdNumber3 = new Component<>(String.class, getField(4));
        this.patientName = new Component<>(String.class, getField(5));
        this.mothersMaidenName = new Component<>(String.class, getField(6));
        this.birthdate = new Component<>(String.class, getField(7));
        this.patientSex = new Component<>(() -> PatientSex.getBy(getField(8)), PatientSex::getIdForComponent);
        this.patientRaceEthnicOrigin = new Component<>(() -> PatientRaceEthnicOrigin.getBy(getField(9)), PatientRaceEthnicOrigin::getIdForComponent);
        this.patientAddress = new Component<>(String.class, getField(10));
        this.reservedField = new Component<>(String.class, getField(11));
        this.patientTelephoneNumber = new Component<>(String.class, getField(12));
        this.attendingPhysicianId = new Component<>(String.class, getField(13));
        this.specialField1 = new Component<>(String.class, getField(14));
        this.specialField2 = new Component<>(String.class, getField(15));
        this.patientHeight = new Component<>(String.class, getField(16));
        this.patientWeight = new Component<>(String.class, getField(17));
        this.patientsKnownOrSuspectedDiagnosis = new Component<>(String.class, getField(18));
        this.patientActiveMedications = new Component<>(String.class, getField(19));
        this.patientsDiet = new Component<>(String.class, getField(20));
        this.practiceFieldNumber1 = new Component<>(String.class, getField(21));
        this.practiceFieldNumber2 = new Component<>(String.class, getField(22));
        this.admissionAndDischargeDates =  new Component<>(String.class, getField(23));
        this.admissionStatus = new Component<>(() -> AdmissionStatus.getBy(getField(24)), AdmissionStatus::getIdForComponent);
        this.location = new Component<>(String.class, getField(25));
        this.natureOfAlternativeDiagnosticCodeAndClassifiers = new Component<>(String.class, getField(26));
        this.alternativeDiagnosticCodeAndClassification = new Component<>(String.class, getField(27));
        this.patientReligion = new Component<>(() -> PatientReligion.getBy(getField(28)), PatientReligion::getIdForComponent);
        this.maritalStatus = new Component<>(() -> MaritalStatus.getBy(getField(29)), MaritalStatus::getIdForComponent);
        this.isolationStatus = new Component<>(() -> IsolationStatus.getBy(getField(30)), IsolationStatus::getIdForComponent);
        this.language = new Component<>(String.class, getField(31));
        this.hospitalService = new Component<>(String.class, getField(32));
        this.hospitalInstitution = new Component<>(String.class, getField(33));
        this.dosageCategory = new Component<>(String.class, getField(34));
    }

	/**
	 * <p>
	 * Добавить задание для этого пациента
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void addOrder(T testOrderRecord){
		if(this.testOrderRecords == null){
			this.testOrderRecords = new ArrayList<>();
		}
		testOrderRecord.setSequenceNumber(String.valueOf(this.testOrderRecords.size() + 1));
		this.testOrderRecords.add(testOrderRecord);
	}


	/**
	 * <p>
	 * Задания этого пациента
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public List<T> getTestOrderRecords() {
		return testOrderRecords;
	}


	@Override
	public String toString() {
		StringBuilder returnStr = new StringBuilder();
		
		returnStr.append(this.getRecord());

		if(this.commentRecords != null){
			this.commentRecords.forEach(commentRecord -> returnStr.append(commentRecord.toString()));
		}

		if(this.testOrderRecords != null){
			this.testOrderRecords.forEach(testOrderRecord -> returnStr.append(testOrderRecord.toString()));
		}

		return returnStr.toString();
	}

	/**
	 * <p>
	 * Этот идентификатор должен быть уникальным идентификатором, назначенным и используемым практикой для идентификации пациента и его / ее результатов после получения результатов тестирования.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public Long getPracticeAssignedPatientId() {
		return practiceAssignedPatientId.getValue();
	}

	/**
	 * <p>
	 * Этот идентификатор должен быть уникальным идентификатором, назначенным и используемым практикой для идентификации пациента и его / ее результатов после получения результатов тестирования.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setPracticeAssignedPatientId(Long practiceAssignedPatientId) {
		Component<Long> practiceAssignedPatientIdComponent = new Component<>(Long.class, practiceAssignedPatientId.toString());
		setField(practiceAssignedPatientIdComponent.toString(), 2);
		this.practiceAssignedPatientId = practiceAssignedPatientIdComponent;
	}


	/**
	 * <p>
	 * Этот идентификатор должен быть уникальным номером обработки, присвоенным пациенту лабораторией.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public Long getLaboratoryAssignedPatientId() {
		return laboratoryAssignedPatientId.getValue();
	}

	/**
	 * <p>
	 * Этот идентификатор должен быть уникальным номером обработки, присвоенным пациенту лабораторией.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setLaboratoryAssignedPatientId(Long laboratoryAssignedPatientId) {
		Component<Long> laboratoryAssignedPatientIdComponent = new Component<>(Long.class, laboratoryAssignedPatientId.toString());
		setField(laboratoryAssignedPatientIdComponent.toString(), 3);
		this.laboratoryAssignedPatientId = laboratoryAssignedPatientIdComponent;
	}

	/**
	 * <p>
	 * Это поле необязательно должно использоваться для дополнительных, универсальных или определенных производителем идентификаторов (таких как номер учетной записи социального обеспечения), которые расположены между передатчиком и приемником. Обратите внимание, что физические лица не обязаны предоставлять номера социального страхования.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getPatientIdNumber3() {
		return patientIdNumber3.getValue();
	}

	/**
	 * <p>
	 * Это поле необязательно должно использоваться для дополнительных, универсальных или определенных производителем идентификаторов (таких как номер учетной записи социального обеспечения), которые расположены между передатчиком и приемником. Обратите внимание, что физические лица не обязаны предоставлять номера социального страхования.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setPatientIdNumber3(String patientIdNumber3) {
		Component<String> patientIdNumber3Component = new Component<>(String.class, patientIdNumber3);
		setField(patientIdNumber3Component.toString(), 4);
		this.patientIdNumber3 = patientIdNumber3Component;
	}

	/**
	 * <p>
	 * Имя пациента должно быть представлено в следующем формате: фамилия, имя, отчество или инициалы, суффикс и титул, и каждый из этих компонентов должен быть разделен разделителем компонентов.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getPatientName() {
		return patientName.getValue();
	}

	/**
	 * <p>
	 * Имя пациента должно быть представлено в следующем формате: фамилия, имя, отчество или инициалы, суффикс и титул, и каждый из этих компонентов должен быть разделен разделителем компонентов.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setPatientName(String patientName) {
		Component<String> patientNameComponent = new Component<>(String.class, patientName);
		setField(patientNameComponent.toString(), 5);
		this.patientName = patientNameComponent;
	}

	/**
	 * <p>
	 * Необязательная девичья фамилия матери может потребоваться, чтобы различать пациентов с одинаковой датой рождения и фамилией, когда файлы реестра очень большие. Это имя должно быть представлено как девичья фамилия матери, например, Томпсон.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getMothersMaidenName() {
		return mothersMaidenName.getValue();
	}

	/**
	 * <p>
	 * Необязательная девичья фамилия матери может потребоваться, чтобы различать пациентов с одинаковой датой рождения и фамилией, когда файлы реестра очень большие. Это имя должно быть представлено как девичья фамилия матери, например, Томпсон.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setMothersMaidenName(String mothersMaidenName) {
		Component<String> mothersMaidenNameComponent = new Component<>(String.class, mothersMaidenName);
		setField(mothersMaidenNameComponent.toString(), 6);
		this.mothersMaidenName = mothersMaidenNameComponent;
	}

	/**
	 * <p>
	 * Дата рождения указывается в стандартном формате.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getBirthdate() {
		return birthdate.getValue();
	}

	/**
	 * <p>
	 * Дата рождения указывается в стандартном формате.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setBirthdate(String birthdate) {
		Component<String> birthdateComponent = new Component<>(String.class, birthdate);
		setField(birthdateComponent.toString(), 7);
		this.birthdate = birthdateComponent;
	}

	/**
	 * <p>
	 * Раса
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public PatientRaceEthnicOrigin getPatientRaceEthnicOrigin() {
		return patientRaceEthnicOrigin.getValue();
	}

	/**
	 * <p>
	 * Раса
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setPatientRaceEthnicOrigin(PatientRaceEthnicOrigin patientRaceEthnicOrigin) {
		Component<PatientRaceEthnicOrigin> patientRaceEthnicOriginComponent = new Component<>(
			() -> patientRaceEthnicOrigin, PatientRaceEthnicOrigin::getIdForComponent
		);
		setField(patientRaceEthnicOriginComponent.toString(), 9);
		this.patientRaceEthnicOrigin = patientRaceEthnicOriginComponent;
	}

	/**
	 * <p>
	 * Это текстовое значение должно содержать почтовый адрес почтового адреса пациента.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getPatientAddress() {
		return patientAddress.getValue();
	}

	/**
	 * <p>
	 * Это текстовое значение должно содержать почтовый адрес почтового адреса пациента.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setPatientAddress(String patientAddress) {
		Component<String> patientAddressComponent = new Component<>(String.class, patientAddress);
		setField(patientAddressComponent.toString(), 10);
		this.patientAddress = patientAddressComponent;
	}

	/**
	 * <p>
	 * Это поле должно быть представлено буквами M, F или U.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public PatientSex getPatientSex() {
		return patientSex.getValue();
	}

	/**
	 * <p>
	 * Это поле должно быть представлено буквами M, F или U.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setPatientSex(PatientSex patientSex) {
		Component<PatientSex> patientSexComponent = new Component<>(
			() -> patientSex, PatientSex::getIdForComponent
		);
		setField(patientSexComponent.toString(), 8);
		this.patientSex = patientSexComponent;
	}

	/**
	 * <p>
	 * Это поле зарезервировано для будущего расширения.
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
	 * Это поле зарезервировано для будущего расширения.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setReservedField(String reservedField) {
		Component<String> reservedFieldComponent = new Component<>(String.class, reservedField);
		setField(reservedFieldComponent.toString(), 11);
		this.reservedField = reservedFieldComponent;
	}

	/**
	 * <p>
	 * Телефон пациента
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getPatientTelephoneNumber() {
		return patientTelephoneNumber.getValue();
	}

	/**
	 * <p>
	 * Телефон пациента
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setPatientTelephoneNumber(String patientTelephoneNumber) {
		Component<String> patientTelephoneNumberComponent = new Component<>(String.class, patientTelephoneNumber);
		setField(patientTelephoneNumberComponent.toString(), 12);
		this.patientTelephoneNumber = patientTelephoneNumberComponent;
	}

	/**
	 * <p>
	 * В этом поле указываются врачи, осуществляющие уход за пациентом, в виде имен или кодов по согласованию между отправителем и получателем. Идентификаторы или имена, или и то, и другое должны быть разделены разделителями компонентов, как указано в Разделе 5.6.6. Множественные имена врачей (например, лечащий врач, лечащий врач, лечащий врач) должны быть разделены повторяющимися разделителями.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getAttendingPhysicianId() {
		return attendingPhysicianId.getValue();
	}

	/**
	 * <p>
	 * В этом поле указываются врачи, осуществляющие уход за пациентом, в виде имен или кодов по согласованию между отправителем и получателем. Идентификаторы или имена, или и то, и другое должны быть разделены разделителями компонентов, как указано в Разделе 5.6.6. Множественные имена врачей (например, лечащий врач, лечащий врач, лечащий врач) должны быть разделены повторяющимися разделителями.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setAttendingPhysicianId(String attendingPhysicianId) {
		Component<String> attendingPhysicianIdComponent = new Component<>(String.class, attendingPhysicianId);
		setField(attendingPhysicianIdComponent.toString(), 13);
		this.attendingPhysicianId = attendingPhysicianIdComponent;
	}

	/**
	 * <p>
	 * Это необязательное текстовое поле для использования поставщиком (каждая лаборатория может использовать его по-своему).
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getSpecialField1() {
		return specialField1.getValue();
	}

	/**
	 * <p>
	 * Это необязательное текстовое поле для использования поставщиком (каждая лаборатория может использовать его по-своему).
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setSpecialField1(String specialField1) {
		Component<String> specialField1Component = new Component<>(String.class, specialField1);
		setField(specialField1Component.toString(), 14);
		this.specialField1 = specialField1Component;
	}

	/**
	 * <p>
	 * Это необязательное текстовое поле для использования поставщиком
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getSpecialField2() {
		return specialField2.getValue();
	}

	/**
	 * <p>
	 * Это необязательное текстовое поле для использования поставщиком
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setSpecialField2(String specialField2) {
		Component<String> specialField2Component = new Component<>(String.class, specialField2);
		setField(specialField2Component.toString(), 15);
		this.specialField2 = specialField2Component;
	}

	/**
	 * <p>
	 * Это необязательное числовое поле, содержащее рост пациента. Единицы измерения по умолчанию - сантиметры. Если измеряется в единицах другой, единицы также должны быть переданы
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getPatientHeight() {
		return patientHeight.getValue();
	}

	/**
	 * <p>
	 * Это необязательное числовое поле, содержащее рост пациента. Единицы измерения по умолчанию - сантиметры. Если измеряется в единицах другой, единицы также должны быть переданы
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setPatientHeight(String patientHeight) {
		Component<String> patientHeightComponent = new Component<>(String.class, patientHeight);
		setField(patientHeightComponent.toString(), 16);
		this.patientHeight = patientHeightComponent;
	}

	/**
	 * <p>
	 * Это необязательное числовое поле, содержащее вес пациента. Единицы измерения по умолчанию - килограммы. При измерении в другой единице, например фунтах, название единицы также должно передаваться, как указано в Разделе 5.6.4. Информация о росте и весе в настоящее время требуется не всем лабораториям, но имеет значение при оценке нормативных значений, основанных на площади поверхности тела.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getPatientWeight() {
		return patientWeight.getValue();
	}

	/**
	 * <p>
	 * Это необязательное числовое поле, содержащее вес пациента. Единицы измерения по умолчанию - килограммы. При измерении в другой единице, например фунтах, название единицы также должно передаваться, как указано в Разделе 5.6.4. Информация о росте и весе в настоящее время требуется не всем лабораториям, но имеет значение при оценке нормативных значений, основанных на площади поверхности тела.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setPatientWeight(String patientWeight) {
		Component<String> patientWeightComponent = new Component<>(String.class, patientWeight);
		setField(patientWeightComponent.toString(), 17);
		this.patientWeight = patientWeightComponent;
	}

	/**
	 * <p>
	 * Это значение следует вводить либо как код МКБ-9, либо как произвольный текст. Если зарегистрировано несколько диагнозов, они должны быть разделены повторяющимися разделителями.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getPatientsKnownOrSuspectedDiagnosis() {
		return patientsKnownOrSuspectedDiagnosis.getValue();
	}

	/**
	 * <p>
	 * Это значение следует вводить либо как код МКБ-9, либо как произвольный текст. Если зарегистрировано несколько диагнозов, они должны быть разделены повторяющимися разделителями.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setPatientsKnownOrSuspectedDiagnosis(String patientsKnownOrSuspectedDiagnosis) {
		Component<String> patientsKnownOrSuspectedDiagnosisComponent = new Component<>(String.class, patientsKnownOrSuspectedDiagnosis);
		setField(patientsKnownOrSuspectedDiagnosisComponent.toString(), 18);
		this.patientsKnownOrSuspectedDiagnosis = patientsKnownOrSuspectedDiagnosisComponent;
	}

	/**
	 * <p>
	 * Это поле используется для пациентов, принимающих активные лекарства или подозреваемых в передозировке. Должно использоваться родовое имя. Это поле используется для интерпретации клинических результатов.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getPatientActiveMedications() {
		return patientActiveMedications.getValue();
	}

	/**
	 * <p>
	 * Это поле используется для пациентов, принимающих активные лекарства или подозреваемых в передозировке. Должно использоваться родовое имя. Это поле используется для интерпретации клинических результатов.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setPatientActiveMedications(String patientActiveMedications) {
		Component<String> patientActiveMedicationsComponent = new Component<>(String.class, patientActiveMedications);
		setField(patientActiveMedicationsComponent.toString(), 19);
		this.patientActiveMedications = patientActiveMedicationsComponent;
	}

	/**
	 * <p>
	 * Это необязательное поле в произвольном тексте следует использовать для обозначения таких условий, которые влияют на результаты тестирования, например, 16-часовое голодание (для триглицеридов) и отсутствие красного мяса (для тестирования гемокульта).
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getPatientsDiet() {
		return patientsDiet.getValue();
	}

	/**
	 * <p>
	 * Это необязательное поле в произвольном тексте следует использовать для обозначения таких условий, которые влияют на результаты тестирования, например, 16-часовое голодание (для триглицеридов) и отсутствие красного мяса (для тестирования гемокульта).
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setPatientsDiet(String patientsDiet) {
		Component<String> patientsDietComponent = new Component<>(String.class, patientsDiet);
		setField(patientsDietComponent.toString(), 20);
		this.patientsDiet = patientsDietComponent;
	}

	/**
	 * <p>
	 * Это текстовое поле для использования в практике; необязательный переданный текст будет возвращен с результатами.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getPracticeFieldNumber1() {
		return practiceFieldNumber1.getValue();
	}

	/**
	 * <p>
	 * Это текстовое поле для использования в практике; необязательный переданный текст будет возвращен с результатами.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setPracticeFieldNumber1(String practiceFieldNumber1) {
		Component<String> practiceFieldNumber1Component = new Component<>(String.class, practiceFieldNumber1);
		setField(practiceFieldNumber1Component.toString(), 21);
		this.practiceFieldNumber1 = practiceFieldNumber1Component;
	}

	/**
	 * <p>
	 * Это текстовое поле для использования в практике; необязательный переданный текст будет возвращен с результатами.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getPracticeFieldNumber2() {
		return practiceFieldNumber2.getValue();
	}

	/**
	 * <p>
	 * Это текстовое поле для использования в практике; необязательный переданный текст будет возвращен с результатами.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setPracticeFieldNumber2(String practiceFieldNumber2) {
		Component<String> practiceFieldNumber2Component = new Component<>(String.class, practiceFieldNumber2);
		setField(practiceFieldNumber2Component.toString(), 22);
		this.practiceFieldNumber2 = practiceFieldNumber2Component;
	}

	/**
	 * <p>
	 * Эти значения должны быть представлены, как указано в разделе 5.1. Дата выписки, если она указана, следует за датой приема и отделяется от нее повторяющимся разделителем.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getAdmissionAndDischargeDates() {
		return admissionAndDischargeDates.getValue();
	}

	/**
	 * <p>
	 * Эти значения должны быть представлены, как указано в разделе 5.1. Дата выписки, если она указана, следует за датой приема и отделяется от нее повторяющимся разделителем.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setAdmissionAndDischargeDates(String admissionAndDischargeDates) {
		Component<String> admissionAndDischargeDatesComponent = new Component<>(String.class, admissionAndDischargeDates);
		setField(admissionAndDischargeDatesComponent.toString(), 23);
		this.admissionAndDischargeDates = admissionAndDischargeDatesComponent;
	}

	/**
	 * <p>
	 * Это значение должно быть представлено следующим минимальным списком или расширениями, согласованными между отправителем и получателем: OP (амбулаторный), PA (предварительный прием), IP (стационарный), ER (отделение неотложной помощи).
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public AdmissionStatus getAdmissionStatus() {
		return admissionStatus.getValue();
	}

	/**
	 * <p>
	 * Это значение должно быть представлено следующим минимальным списком или расширениями, согласованными между отправителем и получателем: OP (амбулаторный), PA (предварительный прием), IP (стационарный), ER (отделение неотложной помощи).
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setAdmissionStatus(AdmissionStatus admissionStatus) {
		Component<AdmissionStatus> admissionStatusComponent = new Component<>(
			() -> admissionStatus, AdmissionStatus::getIdForComponent
		);
		setField(admissionStatusComponent.toString(), 24);
		this.admissionStatus = admissionStatusComponent;
	}

	/**
	 * <p>
	 * Это текстовое значение должно отражать общее расположение клиники или сестринского отделения, палату или койку (или и то, и другое) пациента в сроки, согласованные отправителем и получателем.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getLocation() {
		return location.getValue();
	}

	/**
	 * <p>
	 * Это текстовое значение должно отражать общее расположение клиники или сестринского отделения, палату или койку (или и то, и другое) пациента в сроки, согласованные отправителем и получателем.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setLocation(String location) {
		Component<String> locationComponent = new Component<>(String.class, location);
		setField(locationComponent.toString(), 25);
		this.location = locationComponent;
	}

	/**
	 * <p>
	 * Это поле относится к разделу 7.28. Он идентифицирует класс кода или классификаторов, которые передаются (например, DRG или в будущем AVG [группы амбулаторного посещения]).
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getNatureOfAlternativeDiagnosticCodeAndClassifiers() {
		return natureOfAlternativeDiagnosticCodeAndClassifiers.getValue();
	}

	/**
	 * <p>
	 * Это поле относится к разделу 7.28. Он идентифицирует класс кода или классификаторов, которые передаются (например, DRG или в будущем AVG [группы амбулаторного посещения]).
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setNatureOfAlternativeDiagnosticCodeAndClassifiers(
			String natureOfAlternativeDiagnosticCodeAndClassifiers) {
		Component<String> natureOfAlternativeDiagnosticCodeAndClassifiersComponent = new Component<>(String.class, natureOfAlternativeDiagnosticCodeAndClassifiers);
		setField(natureOfAlternativeDiagnosticCodeAndClassifiersComponent.toString(), 26);
		this.natureOfAlternativeDiagnosticCodeAndClassifiers = natureOfAlternativeDiagnosticCodeAndClassifiersComponent;
	}

	/**
	 * <p>
	 *В это поле могут быть включены альтернативные диагностические коды и классификации (например, коды DRG). Характер диагностического кода описан в разделе 7.27. Если включено несколько кодов, они должны быть разделены повторяющимися разделителями. За отдельными кодами могут следовать необязательные дескрипторы теста (если последние присутствуют), и они должны быть разделены разделителями компонентов.
	 </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getAlternativeDiagnosticCodeAndClassification() {
		return alternativeDiagnosticCodeAndClassification.getValue();
	}

	/**
	 * <p>
	 * В это поле могут быть включены альтернативные диагностические коды и классификации (например, коды DRG). Характер диагностического кода описан в разделе 7.27. Если включено несколько кодов, они должны быть разделены повторяющимися разделителями. За отдельными кодами могут следовать необязательные дескрипторы теста (если последние присутствуют), и они должны быть разделены разделителями компонентов.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setAlternativeDiagnosticCodeAndClassification(
			String alternativeDiagnosticCodeAndClassification) {
		Component<String> alternativeDiagnosticCodeAndClassificationComponent = new Component<>(String.class, alternativeDiagnosticCodeAndClassification);
		setField(alternativeDiagnosticCodeAndClassificationComponent.toString(), 27);
		this.alternativeDiagnosticCodeAndClassification = alternativeDiagnosticCodeAndClassificationComponent;
	}

	/**
	 * <p>
	 * При необходимости это значение должно включать религию пациента. Коды или имена могут быть отправлены по согласованию между отправителем и получателем. Полные названия религий также могут быть отправлены по запросу.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public PatientReligion getPatientReligion() {
		return patientReligion.getValue();
	}

	/**
	 * <p>
	 * При необходимости это значение должно включать религию пациента. Коды или имена могут быть отправлены по согласованию между отправителем и получателем. Полные названия религий также могут быть отправлены по запросу.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setPatientReligion(PatientReligion patientReligion) {
		Component<PatientReligion> patientReligionComponent = new Component<>(
			() -> patientReligion, PatientReligion::getIdForComponent
		);
		setField(patientReligionComponent.toString(), 28);
		this.patientReligion = patientReligionComponent;
	}

	/**
	 * <p>
	 * При необходимости в этом значении указывается семейное положение пациента.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public MaritalStatus getMaritalStatus() {
		return maritalStatus.getValue();
	}

	/**
	 * <p>
	 * При необходимости в этом значении указывается семейное положение пациента.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setMaritalStatus(MaritalStatus maritalStatus) {
		Component<MaritalStatus> maritalStatusComponent = new Component<>(
			() -> maritalStatus, MaritalStatus::getIdForComponent
		);
		setField(maritalStatusComponent.toString(), 29);
		this.maritalStatus = maritalStatusComponent;
	}


	/**
	 * <p>
	 * Коды изоляции указывают на меры предосторожности, которые необходимо соблюдать для защиты пациента или персонала от инфекции. Ниже приведены рекомендуемые коды для общих мер предосторожности. Можно указать несколько мер предосторожности, разделенных повторяющимися разделителями. Также могут быть отправлены полные текстовые предупреждения.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public IsolationStatus getIsolationStatus() {
		return isolationStatus.getValue();
	}

	/**
	 * <p>
	 * Коды изоляции указывают на меры предосторожности, которые необходимо соблюдать для защиты пациента или персонала от инфекции. Ниже приведены рекомендуемые коды для общих мер предосторожности. Можно указать несколько мер предосторожности, разделенных повторяющимися разделителями. Также могут быть отправлены полные текстовые предупреждения.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setIsolationStatus(IsolationStatus isolationStatus) {
		Component<IsolationStatus> isolationStatusComponent = new Component<>(
			() -> isolationStatus, IsolationStatus::getIdForComponent
		);
		setField(isolationStatusComponent.toString(), 30);
		this.isolationStatus = isolationStatusComponent;
	}

	/**
	 * <p>
	 * Значение этого поля указывает на основной язык пациента. Это может потребоваться, если пациент плохо владеет местным языком.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getLanguage() {
		return language.getValue();
	}

	/**
	 * <p>
	 * Значение этого поля указывает на основной язык пациента. Это может потребоваться, если пациент плохо владеет местным языком.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setLanguage(String language) {
		Component<String> languageComponent = new Component<>(String.class, language);
		setField(languageComponent.toString(), 31);
		this.language = languageComponent;
	}

 	/**
	 * <p>
	 * Это значение указывает на больничную услугу, назначенную пациенту в данный момент. И код, и текст могут быть отправлены, если они разделены разделителем компонентов.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getHospitalService() {
		return hospitalService.getValue();
	}

	/**
	 * <p>
	 * Это значение указывает на больничную услугу, назначенную пациенту в данный момент. И код, и текст могут быть отправлены, если они разделены разделителем компонентов.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setHospitalService(String hospitalService) {
		Component<String> hospitalServiceComponent = new Component<>(String.class, hospitalService);
		setField(hospitalServiceComponent.toString(), 32);
		this.hospitalService = hospitalServiceComponent;
	}

	/**
	 * <p>
	 * Это значение указывает на больничное учреждение, назначенное пациенту в данный момент. И код, и текст могут быть отправлены, если они разделены разделителем компонентов.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getHospitalInstitution() {
		return hospitalInstitution.getValue();
	}

	/**
	 * <p>
	 * Это значение указывает на больничное учреждение, назначенное пациенту в данный момент. И код, и текст могут быть отправлены, если они разделены разделителем компонентов.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setHospitalInstitution(String hospitalInstitution) {
		Component<String> hospitalInstitutionComponent = new Component<>(String.class, hospitalInstitution);
		setField(hospitalInstitutionComponent.toString(), 33);
		this.hospitalInstitution = hospitalInstitutionComponent;
	}


	/**
	 * <p>
	 * Это значение указывает группу дозировки пациента. Например, A – ВЗРОСЛЫЙ, P1 – ПЕДИАТРИЧЕСКИЙ (от одного до шести месяцев), P2 – ПЕДИАТРИЧЕСКИЙ (от шести месяцев до трех лет) и т. Д. Подкомпоненты этого поля могут использоваться для определения подгрупп доз.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getDosageCategory() {
		return dosageCategory.getValue();
	}

	/**
	 * <p>
	 * Это значение указывает группу дозировки пациента. Например, A – ВЗРОСЛЫЙ, P1 – ПЕДИАТРИЧЕСКИЙ (от одного до шести месяцев), P2 – ПЕДИАТРИЧЕСКИЙ (от шести месяцев до трех лет) и т. Д. Подкомпоненты этого поля могут использоваться для определения подгрупп доз.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setDosageCategory(String dosageCategory) {
		Component<String> dosageCategoryComponent = new Component<>(String.class, dosageCategory);
		setField(dosageCategoryComponent.toString(), 34);
		this.dosageCategory = dosageCategoryComponent;
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
