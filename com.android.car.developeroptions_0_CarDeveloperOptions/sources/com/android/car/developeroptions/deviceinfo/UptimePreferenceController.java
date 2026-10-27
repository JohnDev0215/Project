package com.android.car.developeroptions.deviceinfo;

import android.content.Context;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.deviceinfo.AbstractUptimePreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class UptimePreferenceController extends AbstractUptimePreferenceController implements PreferenceControllerMixin {
    public UptimePreferenceController(Context context, Lifecycle lifecycle) {
        super(context, lifecycle);
    }
}
