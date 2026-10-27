package W3;

import android.graphics.Path;

/* JADX INFO: loaded from: classes.dex */
public final class d implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f9762a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Path.FillType f9763b;
    public final V3.a c;
    public final V3.a d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final V3.a f9764e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final V3.a f9765f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f9766g;

    public d(String str, int i7, Path.FillType fillType, V3.a aVar, V3.a aVar2, V3.a aVar3, V3.a aVar4, boolean z6) {
        this.f9762a = i7;
        this.f9763b = fillType;
        this.c = aVar;
        this.d = aVar2;
        this.f9764e = aVar3;
        this.f9765f = aVar4;
        this.f9766g = z6;
    }

    @Override // W3.b
    public final Q3.c a(O3.j jVar, O3.a aVar, X3.b bVar) {
        return new Q3.h(jVar, aVar, bVar, this);
    }
}
