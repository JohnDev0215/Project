package defpackage;

import android.view.ViewGroup;

/* JADX INFO: compiled from: OverlayPanelBase.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class mt1 {
    public float e;
    public float f;
    public float g;
    public float h;
    public float i;
    public float j;
    public float k;
    public ViewGroup l;

    public final int C(float f) {
        if (f < 0.0f) {
            return 1;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i >= 5) {
                i = i2;
                i2 = i3;
                break;
            }
            if (i != 0) {
                if (f >= D(Integer.valueOf(i2)) && f < D(Integer.valueOf(i))) {
                    break;
                }
                i3 = i2;
                i2 = i;
            }
            i++;
        }
        float fD = D(Integer.valueOf(i2));
        return (f - fD) / (D(Integer.valueOf(i)) - fD) > 0.5f ? i : i2;
    }

    public final float D(Integer num) {
        if (num == null || num.intValue() == 2) {
            return 0.0f;
        }
        if (num.intValue() == 3) {
            return (((this.e > 680.0f ? 1 : (this.e == 680.0f ? 0 : -1)) <= 0 ? this.f : this.f - 0.0f) - 0.0f) * 0.7f;
        }
        if (num.intValue() == 4) {
            return this.f - 0.0f;
        }
        return 0.0f;
    }

    public final boolean E() {
        return this.k > 0.0f;
    }

    public final void H(float f) {
        float fRound;
        int i = 0;
        while (true) {
            if (i >= 5) {
                i = 1;
                break;
            }
            if ((i != 0) && f <= D(Integer.valueOf(i))) {
                break;
            } else {
                i++;
            }
        }
        Integer numValueOf = (i < 2 || i > 4) ? null : Integer.valueOf(i - 1);
        D(Integer.valueOf(numValueOf != null ? numValueOf.intValue() : 0));
        D(Integer.valueOf(i));
        this.k = f;
        float f2 = this.e;
        if (f2 <= 680.0f) {
            fRound = 0.0f;
        } else {
            fRound = Math.round((f2 - (f2 <= 680.0f ? f2 : 600.0f)) / 2.0f);
        }
        this.i = fRound;
        this.j = ((this.f - 0.0f) + 0.0f) - this.k;
        D(4);
        throw null;
    }
}
