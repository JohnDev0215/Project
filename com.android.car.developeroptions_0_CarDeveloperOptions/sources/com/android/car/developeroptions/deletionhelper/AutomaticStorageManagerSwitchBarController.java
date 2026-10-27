package com.android.car.developeroptions.deletionhelper;

import android.content.Context;
import android.os.SystemProperties;
import android.provider.Settings;
import android.widget.Switch;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import com.android.car.developeroptions.widget.SwitchBar;
import com.android.settingslib.Utils;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class AutomaticStorageManagerSwitchBarController implements SwitchBar.OnSwitchChangeListener {
    private Context mContext;
    private Preference mDaysToRetainPreference;
    private FragmentManager mFragmentManager;
    private MetricsFeatureProvider mMetrics;
    private SwitchBar mSwitchBar;

    public AutomaticStorageManagerSwitchBarController(Context context, SwitchBar switchBar, MetricsFeatureProvider metricsFeatureProvider, Preference preference, FragmentManager fragmentManager) {
        Objects.requireNonNull(context);
        this.mContext = context;
        Objects.requireNonNull(switchBar);
        this.mSwitchBar = switchBar;
        Objects.requireNonNull(metricsFeatureProvider);
        this.mMetrics = metricsFeatureProvider;
        Objects.requireNonNull(preference);
        this.mDaysToRetainPreference = preference;
        Objects.requireNonNull(fragmentManager);
        this.mFragmentManager = fragmentManager;
        initializeCheckedStatus();
    }

    private void initializeCheckedStatus() {
        this.mSwitchBar.setChecked(Utils.isStorageManagerEnabled(this.mContext));
        this.mSwitchBar.addOnSwitchChangeListener(this);
    }

    @Override // com.android.car.developeroptions.widget.SwitchBar.OnSwitchChangeListener
    public void onSwitchChanged(Switch r3, boolean z) {
        this.mMetrics.action(this.mContext, 489, z);
        this.mDaysToRetainPreference.setEnabled(z);
        Settings.Secure.putInt(this.mContext.getContentResolver(), "automatic_storage_manager_enabled", z ? 1 : 0);
        if (z) {
            maybeShowWarning();
        }
    }

    public void tearDown() {
        this.mSwitchBar.removeOnSwitchChangeListener(this);
    }

    private void maybeShowWarning() {
        if (SystemProperties.getBoolean("ro.storage_manager.enabled", false)) {
            return;
        }
        ActivationWarningFragment.newInstance().show(this.mFragmentManager, "ActivationWarningFragment");
    }
}
