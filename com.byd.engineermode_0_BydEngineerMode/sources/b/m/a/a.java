package b.m.a;

/* JADX INFO: compiled from: SimpleSQLiteQuery.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f1901b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Object[] f1902c;

    public a(String str, Object[] objArr) {
        this.f1901b = str;
        this.f1902c = objArr;
    }

    private static void a(d dVar, int i, Object obj) {
        if (obj == null) {
            dVar.m(i);
            return;
        }
        if (obj instanceof byte[]) {
            dVar.l(i, (byte[]) obj);
            return;
        }
        if (obj instanceof Float) {
            dVar.n(i, ((Float) obj).floatValue());
            return;
        }
        if (obj instanceof Double) {
            dVar.n(i, ((Double) obj).doubleValue());
            return;
        }
        if (obj instanceof Long) {
            dVar.g(i, ((Long) obj).longValue());
            return;
        }
        if (obj instanceof Integer) {
            dVar.g(i, ((Integer) obj).intValue());
            return;
        }
        if (obj instanceof Short) {
            dVar.g(i, ((Short) obj).shortValue());
            return;
        }
        if (obj instanceof Byte) {
            dVar.g(i, ((Byte) obj).byteValue());
            return;
        }
        if (obj instanceof String) {
            dVar.f(i, (String) obj);
            return;
        }
        if (obj instanceof Boolean) {
            dVar.g(i, ((Boolean) obj).booleanValue() ? 1L : 0L);
            return;
        }
        throw new IllegalArgumentException("Cannot bind " + obj + " at index " + i + " Supported types: null, byte[], float, double, long, int, short, byte, string");
    }

    public static void b(d dVar, Object[] objArr) {
        if (objArr == null) {
            return;
        }
        int length = objArr.length;
        int i = 0;
        while (i < length) {
            Object obj = objArr[i];
            i++;
            a(dVar, i, obj);
        }
    }

    @Override // b.m.a.e
    public String o() {
        return this.f1901b;
    }

    @Override // b.m.a.e
    public void u(d dVar) {
        b(dVar, this.f1902c);
    }

    public a(String str) {
        this(str, null);
    }
}
