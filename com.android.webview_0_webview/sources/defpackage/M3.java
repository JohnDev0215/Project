package defpackage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class M3 extends R3 {
    public final /* synthetic */ O3 e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public M3(O3 o3) {
        super(o3);
        this.e = o3;
    }

    @Override // defpackage.P3
    public View b(int i) {
        return this.e.findViewById(i);
    }

    @Override // defpackage.P3
    public boolean c() {
        Window window = this.e.getWindow();
        return (window == null || window.peekDecorView() == null) ? false : true;
    }

    @Override // defpackage.R3
    public void d(K3 k3, Intent intent, int i, Bundle bundle) {
        O3 o3 = this.e;
        o3.z = true;
        try {
            if (i == -1) {
                I1.f(o3, intent, -1, bundle);
            } else {
                O3.d(i);
                I1.f(o3, intent, ((o3.c(k3) + 1) << 16) + (i & 65535), bundle);
            }
        } finally {
            o3.z = false;
        }
    }
}
