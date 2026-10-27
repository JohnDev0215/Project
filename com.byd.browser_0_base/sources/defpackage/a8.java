package defpackage;

import J.N;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.directwriting.IDirectWritingService;
import androidx.cardview.widget.CardView;
import androidx.slidingpanelayout.widget.b;
import defpackage.m31;
import defpackage.q00;
import defpackage.u6;
import defpackage.vb2;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CancellationException;
import org.chromium.base.LocaleUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.browserservices.verification.ChromeOriginVerifier;
import org.chromium.chrome.browser.metrics.UmaSessionStats;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.SyncConsentActivity;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.components.browser_ui.settings.R$string;
import org.chromium.components.embedder_support.contextmenu.ContextMenuParams;
import org.chromium.components.offline_items_collection.a;
import org.chromium.components.webxr.ArCoreJavaUtils;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.ResourceBundle;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ArCoreJavaUtilsJni.java */
/* JADX INFO: loaded from: classes3.dex */
public class a8 implements gk1, sq, o01 {
    public static jc a;
    public static gc b;
    public static a8 j;
    public static du3 m;
    public static boolean n;
    public static Boolean o;
    public static a8 s;
    public static a8 t;
    public static a8 u;
    public static a v;
    public static final float[][] c = {new float[]{0.401288f, 0.650173f, -0.051461f}, new float[]{-0.250268f, 1.204414f, 0.045854f}, new float[]{-0.002079f, 0.048952f, 0.953127f}};
    public static final float[][] d = {new float[]{1.8620678f, -1.0112547f, 0.14918678f}, new float[]{0.38752654f, 0.62144744f, -0.00897398f}, new float[]{-0.0158415f, -0.03412294f, 1.0499644f}};
    public static final float[] e = {95.047f, 100.0f, 108.883f};
    public static final float[][] f = {new float[]{0.41233894f, 0.35762063f, 0.18051042f}, new float[]{0.2126f, 0.7152f, 0.0722f}, new float[]{0.01932141f, 0.11916382f, 0.9503448f}};
    public static final sh g = new sh("UseChimeAndroidSdk", "always_register", false);
    public static final byte[] h = {48, -126, 4, 104, 48, -126, 2, -48, -96, 3, 2, 1, 2, 2, 20, 120, 33, -22, -36, -115, 7, 116, 66, 116, 113, -122, -126, -124, 32, 44, 72, -43, 127, -13, -11, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 11, 5, 0, 48, 67, 49, 11, 48, 9, 6, 3, 85, 4, 6, 19, 2, 85, 83, 49, 15, 48, 13, 6, 3, 85, 4, 10, 19, 6, 71, 111, 111, 103, 108, 101, 49, 22, 48, 20, 6, 3, 85, 4, 11, 19, 13, 67, 104, 114, 111, 109, 101, 32, 87, 101, 98, 65, 80, 75, 49, 11, 48, 9, 6, 3, 85, 4, 3, 19, 2, 67, 65, 48, 30, 23, 13, 49, 54, 48, 56, 50, 51, 50, 48, 48, 56, 49, 49, 90, 23, 13, 52, 52, 48, 49, 49, 48, 50, 48, 48, 56, 49, 49, 90, 48, 67, 49, 11, 48, 9, 6, 3, 85, 4, 6, 19, 2, 85, 83, 49, 15, 48, 13, 6, 3, 85, 4, 10, 19, 6, 71, 111, 111, 103, 108, 101, 49, 22, 48, 20, 6, 3, 85, 4, 11, 19, 13, 67, 104, 114, 111, 109, 101, 32, 87, 101, 98, 65, 80, 75, 49, 11, 48, 9, 6, 3, 85, 4, 3, 19, 2, 67, 65, 48, -126, 1, -94, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, -126, 1, -113, 0, 48, -126, 1, -118, 2, -126, 1, -127, 0, -60, 57, -128, 54, 45, -71, -75, 1, 42, 100, 64, 111, -6, 104, -8, -57, -45, 42, 33, 124, 43, 20, -115, -88, -100, -115, -19, 90, 25, 16, 50, -96, 27, 4, 64, -65, 92, -74, 61, 98, 54, 88, 122, -71, -116, -86, -26, -30, -34, -16, -87, -113, 103, -81, -76, -2, -120, -124, -70, 41, 30, 46, 75, 119, 22, 61, 92, -8, 92, 55, 8, -53, -70, 100, 40, 77, 77, 10, -5, -74, 37, 48, -71, 108, 92, -72, -56, 17, 39, -11, 61, -86, -52, -3, -128, 72, 68, 27, 28, -128, -126, 91, 64, -117, -67, 58, 114, -70, 112, -44, -70, -51, -81, -95, -78, 12, -1, 123, 68, -96, -32, -76, 65, -59, 37, -31, 88, 34, -125, -67, 58, 77, 75, -112, 23, 127, -15, -45, 56, -10, 46, -40, -82, -49, 42, 35, 127, -70, 120, -37, 8, 24, 38, 59, -1, -39, -104, -85, 101, 43, 39, 107, -35, -7, -80, -38, -47, -86, -53, -94, 11, 62, 86, 78, 72, 90, -54, 5, 21, -72, -122, 14, 102, 95, -81, 94, -11, -101, 36, -31, 104, -66, -51, 24, -99, 74, 21, -106, 76, 67, 86, 49, 121, 59, -7, 79, -87, -115, 27, -82, -98, 15, -5, 13, -90, -20, 70, 17, -68, 124, 82, 35, 53, 76, -52, 69, 23, -97, -2, -117, 7, -27, 79, 46, -54, -99, 41, -22, -108, -25, 8, 108, 109, -23, 13, -11, 56, 11, -71, 20, 37, -64, -13, -114, -34, -78, -49, 7, 45, 80, 7, 69, 106, 3, -128, 70, 0, 41, -105, 76, 106, -114, 18, -22, 92, -113, -12, 109, 11, 111, 48, -63, 22, 29, -5, -113, -2, 117, -105, 1, -21, 40, 23, -8, -36, -109, -41, -1, 92, 94, 51, 122, -67, -116, -64, 28, 38, 112, 4, -25, -18, -92, 53, -119, -37, 58, -28, 62, -88, 126, -66, -113, -101, 57, -66, -48, -88, -47, -65, -65, 108, -116, -52, -87, -33, 30, 121, -6, -50, -97, 99, -102, 106, -31, 119, -26, -49, 63, 90, -19, 119, 103, -83, 125, 29, -32, -102, -97, -99, -45, 59, 36, 30, 58, 28, 59, 2, 48, 0, -76, 108, 98, 62, 68, -11, -82, -74, -38, 93, -22, -79, -110, 73, 13, 2, 3, 1, 0, 1, -93, 84, 48, 82, 48, 14, 6, 3, 85, 29, 15, 1, 1, -1, 4, 4, 3, 2, 2, -92, 48, 19, 6, 3, 85, 29, 37, 4, 12, 48, 10, 6, 8, 43, 6, 1, 5, 5, 7, 3, 1, 48, 12, 6, 3, 85, 29, 19, 1, 1, -1, 4, 2, 48, 0, 48, 29, 6, 3, 85, 29, 14, 4, 22, 4, 20, 62, -89, -113, -1, -62, -65, -19, 4, 56, -51, 41, -53, 51, 41, -28, -42, -36, 31, -89, -19, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 11, 5, 0, 3, -126, 1, -127, 0, 27, -3, -41, -125, 123, -93, 20, -83, -83, -13, -120, -13, -123, 29, 107, -9, 77, -75, -118, 106, 5, 113, -11, 40, -84, 98, -126, -55, 39, 77, -87, -53, -5, 122, -63, -108, 23, 112, -88, 17, 28, 15, -103, 75, 75, 123, -21, 119, 125, -78, -125, 87, 78, 41, -103, 6, 9, 91, 89, 42, 86, 25, -62, -30, 117, 22, -107, 53, 66, 56, -23, -115, 16, -85, -38, 77, -117, -87, 106, -4, -91, 36, 81, 40, -31, 123, -40, 69, -119, -69, -83, -78, 64, 123, 46, 36, 31, -29, 64, 36, -8, -22, -93, 22, -48, -49, 11, -67, 48, -1, 26, 94, -82, 65, 6, 82, 18, 91, -56, 118, -55, 111, -18, -114, -86, -98, -3, 40, -48, -46, -102, -120, 20, 101, -60, 91, -103, -92, 43, 12, -104, -10, -60, 10, -31, -26, 111, -125, 103, 102, -105, 116, -66, -89, -58, 11, 34, 1, 22, 112, 126, 81, 115, -104, -120, 115, -43, -59, 50, -67, -42, -42, 122, -66, 57, 121, 30, -22, -8, 96, 114, -110, -98, 102, 41, -64, 115, -44, -84, 10, 63, 19, -105, 118, -2, 98, 45, -49, 94, 114, 81, -84, -111, -34, -23, 49, 73, 91, 76, 81, 90, 86, 112, 12, -87, -41, -61, -82, 32, -87, -7, 95, -28, -25, -9, -38, -102, 54, -81, -109, -126, 59, -82, 103, 126, -92, -51, -81, 44, 61, 103, 127, -1, -23, -84, -35, -43, -88, 41, 120, 76, 120, 
    39, -124, 81, 90, 64, 69, 112, 18, 54, -115, -7, 12, -110, 105, 48, -44, 88, 35, -104, 107, -83, -4, -16, 123, 59, -13, 121, 63, -89, 118, 100, 38, 118, -30, 9, -57, -54, -108, -26, -45, 29, -22, 57, -81, 83, -124, -114, -50, 16, 78, 23, -41, -4, 119, -13, -68, 38, -23, 56, 22, -96, -63, -27, 70, -94, -35, 111, -45, -9, 59, -90, -27, 103, 95, 16, 127, -118, -98, -75, -52, 7, 32, 65, -27, -68, 62, -81, 98, -54, -80, -23, 59, 38, -127, 96, 71, 123, 34, -113, -23, -80, 32, -97, -55, -100, 121, 120, 50, -48, 58, 69, -105, 26, 126, 30, -1, -112, -41, -18, -16, 62, 48, -22, -2, 19, 117, -6, 59, 74, -13, 92, -1};
    public static final byte[] i = {48, 89, 48, 19, 6, 7, 42, -122, 72, -50, 61, 2, 1, 6, 8, 42, -122, 72, -50, 61, 3, 1, 7, 3, 66, 0, 4, -25, 45, 2, 49, 44, -60, 107, -108, -45, 27, -40, -8, -116, 44, 7, -38, -103, 52, -81, 33, -90, -80, -94, 125, -3, -67, 51, -125, -63, 6, -127, 89, 32, 53, 83, -120, -106, -113, -121, -39, 115, -50, 15, 117, 66, 78, -89, -124, -120, 4, -61, 8, -90, -67, -6, 71, -120, -120, 23, 23, 77, 75, 103, -28};
    public static final int[] k = new int[0];
    public static final Object[] l = new Object[0];
    public static final a41 p = new a41(2, "UNDEFINED");
    public static final String[] q = {"ContentSuggestions.Feed.LoadMoreTrigger.TotalCards", "ContentSuggestions.Feed.WebFeed.LoadMoreTrigger.TotalCards", "ContentSuggestions.Feed.SingleWebFeed.LoadMoreTrigger.TotalCards"};
    public static final String[] r = {"ContentSuggestions.Feed.LoadMoreTrigger.OffsetFromEndOfStream", "ContentSuggestions.Feed.WebFeed.LoadMoreTrigger.OffsetFromEndOfStream", "ContentSuggestions.Feed.SingleWebFeed.LoadMoreTrigger.OffsetFromEndOfStream"};

