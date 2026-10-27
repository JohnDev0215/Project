package com.amazon.mp3.environment;

import com.amazon.mp3.environment.url.EndpointsProvider;
import java.net.MalformedURLException;
import java.net.URL;

/* JADX INFO: loaded from: classes3.dex */
public final class MusicURL {
    public static URL getStationURL() {
        try {
            return EndpointsProvider.get().getStationEndpoint();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static URL getMuseUrl() {
        try {
            return EndpointsProvider.get().getMuseEndpoint();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static URL getMuseBrushUrl(boolean isCustomEndpoint) {
        try {
            if (isCustomEndpoint) {
                return Environment.MUSE_URL.getMuseBrushEndpoint();
            }
            return EndpointsProvider.get().getMuseBrushEndpoint();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static URL getNautilusUrl() {
        try {
            return EndpointsProvider.get().getNautilusEndpoint();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static URL getMusicPlayqueueServiceUrl() {
        try {
            return EndpointsProvider.get().getMusicPlayqueueServiceUrl();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static URL getMusicPlayqueueServiceV2Url() {
        try {
            return EndpointsProvider.get().getMusicPlayqueueServiceV2Url();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static URL getAlexaVoiceServiceUrl() {
        try {
            return EndpointsProvider.get().getAlexaVoiceServiceUrl();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
