package c.a.a.a;

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
/* JADX INFO: loaded from: classes3.dex */
public interface i extends IHwInterface {

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class a extends HwBinder implements i {
        public final void A() {
            HwBinder.enableInstrumentation();
        }

        public final void B() {
        }

        public final void C() {
        }

        @Override // c.a.a.a.i
        public IHwBinder asBinder() {
            return this;
        }

        public final ArrayList<String> c() {
            return new ArrayList<>(Arrays.asList("vendor.bosch.diag@1.0::IDiagCallback", "android.hidl.base@1.0::IBase"));
        }

        public final boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) {
            return true;
        }

        public void onTransact(int i, HwParcel hwParcel, HwParcel hwParcel2, int i2) {
            switch (i) {
                case 1:
                    hwParcel.enforceInterface("vendor.bosch.diag@1.0::IDiagCallback");
                    int i3 = i(hwParcel.readInt16());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(i3);
                    hwParcel2.send();
                    return;
                case 2:
                    hwParcel.enforceInterface("vendor.bosch.diag@1.0::IDiagCallback");
                    f fVar = new f();
                    fVar.b(hwParcel);
                    int iQ = q(fVar);
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iQ);
                    hwParcel2.send();
                    return;
                case 3:
                    hwParcel.enforceInterface("vendor.bosch.diag@1.0::IDiagCallback");
                    c cVar = new c();
                    cVar.b(hwParcel);
                    int iN = n(cVar);
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iN);
                    hwParcel2.send();
                    return;
                case 4:
                    hwParcel.enforceInterface("vendor.bosch.diag@1.0::IDiagCallback");
                    b bVar = new b();
                    bVar.b(hwParcel);
                    t(bVar);
                    return;
                case 5:
                    hwParcel.enforceInterface("vendor.bosch.diag@1.0::IDiagCallback");
                    g gVar = new g();
                    gVar.b(hwParcel);
                    j(gVar);
                    return;
                case 6:
                    hwParcel.enforceInterface("vendor.bosch.diag@1.0::IDiagCallback");
                    c.a.a.a.a aVar = new c.a.a.a.a();
                    aVar.b(hwParcel);
                    h(aVar, hwParcel.readInt32());
                    return;
                case 7:
                    hwParcel.enforceInterface("vendor.bosch.diag@1.0::IDiagCallback");
                    p(hwParcel.readInt32());
                    return;
                case 8:
                    hwParcel.enforceInterface("vendor.bosch.diag@1.0::IDiagCallback");
                    a(hwParcel.readInt8());
                    return;
                default:
                    switch (i) {
                        case 256067662:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            ArrayList<String> arrayListC = c();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeStringVector(arrayListC);
                            hwParcel2.send();
                            return;
                        case 256131655:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            w(hwParcel.readNativeHandle(), hwParcel.readStringVector());
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 256136003:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            String strZ = z();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeString(strZ);
                            hwParcel2.send();
                            return;
                        case 256398152:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            ArrayList<byte[]> arrayListY = y();
                            hwParcel2.writeStatus(0);
                            HwBlob hwBlob = new HwBlob(16);
                            int size = arrayListY.size();
                            hwBlob.putInt32(8L, size);
                            hwBlob.putBool(12L, false);
                            HwBlob hwBlob2 = new HwBlob(size * 32);
                            for (int i4 = 0; i4 < size; i4++) {
                                long j = i4 * 32;
                                byte[] bArr = arrayListY.get(i4);
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
                            C();
                            return;
                        case 256921159:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            B();
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 257049926:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            a.a.a.a.a aVarX = x();
                            hwParcel2.writeStatus(0);
                            aVarX.b(hwParcel2);
                            hwParcel2.send();
                            return;
                        case 257120595:
                            hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                            A();
                            return;
                        default:
                            return;
                    }
            }
        }

        public IHwInterface queryLocalInterface(String str) {
            if ("vendor.bosch.diag@1.0::IDiagCallback".equals(str)) {
                return this;
            }
            return null;
        }

        public String toString() {
            return z() + "@Stub";
        }

        public final boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) {
            return true;
        }

        public void w(NativeHandle nativeHandle, ArrayList<String> arrayList) {
        }

        public final a.a.a.a.a x() {
            a.a.a.a.a aVar = new a.a.a.a.a();
            aVar.f1a = HidlSupport.getPidIfSharable();
            aVar.f2b = 0L;
            aVar.f3c = 0;
            return aVar;
        }

        public final ArrayList<byte[]> y() {
            return new ArrayList<>(Arrays.asList(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{-20, 127, -41, -98, -48, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, -54, 76}));
        }

        public final String z() {
            return "vendor.bosch.diag@1.0::IDiagCallback";
        }
    }

    void a(byte b2);

    IHwBinder asBinder();

    void h(c.a.a.a.a aVar, int i);

    int i(short s);

    void j(g gVar);

    int n(c cVar);

    void p(int i);

    int q(f fVar);

    void t(b bVar);
}
