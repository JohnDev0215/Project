package p;

import com.google.firebase.encoders.EncodingException;

/* JADX INFO: loaded from: classes.dex */
public final class vy1 implements ss2 {
    public boolean a = false;
    public boolean b = false;
    public wt0 c;
    public final uy1 d;

    public vy1(uy1 uy1Var) {
        this.d = uy1Var;
    }

    @Override // p.ss2
    public final ss2 d(String str) {
        if (this.a) {
            throw new EncodingException("Cannot encode a second value in the ValueEncoderContext");
        }
        this.a = true;
        this.d.g(this.c, str, this.b);
        return this;
    }

    @Override // p.ss2
    public final ss2 e(boolean z) {
        if (this.a) {
            throw new EncodingException("Cannot encode a second value in the ValueEncoderContext");
        }
        this.a = true;
        this.d.d(this.c, z ? 1 : 0, this.b);
        return this;
    }
}
