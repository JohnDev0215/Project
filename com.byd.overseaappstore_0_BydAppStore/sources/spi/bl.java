package spi;

/* JADX INFO: loaded from: classes2.dex */
public final class bl {
    private bl() {
    }

    public static void a(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalStateException(String.format(str, objArr));
        }
    }
}
