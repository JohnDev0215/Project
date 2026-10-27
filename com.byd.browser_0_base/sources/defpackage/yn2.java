package defpackage;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import androidx.preference.Preference;
import defpackage.iq2;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.site_settings.R$drawable;
import org.chromium.components.browser_ui.site_settings.R$string;
import org.chromium.content_public.browser.BrowserContextHandle;

/* JADX INFO: compiled from: SiteSettingsCategory.java */
/* JADX INFO: loaded from: classes2.dex */
public class yn2 {
    public final BrowserContextHandle a;
    public final int b;
    public final String c;

    public yn2(int i, String str, Profile profile) {
        this.a = profile;
        this.b = i;
        this.c = str;
    }

    public static int b(int i) {
        switch (i) {
            case 0:
            case 22:
            default:
                return -1;
            case 1:
                return 25;
            case 2:
                return 56;
            case 3:
                return 12;
            case 4:
                return 21;
            case 5:
                return 43;
            case 6:
                return 9;
            case 7:
                return 52;
            case 8:
            case 26:
            case 27:
                return 0;
            case 9:
                return 4;
            case 10:
                return 38;
            case 11:
                return 2;
            case 12:
                return 8;
            case 13:
                return 50;
            case 14:
                return 5;
            case 15:
                return 3;
            case 16:
                return 15;
            case 17:
                return 32;
            case 18:
                return 30;
            case 19:
                return 35;
            case 20:
                return 20;
            case 21:
                return 55;
            case 23:
                return 72;
            case 24:
                return 73;
            case 25:
                return 74;
            case 28:
                return 85;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static defpackage.yn2 c(org.chromium.chrome.browser.profiles.Profile r2, int r3) {
        /*
            r0 = 9
            if (r3 != r0) goto La
            x91 r3 = new x91
            r3.<init>(r2)
            return r3
        La:
            r0 = 13
            if (r3 != r0) goto L14
            cn1 r3 = new cn1
            r3.<init>(r2)
            return r3
        L14:
            r0 = 14
            if (r3 != r0) goto L1e
            ho1 r3 = new ho1
            r3.<init>(r2)
            return r3
        L1e:
            r0 = 6
            if (r3 != r0) goto L22
            goto L2c
        L22:
            r0 = 12
            if (r3 != r0) goto L29
            java.lang.String r0 = "android.permission.RECORD_AUDIO"
            goto L31
        L29:
            r0 = 2
            if (r3 != r0) goto L2f
        L2c:
            java.lang.String r0 = "android.permission.CAMERA"
            goto L31
        L2f:
            java.lang.String r0 = ""
        L31:
            yn2 r1 = new yn2
            r1.<init>(r3, r0, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yn2.c(org.chromium.chrome.browser.profiles.Profile, int):yn2");
    }

    public static Drawable g(Context context) {
        Drawable drawableC = c6.c(context.getResources(), R$drawable.ic_warning_triangle, 0);
        drawableC.mutate();
        return drawableC;
    }

    public static String m(int i) {
        switch (i) {
            case 0:
                return "all_sites";
            case 1:
                return "ads";
            case 2:
                return "augmented_reality";
            case 3:
                return "automatic_downloads";
            case 4:
                return "background_sync";
            case 5:
                return "bluetooth_scanning";
            case 6:
                return "camera";
            case 7:
                return "clipboard";
            case 8:
                return "cookies";
            case 9:
                return "device_location";
            case 10:
                return "idle_detection";
            case 11:
                return "javascript";
            case 12:
                return "microphone";
            case 13:
                return "nfc";
            case 14:
                return "notifications";
            case 15:
                return "popups";
            case 16:
                return "protected_content";
            case 17:
                return "sensors";
            case 18:
                return "sound";
            case 19:
                return "usb";
            case 20:
                return "bluetooth";
            case 21:
                return "virtual_reality";
            case 22:
                return "use_storage";
            case 23:
                return "auto_dark_web_content";
            case 24:
                return "request_desktop_site";
            case 25:
                return "federated_identity_api";
            case 26:
                return "third_party_cookies";
            case 27:
                return "site_data";
            case 28:
                return "anti_abuse";
            default:
                return "";
        }
    }

    public final void a(Preference preference, Preference preference2, Context context, boolean z, String str) {
        Intent intent;
        String string;
        if (d(context)) {
            intent = null;
        } else {
            intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(new Uri.Builder().scheme("package").opaquePart(context.getPackageName()).build());
        }
        Intent intentH = h();
        boolean z2 = !z;
        int iF = f();
        if (iF == 4) {
            string = "";
        } else {
            int i = R$string.android_permission_off;
            if (iF == 8) {
                i = R$string.android_microphone_permission_off;
            } else if (iF == 9) {
                i = R$string.android_camera_permission_off;
            } else if (iF == 56) {
                i = R$string.android_ar_camera_permission_off;
            } else if (iF == 5) {
                i = R$string.android_notifications_permission_off;
            }
            Resources resources = context.getResources();
            if (z2) {
                i = R$string.android_permission_off_plural;
            }
            string = resources.getString(i, str);
        }
        String strI = i(context);
        String strJ = j(context);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(ii2.b(context));
        if (intent != null && !TextUtils.isEmpty(string)) {
            preference.T(iq2.a(string, new iq2.a(foregroundColorSpan, "<link>", "</link>")));
            preference.q = intent;
            if (!z) {
                preference.L(g(context));
            }
        }
        if (!o()) {
            preference2.T(strJ);
            preference2.L(g(context));
        } else if (intentH != null) {
            preference2.T(iq2.a(strI, new iq2.a(foregroundColorSpan, "<link>", "</link>")));
            preference2.q = intentH;
            if (z) {
                return;
            }
            if (intent == null) {
                preference2.L(g(context));
            } else {
                preference2.L(new ColorDrawable(0));
            }
        }
    }

    public final boolean d(Context context) {
        String str = this.c;
        return str.isEmpty() || c6.a(Process.myPid(), Process.myUid(), context, str) == 0;
    }

    public boolean e() {
        return true;
    }

    public final int f() {
        return b(this.b);
    }

    public Intent h() {
        return null;
    }

    public String i(Context context) {
        return null;
    }

    public String j(Context context) {
        return null;
    }

    public final boolean k() {
        BrowserContextHandle browserContextHandle = this.a;
        int i = this.b;
        if (i == 3 || i == 4 || i == 11 || i == 15) {
            return N.MnAiqOhu(browserContextHandle, f());
        }
        if (i == 8 || i == 9 || i == 6 || i == 12) {
            return !N.MB23OvTV(browserContextHandle, f());
        }
        if (i == 26) {
            return oj2.k(browserContextHandle).d("profile.cookie_controls_mode");
        }
        return false;
    }

    public final boolean l() {
        int i = this.b;
        if (i != 8 && i != 9 && i != 6 && i != 12) {
            return false;
        }
        return N.M1hZJgqW(this.a, f());
    }

    public boolean n(Context context) {
        return (d(context) && e()) ? false : true;
    }

    public boolean o() {
        return true;
    }
}
