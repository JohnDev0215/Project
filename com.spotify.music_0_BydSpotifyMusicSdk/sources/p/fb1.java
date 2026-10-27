package p;

import android.database.DataSetObserver;

/* JADX INFO: loaded from: classes.dex */
public final class fb1 extends DataSetObserver {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ fb1(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.database.DataSetObserver
    public final void onChanged() {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                ib1 ib1Var = (ib1) obj;
                if (ib1Var.G.isShowing()) {
                    ib1Var.c();
                }
                break;
            default:
                s40 s40Var = (s40) obj;
                s40Var.c = true;
                s40Var.notifyDataSetChanged();
                break;
        }
    }

    @Override // android.database.DataSetObserver
    public final void onInvalidated() {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                ((ib1) obj).dismiss();
                break;
            default:
                s40 s40Var = (s40) obj;
                s40Var.c = false;
                s40Var.notifyDataSetInvalidated();
                break;
        }
    }
}
