package com.android.internal.accessibility.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.internal.R;
import com.android.internal.accessibility.dialog.TargetAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
class ShortcutTargetAdapter extends TargetAdapter {
    private int mShortcutMenuMode = 0;
    private final List<AccessibilityTarget> mTargets;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    ShortcutTargetAdapter(List<AccessibilityTarget> list) {
        this.mTargets = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mTargets.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.mTargets.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        TargetAdapter.ViewHolder viewHolder;
        Context context = viewGroup.getContext();
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.accessibility_shortcut_chooser_item, viewGroup, false);
            viewHolder = new TargetAdapter.ViewHolder();
            viewHolder.mCheckBoxView = (CheckBox) view.findViewById(R.id.accessibility_shortcut_target_checkbox);
            viewHolder.mIconView = (ImageView) view.findViewById(R.id.accessibility_shortcut_target_icon);
            viewHolder.mLabelView = (TextView) view.findViewById(R.id.accessibility_shortcut_target_label);
            viewHolder.mStatusView = (TextView) view.findViewById(R.id.accessibility_shortcut_target_status);
            view.setTag(viewHolder);
        } else {
            viewHolder = (TargetAdapter.ViewHolder) view.getTag();
        }
        this.mTargets.get(i).updateActionItem(viewHolder, this.mShortcutMenuMode);
        return view;
    }

    void setShortcutMenuMode(int i) {
        this.mShortcutMenuMode = i;
    }

    int getShortcutMenuMode() {
        return this.mShortcutMenuMode;
    }
}
