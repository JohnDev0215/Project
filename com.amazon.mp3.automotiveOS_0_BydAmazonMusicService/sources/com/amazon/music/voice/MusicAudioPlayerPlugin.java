package com.amazon.music.voice;

import android.os.Handler;
import com.amazon.alexa.voice.core.AudioPlayer;
import com.amazon.alexa.voice.core.Consumable;
import com.amazon.alexa.voice.core.Directive;
import com.amazon.alexa.voice.core.Logger;
import com.amazon.alexa.voice.core.VoiceResponder;
import com.amazon.alexa.voice.core.channel.Channel;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.processor.superbowl.Context;
import com.amazon.alexa.voice.core.processor.superbowl.ContextResolver;
import com.amazon.alexa.voice.core.processor.superbowl.context.AudioPlaybackContext;
import com.amazon.alexa.voice.core.processor.superbowl.context.AudioPlaybackState;
import com.amazon.alexa.voice.core.processor.superbowl.context.AudioPlayerState;
import com.amazon.alexa.voice.core.processor.superbowl.directives.AudioClearQueueDirective;
import com.amazon.alexa.voice.core.processor.superbowl.directives.AudioPlayDirective;
import com.amazon.alexa.voice.core.processor.superbowl.directives.AudioStopDirective;
import com.amazon.alexa.voice.core.processor.superbowl.events.AudioPlaybackFailedEvent;
import com.amazon.alexa.voice.core.processor.superbowl.events.AudioPlaybackFinishedEvent;
import com.amazon.alexa.voice.core.processor.superbowl.events.AudioPlaybackNearlyFinishedEvent;
import com.amazon.alexa.voice.core.processor.superbowl.events.AudioPlaybackPausedEvent;
import com.amazon.alexa.voice.core.processor.superbowl.events.AudioPlaybackQueueClearedEvent;
import com.amazon.alexa.voice.core.processor.superbowl.events.AudioPlaybackResumedEvent;
import com.amazon.alexa.voice.core.processor.superbowl.events.AudioPlaybackStartedEvent;
import com.amazon.alexa.voice.core.processor.superbowl.events.AudioPlaybackStoppedEvent;
import com.amazon.alexa.voice.core.processor.superbowl.events.AudioPlaybackStutterFinishedEvent;
import com.amazon.alexa.voice.core.processor.superbowl.events.AudioPlaybackStutterStartedEvent;
import com.amazon.alexa.voice.core.processor.superbowl.events.AudioProgressReportDelayElapsedEvent;
import com.amazon.alexa.voice.core.processor.superbowl.events.AudioProgressReportIntervalElapsedEvent;
import com.amazon.alexa.voice.core.processor.superbowl.events.NextCommandIssuedEvent;
import com.amazon.alexa.voice.core.processor.superbowl.events.PreviousCommandIssuedEvent;
import com.amazon.alexa.voice.superbowl.VoicePlugin;
import com.amazon.music.voice.ShuffleStateEvent;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes4.dex */
public final class MusicAudioPlayerPlugin extends VoicePlugin {
    private AudioPlayDirective.AudioItem audioItem;
    private final AudioPlayerProxy audioPlayer;
    private boolean awaitingIdle;
    private final Channel channel;
    private long nearlyFinishedBeginningThreshold;
    private long nearlyFinishedEndThreshold;
    private long nearlyFinishedInterval;
    private final PriorityBehavior priorityBehavior;
    private boolean sentBufferUnderrun;
    private boolean sentNearlyFinished;
    private boolean sentPaused;
    private boolean sentPlaybackStarted;
    private long stutterStartedMillis;
    private boolean wasStopped;
    private boolean wePaused;
    private final Deque<AudioPlayDirective.AudioItem> queue = new ArrayDeque();
    private final Handler handler = new Handler();
    private final List<AudioPlayerListener> audioPlayerListeners = new ArrayList();
    private final AudioPlayer.Callback audioPlayerCallback = new AudioPlayerCallback();
    private final Channel.Listener channelListener = new Channel.SimpleListener() { // from class: com.amazon.music.voice.MusicAudioPlayerPlugin.1
        @Override // com.amazon.alexa.voice.core.channel.Channel.SimpleListener, com.amazon.alexa.voice.core.channel.Channel.Listener
        public void onBroughtToForeground(Channel channel) {
            if (MusicAudioPlayerPlugin.this.priorityBehavior == PriorityBehavior.DUCK) {
                MusicAudioPlayerPlugin.this.audioPlayer.fadeVolume(MusicAudioPlayerPlugin.this.audioPlayer.getMaximumVolume());
                return;
            }
            if (MusicAudioPlayerPlugin.this.priorityBehavior == PriorityBehavior.PAUSE && MusicAudioPlayerPlugin.this.audioItem != null && !MusicAudioPlayerPlugin.this.wasStopped && MusicAudioPlayerPlugin.this.audioPlayer.isPaused() && MusicAudioPlayerPlugin.this.wePaused) {
                MusicAudioPlayerPlugin.this.audioPlayer.play();
                MusicAudioPlayerPlugin.this.wePaused = false;
            }
        }

        @Override // com.amazon.alexa.voice.core.channel.Channel.SimpleListener, com.amazon.alexa.voice.core.channel.Channel.Listener
        public void onBroughtToBackground(Channel channel) {
            if (MusicAudioPlayerPlugin.this.priorityBehavior == PriorityBehavior.DUCK) {
                MusicAudioPlayerPlugin.this.audioPlayer.fadeVolume(MusicAudioPlayerPlugin.this.audioPlayer.getMinimumVolume());
                return;
            }
            if (MusicAudioPlayerPlugin.this.priorityBehavior != PriorityBehavior.PAUSE || MusicAudioPlayerPlugin.this.audioItem == null || MusicAudioPlayerPlugin.this.wasStopped || !MusicAudioPlayerPlugin.this.audioPlayer.isPlaying()) {
                return;
            }
            MusicAudioPlayerPlugin.this.wePaused = true;
            MusicAudioPlayerPlugin.this.audioPlayer.pause();
        }
    };
    private final Runnable reportProgressDelay = new Runnable() { // from class: com.amazon.music.voice.MusicAudioPlayerPlugin.2
        @Override // java.lang.Runnable
        public void run() {
            if (MusicAudioPlayerPlugin.this.audioItem == null) {
                return;
            }
            Logger.debug("Sending AudioPlayer.ProgressReportDelayElapsed");
            MusicAudioPlayerPlugin.this.postEvent(new AudioProgressReportDelayElapsedEvent.Builder().token(MusicAudioPlayerPlugin.this.audioItem.getStream().getToken()).offsetInMilliseconds(MusicAudioPlayerPlugin.this.audioPlayer.getCurrentPosition()).build());
        }
    };
    private final Runnable reportProgressInterval = new Runnable() { // from class: com.amazon.music.voice.MusicAudioPlayerPlugin.3
        @Override // java.lang.Runnable
        public void run() {
            if (MusicAudioPlayerPlugin.this.audioItem == null) {
                return;
            }
            Logger.debug("Sending AudioPlayer.ProgressReportIntervalElapsed");
            AudioPlayDirective.Stream stream = MusicAudioPlayerPlugin.this.audioItem.getStream();
            MusicAudioPlayerPlugin.this.postEvent(new AudioProgressReportIntervalElapsedEvent.Builder().token(stream.getToken()).offsetInMilliseconds(MusicAudioPlayerPlugin.this.audioPlayer.getCurrentPosition()).build());
            MusicAudioPlayerPlugin.this.handler.postDelayed(this, stream.getProgressReport().getIntervalInMilliseconds());
        }
    };
    private final Runnable nearlyFinished = new Runnable() { // from class: com.amazon.music.voice.MusicAudioPlayerPlugin.4
        @Override // java.lang.Runnable
        public void run() {
            if (MusicAudioPlayerPlugin.this.sentNearlyFinished || !MusicAudioPlayerPlugin.this.sentPlaybackStarted || MusicAudioPlayerPlugin.this.sentPaused || MusicAudioPlayerPlugin.this.sentBufferUnderrun || MusicAudioPlayerPlugin.this.audioItem == null) {
                return;
            }
            long currentPosition = MusicAudioPlayerPlugin.this.audioPlayer.getCurrentPosition();
            long duration = MusicAudioPlayerPlugin.this.audioPlayer.getDuration();
            if (currentPosition >= MusicAudioPlayerPlugin.this.nearlyFinishedBeginningThreshold || duration - currentPosition <= MusicAudioPlayerPlugin.this.nearlyFinishedEndThreshold) {
                MusicAudioPlayerPlugin.this.sentNearlyFinished = true;
                Logger.debug("Sending AudioPlayer.PlaybackNearlyFinished");
                MusicAudioPlayerPlugin.this.postEvent(new AudioPlaybackNearlyFinishedEvent.Builder().token(MusicAudioPlayerPlugin.this.audioItem.getStream().getToken()).offsetInMilliseconds(currentPosition).build());
                return;
            }
            MusicAudioPlayerPlugin.this.handler.postDelayed(this, MusicAudioPlayerPlugin.this.nearlyFinishedInterval);
        }
    };

