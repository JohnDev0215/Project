package b.b.j;

import android.content.Context;
import android.hardware.bydauto.TsNormalNum;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.digital.BYDAutoDigitalKeyDevice;
import android.hardware.bydauto.digital.IDigitalKeyLisenter;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import androidx.exifinterface.media.ExifInterface;
import b.b.a.f.h;
import java.util.Arrays;

/* JADX INFO: compiled from: CarDigitalKeyImp.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f1935a = "CarDigitalKeyImp";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static c f1936b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f1937c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final BYDAutoDigitalKeyDevice f1938d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1939e = 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1940f = -1;
    public BYDAutoGearboxDevice g;

    /* JADX INFO: compiled from: CarDigitalKeyImp.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ byte[] f1941a;

        public a(byte[] bArr) {
            this.f1941a = bArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.f1938d.offOnlineRemoveKey98V2(this.f1941a);
            b.b.a.f.f.b(c.f1935a, "offOnlineRemoveKey98V2,HexString:" + c.B(this.f1941a), new Object[0]);
        }
    }

    public c(Context context) {
        this.f1937c = context;
        this.f1938d = BYDAutoDigitalKeyDevice.getInstance(context);
    }

    public static String B(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & ExifInterface.MARKER);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString().toUpperCase();
    }

    public static c i(Context context) {
        if (f1936b == null) {
            synchronized (c.class) {
                if (f1936b == null) {
                    f1936b = new c(context.getApplicationContext());
                }
            }
        }
        return f1936b;
    }

    public void A() {
        this.f1938d.startRegisterNFCKey();
    }

    public void C(b.b.j.a aVar) {
        this.f1938d.unregisterListener((IDigitalKeyLisenter) aVar);
        b.b.a.f.f.b(f1935a, "unRegisterListener:" + aVar, new Object[0]);
    }

    public void D(b bVar) {
        this.f1938d.unregisterTboxListener(bVar);
        b.b.a.f.f.b(f1935a, "unRegisterTboxListener:" + bVar, new Object[0]);
    }

    public void b(String str) {
        b.b.a.f.f.b(f1935a, "deleteNfcKeyByKeyNo:" + str, new Object[0]);
        this.f1938d.deleteNfcKeyByKeyNo(1, str);
        e.c(25006, 0, 20000);
    }

    public void c(int i) {
        b.b.a.f.f.b(f1935a, "deleteNfcKeyByType:" + i, new Object[0]);
        this.f1938d.deleteNfcKeyByType(i);
        e.c(25007, 0, 20000);
    }

    public void d(String str) {
        b.b.a.f.f.b(f1935a, "deleteOwnIphoneKey:" + str, new Object[0]);
        this.f1938d.deleteOwnIphoneKey(4, str);
        e.c(25005, 0, 20000);
    }

    public int e(Context context) {
        int autoType = BYDAutoBodyworkDevice.getInstance(context).getAutoType();
        b.b.a.f.f.b(f1935a, "getAutoType:" + autoType, new Object[0]);
        return autoType;
    }

    public final byte f(boolean z, byte[] bArr) {
        byte[] bArr2 = new byte[10];
        bArr2[0] = 1;
        bArr2[1] = (byte) (z ? 2 : 1);
        bArr2[2] = bArr[0];
        bArr2[3] = bArr[1];
        bArr2[4] = bArr[2];
        bArr2[5] = bArr[3];
        bArr2[6] = bArr[4];
        bArr2[7] = bArr[5];
        bArr2[8] = bArr[6];
        bArr2[9] = bArr[7];
        int i = 0;
        for (int i2 = 0; i2 < 10; i2++) {
            i += bArr2[i2];
            if (i > 256) {
                i %= 256;
            }
        }
        return (byte) i;
    }

    public int g() {
        return BYDAutoTestDevice.getInstance(this.f1937c).getCloudServiceConnectState();
    }

    public int h() {
        return this.f1938d.getIkConfigKeylessTime();
    }

    public int j() {
        int keyFindingResults = this.f1938d.getKeyFindingResults(1);
        b.b.a.f.f.b(f1935a, "getKeyFindingResults-result:" + keyFindingResults, new Object[0]);
        return keyFindingResults;
    }

    public int k() {
        this.f1939e = 1;
        return 1;
    }

    public int l() {
        b.b.a.f.f.b(f1935a, "getPadOfflineOperationResp", new Object[0]);
        return this.f1938d.getPadOfflineOperationResp();
    }

    public int m() {
        int pairingProcess = this.f1938d.getPairingProcess();
        b.b.a.f.f.b(f1935a, "getPairingProcess:" + pairingProcess, new Object[0]);
        return pairingProcess;
    }

    public int n() {
        return this.f1938d.getKeyFindingResults(0);
    }

    public int o() {
        return this.f1938d.getResponseFeedbackInform();
    }

    public boolean p(Context context) {
        int i = this.f1940f;
        if (i == -1 || i == 0) {
            this.f1940f = e(context);
        }
        switch (this.f1940f) {
            case 0:
            case 14:
            case 39:
            case 40:
            case 41:
            case 44:
            case 45:
            case 49:
            case 51:
            case 58:
            case 59:
            case 60:
            case 62:
            case 69:
            case 70:
            case 72:
            case 84:
            case 92:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 101:
            case 114:
            case 128:
            case 135:
            case 137:
            case 138:
            case 147:
            case 196:
                return true;
            default:
                return false;
        }
    }

    public boolean q(Context context) {
        if (this.g == null) {
            this.g = BYDAutoGearboxDevice.getInstance(b.b.a.a.a.b().a());
        }
        boolean zP = i(context).p(context);
        b.b.a.f.f.b("getGearboxAutoModeType", "是否是电车" + zP, new Object[0]);
        int gearboxAutoModeLevel = !zP ? this.g.getGearboxAutoModeLevel() : this.g.getGear();
        return gearboxAutoModeLevel == 1 || gearboxAutoModeLevel == 3;
    }

    public boolean r() {
        return this.f1938d.getOperationType19C33() == 1 && this.f1938d.getOperationCommand19C33() == 3 && this.f1938d.getOperationResult19C33() == 57;
    }

    public void s(boolean z, byte[] bArr) {
        b.b.a.f.f.b(f1935a, "offOnlineRemoveKey:" + Arrays.toString(bArr), new Object[0]);
        if (bArr == null || bArr.length != 8) {
            b.b.a.f.f.a(f1935a, "钥匙id数据异常:" + Arrays.toString(bArr), new Object[0]);
            return;
        }
        byte[] bArr2 = new byte[8];
        bArr2[0] = -105;
        bArr2[1] = 10;
        bArr2[2] = f(z, bArr);
        bArr2[3] = 1;
        bArr2[4] = (byte) (z ? 2 : 1);
        bArr2[5] = bArr[0];
        bArr2[6] = bArr[1];
        bArr2[7] = bArr[2];
        this.f1938d.offOnlineRemoveKey97V2(bArr2);
        b.b.a.f.f.b(f1935a, "offOnlineRemoveKey97V2,HexString:" + B(bArr2), new Object[0]);
        h.a().g(new a(new byte[]{-104, bArr[3], bArr[4], bArr[5], bArr[6], bArr[7], 0, 0}), 300L);
        e.b(25009);
        e.c(25009, 0, 10000);
    }

    public void t() {
        this.f1938d.queryKeyListReq(0, 1, 1, "", new byte[8]);
        e.b(25008);
        e.c(25008, 0, 10000);
    }

    public void u(int i) {
        byte[] bArr = new byte[8];
        bArr[0] = 54;
        bArr[1] = (byte) (i != 1 ? 10 : 8);
        this.f1938d.setQueryOwnersKeyV2(bArr);
        e.c(i == 1 ? 250000 : 250001, 0, TsNormalNum.THOUSAND_SIX);
        b.b.a.f.f.b(f1935a, "setQueryOwnersKey:op:" + i + ",HexString:" + B(bArr), new Object[0]);
    }

    public void v(b.b.j.a aVar) {
        this.f1938d.registerListener((IDigitalKeyLisenter) aVar);
        b.b.a.f.f.b(f1935a, "registerListener:" + aVar, new Object[0]);
    }

    public void w(b bVar) {
        this.f1938d.registerTboxListener(bVar);
        b.b.a.f.f.b(f1935a, "registerTboxListener:" + bVar, new Object[0]);
    }

    public void x(int i) {
        if (i == 2) {
            e.c(250002, 0, TsNormalNum.THOUSAND_SIX);
            b.b.a.f.f.b(f1935a, "更换车主钥匙寻实体钥匙请求", new Object[0]);
        } else if (i == 3) {
            e.c(250003, 0, TsNormalNum.THOUSAND_SIX);
            b.b.a.f.f.b(f1935a, "删单把钥匙寻实体钥匙请求", new Object[0]);
        } else if (i == 4) {
            e.c(250004, 0, TsNormalNum.THOUSAND_SIX);
            b.b.a.f.f.b(f1935a, "删所有钥匙寻实体钥匙请求", new Object[0]);
        }
        byte[] bArr = new byte[8];
        bArr[0] = -106;
        bArr[1] = 2;
        this.f1938d.deleteNfcFindKeyV2(bArr);
        b.b.a.f.f.b(f1935a, "removeKeyCheckOwnerKeyV2:op:" + i + ",HexString:" + B(bArr), new Object[0]);
    }

    public void y(int i) {
        byte[] bArr = new byte[8];
        bArr[0] = 54;
        bArr[1] = (byte) i;
        this.f1938d.setAllowStartTimeAfterUnlockV2(bArr);
        b.b.a.f.f.b(f1935a, "setAllowStartTimeAfterUnlockV2:value:" + i + ",HexString:" + B(bArr), new Object[0]);
    }

    public void z(int i) {
        byte[] bArr = new byte[8];
        bArr[0] = 54;
        bArr[1] = (byte) (i == 1 ? 32 : 64);
        this.f1938d.setRegisterKeyStartPairingV2(bArr);
        if (i == 1) {
            e.c(250001, 0, TsNormalNum.THOUSAND_SIX);
        }
        b.b.a.f.f.b(f1935a, "setRegisterKeyStartPairing:op:" + i + ",HexString:" + B(bArr), new Object[0]);
    }
}
