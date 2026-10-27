package com.android.settingslib.suggestions;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

/* JADX WARN: Unexpected interfaces in signature: [androidx.lifecycle.e, java.lang.Object<java.util.List<android.service.settings.suggestions.Suggestion>>] */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class SuggestionControllerMixinCompat implements Object {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final a f1371c;

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        this.f1371c.b();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        this.f1371c.c();
    }
}
