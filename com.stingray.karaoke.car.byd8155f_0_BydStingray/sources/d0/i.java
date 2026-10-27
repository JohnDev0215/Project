package d0;

import java.util.LinkedHashMap;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class i extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final i f13270n = new i(0, 0);
    public static final i o = new i(0, 1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13271m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i(int i7, int i10) {
        super(i7);
        this.f13271m = i10;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f13271m) {
            case 0:
                return new h(new LinkedHashMap());
            default:
                return null;
        }
    }
}
