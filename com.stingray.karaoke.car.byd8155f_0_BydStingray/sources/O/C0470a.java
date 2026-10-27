package O;

import z7.InterfaceC2772a;

/* JADX INFO: renamed from: O.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0470a extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4943m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0480k f4944n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0470a(InterfaceC0480k interfaceC0480k, int i7) {
        super(0);
        this.f4943m = i7;
        this.f4944n = interfaceC0480k;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f4943m) {
        }
        return Boolean.valueOf(h6.e.m(this.f4944n.a()));
    }
}
