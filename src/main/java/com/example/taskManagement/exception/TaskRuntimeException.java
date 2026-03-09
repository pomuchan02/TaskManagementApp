package com.example.taskManagement.exception;

import com.example.taskManagement.util.MessageUtil;

/**
 * [概要] タスク関連の実行時例外クラス
 * [詳細] タスク関連の処理で発生する実行時例外を表すクラス
 */
public class TaskRuntimeException extends RuntimeException {

    /**
     * [概要] メッセージIDから実行時例外を生成
     * @param messageId メッセージID
     * @param args メッセージの引数
     */
    public TaskRuntimeException(String messageId, Object... args) {
        super(MessageUtil.getMessage(messageId, args));
    }

    /**
     * [概要] 原因例外付きで生成
     * @param messageId メッセージID
     * @param cause 原因となった例外
     * @param args メッセージの引数
     */
    public TaskRuntimeException(String messageId, Throwable cause, Object... args) {
        super(MessageUtil.getMessage(messageId, args), cause);
    }
}


