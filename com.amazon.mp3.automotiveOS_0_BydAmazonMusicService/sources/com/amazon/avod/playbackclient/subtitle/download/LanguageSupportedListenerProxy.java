package com.amazon.avod.playbackclient.subtitle.download;

import com.amazon.avod.listeners.SetListenerProxy;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleLanguage;
import com.google.common.collect.ImmutableSet;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class LanguageSupportedListenerProxy extends SetListenerProxy<LanguageSupportedListener> implements LanguageSupportedListener {
    @Override // com.amazon.avod.playbackclient.subtitle.download.LanguageSupportedListener
    public void onLanguagesAvailable(@Nonnull ImmutableSet<SubtitleLanguage> immutableSet, @Nonnull ImmutableSet<SubtitleLanguage> immutableSet2) {
        Iterator<LanguageSupportedListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onLanguagesAvailable(immutableSet, immutableSet2);
        }
    }
}
