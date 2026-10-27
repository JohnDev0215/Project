package p;

import com.spotify.automotive.ubi.Referrer;
import java.util.Locale;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final class xj2 implements t72 {
    public final sn a;

    public xj2(sn snVar) {
        v03.j(snVar, "browseActionResolverProxy");
        this.a = snVar;
    }

    @Override // p.t72
    public final void a(f82 f82Var, Locale locale, ry0 ry0Var, py0 py0Var) {
        wj2 wj2Var = (wj2) f82Var;
        if2 if2VarH = vr2.h(wj2Var.a);
        if (if2VarH == null) {
            ry0Var.invoke(EmptyList.c);
            return;
        }
        ((zn) this.a).e(if2VarH, Referrer.SEARCH.a(), locale, wj2Var.b, ry0Var);
    }
}
