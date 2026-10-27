package com.amazon.avod.playback.event.playback;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AdChapteringEvent {
    final String mChapteringNote;

    public AdChapteringEvent(@Nonnull String str) {
        this.mChapteringNote = str;
    }

    public String getChapteringNote() {
        return this.mChapteringNote;
    }
}