    public interface AudioPlayerListener {
        void onAudioItemDropped(AudioPlayDirective.AudioItem audioItem);

        void onAudioItemEnqueued(AudioPlayDirective.AudioItem audioItem);

        void onAudioItemTaken(AudioPlayDirective.AudioItem audioItem);

        void onAudioPlaybackEnded(AudioPlayDirective.AudioItem audioItem);

        void onAudioPlaybackFailed(AudioPlayDirective.AudioItem audioItem, Throwable th);

        void onAudioPlaybackPaused(AudioPlayDirective.AudioItem audioItem);

        void onAudioPlaybackStarted(AudioPlayDirective.AudioItem audioItem);
    }

    public enum PriorityBehavior {
        DUCK,
        PAUSE
    }

    public static class SimpleAudioPlayerListener implements AudioPlayerListener {
        @Override // com.amazon.music.voice.MusicAudioPlayerPlugin.AudioPlayerListener
        public void onAudioItemDropped(AudioPlayDirective.AudioItem audioItem) {
        }

        @Override // com.amazon.music.voice.MusicAudioPlayerPlugin.AudioPlayerListener
        public void onAudioItemEnqueued(AudioPlayDirective.AudioItem audioItem) {
        }

        @Override // com.amazon.music.voice.MusicAudioPlayerPlugin.AudioPlayerListener
        public void onAudioItemTaken(AudioPlayDirective.AudioItem audioItem) {
        }

