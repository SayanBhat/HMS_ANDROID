package project.sayan.hms.Model;

import java.util.List;

/**
 * Created by Sayan on 3/25/2018.
 */

public class HealthNewsModel {

    public String STATUS="status";
    public String TOTALRESULTS="totalResults";

    public  String AUTHOR="author";
    public String TITLE="title";
    public String DESCRIPTION="description";
    public String URL="url";
    public String URLTOIMAGE="urlToImage";
    public String PUBLISHEDAT="publishedAt";
    public String ARTICLES="articles";

    public String status;
    public int totalResults;

    public  String author;
    public String title;
    public String description;
    public String url;
    public String urlToImage;
    public String publishedAt;

    public List<HealthNewsModel> articles;
}
