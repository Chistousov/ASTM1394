package com.github.chistousov.lib.astm1394.record.comment;

import java.util.HashMap;
import java.util.Map;

public enum CommentSource {
	UNDEFINED("U", "Undefined", "неопределенный"),
	P("P", "practice", "практический комментарий"),
	L("L", "information system", "информационная система"),
	I("I", "clinical instrument system", "система клинических инструментов");

	private String id;
    private String fullName;
    private String fullNameRus;

    CommentSource(String id, String fullName, String fullNameRus){
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

    private static final Map<String, CommentSource> commentSources = new HashMap<>();

	static {
		commentSources.put(UNDEFINED.getId(), UNDEFINED);
		commentSources.put(P.getId(), P);
		commentSources.put(L.getId(), L);
		commentSources.put(I.getId(), I);	
	}

	public static CommentSource getBy(String id) {
        CommentSource commentSource = commentSources.get(id);
        if(commentSource == null){
            commentSource = UNDEFINED;
        }
        return commentSource;
	}
}
