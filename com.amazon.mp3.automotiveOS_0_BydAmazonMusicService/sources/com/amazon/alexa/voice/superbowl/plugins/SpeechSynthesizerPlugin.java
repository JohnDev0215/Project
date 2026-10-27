package com.amazon.alexa.voice.superbowl.plugins;

import com.amazon.alexa.voice.core.Action;
import com.amazon.alexa.voice.core.ActionValue;
import com.amazon.alexa.voice.core.AudioSource;
import com.amazon.alexa.voice.core.Cancellable;
import com.amazon.alexa.voice.core.Consumable;
import com.amazon.alexa.voice.core.Consumer;
import com.amazon.alexa.voice.core.Directive;
import com.amazon.alexa.voice.core.Executable;
import com.amazon.alexa.voice.core.Function;
import com.amazon.alexa.voice.core.SimpleCancellable;
import com.amazon.alexa.voice.core.Task;
import com.amazon.alexa.voice.core.VoiceResponder;
import com.amazon.alexa.voice.core.audio.AudioPipeline;
import com.amazon.alexa.voice.core.audio.EncodedAudioSpeaker;
import com.amazon.alexa.voice.core.channel.Channel;
import com.amazon.alexa.voice.core.internal.AudioFocusManager;
import com.amazon.alexa.voice.core.internal.audio.MP3;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.processor.superbowl.Content;
import com.amazon.alexa.voice.core.processor.superbowl.Context;
import com.amazon.alexa.voice.core.processor.superbowl.ContextResolver;
import com.amazon.alexa.voice.core.processor.superbowl.context.SynthesizeSpeechContext;
import com.amazon.alexa.voice.core.processor.superbowl.directives.SynthesizeSpeechDirective;
import com.amazon.alexa.voice.core.processor.superbowl.events.SpeechFinishedEvent;
import com.amazon.alexa.voice.core.processor.superbowl.events.SpeechStartedEvent;
import com.amazon.alexa.voice.core.responders.ChannelResponder;
import com.amazon.alexa.voice.core.responders.ComposableResponder;
import com.amazon.alexa.voice.core.schedulers.Schedulers;
import com.amazon.alexa.voice.superbowl.VoicePlugin;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes2.dex */
public final class SpeechSynthesizerPlugin extends VoicePlugin {
    private final AudioFocusManager audioFocusManager;
    private final Channel channel;
    private volatile boolean muted;
    private volatile boolean playing;
    private volatile long speechStartedAt;
    private Cancellable task;
    private volatile String token;
    private final List<SynthesizeSpeechListener> synthesizeSpeechListeners = new ArrayList();
    private final Channel.Listener channelListener = createChannelListener();

    public static class SimpleSynthesizeSpeechListener implements SynthesizeSpeechListener {
        @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.SynthesizeSpeechListener
        public void onSynthesizeSpeechCancelled(String str) {
        }

        @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.SynthesizeSpeechListener
        public void onSynthesizeSpeechCompleted(String str) {
        }

        @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.SynthesizeSpeechListener
        public void onSynthesizeSpeechFailed(String str, Throwable th) {
        }

        @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.SynthesizeSpeechListener
        public void onSynthesizeSpeechStarted(String str) {
        }
    }

    public interface SynthesizeSpeechListener {
        void onSynthesizeSpeechCancelled(String str);

        void onSynthesizeSpeechCompleted(String str);

        void onSynthesizeSpeechFailed(String str, Throwable th);

        void onSynthesizeSpeechStarted(String str);
    }

    SpeechSynthesizerPlugin(Builder builder) {
        this.channel = builder.channel;
        this.audioFocusManager = new AudioFocusManager(builder.context, 3);
    }

    @Override // com.amazon.alexa.voice.superbowl.VoicePlugin
    protected void onInitialize() {
        addResponderInSequence(createResponder(), SynthesizeSpeechDirective.class);
        addContextResolver(new ContextResolver<Context>() { // from class: com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.1
            @Override // com.amazon.alexa.voice.core.processor.superbowl.ContextResolver
            public Context resolve() {
                String str;
                String str2 = "FINISHED";
                if (SpeechSynthesizerPlugin.this.token != null) {
                    str = SpeechSynthesizerPlugin.this.token;
                    jCurrentTimeMillis = SpeechSynthesizerPlugin.this.playing ? System.currentTimeMillis() - SpeechSynthesizerPlugin.this.speechStartedAt : 0L;
                    if (SpeechSynthesizerPlugin.this.playing) {
                        str2 = "PLAYING";
                    }
                } else {
                    str = "";
                }
                return new SynthesizeSpeechContext.Builder().contentToken(str).offsetInMilliseconds(jCurrentTimeMillis).activity(str2).build();
            }
        });
        Channel channel = this.channel;
        if (channel != null) {
            channel.addListener(this.channelListener);
        }
    }

