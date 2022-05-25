package com.github.chistousov.lib.astm1394;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.github.chistousov.lib.astm1394.centaurxp.IWithCommentsCentaurXP;
import com.github.chistousov.lib.astm1394.centaurxp.PatientInformationCentaurXPRecord;
import com.github.chistousov.lib.astm1394.centaurxp.RequestInformationCentaurXPRecord;
import com.github.chistousov.lib.astm1394.centaurxp.ResultCentaurXPRecord;
import com.github.chistousov.lib.astm1394.centaurxp.TestOrderCentaurXPRecord;
import com.github.chistousov.lib.astm1394.record.ScientificRecord;
import com.github.chistousov.lib.astm1394.record.comment.CommentRecord;
import com.github.chistousov.lib.astm1394.record.header.MessageHeaderRecord;
import com.github.chistousov.lib.astm1394.record.terminator.MessageTerminatorRecord;
import com.github.chistousov.lib.astm1394.unicelldxh.IWithCommentsUniCellDxH;
import com.github.chistousov.lib.astm1394.unicelldxh.PatientInformationUniCellDxHRecord;
import com.github.chistousov.lib.astm1394.unicelldxh.RequestInformationUniCellDxHRecord;
import com.github.chistousov.lib.astm1394.unicelldxh.ResultUniCellDxHrecord;
import com.github.chistousov.lib.astm1394.unicelldxh.TestOrderUniCellDxHRecord;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;



class MessageTest {

	private static Stream<String> requestCentaurXPProvider() throws Exception{	
		return Stream.of(Files.walk(
								Paths.get("src", "test", "resources", "CentaurXP", "requests")
							)
							.filter(Files::isRegularFile)
					.map(file -> {
							try {
								return new String(Files.readAllBytes(file), "UTF-8");
							} catch (IOException e) {
								return file.toString();
							}
						})
					.toArray(String[]::new));
	}

	private static Stream<String> ordersCentaurXPProvider() throws Exception{	
		return Stream.of(Files.walk(
								Paths.get("src", "test", "resources", "CentaurXP", "orders")
							)
							.filter(Files::isRegularFile)
					.map(file -> {
							try {
								return new String(Files.readAllBytes(file), "UTF-8");
							} catch (IOException e) {
								return file.toString();
							}
						})
					.toArray(String[]::new));
	}

	private static Stream<String> resultsCentaurXPProvider() throws Exception{	
		return Stream.of(Files.walk(
								Paths.get("src", "test", "resources", "CentaurXP", "results")
							)
							.filter(Files::isRegularFile)
					.map(file -> {
							try {
								return new String(Files.readAllBytes(file), "UTF-8");
							} catch (IOException e) {
								return file.toString();
							}
						})
					.toArray(String[]::new));
	}



	@ParameterizedTest
	@DisplayName("Пропускаем через библиотеку тестовые запросы(request) CentaurXP")
	@MethodSource("requestCentaurXPProvider")
	void checkResultsCentaurXP(String expected) throws InvocationTargetException, IllegalAccessException, InstantiationException{
		
		//given

		//when
		Message<MessageHeaderRecord, 
		PatientInformationCentaurXPRecord, 
		TestOrderCentaurXPRecord, 
		ResultCentaurXPRecord, 
		RequestInformationCentaurXPRecord,  
		ScientificRecord, 
		MessageTerminatorRecord,
		CommentRecord,
		IWithCommentsCentaurXP> messageCentaurXP = new Message<>(
			MessageHeaderRecord.class, 
			PatientInformationCentaurXPRecord.class, 
			TestOrderCentaurXPRecord.class, 
			ResultCentaurXPRecord.class, 
			RequestInformationCentaurXPRecord.class,  
			ScientificRecord.class, 
			MessageTerminatorRecord.class,
			CommentRecord.class,
			IWithCommentsCentaurXP.class,
			expected);

		String actual = String.join("\r", Arrays.asList(
			messageCentaurXP.toString()
			.split("\r|\n|\r\n"))
			.stream()
			.map(record -> record.toString()
			.replaceAll("(\\|+$)", ""))
			.toArray(String[]::new)
			)+"\r";

        //then
        assertThat(actual).isEqualTo(expected);

	}

