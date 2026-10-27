package g.a.b.j0;

/* JADX INFO: loaded from: classes.dex */
public class f extends b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public i f5021d;

    public f(boolean z, i iVar) {
        super(z);
        this.f5021d = iVar;
    }

    public i b() {
        return this.f5021d;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof f)) {
            return false;
        }
        i iVar = this.f5021d;
        i iVarB = ((f) obj).b();
        return iVar == null ? iVarB == null : iVar.equals(iVarB);
    }

    public int hashCode() {
        int i2 = !a() ? 1 : 0;
        i iVar = this.f5021d;
        return iVar != null ? i2 ^ iVar.hashCode() : i2;
    }
}
