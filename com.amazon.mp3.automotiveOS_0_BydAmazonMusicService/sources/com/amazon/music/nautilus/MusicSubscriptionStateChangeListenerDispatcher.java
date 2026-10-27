package com.amazon.music.nautilus;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class MusicSubscriptionStateChangeListenerDispatcher implements MusicSubscriptionStateChangeListener {
    private final ExecutorService executorService;
    private final Set<MusicSubscriptionStateChangeListener> listeners;

    public MusicSubscriptionStateChangeListenerDispatcher() {
        this(Executors.newSingleThreadExecutor());
    }

    MusicSubscriptionStateChangeListenerDispatcher(ExecutorService executorService) {
        this.listeners = new CopyOnWriteArraySet();
        this.executorService = executorService;
    }

    public void registerListener(MusicSubscriptionStateChangeListener musicSubscriptionStateChangeListener) {
        this.listeners.add((MusicSubscriptionStateChangeListener) Validate.notNull(musicSubscriptionStateChangeListener));
    }

    public void deregisterListener(MusicSubscriptionStateChangeListener musicSubscriptionStateChangeListener) {
        this.listeners.remove(Validate.notNull(musicSubscriptionStateChangeListener));
    }

    @Override // com.amazon.music.nautilus.MusicSubscriptionStateChangeListener
    public void onMusicSubscriptionStateChange(Set<MusicSubscriptionStateField> set) {
        Iterator<MusicSubscriptionStateChangeListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            this.executorService.submit(new MusicSubscriptionStateChangeListenerRunnable(it.next(), set));
        }
    }
}
