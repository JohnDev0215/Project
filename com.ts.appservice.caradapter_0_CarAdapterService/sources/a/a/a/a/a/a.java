package a.a.a.a.a;

import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: compiled from: IVehicle.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IHwInterface {

    /* JADX INFO: renamed from: a.a.a.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IVehicle.java */
    public static final class C0000a implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IHwBinder f0a;

        public C0000a(IHwBinder iHwBinder) {
            Objects.requireNonNull(iHwBinder);
            this.f0a = iHwBinder;
        }

        public IHwBinder asBinder() {
            return this.f0a;
        }

        @Override // a.a.a.a.a.a
        public ArrayList<String> c() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f0a.transact(256067662, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readStringVector();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // a.a.a.a.a.a
        public String d() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f0a.transact(256136003, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // a.a.a.a.a.a
        public void e(a.a.a.a.a.b bVar, b bVar2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hardware.automotive.vehicle@2.0::IVehicle");
            bVar.d(hwParcel);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f0a.transact(3, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                int int32 = hwParcel2.readInt32();
                a.a.a.a.a.b bVar3 = new a.a.a.a.a.b();
                bVar3.b(hwParcel2);
                bVar2.a(int32, bVar3);
            } finally {
                hwParcel2.release();
            }
        }

        public final boolean equals(Object obj) {
            return HidlSupport.interfacesEqual(this, obj);
        }

        @Override // a.a.a.a.a.a
        public boolean f(IHwBinder.DeathRecipient deathRecipient) {
            return this.f0a.unlinkToDeath(deathRecipient);
        }

        @Override // a.a.a.a.a.a
        public boolean g(IHwBinder.DeathRecipient deathRecipient, long j) {
            return this.f0a.linkToDeath(deathRecipient, j);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        public String toString() {
            try {
                return d() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of android.hardware.automotive.vehicle@2.0::IVehicle]@Proxy";
            }
        }
    }

    /* JADX INFO: compiled from: IVehicle.java */
    @FunctionalInterface
    public interface b {
        void a(int i, a.a.a.a.a.b bVar);
    }

    static a a(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface("android.hardware.automotive.vehicle@2.0::IVehicle");
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof a)) {
            return (a) iHwInterfaceQueryLocalInterface;
        }
        C0000a c0000a = new C0000a(iHwBinder);
        try {
            Iterator<String> it = c0000a.c().iterator();
            while (it.hasNext()) {
                if (it.next().equals("android.hardware.automotive.vehicle@2.0::IVehicle")) {
                    return c0000a;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static a b(String str) {
        return a(HwBinder.getService("android.hardware.automotive.vehicle@2.0::IVehicle", str));
    }

    ArrayList<String> c();

    String d();

    void e(a.a.a.a.a.b bVar, b bVar2);

    boolean f(IHwBinder.DeathRecipient deathRecipient);

    boolean g(IHwBinder.DeathRecipient deathRecipient, long j);
}
