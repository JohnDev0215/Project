package m7;

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
/* JADX INFO: loaded from: classes2.dex */
public interface d extends IHwInterface {

    /* JADX INFO: compiled from: ITboxRilCallback.java */
    public static abstract class a extends HwBinder implements d {
        @Override // m7.d
        public IHwBinder asBinder() {
            return this;
        }

        public final ArrayList<String> i() {
            return new ArrayList<>(Arrays.asList("vendor.bosch.tbox@1.0::ITboxRilCallback", "android.hidl.base@1.0::IBase"));
        }

        public final boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j8) {
            return true;
        }

        public void o(NativeHandle nativeHandle, ArrayList<String> arrayList) {
        }

        public void onTransact(int i8, HwParcel hwParcel, HwParcel hwParcel2, int i9) throws RemoteException {
            switch (i8) {
                case 1:
                    hwParcel.enforceInterface("vendor.bosch.tbox@1.0::ITboxRilCallback");
                    b bVar = new b();
                    bVar.b(hwParcel);
                    m(bVar);
                    return;
                case 2:
                    hwParcel.enforceInterface("vendor.bosch.tbox@1.0::ITboxRilCallback");
                    j(hwParcel.readInt8());
                    return;
                case 3:
                    hwParcel.enforceInterface("vendor.bosch.tbox@1.0::ITboxRilCallback");
                    f(hwParcel.readInt8());
                    return;
                case 4:
                    hwParcel.enforceInterface("vendor.bosch.tbox@1.0::ITboxRilCallback");
                    d(hwParcel.readInt8());
                    return;
                case 5:
                    hwParcel.enforceInterface("vendor.bosch.tbox@1.0::ITboxRilCallback");
                    c(hwParcel.readInt8());
                    return;
                case 6:
                    hwParcel.enforceInterface("vendor.bosch.tbox@1.0::ITboxRilCallback");
                    g(hwParcel.readInt8());
                    return;
                case 7:
                    hwParcel.enforceInterface("vendor.bosch.tbox@1.0::ITboxRilCallback");
                    l(hwParcel.readString());
                    return;
                default:
                    switch (i8) {
                        case 256067662:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            ArrayList<String> arrayListI = i();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeStringVector(arrayListI);
                            hwParcel2.send();
                            return;
                        case 256131655:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            o(hwParcel.readNativeHandle(), hwParcel.readStringVector());
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 256136003:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            String strR = r();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeString(strR);
                            hwParcel2.send();
                            return;
                        case 256398152:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            ArrayList<byte[]> arrayListQ = q();
                            hwParcel2.writeStatus(0);
                            HwBlob hwBlob = new HwBlob(16);
                            int size = arrayListQ.size();
                            hwBlob.putInt32(8L, size);
                            hwBlob.putBool(12L, false);
                            HwBlob hwBlob2 = new HwBlob(size * 32);
                            for (int i10 = 0; i10 < size; i10++) {
                                long j8 = i10 * 32;
                                byte[] bArr = arrayListQ.get(i10);
                                if (bArr == null || bArr.length != 32) {
                                    throw new IllegalArgumentException("Array element is not of the expected length");
                                }
                                hwBlob2.putInt8Array(j8, bArr);
                            }
                            hwBlob.putBlob(0L, hwBlob2);
                            hwParcel2.writeBuffer(hwBlob);
                            hwParcel2.send();
                            return;
                        case 256462420:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            u();
                            return;
                        case 256921159:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            t();
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 257049926:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            f.a aVarP = p();
                            hwParcel2.writeStatus(0);
                            aVarP.b(hwParcel2);
                            hwParcel2.send();
                            return;
                        case 257120595:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            s();
                            return;
                        default:
                            return;
                    }
            }
        }

        public final f.a p() {
            f.a aVar = new f.a();
            aVar.f4802a = HidlSupport.getPidIfSharable();
            aVar.f4803b = 0L;
            aVar.f4804c = 0;
            return aVar;
        }

        public final ArrayList<byte[]> q() {
            return new ArrayList<>(Arrays.asList(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{-20, 127, -41, -98, -48, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, -54, 76}));
        }

        public IHwInterface queryLocalInterface(String str) {
            if ("vendor.bosch.tbox@1.0::ITboxRilCallback".equals(str)) {
                return this;
            }
            return null;
        }

        public final String r() {
            return "vendor.bosch.tbox@1.0::ITboxRilCallback";
        }

        public final void s() {
            HwBinder.enableInstrumentation();
        }

        public final void t() {
        }

        public String toString() {
            return r() + "@Stub";
        }

        public final void u() {
        }

        public final boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) {
            return true;
        }
    }

    IHwBinder asBinder();

    void c(byte b8) throws RemoteException;

    void d(byte b8) throws RemoteException;

    void f(byte b8) throws RemoteException;

    void g(byte b8) throws RemoteException;

    void j(byte b8) throws RemoteException;

    void l(String str) throws RemoteException;

    void m(b bVar) throws RemoteException;
}
