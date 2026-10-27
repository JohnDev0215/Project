package org.chromium.chrome.browser.offlinepages;

import J.N;
import android.text.TextUtils;
import defpackage.C0175b;
import defpackage.ar1;
import defpackage.g23;
import defpackage.xz;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes2.dex */
public class OfflinePageBridge {
    public long a;
    public boolean b;
    public final org.chromium.base.b<a> c = new org.chromium.base.b<>();

    public interface SavePageCallback {
        @CalledByNative
        void onSavePageDone(int i, String str, long j);
    }

    public OfflinePageBridge(long j) {
        this.a = j;
    }

    public static OfflinePageBridge a(Profile profile) {
        Object obj = ThreadUtils.a;
        if (profile == null) {
            return null;
        }
        return (OfflinePageBridge) N.MspGcmXb(profile.g());
    }

    @CalledByNative
    public static OfflinePageBridge create(long j) {
        return new OfflinePageBridge(j);
    }

    @CalledByNative
    public static ClientId createClientId(String str, String str2) {
        return new ClientId(str, str2);
    }

    @CalledByNative
    public static DeletedPageInfo createDeletedPageInfo(long j, String str, String str2, String str3) {
        return new DeletedPageInfo(j);
    }

    @CalledByNative
    public static LoadUrlParams createLoadUrlParams(String str, String str2, String str3) {
        LoadUrlParams loadUrlParams = new LoadUrlParams(str, 0);
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            HashMap map = new HashMap();
            map.put(str2, str3);
            loadUrlParams.f = map;
            loadUrlParams.g = C0175b.k(str2, ":", str3);
        }
        return loadUrlParams;
    }

    @CalledByNative
    public static void createOfflinePageAndAddToList(List<OfflinePageItem> list, String str, long j, String str2, String str3, String str4, String str5, long j2, long j3, int i, long j4, String str6) {
        list.add(createOfflinePageItem(str, j, str2, str3, str4, str5, j2, j3, i, j4, str6));
    }

    @CalledByNative
    public static OfflinePageItem createOfflinePageItem(String str, long j, String str2, String str3, String str4, String str5, long j2, long j3, int i, long j4, String str6) {
        return new OfflinePageItem(str, j, str2, str3, str4, str5, j2, j3, i, j4, str6);
    }

    @CalledByNative
    public static String getEncodedOriginApp(Tab tab) {
        return new ar1(xz.a, tab).a();
    }

    public final void b(WebContents webContents, ClientId clientId, SavePageCallback savePageCallback) {
        Tab tabU = g23.u(webContents.V());
        N.MD7l7nn$(this.a, this, savePageCallback, webContents, clientId.a, clientId.b, (tabU != null ? new ar1(xz.a, tabU) : new ar1()).a());
    }

    @CalledByNative
    public void offlinePageAdded(OfflinePageItem offlinePageItem) {
        Iterator<a> it = this.c.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).a();
            }
        }
    }

    @CalledByNative
    public void offlinePageBridgeDestroyed() {
        Object obj = ThreadUtils.a;
        this.b = false;
        this.a = 0L;
        this.c.clear();
    }

    @CalledByNative
    public void offlinePageDeleted(DeletedPageInfo deletedPageInfo) {
        Iterator<a> it = this.c.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).b(deletedPageInfo);
            }
        }
    }

    @CalledByNative
    public void offlinePageModelLoaded() {
        this.b = true;
        Iterator<a> it = this.c.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).c();
            }
        }
    }

    public static abstract class a {
        public void a() {
        }

        public void c() {
        }

        public void b(DeletedPageInfo deletedPageInfo) {
        }
    }
}
