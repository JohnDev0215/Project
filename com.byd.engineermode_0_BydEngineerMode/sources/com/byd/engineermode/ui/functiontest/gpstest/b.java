package com.byd.engineermode.ui.functiontest.gpstest;

/* JADX INFO: compiled from: GPSLocationInfo.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements Comparable<b> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2459b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Float f2460c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f2461d;

    public b(String str, int i, Float f) {
        this.f2460c = f;
        this.f2461d = str;
        this.f2459b = i;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(b bVar) {
        if (this.f2460c.floatValue() < bVar.f2460c.floatValue()) {
            return 1;
        }
        return this.f2460c.floatValue() > bVar.f2460c.floatValue() ? -1 : 0;
    }
}
