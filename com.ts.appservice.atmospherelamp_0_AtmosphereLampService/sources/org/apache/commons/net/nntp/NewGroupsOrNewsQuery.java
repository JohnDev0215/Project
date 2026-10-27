package org.apache.commons.net.nntp;

import java.util.Calendar;

/* JADX INFO: loaded from: classes3.dex */
public final class NewGroupsOrNewsQuery {
    private final String date;
    private final boolean isGMT;
    private final String time;
    private StringBuffer distributions = null;
    private StringBuffer newsgroups = null;

    public NewGroupsOrNewsQuery(Calendar date, boolean gmt) {
        this.isGMT = gmt;
        StringBuilder buffer = new StringBuilder();
        String str = Integer.toString(date.get(1));
        int num = str.length();
        if (num >= 2) {
            buffer.append(str.substring(num - 2));
        } else {
            buffer.append("00");
        }
        String str2 = Integer.toString(date.get(2) + 1);
        int num2 = str2.length();
        if (num2 == 1) {
            buffer.append('0');
            buffer.append(str2);
        } else if (num2 == 2) {
            buffer.append(str2);
        } else {
            buffer.append("01");
        }
        String str3 = Integer.toString(date.get(5));
        int num3 = str3.length();
        if (num3 == 1) {
            buffer.append('0');
            buffer.append(str3);
        } else if (num3 == 2) {
            buffer.append(str3);
        } else {
            buffer.append("01");
        }
        this.date = buffer.toString();
        buffer.setLength(0);
        String str4 = Integer.toString(date.get(11));
        int num4 = str4.length();
        if (num4 == 1) {
            buffer.append('0');
            buffer.append(str4);
        } else if (num4 == 2) {
            buffer.append(str4);
        } else {
            buffer.append("00");
        }
        String str5 = Integer.toString(date.get(12));
        int num5 = str5.length();
        if (num5 == 1) {
            buffer.append('0');
            buffer.append(str5);
        } else if (num5 == 2) {
            buffer.append(str5);
        } else {
            buffer.append("00");
        }
        String str6 = Integer.toString(date.get(13));
        int num6 = str6.length();
        if (num6 == 1) {
            buffer.append('0');
            buffer.append(str6);
        } else if (num6 == 2) {
            buffer.append(str6);
        } else {
            buffer.append("00");
        }
        this.time = buffer.toString();
    }

    public void addNewsgroup(String newsgroup) {
        StringBuffer stringBuffer = this.newsgroups;
        if (stringBuffer != null) {
            stringBuffer.append(',');
        } else {
            this.newsgroups = new StringBuffer();
        }
        this.newsgroups.append(newsgroup);
    }

    public void omitNewsgroup(String newsgroup) {
        addNewsgroup("!" + newsgroup);
    }

    public void addDistribution(String distribution) {
        StringBuffer stringBuffer = this.distributions;
        if (stringBuffer != null) {
            stringBuffer.append(',');
        } else {
            this.distributions = new StringBuffer();
        }
        this.distributions.append(distribution);
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    public boolean isGMT() {
        return this.isGMT;
    }

    public String getDistributions() {
        StringBuffer stringBuffer = this.distributions;
        if (stringBuffer == null) {
            return null;
        }
        return stringBuffer.toString();
    }

    public String getNewsgroups() {
        StringBuffer stringBuffer = this.newsgroups;
        if (stringBuffer == null) {
            return null;
        }
        return stringBuffer.toString();
    }
}
