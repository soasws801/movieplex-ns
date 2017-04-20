/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.movieplex.reviews;

import com.soa.movieplex.client.MovieReviewBackingBean;
import com.soa.movieplex.entities.Movie;
import com.soa.movieplex.entities.MovieReviews;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 *
 * @author neelshah
 */
@Named
@FlowScoped("review")
public class Review implements Serializable {

    int movieId;
    
    @Inject
    MovieReviewBackingBean bean;
    
    @Resource
    private UserTransaction userTransaction;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    @PersistenceContext
    EntityManager entityManager;

    public String getMovieName() {
        try {
            return entityManager.createNamedQuery("Movie.findById", Movie.class).setParameter("id", movieId).getSingleResult().getName();
        } catch (NoResultException e) {
            return "";
        }
    }
    
    
    public void addReview() throws Exception {
        userTransaction.begin();
        MovieReviews review = new MovieReviews(bean.getID(),bean.getCusName(), bean.getRating(), bean.getComment());
        Movie movie = new Movie(movieId);
        review.setMovie(movie);

        entityManager.persist(review);
        userTransaction.commit();
    }
    
 // public List<MovieReviews> getReviews() {
       
//            List<MovieReviews> list = entityManager.createNamedQuery(
  //                  "MovieReviews.findByMovieAndId",
    //                MovieReviews.class).setParameter("movieId", movieId).getResultList();
     //      
       //     return list;
        
   // }
    
    
}
