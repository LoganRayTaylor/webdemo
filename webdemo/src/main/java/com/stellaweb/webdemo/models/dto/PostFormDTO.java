package com.stellaweb.webdemo.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class PostFormDTO {

    @NotNull
    @NotBlank(message="Must not be blank")
    @Size(min=3, max=50, message="Username must be between 3 and 50 characters")
    private String title;

    @NotNull
    @NotBlank(message="Must not be blank")
    @Size(min=3, max=250, message="Post must be between 3 and 250 characters")
    private String text;

    private boolean isPublic;

    private boolean completed;

    private LocalDate postDate;

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


    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        String[] postDateArr = postDate.split("-");
        int year = Integer.parseInt(postDateArr[0]);
        int month = Integer.parseInt(postDateArr[1]);
        int day = Integer.parseInt(postDateArr[2]);
        this.postDate = LocalDate.of(year, month, day);
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;


    }
}