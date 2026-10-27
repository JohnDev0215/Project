package com.amazon.video.sdk.avod.playbackclient.subtitle.download;

import com.amazon.avod.core.Subtitle;
import com.amazon.avod.media.download.plugin.PluginLoadStatus;
import com.amazon.avod.media.download.plugin.action.ContentFetcherPluginActionBase;
import com.amazon.avod.media.download.plugin.action.ContentPluginActionResult;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.playbackclient.subtitle.download.LanguageDownloadListener;
import com.amazon.avod.playbackclient.subtitle.download.LanguageSupportedListener;
import com.amazon.avod.playbackclient.subtitle.download.SubtitlePluginResponseHolder;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleConfig;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleLanguage;
import com.amazon.avod.util.DLog;
import com.amazon.video.sdk.avod.playbackclient.utils.SubtitlesLanguageHelper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
class SubtitleLanguageAction extends ContentFetcherPluginActionBase {
    private static boolean DID_SERVER_PROVIDE_SUBTITLES = true;
    private static final ImmutableSet<SubtitleLanguage> NO_SUBTITLES = ImmutableSet.of();
    private final SubtitleDownloadAction mDownloadAction;
    private final CountDownLatch mInitializationLatch;
    private final long mInitializationLatchTimeoutInMillis;
    private LanguageSupportedListener mLanguageSupportedListener;
    private final SubtitlePluginResponseHolder mSubtitlePluginResponseHolder;
    private ImmutableList<Subtitle> mSubtitles;
    private final SubtitlesLanguageHelper mSubtitlesLanguageHelper;

    SubtitleLanguageAction(@Nonnull DownloadService downloadService, @Nonnull SubtitlePluginResponseHolder subtitlePluginResponseHolder) {
        this(new SubtitleDownloadAction(downloadService, subtitlePluginResponseHolder), new SubtitlesLanguageHelper(), subtitlePluginResponseHolder, new CountDownLatch(1), SubtitleConfig.getInstance().getSubtitleLanguageInitializationLatchTimeoutInMillis());
    }

    SubtitleLanguageAction(@Nonnull SubtitleDownloadAction subtitleDownloadAction, @Nonnull SubtitlesLanguageHelper subtitlesLanguageHelper, @Nonnull SubtitlePluginResponseHolder subtitlePluginResponseHolder, @Nonnull CountDownLatch countDownLatch, long j) {
        this.mDownloadAction = (SubtitleDownloadAction) Preconditions.checkNotNull(subtitleDownloadAction, "SubtitleDownloadAction cannot be null");
        this.mSubtitlesLanguageHelper = (SubtitlesLanguageHelper) Preconditions.checkNotNull(subtitlesLanguageHelper, "SubtitlesLanguageHelper cannot be null");
        this.mSubtitlePluginResponseHolder = (SubtitlePluginResponseHolder) Preconditions.checkNotNull(subtitlePluginResponseHolder, "SubtitlePluginResponseHolder cannot be null");
        this.mInitializationLatch = (CountDownLatch) Preconditions.checkNotNull(countDownLatch, "initializationLatch");
        this.mInitializationLatchTimeoutInMillis = j;
    }

    public void initSubtitles(@Nonnull ImmutableList<Subtitle> immutableList) {
        this.mSubtitles = (ImmutableList) Preconditions.checkNotNull(immutableList, "subtitles");
    }

    public void setLanguageDownloadListener(@Nullable LanguageDownloadListener languageDownloadListener) {
        this.mDownloadAction.setLanguageDownloadListener(languageDownloadListener);
    }

