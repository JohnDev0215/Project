package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

/* JADX INFO: compiled from: SuspendedTab.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vw2 implements View.OnClickListener {
    public final /* synthetic */ Context e;
    public final /* synthetic */ ww2 f;

    public vw2(ww2 ww2Var, Context context) {
        this.f = ww2Var;
        this.e = context;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Intent intent = new Intent("org.chromium.chrome.browser.usage_stats.action.SHOW_WEBSITE_DETAILS");
        intent.setFlags(268435456);
        intent.putExtra("org.chromium.chrome.browser.usage_stats.extra.FULLY_QUALIFIED_DOMAIN_NAME", this.f.h);
        intent.putExtra("android.intent.extra.PACKAGE_NAME", xz.a.getPackageName());
        try {
            this.e.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e("cr_SuspendedTab", "No activity found for site details intent", e);
        }
    }
}
