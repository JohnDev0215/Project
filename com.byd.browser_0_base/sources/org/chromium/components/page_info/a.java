package org.chromium.components.page_info;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import defpackage.an2;
import defpackage.bq;
import defpackage.sk;
import defpackage.tu1;
import defpackage.uu1;
import defpackage.vq;
import java.util.List;
import org.chromium.components.page_info.PageInfoAdPersonalizationPreference;

/* JADX INFO: compiled from: PageInfoAdPersonalizationController.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a extends uu1 {
    public static final int l = View.generateViewId();
    public final tu1 g;
    public final PageInfoRowView h;
    public PageInfoAdPersonalizationPreference i;
    public boolean j;
    public List<String> k;

    public a(tu1 tu1Var, PageInfoRowView pageInfoRowView, vq vqVar) {
        super(vqVar);
        this.g = tu1Var;
        this.h = pageInfoRowView;
    }

    @Override // defpackage.xu1
    public final String b() {
        Context context = ((vq) this.e).i;
        Resources resources = this.h.getContext().getResources();
        sk skVar = bq.a;
        return resources.getString(N.M09VlOh_("PrivacySandboxSettings4") ? R$string.page_info_ad_privacy_header : R$string.page_info_ad_personalization_title);
    }

    @Override // defpackage.xu1
    public final View c(PageInfoContainer pageInfoContainer) {
        PageInfoAdPersonalizationPreference pageInfoAdPersonalizationPreference = new PageInfoAdPersonalizationPreference();
        this.i = pageInfoAdPersonalizationPreference;
        PageInfoAdPersonalizationPreference.a aVar = new PageInfoAdPersonalizationPreference.a();
        aVar.a = this.j;
        aVar.b = this.k;
        aVar.c = new an2(1, this);
        pageInfoAdPersonalizationPreference.i0 = aVar;
        pageInfoAdPersonalizationPreference.M0();
        return e(this.i);
    }

    @Override // defpackage.xu1
    public final void f() {
        g();
        this.i = null;
    }

    @Override // defpackage.xu1
    public final void a() {
    }

    @Override // defpackage.xu1
    public final void d() {
    }
}
