package W3;

import Q3.r;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class o implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final V3.b f9801a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f9802b;
    public final V3.a c;
    public final V3.a d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final V3.b f9803e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f9804f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f9805g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f9806h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f9807i;

    public o(String str, V3.b bVar, ArrayList arrayList, V3.a aVar, V3.a aVar2, V3.b bVar2, int i7, int i10, float f10, boolean z6) {
        this.f9801a = bVar;
        this.f9802b = arrayList;
        this.c = aVar;
        this.d = aVar2;
        this.f9803e = bVar2;
        this.f9804f = i7;
        this.f9805g = i10;
        this.f9806h = f10;
        this.f9807i = z6;
    }

    @Override // W3.b
    public final Q3.c a(O3.j jVar, O3.a aVar, X3.b bVar) {
        return new r(jVar, bVar, this);
    }
}
