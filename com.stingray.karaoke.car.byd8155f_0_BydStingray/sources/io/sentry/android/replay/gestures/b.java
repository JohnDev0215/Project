package io.sentry.android.replay.gestures;

import A7.m;
import android.view.View;
import android.view.Window;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.android.replay.ReplayIntegration;
import io.sentry.android.replay.f;
import io.sentry.android.replay.y;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import n7.AbstractC2004s;
import n7.z;

/* JADX INFO: loaded from: classes.dex */
public final class b implements f {
    public final F1 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ReplayIntegration f16399m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ArrayList f16400n = new ArrayList();

    public b(F1 f12, ReplayIntegration replayIntegration) {
        this.l = f12;
        this.f16399m = replayIntegration;
    }

    public final void a(View view) {
        Window windowM = z.m(view);
        if (windowM == null) {
            this.l.getLogger().k(EnumC1495p1.DEBUG, "Window was null in stopGestureTracking", new Object[0]);
        } else if (windowM.getCallback() instanceof a) {
            Window.Callback callback = windowM.getCallback();
            m.d(callback, "null cannot be cast to non-null type io.sentry.android.replay.gestures.GestureRecorder.SentryReplayGestureRecorder");
            windowM.setCallback(((a) callback).l);
        }
    }

    @Override // io.sentry.android.replay.f
    public final void b(View view, boolean z6) throws IllegalAccessException {
        m.f(view, "root");
        ArrayList arrayList = this.f16400n;
        if (!z6) {
            a(view);
            AbstractC2004s.M(arrayList, new y(view, 1));
            return;
        }
        arrayList.add(new WeakReference(view));
        Window windowM = z.m(view);
        F1 f12 = this.l;
        if (windowM == null) {
            f12.getLogger().k(EnumC1495p1.DEBUG, "Window is invalid, not tracking gestures", new Object[0]);
            return;
        }
        Window.Callback callback = windowM.getCallback();
        if (callback instanceof a) {
            return;
        }
        windowM.setCallback(new a(f12, this.f16399m, callback));
    }
}
