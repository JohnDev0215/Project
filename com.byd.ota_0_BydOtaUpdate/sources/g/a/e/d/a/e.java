package g.a.e.d.a;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final BigInteger f5704a = BigInteger.valueOf(0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final BigInteger f5705b = BigInteger.valueOf(1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final BigInteger f5706c = BigInteger.valueOf(2);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final BigInteger f5707d = BigInteger.valueOf(4);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f5708e = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static SecureRandom f5709f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int[] f5710g = {0, 1, 0, -1, 0, -1, 0, 1};

    public static int a(int i2) {
        if (i2 == 0) {
            return 1;
        }
        if (i2 < 0) {
            i2 = -i2;
        }
        int i3 = 0;
        while (i2 > 0) {
            i3++;
            i2 >>>= 8;
        }
        return i3;
    }
}
