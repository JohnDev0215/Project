package W3;

import android.graphics.Path;
import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class l implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f9794a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Path.FillType f9795b;
    public final V3.a c;
    public final V3.a d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f9796e;

    public l(String str, boolean z6, Path.FillType fillType, V3.a aVar, V3.a aVar2, boolean z10) {
        this.f9794a = z6;
        this.f9795b = fillType;
        this.c = aVar;
        this.d = aVar2;
        this.f9796e = z10;
    }

    @Override // W3.b
    public final Q3.c a(O3.j jVar, O3.a aVar, X3.b bVar) {
        return new Q3.g(jVar, bVar, this);
    }

    public final String toString() {
        return AbstractC1347d.i(new StringBuilder("ShapeFill{color=, fillEnabled="), this.f9794a, '}');
    }
}
