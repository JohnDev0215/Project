package com.amazon.video.sdk.avod.playbackclient.subtitle.download;

import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.json.JacksonCache;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleConfig;
import com.amazon.avod.util.DLog;
import com.amazon.video.sdk.avod.playbackclient.subtitle.SubtitlePreset;
import com.amazon.video.sdk.avod.playbackclient.subtitle.SubtitlePresets;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes5.dex */
class PresetSerializer {
    private final PresetIconDownloader mIconDownloader;
    private final ObjectMapper mObjectMapper;
    private final StorageHelper mStorageHelper;
    private final SubtitleConfig mSubtitleConfig;

    PresetSerializer() {
        this(SubtitleConfig.getInstance(), StorageHelper.getInstance(), new PresetIconDownloader(), JacksonCache.OBJECT_MAPPER);
    }

    PresetSerializer(@Nonnull SubtitleConfig subtitleConfig, @Nonnull StorageHelper storageHelper, @Nonnull PresetIconDownloader presetIconDownloader, @Nonnull ObjectMapper objectMapper) {
        this.mSubtitleConfig = (SubtitleConfig) Preconditions.checkNotNull(subtitleConfig, "subtitleConfig");
        this.mStorageHelper = (StorageHelper) Preconditions.checkNotNull(storageHelper, "storageHelper");
        this.mIconDownloader = (PresetIconDownloader) Preconditions.checkNotNull(presetIconDownloader, "iconDownloader");
        this.mObjectMapper = (ObjectMapper) Preconditions.checkNotNull(objectMapper, "objectMapper");
    }

    public boolean savePresetsToDisk(@Nonnull SubtitlePresets subtitlePresets) {
        Preconditions.checkNotNull(subtitlePresets, "Cannot save null presets to disk");
        try {
            File presetDirectoryPath = getPresetDirectoryPath();
            File file = new File(presetDirectoryPath, "subtitlepresets.json");
            Files.createParentDirs(file);
            DLog.devf("Saving subtitle preset file to disk at %s", file);
            SimpleModule simpleModule = new SimpleModule();
            simpleModule.addSerializer(SubtitlePresets.class, new SubtitlePresets.SubtitlePresetsSerializer(SubtitlePresets.class));
            simpleModule.addSerializer(SubtitlePreset.class, new SubtitlePresets.SubtitlePresetSerializer(SubtitlePreset.class));
            this.mObjectMapper.registerModule(simpleModule);
            this.mObjectMapper.writeValue(file, subtitlePresets);
            UnmodifiableIterator<SubtitlePreset> it = subtitlePresets.getPresets().iterator();
            while (it.hasNext()) {
                this.mIconDownloader.downloadIcon(presetDirectoryPath, it.next(), this.mStorageHelper);
            }
            return true;
        } catch (IOException e) {
            DLog.exceptionf(e, "IO error trying to persist subtitle presets", new Object[0]);
            return false;
        }
    }

    private File getPresetDirectoryPath() {
        return new File(this.mStorageHelper.getGeneralFileDir(), this.mSubtitleConfig.getRelativePath());
    }

    static class PresetIconDownloader {
        PresetIconDownloader() {
        }

        public void downloadIcon(@Nonnull File file, @Nonnull SubtitlePreset subtitlePreset, @Nonnull StorageHelper storageHelper) throws IOException {
            URL iconUrl = subtitlePreset.getIconUrl();
            if (iconUrl == null) {
                return;
            }
            String normalizedIconFilename = subtitlePreset.getNormalizedIconFilename();
            File file2 = new File(file, normalizedIconFilename);
            DLog.devf("Saving subtitle preset icon %s to disk at %s", normalizedIconFilename, file2);
            try {
                InputStream inputStreamOpenStream = FirebasePerfUrlConnection.openStream(iconUrl);
                try {
                    Files.write(ByteStreams.toByteArray(inputStreamOpenStream), file2);
                    if (inputStreamOpenStream != null) {
                        inputStreamOpenStream.close();
                    }
                } finally {
                }
            } catch (ArrayIndexOutOfBoundsException unused) {
                DLog.logf("Unable to download icon for subtitle preset: %s", normalizedIconFilename);
            }
        }
    }
}
