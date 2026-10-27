package ts.car.diag.b;

import com.byd.mediacenter.api.media.MediaControl;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class b {
    public static byte[] d(int i) {
        return new byte[]{(byte) ((i >> 24) & MediaControl.ACTION_NONE), (byte) ((i >> 16) & MediaControl.ACTION_NONE), (byte) ((i >> 8) & MediaControl.ACTION_NONE), (byte) (i & MediaControl.ACTION_NONE)};
    }

    public static byte[] e(int i, int i2) {
        return new byte[]{(byte) ((i >> 8) & MediaControl.ACTION_NONE), (byte) (i & MediaControl.ACTION_NONE), (byte) ((i2 >> 8) & MediaControl.ACTION_NONE), (byte) (i2 & MediaControl.ACTION_NONE)};
    }

    public static byte[] f(int i) {
        return new byte[]{(byte) ((i >> 8) & MediaControl.ACTION_NONE), (byte) (i & MediaControl.ACTION_NONE)};
    }

    public int a(byte[] bArr) {
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            i += (bArr[i2] & 255) << ((3 - i2) * 8);
        }
        return i;
    }

    public int b(byte[] bArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            i2 += (bArr[i3 + i] & MediaControl.ACTION_NONE) << ((3 - i3) * 8);
        }
        return i2;
    }

    public String c(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "data is null";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b2 : bArr) {
            stringBuffer.append((int) b2);
            stringBuffer.append(" ");
        }
        return stringBuffer.toString();
    }

    public byte[] g(byte b2) {
        return new byte[]{b2};
    }
}
