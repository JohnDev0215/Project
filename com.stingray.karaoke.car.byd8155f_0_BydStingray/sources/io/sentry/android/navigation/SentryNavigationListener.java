package io.sentry.android.navigation;

import A7.m;
import android.os.Bundle;
import io.sentry.C1489n1;
import io.sentry.E;
import io.sentry.J;
import io.sentry.V;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import n3.InterfaceC1949n;
import n7.AbstractC2000o;
import n7.v;
import n7.z;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lio/sentry/android/navigation/SentryNavigationListener;", "Ln3/n;", "sentry-android-navigation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SentryNavigationListener implements InterfaceC1949n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f16305b;
    public final boolean c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public WeakReference f16306e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Bundle f16307f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public V f16308g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final J f16304a = E.f15834a;
    public final String d = "jetpack_compose";

    public SentryNavigationListener(boolean z6, boolean z10) {
        this.f16305b = z6;
        this.c = z10;
        z.c("NavigationListener");
        C1489n1.s().g("maven:io.sentry:sentry-android-navigation");
    }

    public static Map b(Bundle bundle) {
        if (bundle == null) {
            return v.l;
        }
        Set<String> setKeySet = bundle.keySet();
        m.e(setKeySet, "args.keySet()");
        ArrayList arrayList = new ArrayList();
        for (Object obj : setKeySet) {
            if (!m.a((String) obj, "android-support-nav:controller:deepLinkIntent")) {
                arrayList.add(obj);
            }
        }
        int iQ = z.q(AbstractC2000o.J(arrayList, 10));
        if (iQ < 16) {
            iQ = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ);
        for (Object obj2 : arrayList) {
            linkedHashMap.put(obj2, bundle.get((String) obj2));
        }
        return linkedHashMap;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00b8 A[PHI: r2
  0x00b8: PHI (r2v27 java.lang.String) = (r2v1 java.lang.String), (r2v4 java.lang.String) binds: [B:25:0x0094, B:29:0x00b4] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // n3.InterfaceC1949n
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(n3.C1930E r11, n3.y r12, android.os.Bundle r13) {
        /*
            Method dump skipped, instruction units count: 401
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.navigation.SentryNavigationListener.a(n3.E, n3.y, android.os.Bundle):void");
    }
}
