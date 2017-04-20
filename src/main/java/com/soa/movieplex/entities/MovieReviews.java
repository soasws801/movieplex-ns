/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.movieplex.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neelshah
 */
@Entity
@Table(name = "MOVIE_REVIEWS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovieReviews.findAll", query = "SELECT m FROM MovieReviews m")
    , @NamedQuery(name = "MovieReviews.findById", query = "SELECT m FROM MovieReviews m WHERE m.id = :id") 
    , @NamedQuery(name = "MovieReviews.findByCustomer", query = "SELECT m FROM MovieReviews m WHERE m.customer = :customer")
    , @NamedQuery(name = "MovieReviews.findByRating", query = "SELECT m FROM MovieReviews m WHERE m.rating = :rating")
    , @NamedQuery(name = "MovieReviews.findByComments", query = "SELECT m FROM MovieReviews m WHERE m.comments = :comments")
   })
public class MovieReviews implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CUSTOMER")
    private String customer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RATING")
    private int rating;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "COMMENTS")
    private String comments;

    public MovieReviews() {
    }

    public MovieReviews(Integer id) {
        this.id = id;
    }

    public MovieReviews(Integer id, String customer, int rating, String comments) {
        this.id = id;
        this.customer = customer;
        this.rating = rating;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovieReviews)) {
            return false;
        }
        MovieReviews other = (MovieReviews) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soa.movieplex.entities.MovieReviews[ id=" + id + " ]";
    }

    private Movie movie;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }


   
    
}
