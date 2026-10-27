package com.amazon.video.sdk.avod.playbackclient.trickplay.download;

import com.amazon.avod.media.download.plugin.PluginLoadStatus;
import com.amazon.avod.media.download.plugin.PluginLogConstants$DownloadError;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Preconditions;
import java.io.File;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes5.dex */
class TrickplayPluginLog {
    private final int mNumParsedImages;

    public int getNumParsedImages() {
        return this.mNumParsedImages;
    }

    @Nonnull
    public static Builder newBuilder() {
        return new Builder();
    }

    static class Builder {
        private String mAsin;
        private String mBifError;
        private PluginLoadStatus.Source mBifSource;
        private String mBifUrlPath;
        private String mCreateManifestError;
        private Boolean mCreateManifestPersisted;
        private Boolean mIsServerEnabled;
        private Boolean mIsTrailer;
        private File mManifestDiskPath;
        private PluginLoadStatus.Source mManifestSource;
        private long mValidManifestTime = -1;
        private long mBifFileSize = -1;
        private long mBifFileTime = -1;
        private long mCreateManifestTime = -1;
        private int mNumParsedImages = -1;
        private long mParseImageTime = -1;

        Builder() {
        }

        public Builder withAsin(@Nonnull String str) {
            this.mAsin = (String) Preconditions.checkNotNull(str, "Asin cannot be null");
            return this;
        }

        public Builder isTrailer(boolean z) {
            this.mIsTrailer = Boolean.valueOf(z);
            return this;
        }

        public Builder withTrickplayServerEnabled() {
            this.mIsServerEnabled = Boolean.TRUE;
            return this;
        }

        public Builder withTrickplayServerDisabled() {
            this.mIsServerEnabled = Boolean.FALSE;
            return this;
        }

        public Builder withManifestSource(@Nonnull PluginLoadStatus.Source source) {
            this.mManifestSource = (PluginLoadStatus.Source) Preconditions.checkNotNull(source, "PluginDataSource cannot be null");
            return this;
        }

        public Builder withManifestDiskUrl(@Nonnull File file) {
            this.mManifestDiskPath = (File) Preconditions.checkNotNull(file, "diskUrl cannot be null");
            return this;
        }

        public Builder withManifestValidationTime(@Nonnegative long j) {
            Preconditions.checkArgument(j >= 0, "valid manifest check time cannot be negative");
            this.mValidManifestTime = j;
            return this;
        }

        public Builder withBifFileSource(@Nonnull PluginLoadStatus.Source source) {
            this.mBifSource = (PluginLoadStatus.Source) Preconditions.checkNotNull(source, "PluginDataSource cannot be null");
            return this;
        }

        public Builder withBifFromUrl(@Nonnull String str) {
            this.mBifUrlPath = (String) Preconditions.checkNotNull(str, "url cannot be null");
            return this;
        }

        public Builder withBifDownloadError(@Nonnull PluginLogConstants$DownloadError pluginLogConstants$DownloadError) {
            Preconditions.checkNotNull(pluginLogConstants$DownloadError, "DownloadError cannot be null");
            this.mBifError = pluginLogConstants$DownloadError.getError();
            return this;
        }

        public Builder withBifDownloadException(@Nonnull String str) {
            Preconditions.checkNotNull(str, "message");
            this.mBifError = str;
            return this;
        }

        public Builder withBifFileSize(@Nonnegative long j) {
            Preconditions.checkArgument(j >= 0, "bif file size cannot be negative");
            this.mBifFileSize = (long) DataUnit.BYTES.toKiloBytes(j);
            return this;
        }

        public Builder withBifFileLoadTime(@Nonnegative long j) {
            Preconditions.checkArgument(j >= 0, "bif file time cannot be negative");
            this.mBifFileTime = j;
            return this;
        }

        public Builder withCreateManifestError(@Nonnull PluginLogConstants$DownloadError pluginLogConstants$DownloadError) {
            Preconditions.checkNotNull(pluginLogConstants$DownloadError, "DownloadError cannot be null");
            this.mCreateManifestError = pluginLogConstants$DownloadError.getError();
            return this;
        }

        public Builder withCreateManifestException(@Nonnull Exception exc) {
            Preconditions.checkNotNull(exc, "Exception cannot be null");
            this.mCreateManifestError = exc.getClass().getSimpleName() + " " + exc.getMessage();
            return this;
        }

        public Builder withManifestPersisted() {
            this.mCreateManifestPersisted = Boolean.TRUE;
            return this;
        }

        public Builder withManifestNotPersisted() {
            this.mCreateManifestPersisted = Boolean.FALSE;
            return this;
        }

        public Builder withCreateManifestTime(@Nonnegative long j) {
            Preconditions.checkArgument(j >= 0, "Create manifest time cannot be negative");
            this.mCreateManifestTime = j;
            return this;
        }

        public Builder withNewParsedImage() {
            this.mNumParsedImages++;
            return this;
        }

        public Builder withParseImagesTime(@Nonnegative long j) {
            Preconditions.checkArgument(j >= 0, "parse image time cannot be negative");
            this.mParseImageTime = j;
            return this;
        }
    }
}
