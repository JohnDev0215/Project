package s0;

import android.graphics.PathMeasure;
import n0.C1881j;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: s0.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2338e extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C2338e f22020n = new C2338e(0, 0);
    public static final C2338e o = new C2338e(0, 1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f22021m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C2338e(int i7, int i10) {
        super(i7);
        this.f22021m = i10;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f22021m) {
            case 0:
                return new C1881j(new PathMeasure());
            default:
                return m7.q.f19267a;
        }
    }
}
