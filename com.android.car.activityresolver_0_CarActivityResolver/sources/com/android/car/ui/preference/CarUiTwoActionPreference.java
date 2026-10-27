package com.android.car.ui.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.preference.PreferenceViewHolder;
import com.android.car.ui.R;
import com.android.car.ui.utils.CarUiUtils;

/* JADX INFO: loaded from: classes2.dex */
public class CarUiTwoActionPreference extends CarUiPreference {
    private boolean mIsActionShown;

    public CarUiTwoActionPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public CarUiTwoActionPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CarUiTwoActionPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CarUiTwoActionPreference(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        setLayoutResource(R.layout.car_ui_two_action_preference);
        TypedArray preferenceAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.CarUiTwoActionPreference);
        this.mIsActionShown = preferenceAttributes.getBoolean(R.styleable.CarUiTwoActionPreference_actionShown, true);
        setShowChevron(false);
        preferenceAttributes.recycle();
    }

    public void showAction(boolean isShown) {
        this.mIsActionShown = isShown;
        notifyChanged();
    }

    public boolean isActionShown() {
        return this.mIsActionShown;
    }

    @Override // com.android.car.ui.preference.CarUiPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        View containerWithoutWidget = CarUiUtils.findViewByRefId(holder.itemView, R.id.car_ui_preference_container_without_widget);
        View actionContainer = CarUiUtils.findViewByRefId(holder.itemView, R.id.action_widget_container);
        View widgetFrame = CarUiUtils.findViewByRefId(holder.itemView, android.R.id.widget_frame);
        holder.itemView.setFocusable(!this.mIsActionShown);
        containerWithoutWidget.setOnClickListener(this.mIsActionShown ? new View.OnClickListener() { // from class: com.android.car.ui.preference.-$$Lambda$CarUiTwoActionPreference$8DJAUHOTU3R_fmOeFNGTBUp16OQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.performClick(view);
            }
        } : null);
        containerWithoutWidget.setClickable(this.mIsActionShown);
        containerWithoutWidget.setFocusable(this.mIsActionShown);
        actionContainer.setVisibility(this.mIsActionShown ? 0 : 8);
        widgetFrame.setFocusable(this.mIsActionShown);
        if (this.mIsActionShown) {
            onBindWidgetFrame(widgetFrame);
        }
    }

    protected void onBindWidgetFrame(View widgetFrame) {
    }
}
