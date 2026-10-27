package defpackage;

import android.view.View;
import com.google.android.material.datepicker.f;

/* JADX INFO: compiled from: MaterialDatePicker.java */
/* JADX INFO: loaded from: classes.dex */
public final class lc1 implements View.OnClickListener {
    public final /* synthetic */ f e;

    public lc1(f fVar) {
        this.e = fVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        f fVar = this.e;
        fVar.J0.setEnabled(fVar.N0().T());
        fVar.H0.toggle();
        fVar.T0(fVar.H0);
        fVar.R0();
    }
}
