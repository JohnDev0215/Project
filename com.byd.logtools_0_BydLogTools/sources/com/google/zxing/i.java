package com.google.zxing;

/* JADX INFO: compiled from: ResultPoint.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float f4293a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f4294b;

    public i(float f2, float f3) {
        this.f4293a = f2;
        this.f4294b = f3;
    }

    private static float a(i iVar, i iVar2, i iVar3) {
        float f2 = iVar2.f4293a;
        float f3 = iVar2.f4294b;
        return ((iVar3.f4293a - f2) * (iVar.f4294b - f3)) - ((iVar3.f4294b - f3) * (iVar.f4293a - f2));
    }

    public static float b(i iVar, i iVar2) {
        return com.google.zxing.common.l.a.a(iVar.f4293a, iVar.f4294b, iVar2.f4293a, iVar2.f4294b);
    }

    public static void e(i[] iVarArr) {
        i iVar;
        i iVar2;
        i iVar3;
        float fB = b(iVarArr[0], iVarArr[1]);
        float fB2 = b(iVarArr[1], iVarArr[2]);
        float fB3 = b(iVarArr[0], iVarArr[2]);
        if (fB2 >= fB && fB2 >= fB3) {
            iVar = iVarArr[0];
            iVar2 = iVarArr[1];
            iVar3 = iVarArr[2];
        } else if (fB3 < fB2 || fB3 < fB) {
            iVar = iVarArr[2];
            iVar2 = iVarArr[0];
            iVar3 = iVarArr[1];
        } else {
            iVar = iVarArr[1];
            iVar2 = iVarArr[0];
            iVar3 = iVarArr[2];
        }
        if (a(iVar2, iVar, iVar3) < 0.0f) {
            i iVar4 = iVar3;
            iVar3 = iVar2;
            iVar2 = iVar4;
        }
        iVarArr[0] = iVar2;
        iVarArr[1] = iVar;
        iVarArr[2] = iVar3;
    }

    public final float c() {
        return this.f4293a;
    }

    public final float d() {
        return this.f4294b;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof i) {
            i iVar = (i) obj;
            if (this.f4293a == iVar.f4293a && this.f4294b == iVar.f4294b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (Float.floatToIntBits(this.f4293a) * 31) + Float.floatToIntBits(this.f4294b);
    }

    public final String toString() {
        return "(" + this.f4293a + ',' + this.f4294b + ')';
    }
}
