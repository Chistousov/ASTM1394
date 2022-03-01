package com.github.chistousov.lib.astm1394.record.patient;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Isolation codes indicate precautions that must be taken to protect the patient or staff from infection. Below are the recommended codes for general precautions. You can specify multiple precautions separated by repeating delimiters. Full text warnings can also be sent.
 * (Перечисление хранит - Коды изоляции указывают на меры предосторожности, которые необходимо соблюдать для защиты пациента или персонала от инфекции. Ниже приведены рекомендуемые коды для общих мер предосторожности. Можно указать несколько мер предосторожности, разделенных повторяющимися разделителями. Также могут быть отправлены полные текстовые предупреждения.)
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public enum IsolationStatus {
    U("U", "Undefined", "неопределенный"),
    ARP("ARP", "antibiotic resistance precautions", "меры предосторожности к антибиотикам"),
    BP("BP", "blood and needle precautions", "меры предосторожности с кровью и иглами"),
    ENP("ENP", "enteric precautions", "меры предосторожности к кишечному тракту"),
    NP("NP", "precautions for neutropenic patient", "меры предосторожности для пациентов с нейтропенией"),
    PWP("PWP", "precautions for pregnant women", "меры предосторожности для беременных"),
    RI("RI", "respiratory isolation", "респираторная изоляция"),
    SE("SE", "secretion/excretion precautions", "меры предосторожности при секреции/экскреции"),
    SI("SI", "strict isolation", "строгая изоляция"),
    WSP("WSP", "wound and skin precautions", "меры предосторожности для ран и кожи");


    private String id;
    private String fullName;
    private String fullNameRus;

    IsolationStatus(String id, String fullName, String fullNameRus){
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

    private static final Map<String, IsolationStatus> isolationStatuses = new HashMap<>();

	static {
        isolationStatuses.put(U.getId(), U);
        isolationStatuses.put(ARP.getId(), ARP);
        isolationStatuses.put(BP.getId(), BP);
        isolationStatuses.put(ENP.getId(), ENP);
        isolationStatuses.put(NP.getId(), NP);
        isolationStatuses.put(PWP.getId(), PWP);
        isolationStatuses.put(RI.getId(), RI);
        isolationStatuses.put(SE.getId(), SE);
        isolationStatuses.put(SI.getId(), SI);
        isolationStatuses.put(WSP.getId(), WSP);
	}

	public static IsolationStatus getBy(String id) {
        IsolationStatus isolationStatus = isolationStatuses.get(id);
        if(isolationStatus == null){
            isolationStatus = U;
        }
        return isolationStatus;
	}
    
}
