package com.github.chistousov.lib.astm1394.record;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This enum describes the type of record (Record) in the message (Это перечисление, описывает тип записи (Record) в сообщении)
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */

public enum RecordType {
    U("U", "Undefined", "неопределенный"),
    H("H", "Message Header Record", "Запись заголовка сообщения"),
    P("P", "Patient Information Record", "Запись информации о пациенте"),
    O("O", "Test Order Record", "Запись задания на тест"),
    R("R", "Result Record", "Запись результата теста"),
    C("C", "Comment Record", "Запись-комментарий"),
    Q("Q", "Request Information Record", "Запись с запрашиваемой информацией"),
    L("L", "Message Terminator Record", "Запись конца сообщения"),
    S("S", "Scientific Record", "Научный отчет"),
    M("M", "Manufacturer Information Record", "Прибор");

    private String recordTypeId;
    private String fullName;
    private String fullNameRus;

    RecordType(String recordTypeId, String fullName, String fullNameRus){
        this.recordTypeId = recordTypeId;
        this.fullName = fullName;
        this.fullNameRus = fullNameRus;
    }
    
    @Override
    public String toString() {
        return fullNameRus + "(" + recordTypeId + ")";
    }

    public String getRecordTypeId() {
        return recordTypeId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getFullNameRus() {
        return fullNameRus;
    }

    private static final Map<String, RecordType> types = new HashMap<>();

	static {
        types.put(U.getRecordTypeId(), U);
		types.put(H.getRecordTypeId(), H);
        types.put(P.getRecordTypeId(), P);
        types.put(O.getRecordTypeId(), O);
        types.put(R.getRecordTypeId(), R);
        types.put(C.getRecordTypeId(), C);
        types.put(Q.getRecordTypeId(), Q);
        types.put(L.getRecordTypeId(), L);
        types.put(S.getRecordTypeId(), S);
        types.put(M.getRecordTypeId(), M);
	}

	public static RecordType getBy(String recordTyoeId) {
		RecordType recordType = types.get(recordTyoeId);
        if(recordType == null){
            recordType = U;
        }
        return recordType;
	}
}
