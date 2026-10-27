package com.amazon.avod.playbackclient.subtitle.internal;

import amazon.android.config.ConfigBase;
import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.core.constants.UrlType;
import com.amazon.avod.core.subtitle.SubtitleType;
import com.amazon.avod.playbackclient.subtitle.SubtitlePresetSource;
import com.amplifyframework.core.model.ModelIdentifier;
import com.google.android.gms.cast.MediaTrack;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.WeakHashMap;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class SubtitleConfig extends ConfigBase {
    private static final ImmutableSet<String> CHARACTERS_TO_ROTATE_IN_VERTICAL_SUBTITLES = ImmutableSet.of("（", "〔", "［", "｛", "〈", "<", "《", "「", "『", "【", "｟", "〘", "〖", "«", "）", "〕", "］", "｝", "〉", ">", "》", "」", "』", "】", "｠", "〙", "〗", "»", "‐", "〜", "゠", "–", "—", "…", "‥", "。", "、", "ー", ")", "“", "：", ":", "(", "”", ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR, "―");
    private static final ImmutableSet<String> CHARACTER_ROTATION_SUPPORTED_LANGUAGE_CODES = ImmutableSet.of("ja-jp");
    private final ConfigurationValue<Boolean> mAllowSelectingUiLanguageAsFallback;
    private final ConfigurationValue<Boolean> mAreAnnotationsEnabled;
    private final ConfigurationValue<Boolean> mAreVerticalSubtitlesEnabled;
    private final ConfigurationValue<Set<String>> mCharacterRotationSupportedLanguageCodes;
    private final ConfigurationValue<String> mDefaultLanguageCode;
    private final ConfigurationValue<String> mDefaultLanguageCodes;
    private final ConfigurationValue<String> mDefaultSubtitleType;
    private final ConfigurationValue<Float> mDegreeOfRotationOfVerticalCharacters;
    private final ConfigurationValue<Boolean> mEnableRetriableSubtitleDownloads;
    private final ConfigurationValue<Boolean> mHasShownPreferredLanguageUnavailableToast;
    private final ConfigurationValue<String> mHorizontalSubtitleExtent;
    private final ConfigurationValue<Boolean> mIsDynamicPositioningEnabled;
    private final ConfigurationValue<Boolean> mIsHorizontalSubtitleFontShearEnabled;
    private final ConfigurationValue<Boolean> mIsNarrativeLanguageFilteringEnabled;
    private final ConfigurationValue<Boolean> mIsRotationOfCharactersEnabled;
    private final ConfigurationValue<Boolean> mIsSubtitleLanguageFilteringEnabled;
    private final ConfigurationValue<Boolean> mIsSubtitlesEnabledDuringPlayback;
    private final ConfigurationValue<Boolean> mIsTTMLV2FullySupported;
    private final ConfigurationValue<Boolean> mIsTTMLV2FullySupportedInRothko;
    private final ConfigurationValue<Boolean> mIsTextAlignEnabled;
    private final ConfigurationValue<Boolean> mIsTextCombineEnabled;
    private final ConfigurationValue<Boolean> mIsTextEmphasisEnabled;
    private final ConfigurationValue<Boolean> mIsVerticalSubtitleFontShearEnabled;
    private final Object mListenerLock;
    private final ConfigurationValue<String> mPreferredLanguageCode;
    private final ConfigurationValue<Integer> mPreferredPresetNumber;
    private final ConfigurationValue<String> mPreferredSubtitleType;
    private final ConfigurationValue<Integer> mPreferredTextSize;
    private SubtitlePresetSource mPresetSource;
    private final ConfigurationValue<Float> mRubyToBaseSizeScalingFactor;
    private final ConfigurationValue<Set<String>> mSetOfCharacterToRotateInVerticalSubtitle;
    private final ConfigurationValue<Boolean> mShouldHonorWhitespacesBetweenTags;
    private final ConfigurationValue<Boolean> mShouldParseRegionStyle;
    private final ConfigurationValue<Boolean> mShouldReportTTMLv2EventsPerSubtitle;
    private final ConfigurationValue<Boolean> mShouldScaleDownSubtitleVerticalBasePadding;
    private final ConfigurationValue<Boolean> mShouldSetHeightOfReplacementSpan;
    private final ConfigurationValue<Boolean> mShouldShowPreferredLanguageUnavailableToast;
    private final ConfigurationValue<Boolean> mShouldTrimNewLineOverflow;
    private final ConfigurationValue<Boolean> mShouldUseTTMLV2Parser;
    private final ConfigurationValue<Float> mSpacingScaleAroundVerticalSubtitlesRotatedCharacters;
    private final ConfigurationValue<Float> mSubtitleBasePaddingVerticalScaleDownFactor;

    @GuardedBy("mListenerLock")
    private final Set<SubtitleConfigChangeListener> mSubtitleConfigChangeListeners;
    private final ConfigurationValue<Boolean> mSubtitleDynamicPlacementCorrectionEnabled;
    private final ConfigurationValue<Long> mSubtitleLanguageInitializationLatchTimeoutInMillis;
    private final ConfigurationValue<String> mSubtitleLeftOrigin;
    private final ConfigurationValue<String> mSubtitleRightOrigin;
    private boolean mSubtitleSettingsEnabled;
    private final ConfigurationValue<String> mSubtitleTopOrigin;
    private final ConfigurationValue<Long> mSubtitleUpdatePollMillis;
    private final ConfigurationValue<Boolean> mSupportsMultipleLanguages;
    private final SubtitleTextSizeRationalizer mTextSizeRationalizer;
    private final ConfigurationValue<Boolean> mUpdateSubtitlesOnConfigChangeDuringPlayback;
    private final ConfigurationValue<String> mVerticalSubtitleExtent;

    @Deprecated
    public String getRelativePath() {
        return "closedcaptioning";
    }

    private static final class SingletonHolder {
        private static SubtitleConfig sInstance = new SubtitleConfig();

        private SingletonHolder() {
        }
    }

    public static final SubtitleConfig getInstance() {
        return SingletonHolder.sInstance;
    }

    SubtitleConfig() {
        this(new SubtitleTextSizeRationalizer());
    }

    SubtitleConfig(@Nonnull SubtitleTextSizeRationalizer subtitleTextSizeRationalizer) {
        super("com.amazon.avod.captions");
        this.mListenerLock = new Object();
        this.mSubtitleConfigChangeListeners = Collections.newSetFromMap(new WeakHashMap());
        this.mPresetSource = SubtitlePresetSource.DEFAULT;
        this.mSubtitleSettingsEnabled = true;
        this.mTextSizeRationalizer = (SubtitleTextSizeRationalizer) Preconditions.checkNotNull(subtitleTextSizeRationalizer, "rationalizer");
        ConfigType configType = ConfigType.SERVER;
        this.mDefaultLanguageCodes = newStringConfigValue("closedcaptioning_defaultCodes", "en-us,eng-us,en_us,eng_us", configType);
        this.mSupportsMultipleLanguages = newBooleanConfigValue("captions_supports_multiple_languages", true, configType);
        this.mSubtitleUpdatePollMillis = newLongConfigValue("SubtitleUpdatePollMillis", 500L, configType);
        ConfigurationValue<String> configurationValueNewStringConfigValue = newStringConfigValue("closedcaption_preferred_default_language_code", "en-US", configType);
        this.mDefaultLanguageCode = configurationValueNewStringConfigValue;
        ConfigurationValue<String> configurationValueNewStringConfigValue2 = newStringConfigValue("preferred_default_type", SubtitleType.AUDIO.name(), configType);
        this.mDefaultSubtitleType = configurationValueNewStringConfigValue2;
        this.mIsDynamicPositioningEnabled = newBooleanConfigValue("isDynamicSubtitlePositioningEnabled", true, configType);
        this.mShouldShowPreferredLanguageUnavailableToast = newBooleanConfigValue("show_subtitle_preference_unavailable_toast", true, configType);
        this.mAllowSelectingUiLanguageAsFallback = newBooleanConfigValue("captions_allow_selecting_ui_language_fallback", true, configType);
        this.mUpdateSubtitlesOnConfigChangeDuringPlayback = newBooleanConfigValue("update_subtitles_on_config_change_during_playback", true, configType);
        this.mEnableRetriableSubtitleDownloads = newBooleanConfigValue("captions_enableRetriableDownloads", true, configType);
        this.mIsSubtitleLanguageFilteringEnabled = newBooleanConfigValue("subtitle_enableFilteringByTrackGroupId", false, configType);
        this.mIsNarrativeLanguageFilteringEnabled = newBooleanConfigValue("narrative_enableFilteringByTrackGroupId", true, configType);
        this.mShouldUseTTMLV2Parser = newBooleanConfigValue("subtitle_shouldUseTTMLV2Parser", true, configType);
        this.mShouldParseRegionStyle = newBooleanConfigValue("subtitle_shouldParseRegionStyle", true, configType);
        this.mIsTTMLV2FullySupportedInRothko = newBooleanConfigValue("subtitle_isTTMLV2FullySupportedInRothko", false, configType);
        this.mIsTTMLV2FullySupported = newBooleanConfigValue("subtitle_isTTMLV2FullySupported", true, configType);
        this.mIsRotationOfCharactersEnabled = newBooleanConfigValue("subtitle_isRotationOfCharactersEnabled", true, configType);
        this.mShouldTrimNewLineOverflow = newBooleanConfigValue("subtitle_shouldTrimNewLineOverflow", true, configType);
        this.mRubyToBaseSizeScalingFactor = newFloatConfigValue("subtitle_rubyToBaseScalingFactor", 0.7f, configType);
        this.mSpacingScaleAroundVerticalSubtitlesRotatedCharacters = newFloatConfigValue("subtitle_spacingScaleAroundVerticalSubtitlesRotatedCharacters", 0.4f, configType);
        this.mSubtitleBasePaddingVerticalScaleDownFactor = newFloatConfigValue("subtitle_basePaddingVerticalScaleDownFactor", 0.4f, configType);
        this.mSubtitleDynamicPlacementCorrectionEnabled = newBooleanConfigValue("subtitle_dynamicPlacementCorrectionEnabled", true, configType);
        this.mShouldScaleDownSubtitleVerticalBasePadding = newBooleanConfigValue("subtitle_shouldScaleDownSubtitleVerticalBasePadding", true, configType);
        this.mDegreeOfRotationOfVerticalCharacters = newFloatConfigValue("subtitle_degreeOfRotationOfVerticalCharacters", 90.0f, configType);
        this.mCharacterRotationSupportedLanguageCodes = newStringSetConfigValue("subtitle_characterRotationSupportedLanguageCodes", Joiner.on(",").join(CHARACTER_ROTATION_SUPPORTED_LANGUAGE_CODES), ",", configType);
        this.mSetOfCharacterToRotateInVerticalSubtitle = newStringSetConfigValue("subtitle_setOfCharacterToRotateInVerticalSubtitle", Joiner.on(",").join(CHARACTERS_TO_ROTATE_IN_VERTICAL_SUBTITLES), ",", configType);
        this.mHorizontalSubtitleExtent = newStringConfigValue("subtitle_horizontalSubtitleExtent", "80% 15%", configType);
        this.mVerticalSubtitleExtent = newStringConfigValue("subtitle_verticalSubtitleExtent", "15% 80%", configType);
        this.mSubtitleTopOrigin = newStringConfigValue("subtitle_subtitleTopOrigin", "10% 10%", configType);
        this.mSubtitleLeftOrigin = newStringConfigValue("subtitle_subtitleLeftOrigin", "5% 10%", configType);
        this.mSubtitleRightOrigin = newStringConfigValue("subtitle_subtitleRightOrigin", "80% 10%", configType);
        this.mIsHorizontalSubtitleFontShearEnabled = newBooleanConfigValue("subtitle_horizontalSubtitleFontShearEnabled", true, configType);
        this.mIsVerticalSubtitleFontShearEnabled = newBooleanConfigValue("subtitle_verticalSubtitleFontShearEnabled", false, configType);
        this.mIsTextCombineEnabled = newBooleanConfigValue("subtitle_textCombineEnabled", true, configType);
        this.mIsTextEmphasisEnabled = newBooleanConfigValue("subtitle_textEmphasisEnabled", true, configType);
        this.mAreAnnotationsEnabled = newBooleanConfigValue("subtitle_annotationsEnabled", true, configType);
        this.mIsTextAlignEnabled = newBooleanConfigValue("subtitle_textAlignEnabled", true, configType);
        this.mAreVerticalSubtitlesEnabled = newBooleanConfigValue("subtitle_verticalSubtitlesEnabled", true, configType);
        ConfigType configType2 = ConfigType.INTERNAL;
        this.mIsSubtitlesEnabledDuringPlayback = newBooleanConfigValue("show_cc_during_playback", false, configType2);
        this.mPreferredLanguageCode = newStringConfigValue("preferred_caption_language_code", configurationValueNewStringConfigValue.getValue(), configType2);
        this.mPreferredSubtitleType = newStringConfigValue("preferred_caption_type", configurationValueNewStringConfigValue2.getValue(), configType2);
        this.mPreferredTextSize = newIntConfigValue("preferred_caption_text_size", 100, configType2);
        this.mPreferredPresetNumber = newIntConfigValue("preferred_caption_preset_number", 0, configType2);
        this.mHasShownPreferredLanguageUnavailableToast = newBooleanConfigValue("has_shown_subtitle_preference_unavailable_toast", false, configType2);
        this.mShouldHonorWhitespacesBetweenTags = newBooleanConfigValue("subtitle_shouldHonorWhitespacesBetweenTags", true, configType);
        this.mShouldSetHeightOfReplacementSpan = newBooleanConfigValue("subtitle_shouldSetHeightOfSpan", true, configType);
        this.mShouldReportTTMLv2EventsPerSubtitle = newBooleanConfigValue("subtitle_shouldReportTTMLv2EventsPerSubtitle", false, configType);
        this.mSubtitleLanguageInitializationLatchTimeoutInMillis = newLongConfigValue("subtitleLanguageInitializationLatchTimeoutInMillis", 1000L, configType);
    }

    public boolean isSubtitleLanguageFilteringEnabled() {
        return this.mIsSubtitleLanguageFilteringEnabled.getValue().booleanValue();
    }

    public boolean isNarrativeLanguageFilteringEnabled() {
        return this.mIsNarrativeLanguageFilteringEnabled.getValue().booleanValue();
    }

    public File getSubtitleFileForTitle(@Nonnull File file, @Nonnull SubtitleLanguage subtitleLanguage, @Nonnull UrlType urlType) {
        Preconditions.checkNotNull(file, "subtitleDirectory");
        Preconditions.checkNotNull(subtitleLanguage, "language");
        Preconditions.checkNotNull(urlType, "urlType");
        return new File(file, getSubtitleFileName(subtitleLanguage, urlType));
    }

    public File getLegacySubtitleFile(@Nonnull File file, @Nonnull SubtitleLanguage subtitleLanguage, @Nonnull UrlType urlType) {
        Preconditions.checkNotNull(file, "subtitleDirectory");
        Preconditions.checkNotNull(subtitleLanguage, "language");
        Preconditions.checkNotNull(urlType, "urlType");
        return new File(file, String.format(Locale.US, "caption_%s_%s.xml", subtitleLanguage.getLanguageCode(), urlType.getLegacyName()));
    }

    public Set<String> getDefaultLanguageCodes() {
        return Sets.newHashSet(this.mDefaultLanguageCodes.getValue().toLowerCase(Locale.US).split(","));
    }

    public float getDegreeOfRotation() {
        return this.mDegreeOfRotationOfVerticalCharacters.getValue().floatValue();
    }

    public Set<String> getCharacterRotationSupportedLanguageCodes() {
        return this.mCharacterRotationSupportedLanguageCodes.getValue();
    }

    public String getHorizontalSubtitleExtent() {
        return this.mHorizontalSubtitleExtent.getValue();
    }

    public String getVerticalSubtitleExtent() {
        return this.mVerticalSubtitleExtent.getValue();
    }

    public String getSubtitleTopOrigin() {
        return this.mSubtitleTopOrigin.getValue();
    }

    public String getSubtitleLeftOrigin() {
        return this.mSubtitleLeftOrigin.getValue();
    }

    public String getSubtitleRightOrigin() {
        return this.mSubtitleRightOrigin.getValue();
    }

    public boolean isHorizontalSubtitleFontShearEnabled() {
        return this.mIsHorizontalSubtitleFontShearEnabled.getValue().booleanValue();
    }

    public boolean isVerticalSubtitleFontShearEnabled() {
        return this.mIsVerticalSubtitleFontShearEnabled.getValue().booleanValue();
    }

    public boolean isTextCombineEnabled() {
        return this.mIsTextCombineEnabled.getValue().booleanValue();
    }

    public boolean areAnnotationsEnabled() {
        return this.mAreAnnotationsEnabled.getValue().booleanValue();
    }

    public boolean isTextEmphasisEnabled() {
        return this.mIsTextEmphasisEnabled.getValue().booleanValue();
    }

    public boolean isTextAlignEnabled() {
        return this.mIsTextAlignEnabled.getValue().booleanValue();
    }

    public boolean areVerticalSubtitlesEnabled() {
        return this.mAreVerticalSubtitlesEnabled.getValue().booleanValue();
    }

    public Set<String> getCharactersToRotateInVerticalSubtitles() {
        return this.mSetOfCharacterToRotateInVerticalSubtitle.getValue();
    }

    public boolean shouldHonorWhitespacesBetweenTags() {
        return this.mShouldHonorWhitespacesBetweenTags.getValue().booleanValue();
    }

    public long getSubtitleLanguageInitializationLatchTimeoutInMillis() {
        return this.mSubtitleLanguageInitializationLatchTimeoutInMillis.getValue().longValue();
    }

    public long getSubtitleUpdatePollMillis() {
        return this.mSubtitleUpdatePollMillis.getValue().longValue();
    }

    @Nonnull
    public SubtitlePreferences getSubtitlePreferences() {
        boolean zBooleanValue = this.mIsSubtitlesEnabledDuringPlayback.getValue().booleanValue();
        String value = this.mPreferredLanguageCode.getValue();
        String value2 = this.mPreferredSubtitleType.getValue();
        int iIntValue = this.mPreferredPresetNumber.getValue().intValue();
        int iIntValue2 = this.mPreferredTextSize.getValue().intValue();
        int iRationalize = this.mTextSizeRationalizer.rationalize(iIntValue2);
        if (iIntValue2 != iRationalize) {
            this.mPreferredTextSize.updateValue(Integer.valueOf(iRationalize));
        }
        SubtitlePreferences subtitlePreferences = new SubtitlePreferences();
        subtitlePreferences.setSubtitlesEnabled(zBooleanValue);
        subtitlePreferences.setLanguageCode(value);
        subtitlePreferences.setSubtitleType(SubtitleType.valueOf(value2));
        subtitlePreferences.setFontScaleInPercent(iRationalize);
        subtitlePreferences.setPresetNumber(iIntValue);
        subtitlePreferences.setPresetSource(this.mPresetSource);
        subtitlePreferences.setSubtitlesSettingsEnabled(this.mSubtitleSettingsEnabled);
        return subtitlePreferences;
    }

    public void setSubtitlePreferences(@Nonnull SubtitlePreferences subtitlePreferences) {
        this.mIsSubtitlesEnabledDuringPlayback.updateValue(Boolean.valueOf(subtitlePreferences.areSubtitlesEnabled()));
        this.mPreferredLanguageCode.updateValue(subtitlePreferences.getLanguageCode());
        SubtitleType subtitleType = subtitlePreferences.getSubtitleType();
        this.mPreferredSubtitleType.updateValue(subtitleType != null ? subtitleType.name() : null);
        this.mPreferredTextSize.updateValue(Integer.valueOf(this.mTextSizeRationalizer.rationalize(subtitlePreferences.getFontScaleInPercent())));
        this.mPreferredPresetNumber.updateValue(Integer.valueOf(subtitlePreferences.getPresetNumber()));
        this.mPresetSource = subtitlePreferences.getPresetSource();
        this.mSubtitleSettingsEnabled = subtitlePreferences.areSubtitlesSettingsEnabled();
        onConfigChanged();
    }

    public boolean isDynamicPositioningEnabled() {
        return this.mIsDynamicPositioningEnabled.getValue().booleanValue();
    }

    public boolean allowSelectingUiLanguageAsFallback() {
        return this.mAllowSelectingUiLanguageAsFallback.getValue().booleanValue();
    }

    private void onConfigChanged() {
        HashSet hashSetNewHashSet;
        synchronized (this.mListenerLock) {
            hashSetNewHashSet = Sets.newHashSet(this.mSubtitleConfigChangeListeners);
        }
        Iterator it = hashSetNewHashSet.iterator();
        while (it.hasNext()) {
            ((SubtitleConfigChangeListener) it.next()).onConfigChanged();
        }
    }

    public boolean isRetriableSubtitleDownloadsEnabled() {
        return this.mEnableRetriableSubtitleDownloads.getValue().booleanValue();
    }

    public boolean shouldUseTTMLV2Parser() {
        return this.mShouldUseTTMLV2Parser.getValue().booleanValue();
    }

    public boolean shouldParseRegionStyle() {
        return this.mShouldParseRegionStyle.getValue().booleanValue();
    }

    public boolean isTTMLV2FullySupportedInRothko() {
        return this.mIsTTMLV2FullySupportedInRothko.getValue().booleanValue();
    }

    public boolean isRotationOfCharactersEnabled() {
        return this.mIsRotationOfCharactersEnabled.getValue().booleanValue();
    }

    public boolean shouldTrimNewLineOverflow() {
        return this.mShouldTrimNewLineOverflow.getValue().booleanValue();
    }

    public float getRubyToBaseScalingFactor() {
        return this.mRubyToBaseSizeScalingFactor.getValue().floatValue();
    }

    public float getSpacingScaleAroundVerticalSubtitlesRotatedCharacters() {
        return this.mSpacingScaleAroundVerticalSubtitlesRotatedCharacters.getValue().floatValue();
    }

    public float getSubtitleBasePaddingVerticalScaleDownFactor() {
        return this.mSubtitleBasePaddingVerticalScaleDownFactor.getValue().floatValue();
    }

    private String getSubtitleFileName(SubtitleLanguage subtitleLanguage, UrlType urlType) {
        Locale locale = Locale.US;
        Object[] objArr = new Object[4];
        objArr[0] = subtitleLanguage.isForced() ? "narrative" : MediaTrack.ROLE_SUBTITLE;
        objArr[1] = subtitleLanguage.getLanguageCode();
        objArr[2] = subtitleLanguage.getSubtitleType().name();
        objArr[3] = urlType.getLegacyName();
        return String.format(locale, "%s_%s_%s_%s.xml", objArr);
    }
}
