package defpackage;

import J.N;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.role.RoleManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.hardware.bydauto.ota.tsimpl.TestOtaUpgradeManagerCallBack;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.KeyboardShortcutInfo;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.a;
import com.google.android.material.elevation.ElevationOverlayProvider;
import defpackage.ct;
import defpackage.o12;
import defpackage.xl2;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.chromium.base.PiiElider;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$attr;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$integer;
import org.chromium.chrome.R$style;
import org.chromium.chrome.R$styleable;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.ShortcutHelper;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.bookmark_history.BookmarkHistoryActivity;
import org.chromium.chrome.browser.contacts_picker.ChromePickerAdapter;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.infobar.InfoBarContainer;
import org.chromium.chrome.browser.password_check.PasswordCheckBridge;
import org.chromium.chrome.browser.password_check.e;
import org.chromium.chrome.browser.password_manager.settings.PasswordReauthenticationFragment;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.price_tracking.PriceDropNotificationManagerImpl;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileKey;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabFavicon;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tab.TrustedCdn;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.components.browser_ui.site_settings.SingleCategorySettings;
import org.chromium.components.browser_ui.widget.R$string;
import org.chromium.components.commerce.core.ShoppingService;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.components.payments.Address;
import org.chromium.content.browser.framehost.RenderFrameHostDelegate;
import org.chromium.content.browser.framehost.RenderFrameHostImpl;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;
import org.chromium.device.gamepad.GamepadList;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.base.d;
import org.chromium.url.GURL;
import org.chromium.webapk.lib.common.R$color;

/* JADX INFO: compiled from: AdaptiveToolbarFeatures.java */
/* JADX INFO: loaded from: classes2.dex */
public class n3 implements c72, tv2, al0, nj, SettingsLauncher, pn0, ub {
    public static boolean A;
    public static Long B;
    public static int C;
    public static boolean D;
    public static s62 E;
    public static n3 G;
    public static Integer e;
    public static n3 g;
    public static n3 h;
    public static wd0 j;
    public static boolean t;
    public static boolean u;
    public static n3 v;
    public static e x;
    public static long y;
    public static int z;
    public static final t50 f = new t50(24, 0);
    public static final hi1 i = new hi1("cablev2_authenticator", ii1.class, "org.chromium.chrome.modules.cablev2_authenticator.ModuleImpl");
    public static final double[] k = {-3.8d, -2.2d, -1.58d, -1.22d, -0.58d, 0.0d, 0.52d, 1.22d, 1.56d, 2.22d, 3.07d, 3.8d, 5.03d, 6.03d};
    public static float l = 1.0f;
    public static final a41 m = new a41(2, "COMPLETING_ALREADY");
    public static final a41 n = new a41(2, "COMPLETING_WAITING_CHILDREN");
    public static final a41 o = new a41(2, "COMPLETING_RETRY");
    public static final a41 p = new a41(2, "TOO_LATE_TO_CANCEL");
    public static final a41 q = new a41(2, "SEALED");
    public static final tg0 r = new tg0(false);
    public static final tg0 s = new tg0(true);
    public static final nu2 w = new nu2("PageAnnotationsService", "page_annotations_base_url", "https://memex-pa.googleapis.com/v1/annotations");
    public static final x63 F = new x63();

    public /* synthetic */ n3() {
    }

    public static ms3 A(Intent intent) {
        long longExtra;
        String str;
        long longExtra2;
        String str2;
        String strR = q11.r(intent, "org.chromium.chrome.browser.webapp_id");
        String strR2 = q11.r(intent, "org.chromium.chrome.browser.webapp_url");
        if (strR == null || strR2 == null) {
            Log.e("cr_WebappInfo", "Incomplete data provided: " + strR + ", " + strR2);
            return null;
        }
        try {
            longExtra = intent.getLongExtra("org.chromium.chrome.browser.theme_color", 2147483648L);
        } catch (Throwable unused) {
            Log.e("cr_IntentUtils", "getLongExtra failed on intent " + intent);
            longExtra = 2147483648L;
        }
        boolean z2 = longExtra != 2147483648L;
        int i2 = z2 ? (int) longExtra : -1;
        String strR3 = q11.r(intent, "org.chromium.chrome.browser.webapp_icon");
        String strR4 = q11.r(intent, "org.chromium.chrome.browser.webapp_scope");
        if (TextUtils.isEmpty(strR4)) {
            strR4 = ShortcutHelper.b(strR2);
        }
        int iL = q11.l(intent, "org.chromium.chrome.browser.webapp_display_mode", 3);
        int iL2 = q11.l(intent, "org.chromium.content_public.common.orientation", 0);
        int iL3 = q11.l(intent, "org.chromium.chrome.browser.webapp_source", 0);
        int i3 = iL3 >= 17 ? 0 : iL3;
        try {
            str = strR3;
            longExtra2 = intent.getLongExtra("org.chromium.chrome.browser.background_color", 2147483648L);
        } catch (Throwable unused2) {
            Log.e("cr_IntentUtils", "getLongExtra failed on intent " + intent);
            str = strR3;
            longExtra2 = 2147483648L;
        }
        Integer numValueOf = longExtra2 != 2147483648L ? Integer.valueOf((int) longExtra2) : null;
        boolean zI = q11.i(intent, "org.chromium.chrome.browser.is_icon_generated", false);
        boolean zI2 = q11.i(intent, "org.chromium.chrome.browser.webapp_icon_adaptive", false);
        boolean zI3 = q11.i(intent, "org.chromium.chrome.browser.webapk_force_navigation", false);
        String strR5 = q11.r(intent, "org.chromium.chrome.browser.webapp_name");
        if (strR5 == null && (strR5 = q11.r(intent, "org.chromium.chrome.browser.webapp_title")) == null) {
            strR5 = "";
        }
        String str3 = strR5;
        String strR6 = q11.r(intent, "org.chromium.chrome.browser.webapp_short_name");
        if (strR6 == null) {
            String strR7 = q11.r(intent, "org.chromium.chrome.browser.webapp_title");
            str2 = strR7 != null ? strR7 : "";
        } else {
            str2 = strR6;
        }
        return new ms3(intent, i2, z2, null, new js3(strR, strR2, strR4, new ks3(str), str3, str2, iL, iL2, i3, numValueOf, xz.a.getResources().getColor(R$color.webapp_default_bg, null), zI, zI2, zI3), null);
    }

    public static String A0(String str, GURL gurl) {
        if (!TextUtils.isEmpty(str) || gurl == null || GURL.l(gurl)) {
            return str;
        }
        String strE = gurl.e();
        String str2 = "";
        if (strE == null) {
            strE = "";
        }
        String strG = gurl.g();
        if (strG != null && !strG.equals("/")) {
            str2 = strG;
        }
        return strE.concat(str2);
    }

