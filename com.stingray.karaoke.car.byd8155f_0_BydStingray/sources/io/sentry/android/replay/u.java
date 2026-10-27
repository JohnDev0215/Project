package io.sentry.android.replay;

import h2.AbstractC1347d;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f16435a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f16436b;
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f16437e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f16438f;

    public u(int i7, int i10, float f10, float f11, int i11, int i12) {
        this.f16435a = i7;
        this.f16436b = i10;
        this.c = f10;
        this.d = f11;
        this.f16437e = i11;
        this.f16438f = i12;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof u)) {
            return false;
        }
        u uVar = (u) obj;
        return this.f16435a == uVar.f16435a && this.f16436b == uVar.f16436b && Float.compare(this.c, uVar.c) == 0 && Float.compare(this.d, uVar.d) == 0 && this.f16437e == uVar.f16437e && this.f16438f == uVar.f16438f;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f16438f) + AbstractC2641j.b(this.f16437e, AbstractC1347d.b(AbstractC1347d.b(AbstractC2641j.b(this.f16436b, Integer.hashCode(this.f16435a) * 31, 31), 31, this.c), 31, this.d), 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ScreenshotRecorderConfig(recordingWidth=");
        sb.append(this.f16435a);
        sb.append(", recordingHeight=");
        sb.append(this.f16436b);
        sb.append(", scaleFactorX=");
        sb.append(this.c);
        sb.append(", scaleFactorY=");
        sb.append(this.d);
        sb.append(", frameRate=");
        sb.append(this.f16437e);
        sb.append(", bitRate=");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.f16438f, ')');
    }
}
