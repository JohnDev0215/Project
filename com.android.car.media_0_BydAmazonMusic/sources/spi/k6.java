package spi;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: LongSerializationPolicy.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class k6 {
    public static final a a;
    public static final b b;
    public static final /* synthetic */ k6[] c;

    /* JADX INFO: compiled from: LongSerializationPolicy.java */
    public enum a extends k6 {
        public a(String str, int i) {
            super(str, i, null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [spi.k6$b] */
    static {
        a aVar = new a("DEFAULT", 0);
        a = aVar;
        ?? r1 = new k6("STRING", 1) { // from class: spi.k6.b
            {
                a aVar2 = null;
            }
        };
        b = r1;
        c = new k6[]{aVar, r1};
    }

    private k6(String str, int i) {
    }

    public static k6 valueOf(String str) {
        return (k6) Enum.valueOf(k6.class, str);
    }

    public static k6[] values() {
        return (k6[]) c.clone();
    }

    public /* synthetic */ k6(String str, int i, a aVar) {
        this(str, i);
    }
}
