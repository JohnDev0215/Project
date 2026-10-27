package com.amazon.music.chromecast;

import androidx.annotation.VisibleForTesting;
import com.amazon.music.chromecast.alert.ReceiverAlert;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.framework.CastSession;
import java.io.IOException;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class ChromecastNerdStats {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) ChromecastNerdStats.class);
    private final ChromecastController chromecastController;

    public interface ChromecastPlaybackAttributes {
        public static final ChromecastPlaybackAttributes UNKNOWN = new ChromecastPlaybackAttributes() { // from class: com.amazon.music.chromecast.ChromecastNerdStats.ChromecastPlaybackAttributes.1
            @Override // com.amazon.music.chromecast.ChromecastNerdStats.ChromecastPlaybackAttributes
            public int bitDepth() {
                return -1;
            }

            @Override // com.amazon.music.chromecast.ChromecastNerdStats.ChromecastPlaybackAttributes
            public int bitrate() {
                return -1;
            }

            @Override // com.amazon.music.chromecast.ChromecastNerdStats.ChromecastPlaybackAttributes
            public int sampleRate() {
                return -1;
            }

            @Override // com.amazon.music.chromecast.ChromecastNerdStats.ChromecastPlaybackAttributes
            public String playerAudioQuality() {
                return "Unknown".toUpperCase(Locale.ENGLISH);
            }
        };

        int bitDepth();

        int bitrate();

        String playerAudioQuality();

        int sampleRate();
    }

    ChromecastNerdStats(ChromecastController chromecastController) {
        this.chromecastController = chromecastController;
    }

    void initializeChannel(CastSession castSession) {
        this.chromecastController.setIsChromecastNerdStatsChannelActive(false);
        try {
            castSession.setMessageReceivedCallbacks("urn:x-cast:com.amazon.music.quality-stats", new MessageReceiver(this.chromecastController));
        } catch (IOException e) {
            LOG.error("Failed to initialize Chromecast channel urn:x-cast:com.amazon.music.quality-stats", (Throwable) e);
        }
    }

    @VisibleForTesting
    static class MessageReceiver implements Cast.MessageReceivedCallback {
        private final ChromecastController chromecastController;

        MessageReceiver(ChromecastController chromecastController) {
            this.chromecastController = chromecastController;
        }

        @Override // com.google.android.gms.cast.Cast.MessageReceivedCallback
        public void onMessageReceived(CastDevice castDevice, String str, String str2) {
            boolean z;
            ChromecastPlaybackAttributes chromecastPlaybackAttributes;
            ReceiverAlert<?> receiverAlert;
            ChromecastPlaybackAttributes chromecastPlaybackAttributesPlaybackAttributes;
            JSONObject jSONObject;
            ChromecastPlaybackAttributes chromecastPlaybackAttributesPlaybackAttributes2 = ChromecastPlaybackAttributes.UNKNOWN;
            boolean enableSpatialToggle = false;
            try {
                jSONObject = new JSONObject(str2);
                chromecastPlaybackAttributesPlaybackAttributes = ChromecastNerdStats.playbackAttributes("current", jSONObject);
            } catch (ReceiverException | JSONException unused) {
                z = false;
                chromecastPlaybackAttributes = chromecastPlaybackAttributesPlaybackAttributes2;
            }
            try {
                this.chromecastController.setIsChromecastNerdStatsChannelActive(true);
                chromecastPlaybackAttributesPlaybackAttributes2 = ChromecastNerdStats.playbackAttributes("bestAvailable", jSONObject);
                enableSpatialToggle = ChromecastNerdStats.getEnableSpatialToggle(jSONObject);
                receiverAlert = ChromecastNerdStats.getReceiverAlert(jSONObject);
            } catch (ReceiverException | JSONException unused2) {
                z = enableSpatialToggle;
                chromecastPlaybackAttributes = chromecastPlaybackAttributesPlaybackAttributes2;
                chromecastPlaybackAttributesPlaybackAttributes2 = chromecastPlaybackAttributesPlaybackAttributes;
                ChromecastNerdStats.LOG.error("Unable to read stats JSON message from Chromecast: {}", str2);
                receiverAlert = null;
                chromecastPlaybackAttributesPlaybackAttributes = chromecastPlaybackAttributesPlaybackAttributes2;
                chromecastPlaybackAttributesPlaybackAttributes2 = chromecastPlaybackAttributes;
                enableSpatialToggle = z;
            }
            this.chromecastController.updatePlaybackAttributes(chromecastPlaybackAttributesPlaybackAttributes, chromecastPlaybackAttributesPlaybackAttributes2, enableSpatialToggle, receiverAlert);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ChromecastPlaybackAttributes playbackAttributes(String str, JSONObject jSONObject) throws ReceiverException, JSONException {
        if (jSONObject.has("error")) {
            throw new ReceiverException();
        }
        if (jSONObject.has(str)) {
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            final String upperCase = jSONObject2.getString("playerAudioQuality").toUpperCase(Locale.ENGLISH);
            final int i = jSONObject2.getInt("bitrate");
            final int i2 = jSONObject2.has("bitDepth") ? jSONObject2.getInt("bitDepth") : -1;
            final int i3 = jSONObject2.getInt("sampleRate");
            return new ChromecastPlaybackAttributes() { // from class: com.amazon.music.chromecast.ChromecastNerdStats.1
                @Override // com.amazon.music.chromecast.ChromecastNerdStats.ChromecastPlaybackAttributes
                public String playerAudioQuality() {
                    return upperCase;
                }

                @Override // com.amazon.music.chromecast.ChromecastNerdStats.ChromecastPlaybackAttributes
                public int bitrate() {
                    return i;
                }

                @Override // com.amazon.music.chromecast.ChromecastNerdStats.ChromecastPlaybackAttributes
                public int bitDepth() {
                    return i2;
                }

                @Override // com.amazon.music.chromecast.ChromecastNerdStats.ChromecastPlaybackAttributes
                public int sampleRate() {
                    return i3;
                }
            };
        }
        return ChromecastPlaybackAttributes.UNKNOWN;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean getEnableSpatialToggle(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("enableSpatialToggle")) {
            return jSONObject.getBoolean("enableSpatialToggle");
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ReceiverAlert<?> getReceiverAlert(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("alert")) {
            return ChromecastAlert.receiverAlertFromJSON(jSONObject.getJSONObject("alert"));
        }
        return null;
    }

    private static class ReceiverException extends Exception {
        private ReceiverException() {
        }
    }
}