    private Channel.Listener createChannelListener() {
        return new Channel.SimpleListener() { // from class: com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.2
            private Object audioFocus;

            @Override // com.amazon.alexa.voice.core.channel.Channel.SimpleListener, com.amazon.alexa.voice.core.channel.Channel.Listener
            public void onBroughtToForeground(Channel channel) {
                if (this.audioFocus != null) {
                    return;
                }
                this.audioFocus = SpeechSynthesizerPlugin.this.audioFocusManager.acquire();
            }

            @Override // com.amazon.alexa.voice.core.channel.Channel.SimpleListener, com.amazon.alexa.voice.core.channel.Channel.Listener
            public void onBroughtToBackground(Channel channel) {
                if (this.audioFocus == null) {
                    return;
                }
                SpeechSynthesizerPlugin.this.audioFocusManager.release(this.audioFocus);
                this.audioFocus = null;
            }
        };
    }

    private VoiceResponder createResponder() {
        return new ComposableResponder() { // from class: com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.4
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(final Consumable<Directive> consumable) {
                Preconditions.mainThread("SpeechSynthesizer should respondToDirectives on a main thread!");
                if (SpeechSynthesizerPlugin.this.channel == null || !SpeechSynthesizerPlugin.this.channel.isInBackground()) {
                    if (SpeechSynthesizerPlugin.this.task != null && !SpeechSynthesizerPlugin.this.task.isCancelled()) {
                        SpeechSynthesizerPlugin.this.task.cancel();
                    }
                    final SynthesizeSpeechDirective synthesizeSpeechDirective = (SynthesizeSpeechDirective) consumable.get();
                    SpeechSynthesizerPlugin.this.token = synthesizeSpeechDirective.getToken();
                    if (SpeechSynthesizerPlugin.this.muted) {
                        consumable.accept();
                        return;
                    }
                    SpeechSynthesizerPlugin.this.speechStartedAt = System.currentTimeMillis();
                    SpeechSynthesizerPlugin.this.playing = true;
                    SpeechSynthesizerPlugin.this.notifySynthesizeSpeechStarted(synthesizeSpeechDirective.getToken());
                    SpeechSynthesizerPlugin.this.task = new Executable(new ActionValue<Task<Void>>() { // from class: com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.4.4
                        @Override // com.amazon.alexa.voice.core.ActionValue
                        public void call(Task<Void> task) throws Exception {
                            Cancellable cancellableTranscodeToSpeaker;
                            Content content = synthesizeSpeechDirective.getContent();
                            SpeechSynthesizerPlugin.this.postEvent(new SpeechStartedEvent.Builder().token(synthesizeSpeechDirective.getToken()).build());
                            long durationMillis = MP3.getDurationMillis(content.data(), content.offset(), content.size());
                            if (durationMillis == -1 || durationMillis >= 100) {
                                cancellableTranscodeToSpeaker = SpeechSynthesizerPlugin.this.transcodeToSpeaker(content.audioSource(), task);
                            } else {
                                cancellableTranscodeToSpeaker = new SimpleCancellable();
                                task.add(cancellableTranscodeToSpeaker);
                                Thread.sleep(durationMillis);
                            }
                            if (cancellableTranscodeToSpeaker.isCancelled()) {
                                return;
                            }
                            SpeechSynthesizerPlugin.this.postEvent(new SpeechFinishedEvent.Builder().token(synthesizeSpeechDirective.getToken()).build());
                        }
                    }).executeOn(Schedulers.io()).consumeOn(Schedulers.main()).onCancel(new Action() { // from class: com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.4.3
                        @Override // com.amazon.alexa.voice.core.Action
                        public void call() throws Exception {
                            SpeechSynthesizerPlugin.this.playing = false;
                            if (SpeechSynthesizerPlugin.this.muted) {
                                consumable.accept();
                                SpeechSynthesizerPlugin.this.notifySynthesizeSpeechCompleted(synthesizeSpeechDirective.getToken());
                            } else {
                                consumable.reject(new CancellationException("Synthesize speech was cancelled"));
                                SpeechSynthesizerPlugin.this.notifySynthesizeSpeechCancelled(synthesizeSpeechDirective.getToken());
                            }
                        }
                    }).execute(new Consumer<Void>() { // from class: com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.4.1
                        @Override // com.amazon.alexa.voice.core.Consumer
                        public void accept(Void r2) throws Exception {
                            SpeechSynthesizerPlugin.this.playing = false;
                            consumable.accept();
                            SpeechSynthesizerPlugin.this.notifySynthesizeSpeechCompleted(synthesizeSpeechDirective.getToken());
                        }
                    }, new Consumer<Throwable>() { // from class: com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.4.2
                        @Override // com.amazon.alexa.voice.core.Consumer
                        public void accept(Throwable th) throws Exception {
                            SpeechSynthesizerPlugin.this.playing = false;
                            consumable.reject(th);
                            SpeechSynthesizerPlugin.this.notifySynthesizeSpeechFailed(synthesizeSpeechDirective.getToken(), th);
                        }
                    });
                    return;
                }
                consumable.reject(new RejectedExecutionException("Dialog channel is in background"));
            }
        }.compose(new Function<VoiceResponder, VoiceResponder>() { // from class: com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.3
            @Override // com.amazon.alexa.voice.core.Function
            public VoiceResponder apply(VoiceResponder voiceResponder) throws Exception {
                return SpeechSynthesizerPlugin.this.channel == null ? voiceResponder : new ChannelResponder(voiceResponder, SpeechSynthesizerPlugin.this.channel);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Cancellable transcodeToSpeaker(AudioSource audioSource, Task<Void> task) throws IOException {
        EncodedAudioSpeaker encodedAudioSpeaker = new EncodedAudioSpeaker();
        AudioPipeline audioPipeline = new AudioPipeline(audioSource, encodedAudioSpeaker);
        task.add(encodedAudioSpeaker);
        task.add(audioPipeline);
        audioPipeline.transcode();
        return audioPipeline;
    }

    public void cancel() {
        Preconditions.mainThread("cancel() has to be called from the main thread");
        Cancellable cancellable = this.task;
        if (cancellable == null || cancellable.isCancelled()) {
            return;
        }
        this.task.cancel();
    }

    public void addListener(SynthesizeSpeechListener synthesizeSpeechListener) {
        Preconditions.mainThread("addListener() has to be called from the main thread");
        Preconditions.notNull(synthesizeSpeechListener, "listener == null");
        this.synthesizeSpeechListeners.add(synthesizeSpeechListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifySynthesizeSpeechStarted(String str) {
        for (int size = this.synthesizeSpeechListeners.size() - 1; size >= 0; size--) {
            this.synthesizeSpeechListeners.get(size).onSynthesizeSpeechStarted(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifySynthesizeSpeechCompleted(String str) {
        for (int size = this.synthesizeSpeechListeners.size() - 1; size >= 0; size--) {
            this.synthesizeSpeechListeners.get(size).onSynthesizeSpeechCompleted(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifySynthesizeSpeechCancelled(String str) {
        for (int size = this.synthesizeSpeechListeners.size() - 1; size >= 0; size--) {
            this.synthesizeSpeechListeners.get(size).onSynthesizeSpeechCancelled(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifySynthesizeSpeechFailed(String str, Throwable th) {
        for (int size = this.synthesizeSpeechListeners.size() - 1; size >= 0; size--) {
            this.synthesizeSpeechListeners.get(size).onSynthesizeSpeechFailed(str, th);
        }
    }

    public static final class Builder {
        Channel channel;
        android.content.Context context;

        @Deprecated
        public Builder audioCacheDirectory(File file) {
            return this;
        }

        public Builder(android.content.Context context) {
            this.context = context;
        }

        public Builder channel(Channel channel) {
            this.channel = channel;
            return this;
        }

        public SpeechSynthesizerPlugin build() {
            Preconditions.mainThread("build() has to be called from the main thread");
            Preconditions.notNull(this.context, "context == null");
            return new SpeechSynthesizerPlugin(this);
        }
    }
}
