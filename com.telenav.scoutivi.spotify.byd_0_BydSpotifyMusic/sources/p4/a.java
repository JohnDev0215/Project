package p4;

import java.util.LinkedHashMap;

/* JADX INFO: compiled from: DaggerCollections.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    private static int a(int i7) {
        if (i7 < 3) {
            return i7 + 1;
        }
        if (i7 < 1073741824) {
            return (int) ((i7 / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static <K, V> LinkedHashMap<K, V> b(int i7) {
        return new LinkedHashMap<>(a(i7));
    }
}
