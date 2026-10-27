package F0;

import H7.InterfaceC0317b;
import H7.InterfaceC0323h;
import H7.InterfaceC0324i;
import androidx.compose.ui.platform.AndroidComposeView;

/* JADX INFO: renamed from: F0.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0226s extends A7.q implements InterfaceC0324i {
    @Override // z7.InterfaceC2772a
    public final Object a() {
        return get();
    }

    @Override // A7.AbstractC0056d
    public final InterfaceC0317b f() {
        return A7.A.f443a.e(this);
    }

    @Override // H7.r
    public final Object get() {
        return ((AndroidComposeView) this.f455m).getLayoutDirection();
    }

    @Override // H7.w
    public final H7.q c() {
        return ((InterfaceC0324i) l()).c();
    }

    @Override // H7.l
    public final InterfaceC0323h d() {
        return ((InterfaceC0324i) l()).d();
    }
}
