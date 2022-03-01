package com.github.chistousov.lib.astm1394.record.patient;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Race
 * Перечисление хранит - расу
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public enum PatientRaceEthnicOrigin {
    U("U", "Undefined", "неопределенный"),
    W("W", "white", "белый"),
    B("B", "black", "черный"),
    O("O", "Asian/Pacific Islander", "Житель азиатских/тихоокеанских островов"),
    NA("NA", "Native American/Alaskan Native", "Коренные американцы/коренные жители Аляски"),
    H("H", "Hispanic", "Латиноамериканец");

    private String id;
    private String fullName;
    private String fullNameRus;

    PatientRaceEthnicOrigin(String id, String fullName, String fullNameRus){
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

    private static final Map<String, PatientRaceEthnicOrigin> races = new HashMap<>();

	static {
        races.put(U.getId(), U);
        races.put(W.getId(), W);
        races.put(B.getId(), B);
        races.put(O.getId(), O);
        races.put(NA.getId(), NA);
        races.put(H.getId(), H);
	}

	public static PatientRaceEthnicOrigin getBy(String id) {
        PatientRaceEthnicOrigin race = races.get(id);
        if(race == null){
            race = U;
        }
        return race;
	}

}