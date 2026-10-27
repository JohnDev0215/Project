package org.chromium.chrome.browser.download;

import J.N;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.hardware.bydauto_test.TestInstrumentManagerImplCallback;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.LongSparseArray;
import androidx.appcompat.app.AlertDialog;
import defpackage.aw;
import defpackage.bq;
import defpackage.dy2;
import defpackage.gd0;
import defpackage.gq2;
import defpackage.hd0;
import defpackage.i10;
import defpackage.i9;
import defpackage.kd0;
import defpackage.ku2;
import defpackage.ld0;
import defpackage.n3;
import defpackage.ny;
import defpackage.oe1;
import defpackage.oj2;
import defpackage.sk;
import defpackage.tp2;
import defpackage.vc0;
import defpackage.xb0;
import defpackage.xz;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.Callback;
import org.chromium.base.ContentUriUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.download.DownloadController;
import org.chromium.chrome.browser.download.DownloadInfo;
import org.chromium.chrome.browser.download.DownloadItem;
import org.chromium.chrome.browser.download.DownloadManagerBridge;
import org.chromium.chrome.browser.download.OMADownloadHandler;
import org.chromium.chrome.browser.download.b;
import org.chromium.chrome.browser.download.g;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileKey;
import org.chromium.chrome.browser.profiles.ProfileManager;
import org.chromium.components.download.DownloadCollectionBridge;
import org.chromium.components.prefs.PrefService;
import org.chromium.ui.widget.Toast;

/* JADX INFO: loaded from: classes.dex */
public class DownloadManagerService implements DownloadController.a, kd0, ProfileManager.a {
    public static final HashSet r = new HashSet();
    public static DownloadManagerService s;
    public final hd0 f;
    public final long g;
    public final Handler h;
    public final OMADownloadHandler k;
    public final ld0 l;
    public e m;
    public long n;
    public boolean o;
    public boolean q;
    public final HashMap<String, b> e = new HashMap<>(4, 0.75f);
    public final ArrayList i = new ArrayList();
    public final org.chromium.base.b<a> j = new org.chromium.base.b<>();
    public int p = -1;

    public interface a extends g.b {
        void a();

        void c();

        void d();

        void e();
    }

    public static class b {
        public boolean a;
        public DownloadItem b;
        public int c;
        public boolean d = false;
        public boolean e = true;
        public boolean f;

        public b(long j, boolean z, DownloadItem downloadItem, int i) {
            this.a = z;
            this.b = downloadItem;
            this.c = i;
        }
    }

