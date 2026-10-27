package c.a.a.d.i;

import c.a.a.g.e;

/* JADX INFO: compiled from: MutableInt.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends Number implements Comparable<a> {
    private static final long serialVersionUID = 1;
    private int value;

    public a() {
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(a aVar) {
        return e.a(this.value, aVar.value);
    }

    public a b() {
        this.value++;
        return this;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        return (obj instanceof a) && this.value == ((a) obj).intValue();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value;
    }

    @Override // java.lang.Number
    public int intValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.value;
    }

    public String toString() {
        return String.valueOf(this.value);
    }

    public a(int i2) {
        this.value = i2;
    }
}
