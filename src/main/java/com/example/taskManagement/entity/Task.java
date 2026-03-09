package com.example.taskManagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TASK")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "タイトルは必須です")
    @Size(max = 20, message = "タイトルは20文字以内です")
    @Column(nullable = false)
    private String title;

    @Size(max = 100, message = "内容は100文字以内です")
    private String description;

    @NotBlank(message = "ステータスは必須です")
    @Pattern(regexp = "^(TODO|DOING|DONE)$", message = "ステータスはTODO、DOING、DONEのいずれかです")
    @Column(nullable = false, length = 5)
    private String status;

    @NotNull(message = "期限は必須です")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "期限日はyyyy-MM-dd形式で入力してください")
    @Column(nullable = false, length = 10, columnDefinition = "CHAR(10)")
    private String limitDate;

    @Column(nullable = false)
    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    // --- JPA用 ---
    protected Task() {}

    /**
     * DBに保存せず、`id` から動的に生成する業務番号を返す
     */
    @Transient
    public String getTaskNumber() {
        if (this.id == null) return null;
        return String.valueOf(this.id);
    }

}