    public /* synthetic */ a8() {
    }

    public static du3 A() {
        if (n) {
            return m;
        }
        try {
            int i2 = eu3.h;
            m = (du3) eu3.class.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        } catch (Throwable th) {
            n = true;
            throw th;
        }
        n = true;
        return m;
    }

    @SuppressLint({"BanUncheckedReflection"})
    public static Set B() {
        try {
            Object objInvoke = Class.forName("android.text.EmojiConsistency").getMethod("getEmojiConsistencySet", new Class[0]).invoke(null, new Object[0]);
            if (objInvoke == null) {
                return Collections.emptySet();
            }
            Set set = (Set) objInvoke;
            Iterator it = set.iterator();
            while (it.hasNext()) {
                if (!(it.next() instanceof int[])) {
                    return Collections.emptySet();
                }
            }
            return set;
        } catch (Throwable unused) {
            return Collections.emptySet();
        }
    }

    public static a8 C() {
        if (t == null) {
            AppHooks.get().getClass();
            t = new a8();
        }
        return t;
    }

    public static GURL D(GURL gurl) {
        return gurl.k() ? gurl : (GURL) N.M1WDPiaY(gurl.j());
    }

    public static hc E() {
        Object obj = ThreadUtils.a;
        if (a == null) {
            a = new jc(new kc());
        }
        return a;
    }

