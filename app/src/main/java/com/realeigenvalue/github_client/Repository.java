package com.realeigenvalue.github_client;

/**
 * Class to hold repository data
 */
public class Repository {
    private String repository_name;
    private String username;
    private String description;
    private String url;

    public Repository() {
    }

    public String getRepository_name() {
        return repository_name;
    }

    public void setRepository_name(String repository_name) {
        this.repository_name = repository_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
