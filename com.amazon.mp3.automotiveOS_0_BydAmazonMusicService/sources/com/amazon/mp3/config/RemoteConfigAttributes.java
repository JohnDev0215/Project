package com.amazon.mp3.config;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.util.IoUtil;
import com.amazon.mp3.util.JsonUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.config.Attributes;
import com.amazon.music.marketplace.Marketplace;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class RemoteConfigAttributes implements Attributes {
    private static final Map<Marketplace, String> MARKET_TO_LANGUAGE_MAP;
    private static final String TAG = "RemoteConfigAttributes";
    private static volatile RemoteConfigAttributes sInstance;
    private final Context mContext;

    private RemoteConfigAttributes(final Context context) {
        this.mContext = context;
    }

    static {
        HashMap map = new HashMap();
        MARKET_TO_LANGUAGE_MAP = map;
        map.put(Marketplace.USA, "en");
        map.put(Marketplace.UK, "en");
        map.put(Marketplace.GERMANY, "de");
        map.put(Marketplace.FRANCE, "fr");
        map.put(Marketplace.SPAIN, "es");
        map.put(Marketplace.ITALY, "it");
        map.put(Marketplace.JAPAN, "ja");
    }

    public static RemoteConfigAttributes getInstance(final Context context) {
        RemoteConfigAttributes remoteConfigAttributes = sInstance;
        if (remoteConfigAttributes == null) {
            synchronized (ApplicationAttributes.class) {
                remoteConfigAttributes = sInstance;
                if (remoteConfigAttributes == null) {
                    remoteConfigAttributes = new RemoteConfigAttributes(context);
                    sInstance = remoteConfigAttributes;
                }
            }
        }
        return remoteConfigAttributes;
    }

    @Override // com.amazon.music.config.Attributes
    public String getVersion() {
        return ApplicationAttributes.getInstance(this.mContext).getVersion();
    }

    @Override // com.amazon.music.config.Attributes
    public String getVersionShort() {
        return ApplicationAttributes.getInstance(this.mContext).getVersionShort();
    }

    @Override // com.amazon.music.config.Attributes
    public int getVersionCode() {
        return ApplicationAttributes.getInstance(this.mContext).getVersionCode();
    }

    @Override // com.amazon.music.config.Attributes
    public String getPlatformName() {
        return AmazonApplication.getCapabilities().getPlatformName();
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:8:0x0027 -> B:22:0x003c). Please report as a decompilation issue!!! */
    @Override // com.amazon.music.config.Attributes
    public JSONObject getDefaultConfigJSON() {
        JSONObject jSONObject = new JSONObject();
        InputStream inputStreamOpen = null;
        try {
            try {
                try {
                    inputStreamOpen = this.mContext.getResources().getAssets().open("defaultConfig");
                    jSONObject = JsonUtil.stringToJson(IoUtil.streamToString(inputStreamOpen));
                    if (inputStreamOpen != null) {
                        inputStreamOpen.close();
                    }
                } catch (Throwable th) {
                    if (inputStreamOpen != null) {
                        try {
                            inputStreamOpen.close();
                        } catch (IOException e) {
                            Log.warning(TAG, "Failed to close the stream", e);
                        }
                    }
                    throw th;
                }
            } catch (IOException e2) {
                Log.warning(TAG, "Failed to get the bundled default configuration", e2);
                if (inputStreamOpen != null) {
                    inputStreamOpen.close();
                }
                return jSONObject;
            }
        } catch (IOException e3) {
            Log.warning(TAG, "Failed to close the stream", e3);
        }
        return jSONObject;
    }

    @Override // com.amazon.music.config.Attributes
    public String getLocaleLanguage() {
        try {
            Marketplace homeMarketPlace = AccountDetailUtil.get(this.mContext).getHomeMarketPlace();
            Map<Marketplace, String> map = MARKET_TO_LANGUAGE_MAP;
            if (map.containsKey(homeMarketPlace)) {
                return map.get(homeMarketPlace);
            }
        } catch (Exception e) {
            Log.error(TAG, "Exception determining the marketplace", e);
        }
        return MARKET_TO_LANGUAGE_MAP.get(Marketplace.USA);
    }

    @Override // com.amazon.music.config.Attributes
    public String getLocaleCountryCode() {
        try {
            if (MARKET_TO_LANGUAGE_MAP.containsKey(AccountDetailUtil.get(this.mContext).getHomeMarketPlace())) {
                return AccountDetailUtil.getMusicTerritoryOfResidence();
            }
        } catch (Exception e) {
            Log.error(TAG, "Exception determining the marketplace", e);
        }
        return MusicTerritory.US.value;
    }

    public String toString() {
        return getClass().getSimpleName() + " { version: " + getVersion() + ",  versionCode: " + getVersionCode() + ",  versionShort: " + getVersionShort() + ",  platformName: " + getPlatformName() + " }";
    }
}
