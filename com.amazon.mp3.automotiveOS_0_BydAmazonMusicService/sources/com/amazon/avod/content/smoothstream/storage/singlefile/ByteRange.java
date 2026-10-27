package com.amazon.avod.content.smoothstream.storage.singlefile;

import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.media.downloadservice.DownloadRequest;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.CharMatcher;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.firebase.perf.plugin.util.AsmString;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class ByteRange {

    @JsonProperty("end")
    private final long mEnd;

    @JsonProperty(AsmString.METHOD_FIREBASE_PERF_TRACE_START)
    private final long mStart;

    @JsonCreator
    public ByteRange(@JsonProperty(AsmString.METHOD_FIREBASE_PERF_TRACE_START) @Nonnegative long j, @JsonProperty("end") @Nonnegative long j2) {
        Preconditions.checkArgument(j >= 0, "start should be non-negative");
        Preconditions.checkArgument(j2 >= 0, "end should be non-negative");
        Preconditions.checkArgument(j <= j2, "end should be greater or equal to start");
        this.mStart = j;
        this.mEnd = j2;
    }

    public static ByteRange fromString(@Nonnull String str) {
        Preconditions.checkNotNull(str, "byteRange");
        String[] strArrSplit = str.split("-");
        Preconditions.checkArgument(strArrSplit.length == 2, "byte range should contain only one '-' character");
        return new ByteRange(Long.parseLong(CharMatcher.digit().retainFrom(strArrSplit[0])), Long.parseLong(CharMatcher.digit().retainFrom(strArrSplit[1])));
    }

    public static ByteRange fromSmoothStreamingURI(@Nonnull SmoothStreamingURI smoothStreamingURI) {
        Preconditions.checkNotNull(smoothStreamingURI, "ssu");
        return fromString(smoothStreamingURI.getDownloadHeaders().get("Range"));
    }

    public static ByteRange fromDownloadRequest(@Nonnull DownloadRequest downloadRequest) {
        Preconditions.checkNotNull(downloadRequest, "downloadRequest");
        return fromString(downloadRequest.getHeaders().get("Range"));
    }

    public long getSizeBytes() {
        return (this.mEnd - this.mStart) + 1;
    }

    public long getStart() {
        return this.mStart;
    }

    public long getEnd() {
        return this.mEnd;
    }

    public boolean contains(@Nonnull ByteRange byteRange) {
        Preconditions.checkNotNull(byteRange, "range");
        return this.mEnd >= byteRange.getEnd() && this.mStart <= byteRange.getStart();
    }

    public boolean intersectOrTouching(@Nonnull ByteRange byteRange) {
        Preconditions.checkNotNull(byteRange, "range");
        return this.mStart <= byteRange.getEnd() + 1 && byteRange.getStart() <= this.mEnd + 1;
    }

    public boolean intersect(@Nonnull ByteRange byteRange) {
        Preconditions.checkNotNull(byteRange, "range");
        return this.mStart <= byteRange.getEnd() && byteRange.getStart() <= this.mEnd;
    }

    public ByteRange merge(@Nonnull ByteRange byteRange) {
        Preconditions.checkArgument(intersectOrTouching(byteRange), "range");
        return new ByteRange(Math.min(this.mStart, byteRange.getStart()), Math.max(this.mEnd, byteRange.getEnd()));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ByteRange byteRange = (ByteRange) obj;
        return this.mStart == byteRange.mStart && this.mEnd == byteRange.mEnd;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.mStart), Long.valueOf(this.mEnd));
    }
}