    public static String F(ContextMenuParams contextMenuParams) {
        if (!TextUtils.isEmpty(contextMenuParams.e)) {
            return contextMenuParams.e;
        }
        String str = contextMenuParams.d;
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        boolean z = contextMenuParams.j;
        GURL gurl = contextMenuParams.g;
        return (z || contextMenuParams.k || gurl.i().equals("file")) ? URLUtil.guessFileName(gurl.j(), null, null) : "";
    }

    public static Rect G(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i2 = iArr[0];
        int i3 = iArr[1];
        return new Rect(i2, i3, view.getWidth() + i2, view.getHeight() + i3);
    }

    public static final void H(m00 m00Var, Throwable th) throws IllegalAccessException, InvocationTargetException {
        try {
            q00 q00Var = (q00) m00Var.get(q00.a.e);
            if (q00Var == null) {
                r00.a(m00Var, th);
            } else {
                q00Var.handleException(m00Var, th);
            }
        } catch (Throwable th2) {
            if (th != th2) {
                RuntimeException runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                n3.l(runtimeException, th);
                th = runtimeException;
            }
            r00.a(m00Var, th);
        }
    }

    public static int I(int i2) {
        int iRotateLeft = ((Integer.rotateLeft(i2 * (-862048943), 15) * 461845907) ^ (-228025352)) ^ 2;
        int i3 = (iRotateLeft ^ (iRotateLeft >>> 16)) * (-2048144789);
        int i4 = (i3 ^ (i3 >>> 13)) * (-1028477387);
        return i4 ^ (i4 >>> 16);
    }

