package com.amazon.mp3.environment;

import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.mp3.configuration.managers.EndPointManager;
import com.amazon.mp3.curate.provider.CurateEndpointSettingsProvider;
import com.amazon.mp3.environment.url.BaseEndPointURLFactory;
import com.amazon.mp3.environment.url.CirrusURLFactory;
import com.amazon.mp3.environment.url.ClientBuddyEndPointURLFactory;
import com.amazon.mp3.environment.url.CloudPlayerURLFactory;
import com.amazon.mp3.environment.url.CloudQueueAuthEndpointURLFactory;
import com.amazon.mp3.environment.url.DMAutomotiveEndPointURLFactory;
import com.amazon.mp3.environment.url.DmlsURLFactory;
import com.amazon.mp3.environment.url.DynMsgEndpointProvider;
import com.amazon.mp3.environment.url.EndPointURLFactory;
import com.amazon.mp3.environment.url.IAMEndPointURLFactory;
import com.amazon.mp3.environment.url.MuseEndPointURLFactory;
import com.amazon.mp3.environment.url.RESOV2EndpointURLFactory;
import com.amazon.mp3.environment.url.SopranoEndPointURLFactory;
import com.amazon.mp3.environment.url.StratusURLFactory;
import com.amazon.mp3.explore.ExploreEndpointOverrideFactory;
import com.amazon.mp3.feed.FeedEndpointFactory;
import com.amazon.mp3.util.Log;
import com.amazon.podcast.PodcastEndpointOverrideFactory;

/* JADX INFO: loaded from: classes3.dex */
public enum Environment {
    INSTANCE;

    public static final FeedEndpointFactory ACTIVITY_FEED_SERVICE;
    public static final DMAutomotiveEndPointURLFactory AUTOMOTIVE;
    public static final EndPointURLFactory CIRRUS_V3;
    public static final ClientBuddyEndPointURLFactory CLIENT_BUDDY_CTA;
    public static final CloudQueueAuthEndpointURLFactory CLOUD_QUEUE_AUTH;
    public static final EndPointURLFactory CONFIG;
    public static final CurateEndpointSettingsProvider CURATE_SERVICE;
    public static final EndPointURLFactory DMLS;
    public static final String DMLS_PATH = "dmls";
    public static final DynMsgEndpointProvider DYNMSG_URL;
    public static final ExploreEndpointOverrideFactory EXPLORE_SERVICE;
    public static final EndPointURLFactory FTU;
    public static final IAMEndPointURLFactory IAM_URL;
    public static final MuseEndPointURLFactory MUSE_URL;
    public static final EndPointURLFactory MUSIC_PLAYLIST_SERVICE;
    public static final BaseEndPointURLFactory NAUTILUS;
    public static final String NAUTILUS_PATH = "nautilus";
    public static final String PLAYLIST_PATH = "playlist";
    public static final PodcastEndpointOverrideFactory PODCAST;
    public static final RESOV2EndpointURLFactory RESOV2_URL;
    public static final ClientBuddyEndPointURLFactory SEARCH;
    public static final SopranoEndPointURLFactory SOPRANOS_URL;
    public static final StratusURLFactory STRATUS;
    public static final String STRATUS_PATH = "stratus";
    public static final String V3_PATH = "v3";
    public static final EndPointURLFactory WEBVIEW;
    public static final EndPointURLFactory STORE = new BaseEndPointURLFactory() { // from class: com.amazon.mp3.environment.url.StoreURLFactory
        private static final String TAG = "com.amazon.mp3.environment.url.StoreURLFactory";

        @Override // com.amazon.mp3.environment.url.BaseEndPointURLFactory, com.amazon.mp3.environment.url.EndPointURLFactory
        public EndPointURL get() {
            try {
                return new EndPointURL(EndpointsProvider.get().getStoreEndpoint());
            } catch (Exception e) {
                Log.error(TAG, "Can't get store endpoint " + e);
                return null;
            }
        }
    };
    public static final CloudPlayerURLFactory CLOUD = new CloudPlayerURLFactory();

