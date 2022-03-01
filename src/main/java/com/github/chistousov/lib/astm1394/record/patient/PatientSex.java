package com.github.chistousov.lib.astm1394.record.patient;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Sex.
 * Перечисление хранит - пол
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public enum PatientSex {
    M("M", "Male", "Мужчина"),
    F("F", "Female", "Женщина"),
    U("U", "Undefined", "Неопределенный");

    private String id;
    private String fullName;
    private String fullNameRus;

    PatientSex(String id, String fullName, String fullNameRus){
        this.id = id;
        this.fullName = fullName;
        this.fullNameRus = fullNameRus;
    }

    @Override
    public String toString() {
        return fullNameRus + "(" + id + ")";
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

	public String getIdForComponent(){
		if(this.id.equals("U")){
			return "";
		}
		return getId();
	}

    private static final Map<String, PatientSex> sexs = new HashMap<>();

	static {
        sexs.put(M.getId(), M);
        sexs.put(F.getId(), F);
        sexs.put(U.getId(), U);        
	}

	public static PatientSex getBy(String id) {
		PatientSex patientSex = sexs.get(id);
        if(patientSex == null){
            patientSex = U;
        }
        return patientSex;
    }

}
