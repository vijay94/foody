package com.vijay.entities;

import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Embeddable
class RestaurantCousineMappingId implements Serializable  {

    @Column(name = "cousine_id", nullable = false)
    private Integer cousineId;

    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;

    public Integer getCousineId() {
        return cousineId;
    }

    public void setCousineId(Integer cousineId) {
        this.cousineId = cousineId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}

@Entity
@Table(name = "restaurant_cousine_mapping")
public class RestaurantCousineMapping implements Serializable {

    @EmbeddedId
    RestaurantCousineMappingId restaurantCousineMappingId;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("restaurant_id")
    private Restaurants restaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("cousine_id")
    private Cousines cousines;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    public RestaurantCousineMappingId getRestaurantCousineMappingId() {
        return restaurantCousineMappingId;
    }

    public void setRestaurantCousineMappingId(RestaurantCousineMappingId restaurantCousineMappingId) {
        this.restaurantCousineMappingId = restaurantCousineMappingId;
    }

    public Restaurants getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurants restaurant) {
        this.restaurant = restaurant;
    }

    public Cousines getCousines() {
        return cousines;
    }

    public void setCousines(Cousines cousines) {
        this.cousines = cousines;
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
}