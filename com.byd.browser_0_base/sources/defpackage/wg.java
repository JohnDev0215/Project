package defpackage;

import android.widget.CompoundButton;
import com.google.android.material.chip.Chip;
import defpackage.jc1;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class wg implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ wg(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
            case 1:
                ((xg) obj).o(compoundButton, z);
                break;
            default:
                Chip chip = (Chip) obj;
                jc1.a<Chip> aVar = chip.n;
                if (aVar != null) {
                    cn cnVar = ((bn) aVar).a;
                    if (!z ? cnVar.e(chip, cnVar.e) : cnVar.a(chip)) {
                        cnVar.d();
                    }
                }
                CompoundButton.OnCheckedChangeListener onCheckedChangeListener = chip.m;
                if (onCheckedChangeListener != null) {
                    onCheckedChangeListener.onCheckedChanged(compoundButton, z);
                }
                break;
        }
    }
}
