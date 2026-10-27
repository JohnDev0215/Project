package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import defpackage.g3;
import defpackage.p1;
import defpackage.r1;
import defpackage.w1;

/* JADX INFO: loaded from: classes.dex */
public final class ExpandedMenuView extends ListView implements p1.b, w1, AdapterView.OnItemClickListener {
    public static final int[] b = {R.attr.background, R.attr.divider};
    public p1 c;
    public int d;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listViewStyle);
    }

    @Override // p1.b
    public boolean a(r1 r1Var) {
        return this.c.N(r1Var, 0);
    }

    @Override // defpackage.w1
    public void b(p1 p1Var) {
        this.c = p1Var;
    }

    public int getWindowAnimations() {
        return this.d;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        a((r1) getAdapter().getItem(i));
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        g3 g3VarV = g3.v(context, attributeSet, b, i, 0);
        if (g3VarV.s(0)) {
            setBackgroundDrawable(g3VarV.g(0));
        }
        if (g3VarV.s(1)) {
            setDivider(g3VarV.g(1));
        }
        g3VarV.w();
    }
}
