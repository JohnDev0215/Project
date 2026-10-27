package defpackage;

import android.app.Activity;
import android.hardware.Camera;
import android.view.View;
import org.chromium.chrome.browser.share.qrcode.scan_tab.CameraPreview;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: QrCodeScanCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class z72 implements w72 {
    public final c82 a;
    public final a82 b;

    /* JADX WARN: Type inference failed for: r2v0, types: [y72] */
    public z72(Activity activity, WindowAndroid windowAndroid) {
        PropertyModel propertyModel = new PropertyModel(f82.d);
        final a82 a82Var = new a82(activity, propertyModel, windowAndroid);
        this.b = a82Var;
        c82 c82Var = new c82(activity, new Camera.PreviewCallback() { // from class: y72
            @Override // android.hardware.Camera.PreviewCallback
            public final void onPreviewFrame(byte[] bArr, Camera camera) {
                a82Var.getClass();
            }
        }, new pq2(27, a82Var));
        this.a = c82Var;
        j.b(propertyModel, c82Var, new e82());
    }

    @Override // defpackage.w72
    public final View getView() {
        return this.a.b;
    }

    @Override // defpackage.w72
    public final void onDestroy() {
        c82 c82Var = this.a;
        CameraPreview cameraPreview = c82Var.g;
        if (cameraPreview != null) {
            cameraPreview.d();
            c82Var.g = null;
        }
    }

    @Override // defpackage.w72
    public final void onPause() {
        this.b.b.l(f82.c, false);
    }

    @Override // defpackage.w72
    public final void onResume() {
        n3.X0("SharingQRCode.TabVisible.Scan");
        a82 a82Var = this.b;
        a82Var.a();
        a82Var.b.l(f82.c, true);
    }

    @Override // defpackage.w72
    public final void a(WindowAndroid windowAndroid) {
    }
}
