package defpackage;

/* JADX INFO: compiled from: SatelliteInfo.java */
/* JADX INFO: loaded from: classes.dex */
public class tf implements Comparable<tf> {
    public int b;
    public Float c;
    public String d;

    public tf(String str, int i, Float f) {
        this.c = f;
        this.d = str;
        this.b = i;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(tf tfVar) {
        if (this.c.floatValue() < tfVar.c.floatValue()) {
            return 1;
        }
        return this.c.floatValue() > tfVar.c.floatValue() ? -1 : 0;
    }
}
