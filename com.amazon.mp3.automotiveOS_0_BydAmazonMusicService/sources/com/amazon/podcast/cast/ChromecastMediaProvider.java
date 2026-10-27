package com.amazon.podcast.cast;

import android.net.Uri;
import com.amazon.music.chromecast.ChromecastController;
import com.amazon.music.chromecast.CustomData;
import com.amazon.music.chromecast.PlaybackListener;
import com.amazon.music.chromecast.SimpleListener;
import com.amazon.music.chromecast.StreamType;
import com.amazon.music.chromecast.TrackInformation;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.marketplace.MarketplaceResolver;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.OAuthInfo;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.media.playback.Media;
import com.amazon.podcast.media.playback.Playback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public class ChromecastMediaProvider {
    private static String accessToken;
    private static final Logger logger = LoggerFactory.getLogger(ChromecastMediaProvider.class.getSimpleName());
    private static boolean trackLoadedOnChromecast = false;
    private static boolean playbackStartedOnChromecast = false;
    private static boolean playbackOnChromecastPaused = false;

    public static void loadTrackOnChromecast() {
        TrackInformation trackInformation;
        if (Casting.isCasting() && (trackInformation = getTrackInformation()) != null) {
            if (accessToken == null) {
                fetchAuthToken();
                return;
            }
            long bufferedPosition = Podcast.getPlayback().getBufferedPosition(Podcast.getPlayback().getMedia().mediaId());
            CustomData customDataBuildCustomData = buildCustomData();
            accessToken = null;
            setPlaybackOnChromecastPaused(true);
            ChromecastController.getInstance().loadTrack(trackInformation, customDataBuildCustomData, false, bufferedPosition, new PlaybackListener() { // from class: com.amazon.podcast.cast.ChromecastMediaProvider.1
                @Override // com.amazon.music.chromecast.PlaybackListener
                public void onPlaybackComplete() {
                }

                @Override // com.amazon.music.chromecast.PlaybackListener
                public void onRemotePause() {
                }

                @Override // com.amazon.music.chromecast.PlaybackListener
                public void onLoadSuccess() {
                    Playback playback = Podcast.getPlayback();
                    if (playback != null && playback.getPlaybackDuration() <= 0) {
                        playback.setPlaybackDuration(ChromecastController.getInstance().getDuration());
                    }
                    boolean unused = ChromecastMediaProvider.trackLoadedOnChromecast = true;
                    int playbackState = Podcast.getPlayback().getPlaybackState();
                    if (playbackState == 6 || playbackState == 3 || playbackState == 5 || playbackState == 4) {
                        ChromecastMediaProvider.startPlaybackOnChromecast();
                    } else {
                        ChromecastMediaProvider.pausePlaybackOnChromecast();
                    }
                }

                @Override // com.amazon.music.chromecast.PlaybackListener
                public void onLoadError(int i) {
                    ChromecastMediaProvider.logger.debug("Failed to load podcast episode metadata on chromecast with error code: " + i);
                }

                @Override // com.amazon.music.chromecast.PlaybackListener
                public void onRemotePlay() {
                    boolean unused = ChromecastMediaProvider.playbackStartedOnChromecast = true;
                }
            });
        }
    }

    private static CustomData buildCustomData() {
        String appVersion = Podcast.getBuildInfoProvider().getAppVersion();
        String strCustomerId = Podcast.getUserInfoProvider().customerId();
        String deviceId = Podcast.getDeviceInfoProvider().getDeviceIdentity().getDeviceId();
        String deviceTypeId = Podcast.getDeviceInfoProvider().getDeviceIdentity().getDeviceTypeId();
        String strMarketplaceId = Podcast.getUserInfoProvider().marketplaceId();
        return CustomData.builder().withItemId(null).withToken(accessToken).withClientId("Android").withClientAppVersion(appVersion).withCustomerId(strCustomerId).withDeviceId(deviceId).withDeviceTypeId(deviceTypeId).withMarketplaceRegion(marketplaceRegion(strMarketplaceId)).withChromecastDeviceId(ChromecastController.getInstance().getChromecastDeviceId()).build();
    }

    public static void startPlaybackOnChromecast() {
        if (Casting.isCasting()) {
            ChromecastController.getInstance().play(new SimpleListener() { // from class: com.amazon.podcast.cast.ChromecastMediaProvider.2
                @Override // com.amazon.music.chromecast.SimpleListener
                public void onSuccess() {
                    ChromecastMediaProvider.setPlaybackOnChromecastPaused(true);
                }

                @Override // com.amazon.music.chromecast.SimpleListener
                public void onError(int i) {
                    ChromecastMediaProvider.logger.debug("Failed to start Playback on Chromecast with errorcode: " + i);
                }
            });
        }
    }

    public static void pausePlaybackOnChromecast() {
        if (Casting.isCasting()) {
            ChromecastController.getInstance().pause(new SimpleListener() { // from class: com.amazon.podcast.cast.ChromecastMediaProvider.3
                @Override // com.amazon.music.chromecast.SimpleListener
                public void onSuccess() {
                }

                @Override // com.amazon.music.chromecast.SimpleListener
                public void onError(int i) {
                    ChromecastMediaProvider.logger.debug("Failed to pause Playback on Chromecast with errorcode: " + i);
                }
            });
        }
    }

    public static void seekToOnChromecast(long position) {
        if (Casting.isCasting()) {
            ChromecastController.getInstance().seek(position, new SimpleListener() { // from class: com.amazon.podcast.cast.ChromecastMediaProvider.4
                @Override // com.amazon.music.chromecast.SimpleListener
                public void onSuccess() {
                }

                @Override // com.amazon.music.chromecast.SimpleListener
                public void onError(int i) {
                    ChromecastMediaProvider.logger.debug("Failed to seek Playback on Chromecast with errorcode: " + i);
                }
            });
        }
    }

    public static void stopOnChromeCast() {
        if (Casting.isCasting()) {
            ChromecastController.getInstance().stop(new SimpleListener() { // from class: com.amazon.podcast.cast.ChromecastMediaProvider.5
                @Override // com.amazon.music.chromecast.SimpleListener
                public void onSuccess() {
                }

                @Override // com.amazon.music.chromecast.SimpleListener
                public void onError(int i) {
                    ChromecastMediaProvider.logger.debug("Failed to stop Playback on Chromecast with errorcode: " + i);
                }
            });
        }
    }

    private static void fetchAuthToken() {
        Handlers.INSTANCE.getBackground().post(new Runnable() { // from class: com.amazon.podcast.cast.ChromecastMediaProvider.6
            @Override // java.lang.Runnable
            public void run() {
                if (ChromecastMediaProvider.retrieveOAuthToken()) {
                    Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.cast.ChromecastMediaProvider.6.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ChromecastMediaProvider.loadTrackOnChromecast();
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean retrieveOAuthToken() {
        Outcome<OAuthInfo> oAuthInfoBlocking = Podcast.getAuthenticationProvider().getOAuthInfoBlocking();
        if (!(oAuthInfoBlocking instanceof Outcome.Success)) {
            return false;
        }
        accessToken = ((OAuthInfo) ((Outcome.Success) oAuthInfoBlocking).getValue()).getAccessToken();
        return true;
    }

    private static TrackInformation getTrackInformation() {
        Media media = Podcast.getPlayback().getMedia();
        if (media == null || media.getMediaUri() == null) {
            return null;
        }
        long jLongValue = media.getMediaMetadataElement().getDurationSeconds().longValue();
        String title = media.getMediaMetadataElement().getTitle();
        String subtitle = media.getMediaMetadataElement().getSubtitle();
        Uri uri = Uri.parse(media.getMediaMetadataElement().getArtwork());
        return new TrackInformation(title, "", subtitle, jLongValue, media.getMediaUri(), uri != null ? uri.toString() : null, StreamType.valueOf(Podcast.getPlayback().getStreamType()));
    }

    public static boolean isTrackLoadedOnChromecast() {
        return trackLoadedOnChromecast;
    }

    public static void setTrackLoadedOnChromecast(boolean trackLoaded) {
        trackLoadedOnChromecast = trackLoaded;
    }

    public static boolean isPlaybackStartedOnChromecast() {
        return playbackStartedOnChromecast;
    }

    public static boolean isPlaybackOnChromecastPaused() {
        return playbackOnChromecastPaused;
    }

    public static void setPlaybackOnChromecastPaused(boolean playbackOnChromecastPaused2) {
        playbackOnChromecastPaused = playbackOnChromecastPaused2;
    }

    private static String marketplaceRegion(final String marketplaceId) {
        String region;
        Marketplace marketplaceLookupByObfuscatedId = MarketplaceResolver.lookupByObfuscatedId(marketplaceId);
        if (marketplaceLookupByObfuscatedId != null && (region = marketplaceLookupByObfuscatedId.getRegion()) != null) {
            return region;
        }
        logger.debug("No region found for marketplace ID " + marketplaceId + ". Using default.");
        return Marketplace.USA.getRegion();
    }
}
