package org.chromium.chrome.browser.download;

import J.N;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import defpackage.ar1;
import defpackage.bq;
import defpackage.iz2;
import defpackage.md0;
import defpackage.n3;
import defpackage.oj2;
import defpackage.pm0;
import defpackage.q11;
import defpackage.qj;
import defpackage.qo;
import defpackage.sk;
import defpackage.xz;
import java.io.File;
import java.util.List;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContentUriUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.app.download.home.DownloadActivity;
import org.chromium.chrome.browser.download.b;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileKey;
import org.chromium.chrome.browser.profiles.ProfileManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: loaded from: classes.dex */
public class DownloadUtils {
    public static void a(ProfileKey profileKey) {
        sk skVar = bq.a;
        if (N.M09VlOh_("UseDownloadOfflineContentProvider")) {
            return;
        }
        if (profileKey.a) {
            DownloadManagerService downloadManagerServiceG = DownloadManagerService.g();
            N.MQ35Y$D$(downloadManagerServiceG.k(), downloadManagerServiceG, profileKey);
        }
        DownloadManagerService downloadManagerServiceG2 = DownloadManagerService.g();
        N.MQ35Y$D$(downloadManagerServiceG2.k(), downloadManagerServiceG2, ProfileKey.a());
        n3.X0("Android.DownloadManager.CheckForExternallyRemovedItems");
    }

    public static boolean b(Intent intent) {
        if (!ProfileManager.b) {
            return false;
        }
        OTRProfileID oTRProfileIDDeserializeWithoutVerify = OTRProfileID.deserializeWithoutVerify(q11.p(intent.getExtras(), "org.chromium.chrome.browser.download.OTR_PROFILE_ID"));
        if (oTRProfileIDDeserializeWithoutVerify != null) {
            Profile profileD = Profile.d();
            if (!N.MQioXkwA(profileD.b, profileD, oTRProfileIDDeserializeWithoutVerify)) {
                return false;
            }
        }
        return true;
    }

    public static void c(Context context, Tab tab) {
        Profile profileB;
        ar1 ar1Var = new ar1(context, tab);
        if (tab.m()) {
            OfflinePageBridge offlinePageBridgeA = OfflinePageBridge.a(Profile.b(tab.c()));
            N.MNR_O1IV(offlinePageBridgeA.a, offlinePageBridgeA, tab.c(), "async_loading", tab.getUrl().j(), 1, ar1Var.a());
        } else {
            N.MgaTXnFG(tab, ar1Var.a());
            oj2.Z(Math.round(tab.q() * 100.0f), "OfflinePages.SavePage.PercentLoaded");
        }
        WebContents webContentsC = tab.c();
        if (webContentsC == null || (profileB = Profile.b(webContentsC)) == null) {
            return;
        }
        oj2.t(profileB).notifyEvent("download_page_started");
    }

    public static CharSequence d(String str, String str2, boolean z, long j, ClickableSpan clickableSpan) {
        String str3;
        SpannableString spannableString = new SpannableString(str2);
        spannableString.setSpan(new StyleSpan(1), 0, str2.length(), 33);
        spannableString.setSpan(clickableSpan, 0, str2.length(), 33);
        if (!z) {
            return TextUtils.expandTemplate(str, spannableString);
        }
        if (j > 0) {
            str3 = " (" + md0.a(xz.a, j) + ")";
        } else {
            str3 = "";
        }
        return TextUtils.expandTemplate(str, spannableString, str3);
    }

    public static Uri e(String str) {
        if (ContentUriUtils.d(str)) {
            return Uri.parse(str);
        }
        if (!b.c(str)) {
            return pm0.b(new File(str));
        }
        String[] strArr = DownloadFileProvider.i;
        b.c cVar = new b.c();
        if (ContentUriUtils.d(str)) {
            return Uri.parse(str);
        }
        if (TextUtils.isEmpty(str)) {
            return Uri.EMPTY;
        }
        File fileB = b.b();
        if (str.indexOf(fileB.getAbsolutePath()) == 0 && str.length() > fileB.getAbsolutePath().length()) {
            return DownloadFileProvider.c("download", str.substring(fileB.getAbsolutePath().length() + 1));
        }
        b.f fVarA = cVar.a();
        List<File> list = fVarA.a;
        if (list != null) {
            for (File file : list) {
                if (file != null && str.startsWith(file.getAbsolutePath())) {
                    return DownloadFileProvider.c("external_volume", str.substring(file.getAbsolutePath().length() + 1));
                }
            }
        }
        List<File> list2 = fVarA.b;
        if (list2 == null) {
            return Uri.EMPTY;
        }
        for (File file2 : list2) {
            if (file2 != null && str.startsWith(file2.getAbsolutePath())) {
                return DownloadFileProvider.c("download_external", str.substring(file2.getAbsolutePath().length() + 1));
            }
        }
        return Uri.EMPTY;
    }

