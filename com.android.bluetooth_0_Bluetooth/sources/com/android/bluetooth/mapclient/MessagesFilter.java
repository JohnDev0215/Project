package com.android.bluetooth.mapclient;

import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public final class MessagesFilter {
    public static final byte MESSAGE_TYPE_ALL = 0;
    public static final byte MESSAGE_TYPE_EMAIL = 4;
    public static final byte MESSAGE_TYPE_MMS = 8;
    public static final byte MESSAGE_TYPE_SMS_CDMA = 2;
    public static final byte MESSAGE_TYPE_SMS_GSM = 1;
    public static final byte PRIORITY_ANY = 0;
    public static final byte PRIORITY_HIGH = 1;
    public static final byte PRIORITY_NON_HIGH = 2;
    public static final byte READ_STATUS_ANY = 0;
    public static final byte READ_STATUS_READ = 2;
    public static final byte READ_STATUS_UNREAD = 1;
    public byte messageType = 0;
    public String periodBegin = null;
    public String periodEnd = null;
    public byte readStatus = 0;
    public String recipient = null;
    public String originator = null;
    public byte priority = 0;

    public void setMessageType(byte filter) {
        this.messageType = filter;
    }

    public void setPeriod(Date filterBegin, Date filterEnd) {
        if (filterBegin != null) {
            this.periodBegin = new ObexTime(filterBegin).toString();
        }
        if (filterEnd != null) {
            this.periodEnd = new ObexTime(filterEnd).toString();
        }
    }

    public void setReadStatus(byte readfilter) {
        this.readStatus = readfilter;
    }

    public void setRecipient(String filter) {
        if (filter != null && filter.isEmpty()) {
            this.recipient = null;
        } else {
            this.recipient = filter;
        }
    }

    public void setOriginator(String filter) {
        if (filter != null && filter.isEmpty()) {
            this.originator = null;
        } else {
            this.originator = filter;
        }
    }

    public void setPriority(byte filter) {
        this.priority = filter;
    }
}
