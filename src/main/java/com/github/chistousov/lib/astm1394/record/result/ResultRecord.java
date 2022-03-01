package com.github.chistousov.lib.astm1394.record.result;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.github.chistousov.lib.astm1394.record.Component;
import com.github.chistousov.lib.astm1394.record.GeneralConsiderations;
import com.github.chistousov.lib.astm1394.record.IWithComments;
import com.github.chistousov.lib.astm1394.record.Record;
import com.github.chistousov.lib.astm1394.record.RecordType;
import com.github.chistousov.lib.astm1394.record.comment.CommentRecord;

/**
 * <p>
 * Result Record
 * Запись результата
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public class ResultRecord<T extends CommentRecord> extends Record implements IWithComments<T> {
	
	/*
	 * Это первый компонент поля идентификатора теста. Это поле в настоящее время не используется, но зарезервировано для применения универсального кода идентификатора теста (коды LOINC), если одна система станет доступной для использования в будущем.
	 */
	private Component<String> universalTestID;
	
	/*
	 * Это будет имя теста или батареи, связанное с универсальным идентификационным кодом теста, описанным в разделе 5.6.1.1.
	 */
	private Component<String> universalTestIdName;

	/*
	 * В случае, когда существует несколько национальных или международных схем кодирования, это поле может использоваться для определения того, какая схема кодирования используется в полях идентификатора теста и имени идентификатора теста.
	 */
	private Component<String> universalTestIdType;

	/*
	 * Это код, определенный производителем. Этот код может быть числом, символами или множественным обозначением теста на основе разделителей, определенных изготовителем (то есть AK.23.34-B). За расширениями или квалификаторами этого кода могут следовать последующие поля компонентов, которые должны быть определены и задокументированы производителем. Например, этот код может представлять собой трехкомпонентный идентификатор, такой как: Dilution ˆ Diluent ˆ Description.
	 */
	private Component<String> manufacturersOrLocalCode;

	/*
	 * Дополнительные параметры поля 3
	 */
	private List<Component<String>> additionalParameters;

	/*
	 * Будь то числовые, текстовые или кодированные значения, данные должны быть записаны в текстовой нотации ASCII. Если результат данных содержит квалифицирующие элементы одинакового статуса, они должны быть разделены разделителями компонентов.
	 * Это относится исключительно к результатам идентичного характера (то есть это поле не может содержать подразумеваемых подзначений). По возможности следует избегать использования компонентов в этом поле. Множественные результаты или значения, наблюдаемые, рассчитанные или предполагаемые для одного заказа теста (т. Е. MIC или коды интерпретации из одного теста на чувствительность к антибиотикам), должны быть представлены в отдельных записях результатов, причем каждое определение результата однозначно определяется полем идентификатора теста как приведено в разделе 9.3. Соответственно, поле идентификатора теста (см. Раздел 9.3) должно быть достаточно информативным, чтобы определять размещение значения данных со ссылкой на исходную запись заказа на тестирование и другие записи результатов, связанные с указанной записью заказа на тестирование.
	 */
	private Component<String> dataOrMeasurementValue;

	/*
	 * Здесь должно быть указано сокращение единиц для числовых результатов. При наличии следует использовать стандартные сокращения ISO в соответствии с ISO 29554 (например, использовать мг, а не миллиграммы). Единицы могут быть указаны в верхнем или нижнем регистре.
	 */
	private Component<String> units;

	/*
	 * Это значение должно быть представлено в следующем формате выборки: (от нижнего предела до верхнего предела, т. Е. От 3,5 до 4,5). Определение диапазона может быть включено в текстовое описание. См. Следующий абзац в этом разделе. Если вещество токсично, то верхний предел диапазона определяет предел токсичности. Если измеряемое вещество является лекарственным средством, нижние пределы определяют нижние терапевтические пределы, а верхние пределы представляют собой верхние терапевтические границы, выше которых часто встречаются токсические побочные эффекты. Результат может иметь несколько диапазонов, например, наблюдение может иметь физиологический и терапевтический диапазон (например, сывороточный магний используется для лечения эклампсии). При отправке нескольких диапазонов они должны быть разделены повторяющимися разделителями. Каждый диапазон также может иметь текстовое описание. Текстовое описание следует сразу после диапазона и отделяется от него разделителем компонентов. Для большинства результатов будет передаваться только один нормальный диапазон.
	 */
	private Component<String> referenceRanges; 

	/*
	 * Это поле должно указывать на нормальный статус результата. Символы для обозначения значительных изменений вверх или вниз или аномальных значений
	 */
	private Component<ResultAbnormalFlag> resultAbnormalFlag;

	/*
	 * Тип выполняемого нормального тестирования должен использовать следующее представление: A означает, что тестировалась популяция на основе возраста; S - популяция по половому признаку; и R - расовое население. Должно быть включено столько кодов, сколько применимо. Например, при проверке нормального пола, возраста и расы будет передан ASR. N означает, что ко всем образцам от пациентов применялся общий нормальный диапазон.
	 */
	private Component<String> natureOfAbnormalityTesting;


	/*
	 * Статус результата
	 */
	private Component<ResultStatus> resultStatus;
	
	/*
	 * Это поле должно оставаться пустым, если нет соответствующих нормалей или единиц. В противном случае он должен быть представлен как в разделе 5.6.2. Отличие этих данных от данных, записанных в словаре принимающей системы, указывает на необходимость ручного просмотра результатов, чтобы определить, можно ли их считать такими же, как предыдущие.
	 */ 
	private Component<LocalDateTime> dateOfChangeInInstrumentNormativeValuesOrUnits;

	/*
	 * Первый компонент идентифицирует оператора прибора, который выполнил испытание. Второй компонент идентифицирует проверяющего для теста.
	 */
	private Component<String> operatorIdentification;

	/*
	 * В этом поле записываются дата и время, когда прибор начал тест, результаты которого теперь сообщаются. Дата и время должны быть указаны, как указано в разделе 5.6.2.
	 */
	private Component<LocalDateTime> dateTimeTestStarted;

	/*
	 * В этом поле записывается дата и время, когда прибор завершил тест, результаты которого теперь сообщаются. Дата и время должны быть указаны, как указано в разделе 5.6.2.
	 */
	private Component<LocalDateTime> dateTimeTestCompleted;

	/*
	 * В этом поле указывается прибор или часть прибора, которые выполнили это конкретное измерение.
	 */
	private Component<String> instrumentIdentification;

	public ResultRecord(String fieldDelimiter, String repeatDelimiter, String componentDelimiter, String escapeDelimiter){
		super("", 14, RecordType.R, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
	}

	public ResultRecord(String recordString, String fieldDelimiter, String repeatDelimiter, String componentDelimiter, String escapeDelimiter) throws IllegalArgumentException {
        super(recordString, 14, RecordType.R, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);

        if(recordString.charAt(0) != 'R'){
            throw new IllegalArgumentException("Сообщение не начинается с R, поэтому не является записью результата теста");
        }
        setSequenceNumber(getField(1));

		String[] components = getField(2).split("\\" + componentDelimiter);

		if(components.length > 1) {
            try{
                
				setUniversalTestID(components[0]);
            } catch(ArrayIndexOutOfBoundsException ex) {
            }

            try{
				setUniversalTestIdName(components[1]);
            } catch(ArrayIndexOutOfBoundsException ex) {
            }

            try{
				setUniversalTestIdType(components[2]);
            } catch(ArrayIndexOutOfBoundsException ex) {
            }

			try{
				setManufacturersOrLocalCode(components[3]);
            } catch(ArrayIndexOutOfBoundsException ex) {
			}

			if(components.length > 4){
				setAdditionalParameters(Arrays.copyOfRange(components, 4, components.length));
			}

        }


		this.dataOrMeasurementValue = new Component<>(String.class, getField(3));
		this.units = new Component<>(String.class, getField(4));
		this.referenceRanges = new Component<>(String.class, getField(5));
		this.resultAbnormalFlag = new Component<>(() -> ResultAbnormalFlag.getBy(getField(6)), ResultAbnormalFlag::getIdForComponent);
		this.natureOfAbnormalityTesting = new Component<>(String.class, getField(7));
		this.resultStatus = new Component<>(() -> ResultStatus.getBy(getField(8)), ResultStatus::getIdForComponent);
		this.dateOfChangeInInstrumentNormativeValuesOrUnits = new Component<>(LocalDateTime.class, getField(9));
		this.operatorIdentification = new Component<>(String.class, getField(10));
		this.dateTimeTestStarted = new Component<>(LocalDateTime.class, getField(11));
		this.dateTimeTestCompleted = new Component<>(LocalDateTime.class, getField(12));
		this.instrumentIdentification = new Component<>(String.class, getField(13));
		
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
	 * Это первый компонент поля идентификатора теста. Это поле в настоящее время не используется, но зарезервировано для применения универсального кода идентификатора теста (коды LOINC), если одна система станет доступной для использования в будущем.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getUniversalTestID() {
		return universalTestID.getValue();
	}

	/**
	 * <p>
	 * Это будет имя теста или батареи, связанное с универсальным идентификационным кодом теста, описанным в разделе 5.6.1.1.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getUniversalTestIdName() {
		return universalTestIdName.getValue();
	}

	/**
	 * <p>
	 * В случае, когда существует несколько национальных или международных схем кодирования, это поле может использоваться для определения того, какая схема кодирования используется в полях идентификатора теста и имени идентификатора теста.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getUniversalTestIdType() {
		return universalTestIdType.getValue();
	}


	/**
	 * <p>
	 * Это код, определенный производителем. Этот код может быть числом, символами или множественным обозначением теста на основе разделителей, определенных изготовителем (то есть AK.23.34-B). За расширениями или квалификаторами этого кода могут следовать последующие поля компонентов, которые должны быть определены и задокументированы производителем. Например, этот код может представлять собой трехкомпонентный идентификатор, такой как: Dilution ˆ Diluent ˆ Description.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getManufacturersOrLocalCode() {
		return manufacturersOrLocalCode.getValue();
	}

	/**
	 * <p>
	 * Дополнительные параметры поля 3
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String[] getAdditionalParameters() {
		return additionalParameters.stream().map(Component::toString).toArray(String[]::new);
	}

	/**
	 * <p>
	 * Это первый компонент поля идентификатора теста. Это поле в настоящее время не используется, но зарезервировано для применения универсального кода идентификатора теста (коды LOINC), если одна система станет доступной для использования в будущем.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setUniversalTestID(String universalTestID) {
		this.universalTestID = new Component<>(String.class, universalTestID);
		getField3();
	}

	/**
	 * <p>
	 * Это будет имя теста или батареи, связанное с универсальным идентификационным кодом теста, описанным в разделе 5.6.1.1.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setUniversalTestIdName(String universalTestIdName) {
		this.universalTestIdName = new Component<>(String.class, universalTestIdName);
		getField3();
	}

	/**
	 * <p>
	 * В случае, когда существует несколько национальных или международных схем кодирования, это поле может использоваться для определения того, какая схема кодирования используется в полях идентификатора теста и имени идентификатора теста.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setUniversalTestIdType(String universalTestIdType) {
		this.universalTestIdType = new Component<>(String.class, universalTestIdType);
		getField3();
	}

	/**
	 * <p>
	 * Это код, определенный производителем. Этот код может быть числом, символами или множественным обозначением теста на основе разделителей, определенных изготовителем (то есть AK.23.34-B). За расширениями или квалификаторами этого кода могут следовать последующие поля компонентов, которые должны быть определены и задокументированы производителем. Например, этот код может представлять собой трехкомпонентный идентификатор, такой как: Dilution ˆ Diluent ˆ Description.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setManufacturersOrLocalCode(String manufacturersOrLocalCode) {
		this.manufacturersOrLocalCode = new Component<>(String.class, manufacturersOrLocalCode);
		getField3();
	}

	/**
	 * <p>
	 * Дополнительные параметры поля 3
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setAdditionalParameters(String[] additionalParameters) {
		this.additionalParameters = Arrays.asList(additionalParameters).stream().map(addParam -> new Component<String>(String.class, addParam)).collect(Collectors.toList());
		getField3();
	}

    private void getField3(){
        String univTestID = this.universalTestID != null ? this.universalTestID.getValue() : null;
        String univTestIdName = this.universalTestIdName != null ? this.universalTestIdName.getValue() : null;
        String univTestIdType = this.universalTestIdType != null ? this.universalTestIdType.getValue() : null;
        String manufOrLocalCode = this.manufacturersOrLocalCode != null ? this.manufacturersOrLocalCode.getValue() : null;

		List<String> buffer = new ArrayList<>(Arrays.asList(univTestID, univTestIdName, univTestIdType, manufOrLocalCode));

		if(this.additionalParameters != null) {
			buffer.addAll(this.additionalParameters.stream().map(Component::getValue).collect(Collectors.toList()));
		}
		
        String field8 = buffer.stream().filter(Objects::nonNull).collect(Collectors.joining(getComponentDelimiter().getValue()));

		setField(field8, 2);
    }

	public String getComponentField3(int index){
		String[] additionalParams = getAdditionalParameters();
		if(additionalParams != null && additionalParams.length > 0){
			return additionalParams[index];
		}
		return "";
	}

	public void setComponentField3(int index, String value){
		String[] additionalParams = getAdditionalParameters();
		if(additionalParams != null && additionalParams.length > 0){
			additionalParams[index] = value;
		}
		setAdditionalParameters(additionalParams);
	}

	/**
	 * <p>
	 * Будь то числовые, текстовые или кодированные значения, данные должны быть записаны в текстовой нотации ASCII. Если результат данных содержит квалифицирующие элементы одинакового статуса, они должны быть разделены разделителями компонентов.
	 * Это относится исключительно к результатам идентичного характера (то есть это поле не может содержать подразумеваемых подзначений). По возможности следует избегать использования компонентов в этом поле. Множественные результаты или значения, наблюдаемые, рассчитанные или предполагаемые для одного заказа теста (т. Е. MIC или коды интерпретации из одного теста на чувствительность к антибиотикам), должны быть представлены в отдельных записях результатов, причем каждое определение результата однозначно определяется полем идентификатора теста как приведено в разделе 9.3. Соответственно, поле идентификатора теста (см. Раздел 9.3) должно быть достаточно информативным, чтобы определять размещение значения данных со ссылкой на исходную запись заказа на тестирование и другие записи результатов, связанные с указанной записью заказа на тестирование.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getDataOrMeasurementValue() {
		return dataOrMeasurementValue.getValue();
	}

	/**
	 * <p>
	 * Будь то числовые, текстовые или кодированные значения, данные должны быть записаны в текстовой нотации ASCII. Если результат данных содержит квалифицирующие элементы одинакового статуса, они должны быть разделены разделителями компонентов.
	 * Это относится исключительно к результатам идентичного характера (то есть это поле не может содержать подразумеваемых подзначений). По возможности следует избегать использования компонентов в этом поле. Множественные результаты или значения, наблюдаемые, рассчитанные или предполагаемые для одного заказа теста (т. Е. MIC или коды интерпретации из одного теста на чувствительность к антибиотикам), должны быть представлены в отдельных записях результатов, причем каждое определение результата однозначно определяется полем идентификатора теста как приведено в разделе 9.3. Соответственно, поле идентификатора теста (см. Раздел 9.3) должно быть достаточно информативным, чтобы определять размещение значения данных со ссылкой на исходную запись заказа на тестирование и другие записи результатов, связанные с указанной записью заказа на тестирование.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setDataOrMeasurementValue(String dataOrMeasurementValue) {
		Component<String> dataOrMeasurementValueComponent = new Component<>(String.class, dataOrMeasurementValue);
		setField(dataOrMeasurementValueComponent.toString(), 3);
		this.dataOrMeasurementValue = dataOrMeasurementValueComponent;
	}

	/**
	 * <p>
	 * Здесь должно быть указано сокращение единиц для числовых результатов. При наличии следует использовать стандартные сокращения ISO в соответствии с ISO 29554 (например, использовать мг, а не миллиграммы). Единицы могут быть указаны в верхнем или нижнем регистре.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getUnits() {
		return units.getValue();
	}

	/**
	 * <p>
	 * Здесь должно быть указано сокращение единиц для числовых результатов. При наличии следует использовать стандартные сокращения ISO в соответствии с ISO 29554 (например, использовать мг, а не миллиграммы). Единицы могут быть указаны в верхнем или нижнем регистре.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setUnits(String units) {
		Component<String> unitsComponent = new Component<>(String.class, units);
		setField(unitsComponent.toString(), 4);
		this.units = unitsComponent;
	}

	/**
	 * <p>
	 * Это значение должно быть представлено в следующем формате выборки: (от нижнего предела до верхнего предела, т. Е. От 3,5 до 4,5). Определение диапазона может быть включено в текстовое описание. См. Следующий абзац в этом разделе. Если вещество токсично, то верхний предел диапазона определяет предел токсичности. Если измеряемое вещество является лекарственным средством, нижние пределы определяют нижние терапевтические пределы, а верхние пределы представляют собой верхние терапевтические границы, выше которых часто встречаются токсические побочные эффекты. Результат может иметь несколько диапазонов, например, наблюдение может иметь физиологический и терапевтический диапазон (например, сывороточный магний используется для лечения эклампсии). При отправке нескольких диапазонов они должны быть разделены повторяющимися разделителями. Каждый диапазон также может иметь текстовое описание. Текстовое описание следует сразу после диапазона и отделяется от него разделителем компонентов. Для большинства результатов будет передаваться только один нормальный диапазон.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getReferenceRanges() {
		return referenceRanges.getValue();
	}

	/**
	 * <p>
	 * Это значение должно быть представлено в следующем формате выборки: (от нижнего предела до верхнего предела, т. Е. От 3,5 до 4,5). Определение диапазона может быть включено в текстовое описание. См. Следующий абзац в этом разделе. Если вещество токсично, то верхний предел диапазона определяет предел токсичности. Если измеряемое вещество является лекарственным средством, нижние пределы определяют нижние терапевтические пределы, а верхние пределы представляют собой верхние терапевтические границы, выше которых часто встречаются токсические побочные эффекты. Результат может иметь несколько диапазонов, например, наблюдение может иметь физиологический и терапевтический диапазон (например, сывороточный магний используется для лечения эклампсии). При отправке нескольких диапазонов они должны быть разделены повторяющимися разделителями. Каждый диапазон также может иметь текстовое описание. Текстовое описание следует сразу после диапазона и отделяется от него разделителем компонентов. Для большинства результатов будет передаваться только один нормальный диапазон.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setReferenceRanges(String referenceRanges) {
		Component<String> referenceRangesComponent = new Component<>(String.class, referenceRanges);
		setField(referenceRangesComponent.toString(), 5);
		this.referenceRanges = referenceRangesComponent;
	}

  	/**
	 * <p>
	 * Это поле должно указывать на нормальный статус результата. Символы для обозначения значительных изменений вверх или вниз или аномальных значений
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public ResultAbnormalFlag getResultAbnormalFlag() {
		return resultAbnormalFlag.getValue();
	}

	/**
	 * <p>
	 * Это поле должно указывать на нормальный статус результата. Символы для обозначения значительных изменений вверх или вниз или аномальных значений
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setResultAbnormalFlag(ResultAbnormalFlag resultAbnormalFlag) {
		Component<ResultAbnormalFlag> resultAbnormalFlagComponent = new Component<>(
			() -> resultAbnormalFlag, ResultAbnormalFlag::getIdForComponent
		);
		setField(resultAbnormalFlagComponent.toString(), 6);
		this.resultAbnormalFlag = resultAbnormalFlagComponent;
	}

	/**
	 * <p>
	 * Тип выполняемого нормального тестирования должен использовать следующее представление: A означает, что тестировалась популяция на основе возраста; S - популяция по половому признаку; и R - расовое население. Должно быть включено столько кодов, сколько применимо. Например, при проверке нормального пола, возраста и расы будет передан ASR. N означает, что ко всем образцам от пациентов применялся общий нормальный диапазон.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getNatureOfAbnormalityTesting() {
		return natureOfAbnormalityTesting.getValue();
	}

	/**
	 * <p>
	 * Тип выполняемого нормального тестирования должен использовать следующее представление: A означает, что тестировалась популяция на основе возраста; S - популяция по половому признаку; и R - расовое население. Должно быть включено столько кодов, сколько применимо. Например, при проверке нормального пола, возраста и расы будет передан ASR. N означает, что ко всем образцам от пациентов применялся общий нормальный диапазон.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setNatureOfAbnormalityTesting(String natureOfAbnormalityTesting) {
		Component<String> natureOfAbnormalityTestingComponent = new Component<>(String.class, natureOfAbnormalityTesting);
		setField(natureOfAbnormalityTestingComponent.toString(), 7);
		this.natureOfAbnormalityTesting = natureOfAbnormalityTestingComponent;
	}

	/**
	 * <p>
	 * Статус результата
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public ResultStatus getResultStatus() {
		return resultStatus.getValue();
	}

	/**
	 * <p>
	 * Статус результата
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setResultStatus(ResultStatus resultStatus) {
		Component<ResultStatus> resultStatusComponent = new Component<>(
			() -> resultStatus, ResultStatus::getIdForComponent
		);
		setField(resultStatusComponent.toString(), 8);
		this.resultStatus = resultStatusComponent;
	}

	/**
	 * <p>
	 * Это поле должно оставаться пустым, если нет соответствующих нормалей или единиц. В противном случае он должен быть представлен как в разделе 5.6.2. Отличие этих данных от данных, записанных в словаре принимающей системы, указывает на необходимость ручного просмотра результатов, чтобы определить, можно ли их считать такими же, как предыдущие.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public LocalDateTime getDateOfChangeInInstrumentNormativeValuesOrUnits() {
		return dateOfChangeInInstrumentNormativeValuesOrUnits.getValue();
	}

	/**
	 * <p>
	 * Это поле должно оставаться пустым, если нет соответствующих нормалей или единиц. В противном случае он должен быть представлен как в разделе 5.6.2. Отличие этих данных от данных, записанных в словаре принимающей системы, указывает на необходимость ручного просмотра результатов, чтобы определить, можно ли их считать такими же, как предыдущие.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setDateOfChangeInInstrumentNormativeValuesOrUnits(
			LocalDateTime dateOfChangeInInstrumentNormativeValuesOrUnits) {
		Component<LocalDateTime> dateOfChangeInInstrumentNormativeValuesOrUnitsComponent = new Component<>(LocalDateTime.class, dateOfChangeInInstrumentNormativeValuesOrUnits.format(GeneralConsiderations.DATE_TIME_FORMATTER));
		setField(dateOfChangeInInstrumentNormativeValuesOrUnitsComponent.toString(), 9);
		this.dateOfChangeInInstrumentNormativeValuesOrUnits = dateOfChangeInInstrumentNormativeValuesOrUnitsComponent;
	}

	/**
	 * <p>
	 * Первый компонент идентифицирует оператора прибора, который выполнил испытание. Второй компонент идентифицирует проверяющего для теста.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getOperatorIdentification() {
		return operatorIdentification.getValue();
	}

		/**
	 * <p>
	 * Первый компонент идентифицирует оператора прибора, который выполнил испытание. Второй компонент идентифицирует проверяющего для теста.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setOperatorIdentification(String operatorIdentification) {
		Component<String> operatorIdentificationComponent = new Component<>(String.class, operatorIdentification);
		setField(operatorIdentificationComponent.toString(), 10);
		this.operatorIdentification = operatorIdentificationComponent;
	}

	/**
	 * <p>
	 * В этом поле записываются дата и время, когда прибор начал тест, результаты которого теперь сообщаются. Дата и время должны быть указаны, как указано в разделе 5.6.2.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public LocalDateTime getDateTimeTestStarted() {
		return dateTimeTestStarted.getValue();
	}

	/**
	 * <p>
	 * В этом поле записываются дата и время, когда прибор начал тест, результаты которого теперь сообщаются. Дата и время должны быть указаны, как указано в разделе 5.6.2.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setDateTimeTestStarted(LocalDateTime dateTimeTestStarted) {
		Component<LocalDateTime> dateTimeTestStartedComponent = new Component<>(LocalDateTime.class, dateTimeTestStarted.format(GeneralConsiderations.DATE_TIME_FORMATTER));
		setField(dateTimeTestStartedComponent.toString(), 11);
		this.dateTimeTestStarted = dateTimeTestStartedComponent;
	}

    /**
	 * <p>
	 * В этом поле записывается дата и время, когда прибор завершил тест, результаты которого теперь сообщаются. Дата и время должны быть указаны, как указано в разделе 5.6.2.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public LocalDateTime getDateTimeTestCompleted() {
		return dateTimeTestCompleted.getValue();
	}

	/**
	 * <p>
	 * В этом поле записывается дата и время, когда прибор завершил тест, результаты которого теперь сообщаются. Дата и время должны быть указаны, как указано в разделе 5.6.2.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setDateTimeTestCompleted(LocalDateTime dateTimeTestCompleted) {
		Component<LocalDateTime> dateTimeTestCompletedComponent = new Component<>(LocalDateTime.class, dateTimeTestCompleted.format(GeneralConsiderations.DATE_TIME_FORMATTER));
		setField(dateTimeTestCompletedComponent.toString(), 12);
		this.dateTimeTestCompleted = dateTimeTestCompletedComponent;
	}

	/**
	 * <p>
	 * В этом поле указывается прибор или часть прибора, которые выполнили это конкретное измерение.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public String getInstrumentIdentification() {
		return instrumentIdentification.getValue();
	}

	/**
	 * <p>
	 * В этом поле указывается прибор или часть прибора, которые выполнили это конкретное измерение.
	 * </p>
	 * 
	 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
	 * @since 8
     * 
     */
	public void setInstrumentIdentification(String instrumentIdentification) {
		Component<String> instrumentIdentificationComponent = new Component<>(String.class, instrumentIdentification);
		setField(instrumentIdentificationComponent.toString(), 13);
		this.instrumentIdentification = instrumentIdentificationComponent;
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
