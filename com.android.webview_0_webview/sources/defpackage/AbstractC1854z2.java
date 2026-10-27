package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import java.io.File;
import java.io.InputStream;

/* JADX INFO: renamed from: z2, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1854z2 {
    public static Object e(Object[] objArr, int i, InterfaceC1799y2 interfaceC1799y2) {
        int i2 = (i & 1) == 0 ? 400 : 700;
        boolean z = (i & 2) != 0;
        Object obj = null;
        int i3 = Integer.MAX_VALUE;
        for (Object obj2 : objArr) {
            int iAbs = (Math.abs(interfaceC1799y2.a(obj2) - i2) * 2) + (interfaceC1799y2.b(obj2) == z ? 0 : 1);
            if (obj == null || i3 > iAbs) {
                obj = obj2;
                i3 = iAbs;
            }
        }
        return obj;
    }

    public Typeface a(Context context, C0749f2 c0749f2, Resources resources, int i) {
        C0805g2 c0805g2 = (C0805g2) e(c0749f2.a, i, new C1744x2(this));
        if (c0805g2 == null) {
            return null;
        }
        return AbstractC1414r2.b(context, resources, c0805g2.f, c0805g2.a, i);
    }

    public abstract Typeface b(Context context, CancellationSignal cancellationSignal, O2[] o2Arr, int i);

    public Typeface c(Context context, InputStream inputStream) {
        File fileC = A2.c(context);
        if (fileC == null) {
            return null;
        }
        try {
            if (A2.b(fileC, inputStream)) {
                return Typeface.createFromFile(fileC.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            fileC.delete();
        }
    }

    public Typeface d(Context context, Resources resources, int i, String str, int i2) {
        File fileC = A2.c(context);
        if (fileC == null) {
            return null;
        }
        try {
            if (A2.a(fileC, resources, i)) {
                return Typeface.createFromFile(fileC.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            fileC.delete();
        }
    }

    public O2 f(O2[] o2Arr, int i) {
        return (O2) e(o2Arr, i, new C1689w2(this));
    }
}
