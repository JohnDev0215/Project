package defpackage;

import J.N;
import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.preference.Preference;
import java.util.regex.Pattern;
import org.chromium.components.browser_ui.settings.ChromeImageViewPreference;
import org.chromium.components.browser_ui.site_settings.R$color;
import org.chromium.components.browser_ui.site_settings.R$dimen;
import org.chromium.components.browser_ui.site_settings.R$id;
import org.chromium.components.browser_ui.site_settings.R$layout;
import org.chromium.components.browser_ui.site_settings.R$plurals;
import org.chromium.components.browser_ui.site_settings.R$string;
import org.chromium.components.browser_ui.site_settings.f;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: WebsitePreference.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dt3 extends ChromeImageViewPreference {
    public final zn2 b0;
    public final f c0;
    public final yn2 d0;
    public boolean e0;

    public dt3(Context context, zn2 zn2Var, f fVar, yn2 yn2Var) {
        jj0 jj0Var;
        super(context);
        this.b0 = zn2Var;
        this.c0 = fVar;
        this.d0 = yn2Var;
        this.K = R$layout.website_features;
        L(new ColorDrawable(0));
        T(fVar.h());
        boolean zB = ((kr) zn2Var).b();
        Context context2 = this.e;
        if (zB && N.MhilDEgf() && (jj0Var = fVar.j) != null) {
            Resources resources = context2.getResources();
            int i = R$plurals.allsites_fps_list_summary;
            int i2 = jj0Var.f;
            R(resources.getQuantityString(i, i2, Integer.toString(i2), jj0Var.e));
            return;
        }
        ts3 ts3Var = fVar.f;
        if (ts3Var != null) {
            R(fVar.l() ? context2.getString(R$string.website_settings_third_party_cookies_exception_label) : String.format(context2.getString(R$string.website_settings_embedded_on), ts3Var.l()));
            return;
        }
        if (fVar.k(yn2Var.f())) {
            R(context2.getString(R$string.automatically_blocked));
            return;
        }
        if (yn2Var.b == 24 && N.MIXz$64w("RequestDesktopSiteExceptions", "SubdomainSettings", true)) {
            ts3 ts3Var2 = fVar.e;
            if (ts3Var2.e.startsWith("[*.]")) {
                R(String.format(context2.getString(R$string.website_settings_domain_exception_label), ts3Var2.h));
            }
        }
    }

    @Override // androidx.preference.Preference, java.lang.Comparable
    /* JADX INFO: renamed from: i */
    public final int compareTo(Preference preference) {
        if (!(preference instanceof dt3)) {
            return super.compareTo(preference);
        }
        dt3 dt3Var = (dt3) preference;
        int i = this.d0.b;
        f fVar = this.c0;
        if (i != 22) {
            return fVar.b(dt3Var.c0);
        }
        f fVar2 = dt3Var.c0;
        fVar.getClass();
        if (fVar == fVar2) {
            return 0;
        }
        long j = fVar2.j();
        long j2 = fVar.j();
        if (j < j2) {
            return -1;
        }
        return j == j2 ? 0 : 1;
    }

    @Override // org.chromium.components.browser_ui.settings.ChromeImageViewPreference, androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        TextView textView = (TextView) n32Var.u(R.id.title);
        Context context = this.e;
        if (textView != null) {
            textView.setTextColor(context.getColor(R$color.settings_preference_title_text_color));
            textView.setTextSize(0, context.getResources().getDimensionPixelSize(R$dimen.preference_title_text_size));
        }
        TextView textView2 = (TextView) n32Var.u(R.id.summary);
        if (textView2 != null) {
            textView2.setTextColor(context.getColor(R$color.settings_preference_summary_text_color));
            textView2.setTextSize(0, context.getResources().getDimensionPixelSize(R$dimen.preference_title_summary_size));
        }
        TextView textView3 = (TextView) n32Var.u(R$id.usage_text);
        textView3.setVisibility(8);
        int i = this.d0.b;
        f fVar = this.c0;
        if (i == 22) {
            long j = fVar.j();
            if (j > 0) {
                textView3.setText(Formatter.formatShortFileSize(context, j));
                textView3.setTextSize(0, context.getResources().getDimensionPixelSize(R$dimen.preference_title_text_size));
                textView3.setTextColor(context.getColor(R$color.settings_preference_summary_text_color));
                textView3.setVisibility(0);
            }
        }
        tk0.a(context.getResources(), (ImageView) n32Var.u(R.id.icon));
        if (this.e0) {
            return;
        }
        GURL gurl = new GURL((fVar.l() ? fVar.f : fVar.e).h());
        Pattern pattern = bk3.a;
        if (!TextUtils.isEmpty(gurl.h())) {
            gurl = (GURL) N.MGM8OMf9(gurl);
        }
        x10 x10Var = new x10(28, this);
        kr krVar = (kr) this.b0;
        if (krVar.e == null) {
            krVar.e = new LargeIconBridge(krVar.b);
        }
        ok0.a(krVar.a, krVar.e, gurl, x10Var);
        this.e0 = true;
    }
}
