package spi;

/* JADX INFO: compiled from: JsonObject.java */
/* JADX INFO: loaded from: classes2.dex */
public final class l5 extends i5 {
    public final y5<String, i5> a = new y5<>();

    public final boolean equals(Object obj) {
        return obj == this || ((obj instanceof l5) && ((l5) obj).a.equals(this.a));
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
