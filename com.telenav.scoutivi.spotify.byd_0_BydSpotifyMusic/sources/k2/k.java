package k2;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Looper;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import u1.l;

/* JADX INFO: compiled from: Util.java */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final char[] f7258a = "0123456789abcdef".toCharArray();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final char[] f7259b = new char[64];

    /* JADX INFO: compiled from: Util.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f7260a;

        static {
            int[] iArr = new int[Bitmap.Config.values().length];
            f7260a = iArr;
            try {
                iArr[Bitmap.Config.ALPHA_8.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7260a[Bitmap.Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7260a[Bitmap.Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7260a[Bitmap.Config.RGBA_F16.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7260a[Bitmap.Config.ARGB_8888.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static void a() {
        if (!q()) {
            throw new IllegalArgumentException("You must call this method on the main thread");
        }
    }

    public static boolean b(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj instanceof l ? ((l) obj).a(obj2) : obj.equals(obj2);
    }

    public static boolean c(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    private static String d(byte[] bArr, char[] cArr) {
        for (int i7 = 0; i7 < bArr.length; i7++) {
            int i8 = bArr[i7] & 255;
            int i9 = i7 * 2;
            char[] cArr2 = f7258a;
            cArr[i9] = cArr2[i8 >>> 4];
            cArr[i9 + 1] = cArr2[i8 & 15];
        }
        return new String(cArr);
    }

    public static <T> Queue<T> e(int i7) {
        return new ArrayDeque(i7);
    }

    public static int f(int i7, int i8, Bitmap.Config config) {
        return i7 * i8 * h(config);
    }

    @TargetApi(19)
    public static int g(Bitmap bitmap) {
        if (!bitmap.isRecycled()) {
            try {
                return bitmap.getAllocationByteCount();
            } catch (NullPointerException unused) {
                return bitmap.getHeight() * bitmap.getRowBytes();
            }
        }
        throw new IllegalStateException("Cannot obtain size for recycled Bitmap: " + bitmap + "[" + bitmap.getWidth() + "x" + bitmap.getHeight() + "] " + bitmap.getConfig());
    }

    private static int h(Bitmap.Config config) {
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        int i7 = a.f7260a[config.ordinal()];
        if (i7 == 1) {
            return 1;
        }
        if (i7 == 2 || i7 == 3) {
            return 2;
        }
        return i7 != 4 ? 4 : 8;
    }

    public static <T> List<T> i(Collection<T> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (T t6 : collection) {
            if (t6 != null) {
                arrayList.add(t6);
            }
        }
        return arrayList;
    }

    public static int j(float f7) {
        return k(f7, 17);
    }

    public static int k(float f7, int i7) {
        return m(Float.floatToIntBits(f7), i7);
    }

    public static int l(int i7) {
        return m(i7, 17);
    }

    public static int m(int i7, int i8) {
        return (i8 * 31) + i7;
    }

    public static int n(Object obj, int i7) {
        return m(obj == null ? 0 : obj.hashCode(), i7);
    }

    public static int o(boolean z6, int i7) {
        return m(z6 ? 1 : 0, i7);
    }

    public static boolean p() {
        return !q();
    }

    public static boolean q() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    private static boolean r(int i7) {
        return i7 > 0 || i7 == Integer.MIN_VALUE;
    }

    public static boolean s(int i7, int i8) {
        return r(i7) && r(i8);
    }

    public static String t(byte[] bArr) {
        String strD;
        char[] cArr = f7259b;
        synchronized (cArr) {
            strD = d(bArr, cArr);
        }
        return strD;
    }
}
