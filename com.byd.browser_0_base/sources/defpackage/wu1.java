package defpackage;

import J.N;
import android.content.Context;
import android.view.View;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.commerce.core.ShoppingService;
import org.chromium.components.page_info.PageInfoContainer;
import org.chromium.components.page_info.PageInfoController;
import org.chromium.components.page_info.PageInfoRowView;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: PageInfoStoreInfoController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class wu1 implements xu1 {
    public static final int m = View.generateViewId();
    public final nw2<a> e;
    public final tu1 f;
    public final PageInfoRowView g;
    public final Context h;
    public final boolean i;
    public final WebContents j;
    public final qu1 k = new qu1();
    public final wf1 l = new wf1();

    /* JADX INFO: compiled from: PageInfoStoreInfoController.java */
    public interface a {
        void a(ShoppingService.MerchantInfo merchantInfo);
    }

    public wu1(tu1 tu1Var, PageInfoRowView pageInfoRowView, nw2<a> nw2Var, boolean z, WebContents webContents, Profile profile) {
        this.f = tu1Var;
        this.g = pageInfoRowView;
        this.h = pageInfoRowView.getContext();
        this.e = nw2Var;
        this.i = z;
        this.j = webContents;
        if (profile != null) {
            ShoppingService shoppingServiceY = n3.Y(profile);
            long j = shoppingServiceY.a;
            if (j == 0 ? false : N.MLzANZWE(j, shoppingServiceY)) {
                new ag1().a(new xr(29, this), profile, ((PageInfoController) tu1Var).m);
                return;
            }
        }
        e(null);
    }

    @Override // defpackage.xu1
    public final String b() {
        return "";
    }

    @Override // defpackage.xu1
    public final View c(PageInfoContainer pageInfoContainer) {
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(org.chromium.components.commerce.core.ShoppingService.MerchantInfo r7) {
        /*
            r6 = this;
            org.chromium.components.page_info.PageInfoRowView$a r0 = new org.chromium.components.page_info.PageInfoRowView$a
            r0.<init>()
            wf1 r1 = r6.l
            nw2<wu1$a> r2 = r6.e
            if (r2 == 0) goto L6a
            java.lang.Object r2 = r2.get()
            if (r2 == 0) goto L6a
            if (r7 != 0) goto L14
            goto L6a
        L14:
            r2 = 1
            r0.a = r2
            android.content.Context r3 = r6.h
            android.content.res.Resources r4 = r3.getResources()
            int r5 = org.chromium.chrome.R$string.page_info_store_info_title
            java.lang.String r4 = r4.getString(r5)
            r0.e = r4
            float r4 = r7.a
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L33
            android.text.SpannableStringBuilder r2 = defpackage.vf1.a(r3, r7, r2)
            if (r2 == 0) goto L42
            goto L44
        L33:
            boolean r2 = r7.d
            if (r2 == 0) goto L42
            android.content.res.Resources r2 = r3.getResources()
            int r3 = org.chromium.chrome.R$string.page_info_store_info_description_with_no_rating
            java.lang.String r2 = r2.getString(r3)
            goto L44
        L42:
            java.lang.String r2 = ""
        L44:
            r0.f = r2
            int r2 = org.chromium.chrome.R$drawable.ic_storefront_blue
            r0.b = r2
            boolean r2 = r6.i
            if (r2 == 0) goto L52
            int r2 = org.chromium.chrome.R$color.iph_highlight_blue
            r0.j = r2
        L52:
            a9 r2 = new a9
            r3 = 10
            r2.<init>(r6, r3, r7)
            r0.g = r2
            r1.getClass()
            org.chromium.content_public.browser.WebContents r7 = r6.j
            if (r7 == 0) goto L6d
            java.lang.String r2 = "Shopping.MerchantTrust.RowSeen"
            java.lang.String r3 = "HasOccurred"
            J.N.M$ejnyHh(r7, r2, r3)
            goto L6d
        L6a:
            r7 = 0
            r0.a = r7
        L6d:
            boolean r7 = r0.a
            r1.getClass()
            java.lang.String r1 = "MerchantTrust.PageInfo.IsStoreInfoVisible"
            defpackage.oj2.O(r1, r7)
            org.chromium.components.page_info.PageInfoRowView r6 = r6.g
            r6.setParams(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.wu1.e(org.chromium.components.commerce.core.ShoppingService$MerchantInfo):void");
    }

    @Override // defpackage.xu1
    public final void a() {
    }

    @Override // defpackage.xu1
    public final void d() {
    }

    @Override // defpackage.xu1
    public final void f() {
    }
}
