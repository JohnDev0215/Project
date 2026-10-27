package b.a.a.a;

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

    /* JADX INFO: renamed from: b.a.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    public static final class C0001a implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IHwBinder f3a;

        public C0001a(IHwBinder iHwBinder) {
            Objects.requireNonNull(iHwBinder);
            this.f3a = iHwBinder;
        }

        public IHwBinder asBinder() {
            return this.f3a;
        }

        @Override // b.a.a.a.a
        public ArrayList<String> b() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f3a.transact(256067662, hwParcel, hwParcel2, 0);
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

        @Override // b.a.a.a.a
        public boolean getDisplayForwardLinkMarginValue(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.display@1.0::IDisplay");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f3a.transact(17, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readBool();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // b.a.a.a.a
        public boolean getDisplayReverseLinkMarginValue(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.display@1.0::IDisplay");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f3a.transact(16, hwParcel, hwParcel2, 0);
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

        public String toString() {
            try {
                return v() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of vendor.bosch.display@1.0::IDisplay]@Proxy";
            }
        }

        public String v() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f3a.transact(256136003, hwParcel, hwParcel2, 0);
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
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface("vendor.bosch.display@1.0::IDisplay");
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof a)) {
            return (a) iHwInterfaceQueryLocalInterface;
        }
        C0001a c0001a = new C0001a(iHwBinder);
        try {
            Iterator<String> it = c0001a.b().iterator();
            while (it.hasNext()) {
                if (it.next().equals("vendor.bosch.display@1.0::IDisplay")) {
                    return c0001a;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static a c(String str) {
        return a(HwBinder.getService("vendor.bosch.display@1.0::IDisplay", str));
    }

    static a d() {
        return c("default");
    }

    ArrayList<String> b();

    boolean getDisplayForwardLinkMarginValue(int i);

    boolean getDisplayReverseLinkMarginValue(int i);
}
