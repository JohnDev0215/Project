package com.android.internal.accessibility.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.internal.R;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
class ButtonTargetAdapter extends TargetAdapter {
    private List<AccessibilityTarget> mTargets;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    ButtonTargetAdapter(List<AccessibilityTarget> list) {
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
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.accessibility_button_chooser_item, viewGroup, false);
        AccessibilityTarget accessibilityTarget = this.mTargets.get(i);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.accessibility_button_target_icon);
        TextView textView = (TextView) viewInflate.findViewById(R.id.accessibility_button_target_label);
        imageView.setImageDrawable(accessibilityTarget.getIcon());
        textView.setText(accessibilityTarget.getLabel());
        return viewInflate;
    }
}
