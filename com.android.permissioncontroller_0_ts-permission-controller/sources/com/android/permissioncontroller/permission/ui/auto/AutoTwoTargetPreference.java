package com.android.permissioncontroller.permission.ui.auto;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.permissioncontroller.R;

/* JADX INFO: loaded from: classes8.dex */
public class AutoTwoTargetPreference extends Preference {
    private boolean mIsDividerVisible;
    private OnSecondTargetClickListener mListener;

    public interface OnSecondTargetClickListener {
        void onSecondTargetClick(AutoTwoTargetPreference autoTwoTargetPreference);
    }

    public AutoTwoTargetPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mIsDividerVisible = true;
        init();
    }

    public AutoTwoTargetPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mIsDividerVisible = true;
        init();
    }

    public AutoTwoTargetPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mIsDividerVisible = true;
        init();
    }

    public AutoTwoTargetPreference(Context context) {
        super(context);
        this.mIsDividerVisible = true;
        init();
    }

    private void init() {
        setLayoutResource(R.layout.car_two_target_preference);
    }

    public void setOnSecondTargetClickListener(OnSecondTargetClickListener listener) {
        this.mListener = listener;
        notifyChanged();
    }

    public void setDividerVisible(boolean visible) {
        this.mIsDividerVisible = visible;
        notifyChanged();
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        View actionContainer = holder.findViewById(R.id.action_widget_container);
        View divider = holder.findViewById(R.id.two_target_divider);
        FrameLayout widgetFrame = (FrameLayout) holder.findViewById(android.R.id.widget_frame);
        if (this.mListener != null) {
            actionContainer.setVisibility(0);
            divider.setVisibility(this.mIsDividerVisible ? 0 : 8);
            widgetFrame.setVisibility(0);
            widgetFrame.setOnClickListener(new View.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoTwoTargetPreference$lC83sZTzAwhIphPOXb6-MD9_X7U
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onBindViewHolder$0$AutoTwoTargetPreference(view);
                }
            });
            return;
        }
        actionContainer.setVisibility(8);
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$AutoTwoTargetPreference(View v) {
        this.mListener.onSecondTargetClick(this);
    }
}
