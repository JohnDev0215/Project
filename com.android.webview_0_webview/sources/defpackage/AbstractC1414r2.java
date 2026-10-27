package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Handler;

/* JADX INFO: renamed from: r2, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1414r2 {
    public static final AbstractC1854z2 a;
    public static final C1249o1 b;

    /* JADX WARN: Removed duplicated region for block: B:19:0x003a  */
    static {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r0 < r1) goto Le
            v2 r0 = new v2
            r0.<init>()
            defpackage.AbstractC1414r2.a = r0
            goto L41
        Le:
            r1 = 26
            if (r0 < r1) goto L1a
            u2 r0 = new u2
            r0.<init>()
            defpackage.AbstractC1414r2.a = r0
            goto L41
        L1a:
            r1 = 24
            if (r0 < r1) goto L3a
            java.lang.reflect.Method r0 = defpackage.C1524t2.c
            if (r0 != 0) goto L29
            java.lang.String r0 = "TypefaceCompatApi24Impl"
            java.lang.String r1 = "Unable to collect necessary private methods.Fallback to legacy implementation."
            android.util.Log.w(r0, r1)
        L29:
            java.lang.reflect.Method r0 = defpackage.C1524t2.c
            if (r0 == 0) goto L2f
            r0 = 1
            goto L30
        L2f:
            r0 = 0
        L30:
            if (r0 == 0) goto L3a
            t2 r0 = new t2
            r0.<init>()
            defpackage.AbstractC1414r2.a = r0
            goto L41
        L3a:
            s2 r0 = new s2
            r0.<init>()
            defpackage.AbstractC1414r2.a = r0
        L41:
            o1 r0 = new o1
            r1 = 16
            r0.<init>(r1)
            defpackage.AbstractC1414r2.b = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC1414r2.<clinit>():void");
    }

    public static Typeface a(Context context, InterfaceC0693e2 interfaceC0693e2, Resources resources, int i, int i2, AbstractC1140m2 abstractC1140m2, Handler handler, boolean z) {
        Typeface typefaceA;
        if (interfaceC0693e2 instanceof C0861h2) {
            C0861h2 c0861h2 = (C0861h2) interfaceC0693e2;
            typefaceA = Q2.c(context, c0861h2.a, abstractC1140m2, handler, !z ? abstractC1140m2 != null : c0861h2.c != 0, z ? c0861h2.b : -1, i2);
        } else {
            typefaceA = a.a(context, (C0749f2) interfaceC0693e2, resources, i2);
            if (abstractC1140m2 != null) {
                if (typefaceA != null) {
                    abstractC1140m2.b(typefaceA, handler);
                } else {
                    abstractC1140m2.a(-3, handler);
                }
            }
        }
        if (typefaceA != null) {
            b.b(c(resources, i, i2), typefaceA);
        }
        return typefaceA;
    }

    public static Typeface b(Context context, Resources resources, int i, String str, int i2) {
        Typeface typefaceD = a.d(context, resources, i, str, i2);
        if (typefaceD != null) {
            b.b(c(resources, i, i2), typefaceD);
        }
        return typefaceD;
    }

    public static String c(Resources resources, int i, int i2) {
        return resources.getResourcePackageName(i) + "-" + i + "-" + i2;
    }
}
