package com.android.car.developeroptions.notification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.internal.widget.LockPatternUtils;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ChannelNotificationSettings extends NotificationSettingsBase {
    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "ChannelSettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 265;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.channel_notification_settings;
    }

    @Override // com.android.car.developeroptions.notification.NotificationSettingsBase, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        Bundle arguments = getArguments();
        if (preferenceScreen == null || arguments == null || arguments.getBoolean("fromSettings", false)) {
            return;
        }
        preferenceScreen.setInitialExpandedChildrenCount(Integer.MAX_VALUE);
    }

    @Override // com.android.car.developeroptions.notification.NotificationSettingsBase, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mUid < 0 || TextUtils.isEmpty(this.mPkg) || this.mPkgInfo == null || this.mChannel == null) {
            Log.w("ChannelSettings", "Missing package or uid or packageinfo or channel");
            finish();
            return;
        }
        for (NotificationPreferenceController notificationPreferenceController : this.mControllers) {
            notificationPreferenceController.onResume(this.mAppRow, this.mChannel, this.mChannelGroup, this.mSuspendedAppsAdmin);
            notificationPreferenceController.displayPreference(getPreferenceScreen());
        }
        updatePreferenceStates();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        for (Object obj : this.mControllers) {
            if (obj instanceof PreferenceManager.OnActivityResultListener) {
                ((PreferenceManager.OnActivityResultListener) obj).onActivityResult(i, i2, intent);
            }
        }
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        this.mControllers = arrayList;
        arrayList.add(new HeaderPreferenceController(context, this));
        this.mControllers.add(new ImportancePreferenceController(context, this.mImportanceListener, this.mBackend));
        this.mControllers.add(new MinImportancePreferenceController(context, this.mImportanceListener, this.mBackend));
        this.mControllers.add(new HighImportancePreferenceController(context, this.mImportanceListener, this.mBackend));
        this.mControllers.add(new AllowSoundPreferenceController(context, this.mImportanceListener, this.mBackend));
        this.mControllers.add(new SoundPreferenceController(context, this, this.mImportanceListener, this.mBackend));
        this.mControllers.add(new VibrationPreferenceController(context, this.mBackend));
        this.mControllers.add(new AppLinkPreferenceController(context));
        this.mControllers.add(new DescriptionPreferenceController(context));
        this.mControllers.add(new VisibilityPreferenceController(context, new LockPatternUtils(context), this.mBackend));
        this.mControllers.add(new LightsPreferenceController(context, this.mBackend));
        this.mControllers.add(new BadgePreferenceController(context, this.mBackend));
        this.mControllers.add(new DndPreferenceController(context, this.mBackend));
        this.mControllers.add(new NotificationsOffPreferenceController(context));
        return new ArrayList(this.mControllers);
    }
}
