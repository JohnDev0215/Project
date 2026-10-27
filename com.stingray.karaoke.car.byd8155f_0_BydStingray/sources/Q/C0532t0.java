package Q;

import java.util.LinkedHashMap;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q.t0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0532t0 extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0540x0 f6045m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f6046n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f6047p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0532t0(C0540x0 c0540x0, boolean z6, boolean z10, boolean z11) {
        super(2);
        this.f6045m = c0540x0;
        this.f6046n = z6;
        this.o = z10;
        this.f6047p = z11;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        long j3 = ((a1.j) obj).f11037a;
        long j10 = ((a1.a) obj2).f11027a;
        float f10 = (int) (j3 >> 32);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(EnumC0542y0.f6072n, Float.valueOf(0.0f));
        boolean z6 = this.f6046n;
        boolean z10 = this.o;
        if (z6) {
            linkedHashMap.put(EnumC0542y0.l, Float.valueOf(z10 ? -f10 : f10));
        }
        if (this.f6047p) {
            EnumC0542y0 enumC0542y0 = EnumC0542y0.f6071m;
            if (!z10) {
                f10 = -f10;
            }
            linkedHashMap.put(enumC0542y0, Float.valueOf(f10));
        }
        return new m7.i(new R.E(linkedHashMap), (EnumC0542y0) this.f6045m.f6069b.f7330g.getValue());
    }
}
