package m0;

import n7.AbstractC1985B;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f18878a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f18879b;
    public float c;
    public float d;

    public final void a(float f10, float f11, float f12, float f13) {
        this.f18878a = Math.max(f10, this.f18878a);
        this.f18879b = Math.max(f11, this.f18879b);
        this.c = Math.min(f12, this.c);
        this.d = Math.min(f13, this.d);
    }

    public final boolean b() {
        return this.f18878a >= this.c || this.f18879b >= this.d;
    }

    public final String toString() {
        return "MutableRect(" + AbstractC1985B.v(this.f18878a) + ", " + AbstractC1985B.v(this.f18879b) + ", " + AbstractC1985B.v(this.c) + ", " + AbstractC1985B.v(this.d) + ')';
    }
}
