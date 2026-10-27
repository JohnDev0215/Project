package defpackage;

import J.N;
import android.graphics.Point;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.browser.customtabs.CustomTabsSessionToken;
import defpackage.o23;
import java.util.ArrayList;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.customtabs.features.TabInteractionRecorder;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content.browser.GestureListenerManagerImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.LoadCommittedDetails;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: RealtimeEngagementSignalObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class n92 extends o23.b {
    public final CustomTabsConnection e;
    public final CustomTabsSessionToken f;
    public final boolean g;
    public WebContents h;
    public a i;
    public b j;
    public c k;
    public final int l;
    public final int m;

    /* JADX INFO: compiled from: RealtimeEngagementSignalObserver.java */
    public class a extends os0 {
        public final /* synthetic */ Tab a;

        public a(Tab tab) {
            this.a = tab;
        }

        @Override // defpackage.os0
        public final void c(int i, int i2) {
            h(true);
        }

        @Override // defpackage.os0
        public final void d(int i, int i2) {
            n92 n92Var = n92.this;
            if (n92Var.l == 0) {
                return;
            }
            if (n92Var.k.b(((WebContentsImpl) this.a.c()).l.b(), (int) Math.floor(r5.a(r5.d) - r5.a(r5.f)))) {
                h(false);
            }
        }

        @Override // defpackage.os0
        public final void e(int i, int i2, boolean z) {
            n92 n92Var = n92.this;
            c cVar = n92Var.k;
            boolean z2 = false;
            if (cVar.e) {
                cVar.a(false);
            }
            cVar.e = true;
            cVar.f = z;
            cVar.k = null;
            if (n92Var.g && z) {
                z2 = true;
            }
            CustomTabsConnection customTabsConnection = n92Var.e;
            customTabsConnection.getClass();
            Bundle bundle = new Bundle();
            bundle.putBoolean("isDirectionUp", z2);
            if (customTabsConnection.r(n92Var.f, "onVerticalScrollEvent", bundle)) {
                customTabsConnection.l(bundle, "extraCallback(onVerticalScrollEvent)");
            }
        }

        @Override // defpackage.os0
        public final void f(Point point) {
            n92 n92Var = n92.this;
            if (n92Var.l == 1 || point == null) {
                return;
            }
            za2 za2Var = ((WebContentsImpl) this.a.c()).l;
            n92Var.k.b(point.y, (int) Math.floor(za2Var.a(za2Var.d) - za2Var.a(za2Var.f)));
        }

        @Override // defpackage.os0
        public final void g(boolean z) {
            boolean z2;
            n92 n92Var = n92.this;
            c cVar = n92Var.k;
            if (!cVar.e || z == cVar.f) {
                z2 = false;
            } else {
                cVar.f = z;
                z2 = true;
            }
            if (z2) {
                boolean z3 = n92Var.g && z;
                CustomTabsConnection customTabsConnection = n92Var.e;
                customTabsConnection.getClass();
                Bundle bundle = new Bundle();
                bundle.putBoolean("isDirectionUp", z3);
                if (customTabsConnection.r(n92Var.f, "onVerticalScrollEvent", bundle)) {
                    customTabsConnection.l(bundle, "extraCallback(onVerticalScrollEvent)");
                }
            }
        }

        public final void h(boolean z) {
            n92 n92Var = n92.this;
            int iA = n92Var.k.a(z);
            if (iA != -1) {
                CustomTabsConnection customTabsConnection = n92Var.e;
                CustomTabsSessionToken customTabsSessionToken = n92Var.f;
                if (!n92Var.g) {
                    iA = 0;
                }
                customTabsConnection.getClass();
                Bundle bundle = new Bundle();
                bundle.putInt("scrollPercentage", iA);
                if (customTabsConnection.r(customTabsSessionToken, "onGreatestScrollPercentageIncreased", bundle)) {
                    customTabsConnection.l(bundle, "extraCallback(onGreatestScrollPercentageIncreased)");
                }
            }
        }
    }

    /* JADX INFO: compiled from: RealtimeEngagementSignalObserver.java */
    public class b extends rq3 {
        public b() {
        }

        @Override // defpackage.rq3
        public final void navigationEntryCommitted(LoadCommittedDetails loadCommittedDetails) {
            if (!loadCommittedDetails.b || loadCommittedDetails.a) {
                return;
            }
            c cVar = n92.this.k;
            cVar.g = 0;
            cVar.h = 0;
        }
    }

    /* JADX INFO: compiled from: RealtimeEngagementSignalObserver.java */
    public static class c implements lk3 {
        public boolean e;
        public boolean f;
        public int g;
        public int h;
        public int i = 0;
        public int j = 100;
        public Long k;

        public final int a(boolean z) {
            int i = this.g;
            int i2 = i - (i % 5);
            if (i2 > this.h) {
                this.h = i2;
            } else {
                i2 = -1;
            }
            if (this.i == 1 && z) {
                this.k = Long.valueOf(SystemClock.elapsedRealtime());
            }
            this.e = false;
            return i2;
        }

        public final boolean b(int i, int i2) {
            int iQ;
            if (!this.e && this.k != null) {
                oj2.b0(SystemClock.elapsedRealtime() - this.k.longValue(), "CustomTabs.TimeScrollUpdateReceivedAfterScrollEnd");
            }
            boolean z = (this.e || this.k == null || SystemClock.elapsedRealtime() - this.k.longValue() > ((long) this.j)) ? false : true;
            if ((this.e || z) && (iQ = a8.q(Math.round((i / i2) * 100.0f), 0, 100)) > this.g) {
                this.g = iQ;
            }
            this.k = null;
            return z;
        }
    }

    public n92(o23 o23Var, CustomTabsConnection customTabsConnection, CustomTabsSessionToken customTabsSessionToken) {
        this.e = customTabsConnection;
        this.f = customTabsSessionToken;
        sk skVar = bq.a;
        this.l = N.M09VlOh_("CCTRealTimeEngagementSignalsAlternativeImpl") ? 1 : 0;
        this.m = N.M37SqSAy("CCTRealTimeEngagementSignalsAlternativeImpl", "time_can_update_after_end", 100);
        o23Var.c(this);
        ArrayList arrayList = CustomTabsConnection.e().m;
        this.g = arrayList != null && arrayList.contains("CCTRealTimeEngagementSignals") ? true : N.M6bsIDpc("CCTRealTimeEngagementSignals", "real_values", true);
        customTabsConnection.h = new yq2(9, this);
    }

    @Override // o23.b
    public final void A1(Tab tab) {
        D1(this.h);
        C1(tab);
    }

    public final void B1(Tab tab) {
        TabInteractionRecorder tabInteractionRecorder;
        if (((tab == null || tab.c() == null || tab.isIncognito() || !v42.g().a()) ? false : true) && (tabInteractionRecorder = (TabInteractionRecorder) N.MJqYRCCN(tab)) != null) {
            boolean zMditPAbg = N.MditPAbg(tabInteractionRecorder.a);
            CustomTabsConnection customTabsConnection = this.e;
            customTabsConnection.getClass();
            Bundle bundle = new Bundle();
            bundle.putBoolean("didInteract", zMditPAbg);
            if (customTabsConnection.r(this.f, "didGetUserInteraction", bundle)) {
                customTabsConnection.l(bundle, "extraCallback(didGetUserInteraction)");
            }
        }
    }

    public final void C1(Tab tab) {
        if ((tab == null || tab.c() == null || tab.isIncognito() || !v42.g().a()) ? false : true) {
            WebContents webContents = this.h;
            if (webContents != null) {
                D1(webContents);
            }
            this.h = tab.c();
            c cVar = (c) tab.B().b(c.class);
            if (cVar == null) {
                cVar = new c();
                tab.B().d(c.class, cVar);
            }
            this.k = cVar;
            int i = this.l;
            cVar.i = i;
            cVar.j = this.m;
            this.i = new a(tab);
            this.j = new b();
            GestureListenerManagerImpl gestureListenerManagerImplE = GestureListenerManagerImpl.e(this.h);
            if (!gestureListenerManagerImplE.f.b(this.i)) {
                gestureListenerManagerImplE.d(this.i, i);
            }
            this.h.C(this.j);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void D0(Tab tab, boolean z) {
        if (z) {
            D1(this.h);
        }
    }

    public final void D1(WebContents webContents) {
        if (webContents != null) {
            if (this.i != null) {
                GestureListenerManagerImpl.e(webContents).h(this.i);
            }
            b bVar = this.j;
            if (bVar != null) {
                webContents.z(bVar);
            }
        }
        this.i = null;
        this.j = null;
        this.k = null;
        this.h = null;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void F0(Tab tab) {
        B1(tab);
        D1(tab.c());
    }

    @Override // defpackage.n23
    public final void P0(Tab tab, WindowAndroid windowAndroid) {
        D1(tab.c());
        super.P0(tab, windowAndroid);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void T0(int i, Tab tab) {
        if (i == 1) {
            B1(tab);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void e1(Tab tab) {
        D1(tab.c());
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void u(Tab tab) {
        C1(tab);
    }

    @Override // o23.b
    public final void y1() {
        D1(this.h);
    }

    @Override // o23.b
    public final void z1(Tab tab) {
        C1(tab);
    }
}
