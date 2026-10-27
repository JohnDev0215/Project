package io.sentry.android.core.internal.gestures;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import androidx.core.view.GestureDetectorCompat;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.hints.i;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public final class g extends h {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Window.Callback f16188m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final f f16189n;
    public final GestureDetectorCompat o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final F1 f16190p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final i f16191q;

    public g(Window.Callback callback, Activity activity, f fVar, F1 f12) {
        GestureDetectorCompat gestureDetectorCompat = new GestureDetectorCompat(activity, fVar);
        i iVar = new i(23);
        super(callback);
        this.f16188m = callback;
        this.f16189n = fVar;
        this.f16190p = f12;
        this.o = gestureDetectorCompat;
        this.f16191q = iVar;
    }

    public final void a(MotionEvent motionEvent) {
        this.o.f11750a.onTouchEvent(motionEvent);
        if (motionEvent.getActionMasked() == 1) {
            f fVar = this.f16189n;
            View viewB = fVar.b("onUp");
            e eVar = fVar.f16187r;
            io.sentry.internal.gestures.c cVar = eVar.f16182b;
            if (viewB == null || cVar == null) {
                return;
            }
            d dVar = eVar.f16181a;
            d dVar2 = d.Unknown;
            if (dVar == dVar2) {
                fVar.f16184n.getLogger().k(EnumC1495p1.DEBUG, "Unable to define scroll type. No breadcrumb captured.", new Object[0]);
                return;
            }
            float x8 = motionEvent.getX() - eVar.c;
            float y10 = motionEvent.getY() - eVar.d;
            fVar.a(cVar, eVar.f16181a, Collections.singletonMap("direction", Math.abs(x8) > Math.abs(y10) ? x8 > 0.0f ? "right" : "left" : y10 > 0.0f ? "down" : "up"), motionEvent);
            fVar.d(cVar, eVar.f16181a);
            eVar.f16182b = null;
            eVar.f16181a = dVar2;
            eVar.c = 0.0f;
            eVar.d = 0.0f;
        }
    }

    @Override // io.sentry.android.core.internal.gestures.h, android.view.Window.Callback
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        F1 f12;
        if (motionEvent != null) {
            this.f16191q.getClass();
            try {
                a(MotionEvent.obtain(motionEvent));
            } finally {
                if (f12 != null) {
                    try {
                    } finally {
                    }
                }
            }
        }
        return this.l.dispatchTouchEvent(motionEvent);
    }
}
