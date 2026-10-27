package com.amazon.music.nautilus;

import java.util.Set;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class MusicSubscriptionStateChangeListenerRunnable implements Runnable {
    private final Set<MusicSubscriptionStateField> changedFields;
    private final MusicSubscriptionStateChangeListener listener;

    public MusicSubscriptionStateChangeListenerRunnable(MusicSubscriptionStateChangeListener musicSubscriptionStateChangeListener, Set<MusicSubscriptionStateField> set) {
        this.listener = (MusicSubscriptionStateChangeListener) Validate.notNull(musicSubscriptionStateChangeListener);
        this.changedFields = (Set) Validate.notNull(set);
    }

    @Override // java.lang.Runnable
    public void run() {
        this.listener.onMusicSubscriptionStateChange(this.changedFields);
    }
}
