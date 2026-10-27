package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.TintTypedArray;

/* JADX INFO: loaded from: classes.dex */
public final class ExpandedMenuView extends ListView implements MenuBuilder.ItemInvoker, MenuView, AdapterView.OnItemClickListener {
    public static final int[] f = {R.attr.background, R.attr.divider};
    public MenuBuilder e;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listViewStyle);
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder.ItemInvoker
    public final boolean a(MenuItemImpl menuItemImpl) {
        return this.e.q(menuItemImpl, null, 0);
    }

    @Override // androidx.appcompat.view.menu.MenuView
    public final void b(MenuBuilder menuBuilder) {
        this.e = menuBuilder;
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
        a((MenuItemImpl) getAdapter().getItem(i));
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        TintTypedArray tintTypedArray = new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, f, i, 0));
        if (tintTypedArray.l(0)) {
            setBackgroundDrawable(tintTypedArray.e(0));
        }
        if (tintTypedArray.l(1)) {
            setDivider(tintTypedArray.e(1));
        }
        tintTypedArray.n();
    }
}
