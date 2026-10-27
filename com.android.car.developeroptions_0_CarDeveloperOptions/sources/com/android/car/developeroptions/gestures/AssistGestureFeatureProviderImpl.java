package com.android.car.developeroptions.gestures;

import android.content.Context;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AssistGestureFeatureProviderImpl implements AssistGestureFeatureProvider {
    @Override // com.android.car.developeroptions.gestures.AssistGestureFeatureProvider
    public boolean isSensorAvailable(Context context) {
        return false;
    }

    @Override // com.android.car.developeroptions.gestures.AssistGestureFeatureProvider
    public boolean isSupported(Context context) {
        return false;
    }

    @Override // com.android.car.developeroptions.gestures.AssistGestureFeatureProvider
    public List<AbstractPreferenceController> getControllers(Context context, Lifecycle lifecycle) {
        return new ArrayList();
    }
}
