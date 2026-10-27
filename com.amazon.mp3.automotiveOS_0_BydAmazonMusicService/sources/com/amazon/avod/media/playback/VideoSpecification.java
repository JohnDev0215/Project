package com.amazon.avod.media.playback;

import com.amazon.atvplaybackresource.AuditPing;
import com.amazon.atvplaybackresource.PlaybackSettings;
import com.amazon.atvplaybackresource.SyeCdn;
import com.amazon.atvplaybackresource.SyeUrlResponse;
import com.amazon.avod.content.urlvending.EPrivacyConsentData;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.playback.util.VideoRegion;
import com.amazon.video.sdk.player.PlaybackEnvelope;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class VideoSpecification {

    @Nullable
    private final String mAdIdentifier;
    private final VideoAttributes mAttributes;
    private final AudioFormat mAudioFormat;

    @Nullable
    private final String mAudioLanguage;

    @Nonnull
    private final ImmutableList<String> mAudioTrackIds;

    @Nullable
    private final ImmutableList<AuditPing> mAuditPingUrls;
    private final String mContentId;
    private final ContentType mContentType;
    private final boolean mDoNotUseStoredAssets;
    private final TimeSpan mDuration;

    @Nonnull
    private final EPrivacyConsentData mEPrivacyConsent;
    private final boolean mIsEmbedded;
    private final long mLiveLookBackMillis;
    private final MediaQuality mMediaQuality;
    private final String mMimeType;

    @Nullable
    private final PlaybackEnvelope mPlaybackEnvelope;
    private final PlaybackSettings mPlaybackSettings;

    @Nullable
    private final String mPlaybackToken;

    @Nonnull
    private final ImmutableList<String> mPreferredAudioTrackIds;

    @Nullable
    private final String mPrimaryAudioTrackId;
    private final String mRendererSchemeTypeString;
    private final String mReportingTag;
    private final boolean mShowAds;
    private final TimeSpan mStartTime;

    @Nullable
    private final String mStreamIntent;
    private final SyeUrlResponse mSyeUrls;
    private final String mTitleId;
    private final String mUniqueIdentifier;
    private final String mUrl;
    private final String mVCID;
    private final VideoRegion mVideoRegion;

    /* synthetic */ VideoSpecification(String str, String str2, String str3, String str4, TimeSpan timeSpan, AudioFormat audioFormat, ImmutableList immutableList, ImmutableList immutableList2, String str5, MediaQuality mediaQuality, VideoAttributes videoAttributes, TimeSpan timeSpan2, ContentType contentType, String str6, VideoRegion videoRegion, SyeUrlResponse syeUrlResponse, PlaybackSettings playbackSettings, String str7, long j, String str8, boolean z, String str9, String str10, ImmutableList immutableList3, String str11, boolean z2, PlaybackEnvelope playbackEnvelope, boolean z3, EPrivacyConsentData ePrivacyConsentData, String str12, AnonymousClass1 anonymousClass1) {
        this(str, str2, str3, str4, timeSpan, audioFormat, immutableList, immutableList2, str5, mediaQuality, videoAttributes, timeSpan2, contentType, str6, videoRegion, syeUrlResponse, playbackSettings, str7, j, str8, z, str9, str10, immutableList3, str11, z2, playbackEnvelope, z3, ePrivacyConsentData, str12);
    }

    private VideoSpecification(String str, String str2, String str3, String str4, TimeSpan timeSpan, @Nonnull AudioFormat audioFormat, @Nonnull ImmutableList<String> immutableList, @Nonnull ImmutableList<String> immutableList2, @Nullable String str5, MediaQuality mediaQuality, VideoAttributes videoAttributes, @Nullable TimeSpan timeSpan2, ContentType contentType, String str6, @Nullable VideoRegion videoRegion, @Nullable SyeUrlResponse syeUrlResponse, @Nullable PlaybackSettings playbackSettings, @Nullable String str7, long j, @Nullable String str8, boolean z, @Nullable String str9, @Nullable String str10, @Nullable ImmutableList<AuditPing> immutableList3, @Nullable String str11, boolean z2, @Nullable PlaybackEnvelope playbackEnvelope, boolean z3, @Nullable EPrivacyConsentData ePrivacyConsentData, @Nullable String str12) {
        this.mContentType = contentType;
        this.mUrl = (String) Preconditions.checkNotNull(str, "%s must not be null", ImagesContract.URL);
        String str13 = (String) Preconditions.checkNotNull(str2, "%s must not be null", "titleId");
        this.mTitleId = str13;
        this.mMimeType = (String) Preconditions.checkNotNull(str3, "%s must not be null", "mimeType");
        this.mStartTime = (TimeSpan) Preconditions.checkNotNull(timeSpan, "%s must not be null", "startTime");
        this.mAudioFormat = (AudioFormat) Preconditions.checkNotNull(audioFormat, "%s must not be null", "audioFormat");
        this.mMediaQuality = (MediaQuality) Preconditions.checkNotNull(mediaQuality, "%s must not be null", "quality");
        this.mAudioTrackIds = (ImmutableList) Preconditions.checkNotNull(immutableList, "%s must not be null", "audioTrackIds");
        this.mPreferredAudioTrackIds = (ImmutableList) Preconditions.checkNotNull(immutableList2, "%s must not be null", "preferredAudioTrackIds");
        this.mPrimaryAudioTrackId = str5;
        this.mReportingTag = str4;
        this.mAttributes = videoAttributes != null ? videoAttributes : VideoAttributes.newBuilder().build();
        this.mDuration = timeSpan2;
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$media$playback$ContentType[contentType.ordinal()];
        this.mUniqueIdentifier = String.format(Locale.US, "%s%s", str13, i != 1 ? i != 2 ? i != 3 ? "" : "-E" : "-L" : "-T");
        this.mAudioLanguage = str6;
        this.mVideoRegion = videoRegion;
        this.mSyeUrls = syeUrlResponse;
        this.mPlaybackSettings = playbackSettings;
        this.mVCID = str7;
        this.mLiveLookBackMillis = j;
        this.mRendererSchemeTypeString = str8;
        this.mIsEmbedded = z;
        this.mPlaybackToken = str9;
        this.mAdIdentifier = str10;
        this.mAuditPingUrls = immutableList3;
        this.mContentId = str11;
        this.mDoNotUseStoredAssets = z2;
        this.mPlaybackEnvelope = playbackEnvelope;
        this.mShowAds = z3;
        this.mEPrivacyConsent = ePrivacyConsentData == null ? EPrivacyConsentData.EMPTY : ePrivacyConsentData;
        this.mStreamIntent = str12;
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.playback.VideoSpecification$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$playback$ContentType;

        static {
            int[] iArr = new int[ContentType.values().length];
            $SwitchMap$com$amazon$avod$media$playback$ContentType = iArr;
            try {
                iArr[ContentType.Trailer.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$ContentType[ContentType.LiveStreaming.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$ContentType[ContentType.External.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getTitleId() {
        return this.mTitleId;
    }

    public String getMimeType() {
        return this.mMimeType;
    }

    public AudioFormat getAudioFormat() {
        return this.mAudioFormat;
    }

    @Nonnull
    public ImmutableList<String> getAudioTrackIds() {
        return this.mAudioTrackIds;
    }

    @Nonnull
    public ImmutableList<String> getPreferredAudioTrackIds() {
        return this.mPreferredAudioTrackIds;
    }

    @Nullable
    public String getPrimaryAudioTrackId() {
        return this.mPrimaryAudioTrackId;
    }

    @Nullable
    public String getAudioLanguage() {
        return this.mAudioLanguage;
    }

    public TimeSpan getStartTime() {
        return this.mStartTime;
    }

    public MediaQuality getMediaQuality() {
        return this.mMediaQuality;
    }

    @Nullable
    public VideoRegion getVideoRegion() {
        return this.mVideoRegion;
    }

    @Nonnull
    public VideoAttributes getAttributes() {
        return this.mAttributes;
    }

    public boolean isTrailer() {
        return ContentType.Trailer.equals(this.mContentType);
    }

    @Nullable
    public String getReportingTag() {
        return this.mReportingTag;
    }

    @Nullable
    public TimeSpan getDuration() {
        return this.mDuration;
    }

    public boolean isLiveStream() {
        return ContentType.isLive(this.mContentType);
    }

    public String getUniqueIdentifier() {
        return this.mUniqueIdentifier;
    }

    @Nonnull
    public ContentType getContentType() {
        return this.mContentType;
    }

    @Nonnull
    public List<SyeCdn> getSyeUrls() {
        ImmutableList<SyeCdn> immutableList;
        SyeUrlResponse syeUrlResponse = this.mSyeUrls;
        return (syeUrlResponse == null || (immutableList = syeUrlResponse.listOfCdns) == null) ? Collections.emptyList() : immutableList;
    }

    @Nullable
    public PlaybackSettings getPlaybackSettings() {
        return this.mPlaybackSettings;
    }

    @Nullable
    public SyeUrlResponse getSyeResponse() {
        return this.mSyeUrls;
    }

    @Nullable
    public String getVCID() {
        return this.mVCID;
    }

    public long getLiveLookBackMillis() {
        return this.mLiveLookBackMillis;
    }

    @Nullable
    public String getRendererSchemeTypeString() {
        return this.mRendererSchemeTypeString;
    }

    public boolean isEmbedded() {
        return this.mIsEmbedded;
    }

    @Nullable
    public String getPlaybackToken() {
        return this.mPlaybackToken;
    }

    @Nullable
    public String getAdIdentifier() {
        return this.mAdIdentifier;
    }

    @Nonnull
    public ImmutableList<AuditPing> getAuditPingUrls() {
        ImmutableList<AuditPing> immutableList = this.mAuditPingUrls;
        return immutableList != null ? immutableList : ImmutableList.of();
    }

    @Nullable
    public String getContentId() {
        return this.mContentId;
    }

    @Nullable
    public PlaybackEnvelope getPlaybackEnvelope() {
        return this.mPlaybackEnvelope;
    }

    @Nonnull
    public EPrivacyConsentData getEPrivacyConsent() {
        return this.mEPrivacyConsent;
    }

    public boolean doNotUseStoredAssets() {
        return this.mDoNotUseStoredAssets;
    }

    public boolean shouldShowAds() {
        return this.mShowAds;
    }

    @Nullable
    public String getStreamIntent() {
        return this.mStreamIntent;
    }

    public VideoSpecification trimToFirstAudioTrack() {
        ImmutableList<String> immutableListOf;
        Builder builderNewBuilder = newBuilder(this);
        if (getAudioTrackIds().size() == 0) {
            immutableListOf = ImmutableList.of();
        } else {
            immutableListOf = ImmutableList.of(getAudioTrackIds().get(0));
        }
        return builderNewBuilder.setAudioTrackIds(immutableListOf).build();
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("titleId", getTitleId()).add("contentType", getContentType()).add("mimeType", getMimeType()).add("isTrailer", isTrailer()).add("reportingMessage", getReportingTag()).add("audioFormat", getAudioFormat()).add("audioTrackIds", getAudioTrackIds()).add("primaryAudioTrackId", getPrimaryAudioTrackId()).add("preferredAudioTrackIds", getPreferredAudioTrackIds()).add("quality", getMediaQuality()).add("startTime", getStartTime()).add(ImagesContract.URL, getUrl()).add("duration", getDuration()).add("attributes", this.mAttributes).add("isLiveStream", isLiveStream()).add("audioLanguage", getAudioLanguage()).add("videoRegion", getVideoRegion()).add("playbackToken", getPlaybackToken()).add("auditPingUrls", getAuditPingUrls()).add("duration", getDuration()).add("liveLookBackMillis", getLiveLookBackMillis()).add("contentId", getContentId()).add("doNotUseStoredAssets", doNotUseStoredAssets()).add("showAds", shouldShowAds()).add("ePrivacyConsent", getEPrivacyConsent()).add("streamIntent", getStreamIntent()).toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VideoSpecification)) {
            return false;
        }
        VideoSpecification videoSpecification = (VideoSpecification) obj;
        return equalsIgnoringStartTime(videoSpecification) && Objects.equal(getStartTime(), videoSpecification.getStartTime());
    }

    public int hashCode() {
        return Objects.hashCode(getTitleId(), getMimeType(), Boolean.valueOf(isTrailer()), getReportingTag(), getAudioFormat(), getMediaQuality(), getStartTime(), getUrl(), getDuration(), getAttributes(), Boolean.valueOf(isLiveStream()), getVideoRegion(), getPlaybackToken(), Boolean.valueOf(doNotUseStoredAssets()));
    }

    public boolean equalsIgnoringStartTimePreferContentId(@Nonnull VideoSpecification videoSpecification) {
        boolean zEqual;
        if (this.mContentId != null && videoSpecification.getContentId() != null) {
            zEqual = Objects.equal(getContentId(), videoSpecification.getContentId());
        } else {
            zEqual = Objects.equal(getTitleId(), videoSpecification.getTitleId());
        }
        if (zEqual && Objects.equal(getMimeType(), videoSpecification.getMimeType()) && Objects.equal(Boolean.valueOf(isTrailer()), Boolean.valueOf(videoSpecification.isTrailer())) && Objects.equal(getReportingTag(), videoSpecification.getReportingTag()) && Objects.equal(getAudioFormat(), videoSpecification.getAudioFormat()) && getAudioTrackIds().containsAll(videoSpecification.getAudioTrackIds()) && Objects.equal(getMediaQuality(), videoSpecification.getMediaQuality()) && Objects.equal(getUrl(), videoSpecification.getUrl()) && Objects.equal(getDuration(), videoSpecification.getDuration()) && Objects.equal(getAttributes(), videoSpecification.getAttributes()) && Objects.equal(Boolean.valueOf(isLiveStream()), Boolean.valueOf(videoSpecification.isLiveStream())) && Objects.equal(getVideoRegion(), videoSpecification.getVideoRegion()) && Objects.equal(getPlaybackToken(), videoSpecification.getPlaybackToken())) {
            if (Objects.equal(Boolean.valueOf(doNotUseStoredAssets()), Boolean.valueOf(videoSpecification.doNotUseStoredAssets() && Objects.equal(getPlaybackEnvelope(), videoSpecification.getPlaybackEnvelope()) && Objects.equal(getEPrivacyConsent(), videoSpecification.getEPrivacyConsent()) && Objects.equal(Boolean.valueOf(isEmbedded()), Boolean.valueOf(videoSpecification.isEmbedded()))))) {
                return true;
            }
        }
        return false;
    }

    public boolean equalsIgnoringStartTime(@Nonnull VideoSpecification videoSpecification) {
        return Objects.equal(getTitleId(), videoSpecification.getTitleId()) && Objects.equal(getMimeType(), videoSpecification.getMimeType()) && Objects.equal(Boolean.valueOf(isTrailer()), Boolean.valueOf(videoSpecification.isTrailer())) && Objects.equal(getReportingTag(), videoSpecification.getReportingTag()) && Objects.equal(getAudioFormat(), videoSpecification.getAudioFormat()) && getAudioTrackIds().containsAll(videoSpecification.getAudioTrackIds()) && Objects.equal(getMediaQuality(), videoSpecification.getMediaQuality()) && Objects.equal(getUrl(), videoSpecification.getUrl()) && Objects.equal(getDuration(), videoSpecification.getDuration()) && Objects.equal(getAttributes(), videoSpecification.getAttributes()) && Objects.equal(Boolean.valueOf(isLiveStream()), Boolean.valueOf(videoSpecification.isLiveStream())) && Objects.equal(getVideoRegion(), videoSpecification.getVideoRegion()) && Objects.equal(getPlaybackToken(), videoSpecification.getPlaybackToken()) && Objects.equal(getPlaybackEnvelope(), videoSpecification.getPlaybackEnvelope()) && Objects.equal(getEPrivacyConsent(), videoSpecification.getEPrivacyConsent()) && Objects.equal(Boolean.valueOf(isEmbedded()), Boolean.valueOf(videoSpecification.isEmbedded()));
    }

    public static Builder newBuilder() {
        return new Builder((AnonymousClass1) null);
    }

    public static Builder newBuilder(@Nonnull VideoSpecification videoSpecification) {
        return new Builder(videoSpecification);
    }

    public static final class Builder {

        @Nullable
        private String mAdIdentifier;
        private VideoAttributes mAttributes;
        private AudioFormat mAudioFormat;
        private String mAudioLanguage;
        private ImmutableList<String> mAudioTrackIds;

        @Nullable
        private ImmutableList<AuditPing> mAuditPingUrls;

        @Nullable
        private String mContentId;
        private ContentType mContentType;
        private boolean mDoNotUseStoredAssets;
        private TimeSpan mDuration;

        @Nullable
        private EPrivacyConsentData mEPrivacyConsent;
        private boolean mIsEmbedded;
        private long mLiveLookBackMillis;
        private MediaQuality mMediaQuality;
        private String mMimeType;

        @Nullable
        private PlaybackEnvelope mPlaybackEnvelope;

        @Nullable
        private PlaybackSettings mPlaybackSettings;

        @Nullable
        private String mPlaybackToken;
        private ImmutableList<String> mPreferredAudioTrackIds;
        private String mPrimaryAudioTrackId;
        private String mRendererSchemeTypeString;
        private String mReportingTag;
        private boolean mShowAds;
        private TimeSpan mStartTime;
        private String mStreamIntent;

        @Nullable
        private SyeUrlResponse mSyeUrls;
        private String mTitleId;
        private String mUrl;
        private String mVCID;

        @Nullable
        private VideoRegion mVideoRegion;

        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            this.mUrl = null;
            this.mTitleId = null;
            this.mMimeType = "video/aiv-asin";
            this.mContentType = ContentType.Feature;
            this.mStartTime = TimeSpan.ZERO;
            this.mAudioLanguage = null;
            this.mVCID = null;
            this.mLiveLookBackMillis = 0L;
            this.mRendererSchemeTypeString = null;
            this.mIsEmbedded = false;
            this.mShowAds = true;
            this.mStreamIntent = null;
            this.mAudioTrackIds = ImmutableList.of();
            this.mPreferredAudioTrackIds = ImmutableList.of();
        }

        public Builder(@Nonnull VideoSpecification videoSpecification) {
            this.mUrl = null;
            this.mTitleId = null;
            this.mMimeType = "video/aiv-asin";
            this.mContentType = ContentType.Feature;
            this.mStartTime = TimeSpan.ZERO;
            this.mAudioLanguage = null;
            this.mVCID = null;
            this.mLiveLookBackMillis = 0L;
            this.mRendererSchemeTypeString = null;
            this.mIsEmbedded = false;
            this.mShowAds = true;
            this.mStreamIntent = null;
            setUrl(videoSpecification.getUrl());
            setTitleId(videoSpecification.getTitleId());
            setMimeType(videoSpecification.getMimeType());
            setReportingTag(videoSpecification.getReportingTag());
            setStartTime(videoSpecification.getStartTime());
            setAudioFormat(videoSpecification.getAudioFormat());
            setAudioTrackIds(videoSpecification.getAudioTrackIds());
            setPreferredAudioTrackIds(videoSpecification.getPreferredAudioTrackIds());
            setPrimaryAudioTrackId(videoSpecification.getPrimaryAudioTrackId());
            setMediaQuality(videoSpecification.getMediaQuality());
            setAttributes(videoSpecification.getAttributes());
            setDuration(videoSpecification.getDuration());
            setContentType(videoSpecification.getContentType());
            setAudioLanguage(videoSpecification.getAudioLanguage());
            setVideoRegion(videoSpecification.getVideoRegion());
            setLiveLookBackMillis(videoSpecification.getLiveLookBackMillis());
            setRendererSchemeType(videoSpecification.getRendererSchemeTypeString());
            isEmbedded(videoSpecification.isEmbedded());
            setPlaybackToken(videoSpecification.getPlaybackToken());
            setContentId(videoSpecification.getContentId());
            setSyeUrls(videoSpecification.getSyeResponse());
            setPlaybackSettings(videoSpecification.getPlaybackSettings());
            setVCID(videoSpecification.getVCID());
            setDoNotUseStoredAssets(videoSpecification.doNotUseStoredAssets());
            setPlaybackEnvelope(videoSpecification.getPlaybackEnvelope());
            setShowAds(videoSpecification.shouldShowAds());
            setEPrivacyConsent(videoSpecification.getEPrivacyConsent());
            setStreamIntent(videoSpecification.getStreamIntent());
        }

        public Builder setUrl(String str) {
            this.mUrl = str;
            return this;
        }

        public Builder setTitleId(String str) {
            this.mTitleId = str;
            return this;
        }

        public Builder setMimeType(String str) {
            this.mMimeType = str;
            return this;
        }

        public Builder setContentType(ContentType contentType) {
            this.mContentType = contentType;
            return this;
        }

        public Builder setReportingTag(String str) {
            this.mReportingTag = str;
            return this;
        }

        public Builder setStartTime(TimeSpan timeSpan) {
            this.mStartTime = timeSpan;
            return this;
        }

        public Builder setAudioFormat(AudioFormat audioFormat) {
            this.mAudioFormat = audioFormat;
            return this;
        }

        @Nonnull
        public Builder setAudioTrackIds(@Nonnull ImmutableList<String> immutableList) {
            this.mAudioTrackIds = (ImmutableList) Preconditions.checkNotNull(immutableList);
            return this;
        }

        @Nonnull
        public Builder setPreferredAudioTrackIds(@Nonnull ImmutableList<String> immutableList) {
            this.mPreferredAudioTrackIds = (ImmutableList) Preconditions.checkNotNull(immutableList);
            return this;
        }

        @Nonnull
        public Builder setPrimaryAudioTrackId(@Nullable String str) {
            this.mPrimaryAudioTrackId = str;
            return this;
        }

        public Builder setMediaQuality(MediaQuality mediaQuality) {
            this.mMediaQuality = mediaQuality;
            return this;
        }

        public Builder setAttributes(VideoAttributes videoAttributes) {
            this.mAttributes = videoAttributes;
            return this;
        }

        public Builder setDuration(TimeSpan timeSpan) {
            this.mDuration = timeSpan;
            return this;
        }

        public Builder setAudioLanguage(@Nullable String str) {
            this.mAudioLanguage = str;
            return this;
        }

        public Builder setSyeUrls(@Nullable SyeUrlResponse syeUrlResponse) {
            this.mSyeUrls = syeUrlResponse;
            return this;
        }

        public Builder setPlaybackSettings(@Nullable PlaybackSettings playbackSettings) {
            this.mPlaybackSettings = playbackSettings;
            return this;
        }

        public Builder setVideoRegion(@Nullable VideoRegion videoRegion) {
            this.mVideoRegion = videoRegion;
            return this;
        }

        public Builder setAdIdentifier(@Nullable String str) {
            this.mAdIdentifier = str;
            return this;
        }

        public Builder setVCID(String str) {
            this.mVCID = str;
            return this;
        }

        public Builder setLiveLookBackMillis(long j) {
            this.mLiveLookBackMillis = j;
            return this;
        }

        public Builder setRendererSchemeType(@Nullable String str) {
            this.mRendererSchemeTypeString = str;
            return this;
        }

        public Builder isEmbedded(boolean z) {
            this.mIsEmbedded = z;
            return this;
        }

        public Builder setPlaybackToken(@Nullable String str) {
            this.mPlaybackToken = str;
            return this;
        }

        @Nonnull
        public Builder setContentId(@Nullable String str) {
            this.mContentId = str;
            return this;
        }

        @Nonnull
        public Builder setDoNotUseStoredAssets(boolean z) {
            this.mDoNotUseStoredAssets = z;
            return this;
        }

        @Nonnull
        public Builder setPlaybackEnvelope(@Nullable PlaybackEnvelope playbackEnvelope) {
            this.mPlaybackEnvelope = playbackEnvelope;
            return this;
        }

        @Nonnull
        public Builder setShowAds(boolean z) {
            this.mShowAds = z;
            return this;
        }

        public Builder setEPrivacyConsent(@Nonnull EPrivacyConsentData ePrivacyConsentData) {
            this.mEPrivacyConsent = (EPrivacyConsentData) Preconditions.checkNotNull(ePrivacyConsentData, "ePrivacyConsent");
            return this;
        }

        @Nonnull
        public Builder setStreamIntent(@Nullable String str) {
            this.mStreamIntent = str;
            return this;
        }

        public VideoSpecification build() {
            Preconditions.checkArgument((this.mUrl == null && this.mTitleId == null) ? false : true);
            if (this.mUrl == null) {
                this.mUrl = "";
            } else if (this.mTitleId == null) {
                this.mTitleId = "";
            }
            return new VideoSpecification(this.mUrl, this.mTitleId, this.mMimeType, this.mReportingTag, this.mStartTime, this.mAudioFormat, this.mAudioTrackIds, this.mPreferredAudioTrackIds, this.mPrimaryAudioTrackId, this.mMediaQuality, this.mAttributes, this.mDuration, this.mContentType, this.mAudioLanguage, this.mVideoRegion, this.mSyeUrls, this.mPlaybackSettings, this.mVCID, this.mLiveLookBackMillis, this.mRendererSchemeTypeString, this.mIsEmbedded, this.mPlaybackToken, this.mAdIdentifier, this.mAuditPingUrls, this.mContentId, this.mDoNotUseStoredAssets, this.mPlaybackEnvelope, this.mShowAds, this.mEPrivacyConsent, this.mStreamIntent, null);
        }
    }
}
