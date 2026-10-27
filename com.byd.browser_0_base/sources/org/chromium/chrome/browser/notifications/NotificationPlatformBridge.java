package org.chromium.chrome.browser.notifications;

import J.N;
import android.app.Activity;
import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.Log;
import defpackage.c01;
import defpackage.fp3;
import defpackage.fq3;
import defpackage.gq2;
import defpackage.kp1;
import defpackage.o8;
import defpackage.ph3;
import defpackage.pp;
import defpackage.rp3;
import defpackage.s50;
import defpackage.s62;
import defpackage.sf3;
import defpackage.xn2;
import defpackage.xz;
import defpackage.z70;
import defpackage.zs;
import java.net.URISyntaxException;
import java.util.Arrays;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.history_clusters.HistoryClustersResult;
import org.chromium.chrome.browser.notifications.ActionInfo;
import org.chromium.chrome.browser.notifications.NotificationPlatformBridge;
import org.chromium.chrome.browser.notifications.NotificationServiceImpl;
import org.chromium.chrome.browser.notifications.NotificationSystemStatusUtil;
import org.chromium.chrome.browser.notifications.StandardNotificationBuilder;
import org.chromium.chrome.browser.notifications.a;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationPlatformBridge {
    public static final int[] e = new int[0];
    public static NotificationPlatformBridge f;
    public final long a;
    public final NotificationManagerProxyImpl b = new NotificationManagerProxyImpl(xz.a);
    public long c;
    public sf3 d;

    public class a implements fp3.a {
        public final /* synthetic */ String e;
        public final /* synthetic */ String f;
        public final /* synthetic */ String g;

        public a(String str, String str2, String str3) {
            this.e = str;
            this.f = str2;
            this.g = str3;
        }

        @Override // fp3.a
        public final void c(String str, boolean z) {
            NotificationPlatformBridge.this.a(this.e, z ? this.f : null, this.g);
        }
    }

    public NotificationPlatformBridge(long j) {
        this.a = j;
    }

    public static String b(Intent intent) {
        CharSequence charSequence;
        if (intent.getStringExtra("notification_reply") != null) {
            return intent.getStringExtra("notification_reply");
        }
        Bundle resultsFromIntent = RemoteInput.getResultsFromIntent(intent);
        if (resultsFromIntent == null || (charSequence = resultsFromIntent.getCharSequence("key_text_reply")) == null) {
            return null;
        }
        return charSequence.toString();
    }

    public static String c(String str) {
        if (str != null && str.startsWith("p#")) {
            String[] strArrSplit = str.split("#");
            try {
                if (new ph3(strArrSplit[1]).e() != null) {
                    return strArrSplit[1];
                }
                return null;
            } catch (URISyntaxException e2) {
                Log.e("cr_NotificationPlatformBridge", "Expected to find a valid url in the notification tag extra.", e2);
            }
        }
        return null;
    }

    @CalledByNative
    public static NotificationPlatformBridge create(long j) {
        if (f != null) {
            throw new IllegalStateException("There must only be a single NotificationPlatformBridge.");
        }
        NotificationPlatformBridge notificationPlatformBridge = new NotificationPlatformBridge(j);
        f = notificationPlatformBridge;
        return notificationPlatformBridge;
    }

    public static Uri e(int i, String str, String str2) {
        return Uri.parse(str2).buildUpon().fragment(str + "," + i).build();
    }

    public static o8 f(Context context, String str, String str2, int i, String str3, String str4, String str5, boolean z, String str6, int i2, boolean z2) {
        Intent intent = new Intent(str, e(i2, str2, str3));
        intent.setClass(context, NotificationServiceImpl.Receiver.class);
        intent.putExtra("notification_id", str2);
        intent.putExtra("notification_type", i);
        intent.putExtra("notification_info_origin", str3);
        intent.putExtra("notification_info_scope", str4);
        intent.putExtra("notification_info_profile_id", str5);
        intent.putExtra("notification_info_profile_incognito", z);
        intent.putExtra("notification_info_webapk_package", str6);
        intent.putExtra("notification_info_action_index", i2);
        intent.addFlags(268435456);
        return o8.b(context, 0, intent, 134217728, z2);
    }

    public final void a(final String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str2)) {
            rp3 rp3VarA = rp3.a();
            rp3VarA.getClass();
            rp3.a aVar = new rp3.a() { // from class: mp3
                public final /* synthetic */ int b = -1;

                @Override // rp3.a
                public final void b(pw0 pw0Var) {
                    pw0Var.w(this.b, str);
                }
            };
            rp3VarA.a.a(xz.a, str2, aVar);
            return;
        }
        int i = -1;
        if (d().b(Uri.parse(str3))) {
            sf3 sf3VarD = d();
            Uri uri = Uri.parse(str3);
            sf3VarD.getClass();
            sf3VarD.a(uri, new c01(i, str));
        }
        this.b.d(-1, str);
    }

    @CalledByNative
    public final void closeNotification(String str, String str2, boolean z, String str3) {
        String strD;
        if (z || (strD = fq3.d(xz.a, str2)) == null) {
            a(str, str3, str2);
        } else {
            zs.a(strD, new a(str, strD, str2));
        }
    }

    public final sf3 d() {
        if (this.d == null) {
            this.d = ((s50) pp.d()).j();
        }
        return this.d;
    }

    @CalledByNative
    public final void destroy() {
        f = null;
    }

    @CalledByNative
    public final void displayNotification(final String str, final int i, final String str2, final String str3, final String str4, Profile profile, final String str5, final String str6, final Bitmap bitmap, final Bitmap bitmap2, final Bitmap bitmap3, final int[] iArr, final long j, final boolean z, final boolean z2, final ActionInfo[] actionInfoArr) {
        s62 s62VarC;
        final boolean zL = gq2.l("notifications.vibrate_enabled");
        final boolean zH = profile.h();
        String strD = fq3.d(xz.a, str3);
        if (strD == null) {
            s62VarC = s62.c("");
        } else {
            s62 s62Var = new s62();
            zs.a(strD, new z70(s62Var, 9, strD));
            s62VarC = s62Var;
        }
        s62VarC.g(new Callback() { // from class: ep1
            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                int i2;
                Bitmap bitmapCopy;
                Bitmap bitmapCopy2;
                ActionInfo[] actionInfoArr2;
                int i3;
                StandardNotificationBuilder standardNotificationBuilder;
                s62 s62VarC2;
                String str7 = str;
                int i4 = i;
                final String str8 = str2;
                String str9 = str3;
                String str10 = str4;
                boolean z3 = zH;
                final String str11 = (String) obj;
                NotificationPlatformBridge notificationPlatformBridge = this.e;
                N.MlTGi82B(notificationPlatformBridge.a, notificationPlatformBridge, str7, str11);
                oj2.U(NotificationSystemStatusUtil.getAppNotificationStatus(), 4, "Notifications.AppNotificationStatus");
                Context context = xz.a;
                o8 o8VarF = NotificationPlatformBridge.f(context, "org.chromium.chrome.browser.notifications.CLICK_NOTIFICATION", str7, i4, str8, str9, str10, z3, str11, -1, false);
                o8 o8VarF2 = NotificationPlatformBridge.f(context, "org.chromium.chrome.browser.notifications.CLOSE_NOTIFICATION", str7, i4, str8, str9, str10, z3, str11, -1, false);
                Bitmap bitmap4 = bitmap;
                boolean z4 = bitmap4 != null;
                boolean z5 = !str11.isEmpty();
                Context context2 = context;
                StandardNotificationBuilder standardNotificationBuilder2 = new StandardNotificationBuilder(context2);
                String str12 = str5;
                standardNotificationBuilder2.d = a.d(str12);
                String str13 = str6;
                standardNotificationBuilder2.e = a.d(str13);
                standardNotificationBuilder2.i = bitmap4;
                standardNotificationBuilder2.v = bitmap2;
                standardNotificationBuilder2.j = R$drawable.ic_chrome;
                Bitmap bitmap5 = bitmap3;
                if (bitmap5 != null) {
                    bitmapCopy = bitmap5.copy(bitmap5.getConfig(), true);
                    Paint paint = new Paint();
                    i2 = i4;
                    paint.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
                    new Canvas(bitmapCopy).drawBitmap(bitmapCopy, 0.0f, 0.0f, paint);
                } else {
                    i2 = i4;
                    bitmapCopy = null;
                }
                standardNotificationBuilder2.k = bitmapCopy;
                if (bitmap5 != null) {
                    bitmapCopy2 = bitmap5.copy(bitmap5.getConfig(), true);
                    Paint paint2 = new Paint();
                    paint2.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
                    new Canvas(bitmapCopy2).drawBitmap(bitmapCopy2, 0.0f, 0.0f, paint2);
                } else {
                    bitmapCopy2 = null;
                }
                standardNotificationBuilder2.l = bitmapCopy2;
                standardNotificationBuilder2.m = o8VarF;
                standardNotificationBuilder2.n = o8VarF2;
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                spannableStringBuilder.append((CharSequence) str12);
                spannableStringBuilder.append((CharSequence) "\n");
                spannableStringBuilder.append((CharSequence) str13);
                boolean z6 = false;
                spannableStringBuilder.setSpan(new StyleSpan(1), 0, str12.length(), 18);
                standardNotificationBuilder2.h = a.d(spannableStringBuilder);
                standardNotificationBuilder2.t = j;
                standardNotificationBuilder2.u = z;
                standardNotificationBuilder2.f = a.d(N.MR6Af3ZS(str8, 1));
                if (!z5) {
                    standardNotificationBuilder2.g = xn2.a.a.b(str8);
                }
                int i5 = 0;
                while (true) {
                    actionInfoArr2 = actionInfoArr;
                    if (i5 >= actionInfoArr2.length) {
                        break;
                    }
                    ActionInfo actionInfo = actionInfoArr2[i5];
                    Context context3 = context2;
                    StandardNotificationBuilder standardNotificationBuilder3 = standardNotificationBuilder2;
                    String str14 = str7;
                    o8 o8VarF3 = NotificationPlatformBridge.f(context2, "org.chromium.chrome.browser.notifications.CLICK_NOTIFICATION", str7, i2, str8, str9, str10, z3, str11, i5, actionInfo.c == 1 ? true : z6);
                    Bitmap bitmap6 = z4 ? null : actionInfo.b;
                    if (actionInfo.c == 1) {
                        standardNotificationBuilder3.b(bitmap6, actionInfo.a, o8VarF3, 1, actionInfo.d);
                    } else {
                        standardNotificationBuilder3.b(bitmap6, actionInfo.a, o8VarF3, 0, null);
                    }
                    i5++;
                    standardNotificationBuilder2 = standardNotificationBuilder3;
                    context2 = context3;
                    str7 = str14;
                    z6 = false;
                }
                StandardNotificationBuilder standardNotificationBuilder4 = standardNotificationBuilder2;
                final String str15 = str7;
                boolean z7 = zL;
                int[] iArr2 = !z7 ? NotificationPlatformBridge.e : iArr;
                int length = iArr2.length;
                boolean z8 = z2;
                if (z8) {
                    standardNotificationBuilder = standardNotificationBuilder4;
                    i3 = 0;
                } else {
                    i3 = (length > 0 || !z7) ? -3 : -1;
                    standardNotificationBuilder = standardNotificationBuilder4;
                }
                standardNotificationBuilder.q = i3;
                int length2 = iArr2.length + 1;
                long[] jArr = new long[length2];
                int i6 = 0;
                while (i6 < iArr2.length) {
                    int i7 = i6 + 1;
                    jArr[i7] = iArr2[i6];
                    i6 = i7;
                }
                standardNotificationBuilder.r = Arrays.copyOf(jArr, length2);
                standardNotificationBuilder.s = z8;
                if (!str11.isEmpty()) {
                    final rp3 rp3VarA = rp3.a();
                    rp3VarA.getClass();
                    final StandardNotificationBuilder standardNotificationBuilder5 = standardNotificationBuilder;
                    rp3VarA.a.a(xz.a, str11, new rp3.a() { // from class: np3
                        public final /* synthetic */ int f = -1;

                        @Override // rp3.a
                        public final void b(pw0 pw0Var) {
                            Bitmap bitmapDecodeResource;
                            int i8;
                            Bitmap bitmapCopy3;
                            Bitmap bitmapCopy4;
                            String str16 = str11;
                            rp3VarA.getClass();
                            String string = null;
                            try {
                                bitmapDecodeResource = BitmapFactory.decodeResource(xz.a.getPackageManager().getResourcesForApplication(str16), pw0Var.getSmallIconId());
                            } catch (PackageManager.NameNotFoundException unused) {
                                bitmapDecodeResource = null;
                            }
                            a aVar = standardNotificationBuilder5;
                            boolean z9 = false;
                            if (!(aVar.l != null)) {
                                if (bitmapDecodeResource != null) {
                                    bitmapCopy4 = bitmapDecodeResource.copy(bitmapDecodeResource.getConfig(), true);
                                    Paint paint3 = new Paint();
                                    paint3.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
                                    new Canvas(bitmapCopy4).drawBitmap(bitmapCopy4, 0.0f, 0.0f, paint3);
                                } else {
                                    bitmapCopy4 = null;
                                }
                                aVar.l = bitmapCopy4;
                            }
                            if (!(aVar.k != null)) {
                                if (bitmapDecodeResource != null) {
                                    bitmapCopy3 = bitmapDecodeResource.copy(bitmapDecodeResource.getConfig(), true);
                                    Paint paint4 = new Paint();
                                    paint4.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
                                    new Canvas(bitmapCopy3).drawBitmap(bitmapCopy3, 0.0f, 0.0f, paint4);
                                } else {
                                    bitmapCopy3 = null;
                                }
                                aVar.k = bitmapCopy3;
                            }
                            int iK = pw0Var.k();
                            if (iK == 0) {
                                i8 = 1;
                            } else {
                                i8 = 2;
                                if (iK == 2) {
                                    i8 = 3;
                                }
                            }
                            if (i8 != 1 && pw0Var.u()) {
                                i8 = 1;
                            }
                            oj2.U(i8, 6, "WebApk.Notification.Permission.Status2");
                            if (i8 != 1) {
                                String str17 = str8;
                                ft1 ft1VarB = ft1.b(str17);
                                if (ft1VarB == null) {
                                    fa1.k("WebApkServiceClient", "String (%s) could not be parsed as Origin.", str17);
                                    return;
                                } else {
                                    if (yj.a()) {
                                        org.chromium.chrome.browser.browserservices.permissiondelegation.a.a().e(ft1VarB, str16, 5, i8);
                                        return;
                                    }
                                    return;
                                }
                            }
                            try {
                                if (xz.a.getPackageManager().getApplicationInfo(str16, 0).targetSdkVersion >= 26) {
                                    z9 = true;
                                }
                            } catch (PackageManager.NameNotFoundException unused2) {
                            }
                            if (z9) {
                                aVar.g = "default_channel_id";
                                string = xz.a.getString(R$string.webapk_notification_channel_name);
                            }
                            int i9 = this.f;
                            String str18 = str15;
                            pw0Var.q(str18, i9, string, aVar.c(new o8(9, i9, str18)).a);
                        }
                    });
                    return;
                }
                if (notificationPlatformBridge.d().b(Uri.parse(str9))) {
                    final sf3 sf3VarD = notificationPlatformBridge.d();
                    Uri uri = Uri.parse(str9);
                    final kp1 kp1Var = kp1.a.a;
                    sf3VarD.getClass();
                    final String string = xz.a.getResources().getString(R$string.notification_category_group_general);
                    final StandardNotificationBuilder standardNotificationBuilder6 = standardNotificationBuilder;
                    sf3VarD.a(uri, new sf3.a() { // from class: kf3
                        public final /* synthetic */ int i = -1;

                        /* JADX WARN: Removed duplicated region for block: B:17:0x0059  */
                        @Override // sf3.a
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct code enable 'Show inconsistent code' option in preferences
                        */
                        public final void f(defpackage.ft1 r14, defpackage.vf3 r15) {
                            /*
                                Method dump skipped, instruction units count: 288
                                To view this dump change 'Code comments level' option to 'DEBUG'
                            */
                            throw new UnsupportedOperationException("Method not decompiled: defpackage.kf3.f(ft1, vf3):void");
                        }
                    });
                    return;
                }
                Context context4 = xz.a;
                Resources resources = context4.getResources();
                String name = SingleWebsiteSettings.class.getName();
                Bundle bundleM0 = SingleWebsiteSettings.M0(str8);
                Intent intent = new Intent();
                intent.setClass(context4, SettingsActivity.class);
                if (!(context4 instanceof Activity)) {
                    intent.addFlags(268435456);
                    intent.addFlags(67108864);
                }
                intent.putExtra("show_fragment", name);
                intent.putExtra("show_fragment_args", bundleM0);
                intent.setData(NotificationPlatformBridge.e(-1, str15, str8));
                final int i8 = 0;
                o8 o8VarA = o8.a(context4, 0, intent, 134217728);
                boolean z9 = actionInfoArr2.length > 0;
                standardNotificationBuilder.p = new a.C0095a(z9 ? 0 : R$drawable.settings_cog, a.d(z9 ? resources.getString(R$string.notification_site_settings_button) : resources.getString(R$string.page_info_site_settings_button)), o8VarA);
                lp1 lp1VarC = standardNotificationBuilder.c(new o8(7, -1, str15));
                n62 n62Var = new n62(notificationPlatformBridge, 8, lp1VarC);
                if (i2 == 0) {
                    BrowserStartupControllerImpl browserStartupControllerImplA = qj.a();
                    if (browserStartupControllerImplA.g()) {
                        s62VarC2 = s62.c(null);
                    } else {
                        s62 s62Var2 = new s62();
                        browserStartupControllerImplA.b(new gp1(s62Var2));
                        s62VarC2 = s62Var2;
                    }
                    fp1 fp1Var = new fp1();
                    final s62 s62Var3 = new s62();
                    s62VarC2.i(new p62(fp1Var, i8, s62Var3));
                    s62VarC2.a(new Callback() { // from class: q62
                        @Override // org.chromium.base.Callback
                        public final void onResult(Object obj2) {
                            int i9 = i8;
                            s62 s62Var4 = s62Var3;
                            switch (i9) {
                                case 0:
                                    s62Var4.e((Exception) obj2);
                                    break;
                                default:
                                    s62Var4.b((HistoryClustersResult) obj2);
                                    break;
                            }
                        }
                    });
                    s62Var3.f(new n91(2, lp1VarC)).g(n62Var);
                } else {
                    n62Var.onResult(Boolean.FALSE);
                }
                if (str8.isEmpty() || z3) {
                    return;
                }
                ro1 ro1Var = new ro1(xz.a);
                if (z7.s == null) {
                    z7.s = new z7();
                }
                z7 z7Var = z7.s;
                boolean zA = ro1Var.a();
                z7Var.getClass();
                N.MdNuOA_F(str8, str10, zA);
            }
        });
    }
}
