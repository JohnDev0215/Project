package i.a.a.a;

import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwBlob;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.NativeHandle;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: compiled from: ITboxRilCallback.java */
/* JADX INFO: loaded from: classes.dex */
public interface d extends IHwInterface {

    /* JADX INFO: compiled from: ITboxRilCallback.java */
    public static abstract class a extends HwBinder implements d {
        @Override // i.a.a.a.d
        public IHwBinder asBinder() {
            return this;
        }

        public final ArrayList<String> e() {
            return new ArrayList<>(Arrays.asList("vendor.bosch.tbox@1.0::ITboxRilCallback", "android.hidl.base@1.0::IBase"));
        }

        public final boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) {
            return true;
        }

        public void n(NativeHandle nativeHandle, ArrayList<String> arrayList) {
        }

        public final a.c.a.a.a o() {
            a.c.a.a.a aVar = new a.c.a.a.a();
            aVar.f52a = HidlSupport.getPidIfSharable();
            aVar.f53b = 0L;
            aVar.f54c = 0;
            return aVar;
        }

        public void onTransact(int i2, HwParcel hwParcel, HwParcel hwParcel2, int i3) throws RemoteException {
            switch (i2) {
                case 1:
                    hwParcel.enforceInterface("vendor.bosch.tbox@1.0::ITboxRilCallback");
                    b bVar = new b();
                    bVar.b(hwParcel);
                    i(bVar);
                    return;
                case 2:
                    hwParcel.enforceInterface("vendor.bosch.tbox@1.0::ITboxRilCallback");
                    f(hwParcel.readInt8());
                    return;
                case 3:
                    hwParcel.enforceInterface("vendor.bosch.tbox@1.0::ITboxRilCallback");
                    k(hwParcel.readInt8());
                    return;
                case 4:
                    hwParcel.enforceInterface("vendor.bosch.tbox@1.0::ITboxRilCallback");
                    g(hwParcel.readInt8());
                    return;
                case 5:
                    hwParcel.enforceInterface("vendor.bosch.tbox@1.0::ITboxRilCallback");
                    d(hwParcel.readInt8());
                    return;
                case 6:
                    hwParcel.enforceInterface("vendor.bosch.tbox@1.0::ITboxRilCallback");
                    l(hwParcel.readInt8());
                    return;
                default:
                    switch (i2) {
                        case 256067662:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            ArrayList<String> arrayListE = e();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeStringVector(arrayListE);
                            hwParcel2.send();
                            return;
                        case 256131655:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            n(hwParcel.readNativeHandle(), hwParcel.readStringVector());
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 256136003:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            String strQ = q();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeString(strQ);
                            hwParcel2.send();
                            return;
                        case 256398152:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            ArrayList<byte[]> arrayListP = p();
                            hwParcel2.writeStatus(0);
                            HwBlob hwBlob = new HwBlob(16);
                            int size = arrayListP.size();
                            hwBlob.putInt32(8L, size);
                            hwBlob.putBool(12L, false);
                            HwBlob hwBlob2 = new HwBlob(size * 32);
                            for (int i4 = 0; i4 < size; i4++) {
                                long j = i4 * 32;
                                byte[] bArr = arrayListP.get(i4);
                                if (bArr == null || bArr.length != 32) {
                                    throw new IllegalArgumentException("Array element is not of the expected length");
                                }
                                hwBlob2.putInt8Array(j, bArr);
                            }
                            hwBlob.putBlob(0L, hwBlob2);
                            hwParcel2.writeBuffer(hwBlob);
                            hwParcel2.send();
                            return;
                        case 256462420:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            t();
                            return;
                        case 256921159:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            s();
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 257049926:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            a.c.a.a.a aVarO = o();
                            hwParcel2.writeStatus(0);
                            aVarO.b(hwParcel2);
                            hwParcel2.send();
                            return;
                        case 257120595:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            r();
                            return;
                        default:
                            return;
                    }
            }
        }

        public final ArrayList<byte[]> p() {
            return new ArrayList<>(Arrays.asList(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{-20, 127, -41, -98, -48, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, -54, 76}));
        }

        public final String q() {
            return "vendor.bosch.tbox@1.0::ITboxRilCallback";
        }

        public IHwInterface queryLocalInterface(String str) {
            if ("vendor.bosch.tbox@1.0::ITboxRilCallback".equals(str)) {
                return this;
            }
            return null;
        }

        public final void r() {
            HwBinder.enableInstrumentation();
        }

        public final void s() {
        }

        public final void t() {
        }

        public String toString() {
            return q() + "@Stub";
        }

        public final boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) {
            return true;
        }
    }

    IHwBinder asBinder();

    void d(byte b2) throws RemoteException;

    void f(byte b2) throws RemoteException;

    void g(byte b2) throws RemoteException;

    void i(b bVar) throws RemoteException;

    void k(byte b2) throws RemoteException;

    void l(byte b2) throws RemoteException;
}
