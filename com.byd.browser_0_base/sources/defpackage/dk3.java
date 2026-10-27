package defpackage;

import android.app.Activity;
import android.content.res.Resources;
import org.chromium.base.Callback;
import org.chromium.chrome.R$string;
import org.chromium.components.browser_ui.modaldialog.AppModalPresenter;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: UsageStatsConsentDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dk3 {
    public final Activity a;
    public bi1 b;
    public PropertyModel c;
    public final boolean d;
    public final Callback<Boolean> e;

    public dk3(Activity activity, boolean z, Callback<Boolean> callback) {
        this.a = activity;
        this.d = z;
        this.e = callback;
    }

    public final void a() {
        Activity activity = this.a;
        Resources resources = activity.getResources();
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.e(di1.a, new ck3(this));
        bVar.d(di1.m, resources, R$string.cancel);
        if (this.d) {
            bVar.d(di1.c, resources, R$string.usage_stats_revocation_prompt);
            bVar.e(di1.f, resources.getString(R$string.usage_stats_revocation_explanation));
            bVar.d(di1.j, resources, R$string.remove);
        } else {
            bVar.d(di1.c, resources, R$string.usage_stats_consent_title);
            bVar.e(di1.f, resources.getString(R$string.usage_stats_consent_prompt));
            bVar.d(di1.j, resources, R$string.show);
        }
        this.c = bVar.a();
        bi1 bi1Var = new bi1(new AppModalPresenter(activity));
        this.b = bi1Var;
        bi1Var.j(this.c, 1, false);
    }
}
