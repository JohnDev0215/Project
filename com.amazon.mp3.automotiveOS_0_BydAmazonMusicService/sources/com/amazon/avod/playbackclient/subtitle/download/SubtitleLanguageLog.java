package com.amazon.avod.playbackclient.subtitle.download;

import com.amazon.avod.media.download.plugin.PluginLoadStatus;
import com.amazon.avod.media.download.plugin.PluginLogConstants$DownloadError;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleLanguage;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitleLanguageLog {
    private final long mDownloadTime;
    private final String mErrorReason;
    private final long mFileSize;
    private final String mLanguageCode;
    private final PluginLoadStatus.Source mSource;

    private SubtitleLanguageLog(@Nonnull String str, @Nonnull PluginLoadStatus.Source source, @Nonnegative long j, @Nonnegative long j2, @Nullable String str2) {
        this.mLanguageCode = (String) Preconditions.checkNotNull(str, "languageCode cannot be null");
        this.mSource = (PluginLoadStatus.Source) Preconditions.checkNotNull(source, "Source cannot be null");
        Preconditions.checkArgument(j >= 0, "downloadTime cannot be negative");
        Preconditions.checkArgument(j2 >= 0, "fileSize cannot be negative");
        this.mDownloadTime = j;
        this.mFileSize = j2;
        this.mErrorReason = str2;
    }

    public static SubtitleLanguageLogBuilder newBuilder(@Nonnull SubtitleLanguage subtitleLanguage) {
        return new SubtitleLanguageLogBuilder(subtitleLanguage.getLanguageCode());
    }

    public static final class SubtitleLanguageLogBuilder {
        private long mDownloadTime;
        private String mErrorReason;
        private String mExceptionReason;
        private long mFileSize;
        private final String mLanguageCode;
        private PluginLoadStatus.Source mSource;

        private SubtitleLanguageLogBuilder(@Nonnull String str) {
            this.mFileSize = 0L;
            this.mDownloadTime = 0L;
            this.mErrorReason = null;
            this.mExceptionReason = null;
            this.mLanguageCode = (String) Preconditions.checkNotNull(str, "languageCode cannot be null");
        }

        public SubtitleLanguageLogBuilder withSubtitleFileSource(@Nonnull PluginLoadStatus.Source source) {
            this.mSource = (PluginLoadStatus.Source) Preconditions.checkNotNull(source, "fileSource cannot be null");
            return this;
        }

        public SubtitleLanguageLogBuilder withSubtitleFileSize(@Nonnegative long j) {
            Preconditions.checkArgument(j >= 0, "fileSize cannot be negative");
            this.mFileSize = j;
            return this;
        }

        public SubtitleLanguageLogBuilder withSubtitleFileDownloadTime(@Nonnegative long j) {
            Preconditions.checkArgument(j >= 0, "totalDownloadTime cannot be negative");
            this.mDownloadTime = j;
            return this;
        }

        public SubtitleLanguageLogBuilder withSubtitleFileDownloadError(@Nonnull PluginLogConstants$DownloadError pluginLogConstants$DownloadError) {
            Preconditions.checkNotNull(pluginLogConstants$DownloadError, "DownloadError cannot be null");
            this.mErrorReason = pluginLogConstants$DownloadError.getError();
            return this;
        }

        public SubtitleLanguageLogBuilder withSubtitleFileDownloadException(@Nonnull Exception exc) {
            Preconditions.checkNotNull(exc, "Exception cannot be null");
            this.mExceptionReason = exc.getClass().getSimpleName() + " " + exc.getMessage();
            return this;
        }

        public SubtitleLanguageLog build() {
            Preconditions.checkState(this.mExceptionReason == null || this.mErrorReason == null, "Cannot have both exception reason and error reason");
            return new SubtitleLanguageLog(this.mLanguageCode, this.mSource, this.mDownloadTime, this.mFileSize, this.mErrorReason);
        }
    }
}
