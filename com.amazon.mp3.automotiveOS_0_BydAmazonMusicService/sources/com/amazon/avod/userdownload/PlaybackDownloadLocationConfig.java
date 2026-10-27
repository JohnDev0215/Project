package com.amazon.avod.userdownload;

import amazon.android.config.ConfigBase;
import android.util.Pair;
import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.util.DLog;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.io.File;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloadLocationConfig extends ConfigBase {
    private UserDownloadLocation mDownloadLocation;
    private String mPlaybackDownloadRootPrefix;
    private final StorageHelper mStorageHelper;

    public PlaybackDownloadLocationConfig(@Nonnull StorageHelper storageHelper) {
        super("com.amazon.avod.userdownload.DownloadLocationConfig");
        this.mStorageHelper = (StorageHelper) Preconditions.checkNotNull(storageHelper, "storageHelper");
        this.mDownloadLocation = UserDownloadLocation.INTERNAL_STORAGE;
        this.mPlaybackDownloadRootPrefix = "";
    }

    @Nonnull
    public File getRelativePathFromTitleId(@Nonnull String str) {
        Preconditions.checkNotNull(str, "titleId");
        return new File((String) null, str + "-" + UUID.randomUUID());
    }

    @Nonnull
    public Optional<File> getAbsoluteStoragePathIfAvailable(@Nonnull UserDownloadLocation userDownloadLocation, @Nullable File file) {
        if (file == null) {
            return Optional.absent();
        }
        if (file.isAbsolute()) {
            return Optional.of(file);
        }
        Preconditions.checkNotNull(userDownloadLocation, "location");
        File fileOrNull = getDownloadRoot(userDownloadLocation).orNull();
        if (fileOrNull == null) {
            DLog.warnf("DWNLD_SDK Unable to get absolute storage path for a download on %s", userDownloadLocation.getPersistenceName());
            return Optional.absent();
        }
        return Optional.of(new File(fileOrNull, file.getPath()));
    }

    @Nonnull
    public Pair<UserDownloadLocation, File> getBestAvailableAbsolutePath(@Nonnull UserDownloadLocation userDownloadLocation, @Nonnull File file) {
        Preconditions.checkNotNull(userDownloadLocation, "location");
        Preconditions.checkNotNull(file, "relativePath");
        return Pair.create(getBestAvailableLocation(userDownloadLocation), new File(getDownloadRoot(userDownloadLocation).or(this.mStorageHelper.getInternalPlaybackDownloadDir()), file.getPath()));
    }

    @Nonnull
    public UserDownloadLocation getPreferredDownloadLocation() {
        return this.mDownloadLocation;
    }

    public void setDownloadLocationRootPrefix(@Nonnull String str) {
        this.mPlaybackDownloadRootPrefix = (String) Preconditions.checkNotNull(str, "rootPrefix");
    }

    @Nonnull
    private UserDownloadLocation getBestAvailableLocation(@Nonnull UserDownloadLocation userDownloadLocation) {
        Preconditions.checkNotNull(userDownloadLocation, "preferredLocation");
        UserDownloadLocation userDownloadLocation2 = UserDownloadLocation.SD_CARD;
        return userDownloadLocation == userDownloadLocation2 && this.mStorageHelper.getExternalPlaybackDownloadDir().isPresent() ? userDownloadLocation2 : UserDownloadLocation.INTERNAL_STORAGE;
    }

    @Nonnull
    private Optional<File> getDownloadRoot(@Nonnull UserDownloadLocation userDownloadLocation) {
        if (userDownloadLocation == UserDownloadLocation.INTERNAL_STORAGE) {
            return rootPrefixAnnotate(this.mStorageHelper.getInternalPlaybackDownloadDir());
        }
        if (userDownloadLocation == UserDownloadLocation.SD_CARD) {
            return rootPrefixAnnotate(this.mStorageHelper.getExternalPlaybackDownloadDir().orNull());
        }
        return Optional.absent();
    }

    private Optional<File> rootPrefixAnnotate(@Nullable File file) {
        if (file == null) {
            return Optional.absent();
        }
        if (Strings.isNullOrEmpty(this.mPlaybackDownloadRootPrefix)) {
            return Optional.of(file);
        }
        return Optional.of(new File(file, this.mPlaybackDownloadRootPrefix));
    }
}
