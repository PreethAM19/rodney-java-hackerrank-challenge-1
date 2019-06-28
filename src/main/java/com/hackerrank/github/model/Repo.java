package com.hackerrank.github.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Repo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String url;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "repo")
    private List<Event> eventList;

    public Repo() {
    }

    public Repo(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public Repo setEventList(List<Event> eventList) {
        this.eventList = eventList;
        return this;
    }
}
