package com.vijay.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "restaurants")
@Entity
public class Restaurants implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Long id;

    @Column(name = "restaurant_name", nullable = false)
    private String restaurantName;

    @Column(name = "cover_image")
    private String coverImage;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
//
//    @OneToMany(mappedBy = "restaurants", cascade = CascadeType.ALL,
//            orphanRemoval = true, fetch = FetchType.EAGER)
//    List<Cousines> cousines;

    @OneToMany(targetEntity = Dishes.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    @Fetch(value = FetchMode.SUBSELECT)
    List<Dishes> dishes;

    @OneToMany(targetEntity = Reviews.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    @Fetch(value = FetchMode.SUBSELECT)
    List<Reviews> reviews;

    public static long getSerialVersionUID() {
    return serialVersionUID;
    }

    public Long getId() {
    return id;
    }

    public void setId(Long id) {
    this.id = id;
    }

    public String getRestaurantName() {
    return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
    this.restaurantName = restaurantName;
    }

    public String getCoverImage() {
    return coverImage;
    }

    public void setCoverImage(String coverImage) {
    this.coverImage = coverImage;
    }

    public Date getCreatedAt() {
    return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
    return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
    }

//    public List<Cousines> getCousines() {
//        return cousines;
//    }
//
//    public void setCousines(List<Cousines> cousines) {
//        this.cousines = cousines;
//    }

    public List<Dishes> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dishes> dishes) {
        this.dishes = dishes;
    }

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }
}