package com.github.chistousov.lib.astm1394.record;

import java.time.LocalDateTime;


/**
 * <p>
 * In Scientific Records, test data relating to the operation of clinical instrument laboratories, quality assurance, 
 * or method development are exchanged. It contains information in addition to the analyte measurements 
 * found in the result record, although there are common elements in the two records.
 * (В научных отчетах (Scientific Record) обмениваются данными испытаний, касающимися работы клинических лабораторий/приборов, 
 * обеспечения качества или разработки методов. Он содержит информацию в дополнение к измерениям аналитов, 
 * найденным в записи результатов, хотя в этих двух записях есть общие элементы.)
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public class ScientificRecord extends Record{
	
	/*
	 * Это текстовое поле должно соответствовать Приложению I к Elevitch и Boroviczeny.
	 */
	private Component<String> analyticalMethod;

	/*
	 * Это текстовое поле должно быть представлено идентификатором, состоящим из кодов производителя и прибора, 
	 * соединенных тире (Latin-1 (45)). Эти коды должны соответствовать Приложению I к Elevitch и Boroviczeny5.
	 */
	private Component<String> instrumentation;

	/*
	 * Это текстовое поле должно включать список кодов составляющих реагентов, разделенных идентификатором подполя. Эти коды должны соответствовать схеме Американского химического общества.
	 */
	private Component<String> reagents;

	/*
	 * Единицы измерения должны быть представлены, как указано в Разделе 9.5.
	 */
	private Component<String> unitsOfMeasure;

	/*
	 * Спецификации по контролю качества разрабатываются.
	 */ 
	private Component<String> qualityControl;

	/*
	 * В этом поле должно использоваться соглашение, описанное в разделе 8.4.16.
	 */
	private Component<String> specimenDescriptor;

	/*
	 * Это поле зарезервировано для будущего расширения.
	 */
	private Component<String> reservedField;

	/*
	 * Технические условия на контейнеры разрабатываются.
	 */
	private Component<String> container;

	/*
	 * Это текстовое поле должно представлять уникальный идентификатор образца, присвоенный отправителем и возвращенный принимающим прибором. 
	 */ 
	private Component<String> specimenId;

	/*
	 * Спецификации на аналит находятся в стадии разработки.
	 */
	private Component<String> analyte;

	/*
	 * Это числовое поле должно представлять определенное значение аналита.
	 */
	private Component<String> result;

	/*
	 * Это поле должно быть представлено, как описано в разделе 9.5.
	 */
	private Component<String> resultUnits;
	  
	/*
	 * Это поле должно быть представлено в соответствии с разделом 5.6.2.
	 */
	private Component<LocalDateTime> collectionDateAndTime;

	/*
	 * Это поле должно быть представлено в соответствии с разделом 5.6.2.
	 */
	private Component<LocalDateTime> resultDateAndTime;

	/*
	 * Это текстовое поле должно содержать описание любых шагов предварительной обработки.
	 */
	private Component<String> analyticalPreprocessingSteps;

	/*
	 * Это поле должно быть представлено кодами ICD-9-CM.
	 */ 
	private Component<String> patientDiagnosis;

	/*
	 * Это должно быть представлено, как указано в разделе 7.8.
	 */
	private Component<String> patientBirthdate;

	/*
	 * Это поле должно быть представлено в соответствии с разделом 7.9.
	 */
	private Component<String> patientSex;

	/*
	 * Это должно быть представлено в соответствии с Разделом 7.10.
	 */
	private Component<String> patientRace;

	public ScientificRecord(String fieldDelimiter, String repeatDelimiter, String componentDelimiter, String escapeDelimiter) {
        super("", 21, RecordType.S, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
	}

	public ScientificRecord(String record, String fieldDelimiter, String repeatDelimiter, String componentDelimiter, String escapeDelimiter) throws IllegalArgumentException {
        super(record, 21, RecordType.S, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);

        if(record.charAt(0) != 'S'){
            throw new IllegalArgumentException("The message does not start with an S, so it is not a Scientific Record.");
        }
        setSequenceNumber(getField(1));

		this.analyticalMethod = new Component<>(String.class, getField(2));
		this.instrumentation = new Component<>(String.class, getField(3));
		this.reagents = new Component<>(String.class, getField(4));
		this.unitsOfMeasure = new Component<>(String.class, getField(5));
		this.qualityControl = new Component<>(String.class, getField(6));
		this.specimenDescriptor = new Component<>(String.class, getField(7));
		this.reservedField = new Component<>(String.class, getField(8));
		this.container = new Component<>(String.class, getField(9));
		this.specimenId = new Component<>(String.class, getField(10));
		this.analyte = new Component<>(String.class, getField(11));
		this.result = new Component<>(String.class, getField(12));
		this.resultUnits = new Component<>(String.class, getField(13));
		this.collectionDateAndTime = new Component<>(LocalDateTime.class, getField(14));
		this.resultDateAndTime = new Component<>(LocalDateTime.class, getField(15));
		this.analyticalPreprocessingSteps = new Component<>(String.class, getField(16));
		this.patientDiagnosis = new Component<>(String.class, getField(17));
		this.patientBirthdate = new Component<>(String.class, getField(18));
		this.patientSex = new Component<>(String.class, getField(19));
		this.patientRace = new Component<>(String.class, getField(20));
	}

}
