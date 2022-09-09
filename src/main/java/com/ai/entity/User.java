package com.ai.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User {

    @Id
    @Column(length = 10,name = "login_id")
    @NotBlank(message = "Student ID is required!")
    private String loginId;
    @Column(nullable = false)
    @NotBlank(message = "Student Name is required!")
    private String name;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Student Email is required!")
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "is_active", nullable = false)
    public boolean isActive;
	@ManyToMany
    @JoinTable(name = "batch_has_user",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "batch_id"))
    @JsonIgnore
	private List<Batch> batches;

	@OneToMany(mappedBy = "user", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private List<Message> messages;

	@Column(name = "photo",columnDefinition = "VARCHAR(255) DEFAULT 'default.png'", nullable = false)
	private String photo;

	@Transient
    private Integer[] batchId;

    public enum Role{
        Admin,
        Teacher,
        Student
    }
}
