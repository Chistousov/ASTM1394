package com.github.chistousov.lib.astm1394;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.github.chistousov.lib.astm1394.record.IWithComments;
import com.github.chistousov.lib.astm1394.record.Record;
import com.github.chistousov.lib.astm1394.record.RecordType;
import com.github.chistousov.lib.astm1394.record.ScientificRecord;
import com.github.chistousov.lib.astm1394.record.comment.CommentRecord;
import com.github.chistousov.lib.astm1394.record.header.MessageHeaderRecord;
import com.github.chistousov.lib.astm1394.record.order.TestOrderRecord;
import com.github.chistousov.lib.astm1394.record.patient.PatientInformationRecord;
import com.github.chistousov.lib.astm1394.record.request.RequestInformationRecord;
import com.github.chistousov.lib.astm1394.record.terminator.MessageTerminatorRecord;


/**
 * <p>
 * ASTM 1394 protocol message.(Сообщение по протоколу ASTM 1394).
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public class Message<T1 extends MessageHeaderRecord, 
					 T2 extends PatientInformationRecord<T3, T9>,
					 T3 extends TestOrderRecord<T4, T9>,
					 T4 extends Record,
					 T6 extends RequestInformationRecord<T9>,
					 T7 extends ScientificRecord,
					 T8 extends MessageTerminatorRecord,
					 T9 extends CommentRecord,
					 T10 extends IWithComments<T9>
					> {


	private Class<T1> clazzH;
	private Class<T2> clazzP;
	private Class<T3> clazzO;
	private Class<T4> clazzR;
	private Class<T6> clazzQ;
	private Class<T7> clazzS;
	private Class<T8> clazzL;
	private Class<T9> clazzC;
	private Class<T10> interfaceIWithComments;

	private T1 headerRecord;

	private List<T2> patientInformationRecords;

	private List<T6> requestInformationRecords;

	private T8 terminatorRecord;

	public Message(Class<T1> clazzT1,
				   Class<T2> clazzT2,
				   Class<T3> clazzT3,
				   Class<T4> clazzT4,
				   Class<T6> clazzT6,
				   Class<T7> clazzT7,
				   Class<T8> clazzT8,
				   Class<T9> clazzT9,
				   Class<T10> interfaceIWithComments,
				   String messageStr) throws InvocationTargetException, IllegalAccessException, InstantiationException{

		this.clazzH = clazzT1;
		this.clazzP = clazzT2;
		this.clazzO = clazzT3;
		this.clazzR = clazzT4;
		this.clazzQ = clazzT6;
		this.clazzS = clazzT7;
		this.clazzL = clazzT8;
		this.clazzC = clazzT9;
		this.interfaceIWithComments = interfaceIWithComments;

		String[] recordsStr = messageStr.replaceAll("(\r\n)", "\r") .split("(\r)");
		
		try{
			Constructor<T1> contructorT1 = this.clazzH.getDeclaredConstructor(String.class);
			this.headerRecord = contructorT1.newInstance(recordsStr[0]);
		} catch(Exception ex){
			throw new IllegalArgumentException("MessageHeaderRecord descendant has no (String) constructor");
		}

		//last record
		//последняя запись
		T10 currectRecordWithComments = null;
			
		//To link patient and orders
		//Для связи пациента и заданий
		T2 patientInformationRecord = null;

		//To link orders and results
		//Для связи задания и результатов
		T3 testOrderRecord = null;

		//constructors for creating types
		//конструкторы для создания типов
		Constructor<T2> constructorPatientInformationRecord;
		try{
			constructorPatientInformationRecord = clazzP.getDeclaredConstructor(String.class,
			String.class,
			String.class,
			String.class,
			String.class);
		} catch(Exception ex){
			throw new IllegalArgumentException("Child PatientInformationRecord has no constructor (String,String,String,String,String)");
		}

		Constructor<T3> constructorTestOrderRecord;
		try{
			constructorTestOrderRecord = clazzO.getDeclaredConstructor(String.class,
			String.class,
			String.class,
			String.class,
			String.class);
		} catch(Exception ex){
			throw new IllegalArgumentException("TestOrderRecord child has no constructor (String,String,String,String,String)");
		}

		Constructor<T4> constructorResultRecord;
		try{
			constructorResultRecord = clazzR.getDeclaredConstructor(String.class,
			String.class,
			String.class,
			String.class,
			String.class);
		} catch(Exception ex){
			throw new IllegalArgumentException("ResultRecord child has no constructor (String,String,String,String,String)");
		}



		Constructor<T6> constructorRequestInformationRecord;
		try{
			constructorRequestInformationRecord = clazzQ.getDeclaredConstructor(String.class,
			String.class,
			String.class,
			String.class,
			String.class);
		} catch(Exception ex){
			throw new IllegalArgumentException("RequestInformationRecord child has no constructor (String,String,String,String,String)");
		}

		Constructor<T8> constructorMessageTerminatorRecord;
		try{
			constructorMessageTerminatorRecord = clazzL.getDeclaredConstructor(String.class,
			String.class,
			String.class,
			String.class,
			String.class);
		} catch(Exception ex){
			throw new IllegalArgumentException("TerminatorRecord child has no constructor (String,String,String,String,String)");
		}


		Constructor<T9> constructorCommentRecord;
		try{
			constructorCommentRecord = clazzC.getDeclaredConstructor(String.class,
			String.class,
			String.class,
			String.class,
			String.class);
		} catch(Exception ex){
			throw new IllegalArgumentException("CommentRecord child has no constructor (String,String,String,String,String)");
		}
		
		for(int i = 1; i < recordsStr.length; i++){
			
			RecordType recordType = RecordType.getBy(String.valueOf(recordsStr[i].charAt(0)));

			if(recordType == RecordType.P) {
				try{
					patientInformationRecord = constructorPatientInformationRecord.newInstance(recordsStr[i], 
																			this.headerRecord.getFieldDelimiter().getValue(), 
																			this.headerRecord.getRepeatDelimiter().getValue(), 
																			this.headerRecord.getComponentDelimiter().getValue(), 
																			this.headerRecord.getEscapeDelimiter().getValue());
				} catch (InvocationTargetException itex){
					throw new InvocationTargetException(itex, "An error occurred in the PatientInformationRecord constructor");
				} catch (IllegalAccessException iaex){
					throw new IllegalAccessException("No access to PatientInformationRecord constructor");
				} catch (InstantiationException ex){
					throw new InstantiationException("Unable to create type PatientInformationRecord");
				}
				
				if(this.patientInformationRecords == null){
					this.patientInformationRecords = new ArrayList<>();
				}
				this.patientInformationRecords.add(patientInformationRecord);
				
				try{
					currectRecordWithComments = (T10)patientInformationRecord;
				} catch(Exception ex) {}

			} else if(recordType == RecordType.O) {
				try{
					testOrderRecord = constructorTestOrderRecord.newInstance(recordsStr[i], 
															this.headerRecord.getFieldDelimiter().getValue(), 
															this.headerRecord.getRepeatDelimiter().getValue(), 
															this.headerRecord.getComponentDelimiter().getValue(), 
															this.headerRecord.getEscapeDelimiter().getValue());
				} catch (InvocationTargetException itex){
					throw new InvocationTargetException(itex, "An error occurred in the TestOrderRecord constructor");
				} catch (IllegalAccessException iaex){
					throw new IllegalAccessException("No access to TestOrderRecord constructor");
				} catch (InstantiationException ex){
					throw new InstantiationException("Unable to create type TestOrderRecord");
				}
				
				patientInformationRecord.addOrder(testOrderRecord);

				try{
					currectRecordWithComments = (T10)testOrderRecord;
				} catch(Exception ex) {}

			} else if(recordType == RecordType.R) {
				T4 resultRecord;
				try{
				resultRecord = constructorResultRecord.newInstance(recordsStr[i], 
															this.headerRecord.getFieldDelimiter().getValue(), 
															this.headerRecord.getRepeatDelimiter().getValue(), 
															this.headerRecord.getComponentDelimiter().getValue(), 
															this.headerRecord.getEscapeDelimiter().getValue());
				} catch (InvocationTargetException itex){
					throw new InvocationTargetException(itex, "An error occurred in the ResultRecord constructor");
				} catch (IllegalAccessException iaex){
					throw new IllegalAccessException("No access to ResultRecord constructor");
				} catch (InstantiationException ex){
					throw new InstantiationException("Unable to create type ResultRecord");
				}
				
				testOrderRecord.addResult(resultRecord);


				try{
					currectRecordWithComments = (T10)resultRecord;
				} catch(Exception ex) {}

			} else if(recordType == RecordType.C) {
				try{
					currectRecordWithComments.addCommentRecord(
						constructorCommentRecord.newInstance(
							recordsStr[i], 
							this.headerRecord.getFieldDelimiter().getValue(), 
							this.headerRecord.getRepeatDelimiter().getValue(), 
							this.headerRecord.getComponentDelimiter().getValue(), 
							this.headerRecord.getEscapeDelimiter().getValue())
					);
				} catch (InvocationTargetException itex){
					throw new InvocationTargetException(itex, "An error occurred in the CommentRecord constructor");
				} catch (IllegalAccessException iaex){
					throw new IllegalAccessException("No access to CommentRecord constructor");
				} catch (InstantiationException ex){
					throw new InstantiationException("Unable to create type CommentRecord");
				}

			} else if(recordType == RecordType.L) {
				try{
					this.terminatorRecord = constructorMessageTerminatorRecord.newInstance(recordsStr[i], 
					this.headerRecord.getFieldDelimiter().getValue(), 
					this.headerRecord.getRepeatDelimiter().getValue(), 
					this.headerRecord.getComponentDelimiter().getValue(), 
					this.headerRecord.getEscapeDelimiter().getValue());
				} catch (InvocationTargetException itex){
					throw new InvocationTargetException(itex, "An error occurred in the MessageTerminatorRecord constructor");
				} catch (IllegalAccessException iaex){
					throw new IllegalAccessException("No access to MessageTerminatorRecord constructor");
				} catch (InstantiationException ex){
					throw new InstantiationException("Unable to create type MessageTerminatorRecord");
				}

			} else if(recordType == RecordType.Q){
				if(this.requestInformationRecords == null){
					this.requestInformationRecords = new ArrayList<>();
				}
				T6 requestInformationRecord = null;
				try{
					requestInformationRecord = constructorRequestInformationRecord.newInstance(recordsStr[i], 
					this.headerRecord.getFieldDelimiter().getValue(), 
					this.headerRecord.getRepeatDelimiter().getValue(), 
					this.headerRecord.getComponentDelimiter().getValue(), 
					this.headerRecord.getEscapeDelimiter().getValue());
				} catch (InvocationTargetException itex){
					throw new InvocationTargetException(itex, "An error occurred in the RequestInformationRecord constructor");
				} catch (IllegalAccessException iaex){
					throw new IllegalAccessException("No access to RequestInformationRecord constructor");
				} catch (InstantiationException ex){
					throw new InstantiationException("Unable to create type RequestInformationRecord");
				}

				this.requestInformationRecords.add(requestInformationRecord);

				try{
					currectRecordWithComments = (T10)requestInformationRecord;
				} catch(Exception ex) {}

			}
		}
	}


	public Message(Class<T1> clazzT1,
				   Class<T2> clazzT2,
				   Class<T3> clazzT3,
				   Class<T4> clazzT4,
				   Class<T6> clazzT6,
				   Class<T7> clazzT7,
				   Class<T8> clazzT8,
				   Class<T9> clazzT9,
				   Class<T10> interfaceIWithComments){

		this.clazzH = clazzT1;
		this.clazzP = clazzT2;
		this.clazzO = clazzT3;
		this.clazzR = clazzT4;
		this.clazzQ = clazzT6;
		this.clazzS = clazzT7;
		this.clazzL = clazzT8;
		this.clazzC = clazzT9;
		this.interfaceIWithComments = interfaceIWithComments;
	}


	@Override
	public String toString() {
		StringBuilder returnStr = new StringBuilder();
		
		returnStr.append(this.headerRecord.toString());

		if(this.requestInformationRecords != null) {
			this.requestInformationRecords.forEach(requestInformationRecord -> returnStr.append(requestInformationRecord.toString()));
		} else if(this.patientInformationRecords != null) {
			this.patientInformationRecords.forEach(patientInformationRecord -> returnStr.append(patientInformationRecord.toString()));
		}

		returnStr.append(this.terminatorRecord.toString());

		return returnStr.toString();
	}

	public T1 getHeaderRecord() {
		return headerRecord;
	}

	public void setHeaderRecord(T1 headerRecord) {
		this.headerRecord = headerRecord;
	}


	public List<T6> getRequestInformationRecords() {
		return requestInformationRecords;
	}

	public List<T2> getPatientInformationRecords() {
		return patientInformationRecords;
	}

	public void addPatientInformationRecord(T2 patientInformationRecord){
		if(this.requestInformationRecords != null){
			throw new IllegalArgumentException("The message already contains a record of type Q - the message cannot store records of type Q and P at the same time");
		}
		if(this.patientInformationRecords == null){
			this.patientInformationRecords = new ArrayList<>();
		}
		patientInformationRecord.setSequenceNumber(String.valueOf(this.patientInformationRecords.size() + 1));
		patientInformationRecords.add(patientInformationRecord);
	}

	public void setPatientInformationRecords(List<T2> patientInformationRecords) {
		patientInformationRecords.forEach(this::addPatientInformationRecord);
	}


	public void addRequestInformationRecord(T6 requestInformationRecord){
		if(this.patientInformationRecords != null){
			throw new IllegalArgumentException("The message already contains a record of type P - the message cannot store records of type Q and P at the same time");
		}
		if(this.requestInformationRecords == null){
			this.requestInformationRecords = new ArrayList<>();
		}
		requestInformationRecord.setSequenceNumber(String.valueOf(this.requestInformationRecords.size() + 1));
		requestInformationRecords.add(requestInformationRecord);
	}


	public void setRequestInformationRecords(List<T6> requestInformationRecords) {
		requestInformationRecords.forEach(this::addRequestInformationRecord);
	}

	public void clearPatientsOrRequest() {
		this.patientInformationRecords = null;
		this.requestInformationRecords = null;
	}


	public T8 getTerminatorRecord() {
		return terminatorRecord;
	}

	public void setTerminatorRecord(T8 terminatorRecord) {
		this.terminatorRecord = terminatorRecord;
	}
}
