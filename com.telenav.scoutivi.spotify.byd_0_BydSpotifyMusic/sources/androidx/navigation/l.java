package androidx.navigation;

import android.os.Bundle;
import androidx.navigation.r;

/* JADX INFO: compiled from: NavGraphNavigator.java */
/* JADX INFO: loaded from: classes.dex */
@r.b("navigation")
public class l extends r<k> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final s f2723a;

    public l(s sVar) {
        this.f2723a = sVar;
    }

    @Override // androidx.navigation.r
    public boolean e() {
        return true;
    }

    @Override // androidx.navigation.r
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public k a() {
        return new k(this);
    }

    @Override // androidx.navigation.r
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public j b(k kVar, Bundle bundle, o oVar, r.a aVar) {
        int iZ = kVar.z();
        if (iZ == 0) {
            throw new IllegalStateException("no start destination defined via app:startDestination for " + kVar.i());
        }
        j jVarX = kVar.x(iZ, false);
        if (jVarX != null) {
            return this.f2723a.e(jVarX.m()).b(jVarX, jVarX.e(bundle), oVar, aVar);
        }
        throw new IllegalArgumentException("navigation destination " + kVar.y() + " is not a direct child of this NavGraph");
    }
}
