package com.javalec.retrofit_02_mypractice;

public class MoreDetails {

    // Model class for details data

    // ----------- //
    // Properties //
    // ----------- //

    private String release;
    private String category;
    private String duration;


    // ----------- //
    // Constructor //
    // ----------- //


    public MoreDetails(String release, String category, String duration) {
        this.release = release;
        this.category = category;
        this.duration = duration;
    }

    // Getter
    public String getRelease() {
        return release;
    }

    public String getCategory() {
        return category;
    }

    public String getDuration() {
        return duration;
    }
}
