package com.example.fragmentapplication;

public class Word {
    private String wordThis;
    private String wordMeaning;
    private  String wordInstance;
    public Word() {
    }
    public Word(String wordThis, String wordMeaning, String wordInstance){
        this.wordThis=wordThis;
        this.wordMeaning = wordMeaning;
        this.wordInstance = wordInstance;
    }
    public String getWordThis() {
        return wordThis;
    }

    public void setWordThis(String wordThis) {
        this.wordThis = wordThis;
    }

    public String getWordMeaning() {
        return wordMeaning;
    }

    public void setWordMeaning(String wordMeaning) {
        this.wordMeaning = wordMeaning;
    }

    public String getWordInstance() {
        return wordInstance;
    }

    public void setWordInstance(String wordInstance) {
        this.wordInstance = wordInstance;
    }
}
