package com.example.menusassignment.model.articles;

import java.util.Date;

public class Article {

    /**
     * {
     *       "source": {
     *         "id": "the-washington-post",
     *         "name": "The Washington Post"
     *       },
     *       "author": "Dan Rosenzweig-Ziff",
     *       "title": "Nearly any material can harvest energy out of thin air, scientists find - The Washington Post",
     *       "description": "The technology builds on research that showed it was possible to capture the energy in humidity. The latest discovery finds it's possible to do so with any material.",
     *       "url": "https://www.washingtonpost.com/science/2023/05/26/harvest-energy-thin-air/",
     *       "urlToImage": "https://www.washingtonpost.com/wp-apps/imrs.php?src=https://arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/52YSPI46IESSBZL6CT43CHRITE.jpg&w=1440",
     *       "publishedAt": "2023-05-27T01:20:21Z",
     *       "content": "Comment on this story\r\nComment\r\nNearly any material can be used to turn the energy in air humidity into electricity, scientists found in a discovery that could lead to continuously producing clean en… [+3522 chars]"
     *     }
     */

    private String author, title, description, url, urlToImage, content;
    private Source source;
    private Date publishedAt;

    public Article( Source source, String author, String title, String description, String url, String urlToImage, String content, Date publishedAt) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.content = content;
        this.source = source;
        this.publishedAt = publishedAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }
}
