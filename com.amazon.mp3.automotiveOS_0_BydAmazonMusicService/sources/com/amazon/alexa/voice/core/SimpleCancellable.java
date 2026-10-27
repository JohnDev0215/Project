package com.amazon.alexa.voice.core;

/* JADX INFO: loaded from: classes2.dex */
public class SimpleCancellable implements Cancellable {
    private volatile boolean cancelled;

    protected void onCancelled() {
    }

    @Override // com.amazon.alexa.voice.core.Cancellable
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override // com.amazon.alexa.voice.core.Cancellable
    public final void cancel() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        onCancelled();
    }
}
