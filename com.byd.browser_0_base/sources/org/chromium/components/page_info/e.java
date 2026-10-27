package org.chromium.components.page_info;

import J.N;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import androidx.fragment.app.Fragment;
import defpackage.qu1;
import defpackage.tu1;
import defpackage.uu1;
import defpackage.vq;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;

/* JADX INFO: compiled from: PageInfoPermissionsController.java */
/* JADX INFO: loaded from: classes3.dex */
public final class e extends uu1 implements SingleWebsiteSettings.a {
    public final tu1 g;
    public final PageInfoRowView h;
    public final String i;
    public final String j;
    public boolean k;
    public boolean l;
    public final int m;
    public final int n;
    public final qu1 o;

    /* JADX INFO: compiled from: PageInfoPermissionsController.java */
    public static class a {
        public int a;
        public SpannableString b;
        public SpannableString c;
        public boolean d;
        public int e;
    }

    public e(tu1 tu1Var, PageInfoRowView pageInfoRowView, vq vqVar, int i) {
        super(vqVar);
        this.o = new qu1();
        this.g = tu1Var;
        this.h = pageInfoRowView;
        this.j = ((PageInfoController) tu1Var).m.j();
        this.m = i;
        Resources resources = pageInfoRowView.getContext().getResources();
        this.n = R$color.iph_highlight_blue;
        this.i = resources.getString(R$string.page_info_permissions_title);
    }

    @Override // defpackage.xu1
    public final String b() {
        return this.i;
    }

    @Override // defpackage.xu1
    public final View c(PageInfoContainer pageInfoContainer) {
        if (!(!this.e.a().L())) {
            return null;
        }
        Bundle bundleM0 = SingleWebsiteSettings.M0(this.j);
        bundleM0.putBoolean("org.chromium.chrome.preferences.show_sound", this.k);
        SingleWebsiteSettings singleWebsiteSettings = (SingleWebsiteSettings) Fragment.U(this.h.getContext(), SingleWebsiteSettings.class.getName(), bundleM0);
        singleWebsiteSettings.i0 = true;
        singleWebsiteSettings.l0 = this;
        int i = this.m;
        if (i != -1) {
            singleWebsiteSettings.j0 = i;
            singleWebsiteSettings.k0 = this.n;
        }
        return e(singleWebsiteSettings);
    }

    @Override // defpackage.xu1
    public final void d() {
        if (this.l) {
            PageInfoController pageInfoController = (PageInfoController) this.g;
            pageInfoController.q.a.clear();
            long j = pageInfoController.i;
            if (j != 0) {
                N.MDd48bYq(j, pageInfoController);
            }
        }
        this.l = false;
    }

    @Override // defpackage.xu1
    public final void f() {
        g();
    }

    @Override // defpackage.xu1
    public final void a() {
    }
}
