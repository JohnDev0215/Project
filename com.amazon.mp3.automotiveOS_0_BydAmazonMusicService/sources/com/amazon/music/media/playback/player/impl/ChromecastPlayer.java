package com.amazon.music.media.playback.player.impl;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.digitalmusicplayback.PlayerAudioQuality;
import com.amazon.music.chromecast.ChromecastController;
import com.amazon.music.chromecast.ChromecastNerdStats;
import com.amazon.music.chromecast.CustomData;
import com.amazon.music.media.playback.PlaybackAttributes;
import com.amazon.music.media.playback.PlaybackAudioQuality;
import com.amazon.music.media.playback.PlayerCapabilities;
import com.amazon.music.media.playback.config.BitRateSelection;
import com.amazon.music.media.playback.config.PlaybackConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class ChromecastPlayer extends MultiplexPlayer {
    private BitRateSelection bitRateSelectionForWifi;
    private final ChromecastController controller;
    private PlaybackAttributes currentPlaybackAttributes;
    private PlaybackAttributes dynamicBestAvailablePlaybackAttributes;
    private boolean loudnessNormalizationEnabled;
    private boolean spatialEnabled;
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) ChromecastPlayer.class);
    private static final PlayerCapabilities CHROMECAST_RECEIVER_APP_CAPABILITIES = new PlayerCapabilities(true, 16, 48000);

    public ChromecastPlayer(@NonNull Context context) {
        super(context, ChromecastMediaItemPlayer.FACTORY);
        PlaybackAttributes playbackAttributes = PlaybackAttributes.UNKNOWN;
        this.currentPlaybackAttributes = playbackAttributes;
        this.dynamicBestAvailablePlaybackAttributes = playbackAttributes;
        setHonorAudioFocus(false);
        ChromecastController chromecastController = ChromecastController.getInstance();
        this.controller = chromecastController;
        chromecastController.setOnPlaybackAttributesChangeListener(makeOnPlaybackAttributesChangeListener(this));
    }

    @VisibleForTesting(otherwise = 2)
    static ChromecastController.OnPlaybackAttributesChangeListener makeOnPlaybackAttributesChangeListener(ChromecastPlayer chromecastPlayer) {
        return new ChromecastController.OnPlaybackAttributesChangeListener() { // from class: com.amazon.music.media.playback.player.impl.ChromecastPlayer.1
            @Override // com.amazon.music.chromecast.ChromecastController.OnPlaybackAttributesChangeListener
            public void onPlaybackAttributesChange(ChromecastNerdStats.ChromecastPlaybackAttributes chromecastPlaybackAttributes, ChromecastNerdStats.ChromecastPlaybackAttributes chromecastPlaybackAttributes2) {
                ChromecastPlayer.this.currentPlaybackAttributes = ChromecastPlayer.convertChromecastPlaybackAttributes(chromecastPlaybackAttributes);
                ChromecastPlayer.this.dynamicBestAvailablePlaybackAttributes = ChromecastPlayer.convertChromecastPlaybackAttributes(chromecastPlaybackAttributes2);
                ChromecastPlayer.this.notifyPlaybackAttributesChanged();
            }
        };
    }

    @VisibleForTesting(otherwise = 2)
    static PlaybackAttributes convertChromecastPlaybackAttributes(@NonNull ChromecastNerdStats.ChromecastPlaybackAttributes chromecastPlaybackAttributes) {
        if (chromecastPlaybackAttributes == ChromecastNerdStats.ChromecastPlaybackAttributes.UNKNOWN) {
            return PlaybackAttributes.UNKNOWN;
        }
        try {
            return new PlaybackAttributes(PlaybackAudioQuality.fromPlayerAudioQuality(PlayerAudioQuality.valueOf(chromecastPlaybackAttributes.playerAudioQuality())), chromecastPlaybackAttributes.bitrate(), chromecastPlaybackAttributes.bitDepth(), chromecastPlaybackAttributes.sampleRate());
        } catch (IllegalArgumentException unused) {
            LOG.error("Unable to interpret player audio quality from Chromecast: " + chromecastPlaybackAttributes.playerAudioQuality());
            return PlaybackAttributes.UNKNOWN;
        }
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer, com.amazon.music.media.playback.player.Player
    @NonNull
    public PlaybackAttributes getCurrentPlaybackAttributes() {
        return this.currentPlaybackAttributes;
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer, com.amazon.music.media.playback.player.Player
    @NonNull
    public PlaybackAttributes getDynamicBestAvailablePlaybackAttributes() {
        return this.dynamicBestAvailablePlaybackAttributes;
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer, com.amazon.music.media.playback.player.Player
    @NonNull
    public PlayerCapabilities getPlayerCapabilities() {
        return CHROMECAST_RECEIVER_APP_CAPABILITIES;
    }

    @Override // com.amazon.music.media.playback.player.impl.MultiplexPlayer, com.amazon.music.media.playback.player.AbstractPlayer
    protected void onConfigurationChanged() {
        super.onConfigurationChanged();
        LOG.info("onConfigurationChanged()");
        innerOnConfigurationChanged(this.controller, getPlaybackConfig());
    }

    @VisibleForTesting
    void innerOnConfigurationChanged(ChromecastController chromecastController, PlaybackConfig playbackConfig) {
        boolean z;
        BitRateSelection bitRateSelectionForWifi = playbackConfig.getBitRateSelectionForWifi();
        boolean z2 = false;
        if (this.bitRateSelectionForWifi != bitRateSelectionForWifi) {
            this.bitRateSelectionForWifi = bitRateSelectionForWifi;
            z = true;
        } else {
            z = false;
        }
        boolean zIsSpatialEnabled = playbackConfig.isSpatialEnabled();
        if (this.spatialEnabled != zIsSpatialEnabled) {
            this.spatialEnabled = zIsSpatialEnabled;
            z = true;
        }
        boolean zIsLoudnessNormalizationEnabled = playbackConfig.isLoudnessNormalizationEnabled();
        if (this.loudnessNormalizationEnabled != zIsLoudnessNormalizationEnabled) {
            this.loudnessNormalizationEnabled = zIsLoudnessNormalizationEnabled;
            z2 = true;
        }
        if (z || z2) {
            chromecastController.handlePlaybackUpdate(CustomData.Update.builder().withEnabledTrackTypes(z ? ChromecastMediaItemPlayer.enabledTrackTypes(this.bitRateSelectionForWifi, this.spatialEnabled) : null).withNormalizeLoudness(Boolean.valueOf(this.loudnessNormalizationEnabled)).build());
        }
    }

    @Override // com.amazon.music.media.playback.player.impl.MultiplexPlayer, com.amazon.music.media.playback.player.AbstractPlayer
    protected void onConnect() {
        super.onConnect();
        innerOnConnect(this.controller, getPlaybackConfig());
    }

    @VisibleForTesting
    void innerOnConnect(ChromecastController chromecastController, PlaybackConfig playbackConfig) {
        this.bitRateSelectionForWifi = playbackConfig.getBitRateSelectionForWifi();
        this.spatialEnabled = playbackConfig.isSpatialEnabled();
        boolean zIsLoudnessNormalizationEnabled = playbackConfig.isLoudnessNormalizationEnabled();
        this.loudnessNormalizationEnabled = zIsLoudnessNormalizationEnabled;
        chromecastController.setNormalizeLoudness(zIsLoudnessNormalizationEnabled);
    }
}
