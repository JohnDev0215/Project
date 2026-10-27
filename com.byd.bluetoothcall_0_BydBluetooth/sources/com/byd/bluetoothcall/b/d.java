package com.byd.bluetoothcall.b;

/* JADX INFO: compiled from: T9Search_Card.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements Comparable<d> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3242b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f3243c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f3244d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private long f3245e;

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(d dVar) {
        long jH = dVar.h() - h();
        if (jH > 0) {
            return 1;
        }
        return jH == 0 ? 0 : -1;
    }

    public String d() {
        return this.f3244d;
    }

    public String e() {
        return this.f3242b;
    }

    public boolean equals(Object obj) {
        if (obj instanceof d) {
            d dVar = (d) obj;
            if (e().equals(dVar.e()) && g().equals(dVar.g())) {
                return true;
            }
        }
        return false;
    }

    public String g() {
        return this.f3243c;
    }

    public long h() {
        return this.f3245e;
    }

    public void i(String str) {
        this.f3244d = str;
    }

    public void j(String str) {
        this.f3242b = str;
    }

    public void k(String str) {
        this.f3243c = str;
    }

    public void l(long j) {
        this.f3245e = j;
    }

    public String toString() {
        return "T9Search_Card{name='" + this.f3242b + "', number='" + this.f3243c + "', keyword='" + this.f3244d + "', time=" + this.f3245e + '}';
    }
}
