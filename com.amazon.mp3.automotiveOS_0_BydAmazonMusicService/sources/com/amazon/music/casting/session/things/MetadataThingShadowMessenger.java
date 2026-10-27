package com.amazon.music.casting.session.things;

import android.os.Handler;
import android.text.TextUtils;
import com.amazon.music.casting.session.CastingPlaybackCallback;
import com.amazon.music.casting.session.CastingSessionConfig;
import com.amazon.music.casting.session.things.MetadataThingShadow;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttManager;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttNewMessageCallback;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.UnsupportedEncodingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class MetadataThingShadowMessenger extends AbstractThingShadowMessenger {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) MetadataThingShadowMessenger.class);
    private static final int NUM_TRACKS = 1;
    private static final String PREFIX = "-metadata";
    protected AWSIotMqttNewMessageCallback messageAcceptedCallback;
    protected AWSIotMqttNewMessageCallback messageDocumentsCallback;
    private AWSIotMqttNewMessageCallback messageRejectedCallback;
    private NowPlayingThingShadowMessenger nowPlayingThingShadowMessenger;
    private CastingPlaybackCallback playbackCallback;

    public MetadataThingShadowMessenger(AWSIotMqttManager aWSIotMqttManager, String str, Handler handler, CastingPlaybackCallback castingPlaybackCallback, NowPlayingThingShadowMessenger nowPlayingThingShadowMessenger, CastingSessionConfig castingSessionConfig) {
        super(aWSIotMqttManager, null, str, handler, castingSessionConfig);
        this.messageAcceptedCallback = new AWSIotMqttNewMessageCallback() { // from class: com.amazon.music.casting.session.things.MetadataThingShadowMessenger.1
            @Override // com.amazonaws.mobileconnectors.iot.AWSIotMqttNewMessageCallback
            public synchronized void onMessageArrived(String str2, byte[] bArr) {
                try {
                    String str3 = new String(bArr, ParserUtil.UTF_8);
                    MetadataThingShadowMessenger.LOG.debug("onMessageArrived from topic: " + str2 + " dataDeserialized: " + str3);
                    final MetadataThingShadow metadataThingShadow = (MetadataThingShadow) new GsonBuilder().create().fromJson(str3, MetadataThingShadow.class);
                    MetadataThingShadowMessenger.LOG.debug("MetadataThingShadow received: " + metadataThingShadow);
                    MetadataThingShadowMessenger.this.backgroundHandler.post(new Runnable() { // from class: com.amazon.music.casting.session.things.MetadataThingShadowMessenger.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MetadataThingShadowMessenger.this.nowPlayingThingShadowMessenger.handleMetadataTSUpdate(metadataThingShadow, null);
                        }
                    });
                } catch (JsonSyntaxException | UnsupportedEncodingException e) {
                    MetadataThingShadowMessenger.LOG.error("Received exception while executing onMessageArrived ", e);
                }
            }
        };
        this.messageRejectedCallback = new AWSIotMqttNewMessageCallback() { // from class: com.amazon.music.casting.session.things.MetadataThingShadowMessenger.2
            @Override // com.amazonaws.mobileconnectors.iot.AWSIotMqttNewMessageCallback
            public void onMessageArrived(String str2, byte[] bArr) {
                try {
                    String str3 = new String(bArr, ParserUtil.UTF_8);
                    MetadataThingShadowMessenger.LOG.warn("Message was rejected from topic = " + str2 + " dataDeserialized = " + str3 + " this can happen on first time playback. Ignoring it.");
                } catch (UnsupportedEncodingException e) {
                    MetadataThingShadowMessenger.LOG.error("Received exception while executing onMessageArrived ", (Throwable) e);
                }
            }
        };
        this.messageDocumentsCallback = new AWSIotMqttNewMessageCallback() { // from class: com.amazon.music.casting.session.things.MetadataThingShadowMessenger.3
            @Override // com.amazonaws.mobileconnectors.iot.AWSIotMqttNewMessageCallback
            public void onMessageArrived(String str2, byte[] bArr) {
                try {
                    String str3 = new String(bArr, ParserUtil.UTF_8);
                    MetadataThingShadowMessenger.LOG.debug("Message arrived on messageDocumentsCallback! from topic = " + str2 + " data = " + str3);
                    final MetadataThingShadowDocument metadataThingShadowDocument = (MetadataThingShadowDocument) new Gson().fromJson(str3, MetadataThingShadowDocument.class);
                    MetadataThingShadowMessenger.LOG.debug("MetadataThingShadowDocument received: " + metadataThingShadowDocument);
                    MetadataThingShadowMessenger.this.backgroundHandler.post(new Runnable() { // from class: com.amazon.music.casting.session.things.MetadataThingShadowMessenger.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MetadataThingShadowMessenger.this.nowPlayingThingShadowMessenger.handleMetadataTSUpdate(metadataThingShadowDocument.getCurrent(), metadataThingShadowDocument.getPrevious());
                        }
                    });
                } catch (UnsupportedEncodingException e) {
                    MetadataThingShadowMessenger.LOG.error("Received exception while executing messageDocumentsCallback ", (Throwable) e);
                }
            }
        };
        this.playbackCallback = castingPlaybackCallback;
        this.nowPlayingThingShadowMessenger = nowPlayingThingShadowMessenger;
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

    @Override // com.amazon.music.casting.session.things.AbstractThingShadowMessenger
    /* JADX INFO: renamed from: getIdentifier */
    protected String getThingShadowName() {
        return this.targetId + PREFIX;
    }

    protected void handleMetadataTSUpdate(MetadataThingShadow metadataThingShadow, MetadataThingShadow metadataThingShadow2) {
        MetadataThingShadow.Track track;
        if (metadataThingShadow == null || this.playbackCallback == null) {
            LOG.error("handleMetadataTSUpdate failed, currentThingShadow = " + metadataThingShadow + " playbackCallback = " + this.playbackCallback);
            return;
        }
        if (!updateVersion(metadataThingShadow.getVersion())) {
            LOG.error("Cannot execute an older version of the message!");
            return;
        }
        MetadataThingShadow.Track[] trackArr = new MetadataThingShadow.Track[1];
        if (metadataThingShadow.getState() != null && metadataThingShadow.getState().getReportedNode() != null) {
            trackArr[0] = metadataThingShadow.getState().getReportedNode().getCurrentTrack();
        }
        MetadataThingShadow.Track[] trackArr2 = new MetadataThingShadow.Track[1];
        if (metadataThingShadow2 != null && metadataThingShadow2.getState() != null && metadataThingShadow2.getState().getReportedNode() != null) {
            trackArr2[0] = metadataThingShadow2.getState().getReportedNode().getCurrentTrack();
            int i = 0;
            for (int i2 = 0; i2 < 1; i2++) {
                MetadataThingShadow.Track track2 = trackArr[i2];
                if (track2 != null && (track = trackArr2[i2]) != null && TextUtils.equals(track2.trackId, track.trackId)) {
                    i++;
                }
            }
            if (i == 1) {
                LOG.debug("handleMetadataTsUpdate skipping update for same tracks");
                return;
            }
        }
        LOG.debug("handleMetadataTsUpdate extracted: \ntrack[0]: " + trackArr[0]);
        CastingPlaybackCallback castingPlaybackCallback = this.playbackCallback;
        if (castingPlaybackCallback != null) {
            castingPlaybackCallback.onTracksMetadataUpdated(trackArr);
        }
    }
}
