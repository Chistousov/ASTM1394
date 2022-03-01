package com.github.chistousov.lib.astm1394.record.patient;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Religion
 * Перечисление хранит - религию
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public enum PatientReligion {
    U("U", "Undefined", "неопределенный"),
    P("P", "Protestant", "протестант"),
    C("C", "Catholic", "католик"),
    M("M", "Church of the Latter Day Saints (Mormon)", "церковь Святых последних дней"),
    J("J", "Jewish", "иудей"),
    L("L", "Lutheran", "лютеранин"),
    H("H", "Hindu", "индуист");
    

    private String id;
    private String fullName;
    private String fullNameRus;

    PatientReligion(String id, String fullName, String fullNameRus){
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

    private static final Map<String, PatientReligion> religions = new HashMap<>();

	static {
        religions.put(U.getId(), U);
        religions.put(P.getId(), P); 
        religions.put(C.getId(), C);
        religions.put(M.getId(), M);
        religions.put(J.getId(), J);
        religions.put(L.getId(), L); 
        religions.put(H.getId(), H);       
	}

	public static PatientReligion getBy(String id) {
	 	PatientReligion patientReligion = religions.get(id);
        if(patientReligion == null){
            patientReligion = U;
        }
        return patientReligion;
	}

}