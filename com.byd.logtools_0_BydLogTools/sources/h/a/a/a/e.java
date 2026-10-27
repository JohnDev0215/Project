package h.a.a.a;

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

/* JADX INFO: compiled from: ILogCallback.java */
/* JADX INFO: loaded from: classes.dex */
public interface e extends IHwInterface {

    /* JADX INFO: compiled from: ILogCallback.java */
    public static abstract class a extends HwBinder implements e {
        public void D(NativeHandle nativeHandle, ArrayList<String> arrayList) {
        }

        public final a.b.a.a.a E() {
            a.b.a.a.a aVar = new a.b.a.a.a();
            aVar.f39a = HidlSupport.getPidIfSharable();
            aVar.f40b = 0L;
            aVar.f41c = 0;
            return aVar;
        }

        public final ArrayList<byte[]> F() {
            return new ArrayList<>(Arrays.asList(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{-20, 127, -41, -98, -48, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, -54, 76}));
        }

        public final String G() {
            return "vendor.bosch.log@1.0::ILogCallback";
        }

        public final void H() {
            HwBinder.enableInstrumentation();
        }

        public final void I() {
        }

        public final void J() {
        }

        public final ArrayList<String> b() {
            return new ArrayList<>(Arrays.asList("vendor.bosch.log@1.0::ILogCallback", "android.hidl.base@1.0::IBase"));
        }

        public final boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) {
            return true;
        }

        public void onTransact(int i, HwParcel hwParcel, HwParcel hwParcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    hwParcel.enforceInterface("vendor.bosch.log@1.0::ILogCallback");
                    t(g.b(hwParcel));
                    hwParcel2.writeStatus(0);
                    hwParcel2.send();
                    return;
                case 2:
                    hwParcel.enforceInterface("vendor.bosch.log@1.0::ILogCallback");
                    w(hwParcel.readBool());
                    hwParcel2.writeStatus(0);
                    hwParcel2.send();
                    return;
                case 3:
                    hwParcel.enforceInterface("vendor.bosch.log@1.0::ILogCallback");
                    o(hwParcel.readString());
                    hwParcel2.writeStatus(0);
                    hwParcel2.send();
                    return;
                case 4:
                    hwParcel.enforceInterface("vendor.bosch.log@1.0::ILogCallback");
                    s(hwParcel.readString());
                    hwParcel2.writeStatus(0);
                    hwParcel2.send();
                    return;
                case 5:
                    hwParcel.enforceInterface("vendor.bosch.log@1.0::ILogCallback");
                    h(hwParcel.readBool());
                    hwParcel2.writeStatus(0);
                    hwParcel2.send();
                    return;
                case 6:
                    hwParcel.enforceInterface("vendor.bosch.log@1.0::ILogCallback");
                    u(hwParcel.readBool());
                    hwParcel2.writeStatus(0);
                    hwParcel2.send();
                    return;
                case 7:
                    hwParcel.enforceInterface("vendor.bosch.log@1.0::ILogCallback");
                    String string = hwParcel.readString();
                    b bVar = new b();
                    bVar.b(hwParcel);
                    j(string, bVar);
                    hwParcel2.writeStatus(0);
                    hwParcel2.send();
                    return;
                case 8:
                    hwParcel.enforceInterface("vendor.bosch.log@1.0::ILogCallback");
                    e();
                    hwParcel2.writeStatus(0);
                    hwParcel2.send();
                    return;
                case 9:
                    hwParcel.enforceInterface("vendor.bosch.log@1.0::ILogCallback");
                    k(hwParcel.readBool());
                    hwParcel2.writeStatus(0);
                    hwParcel2.send();
                    return;
                case 10:
                    hwParcel.enforceInterface("vendor.bosch.log@1.0::ILogCallback");
                    n(hwParcel.readBool());
                    hwParcel2.writeStatus(0);
                    hwParcel2.send();
                    return;
                default:
                    switch (i) {
                        case 256067662:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            ArrayList<String> arrayListB = b();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeStringVector(arrayListB);
                            hwParcel2.send();
                            return;
                        case 256131655:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            D(hwParcel.readNativeHandle(), hwParcel.readStringVector());
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 256136003:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            String strG = G();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeString(strG);
                            hwParcel2.send();
                            return;
                        case 256398152:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            ArrayList<byte[]> arrayListF = F();
                            hwParcel2.writeStatus(0);
                            HwBlob hwBlob = new HwBlob(16);
                            int size = arrayListF.size();
                            hwBlob.putInt32(8L, size);
                            hwBlob.putBool(12L, false);
                            HwBlob hwBlob2 = new HwBlob(size * 32);
                            for (int i3 = 0; i3 < size; i3++) {
                                long j = i3 * 32;
                                byte[] bArr = arrayListF.get(i3);
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
                            J();
                            return;
                        case 256921159:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            I();
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 257049926:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            a.b.a.a.a aVarE = E();
                            hwParcel2.writeStatus(0);
                            aVarE.b(hwParcel2);
                            hwParcel2.send();
                            return;
                        case 257120595:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            H();
                            return;
                        default:
                            return;
                    }
            }
        }

        public IHwInterface queryLocalInterface(String str) {
            if ("vendor.bosch.log@1.0::ILogCallback".equals(str)) {
                return this;
            }
            return null;
        }

        public String toString() {
            return G() + "@Stub";
        }

        public final boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) {
            return true;
        }
    }

    IHwBinder asBinder();

    void e() throws RemoteException;

    void h(boolean z) throws RemoteException;

    void j(String str, b bVar) throws RemoteException;

    void k(boolean z) throws RemoteException;

    void n(boolean z) throws RemoteException;

    void o(String str) throws RemoteException;

    void s(String str) throws RemoteException;

    void t(ArrayList<g> arrayList) throws RemoteException;

    void u(boolean z) throws RemoteException;

    void w(boolean z) throws RemoteException;
}
