package defpackage;

import J.N;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Collections;
import java.util.HashSet;
import org.chromium.base.SysUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.browser_ui.site_settings.WebsitePreferenceBridge;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: compiled from: DesktopSiteSettingsIPHController.java */
/* JADX INFO: loaded from: classes.dex */
public final class t80 {
    public final pk3 a;
    public final h7 b;
    public final View c;
    public final WebsitePreferenceBridge d;
    public s80 e;
    public final HashSet f;

    public t80(g3 g3Var, z2 z2Var, Profile profile, ImageButton imageButton, i7 i7Var, pk3 pk3Var, WebsitePreferenceBridge websitePreferenceBridge, double d) {
        HashSet hashSet = new HashSet();
        this.f = hashSet;
        this.c = imageButton;
        this.b = i7Var;
        this.a = pk3Var;
        this.d = websitePreferenceBridge;
        sk skVar = bq.a;
        boolean z = false;
        boolean zM6bsIDpc = N.M6bsIDpc("RequestDesktopSitePerSiteIph", "iph_type_generic", false);
        if (N.M6bsIDpc("RequestDesktopSitePerSiteIph", "iph_type_specific", false) && hashSet.isEmpty()) {
            Collections.addAll(hashSet, N.MMltG$kc("RequestDesktopSitePerSiteIph", "site_list").split(","));
            this.e = new s80(z2Var, new gp(this, 9, profile));
            return;
        }
        if (zM6bsIDpc) {
            boolean z2 = true;
            if (DeviceFormFactor.b(g3Var) && d >= N.MQdjXFDB("RequestDesktopSitePerSiteIph", "generic_iph_screen_size_threshold_inches", 0.0d)) {
                int iM37SqSAy = N.M37SqSAy("RequestDesktopSitePerSiteIph", "generic_iph_memory_threshold_mb", 0);
                if (iM37SqSAy != 0 && SysUtils.amountOfPhysicalMemoryKB() < iM37SqSAy * 1024) {
                    z = true;
                }
                z2 = z;
            }
            if (z2) {
                return;
            }
            this.e = new s80(z2Var, new n62(this, 5, profile));
        }
    }

    public static t80 a(AppCompatActivity appCompatActivity, g3 g3Var, z2 z2Var, Profile profile, ImageButton imageButton, i7 i7Var, double d) {
        sk skVar = bq.a;
        if (N.M09VlOh_("RequestDesktopSitePerSiteIph")) {
            return new t80(g3Var, z2Var, profile, imageButton, i7Var, new pk3(appCompatActivity, new Handler(Looper.getMainLooper())), new WebsitePreferenceBridge(), d);
        }
        return null;
    }

    public static boolean b(Tab tab, xe3 xe3Var, String str) {
        return !xe3Var.wouldTriggerHelpUI(str) || tab.isIncognito() || bk3.j(tab.getUrl()) || tab.c() == null;
    }

    public final void c(String str, int i, Object[] objArr) {
        View view = this.c;
        hw0 hw0Var = new hw0(view.getContext().getResources(), str, i, objArr, i, objArr);
        hw0Var.j = view;
        hw0Var.k = new wq0(28, this);
        hw0Var.m = new db3(1, this);
        this.a.a(hw0Var.a());
    }
}
