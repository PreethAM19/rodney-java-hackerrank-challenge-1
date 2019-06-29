package com.hackerrank.github.model;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Event {

    @Id
    @Column(unique = true)
    private Long id;

    @Column
    private String type;

    @JoinColumn(referencedColumnName = "id")
    @ManyToOne
    private Actor actor;

    @JoinColumn(referencedColumnName = "id")
    @ManyToOne
    private Repo repo;

    @Column
    //@JsonProperty(value = "created_at")
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Timestamp createdAt;

    public Event() {
    }

    public Event(Long id, String type, Actor actor, Repo repo, Timestamp createdAt) {
        this.id = id;
        this.type = type;
        this.actor = actor;
        this.repo = repo;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
