package com.amazon.alexa.voice.superbowl.plugins;

import android.content.Context;
import android.os.Handler;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.alexa.voice.core.Action;
import com.amazon.alexa.voice.core.AudioFormat;
import com.amazon.alexa.voice.core.AudioSink;
import com.amazon.alexa.voice.core.AudioTranscoder;
import com.amazon.alexa.voice.core.Cancellable;
import com.amazon.alexa.voice.core.Consumable;
import com.amazon.alexa.voice.core.Consumables;
import com.amazon.alexa.voice.core.Consumer;
import com.amazon.alexa.voice.core.Directive;
import com.amazon.alexa.voice.core.Executable;
import com.amazon.alexa.voice.core.Function;
import com.amazon.alexa.voice.core.Logger;
import com.amazon.alexa.voice.core.SimpleCancellable;
import com.amazon.alexa.voice.core.Task;
import com.amazon.alexa.voice.core.VoiceProcessorException;
import com.amazon.alexa.voice.core.VoiceResponder;
import com.amazon.alexa.voice.core.audio.AudioPipe;
import com.amazon.alexa.voice.core.channel.Channel;
import com.amazon.alexa.voice.core.internal.AudioFocusManager;
import com.amazon.alexa.voice.core.internal.audio.AudioSinkDelegate;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.processor.superbowl.directives.ExpectSpeechDirective;
import com.amazon.alexa.voice.core.processor.superbowl.events.ExpectSpeechTimedOutEvent;
import com.amazon.alexa.voice.core.processor.superbowl.events.RecognizeSpeechEvent;
import com.amazon.alexa.voice.core.schedulers.Schedulers;
import com.amazon.alexa.voice.superbowl.VoicePlugin;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* JADX INFO: loaded from: classes2.dex */
public final class SpeechRecognizerPlugin extends VoicePlugin {
    private final AudioFocusManager audioFocusManager;
    private final Function<AudioSink, AudioTranscoder> audioTranscoder;
    private final Channel channel;
    private Runnable expectSpeechTimedOutRunnable;
    private final String profile;
    private Cancellable task;
    private final List<RecognizeSpeechListener> recognizeSpeechListeners = new ArrayList();
    private final Handler handler = new Handler();
    private boolean listening = false;
    private final Channel.Listener channelListener = createChannelListener();

    public interface OnPrepareListener {
        void proceed();
    }

    public interface RecognizeSpeechListener {
        void onRecognizeSpeechCancelled();

        void onRecognizeSpeechCompleted();

        void onRecognizeSpeechFailed(Throwable th);

        void onRecognizeSpeechPrepare(OnPrepareListener onPrepareListener);

        void onRecognizeSpeechRecordingCompleted();

        void onRecognizeSpeechStarted(boolean z);
    }

    SpeechRecognizerPlugin(Builder builder) {
        this.audioTranscoder = builder.audioTranscoder;
        this.channel = builder.channel;
        this.profile = builder.profile;
        this.audioFocusManager = new AudioFocusManager(builder.context, 2);
    }

    @Override // com.amazon.alexa.voice.superbowl.VoicePlugin
    protected void onInitialize() {
        addResponderInSequence(createResponder(), ExpectSpeechDirective.class);
        Channel channel = this.channel;
        if (channel != null) {
            channel.addListener(this.channelListener);
        }
    }

    private Channel.Listener createChannelListener() {
        return new Channel.SimpleListener() { // from class: com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.1
            private Object audioFocus;

            @Override // com.amazon.alexa.voice.core.channel.Channel.SimpleListener, com.amazon.alexa.voice.core.channel.Channel.Listener
            public void onBroughtToForeground(Channel channel) {
                if (this.audioFocus != null) {
                    return;
                }
                this.audioFocus = SpeechRecognizerPlugin.this.audioFocusManager.acquire();
            }

            @Override // com.amazon.alexa.voice.core.channel.Channel.SimpleListener, com.amazon.alexa.voice.core.channel.Channel.Listener
            public void onBroughtToBackground(Channel channel) {
                if (this.audioFocus == null) {
                    return;
                }
                SpeechRecognizerPlugin.this.audioFocusManager.release(this.audioFocus);
                this.audioFocus = null;
            }
        };
    }

