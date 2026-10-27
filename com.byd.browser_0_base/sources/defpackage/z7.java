package defpackage;

import J.N;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.runtime.R$id;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.customtabs.BaseCustomTabActivity;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.history.HistoryActivity;
import org.chromium.chrome.browser.metrics.UmaSessionStats;
import org.chromium.chrome.browser.offlinepages.downloads.OfflinePageDownloadBridge;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.chrome.browser.webapps.WebappActivity;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.components.offline_items_collection.a;
import org.chromium.components.omnibox.R$drawable;
import org.chromium.components.signin.identitymanager.IdentityManager;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: ArCoreInstallUtilsJni.java */
/* JADX INFO: loaded from: classes3.dex */
public class z7 implements c72, us0, ne1 {
    public static z7 l;
    public static gq1 o;
    public static boolean p;
    public static Method q;
    public static z7 s;
    public static final a41 e = new a41(2, "NO_DECISION");
    public static final Object[] f = new Object[0];
    public static final int[] g = new int[0];
    public static final Object[] h = new Object[0];
    public static final hi1 i = new hi1("dev_ui", d90.class, "e90");
    public static final a41 j = new a41(2, "REMOVED_TASK");
    public static final a41 k = new a41(2, "CLOSED_EMPTY");
    public static final a41 m = new a41(2, "CONDITION_FALSE");
    public static final String[] n = {"chrome"};
    public static final String[] r = {"af", "am", "ar", "ar-XB", "as", "az", "be", "bg", "bn", "bs", "ca", "cs", "da", "de", "el", "en-GB", "en-US", "en-XA", "es", "es-419", "et", "eu", "fa", "fi", "fil", "fr", "fr-CA", "gl", "gu", "he", "hi", "hr", "hu", "hy", "id", "is", "it", "ja", "ka", "kk", "km", "kn", "ko", "ky", "lo", "lt", "lv", "mk", "ml", "mn", "mr", "ms", "my", "nb", "ne", "nl", "or", "pa", "pl", "pt-BR", "pt-PT", "ro", "ru", "si", "sk", "sl", "sq", "sr", "sr-Latn", "sv", "sw", "ta", "te", "th", "tr", "uk", "ur", "uz", "vi", "zh-CN", "zh-HK", "zh-TW", "zu"};

    public /* synthetic */ z7() {
    }

    public static int A(int i2) {
        return Color.rgb(Color.red(i2), Color.green(i2), Color.blue(i2));
    }

    public static boolean B() {
        if (!a8.P()) {
            return false;
        }
        sk skVar = bq.a;
        return N.M6bsIDpc("CommercePriceTracking", "enable_price_notification", false);
    }

    public static int C(int i2, boolean z, boolean z2, boolean z3) {
        if (i2 == 0) {
            if (z && z2) {
                return 0;
            }
            return R$drawable.omnibox_info;
        }
        if (i2 == 3 || i2 == 4) {
            return z3 ? R$drawable.omnibox_https_valid_arrow : R$drawable.omnibox_https_valid;
        }
        if (i2 == 5 || i2 == 6) {
            return R$drawable.omnibox_not_secure_warning;
        }
        return 0;
    }

    public static SigninManager D(Profile profile) {
        Object obj = ThreadUtils.a;
        return (SigninManager) N.MOZZ$5wu(profile);
    }

    public static String E(String[] strArr, String str) {
        if (strArr == null) {
            return null;
        }
        String strG = gq2.g("--", str, "=");
        for (String str2 : strArr) {
            if (str2 != null && str2.startsWith(strG)) {
                return str2.substring(strG.length());
            }
        }
        return null;
    }

    public static Tab F(w03 w03Var, int i2) {
        int iG = G(w03Var, i2);
        if (iG == -1) {
            return null;
        }
        return w03Var.getTabAt(iG);
    }

    public static int G(w03 w03Var, int i2) {
        int count = w03Var.getCount();
        for (int i3 = 0; i3 < count; i3++) {
            if (w03Var.getTabAt(i3).getId() == i2) {
                return i3;
            }
        }
        return -1;
    }

