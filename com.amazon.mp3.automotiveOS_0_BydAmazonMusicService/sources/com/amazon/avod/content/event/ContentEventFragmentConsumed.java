package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ContentEventFragmentConsumed extends ContentEventBase {
    private final SmoothStreamingURI mURI;

    public ContentEventFragmentConsumed(PlayableContent playableContent, ContentSessionType contentSessionType, @Nonnull SmoothStreamingURI smoothStreamingURI) {
        super(playableContent, contentSessionType);
        this.mURI = (SmoothStreamingURI) Preconditions.checkNotNull(smoothStreamingURI, "uri");
    }

    @Nonnull
    public SmoothStreamingURI getURI() {
        return this.mURI;
    }
}