    private VoiceResponder createResponder() {
        return new VoiceResponder() { // from class: com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.2
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(Consumable<Directive> consumable) {
                Preconditions.mainThread("SpeechRecognizer should respondToDirectives on a main thread!");
                ExpectSpeechDirective expectSpeechDirective = (ExpectSpeechDirective) consumable.get();
                String dialogRequestId = expectSpeechDirective.getDialogRequestId();
                if (dialogRequestId == null) {
                    Logger.debug("Dialog id is null! Generating a new dialog id on demand");
                    dialogRequestId = SpeechRecognizerPlugin.this.getDialogRequest().newRequestId();
                }
                SpeechRecognizerPlugin.this.recognizeSpeech(dialogRequestId, expectSpeechDirective.getTimeoutInMilliseconds(), true, consumable);
            }
        };
    }

    public void recognizeSpeech(long j) {
        if (!isInitialized()) {
            throw new IllegalStateException("SpeechRecognizer was not applied to a Voice");
        }
        recognizeSpeech(getDialogRequest().newRequestId(), j, false, Consumables.empty());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void cancelExpectSpeechTimeout() {
        Runnable runnable = this.expectSpeechTimedOutRunnable;
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
            this.expectSpeechTimedOutRunnable = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recognizeSpeech(String str, long j, boolean z, Consumable<?> consumable) {
        Preconditions.mainThread("recognizeSpeech() has to be called from the main thread");
        cancelExpectSpeechTimeout();
        Cancellable cancellable = this.task;
        if (cancellable != null && !cancellable.isCancelled()) {
            this.task.cancel();
        }
        this.listening = true;
        notifyRecognizeSpeechStarted(z);
        notifyPrepareRecognizeSpeech(new RecognizeSpeechTask(str, j, z, consumable));
    }

    public boolean isListening() {
        return this.listening;
    }

    public void cancel() {
        Preconditions.mainThread("cancel() has to be called from the main thread");
        cancelExpectSpeechTimeout();
        Cancellable cancellable = this.task;
        if (cancellable == null || cancellable.isCancelled()) {
            return;
        }
        this.task.cancel();
    }

    public void addListener(RecognizeSpeechListener recognizeSpeechListener) {
        Preconditions.mainThread("addListener() has to be called from the main thread");
        Preconditions.notNull(recognizeSpeechListener, "listener == null");
        this.recognizeSpeechListeners.add(recognizeSpeechListener);
    }

    private void notifyRecognizeSpeechStarted(boolean z) {
        for (int size = this.recognizeSpeechListeners.size() - 1; size >= 0; size--) {
            this.recognizeSpeechListeners.get(size).onRecognizeSpeechStarted(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyRecognizeSpeechCompleted() {
        for (int size = this.recognizeSpeechListeners.size() - 1; size >= 0; size--) {
            this.recognizeSpeechListeners.get(size).onRecognizeSpeechCompleted();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyRecognizeSpeechRecordingCompleted() {
        for (int size = this.recognizeSpeechListeners.size() - 1; size >= 0; size--) {
            this.recognizeSpeechListeners.get(size).onRecognizeSpeechRecordingCompleted();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyRecognizeSpeechCancelled() {
        for (int size = this.recognizeSpeechListeners.size() - 1; size >= 0; size--) {
            this.recognizeSpeechListeners.get(size).onRecognizeSpeechCancelled();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyRecognizeSpeechFailed(Throwable th) {
        for (int size = this.recognizeSpeechListeners.size() - 1; size >= 0; size--) {
            this.recognizeSpeechListeners.get(size).onRecognizeSpeechFailed(th);
        }
    }

    private void notifyPrepareRecognizeSpeech(OnPrepareListener onPrepareListener) {
        int size = this.recognizeSpeechListeners.size();
        if (size == 0) {
            onPrepareListener.proceed();
            return;
        }
        AggregateOnPrepareListener aggregateOnPrepareListener = new AggregateOnPrepareListener(size, onPrepareListener);
        for (int i = size - 1; i >= 0; i--) {
            this.recognizeSpeechListeners.get(i).onRecognizeSpeechPrepare(aggregateOnPrepareListener);
        }
    }

    static class AggregateOnPrepareListener implements OnPrepareListener {
        private int count;
        private final OnPrepareListener listener;
        private final int numListeners;

        public AggregateOnPrepareListener(int i, OnPrepareListener onPrepareListener) {
            this.numListeners = i;
            this.listener = onPrepareListener;
        }

        @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.OnPrepareListener
        public void proceed() {
            int i = this.count + 1;
            this.count = i;
            if (i == this.numListeners) {
                this.listener.proceed();
            }
        }
    }

    public static class SimpleRecognizeSpeechListener implements RecognizeSpeechListener {
        @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechListener
        public void onRecognizeSpeechCancelled() {
        }

        @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechListener
        public void onRecognizeSpeechCompleted() {
        }

        @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechListener
        public void onRecognizeSpeechFailed(Throwable th) {
        }

        @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechListener
        public void onRecognizeSpeechRecordingCompleted() {
        }

        @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechListener
        public void onRecognizeSpeechStarted(boolean z) {
        }

        @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechListener
        public void onRecognizeSpeechPrepare(OnPrepareListener onPrepareListener) {
            onPrepareListener.proceed();
        }
    }

    final class RecognizeSpeechTask implements OnPrepareListener {
        private final Consumable<?> consumable;
        private final String dialogId;
        private final boolean inResponse;
        private final long timeoutMillis;

        public RecognizeSpeechTask(String str, long j, boolean z, Consumable<?> consumable) {
            this.dialogId = str;
            this.timeoutMillis = j;
            this.inResponse = z;
            this.consumable = consumable;
        }

        @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.OnPrepareListener
        public void proceed() {
            if (this.timeoutMillis > 0) {
                SpeechRecognizerPlugin.this.expectSpeechTimedOutRunnable = new Runnable() { // from class: com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechTask.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (SpeechRecognizerPlugin.this.task == null || SpeechRecognizerPlugin.this.task.isCancelled()) {
                            return;
                        }
                        Logger.verbose("Expect speech timeout occurred");
                        SpeechRecognizerPlugin.this.task.cancel();
                        if (RecognizeSpeechTask.this.inResponse) {
                            SpeechRecognizerPlugin.this.postEvent(new ExpectSpeechTimedOutEvent.Builder().dialogRequestId(RecognizeSpeechTask.this.dialogId).build());
                        }
                    }
                };
                SpeechRecognizerPlugin.this.handler.postDelayed(SpeechRecognizerPlugin.this.expectSpeechTimedOutRunnable, this.timeoutMillis);
            }
            SpeechRecognizerPlugin.this.task = new Executable(new Function<Task<Directive[]>, Directive[]>() { // from class: com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechTask.5
                @Override // com.amazon.alexa.voice.core.Function
                public Directive[] apply(Task<Directive[]> task) throws Exception {
                    final Collection collectionResolveContext = SpeechRecognizerPlugin.this.resolveContext();
                    SpeechRecognizerPlugin.this.handler.post(new Runnable() { // from class: com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechTask.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (SpeechRecognizerPlugin.this.channel != null) {
                                SpeechRecognizerPlugin.this.channel.acquireActivation();
                            }
                        }
                    });
                    final AudioPipe audioPipe = new AudioPipe(2097152, RecognizeSpeechTask.this.timeoutMillis > 0 ? RecognizeSpeechTask.this.timeoutMillis : CoroutineLiveDataKt.DEFAULT_TIMEOUT);
                    final AudioTranscoder audioTranscoder = (AudioTranscoder) SpeechRecognizerPlugin.this.audioTranscoder.apply(new AudioSinkDelegate(audioPipe) { // from class: com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechTask.5.2
                        @Override // com.amazon.alexa.voice.core.internal.audio.AudioSinkDelegate
                        protected void onWrite(byte[] bArr, int i, int i2) {
                            SpeechRecognizerPlugin.this.cancelExpectSpeechTimeout();
                        }
                    });
                    task.add(audioTranscoder);
                    final AudioFormat audioFormat = audioTranscoder.getAudioFormat();
                    final FutureTask futureTask = new FutureTask(new Callable<Directive[]>() { // from class: com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechTask.5.3
                        @Override // java.util.concurrent.Callable
                        public Directive[] call() throws Exception {
                            try {
                                if (SpeechRecognizerPlugin.this.getDownchannel() != null) {
                                    SpeechRecognizerPlugin.this.getDownchannel().activate();
                                }
                                return SpeechRecognizerPlugin.this.getProcessor().execute(new RecognizeSpeechEvent.Builder().profile(SpeechRecognizerPlugin.this.profile).addContext(collectionResolveContext).audio(audioPipe, audioFormat).dialogRequestId(RecognizeSpeechTask.this.dialogId).build());
                            } catch (Exception e) {
                                audioTranscoder.cancel();
                                if (e instanceof IOException) {
                                    throw new VoiceProcessorException((IOException) e);
                                }
                                throw e;
                            }
                        }
                    });
                    task.add(new SimpleCancellable() { // from class: com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechTask.5.4
                        @Override // com.amazon.alexa.voice.core.SimpleCancellable
                        protected void onCancelled() {
                            futureTask.cancel(true);
                        }
                    });
                    SpeechRecognizerPlugin.this.getEventScheduler().schedule(futureTask);
                    try {
                        audioTranscoder.transcode();
                        if (!audioTranscoder.isCancelled()) {
                            Schedulers.main().schedule(new Runnable() { // from class: com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechTask.5.5
                                @Override // java.lang.Runnable
                                public void run() {
                                    SpeechRecognizerPlugin.this.notifyRecognizeSpeechRecordingCompleted();
                                }
                            });
                        }
                        return (Directive[]) futureTask.get();
                    } catch (IOException e) {
                        Logger.error("Audio transcoding completed with error", e);
                        throw new SpeechRecognizerException(new VoiceProcessorException(e));
                    }
                }
            }).executeOn(Schedulers.io()).consumeOn(Schedulers.main()).onCancel(new Action() { // from class: com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechTask.4
                @Override // com.amazon.alexa.voice.core.Action
                public void call() throws Exception {
                    RecognizeSpeechTask.this.consumable.reject(new CancellationException("Speech recognition was cancelled"));
                    if (SpeechRecognizerPlugin.this.channel != null) {
                        SpeechRecognizerPlugin.this.channel.releaseActivation();
                    }
                    SpeechRecognizerPlugin.this.listening = false;
                    SpeechRecognizerPlugin.this.notifyRecognizeSpeechCancelled();
                }
            }).execute(new Consumer<Directive[]>() { // from class: com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechTask.2
                @Override // com.amazon.alexa.voice.core.Consumer
                public void accept(Directive[] directiveArr) throws Exception {
                    RecognizeSpeechTask.this.consumable.accept();
                    SpeechRecognizerPlugin.this.listening = false;
                    SpeechRecognizerPlugin.this.notifyRecognizeSpeechCompleted();
                    SpeechRecognizerPlugin.this.respondToDirectives(directiveArr);
                    if (SpeechRecognizerPlugin.this.channel != null) {
                        SpeechRecognizerPlugin.this.channel.releaseActivation();
                    }
                }
            }, new Consumer<Throwable>() { // from class: com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechTask.3
                @Override // com.amazon.alexa.voice.core.Consumer
                public void accept(Throwable th) throws Exception {
                    if (th instanceof ExecutionException) {
                        th = th.getCause();
                    }
                    if (th instanceof VoiceProcessorException) {
                        SpeechRecognizerException speechRecognizerException = new SpeechRecognizerException((VoiceProcessorException) th);
                        SpeechRecognizerPlugin.this.handleException(speechRecognizerException);
                        th = speechRecognizerException;
                    }
                    RecognizeSpeechTask.this.consumable.reject(th);
                    if (SpeechRecognizerPlugin.this.channel != null) {
                        SpeechRecognizerPlugin.this.channel.releaseActivation();
                    }
                    SpeechRecognizerPlugin.this.listening = false;
                    SpeechRecognizerPlugin.this.notifyRecognizeSpeechFailed(th);
                }
            });
        }
    }

    public static final class Builder {
        Function<AudioSink, AudioTranscoder> audioTranscoder;
        Channel channel;
        Context context;
        String profile;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder channel(Channel channel) {
            this.channel = channel;
            return this;
        }

        public Builder audioTranscoder(Function<AudioSink, AudioTranscoder> function) {
            this.audioTranscoder = function;
            return this;
        }

        public SpeechRecognizerPlugin build() {
            Preconditions.mainThread("build() has to be called from the main thread");
            Preconditions.notNull(this.context, "context == null");
            Preconditions.notNull(this.audioTranscoder, "audioTranscoder == null");
            return new SpeechRecognizerPlugin(this);
        }
    }
}
