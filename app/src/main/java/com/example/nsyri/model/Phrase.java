package com.example.nsyri.model;

public class Phrase {
    private String turkishText;
    private String nusayriText;
    private String pronunciation;
    private String category;

    public Phrase(String turkishText, String nusayriText, String pronunciation, String category) {
        this.turkishText = turkishText;
        this.nusayriText = nusayriText;
        this.pronunciation = pronunciation;
        this.category = category;
    }

    public String getTurkishText() {
        return turkishText;
    }

    public String getNusayriText() {
        return nusayriText;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public String getCategory() {
        return category;
    }
} 