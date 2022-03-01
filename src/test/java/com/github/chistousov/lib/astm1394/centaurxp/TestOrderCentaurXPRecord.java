package com.github.chistousov.lib.astm1394.centaurxp;

import com.github.chistousov.lib.astm1394.record.comment.CommentRecord;
import com.github.chistousov.lib.astm1394.record.order.TestOrderRecord;

public class TestOrderCentaurXPRecord extends TestOrderRecord<ResultCentaurXPRecord, CommentRecord>  {

    public TestOrderCentaurXPRecord(String fieldDelimiter, String repeatDelimiter, String componentDelimiter,
            String escapeDelimiter) {
        super(fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
    }

    public TestOrderCentaurXPRecord(String record, String fieldDelimiter, String repeatDelimiter,
            String componentDelimiter, String escapeDelimiter) throws IllegalArgumentException {
        super(record, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
    }


    
}
