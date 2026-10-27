package com.amazon.music.chromecast;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.music.chromecast.CustomData;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import java.io.IOException;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class ChromecastUpdate {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) ChromecastUpdate.class);
    private static final ResultCallback<Status> loggingCallback = new ResultCallback<Status>() { // from class: com.amazon.music.chromecast.ChromecastUpdate.1
        @Override // com.google.android.gms.common.api.ResultCallback
        public void onResult(@NonNull Status status) {
            if (status.isSuccess()) {
                return;
            }
            ChromecastUpdate.LOG.error("Failed to send message on Chromecast channel urn:x-cast:com.amazon.music.playback-update: [" + status.getStatusCode() + "] " + status.getStatusMessage());
        }
    };
    private CastSession castSession;

    void initializeChannel(CastSession castSession) {
        try {
            castSession.setMessageReceivedCallbacks("urn:x-cast:com.amazon.music.playback-update", new LoggingReceiver());
            this.castSession = castSession;
        } catch (IOException e) {
            LOG.error("Failed to initialize Chromecast channel urn:x-cast:com.amazon.music.playback-update", (Throwable) e);
        }
    }

    void closeChannel() {
        try {
            CastSession castSession = this.castSession;
            if (castSession != null) {
                this.castSession = null;
                castSession.removeMessageReceivedCallbacks("urn:x-cast:com.amazon.music.playback-update");
            }
        } catch (IOException e) {
            LOG.error("Failed to close Chromecast channel urn:x-cast:com.amazon.music.playback-update", (Throwable) e);
        }
    }

    boolean isInitialized() {
        return this.castSession != null;
    }

    void sendUpdate(@NonNull CustomData.Update update) {
        CastSession castSession = this.castSession;
        if (castSession == null) {
            LOG.info("Cannot send message on uninitialized Chromecast channel urn:x-cast:com.amazon.music.playback-update");
            return;
        }
        try {
            castSession.sendMessage("urn:x-cast:com.amazon.music.playback-update", update.toJSONObject().toString()).setResultCallback(loggingCallback);
        } catch (JSONException e) {
            LOG.error("Failed to send message on Chromecast channel urn:x-cast:com.amazon.music.playback-update", (Throwable) e);
        }
    }

    @VisibleForTesting(otherwise = 2)
    static class LoggingReceiver implements Cast.MessageReceivedCallback {
        LoggingReceiver() {
        }

        @Override // com.google.android.gms.cast.Cast.MessageReceivedCallback
        public void onMessageReceived(CastDevice castDevice, String str, String str2) {
            ChromecastUpdate.LOG.warn("Unexpected message received on Chromecast channel " + str + ": " + str2);
        }
    }
}
