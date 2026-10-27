package com.amazon.video.sdk.avod.playbackclient.subtitle;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.core.Subtitle;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.playbackclient.subtitle.internal.EmptySubtitleException;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleCollection;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleDataProvider;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleException;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleLanguage;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Ticker;
import java.io.File;
import java.io.InputStream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class SubtitleLoader {
    private final SubtitleDataProvider mSubtitleDataProvider;
    private final Ticker mTicker;

    public SubtitleLoader(@Nonnull SubtitleDataProvider subtitleDataProvider) {
        this(subtitleDataProvider, Tickers.androidTicker());
    }

    @VisibleForTesting
    SubtitleLoader(@Nonnull SubtitleDataProvider subtitleDataProvider, @Nonnull Ticker ticker) {
        this.mSubtitleDataProvider = (SubtitleDataProvider) Preconditions.checkNotNull(subtitleDataProvider, "subtitleDataProvider");
        this.mTicker = (Ticker) Preconditions.checkNotNull(ticker, "ticker");
    }

    @Nullable
    public SubtitleCollection loadFromFile(@Nonnull File file, @Nonnull Subtitle subtitle) {
        Preconditions.checkNotNull(file, "file");
        Preconditions.checkNotNull(subtitle, "subtitleData");
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.INFO, "Subtitle language %s parse", subtitle.getLanguageCode());
        try {
            try {
                return this.mSubtitleDataProvider.getSubtitleCollection(file, subtitle);
            } catch (SubtitleException e) {
                DLog.exceptionf(e, "An error has occurred of type: %s", e.getErrorCode());
                Profiler.endTrace(traceKeyBeginTrace);
                return null;
            }
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
        }
    }

    @Nullable
    public SubtitleCollection loadFromStream(@Nonnull InputStream inputStream, @Nonnull SubtitleLanguage subtitleLanguage, long j, long j2) {
        Preconditions.checkNotNull(inputStream, "inputStream");
        Preconditions.checkNotNull(subtitleLanguage, "language");
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.INFO, "%s parse", subtitleLanguage);
        try {
            try {
                return this.mSubtitleDataProvider.getSubtitleCollection(inputStream, j, j2);
            } catch (EmptySubtitleException unused) {
                DLog.logf("Empty captions received from InputStream.");
                Profiler.endTrace(traceKeyBeginTrace);
                return null;
            } catch (SubtitleException e) {
                DLog.exceptionf(e, "An error has occurred of type: %s", e.getErrorCode());
                Profiler.endTrace(traceKeyBeginTrace);
                return null;
            }
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
        }
    }
}
