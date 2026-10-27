package defpackage;

import J.N;
import android.app.Dialog;
import android.view.View;
import org.chromium.components.browser_ui.site_settings.SingleCategorySettings;

/* JADX INFO: compiled from: SingleCategorySettings.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dn2 implements View.OnClickListener {
    public final /* synthetic */ Dialog e;
    public final /* synthetic */ SingleCategorySettings f;

    public dn2(SingleCategorySettings singleCategorySettings, Dialog dialog) {
        this.f = singleCategorySettings;
        this.e = dialog;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SingleCategorySettings singleCategorySettings = this.f;
        N.MM1KTgoi(((kr) singleCategorySettings.h0).b, singleCategorySettings.k0.f(), false);
        singleCategorySettings.P0();
        this.e.dismiss();
    }
}
