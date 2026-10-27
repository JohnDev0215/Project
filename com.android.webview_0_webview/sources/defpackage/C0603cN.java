package defpackage;

import J.N;
import org.chromium.android_webview.AwSettings;
import org.chromium.support_lib_boundary.WebSettingsBoundaryInterface;

/* JADX INFO: renamed from: cN, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0603cN implements WebSettingsBoundaryInterface {
    public final AwSettings a;

    public C0603cN(AwSettings awSettings) {
        this.a = awSettings;
    }

    @Override // org.chromium.support_lib_boundary.WebSettingsBoundaryInterface
    public int getDisabledActionModeMenuItems() {
        C0826gN.a(39);
        return this.a.m();
    }

    @Override // org.chromium.support_lib_boundary.WebSettingsBoundaryInterface
    public int getForceDark() {
        int forceDarkModeLocked;
        C0826gN.a(40);
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            forceDarkModeLocked = awSettings.getForceDarkModeLocked();
        }
        return forceDarkModeLocked;
    }

    @Override // org.chromium.support_lib_boundary.WebSettingsBoundaryInterface
    public int getForceDarkBehavior() {
        int forceDarkBehaviorLocked;
        C0826gN.a(41);
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            forceDarkBehaviorLocked = awSettings.getForceDarkBehaviorLocked();
        }
        if (forceDarkBehaviorLocked != 0) {
            return forceDarkBehaviorLocked != 1 ? 2 : 1;
        }
        return 0;
    }

    @Override // org.chromium.support_lib_boundary.WebSettingsBoundaryInterface
    public boolean getOffscreenPreRaster() {
        C0826gN.a(42);
        return this.a.v();
    }

    @Override // org.chromium.support_lib_boundary.WebSettingsBoundaryInterface
    public boolean getSafeBrowsingEnabled() {
        C0826gN.a(43);
        return this.a.w();
    }

    @Override // org.chromium.support_lib_boundary.WebSettingsBoundaryInterface
    public boolean getWillSuppressErrorPage() {
        C0826gN.a(44);
        return this.a.E();
    }

    @Override // org.chromium.support_lib_boundary.WebSettingsBoundaryInterface
    public void setDisabledActionModeMenuItems(int i) {
        C0826gN.a(45);
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            awSettings.S = i;
        }
    }

    @Override // org.chromium.support_lib_boundary.WebSettingsBoundaryInterface
    public void setForceDark(int i) {
        C0826gN.a(46);
        this.a.J(i);
    }

    @Override // org.chromium.support_lib_boundary.WebSettingsBoundaryInterface
    public void setForceDarkBehavior(int i) {
        C0826gN.a(47);
        if (i == 0) {
            this.a.I(0);
        } else if (i == 1) {
            this.a.I(1);
        } else {
            if (i != 2) {
                return;
            }
            this.a.I(2);
        }
    }

    @Override // org.chromium.support_lib_boundary.WebSettingsBoundaryInterface
    public void setOffscreenPreRaster(boolean z) {
        C0826gN.a(48);
        this.a.L(z);
    }

    @Override // org.chromium.support_lib_boundary.WebSettingsBoundaryInterface
    public void setSafeBrowsingEnabled(boolean z) {
        C0826gN.a(49);
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            awSettings.U = Boolean.valueOf(z);
        }
    }

    @Override // org.chromium.support_lib_boundary.WebSettingsBoundaryInterface
    public void setWillSuppressErrorPage(boolean z) {
        C0826gN.a(50);
        final AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (awSettings.Q == z) {
                return;
            }
            awSettings.Q = z;
            awSettings.m0.a(new Runnable(awSettings) { // from class: kp
                public final AwSettings s;

                {
                    this.s = awSettings;
                }

                @Override // java.lang.Runnable
                public void run() {
                    AwSettings awSettings2 = this.s;
                    N.MVXG782O(awSettings2.l0, awSettings2);
                }
            });
        }
    }
}
