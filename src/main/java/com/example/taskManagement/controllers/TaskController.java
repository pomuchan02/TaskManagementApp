package com.example.taskManagement.controllers;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.taskManagement.entity.Task;
import com.example.taskManagement.service.TaskService;

/**
 * [概要] タスク管理Webアプリのコントローラークラス
 * [詳細] タスクの一覧表示/登録/更新/削除のリクエストを処理するクラス
 */
@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * [概要] 一覧表示(GET)
     * [詳細] タスクの一覧を表示する
     * @param model
     * @return タスク一覧画面(G001)
     */
    @GetMapping("/tasks")
    public String showTask(Model model) {
        // タスクの一覧を取得(T001)してモデルに追加
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks/list";
    }

    /**
     * [概要] 新規登録画面表示(GET)
     * [詳細] タスク登録フォームを表示する
     * @return タスク登録画面(G002)
     */
    @GetMapping("/tasks/new")
    public String showNewTaskForm() {
        return "tasks/new";
    }

    /**
     * [概要] 新規登録(POST)
     * [詳細] タスクを新規登録する
     * @param newTask 登録するタスクの情報
     * @param result バリデーション結果
     * @param model
     * @return タスク一覧画面(G001)
     */
    @PostMapping("/tasks/new")
    public String createTask(@Valid @ModelAttribute Task newTask, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            // タスク登録(T002)
            taskService.createTask(newTask);
            // タスクの一覧を取得(T001)してモデルに追加
            model.addAttribute("tasks", taskService.getAllTasks());
            return "tasks/list";
        } else {
            // バリデーションエラーがある場合は、エラー情報をモデルに追加してタスク登録画面に戻す
            model.addAttribute("errors", result.getAllErrors());
            return "tasks/new";
        }
    }

    /**
     * [概要] 編集画面表示(GET)
     * [詳細] タスク編集フォームを表示する
     * @param id 更新するタスクのID
     * @param model
     * @return タスク編集画面(G003)
     */
    @GetMapping("/tasks/{id}/edit")
    public String showEditTaskForm(@PathVariable(name="id") Long id, Model model) {
        // 更新するタスクの情報を取得してモデルに追加
        model.addAttribute("task", taskService.getTaskById(id));
        return "tasks/edit";
    }

    /**
     * [概要] 更新(POST)
     * [詳細] タスクを更新する
     * @param id 更新するタスクのID
     * @param updatedTask 更新するタスクの情報
     * @param result バリデーション結果
     * @param model
     * @return タスク一覧画面(G001)
     */
    @PostMapping("/tasks/{id}/edit")
    public String updateTask(@PathVariable(name="id") Long id, @Valid @ModelAttribute Task updatedTask, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            // タスク更新(T003)
            taskService.updateTask(id, updatedTask);
            // タスクの一覧を取得(T001)してモデルに追加
            model.addAttribute("tasks", taskService.getAllTasks());
            return "tasks/list";
        } else {
            // バリデーションエラーがある場合は、エラー情報をモデルに追加してタスク編集画面に戻す
            model.addAttribute("errors", result.getAllErrors());
            return "tasks/edit";
        }
    }

    /**
     * [概要] 削除(POST)
     * [詳細] タスクを削除する
     * @param id 削除するタスクのID
     * @param model
     * @return タスク一覧画面(G001)
     */
    @PostMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable(name="id") Long id, Model model) {
        // タスク削除(T004)
        taskService.deleteTask(id);
        // タスクの一覧を取得(T001)してモデルに追加
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks/list";
    }
}