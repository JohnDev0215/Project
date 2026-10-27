package io.sentry.android.core;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.compose.ui.node.Owner;
import h8.AbstractC1387d;
import io.sentry.C1422a;
import io.sentry.C1480k1;
import io.sentry.C1489n1;
import io.sentry.C1523x;
import io.sentry.EnumC1495p1;
import io.sentry.InterfaceC1511t;
import io.sentry.compose.viewhierarchy.ComposeViewHierarchyExporter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class ViewHierarchyEventProcessor implements InterfaceC1511t {
    public final SentryAndroidOptions l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final io.sentry.android.core.internal.util.d f16123m;

    public ViewHierarchyEventProcessor(SentryAndroidOptions sentryAndroidOptions) {
        h6.e.q(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.l = sentryAndroidOptions;
        this.f16123m = new io.sentry.android.core.internal.util.d(2000L, 3);
        if (sentryAndroidOptions.isAttachViewHierarchy()) {
            n7.z.c("ViewHierarchy");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(View view, io.sentry.protocol.G g7, List list) {
        if (view instanceof ViewGroup) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ComposeViewHierarchyExporter composeViewHierarchyExporter = (ComposeViewHierarchyExporter) it.next();
                composeViewHierarchyExporter.getClass();
                if (view instanceof Owner) {
                    if (composeViewHierarchyExporter.f16506b == null) {
                        synchronized (composeViewHierarchyExporter) {
                            try {
                                if (composeViewHierarchyExporter.f16506b == null) {
                                    composeViewHierarchyExporter.f16506b = new C1489n1(composeViewHierarchyExporter.f16505a);
                                }
                            } finally {
                            }
                        }
                    }
                    ComposeViewHierarchyExporter.a(composeViewHierarchyExporter.f16506b, g7, null, ((Owner) view).getRoot());
                    return;
                }
            }
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            if (childCount == 0) {
                return;
            }
            ArrayList arrayList = new ArrayList(childCount);
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = viewGroup.getChildAt(i7);
                if (childAt != null) {
                    io.sentry.protocol.G gC = c(childAt);
                    arrayList.add(gC);
                    a(childAt, gC, list);
                }
            }
            g7.f16673v = arrayList;
        }
    }

    public static io.sentry.protocol.G c(View view) {
        io.sentry.protocol.G g7 = new io.sentry.protocol.G();
        g7.f16665m = h6.r.h(view);
        try {
            g7.f16666n = h6.q.t(view);
        } catch (Throwable unused) {
        }
        g7.f16669r = Double.valueOf(view.getX());
        g7.f16670s = Double.valueOf(view.getY());
        g7.f16667p = Double.valueOf(view.getWidth());
        g7.f16668q = Double.valueOf(view.getHeight());
        g7.f16672u = Double.valueOf(view.getAlpha());
        int visibility = view.getVisibility();
        if (visibility == 0) {
            g7.f16671t = "visible";
        } else if (visibility == 4) {
            g7.f16671t = "invisible";
        } else if (visibility == 8) {
            g7.f16671t = "gone";
        }
        return g7;
    }

    @Override // io.sentry.InterfaceC1511t
    public final io.sentry.protocol.A g(io.sentry.protocol.A a10, C1523x c1523x) {
        return a10;
    }

    @Override // io.sentry.InterfaceC1511t
    public final C1480k1 i(C1480k1 c1480k1, C1523x c1523x) {
        if (!c1480k1.c()) {
            return c1480k1;
        }
        SentryAndroidOptions sentryAndroidOptions = this.l;
        if (!sentryAndroidOptions.isAttachViewHierarchy()) {
            sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "attachViewHierarchy is disabled.", new Object[0]);
            return c1480k1;
        }
        if (AbstractC1387d.i(c1523x)) {
            return c1480k1;
        }
        boolean zA = this.f16123m.a();
        sentryAndroidOptions.getBeforeViewHierarchyCaptureCallback();
        if (zA) {
            return c1480k1;
        }
        WeakReference weakReference = (WeakReference) B.f16041b.f16042a;
        io.sentry.protocol.F f10 = null;
        Activity activity = weakReference != null ? (Activity) weakReference.get() : null;
        List<ComposeViewHierarchyExporter> viewHierarchyExporters = sentryAndroidOptions.getViewHierarchyExporters();
        io.sentry.util.thread.a mainThreadChecker = sentryAndroidOptions.getMainThreadChecker();
        io.sentry.K logger = sentryAndroidOptions.getLogger();
        if (activity == null) {
            logger.k(EnumC1495p1.INFO, "Missing activity for view hierarchy snapshot.", new Object[0]);
        } else {
            Window window = activity.getWindow();
            if (window == null) {
                logger.k(EnumC1495p1.INFO, "Missing window for view hierarchy snapshot.", new Object[0]);
            } else {
                View viewPeekDecorView = window.peekDecorView();
                if (viewPeekDecorView == null) {
                    logger.k(EnumC1495p1.INFO, "Missing decor view for view hierarchy snapshot.", new Object[0]);
                } else {
                    try {
                        if (mainThreadChecker.a()) {
                            ArrayList arrayList = new ArrayList(1);
                            io.sentry.protocol.F f11 = new io.sentry.protocol.F("android_view_system", arrayList);
                            io.sentry.protocol.G gC = c(viewPeekDecorView);
                            arrayList.add(gC);
                            a(viewPeekDecorView, gC, viewHierarchyExporters);
                            f10 = f11;
                        } else {
                            CountDownLatch countDownLatch = new CountDownLatch(1);
                            AtomicReference atomicReference = new AtomicReference(null);
                            activity.runOnUiThread(new d0(atomicReference, viewPeekDecorView, viewHierarchyExporters, countDownLatch, logger, 0));
                            if (countDownLatch.await(1000L, TimeUnit.MILLISECONDS)) {
                                f10 = (io.sentry.protocol.F) atomicReference.get();
                            }
                        }
                    } catch (Throwable th) {
                        logger.s(EnumC1495p1.ERROR, "Failed to process view hierarchy.", th);
                    }
                }
            }
        }
        if (f10 != null) {
            c1523x.d = new C1422a(f10);
        }
        return c1480k1;
    }
}
