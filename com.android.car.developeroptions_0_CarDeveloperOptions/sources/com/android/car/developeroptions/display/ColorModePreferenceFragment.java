package com.android.car.developeroptions.display;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.hardware.display.ColorDisplayManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.widget.RadioButtonPickerFragment;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import com.android.settingslib.widget.CandidateInfo;
import com.android.settingslib.widget.LayoutPreference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ColorModePreferenceFragment extends RadioButtonPickerFragment {
    static final String KEY_COLOR_MODE_AUTOMATIC = "color_mode_automatic";
    static final String KEY_COLOR_MODE_BOOSTED = "color_mode_boosted";
    static final String KEY_COLOR_MODE_NATURAL = "color_mode_natural";
    static final String KEY_COLOR_MODE_SATURATED = "color_mode_saturated";
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.display.ColorModePreferenceFragment.2
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.color_mode_settings;
            return Arrays.asList(searchIndexableResource);
        }
    };
    private ColorDisplayManager mColorDisplayManager;
    private ContentObserver mContentObserver;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1143;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.color_mode_settings;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mColorDisplayManager = (ColorDisplayManager) context.getSystemService(ColorDisplayManager.class);
        ContentResolver contentResolver = context.getContentResolver();
        this.mContentObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.android.car.developeroptions.display.ColorModePreferenceFragment.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                super.onChange(z, uri);
                if (ColorDisplayManager.areAccessibilityTransformsEnabled(ColorModePreferenceFragment.this.getContext())) {
                    ColorModePreferenceFragment.this.getActivity().finish();
                }
            }
        };
        contentResolver.registerContentObserver(Settings.Secure.getUriFor("accessibility_display_inversion_enabled"), false, this.mContentObserver, this.mUserId);
        contentResolver.registerContentObserver(Settings.Secure.getUriFor("accessibility_display_daltonizer_enabled"), false, this.mContentObserver, this.mUserId);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        if (this.mContentObserver != null) {
            getContext().getContentResolver().unregisterContentObserver(this.mContentObserver);
            this.mContentObserver = null;
        }
    }

    void configureAndInstallPreview(LayoutPreference layoutPreference, PreferenceScreen preferenceScreen) {
        layoutPreference.setSelectable(false);
        preferenceScreen.addPreference(layoutPreference);
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected void addStaticPreferences(PreferenceScreen preferenceScreen) {
        configureAndInstallPreview(new LayoutPreference(preferenceScreen.getContext(), R.layout.color_mode_preview), preferenceScreen);
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected List<? extends CandidateInfo> getCandidates() {
        Context context = getContext();
        int[] intArray = context.getResources().getIntArray(android.R.array.config_autoBrightnessLevels);
        ArrayList arrayList = new ArrayList();
        if (intArray != null) {
            for (int i : intArray) {
                if (i == 0) {
                    arrayList.add(new ColorModeCandidateInfo(context.getText(R.string.color_mode_option_natural), KEY_COLOR_MODE_NATURAL, true));
                } else if (i == 1) {
                    arrayList.add(new ColorModeCandidateInfo(context.getText(R.string.color_mode_option_boosted), KEY_COLOR_MODE_BOOSTED, true));
                } else if (i == 2) {
                    arrayList.add(new ColorModeCandidateInfo(context.getText(R.string.color_mode_option_saturated), KEY_COLOR_MODE_SATURATED, true));
                } else if (i == 3) {
                    arrayList.add(new ColorModeCandidateInfo(context.getText(R.string.color_mode_option_automatic), KEY_COLOR_MODE_AUTOMATIC, true));
                }
            }
        }
        return arrayList;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected String getDefaultKey() {
        int colorMode = this.mColorDisplayManager.getColorMode();
        return colorMode == 3 ? KEY_COLOR_MODE_AUTOMATIC : colorMode == 2 ? KEY_COLOR_MODE_SATURATED : colorMode == 1 ? KEY_COLOR_MODE_BOOSTED : KEY_COLOR_MODE_NATURAL;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0034  */
    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean setDefaultKey(java.lang.String r6) {
        /*
            r5 = this;
            int r0 = r6.hashCode()
            r1 = 0
            r2 = 3
            r3 = 2
            r4 = 1
            switch(r0) {
                case -2029194174: goto L2a;
                case -739564821: goto L20;
                case -365217559: goto L16;
                case 765917269: goto Lc;
                default: goto Lb;
            }
        Lb:
            goto L34
        Lc:
            java.lang.String r0 = "color_mode_saturated"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L34
            r6 = r3
            goto L35
        L16:
            java.lang.String r0 = "color_mode_natural"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L34
            r6 = r1
            goto L35
        L20:
            java.lang.String r0 = "color_mode_automatic"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L34
            r6 = r2
            goto L35
        L2a:
            java.lang.String r0 = "color_mode_boosted"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L34
            r6 = r4
            goto L35
        L34:
            r6 = -1
        L35:
            if (r6 == 0) goto L50
            if (r6 == r4) goto L4a
            if (r6 == r3) goto L44
            if (r6 == r2) goto L3e
            goto L55
        L3e:
            android.hardware.display.ColorDisplayManager r5 = r5.mColorDisplayManager
            r5.setColorMode(r2)
            goto L55
        L44:
            android.hardware.display.ColorDisplayManager r5 = r5.mColorDisplayManager
            r5.setColorMode(r3)
            goto L55
        L4a:
            android.hardware.display.ColorDisplayManager r5 = r5.mColorDisplayManager
            r5.setColorMode(r4)
            goto L55
        L50:
            android.hardware.display.ColorDisplayManager r5 = r5.mColorDisplayManager
            r5.setColorMode(r1)
        L55:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.display.ColorModePreferenceFragment.setDefaultKey(java.lang.String):boolean");
    }

    static class ColorModeCandidateInfo extends CandidateInfo {
        private final String mKey;
        private final CharSequence mLabel;

        @Override // com.android.settingslib.widget.CandidateInfo
        public Drawable loadIcon() {
            return null;
        }

        ColorModeCandidateInfo(CharSequence charSequence, String str, boolean z) {
            super(z);
            this.mLabel = charSequence;
            this.mKey = str;
        }

        @Override // com.android.settingslib.widget.CandidateInfo
        public CharSequence loadLabel() {
            return this.mLabel;
        }

        @Override // com.android.settingslib.widget.CandidateInfo
        public String getKey() {
            return this.mKey;
        }
    }
}
