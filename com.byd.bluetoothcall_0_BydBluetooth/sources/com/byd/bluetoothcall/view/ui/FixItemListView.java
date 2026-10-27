package com.byd.bluetoothcall.view.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

/* JADX INFO: loaded from: classes.dex */
public class FixItemListView extends ListView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private transient int f3455b;

    public FixItemListView(Context context) {
        super(context);
    }

    private void a() {
        ListAdapter adapter = getAdapter();
        if (adapter == null || this.f3455b == 0 || adapter.getCount() == 0) {
            return;
        }
        View view = adapter.getView(0, null, this);
        view.measure(0, 0);
        int measuredHeight = view.getMeasuredHeight();
        int count = adapter.getCount();
        int i = this.f3455b;
        LinearLayout.LayoutParams layoutParams = count <= i ? (count == 1 || count == 2) ? new LinearLayout.LayoutParams(-1, (measuredHeight * count) - 2) : new LinearLayout.LayoutParams(-1, -2) : new LinearLayout.LayoutParams(-1, (measuredHeight * i) - 2);
        layoutParams.leftMargin = 32;
        layoutParams.rightMargin = 20;
        layoutParams.topMargin = 8;
        layoutParams.bottomMargin = 16;
        setLayoutParams(layoutParams);
    }

    public void setFixItemCount(int i) {
        this.f3455b = i;
        a();
    }

    public FixItemListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.AdapterView
    public void setAdapter(ListAdapter listAdapter) {
        super.setAdapter(listAdapter);
        a();
    }

    public FixItemListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