    public static PriceDropNotificationManagerImpl B() {
        Context context = xz.a;
        return new PriceDropNotificationManagerImpl(context, new NotificationManagerProxyImpl(context));
    }

    public static String B0(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? "Unknown" : "VoiceSearch" : "Search" : "Download" : "ChromeIntro" : "Summary";
    }

    public static void C0(TabImpl tabImpl, Tab tab) {
        Integer num = tabImpl.p;
        if (num != null) {
            tabImpl.B.d(f53.class, new f53(num.intValue(), tabImpl));
        }
        tabImpl.B.d(p33.class, new p33(tabImpl, tabImpl.p));
        tabImpl.B.d(kz2.class, new kz2(tabImpl));
        tabImpl.B.d(x11.class, new x11(tabImpl));
        new le1(tabImpl);
        if (tab != null) {
            ly2.a(tabImpl).c(Long.valueOf(N.MjsSsYT7(tab.c())), "ParentTaskId");
            ly2.a(tabImpl).c(Long.valueOf(N.M848Q9ZH(tab.c())), "ParentRootTaskId");
        }
        tabImpl.B.d(py2.class, new py2(tabImpl));
        if (i92.A1()) {
            tabImpl.B.d(i92.class, new i92(tabImpl, new hs(4, tabImpl)));
        }
        new zw1(tabImpl);
        tabImpl.B.d(C0186g0.class, new C0186g0(tabImpl));
        if (!tabImpl.isIncognito() && !tabImpl.isCustomTab() && z7.Q() && xl2.r()) {
            y00 y00Var = new y00(1, tabImpl);
            if (xl2.y) {
                xl2.o(y00Var, tabImpl);
            } else {
                xl2.x.add(new xl2.e(y00Var, tabImpl));
            }
        }
        if (tabImpl.isIncognito()) {
            ct ctVar = ct.c.a;
            synchronized (ctVar.a) {
                ctVar.d();
            }
        }
    }

    public static qq D(String str, o8 o8Var) {
        Context context = xz.a;
        return new qq(context, str, new zm(new NotificationManagerProxyImpl(context), context.getResources()), o8Var);
    }

    public static void D0(Tab tab) {
        if (InfoBarContainer.d(tab) == null) {
        }
        l53.d(tab);
        fx2.g(tab);
        if ((!tab.isInitialized() ? null : (TabFavicon) tab.B().b(TabFavicon.class)) == null) {
        }
        if (((TrustedCdn) tab.B().b(TrustedCdn.class)) == null) {
        }
        jy2.d(tab);
        if (((lz2) tab.B().b(lz2.class)) == null) {
        }
    }

    public static void E() {
        e eVar = x;
        if (eVar == null) {
            return;
        }
        PasswordCheckBridge passwordCheckBridge = eVar.a;
        long j2 = passwordCheckBridge.a;
        if (j2 != 0) {
            N.M$1pAUJ0(j2);
            passwordCheckBridge.a = 0L;
        }
        x = null;
    }

    public static float E0(float[] fArr, float f2, float f3) {
        if (f3 >= 1.0f) {
            return 1.0f;
        }
        if (f3 <= 0.0f) {
            return 0.0f;
        }
        int iMin = Math.min((int) ((fArr.length - 1) * f3), fArr.length - 2);
        float f4 = (f3 - (iMin * f2)) / f2;
        float f5 = fArr[iMin];
        return C0173a.f(fArr[iMin + 1], f5, f4, f5);
    }

    public static void F() {
        ((NotificationManager) xz.a.getSystemService("notification")).cancel("incognito_tabs_open", 100);
    }

