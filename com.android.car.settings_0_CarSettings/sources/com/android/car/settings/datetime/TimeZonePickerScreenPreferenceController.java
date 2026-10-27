package com.android.car.settings.datetime;

import android.app.timezonedetector.ManualTimeZoneSuggestion;
import android.app.timezonedetector.TimeZoneDetector;
import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.ui.preference.CarUiPreference;
import com.android.settingslib.datetime.ZoneGetter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class TimeZonePickerScreenPreferenceController extends PreferenceController<PreferenceGroup> {
    TimeZoneDetector mTimeZoneDetector;
    private List<Preference> mZonesList;

    public TimeZonePickerScreenPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mTimeZoneDetector = (TimeZoneDetector) getContext().getSystemService(TimeZoneDetector.class);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preferenceGroup) {
        if (this.mZonesList == null) {
            constructTimeZoneList();
        }
        for (Preference zonePreference : this.mZonesList) {
            preferenceGroup.addPreference(zonePreference);
        }
    }

    private void constructTimeZoneList() {
        List<Map<String, Object>> zones = ZoneGetter.getZonesList(getContext());
        setZonesList(zones);
    }

    void setZonesList(List<Map<String, Object>> zones) {
        Collections.sort(zones, new TimeZonesComparator());
        this.mZonesList = new ArrayList();
        for (Map<String, Object> zone : zones) {
            this.mZonesList.add(createTimeZonePreference(zone));
        }
    }

    private Preference createTimeZonePreference(final Map<String, Object> timeZone) {
        CarUiPreference preference = new CarUiPreference(getContext());
        preference.setKey(timeZone.get(ZoneGetter.KEY_ID).toString());
        preference.setTitle(timeZone.get(ZoneGetter.KEY_DISPLAY_LABEL).toString());
        preference.setSummary(timeZone.get(ZoneGetter.KEY_OFFSET_LABEL).toString());
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.settings.datetime.-$$Lambda$TimeZonePickerScreenPreferenceController$ig-QrQEpLNvQluSyKzsqV7nGV2k
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference2) {
                return this.f$0.lambda$createTimeZonePreference$0$TimeZonePickerScreenPreferenceController(timeZone, preference2);
            }
        });
        return preference;
    }

    public /* synthetic */ boolean lambda$createTimeZonePreference$0$TimeZonePickerScreenPreferenceController(Map timeZone, Preference pref) {
        String tzId = timeZone.get(ZoneGetter.KEY_ID).toString();
        ManualTimeZoneSuggestion suggestion = TimeZoneDetector.createManualTimeZoneSuggestion(tzId, "Settings: Set time zone");
        this.mTimeZoneDetector.suggestManualTimeZone(suggestion);
        getFragmentController().goBack();
        getContext().sendBroadcast(new Intent("android.intent.action.TIME_SET"));
        return true;
    }

    private static final class TimeZonesComparator implements Comparator<Map<String, Object>> {
        TimeZonesComparator() {
        }

        @Override // java.util.Comparator
        public int compare(Map<String, Object> map1, Map<String, Object> map2) {
            int timeZoneOffsetCompare = compareWithKey(map1, map2, ZoneGetter.KEY_OFFSET);
            if (timeZoneOffsetCompare == 0) {
                return compareWithKey(map1, map2, ZoneGetter.KEY_DISPLAY_LABEL);
            }
            return timeZoneOffsetCompare;
        }

        private int compareWithKey(Map<String, Object> map1, Map<String, Object> map2, String comparisonKey) {
            Object value1 = map1.get(comparisonKey);
            Object value2 = map2.get(comparisonKey);
            if (!isComparable(value1) || !isComparable(value2)) {
                throw new IllegalArgumentException("Cannot use Map which has values that are not Comparable");
            }
            return ((Comparable) value1).compareTo(value2);
        }

        private boolean isComparable(Object value) {
            return value != null && (value instanceof Comparable);
        }
    }
}
