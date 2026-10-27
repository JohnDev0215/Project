package defpackage;

import android.hardware.bydauto.TsNormalNum;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.base.SplitCompatApplication;
import org.chromium.chrome.browser.crash.MinidumpUploadServiceImpl;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;
import org.chromium.content.browser.LauncherThread;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ar2 implements ApplicationStatus.d {
    public final /* synthetic */ int e;

    public /* synthetic */ ar2(int i) {
        this.e = i;
    }

    @Override // org.chromium.base.ApplicationStatus.d
    public final void v(int i) {
        Integer num;
        switch (this.e) {
            case 0:
                int i2 = SplitCompatApplication.g;
                if (i == 1) {
                    ve1 ve1Var = ve1.i;
                    ve1Var.getClass();
                    Object obj = ThreadUtils.a;
                    if (!ve1Var.e) {
                        ve1Var.e = true;
                        if (!ve1Var.d && (num = (Integer) ve1Var.f.get()) != null) {
                            ve1Var.b(num.intValue());
                            break;
                        }
                    }
                } else if (i == 3) {
                    ve1 ve1Var2 = ve1.i;
                    ve1Var2.getClass();
                    Object obj2 = ThreadUtils.a;
                    if (ve1Var2.e) {
                        ve1Var2.e = false;
                        break;
                    }
                }
                break;
            case 1:
                AtomicBoolean atomicBoolean = MinidumpUploadServiceImpl.b;
                SharedPreferencesManager.getInstance().m(i, "Chrome.CrashReporting.LastSessionApplicationState");
                break;
            default:
                if (i == 0) {
                    o90 o90Var = ChildProcessLauncherHelperImpl.m;
                } else {
                    o90 o90Var2 = ChildProcessLauncherHelperImpl.m;
                    if (i == 1 || i == 2) {
                        if (!ChildProcessLauncherHelperImpl.w) {
                            ChildProcessLauncherHelperImpl.w = true;
                            LauncherThread.b.removeCallbacks(o90Var2);
                            LauncherThread.a(new o90(14));
                        }
                    } else if (ChildProcessLauncherHelperImpl.w) {
                        ChildProcessLauncherHelperImpl.w = false;
                        LauncherThread.b.postDelayed(o90Var2, TsNormalNum.THOUSAND_NINE);
                        LauncherThread.a(new qe3(24));
                    }
                }
                break;
        }
    }
}
