package com.github.chistousov.lib.astm1394.record.header;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * The enum store-handling identifier indicates how this message should be processed.
 * (Перечисление хранит - идентификатор обработки указывает, как это сообщение должно быть обработано.)
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public enum ProcessingId {
    U("U", "Undefined", "неопределенный"),
    P("P", "Production", "Treat message as active message to be completed according to standard processing"),
    T("T", "Training", "Message is initiated by a trainer and should not have an effect on the system"),
    D("D", "Debugging", "Message is initiated for the purpose of a debugging program"),
    Q("Q", "Quality Control", "Message is initiated for the purpose of transmitting quality control/quality assurance or regulatory data");

    private String id;
    private String fullName;
    private String description;

    ProcessingId(String id, String fullName, String description){
        this.id = id;
        this.fullName = fullName;
        this.description = description;
    }

    public String getId() {
        return id;
    }
    
    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return fullName + "(" + id + ")";
    }

	public String getIdForComponent(){
		if(this.id.equals("U")){
			return "";
		}
		return getId();
	}

    private static final Map<String, ProcessingId> processingIds = new HashMap<>();

	static {
        processingIds.put(U.getId(), U);
		processingIds.put(P.getId(), P);
        processingIds.put(T.getId(), T);
        processingIds.put(D.getId(), D);
        processingIds.put(Q.getId(), Q);
	}

	public static ProcessingId getBy(String id) {
        ProcessingId processingId = processingIds.get(id);
        if(processingId == null){
            processingId = U;
        }
        return processingId;
	}
}
