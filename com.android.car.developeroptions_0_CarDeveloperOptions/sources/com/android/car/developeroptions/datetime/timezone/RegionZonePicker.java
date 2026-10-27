package com.android.car.developeroptions.datetime.timezone;

import android.content.Intent;
import android.icu.text.Collator;
import android.icu.text.LocaleDisplayNames;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.util.Log;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.datetime.timezone.TimeZoneInfo;
import com.android.car.developeroptions.datetime.timezone.model.FilteredCountryTimeZones;
import com.android.car.developeroptions.datetime.timezone.model.TimeZoneData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public class RegionZonePicker extends BaseTimeZoneInfoPicker {
    private String mRegionName;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1356;
    }

    public RegionZonePicker() {
        super(R.string.date_time_set_timezone_title, R.string.search_settings, true, false);
    }

    @Override // com.android.car.developeroptions.datetime.timezone.BaseTimeZonePicker, com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LocaleDisplayNames localeDisplayNames = LocaleDisplayNames.getInstance(getLocale());
        String string = getArguments() == null ? null : getArguments().getString("com.android.car.developeroptions.datetime.timezone.region_id");
        this.mRegionName = string != null ? localeDisplayNames.regionDisplayName(string) : null;
    }

    @Override // com.android.car.developeroptions.datetime.timezone.BaseTimeZoneInfoPicker
    protected CharSequence getHeaderText() {
        return this.mRegionName;
    }

    @Override // com.android.car.developeroptions.datetime.timezone.BaseTimeZoneInfoPicker
    protected Intent prepareResultData(TimeZoneInfo timeZoneInfo) {
        Intent intentPrepareResultData = super.prepareResultData(timeZoneInfo);
        intentPrepareResultData.putExtra("com.android.car.developeroptions.datetime.timezone.result_region_id", getArguments().getString("com.android.car.developeroptions.datetime.timezone.region_id"));
        return intentPrepareResultData;
    }

    @Override // com.android.car.developeroptions.datetime.timezone.BaseTimeZoneInfoPicker
    public List<TimeZoneInfo> getAllTimeZoneInfos(TimeZoneData timeZoneData) {
        if (getArguments() == null) {
            Log.e("RegionZoneSearchPicker", "getArguments() == null");
            getActivity().finish();
            return Collections.emptyList();
        }
        String string = getArguments().getString("com.android.car.developeroptions.datetime.timezone.region_id");
        FilteredCountryTimeZones filteredCountryTimeZonesLookupCountryTimeZones = timeZoneData.lookupCountryTimeZones(string);
        if (filteredCountryTimeZonesLookupCountryTimeZones == null) {
            Log.e("RegionZoneSearchPicker", "region id is not valid: " + string);
            getActivity().finish();
            return Collections.emptyList();
        }
        return getRegionTimeZoneInfo(filteredCountryTimeZonesLookupCountryTimeZones.getTimeZoneIds());
    }

    public List<TimeZoneInfo> getRegionTimeZoneInfo(Collection<String> collection) {
        TimeZoneInfo.Formatter formatter = new TimeZoneInfo.Formatter(getLocale(), new Date());
        TreeSet treeSet = new TreeSet(new TimeZoneInfoComparator(Collator.getInstance(getLocale()), new Date()));
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            TimeZone frozenTimeZone = TimeZone.getFrozenTimeZone(it.next());
            if (!frozenTimeZone.getID().equals("Etc/Unknown")) {
                treeSet.add(formatter.format(frozenTimeZone));
            }
        }
        return Collections.unmodifiableList(new ArrayList(treeSet));
    }

    static class TimeZoneInfoComparator implements Comparator<TimeZoneInfo> {
        private Collator mCollator;
        private final Date mNow;

        TimeZoneInfoComparator(Collator collator, Date date) {
            this.mCollator = collator;
            this.mNow = date;
        }

        @Override // java.util.Comparator
        public int compare(TimeZoneInfo timeZoneInfo, TimeZoneInfo timeZoneInfo2) {
            int iCompare = Integer.compare(timeZoneInfo.getTimeZone().getOffset(this.mNow.getTime()), timeZoneInfo2.getTimeZone().getOffset(this.mNow.getTime()));
            if (iCompare == 0) {
                iCompare = Integer.compare(timeZoneInfo.getTimeZone().getRawOffset(), timeZoneInfo2.getTimeZone().getRawOffset());
            }
            if (iCompare == 0) {
                iCompare = this.mCollator.compare(timeZoneInfo.getExemplarLocation(), timeZoneInfo2.getExemplarLocation());
            }
            return (iCompare != 0 || timeZoneInfo.getGenericName() == null || timeZoneInfo2.getGenericName() == null) ? iCompare : this.mCollator.compare(timeZoneInfo.getGenericName(), timeZoneInfo2.getGenericName());
        }
    }
}
