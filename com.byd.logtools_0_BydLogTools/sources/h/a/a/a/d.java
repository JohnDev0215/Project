package h.a.a.a;

import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: compiled from: ILog.java */
/* JADX INFO: loaded from: classes.dex */
public interface d extends IHwInterface {

    /* JADX INFO: compiled from: ILog.java */
    public static final class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IHwBinder f4971a;

        public a(IHwBinder iHwBinder) {
            Objects.requireNonNull(iHwBinder);
            this.f4971a = iHwBinder;
        }

        @Override // h.a.a.a.d
        public boolean A(ArrayList<Integer> arrayList) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.log@1.0::ILog");
            hwParcel.writeInt32Vector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f4971a.transact(1, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readBool();
            } finally {
                hwParcel2.release();
            }
        }

        public String D() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f4971a.transact(256136003, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        public IHwBinder asBinder() {
            return this.f4971a;
        }

        @Override // h.a.a.a.d
        public ArrayList<String> b() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f4971a.transact(256067662, hwParcel, hwParcel2, 0);
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

        @Override // h.a.a.a.d
        public boolean f(e eVar) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.log@1.0::ILog");
            hwParcel.writeStrongBinder(eVar == null ? null : eVar.asBinder());
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f4971a.transact(8, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readBool();
            } finally {
                hwParcel2.release();
            }
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // h.a.a.a.d
        public boolean p(ArrayList<g> arrayList) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.log@1.0::ILog");
            g.d(hwParcel, arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f4971a.transact(2, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readBool();
            } finally {
                hwParcel2.release();
            }
        }

        public String toString() {
            try {
                return D() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of vendor.bosch.log@1.0::ILog]@Proxy";
            }
        }

        @Override // h.a.a.a.d
        public boolean z() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.log@1.0::ILog");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f4971a.transact(3, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readBool();
            } finally {
                hwParcel2.release();
            }
        }
    }

    static d a(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface("vendor.bosch.log@1.0::ILog");
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof d)) {
            return (d) iHwInterfaceQueryLocalInterface;
        }
        a aVar = new a(iHwBinder);
        try {
            Iterator<String> it = aVar.b().iterator();
            while (it.hasNext()) {
                if (it.next().equals("vendor.bosch.log@1.0::ILog")) {
                    return aVar;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static d c(String str) throws RemoteException {
        return a(HwBinder.getService("vendor.bosch.log@1.0::ILog", str));
    }

    static d d() throws RemoteException {
        return c("default");
    }

    boolean A(ArrayList<Integer> arrayList) throws RemoteException;

    ArrayList<String> b() throws RemoteException;

    boolean f(e eVar) throws RemoteException;

    boolean p(ArrayList<g> arrayList) throws RemoteException;

    boolean z() throws RemoteException;
}
