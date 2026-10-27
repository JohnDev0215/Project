package com.amazon.video.sdk.avod.playbackclient.subtitle.download;

import com.amazon.avod.fileio.DiskUtils;
import com.amazon.avod.media.download.plugin.ContentFetcherPlugin;
import com.amazon.avod.media.download.plugin.ContentFetcherPluginContext;
import com.amazon.avod.media.download.plugin.PluginLoadStatus;
import com.amazon.avod.media.download.plugin.action.ContentFetcherPluginAction;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.playbackclient.subtitle.download.LanguageDownloadListener;
import com.amazon.avod.playbackclient.subtitle.download.LanguageSupportedListener;
import com.amazon.avod.playbackclient.subtitle.download.SubtitlePluginResponseHolder;
import com.amazon.avod.util.DLog;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.io.File;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes5.dex */
public class SubtitleContentPlugin implements ContentFetcherPlugin {
    private static final Object PLUGIN_CACHE_LOCK = new Object();
    private final SubtitlePresetsAction mFirstAction;
    private ContentFetcherPluginContext mPluginContext;
    private final SubtitlePluginResponseHolder mSubtitlePluginResponseHolder;

    @Override // com.amazon.avod.media.download.plugin.ContentFetcherPlugin
    public boolean isComplete() {
        return false;
    }

    public static class PluginProvider implements Provider<SubtitleContentPlugin> {
        private final DownloadService mDownloadService;

        public PluginProvider(@Nonnull DownloadService downloadService) {
            this.mDownloadService = (DownloadService) Preconditions.checkNotNull(downloadService, "downloadService cannot be null");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        public SubtitleContentPlugin get() {
            SubtitlePluginResponseHolder subtitlePluginResponseHolder = new SubtitlePluginResponseHolder();
            return new SubtitleContentPlugin(new SubtitlePresetsAction(this.mDownloadService, subtitlePluginResponseHolder), subtitlePluginResponseHolder);
        }
    }

    SubtitleContentPlugin(@Nonnull SubtitlePresetsAction subtitlePresetsAction, @Nonnull SubtitlePluginResponseHolder subtitlePluginResponseHolder) {
        this.mFirstAction = (SubtitlePresetsAction) Preconditions.checkNotNull(subtitlePresetsAction, "firstAction");
        this.mSubtitlePluginResponseHolder = (SubtitlePluginResponseHolder) Preconditions.checkNotNull(subtitlePluginResponseHolder, "subtitleResponseHolder");
    }

    @Override // com.amazon.avod.media.download.plugin.ContentFetcherPlugin
    public void init(@Nonnull ContentFetcherPluginContext contentFetcherPluginContext) {
        this.mPluginContext = (ContentFetcherPluginContext) Preconditions.checkNotNull(contentFetcherPluginContext, "pluginContext");
        this.mFirstAction.init(contentFetcherPluginContext);
        this.mSubtitlePluginResponseHolder.reset();
    }

    @Override // com.amazon.avod.media.download.plugin.ContentFetcherPlugin
    public ContentFetcherPluginAction getFirstAction() {
        return this.mFirstAction;
    }

    @Override // com.amazon.avod.media.download.plugin.ContentFetcherPlugin
    public Object getLock() {
        return PLUGIN_CACHE_LOCK;
    }

    @Override // com.amazon.avod.media.download.plugin.ContentFetcherPlugin
    public void clear() {
        PluginLoadStatus.Source loadSource = this.mSubtitlePluginResponseHolder.getLoadSource();
        if (loadSource == PluginLoadStatus.Source.NETWORK) {
            File file = new File(this.mPluginContext.getStoragePath(), "subtitles");
            if (DiskUtils.deleteFile(file)) {
                DLog.devf("Clearing subtitle file %s", file);
                return;
            } else {
                DLog.warnf("There was an error deleting the subtitles directory!");
                return;
            }
        }
        DLog.devf("Not clearing subtitle file because source was %s.", loadSource);
    }

    public void setLanguageDownloadListener(@Nullable LanguageDownloadListener languageDownloadListener) {
        this.mFirstAction.setLanguageDownloadListener(languageDownloadListener);
    }

    public void setLanguageSupportedListener(@Nullable LanguageSupportedListener languageSupportedListener) {
        this.mFirstAction.setLanguageSupportedListener(languageSupportedListener);
    }

    @Override // com.amazon.avod.media.download.plugin.ContentFetcherPlugin
    public Optional<ContentFetcherPluginAction> getCleanupAction() {
        return Optional.absent();
    }
}
