package com.google.zxing.n.r;

/* JADX INFO: compiled from: DataCharacter.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f4363a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f4364b;

    public b(int i, int i2) {
        this.f4363a = i;
        this.f4364b = i2;
    }

    public final int a() {
        return this.f4364b;
    }

    public final int b() {
        return this.f4363a;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f4363a == bVar.f4363a && this.f4364b == bVar.f4364b;
    }

    public final int hashCode() {
        return this.f4363a ^ this.f4364b;
    }

    public final String toString() {
        return this.f4363a + "(" + this.f4364b + ')';
    }
}
