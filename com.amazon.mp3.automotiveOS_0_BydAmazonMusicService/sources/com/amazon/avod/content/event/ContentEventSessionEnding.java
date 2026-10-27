package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;

/* JADX INFO: loaded from: classes2.dex */
public class ContentEventSessionEnding extends ContentEventBase {
    public ContentEventSessionEnding(PlayableContent playableContent, ContentSessionType contentSessionType) {
        super(playableContent, contentSessionType);
    }
}
