package defpackage;

import android.content.Context;
import android.text.Layout;
import android.view.View;
import android.widget.TextView;

/* JADX INFO: compiled from: SectionUiUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fh2 implements View.OnLayoutChangeListener {
    public final /* synthetic */ dh2 e;
    public final /* synthetic */ TextView f;
    public final /* synthetic */ Context g;

    public fh2(Context context, TextView textView, dh2 dh2Var) {
        this.e = dh2Var;
        this.f = textView;
        this.g = context;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        dh2 dh2Var = this.e;
        lf0 lf0VarB = dh2Var.b();
        TextView textView = this.f;
        if (lf0VarB != null) {
            textView.removeOnLayoutChangeListener(this);
            return;
        }
        Layout layout = textView.getLayout();
        if (layout.getEllipsisCount(0) > 0) {
            textView.setText(gh2.a(this.g, dh2Var, layout, textView.getPaint()));
        }
    }
}
