package com.byd.carsettings.common;

/* JADX INFO: loaded from: classes2.dex */
public enum SizeUnitType {
    GB(1073741824),
    MB(1048576),
    KB(1024),
    B(1);

    public long size;

    SizeUnitType(long j) {
        this.size = j;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "SizeUnitType{name=" + name() + ", size=" + this.size + '}';
    }
}
