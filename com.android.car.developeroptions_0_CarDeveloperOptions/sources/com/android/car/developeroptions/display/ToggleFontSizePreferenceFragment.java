package com.android.car.developeroptions.display;

import android.content.ContentResolver;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.search.Indexable$SearchIndexProvider;

/* JADX INFO: loaded from: classes.dex */
public class ToggleFontSizePreferenceFragment extends PreviewSeekBarPreferenceFragment {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.display.ToggleFontSizePreferenceFragment.1
    };
    private float[] mValues;

    @Override // com.android.car.developeroptions.display.PreviewSeekBarPreferenceFragment
    protected int getActivityLayoutResId() {
        return R.layout.font_size_activity;
    }

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_font_size;
    }

    public int getMetricsCategory() {
        return 340;
    }

    @Override // com.android.car.developeroptions.display.PreviewSeekBarPreferenceFragment
    protected int[] getPreviewSampleResIds() {
        return new int[]{R.layout.font_size_preview};
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Resources resources = getContext().getResources();
        ContentResolver contentResolver = getContext().getContentResolver();
        this.mEntries = resources.getStringArray(R.array.entries_font_size);
        String[] stringArray = resources.getStringArray(R.array.entryvalues_font_size);
        this.mInitialIndex = fontSizeValueToIndex(Settings.System.getFloat(contentResolver, "font_scale", 1.0f), stringArray);
        this.mValues = new float[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            this.mValues[i] = Float.parseFloat(stringArray[i]);
        }
        getActivity().setTitle(R.string.title_font_size);
    }

    @Override // com.android.car.developeroptions.display.PreviewSeekBarPreferenceFragment
    protected Configuration createConfig(Configuration configuration, int i) {
        Configuration configuration2 = new Configuration(configuration);
        configuration2.fontScale = this.mValues[i];
        return configuration2;
    }

    @Override // com.android.car.developeroptions.display.PreviewSeekBarPreferenceFragment
    protected void commit() {
        if (getContext() == null) {
            return;
        }
        Settings.System.putFloat(getContext().getContentResolver(), "font_scale", this.mValues[this.mCurrentIndex]);
    }

    public static int fontSizeValueToIndex(float f, String[] strArr) {
        float f2 = Float.parseFloat(strArr[0]);
        int i = 1;
        while (i < strArr.length) {
            float f3 = Float.parseFloat(strArr[i]);
            if (f < f2 + ((f3 - f2) * 0.5f)) {
                return i - 1;
            }
            i++;
            f2 = f3;
        }
        return strArr.length - 1;
    }
}
