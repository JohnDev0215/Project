package com.amazon.mp3.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;
import java.util.Collection;

/* JADX INFO: loaded from: classes3.dex */
public class SubMenuSpinnerAdapter<T> extends ArrayAdapter<Object> {
    private int mDropdownItemLayoutId;
    private int mItemLayoutId;
    private int mItemViewId;
    private int mSubmenuHeaderLayoutId;
    private int mSubmenuHeaderViewId;

    protected String getTextForItem(T item) {
        throw null;
    }

    private static class SubmenuHeader {
        private Object mData;

        public SubmenuHeader(Object data) {
            this.mData = data;
        }
    }

    public SubMenuSpinnerAdapter(Context context, int itemLayoutId, int dropdownItemLayoutId, int itemViewId, int submenuHeaderLayoutId, int submenuHeaderViewId) {
        super(context, itemLayoutId, itemViewId);
        setDropDownViewResource(dropdownItemLayoutId);
        this.mItemLayoutId = itemLayoutId;
        this.mDropdownItemLayoutId = dropdownItemLayoutId;
        this.mItemViewId = itemViewId;
        this.mSubmenuHeaderLayoutId = submenuHeaderLayoutId;
        this.mSubmenuHeaderViewId = submenuHeaderViewId;
    }

    public void addSection(Object header, Collection<T> items) {
        if (header != null) {
            add(new SubmenuHeader(header));
        }
        addAll(items);
    }

    protected String getTextForHeader(Object header) {
        return header.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        Object item = getItem(position);
        if (item instanceof SubmenuHeader) {
            return getSubmenuView(((SubmenuHeader) item).mData, convertView, parent);
        }
        return getItemView(item, convertView, parent, this.mItemLayoutId);
    }

    private View getItemView(T item, View convertView, final ViewGroup parent, int layoutId) {
        String textForItem = getTextForItem(item);
        if (convertView != null) {
            TextView textView = (TextView) convertView.findViewById(this.mItemViewId);
            if (textView == null) {
                convertView = null;
            } else {
                textView.setText(textForItem);
            }
        }
        if (convertView != null) {
            return convertView;
        }
        View viewInflate = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        ((TextView) viewInflate.findViewById(this.mItemViewId)).setText(textForItem);
        return viewInflate;
    }

    private View getSubmenuView(Object header, View convertView, final ViewGroup parent) {
        String textForHeader = getTextForHeader(header);
        if (convertView != null) {
            TextView textView = (TextView) convertView.findViewById(this.mSubmenuHeaderViewId);
            if (textView == null) {
                convertView = null;
            } else {
                textView.setText(textForHeader);
            }
        }
        if (convertView != null) {
            return convertView;
        }
        View viewInflate = LayoutInflater.from(getContext()).inflate(this.mSubmenuHeaderLayoutId, parent, false);
        ((TextView) viewInflate.findViewById(this.mSubmenuHeaderViewId)).setText(textForHeader);
        return viewInflate;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(final int position) {
        return !(getItem(position) instanceof SubmenuHeader);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(final int position, final View convertView, final ViewGroup parent) {
        View itemView;
        int dimensionPixelSize;
        Object item = getItem(position);
        if (item instanceof SubmenuHeader) {
            itemView = getSubmenuView(((SubmenuHeader) item).mData, convertView, parent);
        } else {
            itemView = getItemView(item, convertView, parent, this.mDropdownItemLayoutId);
        }
        int dimensionPixelSize2 = 0;
        if (position == 0) {
            dimensionPixelSize2 = itemView.getResources().getDimensionPixelSize(R.dimen.refinement_spinner_vertical_padding);
            dimensionPixelSize = 0;
        } else {
            dimensionPixelSize = position == getCount() + (-1) ? itemView.getResources().getDimensionPixelSize(R.dimen.refinement_spinner_vertical_padding) : 0;
        }
        itemView.setPadding(itemView.getPaddingLeft(), dimensionPixelSize2, itemView.getPaddingRight(), dimensionPixelSize);
        return itemView;
    }
}
