package com.github.chistousov.lib.astm1394.record;

/**
 * <p>
 * The class stores the identification of a specific test for records of type O and R (Класс хранит идентификацию определенного теста для записей типа O и R)
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public class UniversalTestIDField {

	/*
	 * Это первый компонент поля идентификатора теста. 
	 * Это поле в настоящее время не используется, но зарезервировано для применения универсального кода идентификатора теста (коды LOINC), 
	 * если одна система станет доступной для использования в будущем.
	 */
	private Component<String> universalTestID;
	
	/*
	 * Это будет имя теста или батареи, связанное с универсальным идентификационным кодом теста, описанным в разделе 5.6.1.1.
	 */
	private Component<String> universalTestIdName;

	/*
	 * В случае, когда существует несколько национальных или международных схем кодирования, 
	 * это поле может использоваться для определения того, какая схема кодирования используется 
	 * в полях идентификатора теста и имени идентификатора теста.
	 */
	private Component<String> universalTestIdType;

	/*
	 * Это код, определенный производителем. Этот код может быть числом, символами или множественным обозначением теста 
	 * на основе разделителей, определенных изготовителем (то есть AK.23.34-B). За расширениями или квалификаторами этого 
	 * кода могут следовать последующие поля компонентов, которые должны быть определены и задокументированы производителем. 
	 * Например, этот код может представлять собой трехкомпонентный идентификатор, такой как: Dilution ˆ Diluent ˆ Description.
	 */
	private Component<String> manufacturersOrLocalCode;

	/**
     * The class stores the identification of a specific test for records of type O and R (Класс хранит идентификацию определенного теста для записей типа O и R)
     * 
     * @author Nikita Chistousov (chistousov.nik@yandex.ru)
     * @since 8
     * 
     * @param universalTestID This is the first component of the Test ID field. This field is not currently used, but is reserved for the use of the Universal Test Identifier Code (LOINC codes) if one system becomes available for use in the future.(Это первый компонент поля идентификатора теста.  Это поле в настоящее время не используется, но зарезервировано для применения универсального кода идентификатора теста (коды LOINC), если одна система станет доступной для использования в будущем.)
	 * @param universalTestIdName This will be the test or battery name associated with the test's Uniform Identifier, described in Section 5.6.1.1.(Это будет имя теста или батареи, связанное с универсальным идентификационным кодом теста, описанным в разделе 5.6.1.1.)
	 * @param universalTestIdType In the case where there are multiple national or international encoding schemes, this field can be used to determine which encoding scheme is used in the Test ID and Test ID Name fields. (В случае, когда существует несколько национальных или международных схем кодирования, это поле может использоваться для определения того, какая схема кодирования используется в полях идентификатора теста и имени идентификатора теста.)
	 * @param manufacturersOrLocalCode This is the manufacturer's code. This code can be a number, symbols, or a multiple test designation based on manufacturer-defined delimiters (ie, AK.23.34-B). Extensions or qualifiers to this code may be followed by subsequent component fields, which must be defined and documented by the manufacturer. For example, this code could be a three-part identifier such as: Dilution ˆ Diluent ˆ Description.(Это код, определенный производителем. Этот код может быть числом, символами или множественным обозначением теста 
	 * на основе разделителей, определенных изготовителем (то есть AK.23.34-B). За расширениями или квалификаторами этого 
	 * кода могут следовать последующие поля компонентов, которые должны быть определены и задокументированы производителем. 
	 * Например, этот код может представлять собой трехкомпонентный идентификатор, такой как: Dilution ˆ Diluent ˆ Description.)
     */
	public UniversalTestIDField(String universalTestID, String universalTestIdName, String universalTestIdType, String manufacturersOrLocalCode){
		
		this.universalTestID = new Component<>(String.class, universalTestID);
		this.universalTestIdName = new Component<>(String.class, universalTestIdName);
		this.universalTestIdType = new Component<>(String.class, universalTestIdType);
		this.manufacturersOrLocalCode =  new Component<>(String.class, manufacturersOrLocalCode);
	}

	public String toString(String componentDelimiter){
		return universalTestID
			+ componentDelimiter
			+ universalTestIdName
			+ componentDelimiter
			+ universalTestIdType
			+ componentDelimiter
			+ manufacturersOrLocalCode;
	}

}
