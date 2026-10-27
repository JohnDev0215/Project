package b.a.b.a;

import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwBlob;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.NativeHandle;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface e extends IHwInterface {

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class a extends HwBinder implements e {
        public final void A() {
        }

        public final void B() {
        }

        @Override // b.a.b.a.e
        public IHwBinder asBinder() {
            return this;
        }

        public final ArrayList<String> b() {
            return new ArrayList<>(Arrays.asList("vendor.bosch.log@1.0::ILogCallback", "android.hidl.base@1.0::IBase"));
        }

        public final boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) {
            return true;
        }

        public void onTransact(int i, HwParcel hwParcel, HwParcel hwParcel2, int i2) {
            switch (i) {
                case 1:
                    hwParcel.enforceInterface("vendor.bosch.log@1.0::ILogCallback");
                    m(g.b(hwParcel));
                    hwParcel2.writeStatus(0);
                    hwParcel2.send();
                    return;
                case 2:
                    hwParcel.enforceInterface("vendor.bosch.log@1.0::ILogCallback");
                    f(hwParcel.readBool());
                    hwParcel2.writeStatus(0);
                    hwParcel2.send();
                    return;
                case 3:
                    hwParcel.enforceInterface("vendor.bosch.log@1.0::ILogCallback");
                    g(hwParcel.readString());
                    hwParcel2.writeStatus(0);
                    hwParcel2.send();
                    return;
                case 4:
                    hwParcel.enforceInterface("vendor.bosch.log@1.0::ILogCallback");
                    j(hwParcel.readString());
                    hwParcel2.writeStatus(0);
                    hwParcel2.send();
                    return;
                case 5:
                    hwParcel.enforceInterface("vendor.bosch.log@1.0::ILogCallback");
                    l(hwParcel.readBool());
                    hwParcel2.writeStatus(0);
                    hwParcel2.send();
                    return;
                case 6:
                    hwParcel.enforceInterface("vendor.bosch.log@1.0::ILogCallback");
                    r(hwParcel.readBool());
                    hwParcel2.writeStatus(0);
                    hwParcel2.send();
                    return;
                case 7:
                    hwParcel.enforceInterface("vendor.bosch.log@1.0::ILogCallback");
                    String string = hwParcel.readString();
                    b bVar = new b();
                    bVar.b(hwParcel);
                    o(string, bVar);
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
                    p(hwParcel.readBool());
                    hwParcel2.writeStatus(0);
                    hwParcel2.send();
                    return;
                case 10:
                    hwParcel.enforceInterface("vendor.bosch.log@1.0::ILogCallback");
                    t(hwParcel.readBool());
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
                            v(hwParcel.readNativeHandle(), hwParcel.readStringVector());
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 256136003:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            String strY = y();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeString(strY);
                            hwParcel2.send();
                            return;
                        case 256398152:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            ArrayList<byte[]> arrayListX = x();
                            hwParcel2.writeStatus(0);
                            HwBlob hwBlob = new HwBlob(16);
                            int size = arrayListX.size();
                            hwBlob.putInt32(8L, size);
                            hwBlob.putBool(12L, false);
                            HwBlob hwBlob2 = new HwBlob(size * 32);
                            for (int i3 = 0; i3 < size; i3++) {
                                long j = i3 * 32;
                                byte[] bArr = arrayListX.get(i3);
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
                            B();
                            return;
                        case 256921159:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            A();
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 257049926:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            a.a.a.a.a aVarW = w();
                            hwParcel2.writeStatus(0);
                            aVarW.b(hwParcel2);
                            hwParcel2.send();
                            return;
                        case 257120595:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            z();
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
            return y() + "@Stub";
        }

        public final boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) {
            return true;
        }

        public void v(NativeHandle nativeHandle, ArrayList<String> arrayList) {
        }

        public final a.a.a.a.a w() {
            a.a.a.a.a aVar = new a.a.a.a.a();
            aVar.f0a = HidlSupport.getPidIfSharable();
            aVar.f1b = 0L;
            aVar.c = 0;
            return aVar;
        }

        public final ArrayList<byte[]> x() {
            return new ArrayList<>(Arrays.asList(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{-20, 127, -41, -98, -48, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, -54, 76}));
        }

        public final String y() {
            return "vendor.bosch.log@1.0::ILogCallback";
        }

        public final void z() {
            HwBinder.enableInstrumentation();
        }
    }

    IHwBinder asBinder();

    void e();

    void f(boolean z);

    void g(String str);

    void j(String str);

    void l(boolean z);

    void m(ArrayList<g> arrayList);

    void o(String str, b bVar);

    void p(boolean z);

    void r(boolean z);

    void t(boolean z);
}
