package com.github.chistousov.lib.astm1394.record.request;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * status codes
 * Перечисление хранит - коды статусов
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public enum RequestInformationStatusCodes {
	UNDEFINED("U", "Undefined", "неопределенный"),
	C("C", "correction of previously transmitted results", "исправление ранее переданных результатов"),
	P("P", "preliminary results", "предварительные результаты"),
	F("F", "final results", "окончательные результаты"),
	X("X", "results cannot be done, request cancelled", "results cannot be done, request cancelled"),
	I("I", "request results pending", "запрос ожидает результатов"),
	S("S", "request partial/unfinalized results", "запросить частичные/незавершенные результаты"),
	M("M", "result is an MIC level", "результат - уровень MIC"),
	R("R", "this result was previously transmitted", "этот результат был передан ранее"),
	A("A", "abort/cancel last request criteria (allows a new request to follow)", "критерий прерывания / отмены последнего запроса (позволяет следовать новому запросу)"),
	N("N", "requesting new or edited results only", "запрос только новых или отредактированных результатов"),
	O("O", "requesting test orders and demographics only (no results)", "запрос только тестовых заказов и демографических данных (без результатов)"),
	D("D", "requesting demographics only (e.g., patient record)", "requesting demographics only (e.g., patient record)");


	private String id;
    private String fullName;
    private String fullNameRus;

    RequestInformationStatusCodes(String id, String fullName, String fullNameRus){
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

    private static final Map<String, RequestInformationStatusCodes> codes = new HashMap<>();

	static {
		codes.put(UNDEFINED.getId(), UNDEFINED);
		codes.put(C.getId(), C);
		codes.put(P.getId(), P);
		codes.put(F.getId(), F);
		codes.put(X.getId(), X);
		codes.put(S.getId(), S);
		codes.put(M.getId(), M);
		codes.put(R.getId(), R);
		codes.put(A.getId(), A);
		codes.put(N.getId(), N);
		codes.put(O.getId(), O);
		codes.put(D.getId(), D);

	}

	public static RequestInformationStatusCodes getBy(String id) {
        RequestInformationStatusCodes code = codes.get(id);
        if(code == null){
            code = UNDEFINED;
        }
        return code;
	}
}
