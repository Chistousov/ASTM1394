package com.github.chistousov.lib.astm1394.centaurxp;

import com.github.chistousov.lib.astm1394.record.comment.CommentRecord;
import com.github.chistousov.lib.astm1394.record.patient.PatientInformationRecord;

public class PatientInformationCentaurXPRecord extends PatientInformationRecord<TestOrderCentaurXPRecord, CommentRecord>  {

    public PatientInformationCentaurXPRecord(String fieldDelimiter, String repeatDelimiter, String componentDelimiter,
            String escapeDelimiter) throws IllegalArgumentException {
        super(fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
    }

    public PatientInformationCentaurXPRecord(String record, String fieldDelimiter, String repeatDelimiter,
            String componentDelimiter, String escapeDelimiter) throws IllegalArgumentException {
        super(record, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
    }

    
    
}
