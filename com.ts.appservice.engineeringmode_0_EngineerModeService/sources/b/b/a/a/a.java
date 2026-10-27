package b.b.a.a;

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
public interface a extends IHwInterface {

    /* JADX INFO: renamed from: b.b.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    public static final class C0002a implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IHwBinder f13a;

        public C0002a(IHwBinder iHwBinder) {
            Objects.requireNonNull(iHwBinder);
            this.f13a = iHwBinder;
        }

        public IHwBinder asBinder() {
            return this.f13a;
        }

        @Override // b.b.a.a.a
        public ArrayList<String> b() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f13a.transact(256067662, hwParcel, hwParcel2, 0);
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

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // b.b.a.a.a
        public boolean i(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.rbxc.usbext@1.0::IUsbext");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f13a.transact(11, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readBool();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // b.b.a.a.a
        public int k(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.rbxc.usbext@1.0::IUsbext");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f13a.transact(10, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        public String toString() {
            try {
                return v() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of vendor.rbxc.usbext@1.0::IUsbext]@Proxy";
            }
        }

        public String v() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f13a.transact(256136003, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }
    }

    static a a(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface("vendor.rbxc.usbext@1.0::IUsbext");
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof a)) {
            return (a) iHwInterfaceQueryLocalInterface;
        }
        C0002a c0002a = new C0002a(iHwBinder);
        try {
            Iterator<String> it = c0002a.b().iterator();
            while (it.hasNext()) {
                if (it.next().equals("vendor.rbxc.usbext@1.0::IUsbext")) {
                    return c0002a;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static a c(String str) {
        return a(HwBinder.getService("vendor.rbxc.usbext@1.0::IUsbext", str));
    }

    static a d() {
        return c("default");
    }

    ArrayList<String> b();

    boolean i(int i);

    int k(int i);
}
