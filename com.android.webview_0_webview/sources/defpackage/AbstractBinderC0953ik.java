package defpackage;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: renamed from: ik, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0953ik extends AbstractBinderC0401Wg implements InterfaceC1009jk {
    public static InterfaceC1009jk v(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator");
        return iInterfaceQueryLocalInterface instanceof InterfaceC1009jk ? (InterfaceC1009jk) iInterfaceQueryLocalInterface : new C1065kk(iBinder);
    }
}
