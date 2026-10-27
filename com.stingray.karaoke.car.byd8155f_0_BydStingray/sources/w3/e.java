package W3;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class e implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f9767a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final V3.a f9768b;
    public final V3.a c;
    public final V3.a d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final V3.a f9769e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final V3.b f9770f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f9771g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f9772h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float f9773i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ArrayList f9774j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final V3.b f9775k;
    public final boolean l;

    public e(String str, int i7, V3.a aVar, V3.a aVar2, V3.a aVar3, V3.a aVar4, V3.b bVar, int i10, int i11, float f10, ArrayList arrayList, V3.b bVar2, boolean z6) {
        this.f9767a = i7;
        this.f9768b = aVar;
        this.c = aVar2;
        this.d = aVar3;
        this.f9769e = aVar4;
        this.f9770f = bVar;
        this.f9771g = i10;
        this.f9772h = i11;
        this.f9773i = f10;
        this.f9774j = arrayList;
        this.f9775k = bVar2;
        this.l = z6;
    }

    @Override // W3.b
    public final Q3.c a(O3.j jVar, O3.a aVar, X3.b bVar) {
        return new Q3.i(jVar, bVar, this);
    }
}
