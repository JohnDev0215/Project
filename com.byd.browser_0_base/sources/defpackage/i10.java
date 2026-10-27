package defpackage;

import J.N;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.ContentUriUtils;
import org.chromium.chrome.browser.creator.CreatorCoordinator;
import org.chromium.chrome.browser.download.DownloadInfo;
import org.chromium.chrome.browser.download.DownloadItem;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.download.b;
import org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$QueryResult;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.prefs.PrefService;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i10 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;
    public final /* synthetic */ Object i;

    public /* synthetic */ i10(Object obj, Object obj2, Object obj3, Object obj4, int i) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
        this.h = obj3;
        this.i = obj4;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        boolean z;
        int i = this.e;
        Object obj2 = this.i;
        Object obj3 = this.h;
        Object obj4 = this.g;
        Object obj5 = this.f;
        switch (i) {
            case 0:
                CreatorCoordinator creatorCoordinator = (CreatorCoordinator) obj5;
                WebFeedBridge$QueryResult webFeedBridge$QueryResult = (WebFeedBridge$QueryResult) obj;
                PropertyModel propertyModel = creatorCoordinator.j;
                propertyModel.p(n10.a, webFeedBridge$QueryResult.a.getBytes());
                propertyModel.p(n10.b, webFeedBridge$QueryResult.b);
                PropertyModel.r<String> rVar = n10.c;
                if (TextUtils.isEmpty((CharSequence) propertyModel.j(rVar))) {
                    String str = webFeedBridge$QueryResult.c;
                    propertyModel.p(rVar, str);
                    propertyModel.p(n10.d, N.MpICpYBr(new GURL(str)));
                }
                creatorCoordinator.b();
                break;
            case 1:
                DownloadManagerService downloadManagerService = (DownloadManagerService) obj5;
                b bVar = (b) obj4;
                List<DownloadItem> list = (List) obj3;
                PrefService prefService = (PrefService) obj2;
                ArrayList<ma0> arrayList = (ArrayList) obj;
                HashSet hashSet = DownloadManagerService.r;
                downloadManagerService.getClass();
                if (arrayList.size() <= 1) {
                    String str2 = bVar.a ? bVar.e : null;
                    for (DownloadItem downloadItem : list) {
                        String str3 = downloadItem.c.g;
                        if (TextUtils.isEmpty(str3) || str3.contains(str2) || ContentUriUtils.d(str3)) {
                            z = false;
                        } else {
                            for (ma0 ma0Var : arrayList) {
                                if (!TextUtils.isEmpty(ma0Var.b) && str3.contains(ma0Var.b)) {
                                    z = false;
                                }
                            }
                            z = true;
                        }
                        DownloadInfo downloadInfo = downloadItem.c;
                        int i2 = downloadInfo.w;
                        if (!((i2 == 3 && !downloadInfo.r) || i2 == 2) && z) {
                            downloadManagerService.h.post(new l10(7, downloadManagerService));
                            prefService.e("download.show_missing_sd_card_error_android", false);
                            break;
                        }
                    }
                    break;
                }
                break;
            default:
                Callback callback = (Callback) obj5;
                Tab tab = (Tab) obj4;
                Class cls = (Class) obj3;
                String str4 = (String) obj2;
                l02 l02Var = (l02) obj;
                if (l02Var != null) {
                    HashMap map = l02.l;
                    if (l02Var.j()) {
                        callback.onResult(new mc((Object) tab, 4, str4, (Object) cls));
                    }
                }
                l02.k(cls, str4, tab, l02Var);
                break;
        }
    }
}
