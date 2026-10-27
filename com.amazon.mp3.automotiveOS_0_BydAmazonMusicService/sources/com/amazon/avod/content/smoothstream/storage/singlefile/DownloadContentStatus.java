package com.amazon.avod.content.smoothstream.storage.singlefile;

import java.io.IOException;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface DownloadContentStatus {
    @Nonnull
    MediaFileStatus addMediaFileStatus(String str, int i, long j);

    @Nonnull
    Set<String> getFileNames();

    @Nullable
    MediaFileStatus getMediaFileStatus(@Nonnull String str);

    void save() throws IOException;
}
