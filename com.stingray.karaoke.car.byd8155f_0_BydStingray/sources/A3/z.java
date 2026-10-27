package A3;

import T.C0753m;
import T.C0761q;
import android.content.Context;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;

/* JADX INFO: loaded from: classes.dex */
public abstract class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f408a = io.sentry.config.a.U(0, 0, 0, 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final L3.e f409b;

    static {
        L3.g gVar = L3.g.c;
        f409b = new L3.e();
    }

    public static final K3.i a(Object obj, C0761q c0761q) {
        c0761q.R(1087186730);
        if (obj instanceof K3.i) {
            K3.i iVar = (K3.i) obj;
            c0761q.p(false);
            return iVar;
        }
        Context context = (Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b);
        c0761q.R(-1245195153);
        boolean zF = c0761q.f(context) | c0761q.f(obj);
        Object objG = c0761q.G();
        if (zF || objG == C0753m.f8270a) {
            K3.h hVar = new K3.h(context);
            hVar.c = obj;
            objG = hVar.a();
            c0761q.a0(objG);
        }
        K3.i iVar2 = (K3.i) objG;
        c0761q.p(false);
        c0761q.p(false);
        return iVar2;
    }
}
