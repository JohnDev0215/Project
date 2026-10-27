package defpackage;

import android.graphics.Bitmap;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.find_in_page.FindMatchRectsDetails;
import org.chromium.components.find_in_page.FindNotificationDetails;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: TabObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public interface n23 {
    void A0(TabImpl tabImpl);

    void C0(TabImpl tabImpl, NavigationHandle navigationHandle);

    void D(TabImpl tabImpl, int i, int i2, int i3, int i4, int i5);

    void D0(Tab tab, boolean z);

    void F0(Tab tab);

    void H0();

    void I(Tab tab, boolean z);

    void I0(TabImpl tabImpl);

    void J0(int i, Tab tab);

    void K(TabImpl tabImpl, boolean z);

    void K0(Tab tab);

    void L0(TabImpl tabImpl, boolean z);

    void M(int i, Tab tab);

    default void P0(Tab tab, WindowAndroid windowAndroid) {
        if (tab == null || windowAndroid != null) {
            return;
        }
        tab.t(this);
    }

    void Q(Tab tab, boolean z, boolean z2);

    void R0(TabImpl tabImpl);

    void S0(Tab tab, boolean z);

    void T0(int i, Tab tab);

    void U0();

    void V();

    void X(Tab tab, Bitmap bitmap, GURL gurl);

    void Z(TabImpl tabImpl);

    void a1(Tab tab, GURL gurl);

    void e(boolean z);

    void e1(Tab tab);

    void g0(int i, Tab tab, LoadUrlParams loadUrlParams);

    void g1(Tab tab, GURL gurl);

    void j0(FindMatchRectsDetails findMatchRectsDetails);

    void j1(FindNotificationDetails findNotificationDetails);

    void k(GURL gurl);

    void l0(int i, Tab tab);

    void l1(Tab tab, float f);

    void o1(TabImpl tabImpl, NavigationHandle navigationHandle);

    void r1(Tab tab);

    void s(TabImpl tabImpl);

    void u(Tab tab);

    void v1(int i);

    void x0(TabImpl tabImpl);

    void x1();

    void y0(String str);
}
