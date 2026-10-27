package defpackage;

import J.N;
import java.util.Set;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.chrome.browser.offlinepages.OfflinePageItem;
import org.chromium.chrome.browser.offlinepages.PublishPageCallback;
import org.chromium.chrome.browser.omnibox.geo.f;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class hr1 implements Callback {
    public final /* synthetic */ int e = 1;
    public final /* synthetic */ Callback f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;
    public final /* synthetic */ Object i;

    public /* synthetic */ hr1(fm3 fm3Var, f.b bVar, f.a aVar, Set set) {
        this.f = fm3Var;
        this.g = bVar;
        this.h = aVar;
        this.i = set;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        Callback callback = this.f;
        Object obj2 = this.i;
        Object obj3 = this.h;
        Object obj4 = this.g;
        switch (i) {
            case 0:
                WindowAndroid windowAndroid = (WindowAndroid) obj4;
                OfflinePageBridge offlinePageBridge = (OfflinePageBridge) obj3;
                OfflinePageItem offlinePageItem = (OfflinePageItem) obj2;
                if (!((Boolean) obj).booleanValue()) {
                    oj2.U(14, 16, "OfflinePages.Sharing.PublishInternalPageResult");
                } else {
                    PublishPageCallback publishPageCallback = new PublishPageCallback(callback, offlinePageItem, windowAndroid);
                    N.MSHYzaXq(offlinePageBridge.a, offlinePageBridge, offlinePageItem.b, publishPageCallback);
                }
                break;
            default:
                callback.onResult(new f((f.b) obj4, (f.a) obj3, (Set) obj2, (Set) obj));
                break;
        }
    }

    public /* synthetic */ hr1(WindowAndroid windowAndroid, OfflinePageBridge offlinePageBridge, OfflinePageItem offlinePageItem, Callback callback) {
        this.g = windowAndroid;
        this.h = offlinePageBridge;
        this.i = offlinePageItem;
        this.f = callback;
    }
}
