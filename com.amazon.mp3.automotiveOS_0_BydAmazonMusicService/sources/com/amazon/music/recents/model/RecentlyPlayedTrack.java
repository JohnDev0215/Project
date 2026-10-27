package com.amazon.music.recents.model;

import androidx.annotation.Nullable;
import com.amazon.music.contentEncoding.ContentEncoding;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class RecentlyPlayedTrack {

    @Nullable
    private String albumAsin;

    @Nullable
    private String albumName;

    @Nullable
    private String artistAsin;

    @Nullable
    private String artistName;

    @Nullable
    private String asin;

    @Nullable
    private List<ContentEncoding> contentEncodings;

    @Nullable
    private String deviceId;

    @Nullable
    private String deviceType;

    @Nullable
    private String displayName;

    @Nullable
    private Integer durationSeconds;

    @Nullable
    private Boolean hasLyrics;

    @Nullable
    private String image;

    @Nullable
    private Boolean isExplicit;

    @Nullable
    private Boolean isFree;

    @Nullable
    private Boolean isFreeOnDemandPlayable;

    @Nullable
    private Boolean isInLibrary;

    @Nullable
    private Boolean isInstantImport;

    @Nullable
    private Boolean isMusicSubscription;

    @Nullable
    private Boolean isPrime;

    @Nullable
    private Boolean isPurchased;

    @Nullable
    private Boolean isSonicRush;

    @Nullable
    private Boolean isSonicRushOnDemandPlayable;

    @Nullable
    private Boolean isUploaded;

    @Nullable
    private String marketPlaceId;

    @Nullable
    private String objectId;

    @Nullable
    private Long timestamp;

    @Nullable
    private String uid;

    private RecentlyPlayedTrack(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable Integer num, @Nullable Boolean bool, @Nullable String str9, @Nullable Boolean bool2, @Nullable Boolean bool3, @Nullable Boolean bool4, @Nullable Boolean bool5, @Nullable Boolean bool6, @Nullable Boolean bool7, @Nullable String str10, @Nullable String str11, @Nullable Long l, @Nullable Boolean bool8, @Nullable String str12, @Nullable Boolean bool9, @Nullable Boolean bool10, @Nullable List<ContentEncoding> list, @Nullable Boolean bool11, @Nullable Boolean bool12) {
        this.albumAsin = str;
        this.albumName = str2;
        this.artistAsin = str3;
        this.artistName = str4;
        this.asin = str5;
        this.deviceId = str6;
        this.deviceType = str7;
        this.displayName = str8;
        this.durationSeconds = num;
        this.hasLyrics = bool;
        this.image = str9;
        this.isInLibrary = bool2;
        this.isInstantImport = bool3;
        this.isPrime = bool4;
        this.isPurchased = bool5;
        this.isMusicSubscription = bool6;
        this.isUploaded = bool7;
        this.marketPlaceId = str10;
        this.objectId = str11;
        this.timestamp = l;
        this.isExplicit = bool8;
        this.uid = str12;
        this.isFree = bool9;
        this.isFreeOnDemandPlayable = bool10;
        this.contentEncodings = list;
        this.isSonicRush = bool11;
        this.isSonicRushOnDemandPlayable = bool12;
    }

    @Nullable
    public String getAlbumAsin() {
        return this.albumAsin;
    }

    @Nullable
    public String getAlbumName() {
        return this.albumName;
    }

    @Nullable
    public String getArtistAsin() {
        return this.artistAsin;
    }

    @Nullable
    public String getArtistName() {
        return this.artistName;
    }

    @Nullable
    public String getAsin() {
        return this.asin;
    }

    @Nullable
    public String getDisplayName() {
        return this.displayName;
    }

    @Nullable
    public Integer getDurationSeconds() {
        return this.durationSeconds;
    }

    @Nullable
    public Boolean getHasLyrics() {
        return this.hasLyrics;
    }

    @Nullable
    public String getImage() {
        return this.image;
    }

    @Nullable
    public Boolean getInLibrary() {
        return this.isInLibrary;
    }

    @Nullable
    public Boolean isPrime() {
        return this.isPrime;
    }

    @Nullable
    public Boolean isPurchased() {
        return this.isPurchased;
    }

    @Nullable
    public Boolean isMusicSubscription() {
        return this.isMusicSubscription;
    }

    @Nullable
    public String getObjectId() {
        return this.objectId;
    }

    @Nullable
    public Long getTimestamp() {
        return this.timestamp;
    }

    @Nullable
    public Boolean getExplicit() {
        return this.isExplicit;
    }

    @Nullable
    public List<ContentEncoding> getContentEncodings() {
        return this.contentEncodings;
    }

    @Nullable
    public Boolean isFree() {
        return this.isFree;
    }

    @Nullable
    public Boolean isFreeOnDemandPlayable() {
        return this.isFreeOnDemandPlayable;
    }

    @Nullable
    public Boolean isSonicRush() {
        return this.isSonicRush;
    }

    @Nullable
    public Boolean isSonicRushOnDemandPlayable() {
        return this.isSonicRushOnDemandPlayable;
    }

    public static final class Builder {

        @Nullable
        private String albumAsin;

        @Nullable
        private String albumName;

        @Nullable
        private String artistAsin;

        @Nullable
        private String artistName;

        @Nullable
        private String asin;

        @Nullable
        private List<ContentEncoding> contentEncodings;

        @Nullable
        private String deviceId;

        @Nullable
        private String deviceType;

        @Nullable
        private String displayName;

        @Nullable
        private Integer durationSeconds;

        @Nullable
        private Boolean hasLyrics;

        @Nullable
        private String image;

        @Nullable
        private Boolean isExplicit;

        @Nullable
        private Boolean isFree;

        @Nullable
        private Boolean isFreeOnDemandPlayable;

        @Nullable
        private Boolean isInLibrary;

        @Nullable
        private Boolean isInstantImport;

        @Nullable
        private Boolean isMusicSubscription;

        @Nullable
        private Boolean isPrime;

        @Nullable
        private Boolean isPurchased;

        @Nullable
        private Boolean isSonicRush;

        @Nullable
        private Boolean isSonicRushOnDemandPlayable;

        @Nullable
        private Boolean isUploaded;

        @Nullable
        private String marketPlaceId;

        @Nullable
        private String objectId;

        @Nullable
        private Long timestamp;

        @Nullable
        private String uid;

        public final Builder albumAsin(@Nullable String str) {
            this.albumAsin = str;
            return this;
        }

        public final Builder albumName(@Nullable String str) {
            this.albumName = str;
            return this;
        }

        public final Builder artistAsin(@Nullable String str) {
            this.artistAsin = str;
            return this;
        }

        public final Builder artistName(@Nullable String str) {
            this.artistName = str;
            return this;
        }

        public final Builder trackAsin(@Nullable String str) {
            this.asin = str;
            return this;
        }

        public final Builder deviceId(@Nullable String str) {
            this.deviceId = str;
            return this;
        }

        public final Builder deviceType(@Nullable String str) {
            this.deviceType = str;
            return this;
        }

        public final Builder displayName(@Nullable String str) {
            this.displayName = str;
            return this;
        }

        public final Builder durationSeconds(@Nullable Integer num) {
            this.durationSeconds = num;
            return this;
        }

        public final Builder hasLyrics(@Nullable Boolean bool) {
            this.hasLyrics = bool;
            return this;
        }

        public final Builder image(@Nullable String str) {
            this.image = str;
            return this;
        }

        public final Builder isInLibrary(@Nullable Boolean bool) {
            this.isInLibrary = bool;
            return this;
        }

        public final Builder isInstantImport(@Nullable Boolean bool) {
            this.isInstantImport = bool;
            return this;
        }

        public final Builder isPrime(@Nullable Boolean bool) {
            this.isPrime = bool;
            return this;
        }

        public final Builder isPurchased(@Nullable Boolean bool) {
            this.isPurchased = bool;
            return this;
        }

        public final Builder isMusicSubscription(@Nullable Boolean bool) {
            this.isMusicSubscription = bool;
            return this;
        }

        public final Builder isUploaded(@Nullable Boolean bool) {
            this.isUploaded = bool;
            return this;
        }

        public final Builder marketPlaceId(@Nullable String str) {
            this.marketPlaceId = str;
            return this;
        }

        public final Builder objectId(@Nullable String str) {
            this.objectId = str;
            return this;
        }

        public final Builder timestamp(@Nullable Long l) {
            this.timestamp = l;
            return this;
        }

        public final Builder isExplicit(@Nullable Boolean bool) {
            this.isExplicit = bool;
            return this;
        }

        public final Builder uid(@Nullable String str) {
            this.uid = str;
            return this;
        }

        public final Builder isFree(@Nullable Boolean bool) {
            this.isFree = bool;
            return this;
        }

        public final Builder isFreeOnDemandPlayable(@Nullable Boolean bool) {
            this.isFreeOnDemandPlayable = bool;
            return this;
        }

        public final Builder isSonicRush(@Nullable Boolean bool) {
            this.isSonicRush = bool;
            return this;
        }

        public final Builder isSonicRushOnDemandPlayable(@Nullable Boolean bool) {
            this.isSonicRushOnDemandPlayable = bool;
            return this;
        }

        public final Builder contentEncodings(@Nullable List<ContentEncoding> list) {
            this.contentEncodings = list;
            return this;
        }

        public final RecentlyPlayedTrack build() {
            return new RecentlyPlayedTrack(this.albumAsin, this.albumName, this.artistAsin, this.artistName, this.asin, this.deviceId, this.deviceType, this.displayName, this.durationSeconds, this.hasLyrics, this.image, this.isInLibrary, this.isInstantImport, this.isPrime, this.isPurchased, this.isMusicSubscription, this.isUploaded, this.marketPlaceId, this.objectId, this.timestamp, this.isExplicit, this.uid, this.isFree, this.isFreeOnDemandPlayable, this.contentEncodings, this.isSonicRush, this.isSonicRushOnDemandPlayable);
        }
    }
}
