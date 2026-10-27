package com.android.car.developeroptions.language;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.dashboard.SummaryLoader;
import com.android.car.developeroptions.inputmethod.PhysicalKeyboardPreferenceController;
import com.android.car.developeroptions.inputmethod.SpellCheckerPreferenceController;
import com.android.car.developeroptions.inputmethod.VirtualKeyboardPreferenceController;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.widget.PreferenceCategoryController;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LanguageAndInputSettings extends DashboardFragment {
    public static final SummaryLoader.SummaryProviderFactory SUMMARY_PROVIDER_FACTORY = new SummaryLoader.SummaryProviderFactory() { // from class: com.android.car.developeroptions.language.-$$Lambda$LanguageAndInputSettings$Vjtme-k09dlohrVeWBaIQt9m_WE
        @Override // com.android.car.developeroptions.dashboard.SummaryLoader.SummaryProviderFactory
        public final SummaryLoader.SummaryProvider createSummaryProvider(Activity activity, SummaryLoader summaryLoader) {
            return LanguageAndInputSettings.lambda$static$0(activity, summaryLoader);
        }
    };
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.language.LanguageAndInputSettings.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.language_and_input;
            return Arrays.asList(searchIndexableResource);
        }
    };

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "LangAndInputSettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 750;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.language_and_input;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.setTitle(R.string.language_settings);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, getSettingsLifecycle());
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Lifecycle lifecycle) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PhoneLanguagePreferenceController(context));
        VirtualKeyboardPreferenceController virtualKeyboardPreferenceController = new VirtualKeyboardPreferenceController(context);
        PhysicalKeyboardPreferenceController physicalKeyboardPreferenceController = new PhysicalKeyboardPreferenceController(context, lifecycle);
        arrayList.add(virtualKeyboardPreferenceController);
        arrayList.add(physicalKeyboardPreferenceController);
        arrayList.add(new PreferenceCategoryController(context, "keyboards_category").setChildren(Arrays.asList(virtualKeyboardPreferenceController, physicalKeyboardPreferenceController)));
        TtsPreferenceController ttsPreferenceController = new TtsPreferenceController(context, "tts_settings_summary");
        arrayList.add(ttsPreferenceController);
        PointerSpeedController pointerSpeedController = new PointerSpeedController(context);
        arrayList.add(pointerSpeedController);
        arrayList.add(new PreferenceCategoryController(context, "pointer_and_tts_category").setChildren(Arrays.asList(pointerSpeedController, ttsPreferenceController)));
        arrayList.add(new SpellCheckerPreferenceController(context));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class SummaryProvider implements SummaryLoader.SummaryProvider {
        private final Context mContext;
        private final SummaryLoader mSummaryLoader;

        public SummaryProvider(Context context, SummaryLoader summaryLoader) {
            this.mContext = context;
            this.mSummaryLoader = summaryLoader;
        }

        @Override // com.android.car.developeroptions.dashboard.SummaryLoader.SummaryProvider
        public void setListening(boolean z) {
            ContentResolver contentResolver = this.mContext.getContentResolver();
            if (z) {
                String string = Settings.Secure.getString(contentResolver, "default_input_method");
                if (!TextUtils.isEmpty(string)) {
                    PackageManager packageManager = this.mContext.getPackageManager();
                    String packageName = ComponentName.unflattenFromString(string).getPackageName();
                    for (InputMethodInfo inputMethodInfo : ((InputMethodManager) this.mContext.getSystemService("input_method")).getInputMethodList()) {
                        if (TextUtils.equals(inputMethodInfo.getPackageName(), packageName)) {
                            this.mSummaryLoader.setSummary(this, inputMethodInfo.loadLabel(packageManager));
                            return;
                        }
                    }
                }
                this.mSummaryLoader.setSummary(this, "");
            }
        }
    }

    static /* synthetic */ SummaryLoader.SummaryProvider lambda$static$0(Activity activity, SummaryLoader summaryLoader) {
        return new SummaryProvider(activity, summaryLoader);
    }
}
