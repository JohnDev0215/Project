package com.android.car.ui.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.preference.PreferenceViewHolder;
import com.android.car.ui.R$id;
import com.android.car.ui.R$layout;
import com.android.car.ui.R$styleable;
import spi.b2;
import spi.k8;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class CarUiTwoActionPreference extends CarUiPreference {
    private boolean mIsActionShown;

    public CarUiTwoActionPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        setLayoutResource(R$layout.car_ui_two_action_preference);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.CarUiTwoActionPreference);
        this.mIsActionShown = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CarUiTwoActionPreference_actionShown, true);
        setShowChevron(false);
        typedArrayObtainStyledAttributes.recycle();
    }

    public boolean isActionShown() {
        return this.mIsActionShown;
    }

    @Override // com.android.car.ui.preference.CarUiPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View viewD = b2.d(preferenceViewHolder.itemView, R$id.car_ui_preference_container_without_widget);
        View viewD2 = b2.d(preferenceViewHolder.itemView, R$id.action_widget_container);
        View viewD3 = b2.d(preferenceViewHolder.itemView, R.id.widget_frame);
        preferenceViewHolder.itemView.setFocusable(!this.mIsActionShown);
        viewD.setOnClickListener(this.mIsActionShown ? new k8(this, 5) : null);
        viewD.setClickable(this.mIsActionShown);
        viewD.setFocusable(this.mIsActionShown);
        viewD2.setVisibility(this.mIsActionShown ? 0 : 8);
        viewD3.setFocusable(this.mIsActionShown);
        if (this.mIsActionShown) {
            onBindWidgetFrame(viewD3);
        }
    }

    public void onBindWidgetFrame(@NonNull View view) {
    }

    @Override // com.android.car.ui.preference.CarUiPreference, spi.j3
    @Deprecated
    public /* bridge */ /* synthetic */ void setMessageToShowWhenDisabledPreferenceClicked(@NonNull String str) {
        super.setMessageToShowWhenDisabledPreferenceClicked(str);
    }

    public void showAction(boolean z) {
        this.mIsActionShown = z;
        notifyChanged();
    }

    public CarUiTwoActionPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    public CarUiTwoActionPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public CarUiTwoActionPreference(Context context) {
        super(context);
        init(null);
    }
}
