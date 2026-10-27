package com.amazon.avod.listeners;

import com.google.common.base.Preconditions;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public abstract class SetListenerProxy<T> {
    private final Set<T> mListeners = new CopyOnWriteArraySet();

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
