package org.chromium.components.browser_ui.site_settings;

import android.R;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.Formatter;
import android.widget.ImageView;
import defpackage.kr;
import defpackage.n32;
import defpackage.ok0;
import defpackage.se3;
import defpackage.tk0;
import defpackage.ws3;
import defpackage.zn2;
import org.chromium.components.browser_ui.settings.ChromeImageViewPreference;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class WebsiteRowPreference extends ChromeImageViewPreference {
    public final zn2 b0;
    public final ws3 c0;
    public boolean d0;

    public WebsiteRowPreference(Context context, zn2 zn2Var, ws3 ws3Var) {
        super(context);
        this.b0 = zn2Var;
        this.c0 = ws3Var;
        L(new ColorDrawable(0));
        T(ws3Var.g());
        Z(R$drawable.ic_delete_white_24dp, R$string.webstorage_clear_data_dialog_title, null);
        long j = ws3Var.j();
        Context context2 = this.e;
        String shortFileSize = j > 0 ? Formatter.formatShortFileSize(context2, j) : "";
        int i = ws3Var.i();
        if (i > 0) {
            String quantityString = context2.getResources().getQuantityString(R$plurals.cookies_count, i, Integer.valueOf(i));
            shortFileSize = shortFileSize.isEmpty() ? quantityString : String.format(context2.getString(R$string.summary_with_one_bullet), quantityString, shortFileSize);
        }
        shortFileSize = !(ws3Var instanceof f) ? false : ((f) ws3Var).e.h().startsWith("http://") ? shortFileSize.isEmpty() ? "http" : String.format(context2.getString(R$string.summary_with_one_bullet), "http", shortFileSize) : shortFileSize;
        if (shortFileSize.isEmpty()) {
            return;
        }
        R(shortFileSize);
    }

    public final void b0(Bundle bundle) {
        Bundle bundleM = m();
        ws3 ws3Var = this.c0;
        boolean z = ws3Var instanceof f;
        bundleM.putSerializable(z ? "org.chromium.chrome.preferences.site" : "org.chromium.chrome.preferences.site_group", ws3Var);
        this.r = z ? SingleWebsiteSettings.class.getName() : GroupedWebsitesSettings.class.getName();
        m().putInt("org.chromium.chrome.preferences.navigation_source", bundle.getInt("org.chromium.chrome.preferences.navigation_source", 0));
    }

    @Override // org.chromium.components.browser_ui.settings.ChromeImageViewPreference, androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        tk0.a(this.e.getResources(), (ImageView) n32Var.u(R.id.icon));
        if (this.d0) {
            return;
        }
        GURL gurlV = this.c0.v();
        se3 se3Var = new se3(13, this);
        kr krVar = (kr) this.b0;
        if (krVar.e == null) {
            krVar.e = new LargeIconBridge(krVar.b);
        }
        ok0.a(krVar.a, krVar.e, gurlV, se3Var);
        this.d0 = true;
    }
}
