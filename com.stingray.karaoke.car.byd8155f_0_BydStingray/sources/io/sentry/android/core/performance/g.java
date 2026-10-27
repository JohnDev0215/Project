package io.sentry.android.core.performance;

import T1.k;
import android.view.Window;
import io.sentry.android.core.internal.gestures.h;

/* JADX INFO: loaded from: classes.dex */
public final class g extends h {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final k f16289m;

    public g(Window.Callback callback, k kVar) {
        super(callback);
        this.f16289m = kVar;
    }

    @Override // io.sentry.android.core.internal.gestures.h, android.view.Window.Callback
    public final void onContentChanged() {
        super.onContentChanged();
        this.f16289m.run();
    }
}
