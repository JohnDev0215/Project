package com.android.car.developeroptions.core;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.slices.Sliceable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.search.SearchIndexableRaw;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class BasePreferenceController extends AbstractPreferenceController implements Sliceable {
    public static final int AVAILABLE = 0;
    public static final int AVAILABLE_UNSEARCHABLE = 1;
    public static final int CONDITIONALLY_UNAVAILABLE = 2;
    public static final int DISABLED_DEPENDENT_SETTING = 5;
    public static final int DISABLED_FOR_USER = 4;
    private static final String TAG = "SettingsPrefController";
    public static final int UNSUPPORTED_ON_DEVICE = 3;
    protected final String mPreferenceKey;
    protected UiBlockListener mUiBlockListener;

    public interface UiBlockListener {
        void onBlockerWorkFinished(BasePreferenceController basePreferenceController);
    }

    public interface UiBlocker {
    }

    public abstract int getAvailabilityStatus();

    public int getSliceType() {
        return 0;
    }

    public void updateRawDataToIndex(List<SearchIndexableRaw> list) {
    }

    public static BasePreferenceController createInstance(Context context, String str, String str2) {
        try {
            return (BasePreferenceController) Class.forName(str).getConstructor(Context.class, String.class).newInstance(context, str2);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalStateException("Invalid preference controller: " + str, e);
        }
    }

    public static BasePreferenceController createInstance(Context context, String str) {
        try {
            return (BasePreferenceController) Class.forName(str).getConstructor(Context.class).newInstance(context);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalStateException("Invalid preference controller: " + str, e);
        }
    }

    public BasePreferenceController(Context context, String str) {
        super(context);
        this.mPreferenceKey = str;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Preference key must be set");
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return this.mPreferenceKey;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public final boolean isAvailable() {
        int availabilityStatus = getAvailabilityStatus();
        return availabilityStatus == 0 || availabilityStatus == 1 || availabilityStatus == 5;
    }

    public final boolean isSupported() {
        return getAvailabilityStatus() != 3;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        Preference preferenceFindPreference;
        super.displayPreference(preferenceScreen);
        if (getAvailabilityStatus() != 5 || (preferenceFindPreference = preferenceScreen.findPreference(getPreferenceKey())) == null) {
            return;
        }
        preferenceFindPreference.setEnabled(false);
    }

    public void updateNonIndexableKeys(List<String> list) {
        boolean z = true;
        if (isAvailable() && getAvailabilityStatus() != 1) {
            z = false;
        }
        if (z) {
            String preferenceKey = getPreferenceKey();
            if (TextUtils.isEmpty(preferenceKey)) {
                Log.w(TAG, "Skipping updateNonIndexableKeys due to empty key " + toString());
                return;
            }
            if (list.contains(preferenceKey)) {
                Log.w(TAG, "Skipping updateNonIndexableKeys, key already in list. " + toString());
                return;
            }
            list.add(preferenceKey);
        }
    }

    public void setUiBlockListener(UiBlockListener uiBlockListener) {
        this.mUiBlockListener = uiBlockListener;
    }
}