    static {
        final int i = R.string.setting_key_dev_webview_endpoint;
        WEBVIEW = new BaseEndPointURLFactory(i) { // from class: com.amazon.mp3.environment.url.WebViewURLFactory
            private static final String TAG = "WebViewURLFactory";

            @Override // com.amazon.mp3.environment.url.BaseEndPointURLFactory, com.amazon.mp3.environment.url.EndPointURLFactory
            public EndPointURL get() {
                try {
                    return new EndPointURL(EndpointsProvider.get().getWebviewEndpoint());
                } catch (Exception e) {
                    Log.error(TAG, "Can't get webview endpoint " + e);
                    return null;
                }
            }
        };
        CONFIG = new BaseEndPointURLFactory() { // from class: com.amazon.mp3.environment.url.ConfigFileURLFactory
            @Override // com.amazon.mp3.environment.url.BaseEndPointURLFactory, com.amazon.mp3.environment.url.EndPointURLFactory
            public EndPointURL get() {
                try {
                    EndPointURL endPointURLCreate = EndPointURL.create(this, IntlConfiguration.ENDPOINT.id(getCurrentEndPointId()), "US");
                    if (endPointURLCreate.id() != null) {
                        return endPointURLCreate;
                    }
                } catch (EndPointManager.UnknownEndpointException unused) {
                }
                return new EndPointURL();
            }
        };
        DMLS = new DmlsURLFactory(R.string.setting_key_dev_dmls_endpoint, DMLS_PATH);
        CIRRUS_V3 = new CirrusURLFactory(R.string.setting_key_dev_cirrus_v3_endpoint, V3_PATH);
        STRATUS = new StratusURLFactory(R.string.setting_key_dev_stratus_endpoint, STRATUS_PATH);
        final int i2 = R.string.setting_key_dev_nautilus_endpoint;
        final String str = NAUTILUS_PATH;
        NAUTILUS = new BaseEndPointURLFactory(i2, str) { // from class: com.amazon.mp3.environment.url.NautilusURLFactory
            private static final String TAG = DmlsURLFactory.class.getSimpleName();

            @Override // com.amazon.mp3.environment.url.BaseEndPointURLFactory, com.amazon.mp3.environment.url.EndPointURLFactory
            public EndPointURL get() {
                try {
                    return new EndPointURL(EndpointsProvider.get().getNautilusEndpoint());
                } catch (Exception e) {
                    Log.error(TAG, "Can't get Nautilus endpoint " + e);
                    return null;
                }
            }
        };
        final int i3 = R.string.setting_key_dev_playlist_endpoint;
        final String str2 = PLAYLIST_PATH;
        MUSIC_PLAYLIST_SERVICE = new BaseEndPointURLFactory(i3, str2) { // from class: com.amazon.mp3.environment.url.PlaylistUrlFactory
            private static final String TAG = "PlaylistUrlFactory";

            @Override // com.amazon.mp3.environment.url.BaseEndPointURLFactory, com.amazon.mp3.environment.url.EndPointURLFactory
            public EndPointURL get() {
                try {
                    return new EndPointURL(EndpointsProvider.get().getPlaylistEndpoint());
                } catch (Exception e) {
                    Log.error(TAG, "Can't get playlist endpoint " + e);
                    return null;
                }
            }
        };
        CLIENT_BUDDY_CTA = new ClientBuddyEndPointURLFactory(R.string.setting_key_dev_cta_endpoint);
        SEARCH = new ClientBuddyEndPointURLFactory(R.string.setting_key_dev_search_endpoint);
        final int i4 = R.string.setting_key_dev_ftu_endpoint;
        FTU = new BaseEndPointURLFactory(i4) { // from class: com.amazon.mp3.environment.url.WebViewURLFactory
            private static final String TAG = "WebViewURLFactory";

            @Override // com.amazon.mp3.environment.url.BaseEndPointURLFactory, com.amazon.mp3.environment.url.EndPointURLFactory
            public EndPointURL get() {
                try {
                    return new EndPointURL(EndpointsProvider.get().getWebviewEndpoint());
                } catch (Exception e) {
                    Log.error(TAG, "Can't get webview endpoint " + e);
                    return null;
                }
            }
        };
        MUSE_URL = new MuseEndPointURLFactory();
        SOPRANOS_URL = new SopranoEndPointURLFactory();
        IAM_URL = new IAMEndPointURLFactory(R.string.setting_key_dev_iam_endpoint);
        DYNMSG_URL = new DynMsgEndpointProvider();
        RESOV2_URL = new RESOV2EndpointURLFactory(R.string.setting_key_dev_iam_endpoint);
        EXPLORE_SERVICE = new ExploreEndpointOverrideFactory();
        ACTIVITY_FEED_SERVICE = new FeedEndpointFactory();
        CURATE_SERVICE = new CurateEndpointSettingsProvider();
        PODCAST = new PodcastEndpointOverrideFactory();
        AUTOMOTIVE = new DMAutomotiveEndPointURLFactory();
        CLOUD_QUEUE_AUTH = new CloudQueueAuthEndpointURLFactory();
    }
}
