package com.spotify.connectivity.productstate;

import com.google.common.base.Optional;
import io.reactivex.rxjava3.core.Observable;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface RxProductState {

    public static final class Keys {
        public static final Keys INSTANCE = new Keys();
        public static final String KEY_ACCOUNT_CREATION_TIME = "account-creation-time";
        public static final String KEY_ACTIVE_SESSION_DAYS = "active-session-days";
        public static final String KEY_ADS = "ads";
        public static final String KEY_ALLOW_CHILD_VIDEO = "allow-child-video";
        public static final String KEY_ALLOW_CONNECT_ENTRY_POINTS = "allow-connect-entry-points";
        public static final String KEY_ALLOW_KIDS_ACCOUNT_SELECTION = "allow-kids-account-selection";
        public static final String KEY_APPLY_CHILD_CONTENT_RESTRICTIONS = "apply-child-content-restrictions";
        public static final String KEY_AUDIOBOOK_ONBOARDING_COMPLETED = "audiobook-onboarding-completed";
        public static final String KEY_AUDIO_PREVIEW_URL_TEMPLATE = "audio-preview-url-template";
        public static final String KEY_AUDIO_QUALITY = "audio-quality";
        public static final String KEY_AUTOPLAY_GLOBAL = "autoplay_global";
        public static final String KEY_CAN_BLOCK_FOR_KIDS = "can-block-for-kids";
        public static final String KEY_CATALOGUE = "catalogue";
        public static final String KEY_COUNTRY_CODE = "country_code";
        public static final String KEY_CREATED_BY_PARTNER = "created_by_partner";
        public static final String KEY_CUTTLEFISH_ENABLED = "is-cuttlefish";
        public static final String KEY_DISABLE_BLOCKED_CONTENT = "disable-blocked-content";
        public static final String KEY_DSA_MODE_ENABLED = "dsa-mode-enabled";
        public static final String KEY_EMPLOYEE = "employee";
        public static final String KEY_EXPLICIT_CONTENT_FILTERING = "filter-explicit-content";
        public static final String KEY_HAS_BLEND_ACCESS = "has-blend-access";
        public static final String KEY_IS_CHEETARA_ENABLED = "is-cheetara";
        public static final String KEY_IS_EMAIL_VERIFIED = "is_email_verified";
        public static final String KEY_IS_EUTERPE = "is-euterpe";
        public static final String KEY_IS_LINGO_AURORA = "is-lingo-aurora";
        public static final String KEY_IS_MERMAID = "is-mermaid";
        public static final String KEY_IS_STANDALONE_AUDIOBOOKS = "is-standalone-audiobooks";
        public static final String KEY_IS_TUNA_ENABLED = "is-tuna";
        public static final String KEY_KEY_PODCASTS_ENABLED = "shows-collection";
        public static final String KEY_LIMITED_OFFLINE = "limited-offline";
        public static final String KEY_LOUDNESS_LEVELS = "loudness-levels";
        public static final String KEY_MARQUES_ACTIVATION = "marques-activation";
        public static final String KEY_MARQUES_ENABLED = "is-marques";
        public static final String KEY_MIDROLL_VIDEO_ADS = "ab-watch-now";
        public static final String KEY_MOBILE_PRODUCT_TEST = "mobile-product-test";
        public static final String KEY_NEMO_ENABLED = "is-nemo";
        public static final String KEY_NFT_DISABLED = "nft-disabled";
        public static final String KEY_OBFUSCATE_RESTRICTED_TRACKS = "obfuscate-restricted-tracks";
        public static final String KEY_OFFLINE = "offline";
        public static final String KEY_OFFLINE_DAILY_CAP = "offline-daily-cap";
        public static final String KEY_ON_DEMAND_TRIAL = "on-demand-trial";
        public static final String KEY_PARENTAL_PIN_REQUIRED = "parental-pin-required";
        public static final String KEY_PAYMENT_STATE = "payment-state";
        public static final String KEY_PIGEON_USER = "is-pigeon";
        public static final String KEY_POST_OPEN_GRAPH = "post-open-graph";
        public static final String KEY_PREFERRED_LOCALE = "preferred-locale";
        public static final String KEY_PREMIUM_MINI = "premium-mini";
        public static final String KEY_PREMIUM_TAB_LOCK = "premium-tab-lock";
        public static final String KEY_PREROLL_VIDEO_ADS = "ad-formats-preroll-video";
        public static final String KEY_PUFFIN_USER = "is-puffin";
        public static final String KEY_RADIO = "radio";
        public static final String KEY_REFERRAL = "referral";
        public static final String KEY_REFERRAL_TIMESTAMP = "referral-timestamp";
        public static final String KEY_RESTRICTED_SETTINGS_FOR_CHILD = "restrict-settings-for-child";
        public static final String KEY_RESTRICT_ADVERTISING_ID_TRANSMISSION = "restrict-advertising-id-transmission";
        public static final String KEY_SEADRAGON_ENABLED = "is-seadragon";
        public static final String KEY_SEND_EMAIL = "send-email";
        public static final String KEY_SHOWS_COLLECTION = "shows-collection";
        public static final String KEY_SHOWS_COLLECTION_VIDEO = "shows-collection-jam";
        public static final String KEY_SOCIAL_SESSION = "social-session";
        public static final String KEY_SOCIAL_SESSION_FREE = "social-session-free-tier";
        public static final String KEY_STREAMING = "streaming";
        public static final String KEY_STREAMING_RULES = "streaming-rules";
        public static final String KEY_SUPERSTAR_ENABLED = "is-superstar";
        public static final String KEY_TASTE_ONBOARDING_AFTER_SIGNING = "taste-onboarding-signin-eligible";
        public static final String KEY_TYPE = "type";
        public static final String KEY_UGC_ABUSE_REPORT_URL = "ugc-abuse-report-url";
        public static final String KEY_VIDEO_CDN_SAMPLING = "video-cdn-sampling";
        public static final String KEY_VIDEO_MANIFEST_URL = "video-manifest-url";
        public static final String KEY_WIDEVINE_LICENSE_URL = "widevine-license-url";
        public static final String KEY_YE_REMOVE_PLAYED_EPISODES = "your-episodes-remove-played-episodes";
        public static final String KEY_YE_REMOVE_UNPLAYED_EPISODES = "your-episodes-remove-unplayed-episodes";
        public static final String KEY_YL_KIDS_RESTRICTED_CONTENT = "yl-kids-restricted-content";
        public static final String VIDEO_DEVICE_BLACKLISTED = "video-device-blacklisted";

        private Keys() {
        }
    }

    Observable<Map<String, String>> productState();

    Observable<Optional<String>> productStateKey(String str);

    Observable<String> productStateKeyOr(String str, String str2);

    Observable<String> productStateKeyV2(String str);
}
