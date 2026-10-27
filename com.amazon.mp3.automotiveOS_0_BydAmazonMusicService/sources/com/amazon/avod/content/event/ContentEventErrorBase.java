package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.google.common.base.Preconditions;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ContentEventErrorBase extends ContentEventBase {
    private final ContentException mContentException;
    private final ContentUrl mCurrentUrl;
    private final SmoothStreamingURI mSmoothStreamingUri;

    @Nullable
    private final Map<String, List<String>> mUnformattedHeaders;

    public abstract boolean isRetriable();

    protected ContentEventErrorBase(PlayableContent playableContent, ContentSessionType contentSessionType, ContentException contentException, @Nullable ContentUrl contentUrl, @Nullable SmoothStreamingURI smoothStreamingURI, @Nullable Map<String, List<String>> map) {
        super(playableContent, contentSessionType);
        this.mContentException = (ContentException) Preconditions.checkNotNull(contentException);
        this.mCurrentUrl = contentUrl;
        this.mSmoothStreamingUri = smoothStreamingURI;
        this.mUnformattedHeaders = map;
    }

    @Nonnull
    public ContentException getException() {
        return this.mContentException;
    }

    @Nonnull
    public ContentException.ContentError getErrorCode() {
        return this.mContentException.getErrorCode();
    }

    @Nullable
    public ContentUrl getUrl() {
        return this.mCurrentUrl;
    }

    @Nullable
    public SmoothStreamingURI getSmoothStreamingUri() {
        return this.mSmoothStreamingUri;
    }

    @Nullable
    public Map<String, List<String>> getUnformattedHeaders() {
        return this.mUnformattedHeaders;
    }
}
