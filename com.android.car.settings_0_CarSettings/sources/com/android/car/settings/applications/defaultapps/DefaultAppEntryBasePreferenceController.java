package com.android.car.settings.applications.defaultapps;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.car.ui.preference.CarUiPreference;
import com.android.settingslib.applications.DefaultAppInfo;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DefaultAppEntryBasePreferenceController<V extends Preference> extends PreferenceController<V> {
    private static final Logger LOG = new Logger((Class<?>) DefaultAppEntryBasePreferenceController.class);

    protected abstract DefaultAppInfo getCurrentDefaultAppInfo();

    public DefaultAppEntryBasePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(V preference) {
        CharSequence defaultAppLabel = getDefaultAppLabel();
        if (!TextUtils.isEmpty(defaultAppLabel)) {
            preference.setSummary(defaultAppLabel);
            preference.setIcon(DefaultAppUtils.getSafeIcon(getDefaultAppIcon(), getContext().getResources().getInteger(R.integer.default_app_safe_icon_size)));
            return;
        }
        LOG.d("No default app");
        preference.setSummary(R.string.app_list_preference_none);
        preference.setIcon(null);
        if (preference instanceof CarUiPreference) {
            ((CarUiPreference) preference).setShowChevron(false);
        }
    }

    protected int getCurrentProcessUserId() {
        return UserHandle.myUserId();
    }

    private Drawable getDefaultAppIcon() {
        DefaultAppInfo app = getCurrentDefaultAppInfo();
        if (app != null) {
            return app.loadIcon();
        }
        return null;
    }

    private CharSequence getDefaultAppLabel() {
        DefaultAppInfo app = getCurrentDefaultAppInfo();
        if (app != null) {
            return app.loadLabel();
        }
        return null;
    }
}
