package i;

import A7.m;
import T.C0761q;
import T.C0773y;
import T.S;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import h.y;
import h.z;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0773y f15641a = new C0773y(S.f8224q, d.f15640m);

    public static y a(C0761q c0761q) {
        c0761q.R(-2068013981);
        y yVar = (y) c0761q.k(f15641a);
        c0761q.R(1680121597);
        if (yVar == null) {
            View view = (View) c0761q.k(AndroidCompositionLocals_androidKt.f11690f);
            m.f(view, "<this>");
            yVar = (y) P8.m.b0(P8.m.h0(P8.m.d0(view, z.f15149n), z.o));
        }
        c0761q.p(false);
        if (yVar == null) {
            Object baseContext = (Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b);
            while (true) {
                if (!(baseContext instanceof ContextWrapper)) {
                    baseContext = null;
                    break;
                }
                if (baseContext instanceof y) {
                    break;
                }
                baseContext = ((ContextWrapper) baseContext).getBaseContext();
            }
            yVar = (y) baseContext;
        }
        c0761q.p(false);
        return yVar;
    }
}
