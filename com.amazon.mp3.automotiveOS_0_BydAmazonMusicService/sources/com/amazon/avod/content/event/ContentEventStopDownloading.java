package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;

/* JADX INFO: loaded from: classes2.dex */
public class ContentEventStopDownloading extends ContentEventBase {
    public ContentEventStopDownloading(PlayableContent playableContent, ContentSessionType contentSessionType) {
        super(playableContent, contentSessionType);
    }
}
