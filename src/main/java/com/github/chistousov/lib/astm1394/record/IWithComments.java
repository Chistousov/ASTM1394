package com.github.chistousov.lib.astm1394.record;

import java.util.List;

import com.github.chistousov.lib.astm1394.record.comment.CommentRecord;

/**
 * <p>
 * Класс реалирующий этот интерфейс показывает, что он поддерживает комментарии типа T (T extends CommentRecord)
 * (A class implementing this interface shows that it supports comments of type T (T extends CommentRecord))
 * </p>
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public interface IWithComments<T extends CommentRecord> {
    
    /**
     * Adds a comment to a record (record)
     * (Добавляет комментарий к записи (record))
     * 
     * @author Nikita Chistousov (chistousov.nik@yandex.ru)
     * @since 8
     * 
     * @param commentRecord comment descendant of type CommentRecord (комментарий потомок тип CommentRecord)
     */
    public void addCommentRecord(T commentRecord);

    /**
     * Returns a list of the comments of a record (record)
     * (Возвращает список коментариев записи (record))
     * 
     * @author Nikita Chistousov (chistousov.nik@yandex.ru)
     * @since 8
     * 
     * @return List of comments of type T (T extends CommentRecord) (List коментариев типа T (T extends CommentRecord))
     */
	public List<T> getCommentRecords();
}