	@ParameterizedTest
	@DisplayName("Пропускаем через библиотеку тестовые задания(order) CentaurXP")
	@MethodSource("ordersCentaurXPProvider")
	void checkOrdersCentaurXP(String expected) throws InvocationTargetException, IllegalAccessException, InstantiationException{
		
		//given

		//when
		Message<MessageHeaderRecord, 
		PatientInformationCentaurXPRecord, 
		TestOrderCentaurXPRecord, 
		ResultCentaurXPRecord, 
		RequestInformationCentaurXPRecord,  
		ScientificRecord, 
		MessageTerminatorRecord,
		CommentRecord,
		IWithCommentsCentaurXP> messageCentaurXP = new Message<>(
			MessageHeaderRecord.class, 
			PatientInformationCentaurXPRecord.class, 
			TestOrderCentaurXPRecord.class, 
			ResultCentaurXPRecord.class, 
			RequestInformationCentaurXPRecord.class,  
			ScientificRecord.class, 
			MessageTerminatorRecord.class,
			CommentRecord.class,
			IWithCommentsCentaurXP.class,
			expected);

		String actual = String.join("\r", Arrays.asList(
			messageCentaurXP.toString()
			.split("\r|\n|\r\n"))
			.stream()
			.map(record -> record.toString()
			.replaceAll("(\\|+$)", ""))
			.toArray(String[]::new)
			)+"\r";

        //then
        assertThat(actual).isEqualTo(expected);

	}

	@ParameterizedTest
	@DisplayName("Пропускаем через библиотеку тестовые результаты(result) CentaurXP")
	@MethodSource("resultsCentaurXPProvider")
	void checkResultsCentaurXPCentaurXP(String expected) throws InvocationTargetException, IllegalAccessException, InstantiationException{
		
		//given

		//when
		Message<MessageHeaderRecord, 
		PatientInformationCentaurXPRecord, 
		TestOrderCentaurXPRecord, 
		ResultCentaurXPRecord, 
		RequestInformationCentaurXPRecord,  
		ScientificRecord, 
		MessageTerminatorRecord,
		CommentRecord,
		IWithCommentsCentaurXP> messageCentaurXP = new Message<>(
			MessageHeaderRecord.class, 
			PatientInformationCentaurXPRecord.class, 
			TestOrderCentaurXPRecord.class, 
			ResultCentaurXPRecord.class, 
			RequestInformationCentaurXPRecord.class,  
			ScientificRecord.class, 
			MessageTerminatorRecord.class,
			CommentRecord.class,
			IWithCommentsCentaurXP.class,
			expected);

		String actual = String.join("\r", Arrays.asList(
			messageCentaurXP.toString()
			.split("\r|\n|\r\n"))
			.stream()
			.map(record -> record.toString()
			.replaceAll("(\\|+$)", ""))
			.toArray(String[]::new)
			)+"\r";

        //then
        assertThat(actual).isEqualTo(expected);

	}




	private static Stream<String> requestUniCellDxHProvider() throws Exception{	
		return Stream.of(Files.walk(
								Paths.get("src", "test", "resources", "UniCellDxH", "requests")
							)
							.filter(Files::isRegularFile)
					.map(file -> {
							try {
								return new String(Files.readAllBytes(file), "UTF-8");
							} catch (IOException e) {
								return file.toString();
							}
						})
					.toArray(String[]::new));
	}

	private static Stream<String> ordersUniCellDxHProvider() throws Exception{	
		return Stream.of(Files.walk(
								Paths.get("src", "test", "resources", "UniCellDxH", "orders")
							)
							.filter(Files::isRegularFile)
					.map(file -> {
							try {
								return new String(Files.readAllBytes(file), "UTF-8");
							} catch (IOException e) {
								return file.toString();
							}
						})
					.toArray(String[]::new));
	}

	private static Stream<String> resultsUniCellDxHProvider() throws Exception{	
		return Stream.of(Files.walk(
								Paths.get("src", "test", "resources", "UniCellDxH", "results")
							)
							.filter(Files::isRegularFile)
					.map(file -> {
							try {
								return new String(Files.readAllBytes(file), "UTF-8");
							} catch (IOException e) {
								return file.toString();
							}
						})
					.toArray(String[]::new));
	}



