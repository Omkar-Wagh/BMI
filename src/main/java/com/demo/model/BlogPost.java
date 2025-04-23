package com.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "blogs")
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int blogId;

    private String blogProfile;

    @Lob
    private String blogDesc;

    private String imageName;
    private String imageType;

    @Lob
    private byte[] imageData; // Stores image in bytea format

    // Default Constructor
    public BlogPost() {
    }

    // Constructor with parameters (optional)
    public BlogPost(String blogProfile, String blogDesc, String imageName, String imageType, byte[] imageData) {
        this.blogProfile = blogProfile;
        this.blogDesc = blogDesc;
        this.imageName = imageName;
        this.imageType = imageType;
        this.imageData = imageData;
    }

    // Getters and Setters
    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getBlogProfile() {
        return blogProfile;
    }

    public void setBlogProfile(String blogProfile) {
        this.blogProfile = blogProfile;
    }

    public String getBlogDesc() {
        return blogDesc;
    }

    public void setBlogDesc(String blogDesc) {
        this.blogDesc = blogDesc;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    @Transient
    private String base64Image; // Not stored in DB, used for UI display

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
