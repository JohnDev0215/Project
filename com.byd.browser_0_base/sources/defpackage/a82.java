package defpackage;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.os.Process;
import java.lang.ref.WeakReference;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: QrCodeScanMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a82 implements Camera.PreviewCallback {
    public final Context a;
    public final PropertyModel b;
    public final l2 c;
    public final WindowAndroid d;

    public a82(Activity activity, PropertyModel propertyModel, WindowAndroid windowAndroid) {
        this.a = activity;
        this.b = propertyModel;
        this.c = new l2(new WeakReference(activity));
        a();
        this.d = windowAndroid;
    }

    public final void a() {
        PropertyModel.n nVar = f82.b;
        boolean zBooleanValue = Boolean.valueOf(this.c.canRequestPermission("android.permission.CAMERA")).booleanValue();
        PropertyModel propertyModel = this.b;
        propertyModel.l(nVar, zBooleanValue);
        propertyModel.l(f82.a, Boolean.valueOf(this.a.checkPermission("android.permission.CAMERA", Process.myPid(), Process.myUid()) == 0).booleanValue());
    }

    @Override // android.hardware.Camera.PreviewCallback
    public final void onPreviewFrame(byte[] bArr, Camera camera) {
    }
}
