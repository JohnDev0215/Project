package F0;

import androidx.compose.ui.platform.AndroidComposeView;
import r7.EnumC2303a;
import s7.AbstractC2410c;

/* JADX INFO: renamed from: F0.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0240z extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ AndroidComposeView f2299m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f2300n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0240z(AndroidComposeView androidComposeView, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f2299m = androidComposeView;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        this.l = obj;
        this.f2300n |= Integer.MIN_VALUE;
        this.f2299m.E(null, this);
        return EnumC2303a.l;
    }
}
