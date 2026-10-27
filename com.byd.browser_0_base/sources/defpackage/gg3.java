package defpackage;

import J.N;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import defpackage.an;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.ContentUriUtils;
import org.chromium.base.SysUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.enterprise.util.EnterpriseInfo;
import org.chromium.chrome.browser.media.MediaLauncherActivity;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;
import org.chromium.ui.accessibility.AccessibilityState;
import org.chromium.ui.base.Clipboard;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class gg3 implements Runnable {
    public final /* synthetic */ int e;

    public /* synthetic */ gg3(int i) {
        this.e = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Uri uriB;
        int i = 0;
        switch (this.e) {
            case 0:
                n3.X0("TrustedWebActivity.OpenedSettingsViaManageSpace");
                return;
            case 1:
                HashSet hashSet = CustomTabsConnection.o;
                TraceEvent traceEventH0 = TraceEvent.H0("WarmupInternalFinishInitialization", null);
                try {
                    Profile profileD = Profile.d();
                    TraceEvent traceEventH02 = TraceEvent.H0("WarmupManager.startPreconnectPredictorInitialization", null);
                    try {
                        Object obj = ThreadUtils.a;
                        N.MejOrYY2(profileD);
                        if (traceEventH02 != null) {
                            traceEventH02.close();
                        }
                        jb2.b();
                        if (traceEventH0 != null) {
                            traceEventH0.close();
                            return;
                        }
                        return;
                    } catch (Throwable th) {
                        if (traceEventH02 != null) {
                            try {
                                traceEventH02.close();
                                break;
                            } catch (Throwable unused) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    if (traceEventH0 != null) {
                        try {
                            traceEventH0.close();
                            break;
                        } catch (Throwable unused2) {
                        }
                    }
                    throw th2;
                }
            case 2:
                AtomicBoolean atomicBoolean = jb2.g;
                xz.a.getSharedPreferences("customtabs_client_bans", 0).edit();
                return;
            case 3:
            case 4:
                return;
            case 5:
                Object obj2 = b62.d;
                ((jc) a8.E()).getClass();
                TraceEvent traceEventH03 = TraceEvent.H0("BackgroundTaskScheduler.checkForOSUpgrade", null);
                try {
                    Object obj3 = ThreadUtils.a;
                    lc.L().getClass();
                    lc.K();
                    if (traceEventH03 != null) {
                        traceEventH03.close();
                        return;
                    }
                    return;
                } catch (Throwable th3) {
                    if (traceEventH03 != null) {
                        try {
                            traceEventH03.close();
                            break;
                        } catch (Throwable unused3) {
                        }
                    }
                    throw th3;
                }
            case 6:
                Object obj4 = b62.d;
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(xl2.r() ? Arrays.asList(ou0.PRICE_TRACKING) : Arrays.asList(new ou0[0]));
                long j = new vs1(arrayList).a().a;
                if (j != 0) {
                    N.M83D7rt6(j);
                }
                if (z7.Q() && xl2.r()) {
                    xl2.t();
                    return;
                }
                return;
            case 7:
                Object obj5 = b62.d;
                EnterpriseInfo.b().c();
                return;
            case 8:
                Object obj6 = b62.d;
                Object obj7 = an.e;
                an.a.a.a();
                return;
            case 9:
                Context context = xz.a;
                PackageManager packageManager = context.getPackageManager();
                ComponentName componentName = new ComponentName(context, (Class<?>) MediaLauncherActivity.class);
                ComponentName componentName2 = new ComponentName(context, "org.chromium.chrome.browser.media.AudioLauncherActivity");
                int i2 = oe1.c() ? 1 : 2;
                if (oe1.c() && !SysUtils.isLowEndDevice()) {
                    i = 1;
                }
                int i3 = i != 0 ? 1 : 2;
                if (packageManager.getComponentEnabledSetting(componentName) != i2) {
                    packageManager.setComponentEnabledSetting(componentName, i2, 1);
                }
                if (packageManager.getComponentEnabledSetting(componentName2) != i3) {
                    packageManager.setComponentEnabledSetting(componentName2, i3, 1);
                    return;
                }
                return;
            case 10:
                oj2.U(1, 3, "NewTabPage.SuggestTiles.DeletedTileType");
                return;
            case 11:
                n3.X0("MobileToolbarShowMenu");
                return;
            case 12:
            case 13:
                return;
            case 14:
                try {
                    Uri uriC = Clipboard.getInstance().c();
                    pm0.d(jk2.d(), new ik2(i, (uriC != null && (uriB = ContentUriUtils.b(jk2.d())) != null) ? uriC.toString().startsWith(uriB.toString()) : false ? uriC.getPath() : null));
                    return;
                } catch (IOException unused4) {
                    return;
                }
            case 15:
                ChildProcessLauncherHelperImpl.A.sendMessage(ChildProcessLauncherHelperImpl.b());
                return;
            default:
                int i4 = AccessibilityState.a;
                N.Mp$Ilbw7();
                return;
        }
    }
}
