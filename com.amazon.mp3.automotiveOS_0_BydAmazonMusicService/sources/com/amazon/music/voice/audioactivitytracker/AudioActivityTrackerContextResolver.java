package com.amazon.music.voice.audioactivitytracker;

import com.amazon.alexa.voice.core.AudioPlayer;
import com.amazon.alexa.voice.core.processor.superbowl.ContextResolver;
import com.amazon.alexa.voice.core.processor.superbowl.directives.AudioPlayDirective;
import com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin;
import com.amazon.music.voice.MusicAudioPlayerPlugin;
import com.amazon.music.voice.audioactivitytracker.AudioActivityTrackerContext;
import com.amazon.music.voice.metrics.SystemTimeProvider;
import com.amazon.music.voice.playbackstate.PlayerStateProvider;
import io.reactivex.disposables.CompositeDisposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AudioActivityTrackerContextResolver.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b*\u0010+J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\tR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0018\u0010\r\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010\fR\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R*\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00148F@BX\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR*\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0015\u001a\u00020\u001c8F@BX\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R*\u0010$\u001a\u00020#2\u0006\u0010\u0015\u001a\u00020#8F@BX\u0086\u000e¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)¨\u0006,"}, d2 = {"Lcom/amazon/music/voice/audioactivitytracker/AudioActivityTrackerContextResolver;", "Lcom/amazon/alexa/voice/core/processor/superbowl/ContextResolver;", "Lcom/amazon/music/voice/audioactivitytracker/AudioActivityTrackerContext;", "resolve", "Lcom/amazon/music/voice/playbackstate/PlayerStateProvider;", "playerStateProvider", "Lcom/amazon/music/voice/playbackstate/PlayerStateProvider;", "Lcom/amazon/music/voice/metrics/SystemTimeProvider;", "systemTimeProvider", "Lcom/amazon/music/voice/metrics/SystemTimeProvider;", "", "dialogChannelResetTimeInMilliSeconds", "Ljava/lang/Long;", "contentChannelResetTimeInMilliSeconds", "Lcom/amazon/music/voice/audioactivitytracker/AudioActivityTrackerContext$ContentChannel;", "contentChannel", "Lcom/amazon/music/voice/audioactivitytracker/AudioActivityTrackerContext$ContentChannel;", "Lio/reactivex/disposables/CompositeDisposable;", "disposable", "Lio/reactivex/disposables/CompositeDisposable;", "Lcom/amazon/alexa/voice/superbowl/plugins/SpeechSynthesizerPlugin$SynthesizeSpeechListener;", "value", "synthesizeSpeechListener", "Lcom/amazon/alexa/voice/superbowl/plugins/SpeechSynthesizerPlugin$SynthesizeSpeechListener;", "getSynthesizeSpeechListener", "()Lcom/amazon/alexa/voice/superbowl/plugins/SpeechSynthesizerPlugin$SynthesizeSpeechListener;", "setSynthesizeSpeechListener", "(Lcom/amazon/alexa/voice/superbowl/plugins/SpeechSynthesizerPlugin$SynthesizeSpeechListener;)V", "Lcom/amazon/music/voice/MusicAudioPlayerPlugin$AudioPlayerListener;", "audioPlayerListener", "Lcom/amazon/music/voice/MusicAudioPlayerPlugin$AudioPlayerListener;", "getAudioPlayerListener", "()Lcom/amazon/music/voice/MusicAudioPlayerPlugin$AudioPlayerListener;", "setAudioPlayerListener", "(Lcom/amazon/music/voice/MusicAudioPlayerPlugin$AudioPlayerListener;)V", "Lcom/amazon/alexa/voice/core/AudioPlayer$Callback;", "empPlayerListener", "Lcom/amazon/alexa/voice/core/AudioPlayer$Callback;", "getEmpPlayerListener", "()Lcom/amazon/alexa/voice/core/AudioPlayer$Callback;", "setEmpPlayerListener", "(Lcom/amazon/alexa/voice/core/AudioPlayer$Callback;)V", "<init>", "(Lcom/amazon/music/voice/playbackstate/PlayerStateProvider;Lcom/amazon/music/voice/metrics/SystemTimeProvider;)V", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
public final class AudioActivityTrackerContextResolver implements ContextResolver<AudioActivityTrackerContext> {
    private MusicAudioPlayerPlugin.AudioPlayerListener audioPlayerListener;
    private AudioActivityTrackerContext.ContentChannel contentChannel;
    private Long contentChannelResetTimeInMilliSeconds;
    private Long dialogChannelResetTimeInMilliSeconds;
    private final CompositeDisposable disposable;
    private AudioPlayer.Callback empPlayerListener;
    private final PlayerStateProvider playerStateProvider;
    private SpeechSynthesizerPlugin.SynthesizeSpeechListener synthesizeSpeechListener;
    private final SystemTimeProvider systemTimeProvider;

