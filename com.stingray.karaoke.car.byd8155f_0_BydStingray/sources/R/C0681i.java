package R;

import s7.AbstractC2410c;

/* JADX INFO: renamed from: R.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0681i extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f7299m;

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.f7299m |= Integer.MIN_VALUE;
        return androidx.compose.material3.internal.a.a(null, null, this);
    }
}
