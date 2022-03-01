package com.github.chistousov.lib.astm1394.record.order;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Enum stores - write priority
 * (Перечисление хранит - приоритет записи)
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public enum Priority {
	U("U", "Undefined", "неопределенный"),
	S("S", "stat", "статистика"),
	A("A", "as soon as possible", "как можно быстрее"),
	R("R", "routine", "обычный"),
	C("C", "callback", "с обратным вызовом"),
	P("P", "preoperative", "предоперационный");

    
	private String id;
    private String fullName;
    private String fullNameRus;

    Priority(String id, String fullName, String fullNameRus){
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

    private static final Map<String, Priority> priorities = new HashMap<>();

	static {	
		priorities.put(U.getId(), U);
		priorities.put(S.getId(), S);
		priorities.put(A.getId(), A);
		priorities.put(R.getId(), R);
		priorities.put(C.getId(), C);
		priorities.put(P.getId(), P);
	}

	public static Priority getBy(String id) {
        Priority priority = priorities.get(id);
        if(priority == null){
            priority = U;
        }
        return priority;
	}
}
