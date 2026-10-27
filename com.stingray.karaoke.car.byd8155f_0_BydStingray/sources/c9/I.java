package c9;

import java.util.regex.Pattern;
import r9.C2319i;
import r9.InterfaceC2321k;

/* JADX INFO: loaded from: classes.dex */
public final class I extends B3.m {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f12526m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f12527n;
    public final InterfaceC2321k o;

    public /* synthetic */ I(Object obj, long j3, InterfaceC2321k interfaceC2321k, int i7) {
        this.l = i7;
        this.f12527n = obj;
        this.f12526m = j3;
        this.o = interfaceC2321k;
    }

    @Override // B3.m
    public final long i() {
        switch (this.l) {
        }
        return this.f12526m;
    }

    @Override // B3.m
    public final y j() {
        Object obj = this.f12527n;
        switch (this.l) {
            case 0:
                return (y) obj;
            default:
                String str = (String) obj;
                if (str == null) {
                    return null;
                }
                Pattern pattern = y.d;
                return P8.n.L(str);
        }
    }

    @Override // B3.m
    public final InterfaceC2321k v() {
        switch (this.l) {
            case 0:
                return (C2319i) this.o;
            default:
                return (r9.D) this.o;
        }
    }
}