    public DownloadManagerService(dy2 dy2Var, Handler handler) {
        Context context = xz.a;
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        this.f = dy2Var;
        this.g = 1000L;
        this.h = handler;
        this.l = new ld0();
        final OMADownloadHandler oMADownloadHandler = new OMADownloadHandler(context);
        this.k = oMADownloadHandler;
        xb0 xb0Var = new xb0();
        ArrayList arrayList = DownloadCollectionBridge.a;
        ku2 ku2VarT = ku2.t();
        try {
            DownloadCollectionBridge.b = xb0Var;
            ku2VarT.close();
            sk skVar = bq.a;
            if (!N.M09VlOh_("UseDownloadOfflineContentProvider")) {
                DownloadController.a = this;
            }
            sharedPreferencesManager.removeKey("DownloadUmaEntry");
            SharedPreferencesManager sharedPreferencesManager2 = oMADownloadHandler.b;
            if (sharedPreferencesManager2.contains("PendingOMADownloads")) {
                for (String str : new HashSet(sharedPreferencesManager2.h("PendingOMADownloads"))) {
                    int iIndexOf = str.indexOf(",");
                    final long j = Long.parseLong(str.substring(0, iIndexOf));
                    final String strSubstring = str.substring(iIndexOf + 1);
                    Callback callback = new Callback() { // from class: vp1
                        @Override // org.chromium.base.Callback
                        public final void onResult(Object obj) {
                            String str2;
                            long j2 = j;
                            DownloadManagerBridge.a aVar = (DownloadManagerBridge.a) obj;
                            final OMADownloadHandler oMADownloadHandler2 = oMADownloadHandler;
                            LongSparseArray<DownloadItem> longSparseArray = oMADownloadHandler2.c;
                            DownloadItem downloadItem = longSparseArray.get(j2);
                            if (downloadItem == null) {
                                downloadItem = new DownloadItem(null, true);
                                downloadItem.d = j2;
                                downloadItem.a.b = downloadItem.a();
                            }
                            DownloadItem downloadItem2 = downloadItem;
                            DownloadInfo downloadInfo = downloadItem2.c;
                            DownloadInfo.a aVar2 = downloadInfo == null ? new DownloadInfo.a() : DownloadInfo.a.b(downloadInfo);
                            aVar2.j = aVar.e;
                            aVar2.k = aVar.f;
                            if (!TextUtils.isEmpty(aVar.b)) {
                                aVar2.e = aVar.b;
                            }
                            if (!TextUtils.isEmpty(aVar.c)) {
                                aVar2.c = aVar.c;
                            }
                            aVar2.g = aVar.h;
                            final DownloadInfo downloadInfo2 = new DownloadInfo(aVar2);
                            downloadItem2.c = downloadInfo2;
                            int i = aVar.a;
                            LongSparseArray<OMADownloadHandler.a> longSparseArray2 = oMADownloadHandler2.d;
                            String str3 = strSubstring;
                            if (i == 1) {
                                DownloadInfo.a aVarB = DownloadInfo.a.b(downloadInfo2);
                                aVarB.g = aVar.h;
                                DownloadInfo downloadInfo3 = new DownloadInfo(aVarB);
                                downloadItem2.c = downloadInfo3;
                                OMADownloadHandler.a aVar3 = longSparseArray2.get(j2);
                                if (aVar3 == null) {
                                    aVar3 = new OMADownloadHandler.a();
                                    aVar3.a.put("installNotifyURI", str3);
                                }
                                oMADownloadHandler2.b(aVar3, downloadInfo3, j2, "900 Success \n\r");
                                longSparseArray2.remove(j2);
                                oMADownloadHandler2.a(j2);
                                OMADownloadHandler.c(downloadItem2, aVar.a);
                            } else if (i == 2) {
                                switch (aVar.g) {
                                    case 1002:
                                    case 1004:
                                    case 1005:
                                        str2 = "954 Loader Error \n\r";
                                        break;
                                    case 1003:
                                    case 1007:
                                    default:
                                        str2 = "952 Device Aborted \n\r";
                                        break;
                                    case 1006:
                                        str2 = "901 insufficient memory \n\r";
                                        break;
                                    case 1008:
                                        str2 = "903 Loss of Service \n\r";
                                        break;
                                }
                                final String str4 = str2;
                                final OMADownloadHandler.a aVar4 = longSparseArray2.get(j2);
                                if (aVar4 == null) {
                                    OMADownloadHandler.a aVar5 = new OMADownloadHandler.a();
                                    aVar5.a.put("installNotifyURI", str3);
                                    oMADownloadHandler2.b(aVar5, downloadInfo2, j2, str4);
                                } else {
                                    int i2 = R$string.oma_download_failed;
                                    DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: wp1
                                        @Override // android.content.DialogInterface.OnClickListener
                                        public final void onClick(DialogInterface dialogInterface, int i3) {
                                            OMADownloadHandler oMADownloadHandler3 = oMADownloadHandler2;
                                            OMADownloadHandler.a aVar6 = aVar4;
                                            DownloadInfo downloadInfo4 = downloadInfo2;
                                            String str5 = str4;
                                            int i4 = OMADownloadHandler.e;
                                            if (i3 == -1) {
                                                oMADownloadHandler3.b(aVar6, downloadInfo4, -1L, str5);
                                            } else {
                                                oMADownloadHandler3.getClass();
                                            }
                                        }
                                    };
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ApplicationStatus.c, R$style.ThemeOverlay_BrowserUI_AlertDialog);
                                    builder.i(i2);
                                    builder.f(R$string.ok, onClickListener);
                                    builder.a.k = false;
                                    builder.k();
                                    longSparseArray2.remove(j2);
                                }
                                oMADownloadHandler2.a(j2);
                                OMADownloadHandler.c(downloadItem2, aVar.a);
                            }
                            if (longSparseArray.size() == 0) {
                                return;
                            }
                            longSparseArray.remove(j2);
                            if (longSparseArray.size() == 0) {
                                oMADownloadHandler2.a.unregisterReceiver(oMADownloadHandler2);
                            }
                        }
                    };
                    Object obj = DownloadManagerBridge.a;
                    new DownloadManagerBridge.b(callback, j).c(i9.e);
                }
            }
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public static void d(String str, boolean z) {
        SharedPreferences sharedPreferences = xz.a.getSharedPreferences("DownloadRetryCount", 0);
        String strI = i(str, !z, false);
        int iMin = Math.min(sharedPreferences.getInt(strI, 0), 200);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.remove(strI);
        if (z) {
            oj2.a0(iMin, "MobileDownload.ResumptionsCount.Automatic");
        } else {
            oj2.a0(iMin, "MobileDownload.ResumptionsCount.Manual");
            String strI2 = i(str, false, true);
            oj2.a0(Math.min(sharedPreferences.getInt(strI2, 0), 500), "MobileDownload.ResumptionsCount.Total");
            editorEdit.remove(strI2);
        }
        editorEdit.apply();
    }

