package com.amazon.podcast.bootstrap;

import android.content.Context;

/* JADX INFO: loaded from: classes5.dex */
public abstract class NetworkSettingsProvider {
    public boolean isStreamingRestricted(final Context context) {
        throw new UnsupportedOperationException();
    }

    public void showStreamingRestricted(final Context context) {
        throw new UnsupportedOperationException();
    }

    public boolean isOverridingEndpoint() {
        throw new UnsupportedOperationException();
    }
}
