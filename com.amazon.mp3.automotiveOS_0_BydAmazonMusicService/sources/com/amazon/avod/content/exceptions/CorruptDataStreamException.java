package com.amazon.avod.content.exceptions;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.util.URLUtils;
import com.google.common.base.Preconditions;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class CorruptDataStreamException extends ContentException {
    private final SmoothStreamingURI mSsUri;

    public CorruptDataStreamException(MediaException mediaException, SmoothStreamingURI smoothStreamingURI, @Nullable String str) {
        this(null, mediaException, smoothStreamingURI, str);
    }

    public CorruptDataStreamException(String str, MediaException mediaException, SmoothStreamingURI smoothStreamingURI, @Nullable String str2) {
        super(ContentException.ContentError.CDN_ERROR, str, mediaException, URLUtils.nullWhenMalformed(smoothStreamingURI.getAbsoluteUrl(str2)));
        this.mSsUri = smoothStreamingURI;
    }

    @Override // java.lang.Throwable
    public synchronized MediaException getCause() {
        Throwable cause;
        cause = super.getCause();
        Preconditions.checkState(cause instanceof MediaException, "Can only have a MediaException here!");
        return (MediaException) cause;
    }

    public SmoothStreamingURI getURI() {
        return this.mSsUri;
    }
}
