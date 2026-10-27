package kotlinx.coroutines;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class CoroutineStart {
    public static final CoroutineStart c;
    public static final CoroutineStart e;
    public static final CoroutineStart k;
    public static final CoroutineStart l;
    public static final /* synthetic */ CoroutineStart[] m;

    static {
        CoroutineStart coroutineStart = new CoroutineStart("DEFAULT", 0);
        c = coroutineStart;
        CoroutineStart coroutineStart2 = new CoroutineStart("LAZY", 1);
        e = coroutineStart2;
        CoroutineStart coroutineStart3 = new CoroutineStart("ATOMIC", 2);
        k = coroutineStart3;
        CoroutineStart coroutineStart4 = new CoroutineStart("UNDISPATCHED", 3);
        l = coroutineStart4;
        m = new CoroutineStart[]{coroutineStart, coroutineStart2, coroutineStart3, coroutineStart4};
    }

    public static CoroutineStart valueOf(String str) {
        return (CoroutineStart) Enum.valueOf(CoroutineStart.class, str);
    }

    public static CoroutineStart[] values() {
        return (CoroutineStart[]) m.clone();
    }
}