    public static DownloadManagerService g() {
        Object obj = ThreadUtils.a;
        if (s == null) {
            s = new DownloadManagerService(new dy2(), new Handler());
        }
        return s;
    }

    public static String i(String str, boolean z, boolean z2) {
        return z2 ? gq2.f(str, ".Total") : z ? gq2.f(str, ".Manual") : str;
    }

    public static Intent j(String str, long j, boolean z, String str2, String str3, String str4) {
        String string;
        if (j != -1) {
            DownloadManagerBridge.a aVarA = DownloadManagerBridge.a(j);
            String str5 = str4 == null ? aVarA.c : str4;
            Uri uriE = str == null ? aVarA.d : DownloadUtils.e(str);
            if (uriE == null || Uri.EMPTY.equals(uriE)) {
                return null;
            }
            Uri uriFromFile = str == null ? uriE : Uri.fromFile(new File(str));
            Log.e("cr_ggy createLaunchIntent", "啥情况");
            return z ? oe1.b(uriFromFile, uriE, str5, true, xz.a) : oe1.a(uriE, str5, str2, str3);
        }
        if (!ContentUriUtils.d(str)) {
            return null;
        }
        Log.e("cr_ggy getLaunchIntentFromDownloadUri", "啥情况");
        Uri uri = Uri.parse(str);
        if (str4 == null) {
            Cursor cursorQuery = xz.a.getContentResolver().query(uri, null, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.getCount() != 0) {
                        cursorQuery.moveToNext();
                        string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("mime_type"));
                        cursorQuery.close();
                        cursorQuery.close();
                    }
                } finally {
                    try {
                        cursorQuery.close();
                    } catch (Throwable unused) {
                    }
                }
            }
            return cursorQuery != null ? null : null;
        }
        string = str4;
        Log.e("cr_ggy createLaunchIntent", "啥情况");
        return z ? oe1.b(uri, uri, string, true, xz.a) : oe1.a(uri, string, str2, str3);
    }

    public static void m(int i, long j, Context context, String str, String str2, String str3, String str4, String str5, OTRProfileID oTRProfileID, boolean z) {
        Log.e("cr_ggy openDownloadedContent", "啥情况");
        new vc0(i, j, context, str, str3, str4, str5, str2, oTRProfileID, z).c(i9.e);
    }

    @SuppressLint({"ApplySharedPref", "CommitPrefEdits"})
    public static void o(SharedPreferencesManager sharedPreferencesManager, String str, HashSet hashSet, boolean z) {
        boolean zCommit;
        if (hashSet.isEmpty()) {
            if (z) {
                sharedPreferencesManager.a.getClass();
                SharedPreferences.Editor editorEdit = xz.a.a.edit();
                editorEdit.remove(str);
                zCommit = editorEdit.commit();
            } else {
                sharedPreferencesManager.removeKey(str);
                zCommit = true;
            }
        } else if (z) {
            sharedPreferencesManager.a.getClass();
            zCommit = xz.a.a.edit().putStringSet(str, hashSet).commit();
        } else {
            sharedPreferencesManager.p(str, hashSet);
            zCommit = true;
        }
        if (zCommit) {
            return;
        }
        Log.e("cr_DownloadService", "Failed to write DownloadInfo ".concat(str));
    }

    @CalledByNative
    public static void onDownloadItemCanceled(DownloadItem downloadItem, boolean z) {
        String string;
        DownloadManagerService downloadManagerServiceG = g();
        char c = z ? (char) 1007 : (char) 1009;
        downloadManagerServiceG.getClass();
        String str = downloadItem.c.e;
        Context context = xz.a;
        switch (c) {
            case 1001:
                string = context.getString(R$string.download_failed_reason_file_system_error, str);
                break;
            case 1002:
            case 1005:
                string = context.getString(R$string.download_failed_reason_server_issues, str);
                break;
            case 1003:
            default:
                string = context.getString(R$string.download_failed_reason_unknown_error, str);
                break;
            case 1004:
            case 1008:
                string = context.getString(R$string.download_failed_reason_network_failures, str);
                break;
            case 1006:
                string = context.getString(R$string.download_failed_reason_insufficient_space, str);
                break;
            case 1007:
                string = context.getString(R$string.download_failed_reason_storage_not_found, str);
                break;
            case 1009:
                string = context.getString(R$string.download_failed_reason_file_already_exists, str);
                break;
        }
        ld0 ld0Var = downloadManagerServiceG.l;
        if (ld0Var.a() == null) {
            Toast.b(0, xz.a, string).e();
            return;
        }
        boolean z2 = c == 1009;
        OTRProfileID oTRProfileID = downloadItem.c.u;
        e eVar = g().m;
        if (((eVar == null || eVar.p == null) ? false : true) || ld0Var.a() == null) {
            return;
        }
        tp2 tp2VarA = tp2.a(string, ld0Var, 1, 10);
        tp2VarA.i = false;
        tp2VarA.j = TestInstrumentManagerImplCallback.SET_INSTRUMENT_THEME;
        if (z2) {
            tp2VarA.d = xz.a.getString(R$string.open_downloaded_label);
            tp2VarA.e = null;
        }
        ld0Var.a().c(tp2VarA);
    }

    @CalledByNative
    public static void openDownloadsPage(OTRProfileID oTRProfileID, int i) {
        if (DownloadUtils.showDownloadManager(null, null, oTRProfileID, i, false)) {
            return;
        }
        Log.e("cr_ggy openDownloadsPage", "打开了么");
    }

    @Override // defpackage.kd0
    public final void a(ny nyVar, OTRProfileID oTRProfileID) {
        N.MmztvsiA(k(), this, nyVar.b, n3.l0(oTRProfileID));
        b bVar = this.e.get(nyVar.b);
        if (bVar != null) {
            int i = bVar.c;
            if (i == 4 || i == 0) {
                DownloadInfo.a aVarB = DownloadInfo.a.b(bVar.b.c);
                aVarB.s = true;
                aVarB.j = -1L;
                DownloadInfo downloadInfo = new DownloadInfo(aVarB);
                DownloadItem downloadItem = new DownloadItem(downloadInfo, false);
                if (downloadInfo.s) {
                    downloadItem.a();
                }
                p(downloadItem, 0);
                n();
            }
        }
    }

    @CalledByNative
    public final void addDownloadItemToList(List<DownloadItem> list, DownloadItem downloadItem) {
        list.add(downloadItem);
    }

    @Override // org.chromium.chrome.browser.profiles.ProfileManager.a
    public final void b(Profile profile) {
        ProfileManager.b(this);
        N.MFfdOo0Y(this.n, this, profile);
    }

    @Override // defpackage.kd0
    public final void c(ny nyVar, OTRProfileID oTRProfileID) {
        N.MV30ev0v(k(), this, nyVar.b, n3.l0(oTRProfileID));
        HashMap<String, b> map = this.e;
        b bVar = map.get(nyVar.b);
        if (bVar == null) {
            ((dy2) this.f).c(nyVar);
            return;
        }
        l(new DownloadInfo(DownloadInfo.a.b(bVar.b.c)));
        String str = nyVar.b;
        map.remove(str);
        r.remove(str);
    }

    @CalledByNative
    public final List<DownloadItem> createDownloadItemList() {
        return new ArrayList();
    }

    @Override // defpackage.kd0
    public final void h(ny nyVar, DownloadItem downloadItem, boolean z) {
        HashMap<String, b> map = this.e;
        b bVar = map.get(downloadItem.a());
        if (bVar == null || bVar.c != 0 || bVar.b.c.s) {
            int i = z ? 2 : 4;
            List<String> list = gd0.a;
            oj2.U(i, 7, "MobileDownload.DownloadResumption");
            if (bVar == null) {
                HashSet hashSet = r;
                if (!hashSet.contains(downloadItem.a())) {
                    hashSet.add(downloadItem.a());
                    oj2.U(1, 7, "MobileDownload.DownloadResumption");
                }
                p(downloadItem, 0);
                bVar = map.get(downloadItem.a());
            }
            if (z) {
                if (!bVar.a) {
                    bVar.a = ((ConnectivityManager) xz.a.getSystemService("connectivity")).isActiveNetworkMetered();
                }
                String strA = downloadItem.a();
                String strI = i(strA, true, false);
                SharedPreferences sharedPreferences = xz.a.getSharedPreferences("DownloadRetryCount", 0);
                int i2 = sharedPreferences.getInt(strI, 0);
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.putInt(strI, i2 + 1);
                editorEdit.apply();
                String strI2 = i(strA, true, true);
                SharedPreferences sharedPreferences2 = xz.a.getSharedPreferences("DownloadRetryCount", 0);
                int i3 = sharedPreferences2.getInt(strI2, 0);
                SharedPreferences.Editor editorEdit2 = sharedPreferences2.edit();
                editorEdit2.putInt(strI2, i3 + 1);
                editorEdit2.apply();
                d(downloadItem.a(), true);
            } else {
                int i4 = xz.a.getSharedPreferences("DownloadRetryCount", 0).getInt(downloadItem.a(), 0);
                if (this.p < 0) {
                    this.p = N.M3NaDnJv();
                }
                if (i4 >= this.p) {
                    downloadItem.a();
                    DownloadInfo downloadInfo = downloadItem.c;
                    p(new DownloadItem(downloadInfo, false), downloadInfo.r ? 4 : 2);
                    return;
                }
                String strA2 = downloadItem.a();
                String strI3 = i(strA2, false, false);
                SharedPreferences sharedPreferences3 = xz.a.getSharedPreferences("DownloadRetryCount", 0);
                int i5 = sharedPreferences3.getInt(strI3, 0);
                SharedPreferences.Editor editorEdit3 = sharedPreferences3.edit();
                editorEdit3.putInt(strI3, i5 + 1);
                editorEdit3.apply();
                String strI4 = i(strA2, false, true);
                SharedPreferences sharedPreferences4 = xz.a.getSharedPreferences("DownloadRetryCount", 0);
                int i6 = sharedPreferences4.getInt(strI4, 0);
                SharedPreferences.Editor editorEdit4 = sharedPreferences4.edit();
                editorEdit4.putInt(strI4, i6 + 1);
                editorEdit4.apply();
            }
            if (ProfileManager.b || !downloadItem.c.t) {
                N.MieiRHrs(k(), this, downloadItem.a(), n3.l0(downloadItem.c.u), z);
            }
        }
    }

    @CalledByNative
    public final void handleOMADownload(DownloadItem downloadItem, long j) {
        DownloadInfo downloadInfo = downloadItem.c;
        this.k.getClass();
    }

    public final long k() {
        if (this.n == 0) {
            boolean z = ProfileManager.b;
            this.n = N.MeJ$lv4P(this, z);
            if (!z) {
                ProfileManager.a(this);
            }
        }
        return this.n;
    }

    public final void l(DownloadInfo downloadInfo) {
        DownloadInfo.a aVarB = DownloadInfo.a.b(downloadInfo);
        aVarB.w = 2;
        new DownloadItem(aVarB.a(), false).a();
        p(new DownloadItem(downloadInfo, false), 3);
    }

    public final void n() {
        if (this.o) {
            return;
        }
        this.o = true;
        ArrayList arrayList = new ArrayList();
        for (b bVar : this.e.values()) {
            if (bVar.e) {
                arrayList.add(bVar);
            }
        }
        if (arrayList.isEmpty()) {
            this.o = false;
            return;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            r((b) arrayList.get(i));
        }
        this.h.postDelayed(new aw(25, this), this.g);
    }

    @CalledByNative
    public final void onAllDownloadsRetrieved(List<DownloadItem> list, ProfileKey profileKey) {
        Iterator<a> it = this.j.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                break;
            } else {
                ((a) aVar.next()).e();
            }
        }
        PrefService prefServiceK = oj2.k(Profile.d());
        if (prefServiceK.a("download.show_missing_sd_card_error_android")) {
            org.chromium.chrome.browser.download.b bVar = b.e.a;
            bVar.a(new i10(this, bVar, list, prefServiceK, 1));
        }
    }

    @CalledByNative
    public final void onDownloadItemCreated(DownloadItem downloadItem) {
        Iterator<a> it = this.j.iterator();
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
    public final void onDownloadItemRemoved(String str, OTRProfileID oTRProfileID) {
        Iterator<a> it = this.j.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).c();
            }
        }
    }

    @CalledByNative
    public final void onDownloadItemUpdated(DownloadItem downloadItem) {
        Iterator<a> it = this.j.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).d();
            }
        }
    }

    @CalledByNative
    public void onResumptionFailed(String str) {
        DownloadInfo.a aVar = new DownloadInfo.a();
        aVar.m = str;
        aVar.F = 1;
        ((dy2) this.f).d(new DownloadInfo(aVar));
        this.e.remove(str);
        r.remove(str);
        List<String> list = gd0.a;
        oj2.U(3, 7, "MobileDownload.DownloadResumption");
    }

    @CalledByNative
    public final void openDownloadItem(DownloadItem downloadItem, int i) {
        downloadItem.getClass();
    }

    public final void p(DownloadItem downloadItem, int i) {
        boolean z = i == 1 && N.M4t0L845(downloadItem.c.c);
        String strA = downloadItem.a();
        HashMap<String, b> map = this.e;
        b bVar = map.get(strA);
        downloadItem.c.getClass();
        HashSet hashSet = r;
        if (bVar == null) {
            if (downloadItem.c.s) {
                return;
            }
            b bVar2 = new b(System.currentTimeMillis(), ((ConnectivityManager) xz.a.getSystemService("connectivity")).isActiveNetworkMetered(), downloadItem, i);
            bVar2.e = true;
            bVar2.f = z;
            map.put(strA, bVar2);
            hashSet.add(strA);
            if (i != 0) {
                r(bVar2);
                return;
            }
            return;
        }
        bVar.c = i;
        bVar.b = downloadItem;
        bVar.e = true;
        bVar.d = this.i.contains(strA);
        bVar.f = z;
        if (i == 0) {
            if (downloadItem.c.s) {
                r(bVar);
            }
        } else if (i != 1 && i != 2 && i != 3) {
            if (i != 4) {
                return;
            }
            r(bVar);
        } else {
            d(strA, true);
            d(strA, false);
            r(bVar);
            hashSet.remove(strA);
        }
    }

    public final void q(String str, OTRProfileID oTRProfileID) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        N.M2cL0nU9(k(), this, str, n3.l0(oTRProfileID));
    }

    public final void r(b bVar) {
        boolean z;
        DownloadItem downloadItem = bVar.b;
        DownloadInfo downloadInfo = downloadItem.c;
        int i = bVar.c;
        hd0 hd0Var = this.f;
        boolean z2 = true;
        if (i != 0) {
            if (i == 1) {
                try {
                    new c(this, downloadItem, downloadInfo, bVar.f).c(i9.e);
                } catch (RejectedExecutionException unused) {
                    Log.e("cr_DownloadService", "Thread limit reached, reschedule notification update later.");
                    z2 = false;
                }
            } else if (i == 2) {
                ((dy2) hd0Var).d(downloadInfo);
                Log.w("cr_DownloadService", "Download failed: " + downloadInfo.g);
            } else if (i == 3) {
                ((dy2) hd0Var).c(downloadItem.a);
            } else if (i == 4) {
                boolean z3 = bVar.d;
                dy2 dy2Var = (dy2) hd0Var;
                dy2Var.getClass();
                dy2.a aVar = new dy2.a(4, downloadInfo, 0);
                aVar.f = z3;
                aVar.g = 1;
                dy2Var.a(aVar);
                z = !bVar.d;
            }
            z = z2;
        } else if (downloadInfo.s) {
            dy2 dy2Var2 = (dy2) hd0Var;
            dy2Var2.getClass();
            dy2Var2.a(new dy2.a(1, downloadInfo, 0));
            List<String> list = gd0.a;
            oj2.U(0, 7, "MobileDownload.DownloadResumption");
            z = z2;
        } else {
            dy2 dy2Var3 = (dy2) hd0Var;
            dy2Var3.getClass();
            dy2Var3.a(new dy2.a(0, downloadInfo, 1));
            z = false;
        }
        if (z2) {
            bVar.e = false;
        }
        if (z) {
            this.e.remove(downloadItem.a());
        }
    }

    @Override // defpackage.kd0
    public final void f() {
    }

    @Override // org.chromium.chrome.browser.profiles.ProfileManager.a
    public final void e(Profile profile) {
    }
}
