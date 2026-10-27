package b.a.b.a;

import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface d extends IHwInterface {

    /* JADX INFO: compiled from: caradapter-bug */
    public static final class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IHwBinder f8a;

        public a(IHwBinder iHwBinder) {
            Objects.requireNonNull(iHwBinder);
            this.f8a = iHwBinder;
        }

        public IHwBinder asBinder() {
            return this.f8a;
        }

        @Override // b.a.b.a.d
        public ArrayList<String> b() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(256067662, hwParcel, hwParcel2, 0);
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

        @Override // b.a.b.a.d
        public boolean h(e eVar) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.log@1.0::ILog");
            hwParcel.writeStrongBinder(eVar == null ? null : eVar.asBinder());
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(8, hwParcel, hwParcel2, 0);
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

        @Override // b.a.b.a.d
        public boolean n() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.log@1.0::ILog");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(12, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readBool();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // b.a.b.a.d
        public boolean q() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.log@1.0::ILog");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(11, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readBool();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // b.a.b.a.d
        public boolean s(boolean z) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.log@1.0::ILog");
            hwParcel.writeBool(z);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(9, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readBool();
            } finally {
                hwParcel2.release();
            }
        }

        public String toString() {
            try {
                return v() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of vendor.bosch.log@1.0::ILog]@Proxy";
            }
        }

        @Override // b.a.b.a.d
        public boolean u(boolean z) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.log@1.0::ILog");
            hwParcel.writeBool(z);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(10, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readBool();
            } finally {
                hwParcel2.release();
            }
        }

        public String v() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(256136003, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
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

    static d c(String str) {
        return a(HwBinder.getService("vendor.bosch.log@1.0::ILog", str));
    }

    static d d() {
        return c("default");
    }

    ArrayList<String> b();

    boolean h(e eVar);

    boolean n();

    boolean q();

    boolean s(boolean z);

    boolean u(boolean z);
}
