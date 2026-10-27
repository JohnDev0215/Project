package b.d.a.a.h0;

import a.b.p.k0;
import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: loaded from: classes.dex */
public class o implements AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ p f1941a;

    public o(p pVar) {
        this.f1941a = pVar;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Object item;
        p pVar = this.f1941a;
        if (i < 0) {
            k0 k0Var = pVar.f1942d;
            item = !k0Var.a() ? null : k0Var.f305c.getSelectedItem();
        } else {
            item = pVar.getAdapter().getItem(i);
        }
        p.a(this.f1941a, item);
        AdapterView.OnItemClickListener onItemClickListener = this.f1941a.getOnItemClickListener();
        if (onItemClickListener != null) {
            if (view == null || i < 0) {
                k0 k0Var2 = this.f1941a.f1942d;
                view = !k0Var2.a() ? null : k0Var2.f305c.getSelectedView();
                k0 k0Var3 = this.f1941a.f1942d;
                i = !k0Var3.a() ? -1 : k0Var3.f305c.getSelectedItemPosition();
                k0 k0Var4 = this.f1941a.f1942d;
                j = !k0Var4.a() ? Long.MIN_VALUE : k0Var4.f305c.getSelectedItemId();
            }
            onItemClickListener.onItemClick(this.f1941a.f1942d.f305c, view, i, j);
        }
        this.f1941a.f1942d.dismiss();
    }
}
