package N;

import A7.m;
import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4271a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f4272b;
    public boolean c = false;
    public e d = null;

    public i(String str, String str2) {
        this.f4271a = str;
        this.f4272b = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return m.a(this.f4271a, iVar.f4271a) && m.a(this.f4272b, iVar.f4272b) && this.c == iVar.c && m.a(this.d, iVar.d);
    }

    public final int hashCode() {
        int iD = AbstractC1347d.d(M5.b.c(this.f4272b, this.f4271a.hashCode() * 31, 31), 31, this.c);
        e eVar = this.d;
        return iD + (eVar == null ? 0 : eVar.hashCode());
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TextSubstitution(layoutCache=");
        sb.append(this.d);
        sb.append(", isShowingSubstitution=");
        return AbstractC1347d.i(sb, this.c, ')');
    }
}
