package com.amazon.music.inappmessaging.external;

import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.marketplace.Marketplace;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public class Endpoints {
    private static final HashMap<Marketplace, String> MUSIC_ENDPOINT_MAP;
    private static final String SIGNUP_PATH = "/signup/full-catalog";
    private static Endpoints mEndpoints;

    static {
        HashMap<Marketplace, String> map = new HashMap<>();
        MUSIC_ENDPOINT_MAP = map;
        map.put(Marketplace.USA, "https://music.amazon.com");
        map.put(Marketplace.UK, "https://music.amazon.co.uk");
        map.put(Marketplace.GERMANY, "https://music.amazon.de");
        map.put(Marketplace.FRANCE, "https://music.amazon.fr");
        map.put(Marketplace.ITALY, "https://music.amazon.it");
        map.put(Marketplace.SPAIN, "https://music.amazon.es");
        map.put(Marketplace.JAPAN, "https://music.amazon.co.jp");
    }

    public static Endpoints get() {
        if (mEndpoints == null) {
            mEndpoints = new Endpoints();
        }
        return mEndpoints;
    }

    public String getMusicEndpoint() {
        Marketplace marketplace = AccountManagerSingleton.get().getMarketplace();
        HashMap<Marketplace, String> map = MUSIC_ENDPOINT_MAP;
        if (map.containsKey(marketplace)) {
            return map.get(marketplace);
        }
        return map.get(Marketplace.USA);
    }

    public String getSignupEndpoint() {
        return getMusicEndpoint() + SIGNUP_PATH;
    }
}
