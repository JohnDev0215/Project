package com.android.car.developeroptions.location;

import android.content.Context;
import android.os.UserManager;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.location.LocationEnabler;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public abstract class LocationBasePreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, LocationEnabler.LocationModeChangeListener {
    protected final LocationEnabler mLocationEnabler;
    protected final UserManager mUserManager;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public LocationBasePreferenceController(Context context, Lifecycle lifecycle) {
        super(context);
        this.mUserManager = (UserManager) context.getSystemService("user");
        this.mLocationEnabler = new LocationEnabler(context, this, lifecycle);
    }
}
