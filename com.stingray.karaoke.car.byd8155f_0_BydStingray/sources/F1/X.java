package F1;

import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;

/* JADX INFO: loaded from: classes.dex */
public class X extends com.bumptech.glide.c {
    public final WindowInsetsController d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Window f2322e;

    public X(Window window) {
        this.d = window.getInsetsController();
        this.f2322e = window;
    }

    @Override // com.bumptech.glide.c
    public final void A0(boolean z6) {
        Window window = this.f2322e;
        if (z6) {
            if (window != null) {
                View decorView = window.getDecorView();
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 8192);
            }
            this.d.setSystemBarsAppearance(8, 8);
            return;
        }
        if (window != null) {
            View decorView2 = window.getDecorView();
            decorView2.setSystemUiVisibility(decorView2.getSystemUiVisibility() & (-8193));
        }
        this.d.setSystemBarsAppearance(0, 8);
    }

    @Override // com.bumptech.glide.c
    public boolean n0() {
        this.d.setSystemBarsAppearance(0, 0);
        return (this.d.getSystemBarsAppearance() & 16) != 0;
    }

    @Override // com.bumptech.glide.c
    public boolean o0() {
        this.d.setSystemBarsAppearance(0, 0);
        return (this.d.getSystemBarsAppearance() & 8) != 0;
    }

    @Override // com.bumptech.glide.c
    public final void z0(boolean z6) {
        Window window = this.f2322e;
        if (z6) {
            if (window != null) {
                View decorView = window.getDecorView();
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 16);
            }
            this.d.setSystemBarsAppearance(16, 16);
            return;
        }
        if (window != null) {
            View decorView2 = window.getDecorView();
            decorView2.setSystemUiVisibility(decorView2.getSystemUiVisibility() & (-17));
        }
        this.d.setSystemBarsAppearance(0, 16);
    }
}
