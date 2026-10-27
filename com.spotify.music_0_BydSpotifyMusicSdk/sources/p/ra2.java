package p;

import android.view.View;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final class ra2 implements View.OnFocusChangeListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ ra2(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.view.View.OnFocusChangeListener
    public final void onFocusChange(View view, boolean z) {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                SearchView searchView = (SearchView) obj;
                View.OnFocusChangeListener onFocusChangeListener = searchView.S;
                if (onFocusChangeListener != null) {
                    onFocusChangeListener.onFocusChange(searchView, z);
                }
                break;
            case 1:
                et etVar = (et) obj;
                etVar.e(et.d(etVar));
                break;
            default:
                pb0 pb0Var = (pb0) obj;
                pb0Var.a.setEndIconActivated(z);
                if (!z) {
                    pb0Var.f(false);
                    pb0Var.j = false;
                }
                break;
        }
    }
}
