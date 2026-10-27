package com.amazon.avod.ads.http;

import amazon.android.config.ConfigBase;
import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.ads.api.AdBreakErrorCode;
import com.amazon.avod.ads.api.AdInfoErrorCode;
import com.amazon.avod.ads.api.AdNetworkException;
import com.amazon.avod.ads.api.Duration;
import com.amazon.avod.util.DLog;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazonaws.http.HttpHeader;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AdHttpClient {
    private final AdHttpClientConfig mAdsConfig;
    protected final HttpCookieStore mCookieStore;
    private final HttpParameters mParameters;
    private final Random mRandom;

    protected abstract void pingHttpServer(URL url, int i, int i2) throws AdNetworkException;

    protected abstract String processHttpRequest(URL url, int i, int i2) throws AdNetworkException;

    public AdHttpClient(HttpParameters httpParameters, HttpCookieStore httpCookieStore) {
        this(httpParameters, httpCookieStore, new Random(), AdHttpClientConfig.getInstance());
    }

    AdHttpClient(HttpParameters httpParameters, HttpCookieStore httpCookieStore, Random random, AdHttpClientConfig adHttpClientConfig) {
        this.mParameters = (HttpParameters) Preconditions.checkNotNull(httpParameters);
        this.mCookieStore = (HttpCookieStore) Preconditions.checkNotNull(httpCookieStore);
        this.mRandom = random;
        this.mAdsConfig = adHttpClientConfig;
    }

    public void sendVastBeacon(List<URI> list, Duration duration, URI uri, String str) throws AdNetworkException {
        sendVastBeacon(list, duration, uri, (AdInfoErrorCode) null, str);
    }

    public void sendVastBeacon(List<URI> list, Duration duration, URI uri, AdInfoErrorCode adInfoErrorCode, String str) throws AdNetworkException {
        Iterator<URI> it = list.iterator();
        while (it.hasNext()) {
            sendBeacon(it.next(), createVastMacroMap(duration, uri, adInfoErrorCode, str));
        }
    }

    public void sendVastBeacon(URI uri, Duration duration, URI uri2, String str) throws AdNetworkException {
        sendVastBeacon(uri, duration, uri2, (AdInfoErrorCode) null, str);
    }

    public void sendSimpleBeacon(URI uri) throws AdNetworkException {
        try {
            URL url = new URL(uri.toString());
            DLog.logf("Sending beacon: %s", url);
            pingHttpServer(url, this.mParameters.getBeaconRetries(), this.mParameters.getBeaconTimeout());
        } catch (IOException e) {
            throw new AdNetworkException(e);
        }
    }

    public void sendVastBeacon(URI uri, Duration duration, URI uri2, AdInfoErrorCode adInfoErrorCode, String str) throws AdNetworkException {
        sendBeacon(uri, createVastMacroMap(duration, uri2, adInfoErrorCode, str));
    }

    public void sendVmapBeacon(URI uri) throws AdNetworkException {
        sendVmapBeacon(uri, null, null);
    }

    public void sendVmapBeacon(URI uri, AdBreakErrorCode adBreakErrorCode, String str) throws AdNetworkException {
        sendBeacon(uri, createVmapMacroMap(adBreakErrorCode, str));
    }

    private void sendBeacon(URI uri, Map<String, String> map) throws AdNetworkException {
        try {
            URL urlProcessMacros = processMacros(uri.toURL(), map);
            DLog.logf("Sending beacon: %s", urlProcessMacros);
            pingHttpServer(urlProcessMacros, this.mParameters.getBeaconRetries(), this.mParameters.getBeaconTimeout());
        } catch (IOException | IllegalArgumentException e) {
            throw new AdNetworkException(e);
        }
    }

    @Nullable
    public String processRequest(URI uri) throws AdNetworkException {
        try {
            return processHttpRequest(uri.toURL(), this.mParameters.getRequestRetries(), this.mParameters.getRequestTimeout());
        } catch (IllegalArgumentException | MalformedURLException e) {
            throw new AdNetworkException(e);
        }
    }

    protected HttpParameters getHttpParameters() {
        return this.mParameters;
    }

    Map<String, String> createVastMacroMap(Duration duration, URI uri, AdInfoErrorCode adInfoErrorCode, String str) {
        HashMap mapNewHashMap = Maps.newHashMap();
        if (duration != null) {
            mapNewHashMap.put("[CONTENTPLAYHEAD]", duration.toBeaconString());
        }
        if (uri != null) {
            mapNewHashMap.put("[ASSETURI]", uri.toString());
        }
        if (adInfoErrorCode != null) {
            mapNewHashMap.put("[ERRORCODE]", String.valueOf(adInfoErrorCode.getErrorCode()));
        }
        if (str != null) {
            mapNewHashMap.put("[CONSUMPTIONID]", str);
        }
        mapNewHashMap.put("[CACHEBUSTING]", String.valueOf(this.mRandom.nextInt(90000000) + 10000000));
        return mapNewHashMap;
    }

    Map<String, String> createVmapMacroMap(AdBreakErrorCode adBreakErrorCode, String str) {
        HashMap mapNewHashMap = Maps.newHashMap();
        if (str != null) {
            mapNewHashMap.put("[ERROR_MESSAGE]", str);
        }
        if (adBreakErrorCode != null) {
            mapNewHashMap.put("[ERROR_CODE]", String.valueOf(adBreakErrorCode.getErrorCode()));
        }
        return mapNewHashMap;
    }

    URL processMacros(URL url, Map<String, String> map) throws IOException {
        String string = url.toString();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (string.contains(entry.getKey())) {
                string = string.replace(entry.getKey(), urlEncode(entry.getValue()));
            } else {
                string = string.replace(urlEncode(entry.getKey()), urlEncode(entry.getValue()));
            }
        }
        return new URL(string);
    }

    protected void setCookies(HttpURLConnection httpURLConnection) {
        List<String> list = httpURLConnection.getHeaderFields().get("Set-Cookie");
        URL url = httpURLConnection.getURL();
        if (list != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                this.mCookieStore.setCookie(url, it.next());
            }
        }
    }

    static String urlEncode(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, ParserUtil.UTF_8);
    }

    static void updateParameters(HttpURLConnection httpURLConnection, HttpParameters httpParameters) {
        httpURLConnection.setInstanceFollowRedirects(httpParameters.getFollowRedirects());
        httpURLConnection.setRequestProperty(HttpHeader.USER_AGENT, httpParameters.getUserAgent());
    }

    static String convertStreamToString(InputStream inputStream) {
        Scanner scannerUseDelimiter = new Scanner(inputStream, Charsets.UTF_8.name()).useDelimiter("\\A");
        return scannerUseDelimiter.hasNext() ? scannerUseDelimiter.next() : "";
    }

    static class AdHttpClientConfig extends ConfigBase {
        private final ConfigurationValue<Boolean> mShouldEnableEmptyAdsManifestParsingOptimization;

        private static class InstanceHolder {
            public static final AdHttpClientConfig INSTANCE = new AdHttpClientConfig();

            private InstanceHolder() {
            }
        }

        public static final AdHttpClientConfig getInstance() {
            return InstanceHolder.INSTANCE;
        }

        private AdHttpClientConfig() {
            super("com.amazon.avod.ads.http");
            this.mShouldEnableEmptyAdsManifestParsingOptimization = newBooleanConfigValue("ads_ShouldEnableEmptyAdsManifestParsingOptimization", true, ConfigType.SERVER);
        }
    }
}
