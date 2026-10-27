package com.amazon.mp3.store.html5.map;

import android.content.Context;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import androidx.work.PeriodicWorkRequest;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.capability.Capabilities;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.store.html5.util.StoreUrls;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.marketplace.Marketplace;
import com.amazonaws.util.DateUtils;
import java.net.HttpCookie;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class MAPCookiesUtil {
    private static final Map<Marketplace, String> COOKIES_TO_MARKETPLACE_MAP;
    private static final Map<String, String> COOKIES_TO_TERRITORY_MAP;
    private static final String TAG = "MAPCookiesUtil";

    static {
        HashMap map = new HashMap();
        COOKIES_TO_MARKETPLACE_MAP = map;
        map.put(Marketplace.USA, "at-main");
        map.put(Marketplace.UK, "at-acbuk");
        map.put(Marketplace.GERMANY, "at-acbde");
        map.put(Marketplace.FRANCE, "at-acbfr");
        map.put(Marketplace.ITALY, "at-acbit");
        map.put(Marketplace.SPAIN, "at-acbes");
        map.put(Marketplace.JAPAN, "at-acbjp");
        map.put(Marketplace.ROW_NA, "at-main");
        map.put(Marketplace.ROE_EU, "at-main");
        map.put(Marketplace.ROW_FE, "at-main");
        HashMap map2 = new HashMap();
        COOKIES_TO_TERRITORY_MAP = map2;
        map2.put(MusicTerritory.CANADA.value, "at-acbca");
        map2.put(MusicTerritory.INDIA.value, "at-acbin");
        map2.put(MusicTerritory.AUSTRALIA.value, "at-acbau");
        map2.put(MusicTerritory.NEW_ZEALAND.value, "at-acbau");
    }

    private static boolean shouldPrefetchCookies(String url) {
        return cookieHasExpired(url) || cookieExpiresSoon();
    }

    private static boolean cookieHasExpired(String url) {
        CookieSyncManager cookieSyncManagerCreateInstance;
        Context context = AmazonApplication.getContext();
        try {
            cookieSyncManagerCreateInstance = CookieSyncManager.getInstance();
        } catch (IllegalStateException unused) {
            Log.verbose(TAG, "CookieSyncManager not yet created... creating");
            cookieSyncManagerCreateInstance = CookieSyncManager.createInstance(context);
        }
        cookieSyncManagerCreateInstance.sync();
        String cookie = CookieManager.getInstance().getCookie(url);
        if (cookie == null) {
            Log.verbose(TAG, "cookie expired");
            return true;
        }
        for (String str : cookie.split("\\s*;\\s*")) {
            if (cookieExists(str)) {
                return false;
            }
        }
        Log.verbose(TAG, "cookie expired");
        return true;
    }

    static boolean cookieExists(String cookie) {
        if (cookie == null) {
            return false;
        }
        String str = COOKIES_TO_TERRITORY_MAP.get(AccountDetailUtil.getMusicTerritoryOfResidence());
        if (str != null) {
            return cookie.startsWith(str);
        }
        String str2 = COOKIES_TO_MARKETPLACE_MAP.get(AccountDetailUtil.get().getHomeMarketPlace());
        return str2 != null && cookie.startsWith(str2);
    }

    private static boolean cookieExpiresSoon() {
        long timeInMillis = Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis();
        long atMainCookieExpirationDate = AccountCredentialStorage.get().getAtMainCookieExpirationDate();
        if (atMainCookieExpirationDate < 0 || timeInMillis < atMainCookieExpirationDate) {
            return false;
        }
        Log.verbose(TAG, "cookies will expire soon... current = %d, exp = %d", Long.valueOf(timeInMillis), Long.valueOf(atMainCookieExpirationDate));
        return true;
    }

    static long getCookiePreExpirationPeriod() {
        return AmazonApplication.getConfiguration(AmazonApplication.getContext()).getLong("at_main_cookie_pre_expiration", PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS);
    }

    static long getCookieExpirationPeriod() {
        return AmazonApplication.getConfiguration(AmazonApplication.getContext()).getLong("at_main_cookie_expiration", 3600000L);
    }

    public static MAPFuture<Bundle> fetchCookiesForMusicTerritory(final String musicTerritory, final MAPCookiesCallback callback, final boolean forceRefresh) {
        String domainForMusicTerritory = EndpointsProvider.get().getDomainForMusicTerritory(musicTerritory);
        if (domainForMusicTerritory == null) {
            domainForMusicTerritory = EndpointsProvider.get().getDefaultDomain();
        }
        return MAPCookiesManager.getCookiesManager().fetchCookies(domainForMusicTerritory, callback, forceRefresh);
    }

    public static MAPFuture<Bundle> fetchCookies(final MAPCookiesCallback callback, boolean forceRefresh) {
        return MAPCookiesManager.getCookiesManager().fetchCookies(callback, forceRefresh);
    }

    public static void prefetchOnlyIfNeeded() {
        boolean z = false;
        if (AmazonApplication.getDevModeCapabilities().isDeveloperToolEnabled()) {
            Log.verbose(TAG, "checking at-main cookie status... current (GMT) = %d, exp (GMT) = %d, getting expired in %f minutes...", Long.valueOf(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis()), Long.valueOf(AccountCredentialStorage.get().getAtMainCookieExpirationDate()), Double.valueOf((r5 - r3) / 60000.0d));
        }
        Capabilities capabilities = AmazonApplication.getCapabilities();
        if (AccountCredentialUtil.get().isSignedIn() && capabilities.isAppToWebSSOEnabled() && capabilities.isStoreSupported() && ConnectivityUtil.hasAnyInternetConnection()) {
            z = true;
        }
        if (!z) {
            Log.verbose(TAG, "no need to check cookies");
        } else if (shouldPrefetchCookies(StoreUrls.getBaseUrl())) {
            fetchCookies(new MAPCookiesCallback(AmazonApplication.getContext()), true);
        }
    }

    private static String extractCookieExpires(final HttpCookie httpCookie, final String stringCookie) {
        Date date;
        String str;
        String[] strArrSplit = stringCookie.split("\\s*;\\s*");
        int length = strArrSplit.length;
        int i = 0;
        while (true) {
            date = null;
            if (i >= length) {
                str = null;
                break;
            }
            String[] strArrSplit2 = strArrSplit[i].split("\\s*=\\s*");
            if (strArrSplit2.length > 1 && "expires".equalsIgnoreCase(strArrSplit2[0])) {
                str = strArrSplit2[1];
                break;
            }
            i++;
        }
        if (str != null) {
            try {
                date = new SimpleDateFormat("d MMM yyyy HH:mm:ss z", Locale.US).parse(str);
                httpCookie.setMaxAge(date.getTime() / 1000);
            } catch (ParseException unused) {
                Log.warning(TAG, "Date format changed");
            }
        }
        if (date == null) {
            long maxAge = httpCookie.getMaxAge();
            if (maxAge <= 5) {
                Log.warning(TAG, "Using default cookie expiration");
                long jCurrentTimeMillis = System.currentTimeMillis() + 3600000;
                Date date2 = new Date(jCurrentTimeMillis);
                httpCookie.setMaxAge(jCurrentTimeMillis / 1000);
                date = date2;
            } else {
                date = new Date(System.currentTimeMillis() + (maxAge * 1000));
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.RFC822_DATE_PATTERN, Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        simpleDateFormat.format(date);
        return simpleDateFormat.format(new Date(System.currentTimeMillis() + 604800000));
    }

    private static JSONObject cookieToJson(final String cookie) throws JSONException, IllegalArgumentException {
        List<HttpCookie> list = HttpCookie.parse(cookie);
        if (list.size() < 1) {
            throw new IllegalArgumentException("Failed to parse");
        }
        HttpCookie httpCookie = list.get(0);
        httpCookie.setMaxAge(httpCookie.getMaxAge() - 5);
        String strExtractCookieExpires = extractCookieExpires(httpCookie, cookie);
        JSONObject jSONObject = new JSONObject();
        jSONObject.putOpt("name", httpCookie.getName()).putOpt("value", httpCookie.getValue()).putOpt("domain", httpCookie.getDomain()).putOpt("path", httpCookie.getPath()).putOpt("port", httpCookie.getPortlist()).putOpt("comment", httpCookie.getComment()).putOpt("expires", strExtractCookieExpires).put("version", httpCookie.getVersion()).put("discard", httpCookie.getDiscard()).put("httpOnly", false).put("secure", httpCookie.getSecure());
        return jSONObject;
    }

    static JSONArray cookiesToJson(final String[] cookies) {
        JSONArray jSONArray = new JSONArray();
        for (String str : cookies) {
            try {
                jSONArray.put(cookieToJson(str));
            } catch (IllegalArgumentException unused) {
                Log.warning(TAG, "Failed to parse cookie");
            } catch (JSONException unused2) {
                Log.warning(TAG, "JSON exception while converting cookie");
            }
        }
        return jSONArray;
    }
}
