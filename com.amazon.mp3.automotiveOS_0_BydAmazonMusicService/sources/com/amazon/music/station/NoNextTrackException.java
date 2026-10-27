package com.amazon.music.station;

import com.amazon.hermes.UnhandledError;
import com.amazon.musicplayqueueservice.client.common.NoCleanLanguageVersionException;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.TimeoutError;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class NoNextTrackException extends Exception {
    private final Reason reason;

    public enum Reason {
        STATION_NOT_FOUND,
        END_OF_STATION,
        STATION_OUT_OF_TRACKS,
        PROCESSING_ERROR,
        NETWORK_ERROR,
        CLEAN_CONTENT_UNAVAILABLE
    }

    public NoNextTrackException(Exception exc) {
        super(exc);
        if ((exc instanceof NoConnectionError) || (exc instanceof NetworkError) || (exc instanceof TimeoutError)) {
            this.reason = Reason.NETWORK_ERROR;
            return;
        }
        if (exc instanceof NoCleanLanguageVersionException) {
            this.reason = Reason.CLEAN_CONTENT_UNAVAILABLE;
            return;
        }
        if (exc instanceof UnhandledError) {
            String message = exc.getMessage();
            if (StringUtils.contains(message, "StationOutOfTracksException")) {
                this.reason = Reason.STATION_OUT_OF_TRACKS;
                return;
            } else if (StringUtils.contains(message, "StationNotFoundException")) {
                this.reason = Reason.STATION_NOT_FOUND;
                return;
            } else {
                this.reason = Reason.PROCESSING_ERROR;
                return;
            }
        }
        this.reason = Reason.PROCESSING_ERROR;
    }

    public NoNextTrackException(Reason reason) {
        this.reason = reason;
    }

    public Reason getReason() {
        return this.reason;
    }
}
