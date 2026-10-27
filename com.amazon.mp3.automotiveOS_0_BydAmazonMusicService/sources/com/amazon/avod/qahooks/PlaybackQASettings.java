package com.amazon.avod.qahooks;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import com.amazon.avod.fileio.MediaStoreFileUtil;
import com.amazon.avod.perf.internal.DefaultPerfSettings;
import com.amazon.avod.perf.internal.PerfSettings;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.InitializationLatch;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackQASettings {
    private static int MEMORY_BUFFER_MAX_SIZE = 50000;
    private static final String PLAYBACK_QA_HOOKS_DIRECTORY;
    private static final String PLAYBACK_QA_HOOKS_DISK_PATH;
    private final InitializationLatch mInitializationLatch;
    private MediaStoreFileUtil mMediaStoreFileUtil;
    private PerfSettings mPerfSettings;
    private PlaybackQAHookSettings mPlaybackQAHookSettings;
    private File mPlaybackQAOverrideDirectory;
    private File mPlaybackQAOverrideFile;
    private QaSettings mQaSettings;

    static {
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
        PLAYBACK_QA_HOOKS_DIRECTORY = path;
        PLAYBACK_QA_HOOKS_DISK_PATH = path + File.separator + "QA_HOOKS.properties";
    }

    private static class SingletonHolder {
        private static final PlaybackQASettings INSTANCE = new PlaybackQASettings();

        private SingletonHolder() {
        }
    }

    public static PlaybackQASettings getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private PlaybackQASettings() {
        this.mInitializationLatch = new InitializationLatch(this);
        this.mPlaybackQAOverrideFile = new File(PLAYBACK_QA_HOOKS_DISK_PATH);
        this.mPlaybackQAOverrideDirectory = new File(PLAYBACK_QA_HOOKS_DIRECTORY);
        this.mPlaybackQAHookSettings = new DefaultPlaybackQAHookSettings();
        this.mQaSettings = QaSettings.getInstance();
        this.mPerfSettings = new DefaultPerfSettings();
        this.mMediaStoreFileUtil = new MediaStoreFileUtil();
    }

    public void initialize(boolean z, @Nonnull Context context) {
        if (this.mInitializationLatch.isInitialized()) {
            return;
        }
        this.mInitializationLatch.start(30L, TimeUnit.SECONDS);
        if (z) {
            try {
                if (ensureQaHooksOverrideFile(context)) {
                    PlaybackQAOverrideSettingsParser playbackQAOverrideSettingsParser = new PlaybackQAOverrideSettingsParser(this.mPlaybackQAOverrideFile, context);
                    this.mPlaybackQAHookSettings = new OverridePlaybackQAHookSettings(playbackQAOverrideSettingsParser);
                    this.mPerfSettings = new OverridePerfSettings(playbackQAOverrideSettingsParser);
                }
            } finally {
                this.mInitializationLatch.complete();
            }
        }
    }

    private boolean ensureQaHooksOverrideFile(@Nonnull Context context) {
        if (this.mPlaybackQAOverrideFile.exists()) {
            return true;
        }
        if (Build.VERSION.SDK_INT < 29) {
            try {
                if (this.mPlaybackQAOverrideDirectory.exists()) {
                    return this.mPlaybackQAOverrideFile.createNewFile();
                }
                return this.mPlaybackQAOverrideDirectory.mkdirs() && this.mPlaybackQAOverrideFile.createNewFile();
            } catch (IOException unused) {
                DLog.warnf("Unable to create new QA_HOOKS file");
                return false;
            }
        }
        return this.mMediaStoreFileUtil.createFile(this.mPlaybackQAOverrideFile, context, null);
    }

    public boolean isPlaybackQAHooksEnabled() {
        if (shouldWaitOnInit()) {
            this.mInitializationLatch.waitOnInitializationUninterruptibly();
        }
        return this.mPlaybackQAHookSettings.isPlaybackQAHooksEnabled();
    }

    @Nonnull
    public PerfSettings getPerfSettings() {
        return this.mPerfSettings;
    }

    private boolean shouldWaitOnInit() {
        return this.mQaSettings.getAppMode().supportsQa();
    }

    private static class OverridePlaybackQAHookSettings implements PlaybackQAHookSettings {
        private final PlaybackQAOverrideSettingsParser mParser;

        private OverridePlaybackQAHookSettings(PlaybackQAOverrideSettingsParser playbackQAOverrideSettingsParser) {
            this.mParser = (PlaybackQAOverrideSettingsParser) Preconditions.checkNotNull(playbackQAOverrideSettingsParser, "playbackQAOverrideSettingsParser");
        }

        @Override // com.amazon.avod.qahooks.PlaybackQAHookSettings
        public boolean isPlaybackQAHooksEnabled() {
            return this.mParser.isPlaybackQAHooksEnabled();
        }
    }

    private static class OverridePerfSettings implements PerfSettings {
        private final PlaybackQAOverrideSettingsParser mParser;

        OverridePerfSettings(PlaybackQAOverrideSettingsParser playbackQAOverrideSettingsParser) {
            this.mParser = playbackQAOverrideSettingsParser;
        }

        @Override // com.amazon.avod.perf.internal.PerfSettings
        public int getTraceLevel() {
            return this.mParser.getTraceLevel();
        }

        @Override // com.amazon.avod.perf.internal.PerfSettings
        public int getMemoryBufferMaxSize() {
            return this.mParser.getMemoryBufferMaxSize();
        }

        @Override // com.amazon.avod.perf.internal.PerfSettings
        public boolean isPerfLoggingEnabled() {
            return this.mParser.isPerfLoggingEnabled();
        }

        @Override // com.amazon.avod.perf.internal.PerfSettings
        public boolean isLazyFormattingEnabled() {
            return this.mParser.isLazyFormattingEnabled();
        }
    }

    private static class PlaybackQAOverrideSettingsParser extends CoreQAOverrideSettingsParser {
        private PlaybackQAOverrideSettingsParser(@Nonnull File file, @Nonnull Context context) {
            super(file, context);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isPlaybackQAHooksEnabled() {
            return !"true".equalsIgnoreCase(this.mSettings.get("disable_qa_hooks"));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getTraceLevel() {
            try {
                return Integer.parseInt((String) MoreObjects.firstNonNull(this.mSettings.get("aiv_profiler_trace_level"), "0"));
            } catch (NumberFormatException e) {
                DLog.exceptionf(e);
                return 0;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getMemoryBufferMaxSize() {
            try {
                return Integer.parseInt((String) MoreObjects.firstNonNull(this.mSettings.get("aiv_profiler_memory_buffer_size"), "50000"));
            } catch (NumberFormatException e) {
                DLog.exceptionf(e);
                return PlaybackQASettings.MEMORY_BUFFER_MAX_SIZE;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isPerfLoggingEnabled() {
            return "true".equalsIgnoreCase(this.mSettings.get("aiv_profiler_file_logging"));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isLazyFormattingEnabled() {
            return "true".equalsIgnoreCase(this.mSettings.get("aiv_profiler_lazy_formatting"));
        }
    }

    private static class DefaultPlaybackQAHookSettings implements PlaybackQAHookSettings {
        @Override // com.amazon.avod.qahooks.PlaybackQAHookSettings
        public boolean isPlaybackQAHooksEnabled() {
            return false;
        }

        private DefaultPlaybackQAHookSettings() {
        }
    }
}
