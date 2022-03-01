package com.github.chistousov.lib.astm1394.unicelldxh;

import com.github.chistousov.lib.astm1394.record.comment.CommentRecord;
import com.github.chistousov.lib.astm1394.record.patient.PatientInformationRecord;

public class PatientInformationUniCellDxHRecord extends PatientInformationRecord<TestOrderUniCellDxHRecord, CommentRecord>  {

    public PatientInformationUniCellDxHRecord(String fieldDelimiter, String repeatDelimiter, String componentDelimiter,
            String escapeDelimiter) throws IllegalArgumentException {
        super(fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
    }

    public PatientInformationUniCellDxHRecord(String record, String fieldDelimiter, String repeatDelimiter,
            String componentDelimiter, String escapeDelimiter) throws IllegalArgumentException {
        super(record, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
    }

    
}
