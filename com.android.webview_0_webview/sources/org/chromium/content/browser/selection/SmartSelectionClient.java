package org.chromium.content.browser.selection;

import J.N;
import android.os.Build;
import android.text.TextUtils;
import defpackage.C0872hD;
import defpackage.C1039kD;
import defpackage.C1151mD;
import defpackage.C1206nD;
import defpackage.InterfaceC0482aE;
import defpackage.XD;
import defpackage.YD;
import defpackage.ZD;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class SmartSelectionClient extends XD implements InterfaceC0482aE {
    public long a;
    public C1206nD b;
    public ZD c;
    public C1039kD d;

    public SmartSelectionClient(ZD zd, WebContents webContents, WindowAndroid windowAndroid) {
        this.b = new C1206nD(zd, windowAndroid);
        this.c = zd;
        if (Build.VERSION.SDK_INT >= 28) {
            this.d = C1039kD.a(windowAndroid);
        }
        this.a = N.MFA_dMJC(this, webContents);
    }

    private void onNativeSideDestroyed(long j) {
        this.a = 0L;
        C1206nD c1206nD = this.b;
        C1151mD c1151mD = c1206nD.c;
        if (c1151mD != null) {
            c1151mD.b(false);
            c1206nD.c = null;
        }
    }

    private void onSurroundingTextReceived(int i, String str, int i2, int i3) {
        if (!(!TextUtils.isEmpty(str) && i2 >= 0 && i2 < i3 && i3 <= str.length())) {
            ((C0872hD) this.c).a(new YD());
        } else if (i == 0) {
            this.b.b(0, str, i2, i3);
        } else {
            if (i != 1) {
                return;
            }
            this.b.b(1, str, i2, i3);
        }
    }

    public boolean a(boolean z) {
        long j = this.a;
        if (j == 0) {
            onSurroundingTextReceived(z ? 1 : 0, "", 0, 0);
            return true;
        }
        N.M2GZ6ZNR(j, this, 240, z ? 1 : 0);
        return true;
    }
}
