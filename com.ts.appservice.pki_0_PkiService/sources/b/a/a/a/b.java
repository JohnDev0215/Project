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
public interface b extends IHwInterface {

    /* JADX INFO: compiled from: caradapter-bug */
    public static final class a implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IHwBinder f8a;

        public a(IHwBinder iHwBinder) {
            Objects.requireNonNull(iHwBinder);
            this.f8a = iHwBinder;
        }

        @Override // b.a.a.a.b
        public b.a.a.a.a a(ArrayList<Byte> arrayList, int i, int i2, int i3, int i4, int i5) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tee@1.0::IBoschTeeService");
            hwParcel.writeInt8Vector(arrayList);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            hwParcel.writeInt32(i4);
            hwParcel.writeInt32(i5);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(12, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                b.a.a.a.a aVar = new b.a.a.a.a();
                aVar.b(hwParcel2);
                return aVar;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // b.a.a.a.b
        public IHwBinder asBinder() {
            return this.f8a;
        }

        @Override // b.a.a.a.b
        public b.a.a.a.a b(ArrayList<Byte> arrayList, ArrayList<Byte> arrayList2, int i, int i2, int i3, int i4, int i5) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tee@1.0::IBoschTeeService");
            hwParcel.writeInt8Vector(arrayList);
            hwParcel.writeInt8Vector(arrayList2);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            hwParcel.writeInt32(i4);
            hwParcel.writeInt32(i5);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(13, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                b.a.a.a.a aVar = new b.a.a.a.a();
                aVar.b(hwParcel2);
                return aVar;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // b.a.a.a.b
        public b.a.a.a.a d(int i, String str, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tee@1.0::IBoschTeeService");
            hwParcel.writeInt32(i);
            hwParcel.writeString(str);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(21, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                b.a.a.a.a aVar = new b.a.a.a.a();
                aVar.b(hwParcel2);
                return aVar;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // b.a.a.a.b
        public b.a.a.a.a e(int i, int i2, int i3, int i4) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tee@1.0::IBoschTeeService");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            hwParcel.writeInt32(i4);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(5, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                b.a.a.a.a aVar = new b.a.a.a.a();
                aVar.b(hwParcel2);
                return aVar;
            } finally {
                hwParcel2.release();
            }
        }

        public final boolean equals(Object obj) {
            return HidlSupport.interfacesEqual(this, obj);
        }

        @Override // b.a.a.a.b
        public ArrayList<String> f() {
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

        @Override // b.a.a.a.b
        public b.a.a.a.a g(int i, int i2, int i3) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tee@1.0::IBoschTeeService");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(31, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                b.a.a.a.a aVar = new b.a.a.a.a();
                aVar.b(hwParcel2);
                return aVar;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // b.a.a.a.b
        public b.a.a.a.a h(int i, int i2, ArrayList<Byte> arrayList) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tee@1.0::IBoschTeeService");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt8Vector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(34, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                b.a.a.a.a aVar = new b.a.a.a.a();
                aVar.b(hwParcel2);
                return aVar;
            } finally {
                hwParcel2.release();
            }
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // b.a.a.a.b
        public b.a.a.a.a i(int i, String str, int i2, int i3) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tee@1.0::IBoschTeeService");
            hwParcel.writeInt32(i);
            hwParcel.writeString(str);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(6, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                b.a.a.a.a aVar = new b.a.a.a.a();
                aVar.b(hwParcel2);
                return aVar;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // b.a.a.a.b
        public b.a.a.a.a j(int i, ArrayList<Byte> arrayList) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tee@1.0::IBoschTeeService");
            hwParcel.writeInt32(i);
            hwParcel.writeInt8Vector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(30, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                b.a.a.a.a aVar = new b.a.a.a.a();
                aVar.b(hwParcel2);
                return aVar;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // b.a.a.a.b
        public b.a.a.a.a k(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tee@1.0::IBoschTeeService");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(14, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                b.a.a.a.a aVar = new b.a.a.a.a();
                aVar.b(hwParcel2);
                return aVar;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // b.a.a.a.b
        public b.a.a.a.a l(int i, int i2, int i3, int i4, ArrayList<Byte> arrayList) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tee@1.0::IBoschTeeService");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            hwParcel.writeInt32(i4);
            hwParcel.writeInt8Vector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(10, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                b.a.a.a.a aVar = new b.a.a.a.a();
                aVar.b(hwParcel2);
                return aVar;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // b.a.a.a.b
        public b.a.a.a.a m(String str, int i, int i2, int i3, int i4) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tee@1.0::IBoschTeeService");
            hwParcel.writeString(str);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            hwParcel.writeInt32(i4);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(25, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                b.a.a.a.a aVar = new b.a.a.a.a();
                aVar.b(hwParcel2);
                return aVar;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // b.a.a.a.b
        public b.a.a.a.a n(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tee@1.0::IBoschTeeService");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(32, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                b.a.a.a.a aVar = new b.a.a.a.a();
                aVar.b(hwParcel2);
                return aVar;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // b.a.a.a.b
        public b.a.a.a.a p(int i, String str, int i2, int i3) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tee@1.0::IBoschTeeService");
            hwParcel.writeInt32(i);
            hwParcel.writeString(str);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(23, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                b.a.a.a.a aVar = new b.a.a.a.a();
                aVar.b(hwParcel2);
                return aVar;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // b.a.a.a.b
        public b.a.a.a.a q(int i, int i2, int i3, int i4) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tee@1.0::IBoschTeeService");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            hwParcel.writeInt32(i4);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(33, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                b.a.a.a.a aVar = new b.a.a.a.a();
                aVar.b(hwParcel2);
                return aVar;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // b.a.a.a.b
        public b.a.a.a.a r(int i, int i2, int i3, int i4, ArrayList<Byte> arrayList) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tee@1.0::IBoschTeeService");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            hwParcel.writeInt32(i4);
            hwParcel.writeInt8Vector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(9, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                b.a.a.a.a aVar = new b.a.a.a.a();
                aVar.b(hwParcel2);
                return aVar;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // b.a.a.a.b
        public b.a.a.a.a s(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.tee@1.0::IBoschTeeService");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f8a.transact(22, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                b.a.a.a.a aVar = new b.a.a.a.a();
                aVar.b(hwParcel2);
                return aVar;
            } finally {
                hwParcel2.release();
            }
        }

        public String toString() {
            try {
                return u() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of vendor.bosch.tee@1.0::IBoschTeeService]@Proxy";
            }
        }

        public String u() {
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

    static b c(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface("vendor.bosch.tee@1.0::IBoschTeeService");
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof b)) {
            return (b) iHwInterfaceQueryLocalInterface;
        }
        a aVar = new a(iHwBinder);
        try {
            Iterator<String> it = aVar.f().iterator();
            while (it.hasNext()) {
                if (it.next().equals("vendor.bosch.tee@1.0::IBoschTeeService")) {
                    return aVar;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static b o(String str) {
        return c(HwBinder.getService("vendor.bosch.tee@1.0::IBoschTeeService", str));
    }

    static b t() {
        return o("default");
    }

    b.a.a.a.a a(ArrayList<Byte> arrayList, int i, int i2, int i3, int i4, int i5);

    IHwBinder asBinder();

    b.a.a.a.a b(ArrayList<Byte> arrayList, ArrayList<Byte> arrayList2, int i, int i2, int i3, int i4, int i5);

    b.a.a.a.a d(int i, String str, int i2);

    b.a.a.a.a e(int i, int i2, int i3, int i4);

    ArrayList<String> f();

    b.a.a.a.a g(int i, int i2, int i3);

    b.a.a.a.a h(int i, int i2, ArrayList<Byte> arrayList);

    b.a.a.a.a i(int i, String str, int i2, int i3);

    b.a.a.a.a j(int i, ArrayList<Byte> arrayList);

    b.a.a.a.a k(int i);

    b.a.a.a.a l(int i, int i2, int i3, int i4, ArrayList<Byte> arrayList);

    b.a.a.a.a m(String str, int i, int i2, int i3, int i4);

    b.a.a.a.a n(int i);

    b.a.a.a.a p(int i, String str, int i2, int i3);

    b.a.a.a.a q(int i, int i2, int i3, int i4);

    b.a.a.a.a r(int i, int i2, int i3, int i4, ArrayList<Byte> arrayList);

    b.a.a.a.a s(int i, int i2);
}
