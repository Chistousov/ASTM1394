package com.github.chistousov.lib.astm1394.record.order;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Enum  - This field should indicate the actions to be taken on samples that accompany or precede this request.
 * (Перечисление хранит - В этом поле должны быть указаны действия, которые необходимо предпринять в отношении образцов, которые сопровождают этот запрос или предшествуют ему.)
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public enum ActionCode {
	U("U", "Undefined", "неопределенный"),
	C("C","cancel request for the battery or tests named", "отменить запрос батареи или названных тестов"),
	A("A", "add the requested tests or batteries to the existing specimen with the patient and specimen identifiers and date/time given in this record", "добавить требуемые тесты или батареи к существующему образцу с указанием идентификаторов пациента и образца и даты / времени, указанных в этой записи"),
	N("N","new requests accompanying a new specimen", "новые запросы, сопровождающие новый образец"),
	P("P","pending specimen", "ожидающий образец"),
	L("L","reserved", "зарезервированный"),
	X("X", "specimen or test already in process", "образец или тест уже в процессе"),
	Q("Q", "treat specimen as a Q/C test specimen", "рассматривать образец как образец для испытания Q/C");

    
	private String id;
    private String fullName;
    private String fullNameRus;

    ActionCode(String id, String fullName, String fullNameRus){
        this.id = id;
        this.fullName = fullName;
        this.fullNameRus = fullNameRus;
    }
    
    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
    
    public String getFullNameRus() {
        return fullNameRus;
    }

    @Override
    public String toString() {
        return fullNameRus + "(" + id + ")";
    }

	public String getIdForComponent(){
		if(this.id.equals("U")){
			return "";
		}
		return getId();
	}

    private static final Map<String, ActionCode> actionCodes = new HashMap<>();

	static {	
		actionCodes.put(U.getId(), U);
		actionCodes.put(C.getId(), C);
		actionCodes.put(A.getId(), A);
		actionCodes.put(N.getId(), N);
		actionCodes.put(P.getId(), P);
		actionCodes.put(L.getId(), L);
		actionCodes.put(X.getId(), X);
		actionCodes.put(Q.getId(), Q);
	}

	public static ActionCode getBy(String id) {
        ActionCode actionCode = actionCodes.get(id);
        if(actionCode == null){
            actionCode = U;
        }
        return actionCode;
	}
}
