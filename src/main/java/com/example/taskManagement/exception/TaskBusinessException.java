package com.example.taskManagement.exception;

import com.example.taskManagement.util.MessageUtil;

/**
 * [概要] タスク関連のビジネス例外クラス
 * [詳細] タスク関連のビジネスロジック処理で発生する例外を表すクラス
 */
public class TaskBusinessException extends RuntimeException {

    /**
     * [概要] メッセージIDからビジネス例外を生成
     * @param messageId メッセージID
     * @param args メッセージの引数
     */
    public TaskBusinessException(String messageId, Object... args) {
        super(MessageUtil.getMessage(messageId, args));
    }

    /**
     * [概要] 原因例外付きで生成
     * @param messageId メッセージID
     * @param cause 原因となった例外
     * @param args メッセージの引数
     */
    public TaskBusinessException(String messageId, Throwable cause, Object... args) {
        super(MessageUtil.getMessage(messageId, args), cause);
    }
}
