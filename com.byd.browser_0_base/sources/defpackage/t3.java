package defpackage;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.webapps.R;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: AddToHomescreenIPHController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class t3 {
    public Activity a;
    public final WindowAndroid b;
    public final bi1 c;
    public final vg1 d;
    public final xe3 e = oj2.t(Profile.d());

    public t3(AppCompatActivity appCompatActivity, g3 g3Var, bi1 bi1Var, vg1 vg1Var) {
        this.a = appCompatActivity;
        this.b = g3Var;
        this.c = bi1Var;
        this.d = vg1Var;
    }

    public final void a(Tab tab) {
        PropertyModel.b bVar = new PropertyModel.b(ng1.H);
        bVar.c(ng1.a, 8);
        bVar.e(ng1.l, re3.a(this.a.getResources(), R.drawable.ic_apps_blue_24dp, this.a.getTheme()));
        bVar.e(ng1.f, this.a.getResources().getString(R.string.iph_message_add_to_home_screen_title));
        bVar.e(ng1.h, this.a.getResources().getString(R.string.iph_message_add_to_home_screen_description));
        bVar.e(ng1.c, this.a.getResources().getString(R.string.iph_message_add_to_home_screen_action));
        bVar.e(ng1.x, new l51(2, this));
        bVar.e(ng1.d, new k43(this, 2, tab));
        this.d.b(bVar.a(), tab.c(), 2, false);
        n3.X0("Android.AddToHomescreenIPH.Message.Shown");
    }
}
