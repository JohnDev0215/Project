package com.amazon.avod.userdownload;

import android.util.Pair;
import com.amazon.avod.content.DownloadStoreType;
import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.userdownload.PlaybackDownload;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.File;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloadRequest {
    private final AudioFormat mAudioFormat;
    private ImmutableList<String> mAudioTrackIds;
    private final ImmutableList<AudioTrackMetadata> mAudioTrackMetadataList;
    private final UserDownloadLocation mDownloadLocation;
    private final Optional<String> mDownloadRootDir;
    private final GeneralDownloadConfig mGeneralDownloadConfig;
    private final MediaQuality mMediaQuality;
    private final PlaybackDownloadLocationConfig mPlaybackDownloadLocationConfig;
    private final Optional<String> mPlayerSDKAudioStreamMatchers;
    private final Optional<String> mPlayerSDKPlaybackToken;
    private final Optional<String> mPlayerSDKTimedTextStreamMatchers;
    private final Map<String, String> mSessionContext;
    private final boolean mShouldEnableFragmentDownloaderForSSAI;
    private final String mTitleId;

    public static class DuplicatePlaybackDownloadRequestException extends Exception {
    }

    public static class IllegalPlaybackDownloadRequestException extends Exception {
    }

    private PlaybackDownloadRequest(@Nonnull Builder builder) {
        this.mTitleId = builder.mTitleId;
        this.mPlaybackDownloadLocationConfig = (PlaybackDownloadLocationConfig) Preconditions.checkNotNull(builder.mPlaybackDownloadLocationConfig, "builder.mPlaybackDownloadLocationConfig");
        this.mMediaQuality = (MediaQuality) Preconditions.checkNotNull(builder.mMediaQuality, "builder.mMediaQuality");
        this.mAudioFormat = (AudioFormat) Preconditions.checkNotNull(builder.mAudioFormat, "builder.mAudioFormat");
        this.mAudioTrackIds = builder.mAudioTrackIds;
        UserDownloadLocation userDownloadLocation = (UserDownloadLocation) Preconditions.checkNotNull(builder.mDownloadLocation, "builder.mDownloadLocation");
        this.mDownloadLocation = userDownloadLocation;
        this.mDownloadRootDir = (Optional) Preconditions.checkNotNull(builder.mDownloadRootDir, "builder.mDownloadRootDir");
        this.mAudioTrackMetadataList = builder.mAudioTrackMetadataList;
        this.mGeneralDownloadConfig = (GeneralDownloadConfig) Preconditions.checkNotNull(builder.mGeneralDownloadConfig, "builder.mGeneralDownloadConfig");
        this.mSessionContext = builder.mSessionContext;
        this.mPlayerSDKPlaybackToken = builder.mPlayerSDKPlaybackToken;
        this.mPlayerSDKAudioStreamMatchers = builder.mPlayerSDKAudioStreamMatchers;
        this.mPlayerSDKTimedTextStreamMatchers = builder.mPlayerSDKTimedTextStreamMatchers;
        this.mShouldEnableFragmentDownloaderForSSAI = builder.mShouldEnableFragmentDownloaderForSSAI;
        Preconditions.checkState(userDownloadLocation == UserDownloadLocation.INTERNAL_STORAGE || userDownloadLocation == UserDownloadLocation.SD_CARD, "Unsupported download location: %s", userDownloadLocation);
    }

    public PlaybackDownload convertToPlaybackDownload() throws IllegalPlaybackDownloadRequestException {
        String str = this.mTitleId;
        PlaybackDownload.Builder playerSDKTimedTextStreamMatchers = PlaybackDownload.newBuilder(str, this.mPlaybackDownloadLocationConfig).setSessionContext(DownloadSessionContextUtil.mapToJSON(this.mSessionContext)).setAudioFormat(this.mAudioFormat).setAudioTrackIds(this.mAudioTrackIds).setDownloadQuality(this.mMediaQuality).setSessionId(Optional.absent()).setState(UserDownloadState.QUEUEING).setPlayerSDKPlaybackToken(this.mPlayerSDKPlaybackToken).setPlayerSDKAudioStreamMatchers(this.mPlayerSDKAudioStreamMatchers).setPlayerSDKTimedTextStreamMatchers(this.mPlayerSDKTimedTextStreamMatchers);
        File relativePathFromTitleId = this.mPlaybackDownloadLocationConfig.getRelativePathFromTitleId(str);
        if (this.mDownloadRootDir.isPresent()) {
            playerSDKTimedTextStreamMatchers.setStoragePath(this.mDownloadLocation, new File(this.mDownloadRootDir.get(), relativePathFromTitleId.getPath()), Optional.absent());
        } else {
            Pair<UserDownloadLocation, File> bestAvailableAbsolutePath = this.mPlaybackDownloadLocationConfig.getBestAvailableAbsolutePath(this.mDownloadLocation, relativePathFromTitleId);
            playerSDKTimedTextStreamMatchers.setStoragePath((UserDownloadLocation) bestAvailableAbsolutePath.first, (File) bestAvailableAbsolutePath.second, Optional.of(relativePathFromTitleId));
        }
        playerSDKTimedTextStreamMatchers.setAudioTrackMetadataList(this.mAudioTrackMetadataList);
        if (this.mGeneralDownloadConfig.isSingleFileDownloadEnabled() && !this.mShouldEnableFragmentDownloaderForSSAI) {
            playerSDKTimedTextStreamMatchers.setDownloadStoreType(DownloadStoreType.SINGLE_FILE);
        } else {
            playerSDKTimedTextStreamMatchers.setDownloadStoreType(DownloadStoreType.FRAGMENTED);
        }
        return playerSDKTimedTextStreamMatchers.build();
    }

    public static Builder newBuilder(@Nonnull PlaybackDownloadLocationConfig playbackDownloadLocationConfig, @Nonnull GeneralDownloadConfig generalDownloadConfig, boolean z) {
        return new Builder(playbackDownloadLocationConfig, generalDownloadConfig, z);
    }

    public static class Builder {
        private AudioFormat mAudioFormat;
        private ImmutableList<String> mAudioTrackIds;
        private ImmutableList<AudioTrackMetadata> mAudioTrackMetadataList;
        private UserDownloadLocation mDownloadLocation;
        private Optional<String> mDownloadRootDir;
        private GeneralDownloadConfig mGeneralDownloadConfig;
        private MediaQuality mMediaQuality;
        private PlaybackDownloadLocationConfig mPlaybackDownloadLocationConfig;
        private Optional<String> mPlayerSDKAudioStreamMatchers;
        private Optional<String> mPlayerSDKPlaybackToken;
        private Optional<String> mPlayerSDKTimedTextStreamMatchers;
        private Map<String, String> mSessionContext;
        private boolean mShouldEnableFragmentDownloaderForSSAI;
        private String mTitleId;

        private Builder(@Nonnull PlaybackDownloadLocationConfig playbackDownloadLocationConfig, @Nonnull GeneralDownloadConfig generalDownloadConfig, boolean z) {
            this.mAudioTrackIds = ImmutableList.of();
            this.mDownloadLocation = UserDownloadLocation.INTERNAL_STORAGE;
            this.mDownloadRootDir = Optional.absent();
            this.mAudioTrackMetadataList = ImmutableList.of();
            this.mPlayerSDKPlaybackToken = Optional.absent();
            this.mPlayerSDKAudioStreamMatchers = Optional.absent();
            this.mPlayerSDKTimedTextStreamMatchers = Optional.absent();
            this.mPlaybackDownloadLocationConfig = (PlaybackDownloadLocationConfig) Preconditions.checkNotNull(playbackDownloadLocationConfig, "playbackDownloadLocationConfig");
            this.mGeneralDownloadConfig = (GeneralDownloadConfig) Preconditions.checkNotNull(generalDownloadConfig, "generalDownloadConfig");
            this.mShouldEnableFragmentDownloaderForSSAI = z;
        }

        @Nonnull
        public PlaybackDownloadRequest build() {
            return new PlaybackDownloadRequest(this);
        }

        public Builder setTitleId(String str) {
            this.mTitleId = str;
            return this;
        }

        public Builder setMediaQuality(@Nonnull MediaQuality mediaQuality) {
            this.mMediaQuality = (MediaQuality) Preconditions.checkNotNull(mediaQuality, "mediaQuality");
            return this;
        }

        public Builder setAudioFormat(@Nonnull AudioFormat audioFormat) {
            this.mAudioFormat = (AudioFormat) Preconditions.checkNotNull(audioFormat, "audioFormat");
            return this;
        }

        public Builder setAudioTrackIds(@Nonnull ImmutableList<String> immutableList) {
            this.mAudioTrackIds = (ImmutableList) Preconditions.checkNotNull(immutableList, "audioTrackIds");
            return this;
        }

        public Builder setStorageLocation(@Nonnull UserDownloadLocation userDownloadLocation) {
            Preconditions.checkState(!this.mDownloadRootDir.isPresent(), "Not allowed to specify explicit download location (%s) if download root directory override is in use", userDownloadLocation);
            this.mDownloadLocation = (UserDownloadLocation) Preconditions.checkNotNull(userDownloadLocation, "downloadLocation");
            return this;
        }

        public Builder setSessionContext(@Nonnull Map<String, String> map) {
            this.mSessionContext = (Map) Preconditions.checkNotNull(map, "sessionContext");
            return this;
        }

        public Builder setPlayerSDKPlaybackToken(@Nonnull Optional<String> optional) {
            this.mPlayerSDKPlaybackToken = (Optional) Preconditions.checkNotNull(optional, "playerSDKPlaybackToken");
            return this;
        }

        public Builder setPlayerSDKAudioStreamMatchers(@Nonnull Optional<String> optional) {
            this.mPlayerSDKAudioStreamMatchers = (Optional) Preconditions.checkNotNull(optional, "playerSDKAudioStreamMatchers");
            return this;
        }

        public Builder setPlayerSDKTimedTextStreamMatchers(@Nonnull Optional<String> optional) {
            this.mPlayerSDKTimedTextStreamMatchers = (Optional) Preconditions.checkNotNull(optional, "playerSDKTimedTextStreamMatchers");
            return this;
        }
    }
}
