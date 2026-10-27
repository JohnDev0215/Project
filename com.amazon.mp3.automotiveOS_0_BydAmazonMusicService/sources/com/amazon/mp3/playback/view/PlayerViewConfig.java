package com.amazon.mp3.playback.view;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.cloudqueue.sequencer.AdMediaItem;
import com.amazon.mp3.cloudqueue.sequencer.InterludeMediaItem;
import com.amazon.mp3.explore.playback.HttpsMediaItem;
import com.amazon.mp3.library.provider.source.nowplaying.AlexaMediaItem;
import com.amazon.mp3.util.extensions.MediaItemExtensions;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.podcast.media.playback.PodcastMediaItem;

/* JADX INFO: loaded from: classes3.dex */
public class PlayerViewConfig {
    private PlayerViewContentType mPlayerViewContentType;
    private boolean mShowAddToLibrary;
    private boolean mShowCasting;
    private boolean mShowContextMenu;
    private boolean mShowForward;
    private boolean mShowNext;
    private boolean mShowPlayPause;
    private boolean mShowPlayQueue;
    private boolean mShowPodcastEpisodeList;
    private boolean mShowPrevious;
    private boolean mShowRating;
    private boolean mShowRepeat;
    private boolean mShowRewind;
    private boolean mShowShuffle;
    private boolean mShowSpeedControl;

    /* synthetic */ PlayerViewConfig(ConfigBuilder configBuilder, AnonymousClass1 anonymousClass1) {
        this(configBuilder);
    }

    public enum PlayerViewContentType {
        AD,
        ALEXA,
        HTTPS,
        MUSIC,
        RATABLE_MUSIC,
        VIDEO,
        REMOTE_CLOUD_QUEUE,
        PODCAST,
        INTERLUDE;

        public static PlayerViewContentType fromMediaItem(MediaItem mediaItem) {
            if (mediaItem instanceof AdMediaItem) {
                return AD;
            }
            if (mediaItem instanceof InterludeMediaItem) {
                return INTERLUDE;
            }
            if (mediaItem instanceof HttpsMediaItem) {
                return HTTPS;
            }
            if (mediaItem instanceof PodcastMediaItem) {
                return PODCAST;
            }
            if (mediaItem.canRate()) {
                return RATABLE_MUSIC;
            }
            if (mediaItem instanceof AlexaMediaItem) {
                return ALEXA;
            }
            if (MediaItemExtensions.isVideo(mediaItem)) {
                return VIDEO;
            }
            return MUSIC;
        }
    }

    private PlayerViewConfig(final ConfigBuilder builder) {
        this.mPlayerViewContentType = builder.playerViewContentType;
        this.mShowNext = builder.showNext;
        this.mShowPrevious = builder.showPrev;
        this.mShowPlayPause = builder.showPlayPause;
        this.mShowAddToLibrary = builder.showAddToLibrary;
        this.mShowContextMenu = builder.showContextMenu;
        this.mShowCasting = builder.showCasting;
        this.mShowPlayQueue = builder.showPlayQueue;
        this.mShowShuffle = builder.showShuffle;
        this.mShowRepeat = builder.showRepeat;
        this.mShowRating = builder.showRating;
        this.mShowRewind = builder.showRewind;
        this.mShowForward = builder.showForward;
        this.mShowSpeedControl = builder.showSpeedControl;
        this.mShowPodcastEpisodeList = builder.showPodcastEpisodeList;
    }

    public boolean isPodcastView() {
        return this.mPlayerViewContentType == PlayerViewContentType.PODCAST;
    }

    public boolean isMusicPlayQueueAvailable() {
        PlayerViewContentType playerViewContentType = this.mPlayerViewContentType;
        return playerViewContentType == PlayerViewContentType.RATABLE_MUSIC || playerViewContentType == PlayerViewContentType.MUSIC;
    }

