package c.a.a.a;

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
/* JADX INFO: loaded from: classes3.dex */
public interface h extends IHwInterface {

    /* JADX INFO: compiled from: caradapter-bug */
    public static final class a implements h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IHwBinder f1296a;

        public a(IHwBinder iHwBinder) {
            Objects.requireNonNull(iHwBinder);
            this.f1296a = iHwBinder;
        }

        @Override // c.a.a.a.h
        public IHwBinder asBinder() {
            return this.f1296a;
        }

        @Override // c.a.a.a.h
        public ArrayList<String> c() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f1296a.transact(256067662, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readStringVector();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // c.a.a.a.h
        public int d(i iVar) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.diag@1.0::IDiag");
            hwParcel.writeStrongBinder(iVar == null ? null : iVar.asBinder());
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f1296a.transact(8, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // c.a.a.a.h
        public int e(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.diag@1.0::IDiag");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f1296a.transact(3, hwParcel, hwParcel2, 0);
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

        @Override // c.a.a.a.h
        public void f(b bVar) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.diag@1.0::IDiag");
            bVar.d(hwParcel);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f1296a.transact(9, hwParcel, hwParcel2, 1);
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // c.a.a.a.h
        public int g(i iVar, short s) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.diag@1.0::IDiag");
            hwParcel.writeStrongBinder(iVar == null ? null : iVar.asBinder());
            hwParcel.writeInt16(s);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f1296a.transact(6, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // c.a.a.a.h
        public void k(g gVar) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.diag@1.0::IDiag");
            gVar.d(hwParcel);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f1296a.transact(10, hwParcel, hwParcel2, 1);
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // c.a.a.a.h
        public int l(i iVar, f fVar) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.diag@1.0::IDiag");
            hwParcel.writeStrongBinder(iVar == null ? null : iVar.asBinder());
            fVar.d(hwParcel);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f1296a.transact(7, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // c.a.a.a.h
        public int m(i iVar) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.diag@1.0::IDiag");
            hwParcel.writeStrongBinder(iVar == null ? null : iVar.asBinder());
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f1296a.transact(2, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // c.a.a.a.h
        public int o(i iVar, byte b2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.diag@1.0::IDiag");
            hwParcel.writeStrongBinder(iVar == null ? null : iVar.asBinder());
            hwParcel.writeInt8(b2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f1296a.transact(4, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // c.a.a.a.h
        public void s(d dVar) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.diag@1.0::IDiag");
            dVar.b(hwParcel);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f1296a.transact(11, hwParcel, hwParcel2, 1);
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        public String toString() {
            try {
                return w() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of vendor.bosch.diag@1.0::IDiag]@Proxy";
            }
        }

        @Override // c.a.a.a.h
        public int v(i iVar, ArrayList<Short> arrayList, ArrayList<Short> arrayList2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.bosch.diag@1.0::IDiag");
            hwParcel.writeStrongBinder(iVar == null ? null : iVar.asBinder());
            hwParcel.writeInt16Vector(arrayList);
            hwParcel.writeInt16Vector(arrayList2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f1296a.transact(1, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        public String w() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f1296a.transact(256136003, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }
    }

    static h b(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface("vendor.bosch.diag@1.0::IDiag");
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof h)) {
            return (h) iHwInterfaceQueryLocalInterface;
        }
        a aVar = new a(iHwBinder);
        try {
            Iterator<String> it = aVar.c().iterator();
            while (it.hasNext()) {
                if (it.next().equals("vendor.bosch.diag@1.0::IDiag")) {
                    return aVar;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static h r(String str) {
        return b(HwBinder.getService("vendor.bosch.diag@1.0::IDiag", str));
    }

    static h u() {
        return r("default");
    }

    IHwBinder asBinder();

    ArrayList<String> c();

    int d(i iVar);

    int e(int i, int i2);

    void f(b bVar);

    int g(i iVar, short s);

    void k(g gVar);

    int l(i iVar, f fVar);

    int m(i iVar);

    int o(i iVar, byte b2);

    void s(d dVar);

    int v(i iVar, ArrayList<Short> arrayList, ArrayList<Short> arrayList2);
}
