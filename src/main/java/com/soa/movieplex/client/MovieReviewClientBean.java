/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.movieplex.client;

import com.soa.movieplex.entities.MovieReviews;
import com.soa.movieplex.json.MovieWriter;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author neelshah
 */
@Named
@RequestScoped
public class MovieReviewClientBean {

    @Inject
    MovieReviewBackingBean bean;

    Client client;
    WebTarget target;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/movieplex/rest/moviereviews/");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public MovieReviews[] getMovieReviews() {
        return target.request().get(MovieReviews[].class);
    }

    public MovieReviews getMovieReview() {
        MovieReviews mr = target.path("{id}").resolveTemplate("id", bean.getID()).request().get(MovieReviews.class);
        return mr;
    }

    public void addMovieReview() {
        MovieReviews mr = new MovieReviews();
        mr.setId(bean.getID());
        mr.setRating(bean.getRating());
        mr.setCustomer(bean.getCusName());
        mr.setComments(bean.getComment());
        
        target
                .register(MovieWriter.class)
                .request()
                .post(Entity.entity(mr, MediaType.APPLICATION_JSON));
    }

}
