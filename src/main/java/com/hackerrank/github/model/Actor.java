package com.hackerrank.github.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;


@Entity
public class Actor {

    @Id
    @Column(unique = true)
    private Long id;

    @Column(unique = true)
    private String login;

    @Column
    private String avatarUrl;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actor")
    private List<Event> eventList;

    /**
     * Field used to enable optimistic locking
     */
    @JsonIgnore
    @Version
    private Integer version;

    public Actor() {
    }

    public Actor(Long id, String login, String avatarUrl) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @JsonProperty("avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public Actor setEventList(List<Event> eventList) {
        this.eventList = eventList;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public Actor setVersion(Integer version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return "{ id : "+id+ " ,login : "+login+" ,avatarUrl : "+avatarUrl+" }";
    }
}
