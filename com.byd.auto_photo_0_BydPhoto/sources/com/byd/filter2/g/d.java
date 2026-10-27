package com.byd.filter2.g;

import android.content.res.Resources;
import android.graphics.ColorMatrix;
import com.byd.util.w;

/* JADX INFO: loaded from: classes2.dex */
public class d extends e {
    private int A;
    private int B;
    private ColorMatrix C;
    private final ColorMatrix D;
    private final ColorMatrix E;
    private final ColorMatrix F;
    private int z;

    public d(Resources resources) {
        super(resources);
        this.D = new ColorMatrix();
        this.E = new ColorMatrix();
        this.F = new ColorMatrix();
    }

    private void M(int i, boolean z) {
        float f = ((i * 81.5f) / 10000.0f) + 1.0f;
        this.F.reset();
        float[] array = this.F.getArray();
        array[12] = f;
        array[6] = f;
        array[0] = f;
        float f2 = (1.0f - f) * 128.0f;
        array[14] = f2;
        array[9] = f2;
        array[4] = f2;
        if (z) {
            T();
        }
    }

    private void N(int i, boolean z) {
        float f = ((i * 81.5f) / 10000.0f) + 1.0f;
        this.E.reset();
        this.E.setScale(f, f, f, 1.0f);
        if (z) {
            T();
        }
    }

    private void O(int i, boolean z) {
        this.D.reset();
        this.D.setSaturation((i + 100) / 100.0f);
        if (z) {
            T();
        }
    }

    private void T() {
        this.C.reset();
        this.C.postConcat(this.E);
        this.C.postConcat(this.D);
        this.C.postConcat(this.F);
        this.y = this.C.getArray();
    }

    @Override // com.byd.filter2.g.e
    protected float[] L() {
        if (this.C == null) {
            this.C = new ColorMatrix();
        }
        return this.C.getArray();
    }

    public int P() {
        return this.A;
    }

    public int Q() {
        return this.B;
    }

    public int R() {
        return this.z;
    }

    public void S(int i, int i2, int i3) {
        if (this.z == i && this.A == i2 && this.B == i3) {
            return;
        }
        this.z = i;
        this.A = i2;
        this.B = i3;
        O(i, false);
        N(i2, false);
        M(i3, false);
        T();
    }

    public void U(int i) {
        if (this.A == i) {
            return;
        }
        this.A = i;
        w.c("brightness : " + i);
        N(i, true);
    }

    public void V(int i) {
        if (this.B == i) {
            return;
        }
        this.B = i;
        w.c("contrast : " + i);
        M(i, true);
    }

    public void W(int i) {
        if (this.z == i) {
            return;
        }
        this.z = i;
        w.c("saturation : " + i);
        O(i, true);
    }
}
