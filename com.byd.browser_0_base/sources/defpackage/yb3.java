package defpackage;

import android.app.Activity;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.download.DownloadUtils;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: ToolbarButtonInProductHelpController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class yb3 implements ue2, hy1 {
    public final i20 e;
    public final Activity f;
    public final WindowAndroid g;
    public final q2 h;
    public final ve2 i;
    public final Handler j;
    public final View k;
    public final h7 l;
    public final pk3 m;
    public final zp1 n;
    public final nw2<Boolean> o;

    public yb3(AppCompatActivity appCompatActivity, g3 g3Var, d7 d7Var, q2 q2Var, z2 z2Var, nw2 nw2Var, ImageButton imageButton) {
        Handler handler = new Handler();
        this.j = handler;
        this.f = appCompatActivity;
        this.g = g3Var;
        this.l = d7Var.c;
        this.k = imageButton;
        this.o = nw2Var;
        this.m = new pk3(appCompatActivity, handler);
        this.i = new ve2(this);
        this.h = q2Var;
        q2Var.f(this);
        this.n = z2Var;
        this.e = new i20(z2Var, new xb3(this), null);
        vk0.b(3, new ub3(this, 0));
    }

    public final void a(Tab tab, String str) {
        if (tab == null || DeviceFormFactor.b(this.g)) {
            return;
        }
        nw2<Boolean> nw2Var = this.o;
        if ((nw2Var.get() == null || !nw2Var.get().booleanValue()) && DownloadUtils.f(tab)) {
            hw0 hw0Var = new hw0(this.f.getResources(), str, R$string.iph_download_page_for_offline_usage_text, R$string.iph_download_page_for_offline_usage_accessibility_text);
            hw0Var.k = new ub3(this, 1);
            hw0Var.m = new vb3(this, 1);
            hw0Var.j = this.k;
            this.m.a(hw0Var.a());
            af2 af2VarY1 = af2.y1(tab);
            if (af2VarY1 == null || af2VarY1.e <= 0) {
                return;
            }
            af2VarY1.f = 2;
        }
    }

    @Override // defpackage.hy1
    public final void f() {
        if (DeviceFormFactor.b(this.g)) {
            return;
        }
        ve2 ve2Var = this.i;
        ve2Var.getClass();
        Object obj = ThreadUtils.a;
        if (!ve2Var.b) {
            ve2Var.b = true;
            xz.a.getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, ve2Var.c);
        }
        this.j.post(new vb3(this, 0));
    }

    @Override // defpackage.hy1
    public final void h() {
        ve2 ve2Var = this.i;
        ve2Var.getClass();
        Object obj = ThreadUtils.a;
        if (ve2Var.b) {
            ve2Var.b = false;
            xz.a.getContentResolver().unregisterContentObserver(ve2Var.c);
        }
    }
}
