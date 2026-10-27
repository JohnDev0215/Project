package com.amplifyframework.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public final class NoOpAction implements Action {
    @Override // com.amplifyframework.core.Action
    public void call() {
    }

    @NonNull
    public String toString() {
        return "NoOpAction {}";
    }

    private NoOpAction() {
    }

    @NonNull
    public static NoOpAction create() {
        return new NoOpAction();
    }

    public int hashCode() {
        return NoOpAction.class.hashCode();
    }

    public boolean equals(@Nullable Object obj) {
        return obj instanceof NoOpAction;
    }
}
