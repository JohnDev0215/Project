package r;

import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: renamed from: r.Y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2236Y implements AdapterView.OnItemSelectedListener {
    public final /* synthetic */ j0 l;

    public C2236Y(j0 j0Var) {
        this.l = j0Var;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView adapterView, View view, int i7, long j3) {
        i0 i0Var;
        if (i7 == -1 || (i0Var = this.l.f21508n) == null) {
            return;
        }
        i0Var.setListSelectionHidden(false);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView adapterView) {
    }
}
