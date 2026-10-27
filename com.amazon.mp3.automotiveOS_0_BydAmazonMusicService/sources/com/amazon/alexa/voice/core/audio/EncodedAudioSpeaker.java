package com.amazon.alexa.voice.core.audio;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.voice.core.AudioSink;
import com.amazon.alexa.voice.core.Cancellable;
import com.amazon.alexa.voice.core.Logger;
import com.amazon.alexa.voice.core.internal.audio.AudioBase;
import com.amazon.alexa.voice.core.internal.audio.StaticAudioDataSource;
import com.amazon.alexa.voice.core.internal.util.SimpleBuffer;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.audio.MediaCodecAudioRenderer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class EncodedAudioSpeaker extends AudioBase implements Cancellable, AudioSink {
    private SimpleBuffer buffer;
    private volatile boolean cancelled;
    private volatile boolean completed;
    private final Object completionMonitor = new Object();
    private final Handler handler = new Handler(Looper.getMainLooper());
    private ExoPlayer player;

    @Override // com.amazon.alexa.voice.core.AudioSink
    public void flush() throws IOException {
    }

    private void play(final byte[] bArr, final int i, final int i2) throws IOException {
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.voice.core.audio.EncodedAudioSpeaker.1
            @Override // java.lang.Runnable
            public void run() {
                ExtractorMediaSource extractorMediaSource = new ExtractorMediaSource(Uri.EMPTY, new StaticAudioDataSource.Factory(new StreamAudioSource(new ByteArrayInputStream(bArr, i, i2))), new DefaultExtractorsFactory(), EncodedAudioSpeaker.this.handler, null);
                DefaultTrackSelector defaultTrackSelector = new DefaultTrackSelector();
                MediaCodecAudioRenderer mediaCodecAudioRenderer = new MediaCodecAudioRenderer(MediaCodecSelector.DEFAULT);
                if (EncodedAudioSpeaker.this.player != null) {
                    EncodedAudioSpeaker.this.player.release();
                }
                EncodedAudioSpeaker.this.player = ExoPlayerFactory.newInstance(new Renderer[]{mediaCodecAudioRenderer}, defaultTrackSelector);
                EncodedAudioSpeaker.this.player.addListener(new Player.EventListener() { // from class: com.amazon.alexa.voice.core.audio.EncodedAudioSpeaker.1.1
                    @Override // com.google.android.exoplayer2.Player.EventListener
                    public void onLoadingChanged(boolean z) {
                    }

                    @Override // com.google.android.exoplayer2.Player.EventListener
                    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                    }

                    @Override // com.google.android.exoplayer2.Player.EventListener
                    public void onPositionDiscontinuity() {
                    }

                    @Override // com.google.android.exoplayer2.Player.EventListener
                    public void onRepeatModeChanged(int i3) {
                    }

                    @Override // com.google.android.exoplayer2.Player.EventListener
                    public void onTimelineChanged(Timeline timeline, Object obj) {
                    }

                    @Override // com.google.android.exoplayer2.Player.EventListener
                    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
                    }

                    @Override // com.google.android.exoplayer2.Player.EventListener
                    public void onPlayerStateChanged(boolean z, int i3) {
                        if (i3 == 4) {
                            EncodedAudioSpeaker.this.notifyCompleted();
                        }
                    }

                    @Override // com.google.android.exoplayer2.Player.EventListener
                    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
                        EncodedAudioSpeaker.this.notifyCompleted();
                    }
                });
                EncodedAudioSpeaker.this.player.prepare(extractorMediaSource);
                EncodedAudioSpeaker.this.player.sendMessages(new ExoPlayer.ExoPlayerMessage(mediaCodecAudioRenderer, 2, Float.valueOf(1.0f)));
                EncodedAudioSpeaker.this.player.setPlayWhenReady(true);
            }
        });
        awaitCompletion();
    }

    @Override // com.amazon.alexa.voice.core.AudioSink
    public void write(byte[] bArr, int i, int i2) throws IOException {
        checkClosed();
        if (this.buffer == null) {
            this.buffer = new SimpleBuffer();
        }
        this.buffer.write(bArr, i, i2);
    }

    void notifyCompleted() {
        synchronized (this.completionMonitor) {
            this.completed = true;
            this.completionMonitor.notifyAll();
        }
    }

    private void awaitCompletion() throws IOException {
        synchronized (this.completionMonitor) {
            if (!this.completed) {
                try {
                    this.completionMonitor.wait();
                } catch (InterruptedException e) {
                    Logger.error("Failed to await for completion", e);
                }
            }
        }
    }

    @Override // com.amazon.alexa.voice.core.internal.audio.AudioBase, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (isClosed()) {
            return;
        }
        super.close();
        SimpleBuffer simpleBuffer = this.buffer;
        if (simpleBuffer != null && simpleBuffer.size() > 0) {
            play(this.buffer.buffer(), 0, this.buffer.size());
        }
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.voice.core.audio.EncodedAudioSpeaker.2
            @Override // java.lang.Runnable
            public void run() {
                if (EncodedAudioSpeaker.this.player != null) {
                    EncodedAudioSpeaker.this.player.release();
                    EncodedAudioSpeaker.this.player = null;
                }
            }
        });
    }

    @Override // com.amazon.alexa.voice.core.Cancellable
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override // com.amazon.alexa.voice.core.Cancellable
    public void cancel() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.voice.core.audio.EncodedAudioSpeaker.3
            @Override // java.lang.Runnable
            public void run() {
                if (EncodedAudioSpeaker.this.player != null) {
                    EncodedAudioSpeaker.this.player.stop();
                    EncodedAudioSpeaker.this.notifyCompleted();
                }
            }
        });
    }
}
