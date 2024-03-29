
package com.phnews.nick.phnews.Repository;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.phnews.nick.phnews.Entities.Articles;

import java.util.List;

public class GetArticlesResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;
    @SerializedName("articles")
    @Expose
    private List<Articles> articles = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<Articles> getArticles() {
        return articles;
    }
    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }

}
