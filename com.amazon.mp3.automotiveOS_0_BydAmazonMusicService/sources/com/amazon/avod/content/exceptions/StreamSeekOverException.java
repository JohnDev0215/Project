package com.amazon.avod.content.exceptions;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.media.framework.error.MediaException;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class StreamSeekOverException extends ContentException {
    private final long mSeekFromPositionInNanoseconds;
    private final long mSeekToPositionInNanoseconds;
    private final StreamType mStreamType;

    public StreamSeekOverException(@Nullable String str, @Nonnull ContentException.ContentError contentError, @Nonnull MediaException mediaException, @Nonnull StreamType streamType, long j, long j2) {
        super((ContentException.ContentError) Preconditions.checkNotNull(contentError, "contentError"), str, (Throwable) Preconditions.checkNotNull(mediaException, "cause"));
        this.mStreamType = (StreamType) Preconditions.checkNotNull(streamType, "streamType");
        this.mSeekFromPositionInNanoseconds = j;
        this.mSeekToPositionInNanoseconds = j2;
    }

    @Override // java.lang.Throwable
    public MediaException getCause() {
        Throwable cause = super.getCause();
        Preconditions.checkState(cause instanceof MediaException, "Can only have a MediaException here!");
        return (MediaException) cause;
    }

    @Nonnull
    public StreamType getStreamType() {
        return this.mStreamType;
    }

    public boolean shouldHandleImmediately() {
        return ContentException.ContentError.DISK_ERROR.equals(getErrorCode()) || ContentException.ContentError.VIDEOSTREAM_BOUNDARY_NOT_IN_RANGE.equals(getErrorCode());
    }

    public long getSeekFromPositionInNanoseconds() {
        return this.mSeekFromPositionInNanoseconds;
    }

    public long getSeekToPositionInNanoseconds() {
        return this.mSeekToPositionInNanoseconds;
    }
}
