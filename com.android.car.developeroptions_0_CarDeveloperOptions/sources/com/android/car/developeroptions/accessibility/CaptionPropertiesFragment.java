package com.android.car.developeroptions.accessibility;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceFrameLayout;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.CaptioningManager;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.accessibility.ListDialogPreference;
import com.android.car.developeroptions.widget.SwitchBar;
import com.android.car.developeroptions.widget.ToggleSwitch;
import com.android.internal.widget.SubtitleView;
import com.android.settingslib.accessibility.AccessibilityUtils;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class CaptionPropertiesFragment extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, ListDialogPreference.OnValueChangedListener {
    private ColorPreference mBackgroundColor;
    private ColorPreference mBackgroundOpacity;
    private CaptioningManager mCaptioningManager;
    private PreferenceCategory mCustom;
    private ColorPreference mEdgeColor;
    private EdgeTypePreference mEdgeType;
    private ListPreference mFontSize;
    private ColorPreference mForegroundColor;
    private ColorPreference mForegroundOpacity;
    private LocalePreference mLocale;
    private PresetPreference mPreset;
    private SubtitleView mPreviewText;
    private View mPreviewViewport;
    private View mPreviewWindow;
    private boolean mShowingCustom;
    private SwitchBar mSwitchBar;
    private ToggleSwitch mToggleSwitch;
    private ListPreference mTypeface;
    private ColorPreference mWindowColor;
    private ColorPreference mWindowOpacity;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 3;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCaptioningManager = (CaptioningManager) getSystemService("captioning");
        addPreferencesFromResource(R.xml.captioning_settings);
        initializeAllPreferences();
        updateAllPreferences();
        refreshShowingCustom();
        installUpdateListeners();
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.captioning_preview, viewGroup, false);
        if (viewGroup instanceof PreferenceFrameLayout) {
            viewInflate.getLayoutParams().removeBorders = true;
        }
        ((ViewGroup) viewInflate.findViewById(R.id.properties_fragment)).addView(super.onCreateView(layoutInflater, viewGroup, bundle), -1, -1);
        return viewInflate;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        boolean zIsEnabled = this.mCaptioningManager.isEnabled();
        SubtitleView subtitleViewFindViewById = view.findViewById(R.id.preview_text);
        this.mPreviewText = subtitleViewFindViewById;
        subtitleViewFindViewById.setVisibility(zIsEnabled ? 0 : 4);
        this.mPreviewWindow = view.findViewById(R.id.preview_window);
        View viewFindViewById = view.findViewById(R.id.preview_viewport);
        this.mPreviewViewport = viewFindViewById;
        viewFindViewById.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.car.developeroptions.accessibility.CaptionPropertiesFragment.1
            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                CaptionPropertiesFragment.this.refreshPreviewText();
            }
        });
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        boolean zIsEnabled = this.mCaptioningManager.isEnabled();
        SwitchBar switchBar = ((SettingsActivity) getActivity()).getSwitchBar();
        this.mSwitchBar = switchBar;
        switchBar.setSwitchBarText(R.string.accessibility_caption_master_switch_title, R.string.accessibility_caption_master_switch_title);
        this.mSwitchBar.setCheckedInternal(zIsEnabled);
        this.mToggleSwitch = this.mSwitchBar.getSwitch();
        getPreferenceScreen().setEnabled(zIsEnabled);
        refreshPreviewText();
        installSwitchBarToggleSwitch();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        removeSwitchBarToggleSwitch();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshPreviewText() {
        SubtitleView subtitleView;
        FragmentActivity activity = getActivity();
        if (activity == null || (subtitleView = this.mPreviewText) == null) {
            return;
        }
        applyCaptionProperties(this.mCaptioningManager, subtitleView, this.mPreviewViewport, this.mCaptioningManager.getRawUserStyle());
        Locale locale = this.mCaptioningManager.getLocale();
        if (locale != null) {
            subtitleView.setText(AccessibilityUtils.getTextForLocale(activity, locale, R.string.captioning_preview_text));
        } else {
            subtitleView.setText(R.string.captioning_preview_text);
        }
        CaptioningManager.CaptionStyle userStyle = this.mCaptioningManager.getUserStyle();
        if (userStyle.hasWindowColor()) {
            this.mPreviewWindow.setBackgroundColor(userStyle.windowColor);
        } else {
            this.mPreviewWindow.setBackgroundColor(CaptioningManager.CaptionStyle.DEFAULT.windowColor);
        }
    }

    public static void applyCaptionProperties(CaptioningManager captioningManager, SubtitleView subtitleView, View view, int i) {
        subtitleView.setStyle(i);
        Context context = subtitleView.getContext();
        context.getContentResolver();
        float fontScale = captioningManager.getFontScale();
        if (view != null) {
            subtitleView.setTextSize((Math.max(view.getWidth() * 9, view.getHeight() * 16) / 16.0f) * 0.0533f * fontScale);
        } else {
            subtitleView.setTextSize(context.getResources().getDimension(R.dimen.caption_preview_text_size) * fontScale);
        }
        Locale locale = captioningManager.getLocale();
        if (locale != null) {
            subtitleView.setText(AccessibilityUtils.getTextForLocale(context, locale, R.string.captioning_preview_characters));
        } else {
            subtitleView.setText(R.string.captioning_preview_characters);
        }
    }

    protected void onInstallSwitchBarToggleSwitch() {
        this.mToggleSwitch.setOnBeforeCheckedChangeListener(new ToggleSwitch.OnBeforeCheckedChangeListener() { // from class: com.android.car.developeroptions.accessibility.CaptionPropertiesFragment.2
            @Override // com.android.car.developeroptions.widget.ToggleSwitch.OnBeforeCheckedChangeListener
            public boolean onBeforeCheckedChanged(ToggleSwitch toggleSwitch, boolean z) {
                CaptionPropertiesFragment.this.mSwitchBar.setCheckedInternal(z);
                Settings.Secure.putInt(CaptionPropertiesFragment.this.getActivity().getContentResolver(), "accessibility_captioning_enabled", z ? 1 : 0);
                CaptionPropertiesFragment.this.getPreferenceScreen().setEnabled(z);
                if (CaptionPropertiesFragment.this.mPreviewText != null) {
                    CaptionPropertiesFragment.this.mPreviewText.setVisibility(z ? 0 : 4);
                }
                return false;
            }
        });
    }

    private void installSwitchBarToggleSwitch() {
        onInstallSwitchBarToggleSwitch();
        this.mSwitchBar.show();
    }

    private void removeSwitchBarToggleSwitch() {
        this.mSwitchBar.hide();
        this.mToggleSwitch.setOnBeforeCheckedChangeListener(null);
    }

    private void initializeAllPreferences() {
        this.mLocale = (LocalePreference) findPreference("captioning_locale");
        this.mFontSize = (ListPreference) findPreference("captioning_font_size");
        Resources resources = getResources();
        int[] intArray = resources.getIntArray(R.array.captioning_preset_selector_values);
        String[] stringArray = resources.getStringArray(R.array.captioning_preset_selector_titles);
        PresetPreference presetPreference = (PresetPreference) findPreference("captioning_preset");
        this.mPreset = presetPreference;
        presetPreference.setValues(intArray);
        this.mPreset.setTitles(stringArray);
        this.mCustom = (PreferenceCategory) findPreference("custom");
        this.mShowingCustom = true;
        int[] intArray2 = resources.getIntArray(R.array.captioning_color_selector_values);
        String[] stringArray2 = resources.getStringArray(R.array.captioning_color_selector_titles);
        ColorPreference colorPreference = (ColorPreference) this.mCustom.findPreference("captioning_foreground_color");
        this.mForegroundColor = colorPreference;
        colorPreference.setTitles(stringArray2);
        this.mForegroundColor.setValues(intArray2);
        int[] intArray3 = resources.getIntArray(R.array.captioning_opacity_selector_values);
        String[] stringArray3 = resources.getStringArray(R.array.captioning_opacity_selector_titles);
        ColorPreference colorPreference2 = (ColorPreference) this.mCustom.findPreference("captioning_foreground_opacity");
        this.mForegroundOpacity = colorPreference2;
        colorPreference2.setTitles(stringArray3);
        this.mForegroundOpacity.setValues(intArray3);
        ColorPreference colorPreference3 = (ColorPreference) this.mCustom.findPreference("captioning_edge_color");
        this.mEdgeColor = colorPreference3;
        colorPreference3.setTitles(stringArray2);
        this.mEdgeColor.setValues(intArray2);
        int[] iArr = new int[intArray2.length + 1];
        String[] strArr = new String[stringArray2.length + 1];
        System.arraycopy(intArray2, 0, iArr, 1, intArray2.length);
        System.arraycopy(stringArray2, 0, strArr, 1, stringArray2.length);
        iArr[0] = 0;
        strArr[0] = getString(R.string.color_none);
        ColorPreference colorPreference4 = (ColorPreference) this.mCustom.findPreference("captioning_background_color");
        this.mBackgroundColor = colorPreference4;
        colorPreference4.setTitles(strArr);
        this.mBackgroundColor.setValues(iArr);
        ColorPreference colorPreference5 = (ColorPreference) this.mCustom.findPreference("captioning_background_opacity");
        this.mBackgroundOpacity = colorPreference5;
        colorPreference5.setTitles(stringArray3);
        this.mBackgroundOpacity.setValues(intArray3);
        ColorPreference colorPreference6 = (ColorPreference) this.mCustom.findPreference("captioning_window_color");
        this.mWindowColor = colorPreference6;
        colorPreference6.setTitles(strArr);
        this.mWindowColor.setValues(iArr);
        ColorPreference colorPreference7 = (ColorPreference) this.mCustom.findPreference("captioning_window_opacity");
        this.mWindowOpacity = colorPreference7;
        colorPreference7.setTitles(stringArray3);
        this.mWindowOpacity.setValues(intArray3);
        this.mEdgeType = (EdgeTypePreference) this.mCustom.findPreference("captioning_edge_type");
        this.mTypeface = (ListPreference) this.mCustom.findPreference("captioning_typeface");
    }

    private void installUpdateListeners() {
        this.mPreset.setOnValueChangedListener(this);
        this.mForegroundColor.setOnValueChangedListener(this);
        this.mForegroundOpacity.setOnValueChangedListener(this);
        this.mEdgeColor.setOnValueChangedListener(this);
        this.mBackgroundColor.setOnValueChangedListener(this);
        this.mBackgroundOpacity.setOnValueChangedListener(this);
        this.mWindowColor.setOnValueChangedListener(this);
        this.mWindowOpacity.setOnValueChangedListener(this);
        this.mEdgeType.setOnValueChangedListener(this);
        this.mTypeface.setOnPreferenceChangeListener(this);
        this.mFontSize.setOnPreferenceChangeListener(this);
        this.mLocale.setOnPreferenceChangeListener(this);
    }

    private void updateAllPreferences() {
        this.mPreset.setValue(this.mCaptioningManager.getRawUserStyle());
        this.mFontSize.setValue(Float.toString(this.mCaptioningManager.getFontScale()));
        CaptioningManager.CaptionStyle customStyle = CaptioningManager.CaptionStyle.getCustomStyle(getContentResolver());
        this.mEdgeType.setValue(customStyle.edgeType);
        this.mEdgeColor.setValue(customStyle.edgeColor);
        parseColorOpacity(this.mForegroundColor, this.mForegroundOpacity, customStyle.hasForegroundColor() ? customStyle.foregroundColor : 16777215);
        parseColorOpacity(this.mBackgroundColor, this.mBackgroundOpacity, customStyle.hasBackgroundColor() ? customStyle.backgroundColor : 16777215);
        parseColorOpacity(this.mWindowColor, this.mWindowOpacity, customStyle.hasWindowColor() ? customStyle.windowColor : 16777215);
        String str = customStyle.mRawTypeface;
        ListPreference listPreference = this.mTypeface;
        if (str == null) {
            str = "";
        }
        listPreference.setValue(str);
        String rawLocale = this.mCaptioningManager.getRawLocale();
        this.mLocale.setValue(rawLocale != null ? rawLocale : "");
    }

    private void parseColorOpacity(ColorPreference colorPreference, ColorPreference colorPreference2, int i) {
        int i2;
        int i3;
        if (!CaptioningManager.CaptionStyle.hasColor(i)) {
            i3 = (i & 255) << 24;
            i2 = 16777215;
        } else if ((i >>> 24) == 0) {
            i2 = 0;
            i3 = (i & 255) << 24;
        } else {
            i2 = i | (-16777216);
            i3 = (-16777216) & i;
        }
        colorPreference2.setValue(i3 | 16777215);
        colorPreference.setValue(i2);
    }

    private int mergeColorOpacity(ColorPreference colorPreference, ColorPreference colorPreference2) {
        int value = colorPreference.getValue();
        int value2 = colorPreference2.getValue();
        if (CaptioningManager.CaptionStyle.hasColor(value)) {
            return value == 0 ? Color.alpha(value2) : (value & 16777215) | (value2 & (-16777216));
        }
        return 16776960 | Color.alpha(value2);
    }

    private void refreshShowingCustom() {
        boolean z = this.mPreset.getValue() == -1;
        if (!z && this.mShowingCustom) {
            getPreferenceScreen().removePreference(this.mCustom);
            this.mShowingCustom = false;
        } else {
            if (!z || this.mShowingCustom) {
                return;
            }
            getPreferenceScreen().addPreference(this.mCustom);
            this.mShowingCustom = true;
        }
    }

    @Override // com.android.car.developeroptions.accessibility.ListDialogPreference.OnValueChangedListener
    public void onValueChanged(ListDialogPreference listDialogPreference, int i) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        if (this.mForegroundColor == listDialogPreference || this.mForegroundOpacity == listDialogPreference) {
            Settings.Secure.putInt(contentResolver, "accessibility_captioning_foreground_color", mergeColorOpacity(this.mForegroundColor, this.mForegroundOpacity));
        } else if (this.mBackgroundColor == listDialogPreference || this.mBackgroundOpacity == listDialogPreference) {
            Settings.Secure.putInt(contentResolver, "accessibility_captioning_background_color", mergeColorOpacity(this.mBackgroundColor, this.mBackgroundOpacity));
        } else if (this.mWindowColor == listDialogPreference || this.mWindowOpacity == listDialogPreference) {
            Settings.Secure.putInt(contentResolver, "accessibility_captioning_window_color", mergeColorOpacity(this.mWindowColor, this.mWindowOpacity));
        } else if (this.mEdgeColor == listDialogPreference) {
            Settings.Secure.putInt(contentResolver, "accessibility_captioning_edge_color", i);
        } else if (this.mPreset == listDialogPreference) {
            Settings.Secure.putInt(contentResolver, "accessibility_captioning_preset", i);
            refreshShowingCustom();
        } else if (this.mEdgeType == listDialogPreference) {
            Settings.Secure.putInt(contentResolver, "accessibility_captioning_edge_type", i);
        }
        refreshPreviewText();
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        if (this.mTypeface == preference) {
            Settings.Secure.putString(contentResolver, "accessibility_captioning_typeface", (String) obj);
        } else if (this.mFontSize == preference) {
            Settings.Secure.putFloat(contentResolver, "accessibility_captioning_font_scale", Float.parseFloat((String) obj));
        } else if (this.mLocale == preference) {
            Settings.Secure.putString(contentResolver, "accessibility_captioning_locale", (String) obj);
        }
        refreshPreviewText();
        return true;
    }
}
