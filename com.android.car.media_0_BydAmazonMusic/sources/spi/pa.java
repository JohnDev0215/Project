package spi;

/* JADX INFO: compiled from: Subscription.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pa {
    public final Object a;
    public final na b;
    public volatile boolean c = true;

    public pa(Object obj, na naVar) {
        this.a = obj;
        this.b = naVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof pa)) {
            return false;
        }
        pa paVar = (pa) obj;
        return this.a == paVar.a && this.b.equals(paVar.b);
    }

    public final int hashCode() {
        return this.b.f.hashCode() + this.a.hashCode();
    }
}
