package C0;

import androidx.compose.ui.platform.AndroidComposeView;

/* JADX INFO: loaded from: classes.dex */
public final class H extends U {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f862b;
    public final Object c;

    public /* synthetic */ H(Object obj, int i7) {
        this.f862b = i7;
        this.c = obj;
    }

    @Override // C0.U
    public final a1.k b() {
        switch (this.f862b) {
            case 0:
                return ((E0.Q) this.c).getLayoutDirection();
            default:
                return ((AndroidComposeView) this.c).getLayoutDirection();
        }
    }

    @Override // C0.U
    public final int c() {
        switch (this.f862b) {
            case 0:
                return ((E0.Q) this.c).h0();
            default:
                return ((AndroidComposeView) this.c).getRoot().I();
        }
    }
}
