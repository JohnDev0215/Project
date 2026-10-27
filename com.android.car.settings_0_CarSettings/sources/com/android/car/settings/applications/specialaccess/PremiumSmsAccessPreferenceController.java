package com.android.car.settings.applications.specialaccess;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.telephony.SmsManager;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.R;
import com.android.car.settings.applications.specialaccess.AppEntryListManager;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.settingslib.applications.ApplicationsState;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class PremiumSmsAccessPreferenceController extends PreferenceController<PreferenceGroup> {
    AppEntryListManager mAppEntryListManager;
    private final AppEntryListManager.Callback mCallback;
    private List<ApplicationsState.AppEntry> mEntries;
    private final Preference.OnPreferenceChangeListener mOnPreferenceChangeListener;
    private final SmsManager mSmsManager;
    private static final Logger LOG = new Logger((Class<?>) PremiumSmsAccessPreferenceController.class);
    private static final ApplicationsState.AppFilter FILTER_SMS_STATE_KNOWN = new ApplicationsState.AppFilter() { // from class: com.android.car.settings.applications.specialaccess.PremiumSmsAccessPreferenceController.1
        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public void init() {
        }

        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public boolean filterApp(ApplicationsState.AppEntry info) {
            return (info.extraInfo == null || ((Integer) info.extraInfo).intValue() == 0) ? false : true;
        }
    };

    public PremiumSmsAccessPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mOnPreferenceChangeListener = new Preference.OnPreferenceChangeListener() { // from class: com.android.car.settings.applications.specialaccess.PremiumSmsAccessPreferenceController.2
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                PremiumSmsPreference appPreference = (PremiumSmsPreference) preference;
                ApplicationsState.AppEntry entry = appPreference.mEntry;
                int smsState = Integer.parseInt((String) newValue);
                if (smsState != ((Integer) entry.extraInfo).intValue()) {
                    PremiumSmsAccessPreferenceController.this.mSmsManager.setPremiumSmsConsent(entry.info.packageName, smsState);
                    PremiumSmsAccessPreferenceController.this.mAppEntryListManager.forceUpdate(entry);
                    return true;
                }
                return false;
            }
        };
        this.mCallback = new AppEntryListManager.Callback() { // from class: com.android.car.settings.applications.specialaccess.PremiumSmsAccessPreferenceController.3
            @Override // com.android.car.settings.applications.specialaccess.AppEntryListManager.Callback
            public void onAppEntryListChanged(List<ApplicationsState.AppEntry> entries) {
                PremiumSmsAccessPreferenceController.this.mEntries = entries;
                PremiumSmsAccessPreferenceController.this.refreshUi();
            }
        };
        this.mSmsManager = SmsManager.getDefault();
        this.mAppEntryListManager = new AppEntryListManager(context);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        this.mAppEntryListManager.init(new AppStatePremiumSmsBridge(this.mSmsManager), new AppEntryListManager.AppFilterProvider() { // from class: com.android.car.settings.applications.specialaccess.-$$Lambda$PremiumSmsAccessPreferenceController$6HDt8VFSBRPd72nPGuFlprz6LS4
            @Override // com.android.car.settings.applications.specialaccess.AppEntryListManager.AppFilterProvider
            public final ApplicationsState.AppFilter getAppFilter() {
                return PremiumSmsAccessPreferenceController.FILTER_SMS_STATE_KNOWN;
            }
        }, this.mCallback);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        this.mAppEntryListManager.start();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        this.mAppEntryListManager.stop();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onDestroyInternal() {
        this.mAppEntryListManager.destroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preference) {
        if (this.mEntries == null) {
            return;
        }
        preference.removeAll();
        for (ApplicationsState.AppEntry entry : this.mEntries) {
            Preference appPreference = new PremiumSmsPreference(getContext(), entry);
            appPreference.setOnPreferenceChangeListener(this.mOnPreferenceChangeListener);
            preference.addPreference(appPreference);
        }
    }

    private static class PremiumSmsPreference extends ListPreference {
        private final ApplicationsState.AppEntry mEntry;

        PremiumSmsPreference(Context context, ApplicationsState.AppEntry entry) {
            super(context);
            String key = entry.info.packageName + "|" + entry.info.uid;
            setKey(key);
            setTitle(entry.label);
            setIcon(entry.icon);
            setPersistent(false);
            setEntries(R.array.premium_sms_access_values);
            setEntryValues(new CharSequence[]{String.valueOf(1), String.valueOf(2), String.valueOf(3)});
            setValue(String.valueOf(entry.extraInfo));
            setSummary("%s");
            this.mEntry = entry;
        }
    }
}
