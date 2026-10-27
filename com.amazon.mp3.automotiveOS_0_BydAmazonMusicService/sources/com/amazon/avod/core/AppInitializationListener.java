package com.amazon.avod.core;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface AppInitializationListener {
    void onInitializationError(@Nonnull InitializationErrorCode initializationErrorCode);
}
