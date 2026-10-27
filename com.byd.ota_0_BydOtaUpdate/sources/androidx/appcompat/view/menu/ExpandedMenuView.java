package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import b.b.p.j.g;
import b.b.p.j.i;
import b.b.p.j.n;
import b.b.q.e0;

/* JADX INFO: loaded from: classes.dex */
public final class ExpandedMenuView extends ListView implements g.b, n, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f151c = {R.attr.background, R.attr.divider};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g f152d;
    public int s;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listViewStyle);
    }

    @Override // b.b.p.j.g.b
    public boolean a(i iVar) {
        return this.f152d.L(iVar, 0);
    }

    @Override // b.b.p.j.n
    public void b(g gVar) {
        this.f152d = gVar;
    }

    public int getWindowAnimations() {
        return this.s;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i2, long j) {
        a((i) getAdapter().getItem(i2));
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        e0 e0VarU = e0.u(context, attributeSet, f151c, i2, 0);
        if (e0VarU.r(0)) {
            setBackgroundDrawable(e0VarU.f(0));
        }
        if (e0VarU.r(1)) {
            setDivider(e0VarU.f(1));
        }
        e0VarU.v();
    }
}
