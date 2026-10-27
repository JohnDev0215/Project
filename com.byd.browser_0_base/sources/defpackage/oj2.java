package defpackage;

import J.N;
import android.app.AppOpsManager;
import android.app.UiStyleConfig;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.os.Binder;
import android.os.Bundle;
import android.os.Process;
import android.os.SystemClock;
import android.support.v4.media.MediaBrowserCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewParent;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.wrsapi.wtp.jni.WtpJni;
import defpackage.d00;
import defpackage.om3;
import defpackage.sc1;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import org.chromium.base.SysUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileManager;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.components.commerce.core.ShoppingService;
import org.chromium.components.prefs.PrefService;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.content_public.browser.BrowserContextHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.EventForwarder;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: SettingsFeatureList.java */
/* JADX INFO: loaded from: classes2.dex */
public class oj2 {
    public static o53 o = null;
    public static String r = "false";
    public static int s = 1;
    public static String t = null;
    public static int u = -1;
    public static final hi1 a = new hi1("stack_unwinder", gr2.class, "org.chromium.chrome.modules.stack_unwinder.StackUnwinderModuleContentsImpl");
    public static final sh b = new sh("TabStripRedesign", "enable_folio", false);
    public static final sh c = new sh("TabStripRedesign", "enable_detached", true);
    public static final sh d = new sh("TabToGTSAnimation", "skip-slow-zooming", true);
    public static final rb0 e = new rb0("TabGridLayoutAndroid", "thumbnail_aspect_ratio", 0.62d);
    public static final rb0 f = new rb0("TabGridLayoutAndroid", "thumbnail_landscape_aspect_ratio", 1.4d);
    public static final k11 g = new k11(2048, "TabToGTSAnimation", "zooming-min-memory-mb");
    public static final sh h = new sh("TabGridLayoutAndroid", "enable_tab_group_auto_creation", true);
    public static final sh i = new sh("TabGridLayoutAndroid", "show_open_in_tab_group_menu_item_first", false);
    public static final sh j = new sh("GridTabSwitcherForTablets", "delay_creation", true);
    public static final rb0 k = new rb0("TabStripImprovements", "min_tab_width", 108.0d);
    public static final sh l = new sh("TabSelectionEditorV2", "enable_share", false);
    public static final sh m = new sh("TabSelectionEditorV2", "enable_longpress_entrypoint", false);
    public static final sh n = new sh("TabSelectionEditorV2", "enable_bookmarks", false);
    public static final oj2 p = new oj2();
    public static final oj2 q = new oj2();

    public /* synthetic */ oj2() {
    }

    public static boolean A(String str) {
        if (!str.isEmpty()) {
            String strTrim = str.trim();
            if (strTrim.startsWith("{") && strTrim.endsWith("}")) {
                return true;
            }
        }
        return false;
    }

    public static boolean B() {
        Profile profileD;
        ShoppingService shoppingServiceY;
        if (!ProfileManager.b || (profileD = Profile.d()) == null || (shoppingServiceY = n3.Y(profileD)) == null) {
            return false;
        }
        long j2 = shoppingServiceY.a;
        if (j2 == 0) {
            return false;
        }
        return N.Mno1SAkR(j2, shoppingServiceY);
    }

    public static boolean C(Context context) {
        return D(context) && bq.b0.a();
    }

    public static boolean D(Context context) {
        if (DeviceFormFactor.a(context) || g90.a(context) || !bq.a0.a()) {
            return false;
        }
        return z7.r() != null;
    }

    public static boolean E(Context context) {
        return D(context) && bq.d0.a();
    }

    public static boolean F() {
        return b.c();
    }

    public static boolean G() {
        return Double.compare(1.0d, e.c()) != 0;
    }

    public static boolean H() {
        k11 k11Var = g;
        k11Var.c();
        return bq.g0.a() && SysUtils.amountOfPhysicalMemoryKB() / 1024 >= k11Var.c();
    }

    public static boolean I(Context context) {
        return DeviceFormFactor.a(context) && bq.B.a();
    }

    public static int J(int i2, int i3, float f2) {
        return gv.f(gv.g(i3, Math.round(Color.alpha(i3) * f2)), i2);
    }

    public static void K(int i2) {
        U(i2, 20, "Signin.AccountConsistencyPromoAction");
    }

    public static void L(int i2) {
        U(i2, 6, "Signin.AddAccountState");
    }