        @Override // com.amazon.music.voice.MusicAudioPlayerPlugin.AudioPlayerListener
        public void onAudioPlaybackEnded(AudioPlayDirective.AudioItem audioItem) {
        }

        @Override // com.amazon.music.voice.MusicAudioPlayerPlugin.AudioPlayerListener
        public void onAudioPlaybackFailed(AudioPlayDirective.AudioItem audioItem, Throwable th) {
        }

        @Override // com.amazon.music.voice.MusicAudioPlayerPlugin.AudioPlayerListener
        public void onAudioPlaybackPaused(AudioPlayDirective.AudioItem audioItem) {
        }

        @Override // com.amazon.music.voice.MusicAudioPlayerPlugin.AudioPlayerListener
        public void onAudioPlaybackStarted(AudioPlayDirective.AudioItem audioItem) {
        }
    }

    MusicAudioPlayerPlugin(Builder builder) {
        this.audioPlayer = builder.audioPlayer;
        this.channel = builder.channel;
        this.nearlyFinishedBeginningThreshold = builder.nearlyFinishedBeginningThreshold;
        this.nearlyFinishedEndThreshold = builder.nearlyFinishedEndThreshold;
        this.nearlyFinishedInterval = builder.nearlyFinishedInterval;
        this.priorityBehavior = builder.priorityBehavior;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearScheduledRunnables() {
        this.handler.removeCallbacks(this.nearlyFinished);
        this.handler.removeCallbacks(this.reportProgressDelay);
        this.handler.removeCallbacks(this.reportProgressInterval);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rescheduleNearlyFinished() {
        this.handler.removeCallbacks(this.nearlyFinished);
        this.handler.post(this.nearlyFinished);
    }

    @Override // com.amazon.alexa.voice.superbowl.VoicePlugin
    protected void onInitialize() {
        addResponderInSequence(createPlayResponder(), AudioPlayDirective.class);
        addResponderInSequence(createStopResponder(), AudioStopDirective.class);
        addResponderInSequence(createClearQueueResponder(), AudioClearQueueDirective.class);
        addContextResolver(new ContextResolver<Context>() { // from class: com.amazon.music.voice.MusicAudioPlayerPlugin.5
            @Override // com.amazon.alexa.voice.core.processor.superbowl.ContextResolver
            public Context resolve() {
                if (MusicAudioPlayerPlugin.this.audioItem == null) {
                    return null;
                }
                return new AudioPlaybackContext.Builder().offset(Math.max(0L, MusicAudioPlayerPlugin.this.audioPlayer.getCurrentPosition())).token(MusicAudioPlayerPlugin.this.audioItem.getStream().getToken()).playerActivity(MusicAudioPlayerPlugin.this.getPlayerState()).build();
            }
        });
        this.audioPlayer.addCallback(this.audioPlayerCallback);
        Channel channel = this.channel;
        if (channel != null) {
            channel.addListener(this.channelListener);
        }
    }

    private VoiceResponder createPlayResponder() {
        return new VoiceResponder() { // from class: com.amazon.music.voice.MusicAudioPlayerPlugin.6
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(Consumable<Directive> consumable) {
                AudioPlayDirective audioPlayDirective = (AudioPlayDirective) consumable.get();
                AudioPlayDirective.PlayBehavior playBehavior = audioPlayDirective.getPlayBehavior();
                if (playBehavior == AudioPlayDirective.PlayBehavior.ENQUEUE) {
                    MusicAudioPlayerPlugin.this.clearQueue();
                    MusicAudioPlayerPlugin.this.enqueueAudioItem(audioPlayDirective.getAudioItem());
                    if (MusicAudioPlayerPlugin.this.audioItem == null) {
                        MusicAudioPlayerPlugin.this.playNextInQueue();
                    }
                    consumable.accept();
                    return;
                }
                if (playBehavior == AudioPlayDirective.PlayBehavior.REPLACE_ALL) {
                    MusicAudioPlayerPlugin.this.clearQueue();
                    MusicAudioPlayerPlugin.this.stop(false);
                    MusicAudioPlayerPlugin.this.enqueueAudioItem(audioPlayDirective.getAudioItem());
                    MusicAudioPlayerPlugin.this.playNextInQueue();
                    consumable.accept();
                    return;
                }
                if (playBehavior == AudioPlayDirective.PlayBehavior.REPLACE_ENQUEUED) {
                    MusicAudioPlayerPlugin.this.clearQueue();
                    MusicAudioPlayerPlugin.this.enqueueAudioItem(audioPlayDirective.getAudioItem());
                    if (MusicAudioPlayerPlugin.this.audioItem == null) {
                        MusicAudioPlayerPlugin.this.playNextInQueue();
                    }
                    consumable.accept();
                    return;
                }
                consumable.reject(new RejectedExecutionException("Unsupported play behavior"));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearQueue() {
        ArrayList arrayList;
        synchronized (this.queue) {
            arrayList = new ArrayList(this.queue);
            this.queue.clear();
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            notifyAudioItemDropped((AudioPlayDirective.AudioItem) it.next());
        }
    }

    public void cancel() {
        Preconditions.mainThread("cancel() has to be called from the main thread");
        if (this.audioItem == null) {
            return;
        }
        clearQueue();
        stop(true);
        this.audioItem = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enqueueAudioItem(AudioPlayDirective.AudioItem audioItem) {
        synchronized (this.queue) {
            this.queue.add(audioItem);
        }
        notifyAudioItemEnqueued(audioItem);
    }

    private VoiceResponder createClearQueueResponder() {
        return new VoiceResponder() { // from class: com.amazon.music.voice.MusicAudioPlayerPlugin.7
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(Consumable<Directive> consumable) {
                AudioClearQueueDirective.ClearBehavior clearBehavior = ((AudioClearQueueDirective) consumable.get()).getClearBehavior();
                if (clearBehavior == AudioClearQueueDirective.ClearBehavior.CLEAR_ALL) {
                    MusicAudioPlayerPlugin.this.clearQueue();
                    MusicAudioPlayerPlugin.this.stop(true);
                    consumable.accept();
                } else if (clearBehavior == AudioClearQueueDirective.ClearBehavior.CLEAR_ENQUEUED) {
                    MusicAudioPlayerPlugin.this.clearQueue();
                    consumable.accept();
                } else {
                    consumable.reject(new RejectedExecutionException("Unsupported clear behavior"));
                    return;
                }
                Logger.debug("Sending AudioPlayer.PlaybackQueueCleared");
                MusicAudioPlayerPlugin.this.postEvent(new AudioPlaybackQueueClearedEvent.Builder().build());
            }
        };
    }

    private VoiceResponder createStopResponder() {
        return new VoiceResponder() { // from class: com.amazon.music.voice.MusicAudioPlayerPlugin.8
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(Consumable<Directive> consumable) {
                MusicAudioPlayerPlugin.this.clearQueue();
                MusicAudioPlayerPlugin.this.stop(true);
                consumable.accept();
            }
        };
    }

    public void issueNextCommand() {
        AudioPlayDirective.AudioItem audioItemPeek;
        if (!isInitialized()) {
            throw new IllegalStateException("AudioPlayer was not applied to a Voice");
        }
        synchronized (this.queue) {
            audioItemPeek = this.queue.peek();
        }
        if (audioItemPeek != null) {
            stop(false);
            playNextInQueue();
        } else {
            postEvent(new NextCommandIssuedEvent.Builder().addContext(resolveContext()).build());
        }
    }

    public void issuePreviousCommand() {
        if (!isInitialized()) {
            throw new IllegalStateException("AudioPlayer was not applied to a Voice");
        }
        postEvent(new PreviousCommandIssuedEvent.Builder().addContext(resolveContext()).build());
    }

    public void issueShuffleCommand(boolean z) {
        if (!isInitialized()) {
            throw new IllegalStateException("AudioPlayer was not applied to a Voice");
        }
        postEvent(new ShuffleStateEvent.Builder().setState(z).addContext(resolveContext()).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stop(boolean z) {
        Preconditions.mainThread("stop() has to be called from the main thread");
        AudioPlayDirective.AudioItem audioItem = this.audioItem;
        if (audioItem == null || this.wasStopped) {
            return;
        }
        String token = audioItem.getStream().getToken();
        this.wasStopped = true;
        if (z) {
            this.audioPlayer.stop();
        }
        clearScheduledRunnables();
        if (this.sentPlaybackStarted) {
            this.sentPlaybackStarted = false;
            Logger.debug("Sending AudioPlayer.PlaybackStopped");
            postEvent(new AudioPlaybackStoppedEvent.Builder().token(token).offsetInMilliseconds(this.audioPlayer.getCurrentPosition()).build());
        }
        notifyAudioPlaybackEnded(this.audioItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playNextInQueue() {
        AudioPlayDirective.AudioItem audioItemPoll;
        clearScheduledRunnables();
        synchronized (this.queue) {
            audioItemPoll = this.queue.poll();
            this.audioItem = audioItemPoll;
        }
        if (audioItemPoll == null) {
            return;
        }
        notifyAudioItemTaken(audioItemPoll);
        clearScheduledRunnables();
        this.wasStopped = false;
        this.sentPlaybackStarted = false;
        this.sentPaused = false;
        this.sentBufferUnderrun = false;
        this.sentNearlyFinished = false;
        this.awaitingIdle = !this.audioPlayer.isIdle();
        AudioPlayDirective.Stream stream = this.audioItem.getStream();
        if (stream.hasAttachedAudio()) {
            this.audioPlayer.prepare(stream.getAudioSource());
            this.audioPlayer.play();
        } else {
            this.audioPlayer.prepare(stream.getUrl(), stream.getToken());
        }
        this.audioPlayer.seek(stream.getOffsetInMilliseconds());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AudioPlayerState getPlayerState() {
        if (this.audioPlayer.isBuffering()) {
            return AudioPlayerState.BUFFER_UNDERRUN;
        }
        if (this.audioPlayer.isPlaying()) {
            return AudioPlayerState.PLAYING;
        }
        if (this.audioPlayer.isPaused()) {
            return AudioPlayerState.STOPPED;
        }
        if (this.audioPlayer.isEnded()) {
            return AudioPlayerState.FINISHED;
        }
        if (this.audioPlayer.isIdle()) {
            return this.wasStopped ? AudioPlayerState.PAUSED : AudioPlayerState.IDLE;
        }
        return AudioPlayerState.IDLE;
    }

    final class AudioPlayerCallback extends AudioPlayer.SimpleCallback {
        AudioPlayerCallback() {
        }

        @Override // com.amazon.alexa.voice.core.AudioPlayer.SimpleCallback, com.amazon.alexa.voice.core.AudioPlayer.Callback
        public void onPlaybackIdle() {
            MusicAudioPlayerPlugin.this.awaitingIdle = false;
            if (MusicAudioPlayerPlugin.this.channel != null) {
                MusicAudioPlayerPlugin.this.channel.releaseActivation();
            }
        }

        @Override // com.amazon.alexa.voice.core.AudioPlayer.SimpleCallback, com.amazon.alexa.voice.core.AudioPlayer.Callback
        public void onPlaybackReady() {
            if (MusicAudioPlayerPlugin.this.channel != null) {
                MusicAudioPlayerPlugin.this.channel.acquireActivation();
            }
        }

        @Override // com.amazon.alexa.voice.core.AudioPlayer.SimpleCallback, com.amazon.alexa.voice.core.AudioPlayer.Callback
        public void onPlaybackStarted() {
            if (MusicAudioPlayerPlugin.this.audioItem == null || MusicAudioPlayerPlugin.this.awaitingIdle) {
                return;
            }
            AudioPlayDirective.Stream stream = MusicAudioPlayerPlugin.this.audioItem.getStream();
            MusicAudioPlayerPlugin.this.wePaused = false;
            MusicAudioPlayerPlugin.this.wasStopped = false;
            if (!MusicAudioPlayerPlugin.this.sentPlaybackStarted) {
                MusicAudioPlayerPlugin.this.sentPlaybackStarted = true;
                Logger.debug("Sending AudioPlayer.PlaybackStarted");
                MusicAudioPlayerPlugin.this.postEvent(new AudioPlaybackStartedEvent.Builder().token(stream.getToken()).offsetInMilliseconds(MusicAudioPlayerPlugin.this.audioPlayer.getCurrentPosition()).build());
                AudioPlayDirective.ProgressReport progressReport = stream.getProgressReport();
                if (progressReport.getDelayInMilliseconds() > 0) {
                    MusicAudioPlayerPlugin.this.handler.postDelayed(MusicAudioPlayerPlugin.this.reportProgressDelay, progressReport.getDelayInMilliseconds() - stream.getOffsetInMilliseconds());
                }
                if (progressReport.getIntervalInMilliseconds() > 0) {
                    MusicAudioPlayerPlugin.this.handler.postDelayed(MusicAudioPlayerPlugin.this.reportProgressInterval, progressReport.getIntervalInMilliseconds() - stream.getOffsetInMilliseconds());
                }
                MusicAudioPlayerPlugin.this.rescheduleNearlyFinished();
            }
            if (MusicAudioPlayerPlugin.this.sentBufferUnderrun) {
                MusicAudioPlayerPlugin.this.sentBufferUnderrun = false;
                long jCurrentTimeMillis = System.currentTimeMillis() - MusicAudioPlayerPlugin.this.stutterStartedMillis;
                Logger.debug("Sending AudioPlayer.PlaybackStutterFinished");
                MusicAudioPlayerPlugin.this.postEvent(new AudioPlaybackStutterFinishedEvent.Builder().stutterDurationInMilliseconds(jCurrentTimeMillis).token(stream.getToken()).offsetInMilliseconds(MusicAudioPlayerPlugin.this.audioPlayer.getCurrentPosition()).build());
                MusicAudioPlayerPlugin.this.rescheduleNearlyFinished();
            }
            if (MusicAudioPlayerPlugin.this.sentPaused) {
                MusicAudioPlayerPlugin.this.sentPaused = false;
                Logger.debug("Sending AudioPlayer.PlaybackResumed");
                MusicAudioPlayerPlugin.this.postEvent(new AudioPlaybackResumedEvent.Builder().token(stream.getToken()).offsetInMilliseconds(MusicAudioPlayerPlugin.this.audioPlayer.getCurrentPosition()).build());
                MusicAudioPlayerPlugin.this.rescheduleNearlyFinished();
            }
            MusicAudioPlayerPlugin musicAudioPlayerPlugin = MusicAudioPlayerPlugin.this;
            musicAudioPlayerPlugin.notifyAudioPlaybackStarted(musicAudioPlayerPlugin.audioItem);
        }

        @Override // com.amazon.alexa.voice.core.AudioPlayer.SimpleCallback, com.amazon.alexa.voice.core.AudioPlayer.Callback
        public void onPlaybackEnded() {
            if (!MusicAudioPlayerPlugin.this.sentPlaybackStarted || MusicAudioPlayerPlugin.this.audioItem == null || MusicAudioPlayerPlugin.this.awaitingIdle) {
                return;
            }
            AudioPlayDirective.AudioItem audioItem = MusicAudioPlayerPlugin.this.audioItem;
            MusicAudioPlayerPlugin.this.audioItem = null;
            MusicAudioPlayerPlugin.this.sentPlaybackStarted = false;
            MusicAudioPlayerPlugin.this.clearScheduledRunnables();
            if (!MusicAudioPlayerPlugin.this.sentNearlyFinished) {
                Logger.debug("Sending AudioPlayer.PlaybackNearlyFinished");
                MusicAudioPlayerPlugin.this.postEvent(new AudioPlaybackNearlyFinishedEvent.Builder().token(audioItem.getStream().getToken()).offsetInMilliseconds(MusicAudioPlayerPlugin.this.audioPlayer.getCurrentPosition()).build());
            }
            Logger.debug("Sending AudioPlayer.PlaybackFinished");
            MusicAudioPlayerPlugin.this.postEvent(new AudioPlaybackFinishedEvent.Builder().token(audioItem.getStream().getToken()).offsetInMilliseconds(MusicAudioPlayerPlugin.this.audioPlayer.getCurrentPosition()).build());
            MusicAudioPlayerPlugin.this.notifyAudioPlaybackEnded(audioItem);
            MusicAudioPlayerPlugin.this.playNextInQueue();
        }

        @Override // com.amazon.alexa.voice.core.AudioPlayer.SimpleCallback, com.amazon.alexa.voice.core.AudioPlayer.Callback
        public void onPlaybackPaused() {
            if (!MusicAudioPlayerPlugin.this.sentPlaybackStarted || MusicAudioPlayerPlugin.this.audioItem == null || MusicAudioPlayerPlugin.this.awaitingIdle) {
                return;
            }
            if (MusicAudioPlayerPlugin.this.wePaused) {
                if (MusicAudioPlayerPlugin.this.sentPaused) {
                    return;
                }
                MusicAudioPlayerPlugin.this.sentPaused = true;
                Logger.debug("Paused by us due to channel change");
                Logger.debug("Sending AudioPlayer.PlaybackPaused");
                MusicAudioPlayerPlugin.this.postEvent(new AudioPlaybackPausedEvent.Builder().token(MusicAudioPlayerPlugin.this.audioItem.getStream().getToken()).offsetInMilliseconds(MusicAudioPlayerPlugin.this.audioPlayer.getCurrentPosition()).build());
            } else {
                Logger.debug("Paused by user or external event");
                Logger.debug("Sending AudioPlayer.PlaybackStopped");
                MusicAudioPlayerPlugin.this.sentPlaybackStarted = false;
                MusicAudioPlayerPlugin.this.postEvent(new AudioPlaybackStoppedEvent.Builder().token(MusicAudioPlayerPlugin.this.audioItem.getStream().getToken()).offsetInMilliseconds(MusicAudioPlayerPlugin.this.audioPlayer.getCurrentPosition()).build());
            }
            MusicAudioPlayerPlugin musicAudioPlayerPlugin = MusicAudioPlayerPlugin.this;
            musicAudioPlayerPlugin.notifyAudioPlaybackPaused(musicAudioPlayerPlugin.audioItem);
        }

        @Override // com.amazon.alexa.voice.core.AudioPlayer.SimpleCallback, com.amazon.alexa.voice.core.AudioPlayer.Callback
        public void onPlaybackError(Throwable th) {
            if (MusicAudioPlayerPlugin.this.audioItem == null || MusicAudioPlayerPlugin.this.awaitingIdle) {
                return;
            }
            AudioPlayDirective.AudioItem audioItem = MusicAudioPlayerPlugin.this.audioItem;
            MusicAudioPlayerPlugin.this.audioItem = null;
            MusicAudioPlayerPlugin.this.sentPlaybackStarted = false;
            MusicAudioPlayerPlugin.this.clearScheduledRunnables();
            Logger.debug("Sending AudioPlayer.PlaybackFailed");
            MusicAudioPlayerPlugin.this.postEvent(new AudioPlaybackFailedEvent.Builder().error(new AudioPlaybackFailedEvent.Error(AudioPlaybackFailedEvent.Error.Type.MEDIA_INTERNAL_DEVICE_ERROR, th.getMessage())).playbackState(new AudioPlaybackState(MusicAudioPlayerPlugin.this.getPlayerState(), MusicAudioPlayerPlugin.this.audioPlayer.getCurrentPosition(), audioItem.getStream().getToken())).token(audioItem.getStream().getToken()).build());
            MusicAudioPlayerPlugin.this.notifyAudioPlaybackFailed(audioItem, th);
            MusicAudioPlayerPlugin.this.playNextInQueue();
        }

        @Override // com.amazon.alexa.voice.core.AudioPlayer.SimpleCallback, com.amazon.alexa.voice.core.AudioPlayer.Callback
        public void onPlayerBuffering() {
            if (MusicAudioPlayerPlugin.this.audioItem == null || !MusicAudioPlayerPlugin.this.sentPlaybackStarted || MusicAudioPlayerPlugin.this.sentBufferUnderrun || MusicAudioPlayerPlugin.this.awaitingIdle) {
                return;
            }
            MusicAudioPlayerPlugin.this.sentBufferUnderrun = true;
            MusicAudioPlayerPlugin.this.stutterStartedMillis = System.currentTimeMillis();
            Logger.debug("Sending AudioPlayer.PlaybackStutterStarted");
            MusicAudioPlayerPlugin.this.postEvent(new AudioPlaybackStutterStartedEvent.Builder().token(MusicAudioPlayerPlugin.this.audioItem.getStream().getToken()).offsetInMilliseconds(MusicAudioPlayerPlugin.this.audioPlayer.getCurrentPosition()).build());
        }
    }

    public void addAudioPlayerListener(AudioPlayerListener audioPlayerListener) {
        Preconditions.mainThread("addAudioPlayerListener() has to be called from the main thread");
        Preconditions.notNull(audioPlayerListener, "listener == null");
        this.audioPlayerListeners.add(audioPlayerListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAudioPlaybackStarted(AudioPlayDirective.AudioItem audioItem) {
        for (int size = this.audioPlayerListeners.size() - 1; size >= 0; size--) {
            this.audioPlayerListeners.get(size).onAudioPlaybackStarted(audioItem);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAudioPlaybackPaused(AudioPlayDirective.AudioItem audioItem) {
        for (int size = this.audioPlayerListeners.size() - 1; size >= 0; size--) {
            this.audioPlayerListeners.get(size).onAudioPlaybackPaused(audioItem);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAudioPlaybackEnded(AudioPlayDirective.AudioItem audioItem) {
        for (int size = this.audioPlayerListeners.size() - 1; size >= 0; size--) {
            this.audioPlayerListeners.get(size).onAudioPlaybackEnded(audioItem);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAudioPlaybackFailed(AudioPlayDirective.AudioItem audioItem, Throwable th) {
        for (int size = this.audioPlayerListeners.size() - 1; size >= 0; size--) {
            this.audioPlayerListeners.get(size).onAudioPlaybackFailed(audioItem, th);
        }
    }

    private void notifyAudioItemEnqueued(AudioPlayDirective.AudioItem audioItem) {
        for (int size = this.audioPlayerListeners.size() - 1; size >= 0; size--) {
            this.audioPlayerListeners.get(size).onAudioItemEnqueued(audioItem);
        }
    }

    private void notifyAudioItemDropped(AudioPlayDirective.AudioItem audioItem) {
        for (int size = this.audioPlayerListeners.size() - 1; size >= 0; size--) {
            this.audioPlayerListeners.get(size).onAudioItemDropped(audioItem);
        }
    }

    private void notifyAudioItemTaken(AudioPlayDirective.AudioItem audioItem) {
        for (int size = this.audioPlayerListeners.size() - 1; size >= 0; size--) {
            this.audioPlayerListeners.get(size).onAudioItemTaken(audioItem);
        }
    }

    public static final class Builder {
        AudioPlayerProxy audioPlayer;
        Channel channel;
        android.content.Context context;
        long nearlyFinishedBeginningThreshold;
        long nearlyFinishedEndThreshold;
        long nearlyFinishedInterval;
        PriorityBehavior priorityBehavior;

        public Builder(android.content.Context context) {
            Preconditions.notNull(context, "context == null");
            this.context = context;
            this.priorityBehavior = PriorityBehavior.DUCK;
            this.nearlyFinishedBeginningThreshold = 500L;
            this.nearlyFinishedEndThreshold = 1000L;
            this.nearlyFinishedInterval = 500L;
        }

        public Builder configureNearlyFinishedInterval(long j, long j2, long j3) {
            if (j3 <= 0) {
                throw new IllegalArgumentException("Interval cannot be zero or negative");
            }
            this.nearlyFinishedBeginningThreshold = j;
            this.nearlyFinishedEndThreshold = j2;
            this.nearlyFinishedInterval = j3;
            return this;
        }

        public Builder channel(Channel channel) {
            this.channel = channel;
            return this;
        }

        public Builder audioPlayer(AudioPlayerProxy audioPlayerProxy) {
            this.audioPlayer = audioPlayerProxy;
            return this;
        }

        public MusicAudioPlayerPlugin build() {
            Preconditions.mainThread("build() has to be called from the main thread");
            Preconditions.notNull(this.audioPlayer, "audioPlayer == null");
            return new MusicAudioPlayerPlugin(this);
        }
    }
}
