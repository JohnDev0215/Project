package com.amazon.mp3.store.html5.util;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.capability.StoreInfoProvider;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.url.EndPointURL;
import com.amazon.mp3.profile.DeviceProfileProvider;
import com.amazon.mp3.store.purchase.PurchaseUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import com.fasterxml.jackson.core.JsonPointer;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes4.dex */
public abstract class StoreUrls {
    private static final String TAG = "StoreUrls";
    private static final Pattern SPLIT_DOTS = Pattern.compile("\\.");
    private static final Pattern URL_ENCODED_SPACES = Pattern.compile("\\+");
    private static String sReftag = "";
    private static AtomicInteger sUniquePageLoadId = new AtomicInteger(0);

    public enum StoreURLType {
        BASE_PATH("gp/dmusic/device/mp3/store/"),
        INTERNAL_PATH("/gp/dmusic/"),
        AUTH_PATH("/ap/signin"),
        REDIRECT_PAGE("/gp/redirect.html"),
        PRIME_SIGNUP_PAGE("/gp/prime/"),
        MUSIC_UNLIMITED_SIGNUP_PAGE("/music/unlimited/"),
        UNLIMITED_SIGNUP_PAGE("/unlimited/signup");

        private final String mType;

        StoreURLType(String type) {
            this.mType = type;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mType;
        }
    }

    public static void setReftag(final String reftag) {
        if (reftag == null) {
            reftag = "";
        }
        sReftag = reftag;
    }

    public static EndPointURL getHostEndpoint() {
        return Environment.STORE.get();
    }

    public static String getBaseUrl() {
        return getBaseUrl(false);
    }

    public static String getBaseUrl(boolean requestAuth) {
        return getUrlString("home", requestAuth);
    }

    public static boolean pathContainsRootPath(String path) {
        return path != null && path.contains(StoreURLType.BASE_PATH.toString());
    }

    public static boolean isUrlTrusted(String url) {
        if (url == null || url.length() == 0 || "about:blank".equals(url)) {
            return true;
        }
        return isStoreDomain(url) && startsWithStoreURL(Uri.parse(url).getPath());
    }

