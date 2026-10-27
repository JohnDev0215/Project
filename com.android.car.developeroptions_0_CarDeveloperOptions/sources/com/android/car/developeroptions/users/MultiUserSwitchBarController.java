package com.android.car.developeroptions.users;

import android.content.Context;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import com.android.car.developeroptions.widget.SwitchWidgetController;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;

/* JADX INFO: loaded from: classes.dex */
public class MultiUserSwitchBarController implements SwitchWidgetController.OnSwitchChangeListener, LifecycleObserver, OnStart, OnStop {
    private final Context mContext;
    private final OnMultiUserSwitchChangedListener mListener;
    final SwitchWidgetController mSwitchBar;
    private final UserCapabilities mUserCapabilities;

    interface OnMultiUserSwitchChangedListener {
        void onMultiUserSwitchChanged(boolean z);
    }

    MultiUserSwitchBarController(Context context, SwitchWidgetController switchWidgetController, OnMultiUserSwitchChangedListener onMultiUserSwitchChangedListener) {
        this.mContext = context;
        this.mSwitchBar = switchWidgetController;
        this.mListener = onMultiUserSwitchChangedListener;
        UserCapabilities userCapabilitiesCreate = UserCapabilities.create(context);
        this.mUserCapabilities = userCapabilitiesCreate;
        this.mSwitchBar.setChecked(userCapabilitiesCreate.mUserSwitcherEnabled);
        UserCapabilities userCapabilities = this.mUserCapabilities;
        boolean z = userCapabilities.mDisallowSwitchUser;
        if (z) {
            this.mSwitchBar.setDisabledByAdmin(RestrictedLockUtilsInternal.checkIfRestrictionEnforced(this.mContext, "no_user_switch", UserHandle.myUserId()));
        } else {
            this.mSwitchBar.setEnabled((z || userCapabilities.mIsGuest || !userCapabilities.isAdmin()) ? false : true);
        }
        this.mSwitchBar.setListener(this);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mSwitchBar.startListening();
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mSwitchBar.stopListening();
    }

    @Override // com.android.car.developeroptions.widget.SwitchWidgetController.OnSwitchChangeListener
    public boolean onSwitchToggled(boolean z) {
        OnMultiUserSwitchChangedListener onMultiUserSwitchChangedListener;
        Log.d("MultiUserSwitchBarCtrl", "Toggling multi-user feature enabled state to: " + z);
        boolean zPutInt = Settings.Global.putInt(this.mContext.getContentResolver(), "user_switcher_enabled", z ? 1 : 0);
        if (zPutInt && (onMultiUserSwitchChangedListener = this.mListener) != null) {
            onMultiUserSwitchChangedListener.onMultiUserSwitchChanged(z);
        }
        return zPutInt;
    }
}
