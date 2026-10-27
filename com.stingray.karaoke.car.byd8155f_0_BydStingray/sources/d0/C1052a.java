package d0;

import l3.P;
import m7.q;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: d0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1052a extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1053b f13250m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ n f13251n;
    public final /* synthetic */ k o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ String f13252p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f13253q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object[] f13254r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1052a(C1053b c1053b, n nVar, k kVar, String str, Object obj, Object[] objArr) {
        super(0);
        this.f13250m = c1053b;
        this.f13251n = nVar;
        this.o = kVar;
        this.f13252p = str;
        this.f13253q = obj;
        this.f13254r = objArr;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        boolean z6;
        C1053b c1053b = this.f13250m;
        k kVar = c1053b.f13255m;
        k kVar2 = this.o;
        boolean z10 = true;
        if (kVar != kVar2) {
            c1053b.f13255m = kVar2;
            z6 = true;
        } else {
            z6 = false;
        }
        String str = c1053b.f13256n;
        String str2 = this.f13252p;
        if (A7.m.a(str, str2)) {
            z10 = z6;
        } else {
            c1053b.f13256n = str2;
        }
        c1053b.l = this.f13251n;
        c1053b.o = this.f13253q;
        c1053b.f13257p = this.f13254r;
        j jVar = c1053b.f13258q;
        if (jVar != null && z10) {
            ((P) jVar).H();
            c1053b.f13258q = null;
            c1053b.b();
        }
        return q.f19267a;
    }
}
