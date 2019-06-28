package com.hackerrank.github.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Actor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String login;

    @Column
    private String avatarUrl;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actor")
    private List<Event> eventList;

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
}
