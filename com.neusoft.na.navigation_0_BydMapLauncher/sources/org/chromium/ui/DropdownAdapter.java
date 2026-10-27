package org.chromium.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import java.util.Set;
import org.chromium.base.ApiCompatibilityUtils;

/* JADX INFO: loaded from: classes3.dex */
public class DropdownAdapter extends ArrayAdapter<DropdownItem> {
    private final boolean mAreAllItemsEnabled;
    private final Context mContext;
    private final Set<Integer> mSeparators;

    public DropdownAdapter(Context context, List<? extends DropdownItem> list, Set<Integer> set) {
        super(context, R.layout.dropdown_item);
        addAll(list);
        this.mSeparators = set;
        this.mContext = context;
        this.mAreAllItemsEnabled = checkAreAllItemsEnabled();
    }

    private boolean checkAreAllItemsEnabled() {
        for (int i = 0; i < getCount(); i++) {
            DropdownItem item = getItem(i);
            if (item.isEnabled() && !item.isGroupHeader()) {
                return false;
            }
        }
        return true;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.dropdown_item, (ViewGroup) null);
            view.setBackground(new DropdownDividerDrawable());
        }
        DropdownDividerDrawable dropdownDividerDrawable = (DropdownDividerDrawable) view.getBackground();
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.dropdown_item_height);
        if (i == 0) {
            dropdownDividerDrawable.setColor(0);
        } else {
            int dimensionPixelSize2 = this.mContext.getResources().getDimensionPixelSize(R.dimen.dropdown_item_divider_height);
            dimensionPixelSize += dimensionPixelSize2;
            dropdownDividerDrawable.setHeight(dimensionPixelSize2);
            Set<Integer> set = this.mSeparators;
            if (set != null && set.contains(Integer.valueOf(i))) {
                dropdownDividerDrawable.setColor(ApiCompatibilityUtils.getColor(this.mContext.getResources(), R.color.dropdown_dark_divider_color));
            } else {
                dropdownDividerDrawable.setColor(ApiCompatibilityUtils.getColor(this.mContext.getResources(), R.color.dropdown_divider_color));
            }
        }
        DropdownItem item = getItem(i);
        View viewFindViewById = view.findViewById(R.id.dropdown_label_wrapper);
        if (item.isMultilineLabel()) {
            dimensionPixelSize = -2;
        }
        viewFindViewById.setLayoutParams(new LinearLayout.LayoutParams(0, dimensionPixelSize, 1.0f));
        TextView textView = (TextView) view.findViewById(R.id.dropdown_label);
        textView.setText(item.getLabel());
        textView.setSingleLine(!item.isMultilineLabel());
        textView.setEnabled(item.isEnabled());
        if (item.isGroupHeader()) {
            textView.setTypeface(null, 1);
        } else {
            textView.setTypeface(null, 0);
        }
        TextView textView2 = (TextView) view.findViewById(R.id.dropdown_sublabel);
        String sublabel = item.getSublabel();
        if (TextUtils.isEmpty(sublabel)) {
            textView2.setVisibility(8);
        } else {
            textView2.setText(sublabel);
            textView2.setVisibility(0);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.dropdown_icon);
        if (item.getIconId() == 0) {
            imageView.setVisibility(8);
        } else {
            imageView.setImageResource(item.getIconId());
            imageView.setVisibility(0);
        }
        return view;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        return this.mAreAllItemsEnabled;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i) {
        if (i < 0 || i >= getCount()) {
            return false;
        }
        DropdownItem item = getItem(i);
        return item.isEnabled() && !item.isGroupHeader();
    }
}
