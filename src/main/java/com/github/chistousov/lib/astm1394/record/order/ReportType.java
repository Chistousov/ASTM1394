package com.github.chistousov.lib.astm1394.record.order;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Enum stores - report type
 * (Перечисление хранит - тип отчета)
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public enum ReportType {
	U("U", "Undefined", "неопределенный"),
	O("O", "order record; user asking that analysis be performed", "запись заказа; пользователь просит провести анализ"),
	C("C", "correction of previously transmitted results", "исправление ранее переданных результатов"),
	P("P", "preliminary results", "предварительные результаты"),
	F("F", "final results", "окончательные результаты"),
	X("X", "order cannot be done, order cancelled", "заказ не может быть выполнен, заказ отменен"),
	I("I", "in instrument pending", "в инструменте на рассмотрении"),
	Y("Y", "no order on record for this test (in response to query)", "нет заказа в записи для этого теста (в ответ на запрос)"),
	Z("Z", "no record of this patient (in response to query)", "нет записи об этом пациенте (в ответ на запрос)"),
	Q("Q", "response to query (this record is a response to a request-information query)", "ответ на запрос (эта запись является ответом на запрос информации)");

	private String id;
    private String fullName;
    private String fullNameRus;

    ReportType(String id, String fullName, String fullNameRus){
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

    private static final Map<String, ReportType> types = new HashMap<>();

	static {	
		types.put(U.getId(), U);
		types.put(O.getId(), O);
		types.put(C.getId(), C);
		types.put(P.getId(), P);
		types.put(F.getId(), F);
		types.put(X.getId(), X);
		types.put(I.getId(), I);
		types.put(Y.getId(), Y);
		types.put(Z.getId(), Z);
		types.put(Q.getId(), Q);
	}

	public static ReportType getBy(String id) {
        ReportType type = types.get(id);
        if(type == null){
            type = U;
        }
        return type;
	}
}
