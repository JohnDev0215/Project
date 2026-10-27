package f.a.a.a;

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
/* JADX INFO: loaded from: classes3.dex */
public interface c extends IHwInterface {

    /* JADX INFO: compiled from: ITbox.java */
    public static final class a implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public IHwBinder f3955a;

        public a(IHwBinder iHwBinder) {
            Objects.requireNonNull(iHwBinder);
            this.f3955a = iHwBinder;
        }

        public IHwBinder asBinder() {
            return this.f3955a;
        }

        @Override // f.a.a.a.c
        public int b(d dVar) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tbox@1.0::ITbox");
            hwParcel.writeStrongBinder(dVar == null ? null : dVar.asBinder());
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f3955a.transact(4, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        public final boolean equals(Object obj) {
            return HidlSupport.interfacesEqual(this, obj);
        }

        @Override // f.a.a.a.c
        public byte f() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tbox@1.0::ITbox");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f3955a.transact(9, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt8();
            } finally {
                hwParcel2.release();
            }
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // f.a.a.a.c
        public int i(d dVar) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tbox@1.0::ITbox");
            hwParcel.writeStrongBinder(dVar == null ? null : dVar.asBinder());
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f3955a.transact(3, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // f.a.a.a.c
        public ArrayList<String> j() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f3955a.transact(256067662, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readStringVector();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // f.a.a.a.c
        public String l() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tbox@1.0::ITbox");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f3955a.transact(14, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // f.a.a.a.c
        public String m() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tbox@1.0::ITbox");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f3955a.transact(13, hwParcel, hwParcel2, 0);
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
                this.f3955a.transact(256136003, hwParcel, hwParcel2, 0);
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

    static c a(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface("vendor.bosch.tbox@1.0::ITbox");
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof c)) {
            return (c) iHwInterfaceQueryLocalInterface;
        }
        a aVar = new a(iHwBinder);
        try {
            Iterator<String> it = aVar.j().iterator();
            while (it.hasNext()) {
                if (it.next().equals("vendor.bosch.tbox@1.0::ITbox")) {
                    return aVar;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static c e(String str) throws RemoteException {
        return a(HwBinder.getService("vendor.bosch.tbox@1.0::ITbox", str));
    }

    static c p() throws RemoteException {
        return e("default");
    }

    int b(d dVar) throws RemoteException;

    byte f() throws RemoteException;

    int i(d dVar) throws RemoteException;

    ArrayList<String> j() throws RemoteException;

    String l() throws RemoteException;

    String m() throws RemoteException;
}
