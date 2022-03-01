package com.github.chistousov.lib.astm1394.record.comment;

import java.util.HashMap;
import java.util.Map;

public enum CommentType {
	UNDEFINED("U", "Undefined", "неопределенный"),
	G("G", "generic/free result comment", "общий/свободный комментарий результата"),
	T("T", "result name comment", "комментарий названия результата"),
	P("P", "positive result comment", "комментарий положительного результата"),
	N("N", "negative result comment", "комментарий отрицательного результата"),
	I("I", "instrument flag(s) comment", "комментарий параметров инструмента");

	private String id;
    private String fullName;
    private String fullNameRus;

    CommentType(String id, String fullName, String fullNameRus){
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

    private static final Map<String, CommentType> commentTypes = new HashMap<>();

	static {
		commentTypes.put(UNDEFINED.getId(), UNDEFINED);
		commentTypes.put(G.getId(), G);
		commentTypes.put(T.getId(), T);
		commentTypes.put(P.getId(), P);
		commentTypes.put(N.getId(), N);
		commentTypes.put(I.getId(), I);
	}

	public static CommentType getBy(String id) {
        CommentType commentType = commentTypes.get(id);
        if(commentType == null){
            commentType = UNDEFINED;
        }
        return commentType;
	}
}
