package com.amazon.avod.playbackclient.listeners;

import com.amazon.avod.playbackclient.subtitle.internal.SubtitleCollection;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleLanguage;
import com.google.common.base.Optional;

/* JADX INFO: loaded from: classes2.dex */
public interface SubtitleLoadListener {
    void onDataLoadResponded(SubtitleLanguage subtitleLanguage, SubtitleCollection subtitleCollection, Optional<Long> optional, Optional<Long> optional2);
}
