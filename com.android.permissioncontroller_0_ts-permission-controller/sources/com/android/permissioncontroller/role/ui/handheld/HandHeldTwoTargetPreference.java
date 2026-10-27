package com.android.permissioncontroller.role.ui.handheld;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.PreferenceViewHolder;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.role.ui.TwoTargetPreference;

/* JADX INFO: loaded from: classes8.dex */
abstract class HandHeldTwoTargetPreference extends TwoTargetPreference {
    HandHeldTwoTargetPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    HandHeldTwoTargetPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    HandHeldTwoTargetPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    HandHeldTwoTargetPreference(Context context) {
        super(context);
        init();
    }

    private void init() {
        setLayoutResource(R.layout.two_target_preference);
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        View widgetFrame = holder.findViewById(android.R.id.widget_frame);
        ViewGroup widgetFrameParent = (ViewGroup) widgetFrame.getParent();
        ViewGroup itemView = (ViewGroup) holder.itemView;
        if (widgetFrameParent != itemView) {
            widgetFrameParent.removeView(widgetFrame);
            itemView.addView(widgetFrame);
        }
    }
}
