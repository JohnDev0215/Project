package com.amazon.music.voice.metrics;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.core.processor.superbowl.directives.AudioPlayDirective;
import com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin;
import com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin;
import com.amazon.music.voice.MusicAudioPlayerPlugin;
import com.amazon.music.voice.emp.EMPPlayStatus;
import com.amazon.music.voice.emp.EMPPlaybackState;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class AlexaMetricsProvider {
    private static final Logger LOG = LoggerFactory.getLogger(AlexaMetricsProvider.class.getSimpleName());
    private Observable<EMPPlaybackState> empPlaybackStateObservable;
    private String synthesizeSpeechToken;
    private SystemTimeProvider timeProvider;
    private long endOfUtteranceTimeMilliseconds = 0;
    private long endOfTtsMessageTimeMilliseconds = 0;
    private long beginningOfTtsMessagePlaybackMilliseconds = 0;
    private long audioPlaybackStartedMilliseconds = 0;
    private boolean ttsPresent = false;
    private boolean utterancePresent = false;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private SpeechRecognizerPlugin.RecognizeSpeechListener recognizeSpeechListener = new SpeechRecognizerPlugin.SimpleRecognizeSpeechListener() { // from class: com.amazon.music.voice.metrics.AlexaMetricsProvider.1
        @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.SimpleRecognizeSpeechListener, com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechListener
        public void onRecognizeSpeechStarted(boolean z) {
            AlexaMetricsProvider.this.reset();
        }

        @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.SimpleRecognizeSpeechListener, com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechListener
        public void onRecognizeSpeechCancelled() {
            AlexaMetricsProvider.this.reset();
        }

        @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.SimpleRecognizeSpeechListener, com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechListener
        public void onRecognizeSpeechRecordingCompleted() {
            AlexaMetricsProvider.this.utterancePresent = true;
            AlexaMetricsProvider alexaMetricsProvider = AlexaMetricsProvider.this;
            alexaMetricsProvider.endOfUtteranceTimeMilliseconds = alexaMetricsProvider.timeProvider.getCurrentTimeMilliseconds();
            AlexaMetricsProvider.LOG.debug("endOfUtteranceTimeMilliseconds: {}", Long.valueOf(AlexaMetricsProvider.this.endOfUtteranceTimeMilliseconds));
        }

        @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.SimpleRecognizeSpeechListener, com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin.RecognizeSpeechListener
        public void onRecognizeSpeechFailed(Throwable th) {
            AlexaMetricsProvider.this.reset();
        }
    };
    private SpeechSynthesizerPlugin.SynthesizeSpeechListener synthesizeSpeechListener = new SpeechSynthesizerPlugin.SimpleSynthesizeSpeechListener() { // from class: com.amazon.music.voice.metrics.AlexaMetricsProvider.2
        @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.SimpleSynthesizeSpeechListener, com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.SynthesizeSpeechListener
        public void onSynthesizeSpeechStarted(String str) {
            AlexaMetricsProvider alexaMetricsProvider = AlexaMetricsProvider.this;
            alexaMetricsProvider.beginningOfTtsMessagePlaybackMilliseconds = alexaMetricsProvider.timeProvider.getCurrentTimeMilliseconds();
            AlexaMetricsProvider.this.ttsPresent = true;
            AlexaMetricsProvider.this.synthesizeSpeechToken = str;
            AlexaMetricsProvider.LOG.debug("onSynthesizeSpeechStarted: {}, beginningOfTtsMessagePlaybackMilliseconds: {}", str, Long.valueOf(AlexaMetricsProvider.this.beginningOfTtsMessagePlaybackMilliseconds));
        }

        @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.SimpleSynthesizeSpeechListener, com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.SynthesizeSpeechListener
        public void onSynthesizeSpeechCancelled(String str) {
            AlexaMetricsProvider.this.reset();
        }

        @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.SimpleSynthesizeSpeechListener, com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.SynthesizeSpeechListener
        public void onSynthesizeSpeechFailed(String str, Throwable th) {
            AlexaMetricsProvider.this.reset();
        }

        @Override // com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.SimpleSynthesizeSpeechListener, com.amazon.alexa.voice.superbowl.plugins.SpeechSynthesizerPlugin.SynthesizeSpeechListener
        public void onSynthesizeSpeechCompleted(String str) {
            if (AlexaMetricsProvider.this.beginningOfTtsMessagePlaybackMilliseconds == 0) {
                AlexaMetrics metricsByToken = AlexaMetricsProvider.this.getMetricsByToken(str);
                if (metricsByToken != null) {
                    if (metricsByToken.getEndOfTtsMessageTimeMilliseconds() == 0) {
                        metricsByToken.setEndOfTtsMessageTimeMilliseconds(AlexaMetricsProvider.this.timeProvider.getCurrentTimeMilliseconds());
                    } else {
                        AlexaMetricsProvider.LOG.warn("The onSynthesizeSpeechCompleted is called more than once for the same token");
                    }
                }
            } else {
                AlexaMetricsProvider alexaMetricsProvider = AlexaMetricsProvider.this;
                alexaMetricsProvider.endOfTtsMessageTimeMilliseconds = alexaMetricsProvider.timeProvider.getCurrentTimeMilliseconds();
            }
            AlexaMetricsProvider.LOG.debug("onSynthesizeSpeechCompleted: {}", str);
        }
    };
    private MusicAudioPlayerPlugin.AudioPlayerListener audioPlayerListener = new MusicAudioPlayerPlugin.SimpleAudioPlayerListener() { // from class: com.amazon.music.voice.metrics.AlexaMetricsProvider.3
        @Override // com.amazon.music.voice.MusicAudioPlayerPlugin.SimpleAudioPlayerListener, com.amazon.music.voice.MusicAudioPlayerPlugin.AudioPlayerListener
        public void onAudioItemEnqueued(AudioPlayDirective.AudioItem audioItem) {
            AlexaMetricsProvider.LOG.debug("onAudioItemEnqueued: {}", audioItem);
            if (audioItem != null) {
                AlexaMetrics alexaMetrics = new AlexaMetrics(audioItem, AlexaMetricsProvider.this.getAlexaTtsIpdMilliseconds(), AlexaMetricsProvider.this.ttsPresent, AlexaMetricsProvider.this.utterancePresent, AlexaMetricsProvider.this.endOfUtteranceTimeMilliseconds, AlexaMetricsProvider.this.endOfTtsMessageTimeMilliseconds, AlexaMetricsProvider.this.synthesizeSpeechToken);
                AlexaMetricsProvider.this.metricsDeque.addLast(alexaMetrics);
                AlexaMetricsProvider.LOG.debug("metrics: {}", alexaMetrics);
                AlexaMetricsProvider.this.reset();
            }
        }

        @Override // com.amazon.music.voice.MusicAudioPlayerPlugin.SimpleAudioPlayerListener, com.amazon.music.voice.MusicAudioPlayerPlugin.AudioPlayerListener
        public void onAudioPlaybackStarted(AudioPlayDirective.AudioItem audioItem) {
            AlexaMetricsProvider alexaMetricsProvider = AlexaMetricsProvider.this;
            alexaMetricsProvider.audioPlaybackStartedMilliseconds = alexaMetricsProvider.timeProvider.getCurrentTimeMilliseconds();
            if (audioItem == null || audioItem.getStream().getUrl() == null) {
                return;
            }
            AlexaMetrics metricsByMpqsUrl = AlexaMetricsProvider.this.getMetricsByMpqsUrl(audioItem.getStream().getUrl(), audioItem.getStream().getToken());
            if (metricsByMpqsUrl != null) {
                metricsByMpqsUrl.setAudioPlaybackStartedMilliseconds(AlexaMetricsProvider.this.audioPlaybackStartedMilliseconds);
            }
            AlexaMetricsProvider.LOG.debug("onAudioPlaybackStarted: {}, alexaMetrics: {}", Long.valueOf(AlexaMetricsProvider.this.audioPlaybackStartedMilliseconds), metricsByMpqsUrl);
        }
    };
    private Deque<AlexaMetrics> metricsDeque = new LinkedBlockingDeque();

    public static class AlexaMetrics {
        public final long alexaTtsIpdMilliseconds;
        public final AudioPlayDirective.AudioItem audioItem;
        private long audioPlaybackStartedMilliseconds;
        private long endOfTtsMessageTimeMilliseconds;
        public final long endOfUtteranceTimeMilliseconds;
        private String mediaContentId;
        private Map<String, String> metricsContext;
        public final String synthesizeSpeechToken;
        public final boolean ttsPresent;
        public final boolean utterancePresent;

        public AlexaMetrics(AudioPlayDirective.AudioItem audioItem, long j, boolean z, boolean z2, long j2, long j3, String str) {
            this.audioItem = audioItem;
            this.alexaTtsIpdMilliseconds = j;
            this.ttsPresent = z;
            this.utterancePresent = z2;
            this.endOfUtteranceTimeMilliseconds = j2;
            this.endOfTtsMessageTimeMilliseconds = j3;
            this.synthesizeSpeechToken = str;
        }

        public long getInitialPlaybackDelayMilliseconds(long j) {
            if (j == 0) {
                return 0L;
            }
            if (this.ttsPresent) {
                return j - this.endOfTtsMessageTimeMilliseconds;
            }
            if (this.utterancePresent) {
                long j2 = this.endOfUtteranceTimeMilliseconds;
                if (j2 != 0) {
                    return j - j2;
                }
            }
            return 0L;
        }

        public void setAudioPlaybackStartedMilliseconds(long j) {
            this.audioPlaybackStartedMilliseconds = j;
        }

        public long getEndOfTtsMessageTimeMilliseconds() {
            return this.endOfTtsMessageTimeMilliseconds;
        }

        public void setEndOfTtsMessageTimeMilliseconds(long j) {
            this.endOfTtsMessageTimeMilliseconds = j;
        }

        public String getMediaContentId() {
            return this.mediaContentId;
        }

        public void setMediaContentId(String str) {
            this.mediaContentId = str;
        }

        public String toString() {
            return "AlexaMetrics{alexaTtsIpdMilliseconds=" + this.alexaTtsIpdMilliseconds + ", audioItem=" + this.audioItem + ", ttsPresent=" + this.ttsPresent + ", endOfTtsMessageTimeMilliseconds=" + this.endOfTtsMessageTimeMilliseconds + ", audioPlaybackStartedMilliseconds=" + this.audioPlaybackStartedMilliseconds + ", endOfUtteranceTimeMilliseconds=" + this.endOfUtteranceTimeMilliseconds + ", mediaContentId=" + this.mediaContentId + ", metricsContext=" + this.metricsContext + '}';
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AlexaMetrics)) {
                return false;
            }
            AlexaMetrics alexaMetrics = (AlexaMetrics) obj;
            if (this.alexaTtsIpdMilliseconds != alexaMetrics.alexaTtsIpdMilliseconds || this.ttsPresent != alexaMetrics.ttsPresent || this.utterancePresent != alexaMetrics.utterancePresent || this.endOfTtsMessageTimeMilliseconds != alexaMetrics.endOfTtsMessageTimeMilliseconds || this.endOfUtteranceTimeMilliseconds != alexaMetrics.endOfUtteranceTimeMilliseconds || this.audioPlaybackStartedMilliseconds != alexaMetrics.audioPlaybackStartedMilliseconds || !this.audioItem.equals(alexaMetrics.audioItem) || !this.synthesizeSpeechToken.equals(alexaMetrics.synthesizeSpeechToken)) {
                return false;
            }
            String str = this.mediaContentId;
            if (str == null ? alexaMetrics.mediaContentId != null : !str.equals(alexaMetrics.mediaContentId)) {
                return false;
            }
            Map<String, String> map = this.metricsContext;
            Map<String, String> map2 = alexaMetrics.metricsContext;
            return map == null ? map2 == null : map.equals(map2);
        }

        public int hashCode() {
            long j = this.alexaTtsIpdMilliseconds;
            int iHashCode = ((((((((int) (j ^ (j >>> 32))) * 31) + this.audioItem.hashCode()) * 31) + (this.ttsPresent ? 1 : 0)) * 31) + (this.utterancePresent ? 1 : 0)) * 31;
            long j2 = this.endOfTtsMessageTimeMilliseconds;
            int i = (iHashCode + ((int) (j2 ^ (j2 >>> 32)))) * 31;
            long j3 = this.endOfUtteranceTimeMilliseconds;
            int iHashCode2 = (((i + ((int) (j3 ^ (j3 >>> 32)))) * 31) + this.synthesizeSpeechToken.hashCode()) * 31;
            long j4 = this.audioPlaybackStartedMilliseconds;
            int i2 = (iHashCode2 + ((int) ((j4 >>> 32) ^ j4))) * 31;
            String str = this.mediaContentId;
            int iHashCode3 = (i2 + (str != null ? str.hashCode() : 0)) * 31;
            Map<String, String> map = this.metricsContext;
            return iHashCode3 + (map != null ? map.hashCode() : 0);
        }
    }

    public AlexaMetricsProvider(SystemTimeProvider systemTimeProvider, Observable<EMPPlaybackState> observable) {
        this.timeProvider = systemTimeProvider;
        this.empPlaybackStateObservable = observable;
        subscribeToEmpPlaybackStateObservable();
    }

    public AlexaMetrics getMetricsByMpqsUrl(String str, String str2) {
        if (str != null && str2 != null) {
            for (AlexaMetrics alexaMetrics : this.metricsDeque) {
                AudioPlayDirective.AudioItem audioItem = alexaMetrics.audioItem;
                if (audioItem != null && audioItem.getStream() != null && (str.equals(alexaMetrics.audioItem.getStream().getUrl()) || str2.equals(alexaMetrics.audioItem.getStream().getToken()))) {
                    return alexaMetrics;
                }
            }
        }
        return null;
    }

    public AlexaMetrics getMetricsByMediaContentId(String str) {
        if (str == null) {
            return null;
        }
        for (AlexaMetrics alexaMetrics : this.metricsDeque) {
            if (str.equals(alexaMetrics.getMediaContentId())) {
                return alexaMetrics;
            }
        }
        return null;
    }

    @VisibleForTesting
    int getMetricsDequeSize() {
        return this.metricsDeque.size();
    }

    public AlexaMetrics getMetricsByToken(String str) {
        if (str == null) {
            return null;
        }
        Iterator<AlexaMetrics> itDescendingIterator = this.metricsDeque.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            AlexaMetrics next = itDescendingIterator.next();
            String str2 = next.synthesizeSpeechToken;
            if (str2 != null && str2.equals(str)) {
                return next;
            }
        }
        return null;
    }

    public void drainMetrics(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        AlexaMetrics alexaMetricsPollFirst = this.metricsDeque.pollFirst();
        while (alexaMetricsPollFirst != null) {
            AudioPlayDirective.AudioItem audioItem = alexaMetricsPollFirst.audioItem;
            if (audioItem != null && audioItem.getStream() != null && (str.equals(alexaMetricsPollFirst.audioItem.getStream().getUrl()) || str2.equals(alexaMetricsPollFirst.audioItem.getStream().getToken()))) {
                return;
            } else {
                alexaMetricsPollFirst = this.metricsDeque.pollFirst();
            }
        }
    }

    public SpeechRecognizerPlugin.RecognizeSpeechListener getRecognizeSpeechListener() {
        return this.recognizeSpeechListener;
    }

    public SpeechSynthesizerPlugin.SynthesizeSpeechListener getSynthesizeSpeechListener() {
        return this.synthesizeSpeechListener;
    }

    public MusicAudioPlayerPlugin.AudioPlayerListener getAudioPlayerListener() {
        return this.audioPlayerListener;
    }

    protected long getAlexaTtsIpdMilliseconds() {
        long j = this.beginningOfTtsMessagePlaybackMilliseconds;
        long j2 = 0;
        if (j != 0) {
            long j3 = this.endOfUtteranceTimeMilliseconds;
            if (j3 != 0) {
                j2 = j - j3;
            }
        }
        LOG.debug("alexaTtsIpdMilliseconds: {}", Long.valueOf(j2));
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reset() {
        LOG.debug("reset");
        this.endOfUtteranceTimeMilliseconds = 0L;
        this.endOfTtsMessageTimeMilliseconds = 0L;
        this.beginningOfTtsMessagePlaybackMilliseconds = 0L;
        this.audioPlaybackStartedMilliseconds = 0L;
        this.ttsPresent = false;
        this.utterancePresent = false;
        this.synthesizeSpeechToken = null;
    }

    private void subscribeToEmpPlaybackStateObservable() {
        this.disposables.add(this.empPlaybackStateObservable.subscribeOn(Schedulers.computation()).subscribe(new Consumer() { // from class: com.amazon.music.voice.metrics.AlexaMetricsProvider$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) throws Exception {
                this.f$0.lambda$subscribeToEmpPlaybackStateObservable$0((EMPPlaybackState) obj);
            }
        }, new Consumer() { // from class: com.amazon.music.voice.metrics.AlexaMetricsProvider$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) throws Exception {
                AlexaMetricsProvider.lambda$subscribeToEmpPlaybackStateObservable$1((Throwable) obj);
            }
        }));
    }

    /* JADX INFO: renamed from: com.amazon.music.voice.metrics.AlexaMetricsProvider$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$voice$emp$EMPPlayStatus;

        static {
            int[] iArr = new int[EMPPlayStatus.values().length];
            $SwitchMap$com$amazon$music$voice$emp$EMPPlayStatus = iArr;
            try {
                iArr[EMPPlayStatus.PREPARING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$emp$EMPPlayStatus[EMPPlayStatus.PLAYBACK_STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$subscribeToEmpPlaybackStateObservable$0(EMPPlaybackState eMPPlaybackState) throws Exception {
        String mediaItemId = eMPPlaybackState.getMediaItemId();
        int i = AnonymousClass4.$SwitchMap$com$amazon$music$voice$emp$EMPPlayStatus[eMPPlaybackState.getPlayStatus().ordinal()];
        if (i == 1) {
            AlexaMetrics alexaMetrics = new AlexaMetrics(null, getAlexaTtsIpdMilliseconds(), this.ttsPresent, this.utterancePresent, this.endOfUtteranceTimeMilliseconds, this.endOfTtsMessageTimeMilliseconds, this.synthesizeSpeechToken);
            alexaMetrics.setMediaContentId(mediaItemId);
            this.metricsDeque.addLast(alexaMetrics);
            LOG.debug("metrics: {}", alexaMetrics);
            reset();
            return;
        }
        if (i != 2) {
            return;
        }
        this.audioPlaybackStartedMilliseconds = this.timeProvider.getCurrentTimeMilliseconds();
        eMPPlaybackState.getMediaItemId();
        AlexaMetrics metricsByMediaContentId = getMetricsByMediaContentId(mediaItemId);
        if (metricsByMediaContentId != null) {
            metricsByMediaContentId.setAudioPlaybackStartedMilliseconds(this.audioPlaybackStartedMilliseconds);
        }
        LOG.debug("onEmpPlaybackStarted: {}, alexaMetrics: {}", Long.valueOf(this.audioPlaybackStartedMilliseconds), metricsByMediaContentId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$subscribeToEmpPlaybackStateObservable$1(Throwable th) throws Exception {
        LOG.error(th.getLocalizedMessage());
    }
}
