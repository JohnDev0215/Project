package com.android.car.developeroptions;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.provider.SearchIndexableResource;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.dashboard.SummaryLoader;
import com.android.car.developeroptions.datetime.AutoTimeFormatPreferenceController;
import com.android.car.developeroptions.datetime.AutoTimePreferenceController;
import com.android.car.developeroptions.datetime.AutoTimeZonePreferenceController;
import com.android.car.developeroptions.datetime.DatePreferenceController;
import com.android.car.developeroptions.datetime.TimeChangeListenerMixin;
import com.android.car.developeroptions.datetime.TimeFormatPreferenceController;
import com.android.car.developeroptions.datetime.TimePreferenceController;
import com.android.car.developeroptions.datetime.TimeZonePreferenceController;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.datetime.ZoneGetter;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import com.google.android.setupcompat.util.WizardManagerHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DateTimeSettings extends DashboardFragment implements TimePreferenceController.TimePreferenceHost, DatePreferenceController.DatePreferenceHost {
    public static final SummaryLoader.SummaryProviderFactory SUMMARY_PROVIDER_FACTORY = new SummaryLoader.SummaryProviderFactory() { // from class: com.android.car.developeroptions.DateTimeSettings.1
        @Override // com.android.car.developeroptions.dashboard.SummaryLoader.SummaryProviderFactory
        public SummaryLoader.SummaryProvider createSummaryProvider(Activity activity, SummaryLoader summaryLoader) {
            return new SummaryProvider(activity, summaryLoader);
        }
    };
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new DateTimeSearchIndexProvider();

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public int getDialogMetricsCategory(int i) {
        if (i != 0) {
            return i != 1 ? 0 : 608;
        }
        return 607;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "DateTimeSettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 38;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.date_time_prefs;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        getSettingsLifecycle().addObserver(new TimeChangeListenerMixin(context, this));
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        FragmentActivity activity = getActivity();
        boolean booleanExtra = activity.getIntent().getBooleanExtra(WizardManagerHelper.EXTRA_IS_FIRST_RUN, false);
        AutoTimeZonePreferenceController autoTimeZonePreferenceController = new AutoTimeZonePreferenceController(activity, this, booleanExtra);
        AutoTimePreferenceController autoTimePreferenceController = new AutoTimePreferenceController(activity, this);
        AutoTimeFormatPreferenceController autoTimeFormatPreferenceController = new AutoTimeFormatPreferenceController(activity, this);
        arrayList.add(autoTimeZonePreferenceController);
        arrayList.add(autoTimePreferenceController);
        arrayList.add(autoTimeFormatPreferenceController);
        arrayList.add(new TimeFormatPreferenceController(activity, this, booleanExtra));
        arrayList.add(new TimeZonePreferenceController(activity, autoTimeZonePreferenceController));
        arrayList.add(new TimePreferenceController(activity, this, autoTimePreferenceController));
        arrayList.add(new DatePreferenceController(activity, this, autoTimePreferenceController));
        return arrayList;
    }

    @Override // com.android.car.developeroptions.datetime.UpdateTimeAndDateCallback
    public void updateTimeAndDateDisplay(Context context) {
        updatePreferenceStates();
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public Dialog onCreateDialog(int i) {
        if (i == 0) {
            return ((DatePreferenceController) use(DatePreferenceController.class)).buildDatePicker(getActivity());
        }
        if (i == 1) {
            return ((TimePreferenceController) use(TimePreferenceController.class)).buildTimePicker(getActivity());
        }
        throw new IllegalArgumentException();
    }

    @Override // com.android.car.developeroptions.datetime.TimePreferenceController.TimePreferenceHost
    public void showTimePicker() {
        removeDialog(1);
        showDialog(1);
    }

    @Override // com.android.car.developeroptions.datetime.DatePreferenceController.DatePreferenceHost
    public void showDatePicker() {
        showDialog(0);
    }

    private static class SummaryProvider implements SummaryLoader.SummaryProvider {
        private final Context mContext;
        private final SummaryLoader mSummaryLoader;

        public SummaryProvider(Context context, SummaryLoader summaryLoader) {
            this.mContext = context;
            this.mSummaryLoader = summaryLoader;
        }

        @Override // com.android.car.developeroptions.dashboard.SummaryLoader.SummaryProvider
        public void setListening(boolean z) {
            if (z) {
                Calendar calendar = Calendar.getInstance();
                this.mSummaryLoader.setSummary(this, ZoneGetter.getTimeZoneOffsetAndName(this.mContext, calendar.getTimeZone(), calendar.getTime()));
            }
        }
    }

    private static class DateTimeSearchIndexProvider extends BaseSearchIndexProvider {
        private DateTimeSearchIndexProvider() {
        }

        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.date_time_prefs;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    }
}
