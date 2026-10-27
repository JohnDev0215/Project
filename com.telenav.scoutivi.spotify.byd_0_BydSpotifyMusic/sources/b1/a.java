package b1;

/* JADX INFO: compiled from: SimpleSQLiteQuery.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f3465e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Object[] f3466g;

    public a(String str, Object[] objArr) {
        this.f3465e = str;
        this.f3466g = objArr;
    }

    private static void a(d dVar, int i7, Object obj) {
        if (obj == null) {
            dVar.C(i7);
            return;
        }
        if (obj instanceof byte[]) {
            dVar.B(i7, (byte[]) obj);
            return;
        }
        if (obj instanceof Float) {
            dVar.E(i7, ((Float) obj).floatValue());
            return;
        }
        if (obj instanceof Double) {
            dVar.E(i7, ((Double) obj).doubleValue());
            return;
        }
        if (obj instanceof Long) {
            dVar.r(i7, ((Long) obj).longValue());
            return;
        }
        if (obj instanceof Integer) {
            dVar.r(i7, ((Integer) obj).intValue());
            return;
        }
        if (obj instanceof Short) {
            dVar.r(i7, ((Short) obj).shortValue());
            return;
        }
        if (obj instanceof Byte) {
            dVar.r(i7, ((Byte) obj).byteValue());
            return;
        }
        if (obj instanceof String) {
            dVar.q(i7, (String) obj);
            return;
        }
        if (obj instanceof Boolean) {
            dVar.r(i7, ((Boolean) obj).booleanValue() ? 1L : 0L);
            return;
        }
        throw new IllegalArgumentException("Cannot bind " + obj + " at index " + i7 + " Supported types: null, byte[], float, double, long, int, short, byte, string");
    }

    public static void b(d dVar, Object[] objArr) {
        if (objArr == null) {
            return;
        }
        int length = objArr.length;
        int i7 = 0;
        while (i7 < length) {
            Object obj = objArr[i7];
            i7++;
            a(dVar, i7, obj);
        }
    }

    @Override // b1.e
    public void m(d dVar) {
        b(dVar, this.f3466g);
    }

    @Override // b1.e
    public String z() {
        return this.f3465e;
    }

    public a(String str) {
        this(str, null);
    }
}
