package org.chromium.chrome.browser.offlinepages;

import J.N;
import defpackage.a23;
import defpackage.a8;
import defpackage.h23;
import defpackage.i9;
import defpackage.ir1;
import defpackage.n3;
import defpackage.nh0;
import defpackage.oj2;
import defpackage.t02;
import defpackage.y13;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.net.NetworkChangeNotifier;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: OfflinePageUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d {
    public static c a;
    public static final HashMap b = new HashMap();

    /* JADX INFO: compiled from: OfflinePageUtils.java */
    public interface a {
    }

    /* JADX INFO: compiled from: OfflinePageUtils.java */
    public interface b {
        void b(LoadUrlParams loadUrlParams);
    }

    /* JADX INFO: compiled from: OfflinePageUtils.java */
    public static class c implements a {
    }

    /* JADX INFO: renamed from: org.chromium.chrome.browser.offlinepages.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: OfflinePageUtils.java */
    public static class C0097d extends h23 {
        public static final f h = new f();
        public final y13 g;

        /* JADX INFO: renamed from: org.chromium.chrome.browser.offlinepages.d$d$a */
        /* JADX INFO: compiled from: OfflinePageUtils.java */
        public class a implements Callback<Integer> {
            @Override // org.chromium.base.Callback
            public final /* bridge */ /* synthetic */ void onResult(Integer num) {
            }
        }

        public C0097d(a23 a23Var) {
            super(a23Var);
            this.g = a23Var;
        }

        @Override // defpackage.t13
        public final void V0(Tab tab) {
            OfflinePageBridge offlinePageBridgeA = OfflinePageBridge.a(this.g.g(tab.isIncognito()).f());
            if (offlinePageBridgeA == null) {
                return;
            }
            ClientId clientId = new ClientId("last_n", Integer.toString(tab.getId()));
            ArrayList arrayList = new ArrayList();
            arrayList.add(clientId);
            a aVar = new a();
            String[] strArr = new String[arrayList.size()];
            String[] strArr2 = new String[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++) {
                strArr[i] = ((ClientId) arrayList.get(i)).a;
                strArr2[i] = ((ClientId) arrayList.get(i)).b;
            }
            N.Mwp3hyBt(offlinePageBridgeA.a, offlinePageBridgeA, strArr, strArr2, aVar);
        }

        @Override // defpackage.t13
        public final void f1(Tab tab, int i, int i2, boolean z) {
            tab.x(h);
        }

        @Override // defpackage.t13
        public final void t(Tab tab, boolean z, boolean z2) {
            WebContents webContentsC;
            OfflinePageBridge offlinePageBridgeA = OfflinePageBridge.a(this.g.g(tab.isIncognito()).f());
            if (offlinePageBridgeA == null || (webContentsC = tab.c()) == null) {
                return;
            }
            N.MIDiWOi_(offlinePageBridgeA.a, offlinePageBridgeA, webContentsC);
        }
    }

    /* JADX INFO: compiled from: OfflinePageUtils.java */
    public static class e implements b {
        public final Tab a;

        public e(Tab tab) {
            this.a = tab;
        }

        @Override // org.chromium.chrome.browser.offlinepages.d.b
        public final void b(LoadUrlParams loadUrlParams) {
            this.a.b(loadUrlParams);
        }
    }

    /* JADX INFO: compiled from: OfflinePageUtils.java */
    public static class f extends nh0 {
        @Override // defpackage.nh0, defpackage.n23
        public final void I0(TabImpl tabImpl) {
            if (tabImpl.s) {
                c cVar = d.a;
                oj2.U(9, 10, "OfflinePages.TabRestore");
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void J0(int i, Tab tab) {
            if (tab.r()) {
                c cVar = d.a;
                oj2.U(8, 10, "OfflinePages.TabRestore");
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
        /* JADX WARN: Type inference failed for: r2v12 */
        /* JADX WARN: Type inference failed for: r2v13 */
        /* JADX WARN: Type inference failed for: r2v14 */
        /* JADX WARN: Type inference failed for: r2v15 */
        /* JADX WARN: Type inference failed for: r2v4 */
        @Override // defpackage.nh0, defpackage.n23
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void a1(org.chromium.chrome.browser.tab.Tab r3, org.chromium.url.GURL r4) {
            /*
                r2 = this;
                boolean r2 = r3.r()
                if (r2 != 0) goto L7
                return
            L7:
                boolean r2 = org.chromium.chrome.browser.offlinepages.d.d()
                org.chromium.content_public.browser.WebContents r4 = r3.c()
                org.chromium.chrome.browser.offlinepages.OfflinePageItem r4 = org.chromium.chrome.browser.offlinepages.d.c(r4)
                if (r4 == 0) goto L26
                r2 = r2 | 8
                org.chromium.chrome.browser.offlinepages.ClientId r4 = r4.c
                java.lang.String r4 = r4.a
                java.lang.String r0 = "last_n"
                boolean r4 = r4.equals(r0)
                if (r4 == 0) goto L38
                r2 = r2 | 16
                goto L38
            L26:
                org.chromium.url.GURL r4 = r3.getUrl()
                boolean r4 = J.N.MXyz2Okt(r4)
                if (r4 == 0) goto L36
                boolean r4 = r3.isIncognito()
                if (r4 == 0) goto L38
            L36:
                r2 = r2 | 4
            L38:
                r4 = 4
                if (r2 == 0) goto L61
                r0 = 1
                if (r2 == r0) goto L60
                r1 = 5
                if (r2 == r4) goto L5e
                if (r2 == r1) goto L5c
                r4 = 8
                if (r2 == r4) goto L5a
                r4 = 9
                if (r2 == r4) goto L58
                r4 = 24
                if (r2 == r4) goto L56
                r4 = 25
                if (r2 == r4) goto L54
                return
            L54:
                r4 = 3
                goto L61
            L56:
                r4 = 7
                goto L61
            L58:
                r4 = 2
                goto L61
            L5a:
                r4 = 6
                goto L61
            L5c:
                r4 = r0
                goto L61
            L5e:
                r4 = r1
                goto L61
            L60:
                r4 = 0
            L61:
                org.chromium.url.GURL r2 = r3.getUrl()
                r2.j()
                r2 = 10
                java.lang.String r3 = "OfflinePages.TabRestore"
                defpackage.oj2.U(r4, r2, r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.offlinepages.d.f.a1(org.chromium.chrome.browser.tab.Tab, org.chromium.url.GURL):void");
        }
    }

    /* JADX INFO: compiled from: OfflinePageUtils.java */
    public static class g implements b {
        public final WebContents a;

        public g(WebContents webContents) {
            this.a = webContents;
        }

        @Override // org.chromium.chrome.browser.offlinepages.d.b
        public final void b(LoadUrlParams loadUrlParams) {
            this.a.m().b(loadUrlParams);
        }
    }

    public static a a() {
        if (a == null) {
            a = new c();
        }
        return a;
    }

    public static void b(long j, int i, Callback callback, Profile profile) {
        ((c) a()).getClass();
        OfflinePageBridge offlinePageBridgeA = OfflinePageBridge.a(profile);
        if (offlinePageBridgeA == null) {
            callback.onResult(null);
        } else {
            N.MBaVkYrR(offlinePageBridgeA.a, offlinePageBridgeA, j, i, new t02(callback, 2));
        }
    }

    public static OfflinePageItem c(WebContents webContents) {
        if (webContents == null) {
            return null;
        }
        a aVarA = a();
        Profile profileB = Profile.b(webContents);
        ((c) aVarA).getClass();
        OfflinePageBridge offlinePageBridgeA = OfflinePageBridge.a(profileB);
        if (offlinePageBridgeA == null) {
            return null;
        }
        return (OfflinePageItem) N.MzjNdQag(offlinePageBridgeA.a, offlinePageBridgeA, webContents);
    }

    public static boolean d() {
        ((c) a()).getClass();
        return NetworkChangeNotifier.a();
    }

    public static boolean e(Tab tab) {
        WebContents webContentsC;
        ((c) a()).getClass();
        if (tab == null || (webContentsC = tab.c()) == null) {
            return false;
        }
        a aVarA = a();
        Profile profileB = Profile.b(tab.c());
        ((c) aVarA).getClass();
        OfflinePageBridge offlinePageBridgeA = OfflinePageBridge.a(profileB);
        if (offlinePageBridgeA == null) {
            return false;
        }
        return N.Mmgl0zEx(offlinePageBridgeA.a, offlinePageBridgeA, webContentsC);
    }

    public static boolean f(WebContents webContents) {
        OfflinePageBridge offlinePageBridgeA;
        ((c) a()).getClass();
        if (webContents == null || (offlinePageBridgeA = OfflinePageBridge.a(Profile.b(webContents))) == null) {
            return false;
        }
        return N.MD0P9_ar(offlinePageBridgeA.a, offlinePageBridgeA, webContents);
    }

    public static void g(WebContents webContents, b bVar) {
        OfflinePageItem offlinePageItemC = c(webContents);
        if (!f(webContents) && offlinePageItemC != null) {
            bVar.b(new LoadUrlParams(offlinePageItemC.a, 33554440));
            return;
        }
        LoadUrlParams loadUrlParams = new LoadUrlParams(a8.D(webContents.s()).j(), 33554440);
        a aVarA = a();
        Profile profileB = Profile.b(webContents);
        ((c) aVarA).getClass();
        OfflinePageBridge offlinePageBridgeA = OfflinePageBridge.a(profileB);
        loadUrlParams.g = offlinePageBridgeA == null ? "" : N.MRMfaXXV(offlinePageBridgeA.a, offlinePageBridgeA, webContents);
        bVar.b(loadUrlParams);
    }

    public static void h(Callback callback, OfflinePageItem offlinePageItem, WindowAndroid windowAndroid) {
        if (offlinePageItem == null) {
            return;
        }
        String str = offlinePageItem.a;
        String str2 = offlinePageItem.d;
        File file = new File(offlinePageItem.e);
        String str3 = offlinePageItem.e;
        n3.X0("OfflinePages.Sharing.SharePageFromOverflowMenu");
        new ir1(file, str3, str, str2, callback, windowAndroid).c(i9.e);
    }
}
