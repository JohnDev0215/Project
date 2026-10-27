package d0;

import java.util.Map;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: d0.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1056e extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C1056e f13262n = new C1056e(1, 0);
    public static final C1056e o = new C1056e(1, 1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13263m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1056e(int i7, int i10) {
        super(i7);
        this.f13263m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f13263m) {
            case 0:
                return new h((Map) obj);
            default:
                return obj;
        }
    }
}
