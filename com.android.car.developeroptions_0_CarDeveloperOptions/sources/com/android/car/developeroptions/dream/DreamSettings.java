package com.android.car.developeroptions.dream;

import android.content.Context;
import android.provider.SearchIndexableResource;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.dream.DreamBackend;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DreamSettings extends DashboardFragment {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.dream.DreamSettings.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.dream_fragment_overview;
            return Arrays.asList(searchIndexableResource);
        }
    };

    static int getDreamSettingDescriptionResId(int i) {
        return i != 0 ? i != 1 ? i != 2 ? R.string.screensaver_settings_summary_never : R.string.screensaver_settings_summary_either_long : R.string.screensaver_settings_summary_dock : R.string.screensaver_settings_summary_sleep;
    }

    static String getKeyFromSetting(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "never" : "either_charging_or_docked" : "while_docked_only" : "while_charging_only";
    }

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_screen_saver;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "DreamSettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 47;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.dream_fragment_overview;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static int getSettingFromPrefKey(java.lang.String r5) {
        /*
            int r0 = r5.hashCode()
            r1 = 3
            r2 = 0
            r3 = 2
            r4 = 1
            switch(r0) {
                case -1592701525: goto L2a;
                case -294641318: goto L20;
                case 104712844: goto L16;
                case 1019349036: goto Lc;
                default: goto Lb;
            }
        Lb:
            goto L34
        Lc:
            java.lang.String r0 = "while_charging_only"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L34
            r5 = r2
            goto L35
        L16:
            java.lang.String r0 = "never"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L34
            r5 = r1
            goto L35
        L20:
            java.lang.String r0 = "either_charging_or_docked"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L34
            r5 = r3
            goto L35
        L2a:
            java.lang.String r0 = "while_docked_only"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L34
            r5 = r4
            goto L35
        L34:
            r5 = -1
        L35:
            if (r5 == 0) goto L3e
            if (r5 == r4) goto L3d
            if (r5 == r3) goto L3c
            return r1
        L3c:
            return r3
        L3d:
            return r4
        L3e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.dream.DreamSettings.getSettingFromPrefKey(java.lang.String):int");
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context);
    }

    public static CharSequence getSummaryTextWithDreamName(Context context) {
        return getSummaryTextFromBackend(DreamBackend.getInstance(context), context);
    }

    static CharSequence getSummaryTextFromBackend(DreamBackend dreamBackend, Context context) {
        if (!dreamBackend.isEnabled()) {
            return context.getString(R.string.screensaver_settings_summary_off);
        }
        return dreamBackend.getActiveDreamName();
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new WhenToDreamPreferenceController(context));
        arrayList.add(new StartNowPreferenceController(context));
        return arrayList;
    }
}