    private static boolean startsWithStoreURL(final String urlPath) {
        for (StoreURLType storeURLType : StoreURLType.values()) {
            if (urlPath.startsWith(storeURLType.toString())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isStoreDomain(final String url) {
        String host;
        if (url == null || (host = Uri.parse(url).getHost()) == null) {
            return false;
        }
        Pattern pattern = SPLIT_DOTS;
        String[] strArrSplit = pattern.split(Environment.WEBVIEW.get().toHost());
        String[] strArrSplit2 = pattern.split(host);
        int length = strArrSplit.length - 1;
        for (int length2 = strArrSplit2.length - 1; length >= 0 && length2 >= 0; length2--) {
            String str = strArrSplit2[length2];
            String str2 = strArrSplit[length];
            if (!str2.equalsIgnoreCase(str)) {
                return false;
            }
            if ("amazon".equalsIgnoreCase(str2)) {
                break;
            }
            length--;
        }
        return true;
    }

    public static boolean isStoreUrl(Uri uri) {
        String path = uri.getPath();
        if (path != null) {
            if (path.startsWith(JsonPointer.SEPARATOR + StoreURLType.BASE_PATH.toString())) {
                return true;
            }
        }
        return false;
    }

    public static String urlEncode(final String unencoded) {
        try {
            return URL_ENCODED_SPACES.matcher(URLEncoder.encode(unencoded, ParserUtil.UTF_8)).replaceAll("%20");
        } catch (UnsupportedEncodingException e) {
            Log.error(TAG, "Failed to encode UTF-8", e);
            return unencoded;
        }
    }

    public static String getArtistDetailUrl(Bundle bundle) {
        String str;
        String string = bundle.getString("com.amazon.mp3.extra.EXTRA_CONTRIBUTOR_ASIN");
        String string2 = bundle.getString("com.amazon.mp3.extra.EXTRA_ARIST_ASIN");
        if (string2 == null) {
            str = "artist/" + string;
        } else {
            str = "artist/" + string + JsonPointer.SEPARATOR + string2;
        }
        return getUrlString(str, false);
    }

    public static String getAlbumDetailUrl(Bundle bundle) {
        String string = bundle.getString("com.amazon.mp3.extra.ALBUM_ASIN");
        String string2 = bundle.getString("com.amazon.mp3.extra.AUTO_PLAY_TRACK_ASIN");
        if (string == null && string2 != null) {
            return getUrlString("track/" + string2, false);
        }
        String str = "album/" + string;
        if (string2 != null) {
            str = str + "/highlight:" + string2;
        }
        return getUrlString(str, false);
    }

    public static String getTrackDetailUrl(Bundle bundle) {
        String str;
        String string = bundle.getString("com.amazon.mp3.extra.ALBUM_ASIN");
        String string2 = bundle.getString("com.amazon.mp3.extra.TRACK_ASIN");
        if (string != null) {
            str = "album/" + string + "/highlight:" + string2;
        } else {
            str = "track/" + string2;
        }
        return getUrlString(str, false);
    }

    public static String getSearchUrl(Bundle bundle) {
        String strUrlEncode = urlEncode(bundle.getString("com.amazon.mp3.extra.SEARCH_STRING"));
        int i = bundle.getInt("com.amazon.mp3.extra.BROWSE_TYPE", -1);
        if (strUrlEncode == null) {
            strUrlEncode = "";
        }
        String str = strUrlEncode + "+uid:" + sUniquePageLoadId.addAndGet(1) + "+type:";
        if (i == 0) {
            str = str + "tracks";
        } else if (i == 1) {
            str = str + "albums";
        }
        return getUrlString("search/" + str, false);
    }

    public static String getBestsellersUrl(Bundle bundle) {
        return getUrlString("bestsellers/uid:" + sUniquePageLoadId.addAndGet(1) + "+type:" + (bundle.getInt("com.amazon.mp3.extra.GENRE_BROWSE_TYPE", 1) != 0 ? "albums" : "tracks"), false);
    }

    public static String getBestsellersAll() {
        return getUrlString("bestsellers/uid:" + sUniquePageLoadId.addAndGet(1), false);
    }

    public static String getNewReleasesUrl(Bundle bundle) {
        return getUrlString("newreleases/uid:" + sUniquePageLoadId.addAndGet(1) + "+type:" + (bundle.getInt("com.amazon.mp3.extra.GENRE_BROWSE_TYPE", 1) != 0 ? "albums" : "tracks"), false);
    }

    public static String getGenresUrl() {
        return getUrlString("genres", false);
    }

    public static String getGenreDetailUrl(Bundle bundle) {
        String string = bundle.getString("com.amazon.mp3.extra.EXTRA_GENRE_ALBUM_NODE_ID");
        String string2 = bundle.getString("com.amazon.mp3.extra.EXTRA_GENRE_TRACK_NODE_ID");
        if (string == null || string2 == null) {
            return getBaseUrl();
        }
        return getUrlString("genre/uid:" + sUniquePageLoadId.addAndGet(1) + "+albums:" + string + "+tracks:" + string2 + "+type:" + (bundle.getInt("com.amazon.mp3.extra.BROWSE_TYPE", 0) == 1 ? "albums" : "tracks"), false);
    }

    public static String getCampaignDetailUrl(Bundle bundle) {
        String string = bundle.getString("com.amazon.mp3.extra.EXTRA_CAMPAIGN_ROUTE");
        if (string == null) {
            return getBaseUrl();
        }
        return getUrlString(string, false);
    }

    public static String getDeepLinkUrl(Bundle bundle) {
        String string = bundle.getString("com.amazon.mp3.extra.EXTRA_RELATIVE_ROUTE");
        if (string == null) {
            return getBaseUrl();
        }
        return getUrlString(string, false);
    }

    public static String getRecommendedForYouUrl(Bundle bundle) {
        return getUrlString("recommended/uid:" + sUniquePageLoadId.addAndGet(1) + "+type:" + (bundle.getInt("com.amazon.mp3.extra.GENRE_BROWSE_TYPE", 0) != 1 ? "tracks" : "albums"), false);
    }

    public static String getPrimePlaylistsUrl(Bundle bundle) {
        String string = bundle.getString("com.amazon.mp3.extra.EXTRA_PLAYLIST_ASIN");
        if (TextUtils.isEmpty(string)) {
            return getUrlString("playlists", false);
        }
        return getUrlString("playlists/" + string, false);
    }

    public static String getRouteUrl(Bundle bundle) {
        return getUrlString(bundle.getString("com.amazon.mp3.extra.EXTRA_ROUTE_URL").replace(StoreURLType.BASE_PATH.mType, ""), false);
    }

    public static String getUrlString(String route, boolean requestAuth) {
        EndPointURL hostEndpoint = getHostEndpoint();
        Uri.Builder builderBuildUpon = Uri.parse(hostEndpoint.toString()).buildUpon();
        String storeRootPath = getStoreRootPath(getStoreArguments(hostEndpoint), route, requestAuth);
        Log.verbose(TAG, "setting path to: %s", storeRootPath);
        builderBuildUpon.encodedPath(storeRootPath);
        return builderBuildUpon.build().toString();
    }

    public static String getStoreURL(String path) {
        if (TextUtils.isEmpty(path)) {
            return getBaseUrl();
        }
        if (!path.startsWith("/")) {
            path = JsonPointer.SEPARATOR + path;
        }
        return getHostEndpoint() + path;
    }

    public static Uri appendChangedUser(final Uri uri) {
        if (!uri.isHierarchical()) {
            uri = Uri.parse(getBaseUrl());
        }
        return TextUtils.isEmpty(uri.getQueryParameter("changedUser")) ? uri.buildUpon().appendQueryParameter("changedUser", "1").build() : uri;
    }

    private static String getStoreLocale() {
        Locale locale;
        Locale locale2 = AmazonApplication.getLocale();
        if ("".equals(locale2.getLanguage())) {
            locale = new Locale("en", locale2.getCountry(), locale2.getVariant());
        } else {
            if ("UK".equalsIgnoreCase(locale2.getCountry())) {
                locale = new Locale(locale2.getLanguage(), "GB", locale2.getVariant());
            }
            return locale2.toString();
        }
        locale2 = locale;
        return locale2.toString();
    }

    private static String getStoreArguments(EndPointURL hostEndPoint) {
        StringBuilder sb = new StringBuilder("?embedded=1");
        Map<String, String> urlParameters = hostEndPoint.getUrlParameters();
        sb.append("&device=");
        sb.append(StoreInfoProvider.getDeviceName());
        StoreInfoProvider.appendVersion(urlParameters);
        if (urlParameters != null) {
            for (Map.Entry<String, String> entry : urlParameters.entrySet()) {
                sb.append(Typography.amp);
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
            }
        }
        try {
            sb.append("&lang=");
            sb.append(URLEncoder.encode(getStoreLocale(), ParserUtil.UTF_8));
            String referrerPackageName = PurchaseUtil.getReferrerPackageName();
            if (referrerPackageName == null) {
                referrerPackageName = "";
            }
            String strEncode = URLEncoder.encode(referrerPackageName.trim(), ParserUtil.UTF_8);
            if (!TextUtils.isEmpty(strEncode)) {
                sb.append("&subTag=");
                sb.append(strEncode);
            }
            String strEncode2 = URLEncoder.encode(sReftag.trim(), ParserUtil.UTF_8);
            if (!TextUtils.isEmpty(strEncode2)) {
                sb.append("&ref=");
                sb.append(strEncode2);
            }
            sb.append(getCampaignParameters());
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getStoreRootPath(String arguments, String route, boolean requestAuth) {
        if (requestAuth && AmazonApplication.getCapabilities().isAppToWebSSOEnabled() && AccountCredentialUtil.get().isSignedIn()) {
            Uri.Builder builderBuildUpon = Uri.parse(getHostEndpoint().toString()).buildUpon();
            builderBuildUpon.encodedPath(createStoreUrl(arguments, route));
            return StoreURLType.BASE_PATH.toString() + "startSignIn" + arguments + "&return=" + Uri.encode(builderBuildUpon.build().toString());
        }
        return createStoreUrl(arguments, route);
    }

    private static String getCampaignParameters() {
        DeviceProfileProvider deviceProfileProvider = new DeviceProfileProvider(AmazonApplication.getContext());
        return encodeUrlParameter("mod", deviceProfileProvider.getDeviceModel()) + encodeUrlParameter("manufacturer", deviceProfileProvider.getDeviceManufacturer()) + encodeUrlParameter("carrier", deviceProfileProvider.getCarrierName()) + encodeUrlParameter("mnc", deviceProfileProvider.getSimMobileNetworkCode()) + encodeUrlParameter("mcc", deviceProfileProvider.getSimMobileCountryCode()) + encodeUrlParameter("installRef", deviceProfileProvider.getInstallReferrer());
    }

    private static String encodeUrlParameter(String name, String value) {
        if (value != null && !"".equals(value)) {
            try {
                return String.format(Locale.US, "&%s=%s", name, URLEncoder.encode(value, ParserUtil.UTF_8));
            } catch (UnsupportedEncodingException unused) {
                Log.verbose(TAG, "String value can not be encoded %s", value);
            }
        }
        return "";
    }

    private static String createStoreUrl(String arguments, String route) {
        return StoreURLType.BASE_PATH.toString() + arguments + '#' + route;
    }
}
