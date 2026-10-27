package F0;

import r7.EnumC2303a;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class J0 extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f2044m;

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.f2044m |= Integer.MIN_VALUE;
        K0.b(null, null, this);
        return EnumC2303a.l;
    }
}
