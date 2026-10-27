package com.amazon.avod.playbackclient.subtitle.download;

import com.amazon.avod.listeners.SetListenerProxy;
import com.amazon.avod.playbackclient.listeners.SubtitleLoadListener;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleCollection;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleLanguage;
import com.google.common.base.Optional;
import java.util.Iterator;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class SubtitleLoadListenerProxy extends SetListenerProxy<SubtitleLoadListener> implements SubtitleLoadListener {
    @Override // com.amazon.avod.playbackclient.listeners.SubtitleLoadListener
    public void onDataLoadResponded(SubtitleLanguage subtitleLanguage, SubtitleCollection subtitleCollection, Optional<Long> optional, Optional<Long> optional2) {
        Iterator<SubtitleLoadListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onDataLoadResponded(subtitleLanguage, subtitleCollection, optional, optional2);
        }
    }
}
