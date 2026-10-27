package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import h3.y;
import q.InterfaceC2150g;
import q.MenuItemC2152i;

/* JADX INFO: loaded from: classes.dex */
public final class ExpandedMenuView extends ListView implements InterfaceC2150g, AdapterView.OnItemClickListener {
    public static final int[] l = {R.attr.background, R.attr.divider};

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        y yVarF = y.F(context, attributeSet, l, R.attr.listViewStyle);
        TypedArray typedArray = (TypedArray) yVarF.f15500n;
        if (typedArray.hasValue(0)) {
            setBackgroundDrawable(yVarF.s(0));
        }
        if (typedArray.hasValue(1)) {
            setDivider(yVarF.s(1));
        }
        yVarF.G();
    }

    @Override // q.InterfaceC2150g
    public final boolean a(MenuItemC2152i menuItemC2152i) {
        throw null;
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
    public final void onItemClick(AdapterView adapterView, View view, int i7, long j3) {
        throw null;
    }
}
