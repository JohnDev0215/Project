package E0;

import java.util.LinkedHashMap;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: E0.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0159i extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C0159i f1674n = new C0159i(0, 0);
    public static final C0159i o = new C0159i(0, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C0159i f1675p = new C0159i(0, 2);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1676m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0159i(int i7, int i10) {
        super(i7);
        this.f1676m = i10;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f1676m) {
            case 0:
                return new F(2, 0, true);
            case 1:
                return new LinkedHashMap();
            default:
                return new F(3, 0, false);
        }
    }
}
