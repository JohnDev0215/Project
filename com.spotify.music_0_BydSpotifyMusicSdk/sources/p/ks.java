package p;

import android.graphics.Typeface;
import com.google.android.material.chip.Chip;

/* JADX INFO: loaded from: classes.dex */
public final class ks extends zu {
    public final /* synthetic */ int i;
    public final /* synthetic */ Object j;

    public /* synthetic */ ks(int i, Object obj) {
        this.i = i;
        this.j = obj;
    }

    @Override // p.zu
    public final void u0(int i) {
        switch (this.i) {
            case 0:
                break;
            default:
                qj2 qj2Var = (qj2) this.j;
                qj2Var.d = true;
                pj2 pj2Var = (pj2) qj2Var.e.get();
                if (pj2Var != null) {
                    os osVar = (os) pj2Var;
                    osVar.v();
                    osVar.invalidateSelf();
                }
                break;
        }
    }

    @Override // p.zu
    public final void w0(Typeface typeface, boolean z) {
        int i = this.i;
        Object obj = this.j;
        switch (i) {
            case 0:
                Chip chip = (Chip) obj;
                os osVar = chip.m;
                chip.setText(osVar.L0 ? osVar.M : chip.getText());
                chip.requestLayout();
                chip.invalidate();
                break;
            default:
                if (!z) {
                    qj2 qj2Var = (qj2) obj;
                    qj2Var.d = true;
                    pj2 pj2Var = (pj2) qj2Var.e.get();
                    if (pj2Var != null) {
                        os osVar2 = (os) pj2Var;
                        osVar2.v();
                        osVar2.invalidateSelf();
                    }
                    break;
                }
                break;
        }
    }
}
