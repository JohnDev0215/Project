package F1;

import android.view.View;
import android.view.Window;

/* JADX INFO: loaded from: classes.dex */
public final class W extends com.bumptech.glide.c {
    public final Window d;

    public W(Window window) {
        this.d = window;
    }

    @Override // com.bumptech.glide.c
    public final void A0(boolean z6) {
        Window window = this.d;
        if (!z6) {
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & (-8193));
        } else {
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
            View decorView2 = window.getDecorView();
            decorView2.setSystemUiVisibility(decorView2.getSystemUiVisibility() | 8192);
        }
    }

    @Override // com.bumptech.glide.c
    public final boolean n0() {
        return (this.d.getDecorView().getSystemUiVisibility() & 16) != 0;
    }

    @Override // com.bumptech.glide.c
    public final boolean o0() {
        return (this.d.getDecorView().getSystemUiVisibility() & 8192) != 0;
    }

    @Override // com.bumptech.glide.c
    public final void z0(boolean z6) {
        Window window = this.d;
        if (!z6) {
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & (-17));
        } else {
            window.clearFlags(134217728);
            window.addFlags(Integer.MIN_VALUE);
            View decorView2 = window.getDecorView();
            decorView2.setSystemUiVisibility(decorView2.getSystemUiVisibility() | 16);
        }
    }
}
