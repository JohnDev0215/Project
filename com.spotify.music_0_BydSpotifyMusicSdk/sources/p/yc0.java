package p;

import android.text.InputFilter;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class yc0 extends u11 {
    public final xc0 w;

    public yc0(TextView textView) {
        this.w = new xc0(textView);
    }

    @Override // p.u11
    public final InputFilter[] B(InputFilter[] inputFilterArr) {
        return (jc0.j != null) ^ true ? inputFilterArr : this.w.B(inputFilterArr);
    }

    @Override // p.u11
    public final void H(boolean z) {
        if (!(jc0.j != null)) {
            return;
        }
        this.w.H(z);
    }

    @Override // p.u11
    public final void I(boolean z) {
        boolean z2 = !(jc0.j != null);
        xc0 xc0Var = this.w;
        if (z2) {
            xc0Var.y = z;
        } else {
            xc0Var.I(z);
        }
    }
}