    public void setLanguageSupportedListener(@Nullable LanguageSupportedListener languageSupportedListener) {
        this.mLanguageSupportedListener = languageSupportedListener;
        this.mInitializationLatch.countDown();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public ContentPluginActionResult call() {
        Preconditions.checkState(getPluginContext() != null, "Must initialize the plugin context");
        setNextAction(null);
        if (this.mSubtitles.isEmpty()) {
            return onSubtitlesUnavailable(!DID_SERVER_PROVIDE_SUBTITLES);
        }
        SubtitleProcessingResult subtitleProcessingResultProcessSubtitles = processSubtitles();
        DLog.logf("Processed all timed text from the server; result: %s", subtitleProcessingResultProcessSubtitles);
        ArrayList arrayListNewArrayList = Lists.newArrayList(Iterables.concat(subtitleProcessingResultProcessSubtitles.mForcedNarratives, subtitleProcessingResultProcessSubtitles.mSubtitles));
        ImmutableSet<SubtitleLanguage> immutableSetTransformToSubtitleLanguage = this.mSubtitlesLanguageHelper.transformToSubtitleLanguage(subtitleProcessingResultProcessSubtitles.mSubtitles);
        ImmutableSet<SubtitleLanguage> immutableSetTransformToSubtitleLanguage2 = this.mSubtitlesLanguageHelper.transformToSubtitleLanguage(subtitleProcessingResultProcessSubtitles.mForcedNarratives);
        this.mSubtitlePluginResponseHolder.getSubtitleLogBuilder().withAvailableLanguageCount(getSubtitleLanguageCount()).withMalformedLanguageCount(subtitleProcessingResultProcessSubtitles.mNumMalformedResponses).withAcceptedLanguageCount(immutableSetTransformToSubtitleLanguage.size()).withDuplicateLanguages(subtitleProcessingResultProcessSubtitles.mDuplicateLanguages);
        if (arrayListNewArrayList.isEmpty()) {
            return onSubtitlesUnavailable(DID_SERVER_PROVIDE_SUBTITLES);
        }
        this.mSubtitlePluginResponseHolder.setPluginAvailability(PluginLoadStatus.Availability.AVAILABLE);
        this.mDownloadAction.init(getPluginContext());
        this.mDownloadAction.setSubtitleList(arrayListNewArrayList);
        setNextAction(this.mDownloadAction);
        notifyAvailableLanguages(immutableSetTransformToSubtitleLanguage, immutableSetTransformToSubtitleLanguage2);
        return ContentFetcherPluginActionBase.createSuccessfulResult("Got list of subtitles by language");
    }

    private ContentPluginActionResult onSubtitlesUnavailable(boolean z) {
        Object[] objArr = new Object[1];
        objArr[0] = z ? " valid" : "";
        String str = String.format("No%s subtitles found for title", objArr);
        DLog.logf(str);
        this.mSubtitlePluginResponseHolder.setPluginAvailability(PluginLoadStatus.Availability.UNAVAILABLE);
        this.mSubtitlePluginResponseHolder.transitionToCancelled(PluginLoadStatus.CancellationReason.CONTENT_UNAVAILABLE);
        ImmutableSet<SubtitleLanguage> immutableSet = NO_SUBTITLES;
        notifyAvailableLanguages(immutableSet, immutableSet);
        return z ? ContentFetcherPluginActionBase.createFailedResult(str) : ContentFetcherPluginActionBase.createSuccessfulResult(str);
    }

    private void notifyAvailableLanguages(ImmutableSet<SubtitleLanguage> immutableSet, ImmutableSet<SubtitleLanguage> immutableSet2) {
        waitForLanguageSupportedListenerInitialization();
        LanguageSupportedListener languageSupportedListener = this.mLanguageSupportedListener;
        if (languageSupportedListener != null) {
            languageSupportedListener.onLanguagesAvailable(immutableSet, immutableSet2);
        }
    }

    private void waitForLanguageSupportedListenerInitialization() {
        try {
            this.mInitializationLatch.await(this.mInitializationLatchTimeoutInMillis, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            DLog.warnf("Interrupted while waiting for LanguageSupportedListener initialization.");
            Thread.currentThread().interrupt();
        }
    }

    @Deprecated
    private int getSubtitleLanguageCount() {
        UnmodifiableIterator<Subtitle> it = this.mSubtitles.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (!it.next().isForcedNarrative()) {
                i++;
            }
        }
        return i;
    }

    private SubtitleProcessingResult processSubtitles() {
        ImmutableList.Builder builder = ImmutableList.builder();
        ImmutableList.Builder builder2 = ImmutableList.builder();
        ImmutableSet.Builder builder3 = ImmutableSet.builder();
        HashSet hashSetNewHashSet = Sets.newHashSet();
        UnmodifiableIterator<Subtitle> it = this.mSubtitles.iterator();
        int i = 0;
        while (it.hasNext()) {
            Subtitle next = it.next();
            if (Strings.isNullOrEmpty(next.getLanguageCode()) || Strings.isNullOrEmpty(next.getDisplayName())) {
                DLog.warnf("No language code/display name available for subtitle");
                if (!next.isForcedNarrative()) {
                    i++;
                }
            } else {
                SubtitleLanguage subtitleLanguage = new SubtitleLanguage(next);
                if (!hashSetNewHashSet.add(subtitleLanguage)) {
                    DLog.logf("Skipping subtitles for %s. It is a duplicate", subtitleLanguage);
                    if (!next.isForcedNarrative()) {
                        builder3.add(subtitleLanguage);
                    }
                } else {
                    (next.isForcedNarrative() ? builder : builder2).add(next);
                }
            }
        }
        return new SubtitleProcessingResult(builder.build(), builder2.build(), builder3.build(), i);
    }

    private static class SubtitleProcessingResult {
        public final ImmutableSet<SubtitleLanguage> mDuplicateLanguages;
        public final ImmutableList<Subtitle> mForcedNarratives;
        public final int mNumMalformedResponses;
        public final ImmutableList<Subtitle> mSubtitles;

        public SubtitleProcessingResult(ImmutableList<Subtitle> immutableList, ImmutableList<Subtitle> immutableList2, ImmutableSet<SubtitleLanguage> immutableSet, int i) {
            this.mForcedNarratives = immutableList;
            this.mSubtitles = immutableList2;
            this.mDuplicateLanguages = immutableSet;
            this.mNumMalformedResponses = i;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("acceptedSubtitles", this.mSubtitles).add("acceptedNarratives", this.mForcedNarratives).add("duplicatedLanguages", this.mDuplicateLanguages).add("numMalformedResponses", this.mNumMalformedResponses).toString();
        }
    }
}
