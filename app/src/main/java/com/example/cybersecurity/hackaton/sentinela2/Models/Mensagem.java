package com.example.cybersecurity.hackaton.sentinela2.Models;

public class Mensagem {

    private String responseType;
    private String text;
    private String time;
    private String typing;
    private String source;
    private String title;
    private String description;
    private String preference;
    private String options;
    private String messageToHumanAgent;
    private String topic;
    private String suggestions;

    public Mensagem() {
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTyping() {
        return typing;
    }

    public void setTyping(String typing) {
        this.typing = typing;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getMessageToHumanAgent() {
        return messageToHumanAgent;
    }

    public void setMessageToHumanAgent(String messageToHumanAgent) {
        this.messageToHumanAgent = messageToHumanAgent;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }
}
