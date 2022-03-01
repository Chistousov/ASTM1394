package com.github.chistousov.lib.astm1394.record.comment;

import com.github.chistousov.lib.astm1394.record.Component;
import com.github.chistousov.lib.astm1394.record.Record;
import com.github.chistousov.lib.astm1394.record.RecordType;

public class CommentRecord extends Record {
	
	/*
	 * This field specifies the comment origination point as follows:
	 * P practice
	 * L information system
	 * I clinical instrument system 
	 */
	private Component<CommentSource> commentSource;
	    
	/*
	 * Where comment codes/mnemonics are used, the code should be sent first, followed, if desired, by the
	 * comment text and separated by a component delimiter as given in Section 5.6.6. 
	 */
	private Component<String> commentText;

	/*
	 * G generic/free result comment
	 * T result name comment
	 * P positive result comment
	 * N negative result comment
	 * I instrument flag(s) comment 
	 */
	private Component<CommentType> commentType;
	
	public CommentRecord(String fieldDelimiter, String repeatDelimiter, String componentDelimiter, String escapeDelimiter){
        super("", 5, com.github.chistousov.lib.astm1394.record.RecordType.C, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);
	}

	public CommentRecord(String record, String fieldDelimiter, String repeatDelimiter, String componentDelimiter, String escapeDelimiter) throws IllegalArgumentException {
        super(record, 5, RecordType.C, fieldDelimiter, repeatDelimiter, componentDelimiter, escapeDelimiter);

        if(record.charAt(0) != 'C'){
            throw new IllegalArgumentException("The message does not start with a C, so it is not a comment message.");
        }
        setSequenceNumber(getField(1));

		this.commentSource = new Component<>(() -> CommentSource.getBy(getField(2)), CommentSource::getIdForComponent);
		this.commentText = new Component<>(String.class, getField(3));
		this.commentType = new Component<>(() -> CommentType.getBy(getField(4)), CommentType::getIdForComponent);
		
	}

	public CommentSource getCommentSource() {
		return commentSource.getValue();
	}

	public void setCommentSource(CommentSource commentSource) {
		Component<CommentSource> commentSourceComponent = new Component<>(
			() -> commentSource, CommentSource::getIdForComponent
		);
		setField(commentSourceComponent.toString(), 2);
		this.commentSource = commentSourceComponent;
	}

	public String getCommentText() {
		return commentText.getValue();
	}

	public void setCommentText(String commentText) {
		Component<String> commentTextComponent = new Component<>(String.class, commentText);
		setField(commentTextComponent.toString(), 3);
		this.commentText = commentTextComponent;
	}

	public CommentType getCommentType() {
		return commentType.getValue();
	}

	public void setCommentType(CommentType commentType) {
		Component<CommentType> commentTypeComponent = new Component<>(
			() -> commentType, CommentType::getIdForComponent
		);
		setField(commentTypeComponent.toString(), 4);
		this.commentType = commentTypeComponent;
	}

	
}
