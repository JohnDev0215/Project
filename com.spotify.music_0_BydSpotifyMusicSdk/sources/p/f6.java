package p;

import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: loaded from: classes.dex */
public final class f6 implements AdapterView.OnItemClickListener {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object k;

    public /* synthetic */ f6(Object obj, int i, Object obj2) {
        this.c = i;
        this.k = obj;
        this.e = obj2;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        int i2 = this.c;
        Object obj = this.k;
        switch (i2) {
            case 0:
                g6 g6Var = (g6) obj;
                DialogInterface.OnClickListener onClickListener = g6Var.h;
                i6 i6Var = (i6) this.e;
                onClickListener.onClick(i6Var.b, i);
                if (!g6Var.i) {
                    i6Var.b.dismiss();
                }
                break;
            default:
                q9 q9Var = (q9) obj;
                q9Var.N.setSelection(i);
                t9 t9Var = q9Var.N;
                if (t9Var.getOnItemClickListener() != null) {
                    t9Var.performItemClick(view, i, q9Var.K.getItemId(i));
                }
                q9Var.dismiss();
                break;
        }
    }
}
