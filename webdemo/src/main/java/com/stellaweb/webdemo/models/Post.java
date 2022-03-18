package com.stellaweb.webdemo.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Post extends AbstractEntity {



    @NotNull
    private String title;

    @NotNull
    private String text;

    private boolean isPublic;


    private boolean completed;


    @ManyToOne
    private User user;

    private LocalDate startDate;

    private LocalDate postDate;

    private LocalDate completeDate;


    public Post() {}

    public Post(@NotNull String title, @NotNull String text, boolean isPublic, User user, LocalDate postDate) {
        this.title = title;
        this.text = text;
        this.isPublic = isPublic;
        this.user = user;
        this.startDate = LocalDate.now();
        this.postDate = postDate;
        this.completeDate = null;
    }

    public LocalDate getStartdate() {
        return startDate;
    }

    public void setStartDate() {
        this.startDate = startDate;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public Object getUser() {
        if (user != null) {
            return user;
        } else {
            return (new User("Anonymous", "password"));
        }
    }
}