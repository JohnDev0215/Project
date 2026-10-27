package com.amazon.avod.playbackclient.subtitle.download;

import com.amazon.atvplaybackresource.PRSException;
import com.amazon.avod.media.download.plugin.PluginLoadStatus;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleLanguage;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitlePluginLog {
    private final int mNumAccepted;
    private final int mNumAvailable;
    private final int mNumMalformed;
    private final int mNumRejected;

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getNumAvailable() {
        return this.mNumAvailable;
    }

    public int getNumMalformed() {
        return this.mNumMalformed;
    }

    public int getNumAccepted() {
        return this.mNumAccepted;
    }

    public int getNumRejected() {
        return this.mNumRejected;
    }

    public static class Builder {
        private String mAsin;
        private final ImmutableSet.Builder<SubtitleLanguageLog> mDownloadedLanguageLogs;
        private ImmutableSet<SubtitleLanguage> mDuplicatedLanguageCodes;
        private int mNumAccepted;
        private int mNumAvailable;
        private int mNumDuplicated;
        private int mNumMalformed;
        private int mNumRejected;
        private long mPresetActionDuration;
        private String mPresetError;
        private String mPresetErrorReason;
        private PluginLoadStatus.Source mPresetSource;
        private ImmutableSet<SubtitleLanguage> mRejectedLanguageCodes;
        private long mUrlActionDuration;
        private String mUrlError;
        private String mUrlErrorReason;
        private PluginLoadStatus.Source mUrlSource;

        private Builder() {
            this.mPresetError = null;
            this.mPresetErrorReason = null;
            this.mPresetActionDuration = -1L;
            this.mUrlError = null;
            this.mUrlErrorReason = null;
            this.mUrlActionDuration = -1L;
            this.mNumAvailable = 0;
            this.mNumMalformed = 0;
            this.mNumAccepted = 0;
            this.mNumRejected = 0;
            this.mNumDuplicated = 0;
            this.mRejectedLanguageCodes = null;
            this.mDuplicatedLanguageCodes = null;
            this.mDownloadedLanguageLogs = ImmutableSet.builder();
        }

        public Builder withAsin(@Nonnull String str) {
            this.mAsin = (String) Preconditions.checkNotNull(str, "Asin cannot be null");
            return this;
        }

        public Builder withPresetActionStatus(@Nonnull PluginLoadStatus.Source source, @Nonnegative long j) {
            this.mPresetSource = (PluginLoadStatus.Source) Preconditions.checkNotNull(source, "PresetSource cannot be null");
            Preconditions.checkArgument(j >= 0, "Total preset action time cannot be negative");
            this.mPresetActionDuration = j;
            return this;
        }

        public Builder withPresetActionPRSError(@Nonnull PRSException pRSException) {
            Preconditions.checkNotNull(pRSException, "exception");
            this.mPresetError = pRSException.getClass().getSimpleName();
            this.mPresetErrorReason = pRSException.message.orNull();
            return this;
        }

        public Builder withUrlActionStatus(@Nonnull PluginLoadStatus.Source source, @Nonnegative long j) {
            this.mUrlSource = (PluginLoadStatus.Source) Preconditions.checkNotNull(source, "UrlSource cannot be null");
            Preconditions.checkArgument(j >= 0, "Total url action time cannot be negative");
            this.mUrlActionDuration = j;
            return this;
        }

        public Builder withUrlActionError(@Nonnull String str, @Nonnull String str2) {
            this.mUrlError = (String) Preconditions.checkNotNull(str, "error");
            this.mUrlErrorReason = (String) Preconditions.checkNotNull(str2, "message");
            return this;
        }

        public Builder withAvailableLanguageCount(@Nonnegative int i) {
            Preconditions.checkArgument(i >= 0, "numAvailablecannot be negative");
            this.mNumAvailable = i;
            return this;
        }

        public Builder withMalformedLanguageCount(@Nonnegative int i) {
            Preconditions.checkArgument(i >= 0, "numMalformed be negative");
            this.mNumMalformed = i;
            return this;
        }

        public Builder withAcceptedLanguageCount(@Nonnegative int i) {
            Preconditions.checkArgument(i >= 0, "numAccepted be negative");
            this.mNumAccepted = i;
            return this;
        }

        public Builder withDuplicateLanguages(@Nonnull ImmutableSet<SubtitleLanguage> immutableSet) {
            Preconditions.checkNotNull(immutableSet, "duplicateLanguages");
            this.mNumDuplicated = immutableSet.size();
            this.mDuplicatedLanguageCodes = immutableSet;
            return this;
        }

        public Builder withIndividualSubtitleLanguageLog(@Nonnull SubtitleLanguageLog subtitleLanguageLog) {
            Preconditions.checkNotNull(subtitleLanguageLog, "subtitleLanguageLog cannot be null");
            this.mDownloadedLanguageLogs.add(subtitleLanguageLog);
            return this;
        }
    }
}