    public static boolean F0(int i2) {
        return (i2 & 1) > 0;
    }

    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1091)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    public static java.lang.Boolean G(android.view.KeyEvent r4, boolean r5, defpackage.qq0 r6, defpackage.jf1 r7) {
        /*
            int r0 = r4.getKeyCode()
            r1 = 0
            r2 = 84
            r3 = 82
            if (r5 != 0) goto L14
            if (r0 == r2) goto L11
            if (r0 != r3) goto L10
            goto L11
        L10:
            return r1
        L11:
            java.lang.Boolean r4 = java.lang.Boolean.TRUE
            return r4
        L14:
            r5 = 0
            if (r0 == r3) goto L54
            if (r0 == r2) goto L40
            r5 = 111(0x6f, float:1.56E-43)
            if (r0 == r5) goto L27
            switch(r0) {
                case 170: goto L24;
                case 171: goto L24;
                case 172: goto L24;
                case 173: goto L24;
                default: goto L20;
            }
        L20:
            switch(r0) {
                case 177: goto L24;
                case 178: goto L24;
                case 179: goto L24;
                case 180: goto L24;
                case 181: goto L24;
                case 182: goto L24;
                default: goto L23;
            }
        L23:
            goto L3f
        L24:
            java.lang.Boolean r4 = java.lang.Boolean.FALSE
            return r4
        L27:
            int r5 = r4.getAction()
            if (r5 != 0) goto L3f
            int r4 = r4.getRepeatCount()
            if (r4 != 0) goto L3f
            boolean r4 = r6.g()
            if (r4 == 0) goto L3f
            r6.i()
            java.lang.Boolean r4 = java.lang.Boolean.TRUE
            return r4
        L3f:
            return r1
        L40:
            int r6 = r4.getAction()
            if (r6 != 0) goto L51
            int r4 = r4.getRepeatCount()
            if (r4 != 0) goto L51
            int r4 = org.chromium.chrome.R$id.focus_url_bar
            r7.h1(r4, r5)
        L51:
            java.lang.Boolean r4 = java.lang.Boolean.TRUE
            return r4
        L54:
            int r6 = r4.getAction()
            if (r6 != 0) goto L65
            int r4 = r4.getRepeatCount()
            if (r4 != 0) goto L65
            int r4 = org.chromium.chrome.R$id.show_menu
            r7.h1(r4, r5)
        L65:
            java.lang.Boolean r4 = java.lang.Boolean.TRUE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.n3.G(android.view.KeyEvent, boolean, qq0, jf1):java.lang.Boolean");
    }

    public static void H(int i2, FragmentManager fragmentManager) {
        PasswordReauthenticationFragment passwordReauthenticationFragment = new PasswordReauthenticationFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("description", i2);
        bundle.putInt("scope", 0);
        passwordReauthenticationFragment.C0(bundle);
        fragmentManager.getClass();
        a aVar = new a(fragmentManager);
        aVar.d(0, passwordReauthenticationFragment, "reauthentication-manager-fragment", 1);
        if (!aVar.h) {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        }
        aVar.g = true;
        aVar.i = null;
        aVar.g();
    }

    public static boolean H0() {
        sk skVar = bq.a;
        return N.M09VlOh_("AdaptiveButtonInTopToolbarCustomizationV2") && N.M37SqSAy("AdaptiveButtonInTopToolbarCustomizationV2", "min_version_adaptive", 0) <= 4;
    }

    public static float I(float f2, float f3, float f4, float f5) {
        double d = 0.0f - f2;
        double d2 = 0.0f - f3;
        float fHypot = (float) Math.hypot(d, d2);
        double d3 = f4 - f2;
        float fHypot2 = (float) Math.hypot(d3, d2);
        double d4 = f5 - f3;
        float fHypot3 = (float) Math.hypot(d3, d4);
        float fHypot4 = (float) Math.hypot(d, d4);
        return (fHypot <= fHypot2 || fHypot <= fHypot3 || fHypot <= fHypot4) ? (fHypot2 <= fHypot3 || fHypot2 <= fHypot4) ? fHypot3 > fHypot4 ? fHypot3 : fHypot4 : fHypot2 : fHypot;
    }

    public static boolean I0(KeyEvent keyEvent) {
        return keyEvent.getKeyCode() == 66 || keyEvent.getKeyCode() == 160;
    }

    public static boolean J() {
        Iterator it = dz0.a().a.iterator();
        while (it.hasNext()) {
            if (((cz0) it.next()).b()) {
                return true;
            }
        }
        return false;
    }

    public static boolean J0(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            if (keyEvent.getKeyCode() == 20) {
                return true;
            }
            if (!keyEvent.isNumLockOn() && keyEvent.getKeyCode() == 146) {
                return true;
            }
        }
        return false;
    }

    public static boolean K0(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            if (keyEvent.getKeyCode() == 21) {
                return true;
            }
            if (!keyEvent.isNumLockOn() && keyEvent.getKeyCode() == 148) {
                return true;
            }
        }
        return false;
    }

    public static boolean L0(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            if (keyEvent.getKeyCode() == 22) {
                return true;
            }
            if (!keyEvent.isNumLockOn() && keyEvent.getKeyCode() == 150) {
                return true;
            }
        }
        return false;
    }

    public static boolean M0(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            if (keyEvent.getKeyCode() == 19) {
                return true;
            }
            if (!keyEvent.isNumLockOn() && keyEvent.getKeyCode() == 152) {
                return true;
            }
        }
        return false;
    }

    public static String N(long j2, g20 g20Var) {
        String str;
        if (j2 < 10000000) {
            N.M6ORVMPQ(g20Var.a, 2);
            str = String.format(Locale.getDefault(), "%.2f", Double.valueOf(((j2 * 100) / 1000000) / 100.0d));
        } else {
            N.M6ORVMPQ(g20Var.a, 0);
            str = String.format(Locale.getDefault(), "%d", Long.valueOf((long) Math.floor((j2 + 500000) / 1000000.0d)));
        }
        return g20Var.b(str);
    }

    public static boolean N0(ny nyVar) {
        String str;
        return (nyVar == null || (str = nyVar.a) == null || !str.startsWith("LEGACY_DOWNLOAD")) ? false : true;
    }

    public static boolean O0() {
        String language = Locale.getDefault().getLanguage();
        return language.equals("ar") || language.equals("he") || language.equals("iw") || language.equals("fa") || language.equals("ur") || language.equals("ug");
    }

    public static int P(String str) {
        if (TextUtils.isEmpty(str)) {
            return 6;
        }
        Integer num = str.equals("application/ogg") ? 3 : null;
        if (num != null) {
            return num.intValue();
        }
        String[] strArrSplit = str.toLowerCase(Locale.ROOT).split("/");
        if (strArrSplit.length != 2) {
            return 6;
        }
        if ("video".equals(strArrSplit[0])) {
            return 2;
        }
        if ("audio".equals(strArrSplit[0])) {
            return 3;
        }
        if ("image".equals(strArrSplit[0])) {
            return 4;
        }
        return "text".equals(strArrSplit[0]) ? 5 : 6;
    }

    public static boolean P0() {
        sk skVar = bq.a;
        return N.M09VlOh_("ContextualPageActions") && N.M09VlOh_("ContextualPageActionReaderMode");
    }

    public static boolean Q0() {
        Object objInvoke;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method method = cls.getMethod("getInt", String.class, Integer.TYPE);
            method.setAccessible(true);
            objInvoke = method.invoke(cls, "persist.vendor.byd.cfg.huc.steering.wheel.position", 0);
        } catch (Exception e2) {
            Log.e("cr_VehicleUtil", "isRightRudder error:false msg:" + e2.getMessage());
        }
        int iIntValue = objInvoke instanceof Integer ? ((Integer) objInvoke).intValue() : 0;
        boolean z2 = iIntValue == 1;
        Log.i("cr_VehicleUtil", "isRightRudder: " + z2);
        return z2;
    }

    public static WebContents R(RenderFrameHost renderFrameHost) {
        RenderFrameHostDelegate renderFrameHostDelegate = ((RenderFrameHostImpl) renderFrameHost).b;
        if (renderFrameHostDelegate == null || !(renderFrameHostDelegate instanceof WebContents)) {
            return null;
        }
        return (WebContents) renderFrameHostDelegate;
    }

    public static org.chromium.components.signin.identitymanager.a S() {
        Object obj = ThreadUtils.a;
        s62 s62VarM0 = m0();
        if (s62VarM0.d()) {
            return (org.chromium.components.signin.identitymanager.a) s62VarM0.b;
        }
        throw new uv("The AccountInfoService is not yet initialized!");
    }

    public static boolean S0(KeyEvent keyEvent, boolean z2, boolean z3, a23 a23Var, jf1 jf1Var, g gVar) {
        int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getRepeatCount() == 0 && !KeyEvent.isModifierKey(keyCode)) {
            if (KeyEvent.isGamepadButton(keyCode)) {
                if (GamepadList.b.a.e) {
                    return false;
                }
            } else if (!keyEvent.isCtrlPressed() && !keyEvent.isAltPressed() && keyCode != 133 && keyCode != 135 && keyCode != 140 && keyCode != 125) {
                return false;
            }
            TabModel tabModelZ = a23Var.z();
            Tab tabI = a23Var.i();
            WebContents webContentsC = tabI == null ? null : tabI.c();
            int count = tabModelZ.getCount();
            int i2 = (keyEvent.isShiftPressed() ? 536870912 : 0) | (keyEvent.isCtrlPressed() ? Integer.MIN_VALUE : 0) | (keyEvent.isAltPressed() ? 1073741824 : 0);
            int i3 = keyCode | i2;
            switch (i3) {
                case -2147483606:
                    jf1Var.h1(R$id.new_tab_menu_id, false);
                    return true;
                case -2147483600:
                    jf1Var.h1(tabModelZ.isIncognito() ? R$id.new_incognito_tab_menu_id : R$id.new_tab_menu_id, false);
                    return true;
                case -1610612694:
                    jf1Var.h1(R$id.new_incognito_tab_menu_id, false);
                    return true;
                case -1610612688:
                    jf1Var.h1(R$id.open_recently_closed_tab, false);
                    return true;
                case 100:
                case 140:
                case TestOtaUpgradeManagerCallBack.ONDOWNLOADBACKUPSUCCESS /* 1073741857 */:
                case TestOtaUpgradeManagerCallBack.ONDOWNLOADBACKUPPROGRESS /* 1073741858 */:
                    jf1Var.h1(R$id.show_menu, false);
                    return true;
                default:
                    if (z2) {
                        if (z3 && (i2 == Integer.MIN_VALUE || i2 == 1073741824)) {
                            int i4 = keyCode - 7;
                            if (i4 > 0 && i4 <= Math.min(count, 8)) {
                                tabModelZ.q(i4 - 1, 3, false);
                                return true;
                            }
                            if (i4 == 9 && count != 0) {
                                tabModelZ.q(count - 1, 3, false);
                                return true;
                            }
                        }
                        switch (i3) {
                            case -2147483641:
                                oj2.N(webContentsC, -1.0f);
                                break;
                            case -2147483616:
                            case 174:
                                jf1Var.h1(R$id.bookmark_this_page_id, false);
                                break;
                            case -2147483614:
                            case -2147483613:
                            case -1610612701:
                            case 133:
                            case 536871045:
                                jf1Var.h1(R$id.find_in_page_id, false);
                                break;
                            case -2147483612:
                                jf1Var.h1(R$id.open_history_menu_id, false);
                                break;
                            case -2147483608:
                            case 99:
                            case TestOtaUpgradeManagerCallBack.ONUPDATEFAIL /* 1073741856 */:
                                jf1Var.h1(R$id.focus_url_bar, false);
                                break;
                            case -2147483604:
                                jf1Var.h1(R$id.print_id, false);
                                break;
                            case -2147483602:
                            case -1610612690:
                            case 135:
                            case 536871047:
                                if (tabI != null) {
                                    if ((i3 & 536870912) == 536870912) {
                                        tabI.z();
                                    } else {
                                        tabI.a();
                                    }
                                    if (gVar != null && webContentsC != null && webContentsC.e0()) {
                                        gVar.E.n();
                                    } else if (tabI.getView() != null) {
                                        tabI.getView().requestFocus();
                                    }
                                }
                                break;
                            case -2147483597:
                            case -2147483514:
                            case 97:
                                Tab tabP = z7.p(tabModelZ);
                                if (tabP != null) {
                                    tabModelZ.a(tabP);
                                }
                                break;
                            case -2147483587:
                            case -2147483555:
                            case 103:
                                if (z3 && count > 1) {
                                    tabModelZ.q((tabModelZ.index() + 1) % count, 3, false);
                                }
                                break;
                            case -2147483579:
                            case 169:
                                oj2.N(webContentsC, 0.8f);
                                break;
                            case -2147483578:
                            case -2147483567:
                            case -1610612666:
                            case -1610612655:
                            case 168:
                                oj2.N(webContentsC, 1.25f);
                                break;
                            case -2147483556:
                            case -1610612675:
                            case 102:
                                if (z3 && count > 1) {
                                    tabModelZ.q(((tabModelZ.index() + count) - 1) % count, 3, false);
                                }
                                break;
                            case -1610612706:
                                jf1Var.h1(R$id.all_bookmarks_menu_id, false);
                                break;
                            case -1610612660:
                                jf1Var.h1(R$id.help_id, false);
                                break;
                            case 108:
                            case 125:
                            case TestOtaManagerImplCallback.ONUPGRADERESULT /* 1073741846 */:
                                if (tabI != null && tabI.i()) {
                                    tabI.h();
                                }
                                break;
                            case TestOtaManagerImplCallback.ONUPGRADEPROGRESS /* 1073741845 */:
                                if (tabI != null && tabI.g()) {
                                    tabI.e();
                                }
                                break;
                        }
                        return false;
                    }
                    break;
            }
        }
        return false;
    }

    public static int T(Context context, boolean z2, boolean z3) {
        if (z2) {
            return context.getColor(z3 ? org.chromium.chrome.R$color.incognito_tab_title_selected_color : org.chromium.chrome.R$color.incognito_tab_title_color);
        }
        return z3 ? oj2.n(R$attr.colorOnPrimary, context, "TabUiThemeProvider") : oj2.n(R$attr.colorOnSurface, context, "TabUiThemeProvider");
    }

    public static String U(String str, String str2, String str3, String str4, int i2, int i3) {
        boolean z2 = !TextUtils.isEmpty(str);
        boolean z3 = !TextUtils.isEmpty(str3);
        if (z2 && TextUtils.equals(str, str2)) {
            return ".PackageName";
        }
        if (z3 && TextUtils.equals(str3, str4) && i3 == i2) {
            return ".Referrer";
        }
        if (z2 || i3 == i2) {
            if (!z2) {
                str = Uri.parse(str3).getHost();
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = Uri.parse(str4).getHost();
            }
            if (TextUtils.equals(str, str2) && !TextUtils.isEmpty(str)) {
                return ".Mixed";
            }
        }
        return ".Different";
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.LinkedList, java.util.List] */
    /* JADX WARN: Type inference failed for: r2v17, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.util.List] */
    public static List V() throws IOException {
        Process processExec = Runtime.getRuntime().exec("logcat -d");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
        ?? linkedList = new LinkedList();
        Integer numValueOf = null;
        while (numValueOf == null) {
            while (true) {
                try {
                    String line = bufferedReader.readLine();
                    if (line != null) {
                        linkedList.add(line);
                        if (linkedList.size() > 1024) {
                            linkedList.removeFirst();
                        }
                    } else {
                        try {
                            break;
                        } catch (IllegalThreadStateException unused) {
                            Thread.sleep(500L);
                        }
                    }
                } catch (Throwable th) {
                    bufferedReader.close();
                    throw th;
                }
            }
            numValueOf = Integer.valueOf(processExec.exitValue());
        }
        bufferedReader.close();
        if (numValueOf.intValue() != 0) {
            String str = "Logcat failed: " + numValueOf;
            Log.w("cr_LogcatCrashExtractor", str);
            throw new IOException(str);
        }
        int size = linkedList.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (((String) linkedList.get(size)).contains("-----BEGIN BREAKPAD MICRODUMP-----")) {
                linkedList = linkedList.subList(0, size);
                linkedList.add("-----SNIPPED OUT BREAKPAD MICRODUMP FOR THIS CRASH-----");
                break;
            }
        }
        int size2 = linkedList.size();
        ?? SubList = linkedList;
        if (size2 > 256) {
            SubList = linkedList.subList(linkedList.size() - 256, linkedList.size());
        }
        ArrayList arrayList = new ArrayList(SubList.size());
        for (String str2 : SubList) {
            Pattern pattern = PiiElider.a;
            arrayList.add(PiiElider.d.matcher(PiiElider.c.matcher(Patterns.IP_ADDRESS.matcher(PiiElider.a(Patterns.EMAIL_ADDRESS.matcher(str2).replaceAll("XXX@EMAIL.ELIDED"))).replaceAll("1.2.3.4")).replaceAll("01:23:45:67:89:AB")).replaceAll("[ELIDED:CONSOLE(0)] ELIDED CONSOLE MESSAGE"));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static boolean V0(AppCompatActivity appCompatActivity, g3 g3Var, boolean z2) {
        if (m70.b == null) {
            m70.b = new m70();
        }
        if (!k1(m70.b, appCompatActivity, z2)) {
            return false;
        }
        SharedPreferencesManager.getInstance().c("Chrome.DefaultBrowserPromo.PromoedCount");
        SharedPreferencesManager.getInstance().m((int) TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis()), "Chrome.DefaultBrowserPromo.LastPromoTime");
        m70.a(wt1.e());
        Intent intentCreateRequestRoleIntent = ((RoleManager) appCompatActivity.getSystemService("role")).createRequestRoleIntent("android.app.role.BROWSER");
        WindowAndroid.c cVar = new WindowAndroid.c() { // from class: n70
            @Override // org.chromium.ui.base.WindowAndroid.c
            public final void b(Intent intent, int i2) {
            }
        };
        d dVar = g3Var.e;
        if (dVar == null) {
            Objects.toString(intentCreateRequestRoleIntent);
            return true;
        }
        dVar.a(intentCreateRequestRoleIntent, cVar, null);
        return true;
    }

    public static Optional W(String str, Profile profile) {
        Integer num = (Integer) N.MMM3n4FP(profile, str);
        return num == null ? Optional.empty() : Optional.of(Integer.valueOf(num.intValue()));
    }

    public static CustomTabsConnection W0() {
        CustomTabsConnection customTabsConnectionE = CustomTabsConnection.e();
        a8.n(customTabsConnectionE);
        return customTabsConnectionE;
    }

    public static String X(int i2) {
        if (i2 == 6) {
            return "ContextualPageActionPriceTracking";
        }
        if (i2 == 7) {
            return "ContextualPageActionReaderMode";
        }
        throw new IllegalArgumentException("Provided button variant not assigned to feature");
    }

    public static void X0(String str) {
        zh3.a.b(SystemClock.elapsedRealtime(), str);
    }

    public static ShoppingService Y(Profile profile) {
        return (ShoppingService) N.M6mAHnyc(profile);
    }

    public static void Y0(int i2, boolean z2) {
        oj2.U(i2, 4, "Android.DarkTheme.AutoDarkMode.SettingsChangeSource.".concat(z2 ? "Enabled" : "Disabled"));
    }

    public static n3 Z() {
        if (g == null) {
            g = new n3();
        }
        return g;
    }

    public static void Z0(int i2, int i3) {
        String str;
        if (i2 == 0) {
            str = "";
        } else if (i2 == 1) {
            str = "NewTabPage";
        } else {
            if (i2 != 2) {
                throw new AssertionError("The host surface item provided here is wrong.");
            }
            str = "StartSurface";
        }
        oj2.U(i3, 5, str.concat(".Module.Click"));
    }

    public static n3 a0() {
        if (h == null) {
            AppHooks.get().getClass();
            h = new n3(0);
        }
        return h;
    }

    public static void a1(int i2, int i3) {
        oj2.U(i3, 12, "VideoTutorials." + B0(i2) + ".Player.UserAction");
    }

    public static n3 b0() {
        if (G == null) {
            G = new n3();
        }
        return G;
    }

    public static void b1(int i2, int i3) {
        oj2.U(i3, 5, "VideoTutorials." + B0(i2) + ".Player.Progress");
    }

    public static String c1(int i2) {
        switch (i2) {
            case 1:
                return "Total";
            case 2:
                return "OmniboxFocus";
            case 3:
                return "NewTabPage";
            case 4:
                return "Startup";
            case 5:
                return "TabSwitcher";
            case 6:
                return "OpenLinkInNewTab";
            case 7:
                return "StartSurfaceHomepage";
            case 8:
                return "StartSurfaceTabSwitcher";
            default:
                throw new IllegalArgumentException("Invalid scenario value");
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final Class d0(w31 w31Var) {
        i21.e(w31Var, "<this>");
        Class<?> clsA = ((mt) w31Var).a();
        if (!clsA.isPrimitive()) {
            return clsA;
        }
        String name = clsA.getName();
        switch (name.hashCode()) {
            case -1325958191:
                if (!name.equals("double")) {
                }
                break;
            case 104431:
                if (!name.equals("int")) {
                }
                break;
            case 3039496:
                if (!name.equals("byte")) {
                }
                break;
            case 3052374:
                if (!name.equals("char")) {
                }
                break;
            case 3327612:
                if (!name.equals("long")) {
                }
                break;
            case 3625364:
                if (!name.equals("void")) {
                }
                break;
            case 64711720:
                if (!name.equals("boolean")) {
                }
                break;
            case 97526364:
                if (!name.equals("float")) {
                }
                break;
            case 109413500:
                if (!name.equals("short")) {
                }
                break;
        }
        return clsA;
    }

    public static WebContents e0(RenderFrameHost renderFrameHost) {
        WebContents webContentsR = R(renderFrameHost);
        if (webContentsR == null || webContentsR.isDestroyed()) {
            return null;
        }
        return webContentsR;
    }

    public static int f0(Context context, boolean z2, boolean z3) {
        if (z2) {
            return context.getColor(z3 ? org.chromium.chrome.R$color.incognito_tab_thumbnail_placeholder_selected_color : org.chromium.chrome.R$color.incognito_tab_thumbnail_placeholder_color);
        }
        int integer = context.getResources().getInteger(R$integer.tab_thumbnail_placeholder_color_alpha);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(z3 ? R$style.TabThumbnailPlaceHolderStyle_Selected : R$style.TabThumbnailPlaceHolderStyle, R$styleable.TabThumbnailPlaceHolder);
        int color = typedArrayObtainStyledAttributes.getColor(R$styleable.TabThumbnailPlaceHolder_colorTileBase, 0);
        float dimension = typedArrayObtainStyledAttributes.getDimension(R$styleable.TabThumbnailPlaceHolder_elevationTileBase, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
        if (dimension != 0.0f) {
            color = new ElevationOverlayProvider(context).a(dimension, color);
        }
        return oj2.e(color, integer);
    }

    public static final void g1(View view, ge2 ge2Var) {
        i21.e(view, "<this>");
        view.setTag(androidx.savedstate.R$id.view_tree_saved_state_registry_owner, ge2Var);
    }

    public static Profile h0(WindowAndroid windowAndroid) {
        if (windowAndroid == null) {
            return null;
        }
        aj3<wx0> aj3Var = wx0.f;
        wx0 wx0Var = (wx0) wx0.f.e(windowAndroid.r);
        if (wx0Var == null) {
            return null;
        }
        if (wx0Var.e == null) {
            OTRProfileID oTRProfileID = OTRProfileID.b;
            wx0Var.e = (OTRProfileID) N.Mgdxq2wn("CCT:Incognito");
        }
        return Profile.d().e(wx0Var.e);
    }

    public static void h1(Context context, ImageView imageView, String str, int i2) {
        int i3;
        boolean zIsEmpty = TextUtils.isEmpty(str);
        if (i2 == 0) {
            i3 = zIsEmpty ? R$string.remove : R$string.accessibility_list_menu_button;
        } else if (i2 != 1) {
            return;
        } else {
            i3 = zIsEmpty ? R$string.accessibility_toolbar_btn_menu : R$string.accessibility_list_remove_button;
        }
        imageView.setContentDescription(zIsEmpty ? context.getResources().getString(i3) : context.getResources().getString(i3, str));
    }

    public static org.chromium.chrome.browser.password_check.a i0(SettingsLauncher settingsLauncher) {
        if (x == null) {
            x = new e(settingsLauncher);
        }
        return x;
    }

    public static void i1(WebContents webContents, double d) {
        N.MItN0bQy(webContents, d, m(d, l, (float) N.MiPv6Zp_(webContents)));
    }

    public static void j(Context context, KeyboardShortcutGroup keyboardShortcutGroup, int i2, int i3, int i4) {
        keyboardShortcutGroup.addItem(new KeyboardShortcutInfo(context.getString(i2), i3, i4));
    }

    public static View j0(Toolbar toolbar) {
        ActionMenuView actionMenuView;
        int childCount = toolbar.getChildCount();
        while (true) {
            int i2 = childCount - 1;
            if (childCount <= 0) {
                actionMenuView = null;
                break;
            }
            if (toolbar.getChildAt(i2) instanceof ActionMenuView) {
                actionMenuView = (ActionMenuView) toolbar.getChildAt(i2);
                break;
            }
            childCount = i2;
        }
        if (actionMenuView == null) {
            return null;
        }
        View childAt = actionMenuView.getChildAt(actionMenuView.getChildCount() - 1);
        boolean z2 = false;
        if (childAt != null && (childAt instanceof ImageView)) {
            z2 = ((ImageView) childAt).getDrawable() == actionMenuView.getOverflowIcon();
        }
        if (z2) {
            return childAt;
        }
        return null;
    }

    public static boolean j1(Tab tab) {
        if (tab == null) {
            return false;
        }
        GURL url = tab.getUrl();
        return ((url.i().equals("chrome") || url.i().equals("chrome-native")) || url.i().equals("data")) ? false : true;
    }

    public static Profile k0(WindowAndroid windowAndroid, boolean z2) {
        if (!z2) {
            return Profile.d();
        }
        Profile profileH0 = h0(windowAndroid);
        return profileH0 != null ? profileH0 : Profile.d().f(true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x006b, code lost:
    
        if ((r7 != -1 ? (int) (java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(java.lang.System.currentTimeMillis()) - ((long) r7)) : Integer.MAX_VALUE) < 0) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean k1(defpackage.m70 r7, android.content.Context r8, boolean r9) {
        /*
            Method dump skipped, instruction units count: 225
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.n3.k1(m70, android.content.Context, boolean):boolean");
    }

    public static final void l(Throwable th, Throwable th2) throws IllegalAccessException, InvocationTargetException {
        i21.e(th, "<this>");
        i21.e(th2, "exception");
        if (th != th2) {
            p12.a.getClass();
            Method method = o12.a.a;
            if (method != null) {
                method.invoke(th, th2);
            }
        }
    }

    public static ProfileKey l0(OTRProfileID oTRProfileID) {
        return oTRProfileID == null ? ProfileKey.a() : Profile.d().e(oTRProfileID).g();
    }

    public static void l1(ChromeTabbedActivity chromeTabbedActivity, boolean z2) {
        Object obj = ThreadUtils.a;
        Intent intent = new Intent(chromeTabbedActivity == null ? xz.a : chromeTabbedActivity, (Class<?>) BookmarkHistoryActivity.class);
        intent.putExtra("org.chromium.chrome.browser.incognito_mode", z2);
        intent.setData(Uri.parse("chrome-native://bookmarks/"));
        if (chromeTabbedActivity != null) {
            intent.putExtra("org.chromium.chrome.browser.parent_component", chromeTabbedActivity.getComponentName());
            chromeTabbedActivity.startActivity(intent);
        } else {
            intent.addFlags(268435456);
            b.A(null, intent, null);
        }
    }

    public static double m(double d, float f2, float f3) {
        if (a8.h(f2, 1.0f) && a8.h(f3, 1.0f)) {
            return d;
        }
        return Math.round((Math.log10((Math.pow(1.2000000476837158d, d) * ((double) f2)) * ((double) f3)) / Math.log10(1.2000000476837158d)) * 100.0d) / 100.0d;
    }

    public static s62 m0() {
        Object obj = ThreadUtils.a;
        if (E == null) {
            E = new s62();
        }
        return E;
    }

    public static void m1(Context context, int i2) {
        Bundle bundle = new Bundle();
        bundle.putString("category", yn2.m(i2));
        bundle.putString("title", context.getResources().getString(i2 == 26 ? org.chromium.components.browser_ui.site_settings.R$string.third_party_cookies_page_title : xy.c(yn2.b(i2), new kr(context, Profile.d())).b));
        String name = SingleCategorySettings.class.getName();
        Intent intent = new Intent();
        intent.setClass(context, SettingsActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
            intent.addFlags(67108864);
        }
        intent.putExtra("show_fragment", name);
        intent.putExtra("show_fragment_args", bundle);
        ku2 ku2VarT = ku2.t();
        try {
            context.startActivity(intent);
            ku2VarT.close();
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public static File n(File file, d10 d10Var) {
        fa1.g("LogcatCrashExtractor", "Trying to extract logcat for minidump %s.", file.getName());
        try {
            file = new uh1(d10Var, file, V()).b();
            fa1.g("LogcatCrashExtractor", "Succeeded extracting logcat to %s.", file.getName());
            return file;
        } catch (IOException | InterruptedException e2) {
            Log.w("cr_LogcatCrashExtractor", e2.toString());
            return file;
        }
    }

    public static nn3 n0(View view) {
        nn3 nn3Var = new nn3(view);
        nn3Var.d(true);
        nn3Var.e(new Rect(0, 0, 0, (view.getHeight() - view.getResources().getDimensionPixelSize(R$dimen.toolbar_icon_height)) / 2));
        return nn3Var;
    }

    public static boolean n1(WebContents webContents, hy hyVar, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, String str) {
        if (j == null) {
            return false;
        }
        if (!((webContents == null || webContents.isDestroyed() || webContents.r() != 2) ? false : true)) {
            return false;
        }
        wd0 wd0Var = j;
        WindowAndroid windowAndroidV = webContents.V();
        wd0Var.getClass();
        Object obj = b62.d;
        org.chromium.components.browser_ui.contacts_picker.d dVar = new org.chromium.components.browser_ui.contacts_picker.d(windowAndroidV, new ChromePickerAdapter(windowAndroidV.i.get()), hyVar, z2, z3, z4, z5, z6, z7, str);
        dVar.getWindow().getAttributes().windowAnimations = R$style.PickerDialogAnimation;
        dVar.show();
        return true;
    }

    public static boolean o(int i2) {
        int i3 = C;
        return B != null && (i2 == i3 || i3 == 1) && System.currentTimeMillis() - B.longValue() < 60000;
    }

    public static int o0(WebContents webContents) {
        if (webContents == null) {
            return 0;
        }
        return N.MYsxmQ1h(webContents);
    }

    public static int o1(int i2) {
        return (int) (((long) Integer.rotateLeft((int) (((long) i2) * (-862048943)), 15)) * 461845907);
    }

    public static ny p(String str, boolean z2) {
        return new ny(z2 ? "LEGACY_OFFLINE_PAGE" : "LEGACY_DOWNLOAD", str);
    }

    public static int p0() {
        Integer num = e;
        if (num != null) {
            return num.intValue();
        }
        sk skVar = bq.a;
        String strMMltG$kc = N.MMltG$kc("AdaptiveButtonInTopToolbarCustomizationV2", "default_segment");
        if (TextUtils.isEmpty(strMMltG$kc)) {
            strMMltG$kc = "new-tab";
        }
        strMMltG$kc.getClass();
        switch (strMMltG$kc) {
            case "share":
                e = 3;
                break;
            case "voice":
                e = 4;
                break;
            case "new-tab":
                e = 2;
                break;
            default:
                e = 0;
                break;
        }
        return e.intValue();
    }

    public static int p1(Object obj) {
        return o1(obj == null ? 0 : obj.hashCode());
    }

    public static String q0(String str) {
        String strF = iv.e().f(str);
        return TextUtils.isEmpty(strF) ? N.MOVY9QtZ("UpdateMenuItem", str) : strF;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00a9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final long q1(java.lang.String r23, long r24, long r26, long r28) {
        /*
            Method dump skipped, instruction units count: 307
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.n3.q1(java.lang.String, long, long, long):long");
    }

    public static boolean r(Tab tab, boolean z2) {
        return tab.c() != null && N.Mx5ZGJOG(tab.c(), z2);
    }

    public static int r0(Context context, boolean z2, boolean z3) {
        if (z2) {
            return context.getColor(z3 ? org.chromium.chrome.R$color.incognito_tab_grid_dialog_ungroup_bar_bg_hovered_color : org.chromium.chrome.R$color.incognito_tab_grid_dialog_background_color);
        }
        return oj2.n(z3 ? R$attr.colorPrimary : R$attr.colorSurface, context, "TabUiThemeProvider");
    }

    public static int r1(String str, int i2, int i3, int i4, int i5) {
        if ((i5 & 4) != 0) {
            i3 = 1;
        }
        if ((i5 & 8) != 0) {
            i4 = Integer.MAX_VALUE;
        }
        return (int) q1(str, i2, i3, i4);
    }

    public static void s(String str, boolean z2) {
        if (!z2) {
            throw new IllegalArgumentException(str);
        }
    }

    public static int s0(Context context, boolean z2, boolean z3) {
        if (z2) {
            return context.getColor(z3 ? org.chromium.chrome.R$color.incognito_tab_grid_dialog_ungroup_bar_text_hovered_color : org.chromium.chrome.R$color.incognito_tab_grid_dialog_ungroup_bar_text_color);
        }
        return oj2.n(z3 ? R$attr.colorOnPrimary : R$attr.colorPrimary, context, "TabUiThemeProvider");
    }

    public static final gi3 s1(b00 b00Var, m00 m00Var) {
        gi3 gi3Var;
        if (!(b00Var instanceof u00)) {
            return null;
        }
        if (!(m00Var.get(hi3.e) != null)) {
            return null;
        }
        u00 u00VarA = (u00) b00Var;
        while (!(u00VarA instanceof ua0) && (u00VarA = u00VarA.a()) != null) {
            if (u00VarA instanceof gi3) {
                gi3Var = (gi3) u00VarA;
                break;
            }
        }
        gi3Var = null;
        if (gi3Var == null) {
            return gi3Var;
        }
        throw null;
    }

    public static void t(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        }
    }

    public static int t0(Context context, boolean z2, boolean z3) {
        if (z2) {
            return context.getColor(z3 ? org.chromium.chrome.R$color.incognito_tab_tile_number_selected_color : org.chromium.chrome.R$color.incognito_tab_tile_number_color);
        }
        return z3 ? oj2.n(R$attr.colorOnPrimary, context, "TabUiThemeProvider") : oj2.n(R$attr.colorOnSurface, context, "TabUiThemeProvider");
    }

    public static void t1(int i2) {
        if (!(i2 >= 0 && i2 <= 2)) {
            throw new q80("Invalid enum value.");
        }
    }

    public static boolean u(Bundle bundle, String str, Class cls) {
        if (bundle.containsKey(str) && cls.isAssignableFrom(bundle.get(str).getClass())) {
            return true;
        }
        StringBuilder sbN = C0175b.n("Missing field ", str, " of type ");
        sbN.append(cls.getName());
        sbN.append(".");
        Log.w("cr_DigitalGoods", sbN.toString());
        return false;
    }

    public static int u0(int i2, HashMap map) {
        int iU0 = 1;
        if (map.containsKey(Integer.valueOf(i2))) {
            List list = (List) map.get(Integer.valueOf(i2));
            for (int i3 = 0; i3 < list.size(); i3++) {
                iU0 += u0(((Integer) list.get(i3)).intValue(), map);
            }
        }
        return iU0;
    }

    public static void u1(int i2) {
        if (!(i2 >= 0 && i2 <= 5)) {
            throw new q80("Invalid enum value.");
        }
    }

    public static void v(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static int v0(Context context, Tab tab, int i2) {
        int iX0 = x0(i2, context, tab != null && tab.isIncognito());
        jk1 jk1VarU = tab != null ? tab.u() : null;
        return jk1VarU != null ? jk1VarU.g(iX0) : iX0;
    }

    public static void v1(int i2) {
        if (!(i2 >= 0 && i2 <= 3)) {
            throw new q80("Invalid enum value.");
        }
    }

    public static int w(int i2, int i3, int i4) {
        return i2 < i3 ? i3 : i2 > i4 ? i4 : i2;
    }

    public static void x() {
        Iterator it = dz0.a().a.iterator();
        while (it.hasNext()) {
            ((cz0) it.next()).a();
        }
    }

    public static int x0(int i2, Context context, boolean z2) {
        if (z2) {
            return z7.o(i2, context.getColor(org.chromium.chrome.R$color.toolbar_text_box_background_incognito) & (-16777216), Color.alpha(r3) / 255.0f, false);
        }
        if (i2 == yp.a(context, false)) {
            float dimension = bq.d.a() ? context.getResources().getDimension(R$dimen.default_elevation_4) : context.getResources().getDimension(R$dimen.toolbar_text_box_elevation);
            ElevationOverlayProvider elevationOverlayProvider = new ElevationOverlayProvider(context);
            return elevationOverlayProvider.b(dimension, elevationOverlayProvider.d);
        }
        if (z7.i0(i2)) {
            return -1;
        }
        return z7.o(i2, -1, 0.2f, false);
    }

    public static ky1 y(Address address) {
        if (address == null) {
            return null;
        }
        ky1 ky1Var = new ky1();
        ky1Var.b = address.a;
        ky1Var.c = address.b;
        ky1Var.d = address.c;
        ky1Var.e = address.d;
        ky1Var.f = address.e;
        ky1Var.g = address.f;
        ky1Var.h = address.g;
        ky1Var.i = address.h;
        ky1Var.j = address.i;
        ky1Var.k = address.j;
        return ky1Var;
    }

    public static ColorStateList y0(Context context) {
        return bz.b(context, kp2.a());
    }

    public static int z(int i2, Bundle bundle) {
        boolean z2 = false;
        if (bundle.getInt("digitalgoods.version") != 0) {
            if (i2 >= 0 && i2 <= 6) {
                z2 = true;
            }
            if (z2) {
                return i2;
            }
            C0175b.s("Unexpected response code: ", i2, "cr_DigitalGoods");
            return 1;
        }
        if (i2 == 0) {
            return 0;
        }
        if (i2 == 4) {
            return 4;
        }
        if (i2 == 7) {
            return 2;
        }
        if (i2 == 8) {
            return 3;
        }
        C0175b.s("Unexpected response code: ", i2, "cr_DigitalGoods");
        return 1;
    }

    public static String z0(Collection collection) {
        return collection.size() == 1 ? ((OfflineItem) collection.iterator().next()).f : String.format(Locale.getDefault(), "%d", Integer.valueOf(collection.size()));
    }

    public void L(String str, Bundle bundle) {
        throw null;
    }

    public Bundle M(String str, Bundle bundle) {
        throw null;
    }

    @Override // defpackage.nj
    public boolean O() {
        return false;
    }

    @Override // defpackage.nj
    public void Q(y70 y70Var) {
        y70Var.run();
    }

    public void R0(int i2, int i3, Bundle bundle) {
        throw null;
    }

    public void T0(int i2, Bundle bundle) {
        throw null;
    }

    public void U0(int i2, Uri uri, boolean z2, Bundle bundle) {
        throw null;
    }

    @Override // org.chromium.components.browser_ui.settings.SettingsLauncher
    public Intent a(Context context, String str, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, SettingsActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
            intent.addFlags(67108864);
        }
        if (str != null) {
            intent.putExtra("show_fragment", str);
        }
        if (bundle != null) {
            intent.putExtra("show_fragment_args", bundle);
        }
        return intent;
    }

    @Override // org.chromium.components.browser_ui.settings.SettingsLauncher
    public void b(Context context, Class cls, Bundle bundle) {
        Intent intentA = a(context, cls != null ? cls.getName() : null, bundle);
        ComponentName componentName = q11.a;
        try {
            context.startActivity(intentA, null);
        } catch (ActivityNotFoundException unused) {
        }
    }

    @Override // defpackage.nj
    public boolean d() {
        return false;
    }

    @Override // org.chromium.components.browser_ui.settings.SettingsLauncher
    public Intent e(FragmentActivity fragmentActivity, String str) {
        return a(fragmentActivity, str, null);
    }

    @Override // org.chromium.components.browser_ui.settings.SettingsLauncher
    public void g(Context context, Class cls) {
        b(context, cls, null);
    }

    @Override // defpackage.tv2
    public void h(Context context, WebContents webContents) {
        webContents.Z(null);
    }

    @Override // org.chromium.components.browser_ui.settings.SettingsLauncher
    public void i(Context context) {
        b(context, null, null);
    }

    public /* synthetic */ n3(int i2) {
    }

    @Override // defpackage.nj
    public void G0() {
    }

    public void K() {
    }

    @Override // defpackage.nj
    public void d1() {
    }

    @Override // defpackage.nj
    public void e1() {
    }

    @Override // defpackage.nj
    public void g0() {
    }

    @Override // defpackage.nj
    public void w0() {
    }

    @Override // defpackage.nj
    public void q(Exception exc) {
    }

    @Override // defpackage.tv2
    public void k(Context context, boolean z2) {
    }
}