    public static final int J(q8 q8Var, Object obj, int i2) {
        i21.e(q8Var, "<this>");
        int i3 = q8Var.g;
        if (i3 == 0) {
            return -1;
        }
        try {
            int iJ = j(q8Var.g, i2, q8Var.e);
            if (iJ < 0 || i21.a(obj, q8Var.f[iJ])) {
                return iJ;
            }
            int i4 = iJ + 1;
            while (i4 < i3 && q8Var.e[i4] == i2) {
                if (i21.a(obj, q8Var.f[i4])) {
                    return i4;
                }
                i4++;
            }
            for (int i5 = iJ - 1; i5 >= 0 && q8Var.e[i5] == i2; i5--) {
                if (i21.a(obj, q8Var.f[i5])) {
                    return i5;
                }
            }
            return ~i4;
        } catch (IndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public static int K(float f2) {
        if (f2 < 1.0f) {
            return -16777216;
        }
        if (f2 > 99.0f) {
            return -1;
        }
        float f3 = (f2 + 16.0f) / 116.0f;
        float f4 = (f2 > 8.0f ? 1 : (f2 == 8.0f ? 0 : -1)) > 0 ? f3 * f3 * f3 : f2 / 903.2963f;
        float f5 = f3 * f3 * f3;
        boolean z = f5 > 0.008856452f;
        float f6 = z ? f5 : ((f3 * 116.0f) - 16.0f) / 903.2963f;
        if (!z) {
            f5 = ((f3 * 116.0f) - 16.0f) / 903.2963f;
        }
        float[] fArr = e;
        return gv.b(f6 * fArr[0], f4 * fArr[1], f5 * fArr[2]);
    }

    public static boolean L(String str) {
        if (!TextUtils.isEmpty(str) && str.startsWith("chrome-distiller:")) {
            return N.Me1sexxj(str);
        }
        return false;
    }

    public static boolean M(GURL gurl) {
        if (gurl.i().equals("chrome-distiller")) {
            return L(gurl.j());
        }
        return false;
    }

    public static boolean N(String str) {
        return N.M1X7xdZV("WebPaymentsExperimentalFeatures") || N.M1X7xdZV(str);
    }

    public static boolean O(MotionEvent motionEvent, int i2) {
        return (motionEvent.getSource() & i2) == i2;
    }

    public static boolean P() {
        return Q();
    }

    public static boolean Q() {
        if (org.chromium.base.library_loader.a.l.d()) {
            return N.MFTJCvBh();
        }
        return false;
    }

    public static void R(Context context, int i2) {
        z7 z7VarM = z7.m();
        Profile profileD = Profile.d();
        z7VarM.getClass();
        SigninManager signinManagerD = z7.D(profileD);
        signinManagerD.j();
        if (signinManagerD.e()) {
            nb1.h(context, R$string.managed_by_your_organization);
        }
    }

    public static void S(Context context, Bundle bundle) {
        int i2 = SyncConsentActivity.I;
        Intent intent = new Intent(context, (Class<?>) SyncConsentActivity.class);
        intent.putExtra("SigninActivity.FragmentArgs", bundle);
        context.startActivity(intent);
    }

    public static float T(int i2) {
        float f2 = i2 / 255.0f;
        return (f2 <= 0.04045f ? f2 / 12.92f : (float) Math.pow((f2 + 0.055f) / 1.055f, 2.4000000953674316d)) * 100.0f;
    }

    public static long[] U(Long[] lArr) {
        long[] jArr = new long[lArr.length];
        for (int i2 = 0; i2 < lArr.length; i2++) {
            jArr[i2] = lArr[i2].longValue();
        }
        return jArr;
    }

    public static void a0(AnimatorSet animatorSet, ArrayList arrayList) {
        int size = arrayList.size();
        long jMax = 0;
        for (int i2 = 0; i2 < size; i2++) {
            Animator animator = (Animator) arrayList.get(i2);
            jMax = Math.max(jMax, animator.getDuration() + animator.getStartDelay());
        }
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(0, 0);
        valueAnimatorOfInt.setDuration(jMax);
        arrayList.add(0, valueAnimatorOfInt);
        animatorSet.playTogether(arrayList);
    }

    public static void b0(int i2) {
        oj2.U(i2, 7, "ContentSuggestions.Feed.Controls.Actions");
    }

    public static void c0() {
        String strC = LocaleUtils.c(Locale.getDefault().toLanguageTag());
        String strC2 = LocaleUtils.c(LocaleList.getDefault().get(0).toLanguageTag());
        u6.a aVar = u6.a;
        boolean z = TextUtils.equals(strC, null) || Arrays.binarySearch(ResourceBundle.a, strC, aVar) >= 0;
        boolean z2 = TextUtils.equals(strC2, null) || Arrays.binarySearch(ResourceBundle.a, strC2, aVar) >= 0;
        String strC3 = LocaleUtils.c(xz.a.getResources().getString(org.chromium.chrome.R$string.current_detected_ui_locale_name));
        String strC4 = LocaleUtils.c(N.MqGtfAYA());
        boolean zEquals = TextUtils.equals(strC, strC3);
        boolean zEquals2 = TextUtils.equals(strC, strC4);
        boolean z3 = xs0.d.c;
        oj2.U(z3 ? 3 : z2 ? 0 : (!z || z2) ? 2 : 1, 4, "LanguageUsage.UI.Android.Availability");
        boolean z4 = (z2 || z) ? false : true;
        oj2.U(z4 ? 2 : (zEquals && zEquals2) ? 0 : (!zEquals || zEquals2) ? 1 : 3, 4, "LanguageUsage.UI.Android.Correctness");
        if (z3) {
            oj2.U((zEquals && zEquals2) ? 0 : (!zEquals || zEquals2) ? 1 : 3, 4, "LanguageUsage.UI.Android.Correctness.Override");
            return;
        }
        if (z4) {
            i = 2;
        } else if (!zEquals) {
            i = 1;
        }
        oj2.U(i, 4, "LanguageUsage.UI.Android.Correctness.NoOverride");
    }

    public static void d0() {
        throw new UnsupportedOperationException("Local parent auth not supported");
    }

    public static void e(boolean z) {
        v42 v42VarG = v42.g();
        v42VarG.b.l("Chrome.Privacy.UsageAndCrashReportingPermittedByUser", false);
        N.MmqfIJ4g(v42VarG.a());
        N.Mh1r7OJ$(z, 2);
        UmaSessionStats.b();
        SharedPreferencesManager.getInstance().l("first_run_tos_accepted", true);
        N.MSb7o$8Q();
    }

    public static final void e0(b00 b00Var, Object obj, jr0 jr0Var) {
        if (!(b00Var instanceof ta0)) {
            b00Var.c(obj);
            return;
        }
        ta0 ta0Var = (ta0) b00Var;
        Throwable thA = vb2.a(obj);
        Object svVar = thA == null ? jr0Var != null ? new sv(obj, jr0Var) : obj : new rv(thA);
        p00 p00Var = ta0Var.h;
        b00<T> b00Var2 = ta0Var.i;
        ta0Var.getContext();
        boolean z = true;
        if (p00Var.i1()) {
            ta0Var.j = svVar;
            ta0Var.g = 1;
            ta0Var.h.h1(ta0Var.getContext(), ta0Var);
            return;
        }
        ThreadLocal<ai0> threadLocal = w83.a;
        ai0 hfVar = threadLocal.get();
        if (hfVar == null) {
            hfVar = new hf(Thread.currentThread());
            threadLocal.set(hfVar);
        }
        long j2 = hfVar.f;
        if (j2 >= 4294967296L) {
            ta0Var.j = svVar;
            ta0Var.g = 1;
            hfVar.k1(ta0Var);
            return;
        }
        hfVar.f = 4294967296L + j2;
        try {
            m31 m31Var = (m31) ta0Var.getContext().get(m31.a.e);
            if (m31Var == null || m31Var.isActive()) {
                z = false;
            } else {
                CancellationException cancellationExceptionR = m31Var.R();
                ta0Var.b(svVar, cancellationExceptionR);
                ta0Var.c(v(cancellationExceptionR));
            }
            if (!z) {
                Object obj2 = ta0Var.k;
                m00 context = b00Var2.getContext();
                Object objC = v83.c(context, obj2);
                gi3 gi3VarS1 = objC != v83.a ? n3.s1(b00Var2, context) : null;
                try {
                    b00Var2.c(obj);
                    vi3 vi3Var = vi3.a;
                    if (gi3VarS1 != null) {
                        throw null;
                    }
                    v83.a(context, objC);
                } catch (Throwable th) {
                    if (gi3VarS1 != null) {
                        throw null;
                    }
                    v83.a(context, objC);
                    throw th;
                }
            }
            while (hfVar.l1()) {
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    public static final void f(q8 q8Var, int i2) {
        i21.e(q8Var, "<this>");
        q8Var.e = new int[i2];
        q8Var.f = new Object[i2];
    }

    public static boolean g(mj mjVar) {
        return mjVar.p() == 0.0f;
    }

    public static void g0(ox oxVar, View view, float[] fArr) {
        Class<?> cls = view.getClass();
        String str = "set" + oxVar.b;
        try {
            boolean z = true;
            switch (gq2.m(oxVar.c)) {
                case 0:
                    cls.getMethod(str, Integer.TYPE).invoke(view, Integer.valueOf((int) fArr[0]));
                    return;
                case 1:
                    cls.getMethod(str, Float.TYPE).invoke(view, Float.valueOf(fArr[0]));
                    return;
                case 2:
                    cls.getMethod(str, Integer.TYPE).invoke(view, Integer.valueOf((p((int) (((float) Math.pow(fArr[0], 0.45454545454545453d)) * 255.0f)) << 16) | (p((int) (fArr[3] * 255.0f)) << 24) | (p((int) (((float) Math.pow(fArr[1], 0.45454545454545453d)) * 255.0f)) << 8) | p((int) (((float) Math.pow(fArr[2], 0.45454545454545453d)) * 255.0f))));
                    return;
                case 3:
                    Method method = cls.getMethod(str, Drawable.class);
                    int iP = (p((int) (((float) Math.pow(fArr[0], 0.45454545454545453d)) * 255.0f)) << 16) | (p((int) (fArr[3] * 255.0f)) << 24) | (p((int) (((float) Math.pow(fArr[1], 0.45454545454545453d)) * 255.0f)) << 8) | p((int) (((float) Math.pow(fArr[2], 0.45454545454545453d)) * 255.0f));
                    ColorDrawable colorDrawable = new ColorDrawable();
                    colorDrawable.setColor(iP);
                    method.invoke(view, colorDrawable);
                    return;
                case 4:
                    throw new RuntimeException("unable to interpolate strings " + oxVar.b);
                case 5:
                    Method method2 = cls.getMethod(str, Boolean.TYPE);
                    Object[] objArr = new Object[1];
                    if (fArr[0] <= 0.5f) {
                        z = false;
                    }
                    objArr[0] = Boolean.valueOf(z);
                    method2.invoke(view, objArr);
                    return;
                case 6:
                    cls.getMethod(str, Float.TYPE).invoke(view, Float.valueOf(fArr[0]));
                    return;
                default:
                    return;
            }
        } catch (IllegalAccessException e2) {
            StringBuilder sbN = C0175b.n("Cannot access method ", str, " on View \"");
            sbN.append(b70.d(view));
            sbN.append("\"");
            Log.e("CustomSupport", sbN.toString(), e2);
        } catch (NoSuchMethodException e3) {
            StringBuilder sbN2 = C0175b.n("No method ", str, " on View \"");
            sbN2.append(b70.d(view));
            sbN2.append("\"");
            Log.e("CustomSupport", sbN2.toString(), e3);
        } catch (InvocationTargetException e4) {
            StringBuilder sbN3 = C0175b.n("Cannot invoke method ", str, " on View \"");
            sbN3.append(b70.d(view));
            sbN3.append("\"");
            Log.e("CustomSupport", sbN3.toString(), e4);
        }
    }

    public static boolean h(float f2, float f3) {
        return Math.abs(f2 - f3) < 0.001f;
    }

    public static boolean i(int i2, int i3, long[] jArr, long[] jArr2) {
        if (i2 >= 0 && i3 >= 0 && i2 < jArr.length && i3 < jArr.length) {
            if ((jArr[i3] - jArr2[i3]) - jArr[i2] < 50000000) {
                return true;
            }
        }
        return false;
    }

    public static final void i0(Object obj) throws Throwable {
        if (obj instanceof vb2.a) {
            throw ((vb2.a) obj).e;
        }
    }

    public static final int j(int i2, int i3, int[] iArr) {
        i21.e(iArr, "array");
        int i4 = i2 - 1;
        int i5 = 0;
        while (i5 <= i4) {
            int i6 = (i5 + i4) >>> 1;
            int i7 = iArr[i6];
            if (i7 < i3) {
                i5 = i6 + 1;
            } else {
                if (i7 <= i3) {
                    return i6;
                }
                i4 = i6 - 1;
            }
        }
        return ~i5;
    }

    public static final int k(long[] jArr, int i2, long j2) {
        i21.e(jArr, "array");
        int i3 = i2 - 1;
        int i4 = 0;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            long j3 = jArr[i5];
            if (j3 < j2) {
                i4 = i5 + 1;
            } else {
                if (j3 <= j2) {
                    return i5;
                }
                i3 = i5 - 1;
            }
        }
        return ~i4;
    }

    public static boolean k0(Context context) {
        if (context == null) {
            return false;
        }
        sk skVar = bq.a;
        return N.M09VlOh_("ContextMenuPopupForAllScreenSizes") || DeviceFormFactor.a(context);
    }

    public static Bundle l(Rect rect, MotionEvent motionEvent, View view) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(IDirectWritingService.KEY_BUNDLE_EVENT, motionEvent);
        bundle.putParcelable(IDirectWritingService.KEY_BUNDLE_EDIT_RECT, rect);
        bundle.putParcelable(IDirectWritingService.KEY_BUNDLE_ROOT_VIEW_RECT, G(view));
        bundle.putString(IDirectWritingService.KEY_BUNDLE_SERVICE_HOST_SOURCE, IDirectWritingService.VALUE_BUNDLE_SERVICE_HOST_SOURCE_WEBVIEW);
        return bundle;
    }

    public static void l0(int i2) {
        if (!(i2 >= 0 && i2 <= 13)) {
            throw new q80("Invalid enum value.");
        }
    }

    public static Bundle m(View view, Rect rect) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(IDirectWritingService.KEY_BUNDLE_EDIT_RECT, rect);
        bundle.putParcelable(IDirectWritingService.KEY_BUNDLE_ROOT_VIEW_RECT, G(view));
        bundle.putString(IDirectWritingService.KEY_BUNDLE_SERVICE_HOST_SOURCE, IDirectWritingService.VALUE_BUNDLE_SERVICE_HOST_SOURCE_WEBVIEW);
        return bundle;
    }

    public static void m0(int i2) {
        boolean z;
        switch (i2) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 27:
            case 28:
                z = true;
                break;
            case 7:
            case 26:
            default:
                z = false;
                break;
        }
        if (!z) {
            throw new q80("Invalid enum value.");
        }
    }

    public static void n(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        }
    }

