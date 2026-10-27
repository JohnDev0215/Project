package p1;

/* JADX INFO: renamed from: p1.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C2080h extends C2079g {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f20669m;

    public C2080h(p pVar) {
        super(pVar);
        if (pVar instanceof l) {
            this.f20662e = 2;
        } else {
            this.f20662e = 3;
        }
    }

    @Override // p1.C2079g
    public final void d(int i7) {
        if (this.f20667j) {
            return;
        }
        this.f20667j = true;
        this.f20664g = i7;
        for (InterfaceC2077e interfaceC2077e : this.f20668k) {
            interfaceC2077e.a(interfaceC2077e);
        }
    }
}
