package com.amazon.alexa.voice.core;

import com.amazon.alexa.voice.core.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes2.dex */
public final class CompositeCancellable implements Cancellable {
    private ArrayList<Cancellable> cancellables;
    private volatile boolean cancelled;

    public void add(Cancellable cancellable) {
        Preconditions.notNull(cancellable, "cancellable == null");
        if (cancellable.isCancelled()) {
            return;
        }
        synchronized (this) {
            if (!this.cancelled) {
                if (this.cancellables == null) {
                    this.cancellables = new ArrayList<>();
                }
                this.cancellables.add(cancellable);
                return;
            }
            cancellable.cancel();
        }
    }

    private void cancelAll(Collection<Cancellable> collection) {
        if (collection == null) {
            return;
        }
        for (Cancellable cancellable : collection) {
            if (!cancellable.isCancelled()) {
                cancellable.cancel();
            }
        }
    }

    @Override // com.amazon.alexa.voice.core.Cancellable
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override // com.amazon.alexa.voice.core.Cancellable
    public void cancel() {
        synchronized (this) {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            ArrayList<Cancellable> arrayList = this.cancellables;
            this.cancellables = null;
            cancelAll(arrayList);
        }
    }
}
