package io.sentry.android.core.internal.gestures;

import android.app.Activity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import h3.C1350a;
import h6.q;
import io.sentry.C1457d;
import io.sentry.C1458d0;
import io.sentry.C1523x;
import io.sentry.E;
import io.sentry.EnumC1495p1;
import io.sentry.K;
import io.sentry.V;
import io.sentry.W1;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.b2;
import io.sentry.c2;
import io.sentry.protocol.C;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class f implements GestureDetector.OnGestureListener {
    public final WeakReference l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final E f16183m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final SentryAndroidOptions f16184n;
    public io.sentry.internal.gestures.c o = null;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public V f16185p = null;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public d f16186q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final e f16187r;

    public f(Activity activity, E e10, SentryAndroidOptions sentryAndroidOptions) {
        d dVar = d.Unknown;
        this.f16186q = dVar;
        e eVar = new e();
        eVar.f16181a = dVar;
        eVar.c = 0.0f;
        eVar.d = 0.0f;
        this.f16187r = eVar;
        this.l = new WeakReference(activity);
        this.f16183m = e10;
        this.f16184n = sentryAndroidOptions;
    }

    public static String c(d dVar) {
        int i7 = c.f16180a[dVar.ordinal()];
        return i7 != 1 ? i7 != 2 ? i7 != 3 ? "unknown" : "swipe" : "scroll" : "click";
    }

    public final void a(io.sentry.internal.gestures.c cVar, d dVar, Map map, MotionEvent motionEvent) {
        if (this.f16184n.isEnableUserInteractionBreadcrumbs()) {
            String strC = c(dVar);
            C1523x c1523x = new C1523x();
            c1523x.c(motionEvent, "android:motionEvent");
            c1523x.c(cVar.f16571a.get(), "android:view");
            C1457d c1457d = new C1457d();
            c1457d.o = "user";
            c1457d.f16515q = "ui.".concat(strC);
            String str = cVar.c;
            if (str != null) {
                c1457d.c(str, "view.id");
            }
            String str2 = cVar.f16572b;
            if (str2 != null) {
                c1457d.c(str2, "view.class");
            }
            String str3 = cVar.d;
            if (str3 != null) {
                c1457d.c(str3, "view.tag");
            }
            for (Map.Entry entry : map.entrySet()) {
                c1457d.f16514p.put((String) entry.getKey(), entry.getValue());
            }
            c1457d.f16517s = EnumC1495p1.INFO;
            this.f16183m.k(c1457d, c1523x);
        }
    }

    public final View b(String str) {
        Activity activity = (Activity) this.l.get();
        SentryAndroidOptions sentryAndroidOptions = this.f16184n;
        if (activity == null) {
            sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, android.hardware.bydauto.ac.tsimpl.a.i("Activity is null in ", str, ". No breadcrumb captured."), new Object[0]);
            return null;
        }
        Window window = activity.getWindow();
        if (window == null) {
            sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, android.hardware.bydauto.ac.tsimpl.a.i("Window is null in ", str, ". No breadcrumb captured."), new Object[0]);
            return null;
        }
        View decorView = window.getDecorView();
        if (decorView != null) {
            return decorView;
        }
        sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, android.hardware.bydauto.ac.tsimpl.a.i("DecorView is null in ", str, ". No breadcrumb captured."), new Object[0]);
        return null;
    }

    public final void d(io.sentry.internal.gestures.c cVar, d dVar) {
        boolean z6 = dVar == d.Click || !(dVar == this.f16186q && cVar.equals(this.o));
        SentryAndroidOptions sentryAndroidOptions = this.f16184n;
        boolean zIsTracingEnabled = sentryAndroidOptions.isTracingEnabled();
        E e10 = this.f16183m;
        if (!zIsTracingEnabled || !sentryAndroidOptions.isEnableUserInteractionTracing()) {
            if (z6) {
                e10.l(new C1350a(14));
                this.o = cVar;
                this.f16186q = dVar;
                return;
            }
            return;
        }
        Activity activity = (Activity) this.l.get();
        if (activity == null) {
            sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "Activity is null, no transaction captured.", new Object[0]);
            return;
        }
        String str = cVar.c;
        if (str == null) {
            String str2 = cVar.d;
            h6.e.q(str2, "UiElement.tag can't be null");
            str = str2;
        }
        V v10 = this.f16185p;
        if (v10 != null) {
            if (!z6 && !v10.g()) {
                sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, android.hardware.bydauto.ac.tsimpl.a.i("The view with id: ", str, " already has an ongoing transaction assigned. Rescheduling finish"), new Object[0]);
                if (sentryAndroidOptions.getIdleTimeout() != null) {
                    this.f16185p.m();
                    return;
                }
                return;
            }
            e(W1.OK);
        }
        String str3 = activity.getClass().getSimpleName() + "." + str;
        String strConcat = "ui.action.".concat(c(dVar));
        c2 c2Var = new c2();
        c2Var.o = true;
        c2Var.f16486q = 30000L;
        c2Var.f16485p = sentryAndroidOptions.getIdleTimeout();
        c2Var.l = true;
        V vJ = e10.j(new b2(str3, C.COMPONENT, strConcat, null), c2Var);
        vJ.p().f15963t = "auto.ui.gesture_listener." + cVar.f16573e;
        e10.l(new C1458d0(this, 3, vJ));
        this.f16185p = vJ;
        this.o = cVar;
        this.f16186q = dVar;
    }

    public final void e(W1 w1) {
        V v10 = this.f16185p;
        if (v10 != null) {
            if (v10.s() == null) {
                this.f16185p.q(w1);
            } else {
                this.f16185p.z();
            }
        }
        this.f16183m.l(new G2.a(this, 12));
        this.f16185p = null;
        if (this.o != null) {
            this.o = null;
        }
        this.f16186q = d.Unknown;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onDown(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        e eVar = this.f16187r;
        eVar.f16182b = null;
        eVar.f16181a = d.Unknown;
        eVar.c = 0.0f;
        eVar.d = 0.0f;
        eVar.c = motionEvent.getX();
        eVar.d = motionEvent.getY();
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f10, float f11) {
        this.f16187r.f16181a = d.Swipe;
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final void onLongPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f10, float f11) {
        View viewB = b("onScroll");
        if (viewB != null && motionEvent != null) {
            e eVar = this.f16187r;
            if (eVar.f16181a == d.Unknown) {
                float x8 = motionEvent.getX();
                float y10 = motionEvent.getY();
                io.sentry.internal.gestures.b bVar = io.sentry.internal.gestures.b.SCROLLABLE;
                SentryAndroidOptions sentryAndroidOptions = this.f16184n;
                io.sentry.internal.gestures.c cVarQ = q.q(sentryAndroidOptions, viewB, x8, y10, bVar);
                if (cVarQ == null) {
                    sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "Unable to find scroll target. No breadcrumb captured.", new Object[0]);
                    return false;
                }
                K logger = sentryAndroidOptions.getLogger();
                EnumC1495p1 enumC1495p1 = EnumC1495p1.DEBUG;
                String str = cVarQ.c;
                if (str == null) {
                    String str2 = cVarQ.d;
                    h6.e.q(str2, "UiElement.tag can't be null");
                    str = str2;
                }
                logger.k(enumC1495p1, "Scroll target found: ".concat(str), new Object[0]);
                eVar.f16182b = cVarQ;
                eVar.f16181a = d.Scroll;
            }
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final void onShowPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        View viewB = b("onSingleTapUp");
        if (viewB != null && motionEvent != null) {
            float x8 = motionEvent.getX();
            float y10 = motionEvent.getY();
            io.sentry.internal.gestures.b bVar = io.sentry.internal.gestures.b.CLICKABLE;
            SentryAndroidOptions sentryAndroidOptions = this.f16184n;
            io.sentry.internal.gestures.c cVarQ = q.q(sentryAndroidOptions, viewB, x8, y10, bVar);
            if (cVarQ == null) {
                sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "Unable to find click target. No breadcrumb captured.", new Object[0]);
                return false;
            }
            d dVar = d.Click;
            a(cVarQ, dVar, Collections.emptyMap(), motionEvent);
            d(cVarQ, dVar);
        }
        return false;
    }
}