    public static PlayerViewConfig createPlayerViewConfig(final PlayerViewContentType playerViewContentType) {
        ConfigBuilder configBuilderBuilder = builder(playerViewContentType);
        configBuilderBuilder.withPlayPause();
        switch (AnonymousClass1.$SwitchMap$com$amazon$mp3$playback$view$PlayerViewConfig$PlayerViewContentType[playerViewContentType.ordinal()]) {
            case 1:
                configBuilderBuilder.withNext().withPrev().withRepeat().withShuffle().withAddToLibrary().withContextMenu().withCasting().withPlayQueue();
                if (AmazonApplication.getCapabilities().isNPSLikesSupported()) {
                    configBuilderBuilder.withRating();
                }
                break;
            case 2:
                if (AmazonApplication.getCapabilities().isFreeTierSkipLimitEducationalTooltipEnabled() || AmazonApplication.getCapabilities().isFreeTierSkipsRemainingTooltipEnabled()) {
                    configBuilderBuilder.withNext();
                }
                break;
            case 3:
                configBuilderBuilder.withNext().withPrev().withContextMenu();
                break;
            case 4:
                configBuilderBuilder.withNext().withPrev().withRating().withAddToLibrary().withContextMenu().withCasting();
                break;
            case 5:
                configBuilderBuilder.withNext().withPrev().withCasting().withContextMenu();
                break;
            case 6:
                configBuilderBuilder.withNext().withPrev().withRepeat().withShuffle().withContextMenu().withPlayQueue();
                break;
            case 7:
                configBuilderBuilder.withNext();
                break;
            case 8:
                configBuilderBuilder.withForward().withRewind().withNext().withPrev().withSpeedControl().withPodcastEpisodeList();
                break;
        }
        return configBuilderBuilder.build();
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.view.PlayerViewConfig$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$playback$view$PlayerViewConfig$PlayerViewContentType;

        static {
            int[] iArr = new int[PlayerViewContentType.values().length];
            $SwitchMap$com$amazon$mp3$playback$view$PlayerViewConfig$PlayerViewContentType = iArr;
            try {
                iArr[PlayerViewContentType.MUSIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$view$PlayerViewConfig$PlayerViewContentType[PlayerViewContentType.AD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$view$PlayerViewConfig$PlayerViewContentType[PlayerViewContentType.INTERLUDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$view$PlayerViewConfig$PlayerViewContentType[PlayerViewContentType.RATABLE_MUSIC.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$view$PlayerViewConfig$PlayerViewContentType[PlayerViewContentType.ALEXA.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$view$PlayerViewConfig$PlayerViewContentType[PlayerViewContentType.VIDEO.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$view$PlayerViewConfig$PlayerViewContentType[PlayerViewContentType.REMOTE_CLOUD_QUEUE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$view$PlayerViewConfig$PlayerViewContentType[PlayerViewContentType.PODCAST.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public PlayerViewContentType getPlayerViewContentType() {
        return this.mPlayerViewContentType;
    }

    public boolean isShowNext() {
        return this.mShowNext;
    }

    public boolean isShowPrevious() {
        return this.mShowPrevious;
    }

    public boolean isShowPlayPause() {
        return this.mShowPlayPause;
    }

    public boolean isShowAddToLibrary() {
        return this.mShowAddToLibrary;
    }

    public boolean isShowContextMenu() {
        return this.mShowContextMenu;
    }

    public boolean isShowCasting() {
        return this.mShowCasting;
    }

    public boolean isShowPlayQueue() {
        return this.mShowPlayQueue;
    }

    public boolean isShowShuffle() {
        return this.mShowShuffle;
    }

    public boolean isShowRepeat() {
        return this.mShowShuffle;
    }

    public boolean isShowRating() {
        return this.mShowRating;
    }

    public boolean isShowRewind() {
        return this.mShowRewind;
    }

    public boolean isShowForward() {
        return this.mShowForward;
    }

    public boolean isShowSpeedControl() {
        return this.mShowSpeedControl;
    }

    public boolean isShowPodcastEpisodeList() {
        return this.mShowPodcastEpisodeList;
    }

    private static ConfigBuilder builder(final PlayerViewContentType PlayerViewContentType2) {
        return new ConfigBuilder(PlayerViewContentType2, null);
    }

    private static final class ConfigBuilder {
        private PlayerViewContentType playerViewContentType;
        private boolean showAddToLibrary;
        private boolean showCasting;
        private boolean showContextMenu;
        private boolean showForward;
        private boolean showNext;
        private boolean showPlayPause;
        private boolean showPlayQueue;
        private boolean showPodcastEpisodeList;
        private boolean showPrev;
        private boolean showRating;
        private boolean showRepeat;
        private boolean showRewind;
        private boolean showShuffle;
        private boolean showSpeedControl;

        /* synthetic */ ConfigBuilder(PlayerViewContentType playerViewContentType, AnonymousClass1 anonymousClass1) {
            this(playerViewContentType);
        }

        private ConfigBuilder(final PlayerViewContentType contentViewType) {
            this.playerViewContentType = contentViewType;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ConfigBuilder withNext() {
            this.showNext = true;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ConfigBuilder withPrev() {
            this.showPrev = true;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ConfigBuilder withPlayPause() {
            this.showPlayPause = true;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ConfigBuilder withAddToLibrary() {
            this.showAddToLibrary = true;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ConfigBuilder withContextMenu() {
            this.showContextMenu = true;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ConfigBuilder withCasting() {
            this.showCasting = true;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ConfigBuilder withPlayQueue() {
            this.showPlayQueue = true;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ConfigBuilder withRating() {
            this.showRating = true;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ConfigBuilder withShuffle() {
            this.showShuffle = true;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ConfigBuilder withRepeat() {
            this.showRepeat = true;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ConfigBuilder withForward() {
            this.showForward = true;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ConfigBuilder withRewind() {
            this.showRewind = true;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ConfigBuilder withSpeedControl() {
            this.showSpeedControl = true;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ConfigBuilder withPodcastEpisodeList() {
            this.showPodcastEpisodeList = true;
            return this;
        }

        public PlayerViewConfig build() {
            return new PlayerViewConfig(this, null);
        }
    }
}
