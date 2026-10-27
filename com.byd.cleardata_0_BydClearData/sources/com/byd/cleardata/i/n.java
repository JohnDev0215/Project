package com.byd.cleardata.i;

/* JADX INFO: loaded from: classes.dex */
public enum n {
    GB(1073741824),
    MB(1048576),
    KB(1024),
    B(1);

    public long g;

    n(long j) {
        this.g = j;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "SizeUnitType{name=" + name() + ", size=" + this.g + '}';
    }
}
