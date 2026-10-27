package h1;

/* JADX INFO: compiled from: SimpleSQLiteQuery.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f5120d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object[] f5121e;

    public a(String str, Object[] objArr) {
        this.f5120d = str;
        this.f5121e = objArr;
    }

    public static void a(d dVar, int i8, Object obj) {
        if (obj == null) {
            dVar.z(i8);
            return;
        }
        if (obj instanceof byte[]) {
            dVar.b0(i8, (byte[]) obj);
            return;
        }
        if (obj instanceof Float) {
            dVar.A(i8, ((Float) obj).floatValue());
            return;
        }
        if (obj instanceof Double) {
            dVar.A(i8, ((Double) obj).doubleValue());
            return;
        }
        if (obj instanceof Long) {
            dVar.U(i8, ((Long) obj).longValue());
            return;
        }
        if (obj instanceof Integer) {
            dVar.U(i8, ((Integer) obj).intValue());
            return;
        }
        if (obj instanceof Short) {
            dVar.U(i8, ((Short) obj).shortValue());
            return;
        }
        if (obj instanceof Byte) {
            dVar.U(i8, ((Byte) obj).byteValue());
            return;
        }
        if (obj instanceof String) {
            dVar.l(i8, (String) obj);
            return;
        }
        if (obj instanceof Boolean) {
            dVar.U(i8, ((Boolean) obj).booleanValue() ? 1L : 0L);
            return;
        }
        throw new IllegalArgumentException("Cannot bind " + obj + " at index " + i8 + " Supported types: null, byte[], float, double, long, int, short, byte, string");
    }

    public static void b(d dVar, Object[] objArr) {
        if (objArr == null) {
            return;
        }
        int length = objArr.length;
        int i8 = 0;
        while (i8 < length) {
            Object obj = objArr[i8];
            i8++;
            a(dVar, i8, obj);
        }
    }

    @Override // h1.e
    public String e() {
        return this.f5120d;
    }

    @Override // h1.e
    public void g(d dVar) {
        b(dVar, this.f5121e);
    }

    public a(String str) {
        this(str, null);
    }
}
