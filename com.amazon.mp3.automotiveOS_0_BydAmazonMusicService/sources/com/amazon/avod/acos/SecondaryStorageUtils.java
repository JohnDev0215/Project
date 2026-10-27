package com.amazon.avod.acos;

import com.google.common.base.Optional;
import java.io.File;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface SecondaryStorageUtils {
    @Nonnull
    Optional<File> getExternalPathForPlaybackDownload();

    @Nonnull
    Optional<File> getExternalPathForUserDownload();

    @Nonnull
    Optional<File> getExternalStoragePath();

    @Nonnull
    Optional<File> getExternalStoragePathIfWritable();

    boolean isSDCardSlotPresent();

    void onSDCardMounted();
}
