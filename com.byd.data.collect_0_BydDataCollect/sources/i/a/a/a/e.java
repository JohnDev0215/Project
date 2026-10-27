package i.a.a.a;

import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwBlob;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.NativeHandle;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: compiled from: ITboxRilCallback.java */
/* JADX INFO: loaded from: classes.dex */
public interface e extends IHwInterface {

    /* JADX INFO: compiled from: ITboxRilCallback.java */
    public static abstract class a extends HwBinder implements e {
        @Override // i.a.a.a.e
        public IHwBinder asBinder() {
            return this;
        }

        public final ArrayList<String> e() {
            return new ArrayList<>(Arrays.asList("vendor.bosch.tbox@1.0::ITboxRilCallback", "android.hidl.base@1.0::IBase"));
        }

        public final boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) {
            return true;
        }

        public void onTransact(int i2, HwParcel hwParcel, HwParcel hwParcel2, int i3) {
            switch (i2) {
                case 1:
                    hwParcel.enforceInterface("vendor.bosch.tbox@1.0::ITboxRilCallback");
                    c cVar = new c();
                    cVar.b(hwParcel);
                    k(cVar);
                    return;
                case 2:
                    hwParcel.enforceInterface("vendor.bosch.tbox@1.0::ITboxRilCallback");
                    f(hwParcel.readInt8());
                    return;
                case 3:
                    hwParcel.enforceInterface("vendor.bosch.tbox@1.0::ITboxRilCallback");
                    n(hwParcel.readInt8());
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
                    o(hwParcel.readInt8());
                    return;
                case 7:
                    hwParcel.enforceInterface("vendor.bosch.tbox@1.0::ITboxRilCallback");
                    i(hwParcel.readString());
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
                            q(hwParcel.readNativeHandle(), hwParcel.readStringVector());
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 256136003:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            String strT = t();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeString(strT);
                            hwParcel2.send();
                            return;
                        case 256398152:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            ArrayList<byte[]> arrayListS = s();
                            hwParcel2.writeStatus(0);
                            HwBlob hwBlob = new HwBlob(16);
                            int size = arrayListS.size();
                            hwBlob.putInt32(8L, size);
                            hwBlob.putBool(12L, false);
                            HwBlob hwBlob2 = new HwBlob(size * 32);
                            for (int i4 = 0; i4 < size; i4++) {
                                long j = i4 * 32;
                                byte[] bArr = arrayListS.get(i4);
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
                            w();
                            return;
                        case 256921159:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            v();
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 257049926:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            a.b.a.a.a aVarR = r();
                            hwParcel2.writeStatus(0);
                            aVarR.b(hwParcel2);
                            hwParcel2.send();
                            return;
                        case 257120595:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            u();
                            return;
                        default:
                            return;
                    }
            }
        }

        public void q(NativeHandle nativeHandle, ArrayList<String> arrayList) {
        }

        public IHwInterface queryLocalInterface(String str) {
            if ("vendor.bosch.tbox@1.0::ITboxRilCallback".equals(str)) {
                return this;
            }
            return null;
        }

        public final a.b.a.a.a r() {
            a.b.a.a.a aVar = new a.b.a.a.a();
            aVar.f39a = HidlSupport.getPidIfSharable();
            aVar.f40b = 0L;
            aVar.f41c = 0;
            return aVar;
        }

        public final ArrayList<byte[]> s() {
            return new ArrayList<>(Arrays.asList(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{-20, 127, -41, -98, -48, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, -54, 76}));
        }

        public final String t() {
            return "vendor.bosch.tbox@1.0::ITboxRilCallback";
        }

        public String toString() {
            return t() + "@Stub";
        }

        public final void u() {
            HwBinder.enableInstrumentation();
        }

        public final boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) {
            return true;
        }

        public final void v() {
        }

        public final void w() {
        }
    }

    IHwBinder asBinder();

    void d(byte b2);

    void f(byte b2);

    void g(byte b2);

    void i(String str);

    void k(c cVar);

    void n(byte b2);

    void o(byte b2);
}