    public static boolean f(Tab tab) {
        if (tab == null || tab.isIncognito() || !N.MXyz2Okt(tab.getUrl())) {
            return false;
        }
        if (!tab.m()) {
            return !org.chromium.chrome.browser.offlinepages.d.e(tab);
        }
        OfflinePageBridge offlinePageBridgeA = OfflinePageBridge.a(Profile.b(tab.c()));
        return N.Mvkx0jqI(offlinePageBridgeA.a, offlinePageBridgeA, tab.c());
    }

    public static void g(Context context, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        intent.addFlags(268435456);
        intent.setPackage(context.getPackageName());
        context.startActivity(intent);
    }

    @CalledByNative
    public static String getUriStringForPath(String str) {
        if (ContentUriUtils.d(str)) {
            return str;
        }
        Uri uriE = e(str);
        return uriE != null ? uriE.toString() : new String();
    }

    public static boolean h() {
        if (qo.f().c()) {
            return true;
        }
        return xz.a.getResources().getConfiguration().keyboard != 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00d8  */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void openDownload(java.lang.String r7, java.lang.String r8, java.lang.String r9, org.chromium.chrome.browser.profiles.OTRProfileID r10, java.lang.String r11, java.lang.String r12, int r13) {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.download.DownloadUtils.openDownload(java.lang.String, java.lang.String, java.lang.String, org.chromium.chrome.browser.profiles.OTRProfileID, java.lang.String, java.lang.String, int):void");
    }

    @CalledByNative
    public static boolean showDownloadManager(Activity activity, Tab tab, OTRProfileID oTRProfileID, int i, boolean z) {
        boolean zA;
        Profile profileB;
        Profile profileB2;
        if (activity == null) {
            activity = ApplicationStatus.c;
        }
        Context context = xz.a;
        if (tab == null && (activity instanceof ChromeTabbedActivity)) {
            ChromeTabbedActivity chromeTabbedActivity = (ChromeTabbedActivity) activity;
            Tab tabV2 = chromeTabbedActivity.v2();
            zA = chromeTabbedActivity.S;
            tab = tabV2;
        } else {
            zA = DeviceFormFactor.a(activity != null ? activity : context);
        }
        if (oTRProfileID == null && tab != null && (profileB2 = Profile.b(tab.c())) != null) {
            oTRProfileID = profileB2.a;
        }
        OTRProfileID oTRProfileID2 = OTRProfileID.b;
        if (oTRProfileID != null) {
            Profile profileD = Profile.d();
            if (!N.MQioXkwA(profileD.b, profileD, oTRProfileID)) {
                return false;
            }
        }
        if (zA) {
            LoadUrlParams loadUrlParams = new LoadUrlParams("csacsacascasc@vsdvdsvdvdv%^*dsvdsvvdsvdsv", 0);
            if (tab == null || !tab.isInitialized()) {
                new iz2(false).b(2, null, loadUrlParams);
                Log.i("DownloadUtils", "fww showDownloadManager createNewTab: 打开一个下载内容界面");
            } else {
                tab.b(loadUrlParams);
                Intent intentA = org.chromium.chrome.browser.b.a(tab.getId(), 1);
                intentA.addFlags(268435456);
                try {
                    context.startActivity(intentA, null);
                } catch (ActivityNotFoundException unused) {
                }
                Log.i("DownloadUtils", "fww showDownloadManager createNewTab: 去ChromeLauncherActivity");
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(context, DownloadActivity.class);
            intent.putExtra("org.chromium.chrome.browser.download.SHOW_PREFETCHED_CONTENT", z);
            if (oTRProfileID != null) {
                intent.putExtra("org.chromium.chrome.browser.download.OTR_PROFILE_ID", OTRProfileID.serialize(oTRProfileID));
            }
            if (activity == null) {
                intent.addFlags(268435456);
                context.startActivity(intent);
            } else {
                intent.addFlags(671088640);
                activity.startActivity(intent);
            }
        }
        if (qj.a().g()) {
            oj2.t(oTRProfileID == null ? Profile.d() : Profile.d().e(oTRProfileID)).notifyEvent("download_home_opened");
        }
        oj2.U(i, 16, "Android.DownloadPage.OpenSource");
        if (tab != null && (profileB = Profile.b(tab.c())) != null) {
            int iC = Profile.c(profileB);
            oj2.U(iC, 6, "Download.OpenDownloads.PerProfileType");
            if (i == 9) {
                oj2.U(iC, 6, "Download.OpenDownloadsFromMenu.PerProfileType");
            }
        }
        return true;
    }
}
