package defpackage;

import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: compiled from: ITbox.java */
/* JADX INFO: loaded from: classes.dex */
public interface um extends IHwInterface {

    /* JADX INFO: compiled from: ITbox.java */
    public static final class a implements um {
        public IHwBinder a;

        public a(IHwBinder iHwBinder) {
            Objects.requireNonNull(iHwBinder);
            this.a = iHwBinder;
        }

        public IHwBinder asBinder() {
            return this.a;
        }

        @Override // defpackage.um
        public int b(vm vmVar) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tbox@1.0::ITbox");
            hwParcel.writeStrongBinder(vmVar == null ? null : vmVar.asBinder());
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.a.transact(3, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // defpackage.um
        public int c(vm vmVar) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tbox@1.0::ITbox");
            hwParcel.writeStrongBinder(vmVar == null ? null : vmVar.asBinder());
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.a.transact(4, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // defpackage.um
        public ArrayList<String> e() throws RemoteException {
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

        public final boolean equals(Object obj) {
            return HidlSupport.interfacesEqual(this, obj);
        }

        @Override // defpackage.um
        public String h() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tbox@1.0::ITbox");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.a.transact(14, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // defpackage.um
        public byte i() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tbox@1.0::ITbox");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.a.transact(5, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt8();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // defpackage.um
        public String j() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tbox@1.0::ITbox");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.a.transact(7, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        public String q() throws RemoteException {
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

        public String toString() {
            try {
                return q() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of vendor.bosch.tbox@1.0::ITbox]@Proxy";
            }
        }
    }

    static um a(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface("vendor.bosch.tbox@1.0::ITbox");
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof um)) {
            return (um) iHwInterfaceQueryLocalInterface;
        }
        a aVar = new a(iHwBinder);
        try {
            Iterator<String> it = aVar.e().iterator();
            while (it.hasNext()) {
                if (it.next().equals("vendor.bosch.tbox@1.0::ITbox")) {
                    return aVar;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static um m(String str) throws RemoteException {
        return a(HwBinder.getService("vendor.bosch.tbox@1.0::ITbox", str));
    }

    static um p() throws RemoteException {
        return m("default");
    }

    int b(vm vmVar) throws RemoteException;

    int c(vm vmVar) throws RemoteException;

    ArrayList<String> e() throws RemoteException;

    String h() throws RemoteException;

    byte i() throws RemoteException;

    String j() throws RemoteException;
}
