package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import p.qj1;
import p.ri1;
import p.si1;
import p.v4;
import p.xi1;

/* JADX INFO: loaded from: classes.dex */
public final class ExpandedMenuView extends ListView implements ri1, qj1, AdapterView.OnItemClickListener {
    public static final int[] e = {R.attr.background, R.attr.divider};
    public si1 c;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        v4 v4Var = new v4(context, context.obtainStyledAttributes(attributeSet, e, R.attr.listViewStyle, 0));
        if (v4Var.G(0)) {
            setBackgroundDrawable(v4Var.t(0));
        }
        if (v4Var.G(1)) {
            setDivider(v4Var.t(1));
        }
        v4Var.L();
    }

    @Override // p.qj1
    public final void b(si1 si1Var) {
        this.c = si1Var;
    }

    @Override // p.ri1
    public final boolean d(xi1 xi1Var) {
        return this.c.q(xi1Var, null, 0);
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        d((xi1) getAdapter().getItem(i));
    }
}
