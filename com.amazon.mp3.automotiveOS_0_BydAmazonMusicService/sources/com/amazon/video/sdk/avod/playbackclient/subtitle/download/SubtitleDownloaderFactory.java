package com.amazon.video.sdk.avod.playbackclient.subtitle.download;

import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.playbackclient.listeners.SubtitleLoadListener;
import com.amazon.avod.playbackclient.subtitle.download.LanguageSupportedListener;
import com.amazon.avod.playbackclient.subtitle.download.SubtitleDownloader;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleLanguage;
import com.amazon.video.sdk.avod.playbackclient.utils.SubtitlesLanguageHelper;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes5.dex */
public class SubtitleDownloaderFactory {
    private SubtitleDownloaderFactory() {
    }

    public static SubtitleDownloader createDownloader(@Nonnull PlaybackExperienceController playbackExperienceController, @Nonnull LanguageSupportedListener languageSupportedListener, @Nonnull SubtitleLoadListener subtitleLoadListener, @Nonnull ImmutableSet<SubtitleLanguage> immutableSet, @Nonnull SubtitleContentPlugin subtitleContentPlugin) {
        Preconditions.checkNotNull(playbackExperienceController, "playbackExperienceController");
        Preconditions.checkNotNull(languageSupportedListener, "languageSupportedListener");
        Preconditions.checkNotNull(subtitleLoadListener, "subtitleLoadListener");
        Preconditions.checkNotNull(immutableSet, "subtitleLanguagesFromContext");
        Preconditions.checkNotNull(subtitleContentPlugin, "subtitlePlugin");
        return SubtitlesLanguageHelper.isStreamingSubtitlesSupported(playbackExperienceController) ? new SubtitleStreamingDownloader(playbackExperienceController, languageSupportedListener, subtitleLoadListener, immutableSet) : new SubtitleFileDownloader(languageSupportedListener, subtitleLoadListener, subtitleContentPlugin);
    }
}
