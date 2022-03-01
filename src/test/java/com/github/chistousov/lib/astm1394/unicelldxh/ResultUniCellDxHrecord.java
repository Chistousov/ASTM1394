package com.github.chistousov.lib.astm1394.unicelldxh;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.chistousov.lib.astm1394.record.Component;
import com.github.chistousov.lib.astm1394.record.GeneralConsiderations;
import com.github.chistousov.lib.astm1394.record.IWithComments;
import com.github.chistousov.lib.astm1394.record.Record;
import com.github.chistousov.lib.astm1394.record.RecordType;
import com.github.chistousov.lib.astm1394.record.UniversalTestIDField;
import com.github.chistousov.lib.astm1394.record.comment.CommentRecord;
import com.github.chistousov.lib.astm1394.record.result.ResultAbnormalFlag;
import com.github.chistousov.lib.astm1394.record.result.ResultStatus;

public class ResultUniCellDxHrecord extends Record implements IWithComments<CommentRecord> {
	
	/*
	 * This field shall use universal test ID as described in Section 5.6.1. 
	 */
	private UniversalTestIDField[] universalTestID;

	/*
	 * Whether numeric, text, or coded values, the data shall be recorded in ASCII text notation. If the data
	 * result contains qualifying elements of equal stature, these should be separated by component delimiters.
	 * This applies strictly to results of identical nature (that is, this field may not contain implied subvalues).
	 * Use of components within this field should be avoided whenever possible.
	 * Multiple results or values, observed, calculated, or implied, for a single test order (i.e., MIC or
	 * interpretation codes from a single antibiotic sensitivity test) must be reported in separate result records
	 * with each result definition defined uniquely by the test ID field as given in Section 9.3. Correspondingly,
	 * the test ID field (see Section 9.3) must be sufficiently descriptive to determine the placement of the data
	 * value with reference to the original test order record and to other result records associated with said test
	 * order record. 
	 */
	private Component<String> dataOrMeasurementValue;

	/*
	 * The abbreviation of units for numeric results shall appear here. ISO standard abbreviations in accordance
	 * with ISO 29554
	 * should be employed when available (e.g., use mg rather than milligrams). Units can be 
	 * reported in upper or lower case. 
	 */
	private Component<String> units;

    private Component<Integer> dilutionFactor;

	/*
	 * This value shall be reported in the following sample format: (lower limit to upper limit; i.e., 3.5 to 4.5).
	 * The range definition can be included by text description. See the following paragraph in this section. If a
	 * substance is toxic, then the upper limit of the range identifies the toxic limit. If the substance being
	 * measured is a drug, the lower limits identify the lower therapeutic bounds and the upper limits represent
	 * the upper therapeutic bounds above which toxic side effects are common.
	 * A result may have multiple ranges, for example, an observation may have a physiological and a
	 * therapeutic range (e.g., serum magnesium is being used to treat eclampsia). When multiple ranges are
	 * sent, they shall be separated by repeat delimiters. Each range can also have a text description. The text
	 * description follows immediately after the range, and is separated from it by a component delimiter. Most
	 * results will only have one normal range transmitted. 
	 */
	private Component<String> referenceRanges; 

	/*
	 * This field shall indicate the normalcy status of the result. The characters for representing significant
	 * changes either up or down or abnormal values
	 */
	private Component<ResultAbnormalFlag> resultAbnormalFlag;

	/*
	 * The kind of normal testing performed shall use the following representation: A denotes that an age-based
	 * population was tested; S, a sex-based population; and R, a race-based population. As many of the codes as
	 * apply shall be included. For example, if sex, age, and race normals were tested, an ASR would be
	 * transmitted. N implies that generic normal range was applied to all patient specimens. 
	 */
	private Component<String> natureOfAbnormalityTesting;


	/*
	 * C correction of previously transmitted results
	 * P preliminary results
	 * F final results
	 * X order cannot be done
	 * I in instrument, results pending
	 * S partial results
	 * M this result is an MIC level
	 * R this result was previously transmitted
	 * N this result record contains necessary information to run a new order
	 * NOTE: For example, when ordering a sensitivity, the information system may download a result record
	 * containing the organism type, or species, identified in a previous test.
	 * Q this result is a response to an outstanding query
	 * V operator verified/approved result
	 * W Warning: Validity is questionable 
	 */
	private Component<ResultStatus> resultStatus;
	
	/*
	 * This field shall remain empty if there are no relevant normals or units. Otherwise, it shall be represented
	 * as in Section 5.6.2. A change in these data from those recorded in the receiving system’s dictionary
	 * indicates a need for manual review of the results to detect whether they can be considered the same as
	 * preceding ones. 
	 */ 
	private Component<LocalDateTime> dateOfChangeInInstrumentNormativeValuesOrUnits;

