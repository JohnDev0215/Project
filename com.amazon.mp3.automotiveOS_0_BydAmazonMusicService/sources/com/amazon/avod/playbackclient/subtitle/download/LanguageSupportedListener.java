package com.amazon.avod.playbackclient.subtitle.download;

import com.amazon.avod.playbackclient.subtitle.internal.SubtitleLanguage;
import com.google.common.collect.ImmutableSet;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface LanguageSupportedListener {
    void onLanguagesAvailable(@Nonnull ImmutableSet<SubtitleLanguage> immutableSet, @Nonnull ImmutableSet<SubtitleLanguage> immutableSet2);
}
