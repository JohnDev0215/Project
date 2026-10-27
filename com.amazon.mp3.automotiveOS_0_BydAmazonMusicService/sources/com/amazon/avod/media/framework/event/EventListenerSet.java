package com.amazon.avod.media.framework.event;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class EventListenerSet<T> {
    private final Set<T> mListeners = Sets.newCopyOnWriteArraySet();

    @Nonnull
    protected Set<T> getListeners() {
        return this.mListeners;
    }

    public void addListener(@Nonnull T t) {
        Preconditions.checkNotNull(t);
        this.mListeners.add(t);
    }

    public void removeListener(@Nonnull T t) {
        Preconditions.checkNotNull(t);
        this.mListeners.remove(t);
    }

    public void clear() {
        this.mListeners.clear();
    }

    public int getListenerCount() {
        return this.mListeners.size();
    }
}
