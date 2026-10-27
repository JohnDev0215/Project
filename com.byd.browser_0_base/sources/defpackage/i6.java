package defpackage;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import org.chromium.chrome.browser.banners.R;

/* JADX INFO: compiled from: AppBannerInProductHelpController.java */
/* JADX INFO: loaded from: classes.dex */
public final class i6 implements zi3 {
    public final Activity e;
    public final h7 f;
    public final nw2<View> g;
    public final pk3 h;
    public final int i;

    public i6(AppCompatActivity appCompatActivity, i7 i7Var, yo yoVar, int i) {
        Handler handler = new Handler();
        this.e = appCompatActivity;
        this.f = i7Var;
        this.g = yoVar;
        this.i = i;
        this.h = new pk3(appCompatActivity, handler);
    }

    public final void a() {
        hw0 hw0Var = new hw0(this.e.getResources(), "IPH_PwaInstallAvailableFeature", R.string.iph_pwa_install_available_text, R.string.iph_pwa_install_available_text);
        pk3 pk3Var = this.h;
        hw0Var.b(this.g.get());
        hw0Var.d(new aw(9, this));
        hw0Var.c(new wq0(13, this));
        pk3Var.a(hw0Var.a());
    }
}
