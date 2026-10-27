package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TabGroupUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class r03 {
    public static s03 a;

    /* JADX INFO: compiled from: TabGroupUtils.java */
    public class a extends bh0 {
        public final /* synthetic */ u73 e;

        public a(u73 u73Var) {
            this.e = u73Var;
        }

        @Override // defpackage.bh0, defpackage.vi
        public final void d(int i, int i2) {
            u73 u73Var = this.e;
            if (i == 0) {
                u73Var.f();
            } else {
                u73Var.b();
            }
        }
    }

    public static Tab a(y13 y13Var, Tab tab) {
        g03 g03Var = (g03) y13Var.u().c();
        return g03Var.getTabAt(g03Var.r(tab));
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void b(final java.lang.String r10, android.view.View r11, final defpackage.ji r12) {
        /*
            if (r11 != 0) goto L3
            return
        L3:
            android.content.Context r0 = r11.getContext()
            boolean r0 = defpackage.oj2.D(r0)
            if (r0 != 0) goto Le
            return
        Le:
            int r0 = r10.hashCode()
            r1 = 1
            r2 = -1
            switch(r0) {
                case -1060009038: goto L2e;
                case 1292823118: goto L23;
                case 1307968694: goto L18;
                default: goto L17;
            }
        L17:
            goto L38
        L18:
            java.lang.String r0 = "IPH_TabGroupsYourTabsTogether"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L21
            goto L38
        L21:
            r2 = 2
            goto L38
        L23:
            java.lang.String r0 = "IPH_TabGroupsQuicklyComparePages"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L2c
            goto L38
        L2c:
            r2 = r1
            goto L38
        L2e:
            java.lang.String r0 = "IPH_TabGroupsTapToSeeAnotherTab"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L37
            goto L38
        L37:
            r2 = 0
        L38:
            switch(r2) {
                case 0: goto L44;
                case 1: goto L3f;
                case 2: goto L3c;
                default: goto L3b;
            }
        L3b:
            return
        L3c:
            int r0 = org.chromium.chrome.R$string.iph_tab_groups_your_tabs_together_text
            goto L41
        L3f:
            int r0 = org.chromium.chrome.R$string.iph_tab_groups_quickly_compare_pages_text
        L41:
            r6 = r0
            r7 = r6
            goto L4a
        L44:
            int r0 = org.chromium.chrome.R$string.iph_tab_groups_tap_to_see_another_tab_text
            int r2 = org.chromium.chrome.R$string.iph_tab_groups_tap_to_see_another_tab_accessibility_text
            r6 = r0
            r7 = r2
        L4a:
            org.chromium.chrome.browser.profiles.Profile r0 = org.chromium.chrome.browser.profiles.Profile.d()
            xe3 r0 = defpackage.oj2.t(r0)
            boolean r2 = r0.isInitialized()
            if (r2 != 0) goto L59
            return
        L59:
            boolean r2 = r0.shouldTriggerHelpUI(r10)
            if (r2 != 0) goto L60
            return
        L60:
            nn3 r8 = new nn3
            r8.<init>(r11)
            u73 r2 = new u73
            android.content.Context r4 = r11.getContext()
            qo r3 = defpackage.qo.f()
            boolean r9 = r3.c()
            r3 = r2
            r5 = r11
            r3.<init>(r4, r5, r6, r7, r8, r9)
            r2.e(r1)
            android.content.Context r11 = r11.getContext()
            boolean r11 = defpackage.oj2.C(r11)
            if (r11 != 0) goto L91
            gd3 r11 = new gd3
            r11.<init>(r0, r10, r1)
            r2.addOnDismissListener(r11)
            r2.f()
            return
        L91:
            if (r12 != 0) goto L94
            return
        L94:
            r03$a r11 = new r03$a
            r11.<init>(r2)
            q03 r1 = new q03
            r1.<init>()
            r2.addOnDismissListener(r1)
            r12.g(r11)
            r2.f()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.r03.b(java.lang.String, android.view.View, ji):void");
    }
}
