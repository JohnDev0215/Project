package com.amplifyframework.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public final class NoOpConsumer<T> implements Consumer<T> {
    @Override // com.amplifyframework.core.Consumer
    public void accept(@NonNull T t) {
    }

    @NonNull
    public String toString() {
        return "NoOpConsumer {}";
    }

    private NoOpConsumer() {
    }

    @NonNull
    public static <T> NoOpConsumer<T> create() {
        return new NoOpConsumer<>();
    }

    public int hashCode() {
        return NoOpConsumer.class.hashCode();
    }

    public boolean equals(@Nullable Object obj) {
        return obj instanceof NoOpConsumer;
    }
}
