package com.android.car.developeroptions.display;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.hardware.display.ColorDisplayManager;
import android.hardware.display.NightDisplayListener;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NightDisplaySettings extends DashboardFragment implements NightDisplayListener.Callback {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.display.NightDisplaySettings.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.night_display_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    private ColorDisplayManager mColorDisplayManager;
    private NightDisplayListener mNightDisplayListener;

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public int getDialogMetricsCategory(int i) {
        if (i != 0) {
            return i != 1 ? 0 : 589;
        }
        return 588;
    }

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_night_display;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "NightDisplaySettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 488;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.night_display_settings;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context context = getContext();
        this.mColorDisplayManager = (ColorDisplayManager) context.getSystemService(ColorDisplayManager.class);
        this.mNightDisplayListener = new NightDisplayListener(context);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mNightDisplayListener.setCallback(this);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.mNightDisplayListener.setCallback((NightDisplayListener.Callback) null);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        if ("night_display_end_time".equals(preference.getKey())) {
            showDialog(1);
            return true;
        }
        if ("night_display_start_time".equals(preference.getKey())) {
            showDialog(0);
            return true;
        }
        return super.onPreferenceTreeClick(preference);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreateDialog$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreateDialog$0$NightDisplaySettings(int i, TimePicker timePicker, int i2, int i3) {
        LocalTime localTimeOf = LocalTime.of(i2, i3);
        if (i == 0) {
            this.mColorDisplayManager.setNightDisplayCustomStartTime(localTimeOf);
        } else {
            this.mColorDisplayManager.setNightDisplayCustomEndTime(localTimeOf);
        }
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public Dialog onCreateDialog(final int i) {
        LocalTime nightDisplayCustomEndTime;
        if (i == 0 || i == 1) {
            if (i == 0) {
                nightDisplayCustomEndTime = this.mColorDisplayManager.getNightDisplayCustomStartTime();
            } else {
                nightDisplayCustomEndTime = this.mColorDisplayManager.getNightDisplayCustomEndTime();
            }
            Context context = getContext();
            return new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() { // from class: com.android.car.developeroptions.display.-$$Lambda$NightDisplaySettings$Tk0r2eqmMzAr52uZ5NoFPaAUdfc
                @Override // android.app.TimePickerDialog.OnTimeSetListener
                public final void onTimeSet(TimePicker timePicker, int i2, int i3) {
                    this.f$0.lambda$onCreateDialog$0$NightDisplaySettings(i, timePicker, i2, i3);
                }
            }, nightDisplayCustomEndTime.getHour(), nightDisplayCustomEndTime.getMinute(), DateFormat.is24HourFormat(context));
        }
        return super.onCreateDialog(i);
    }

    public void onActivated(boolean z) {
        updatePreferenceStates();
    }

    public void onAutoModeChanged(int i) {
        updatePreferenceStates();
    }

    public void onColorTemperatureChanged(int i) {
        updatePreferenceStates();
    }

    public void onCustomStartTimeChanged(LocalTime localTime) {
        updatePreferenceStates();
    }

    public void onCustomEndTimeChanged(LocalTime localTime) {
        updatePreferenceStates();
    }
}
