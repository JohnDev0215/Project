package d7;

import b7.C1003d;

/* JADX INFO: loaded from: classes.dex */
public final class c implements Runnable {
    public final /* synthetic */ o[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ i f13490m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ j f13491n;
    public final /* synthetic */ b o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ l f13492p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ b f13493q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ C1003d f13494r;

    public c(o[] oVarArr, i iVar, j jVar, b bVar, l lVar, b bVar2, C1003d c1003d) {
        this.l = oVarArr;
        this.f13490m = iVar;
        this.f13491n = jVar;
        this.o = bVar;
        this.f13492p = lVar;
        this.f13493q = bVar2;
        this.f13494r = c1003d;
    }

    @Override // java.lang.Runnable
    public final void run() {
        o[] oVarArr = this.l;
        oVarArr[0].R0("open", this.f13490m);
        oVarArr[0].R0("error", this.f13491n);
        oVarArr[0].R0("close", this.o);
        b bVar = this.f13493q;
        l lVar = this.f13492p;
        lVar.R0("close", bVar);
        lVar.R0("upgrading", this.f13494r);
    }
}