	@ParameterizedTest
	@DisplayName("Пропускаем через библиотеку тестовые запросы(request) UniCellDxH")
	@MethodSource("requestUniCellDxHProvider")
	void checkRequestUniCellDxH(String expected) throws InvocationTargetException, IllegalAccessException, InstantiationException{
		
		//given

		//when
		Message<MessageHeaderRecord, 
		PatientInformationUniCellDxHRecord, 
		TestOrderUniCellDxHRecord, 
		ResultUniCellDxHrecord, 
		RequestInformationUniCellDxHRecord,  
		ScientificRecord, 
		MessageTerminatorRecord,
		CommentRecord,
		IWithCommentsUniCellDxH> messageUniCellDxH = new Message<>(
			MessageHeaderRecord.class, 
			PatientInformationUniCellDxHRecord.class, 
			TestOrderUniCellDxHRecord.class,  
			ResultUniCellDxHrecord.class, 
			RequestInformationUniCellDxHRecord.class, 
			ScientificRecord.class, 
			MessageTerminatorRecord.class,
			CommentRecord.class,
			IWithCommentsUniCellDxH.class,
			expected);

		String actual = String.join("\r", Arrays.asList(
			messageUniCellDxH.toString()
			.split("\r|\n|\r\n"))
			.stream()
			.map(record -> record.toString()
			.replaceAll("(\\|+$)", ""))
			.toArray(String[]::new)
			)+"\r";

        //then
        assertThat(actual).isEqualTo(expected);

	}

	@ParameterizedTest
	@DisplayName("Пропускаем через библиотеку тестовые задания(order) UniCellDxH")
	@MethodSource("ordersUniCellDxHProvider")
	void checkOrdersUniCellDxH(String expected) throws InvocationTargetException, IllegalAccessException, InstantiationException{
		
		//given

		//when
		Message<MessageHeaderRecord, 
		PatientInformationUniCellDxHRecord, 
		TestOrderUniCellDxHRecord, 
		ResultUniCellDxHrecord, 
		RequestInformationUniCellDxHRecord,  
		ScientificRecord, 
		MessageTerminatorRecord,
		CommentRecord,
		IWithCommentsUniCellDxH> messageUniCellDxH = new Message<>(
			MessageHeaderRecord.class, 
			PatientInformationUniCellDxHRecord.class, 
			TestOrderUniCellDxHRecord.class,  
			ResultUniCellDxHrecord.class, 
			RequestInformationUniCellDxHRecord.class, 
			ScientificRecord.class, 
			MessageTerminatorRecord.class,
			CommentRecord.class,
			IWithCommentsUniCellDxH.class,
			expected);

		String actual = String.join("\r", Arrays.asList(
			messageUniCellDxH.toString()
			.split("\r|\n|\r\n"))
			.stream()
			.map(record -> record.toString()
			.replaceAll("(\\|+$)", ""))
			.toArray(String[]::new)
			)+"\r";

        //then
        assertThat(actual).isEqualTo(expected);

	}

	@ParameterizedTest
	@DisplayName("Пропускаем через библиотеку тестовые результаты(result) UniCellDxH")
	@MethodSource("resultsUniCellDxHProvider")
	void checkResultsUniCellDxH(String expected) throws InvocationTargetException, IllegalAccessException, InstantiationException{
		
		//given

		//when
		Message<MessageHeaderRecord, 
		PatientInformationUniCellDxHRecord, 
		TestOrderUniCellDxHRecord, 
		ResultUniCellDxHrecord, 
		RequestInformationUniCellDxHRecord,  
		ScientificRecord, 
		MessageTerminatorRecord,
		CommentRecord,
		IWithCommentsUniCellDxH> messageUniCellDxH = new Message<>(
			MessageHeaderRecord.class, 
			PatientInformationUniCellDxHRecord.class, 
			TestOrderUniCellDxHRecord.class,  
			ResultUniCellDxHrecord.class, 
			RequestInformationUniCellDxHRecord.class, 
			ScientificRecord.class, 
			MessageTerminatorRecord.class,
			CommentRecord.class,
			IWithCommentsUniCellDxH.class,
			expected);

		String actual = String.join("\r", Arrays.asList(
			messageUniCellDxH.toString()
			.split("\r|\n|\r\n"))
			.stream()
			.map(record -> record.toString()
			.replaceAll("(\\|+$)", ""))
			.toArray(String[]::new)
			)+"\r";

        //then
        assertThat(actual).isEqualTo(expected);

	}
}
