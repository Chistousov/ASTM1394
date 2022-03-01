package com.github.chistousov.lib.astm1394.record;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * This abstract class implements the common functionality of all records (record)
 * (Это абстрактный класс реализует общую функциональность всех записей(record))
 * </p>
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public abstract class Record {
    
    /*
     * Тип записи
     */
    private RecordType recordTypeId;

    /*
     * Поля записи (fields)
     */
    private String[] fields;

    /*
     * Разделители: полей(record), повторяющихся полей(repeat), компонентов поля(component) и разделитель, выделяющий escape-последовательности
     */
    private Component<String> fieldDelimiter;
    private Component<String> repeatDelimiter;
    private Component<String> componentDelimiter;
    private Component<String> escapeDelimiter;

	/*
     * Для первого переданного пациента необходимо ввести 1, для второго - 2, ... до последнего.
     */
	private Component<Integer> sequenceNumber;


	protected Record(int lengthArrayFields, RecordType recordTypeId, String fieldDelimiter, String repeatDelimiter, String componentDelimiter, String escapeDelimiter){
		this("", lengthArrayFields, recordTypeId, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
	}


    protected Record(String recordStr, int lengthArrayFields, RecordType recordTypeId, String fieldDelimiter, String repeatDelimiter, String componentDelimiter, String escapeDelimiter){
        this.recordTypeId = recordTypeId;

        this.fieldDelimiter = new Component<>(String.class, fieldDelimiter);
        this.repeatDelimiter = new Component<>(String.class, repeatDelimiter);
        this.componentDelimiter = new Component<>(String.class, componentDelimiter);
        this.escapeDelimiter = new Component<>(String.class, escapeDelimiter);

        //array initialization with one value
		//инициализация массива одним значением
		this.fields = new String[lengthArrayFields];
		Arrays.fill(this.fields, "");

        //parsing string into array above
		//парсинг строки в массив выше
        String[] fieldsRec = recordStr.split("\\"+this.fieldDelimiter.getValue());
		System.arraycopy(fieldsRec, 0, this.fields, 0, fieldsRec.length);
        
        this.fields[0] = recordTypeId.getRecordTypeId();

    }

    public String getRecord() {
		return Arrays.asList(this.fields).stream().filter(Objects::nonNull).collect(Collectors.joining(this.fieldDelimiter.getValue())) + "\r";
    }

    public RecordType getRecordType() {
        return recordTypeId;
    }

    public Component<String> getFieldDelimiter() {
        return fieldDelimiter;
    }

    public Component<String> getRepeatDelimiter() {
        return repeatDelimiter;
    }

    public Component<String> getComponentDelimiter() {
        return componentDelimiter;
    }

    public Component<String> getEscapeDelimiter() {
        return escapeDelimiter;
    }

	protected void setField(String value, int index){
		this.fields[index] = value;
	}

    public String getField(int index){
        String field;
        try{
            field = this.fields[index];
        } catch(ArrayIndexOutOfBoundsException ex) {
            field = "";
        }
        return field;
    }

	public void setSequenceNumber(String sequenceNumber){
        fields[1] = sequenceNumber;
		this.sequenceNumber = new Component<>(Integer.class, sequenceNumber);
	}

	public int getSequenceNumber(){
		return this.sequenceNumber.getValue();
	}

	@Override
	public String toString() {
		return this.getRecord();
	}

}