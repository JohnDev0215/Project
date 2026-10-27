package com.engine.tbox.data;

/* JADX INFO: loaded from: classes.dex */
public class FtpInfo {
    private String password;
    private int port;
    private String url;
    private String username;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (this.password != null) {
            for (int i = 0; i < this.password.length(); i++) {
                builder.append('*');
            }
        }
        return "FtpInfo{username='" + this.username + "', password='" + builder.toString() + "', port=" + this.port + '}';
    }
}
