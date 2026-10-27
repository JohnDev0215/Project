package org.apache.commons.net.smtp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class SimpleSMTPHeader {
    private StringBuffer cc;
    private final String from;
    private boolean hasHeaderDate;
    private final StringBuffer headerFields;
    private final String subject;
    private final String to;

    public SimpleSMTPHeader(String from, String to, String subject) {
        if (from == null) {
            throw new IllegalArgumentException("From cannot be null");
        }
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.headerFields = new StringBuffer();
        this.cc = null;
    }

    public void addHeaderField(String headerField, String value) {
        if (!this.hasHeaderDate && "Date".equals(headerField)) {
            this.hasHeaderDate = true;
        }
        this.headerFields.append(headerField);
        this.headerFields.append(": ");
        this.headerFields.append(value);
        this.headerFields.append('\n');
    }

    public void addCC(String address) {
        StringBuffer stringBuffer = this.cc;
        if (stringBuffer == null) {
            this.cc = new StringBuffer();
        } else {
            stringBuffer.append(", ");
        }
        this.cc.append(address);
    }

    public String toString() {
        StringBuilder header = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        if (!this.hasHeaderDate) {
            addHeaderField("Date", format.format(new Date()));
        }
        if (this.headerFields.length() > 0) {
            header.append(this.headerFields.toString());
        }
        header.append("From: ");
        header.append(this.from);
        header.append("\n");
        if (this.to != null) {
            header.append("To: ");
            header.append(this.to);
            header.append("\n");
        }
        if (this.cc != null) {
            header.append("Cc: ");
            header.append(this.cc.toString());
            header.append("\n");
        }
        if (this.subject != null) {
            header.append("Subject: ");
            header.append(this.subject);
            header.append("\n");
        }
        header.append('\n');
        return header.toString();
    }
}
