package T;

import android.os.Looper;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: T.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0729a extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C0729a f8229n = new C0729a(0, 0);
    public static final C0729a o = new C0729a(0, 1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f8230m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0729a(int i7, int i10) {
        super(i7);
        this.f8230m = i10;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f8230m) {
            case 0:
                return Looper.getMainLooper() != null ? B.l : x0.l;
            default:
                C0735d.z("Unexpected call to default provider");
                throw null;
        }
    }
}
