package com.vijay.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "cousines")
public class Cousines implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    @Column(name = "cousine_name", nullable = false)
    private String cousineName;

    @Column(name = "created_at")
    private Date createdAt;

//    @OneToMany(
//            mappedBy = "cousines",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<Restaurants> restaurants;

    @Column(name = "updated_at")
    private Date updatedAt;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCousineName() {
        return cousineName;
    }

    public void setCousineName(String cousineName) {
        this.cousineName = cousineName;
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
//
//    public List<Restaurants> getRestaurants() {
//        return restaurants;
//    }
//
//    public void setRestaurants(List<Restaurants> restaurants) {
//        this.restaurants = restaurants;
//    }

}