    public static float n0() {
        return ((float) Math.pow((((double) 50.0f) + 16.0d) / 116.0d, 3.0d)) * 100.0f;
    }

    public static float o(float f2, float f3, float f4) {
        float f5 = f3 > f4 ? f4 : f3;
        if (f3 <= f4) {
            f3 = f4;
        }
        return f2 < f5 ? f5 : f2 > f3 ? f3 : f2;
    }

    public static int p(int i2) {
        int i3 = (i2 & (~(i2 >> 31))) - 255;
        return (i3 & (i3 >> 31)) + 255;
    }

    public static int q(int i2, int i3, int i4) {
        int i5 = i3 > i4 ? i4 : i3;
        if (i3 <= i4) {
            i3 = i4;
        }
        return i2 < i5 ? i5 : i2 > i3 ? i3 : i2;
    }

    public static long r(long j2, long j3, long j4) {
        long j5 = j3 > j4 ? j4 : j3;
        if (j3 <= j4) {
            j3 = j4;
        }
        return j2 < j5 ? j5 : j2 > j3 ? j3 : j2;
    }

    public static void s(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }

    public static int t(int i2) {
        Boolean bool = o;
        if (bool == null && bool == null) {
            if ("SAMSUNG".equalsIgnoreCase(Build.MANUFACTURER)) {
                FeatureInfo[] systemAvailableFeatures = xz.a.getPackageManager().getSystemAvailableFeatures();
                int length = systemAvailableFeatures.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        o = Boolean.FALSE;
                        break;
                    }
                    if ("com.sec.feature.spen_usp".equalsIgnoreCase(systemAvailableFeatures[i3].name)) {
                        o = Boolean.TRUE;
                        break;
                    }
                    i3++;
                }
            } else {
                o = Boolean.FALSE;
            }
        }
        if (!o.booleanValue()) {
            return i2;
        }
        switch (i2) {
        }
        return i2;
    }

    public static long u(l50 l50Var) {
        if (l50Var == null) {
            return N.MKzrMxwv();
        }
        Rect rect = (Rect) l50Var.a;
        Rect rect2 = (Rect) l50Var.d;
        return N.MNZCYawX(rect.left, rect.top, rect.right, rect.bottom, rect2.left, rect2.top, rect2.right, rect2.bottom);
    }

    public static final vb2.a v(Throwable th) {
        i21.e(th, "exception");
        return new vb2.a(th);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Flow1 w(b.C0029b c0029b) {
        if (c0029b instanceof ak1) {
            return c0029b;
        }
        Object obj = zn0.f;
        yn0 yn0Var = yn0.f;
        if (c0029b instanceof jb0) {
            jb0 jb0Var = (jb0) c0029b;
            if (jb0Var.b == obj && jb0Var.c == yn0Var) {
                return c0029b;
            }
        }
        return new jb0(c0029b);
    }

    public static final boolean x(char c2, char c3, boolean z) {
        if (c2 == c3) {
            return true;
        }
        if (!z) {
            return false;
        }
        char upperCase = Character.toUpperCase(c2);
        char upperCase2 = Character.toUpperCase(c3);
        return upperCase == upperCase2 || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2);
    }

    public static a8 y() {
        if (u == null) {
            u = new a8();
        }
        return u;
    }

    public static a z() {
        if (v == null) {
            v = (a) N.Ml3x2KBq();
        }
        return v;
    }

    public void V(long j2, ArCoreJavaUtils arCoreJavaUtils) {
        xr0.org_chromium_components_webxr_ArCoreJavaUtils_onDrawingSurfaceDestroyed(j2, arCoreJavaUtils);
    }

    public void W(long j2, ArCoreJavaUtils arCoreJavaUtils, Surface surface, WindowAndroid windowAndroid, int i2, int i3, int i4) {
        xr0.org_chromium_components_webxr_ArCoreJavaUtils_onDrawingSurfaceReady(j2, arCoreJavaUtils, surface, windowAndroid, i2, i3, i4);
    }

    public void X(long j2, ArCoreJavaUtils arCoreJavaUtils, boolean z, boolean z2, int i2, float f2, float f3) {
        xr0.org_chromium_components_webxr_ArCoreJavaUtils_onDrawingSurfaceTouch(j2, arCoreJavaUtils, z, z2, i2, f2, f3);
    }

    public void Y(int i2) {
        throw null;
    }

    public void Z(Typeface typeface) {
        throw null;
    }

    @Override // defpackage.sq
    public ChromeOriginVerifier a(String str, int i2, WebContents webContents, zi0 zi0Var) {
        ys ysVar = ys.b;
        return new ChromeOriginVerifier(str, i2, webContents, zi0Var);
    }

    @Override // defpackage.o01
    public void b(String str, p01 p01Var) {
        p01Var.b();
    }

    public void h0(qm qmVar, float f2) {
        CardView.a aVar = (CardView.a) qmVar;
        oc2 oc2Var = (oc2) aVar.a;
        boolean useCompatPadding = CardView.this.getUseCompatPadding();
        boolean preventCornerOverlap = CardView.this.getPreventCornerOverlap();
        if (f2 != oc2Var.e || oc2Var.f != useCompatPadding || oc2Var.g != preventCornerOverlap) {
            oc2Var.e = f2;
            oc2Var.f = useCompatPadding;
            oc2Var.g = preventCornerOverlap;
            oc2Var.c(null);
            oc2Var.invalidateSelf();
        }
        j0(aVar);
    }

    public void j0(qm qmVar) {
        float f2;
        CardView.a aVar = (CardView.a) qmVar;
        if (!CardView.this.getUseCompatPadding()) {
            aVar.a(0, 0, 0, 0);
            return;
        }
        Drawable drawable = aVar.a;
        float f3 = ((oc2) drawable).e;
        float f4 = ((oc2) drawable).a;
        if (CardView.this.getPreventCornerOverlap()) {
            f2 = (float) (((1.0d - pc2.a) * ((double) f4)) + ((double) f3));
        } else {
            int i2 = pc2.b;
            f2 = f3;
        }
        int iCeil = (int) Math.ceil(f2);
        int iCeil2 = (int) Math.ceil(pc2.a(f3, f4, r1.getPreventCornerOverlap()));
        aVar.a(iCeil, iCeil2, iCeil, iCeil2);
    }

    public /* synthetic */ a8(int i2) {
    }
}
