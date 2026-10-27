package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Q2 {
    public static final C1249o1 a = new C1249o1(16);
    public static final W2 b = new W2("fonts", 10, 10000);
    public static final Object c = new Object();
    public static final C1633v1 d = new C1633v1();
    public static final Comparator e = new M2();

    /* JADX WARN: Removed duplicated region for block: B:30:0x0090 A[LOOP:1: B:15:0x004b->B:30:0x0090, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0094 A[EDGE_INSN: B:80:0x0094->B:32:0x0094 BREAK  A[LOOP:1: B:15:0x004b->B:30:0x0090], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static defpackage.N2 a(android.content.Context r20, android.os.CancellationSignal r21, defpackage.I2 r22) throws android.content.pm.PackageManager.NameNotFoundException {
        /*
            Method dump skipped, instruction units count: 451
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Q2.a(android.content.Context, android.os.CancellationSignal, I2):N2");
    }

    public static P2 b(Context context, I2 i2, int i) {
        try {
            N2 n2A = a(context, null, i2);
            int i3 = n2A.a;
            if (i3 != 0) {
                return new P2(null, i3 == 1 ? -2 : -3);
            }
            Typeface typefaceB = AbstractC1414r2.a.b(context, null, n2A.b, i);
            return new P2(typefaceB, typefaceB != null ? 0 : -3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new P2(null, -1);
        }
    }

    public static Typeface c(Context context, I2 i2, AbstractC1140m2 abstractC1140m2, Handler handler, boolean z, int i, int i3) {
        String str = i2.f + "-" + i3;
        Typeface typeface = (Typeface) a.a(str);
        if (typeface != null) {
            if (abstractC1140m2 != null) {
                abstractC1140m2.c(typeface);
            }
            return typeface;
        }
        if (z && i == -1) {
            P2 p2B = b(context, i2, i3);
            if (abstractC1140m2 != null) {
                int i4 = p2B.b;
                if (i4 == 0) {
                    abstractC1140m2.b(p2B.a, handler);
                } else {
                    abstractC1140m2.a(i4, handler);
                }
            }
            return p2B.a;
        }
        J2 j2 = new J2(context, i2, i3, str);
        if (z) {
            try {
                return ((P2) b.b(j2, i)).a;
            } catch (InterruptedException unused) {
                return null;
            }
        }
        K2 k2 = abstractC1140m2 == null ? null : new K2(abstractC1140m2, handler);
        synchronized (c) {
            if (d.e(str) >= 0) {
                if (k2 != null) {
                    ((ArrayList) d.get(str)).add(k2);
                }
                return null;
            }
            if (k2 != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(k2);
                d.put(str, arrayList);
            }
            W2 w2 = b;
            L2 l2 = new L2(str);
            Objects.requireNonNull(w2);
            w2.a(new T2(w2, j2, new Handler(), l2));
            return null;
        }
    }

    public static Map d(Context context, O2[] o2Arr, CancellationSignal cancellationSignal) {
        HashMap map = new HashMap();
        for (O2 o2 : o2Arr) {
            if (o2.e == 0) {
                Uri uri = o2.a;
                if (!map.containsKey(uri)) {
                    map.put(uri, A2.d(context, cancellationSignal, uri));
                }
            }
        }
        return Collections.unmodifiableMap(map);
    }
}
