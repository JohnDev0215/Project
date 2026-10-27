package n3;

import android.os.Bundle;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class u extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f19895m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Bundle f19896n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ u(int i7, Bundle bundle) {
        super(1);
        this.f19895m = i7;
        this.f19896n = bundle;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f19895m) {
            case 0:
                A7.m.f((String) obj, "argName");
                return Boolean.valueOf(!this.f19896n.containsKey(r2));
            default:
                A7.m.f((String) obj, "key");
                return Boolean.valueOf(!this.f19896n.containsKey(r2));
        }
    }
}
