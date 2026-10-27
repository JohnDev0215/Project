package org.chromium.components.translate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import org.chromium.components.translate.TranslateMessage;

/* JADX INFO: compiled from: TranslateMessageSecondaryMenuAdapter.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b extends BaseAdapter {
    public final LayoutInflater e;
    public TranslateMessage.MenuItem[] f;

    public b(Context context, TranslateMessage.MenuItem[] menuItemArr) {
        this.e = LayoutInflater.from(context);
        this.f = menuItemArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View a(int r4, int r5, android.view.View r6, android.view.ViewGroup r7) {
        /*
            r3 = this;
            r0 = 0
            if (r6 == 0) goto L19
            int r1 = org.chromium.components.translate.R$id.view_type
            java.lang.Object r2 = r6.getTag(r1)
            if (r2 == 0) goto L19
            java.lang.Object r1 = r6.getTag(r1)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            if (r1 != r4) goto L19
            r1 = 1
            goto L1a
        L19:
            r1 = r0
        L1a:
            if (r1 == 0) goto L1d
            return r6
        L1d:
            android.view.LayoutInflater r3 = r3.e
            android.view.View r3 = r3.inflate(r5, r7, r0)
            int r5 = org.chromium.components.translate.R$id.view_type
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3.setTag(r5, r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.translate.b.a(int, int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public final boolean areAllItemsEnabled() {
        for (int i = 0; i < getCount(); i++) {
            if (!isEnabled(i)) {
                return false;
            }
        }
        return true;
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        TranslateMessage.MenuItem[] menuItemArr = this.f;
        if (menuItemArr == null) {
            return 0;
        }
        return menuItemArr.length;
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        return this.f[i];
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final int getItemViewType(int i) {
        TranslateMessage.MenuItem menuItem = this.f[i];
        if (menuItem.a.equals("")) {
            return 0;
        }
        if (menuItem.b.equals("")) {
            return menuItem.c ? 3 : 1;
        }
        return 2;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        int itemViewType = getItemViewType(i);
        if (itemViewType == 0) {
            return a(0, R$layout.app_menu_divider, view, viewGroup);
        }
        if (itemViewType == 1) {
            View viewA = a(1, R$layout.translate_menu_item, view, viewGroup);
            ((TextView) viewA.findViewById(R$id.menu_item_text)).setText(this.f[i].a);
            return viewA;
        }
        if (itemViewType == 2) {
            View viewA2 = a(2, R$layout.translate_menu_extended_item, view, viewGroup);
            ((TextView) viewA2.findViewById(R$id.menu_item_text)).setText(this.f[i].a);
            ((TextView) viewA2.findViewById(R$id.menu_item_secondary_text)).setText(this.f[i].b);
            return viewA2;
        }
        if (itemViewType != 3) {
            return view;
        }
        View viewA3 = a(3, R$layout.translate_menu_item_checked, view, viewGroup);
        ((TextView) viewA3.findViewById(R$id.menu_item_text)).setText(this.f[i].a);
        return viewA3;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final int getViewTypeCount() {
        return 4;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final boolean hasStableIds() {
        return false;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final boolean isEmpty() {
        return getCount() == 0;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public final boolean isEnabled(int i) {
        return getItemViewType(i) != 0;
    }
}