	/*
	 * The first component identifies the instrument operator who performed the test. The second component
	 * identifies the verifier for the test. 
	 */
	private Component<String> operatorIdentification;

	/*
	 * This field records the date and time the instrument started the test for which the results are now being
	 * reported. Date and time should be reported as specified in Section 5.6.2. 
	 */
	private Component<LocalDateTime> dateTimeTestStarted;

	/*
	 * This field records the date and time the instrument completed the test for which the results are now being
	 * reported. Date and time should be reported as specified in Section 5.6.2. 
	 */
	private Component<LocalDateTime> dateTimeTestCompleted;

	/*
	 * This field identifies the instrument or section of instrument that performed this particular measurement
	 */
	private Component<String> instrumentIdentification;

	public ResultUniCellDxHrecord(String fieldDelimiter, String repeatDelimiter, String componentDelimiter, String escapeDelimiter) throws IllegalArgumentException {
		super("", 15, RecordType.R, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
	}

	public ResultUniCellDxHrecord(String record, String fieldDelimiter, String repeatDelimiter, String componentDelimiter, String escapeDelimiter) throws IllegalArgumentException {
        super(record, 15, RecordType.R, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);

        if(record.charAt(0) != 'R'){
            throw new IllegalArgumentException("Сообщение не начинается с R, поэтому не является записью результата теста");
        }
        setSequenceNumber(getField(1));

		//парсинг повторяющегося поля с несколькими компонентами
		String[] repeatFieldsUniversalTestID = getField(2).split("\\" + repeatDelimiter);
		universalTestID  = new UniversalTestIDField[repeatFieldsUniversalTestID.length];

		for(int i =0; i < repeatFieldsUniversalTestID.length; i++){
			String[] components = repeatFieldsUniversalTestID[i].split("\\" + componentDelimiter);
			universalTestID[i] = new UniversalTestIDField(components[0], components[1], components[2], components[3]);
		}

		this.dataOrMeasurementValue = new Component<>(String.class, getField(3));
		this.units = new Component<>(String.class, getField(4));
        this.dilutionFactor = new Component<>(Integer.class, getField(5));
		
        this.referenceRanges = new Component<>(String.class, getField(6));
		this.resultAbnormalFlag = new Component<>(() -> ResultAbnormalFlag.getBy(getField(7)), ResultAbnormalFlag::getIdForComponent);
		this.natureOfAbnormalityTesting = new Component<>(String.class, getField(8));
		this.resultStatus = new Component<>(() -> ResultStatus.getBy(getField(9)), ResultStatus::getIdForComponent);
		this.dateOfChangeInInstrumentNormativeValuesOrUnits = new Component<>(LocalDateTime.class, getField(10));
		this.operatorIdentification = new Component<>(String.class, getField(11));
		this.dateTimeTestStarted = new Component<>(LocalDateTime.class, getField(12));
		this.dateTimeTestCompleted = new Component<>(LocalDateTime.class, getField(13));
		this.instrumentIdentification = new Component<>(String.class, getField(14));
		
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

	public UniversalTestIDField[] getUniversalTestID() {
		return universalTestID;
	}

	public void setUniversalTestID(UniversalTestIDField[] universalTestID) {
		this.universalTestID = universalTestID;
		
		String[] fieldStr = new String[this.universalTestID.length];
		for(int i = 0; i < this.universalTestID.length; i++) {
			fieldStr[i] = universalTestID[i].toString(getComponentDelimiter().getValue());
		}
		setField(String.join("\\" + this.getRepeatDelimiter(), Arrays.asList(fieldStr)), 2);

	}

	public String getDataOrMeasurementValue() {
		return dataOrMeasurementValue.getValue();
	}

	public void setDataOrMeasurementValue(String dataOrMeasurementValue) {
		Component<String> dataOrMeasurementValueComponent = new Component<>(String.class, dataOrMeasurementValue);
		setField(dataOrMeasurementValueComponent.toString(), 3);
		this.dataOrMeasurementValue = dataOrMeasurementValueComponent;
	}

	public String getUnits() {
		return units.getValue();
	}

	public void setUnits(String units) {
		Component<String> unitsComponent = new Component<>(String.class, units);
		setField(unitsComponent.toString(), 4);
		this.units = unitsComponent;
	}

    public Integer getDilutionFactor() {
        return dilutionFactor.getValue();
    }

    public void setDilutionFactor(Integer dilutionFactor) {
		Component<Integer> dilutionFactorComponent = new Component<>(Integer.class, dilutionFactor.toString());
		setField(dilutionFactorComponent.toString(), 5);
		this.dilutionFactor = dilutionFactorComponent;
    }

	public String getReferenceRanges() {
		return referenceRanges.getValue();
	}

	public void setReferenceRanges(String referenceRanges) {
		Component<String> referenceRangesComponent = new Component<>(String.class, referenceRanges);
		setField(referenceRangesComponent.toString(), 6);
		this.referenceRanges = referenceRangesComponent;
	}

	public ResultAbnormalFlag getResultAbnormalFlag() {
		return resultAbnormalFlag.getValue();
	}

	public void setResultAbnormalFlag(ResultAbnormalFlag resultAbnormalFlag) {
		Component<ResultAbnormalFlag> resultAbnormalFlagComponent = new Component<>(
			() -> resultAbnormalFlag, ResultAbnormalFlag::getIdForComponent
		);
		setField(resultAbnormalFlagComponent.toString(), 7);
		this.resultAbnormalFlag = resultAbnormalFlagComponent;
	}

	public String getNatureOfAbnormalityTesting() {
		return natureOfAbnormalityTesting.getValue();
	}

	public void setNatureOfAbnormalityTesting(String natureOfAbnormalityTesting) {
		Component<String> natureOfAbnormalityTestingComponent = new Component<>(String.class, natureOfAbnormalityTesting);
		setField(natureOfAbnormalityTestingComponent.toString(), 8);
		this.natureOfAbnormalityTesting = natureOfAbnormalityTestingComponent;
	}

	public ResultStatus getResultStatus() {
		return resultStatus.getValue();
	}

	public void setResultStatus(ResultStatus resultStatus) {
		Component<ResultStatus> resultStatusComponent = new Component<>(
			() -> resultStatus, ResultStatus::getIdForComponent
		);
		setField(resultStatusComponent.toString(), 9);
		this.resultStatus = resultStatusComponent;
	}

	public LocalDateTime getDateOfChangeInInstrumentNormativeValuesOrUnits() {
		return dateOfChangeInInstrumentNormativeValuesOrUnits.getValue();
	}

	public void setDateOfChangeInInstrumentNormativeValuesOrUnits(
			LocalDateTime dateOfChangeInInstrumentNormativeValuesOrUnits) {
		Component<LocalDateTime> dateOfChangeInInstrumentNormativeValuesOrUnitsComponent = new Component<>(LocalDateTime.class, dateOfChangeInInstrumentNormativeValuesOrUnits.format(GeneralConsiderations.DATE_TIME_FORMATTER));
		setField(dateOfChangeInInstrumentNormativeValuesOrUnitsComponent.toString(), 10);
		this.dateOfChangeInInstrumentNormativeValuesOrUnits = dateOfChangeInInstrumentNormativeValuesOrUnitsComponent;
	}

	public String getOperatorIdentification() {
		return operatorIdentification.getValue();
	}

	public void setOperatorIdentification(String operatorIdentification) {
		Component<String> operatorIdentificationComponent = new Component<>(String.class, operatorIdentification);
		setField(operatorIdentificationComponent.toString(), 11);
		this.natureOfAbnormalityTesting = operatorIdentificationComponent;
	}

	public LocalDateTime getDateTimeTestStarted() {
		return dateTimeTestStarted.getValue();
	}

	public void setDateTimeTestStarted(LocalDateTime dateTimeTestStarted) {
		Component<LocalDateTime> dateTimeTestStartedComponent = new Component<>(LocalDateTime.class, dateTimeTestStarted.format(GeneralConsiderations.DATE_TIME_FORMATTER));
		setField(dateTimeTestStartedComponent.toString(), 12);
		this.dateTimeTestStarted = dateTimeTestStartedComponent;
	}

	public LocalDateTime getDateTimeTestCompleted() {
		return dateTimeTestCompleted.getValue();
	}

	public void setDateTimeTestCompleted(LocalDateTime dateTimeTestCompleted) {
		Component<LocalDateTime> dateTimeTestCompletedComponent = new Component<>(LocalDateTime.class, dateTimeTestCompleted.format(GeneralConsiderations.DATE_TIME_FORMATTER));
		setField(dateTimeTestCompletedComponent.toString(), 13);
		this.dateTimeTestCompleted = dateTimeTestCompletedComponent;
	}

	public String getInstrumentIdentification() {
		return instrumentIdentification.getValue();
	}

	public void setInstrumentIdentification(String instrumentIdentification) {
		Component<String> instrumentIdentificationComponent = new Component<>(String.class, instrumentIdentification);
		setField(instrumentIdentificationComponent.toString(), 14);
		this.instrumentIdentification = instrumentIdentificationComponent;
	}

	
	private List<CommentRecord> commentRecords;
	
	@Override
	public void addCommentRecord(CommentRecord commentRecord){
		if(this.commentRecords == null) {
			this.commentRecords = new ArrayList<>();
		}
		commentRecord.setSequenceNumber(String.valueOf(this.commentRecords.size() + 1));
		this.commentRecords.add(commentRecord);
	}
	
	@Override
	public List<CommentRecord> getCommentRecords() {
		return commentRecords;
	}

}