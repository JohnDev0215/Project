package com.android.settingslib.widget;

import android.view.View;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.e;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class ActionBarShadowController implements e {

    @VisibleForTesting
    static final float ELEVATION_HIGH = 8.0f;

    @VisibleForTesting
    static final float ELEVATION_LOW = 0.0f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private View f1377c;
    private boolean d;

    @VisibleForTesting
    a mScrollChangeWatcher;

    /* JADX INFO: compiled from: caradapter-bug */
    final class a implements View.OnScrollChangeListener {
        public abstract void a(View view);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void attachScrollWatcher() {
        if (this.d) {
            return;
        }
        this.d = true;
        this.f1377c.setOnScrollChangeListener(this.mScrollChangeWatcher);
        this.mScrollChangeWatcher.a(this.f1377c);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void detachScrollWatcher() {
        this.f1377c.setOnScrollChangeListener(null);
        this.d = false;
    }
}
