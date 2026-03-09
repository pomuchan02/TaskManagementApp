package com.example.taskManagement.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.example.taskManagement.exception.TaskBusinessException;
import com.example.taskManagement.exception.TaskRuntimeException;

/**
 * メッセージを取得するユーティリティクラス
 */
@Component
public class MessageUtil {
    
    private static MessageSource messageSource;
    
    public MessageUtil(MessageSource messageSource) {
        MessageUtil.messageSource = messageSource;
    }
    
    /**
     * メッセージIDと引数からメッセージを取得
     * @param messageId メッセージID
     * @param args メッセージの引数
     * @return メッセージ
     */
    public static String getMessage(String messageId, Object... args) {
        if (messageSource == null) {
            return messageId;
        }
        return messageSource.getMessage(messageId, args, messageId, LocaleContextHolder.getLocale());
    }
    
    /**
     * TaskRuntimeException を作成して返す
     * @param messageId メッセージID
     * @param args メッセージの引数
     * @return TaskRuntimeException
     */
    public TaskRuntimeException createTaskRuntimeException(String messageId, Object... args) {
        return new TaskRuntimeException(messageId, args);
    }
    
    /**
     * 原因例外付きで TaskRuntimeException を作成して返す
     * @param cause 原因となった例外
     * @param messageId メッセージID
     * @param args メッセージの引数
     * @return TaskRuntimeException
     */
    public TaskRuntimeException createTaskRuntimeException(Throwable cause, String messageId, Object... args) {
        return new TaskRuntimeException(messageId, cause, args);
    }
    
    /**
     * TaskBusinessException を作成して返す
     * @param messageId メッセージID
     * @param args メッセージの引数
     * @return TaskBusinessException
     */
    public TaskBusinessException createTaskBusinessException(String messageId, Object... args) {
        return new TaskBusinessException(messageId, args);
    }
    
    /**
     * 原因例外付きで TaskBusinessException を作成して返す
     * @param cause 原因となった例外
     * @param messageId メッセージID
     * @param args メッセージの引数
     * @return TaskBusinessException
     */
    public TaskBusinessException createTaskBusinessException(Throwable cause, String messageId, Object... args) {
        return new TaskBusinessException(messageId, cause, args);
    }
}

