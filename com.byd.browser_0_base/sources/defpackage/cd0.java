package defpackage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import defpackage.o93;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.chromium.chrome.browser.download.DownloadInfo;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.download.e;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.components.offline_items_collection.OfflineItem;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class cd0 implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ cd0(Object obj, int i, Object obj2) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i = this.e;
        Object obj = this.g;
        Object obj2 = this.f;
        switch (i) {
            case 0:
                e eVar = (e) obj2;
                eVar.getClass();
                ny nyVar = ((e.b) obj).a;
                LinkedHashMap<ny, OfflineItem> linkedHashMap = eVar.f;
                OfflineItem offlineItemRemove = linkedHashMap.remove(nyVar);
                HashMap map = eVar.k;
                boolean zContainsKey = map.containsKey(nyVar);
                yc0 yc0Var = eVar.o;
                if (zContainsKey) {
                    DownloadInfo.a aVar = new DownloadInfo.a();
                    aVar.z = nyVar;
                    DownloadInfo downloadInfo = new DownloadInfo(aVar);
                    int iIntValue = ((Integer) map.get(nyVar)).intValue();
                    ((dd0) yc0Var).getClass();
                    dy2 dy2Var = (dy2) DownloadManagerService.g().f;
                    dy2Var.getClass();
                    o93 o93Var = o93.a.a;
                    ny nyVar2 = downloadInfo.z;
                    o93Var.b(nyVar2);
                    dy2Var.b().a(iIntValue, nyVar2);
                    map.remove(nyVar);
                }
                if (nyVar != null) {
                    OTRProfileID oTRProfileIDDeserializeWithoutVerify = OTRProfileID.deserializeWithoutVerify(offlineItemRemove != null ? offlineItemRemove.z : null);
                    eVar.g();
                    ((dd0) yc0Var).getClass();
                    if (TextUtils.equals("LEGACY_ANDROID_DOWNLOAD", nyVar.a)) {
                        xz.a.startActivity(new Intent("android.intent.action.VIEW_DOWNLOADS").addFlags(268435456));
                    } else {
                        wq wqVar = new wq(2);
                        wqVar.b = oTRProfileIDDeserializeWithoutVerify != null;
                        a8.z().e(wqVar, nyVar);
                    }
                    n3.X0("Android.Download.InfoBar.LinkClicked.OpenDownload");
                } else {
                    for (OfflineItem offlineItem : linkedHashMap.values()) {
                        if (!TextUtils.isEmpty(offlineItem.z)) {
                            str = offlineItem.z;
                        }
                    }
                    OTRProfileID oTRProfileIDDeserializeWithoutVerify2 = OTRProfileID.deserializeWithoutVerify(str);
                    ((dd0) yc0Var).getClass();
                    DownloadManagerService.openDownloadsPage(oTRProfileIDDeserializeWithoutVerify2, 13);
                    n3.X0("Android.Download.InfoBar.LinkClicked.OpenDownloadHome");
                }
                break;
            default:
                n3.m1((Context) obj2, 24);
                ((xe3) obj).notifyEvent("desktop_site_default_on_primary_action");
                break;
        }
        return 1;
    }
}
