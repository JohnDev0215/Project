package com.android.car.developeroptions.notification;

import android.content.Context;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes.dex */
public class RemoteVolumeSeekBarPreference extends VolumeSeekBarPreference {
    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreference
    public void setStream(int i) {
    }

    public RemoteVolumeSeekBarPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public RemoteVolumeSeekBarPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public RemoteVolumeSeekBarPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RemoteVolumeSeekBarPreference(Context context) {
        super(context);
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreference
    protected void init() {
        if (((VolumeSeekBarPreference) this).mSeekBar == null) {
            return;
        }
        updateIconView();
        updateSuppressionText();
    }
}
