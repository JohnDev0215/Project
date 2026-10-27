package kotlin.coroutines.intrinsics;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class CoroutineSingletons {
    public static final CoroutineSingletons c;
    public static final /* synthetic */ CoroutineSingletons[] e;

    static {
        CoroutineSingletons coroutineSingletons = new CoroutineSingletons("COROUTINE_SUSPENDED", 0);
        c = coroutineSingletons;
        e = new CoroutineSingletons[]{coroutineSingletons, new CoroutineSingletons("UNDECIDED", 1), new CoroutineSingletons("RESUMED", 2)};
    }

    public static CoroutineSingletons valueOf(String str) {
        return (CoroutineSingletons) Enum.valueOf(CoroutineSingletons.class, str);
    }

    public static CoroutineSingletons[] values() {
        return (CoroutineSingletons[]) e.clone();
    }
}
