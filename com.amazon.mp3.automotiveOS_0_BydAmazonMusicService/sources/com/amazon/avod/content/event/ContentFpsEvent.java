package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;

/* JADX INFO: loaded from: classes2.dex */
public class ContentFpsEvent extends ContentEventBase {
    private final double mFramesPerSecond;

    public ContentFpsEvent(PlayableContent playableContent, ContentSessionType contentSessionType, double d) {
        super(playableContent, contentSessionType);
        this.mFramesPerSecond = d;
    }

    public double getFramesPerSecond() {
        return this.mFramesPerSecond;
    }
}
