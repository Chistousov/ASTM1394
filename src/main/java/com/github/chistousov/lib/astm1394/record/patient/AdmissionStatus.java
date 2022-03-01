package com.github.chistousov.lib.astm1394.record.patient;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This value must be represented by the following minimum list or extensions agreed between sender and recipient: OP (Outpatient), PA (Advanced), IP (Inpatient), ER (Emergency Department).
 * (Перечисление хранит - Это значение должно быть представлено следующим минимальным списком или расширениями, согласованными между отправителем и получателем: OP (амбулаторный), PA (предварительный прием), IP (стационарный), ER (отделение неотложной помощи).)
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public enum AdmissionStatus {
    U("U", "Undefined", "неопределенный"),
    OP("OP", "outpatient", "амбулаторный"),
    PA("PA", "preadmit", "предварительно принятый"),
    IP("IP", "inpatient", "стационарный"),
    ER("ER", "emergency room", "приемный покой");

    private String id;
    private String fullName;
    private String fullNameRus;

    AdmissionStatus(String id, String fullName, String fullNameRus){
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


    private static final Map<String, AdmissionStatus> admissionStatuses = new HashMap<>();

	static {
        admissionStatuses.put(U.getId(), U);
        admissionStatuses.put(OP.getId(), OP);
        admissionStatuses.put(PA.getId(), PA);
        admissionStatuses.put(IP.getId(), IP);
        admissionStatuses.put(ER.getId(), ER);
	}

	public static AdmissionStatus getBy(String id) {
        AdmissionStatus admissionStatus = admissionStatuses.get(id);
        if(admissionStatus == null){
            admissionStatus = U;
        }
        return admissionStatus;
	}

}
