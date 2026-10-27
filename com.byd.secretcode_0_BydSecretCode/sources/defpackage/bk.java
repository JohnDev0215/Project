package defpackage;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: compiled from: AdjustedCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class bk implements ck {
    public final ck a;
    public final float b;

    public bk(float f, ck ckVar) {
        while (ckVar instanceof bk) {
            ckVar = ((bk) ckVar).a;
            f += ((bk) ckVar).b;
        }
        this.a = ckVar;
        this.b = f;
    }

    @Override // defpackage.ck
    public float a(RectF rectF) {
        return Math.max(0.0f, this.a.a(rectF) + this.b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof bk)) {
            return false;
        }
        bk bkVar = (bk) obj;
        return this.a.equals(bkVar.a) && this.b == bkVar.b;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.a, Float.valueOf(this.b)});
    }
}
