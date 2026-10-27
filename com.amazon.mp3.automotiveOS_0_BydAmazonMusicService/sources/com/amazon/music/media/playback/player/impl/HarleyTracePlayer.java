package com.amazon.music.media.playback.player.impl;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.digitalmusicplayback.AudioAttributes;
import com.amazon.digitalmusicplayback.AudioFormat;
import com.amazon.digitalmusicplayback.AuthenticationListener;
import com.amazon.digitalmusicplayback.ClientInfo;
import com.amazon.digitalmusicplayback.ConcurrencyListener;
import com.amazon.digitalmusicplayback.CustomerBenefitFlags;
import com.amazon.digitalmusicplayback.DMLSConfig;
import com.amazon.digitalmusicplayback.DeviceCapability;
import com.amazon.digitalmusicplayback.DownloadCoordinator;
import com.amazon.digitalmusicplayback.DownloadCoordinatorConfig;
import com.amazon.digitalmusicplayback.DownloadCoordinatorListener;
import com.amazon.digitalmusicplayback.EngineError;
import com.amazon.digitalmusicplayback.EqualizerFormat;
import com.amazon.digitalmusicplayback.EqualizerSettings;
import com.amazon.digitalmusicplayback.EqualizerUpdate;
import com.amazon.digitalmusicplayback.FrequencySettings;
import com.amazon.digitalmusicplayback.LoggingListener;
import com.amazon.digitalmusicplayback.MTSAttributes;
import com.amazon.digitalmusicplayback.MetricsAttributesProvider;
import com.amazon.digitalmusicplayback.MetricsListener;
import com.amazon.digitalmusicplayback.OutputDeviceAttributes;
import com.amazon.digitalmusicplayback.PlayResponse;
import com.amazon.digitalmusicplayback.PlaybackListener;
import com.amazon.digitalmusicplayback.Player;
import com.amazon.digitalmusicplayback.PlayerConfig;
import com.amazon.digitalmusicplayback.PlayerFactory;
import com.amazon.digitalmusicplayback.PlayerLogLevel;
import com.amazon.digitalmusicplayback.QualitySetting;
import com.amazon.digitalmusicplayback.SequenceEntry;
import com.amazon.digitalmusicplayback.StreamingStatusResponse;
import com.amazon.digitalmusicplayback.Weblab;
import com.amazon.digitalmusicplayback.WeblabClient;
import com.amazon.music.media.playback.util.StringUtils;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.EnumSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class HarleyTracePlayer extends Player {
    private static final Logger logger = LoggerFactory.getLogger(HarleyTracePlayer.class.getSimpleName());
    private final Player player;

    @Override // com.amazon.digitalmusicplayback.Player
    public OutputDeviceAttributes getOutputDeviceAttributes() {
        return null;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void setExclusiveMode(boolean z) {
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void setOutputDevice(String str) {
    }

    static {
        StringUtils.registerStringConverter(new StringUtils.StringConverter<SequenceEntry>(SequenceEntry.class) { // from class: com.amazon.music.media.playback.player.impl.HarleyTracePlayer.1
            @Override // com.amazon.music.media.playback.util.StringUtils.StringConverter
            public String toString(@NonNull SequenceEntry sequenceEntry, boolean z) {
                if (!z) {
                    return sequenceEntry.toString();
                }
                return Integer.toString(sequenceEntry.getSequenceID());
            }
        });
        StringUtils.registerStringConverter(new StringUtils.StringConverter<PlayerConfig>(PlayerConfig.class) { // from class: com.amazon.music.media.playback.player.impl.HarleyTracePlayer.2
            @Override // com.amazon.music.media.playback.util.StringUtils.StringConverter
            public String toString(@NonNull PlayerConfig playerConfig, boolean z) {
                if (!z) {
                    return playerConfig.toString();
                }
                return "PlayerConfig{configPath=" + playerConfig.getConfigPath() + ",dmlsConfig=" + StringUtils.redactIfNeeded(playerConfig.getDmlsConfig()) + ",tempStoragePath=" + playerConfig.getTempStoragePath() + ",drmStoragePath=" + playerConfig.getDrmStoragePath() + ",disableStreamingConcurrencyCheck=" + playerConfig.getDisableStreamingConcurrencyCheck() + ",dataStoragePath=" + playerConfig.getDataStoragePath() + ",authStrategy=" + playerConfig.getAuthStrategy() + "}";
            }
        });
        StringUtils.registerStringConverter(new StringUtils.StringConverter<DMLSConfig>(DMLSConfig.class) { // from class: com.amazon.music.media.playback.player.impl.HarleyTracePlayer.3
            @Override // com.amazon.music.media.playback.util.StringUtils.StringConverter
            public String toString(@NonNull DMLSConfig dMLSConfig, boolean z) {
                if (!z) {
                    return dMLSConfig.toString();
                }
                return "DMLSConfig{customerID=" + StringUtils.redactIfNeeded(dMLSConfig.getCustomerID()) + ",deviceTypeID=" + dMLSConfig.getDeviceTypeID() + ",deviceID=" + StringUtils.redactIfNeeded(dMLSConfig.getDeviceID()) + ",appVersion=" + dMLSConfig.getAppVersion() + ",marketplaceID=" + dMLSConfig.getMarketplaceID() + ",territoryID=" + dMLSConfig.getTerritoryID() + ",gamma=" + dMLSConfig.getGamma() + "}";
            }
        });
        StringUtils.registerStringConverter(new StringUtils.StringConverter<PlayResponse>(PlayResponse.class) { // from class: com.amazon.music.media.playback.player.impl.HarleyTracePlayer.4
            @Override // com.amazon.music.media.playback.util.StringUtils.StringConverter
            public String toString(@NonNull PlayResponse playResponse, boolean z) {
                if (!z) {
                    return playResponse.toString();
                }
                return "PlayResponse{error=" + playResponse.getError() + ",sequenceEntries=" + StringUtils.redactIfNeeded(playResponse.getSequenceEntries()) + "}";
            }
        });
    }

    public static Player create(Context context, final PlaybackListener playbackListener, LoggingListener loggingListener, final MetricsListener metricsListener, final AuthenticationListener authenticationListener, final ConcurrencyListener concurrencyListener, final WeblabClient weblabClient, MetricsAttributesProvider metricsAttributesProvider) {
        String str = "create(" + context + ", ...)";
        Logger logger2 = logger;
        logger2.info(str);
        Player playerCreate = PlayerFactory.create(context, new PlaybackListener() { // from class: com.amazon.music.media.playback.player.impl.HarleyTracePlayer.5
            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void enable24BitPlaybackChanged(boolean z, boolean z2, EngineError engineError) {
            }

            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void outputDeviceAttributesChanged(OutputDeviceAttributes outputDeviceAttributes) {
            }

            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void prioritizeDownloadsChanged(boolean z, EngineError engineError) {
            }

            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void frameReady(int i, int i2, AudioFormat audioFormat, ArrayList<ByteBuffer> arrayList, int i3) {
                playbackListener.frameReady(i, i2, audioFormat, arrayList, i3);
            }

            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void startedPlayback(int i) {
                String str2 = "pb.startedPlayback(" + i + ")";
                HarleyTracePlayer.logger.info(str2);
                playbackListener.startedPlayback(i);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void playbackPaused(boolean z) {
                String str2 = "pb.playbackPaused(" + z + ")";
                HarleyTracePlayer.logger.info(str2);
                playbackListener.playbackPaused(z);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void skippedToNextTrack(int i, int i2) {
                String str2 = "pb.skippedToNextTrack(" + i + ", " + i2 + ")";
                HarleyTracePlayer.logger.info(str2);
                playbackListener.skippedToNextTrack(i, i2);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void finishedPlayback(int i) {
                String str2 = "pb.finishedPlayback(" + i + ")";
                HarleyTracePlayer.logger.info(str2);
                playbackListener.finishedPlayback(i);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void audioAttributesChanged(AudioAttributes audioAttributes) {
                String str2 = "pb.audioAttributesChanged(" + audioAttributes + ")";
                HarleyTracePlayer.logger.info(str2);
                playbackListener.audioAttributesChanged(audioAttributes);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void playbackStalled(boolean z, int i) {
                String str2 = "pb.playbackStalled(" + z + ", " + i + ")";
                HarleyTracePlayer.logger.info(str2);
                playbackListener.playbackStalled(z, i);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void trackInitFailed(int i, EngineError engineError) {
                String str2 = "pb.trackInitFailed(" + i + ", " + engineError + ")";
                HarleyTracePlayer.logger.info(str2);
                playbackListener.trackInitFailed(i, engineError);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void seekFinished(long j) {
                String str2 = "pb.seekFinished(" + j + ")";
                HarleyTracePlayer.logger.info(str2);
                playbackListener.seekFinished(j);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void seekFailed(EngineError engineError) {
                String str2 = "pb.seekFailed(" + engineError + ")";
                HarleyTracePlayer.logger.error(str2);
                playbackListener.seekFailed(engineError);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void liveStreamUpdated(long j) {
                String str2 = "pb.liveStreamUpdated(" + j + ")";
                HarleyTracePlayer.logger.info(str2);
                playbackListener.liveStreamUpdated(j);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void trackPlaybackFailed(int i, EngineError engineError) {
                String str2 = "pb.trackPlaybackFailed(" + i + ", " + engineError + ")";
                HarleyTracePlayer.logger.info(str2);
                playbackListener.trackPlaybackFailed(i, engineError);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void trackProgressed(int i, long j) {
                playbackListener.trackProgressed(i, j);
            }

            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void prebufferChanged(int i, long j, long j2) {
                playbackListener.prebufferChanged(i, j, j2);
            }

            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void playbackMuted(boolean z) {
                String str2 = "pb.playbackMuted(" + z + ")";
                HarleyTracePlayer.logger.info(str2);
                playbackListener.playbackMuted(z);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void playbackVolumeChanged(double d) {
                String str2 = "pb.playbackVolumeChanged(" + d + ")";
                HarleyTracePlayer.logger.info(str2);
                playbackListener.playbackVolumeChanged(d);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void equalizerSettingsChanged(EqualizerSettings equalizerSettings, boolean z) {
                String str2 = "pb.equalizerSettingsChanged(" + equalizerSettings + ", " + z + ")";
                HarleyTracePlayer.logger.info(str2);
                playbackListener.equalizerSettingsChanged(equalizerSettings, z);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void bassSettingsChanged(FrequencySettings frequencySettings, boolean z) {
                String str2 = "pb.bassSettingsChanged(" + frequencySettings + ", " + z + ")";
                HarleyTracePlayer.logger.info(str2);
                playbackListener.bassSettingsChanged(frequencySettings, z);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void trebleSettingsChanged(FrequencySettings frequencySettings, boolean z) {
                String str2 = "pb.trebleSettingsChanged(" + frequencySettings + ", " + z + ")";
                HarleyTracePlayer.logger.info(str2);
                playbackListener.trebleSettingsChanged(frequencySettings, z);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.PlaybackListener
            public void tempoChanged(double d, boolean z) {
                String str2 = "pb.tempoChanged(" + d + ", " + z + ")";
                HarleyTracePlayer.logger.info(str2);
                playbackListener.tempoChanged(d, z);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }
        }, loggingListener, new MetricsListener() { // from class: com.amazon.music.media.playback.player.impl.HarleyTracePlayer.6
            @Override // com.amazon.digitalmusicplayback.MetricsListener
            public void streamingInitiated(int i, MTSAttributes mTSAttributes) {
                String str2 = "metrics.streamingInitiated(" + i + ", " + mTSAttributes + ")";
                HarleyTracePlayer.logger.info(str2);
                metricsListener.streamingInitiated(i, mTSAttributes);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.MetricsListener
            public void localPlaybackFinished(int i, MTSAttributes mTSAttributes) {
                String str2 = "metrics.localPlaybackFinished(" + i + ", " + mTSAttributes + ")";
                HarleyTracePlayer.logger.info(str2);
                metricsListener.localPlaybackFinished(i, mTSAttributes);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.MetricsListener
            public void trackStreamed(int i, MTSAttributes mTSAttributes) {
                String str2 = "metrics.trackStreamed(" + i + ", " + mTSAttributes + ")";
                HarleyTracePlayer.logger.info(str2);
                metricsListener.trackStreamed(i, mTSAttributes);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.MetricsListener
            public void streamingInitFailed(int i, MTSAttributes mTSAttributes) {
                String str2 = "metrics.streamingInitFailed(" + i + ", " + mTSAttributes + ")";
                HarleyTracePlayer.logger.info(str2);
                metricsListener.streamingInitFailed(i, mTSAttributes);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.MetricsListener
            public void playbackTerminated(int i, MTSAttributes mTSAttributes) {
                String str2 = "metrics.playbackTerminated(" + i + ", " + mTSAttributes + ")";
                HarleyTracePlayer.logger.info(str2);
                metricsListener.playbackTerminated(i, mTSAttributes);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.MetricsListener
            public void streamingRequiredRebuffering(int i, float f, MTSAttributes mTSAttributes) {
                String str2 = "metrics.streamingRequiredRebuffering(" + i + ", " + f + ", " + mTSAttributes + ")";
                HarleyTracePlayer.logger.info(str2);
                metricsListener.streamingRequiredRebuffering(i, f, mTSAttributes);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.MetricsListener
            public void trackPlayedLocally(int i, MTSAttributes mTSAttributes) {
                String str2 = "metrics.trackPlayedLocally(" + i + ", " + mTSAttributes + ")";
                HarleyTracePlayer.logger.info(str2);
                metricsListener.trackPlayedLocally(i, mTSAttributes);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }
        }, new AuthenticationListener() { // from class: com.amazon.music.media.playback.player.impl.HarleyTracePlayer.7
            @Override // com.amazon.digitalmusicplayback.AuthenticationListener
            public String getToken() {
                HarleyTracePlayer.logger.info("auth.getADPToken()");
                String token = authenticationListener.getToken();
                HarleyTracePlayer.logger.info("auth.getADPToken() => " + StringUtils.redactIfNeeded(token));
                return token;
            }

            @Override // com.amazon.digitalmusicplayback.AuthenticationListener
            public String getSigningKeyForPlayer() {
                HarleyTracePlayer.logger.info("auth.getSigningKeyForPlayer()");
                String signingKeyForPlayer = authenticationListener.getSigningKeyForPlayer();
                HarleyTracePlayer.logger.info("auth.getSigningKeyForPlayer() => " + StringUtils.redactIfNeeded(signingKeyForPlayer));
                return signingKeyForPlayer;
            }
        }, new ConcurrencyListener() { // from class: com.amazon.music.media.playback.player.impl.HarleyTracePlayer.8
            @Override // com.amazon.digitalmusicplayback.ConcurrencyListener
            public void inactivityThresholdReceived(long j) {
                String str2 = "concurrency.inactivityThresholdReceived(" + j + ")";
                HarleyTracePlayer.logger.info(str2);
                concurrencyListener.inactivityThresholdReceived(j);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }

            @Override // com.amazon.digitalmusicplayback.ConcurrencyListener
            public void maximumConcurrencyReached(int i, ArrayList<StreamingStatusResponse> arrayList) {
                String str2 = "concurrency.maximumConcurrencyReached(" + i + ", " + StringUtils.redactIfNeeded(arrayList) + ")";
                HarleyTracePlayer.logger.info(str2);
                concurrencyListener.maximumConcurrencyReached(i, arrayList);
                HarleyTracePlayer.logger.info(str2 + " returned");
            }
        }, new WeblabClient() { // from class: com.amazon.music.media.playback.player.impl.HarleyTracePlayer.9
            @Override // com.amazon.digitalmusicplayback.WeblabClient
            public void declareWeblabs(ArrayList<Weblab> arrayList) {
                weblabClient.declareWeblabs(arrayList);
            }

            @Override // com.amazon.digitalmusicplayback.WeblabClient
            public String getTreatmentAndRecordTrigger(String str2, boolean z) {
                String treatmentAndRecordTrigger = weblabClient.getTreatmentAndRecordTrigger(str2, z);
                HarleyTracePlayer.logger.info("getTreatmentAndRecordTrigger(" + str2 + "," + Boolean.toString(z) + ") -> " + treatmentAndRecordTrigger);
                return treatmentAndRecordTrigger;
            }
        }, metricsAttributesProvider);
        logger2.info(str + " => " + playerCreate);
        return new HarleyTracePlayer(playerCreate);
    }

    private HarleyTracePlayer(Player player) {
        this.player = player;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public EngineError initialize(PlayerConfig playerConfig, ClientInfo clientInfo, EqualizerFormat equalizerFormat) {
        String str = "initialize(" + StringUtils.redactIfNeeded(playerConfig) + ", " + clientInfo + ", " + equalizerFormat + ")";
        Logger logger2 = logger;
        logger2.info(str);
        EngineError engineErrorInitialize = this.player.initialize(playerConfig, clientInfo, equalizerFormat);
        logger2.info(str + " => " + StringUtils.redactIfNeeded(engineErrorInitialize));
        return engineErrorInitialize;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public PlayResponse play(ArrayList<String> arrayList) {
        String str = "play(" + StringUtils.redactIfNeeded(arrayList) + ")";
        Logger logger2 = logger;
        logger2.info(str);
        PlayResponse playResponsePlay = this.player.play(arrayList);
        logger2.info(str + " => " + StringUtils.redactIfNeeded(playResponsePlay));
        return playResponsePlay;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public PlayResponse enqueue(SequenceEntry sequenceEntry, ArrayList<String> arrayList) {
        String str = "enqueue(" + StringUtils.redactIfNeeded(sequenceEntry) + ", " + StringUtils.redactIfNeeded(arrayList) + ")";
        Logger logger2 = logger;
        logger2.info(str);
        PlayResponse playResponseEnqueue = this.player.enqueue(sequenceEntry, arrayList);
        logger2.info(str + " => " + StringUtils.redactIfNeeded(playResponseEnqueue));
        return playResponseEnqueue;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public PlayResponse prebuffer(ArrayList<String> arrayList) {
        String str = "prebuffer(" + StringUtils.redactIfNeeded(arrayList) + ")";
        Logger logger2 = logger;
        logger2.info(str);
        PlayResponse playResponsePrebuffer = this.player.prebuffer(arrayList);
        logger2.info(str + " => " + StringUtils.redactIfNeeded(playResponsePrebuffer));
        return playResponsePrebuffer;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void playNext(SequenceEntry sequenceEntry) {
        String str = "playNext(" + StringUtils.redactIfNeeded(sequenceEntry) + ")";
        Logger logger2 = logger;
        logger2.info(str);
        this.player.playNext(sequenceEntry);
        logger2.info(str + " returned");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void seek(SequenceEntry sequenceEntry, long j) {
        String str = "seek(" + StringUtils.redactIfNeeded(sequenceEntry) + ", " + j + ")";
        Logger logger2 = logger;
        logger2.info(str);
        this.player.seek(sequenceEntry, j);
        logger2.info(str + " returned");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void shutdown(Boolean bool) {
        String str = "shutdown(" + bool + ")";
        Logger logger2 = logger;
        logger2.info(str);
        this.player.shutdown(bool);
        logger2.info(str + " returned");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public double getVolume() {
        Logger logger2 = logger;
        logger2.info("getVolume()");
        double volume = this.player.getVolume();
        logger2.info("getVolume() => " + volume);
        return volume;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void setVolume(double d) {
        String str = "setVolume(" + d + ")";
        Logger logger2 = logger;
        logger2.info(str);
        this.player.setVolume(d);
        logger2.info(str + " returned");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public int getQueueSizeHint() {
        Logger logger2 = logger;
        logger2.info("getQueueSizeHint()");
        int queueSizeHint = this.player.getQueueSizeHint();
        logger2.info("getQueueSizeHint() => " + queueSizeHint);
        return queueSizeHint;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public long getCurrentTime() {
        Logger logger2 = logger;
        logger2.info("getCurrentTime()");
        long currentTime = this.player.getCurrentTime();
        logger2.info("getCurrentTime() => " + currentTime);
        return currentTime;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public boolean paused() {
        Logger logger2 = logger;
        logger2.info("paused()");
        boolean zPaused = this.player.paused();
        logger2.info("paused() => " + zPaused);
        return zPaused;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public EngineError setPaused(boolean z) {
        String str = "setPaused(" + z + ")";
        Logger logger2 = logger;
        logger2.info(str, (Throwable) new Exception("NOT A REAL EXCEPTION - stacktrace:"));
        EngineError paused = this.player.setPaused(z);
        logger2.info(str + " => " + paused);
        return paused;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public int getPlayingBitrate() {
        Logger logger2 = logger;
        logger2.info("getPlayingBitrate()");
        int playingBitrate = this.player.getPlayingBitrate();
        logger2.info("getPlayingBitrate() => " + playingBitrate);
        return playingBitrate;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void setWifiAudioQualitySetting(QualitySetting qualitySetting) {
        String str = "setWifiAudioQualitySetting(" + qualitySetting.name() + ")";
        Logger logger2 = logger;
        logger2.info(str);
        this.player.setWifiAudioQualitySetting(qualitySetting);
        logger2.info(str + " returned");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void setOTAAudioQualitySetting(QualitySetting qualitySetting) {
        String str = "setOTAAudioQualitySetting(" + qualitySetting.name() + ")";
        Logger logger2 = logger;
        logger2.info(str);
        this.player.setOTAAudioQualitySetting(qualitySetting);
        logger2.info(str + " returned");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public QualitySetting getWifiAudioQualitySetting() {
        Logger logger2 = logger;
        logger2.info("getWifiAudioQualitySetting()");
        QualitySetting wifiAudioQualitySetting = this.player.getWifiAudioQualitySetting();
        logger2.info("getWifiAudioQualitySetting() => " + wifiAudioQualitySetting);
        return wifiAudioQualitySetting;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public QualitySetting getOTAAudioQualitySetting() {
        Logger logger2 = logger;
        logger2.info("getOTAAudioQualitySetting()");
        QualitySetting oTAAudioQualitySetting = this.player.getOTAAudioQualitySetting();
        logger2.info("getOTAAudioQualitySetting() => " + oTAAudioQualitySetting);
        return oTAAudioQualitySetting;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public AudioAttributes getCurrentAudioAttributes() {
        Logger logger2 = logger;
        logger2.info("getCurrentAudioAttributes()");
        AudioAttributes currentAudioAttributes = this.player.getCurrentAudioAttributes();
        logger2.info("getCurrentAudioAttributes() => " + currentAudioAttributes);
        return currentAudioAttributes;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public DeviceCapability getMaximumDeviceCapability() {
        Logger logger2 = logger;
        logger2.info("getMaximumDeviceCapability()");
        DeviceCapability maximumDeviceCapability = this.player.getMaximumDeviceCapability();
        logger2.info("getMaximumDeviceCapability() => " + maximumDeviceCapability);
        return maximumDeviceCapability;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public long getTrackDuration() {
        Logger logger2 = logger;
        logger2.info("getTrackDuration()");
        long trackDuration = this.player.getTrackDuration();
        logger2.info("getTrackDuration() => " + trackDuration);
        return trackDuration;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void reset() {
        Logger logger2 = logger;
        logger2.info("reset()");
        this.player.reset();
        logger2.info("reset() returned");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public Long getLiveStreamCurrentTime() {
        Logger logger2 = logger;
        logger2.info("getLiveStreamCurrentTime()");
        Long liveStreamCurrentTime = this.player.getLiveStreamCurrentTime();
        logger2.info("getLiveStreamCurrentTime() => " + liveStreamCurrentTime);
        return liveStreamCurrentTime;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public Long getLiveStreamStartTime() {
        Logger logger2 = logger;
        logger2.info("getLiveStreamStartTime()");
        Long liveStreamStartTime = this.player.getLiveStreamStartTime();
        logger2.info("getLiveStreamStartTime() => " + liveStreamStartTime);
        return liveStreamStartTime;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public PlayerLogLevel getLogLevel() {
        Logger logger2 = logger;
        logger2.info("getLogLevel()");
        PlayerLogLevel logLevel = this.player.getLogLevel();
        logger2.info("getLogLevel() => " + logLevel);
        return logLevel;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void setLogLevel(PlayerLogLevel playerLogLevel) {
        String str = "setLogLevel(" + playerLogLevel + ")";
        Logger logger2 = logger;
        logger2.info(str);
        this.player.setLogLevel(playerLogLevel);
        logger2.info(str + " returned");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void refreshRequestSigningCredentials() {
        Logger logger2 = logger;
        logger2.info("refreshRequestSigningCredentials()");
        this.player.refreshRequestSigningCredentials();
        logger2.info("refreshRequestSigningCredentials() returned");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void clearRequestSigningCredentials() {
        Logger logger2 = logger;
        logger2.info("clearRequestSigningCredentials()");
        this.player.clearRequestSigningCredentials();
        logger2.info("clearRequestSigningCredentials() returned");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void setTrackProgressNotificationPeriod(long j) {
        String str = "setTrackProgressNotificationPeriod(" + j + ")";
        Logger logger2 = logger;
        logger2.info(str);
        this.player.setTrackProgressNotificationPeriod(j);
        logger2.info(str + " returned");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public long getTrackProgressNotificationPeriod() {
        Logger logger2 = logger;
        logger2.info("getTrackProgressNotificationPeriod()");
        long trackProgressNotificationPeriod = this.player.getTrackProgressNotificationPeriod();
        logger2.info("getTrackProgressNotificationPeriod() => " + trackProgressNotificationPeriod);
        return trackProgressNotificationPeriod;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void setNetworkStallTimeout(long j) {
        String str = "setNetworkStallTimeout(" + j + ")";
        Logger logger2 = logger;
        logger2.info(str);
        this.player.setNetworkStallTimeout(j);
        logger2.info(str + " returned");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public boolean muted() {
        Logger logger2 = logger;
        logger2.info("muted()");
        boolean zMuted = this.player.muted();
        logger2.info("muted() => " + zMuted);
        return zMuted;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void setMuted(boolean z) {
        String str = "setMuted(" + z + ")";
        Logger logger2 = logger;
        logger2.info(str);
        this.player.setMuted(z);
        logger2.info(str + " returned");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void resumeAndTerminateRemoteStream(String str) {
        String str2 = "resumeAndTerminateRemoteStream(" + StringUtils.redactIfNeeded(str) + ")";
        Logger logger2 = logger;
        logger2.info(str2);
        this.player.resumeAndTerminateRemoteStream(str);
        logger2.info(str2 + " returned");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public boolean loudnessNormalizationEnabled() {
        Logger logger2 = logger;
        logger2.info("loudnessNormalizationEnabled()");
        boolean zLoudnessNormalizationEnabled = this.player.loudnessNormalizationEnabled();
        logger2.info("loudnessNormalizationEnabled() => " + zLoudnessNormalizationEnabled);
        return zLoudnessNormalizationEnabled;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public FrequencySettings getBassSettings() {
        Logger logger2 = logger;
        logger2.info("getBassSettings()");
        FrequencySettings bassSettings = this.player.getBassSettings();
        logger2.info("getBassSettings() => " + bassSettings);
        return bassSettings;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void updateBassSettings(short s) {
        String str = "updateBassSettings(" + ((int) s) + ")";
        Logger logger2 = logger;
        logger2.info(str);
        this.player.updateBassSettings(s);
        logger2.info(str + " returned");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public FrequencySettings getTrebleSettings() {
        Logger logger2 = logger;
        logger2.info("getTrebleSettings()");
        FrequencySettings trebleSettings = this.player.getTrebleSettings();
        logger2.info("getTrebleSettings() => " + trebleSettings);
        return trebleSettings;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void updateTrebleSettings(short s) {
        String str = "updateTrebleSettings(" + ((int) s) + ")";
        Logger logger2 = logger;
        logger2.info(str);
        this.player.updateTrebleSettings(s);
        logger2.info(str + " returned");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void setLoudnessNormalizationMode(boolean z) {
        String str = "setLoudnessNormalizationMode(" + z + ")";
        Logger logger2 = logger;
        logger2.info(str);
        this.player.setLoudnessNormalizationMode(z);
        logger2.info(str + " returned");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public ArrayList<EqualizerSettings> getEqualizerPresets() {
        Logger logger2 = logger;
        logger2.info("getEqualizerPresets()");
        ArrayList<EqualizerSettings> equalizerPresets = this.player.getEqualizerPresets();
        logger2.info("getEqualizerPresets() => " + equalizerPresets);
        return equalizerPresets;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void updateEqualizerSettings(ArrayList<EqualizerUpdate> arrayList) {
        String str = "updateEqualizerSettings(" + arrayList + ")";
        Logger logger2 = logger;
        logger2.info(str);
        this.player.updateEqualizerSettings(arrayList);
        logger2.info(str + " returned ");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public EqualizerSettings getEqualizerSettings() {
        Logger logger2 = logger;
        logger2.info("getEqualizerSettings()");
        EqualizerSettings equalizerSettings = this.player.getEqualizerSettings();
        logger2.info("getEqualizerSettings() => " + equalizerSettings);
        return equalizerSettings;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public boolean offlineMode() {
        Logger logger2 = logger;
        logger2.info("offlineMode()");
        boolean zOfflineMode = this.player.offlineMode();
        logger2.info("offlineMode() => " + zOfflineMode);
        return zOfflineMode;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void setOfflineMode(boolean z) {
        String str = "setOfflineMode(" + z + ")";
        Logger logger2 = logger;
        logger2.info(str);
        this.player.setOfflineMode(z);
        logger2.info(str + " returned ");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public boolean streamOnWifiOnly() {
        Logger logger2 = logger;
        logger2.info("streamOnWifiOnly()");
        boolean zStreamOnWifiOnly = this.player.streamOnWifiOnly();
        logger2.info("streamOnWifiOnly() => " + zStreamOnWifiOnly);
        return zStreamOnWifiOnly;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void setStreamOnWifiOnly(boolean z) {
        String str = "setStreamOnWifiOnly(" + z + ")";
        Logger logger2 = logger;
        logger2.info(str);
        this.player.setStreamOnWifiOnly(z);
        logger2.info(str + " returned ");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public DownloadCoordinator getDownloadCoordinator(DownloadCoordinatorListener downloadCoordinatorListener, DownloadCoordinatorConfig downloadCoordinatorConfig) {
        String str = "getDownloadCoordinator(" + downloadCoordinatorListener + ", " + downloadCoordinatorConfig + ")";
        Logger logger2 = logger;
        logger2.info(str);
        DownloadCoordinator downloadCoordinator = this.player.getDownloadCoordinator(downloadCoordinatorListener, downloadCoordinatorConfig);
        logger2.info(str + " => " + downloadCoordinator);
        return downloadCoordinator;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void setCustomerBenefits(EnumSet<CustomerBenefitFlags> enumSet) {
        String str = "setCustomerBenefits(" + enumSet.toString() + ")";
        Logger logger2 = logger;
        logger2.info(str);
        this.player.setCustomerBenefits(enumSet);
        logger2.info(str + " returned ");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void set24BitEnabled(boolean z) {
        String str = "set24BitEnabled(" + z + ")";
        Logger logger2 = logger;
        logger2.info(str);
        this.player.set24BitEnabled(z);
        logger2.info(str + " returned ");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public boolean is24BitEnabled() {
        Logger logger2 = logger;
        logger2.info("is24BitEnabled()");
        boolean zIs24BitEnabled = this.player.is24BitEnabled();
        logger2.info("is24BitEnabled() => " + zIs24BitEnabled);
        return zIs24BitEnabled;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void clearCache() {
        Logger logger2 = logger;
        logger2.info("clearCache()");
        this.player.clearCache();
        logger2.info("clearCache() returned");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void removeCachedTrack(String str) {
        String str2 = "removeCachedTrack(" + str + ")";
        Logger logger2 = logger;
        logger2.info(str2);
        this.player.removeCachedTrack(str);
        logger2.info(str2 + " returned");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void logCacheContents() {
        Logger logger2 = logger;
        logger2.info("logCacheContents()");
        this.player.logCacheContents();
        logger2.info("logCacheContents() returned");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void setThreeDMode(boolean z) {
        String str = "setThreeDMode(" + z + ")";
        Logger logger2 = logger;
        logger2.info(str);
        this.player.setThreeDMode(z);
        logger2.info(str + " returned ");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public boolean threeDMode() {
        Logger logger2 = logger;
        logger2.info("threeDMode()");
        boolean zThreeDMode = this.player.threeDMode();
        logger2.info("threeDMode() => " + zThreeDMode);
        return zThreeDMode;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void setTempo(double d) {
        String str = "setTempo(" + d + ")";
        Logger logger2 = logger;
        logger2.info(str);
        this.player.setTempo(d);
        logger2.info(str + " returned ");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public double getTempo() {
        Logger logger2 = logger;
        logger2.info("getTempo()");
        double tempo = this.player.getTempo();
        logger2.info("getTempo() => " + tempo);
        return tempo;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void setPrioritizeDownloadsPlayback(boolean z) {
        String str = "setPrioritizeDownloadsPlayback(" + z + ")";
        Logger logger2 = logger;
        logger2.info(str);
        this.player.setPrioritizeDownloadsPlayback(z);
        logger2.info(str + " returned ");
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public boolean prioritizeDownloadsPlayback() {
        Logger logger2 = logger;
        logger2.info("prioritizeDownloadsPlayback()");
        boolean zPrioritizeDownloadsPlayback = this.player.prioritizeDownloadsPlayback();
        logger2.info("prioritizeDownloadsPlayback() => " + zPrioritizeDownloadsPlayback);
        return zPrioritizeDownloadsPlayback;
    }

    @Override // com.amazon.digitalmusicplayback.Player
    public void nativeCrash() {
        logger.info("nativeCrash()");
        this.player.nativeCrash();
    }
}
