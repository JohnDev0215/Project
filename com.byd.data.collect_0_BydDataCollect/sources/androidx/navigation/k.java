package androidx.navigation;

import android.os.Bundle;
import androidx.navigation.q;

/* JADX INFO: compiled from: NavGraphNavigator.java */
/* JADX INFO: loaded from: classes.dex */
@q.b("navigation")
public class k extends q<j> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final r f1715a;

    public k(r rVar) {
        this.f1715a = rVar;
    }

    @Override // androidx.navigation.q
    public boolean e() {
        return true;
    }

    @Override // androidx.navigation.q
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public j a() {
        return new j(this);
    }

    @Override // androidx.navigation.q
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public i b(j jVar, Bundle bundle, n nVar, q.a aVar) {
        int iW = jVar.w();
        if (iW == 0) {
            throw new IllegalStateException("no start destination defined via app:startDestination for " + jVar.g());
        }
        i iVarU = jVar.u(iW, false);
        if (iVarU != null) {
            return this.f1715a.e(iVarU.j()).b(iVarU, iVarU.c(bundle), nVar, aVar);
        }
        throw new IllegalArgumentException("navigation destination " + jVar.v() + " is not a direct child of this NavGraph");
    }
}
