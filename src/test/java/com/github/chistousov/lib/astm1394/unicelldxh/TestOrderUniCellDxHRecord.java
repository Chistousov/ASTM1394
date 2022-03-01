package com.github.chistousov.lib.astm1394.unicelldxh;

import com.github.chistousov.lib.astm1394.record.comment.CommentRecord;
import com.github.chistousov.lib.astm1394.record.order.TestOrderRecord;

public class TestOrderUniCellDxHRecord extends TestOrderRecord<ResultUniCellDxHrecord, CommentRecord>  {

    public TestOrderUniCellDxHRecord(String fieldDelimiter, String repeatDelimiter, String componentDelimiter,
            String escapeDelimiter) {
        super(fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
    }

    public TestOrderUniCellDxHRecord(String record, String fieldDelimiter, String repeatDelimiter,
            String componentDelimiter, String escapeDelimiter) throws IllegalArgumentException {
        super(record, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
    }

    
    
}
