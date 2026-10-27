package com.amazon.video.sdk.avod.playbackclient.subtitle.presenters;

import android.content.Context;
import android.widget.Toast;
import com.amazon.avod.media.download.plugin.reporting.SubtitleChangeCause;
import com.amazon.avod.playbackclient.subtitle.SubtitleLanguagePicker;
import com.amazon.avod.playbackclient.subtitle.SubtitlePresetSource;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleCollection;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleConfig;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleLanguage;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitlePreferences;
import com.amazon.avod.playbackclient.subtitle.presenters.SubtitleRenderer;
import com.amazon.avod.playbackclient.subtitle.views.SubtitleTextController;
import com.amazon.avod.playbackclient.subtitle.views.util.SubtitleRenderPreset;
import com.amazon.avod.playbackclient.subtitle.views.util.SubtitleRenderPresets;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.IETFUtils;
import com.amazon.video.player.R$string;
import com.amazon.video.sdk.avod.playbackclient.subtitle.SubtitleEventReporter;
import com.amazon.video.sdk.avod.playbackclient.subtitle.views.util.TypefaceFactory;
import com.amazon.video.sdk.stream.TimedTextConfig;
import com.amazon.video.sdk.stream.TimedTextUtilsKt;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class SubtitlePresenter {
    private Toast mAllFailureToast;
    private final Map<SubtitlePresetSource, SubtitleRenderPresets> mAvailableRenderPresets;
    private LinkedHashSet<SubtitleLanguage> mAvailableSubtitleLanguages;
    private Context mContext;
    private final Set<SubtitleLanguage> mDisabledLanguages;
    private boolean mIsInitialized;
    private final SubtitleConfig mSubtitleConfig;
    private SubtitleEventReporter mSubtitleEventReporter;
    private SubtitlePreferences mSubtitlePreferences;
    private SubtitleRenderer mSubtitleRenderer;
    private final SubtitleTextController mSubtitleTextController;
    private ImmutableSet<SubtitleLanguage> mSupportedSubtitleLanguages;
    private TimedTextConfig mTimedTextConfig;
    private final TypefaceFactory mTypefaceFactory;
    private final String mUiLanguageCode;

    public SubtitlePresenter(@Nonnull Context context, @Nonnull SubtitleTextController subtitleTextController, @Nonnull TimedTextConfig timedTextConfig) {
        this(context, subtitleTextController, IETFUtils.toIETFLanguageTag(Locale.getDefault()), SubtitleConfig.getInstance(), timedTextConfig, TypefaceFactory.getInstance(), Sets.newHashSet());
    }

    SubtitlePresenter(@Nonnull Context context, @Nonnull SubtitleTextController subtitleTextController, @Nonnull String str, @Nonnull SubtitleConfig subtitleConfig, @Nonnull TimedTextConfig timedTextConfig, @Nonnull TypefaceFactory typefaceFactory, @Nonnull Set<SubtitleLanguage> set) {
        this.mAvailableRenderPresets = Maps.newHashMap();
        this.mIsInitialized = false;
        this.mContext = (Context) Preconditions.checkNotNull(context, "context");
        this.mSubtitleTextController = (SubtitleTextController) Preconditions.checkNotNull(subtitleTextController, "subtitleTextController");
        this.mUiLanguageCode = (String) Preconditions.checkNotNull(str, "uiLanguageCode");
        this.mSubtitleConfig = (SubtitleConfig) Preconditions.checkNotNull(subtitleConfig, "subtitleConfig");
        this.mTimedTextConfig = (TimedTextConfig) Preconditions.checkNotNull(timedTextConfig, "timedTextConfig");
        this.mTypefaceFactory = (TypefaceFactory) Preconditions.checkNotNull(typefaceFactory, "typefaceFactory");
        this.mDisabledLanguages = (Set) Preconditions.checkNotNull(set, "disabledLanguages");
    }

    public void initialize(@Nonnull SubtitleRenderPresets subtitleRenderPresets, @Nonnull SubtitleRenderPresets subtitleRenderPresets2, @Nonnull SubtitleRenderer subtitleRenderer, @Nonnull SubtitleEventReporter subtitleEventReporter) {
        this.mSubtitleRenderer = (SubtitleRenderer) Preconditions.checkNotNull(subtitleRenderer, "subtitleRenderer");
        this.mSubtitleEventReporter = subtitleEventReporter;
        checkArgumentRenderPresets((SubtitleRenderPresets) Preconditions.checkNotNull(subtitleRenderPresets, "defaultAivRenderPresets"), SubtitlePresetSource.AIV_SERVICE);
        checkArgumentRenderPresets((SubtitleRenderPresets) Preconditions.checkNotNull(subtitleRenderPresets2, "defaultDeviceRenderPresets"), SubtitlePresetSource.DEVICE);
        setAvailableRenderPresetsReferences(subtitleRenderPresets);
        setAvailableRenderPresetsReferences(subtitleRenderPresets2);
        this.mIsInitialized = true;
    }

    private static void checkArgumentRenderPresets(@Nonnull SubtitleRenderPresets subtitleRenderPresets, @Nonnull SubtitlePresetSource subtitlePresetSource) {
        Preconditions.checkNotNull(subtitleRenderPresets, "Supplied defaults for [%s] were null", subtitlePresetSource);
        Preconditions.checkArgument(subtitleRenderPresets.getPresetSource() == subtitlePresetSource, "Supplied defaults for [%s] came from the wrong source [%s]", subtitlePresetSource, subtitleRenderPresets.getPresetSource());
    }

    public void addDataSource(@Nonnull SubtitleLanguage subtitleLanguage, @Nullable SubtitleCollection subtitleCollection, @Nonnull Optional<Long> optional, @Nonnull Optional<Long> optional2) {
        if (isInitialized()) {
            Preconditions.checkNotNull(subtitleLanguage, "subtitleLanguage");
            Preconditions.checkNotNull(optional, "startTime");
            Preconditions.checkNotNull(optional2, "endTime");
            if (subtitleCollection == null || subtitleCollection.isEmpty()) {
                DLog.warnf("Unable to render subtitle language %s, disabling", subtitleLanguage.toString());
                disableSubtitleLanguage(subtitleLanguage);
            } else {
                SubtitleRenderer subtitleRenderer = this.mSubtitleRenderer;
                if (subtitleRenderer != null) {
                    subtitleRenderer.addLanguage(subtitleLanguage, subtitleCollection, optional, optional2);
                }
            }
        }
    }

    public boolean clear() {
        this.mSupportedSubtitleLanguages = null;
        SubtitleRenderer subtitleRenderer = this.mSubtitleRenderer;
        if (subtitleRenderer != null) {
            subtitleRenderer.clear();
        }
        this.mSubtitleTextController.reset();
        this.mSubtitleRenderer = null;
        this.mContext = null;
        return true;
    }

    private void disableSubtitleLanguage(@Nonnull SubtitleLanguage subtitleLanguage) {
        this.mDisabledLanguages.add(subtitleLanguage);
        if (this.mDisabledLanguages.size() == this.mSupportedSubtitleLanguages.size()) {
            DLog.warnf("None of the supported subtitle languages are available: %s", this.mDisabledLanguages);
            this.mSubtitleRenderer.stopRenderingSubtitles();
            showNoSubtitlesAvailableToast();
            return;
        }
        disableSubtitlesIfUnavailable(this.mSubtitlePreferences, subtitleLanguage);
    }

    public void updateSubtitles() {
        SubtitleRenderer subtitleRenderer = this.mSubtitleRenderer;
        if (subtitleRenderer != null) {
            subtitleRenderer.render();
        }
    }

    public void setAvailableTimedTexPreferences(@Nonnull TimedTextConfig timedTextConfig) {
        this.mTimedTextConfig = (TimedTextConfig) Preconditions.checkNotNull(timedTextConfig, "timedTextConfig");
        this.mSubtitleTextController.applyRenderPreset(getSubtitleRenderPreset());
    }

    private void setAvailableRenderPresetsReferences(@Nonnull SubtitleRenderPresets subtitleRenderPresets) {
        Preconditions.checkNotNull(subtitleRenderPresets);
        DLog.logf("Storing %s", subtitleRenderPresets);
        this.mAvailableRenderPresets.put(subtitleRenderPresets.getPresetSource(), subtitleRenderPresets);
    }

    public void setAvailableLanguages(@Nonnull ImmutableSet<SubtitleLanguage> immutableSet, @Nonnull ImmutableSet<SubtitleLanguage> immutableSet2) {
        if (isInitialized()) {
            this.mSupportedSubtitleLanguages = (ImmutableSet) Preconditions.checkNotNull(immutableSet, "subtitleLanguages");
            setAvailableLanguages(immutableSet);
            if (immutableSet.isEmpty()) {
                this.mSubtitleRenderer.stopRenderingSubtitles();
            }
            updateSubtitleAvailability(Optional.absent(), false);
        }
    }

    public void updateUserPreferences(@Nonnull SubtitlePreferences subtitlePreferences, @Nonnull Optional<SubtitleChangeCause> optional) {
        if (isInitialized()) {
            SubtitlePreferences subtitlePreferences2 = this.mSubtitlePreferences;
            boolean z = (subtitlePreferences2 == null || Objects.equal(subtitlePreferences2.getLanguageCode(), subtitlePreferences.getLanguageCode())) ? false : true;
            Preconditions.checkNotNull(optional, "cause");
            this.mSubtitlePreferences = (SubtitlePreferences) Preconditions.checkNotNull(subtitlePreferences, "preferences");
            DLog.logf("Subtitle preferences have changed to %s", subtitlePreferences);
            this.mSubtitleTextController.applyLanguageCode(subtitlePreferences.getLanguageCode());
            applyRenderPreset();
            updateSubtitleAvailability(optional, z);
        }
    }

    void applyRenderPreset() {
        if (isInitialized()) {
            this.mSubtitleTextController.applyRenderPreset(getSubtitleRenderPreset());
        }
    }

    SubtitleRenderPreset getSubtitleRenderPreset() {
        return new SubtitleRenderPreset(SubtitlePresetSource.CLIENT.name(), null, this.mTypefaceFactory.getTypefaceFromFamily(this.mContext, TimedTextUtilsKt.getTimedTextFontStyle(this.mTimedTextConfig.getFontStyle())), this.mTypefaceFactory.getTypefaceTransformation(TimedTextUtilsKt.getTimedTextFontStyle(this.mTimedTextConfig.getFontStyle())), TimedTextUtilsKt.getEdgeType(this.mTimedTextConfig.getCharacterEdgeType()), TimedTextUtilsKt.getFontSize(this.mTimedTextConfig.getFontSize()), TimedTextUtilsKt.getTimedTextColor(this.mTimedTextConfig.getTextColor()), TimedTextUtilsKt.getTimedTextColor(this.mTimedTextConfig.getTextBackgroundColor()), TimedTextUtilsKt.getTimedTextColor(this.mTimedTextConfig.getTextWindowColor()));
    }

    private void updateSubtitleAvailability(@Nonnull Optional<SubtitleChangeCause> optional, boolean z) {
        SubtitlePreferences subtitlePreferences;
        if (this.mSupportedSubtitleLanguages == null || (subtitlePreferences = this.mSubtitlePreferences) == null) {
            return;
        }
        enableSubtitlesIfAvailable(subtitlePreferences, optional, z);
    }

    private void showNoSubtitlesAvailableToast() {
        Context context = this.mContext;
        Toast toastMakeText = Toast.makeText(context, context.getString(R$string.AV_MOBILE_ANDROID_ERRORS_SUBTITLES), 1);
        this.mAllFailureToast = toastMakeText;
        toastMakeText.show();
    }

    public boolean isInitialized() {
        return this.mIsInitialized;
    }

    public void setAvailableLanguages(@Nonnull ImmutableSet<SubtitleLanguage> immutableSet) {
        Preconditions.checkNotNull(immutableSet, "supportedLanguages");
        this.mAvailableSubtitleLanguages = Sets.newLinkedHashSet();
        UnmodifiableIterator<SubtitleLanguage> it = immutableSet.iterator();
        while (it.hasNext()) {
            this.mAvailableSubtitleLanguages.add(it.next());
        }
    }

    public void enableSubtitlesIfAvailable(@Nonnull SubtitlePreferences subtitlePreferences, @Nonnull Optional<SubtitleChangeCause> optional, boolean z) {
        Preconditions.checkNotNull(optional, "cause");
        Preconditions.checkNotNull(subtitlePreferences, "preferences");
        Preconditions.checkNotNull(this.mAvailableSubtitleLanguages, "Cannot enable a language before setting available languages");
        SubtitleLanguage bestMatch = new SubtitleLanguagePicker(this.mUiLanguageCode, this.mSubtitleConfig).getBestMatch(this.mAvailableSubtitleLanguages, subtitlePreferences.getLanguage());
        this.mSubtitleEventReporter.reportTimedTextEventToAloysius(subtitlePreferences.areSubtitlesEnabled(), bestMatch);
        if (!subtitlePreferences.areSubtitlesEnabled()) {
            this.mSubtitleRenderer.stopRenderingSubtitles();
        } else if (bestMatch != null) {
            this.mSubtitleRenderer.updateCurrentSubtitleLanguage(bestMatch);
            this.mSubtitleRenderer.startRenderingSubtitles();
        } else {
            disableSubtitles(subtitlePreferences);
        }
    }

    public void disableSubtitlesIfUnavailable(@Nonnull SubtitlePreferences subtitlePreferences, @Nonnull SubtitleLanguage subtitleLanguage) {
        Preconditions.checkNotNull(subtitlePreferences, "preferences");
        Preconditions.checkNotNull(subtitleLanguage, "disabledLanguage");
        Preconditions.checkNotNull(this.mAvailableSubtitleLanguages, "Cannot disable a language before setting available languages");
        this.mAvailableSubtitleLanguages.remove(subtitleLanguage);
        if (subtitleLanguage.doesFitLanguagePreference(subtitlePreferences.getLanguage())) {
            disableSubtitles(subtitlePreferences);
        }
    }

    private void disableSubtitles(@Nonnull SubtitlePreferences subtitlePreferences) {
        if (!this.mAvailableSubtitleLanguages.isEmpty()) {
            SubtitleLanguage next = this.mAvailableSubtitleLanguages.iterator().next();
            subtitlePreferences.setLanguageCode(next.getLanguageCode());
            subtitlePreferences.setSubtitleType(next.getSubtitleType());
        }
        subtitlePreferences.setSubtitlesEnabled(false);
        this.mSubtitleRenderer.stopRenderingSubtitles();
    }

    @Nullable
    public LinkedHashSet<SubtitleLanguage> getAvailableSubtitles() {
        return this.mAvailableSubtitleLanguages;
    }
}
