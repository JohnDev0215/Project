package defpackage;

import android.content.Context;
import android.view.View;
import defpackage.c82;
import org.chromium.base.BuildInfo;
import org.chromium.chrome.R$string;
import org.chromium.components.permissions.AndroidPermissionRequester;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: QrCodeScanView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b82 implements View.OnClickListener {
    public final /* synthetic */ c82.b e;

    public b82(pq2 pq2Var) {
        this.e = pq2Var;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        a82 a82Var = (a82) ((pq2) this.e).f;
        a82Var.getClass();
        dm0 dm0Var = new dm0(29, a82Var);
        WindowAndroid windowAndroid = a82Var.d;
        if (windowAndroid.hasPermission("android.permission.CAMERA")) {
            dm0Var.onResult(Boolean.TRUE);
            return;
        }
        Context context = a82Var.a;
        if (context == null) {
            dm0Var.onResult(Boolean.FALSE);
            return;
        }
        im0 im0Var = new im0(windowAndroid, dm0Var, windowAndroid, 1);
        if (windowAndroid.n() != null) {
            AndroidPermissionRequester.b(windowAndroid, context.getString(R$string.infobar_missing_camera_permission_text, BuildInfo.a.a.a), im0Var, dm0Var.p1(Boolean.FALSE));
        }
    }
}
