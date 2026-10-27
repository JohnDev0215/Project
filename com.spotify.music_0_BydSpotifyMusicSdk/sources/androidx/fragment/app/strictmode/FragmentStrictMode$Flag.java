package androidx.fragment.app.strictmode;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class FragmentStrictMode$Flag {
    public static final FragmentStrictMode$Flag c;
    public static final FragmentStrictMode$Flag e;
    public static final FragmentStrictMode$Flag k;
    public static final FragmentStrictMode$Flag l;
    public static final FragmentStrictMode$Flag m;
    public static final FragmentStrictMode$Flag n;
    public static final /* synthetic */ FragmentStrictMode$Flag[] o;

    static {
        FragmentStrictMode$Flag fragmentStrictMode$Flag = new FragmentStrictMode$Flag("PENALTY_LOG", 0);
        c = fragmentStrictMode$Flag;
        FragmentStrictMode$Flag fragmentStrictMode$Flag2 = new FragmentStrictMode$Flag("PENALTY_DEATH", 1);
        e = fragmentStrictMode$Flag2;
        FragmentStrictMode$Flag fragmentStrictMode$Flag3 = new FragmentStrictMode$Flag("DETECT_FRAGMENT_REUSE", 2);
        k = fragmentStrictMode$Flag3;
        FragmentStrictMode$Flag fragmentStrictMode$Flag4 = new FragmentStrictMode$Flag("DETECT_FRAGMENT_TAG_USAGE", 3);
        l = fragmentStrictMode$Flag4;
        FragmentStrictMode$Flag fragmentStrictMode$Flag5 = new FragmentStrictMode$Flag("DETECT_WRONG_NESTED_HIERARCHY", 4);
        m = fragmentStrictMode$Flag5;
        FragmentStrictMode$Flag fragmentStrictMode$Flag6 = new FragmentStrictMode$Flag("DETECT_RETAIN_INSTANCE_USAGE", 5);
        FragmentStrictMode$Flag fragmentStrictMode$Flag7 = new FragmentStrictMode$Flag("DETECT_SET_USER_VISIBLE_HINT", 6);
        FragmentStrictMode$Flag fragmentStrictMode$Flag8 = new FragmentStrictMode$Flag("DETECT_TARGET_FRAGMENT_USAGE", 7);
        FragmentStrictMode$Flag fragmentStrictMode$Flag9 = new FragmentStrictMode$Flag("DETECT_WRONG_FRAGMENT_CONTAINER", 8);
        n = fragmentStrictMode$Flag9;
        o = new FragmentStrictMode$Flag[]{fragmentStrictMode$Flag, fragmentStrictMode$Flag2, fragmentStrictMode$Flag3, fragmentStrictMode$Flag4, fragmentStrictMode$Flag5, fragmentStrictMode$Flag6, fragmentStrictMode$Flag7, fragmentStrictMode$Flag8, fragmentStrictMode$Flag9};
    }

    public static FragmentStrictMode$Flag valueOf(String str) {
        return (FragmentStrictMode$Flag) Enum.valueOf(FragmentStrictMode$Flag.class, str);
    }

    public static FragmentStrictMode$Flag[] values() {
        return (FragmentStrictMode$Flag[]) o.clone();
    }
}
