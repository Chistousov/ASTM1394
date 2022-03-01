package com.github.chistousov.lib.astm1394.record.patient;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * If necessary, this value indicates the patient's marital status.
 * Перечисление хранит - При необходимости в этом значении указывается семейное положение пациента.
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public enum MaritalStatus {
    U("U", "Undefined", "неопределенный"),
    M("M", "married", "женатый/ая"),
    S("S", "single", "холостой/незамужняя"),
    D("D", "divorced", "в разводе"),
    W("W", "widowed", "вдовец/вдова"),
    A("A", "separated", "временно не вместе");

    private String id;
    private String fullName;
    private String fullNameRus;

    MaritalStatus(String id, String fullName, String fullNameRus){
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

    private static final Map<String, MaritalStatus> maritalStatuses = new HashMap<>();

	static {
        maritalStatuses.put(U.getId(), U);
        maritalStatuses.put(M.getId(), M);
        maritalStatuses.put(S.getId(), S);
        maritalStatuses.put(D.getId(), D);
        maritalStatuses.put(W.getId(), W);
        maritalStatuses.put(A.getId(), A);
	}

	public static MaritalStatus getBy(String id) {
        MaritalStatus maritalStatus = maritalStatuses.get(id);
        if(maritalStatus == null){
            maritalStatus = U;
        }
        return maritalStatus;
	}

}
