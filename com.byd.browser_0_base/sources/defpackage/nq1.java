package defpackage;

import android.app.Activity;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.ft2;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.toolbar.g;

/* JADX INFO: compiled from: OfflineIndicatorInProductHelpController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class nq1 implements ft2.a {
    public final Activity e;
    public final g f;
    public final h7 g;
    public final pk3 h;
    public final ft2 i;

    public nq1(AppCompatActivity appCompatActivity, g gVar, i7 i7Var, ft2 ft2Var) {
        Handler handler = new Handler();
        this.e = appCompatActivity;
        this.f = gVar;
        this.g = i7Var;
        this.h = new pk3(appCompatActivity, handler);
        this.i = ft2Var;
        ft2Var.a.g.add(this);
    }

    @Override // ft2.a
    public final void d() {
        hq1.a().getClass();
        if (SharedPreferencesManager.getInstance().readBoolean("Chrome.OfflineIndicatorV2.HasPersistentOfflineContent", false)) {
            g gVar = this.f;
            if (gVar.h().isShown()) {
                hw0 hw0Var = new hw0(this.e.getResources(), "IPH_DownloadIndicator", R$string.iph_download_indicator_text, R$string.iph_download_home_accessibility_text);
                hw0Var.j = gVar.h();
                hw0Var.k = new qm0(13, this);
                hw0Var.m = new zc0(20, this);
                this.h.a(hw0Var.a());
            }
        }
    }
}
