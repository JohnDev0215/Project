package defpackage;

import J.N;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.view.Surface;
import org.chromium.base.UnguessableToken;
import org.chromium.content.common.SurfaceWrapper;

/* JADX INFO: renamed from: wD, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC1700wD extends Binder implements InterfaceC1755xD {
    public AbstractBinderC1700wD() {
        attachInterface(this, "org.chromium.content.common.IGpuProcessCallback");
    }

    public static InterfaceC1755xD c(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("org.chromium.content.common.IGpuProcessCallback");
        return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC1755xD)) ? new C1645vD(iBinder) : (InterfaceC1755xD) iInterfaceQueryLocalInterface;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("org.chromium.content.common.IGpuProcessCallback");
            N.M$O7xE3y(parcel.readInt() != 0 ? (UnguessableToken) UnguessableToken.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Surface) Surface.CREATOR.createFromParcel(parcel) : null);
            return true;
        }
        if (i != 2) {
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("org.chromium.content.common.IGpuProcessCallback");
            return true;
        }
        parcel.enforceInterface("org.chromium.content.common.IGpuProcessCallback");
        SurfaceWrapper surfaceWrapperT = ((BinderC1080kz) this).t(parcel.readInt());
        parcel2.writeNoException();
        if (surfaceWrapperT != null) {
            parcel2.writeInt(1);
            surfaceWrapperT.writeToParcel(parcel2, 1);
        } else {
            parcel2.writeInt(0);
        }
        return true;
    }
}
