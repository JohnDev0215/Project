package defpackage;

import android.content.res.Resources;
import android.util.TypedValue;
import org.chromium.base.TraceEvent;

/* JADX INFO: compiled from: TraceEventVectorDrawableCompat.java */
/* JADX INFO: loaded from: classes2.dex */
public final class re3 {
    public static el3 a(Resources resources, int i, Resources.Theme theme) {
        TraceEvent traceEventH0 = TraceEvent.H0("VectorDrawableCompat.create", null);
        try {
            el3 el3Var = new el3();
            ThreadLocal<TypedValue> threadLocal = rb2.a;
            el3Var.e = resources.getDrawable(i, theme);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return el3Var;
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }
}
