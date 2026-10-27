package defpackage;

import J.N;
import android.app.Activity;
import android.text.SpannableString;
import android.text.TextUtils;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.embedder_support.contextmenu.ContextMenuParams;
import org.chromium.components.omnibox.OmniboxUrlEmphasizer;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ContextMenuHeaderCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class mz {
    public final PropertyModel a;

    public mz(Activity activity, ContextMenuParams contextMenuParams, Profile profile, uz uzVar) {
        CharSequence charSequence;
        String strF = a8.F(contextMenuParams);
        String strJ = contextMenuParams.a().j();
        if (TextUtils.isEmpty(strJ)) {
            charSequence = strJ;
        } else {
            boolean z = !z7.I(activity);
            GURL gurl = contextMenuParams.c;
            SpannableString spannableString = new SpannableString(!aq.i(gurl) ? N.MeroQv$e(gurl.j()) : "");
            qp qpVar = new qp(profile);
            OmniboxUrlEmphasizer.a(spannableString, activity, qpVar, 0, z, false);
            qpVar.a();
            charSequence = spannableString;
        }
        boolean zK0 = a8.k0(activity);
        int i = zK0 ? R$dimen.context_menu_popup_header_monogram_size : R$dimen.context_menu_header_monogram_size;
        PropertyModel.b bVar = new PropertyModel.b(oz.l);
        bVar.e(oz.a, strF);
        bVar.c(oz.b, TextUtils.isEmpty(charSequence) ? 2 : 1);
        bVar.e(oz.c, charSequence);
        bVar.c(oz.e, TextUtils.isEmpty(strF) ? 2 : 1);
        bVar.e(oz.f, null);
        bVar.b(oz.g, false);
        bVar.c(oz.h, activity.getResources().getDimensionPixelSize(i));
        PropertyModel propertyModelA = bVar.a();
        if (zK0) {
            int dimensionPixelSize = activity.getResources().getDimensionPixelSize(R$dimen.context_menu_popup_header_image_max_size);
            propertyModelA.n(oz.i, dimensionPixelSize);
            propertyModelA.n(oz.j, dimensionPixelSize);
            propertyModelA.n(oz.k, 0);
        } else {
            propertyModelA.n(oz.i, -1);
            propertyModelA.n(oz.j, -1);
            propertyModelA.n(oz.k, -1);
        }
        this.a = propertyModelA;
        new nz(activity, propertyModelA, contextMenuParams, profile, uzVar);
    }
}
