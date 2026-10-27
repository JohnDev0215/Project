package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;

/* JADX INFO: loaded from: classes2.dex */
public class ContentEventDownloaded extends ContentEventBase {
    public ContentEventDownloaded(PlayableContent playableContent, ContentSessionType contentSessionType) {
        super(playableContent, contentSessionType);
    }
}
