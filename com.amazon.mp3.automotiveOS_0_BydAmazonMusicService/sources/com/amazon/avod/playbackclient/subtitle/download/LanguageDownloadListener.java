package com.amazon.avod.playbackclient.subtitle.download;

import com.amazon.avod.core.Subtitle;
import java.io.File;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface LanguageDownloadListener {
    void onLanguageDownload(@Nonnull Subtitle subtitle, long j, @Nonnull File file);
}
