package com.amazon.music.casting.session.things;

import android.os.Handler;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.casting.CastingMetricsLogger;
import com.amazon.music.casting.session.CastingPlaybackCallback;
import com.amazon.music.casting.session.CastingSessionConfig;
import com.amazon.music.casting.session.CastingSessionTimer;
import com.amazon.music.casting.session.things.NowPlayingThingShadow;
import com.amazon.music.casting.util.ClockUtil;
import com.amazon.music.casting.util.VolumeUtil;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazonaws.AmazonClientException;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttManager;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttNewMessageCallback;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttQos;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class NowPlayingThingShadowMessenger extends AbstractThingShadowMessenger {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) NowPlayingThingShadowMessenger.class);
    private static final String PREFIX = "-now-playing";
    private MetadataThingShadow cachedCurrentMetadataTS;
    private NowPlayingThingShadow cachedCurrentNowPlayingTS;
    protected CastingSessionTimer inactivityTimer;
    protected CastingSessionTimer.CastingSessionTimerListener inactivityTimerListener;
    protected AWSIotMqttNewMessageCallback messageAcceptedCallback;
    private AWSIotMqttNewMessageCallback messageDocumentsCallback;
    private AWSIotMqttNewMessageCallback messageRejectedCallback;
    private MetadataThingShadowMessenger metadataThingShadowMessenger;
    private CastingPlaybackCallback playbackCallback;
    protected NowPlayingThingShadow.State.ReportedNode reportedNode;

    public NowPlayingThingShadowMessenger(AWSIotMqttManager aWSIotMqttManager, String str, Handler handler, CastingPlaybackCallback castingPlaybackCallback, CastingSessionConfig castingSessionConfig, CastingSessionTimer.CastingSessionTimerListener castingSessionTimerListener) {
        super(aWSIotMqttManager, null, str, handler, castingSessionConfig);
        this.messageAcceptedCallback = new AWSIotMqttNewMessageCallback() { // from class: com.amazon.music.casting.session.things.NowPlayingThingShadowMessenger.1
            @Override // com.amazonaws.mobileconnectors.iot.AWSIotMqttNewMessageCallback
            public synchronized void onMessageArrived(String str2, byte[] bArr) {
                try {
                    String str3 = new String(bArr, ParserUtil.UTF_8);
                    NowPlayingThingShadowMessenger.LOG.debug("onMessageArrived from topic: " + str2 + " dataDeserialized: " + str3);
                    final NowPlayingThingShadow nowPlayingThingShadow = (NowPlayingThingShadow) new Gson().fromJson(str3, NowPlayingThingShadow.class);
                    NowPlayingThingShadowMessenger.LOG.debug("NowPlayingThingShadow received: " + nowPlayingThingShadow);
                    NowPlayingThingShadowMessenger.this.setNowPlayingThingShadow(nowPlayingThingShadow);
                    NowPlayingThingShadowMessenger.this.backgroundHandler.post(new Runnable() { // from class: com.amazon.music.casting.session.things.NowPlayingThingShadowMessenger.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            NowPlayingThingShadowMessenger.this.handleNowPlayingTSUpdate(nowPlayingThingShadow, null);
                            if (NowPlayingThingShadowMessenger.this.cachedCurrentMetadataTS != null) {
                                NowPlayingThingShadowMessenger nowPlayingThingShadowMessenger = NowPlayingThingShadowMessenger.this;
                                nowPlayingThingShadowMessenger.handleMetadataTSUpdate(nowPlayingThingShadowMessenger.cachedCurrentMetadataTS, null);
                            }
                        }
                    });
                } catch (JsonSyntaxException | UnsupportedEncodingException e) {
                    NowPlayingThingShadowMessenger.LOG.error("Received exception while executing onMessageArrived ", e);
                }
            }
        };
        this.messageRejectedCallback = new AWSIotMqttNewMessageCallback() { // from class: com.amazon.music.casting.session.things.NowPlayingThingShadowMessenger.2
            @Override // com.amazonaws.mobileconnectors.iot.AWSIotMqttNewMessageCallback
            public void onMessageArrived(String str2, byte[] bArr) {
                try {
                    String str3 = new String(bArr, ParserUtil.UTF_8);
                    NowPlayingThingShadowMessenger.LOG.warn("Message was rejected from topic = " + str2 + " dataDeserialized = " + str3 + " this can happen on first time playback. Ignoring it.");
                } catch (UnsupportedEncodingException e) {
                    NowPlayingThingShadowMessenger.LOG.error("Received exception while executing onMessageArrived ", (Throwable) e);
                }
            }
        };
        this.messageDocumentsCallback = new AWSIotMqttNewMessageCallback() { // from class: com.amazon.music.casting.session.things.NowPlayingThingShadowMessenger.3
            @Override // com.amazonaws.mobileconnectors.iot.AWSIotMqttNewMessageCallback
            public void onMessageArrived(String str2, byte[] bArr) {
                try {
                    String str3 = new String(bArr, ParserUtil.UTF_8);
                    NowPlayingThingShadowMessenger.LOG.debug("Message arrived on messageDocumentsCallback! from topic = " + str2 + " data = " + str3);
                    final NowPlayingThingShadowDocument nowPlayingThingShadowDocument = (NowPlayingThingShadowDocument) new Gson().fromJson(str3, NowPlayingThingShadowDocument.class);
                    NowPlayingThingShadowMessenger.LOG.debug("NowPlayingThingShadowDocument received: " + nowPlayingThingShadowDocument);
                    NowPlayingThingShadowMessenger.this.setNowPlayingThingShadow(nowPlayingThingShadowDocument.getCurrent());
                    NowPlayingThingShadowMessenger.this.backgroundHandler.post(new Runnable() { // from class: com.amazon.music.casting.session.things.NowPlayingThingShadowMessenger.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            NowPlayingThingShadowMessenger.this.handleNowPlayingTSUpdate(nowPlayingThingShadowDocument.getCurrent(), nowPlayingThingShadowDocument.getPrevious());
                            if (NowPlayingThingShadowMessenger.this.cachedCurrentMetadataTS != null) {
                                NowPlayingThingShadowMessenger nowPlayingThingShadowMessenger = NowPlayingThingShadowMessenger.this;
                                nowPlayingThingShadowMessenger.handleMetadataTSUpdate(nowPlayingThingShadowMessenger.cachedCurrentMetadataTS, null);
                            }
                        }
                    });
                } catch (UnsupportedEncodingException e) {
                    NowPlayingThingShadowMessenger.LOG.error("Received exception while executing messageDocumentsCallback ", (Throwable) e);
                }
            }
        };
        this.playbackCallback = castingPlaybackCallback;
        this.inactivityTimerListener = castingSessionTimerListener;
        this.metadataThingShadowMessenger = ThingShadowFactory.createMetadataThingShadowMessenger(aWSIotMqttManager, str, handler, castingPlaybackCallback, this, castingSessionConfig);
    }

    @Override // com.amazon.music.casting.session.things.AbstractThingShadowMessenger
    public void subscribe(boolean z) throws AmazonClientException {
        super.subscribe(z);
        this.metadataThingShadowMessenger.subscribe(z);
    }

    @Override // com.amazon.music.casting.session.things.AbstractThingShadowMessenger
    public void unsubscribe() throws AmazonClientException {
        super.unsubscribe();
        this.metadataThingShadowMessenger.unsubscribe();
    }

    @Override // com.amazon.music.casting.session.things.AbstractThingShadowMessenger
    protected AWSIotMqttNewMessageCallback getMessageAcceptedCallback() {
        return this.messageAcceptedCallback;
    }

    @Override // com.amazon.music.casting.session.things.AbstractThingShadowMessenger
    protected AWSIotMqttNewMessageCallback getMessageRejectedCallback() {
        return this.messageRejectedCallback;
    }

    @Override // com.amazon.music.casting.session.things.AbstractThingShadowMessenger
    protected AWSIotMqttNewMessageCallback getMessageDocumentsCallback() {
        return this.messageDocumentsCallback;
    }

    public void play() {
        this.backgroundHandler.post(new Runnable() { // from class: com.amazon.music.casting.session.things.NowPlayingThingShadowMessenger.4
            @Override // java.lang.Runnable
            public void run() {
                NowPlayingThingShadowMessenger.this.publish(new Gson().toJson(new NowPlayingThingShadow.Builder().withDesiredNode(new NowPlayingThingShadow.State.DesiredNode.Builder().withPlayState(NowPlayingThingShadow.State.PlayState.PLAY, Long.valueOf(ClockUtil.getCurrentTimeMillis())).build()).build(), NowPlayingThingShadow.class), ThingShadowFactory.getUpdateTopic(NowPlayingThingShadowMessenger.this.getThingShadowName()));
            }
        });
    }

    public void pause() {
        this.backgroundHandler.post(new Runnable() { // from class: com.amazon.music.casting.session.things.NowPlayingThingShadowMessenger.5
            @Override // java.lang.Runnable
            public void run() {
                NowPlayingThingShadowMessenger.this.publish(new Gson().toJson(new NowPlayingThingShadow.Builder().withDesiredNode(new NowPlayingThingShadow.State.DesiredNode.Builder().withPlayState(NowPlayingThingShadow.State.PlayState.PAUSE, Long.valueOf(ClockUtil.getCurrentTimeMillis())).build()).build(), NowPlayingThingShadow.class), ThingShadowFactory.getUpdateTopic(NowPlayingThingShadowMessenger.this.getThingShadowName()));
            }
        });
    }

    public void previous() {
        this.backgroundHandler.post(new Runnable() { // from class: com.amazon.music.casting.session.things.NowPlayingThingShadowMessenger.6
            @Override // java.lang.Runnable
            public void run() {
                NowPlayingThingShadowMessenger.this.publish(new Gson().toJson(new NowPlayingThingShadow.Builder().withDesiredNode(new NowPlayingThingShadow.State.DesiredNode.Builder().withTransport(NowPlayingThingShadow.State.Transport.PREVIOUS, Long.valueOf(ClockUtil.getCurrentTimeMillis())).build()).build(), NowPlayingThingShadow.class), ThingShadowFactory.getUpdateTopic(NowPlayingThingShadowMessenger.this.getThingShadowName()));
            }
        });
    }

    public void next() {
        this.backgroundHandler.post(new Runnable() { // from class: com.amazon.music.casting.session.things.NowPlayingThingShadowMessenger.7
            @Override // java.lang.Runnable
            public void run() {
                NowPlayingThingShadowMessenger.this.publish(new Gson().toJson(new NowPlayingThingShadow.Builder().withDesiredNode(new NowPlayingThingShadow.State.DesiredNode.Builder().withTransport(NowPlayingThingShadow.State.Transport.NEXT, Long.valueOf(ClockUtil.getCurrentTimeMillis())).build()).build(), NowPlayingThingShadow.class), ThingShadowFactory.getUpdateTopic(NowPlayingThingShadowMessenger.this.getThingShadowName()));
            }
        });
    }

    public void seek(final long j) {
        this.backgroundHandler.post(new Runnable() { // from class: com.amazon.music.casting.session.things.NowPlayingThingShadowMessenger.8
            @Override // java.lang.Runnable
            public void run() {
                NowPlayingThingShadow.State.DesiredNode.TrackInfo trackInfo = new NowPlayingThingShadow.State.DesiredNode.TrackInfo();
                trackInfo.positionInMs = Long.valueOf(j);
                NowPlayingThingShadowMessenger.this.publish(new Gson().toJson(new NowPlayingThingShadow.Builder().withDesiredNode(new NowPlayingThingShadow.State.DesiredNode.Builder().withTrackInformation(trackInfo, Long.valueOf(ClockUtil.getCurrentTimeMillis())).build()).build(), NowPlayingThingShadow.class), ThingShadowFactory.getUpdateTopic(NowPlayingThingShadowMessenger.this.getThingShadowName()));
            }
        });
    }

    public void setLoopMode(final NowPlayingThingShadow.State.LoopMode loopMode) {
        this.backgroundHandler.post(new Runnable() { // from class: com.amazon.music.casting.session.things.NowPlayingThingShadowMessenger.9
            @Override // java.lang.Runnable
            public void run() {
                NowPlayingThingShadowMessenger.this.publish(new Gson().toJson(new NowPlayingThingShadow.Builder().withDesiredNode(new NowPlayingThingShadow.State.DesiredNode.Builder().withLoopMode(loopMode, Long.valueOf(ClockUtil.getCurrentTimeMillis())).build()).build(), NowPlayingThingShadow.class), ThingShadowFactory.getUpdateTopic(NowPlayingThingShadowMessenger.this.getThingShadowName()));
            }
        });
    }

    public void setShuffleEnabled(final boolean z) {
        this.backgroundHandler.post(new Runnable() { // from class: com.amazon.music.casting.session.things.NowPlayingThingShadowMessenger.10
            @Override // java.lang.Runnable
            public void run() {
                NowPlayingThingShadowMessenger.this.publish(new Gson().toJson(new NowPlayingThingShadow.Builder().withDesiredNode(new NowPlayingThingShadow.State.DesiredNode.Builder().withIsShuffling(Boolean.valueOf(z), Long.valueOf(ClockUtil.getCurrentTimeMillis())).build()).build(), NowPlayingThingShadow.class), ThingShadowFactory.getUpdateTopic(NowPlayingThingShadowMessenger.this.getThingShadowName()));
            }
        });
    }

    public void setVolume(final float f) {
        this.backgroundHandler.post(new Runnable() { // from class: com.amazon.music.casting.session.things.NowPlayingThingShadowMessenger.11
            @Override // java.lang.Runnable
            public void run() {
                NowPlayingThingShadow.State.ReportedNode reportedNode = NowPlayingThingShadowMessenger.this.reportedNode;
                if (reportedNode == null || reportedNode.getVolume() == null) {
                    return;
                }
                NowPlayingThingShadow.State.ReportedNode.Volume volume = NowPlayingThingShadowMessenger.this.reportedNode.getVolume();
                NowPlayingThingShadow.State.DesiredNode.Volume volume2 = new NowPlayingThingShadow.State.DesiredNode.Volume();
                volume2.level = Float.valueOf(VolumeUtil.mapVolumeLevel(f, 0.0f, 100.0f, volume.min, volume.max, volume.stepSize));
                NowPlayingThingShadowMessenger.this.publish(new Gson().toJson(new NowPlayingThingShadow.Builder().withDesiredNode(new NowPlayingThingShadow.State.DesiredNode.Builder().withVolumeLevel(volume2, Long.valueOf(ClockUtil.getCurrentTimeMillis())).build()).build(), NowPlayingThingShadow.class), ThingShadowFactory.getUpdateTopic(NowPlayingThingShadowMessenger.this.getThingShadowName()));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void publish(String str, String str2) {
        try {
            LOG.debug("publish message: " + str);
            this.awsIotMqttManager.publishString(str, str2, AWSIotMqttQos.QOS0);
        } catch (AmazonClientException e) {
            LOG.error("publish failed", (Throwable) e);
        }
    }

    public float getVolume() {
        NowPlayingThingShadow.State.ReportedNode reportedNode = this.reportedNode;
        if (reportedNode == null || reportedNode.getVolume() == null) {
            return 0.0f;
        }
        NowPlayingThingShadow.State.ReportedNode.Volume volume = this.reportedNode.getVolume();
        return VolumeUtil.mapVolumeLevel(volume.level, volume.min, volume.max, 0.0f, 100.0f, 1.0f);
    }

    public void onVolumeUp() {
        this.backgroundHandler.post(new Runnable() { // from class: com.amazon.music.casting.session.things.NowPlayingThingShadowMessenger.12
            @Override // java.lang.Runnable
            public void run() {
                if (NowPlayingThingShadowMessenger.this.supportsFeature(NowPlayingThingShadow.State.Transport.VOLUME_DIRECTIONAL)) {
                    NowPlayingThingShadow.State.DesiredNode.Volume volume = new NowPlayingThingShadow.State.DesiredNode.Volume();
                    volume.changeDirection = NowPlayingThingShadow.State.VolumeDirection.UP;
                    volume.changeDirectionRequestId = VolumeUtil.generateDirectionRequestId();
                    NowPlayingThingShadowMessenger.this.publish(new Gson().toJson(new NowPlayingThingShadow.Builder().withDesiredNode(new NowPlayingThingShadow.State.DesiredNode.Builder().withVolumeLevel(volume, Long.valueOf(ClockUtil.getCurrentTimeMillis())).build()).build(), NowPlayingThingShadow.class), ThingShadowFactory.getUpdateTopic(NowPlayingThingShadowMessenger.this.getThingShadowName()));
                }
            }
        });
    }

    public void onVolumeDown() {
        this.backgroundHandler.post(new Runnable() { // from class: com.amazon.music.casting.session.things.NowPlayingThingShadowMessenger.13
            @Override // java.lang.Runnable
            public void run() {
                if (NowPlayingThingShadowMessenger.this.supportsFeature(NowPlayingThingShadow.State.Transport.VOLUME_DIRECTIONAL)) {
                    NowPlayingThingShadow.State.DesiredNode.Volume volume = new NowPlayingThingShadow.State.DesiredNode.Volume();
                    volume.changeDirection = NowPlayingThingShadow.State.VolumeDirection.DOWN;
                    volume.changeDirectionRequestId = VolumeUtil.generateDirectionRequestId();
                    NowPlayingThingShadowMessenger.this.publish(new Gson().toJson(new NowPlayingThingShadow.Builder().withDesiredNode(new NowPlayingThingShadow.State.DesiredNode.Builder().withVolumeLevel(volume, Long.valueOf(ClockUtil.getCurrentTimeMillis())).build()).build(), NowPlayingThingShadow.class), ThingShadowFactory.getUpdateTopic(NowPlayingThingShadowMessenger.this.getThingShadowName()));
                }
            }
        });
    }

    public NowPlayingThingShadow.State.ReportedNode getReportedNode() {
        return this.reportedNode;
    }

    public synchronized void resetDisconnectTimer() {
        LOG.debug("resetDisconnectTimer()");
        CastingSessionTimer castingSessionTimer = this.inactivityTimer;
        if (castingSessionTimer != null) {
            castingSessionTimer.stop();
        }
    }

    @Override // com.amazon.music.casting.session.things.AbstractThingShadowMessenger
    /* JADX INFO: renamed from: getIdentifier */
    protected String getThingShadowName() {
        return this.targetId + PREFIX;
    }

    protected synchronized void handleMetadataTSUpdate(@NonNull MetadataThingShadow metadataThingShadow, @Nullable MetadataThingShadow metadataThingShadow2) {
        if (isSameTrackId(this.cachedCurrentNowPlayingTS, metadataThingShadow)) {
            setMetadataThingShadow(null);
            this.metadataThingShadowMessenger.handleMetadataTSUpdate(metadataThingShadow, metadataThingShadow2);
        } else {
            setMetadataThingShadow(metadataThingShadow);
        }
    }

    protected synchronized void setNowPlayingThingShadow(NowPlayingThingShadow nowPlayingThingShadow) {
        this.cachedCurrentNowPlayingTS = nowPlayingThingShadow;
    }

    protected synchronized void setMetadataThingShadow(MetadataThingShadow metadataThingShadow) {
        this.cachedCurrentMetadataTS = metadataThingShadow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean supportsFeature(NowPlayingThingShadow.State.Transport transport) {
        NowPlayingThingShadow.State.Transport[] availableFeatures;
        NowPlayingThingShadow.State.ReportedNode reportedNode = this.reportedNode;
        if (reportedNode != null && (availableFeatures = reportedNode.getAvailableFeatures()) != null) {
            for (NowPlayingThingShadow.State.Transport transport2 : availableFeatures) {
                if (transport2.equals(transport)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleNowPlayingTSUpdate(NowPlayingThingShadow nowPlayingThingShadow, NowPlayingThingShadow nowPlayingThingShadow2) {
        if (this.playbackCallback == null) {
            LOG.error("handleNowPlayingTSUpdate failed, playbackCallback = " + this.playbackCallback);
            return;
        }
        if (!updateVersion(nowPlayingThingShadow.getVersion())) {
            LOG.error("Cannot execute an older version of the message!");
            return;
        }
        if (nowPlayingThingShadow.getState() == null || nowPlayingThingShadow.getState().getReportedNode() == null) {
            LOG.error("handleNowPlayingTSUpdate failed, reportedNode = " + this.reportedNode);
            return;
        }
        NowPlayingThingShadow.State.ReportedNode reportedNode = this.reportedNode;
        NowPlayingThingShadow.State.ReportedNode reportedNode2 = nowPlayingThingShadow.getState().getReportedNode();
        this.reportedNode = reportedNode2;
        logLatencyMetric(reportedNode2);
        LOG.debug("handleNowPlayingTSUpdate extracted: \nreportedNode = " + this.reportedNode + "\npreviousReportedNode = " + reportedNode);
        updatePlayState(this.reportedNode, reportedNode);
        updateShuffle(this.reportedNode, reportedNode);
        updateLoopMode(this.reportedNode, reportedNode);
        updateAvailableFeatures(this.reportedNode, reportedNode);
        updateAvailableLoopModes(this.reportedNode, reportedNode);
        updateRating(this.reportedNode, reportedNode);
        updateVolume(this.reportedNode, reportedNode);
    }

    private void updatePlayState(NowPlayingThingShadow.State.ReportedNode reportedNode, NowPlayingThingShadow.State.ReportedNode reportedNode2) {
        NowPlayingThingShadow.State.PlayState playState = reportedNode.getPlayState();
        if (playState == null) {
            return;
        }
        if (shouldUpdatePlayState(reportedNode, reportedNode2)) {
            int i = AnonymousClass14.$SwitchMap$com$amazon$music$casting$session$things$NowPlayingThingShadow$State$PlayState[playState.ordinal()];
            if (i == 1) {
                this.playbackCallback.onPlay();
            } else if (i == 2) {
                this.playbackCallback.onPause();
            } else if (i == 3) {
                this.playbackCallback.onStop();
            }
        }
        updateDisconnectTimer(playState);
    }

    /* JADX INFO: renamed from: com.amazon.music.casting.session.things.NowPlayingThingShadowMessenger$14, reason: invalid class name */
    static /* synthetic */ class AnonymousClass14 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$casting$session$things$NowPlayingThingShadow$State$PlayState;

        static {
            int[] iArr = new int[NowPlayingThingShadow.State.PlayState.values().length];
            $SwitchMap$com$amazon$music$casting$session$things$NowPlayingThingShadow$State$PlayState = iArr;
            try {
                iArr[NowPlayingThingShadow.State.PlayState.PLAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$casting$session$things$NowPlayingThingShadow$State$PlayState[NowPlayingThingShadow.State.PlayState.PAUSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$casting$session$things$NowPlayingThingShadow$State$PlayState[NowPlayingThingShadow.State.PlayState.STOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private boolean shouldUpdatePlayState(NowPlayingThingShadow.State.ReportedNode reportedNode, NowPlayingThingShadow.State.ReportedNode reportedNode2) {
        NowPlayingThingShadow.State.PlayState playState = reportedNode.getPlayState();
        NowPlayingThingShadow.State.ReportedNode.TrackInfo trackInformation = reportedNode.getTrackInformation();
        if (reportedNode2 == null || !playState.equals(reportedNode2.getPlayState())) {
            return true;
        }
        return (trackInformation == null || reportedNode2.getTrackInformation() == null || trackInformation.positionInMsTimeStamp == null || reportedNode2.getTrackInformation().positionInMsTimeStamp == null || trackInformation.positionInMsTimeStamp.equals(reportedNode2.getTrackInformation().positionInMsTimeStamp)) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x00cb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void updateDisconnectTimer(com.amazon.music.casting.session.things.NowPlayingThingShadow.State.PlayState r15) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.casting.session.things.NowPlayingThingShadowMessenger.updateDisconnectTimer(com.amazon.music.casting.session.things.NowPlayingThingShadow$State$PlayState):void");
    }

    private void updateShuffle(NowPlayingThingShadow.State.ReportedNode reportedNode, NowPlayingThingShadow.State.ReportedNode reportedNode2) {
        Boolean isShuffling = reportedNode.getIsShuffling();
        if (isShuffling == null) {
            return;
        }
        if (reportedNode2 == null || isShuffling != reportedNode2.getIsShuffling()) {
            this.playbackCallback.onSetShuffled(isShuffling.booleanValue());
        }
    }

    private void updateLoopMode(NowPlayingThingShadow.State.ReportedNode reportedNode, NowPlayingThingShadow.State.ReportedNode reportedNode2) {
        NowPlayingThingShadow.State.LoopMode activeLoopMode = reportedNode.getActiveLoopMode();
        if (activeLoopMode == null) {
            return;
        }
        if (reportedNode2 == null || activeLoopMode != reportedNode2.getActiveLoopMode()) {
            this.playbackCallback.onSetLoopMode(activeLoopMode);
        }
    }

    private void updateAvailableFeatures(NowPlayingThingShadow.State.ReportedNode reportedNode, NowPlayingThingShadow.State.ReportedNode reportedNode2) {
        NowPlayingThingShadow.State.Transport[] availableFeatures = reportedNode.getAvailableFeatures();
        if (availableFeatures == null || availableFeatures.length == 0) {
            return;
        }
        if (reportedNode2 == null || !Arrays.equals(availableFeatures, reportedNode2.getAvailableFeatures())) {
            this.playbackCallback.onSetAvailableFeatures(availableFeatures);
        }
    }

    private void updateAvailableLoopModes(NowPlayingThingShadow.State.ReportedNode reportedNode, NowPlayingThingShadow.State.ReportedNode reportedNode2) {
        NowPlayingThingShadow.State.LoopMode[] availableLoopModes = reportedNode.getAvailableLoopModes();
        if (availableLoopModes == null) {
            return;
        }
        if (reportedNode2 == null || !Arrays.equals(availableLoopModes, reportedNode2.getAvailableLoopModes())) {
            this.playbackCallback.onSetAvailableLoopModes(availableLoopModes);
        }
    }

    private void updateRating(NowPlayingThingShadow.State.ReportedNode reportedNode, NowPlayingThingShadow.State.ReportedNode reportedNode2) {
        NowPlayingThingShadow.State.ReportedNode.TrackInfo trackInformation = reportedNode.getTrackInformation();
        if (trackInformation == null || trackInformation.trackRating == null) {
            return;
        }
        if (reportedNode2 == null || reportedNode2.getTrackInformation() == null || !trackInformation.trackRating.equals(reportedNode2.getTrackInformation().trackRating)) {
            this.playbackCallback.onSetRating(trackInformation.trackRating);
        }
    }

    private void updateVolume(NowPlayingThingShadow.State.ReportedNode reportedNode, NowPlayingThingShadow.State.ReportedNode reportedNode2) {
        NowPlayingThingShadow.State.ReportedNode.Volume volume = reportedNode.getVolume();
        if (volume == null) {
            return;
        }
        if (reportedNode2 == null || reportedNode2.getVolume() == null || volume.level != reportedNode2.getVolume().level) {
            this.playbackCallback.onVolumeUpdated(volume);
        }
    }

    private boolean isSameTrackId(NowPlayingThingShadow nowPlayingThingShadow, MetadataThingShadow metadataThingShadow) {
        try {
            return TextUtils.equals(nowPlayingThingShadow.getState().getReportedNode().getTrackInformation().trackId, metadataThingShadow.getState().getReportedNode().getCurrentTrack().trackId);
        } catch (NullPointerException unused) {
            return false;
        }
    }

    private NowPlayingThingShadow.State.ReportedNode.TrackInfo getTrackInfo(NowPlayingThingShadow.State.ReportedNode reportedNode) {
        NowPlayingThingShadow.State.ReportedNode.TrackInfo trackInformation;
        if (reportedNode == null || (trackInformation = reportedNode.getTrackInformation()) == null || trackInformation.positionInMs == null || trackInformation.positionInMsTimeStamp == null || trackInformation.maximumPositionInMs == null) {
            return null;
        }
        return trackInformation;
    }

    private void logLatencyMetric(NowPlayingThingShadow.State.ReportedNode reportedNode) {
        if (reportedNode == null) {
            return;
        }
        try {
            String str = reportedNode.getTrackInformation().trackId;
            long jLongValue = reportedNode.getOptimisticUpdateTimeStampInMs().longValue();
            if (TextUtils.isEmpty(str) || jLongValue <= 0) {
                return;
            }
            long currentTimeMillis = ClockUtil.getCurrentTimeMillis() - jLongValue;
            CastingMetricsLogger.sendLatencyMetric(currentTimeMillis);
            LOG.debug("NowPlayingTS update latency: " + currentTimeMillis);
        } catch (NullPointerException unused) {
        }
    }
}
