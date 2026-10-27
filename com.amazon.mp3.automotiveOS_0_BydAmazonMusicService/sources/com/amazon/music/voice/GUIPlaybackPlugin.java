package com.amazon.music.voice;

import com.amazon.alexa.voice.core.Consumable;
import com.amazon.alexa.voice.core.Directive;
import com.amazon.alexa.voice.core.VoiceResponder;
import com.amazon.alexa.voice.superbowl.VoicePlugin;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackAdjustSeekPositionDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackDisableRepeatDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackDisableShuffleDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackEnableRepeatDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackEnableRepeatOneDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackEnableShuffleDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackFastForwardDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackFavoriteDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackNextDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackPauseDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackPlayDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackPreviousDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackRewindDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackSetSeekPositionDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackStartOverDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackStopDirective;
import com.amazon.music.voice.playbackstate.directives.GUIPlaybackUnFavoriteDirective;
import java.util.concurrent.RejectedExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class GUIPlaybackPlugin extends VoicePlugin {
    private static final Logger LOG = LoggerFactory.getLogger(GUIPlaybackPlugin.class.getSimpleName());
    private GUIPlaybackController musicPlaybackController;
    private MediaSessionStateProvider podcastMediaSessionStateProvider;
    private GUIPlaybackController podcastPlaybackController;
    private UnsupportedActionHandler unsupportedActionHandler;

    public static class SimpleUnsupportedActionHandler implements UnsupportedActionHandler {
        @Override // com.amazon.music.voice.UnsupportedActionHandler
        public void onUnsupportedActionReceived() {
        }
    }

    GUIPlaybackPlugin(GUIPlaybackController gUIPlaybackController) {
        this.musicPlaybackController = gUIPlaybackController;
    }

    public void setPodcastPlaybackController(GUIPlaybackController gUIPlaybackController) {
        this.podcastPlaybackController = gUIPlaybackController;
    }

    public void setPodcastMediaSessionStateProvider(MediaSessionStateProvider mediaSessionStateProvider) {
        this.podcastMediaSessionStateProvider = mediaSessionStateProvider;
    }

    public void addUnsupportedActionHandler(UnsupportedActionHandler unsupportedActionHandler) {
        this.unsupportedActionHandler = unsupportedActionHandler;
    }

    @Override // com.amazon.alexa.voice.superbowl.VoicePlugin
    protected void onInitialize() {
        addSupportedDirectivesResponders();
    }

    private void addSupportedDirectivesResponders() {
        addResponderInSequence(createPlayResponder(), GUIPlaybackPlayDirective.class);
        addResponderInSequence(createPauseResponder(), GUIPlaybackPauseDirective.class);
        addResponderInSequence(createStopResponder(), GUIPlaybackStopDirective.class);
        addResponderInSequence(createNextResponder(), GUIPlaybackNextDirective.class);
        addResponderInSequence(createPreviousResponder(), GUIPlaybackPreviousDirective.class);
        addResponderInSequence(createRewindResponder(), GUIPlaybackRewindDirective.class);
        addResponderInSequence(createFastForwardResponder(), GUIPlaybackFastForwardDirective.class);
        addResponderInSequence(createStartOverResponder(), GUIPlaybackStartOverDirective.class);
        addResponderInSequence(createAdjustSeekPositionResponder(), GUIPlaybackAdjustSeekPositionDirective.class);
        addResponderInSequence(createSetSeekPositionResponder(), GUIPlaybackSetSeekPositionDirective.class);
        addResponderInSequence(createFavouriteResponder(), GUIPlaybackFavoriteDirective.class);
        addResponderInSequence(createUnFavouriteResponder(), GUIPlaybackUnFavoriteDirective.class);
        addResponderInSequence(createEnableShuffleResponder(), GUIPlaybackEnableShuffleDirective.class);
        addResponderInSequence(createDisableShuffleResponder(), GUIPlaybackDisableShuffleDirective.class);
        addResponderInSequence(createEnableRepeatResponder(), GUIPlaybackEnableRepeatDirective.class);
        addResponderInSequence(createEnableRepeatOneResponder(), GUIPlaybackEnableRepeatOneDirective.class);
        addResponderInSequence(createDisableRepeatResponder(), GUIPlaybackDisableRepeatDirective.class);
    }

    private VoiceResponder createPlayResponder() {
        return new VoiceResponder() { // from class: com.amazon.music.voice.GUIPlaybackPlugin.1
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(Consumable<Directive> consumable) {
                GUIPlaybackPlugin.this.getPlaybackController().play();
                consumable.accept();
            }
        };
    }

    private VoiceResponder createPauseResponder() {
        return new VoiceResponder() { // from class: com.amazon.music.voice.GUIPlaybackPlugin.2
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(Consumable<Directive> consumable) {
                GUIPlaybackPlugin.this.getPlaybackController().pause();
                consumable.accept();
            }
        };
    }

    private VoiceResponder createStopResponder() {
        return new VoiceResponder() { // from class: com.amazon.music.voice.GUIPlaybackPlugin.3
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(Consumable<Directive> consumable) {
                GUIPlaybackPlugin.this.getPlaybackController().stop();
                consumable.accept();
            }
        };
    }

    private VoiceResponder createNextResponder() {
        return new VoiceResponder() { // from class: com.amazon.music.voice.GUIPlaybackPlugin.4
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(Consumable<Directive> consumable) {
                GUIPlaybackPlugin.this.getPlaybackController().next();
                consumable.accept();
            }
        };
    }

    private VoiceResponder createPreviousResponder() {
        return new VoiceResponder() { // from class: com.amazon.music.voice.GUIPlaybackPlugin.5
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(Consumable<Directive> consumable) {
                GUIPlaybackPlugin.this.getPlaybackController().previous();
                consumable.accept();
            }
        };
    }

    private VoiceResponder createRewindResponder() {
        return new VoiceResponder() { // from class: com.amazon.music.voice.GUIPlaybackPlugin.6
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(Consumable<Directive> consumable) {
                GUIPlaybackController playbackController = GUIPlaybackPlugin.this.getPlaybackController();
                if (!(playbackController instanceof EMPPlaybackController)) {
                    GUIPlaybackPlugin.this.unsupportedActionHandler.onUnsupportedActionReceived();
                    consumable.reject(new RejectedExecutionException("Unsupported action - Rewind"));
                } else {
                    ((EMPPlaybackController) playbackController).rewind();
                    consumable.accept();
                }
            }
        };
    }

    private VoiceResponder createFastForwardResponder() {
        return new VoiceResponder() { // from class: com.amazon.music.voice.GUIPlaybackPlugin.7
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(Consumable<Directive> consumable) {
                GUIPlaybackController playbackController = GUIPlaybackPlugin.this.getPlaybackController();
                if (!(playbackController instanceof EMPPlaybackController)) {
                    GUIPlaybackPlugin.this.unsupportedActionHandler.onUnsupportedActionReceived();
                    consumable.reject(new RejectedExecutionException("Unsupported action - FastForward"));
                } else {
                    ((EMPPlaybackController) playbackController).fastForward();
                    consumable.accept();
                }
            }
        };
    }

    private VoiceResponder createStartOverResponder() {
        return new VoiceResponder() { // from class: com.amazon.music.voice.GUIPlaybackPlugin.8
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(Consumable<Directive> consumable) {
                GUIPlaybackController playbackController = GUIPlaybackPlugin.this.getPlaybackController();
                if (!(playbackController instanceof EMPPlaybackController)) {
                    GUIPlaybackPlugin.this.unsupportedActionHandler.onUnsupportedActionReceived();
                    consumable.reject(new RejectedExecutionException("Unsupported action - StartOver"));
                } else {
                    ((EMPPlaybackController) playbackController).startOver();
                    consumable.accept();
                }
            }
        };
    }

    private VoiceResponder createAdjustSeekPositionResponder() {
        return new VoiceResponder() { // from class: com.amazon.music.voice.GUIPlaybackPlugin.9
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(Consumable<Directive> consumable) {
                GUIPlaybackController playbackController = GUIPlaybackPlugin.this.getPlaybackController();
                GUIPlaybackAdjustSeekPositionDirective gUIPlaybackAdjustSeekPositionDirective = (GUIPlaybackAdjustSeekPositionDirective) consumable.get();
                if (!(playbackController instanceof AlexaSeekController)) {
                    GUIPlaybackPlugin.this.unsupportedActionHandler.onUnsupportedActionReceived();
                    consumable.reject(new RejectedExecutionException("Unsupported action - Adjust Seek Position"));
                } else {
                    ((AlexaSeekController) playbackController).adjustSeekPosition(gUIPlaybackAdjustSeekPositionDirective.deltaPosition);
                    consumable.accept();
                }
            }
        };
    }

    private VoiceResponder createSetSeekPositionResponder() {
        return new VoiceResponder() { // from class: com.amazon.music.voice.GUIPlaybackPlugin.10
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(Consumable<Directive> consumable) {
                GUIPlaybackController playbackController = GUIPlaybackPlugin.this.getPlaybackController();
                GUIPlaybackSetSeekPositionDirective gUIPlaybackSetSeekPositionDirective = (GUIPlaybackSetSeekPositionDirective) consumable.get();
                if (!(playbackController instanceof AlexaSeekController)) {
                    GUIPlaybackPlugin.this.unsupportedActionHandler.onUnsupportedActionReceived();
                    consumable.reject(new RejectedExecutionException("Unsupported action - Set Seek Position"));
                } else {
                    ((AlexaSeekController) playbackController).setSeekPosition(gUIPlaybackSetSeekPositionDirective.getPosition());
                    consumable.accept();
                }
            }
        };
    }

    private VoiceResponder createFavouriteResponder() {
        return new VoiceResponder() { // from class: com.amazon.music.voice.GUIPlaybackPlugin.11
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(Consumable<Directive> consumable) {
                GUIPlaybackController playbackController = GUIPlaybackPlugin.this.getPlaybackController();
                if (!(playbackController instanceof AlexaFavouritesController)) {
                    GUIPlaybackPlugin.this.unsupportedActionHandler.onUnsupportedActionReceived();
                    consumable.reject(new RejectedExecutionException("Unsupported action - Favorite"));
                } else {
                    ((AlexaFavouritesController) playbackController).toggleFavourite();
                    consumable.accept();
                }
            }
        };
    }

    private VoiceResponder createUnFavouriteResponder() {
        return new VoiceResponder() { // from class: com.amazon.music.voice.GUIPlaybackPlugin.12
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(Consumable<Directive> consumable) {
                GUIPlaybackController playbackController = GUIPlaybackPlugin.this.getPlaybackController();
                if (!(playbackController instanceof AlexaFavouritesController)) {
                    GUIPlaybackPlugin.this.unsupportedActionHandler.onUnsupportedActionReceived();
                    consumable.reject(new RejectedExecutionException("Unsupported action - UnFavorite"));
                } else {
                    ((AlexaFavouritesController) playbackController).toggleUnFavourite();
                    consumable.accept();
                }
            }
        };
    }

    private VoiceResponder createEnableShuffleResponder() {
        return new VoiceResponder() { // from class: com.amazon.music.voice.GUIPlaybackPlugin.13
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(Consumable<Directive> consumable) {
                GUIPlaybackController playbackController = GUIPlaybackPlugin.this.getPlaybackController();
                if (!(playbackController instanceof AlexaPlaylistController)) {
                    GUIPlaybackPlugin.this.unsupportedActionHandler.onUnsupportedActionReceived();
                    consumable.reject(new RejectedExecutionException("Unsupported action - Enable Shuffle"));
                } else {
                    ((AlexaPlaylistController) playbackController).enableShuffle();
                    consumable.accept();
                }
            }
        };
    }

    private VoiceResponder createDisableShuffleResponder() {
        return new VoiceResponder() { // from class: com.amazon.music.voice.GUIPlaybackPlugin.14
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(Consumable<Directive> consumable) {
                GUIPlaybackController playbackController = GUIPlaybackPlugin.this.getPlaybackController();
                if (!(playbackController instanceof AlexaPlaylistController)) {
                    GUIPlaybackPlugin.this.unsupportedActionHandler.onUnsupportedActionReceived();
                    consumable.reject(new RejectedExecutionException("Unsupported action - Disable Shuffle"));
                } else {
                    ((AlexaPlaylistController) playbackController).disableShuffle();
                    consumable.accept();
                }
            }
        };
    }

    private VoiceResponder createEnableRepeatResponder() {
        return new VoiceResponder() { // from class: com.amazon.music.voice.GUIPlaybackPlugin.15
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(Consumable<Directive> consumable) {
                GUIPlaybackController playbackController = GUIPlaybackPlugin.this.getPlaybackController();
                if (!(playbackController instanceof AlexaPlaylistController)) {
                    GUIPlaybackPlugin.this.unsupportedActionHandler.onUnsupportedActionReceived();
                    consumable.reject(new RejectedExecutionException("Unsupported action - Enable Repeat"));
                } else {
                    ((AlexaPlaylistController) playbackController).enableRepeat();
                    consumable.accept();
                }
            }
        };
    }

    private VoiceResponder createEnableRepeatOneResponder() {
        return new VoiceResponder() { // from class: com.amazon.music.voice.GUIPlaybackPlugin.16
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(Consumable<Directive> consumable) {
                GUIPlaybackController playbackController = GUIPlaybackPlugin.this.getPlaybackController();
                if (!(playbackController instanceof AlexaPlaylistController)) {
                    GUIPlaybackPlugin.this.unsupportedActionHandler.onUnsupportedActionReceived();
                    consumable.reject(new RejectedExecutionException("Unsupported action - Enable Repeat One"));
                } else {
                    ((AlexaPlaylistController) playbackController).enableRepeatOne();
                    consumable.accept();
                }
            }
        };
    }

    private VoiceResponder createDisableRepeatResponder() {
        return new VoiceResponder() { // from class: com.amazon.music.voice.GUIPlaybackPlugin.17
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(Consumable<Directive> consumable) {
                GUIPlaybackController playbackController = GUIPlaybackPlugin.this.getPlaybackController();
                if (!(playbackController instanceof AlexaPlaylistController)) {
                    GUIPlaybackPlugin.this.unsupportedActionHandler.onUnsupportedActionReceived();
                    consumable.reject(new RejectedExecutionException("Unsupported action - Disable Repeat"));
                } else {
                    ((AlexaPlaylistController) playbackController).disableRepeat();
                    consumable.accept();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public GUIPlaybackController getPlaybackController() {
        MediaSessionStateProvider mediaSessionStateProvider;
        if (this.podcastPlaybackController != null && (mediaSessionStateProvider = this.podcastMediaSessionStateProvider) != null && mediaSessionStateProvider.isActive()) {
            return this.podcastPlaybackController;
        }
        return this.musicPlaybackController;
    }
}
