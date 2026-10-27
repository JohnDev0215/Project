package defpackage;

import android.app.Activity;
import android.content.res.Resources;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: SessionRestoreMessageController.java */
/* JADX INFO: loaded from: classes.dex */
public final class fj2 implements hk1 {
    public final Activity e;
    public final g3 f;
    public final CustomTabsConnection g;
    public vg1 h;
    public final z20 i;
    public final p61<CompositorViewHolder> j;
    public final p61<o30> k;
    public int l;

    /* JADX WARN: Removed duplicated region for block: B:22:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public fj2(android.app.Activity r7, defpackage.g3 r8, defpackage.q2 r9, defpackage.oj r10, org.chromium.chrome.browser.customtabs.CustomTabsConnection r11, defpackage.p61<defpackage.o30> r12, defpackage.z20 r13, defpackage.p61<org.chromium.chrome.browser.compositor.CompositorViewHolder> r14) {
        /*
            r6 = this;
            r6.<init>()
            r6.e = r7
            r6.f = r8
            r6.g = r11
            r6.k = r12
            r6.i = r13
            r6.j = r14
            sk r8 = defpackage.bq.q
            boolean r8 = r8.a()
            if (r8 == 0) goto L99
            dj2 r8 = r11.f()
            int r4 = r7.getTaskId()
            java.lang.String r0 = r10.j()
            org.chromium.chrome.browser.preferences.SharedPreferencesManager r11 = org.chromium.chrome.browser.preferences.SharedPreferencesManager.getInstance()
            java.lang.String r10 = r10.K()
            java.lang.String r2 = defpackage.v20.a(r7)
            ej2 r8 = (defpackage.ej2) r8
            um3 r7 = r8.a
            java.lang.Object r7 = r7.f
            org.chromium.chrome.browser.tab.Tab r7 = (org.chromium.chrome.browser.tab.Tab) r7
            r8 = 1
            r12 = 0
            if (r7 == 0) goto L3d
            r7 = r8
            goto L3e
        L3d:
            r7 = r12
        L3e:
            if (r7 == 0) goto L89
            boolean r7 = android.text.TextUtils.isEmpty(r0)
            r7 = r7 ^ r8
            boolean r13 = android.text.TextUtils.isEmpty(r2)
            r13 = r13 ^ r8
            if (r7 != 0) goto L4f
            if (r13 != 0) goto L4f
            goto L66
        L4f:
            java.lang.String r7 = "pref_last_custom_tab_url"
            java.lang.String r13 = ""
            java.lang.String r7 = r11.g(r7, r13)
            boolean r7 = android.text.TextUtils.equals(r10, r7)
            if (r7 != 0) goto L5e
            goto L66
        L5e:
            java.lang.String r7 = "Chrome.CustomTabs.LastCloseTabInteraction"
            boolean r7 = r11.readBoolean(r7, r12)
            if (r7 != 0) goto L68
        L66:
            r7 = r12
            goto L86
        L68:
            java.lang.String r7 = "Chrome.CustomTabs.LastClientPackage"
            r10 = 0
            java.lang.String r1 = r11.g(r7, r10)
            java.lang.String r7 = "Chrome.CustomTabs.LastReferrer"
            java.lang.String r3 = r11.g(r7, r10)
            java.lang.String r7 = "Chrome.CustomTabs.LastTaskId"
            int r5 = r11.d(r12, r7)
            java.lang.String r7 = defpackage.n3.U(r0, r1, r2, r3, r4, r5)
            java.lang.String r10 = ".Different"
            boolean r7 = r7.equals(r10)
            r7 = r7 ^ r8
        L86:
            if (r7 == 0) goto L89
            goto L8a
        L89:
            r8 = r12
        L8a:
            if (r8 == 0) goto L99
            boolean r7 = r9.h()
            if (r7 == 0) goto L96
            r6.a()
            goto L99
        L96:
            r9.f(r6)
        L99:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fj2.<init>(android.app.Activity, g3, q2, oj, org.chromium.chrome.browser.customtabs.CustomTabsConnection, p61, z20, p61):void");
    }

    @Override // defpackage.hk1
    public final void G0() {
        a();
    }

    public final void a() {
        this.h = xg1.a(this.f);
        Resources resources = this.e.getResources();
        PropertyModel.b bVar = new PropertyModel.b(ng1.H);
        bVar.c(ng1.a, 37);
        bVar.e(ng1.f, resources.getString(R$string.restore_custom_tab_title));
        bVar.c(ng1.m, R$drawable.infobar_chrome);
        bVar.e(ng1.h, resources.getString(R$string.restore_custom_tab_description));
        bVar.e(ng1.c, resources.getString(R$string.restore_custom_tab_button_text));
        bVar.e(ng1.d, new p11(4, this));
        bVar.e(ng1.x, new c3(15, this));
        this.h.c(bVar.a(), false);
    }
}
