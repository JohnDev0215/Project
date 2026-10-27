package defpackage;

import J.N;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ToolbarButtonInProductHelpController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class xb3 extends nh0 {
    public final /* synthetic */ yb3 e;

    public xb3(yb3 yb3Var) {
        this.e = yb3Var;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void a1(Tab tab, GURL gurl) {
        OfflinePageBridge offlinePageBridgeA;
        TraceEvent traceEventH0 = TraceEvent.H0("ToolbarButtonInProductHelpController::onPageLoadFinished", null);
        try {
            if (!tab.m()) {
                y1(tab);
                if (traceEventH0 != null) {
                    traceEventH0.close();
                    return;
                }
                return;
            }
            if (!DeviceFormFactor.b(this.e.g) && (offlinePageBridgeA = OfflinePageBridge.a(Profile.b(tab.c()))) != null) {
                if (N.Mvkx0jqI(offlinePageBridgeA.a, offlinePageBridgeA, tab.c())) {
                    oj2.t(Profile.b(tab.c())).notifyEvent("user_has_seen_dino");
                }
            }
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0076  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void y1(org.chromium.chrome.browser.tab.Tab r12) {
        /*
            r11 = this;
            java.lang.String r0 = "IPH_DownloadPage"
            yb3 r11 = r11.e
            r11.a(r12, r0)
            r0 = 0
            pk3 r1 = r11.m
            android.view.View r2 = r11.k
            android.app.Activity r3 = r11.f
            r4 = 2
            if (r12 != 0) goto L12
            goto L49
        L12:
            boolean r5 = defpackage.n3.r(r12, r0)
            if (r5 == 0) goto L49
            org.chromium.content_public.browser.WebContents r5 = r12.c()
            boolean r5 = J.N.M8WoKfWJ(r5)
            if (r5 != 0) goto L23
            goto L49
        L23:
            hw0 r5 = new hw0
            android.content.res.Resources r6 = r3.getResources()
            int r7 = org.chromium.chrome.R$string.iph_translate_menu_button_text
            int r8 = org.chromium.chrome.R$string.iph_translate_menu_button_accessibility_text
            java.lang.String r9 = "IPH_TranslateMenuButton"
            r5.<init>(r6, r9, r7, r8)
            ub3 r6 = new ub3
            r6.<init>(r11, r4)
            r5.k = r6
            vb3 r6 = new vb3
            r6.<init>(r11, r4)
            r5.m = r6
            r5.j = r2
            gw0 r5 = r5.a()
            r1.a(r5)
        L49:
            boolean r5 = defpackage.oj2.B()
            if (r5 == 0) goto Lb5
            r5 = 1
            if (r12 != 0) goto L53
            goto L76
        L53:
            org.chromium.chrome.browser.profiles.Profile r6 = org.chromium.chrome.browser.profiles.Profile.d()
            org.chromium.components.commerce.core.ShoppingService r6 = defpackage.n3.Y(r6)
            if (r6 != 0) goto L5e
            goto L76
        L5e:
            org.chromium.url.GURL r12 = r12.getUrl()
            long r7 = r6.a
            r9 = 0
            int r9 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r9 != 0) goto L6c
            r12 = 0
            goto L72
        L6c:
            java.lang.Object r12 = J.N.MdgbKiVU(r7, r6, r12)
            org.chromium.components.commerce.core.ShoppingService$ProductInfo r12 = (org.chromium.components.commerce.core.ShoppingService.ProductInfo) r12
        L72:
            if (r12 == 0) goto L76
            r12 = r5
            goto L77
        L76:
            r12 = r0
        L77:
            if (r12 == 0) goto Lb5
            sk r12 = defpackage.bq.a
            java.lang.String r12 = "ContextualPageActions"
            boolean r6 = J.N.M09VlOh_(r12)
            if (r6 == 0) goto L8c
            java.lang.String r6 = "enable_ui"
            boolean r12 = J.N.M6bsIDpc(r12, r6, r5)
            if (r12 == 0) goto L8c
            r0 = r5
        L8c:
            if (r0 == 0) goto L8f
            goto Lb5
        L8f:
            hw0 r12 = new hw0
            android.content.res.Resources r0 = r3.getResources()
            int r3 = org.chromium.chrome.R$string.iph_price_tracking_menu_item
            int r6 = org.chromium.chrome.R$string.iph_price_tracking_menu_item_accessibility
            java.lang.String r7 = "IPH_ShoppingListMenuItem"
            r12.<init>(r0, r7, r3, r6)
            r12.j = r2
            wb3 r0 = new wb3
            r0.<init>(r11, r5)
            r12.k = r0
            tb3 r0 = new tb3
            r0.<init>(r11, r4)
            r12.m = r0
            gw0 r11 = r12.a()
            r1.a(r11)
        Lb5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.xb3.y1(org.chromium.chrome.browser.tab.Tab):void");
    }
}
