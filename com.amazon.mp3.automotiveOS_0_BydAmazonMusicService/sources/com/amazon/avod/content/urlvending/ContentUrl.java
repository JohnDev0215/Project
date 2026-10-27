package com.amazon.avod.content.urlvending;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ContentUrl {
    private String adsTrackingEndpoint;
    private String cdnName;
    private ContiguityType contiguityType;
    private String drm;
    private long durationMillis;
    private FragmentRepresentation fragmentRepresentation;
    private String livePlaybackStreamId;
    private String origin;
    private String sessionId;
    private long startUtcMillis;
    private String streamingTechnology;
    private SubtitleRepresentation subtitleRepresentation;
    private String url;

    public ContentUrl(String str, String str2) {
        this(str, str2, null, null);
    }

    public ContentUrl(String str, String str2, String str3) {
        this(str, str2, str3, null, null, null);
    }

    public ContentUrl(String str, String str2, String str3, String str4) {
        this(str, str2, str3, null, null, str4);
    }

    public ContentUrl(String str, String str2, String str3, String str4, String str5, String str6) {
        this(str, str2, str3, str4, str5, null, str6);
    }

    public ContentUrl(String str, String str2, String str3, String str4, String str5, @Nullable String str6, String str7) {
        this(str, str2, str3, str4, str5, str6, null, null, null, null, 0L, -1L, str7);
    }

    public ContentUrl(String str, String str2, String str3, String str4, String str5, @Nullable String str6, @Nullable ContiguityType contiguityType, @Nullable FragmentRepresentation fragmentRepresentation, @Nullable SubtitleRepresentation subtitleRepresentation, @Nullable String str7, long j, long j2, @Nullable String str8) {
        this.url = str;
        this.sessionId = str2;
        this.cdnName = str3;
        this.drm = str4;
        this.streamingTechnology = str5;
        this.adsTrackingEndpoint = str6;
        this.contiguityType = contiguityType;
        this.fragmentRepresentation = fragmentRepresentation;
        this.subtitleRepresentation = subtitleRepresentation;
        this.livePlaybackStreamId = str7;
        this.durationMillis = j;
        this.startUtcMillis = j2;
        this.origin = str8;
    }

    public ContentUrl(@Nonnull String str, @Nonnull ContentUrl contentUrl) {
        this(str, contentUrl.getSessionId(), contentUrl.getCdnName(), contentUrl.getDrm(), contentUrl.getStreamingTechnology(), contentUrl.getAdsTrackingEndpoint(), contentUrl.getContiguityType(), contentUrl.getFragmentRepresentation(), contentUrl.getSubtitleRepresentation(), contentUrl.getLivePlaybackStreamId(), contentUrl.getDurationMillis(), contentUrl.getStartUtcMillis(), contentUrl.getOrigin());
    }

    public ContentUrl() {
    }

    public String getUrl() {
        return this.url;
    }

    @Nullable
    public String getSessionId() {
        return this.sessionId;
    }

    public String getCdnName() {
        return this.cdnName;
    }

    public static String stripUrlParams(String str) {
        int iLastIndexOf = str.lastIndexOf(63);
        return iLastIndexOf >= 0 ? str.substring(0, iLastIndexOf) : str;
    }

    public static boolean isDashUrl(String str) {
        return stripUrlParams(str).toLowerCase(Locale.US).endsWith(".mpd");
    }

    public static boolean isDashUrl(ContentUrl contentUrl) {
        return isDashUrl(contentUrl.getUrl());
    }

    public String getDrm() {
        return this.drm;
    }

    public String getStreamingTechnology() {
        return this.streamingTechnology;
    }

    @Nullable
    public String getAdsTrackingEndpoint() {
        return this.adsTrackingEndpoint;
    }

    @Nullable
    public ContiguityType getContiguityType() {
        return this.contiguityType;
    }

    @Nullable
    public FragmentRepresentation getFragmentRepresentation() {
        return this.fragmentRepresentation;
    }

    @Nullable
    public SubtitleRepresentation getSubtitleRepresentation() {
        return this.subtitleRepresentation;
    }

    @Nullable
    public String getLivePlaybackStreamId() {
        return this.livePlaybackStreamId;
    }

    public String toString() {
        return String.format(Locale.US, "(Cdn:%s Url:%s)", getCdnName(), getUrl());
    }

    @JsonIgnore
    public boolean isTnf() {
        return getAdsTrackingEndpoint() != null;
    }

    public long getDurationMillis() {
        return this.durationMillis;
    }

    public long getStartUtcMillis() {
        return this.startUtcMillis;
    }

    @Nullable
    public String getOrigin() {
        return this.origin;
    }
}
