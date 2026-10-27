package defpackage;

/* JADX INFO: compiled from: Subscription.java */
/* JADX INFO: loaded from: classes.dex */
public final class om {
    public final Object a;
    public final mm b;
    public volatile boolean c = true;

    public om(Object obj, mm mmVar) {
        this.a = obj;
        this.b = mmVar;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof om)) {
            return false;
        }
        om omVar = (om) obj;
        return this.a == omVar.a && this.b.equals(omVar.b);
    }

    public int hashCode() {
        return this.a.hashCode() + this.b.f.hashCode();
    }
}