    public AudioActivityTrackerContextResolver(PlayerStateProvider playerStateProvider, SystemTimeProvider systemTimeProvider) {
        Intrinsics.checkNotNullParameter(playerStateProvider, "playerStateProvider");
        Intrinsics.checkNotNullParameter(systemTimeProvider, "systemTimeProvider");
        this.playerStateProvider = playerStateProvider;
        this.systemTimeProvider = systemTimeProvider;
        this.disposable = new CompositeDisposable();
        this.synthesizeSpeechListener = new SpeechSynthesizerPlugin.SimpleSynthesizeSpeechListener() { // from class: com.amazon.music.voice.audioactivitytracker.AudioActivityTrackerContextResolver$synthesizeSpeechListener$1
            @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.SimpleSynthesizeSpeechListener, com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.SynthesizeSpeechListener
            public void onSynthesizeSpeechCompleted(String token) {
                Intrinsics.checkNotNullParameter(token, "token");
                AudioActivityTrackerContextResolver audioActivityTrackerContextResolver = this.this$0;
                audioActivityTrackerContextResolver.dialogChannelResetTimeInMilliSeconds = Long.valueOf(audioActivityTrackerContextResolver.systemTimeProvider.getCurrentTimeMilliseconds());
            }
        };
        this.audioPlayerListener = new MusicAudioPlayerPlugin.SimpleAudioPlayerListener() { // from class: com.amazon.music.voice.audioactivitytracker.AudioActivityTrackerContextResolver$audioPlayerListener$1
            @Override // com.amazon.music.voice.MusicAudioPlayerPlugin.SimpleAudioPlayerListener, com.amazon.music.voice.MusicAudioPlayerPlugin.AudioPlayerListener
            public void onAudioPlaybackStarted(AudioPlayDirective.AudioItem audioItem) {
                Intrinsics.checkNotNullParameter(audioItem, "audioItem");
                this.this$0.contentChannel = AudioActivityTrackerContext.ContentChannel.AudioPlayer;
                AudioActivityTrackerContextResolver audioActivityTrackerContextResolver = this.this$0;
                audioActivityTrackerContextResolver.contentChannelResetTimeInMilliSeconds = Long.valueOf(audioActivityTrackerContextResolver.systemTimeProvider.getCurrentTimeMilliseconds());
            }
        };
        this.empPlayerListener = new AudioPlayer.SimpleCallback() { // from class: com.amazon.music.voice.audioactivitytracker.AudioActivityTrackerContextResolver$empPlayerListener$1
            @Override // com.amazon.alexa.voice.core.AudioPlayer.SimpleCallback, com.amazon.alexa.voice.core.AudioPlayer.Callback
            public void onPlaybackStarted() {
                this.this$0.contentChannel = AudioActivityTrackerContext.ContentChannel.ExternalMediaPlayer;
                AudioActivityTrackerContextResolver audioActivityTrackerContextResolver = this.this$0;
                audioActivityTrackerContextResolver.contentChannelResetTimeInMilliSeconds = Long.valueOf(audioActivityTrackerContextResolver.systemTimeProvider.getCurrentTimeMilliseconds());
            }
        };
        playerStateProvider.add(getEmpPlayerListener());
    }

    public final SpeechSynthesizerPlugin.SynthesizeSpeechListener getSynthesizeSpeechListener() {
        return this.synthesizeSpeechListener;
    }

    public final MusicAudioPlayerPlugin.AudioPlayerListener getAudioPlayerListener() {
        return this.audioPlayerListener;
    }

    public final AudioPlayer.Callback getEmpPlayerListener() {
        return this.empPlayerListener;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.ContextResolver
    public AudioActivityTrackerContext resolve() {
        Long lValueOf;
        Long lValueOf2;
        Long l = this.dialogChannelResetTimeInMilliSeconds;
        if (l == null) {
            lValueOf = null;
        } else {
            lValueOf = Long.valueOf(this.systemTimeProvider.getCurrentTimeMilliseconds() - l.longValue());
        }
        Long l2 = this.contentChannelResetTimeInMilliSeconds;
        if (l2 == null) {
            lValueOf2 = null;
        } else {
            lValueOf2 = Long.valueOf(this.systemTimeProvider.getCurrentTimeMilliseconds() - l2.longValue());
        }
        if (lValueOf == null && lValueOf2 == null) {
            return null;
        }
        return new AudioActivityTrackerContext.Builder().withContentChannel(this.contentChannel, lValueOf2).withDialogChannelIdleTime(lValueOf).build();
    }
}
