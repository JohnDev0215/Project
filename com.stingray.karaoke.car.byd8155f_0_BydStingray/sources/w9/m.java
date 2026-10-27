package w9;

import n3.z;

/* JADX INFO: loaded from: classes.dex */
public final class m extends z {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f23376g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f23377h;

    public m(String str, String str2) {
        this.f23376g = str;
        this.f23377h = str2;
    }

    @Override // n3.z
    public final void a(B8.t tVar) {
        tVar.r0(this);
    }

    @Override // n3.z
    public final String g() {
        return "destination=" + this.f23376g + ", title=" + this.f23377h;
    }
}
