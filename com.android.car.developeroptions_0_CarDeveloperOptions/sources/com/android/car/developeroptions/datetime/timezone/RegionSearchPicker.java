package com.android.car.developeroptions.datetime.timezone;

import android.content.Intent;
import android.icu.text.Collator;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.datetime.timezone.BaseTimeZoneAdapter;
import com.android.car.developeroptions.datetime.timezone.BaseTimeZonePicker;
import com.android.car.developeroptions.datetime.timezone.RegionSearchPicker;
import com.android.car.developeroptions.datetime.timezone.model.FilteredCountryTimeZones;
import com.android.car.developeroptions.datetime.timezone.model.TimeZoneData;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public class RegionSearchPicker extends BaseTimeZonePicker {
    private BaseTimeZoneAdapter<RegionItem> mAdapter;
    private TimeZoneData mTimeZoneData;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1355;
    }

    public RegionSearchPicker() {
        super(R.string.date_time_select_region, R.string.date_time_search_region, true, true);
    }

    @Override // com.android.car.developeroptions.datetime.timezone.BaseTimeZonePicker
    protected BaseTimeZoneAdapter createAdapter(TimeZoneData timeZoneData) {
        this.mTimeZoneData = timeZoneData;
        BaseTimeZoneAdapter<RegionItem> baseTimeZoneAdapter = new BaseTimeZoneAdapter<>(createAdapterItem(timeZoneData.getRegionIds()), new BaseTimeZonePicker.OnListItemClickListener() { // from class: com.android.car.developeroptions.datetime.timezone.-$$Lambda$RegionSearchPicker$fRQllLDDBIioJG_L_7bgMq_A0Po
            @Override // com.android.car.developeroptions.datetime.timezone.BaseTimeZonePicker.OnListItemClickListener
            public final void onListItemClick(BaseTimeZoneAdapter.AdapterItem adapterItem) {
                this.f$0.onListItemClick((RegionSearchPicker.RegionItem) adapterItem);
            }
        }, getLocale(), false, null);
        this.mAdapter = baseTimeZoneAdapter;
        return baseTimeZoneAdapter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onListItemClick(RegionItem regionItem) {
        String id = regionItem.getId();
        FilteredCountryTimeZones filteredCountryTimeZonesLookupCountryTimeZones = this.mTimeZoneData.lookupCountryTimeZones(id);
        FragmentActivity activity = getActivity();
        if (filteredCountryTimeZonesLookupCountryTimeZones == null || filteredCountryTimeZonesLookupCountryTimeZones.getTimeZoneIds().isEmpty()) {
            Log.e("RegionSearchPicker", "Region has no time zones: " + id);
            activity.setResult(0);
            activity.finish();
            return;
        }
        List<String> timeZoneIds = filteredCountryTimeZonesLookupCountryTimeZones.getTimeZoneIds();
        if (timeZoneIds.size() == 1) {
            getActivity().setResult(-1, new Intent().putExtra("com.android.car.developeroptions.datetime.timezone.result_region_id", id).putExtra("com.android.car.developeroptions.datetime.timezone.result_time_zone_id", timeZoneIds.get(0)));
            getActivity().finish();
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("com.android.car.developeroptions.datetime.timezone.region_id", id);
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(getContext());
        subSettingLauncher.setDestination(RegionZonePicker.class.getCanonicalName());
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.setSourceMetricsCategory(getMetricsCategory());
        subSettingLauncher.setResultListener(this, 1);
        subSettingLauncher.launch();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1) {
            if (i2 == -1) {
                getActivity().setResult(-1, intent);
            }
            getActivity().finish();
        }
    }

    private List<RegionItem> createAdapterItem(Set<String> set) {
        TreeSet treeSet = new TreeSet(new RegionInfoComparator(Collator.getInstance(getLocale())));
        LocaleDisplayNames localeDisplayNames = LocaleDisplayNames.getInstance(getLocale());
        long j = 0;
        for (String str : set) {
            treeSet.add(new RegionItem(j, str, localeDisplayNames.regionDisplayName(str)));
            j = 1 + j;
        }
        return new ArrayList(treeSet);
    }

    static class RegionItem implements BaseTimeZoneAdapter.AdapterItem {
        private final String mId;
        private final long mItemId;
        private final String mName;
        private final String[] mSearchKeys;

        @Override // com.android.car.developeroptions.datetime.timezone.BaseTimeZoneAdapter.AdapterItem
        public String getCurrentTime() {
            return null;
        }

        @Override // com.android.car.developeroptions.datetime.timezone.BaseTimeZoneAdapter.AdapterItem
        public String getIconText() {
            return null;
        }

        @Override // com.android.car.developeroptions.datetime.timezone.BaseTimeZoneAdapter.AdapterItem
        public CharSequence getSummary() {
            return null;
        }

        RegionItem(long j, String str, String str2) {
            this.mId = str;
            this.mName = str2;
            this.mItemId = j;
            this.mSearchKeys = new String[]{str, str2};
        }

        public String getId() {
            return this.mId;
        }

        @Override // com.android.car.developeroptions.datetime.timezone.BaseTimeZoneAdapter.AdapterItem
        public CharSequence getTitle() {
            return this.mName;
        }

        @Override // com.android.car.developeroptions.datetime.timezone.BaseTimeZoneAdapter.AdapterItem
        public long getItemId() {
            return this.mItemId;
        }

        @Override // com.android.car.developeroptions.datetime.timezone.BaseTimeZoneAdapter.AdapterItem
        public String[] getSearchKeys() {
            return this.mSearchKeys;
        }
    }

    private static class RegionInfoComparator implements Comparator<RegionItem> {
        private final Collator mCollator;

        RegionInfoComparator(Collator collator) {
            this.mCollator = collator;
        }

        @Override // java.util.Comparator
        public int compare(RegionItem regionItem, RegionItem regionItem2) {
            return this.mCollator.compare(regionItem.getTitle(), regionItem2.getTitle());
        }
    }
}
