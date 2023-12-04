package org.example.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

@Entity
@Table(name = "userprofile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "profileid")
    private Integer profileId;


    @Column(name = "image", columnDefinition = "TEXT")
    @JsonProperty("image")
    private String image;

    @OneToOne
    @JoinColumn(name = "userId")
    @JsonProperty("userId")
    private User user;


    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
