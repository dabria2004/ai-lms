package com.ai.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="module")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Module implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30, nullable = false, unique = true)
    @NotBlank(message = "Module name is required!")
    private String name;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "module", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Resource> resources;

    @OneToMany(mappedBy = "module", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Video> videos;

    @OneToMany(mappedBy = "module", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Schedule> schedules;

    @Transient
    private int resourceCount;

    @Transient
    private int videoCount;

    @Transient
    @NotNull(message = "Resource is required!")
    private MultipartFile[] resource;

    @Transient
    @NotNull(message = "Video is required!")
    private MultipartFile[] video;

    @Transient
    private Schedule schedule;

}
