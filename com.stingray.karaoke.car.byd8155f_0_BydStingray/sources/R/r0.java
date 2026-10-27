package r;

import android.content.Context;
import android.content.ContextWrapper;

/* JADX INFO: loaded from: classes.dex */
public abstract class r0 extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f21554a = null;

    public static void a(Context context) {
        if (context.getResources() instanceof s0) {
            return;
        }
        context.getResources();
        int i7 = C0.f21428a;
    }
}
