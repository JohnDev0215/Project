package com.amazon.avod.playbackclient.subtitle.download;

import com.amazon.avod.core.Subtitle;
import com.amazon.avod.listeners.SetListenerProxy;
import java.io.File;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class LanguageDownloadListenerProxy extends SetListenerProxy<LanguageDownloadListener> implements LanguageDownloadListener {
    @Override // com.amazon.avod.playbackclient.subtitle.download.LanguageDownloadListener
    public void onLanguageDownload(@Nonnull Subtitle subtitle, long j, @Nonnull File file) {
        Iterator<LanguageDownloadListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onLanguageDownload(subtitle, j, file);
        }
    }
}
