package com.amazon.music.downloads.notification;

/* JADX INFO: loaded from: classes4.dex */
public class NotificationInfo {
    private final String mDescription;
    private final String mImageUri;
    private final String mTitle;

    public NotificationInfo(String str, String str2, String str3) {
        this.mTitle = str;
        this.mDescription = str2;
        this.mImageUri = str3;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getImageUri() {
        return this.mImageUri;
    }
}
