package A;

import T.C0735d;
import T.InterfaceC0744h0;
import android.content.Context;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import y0.AbstractC2737o;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: A.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0014g extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C0014g f174n = new C0014g(1, 0);
    public static final C0014g o = new C0014g(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C0014g f175p = new C0014g(1, 2);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f176m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0014g(int i7, int i10) {
        super(i7);
        this.f176m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f176m) {
            case 0:
                InterfaceC0744h0 interfaceC0744h0 = (InterfaceC0744h0) obj;
                T.R0 r02 = AndroidCompositionLocals_androidKt.f11688b;
                interfaceC0744h0.getClass();
                if (((Context) C0735d.P(interfaceC0744h0, r02)).getPackageManager().hasSystemFeature("android.software.leanback")) {
                    return AbstractC0018i.f184b;
                }
                InterfaceC0012f.f168a.getClass();
                return C0010e.c;
            case 1:
                return Boolean.TRUE;
            default:
                return Boolean.valueOf(!AbstractC2737o.e(((y0.q) obj).f24083i, 2));
        }
    }
}
