package c2;

import T.C0761q;
import T.C0773y;
import T.S;
import android.view.View;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.a0;
import androidx.lifecycle.l0;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0773y f12350a = new C0773y(S.f8224q, C1017a.f12349m);

    public static l0 a(C0761q c0761q) {
        c0761q.R(-584162872);
        l0 l0VarG = (l0) c0761q.k(f12350a);
        if (l0VarG == null) {
            c0761q.R(1382572291);
            l0VarG = a0.g((View) c0761q.k(AndroidCompositionLocals_androidKt.f11690f));
            c0761q.p(false);
        }
        c0761q.p(false);
        return l0VarG;
    }
}
