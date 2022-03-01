package com.github.chistousov.lib.astm1394.centaurxp;

import com.github.chistousov.lib.astm1394.record.comment.CommentRecord;
import com.github.chistousov.lib.astm1394.record.request.RequestInformationRecord;

public class RequestInformationCentaurXPRecord extends RequestInformationRecord<CommentRecord> {

    public RequestInformationCentaurXPRecord(String fieldDelimiter, String repeatDelimiter, String componentDelimiter,
            String escapeDelimiter) {
        super(fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
    }

    public RequestInformationCentaurXPRecord(String recordString, String fieldDelimiter, String repeatDelimiter,
            String componentDelimiter, String escapeDelimiter) throws IllegalArgumentException {
        super(recordString, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
    }
    
}
