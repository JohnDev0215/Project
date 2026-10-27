package org.apache.commons.net.nntp;

/* JADX INFO: loaded from: classes3.dex */
public class SimpleNNTPHeader {
    private final String from;
    private final String subject;
    private final StringBuilder newsgroups = new StringBuilder();
    private final StringBuilder headerFields = new StringBuilder();
    private int newsgroupCount = 0;

    public SimpleNNTPHeader(String from, String subject) {
        this.from = from;
        this.subject = subject;
    }

    public void addNewsgroup(String newsgroup) {
        int i = this.newsgroupCount;
        this.newsgroupCount = i + 1;
        if (i > 0) {
            this.newsgroups.append(',');
        }
        this.newsgroups.append(newsgroup);
    }

    public void addHeaderField(String headerField, String value) {
        this.headerFields.append(headerField);
        this.headerFields.append(": ");
        this.headerFields.append(value);
        this.headerFields.append('\n');
    }

    public String getFromAddress() {
        return this.from;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getNewsgroups() {
        return this.newsgroups.toString();
    }

    public String toString() {
        StringBuilder header = new StringBuilder();
        header.append("From: ");
        header.append(this.from);
        header.append("\nNewsgroups: ");
        header.append(this.newsgroups.toString());
        header.append("\nSubject: ");
        header.append(this.subject);
        header.append('\n');
        if (this.headerFields.length() > 0) {
            header.append(this.headerFields.toString());
        }
        header.append('\n');
        return header.toString();
    }
}
