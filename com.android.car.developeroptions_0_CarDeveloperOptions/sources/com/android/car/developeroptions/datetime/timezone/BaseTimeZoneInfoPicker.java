package com.android.car.developeroptions.datetime.timezone;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.text.format.DateFormat;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.datetime.timezone.BaseTimeZoneAdapter;
import com.android.car.developeroptions.datetime.timezone.BaseTimeZoneInfoPicker;
import com.android.car.developeroptions.datetime.timezone.BaseTimeZonePicker;
import com.android.car.developeroptions.datetime.timezone.model.TimeZoneData;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseTimeZoneInfoPicker extends BaseTimeZonePicker {
    protected ZoneAdapter mAdapter;

    public abstract List<TimeZoneInfo> getAllTimeZoneInfos(TimeZoneData timeZoneData);

    protected CharSequence getHeaderText() {
        return null;
    }

    protected BaseTimeZoneInfoPicker(int i, int i2, boolean z, boolean z2) {
        super(i, i2, z, z2);
    }

    @Override // com.android.car.developeroptions.datetime.timezone.BaseTimeZonePicker
    protected BaseTimeZoneAdapter createAdapter(TimeZoneData timeZoneData) {
        ZoneAdapter zoneAdapter = new ZoneAdapter(getContext(), getAllTimeZoneInfos(timeZoneData), new BaseTimeZonePicker.OnListItemClickListener() { // from class: com.android.car.developeroptions.datetime.timezone.-$$Lambda$BaseTimeZoneInfoPicker$vLID8ZRyIWux_0eY6C4f2DTLTac
            @Override // com.android.car.developeroptions.datetime.timezone.BaseTimeZonePicker.OnListItemClickListener
            public final void onListItemClick(BaseTimeZoneAdapter.AdapterItem adapterItem) {
                this.f$0.onListItemClick((BaseTimeZoneInfoPicker.TimeZoneInfoItem) adapterItem);
            }
        }, getLocale(), getHeaderText());
        this.mAdapter = zoneAdapter;
        return zoneAdapter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onListItemClick(TimeZoneInfoItem timeZoneInfoItem) {
        getActivity().setResult(-1, prepareResultData(timeZoneInfoItem.mTimeZoneInfo));
        getActivity().finish();
    }

    protected Intent prepareResultData(TimeZoneInfo timeZoneInfo) {
        return new Intent().putExtra("com.android.car.developeroptions.datetime.timezone.result_time_zone_id", timeZoneInfo.getId());
    }

    protected static class ZoneAdapter extends BaseTimeZoneAdapter<TimeZoneInfoItem> {
        public ZoneAdapter(Context context, List<TimeZoneInfo> list, BaseTimeZonePicker.OnListItemClickListener<TimeZoneInfoItem> onListItemClickListener, Locale locale, CharSequence charSequence) {
            super(createTimeZoneInfoItems(context, list, locale), onListItemClickListener, locale, true, charSequence);
        }

        private static List<TimeZoneInfoItem> createTimeZoneInfoItems(Context context, List<TimeZoneInfo> list, Locale locale) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormat.getTimeFormatString(context), locale);
            ArrayList arrayList = new ArrayList(list.size());
            Resources resources = context.getResources();
            Iterator<TimeZoneInfo> it = list.iterator();
            long j = 0;
            while (it.hasNext()) {
                arrayList.add(new TimeZoneInfoItem(j, it.next(), resources, simpleDateFormat));
                j++;
            }
            return arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class TimeZoneInfoItem implements BaseTimeZoneAdapter.AdapterItem {
        private final long mItemId;
        private final Resources mResources;
        private final String[] mSearchKeys;
        private final android.icu.text.DateFormat mTimeFormat;
        private final TimeZoneInfo mTimeZoneInfo;
        private final String mTitle;

        @Override // com.android.car.developeroptions.datetime.timezone.BaseTimeZoneAdapter.AdapterItem
        public String getIconText() {
            return null;
        }

        private TimeZoneInfoItem(long j, TimeZoneInfo timeZoneInfo, Resources resources, android.icu.text.DateFormat dateFormat) {
            this.mItemId = j;
            this.mTimeZoneInfo = timeZoneInfo;
            this.mResources = resources;
            this.mTimeFormat = dateFormat;
            String strCreateTitle = createTitle(timeZoneInfo);
            this.mTitle = strCreateTitle;
            this.mSearchKeys = new String[]{strCreateTitle};
        }

        private static String createTitle(TimeZoneInfo timeZoneInfo) {
            String exemplarLocation = timeZoneInfo.getExemplarLocation();
            if (exemplarLocation == null) {
                exemplarLocation = timeZoneInfo.getGenericName();
            }
            if (exemplarLocation == null && timeZoneInfo.getTimeZone().inDaylightTime(new Date())) {
                exemplarLocation = timeZoneInfo.getDaylightName();
            }
            if (exemplarLocation == null) {
                exemplarLocation = timeZoneInfo.getStandardName();
            }
            return exemplarLocation == null ? String.valueOf(timeZoneInfo.getGmtOffset()) : exemplarLocation;
        }

        @Override // com.android.car.developeroptions.datetime.timezone.BaseTimeZoneAdapter.AdapterItem
        public CharSequence getTitle() {
            return this.mTitle;
        }

        @Override // com.android.car.developeroptions.datetime.timezone.BaseTimeZoneAdapter.AdapterItem
        public CharSequence getSummary() {
            String genericName = this.mTimeZoneInfo.getGenericName();
            if (genericName == null) {
                if (this.mTimeZoneInfo.getTimeZone().inDaylightTime(new Date())) {
                    genericName = this.mTimeZoneInfo.getDaylightName();
                } else {
                    genericName = this.mTimeZoneInfo.getStandardName();
                }
            }
            if (genericName == null || genericName.equals(this.mTitle)) {
                CharSequence gmtOffset = this.mTimeZoneInfo.getGmtOffset();
                return (gmtOffset == null || gmtOffset.toString().equals(this.mTitle)) ? "" : gmtOffset;
            }
            return SpannableUtil.getResourcesText(this.mResources, R.string.zone_info_offset_and_name, this.mTimeZoneInfo.getGmtOffset(), genericName);
        }

        @Override // com.android.car.developeroptions.datetime.timezone.BaseTimeZoneAdapter.AdapterItem
        public String getCurrentTime() {
            return this.mTimeFormat.format(Calendar.getInstance(this.mTimeZoneInfo.getTimeZone()));
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
}
