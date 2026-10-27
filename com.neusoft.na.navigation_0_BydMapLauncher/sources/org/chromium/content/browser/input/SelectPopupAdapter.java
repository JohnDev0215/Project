package org.chromium.content.browser.input;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class SelectPopupAdapter extends ArrayAdapter<SelectPopupItem> {
    private boolean mAreAllItemsEnabled;
    private List<SelectPopupItem> mItems;

    public SelectPopupAdapter(Context context, int i, List<SelectPopupItem> list) {
        super(context, i, list);
        this.mItems = new ArrayList(list);
        this.mAreAllItemsEnabled = true;
        for (int i2 = 0; i2 < this.mItems.size(); i2++) {
            if (this.mItems.get(i2).getType() != 2) {
                this.mAreAllItemsEnabled = false;
                return;
            }
        }
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (i < 0 || i >= getCount()) {
            return null;
        }
        View view2 = super.getView(i, null, viewGroup);
        ((TextView) view2).setText(this.mItems.get(i).getLabel());
        if (this.mItems.get(i).getType() != 2) {
            if (this.mItems.get(i).getType() == 0) {
                if (view2 instanceof CheckedTextView) {
                    ((CheckedTextView) view2).setCheckMarkDrawable((Drawable) null);
                }
            } else {
                view2.setEnabled(false);
            }
        }
        return view2;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        return this.mAreAllItemsEnabled;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i) {
        return i >= 0 && i < getCount() && this.mItems.get(i).getType() == 2;
    }
}
