package kotlinx.coroutines.channels;

/* JADX INFO: compiled from: BroadcastChannel.kt */
/* JADX INFO: loaded from: classes.dex */
public final class h {
    public static final <E> g<E> a(int i7) {
        if (i7 == -2) {
            return new d(l.f7574f.a());
        }
        if (i7 == -1) {
            return new u();
        }
        if (i7 == 0) {
            throw new IllegalArgumentException("Unsupported 0 capacity for BroadcastChannel");
        }
        if (i7 != Integer.MAX_VALUE) {
            return new d(i7);
        }
        throw new IllegalArgumentException("Unsupported UNLIMITED capacity for BroadcastChannel");
    }
}
