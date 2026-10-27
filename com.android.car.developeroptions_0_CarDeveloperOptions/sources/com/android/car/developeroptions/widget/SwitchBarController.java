package com.android.car.developeroptions.widget;

import android.widget.Switch;
import com.android.car.developeroptions.widget.SwitchBar;
import com.android.car.developeroptions.widget.SwitchWidgetController;
import com.android.settingslib.RestrictedLockUtils;

/* JADX INFO: loaded from: classes.dex */
public class SwitchBarController extends SwitchWidgetController implements SwitchBar.OnSwitchChangeListener {
    private final SwitchBar mSwitchBar;

    public SwitchBarController(SwitchBar switchBar) {
        this.mSwitchBar = switchBar;
    }

    @Override // com.android.car.developeroptions.widget.SwitchWidgetController
    public void setupView() {
        this.mSwitchBar.show();
    }

    @Override // com.android.car.developeroptions.widget.SwitchWidgetController
    public void teardownView() {
        this.mSwitchBar.hide();
    }

    @Override // com.android.car.developeroptions.widget.SwitchWidgetController
    public void updateTitle(boolean z) {
        this.mSwitchBar.setTextViewLabelAndBackground(z);
    }

    @Override // com.android.car.developeroptions.widget.SwitchWidgetController
    public void startListening() {
        this.mSwitchBar.addOnSwitchChangeListener(this);
    }

    @Override // com.android.car.developeroptions.widget.SwitchWidgetController
    public void stopListening() {
        this.mSwitchBar.removeOnSwitchChangeListener(this);
    }

    @Override // com.android.car.developeroptions.widget.SwitchWidgetController
    public void setChecked(boolean z) {
        this.mSwitchBar.setChecked(z);
    }

    @Override // com.android.car.developeroptions.widget.SwitchWidgetController
    public boolean isChecked() {
        return this.mSwitchBar.isChecked();
    }

    @Override // com.android.car.developeroptions.widget.SwitchWidgetController
    public void setEnabled(boolean z) {
        this.mSwitchBar.setEnabled(z);
    }

    @Override // com.android.car.developeroptions.widget.SwitchBar.OnSwitchChangeListener
    public void onSwitchChanged(Switch r1, boolean z) {
        SwitchWidgetController.OnSwitchChangeListener onSwitchChangeListener = this.mListener;
        if (onSwitchChangeListener != null) {
            onSwitchChangeListener.onSwitchToggled(z);
        }
    }

    @Override // com.android.car.developeroptions.widget.SwitchWidgetController
    public void setDisabledByAdmin(RestrictedLockUtils.EnforcedAdmin enforcedAdmin) {
        this.mSwitchBar.setDisabledByAdmin(enforcedAdmin);
    }
}
