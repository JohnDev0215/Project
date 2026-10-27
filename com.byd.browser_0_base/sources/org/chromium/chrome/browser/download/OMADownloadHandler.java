package org.chromium.chrome.browser.download;

import J.N;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.LongSparseArray;
import androidx.appcompat.app.AlertController;
import androidx.appcompat.app.AlertDialog;
import defpackage.dy2;
import defpackage.i9;
import defpackage.n3;
import defpackage.yl1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.download.OMADownloadHandler;
import org.chromium.chrome.browser.download.g;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class OMADownloadHandler extends BroadcastReceiver {
    public static final /* synthetic */ int e = 0;
    public final Context a;
    public final SharedPreferencesManager b;
    public final LongSparseArray<DownloadItem> c = new LongSparseArray<>();
    public final LongSparseArray<a> d = new LongSparseArray<>();

    public static class a {
        public final HashMap a = new HashMap();

        public a() {
            new ArrayList();
        }
    }

    public class b extends i9<Boolean> {
        public final a h;
        public final DownloadInfo i;
        public final String j;
        public final long k;
        public DownloadInfo l;

        public b(a aVar, DownloadInfo downloadInfo, long j, String str) {
            this.h = aVar;
            this.i = downloadInfo;
            this.j = str;
            this.k = j;
        }

        /* JADX WARN: Removed duplicated region for block: B:49:0x00a6  */
        @Override // defpackage.i9
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Boolean b() throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 276
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.download.OMADownloadHandler.b.b():java.lang.Object");
        }

        @Override // defpackage.i9
        public final void l(Boolean bool) {
            boolean zBooleanValue = bool.booleanValue();
            OMADownloadHandler oMADownloadHandler = OMADownloadHandler.this;
            if (zBooleanValue) {
                if (this.l != null) {
                    ((dy2) DownloadManagerService.g().f).e(this.l, this.k, false, false);
                }
                int i = OMADownloadHandler.e;
                oMADownloadHandler.d(this.h);
                return;
            }
            long j = this.k;
            if (j != -1) {
                ((DownloadManager) oMADownloadHandler.a.getSystemService("download")).remove(j);
            }
        }
    }

    static {
        new yl1("oma_download_handler_android");
    }

    public OMADownloadHandler(Context context) {
        new org.chromium.base.b();
        this.a = context;
        this.b = SharedPreferencesManager.getInstance();
    }

    public static void c(DownloadItem downloadItem, int i) {
        DownloadManagerService downloadManagerServiceG = DownloadManagerService.g();
        OTRProfileID oTRProfileID = downloadItem.c.u;
        e eVar = downloadManagerServiceG.m;
        if (eVar == null) {
            return;
        }
        OfflineItem offlineItem = new OfflineItem();
        DownloadInfo downloadInfo = downloadItem.c;
        offlineItem.e = downloadInfo.z;
        offlineItem.u = downloadInfo.g;
        offlineItem.f = downloadInfo.e;
        offlineItem.g = downloadInfo.f;
        offlineItem.i = downloadInfo.B;
        offlineItem.k = downloadInfo.C;
        offlineItem.j = false;
        offlineItem.o = downloadInfo.k;
        long j = downloadInfo.j;
        offlineItem.D = j;
        offlineItem.B = downloadInfo.r;
        GURL gurl = downloadInfo.a;
        offlineItem.w = gurl;
        offlineItem.x = downloadInfo.i;
        offlineItem.y = downloadInfo.t;
        offlineItem.z = OTRProfileID.serialize(downloadInfo.u);
        String str = downloadInfo.c;
        offlineItem.v = str;
        offlineItem.E = downloadInfo.p;
        offlineItem.F = downloadInfo.q;
        offlineItem.G = downloadInfo.y;
        offlineItem.I = downloadInfo.E;
        int i2 = downloadInfo.F;
        offlineItem.H = i2;
        offlineItem.l = downloadInfo.G;
        offlineItem.s = downloadInfo.x;
        offlineItem.q = downloadItem.e;
        offlineItem.r = downloadItem.f;
        offlineItem.p = downloadItem.g;
        offlineItem.m = downloadItem.c.w == 1;
        boolean z = downloadInfo.s;
        int i3 = downloadInfo.w;
        if (i3 == 0) {
            offlineItem.A = z ? 6 : 0;
        } else if (i3 == 1) {
            offlineItem.A = j == 0 ? 5 : 2;
        } else if (i3 == 2) {
            offlineItem.A = 3;
        } else if (i3 == 3) {
            int iMOENIRAW = N.MOENIRAW(gurl.j(), i2);
            if (iMOENIRAW == 0 || iMOENIRAW == 4) {
                offlineItem.A = 4;
            } else if (z) {
                offlineItem.A = 6;
            } else {
                f fVarA = g.a.a.a(downloadItem.a);
                if (fVarA != null && downloadItem.c.w == 3 && fVarA.e) {
                    offlineItem.A = 1;
                } else {
                    offlineItem.A = 5;
                }
            }
        }
        int iP = n3.P(str);
        if (iP == 1) {
            offlineItem.h = 0;
        } else if (iP == 2) {
            offlineItem.h = 1;
        } else if (iP == 3) {
            offlineItem.h = 2;
        } else if (iP == 4) {
            offlineItem.h = 3;
        } else if (iP != 5) {
            offlineItem.h = 5;
        } else {
            offlineItem.h = 4;
        }
        offlineItem.e.a = "LEGACY_ANDROID_DOWNLOAD";
        if (i == 1) {
            offlineItem.A = 2;
        } else if (i == 2) {
            offlineItem.A = 5;
        }
        eVar.d(offlineItem, null);
    }

    public final void a(long j) {
        HashSet hashSet = DownloadManagerService.r;
        SharedPreferencesManager sharedPreferencesManager = this.b;
        HashSet<String> hashSet2 = new HashSet(sharedPreferencesManager.h("PendingOMADownloads"));
        for (String str : hashSet2) {
            int iIndexOf = str.indexOf(",");
            long j2 = Long.parseLong(str.substring(0, iIndexOf));
            str.substring(iIndexOf + 1);
            if (j2 == j) {
                hashSet2.remove(str);
                DownloadManagerService.o(sharedPreferencesManager, "PendingOMADownloads", hashSet2, false);
                return;
            }
        }
    }

    public final void b(a aVar, DownloadInfo downloadInfo, long j, String str) {
        boolean z;
        if (aVar == null || TextUtils.isEmpty((String) aVar.a.get("installNotifyURI"))) {
            z = false;
        } else {
            new b(aVar, downloadInfo, j, str).c(i9.e);
            z = true;
        }
        if (z) {
            return;
        }
        d(aVar);
    }

    public final void d(a aVar) {
        if (TextUtils.isEmpty((String) aVar.a.get("nextURL"))) {
            return;
        }
        final String str = (String) aVar.a.get("nextURL");
        final Activity activity = ApplicationStatus.c;
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: xp1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                int i2 = OMADownloadHandler.e;
                OMADownloadHandler oMADownloadHandler = this.e;
                oMADownloadHandler.getClass();
                if (i == -1) {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                    Activity activity2 = activity;
                    intent.putExtra("com.android.browser.application_id", activity2.getPackageName());
                    intent.putExtra("create_new_tab", true);
                    intent.setPackage(oMADownloadHandler.a.getPackageName());
                    activity2.startActivity(intent);
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.i(R$string.open_url_post_oma_download);
        builder.f(R$string.ok, onClickListener);
        builder.e(R$string.cancel, onClickListener);
        AlertController.AlertParams alertParams = builder.a;
        alertParams.f = str;
        alertParams.k = false;
        builder.k();
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        intent.getAction();
    }
}
