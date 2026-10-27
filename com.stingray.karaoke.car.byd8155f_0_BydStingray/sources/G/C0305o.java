package G;

import C0.InterfaceC0097d;

/* JADX INFO: renamed from: G.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0305o implements InterfaceC0097d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0306p f2603a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ A7.z f2604b;
    public final /* synthetic */ int c;

    public C0305o(C0306p c0306p, A7.z zVar, int i7) {
        this.f2603a = c0306p;
        this.f2604b = zVar;
        this.c = i7;
    }

    @Override // C0.InterfaceC0097d
    public final boolean a() {
        return this.f2603a.i((C0303m) this.f2604b.l, this.c);
    }
}
