package p;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ListAdapter;
import androidx.appcompat.app.AlertController$RecycleListView;

/* JADX INFO: loaded from: classes.dex */
public final class n9 implements s9, DialogInterface.OnClickListener {
    public k6 c;
    public ListAdapter e;
    public CharSequence k;
    public final /* synthetic */ t9 l;

    public n9(t9 t9Var) {
        this.l = t9Var;
    }

    @Override // p.s9
    public final boolean a() {
        k6 k6Var = this.c;
        if (k6Var != null) {
            return k6Var.isShowing();
        }
        return false;
    }

    @Override // p.s9
    public final int b() {
        return 0;
    }

    @Override // p.s9
    public final Drawable d() {
        return null;
    }

    @Override // p.s9
    public final void dismiss() {
        k6 k6Var = this.c;
        if (k6Var != null) {
            k6Var.dismiss();
            this.c = null;
        }
    }

    @Override // p.s9
    public final void f(CharSequence charSequence) {
        this.k = charSequence;
    }

    @Override // p.s9
    public final void g(Drawable drawable) {
        Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
    }

    @Override // p.s9
    public final void h(int i) {
        Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
    }

    @Override // p.s9
    public final void i(int i) {
        Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
    }

    @Override // p.s9
    public final void j(int i) {
        Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
    }

    @Override // p.s9
    public final void k(int i, int i2) {
        if (this.e == null) {
            return;
        }
        t9 t9Var = this.l;
        j6 j6Var = new j6(t9Var.getPopupContext());
        CharSequence charSequence = this.k;
        if (charSequence != null) {
            ((g6) j6Var.e).d = charSequence;
        }
        ListAdapter listAdapter = this.e;
        int selectedItemPosition = t9Var.getSelectedItemPosition();
        g6 g6Var = (g6) j6Var.e;
        g6Var.g = listAdapter;
        g6Var.h = this;
        g6Var.j = selectedItemPosition;
        g6Var.i = true;
        k6 k6VarD = j6Var.d();
        this.c = k6VarD;
        AlertController$RecycleListView alertController$RecycleListView = k6VarD.n.e;
        l9.d(alertController$RecycleListView, i);
        l9.c(alertController$RecycleListView, i2);
        this.c.show();
    }

    @Override // p.s9
    public final int l() {
        return 0;
    }

    @Override // p.s9
    public final CharSequence m() {
        return this.k;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        t9 t9Var = this.l;
        t9Var.setSelection(i);
        if (t9Var.getOnItemClickListener() != null) {
            t9Var.performItemClick(null, i, this.e.getItemId(i));
        }
        dismiss();
    }

    @Override // p.s9
    public final void p(ListAdapter listAdapter) {
        this.e = listAdapter;
    }
}
