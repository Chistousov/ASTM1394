package com.github.chistousov.lib.astm1394.centaurxp;

import com.github.chistousov.lib.astm1394.record.comment.CommentRecord;
import com.github.chistousov.lib.astm1394.record.result.ResultRecord;

public class ResultCentaurXPRecord extends ResultRecord<CommentRecord> {

    public ResultCentaurXPRecord(String fieldDelimiter, String repeatDelimiter, String componentDelimiter,
            String escapeDelimiter) {
        super(fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
    }

    public ResultCentaurXPRecord(String recordString, String fieldDelimiter, String repeatDelimiter,
            String componentDelimiter, String escapeDelimiter) throws IllegalArgumentException {
        super(recordString, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
    }
    
}
