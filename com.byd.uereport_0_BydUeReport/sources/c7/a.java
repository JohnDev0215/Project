package c7;

/* JADX INFO: compiled from: ArrayUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object[] f2340a = new Object[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Class<?>[] f2341b = new Class[0];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String[] f2342c = new String[0];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final long[] f2343d = new long[0];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Long[] f2344e = new Long[0];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int[] f2345f = new int[0];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Integer[] f2346g = new Integer[0];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final short[] f2347h = new short[0];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Short[] f2348i = new Short[0];

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final byte[] f2349j = new byte[0];

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final Byte[] f2350k = new Byte[0];

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final double[] f2351l = new double[0];

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Double[] f2352m = new Double[0];

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final float[] f2353n = new float[0];

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final Float[] f2354o = new Float[0];

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final boolean[] f2355p = new boolean[0];

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final Boolean[] f2356q = new Boolean[0];

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final char[] f2357r = new char[0];

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final Character[] f2358s = new Character[0];

    public static boolean a(Object[] objArr, Object[] objArr2) {
        if (objArr == null && objArr2 != null && objArr2.length > 0) {
            return false;
        }
        if (objArr2 != null || objArr == null || objArr.length <= 0) {
            return objArr == null || objArr2 == null || objArr.length == objArr2.length;
        }
        return false;
    }
}
