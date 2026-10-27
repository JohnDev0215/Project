package com.amazon.avod.content.smoothstream.manifest;

import amazon.android.config.ConfigurationValue;
import android.util.Pair;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.event.RetriableServiceErrorEvent;
import com.amazon.avod.content.event.StaleManifestErrorEvent;
import com.amazon.avod.content.smoothstream.PeriodAwareStreamIndex;
import com.amazon.avod.content.smoothstream.manifest.PeriodView;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class RefreshableManifestValidator {
    private final RefreshableManifestValidatorConfig mConfig;
    private int mConsecutiveStaleManifestCount;
    private final ContentManagementEventBus mContentManagementEventBus;
    private int mErrorCounter;
    private final NearStaleManifestWatchDog mNearStaleManifestWatchDog;
    private final PlaybackEventReporter mPlaybackEventReporter;
    private final PlaybackPmetMetricReporter mPlaybackPmetMetricReporter;
    private final boolean mShouldReportRetriableManifestErrorToAloysius;
    private final Stopwatch mStopwatch;
    private final VideoSpecification mVideoSpecification;

    private enum ErrorLevel {
        WARN,
        ERROR,
        FATAL
    }

    private String getErrorCountBucket(int i) {
        return (i < 1 || i >= 6) ? (i < 6 || i >= 10) ? i >= 10 ? "10+" : "non-positive" : "6-9" : "1-5";
    }

    public RefreshableManifestValidator(@Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull ContentManagementEventBus contentManagementEventBus, VideoSpecification videoSpecification) {
        this(playbackEventReporter, RefreshableManifestValidatorConfig.getInstance(), videoSpecification, contentManagementEventBus, PlaybackPmetMetricReporter.getInstance(), new NearStaleManifestWatchDog(playbackEventReporter, videoSpecification, RefreshableManifestValidatorConfig.getInstance().getWindowSizeInMillis().longValue(), contentManagementEventBus, AloysiusConfig.getInstance().shouldReportRetriableManifestError()), Stopwatch.createUnstarted(Tickers.androidTicker()), AloysiusConfig.getInstance().shouldReportRetriableManifestError());
    }

    public RefreshableManifestValidator(@Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull RefreshableManifestValidatorConfig refreshableManifestValidatorConfig, @Nonnull VideoSpecification videoSpecification, @Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull PlaybackPmetMetricReporter playbackPmetMetricReporter, @Nonnull NearStaleManifestWatchDog nearStaleManifestWatchDog, @Nonnull Stopwatch stopwatch, boolean z) {
        this.mPlaybackEventReporter = (PlaybackEventReporter) Preconditions.checkNotNull(playbackEventReporter, "playbackEventReporter");
        this.mConfig = (RefreshableManifestValidatorConfig) Preconditions.checkNotNull(refreshableManifestValidatorConfig, "config");
        this.mStopwatch = (Stopwatch) Preconditions.checkNotNull(stopwatch, NotificationCompat.CATEGORY_STOPWATCH);
        this.mContentManagementEventBus = (ContentManagementEventBus) Preconditions.checkNotNull(contentManagementEventBus, "contentManagementEventBus");
        this.mVideoSpecification = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "videoSpecification");
        this.mPlaybackPmetMetricReporter = (PlaybackPmetMetricReporter) Preconditions.checkNotNull(playbackPmetMetricReporter, "playbackPmetMetricReporter");
        this.mNearStaleManifestWatchDog = (NearStaleManifestWatchDog) Preconditions.checkNotNull(nearStaleManifestWatchDog, "nearStaleManifestWatchDog");
        this.mShouldReportRetriableManifestErrorToAloysius = z;
    }

    public void validate(Manifest manifest, Manifest manifest2, ContentUrl contentUrl) throws ContentException {
        if (this.mConfig.isManifestValidationEnabled()) {
            for (StreamIndex streamIndex : manifest.getStreams()) {
                try {
                    if (streamIndex.isVideo()) {
                        validate(streamIndex, manifest2.getVideoStream());
                    } else if (streamIndex.isAudio()) {
                        StreamIndex audioStream = manifest2.getAudioStream(streamIndex.getFourCC(), streamIndex.getLanguage());
                        if (audioStream != null) {
                            validate(streamIndex, audioStream);
                        } else {
                            throwInvalidOperationException(String.format(Locale.US, "audio streamIndex is missing, fourCC: %s, language: %s", streamIndex.getFourCC(), streamIndex.getLanguage()), "MISSING_AUDIO_STREAM", ErrorLevel.ERROR);
                        }
                    }
                } catch (InvalidManifestRefreshOperationException e) {
                    if (this.mConfig.isThrowExceptionEnabled()) {
                        throw e;
                    }
                }
            }
            resetErrorCounter();
        }
        if (this.mConfig.isStaleManifestdetectionEnabled()) {
            checkStaleManifest(manifest.getVideoStream(), manifest2.getVideoStream(), contentUrl);
        }
    }

    @VisibleForTesting
    void checkStaleManifest(StreamIndex streamIndex, StreamIndex streamIndex2, ContentUrl contentUrl) throws ContentException {
        List<PeriodView.Period> periods = getPeriods(streamIndex);
        List<PeriodView.Period> periods2 = getPeriods(streamIndex2);
        if (periods.size() == 0 || periods2.size() == 0) {
            return;
        }
        PeriodView.Period period = periods.get(periods.size() - 1);
        PeriodView.Period period2 = periods2.get(periods2.size() - 1);
        DLog.logf("previous period : current period  %d : %d", Long.valueOf(((Integer) period.getIndexRange().upperEndpoint()).longValue()), Long.valueOf(((Integer) period2.getIndexRange().upperEndpoint()).longValue()));
        long jLongValue = ((Integer) period2.getIndexRange().upperEndpoint()).longValue() - ((Integer) period.getIndexRange().upperEndpoint()).longValue();
        if (period.getId().equals(period2.getId()) && jLongValue <= 0) {
            if (!this.mStopwatch.isRunning()) {
                this.mStopwatch.start();
            }
            this.mConsecutiveStaleManifestCount++;
            Stopwatch stopwatch = this.mStopwatch;
            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
            long jElapsed = stopwatch.elapsed(timeUnit);
            Locale locale = Locale.US;
            String str = String.format(locale, "Stale manifest sequence, count:%d, timeElapsedMillis: %d, PeriodId: %s, Prev: %s, Current: %s, numNewFragments %d", Integer.valueOf(this.mConsecutiveStaleManifestCount), Long.valueOf(timeUnit.toMillis(jElapsed)), period2.getId(), period.getIndexRange(), period2.getIndexRange(), Long.valueOf(jLongValue));
            DLog.logf(str);
            String str2 = jLongValue < 0 ? "StaleOldManifest" : "StaleSameManifest";
            reportNonFatalError(str2, str, String.format(locale, "Number of new fragments is %d", Long.valueOf(jLongValue)));
            this.mPlaybackPmetMetricReporter.reportLivePlaybackStaleManifestErrorEvent(jLongValue, contentUrl.getCdnName(), contentUrl.getOrigin(), this.mVideoSpecification.getTitleId());
            ContentException contentException = new ContentException(ContentException.ContentError.LIVE_STALE_MANIFEST, str);
            if (this.mShouldReportRetriableManifestErrorToAloysius) {
                this.mContentManagementEventBus.postEvent(new RetriableServiceErrorEvent(new PlayableContent(this.mVideoSpecification.getTitleId(), this.mVideoSpecification.isTrailer()), ContentSessionType.STREAMING, contentException, null, null, null, str2, str));
            }
            if (this.mConfig.isRestartOnStaleManifestEnabled()) {
                this.mContentManagementEventBus.postEvent(new StaleManifestErrorEvent(new PlayableContent(this.mVideoSpecification.getTitleId(), this.mVideoSpecification.isTrailer()), ContentSessionType.STREAMING, contentException, this.mConsecutiveStaleManifestCount, jElapsed));
                throw contentException;
            }
            throw contentException;
        }
        this.mStopwatch.reset();
        this.mConsecutiveStaleManifestCount = 0;
        if (this.mConfig.isNearStaleManifestDetectionEnabled()) {
            this.mNearStaleManifestWatchDog.observeAndReport(period, period2, contentUrl.getCdnName(), contentUrl.getOrigin());
        }
    }

    private void validate(StreamIndex streamIndex, StreamIndex streamIndex2) throws ContentException {
        String strCreateStreamIndexId = createStreamIndexId(streamIndex);
        DLog.devf("validating a new manifest, streamIndexId: %s", strCreateStreamIndexId);
        List<PeriodView.Period> periods = getPeriods(streamIndex);
        List<PeriodView.Period> periods2 = getPeriods(streamIndex2);
        Pair<Integer, Integer> pairCheckPeriodIntersections = checkPeriodIntersections(periods, periods2, strCreateStreamIndexId);
        Iterator<PeriodView.Period> itCreateSubListIterator = createSubListIterator(periods, ((Integer) pairCheckPeriodIntersections.first).intValue());
        Iterator<PeriodView.Period> itCreateSubListIterator2 = createSubListIterator(periods2, ((Integer) pairCheckPeriodIntersections.second).intValue());
        char c = 1;
        PeriodView.Period periodCheckTimeGapAcrossPeriods = null;
        boolean z = true;
        while (itCreateSubListIterator.hasNext()) {
            PeriodView.Period next = itCreateSubListIterator.next();
            if (!itCreateSubListIterator2.hasNext()) {
                Locale locale = Locale.US;
                Object[] objArr = new Object[4];
                objArr[0] = strCreateStreamIndexId;
                objArr[c] = next.getId();
                objArr[2] = Joiner.on(",").join(getPeriodIds(periods)).toString();
                objArr[3] = Joiner.on(",").join(getPeriodIds(periods2)).toString();
                throwInvalidOperationException(String.format(locale, "current manifest has less periods than the previous one, streamIndexId: %s, missing period: %s, previous periods: %s, current periods: %s", objArr), createErrorType("CURRENT_HAS_LESS_PERIODS", strCreateStreamIndexId), ErrorLevel.ERROR);
            }
            PeriodView.Period next2 = itCreateSubListIterator2.next();
            if (!next.getId().equals(next2.getId())) {
                throwInvalidOperationException(String.format(Locale.US, "missing period on the refreshed version, streamIndexId: %s, previous period: %s", strCreateStreamIndexId, next.getId()), createErrorType("CURRENT_MISSING_PERIOD", strCreateStreamIndexId), ErrorLevel.ERROR);
            }
            checkPeriodsLowerBound(next, next2, z, strCreateStreamIndexId);
            checkPeriodsUpperBound(next, next2, !itCreateSubListIterator.hasNext(), strCreateStreamIndexId);
            periodCheckTimeGapAcrossPeriods = checkTimeGapAcrossPeriods(periodCheckTimeGapAcrossPeriods, next2, strCreateStreamIndexId);
            z = false;
            c = 1;
        }
        while (itCreateSubListIterator2.hasNext()) {
            periodCheckTimeGapAcrossPeriods = checkTimeGapAcrossPeriods(periodCheckTimeGapAcrossPeriods, itCreateSubListIterator2.next(), strCreateStreamIndexId);
        }
    }

    private String createStreamIndexId(StreamIndex streamIndex) {
        if (streamIndex.isVideo()) {
            return streamIndex.getType().name();
        }
        return String.format(Locale.US, "%s-%s-%s", streamIndex.getType(), streamIndex.getLanguage(), streamIndex.getFourCC());
    }

    private String createErrorType(String str, String str2) {
        return String.format(Locale.US, "%s_%s", str, str2);
    }

    private Pair<Integer, Integer> checkPeriodIntersections(List<PeriodView.Period> list, List<PeriodView.Period> list2, String str) throws ContentException {
        Pair<Integer, Integer> firstIntersectionIndex = getFirstIntersectionIndex(list, list2);
        if (firstIntersectionIndex == null) {
            if (isCurrentPeriodAfterPreviousPeriod(list, list2)) {
                throwInvalidOperationException(String.format(Locale.US, "no intersection across manifests, streamIndexId: %s, previous periods: %s, current periods: %s", str, Joiner.on(",").join(getPeriodIds(list)).toString(), Joiner.on(",").join(getPeriodIds(list2)).toString()), createErrorType("NO_INTERSECTION", str), ErrorLevel.FATAL);
            } else {
                throwInvalidOperationException(String.format(Locale.US, "no intersection across manifests but current start period is before than previous start period (manifest stale), streamIndexId: %s, previous periods: %s, current periods: %s", str, Joiner.on(",").join(getPeriodIds(list)).toString(), Joiner.on(",").join(getPeriodIds(list2)).toString()), createErrorType("NO_INTERSECTION_STALE_MANIFEST", str), ErrorLevel.ERROR);
            }
        }
        return firstIntersectionIndex;
    }

    private boolean isCurrentPeriodAfterPreviousPeriod(List<PeriodView.Period> list, List<PeriodView.Period> list2) {
        return (list.isEmpty() || list2.isEmpty() || ((Long) list2.get(0).getTimeRangeInNanos().lowerEndpoint()).longValue() < ((Long) list.get(0).getTimeRangeInNanos().lowerEndpoint()).longValue()) ? false : true;
    }

    private void checkPeriodsLowerBound(PeriodView.Period period, PeriodView.Period period2, boolean z, String str) throws ContentException {
        if (z) {
            if (((Integer) period.getIndexRange().lowerEndpoint()).intValue() > ((Integer) period2.getIndexRange().lowerEndpoint()).intValue()) {
                throwInvalidOperationException(String.format(Locale.US, "current first period starts before than the previous period, streamIndexId: %s, periodId: %s, previous index: %d, current index: %d", str, period.getId(), period.getIndexRange().lowerEndpoint(), period2.getIndexRange().lowerEndpoint()), createErrorType("CURRENT_FIRST_PERIOD_STARTS_AFTER_PREVIOUS", str), ErrorLevel.WARN);
            }
        } else {
            if (((Integer) period.getIndexRange().lowerEndpoint()).equals(period2.getIndexRange().lowerEndpoint())) {
                return;
            }
            throwInvalidOperationException(String.format(Locale.US, "manifests have different start indexes, streamIndexId: %s, periodId: %s, previous index: %d, current index: %d", str, period.getId(), period.getIndexRange().lowerEndpoint(), period2.getIndexRange().lowerEndpoint()), createErrorType("PERIOD_START_TIME_MISMATCH", str), ErrorLevel.WARN);
        }
    }

    private void checkPeriodsUpperBound(PeriodView.Period period, PeriodView.Period period2, boolean z, String str) throws ContentException {
        if (z) {
            if (((Integer) period.getIndexRange().upperEndpoint()).intValue() > ((Integer) period2.getIndexRange().upperEndpoint()).intValue()) {
                throwInvalidOperationException(String.format(Locale.US, "previous last period ends after than the current period, streamIndexId: %s, periodId: %s, previous index: %d, current index: %d", str, period.getId(), period.getIndexRange().upperEndpoint(), period2.getIndexRange().upperEndpoint()), createErrorType("CURRENT_LAST_PERIOD_ENDS_BEFORE_PREVIOUS", str), ErrorLevel.ERROR);
            }
        } else {
            if (((Integer) period.getIndexRange().upperEndpoint()).equals(period2.getIndexRange().upperEndpoint())) {
                return;
            }
            throwInvalidOperationException(String.format(Locale.US, "manifests have different end indexes, streamIndexId: %s, periodId: %s, previous index: %d, current index: %d", str, period.getId(), period.getIndexRange().upperEndpoint(), period2.getIndexRange().upperEndpoint()), createErrorType("PERIOD_END_TIME_MISMATCH", str), ErrorLevel.ERROR);
        }
    }

    private PeriodView.Period checkTimeGapAcrossPeriods(PeriodView.Period period, PeriodView.Period period2, String str) throws ContentException {
        if (period != null && period2 != null && !period.getTimeRangeInNanos().isConnected(period2.getTimeRangeInNanos())) {
            long jLongValue = ((Long) period2.getTimeRangeInNanos().lowerEndpoint()).longValue() - ((Long) period.getTimeRangeInNanos().upperEndpoint()).longValue();
            ErrorLevel errorLevel = jLongValue > getGapPeriodThresholdTimeInNanos() ? ErrorLevel.ERROR : ErrorLevel.WARN;
            Locale locale = Locale.US;
            String str2 = String.format(locale, "streamIndex: %s, last id: %s, current id: %s, last range: %s, current range: %s, delta: %d", str, period.getId(), period2.getId(), period.getTimeRangeInNanos(), period2.getTimeRangeInNanos(), Long.valueOf(jLongValue));
            reportQoSError("INVALID_MANIFEST_REFRESH_DETAILS", str2);
            throwInvalidOperationException(String.format(locale, "there is a time gap between periods on the current manifest, %s", str2), createErrorType("PERIOD_GAP", str), errorLevel);
        }
        return period2;
    }

    @Nonnull
    private long getGapPeriodThresholdTimeInNanos() {
        return TimeUnit.MILLISECONDS.toNanos(this.mConfig.getGapBetweenPeriodsThresholdInMillis().longValue());
    }

    private Iterator<PeriodView.Period> createSubListIterator(List<PeriodView.Period> list, int i) {
        return list.subList(i, list.size()).iterator();
    }

    @Nonnull
    private static List<PeriodView.Period> getPeriods(StreamIndex streamIndex) {
        if (!(streamIndex instanceof PeriodAwareStreamIndex)) {
            return Lists.newLinkedList();
        }
        return ((PeriodAwareStreamIndex) streamIndex).getPeriodView().getPeriods();
    }

    @Nonnull
    private static List<String> getPeriodIds(List<PeriodView.Period> list) {
        LinkedList linkedListNewLinkedList = Lists.newLinkedList();
        if (list != null) {
            Iterator<PeriodView.Period> it = list.iterator();
            while (it.hasNext()) {
                linkedListNewLinkedList.add(it.next().getId());
            }
        }
        return linkedListNewLinkedList;
    }

    @Nonnull
    public static List<String> getPeriodIds(StreamIndex streamIndex) {
        return getPeriodIds(getPeriods(streamIndex));
    }

    @Nullable
    private Pair<Integer, Integer> getFirstIntersectionIndex(List<PeriodView.Period> list, List<PeriodView.Period> list2) {
        for (int i = 0; i < list.size(); i++) {
            for (int i2 = 0; i2 < list2.size(); i2++) {
                if (list.get(i).getId().equals(list2.get(i2).getId())) {
                    return new Pair<>(Integer.valueOf(i), Integer.valueOf(i2));
                }
            }
        }
        return null;
    }

    private void throwInvalidOperationException(String str, String str2, ErrorLevel errorLevel) throws ContentException {
        if (errorLevel == ErrorLevel.WARN) {
            DLog.warnf("invalid manifest, " + str);
            return;
        }
        int iIncreaseErrorCounter = increaseErrorCounter();
        DLog.errorf("invalid manifest, %s, error counter: %d", str, Integer.valueOf(iIncreaseErrorCounter));
        reportQoSError("INVALID_MANIFEST_REFRESH", createQoSNote(str2, iIncreaseErrorCounter));
        throw new InvalidManifestRefreshOperationException(str, str2, iIncreaseErrorCounter, errorLevel == ErrorLevel.FATAL, null);
    }

    private void reportQoSError(String str, String str2) {
        PlaybackEventReporter playbackEventReporter = this.mPlaybackEventReporter;
        if (playbackEventReporter != null) {
            playbackEventReporter.reportMetric(QOSEventName.Information.name(), str, null, str2, null);
        }
    }

    private void reportNonFatalError(String str, String str2, String str3) {
        PlaybackEventReporter playbackEventReporter = this.mPlaybackEventReporter;
        if (playbackEventReporter != null) {
            playbackEventReporter.reportError(str, str2, str3, this.mVideoSpecification.getTitleId(), false);
        }
    }

    private String createQoSNote(String str, int i) {
        return !this.mConfig.isThrowExceptionEnabled() ? str : String.format(Locale.US, "%s__[%s]", str, getErrorCountBucket(i));
    }

    private void resetErrorCounter() {
        this.mErrorCounter = 0;
    }

    private int increaseErrorCounter() {
        int i = this.mErrorCounter + 1;
        this.mErrorCounter = i;
        return i;
    }

    public static class InvalidManifestRefreshOperationException extends ContentException {
        private final int mErrorCounter;
        private final String mErrorType;
        private final boolean mIsFatal;

        public InvalidManifestRefreshOperationException(@Nonnull String str, @Nonnull String str2, int i, boolean z, @Nullable URL url) {
            super(ContentException.ContentError.MALFORMED_MANIFEST, (String) Preconditions.checkNotNull(str, NotificationCompat.CATEGORY_MESSAGE), null, url);
            this.mErrorType = (String) Preconditions.checkNotNull(str2, "errorCode");
            this.mErrorCounter = i;
            this.mIsFatal = z;
        }

        public String getErrorType() {
            return this.mErrorType;
        }

        public int getErrorCounter() {
            return this.mErrorCounter;
        }

        public boolean isFatal() {
            return this.mIsFatal;
        }
    }

    public static class RefreshableManifestValidatorConfig extends MediaConfigBase {
        private final ConfigurationValue<Float> mAggressiveManifestReDownloadFactor;
        private final ConfigurationValue<Long> mGapBetweenPeriodsThresholdInMillis;
        private final ConfigurationValue<Boolean> mIsPeriodViewDebugLogsEnabled;
        private final ConfigurationValue<Boolean> mManifestFragmentContiguityValidationEnabled;
        private final ConfigurationValue<Boolean> mManifestValidationEnabled;
        private final ConfigurationValue<Boolean> mNearStaleManifestDetectionEnabled;
        private final ConfigurationValue<Boolean> mRestartOnStaleManifestEnabled;
        private final ConfigurationValue<Float> mSlowManifestThresholdFactor;
        private final ConfigurationValue<Boolean> mStaleManifestdetectionEnabled;
        private final ConfigurationValue<Boolean> mThrowException;
        private final ConfigurationValue<Long> mWindowSizeInMillis;

        private static final class InstanceHolder {
            private static final RefreshableManifestValidatorConfig INSTANCE = new RefreshableManifestValidatorConfig();

            private InstanceHolder() {
            }
        }

        public static RefreshableManifestValidatorConfig getInstance() {
            return InstanceHolder.INSTANCE;
        }

        private RefreshableManifestValidatorConfig() {
            this.mManifestValidationEnabled = newBooleanConfigValue("playback.refreshablemanifest.validations.enabled", true);
            this.mStaleManifestdetectionEnabled = newBooleanConfigValue("playback.refreshablemanifest.staleManifestdetection.enabled", true);
            this.mAggressiveManifestReDownloadFactor = newFloatConfigValue("playback.refreshablemanifest.staleManifest.redownload.factor", 0.9f);
            this.mThrowException = newBooleanConfigValue("playback.refreshablemanifest.validations.throw_exception", false);
            this.mGapBetweenPeriodsThresholdInMillis = newLongConfigValue("playback.refreshablemanifest.validations.gapBetweenPeriodsThresholdInMillis", 100L);
            this.mManifestFragmentContiguityValidationEnabled = newBooleanConfigValue("playback.refreshablemanifest.fragmentcontinuityvalidation.enabled", true);
            this.mIsPeriodViewDebugLogsEnabled = newBooleanConfigValue("playback.isPeriodViewDebugLogsEnabled", true);
            this.mSlowManifestThresholdFactor = newFloatConfigValue("playback.refreshablemanifest.slowManifest.decision.threshold", 0.95f);
            this.mNearStaleManifestDetectionEnabled = newBooleanConfigValue("playback.refreshablemanifest.nearStaleManifestdetection.enabled", true);
            this.mWindowSizeInMillis = newLongConfigValue("playback.refreshablemanifest.validations.windowSizeInMillis", 10000L);
            this.mRestartOnStaleManifestEnabled = newBooleanConfigValue("playback.refreshablemanifest.restartOnStaleManifest.enabled", true);
        }

        public boolean isThrowExceptionEnabled() {
            return this.mThrowException.getValue().booleanValue();
        }

        public boolean isManifestValidationEnabled() {
            return this.mManifestValidationEnabled.getValue().booleanValue();
        }

        public boolean isStaleManifestdetectionEnabled() {
            return this.mStaleManifestdetectionEnabled.getValue().booleanValue();
        }

        public boolean isRestartOnStaleManifestEnabled() {
            return this.mRestartOnStaleManifestEnabled.getValue().booleanValue();
        }

        public float getManifestReDownloadFactor() {
            return this.mAggressiveManifestReDownloadFactor.getValue().floatValue();
        }

        public Long getGapBetweenPeriodsThresholdInMillis() {
            return this.mGapBetweenPeriodsThresholdInMillis.getValue();
        }

        public Boolean getManifestFragmentContiguityValidationEnabled() {
            return this.mManifestFragmentContiguityValidationEnabled.getValue();
        }

        public boolean isPeriodViewDebugLogsEnabled() {
            return this.mIsPeriodViewDebugLogsEnabled.getValue().booleanValue();
        }

        public float getSlowManifestThresholdFactor() {
            return this.mSlowManifestThresholdFactor.getValue().floatValue();
        }

        public boolean isNearStaleManifestDetectionEnabled() {
            return this.mNearStaleManifestDetectionEnabled.getValue().booleanValue();
        }

        public Long getWindowSizeInMillis() {
            return this.mWindowSizeInMillis.getValue();
        }
    }
}
