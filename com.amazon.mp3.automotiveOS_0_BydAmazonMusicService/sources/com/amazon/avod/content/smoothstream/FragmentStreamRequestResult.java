package com.amazon.avod.content.smoothstream;

import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentStreamRequestResult {
    private final ByteBuffer mByteBuffer;
    private final SmoothStreamingURI mRequestedURI;
    private final SmoothStreamingURI mResponsedURI;

    public FragmentStreamRequestResult(@Nonnull SmoothStreamingURI smoothStreamingURI, @Nonnull SmoothStreamingURI smoothStreamingURI2, @Nonnull ByteBuffer byteBuffer) {
        this.mRequestedURI = (SmoothStreamingURI) Preconditions.checkNotNull(smoothStreamingURI, "requestedURI");
        this.mResponsedURI = (SmoothStreamingURI) Preconditions.checkNotNull(smoothStreamingURI2, "responseURI");
        this.mByteBuffer = (ByteBuffer) Preconditions.checkNotNull(byteBuffer, "byteBuffer");
    }

    public SmoothStreamingURI getRequestedURI() {
        return this.mRequestedURI;
    }

    public SmoothStreamingURI getResponseURI() {
        return this.mResponsedURI;
    }

    public ByteBuffer getByteBuffer() {
        return this.mByteBuffer;
    }

    public int getFragmentLength() {
        return this.mByteBuffer.capacity();
    }
}
