package com.github.chistousov.lib.astm1394.record.result;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Result status
 * Перечисление хранит - статус результата
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public enum ResultStatus {
	UNDEFINED("U", "Undefined", "неопределенный"),
	C("C", "correction of previously transmitted results", "исправление ранее переданных результатов"),
	P("P", "preliminary results", "предварительные результаты"),
	F("F", "final results", "окончательные результаты"),
	X("X", "order cannot be done", "заказ не может быть выполнен"),
	I("I", "in instrument, results pending", "в инструменте, ожидаемые результаты"),
	S("S", "partial results", "частичные результаты"),
	M("M", "this result is an MIC level", "этот результат - уровень MIC"),
	R("R", "this result was previously transmitted", "этот результат был передан ранее"),
	N("N", "this result record contains necessary information to run a new order", "эта запись результата содержит необходимую информацию для запуска нового заказа"),
	Q("Q", "this result is a response to an outstanding query", "этот результат является ответом на невыполненный запрос"),
	V("V", "operator verified/approved result", "оператор проверил/одобрил результат"),
	W("W", "Warning: Validity is questionable", "Предупреждение: валидность сомнительна");

	private String id;
    private String fullName;
    private String fullNameRus;

    ResultStatus(String id, String fullName, String fullNameRus){
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

    private static final Map<String, ResultStatus> resultStatuses = new HashMap<>();

	static {
		resultStatuses.put(UNDEFINED.getId(), UNDEFINED);
		resultStatuses.put(C.getId(), C);
		resultStatuses.put(P.getId(), P);
		resultStatuses.put(F.getId(), F);
		resultStatuses.put(X.getId(), X);
		resultStatuses.put(I.getId(), I);
		resultStatuses.put(S.getId(), S);
		resultStatuses.put(M.getId(), M);
		resultStatuses.put(R.getId(), R);
		resultStatuses.put(N.getId(), N);
		resultStatuses.put(Q.getId(), Q);
		resultStatuses.put(V.getId(), V);
		resultStatuses.put(W.getId(), W);
	}

	public static ResultStatus getBy(String id) {
        ResultStatus status = resultStatuses.get(id);
        if(status == null){
            status = UNDEFINED;
        }
        return status;
	}
}
