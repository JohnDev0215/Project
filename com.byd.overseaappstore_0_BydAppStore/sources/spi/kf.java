package spi;

import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public interface kf extends IHwInterface {

    public static final class a implements kf {
        public IHwBinder a;

        public a(IHwBinder iHwBinder) {
            Objects.requireNonNull(iHwBinder);
            this.a = iHwBinder;
        }

        @Override // spi.kf
        public final byte c() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tbox@1.0::ITbox");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.a.transact(11, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt8();
            } finally {
                hwParcel2.release();
            }
        }

        public final ArrayList<String> d() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.a.transact(256067662, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readStringVector();
            } finally {
                hwParcel2.release();
            }
        }

        public final String e() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.a.transact(256136003, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        public final boolean equals(Object obj) {
            return HidlSupport.interfacesEqual(this, obj);
        }

        public final int hashCode() {
            return this.a.hashCode();
        }

        public final String toString() {
            try {
                return e() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of vendor.bosch.tbox@1.0::ITbox]@Proxy";
            }
        }
    }

    static kf a(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface("vendor.bosch.tbox@1.0::ITbox");
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof kf)) {
            return (kf) iHwInterfaceQueryLocalInterface;
        }
        a aVar = new a(iHwBinder);
        try {
            Iterator<String> it = aVar.d().iterator();
            while (it.hasNext()) {
                if (it.next().equals("vendor.bosch.tbox@1.0::ITbox")) {
                    return aVar;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static kf b() throws RemoteException {
        return a(HwBinder.getService("vendor.bosch.tbox@1.0::ITbox", "default"));
    }

    byte c() throws RemoteException;
}
