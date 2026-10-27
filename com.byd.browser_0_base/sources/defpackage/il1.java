package defpackage;

import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: NavigationSheetMediator.java */
/* JADX INFO: loaded from: classes.dex */
public final class il1 {
    public final a a;
    public final FaviconHelper b = new FaviconHelper();
    public final uc2 c;
    public final int d;
    public final ya1 e;
    public final za3 f;
    public final za3 g;
    public final za3 h;
    public final String i;
    public final String j;
    public final Profile k;
    public xk1 l;

    /* JADX INFO: compiled from: NavigationSheetMediator.java */
    public interface a {
    }

    /* JADX INFO: compiled from: NavigationSheetMediator.java */
    public static class b {
        public static final PropertyModel.r<Drawable> a;
        public static final PropertyModel.r<String> b;
        public static final PropertyModel.r<View.OnClickListener> c;
        public static final v62[] d;

        static {
            PropertyModel.r<Drawable> rVar = new PropertyModel.r<>();
            a = rVar;
            PropertyModel.r<String> rVar2 = new PropertyModel.r<>();
            b = rVar2;
            PropertyModel.r<View.OnClickListener> rVar3 = new PropertyModel.r<>();
            c = rVar3;
            d = new v62[]{rVar, rVar2, rVar3};
        }
    }

    public il1(AppCompatActivity appCompatActivity, ya1 ya1Var, Profile profile, pq2 pq2Var) {
        this.e = ya1Var;
        this.a = pq2Var;
        this.k = profile;
        this.c = org.chromium.chrome.browser.ui.favicon.a.a(appCompatActivity);
        this.d = appCompatActivity.getResources().getDimensionPixelSize(R$dimen.default_favicon_size);
        int i = R$drawable.ic_history_googblue_24dp;
        int i2 = R$color.default_icon_color_tint_list;
        this.f = za3.b(appCompatActivity, i, i2);
        this.g = za3.b(appCompatActivity, R$drawable.ic_chrome, i2);
        this.h = za3.b(appCompatActivity, R$drawable.incognito_small, i2);
        this.i = appCompatActivity.getResources().getString(R$string.menu_new_tab);
        this.j = appCompatActivity.getResources().getString(R$string.menu_new_incognito_tab);
    }
}
