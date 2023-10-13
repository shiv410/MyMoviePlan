package com.MyMoviePlan.model;

public class TicketDetails {

    private String auditoriumName;

    private String showName;

    private String showTiming;

    private double amount;

    private String movieName;

    private String movieImage;

    private String movieBgImage;

    public TicketDetails(String auditoriumName, String showName, String showTiming, double amount, String movieName,
            String movieImage, String movieBgImage) {
        this.auditoriumName = auditoriumName;
        this.showName = showName;
        this.showTiming = showTiming;
        this.amount = amount;
        this.movieName = movieName;
        this.movieImage = movieImage;
        this.movieBgImage = movieBgImage;
    }

    public String getAuditoriumName() {
        return auditoriumName;
    }

    public void setAuditoriumName(String auditoriumName) {
        this.auditoriumName = auditoriumName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShowTiming() {
        return showTiming;
    }

    public void setShowTiming(String showTiming) {
        this.showTiming = showTiming;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public String getMovieBgImage() {
        return movieBgImage;
    }

    public void setMovieBgImage(String movieBgImage) {
        this.movieBgImage = movieBgImage;
    }

}
