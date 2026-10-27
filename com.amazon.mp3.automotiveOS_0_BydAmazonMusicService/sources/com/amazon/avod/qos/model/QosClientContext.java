package com.amazon.avod.qos.model;

import com.amazon.avod.media.playback.VideoSpecification;
import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class QosClientContext {
    private final boolean mIsPlayback;
    private final boolean mIsTitleOwned;
    private final Map<String, String> mSessionContext;
    private final String mUserWatchSessionId;
    private final VideoSpecification mVideoSpec;

    private QosClientContext(String str, VideoSpecification videoSpecification, boolean z, boolean z2, Map<String, String> map) {
        this.mUserWatchSessionId = (String) Preconditions.checkNotNull(str);
        this.mVideoSpec = (VideoSpecification) Preconditions.checkNotNull(videoSpecification);
        this.mIsTitleOwned = z;
        this.mIsPlayback = z2;
        this.mSessionContext = (Map) Preconditions.checkNotNull(map);
    }

    @Nonnull
    public String getUserWatchSessionId() {
        return this.mUserWatchSessionId;
    }

    @Nonnull
    public VideoSpecification getVideoSpec() {
        return this.mVideoSpec;
    }

    @Nonnull
    public Map<String, String> getSessionContext() {
        return this.mSessionContext;
    }

    public String getUniqueVideoName() {
        if (!this.mVideoSpec.getMimeType().equals("video/aiv-asin")) {
            return null;
        }
        Locale locale = Locale.US;
        Object[] objArr = new Object[2];
        objArr[0] = this.mVideoSpec.getTitleId();
        objArr[1] = this.mVideoSpec.isTrailer() ? "-T" : "";
        return String.format(locale, "%s%s", objArr);
    }

    public boolean isTitleOwned() {
        return this.mIsTitleOwned;
    }

    public boolean isPlayback() {
        return this.mIsPlayback;
    }

    public static QosClientContext forPlayback(String str, VideoSpecification videoSpecification, Map<String, String> map) {
        return new QosClientContext(str, videoSpecification, !videoSpecification.isTrailer(), true, map);
    }

    public static QosClientContext forContentDownload(VideoSpecification videoSpecification, Map<String, String> map) {
        return new QosClientContext(UUID.randomUUID().toString(), videoSpecification, !videoSpecification.isTrailer(), false, map);
    }
}
