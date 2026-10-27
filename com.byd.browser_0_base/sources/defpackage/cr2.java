package defpackage;

import android.util.AndroidRuntimeException;
import defpackage.af0;
import defpackage.c90;
import defpackage.q5;

/* JADX INFO: compiled from: SpringAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public final class cr2 extends af0<cr2> {
    public dr2 r;
    public float s;
    public boolean t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public cr2(Object obj) {
        super(obj);
        c90.a aVar = c90.u;
        this.r = null;
        this.s = Float.MAX_VALUE;
        this.t = false;
    }

    @Override // defpackage.af0
    public final boolean d(long j) {
        if (this.t) {
            float f = this.s;
            if (f != Float.MAX_VALUE) {
                this.r.i = f;
                this.s = Float.MAX_VALUE;
            }
            this.b = (float) this.r.i;
            this.a = 0.0f;
            this.t = false;
            return true;
        }
        if (this.s != Float.MAX_VALUE) {
            long j2 = j / 2;
            af0.g gVarA = this.r.a(this.b, this.a, j2);
            dr2 dr2Var = this.r;
            dr2Var.i = this.s;
            this.s = Float.MAX_VALUE;
            af0.g gVarA2 = dr2Var.a(gVarA.a, gVarA.b, j2);
            this.b = gVarA2.a;
            this.a = gVarA2.b;
        } else {
            af0.g gVarA3 = this.r.a(this.b, this.a, j);
            this.b = gVarA3.a;
            this.a = gVarA3.b;
        }
        float fMax = Math.max(this.b, this.g);
        this.b = fMax;
        float fMin = Math.min(fMax, Float.MAX_VALUE);
        this.b = fMin;
        float f2 = this.a;
        dr2 dr2Var2 = this.r;
        dr2Var2.getClass();
        if (!(((double) Math.abs(f2)) < dr2Var2.e && ((double) Math.abs(fMin - ((float) dr2Var2.i))) < dr2Var2.d)) {
            return false;
        }
        this.b = (float) this.r.i;
        this.a = 0.0f;
        return true;
    }

    public final void e() {
        if (!(this.r.b > 0.0d)) {
            throw new UnsupportedOperationException("Spring animations can only come to an end when there is damping");
        }
        if (!((q5.d) af0.b().e).a()) {
            throw new AndroidRuntimeException("Animations may only be started on the same thread as the animation handler");
        }
        if (this.f) {
            this.t = true;
        }
    }
}