    public static int H(Set set) {
        Iterator it = set.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i2 = ~(~(i2 + (next != null ? next.hashCode() : 0)));
        }
        return i2;
    }

    public static boolean I(Context context) {
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    public static View J(int i2, Context context) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        ku2 ku2VarT = ku2.t();
        try {
            View viewInflate = layoutInflaterFrom.inflate(i2, (ViewGroup) null, false);
            ku2VarT.close();
            return viewInflate;
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public static String K(int i2, String str, String str2) {
        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str2 + ":30443/wrs/wtp/getEncryptSalt?code=" + str + "&model=" + String.valueOf(i2) + "&version=2.2").openConnection();
            httpsURLConnection.setConnectTimeout(2000);
            httpsURLConnection.setReadTimeout(2000);
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setRequestProperty("user-agent", "csm");
            httpsURLConnection.setDoInput(true);
            int responseCode = httpsURLConnection.getResponseCode();
            StringBuilder sb = new StringBuilder();
            if (responseCode == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream(), "utf-8"));
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line);
                }
            }
            httpsURLConnection.disconnect();
            return sb.toString();
        } catch (Exception unused) {
            Log.i("WtpLogOut", "initGetConnection error!");
            return "false";
        }
    }

    public static gq1 L() {
        Object obj = ThreadUtils.a;
        if (o == null) {
            a aVarZ = a8.z();
            hd0 hd0Var = DownloadManagerService.g().f;
            if (OfflinePageDownloadBridge.a == null) {
                OfflinePageDownloadBridge.a = new OfflinePageDownloadBridge();
            }
            o = new gq1(aVarZ, hd0Var);
        }
        return o;
    }

    public static boolean M(Activity activity) {
        return activity == null || activity.isDestroyed() || activity.isFinishing();
    }

    public static boolean N() {
        if (!a8.P()) {
            return Q();
        }
        if (!Q()) {
            return false;
        }
        sk skVar = bq.a;
        return N.M6bsIDpc("CommercePriceTracking", "enable_price_drop_badge", false);
    }

    public static boolean O() {
        if (!a8.P()) {
            return Q();
        }
        if (!Q()) {
            return false;
        }
        sk skVar = bq.a;
        return N.M6bsIDpc("CommercePriceTracking", "enable_price_drop_iph", false);
    }

    public static boolean P() {
        return Q() && B();
    }

    public static boolean Q() {
        if (R()) {
            z7 z7VarM = m();
            Profile profileD = Profile.d();
            z7VarM.getClass();
            if (v(profileD).b(1) && N.Mfmn09fr(Profile.d())) {
                return true;
            }
        }
        return false;
    }

    public static boolean R() {
        boolean zM6bsIDpc;
        if (a8.P()) {
            sk skVar = bq.a;
            zM6bsIDpc = N.M6bsIDpc("CommercePriceTracking", "enable_price_tracking", false);
        } else {
            zM6bsIDpc = false;
        }
        return zM6bsIDpc || B();
    }

    public static boolean S(int i2) {
        return x(i2) > 0.94f;
    }

    public static String T(int i2) {
        switch (i2) {
            case 0:
                return "InvalidMessage";
            case 1:
                return "SavePassword";
            case 2:
                return "UpdatePassword";
            case 3:
                return "GeneratedPasswordSaved";
            case 4:
                return "PopupBlocked";
            case 5:
                return "SafetyTip";
            case 6:
                return "SaveAddressProfile";
            case 7:
                return "MerchantTrust";
            case 8:
                return "AddToHomescreenIPH";
            case 9:
                return "SendTabToSelf";
            case 10:
                return "ReaderMode";
            case 11:
                return "ChromeSurvey";
            case 12:
                return "SaveCard";
            case 13:
                return "NotificationBlocked";
            case 14:
                return "PermissionUpdate";
            case 15:
                return "AdsBlocked";
            case 16:
                return "DownloadProgress";
            case 17:
                return "SyncError";
            case 18:
                return "SharedHighlighting";
            case 19:
                return "NearOomReduction";
            case 20:
                return "InstallableAmbientBadge";
            case 21:
                return "AutoDarkWebContents";
            case 22:
                return "TestMessage";
            case 23:
                return "TailoredSecurityEnabled";
            case 24:
                return "VrServicesUpgrade";
            case 25:
                return "TailoredSecurityDisabled";
            case 26:
                return "ArCoreUpgrade";
            case 27:
            case 35:
            default:
                return "Unknown";
            case 28:
                return "AboutThisSite";
            case 29:
                return "Translate";
            case 30:
                return "OfferNotification";
            case 31:
                return "ExternalNavigation";
            case 32:
                return "FramebustBlocked";
            case 33:
                return "DesktopSiteGlobalDefaultOptOut";
            case 34:
                return "DesktopSiteGlobalOptIn";
            case 36:
                return "DownloadIncognitoWarning";
            case 37:
                return "RestoreCustomTab";
            case 38:
                return "UndoCustomTabRestoration";
        }
    }

    public static Context V(op opVar) {
        opVar.getClass();
        Context context = xz.a;
        a8.n(context);
        return context;
    }

    public static q2 W(hp hpVar) {
        q2 q2Var = hpVar.h;
        a8.n(q2Var);
        return q2Var;
    }

    public static void X(int i2) {
        String str = i2 != 3 ? i2 != 4 ? i2 != 5 ? null : "TasksSurface.FakeBox.Lens" : "NewTabPage.SearchBox.Lens" : "MobileOmniboxLens";
        if (str != null) {
            n3.X0(str);
        }
    }

    public static void Y(int i2) {
        oj2.U(i2, 15, "ContextMenu.LensSupportStatus");
    }

    public static void a0(int i2) {
        fa1.g("MessagesMetrics", "Triggered message stacking animation type %s.", Integer.valueOf(i2));
        oj2.U(i2, 8, "Android.Messages.Stacking");
    }

    public static String b(int i2, kj3 kj3Var) {
        String str = "Unknown";
        String strConcat = "GoogleUpdate.Result.".concat(i2 != 0 ? i2 != 1 ? "Unknown" : "TimeWindow" : "Session");
        if (kj3Var == null) {
            return strConcat;
        }
        StringBuilder sbK = C0173a.k(strConcat, ".");
        sbK.append(kj3Var.Y() == 2 ? "Intent" : "Unknown");
        StringBuilder sbK2 = C0173a.k(sbK.toString(), ".");
        int iW = kj3Var.W();
        if (iW == 2) {
            str = "Menu";
        } else if (iW == 3) {
            str = "Infobar";
        } else if (iW == 4) {
            str = "Notification";
        }
        sbK2.append(str);
        return sbK2.toString();
    }

    public static void b0(int i2) {
        oj2.U(i2, 13, "PasswordManager.BulkCheck.UserActionAndroid");
    }

    public static void c() {
        if (!ThreadUtils.g()) {
            throw new AssertionError("Should be on UI thread.");
        }
    }

    public static void c0(int i2) {
        oj2.U(i2, 7, "BrowserServices.VerificationResult");
    }

    public static Intent d(String str, String str2, boolean z) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str2));
        intent.setPackage(str);
        intent.addFlags(268435456);
        intent.putExtra("org.chromium.chrome.browser.webapk_force_navigation", z);
        return intent;
    }

    public static void d0(int i2) {
        oj2.U(i2, 10, "UsageStats.Events");
    }

    public static boolean e(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static boolean e0(nz1 nz1Var) {
        if (nz1Var == null) {
            return false;
        }
        return nz1Var.e || nz1Var.c || nz1Var.d || nz1Var.b;
    }

    public static boolean f(Set set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                if (set.size() == set2.size()) {
                    if (set.containsAll(set2)) {
                        return true;
                    }
                }
                return false;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public static final void f0(View view, b71 b71Var) {
        i21.e(view, "<this>");
        view.setTag(R$id.view_tree_lifecycle_owner, b71Var);
    }

    public static String g(int i2, int i3, String str, String str2) {
        String strF = gq2.f(str, ":30443/wrs/wtp/checkUrl?version=2.2");
        if (i3 == 1) {
            strF = gq2.f(str, ":30443/wrs/wtp/urlLog?version=2.2");
        }
        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(strF).openConnection();
            httpsURLConnection.setConnectTimeout(i2);
            httpsURLConnection.setReadTimeout(i2);
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setRequestProperty("Content-Type", "application/json");
            httpsURLConnection.setRequestProperty("user-agent", "csm");
            httpsURLConnection.setDoOutput(true);
            OutputStream outputStream = httpsURLConnection.getOutputStream();
            byte[] bytes = str2.getBytes("utf-8");
            outputStream.write(bytes, 0, bytes.length);
            outputStream.flush();
            outputStream.close();
            int responseCode = httpsURLConnection.getResponseCode();
            StringBuilder sb = new StringBuilder();
            if (responseCode != 200) {
                return String.valueOf(100);
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream(), "utf-8"));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    httpsURLConnection.disconnect();
                    return sb.toString();
                }
                sb.append(line);
            }
        } catch (Exception unused) {
            return String.valueOf(100);
        }
    }

    public static void g0(Intent intent, ComponentName componentName) {
        Context context = xz.a;
        if (TextUtils.equals(componentName.getPackageName(), context.getPackageName())) {
            if (componentName.getClassName() == null || !TextUtils.equals(componentName.getClassName(), "com.google.android.apps.chrome.Main")) {
                intent.setComponent(componentName);
            } else {
                intent.setClass(context, ChromeTabbedActivity.class);
            }
        }
    }

    public static WeakReference h(int i2) {
        if (i2 == -1) {
            return null;
        }
        for (Activity activity : ApplicationStatus.b()) {
            if (activity instanceof WebappActivity) {
                BaseCustomTabActivity baseCustomTabActivity = (BaseCustomTabActivity) activity;
                Tab tab = baseCustomTabActivity.g1.b;
                if (tab != null && tab.getId() == i2) {
                    return new WeakReference(baseCustomTabActivity);
                }
            }
        }
        return null;
    }

    public static boolean h0(int i2) {
        float fRed = Color.red(i2) / 255.0f;
        float fGreen = Color.green(i2) / 255.0f;
        float fBlue = Color.blue(i2) / 255.0f;
        return Math.abs(1.05f / (((((fBlue > 0.03928f ? 1 : (fBlue == 0.03928f ? 0 : -1)) < 0 ? fBlue / 12.92f : (float) Math.pow((double) ((fBlue + 0.055f) / 1.055f), 2.4000000953674316d)) * 0.0722f) + ((((fGreen > 0.03928f ? 1 : (fGreen == 0.03928f ? 0 : -1)) < 0 ? fGreen / 12.92f : (float) Math.pow((double) ((fGreen + 0.055f) / 1.055f), 2.4000000953674316d)) * 0.7152f) + ((fRed < 0.03928f ? fRed / 12.92f : (float) Math.pow((fRed + 0.055f) / 1.055f, 2.4000000953674316d)) * 0.2126f))) + 0.05f)) >= 3.0f;
    }

    public static void i() {
        try {
            TraceEvent.q("ProfileManagerUtils.commitPendingWritesForAllProfiles", null);
            N.MPpDwRXN();
        } finally {
            TraceEvent.C("ProfileManagerUtils.commitPendingWritesForAllProfiles");
        }
    }

    public static boolean i0(int i2) {
        return x(i2) > 0.82f;
    }

    public static z7 j(String str) {
        if (!str.startsWith("remote-playback://")) {
            return null;
        }
        try {
            new String(Base64.decode(str.substring(18), 8), "UTF-8");
            return new z7(str);
        } catch (UnsupportedEncodingException | IllegalArgumentException e2) {
            Log.e("cr_MediaRemoting", "Couldn't parse the source id.", e2);
            return null;
        }
    }

    public static void j0(Activity activity, Tab tab, boolean z) {
        Context context = xz.a;
        if (DeviceFormFactor.a(activity)) {
            tab.b(new LoadUrlParams("chrome-native://history/", 0));
            return;
        }
        Intent intent = new Intent();
        intent.setClass(context, HistoryActivity.class);
        intent.putExtra("org.chromium.chrome.browser.parent_component", activity.getComponentName());
        intent.putExtra("org.chromium.chrome.browser.incognito_mode", z);
        activity.startActivity(intent);
    }

    public static m33 k(Tab tab) {
        ByteBuffer byteBuffer;
        on2 on2Var;
        if (!tab.isInitialized()) {
            return null;
        }
        m33 m33Var = new m33();
        int iJ = 0;
        if (CriticalPersistedTabData.m(tab).s != null) {
            on2Var = CriticalPersistedTabData.m(tab).s;
        } else {
            LoadUrlParams loadUrlParamsP = tab.p();
            if (loadUrlParamsP == null) {
                byteBuffer = (ByteBuffer) N.MNwGha8e(tab.c());
            } else {
                on2 on2Var2 = loadUrlParamsP.e;
                byteBuffer = (ByteBuffer) N.M_N0bb_o(loadUrlParamsP.a, on2Var2 != null ? (String) on2Var2.b : null, on2Var2 != null ? on2Var2.a : 0, loadUrlParamsP.b, tab.isIncognito());
            }
            if (byteBuffer == null) {
                on2Var = null;
            } else {
                on2 on2Var3 = new on2(byteBuffer);
                on2Var3.a = 2;
                on2Var = on2Var3;
            }
        }
        m33Var.a = on2Var;
        jy2 jy2Var = (jy2) tab.B().b(jy2.class);
        m33Var.e = jy2Var != null ? jy2Var.f : null;
        m33Var.b = CriticalPersistedTabData.m(tab).p;
        m33Var.d = CriticalPersistedTabData.m(tab).r;
        m33Var.g = CriticalPersistedTabData.m(tab).w;
        if (tab.F() && !tab.isNativePage()) {
            iJ = tab.j();
        }
        m33Var.f = iJ;
        m33Var.c = CriticalPersistedTabData.m(tab).q;
        m33Var.i = CriticalPersistedTabData.m(tab).y;
        return m33Var;
    }

    public static final Object[] k0(Collection collection) {
        i21.e(collection, "collection");
        int size = collection.size();
        Object[] objArr = f;
        if (size == 0) {
            return objArr;
        }
        Iterator it = collection.iterator();
        if (!it.hasNext()) {
            return objArr;
        }
        Object[] objArrCopyOf = new Object[size];
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            objArrCopyOf[i2] = it.next();
            if (i3 >= objArrCopyOf.length) {
                if (!it.hasNext()) {
                    return objArrCopyOf;
                }
                int i4 = ((i3 * 3) + 1) >>> 1;
                if (i4 <= i3) {
                    i4 = 2147483645;
                    if (i3 >= 2147483645) {
                        throw new OutOfMemoryError();
                    }
                }
                objArrCopyOf = Arrays.copyOf(objArrCopyOf, i4);
                i21.d(objArrCopyOf, "copyOf(result, newSize)");
            } else if (!it.hasNext()) {
                Object[] objArrCopyOf2 = Arrays.copyOf(objArrCopyOf, i3);
                i21.d(objArrCopyOf2, "copyOf(result, size)");
                return objArrCopyOf2;
            }
            i2 = i3;
        }
    }

    public static Integer l(OfflineItem offlineItem) {
        if (offlineItem.j) {
            return 7;
        }
        int i2 = offlineItem.h;
        if (i2 == 0) {
            return 1;
        }
        if (i2 == 1) {
            return 2;
        }
        if (i2 != 2) {
            return i2 != 3 ? 6 : 4;
        }
        return 3;
    }

    public static final Object[] l0(Collection collection, Object[] objArr) {
        Object[] objArrCopyOf;
        i21.e(collection, "collection");
        objArr.getClass();
        int size = collection.size();
        int i2 = 0;
        if (size == 0) {
            if (objArr.length <= 0) {
                return objArr;
            }
            objArr[0] = null;
            return objArr;
        }
        Iterator it = collection.iterator();
        if (!it.hasNext()) {
            if (objArr.length <= 0) {
                return objArr;
            }
            objArr[0] = null;
            return objArr;
        }
        if (size <= objArr.length) {
            objArrCopyOf = objArr;
        } else {
            Object objNewInstance = Array.newInstance(objArr.getClass().getComponentType(), size);
            i21.c(objNewInstance, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            objArrCopyOf = (Object[]) objNewInstance;
        }
        while (true) {
            int i3 = i2 + 1;
            objArrCopyOf[i2] = it.next();
            if (i3 >= objArrCopyOf.length) {
                if (!it.hasNext()) {
                    return objArrCopyOf;
                }
                int i4 = ((i3 * 3) + 1) >>> 1;
                if (i4 <= i3) {
                    i4 = 2147483645;
                    if (i3 >= 2147483645) {
                        throw new OutOfMemoryError();
                    }
                }
                objArrCopyOf = Arrays.copyOf(objArrCopyOf, i4);
                i21.d(objArrCopyOf, "copyOf(result, newSize)");
            } else if (!it.hasNext()) {
                if (objArrCopyOf == objArr) {
                    objArr[i3] = null;
                    return objArr;
                }
                Object[] objArrCopyOf2 = Arrays.copyOf(objArrCopyOf, i3);
                i21.d(objArrCopyOf2, "copyOf(result, size)");
                return objArrCopyOf2;
            }
            i2 = i3;
        }
    }

    public static z7 m() {
        if (l == null) {
            l = new z7();
        }
        return l;
    }

    public static String m0(int i2) {
        return i2 == 0 ? "csacsacascasc@vsdvdsvdvdv%^*dsvdsvvdsvdsv" : gq2.e("csacsacascasc@vsdvdsvdvdv%^*dsvdsvvdsvdsv", i2);
    }

    public static Activity n(WebContents webContents) {
        WindowAndroid windowAndroidV;
        if (webContents == null || webContents.isDestroyed() || (windowAndroidV = webContents.V()) == null) {
            return null;
        }
        return (Activity) windowAndroidV.k().get();
    }

    public static void n0(int i2) {
        if (!(i2 >= 0 && i2 <= 2)) {
            throw new q80("Invalid enum value.");
        }
    }

    public static int o(int i2, int i3, float f2, boolean z) {
        float fRed = Color.red(i2);
        int iF = (int) C0173a.f(Color.red(i3), fRed, f2, fRed);
        float fGreen = Color.green(i2);
        int iF2 = (int) C0173a.f(Color.green(i3), fGreen, f2, fGreen);
        float fBlue = Color.blue(i2);
        int iF3 = (int) C0173a.f(Color.blue(i3), fBlue, f2, fBlue);
        if (!z) {
            return Color.rgb(iF, iF2, iF3);
        }
        float fAlpha = Color.alpha(i2);
        return Color.argb((int) C0173a.f(Color.alpha(i3), fAlpha, f2, fAlpha), iF, iF2, iF3);
    }

    public static void o0(int i2) {
        if (!(i2 >= 0 && i2 <= 2)) {
            throw new q80("Invalid enum value.");
        }
    }

    public static Tab p(w03 w03Var) {
        int iIndex = w03Var.index();
        if (iIndex != -1) {
            return w03Var.getTabAt(iIndex);
        }
        Log.e("TabModelUtils", "  getCurrentTab: index == TabModel.INVALID_TAB_INDEX   true");
        return null;
    }

    public static String q(String str) {
        if (str == null || !str.contains("b%:&")) {
            return str;
        }
        String str2 = str.split("b%:&")[1];
        Charset charset = C0177c.a;
        try {
            byte[] bArrDecode = Base64.decode(str2, 2);
            Charset charset2 = C0177c.a;
            SecretKeySpec secretKeySpec = new SecretKeySpec("com.byd.browser.".getBytes(charset2), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(2, secretKeySpec);
            return new String(cipher.doFinal(bArrDecode), charset2);
        } catch (Exception e2) {
            Log.e("c", "decrypt---->" + e2);
            return null;
        }
    }

    public static k13 r() {
        hi1 hi1Var = ac2.f;
        if (hi1Var.d()) {
            if (UmaSessionStats.a()) {
                sk skVar = bq.a;
                if (!N.M09VlOh_("TabGridLayoutAndroid")) {
                    N.MT4iKtWs("TabGridLayoutAndroidSyntheticTrial", "Downloaded_Control", 0);
                }
                if (!N.M09VlOh_("TabGroupsAndroid")) {
                    N.MT4iKtWs("TabGroupsAndroidSyntheticTrial", "Downloaded_Control", 0);
                }
            }
            return (k13) hi1Var.a();
        }
        va3 va3Var = new va3();
        try {
            hi1Var.b().c("tab_management");
            va3Var.close();
            if (!UmaSessionStats.a()) {
                return null;
            }
            N.MT4iKtWs("TabGridLayoutAndroidSyntheticTrial", "DownloadAttempted", 0);
            N.MT4iKtWs("TabGroupsAndroidSyntheticTrial", "DownloadAttempted", 0);
            return null;
        } catch (Throwable th) {
            try {
                va3Var.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public static long s(MotionEvent motionEvent) {
        try {
            if (q == null) {
                q = Class.forName("android.view.MotionEvent").getMethod("getEventTimeNano", new Class[0]);
            }
            return ((Long) q.invoke(motionEvent, new Object[0])).longValue();
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            TraceEvent.v0("MotionEventUtils::getEventTimeNano error", e2.toString());
            return motionEvent.getEventTime() * 1000000;
        }
    }

    public static boolean t() {
        return SharedPreferencesManager.getInstance().readBoolean("first_run_flow", false);
    }

    public static String u(int i2, String str) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? "" : str.concat(".Addresses") : str.concat(".CreditCards") : str.concat(".Passwords") : str;
    }

    public static IdentityManager v(Profile profile) {
        Object obj = ThreadUtils.a;
        return (IdentityManager) N.MjWAsIev(profile);
    }

    public static final int w(List list) {
        i21.e(list, "<this>");
        return list.size() - 1;
    }

    public static float x(int i2) {
        int iRed = Color.red(i2);
        int iGreen = Color.green(i2);
        int iBlue = Color.blue(i2);
        return ((Math.min(iRed, Math.min(iGreen, iBlue)) + Math.max(iRed, Math.max(iGreen, iBlue))) / 2) / 255.0f;
    }

    public static long y(Bundle bundle, String str) {
        String string = bundle.getString(str);
        if (string == null || !string.endsWith("L")) {
            return 2147483648L;
        }
        try {
            return Long.parseLong(string.substring(0, string.length() - 1));
        } catch (NumberFormatException unused) {
            return 2147483648L;
        }
    }

    public static Tab z(w03 w03Var, int i2) {
        Tab tab = null;
        long j2 = 0;
        for (int i3 = 0; i3 < w03Var.getCount(); i3++) {
            Tab tabAt = w03Var.getTabAt(i3);
            if (tabAt.getId() != i2 && !tabAt.n()) {
                long j3 = CriticalPersistedTabData.m(tabAt).r;
                if (j3 != -1 && j2 < j3) {
                    tab = tabAt;
                    j2 = j3;
                }
            }
        }
        return tab;
    }

    public void U(long j2, boolean z) {
        xr0.org_chromium_components_webxr_ArCoreInstallUtils_onRequestInstallSupportedArCoreResult(j2, z);
    }

    public void Z(Exception exc) {
        throw null;
    }

    @Override // defpackage.us0
    public void setVisibility(int i2) {
        throw null;
    }

    public /* synthetic */ z7(String str) {
    }

    @Override // defpackage.ne1
    public void a() {
    }
}
