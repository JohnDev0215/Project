package com.engine.tbox.data;

import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public class NetworkFlow {
    private static final int APN_TYPE_LENGTH = 1;
    private static final byte DEFAULT_APN_TYPE = 0;
    private static final int DOWNLOAD_MONTH_LENGTH = 10;
    private static final int DOWNLOAD_TODAY_LENGTH = 10;
    private static final int END_TIME_LENGTH = 23;
    private static final int ICCID_LENGTH = 20;
    private static final int UPLOAD_MONTH_LENGTH = 10;
    private static final int UPLOAD_TODAY_LENGTH = 10;
    private byte apnType = 0;
    private String iccid = "";
    private String endTime = "";
    private String uploadToday = "";
    private String downloadToday = "";
    private String uploadMonth = "";
    private String downloadMonth = "";

    public void setApnType(byte apnType) {
        this.apnType = apnType;
    }

    public byte getApnType() {
        return this.apnType;
    }

    public void setIccid(String iccid) {
        if (iccid == null || iccid.length() > 20) {
            throw new IllegalArgumentException("ICCID must not exceed 20 characters.");
        }
        this.iccid = iccid;
    }

    public String getIccid() {
        return this.iccid;
    }

    public void setEndTime(String endTime) {
        if (endTime == null || endTime.length() > 23) {
            throw new IllegalArgumentException("End time must not exceed 23 characters.");
        }
        this.endTime = endTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setUploadToday(String uploadToday) {
        if (uploadToday == null || uploadToday.length() > 10) {
            throw new IllegalArgumentException("Upload today must not exceed 10 characters.");
        }
        this.uploadToday = uploadToday;
    }

    public String getUploadToday() {
        return this.uploadToday;
    }

    public void setDownloadToday(String downloadToday) {
        if (downloadToday == null || downloadToday.length() > 10) {
            throw new IllegalArgumentException("Download today must not exceed 10 characters.");
        }
        this.downloadToday = downloadToday;
    }

    public String getDownloadToday() {
        return this.downloadToday;
    }

    public void setUploadMonth(String uploadMonth) {
        if (uploadMonth == null || uploadMonth.length() > 10) {
            throw new IllegalArgumentException("Upload month must not exceed 10 characters.");
        }
        this.uploadMonth = uploadMonth;
    }

    public String getUploadMonth() {
        return this.uploadMonth;
    }

    public void setDownloadMonth(String downloadMonth) {
        if (downloadMonth == null || downloadMonth.length() > 10) {
            throw new IllegalArgumentException("Download month must not exceed 10 characters.");
        }
        this.downloadMonth = downloadMonth;
    }

    public String getDownloadMonth() {
        return this.downloadMonth;
    }

    public byte[] toByteArray() {
        byte[] data = new byte[getTotalLength()];
        int offset = 0 + 1;
        data[0] = this.apnType;
        writeFixedLengthString(data, this.iccid, 20, offset);
        int offset2 = offset + 20;
        writeFixedLengthString(data, this.endTime, 23, offset2);
        int offset3 = offset2 + 23;
        writeFixedLengthString(data, this.uploadToday, 10, offset3);
        int offset4 = offset3 + 10;
        writeFixedLengthString(data, this.downloadToday, 10, offset4);
        int offset5 = offset4 + 10;
        writeFixedLengthString(data, this.uploadMonth, 10, offset5);
        writeFixedLengthString(data, this.downloadMonth, 10, offset5 + 10);
        return data;
    }

    public static NetworkFlow fromByteArray(byte[] data) {
        if (data == null || data.length < getTotalLength()) {
            return null;
        }
        NetworkFlow networkFlow = new NetworkFlow();
        int offset = 0 + 1;
        networkFlow.setApnType(data[0]);
        networkFlow.setIccid(readFixedLengthString(data, 20, offset));
        int offset2 = offset + 20;
        networkFlow.setEndTime(readFixedLengthString(data, 23, offset2));
        int offset3 = offset2 + 23;
        networkFlow.setUploadToday(readFixedLengthString(data, 10, offset3));
        int offset4 = offset3 + 10;
        networkFlow.setDownloadToday(readFixedLengthString(data, 10, offset4));
        int offset5 = offset4 + 10;
        networkFlow.setUploadMonth(readFixedLengthString(data, 10, offset5));
        networkFlow.setDownloadMonth(readFixedLengthString(data, 10, offset5 + 10));
        return networkFlow;
    }

    private static int getTotalLength() {
        return 84;
    }

    private static void writeFixedLengthString(byte[] target, String str, int maxLength, int offset) {
        byte[] bytes = str.getBytes(StandardCharsets.US_ASCII);
        int len = Math.min(bytes.length, maxLength);
        System.arraycopy(bytes, 0, target, offset, len);
        for (int i = len; i < maxLength; i++) {
            target[offset + i] = 0;
        }
    }

    private static String readFixedLengthString(byte[] source, int length, int offset) {
        byte[] bytes = new byte[length];
        System.arraycopy(source, offset, bytes, 0, length);
        return new String(bytes, StandardCharsets.US_ASCII).trim();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("NetworkFlow{");
        sb.append("apnType=");
        sb.append((int) this.apnType);
        sb.append(", iccid='");
        sb.append(MaskUtils.maskString(this.iccid));
        sb.append('\'');
        sb.append(", endTime='");
        sb.append(this.endTime);
        sb.append('\'');
        sb.append(", uploadToday='");
        sb.append(this.uploadToday);
        sb.append('\'');
        sb.append(", downloadToday='");
        sb.append(this.downloadToday);
        sb.append('\'');
        sb.append(", uploadMonth='");
        sb.append(this.uploadMonth);
        sb.append('\'');
        sb.append(", downloadMonth='");
        sb.append(this.downloadMonth);
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }
}
