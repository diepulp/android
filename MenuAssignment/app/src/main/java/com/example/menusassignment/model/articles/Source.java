package com.example.menusassignment.model.articles;

/**
 * "source": {
 *         "id": "the-washington-post",
 *         "name": "The Washington Post"
 *       }
 */

/**
 * public class Article{
 *     public Source source;
 *     public String author;
 *     public String title;
 *     public String description;
 *     public String url;
 *     public String urlToImage;
 *     public Date publishedAt;
 *     public String content;
 * }
 *
 * public class Root{
 *     public String status;
 *     public int totalResults;
 *     public ArrayList<Article> articles;
 * }
 *
 * public class Source{
 *     public String id;
 *     public String name;
 * }
 */
public class Source {
    private String id, name;

    public Source(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
