package defpackage;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

/* JADX INFO: compiled from: DropdownPopupWindow.java */
/* JADX INFO: loaded from: classes3.dex */
public class qe0 {
    public final te0 e;

    public qe0(Context context, View view, sq3 sq3Var) {
        this.e = new te0(context, view, sq3Var);
    }

    public final void a(BaseAdapter baseAdapter) {
        te0 te0Var = this.e;
        te0Var.l = baseAdapter;
        te0Var.m.setAdapter((ListAdapter) baseAdapter);
        te0Var.k.g();
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.e.setOnDismissListener(onDismissListener);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.e.setOnItemClickListener(onItemClickListener);
    }
}
