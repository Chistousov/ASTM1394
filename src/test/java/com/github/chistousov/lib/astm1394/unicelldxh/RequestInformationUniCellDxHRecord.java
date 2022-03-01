package com.github.chistousov.lib.astm1394.unicelldxh;

import com.github.chistousov.lib.astm1394.record.comment.CommentRecord;
import com.github.chistousov.lib.astm1394.record.request.RequestInformationRecord;

public class RequestInformationUniCellDxHRecord extends RequestInformationRecord<CommentRecord> {

    public RequestInformationUniCellDxHRecord(String fieldDelimiter, String repeatDelimiter, String componentDelimiter,
            String escapeDelimiter) {
        super(fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
    }

    public RequestInformationUniCellDxHRecord(String recordString, String fieldDelimiter, String repeatDelimiter,
            String componentDelimiter, String escapeDelimiter) throws IllegalArgumentException {
        super(recordString, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
    }
    
}