    public static final int M(int i2) {
        if (i2 < 0) {
            return i2;
        }
        if (i2 < 3) {
            return i2 + 1;
        }
        if (i2 < 1073741824) {
            return (int) ((i2 / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static boolean N(WebContents webContents, float f2) {
        if (webContents == null) {
            return false;
        }
        EventForwarder eventForwarderH = webContents.H();
        long jUptimeMillis = SystemClock.uptimeMillis();
        eventForwarderH.c(12, jUptimeMillis, 0.0f);
        eventForwarderH.c(13, jUptimeMillis, f2);
        eventForwarderH.c(14, jUptimeMillis, 0.0f);
        return true;
    }

    public static void O(String str, boolean z) {
        zh3.a.a(str, z);
    }

    public static void P(int i2, String str) {
        zh3.a.c(i2, 1, 1000, 50, str);
    }

    public static void Q(int i2, String str) {
        zh3.a.c(i2, 1, 100, 50, str);
    }

    public static void R(int i2, String str) {
        zh3.a.c(i2, 1, 1000000, 50, str);
    }

    public static void S(int i2, int i3, int i4, int i5, String str) {
        zh3.a.c(i2, i3, i4, i5, str);
    }

    public static void T(String str, long j2, long j3, long j4, int i2) {
        zh3.a.c(d(j2), d(j3), d(j4), i2, str);
    }

    public static void U(int i2, int i3, String str) {
        zh3.a.e(i2, 1, i3, i3 + 1, str);
    }

    public static void V(int i2) {
        String str;
        switch (i2) {
            case 0:
                str = "Open";
                break;
            case 1:
                str = "Resume";
                break;
            case 2:
                str = "Pause";
                break;
            case 3:
                str = "Cancel";
                break;
            case 4:
                str = "MenuShare";
                break;
            case 5:
                str = "MenuDelete";
                break;
            case 6:
                str = "Retry";
                break;
            case 7:
                str = "MenuRename";
                break;
            case 8:
                str = "MenuChange";
                break;
            default:
                return;
        }
        U(i2, 9, "Android.DownloadManager.List.View.Action");
        n3.X0("Android.DownloadManager.List.View.Action.".concat(str));
    }

    public static void W(int i2, int i3, int i4, int i5, String str) {
        zh3.a.e(i2, i3, i4, i5, str);
    }

    public static void X(long j2, String str) {
        T(str, j2, 1L, 3600000L, 50);
    }

    public static void Y(long j2, String str) {
        T(str, j2, 10L, 180000L, 50);
    }

    public static void Z(int i2, String str) {
        U(i2, 101, str);
    }

    public static boolean a(Bundle bundle, Bundle bundle2) {
        if (bundle == bundle2) {
            return true;
        }
        return bundle == null ? bundle2.getInt(MediaBrowserCompat.EXTRA_PAGE, -1) == -1 && bundle2.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1) == -1 : bundle2 == null ? bundle.getInt(MediaBrowserCompat.EXTRA_PAGE, -1) == -1 && bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1) == -1 : bundle.getInt(MediaBrowserCompat.EXTRA_PAGE, -1) == bundle2.getInt(MediaBrowserCompat.EXTRA_PAGE, -1) && bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1) == bundle2.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
    }

    public static void a0(int i2, String str) {
        zh3.a.d(i2, str);
    }

    public static void b(int i2, String str) {
        if (i2 >= 0) {
            return;
        }
        StringBuilder sb = new StringBuilder(str.length() + 40);
        sb.append(str);
        sb.append(" cannot be negative but was: ");
        sb.append(i2);
        throw new IllegalArgumentException(sb.toString());
    }

    public static void b0(long j2, String str) {
        T(str, j2, 1L, 10000L, 50);
    }

    public static int c(Context context, String str) {
        int iNoteProxyOpNoThrow;
        int iMyPid = Process.myPid();
        int iMyUid = Process.myUid();
        String packageName = context.getPackageName();
        if (context.checkPermission(str, iMyPid, iMyUid) == -1) {
            return -1;
        }
        String strPermissionToOp = AppOpsManager.permissionToOp(str);
        if (strPermissionToOp != null) {
            if (packageName == null) {
                String[] packagesForUid = context.getPackageManager().getPackagesForUid(iMyUid);
                if (packagesForUid == null || packagesForUid.length <= 0) {
                    return -1;
                }
                packageName = packagesForUid[0];
            }
            if (Process.myUid() == iMyUid && Objects.equals(context.getPackageName(), packageName)) {
                AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(AppOpsManager.class);
                iNoteProxyOpNoThrow = appOpsManager == null ? 1 : appOpsManager.checkOpNoThrow(strPermissionToOp, Binder.getCallingUid(), packageName);
                if (iNoteProxyOpNoThrow == 0) {
                    iNoteProxyOpNoThrow = appOpsManager != null ? appOpsManager.checkOpNoThrow(strPermissionToOp, iMyUid, context.getOpPackageName()) : 1;
                }
            } else {
                iNoteProxyOpNoThrow = ((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteProxyOpNoThrow(strPermissionToOp, packageName);
            }
            if (iNoteProxyOpNoThrow != 0) {
                return -2;
            }
        }
        return 0;
    }

    public static void c0(View view, float f2) {
        Drawable background = view.getBackground();
        if (background instanceof sc1) {
            ((sc1) background).m(f2);
        }
    }

    public static int d(long j2) {
        if (j2 > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j2 < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j2;
    }

    public static void d0(View view) {
        Drawable background = view.getBackground();
        if (background instanceof sc1) {
            e0(view, (sc1) background);
        }
    }

    public static int e(int i2, int i3) {
        return gv.g(i2, (Color.alpha(i2) * i3) / 255);
    }

    public static void e0(View view, sc1 sc1Var) {
        ElevationOverlayProvider elevationOverlayProvider = sc1Var.e.b;
        if (elevationOverlayProvider != null && elevationOverlayProvider.a) {
            float fE = 0.0f;
            for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                fE += om3.i.e((View) parent);
            }
            sc1.b bVar = sc1Var.e;
            if (bVar.m != fE) {
                bVar.m = fE;
                sc1Var.v();
            }
        }
    }

    public static d70 f(int i2) {
        return i2 != 0 ? i2 != 1 ? new tc2() : new q50() : new tc2();
    }

    public static boolean f0(Context context, boolean z) {
        return (g90.a(context) || !bq.F.a() || z || SysUtils.isLowEndDevice()) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final b00 g(Object obj, b00 b00Var, nr0 nr0Var) {
        i21.e(nr0Var, "<this>");
        i21.e(b00Var, "completion");
        if (nr0Var instanceof ed) {
            return ((ed) nr0Var).e(obj, b00Var);
        }
        m00 context = b00Var.getContext();
        return context == dh0.e ? new j21(obj, b00Var, nr0Var) : new k21(b00Var, context, nr0Var, obj);
    }

    public static final Map g0(LinkedHashMap linkedHashMap) {
        Map.Entry entry = (Map.Entry) linkedHashMap.entrySet().iterator().next();
        Map mapSingletonMap = Collections.singletonMap(entry.getKey(), entry.getValue());
        i21.d(mapSingletonMap, "with(entries.iterator().…ingletonMap(key, value) }");
        return mapSingletonMap;
    }

    public static GURL h(String str) {
        if (TextUtils.isEmpty(str)) {
            return GURL.emptyGURL();
        }
        GURL.c();
        return (GURL) N.Ml2KxI$W(str);
    }

    public static String h0(long j2) {
        if (j2 == 0) {
            return "0";
        }
        if (j2 > 0) {
            return Long.toString(j2, 10);
        }
        char[] cArr = new char[64];
        long j3 = (j2 >>> 1) / ((long) 5);
        long j4 = 10;
        int i2 = 63;
        cArr[63] = Character.forDigit((int) (j2 - (j3 * j4)), 10);
        while (j3 > 0) {
            i2--;
            cArr[i2] = Character.forDigit((int) (j3 % j4), 10);
            j3 /= j4;
        }
        return new String(cArr, i2, 64 - i2);
    }

    public static String i(GURL gurl) {
        return N.MpICpYBr(gurl);
    }

    public static String j(int i2, GURL gurl) {
        return gurl == null ? "" : N.MN7bz_Mm(gurl, i2);
    }

    public static PrefService k(BrowserContextHandle browserContextHandle) {
        return (PrefService) N.MeUSzoBw(browserContextHandle);
    }

    public static int l() {
        try {
            BYDAutoBodyworkDevice bYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(xz.a);
            int autoType = bYDAutoBodyworkDevice.getAutoType();
            bYDAutoBodyworkDevice.getAutoModelName();
            return autoType;
        } catch (Exception e2) {
            Log.e("BydCarUtils_chrome   ", "getBydInfo:  " + e2);
            return -1;
        }
    }

    public static String m() {
        int uiStyle = UiStyleConfig.getInstance(xz.a).getUiStyle();
        Log.i("BydCarUtils_chrome   ", "getBydUiStyle:uiStyle= " + uiStyle);
        return uiStyle != 1 ? "8155f_dynasty_def" : "8155f_denza_def";
    }

    public static int n(int i2, Context context, String str) {
        TypedValue typedValueB = cc1.b(i2, context, str);
        int i3 = typedValueB.resourceId;
        if (i3 == 0) {
            return typedValueB.data;
        }
        Object obj = bz.a;
        return context.getColor(i3);
    }

    public static int o(Context context, int i2, int i3) {
        TypedValue typedValueA = cc1.a(context, i2);
        if (typedValueA == null) {
            return i3;
        }
        int i4 = typedValueA.resourceId;
        if (i4 == 0) {
            return typedValueA.data;
        }
        Object obj = bz.a;
        return context.getColor(i4);
    }

    public static int p(View view, int i2) {
        Context context = view.getContext();
        TypedValue typedValueB = cc1.b(i2, view.getContext(), view.getClass().getCanonicalName());
        int i3 = typedValueB.resourceId;
        if (i3 == 0) {
            return typedValueB.data;
        }
        Object obj = bz.a;
        return context.getColor(i3);
    }

    public static TemplateUrlService q(Profile profile) {
        Object obj = ThreadUtils.a;
        return (TemplateUrlService) N.MSnR7M2J(profile);
    }

    public static n53 r() {
        Object obj = ThreadUtils.a;
        if (o == null) {
            o = new o53(new d70(), h9.a, vj1.h());
        }
        return o;
    }

    public static String s(int i2) {
        switch (i2) {
            case 0:
                return "All";
            case 1:
                return "OfflinePage";
            case 2:
                return "Video";
            case 3:
                return "Audio";
            case 4:
                return "Image";
            case 5:
                return "Document";
            case 6:
                return "Other";
            case 7:
                return "PrefetchedOfflinePage";
            default:
                return "Invalid";
        }
    }

    public static xe3 t(Profile profile) {
        if (profile == null) {
            throw new IllegalArgumentException("Profile is required for retrieving tracker.");
        }
        if (profile.b != 0) {
            return (xe3) N.MYO54ANr(profile);
        }
        throw new uv("Native profile pointer not initialized.");
    }

    public static void u(int i2, String str, String str2) {
        String str3;
        if (str == null || str.isEmpty() || str.length() > 34 || str2 == null || str2.isEmpty()) {
            return;
        }
        t = str;
        if (i2 < 0) {
            return;
        }
        u = i2;
        String strA = qb0.a(str2);
        String cacheData = WtpJni.getCacheData();
        if (cacheData != null) {
            try {
                str3 = new String(Base64.decode(cacheData, 0));
            } catch (Exception unused) {
                Log.i("WtpLogOut", "cachedata base64 decode error!");
                WtpJni.convertFlag(2);
                str3 = null;
            }
            String strG = gq2.g("{\"log\":\"", str3.replaceAll("[^a-zA-Z0-9|,]", ""), "\"}");
            Log.i("WtpLogOut", "report data: " + strG);
            if ("100".equals(z7.g(3000, 1, strA, strG))) {
                Log.i("WtpLogOut", "report data to cloud failed!");
                WtpJni.convertFlag(0);
            } else {
                Log.i("WtpLogOut", "report data to cloud successed!");
                WtpJni.convertFlag(1);
            }
        }
        String strK = z7.K(u, t, strA);
        if (!"false".equals(strK)) {
            r = WtpJni.parseSalt(strK);
        }
        while (s < 5) {
            if (!"false".equals(r) && !"false".equals(strK)) {
                return;
            }
            Log.i("WtpLogOut", "Trying to reconnect...");
            strK = z7.K(i2, t, strA);
            if (!"false".equals(strK)) {
                r = WtpJni.parseSalt(strK);
            }
            s++;
        }
    }

    public static final b00 v(b00 b00Var) {
        i21.e(b00Var, "<this>");
        c00 c00Var = b00Var instanceof c00 ? (c00) b00Var : null;
        if (c00Var != null && (b00Var = c00Var.g) == null) {
            m00 m00Var = c00Var.f;
            i21.b(m00Var);
            d00 d00Var = (d00) m00Var.get(d00.a.e);
            if (d00Var == null || (b00Var = d00Var.q(c00Var)) == null) {
                b00Var = c00Var;
            }
            c00Var.g = b00Var;
        }
        return b00Var;
    }

    public static boolean w(int i2) {
        switch (i2) {
            case 15:
            case 31:
            case 35:
            case 43:
            case 46:
            case 47:
            case 48:
            case 50:
            case 52:
            case 56:
            case 57:
            case 61:
            case 63:
            case 65:
            case 67:
            case 71:
            case 74:
            case 83:
            case 85:
            case 88:
            case 89:
            case 90:
            case 102:
            case 111:
            case 113:
            case 115:
            case 121:
            case 129:
            case 136:
            case 140:
            case 141:
            case 143:
                return false;
            default:
                return true;
        }
    }

    public static boolean x(int i2) {
        return i2 != 0 && gv.d(i2) > 0.5d;
    }

    public static boolean y() {
        return a8.Q() && N.MrRl5lnV("HighlightManagedPrefDisclaimerAndroid");
    }

    public static boolean z(Context context) {
        if (DeviceFormFactor.a(context)) {
            return I(context);
        }
        return (z7.r() != null) || D(context) || zb2.d(context);
    }

    public /* synthetic */ oj2(g gVar) {
    }
}
