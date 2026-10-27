package T;

import z7.InterfaceC2772a;

/* JADX INFO: renamed from: T.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0773y extends AbstractC0750k0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f8388b = 0;
    public final Object c;

    public C0773y(G0 g02, InterfaceC2772a interfaceC2772a) {
        super(interfaceC2772a);
        this.c = g02;
    }

    @Override // T.AbstractC0750k0
    public final C0752l0 a(Object obj) {
        switch (this.f8388b) {
            case 0:
                return new C0752l0(this, obj, obj == null, null, true);
            default:
                return new C0752l0(this, obj, obj == null, (G0) this.c, true);
        }
    }

    @Override // T.AbstractC0750k0
    public T0 b() {
        switch (this.f8388b) {
            case 0:
                return (C0774z) this.c;
            default:
                return super.b();
        }
    }

    public C0773y() {
        super(C0729a.o);
        this.c = new C0774z();
    }
}
