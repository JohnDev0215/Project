package kotlinx.coroutines.channels;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class BufferOverflow {
    public static final BufferOverflow c;
    public static final BufferOverflow e;
    public static final BufferOverflow k;
    public static final /* synthetic */ BufferOverflow[] l;

    static {
        BufferOverflow bufferOverflow = new BufferOverflow("SUSPEND", 0);
        c = bufferOverflow;
        BufferOverflow bufferOverflow2 = new BufferOverflow("DROP_OLDEST", 1);
        e = bufferOverflow2;
        BufferOverflow bufferOverflow3 = new BufferOverflow("DROP_LATEST", 2);
        k = bufferOverflow3;
        l = new BufferOverflow[]{bufferOverflow, bufferOverflow2, bufferOverflow3};
    }

    public static BufferOverflow valueOf(String str) {
        return (BufferOverflow) Enum.valueOf(BufferOverflow.class, str);
    }

    public static BufferOverflow[] values() {
        return (BufferOverflow[]) l.clone();
    }
}
