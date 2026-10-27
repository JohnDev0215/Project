package com.amazon.music.playbacknotification;

import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.support.v4.media.session.MediaSessionCompat;

/* JADX INFO: loaded from: classes4.dex */
public class PlaybackNotificationModel {
    private final Bitmap artworkBitmap;
    private final String artworkUrl;
    private final PendingIntent clickIntent;
    private final String contentHeader;
    private final String contentText;
    private final String contentTitle;
    private final PendingIntent deleteIntent;
    private final MediaType mediaType;
    private final MediaSessionCompat.Token tokenSession;

    private PlaybackNotificationModel(String str, String str2, String str3, Bitmap bitmap, String str4, PendingIntent pendingIntent, PendingIntent pendingIntent2, MediaType mediaType, MediaSessionCompat.Token token) {
        this.contentHeader = str;
        this.contentTitle = str2;
        this.contentText = str3;
        this.artworkBitmap = bitmap;
        this.artworkUrl = str4;
        this.clickIntent = pendingIntent;
        this.deleteIntent = pendingIntent2;
        this.mediaType = mediaType;
        this.tokenSession = token;
    }

    public String getContentHeader() {
        return this.contentHeader;
    }

    public String getContentTitle() {
        return this.contentTitle;
    }

    public String getContentText() {
        return this.contentText;
    }

    public Bitmap getArtworkBitmap() {
        return this.artworkBitmap;
    }

    public PendingIntent getClickIntent() {
        return this.clickIntent;
    }

    public PendingIntent getDeleteIntent() {
        return this.deleteIntent;
    }

    public MediaType getMediaType() {
        return this.mediaType;
    }

    public MediaSessionCompat.Token getToken() {
        return this.tokenSession;
    }

    public static Builder builder(String str, String str2, PendingIntent pendingIntent, PendingIntent pendingIntent2, MediaType mediaType) {
        return new Builder(str, str2, pendingIntent, pendingIntent2, mediaType);
    }

    public static class Builder {
        private Bitmap artworkBitmap;
        private String artworkUrl;
        private final PendingIntent clickIntent;
        private String contentHeader;
        private final String contentText;
        private final String contentTitle;
        private final PendingIntent deleteIntent;
        private final MediaType mediaType;
        private MediaSessionCompat.Token tokenSession;

        private Builder(String str, String str2, PendingIntent pendingIntent, PendingIntent pendingIntent2, MediaType mediaType) {
            this.contentText = str2;
            this.contentTitle = str;
            this.clickIntent = pendingIntent;
            this.deleteIntent = pendingIntent2;
            this.mediaType = mediaType;
        }

        public Builder withContentHeader(String str) {
            this.contentHeader = str;
            return this;
        }

        public Builder withArtworkBitmap(Bitmap bitmap) {
            this.artworkBitmap = bitmap;
            return this;
        }

        public Builder withToken(MediaSessionCompat.Token token) {
            this.tokenSession = token;
            return this;
        }

        public PlaybackNotificationModel build() {
            return new PlaybackNotificationModel(this.contentHeader, this.contentTitle, this.contentText, this.artworkBitmap, this.artworkUrl, this.clickIntent, this.deleteIntent, this.mediaType, this.tokenSession);
        }
    }
}
