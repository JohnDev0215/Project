package defpackage;

/* JADX INFO: compiled from: Node.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class xn1<K, V> {
    public abstract K a();

    public abstract V b();

    public abstract boolean c();

    public final String toString() {
        return String.format("%s=[key=%s, value=%s, weight=%d, queueType=%,d, accessTimeNS=%,d, writeTimeNS=%,d, varTimeNs=%,d, prevInAccess=%s, nextInAccess=%s, prevInWrite=%s, nextInWrite=%s]", xn1.class.getSimpleName(), a(), b(), 1, 0, 0L, 0L, 0L, false, false, false, false);
    }
}
