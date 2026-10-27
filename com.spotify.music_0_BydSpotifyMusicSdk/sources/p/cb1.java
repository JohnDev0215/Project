package p;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final class cb1 implements AdapterView.OnItemSelectedListener {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;

    public /* synthetic */ cb1(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        ib0 ib0Var;
        int i2 = this.c;
        Object obj = this.e;
        switch (i2) {
            case 0:
                if (i != -1 && (ib0Var = ((ib1) obj).k) != null) {
                    ib0Var.setListSelectionHidden(false);
                    break;
                }
                break;
            default:
                ((SearchView) obj).q(i);
                break;
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView adapterView) {
    }
}
