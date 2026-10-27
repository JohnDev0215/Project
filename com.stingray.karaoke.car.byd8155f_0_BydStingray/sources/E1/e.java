package e1;

import java.util.UUID;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class e extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final e f13774n = new e(0, 0);
    public static final e o = new e(0, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final e f13775p = new e(0, 2);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13776m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(int i7, int i10) {
        super(i7);
        this.f13776m = i10;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f13776m) {
        }
        return UUID.randomUUID();
    }
}
