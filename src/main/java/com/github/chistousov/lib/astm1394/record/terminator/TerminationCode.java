package com.github.chistousov.lib.astm1394.record.terminator;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This field provides an explanation for ending a session.
 * Перечисление хранит - В этом поле приводится объяснение окончания сеанса.
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public enum TerminationCode {
	UNDEFINED("U", "Undefined", "неопределенный"),
	N("N", "normal termination", "нормальное прекращение"),
	NIL("Nil", "normal termination", "нормальное прекращение"),
	T("Nil", "sender aborted", "отправитель прерван"),
	R("R", "receiver requested abort", "получатель запросил прерывание"),
	E("E", "unknown system error", "неизвестная системная ошибка"),
	Q("Q", "error in last request for information", "ошибка в последнем запросе информации"),
	I("I", "no information available from last query", "нет информации по последнему запросу"),
	F("F", "last request for information processed", "последний запрос информации обработан");

	private String id;
    private String fullName;
    private String fullNameRus;

    TerminationCode(String id, String fullName, String fullNameRus){
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

    private static final Map<String, TerminationCode> codes = new HashMap<>();

	static {
		codes.put(UNDEFINED.getId(), UNDEFINED);
		codes.put(N.getId(), N);
		codes.put(NIL.getId(), NIL);
		codes.put(T.getId(), T);
		codes.put(R.getId(), R);
		codes.put(E.getId(), E);
		codes.put(Q.getId(), Q);
		codes.put(I.getId(), I);
		codes.put(F.getId(), F);
	}

	public static TerminationCode getBy(String id) {
        TerminationCode code = codes.get(id);
        if(code == null){
            code = UNDEFINED;
        }
        return code;
	}


}
