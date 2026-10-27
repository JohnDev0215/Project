package com.android.settingslib.suggestions;

import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.service.settings.suggestions.Suggestion;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.e;
import java.util.List;

/* JADX WARN: Unexpected interfaces in signature: [android.app.LoaderManager$LoaderCallbacks<java.util.List<android.service.settings.suggestions.Suggestion>>] */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class SuggestionControllerMixin implements Object, e {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f1370c;
    private final com.android.settingslib.suggestions.a d;
    private final a e;

    /* JADX INFO: compiled from: caradapter-bug */
    public interface a {
        void a(List<Suggestion> list);
    }

    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onLoadFinished(Loader<List<Suggestion>> loader, List<Suggestion> list) {
        this.e.a(list);
    }

    public Loader<List<Suggestion>> onCreateLoader(int i, Bundle bundle) {
        if (i == 42) {
            return new b(this.f1370c, this.d);
        }
        throw new IllegalArgumentException("This loader id is not supported " + i);
    }

    public void onLoaderReset(Loader<List<Suggestion>> loader) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        this.d.b();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        this.d.c();
    }
}
