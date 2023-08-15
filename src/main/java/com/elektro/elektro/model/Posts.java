package com.elektro.elektro.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="posts")
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;
    private String pictureUrl;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateTime;

    @PrePersist
    private void onCreate() {
    dateTime = new Date();
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Posts(){

    }

    

    public Posts(String title, String content, String pictureUrl, Date dateTime, User user) {
        this.title = title;
        this.content = content;
        this.pictureUrl = pictureUrl;
        this.dateTime = dateTime;
        this.user = user;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
}
