package defpackage;

import J.N;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import org.chromium.chrome.browser.webapps.WebApkInstallCoordinatorBridge;

/* JADX INFO: compiled from: IWebApkInstallCoordinatorService.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class qw0 extends Binder implements IInterface {
    public qw0() {
        attachInterface(this, "org.chromium.components.webapk_install.IWebApkInstallCoordinatorService");
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("org.chromium.components.webapk_install.IWebApkInstallCoordinatorService");
        }
        if (i == 1598968902) {
            parcel2.writeString("org.chromium.components.webapk_install.IWebApkInstallCoordinatorService");
            return true;
        }
        if (i != 1) {
            return super.onTransact(i, parcel, parcel2, i2);
        }
        byte[] bArrCreateByteArray = parcel.createByteArray();
        fw0 ew0Var = null;
        Bitmap bitmap = (Bitmap) (parcel.readInt() != 0 ? Bitmap.CREATOR.createFromParcel(parcel) : null);
        boolean z = parcel.readInt() != 0;
        IBinder strongBinder = parcel.readStrongBinder();
        if (strongBinder != null) {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("org.chromium.components.webapk_install.IOnFinishInstallCallback");
            ew0Var = (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof fw0)) ? new ew0(strongBinder) : (fw0) iInterfaceQueryLocalInterface;
        }
        WebApkInstallCoordinatorBridge webApkInstallCoordinatorBridge = new WebApkInstallCoordinatorBridge();
        webApkInstallCoordinatorBridge.b = ew0Var;
        N.M2rXsUfq(webApkInstallCoordinatorBridge.a, webApkInstallCoordinatorBridge, bArrCreateByteArray, bitmap, z);
        return true;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }
}
