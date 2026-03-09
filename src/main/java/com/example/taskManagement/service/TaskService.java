package com.example.taskManagement.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.taskManagement.constants.MsgIdConst;
import com.example.taskManagement.constants.TaskConstants;
import com.example.taskManagement.entity.Task;
import com.example.taskManagement.repository.TaskRepository;
import com.example.taskManagement.util.MessageUtil;

/**
 * [概要] タスク管理Webアプリのサービスクラス
 * [詳細] タスク取得/更新/削除のビジネスロジックを実装するクラス
 */
@Service
@Transactional
public class TaskService {

    @Autowired
    private MessageUtil messageUtil;
    @Autowired
    private TaskRepository taskRepository;

    /**
     * [概要] タスク一覧取得(T001)
     * [詳細] タスクリポジトリを使用してタスクの一覧を取得する
     * @return タスク一覧リスト
     */
    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        try {
            return taskRepository.findAll();
        } catch (DataAccessException e) {
            throw messageUtil.createTaskRuntimeException(e, MsgIdConst.ERROR_TASK_LIST);
        }
    }
    
    /**
     * [概要] タスク登録(T002)
     * [詳細] タスクリポジトリを使用してタスクを登録する
     * @param newTask 登録するタスクの情報
     */
    public void createTask(Task newTask) {
        try {
            // タスクの初期値を設定
            newTask.setStatus(TaskConstants.STATUS_TODO);
            newTask.setCreateDate(LocalDateTime.now());
            // タスク登録
            taskRepository.save(newTask);
        } catch (DataAccessException e) {
            throw messageUtil.createTaskRuntimeException(e, MsgIdConst.ERROR_TASK_CREATE);
        }
    }
    
    /**
     * [概要] タスク更新(T003)
     * [詳細] タスクリポジトリを使用してタスクを更新する
     * @param id 更新するタスクのID
     * @param updatedTask 更新するタスクの情報
     */
    public void updateTask(Long id, Task updatedTask) {
        try {
            // タスクの存在確認
            Task existingTask = getTaskById(id);
            // タスクの更新情報を設定
            updatedTask.setCreateDate(existingTask.getCreateDate());
            updatedTask.setUpdateDate(LocalDateTime.now());
            // タスク更新
            taskRepository.save(updatedTask);
        } catch (DataAccessException e) {
            throw messageUtil.createTaskRuntimeException(e, MsgIdConst.ERROR_TASK_UPDATE, id);
        }
    }
    
    /**
     * [概要] タスク削除(T004)
     * [詳細] タスクリポジトリを使用してタスクを削除する
     * @param id 削除するタスクのID
     */
    public void deleteTask(Long id) {
        try {
            // タスクの存在確認
            getTaskById(id);
            // タスク削除
            taskRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw messageUtil.createTaskRuntimeException(e, MsgIdConst.ERROR_TASK_DELETE, id);
        }
    }

    /**
     * [概要] 指定タスク取得（T005）
     * [詳細] IDでタスクを取得する
     * @param id 取得するタスクのID
     * @return タスク
     */
    @Transactional(readOnly = true)
    public Task getTaskById(Long id) {
        try {
            // タスクの取得と存在確認
            return taskRepository.findById(id)
                .orElseThrow(() -> messageUtil.createTaskBusinessException(MsgIdConst.ERROR_TASK_NOT_FOUND, id));
        } catch (DataAccessException e) {
            throw messageUtil.createTaskRuntimeException(e, MsgIdConst.ERROR_TASK_GET, id);
        }
    }
}

